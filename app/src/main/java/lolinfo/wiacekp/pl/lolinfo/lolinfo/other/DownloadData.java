package lolinfo.wiacekp.pl.lolinfo.lolinfo.other;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import lolinfo.wiacekp.pl.lolinfo.lolinfo.R;
import lolinfo.wiacekp.pl.lolinfo.lolinfo.lists.ChampList;
import lolinfo.wiacekp.pl.lolinfo.lolinfo.lists.ItemsList;
import lolinfo.wiacekp.pl.lolinfo.lolinfo.lists.ListsGenerator;
import lolinfo.wiacekp.pl.lolinfo.lolinfo.lists.SalesList;
import lolinfo.wiacekp.pl.lolinfo.lolinfo.lists.RotationList;

/**
 * Created by Piotrek on 2017-01-03.
 */

public class DownloadData {
    private Activity act;
    private Context ctx;

    private DbAdapter dbAdapter;

    private final String debug_tag="DownloadDataClass";

    private ProgressDialog progress;

    private ListsGenerator listsGenerator = new ListsGenerator();
    private AlertDialog alertDialog;

    public DownloadData(Activity act, Context ctx) {
        this.act = act;
        this.ctx = ctx;
        dbAdapter = new DbAdapter(ctx);


        alertDialog = new AlertDialog.Builder(act).create();
        alertDialog.setTitle("Problem z połączeniem");
        alertDialog.setMessage("Nie masz połączenia z internetem, aby pobrać dane włącz internet.");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
    }

    private String version="";
    private ArrayList<String> toDownloadImage = new ArrayList<String>();

    public void actionLoadData(){
        progress = ProgressDialog.show(act, ctx.getResources().getString(R.string.text_pleaseWait),
                ctx.getResources().getString(R.string.text_downloadData), true);

        boolean continueDownload=false;
        if(checkInternetConnection()) {
            toDownloadImage.add(ctx.getFilesDir().getAbsolutePath());
            new GetJsonData() {
                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    version = listsGenerator.getVersion(s);
                    if ((!dbAdapter.getLastValueFromOtherList().getVersion().equals(version)) || (!dbAdapter.getLastValueFromOtherList().getIsFinish())) {
                        loadChampData();
                        Log.d(debug_tag, "versions is difference, I download data");
                        dbAdapter.insertOther(version, false);
                    } else {
                        Log.d(debug_tag, "Versions is the same");
                        LoadRotation();
                    }

                }
            }.execute(ctx.getResources().getString(R.string.link_version));
        }else{
            progress.dismiss();
            alertDialog.show();
        }
    }

    private void LoadRotation(){
        new GetJsonData(){
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                ArrayList<RotationList> rotationLists =new ArrayList<RotationList>();
                try {
                    JSONArray jsonArray = new JSONArray(s);
                    for(int i=0; i<jsonArray.length(); i++){
                        rotationLists.add(new RotationList(i, jsonArray.getInt(i),
                                dbAdapter.getChampionListByChampId(jsonArray.getInt(i)).getName()+"_0.jpg",
                                dbAdapter.getChampionListByChampId(jsonArray.getInt(i)).getName()));
                        Log.d(debug_tag, dbAdapter.getChampionListByChampId(jsonArray.getInt(i)).getName()+"_0.jpg");
                    }
                    dbAdapter.insertRotacje(rotationLists);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                LoadPromocje();
            }
        }.execute(ctx.getResources().getString(R.string.link_rotation));

    }
    private void LoadPromocje(){
        new GetJsonData(){
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                ArrayList<SalesList> salesLists =new ArrayList<SalesList>();
                try {
                    JSONArray jsonArray = new JSONArray(s);
                    for(int i=0; i<jsonArray.length(); i++){
                        salesLists.add(new SalesList(jsonArray.getJSONObject(i).getInt("id"), jsonArray.getJSONObject(i).getInt("champId"),
                                jsonArray.getJSONObject(i).getString("name"),jsonArray.getJSONObject(i).getString("oldPrice"),
                                jsonArray.getJSONObject(i).getString("newPrice"),jsonArray.getJSONObject(i).getString("nameImage1"),
                                jsonArray.getJSONObject(i).getString("linkToImage1"),jsonArray.getJSONObject(i).getString("nameImage2"),
                                jsonArray.getJSONObject(i).getString("linkToImage2")));
                    }
                    dbAdapter.insertPromocje(salesLists);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                downloadRotationPromocjeSplashArt();
            }
        }.execute(ctx.getResources().getString(R.string.link_sale));
    }

    private void downloadRotationPromocjeSplashArt(){
        ArrayList<RotationList> rotationLists =new ArrayList<>();
        ArrayList<SalesList> salesLists =new ArrayList<>();
        rotationLists = dbAdapter.getRotationList();
        salesLists =dbAdapter.getPromocjeList();
        final String[] array = new String[(2* rotationLists.size())+(4* salesLists.size())+1];
        array[0]=ctx.getFilesDir().getAbsolutePath();
        for(int i = 0, j = 1; i< rotationLists.size(); i++, j+=2){
            array[j+1]= rotationLists.get(i).getNameImage();
            array[j]=ctx.getResources().getString(R.string.link_splash)+ rotationLists.get(i).getNameImage();
        }
        for(int i = 0, j = (rotationLists.size()*2)+1; i< salesLists.size(); i++, j+=4){
            array[j]= salesLists.get(i).getLinkToImage1();
            array[j+1]= salesLists.get(i).getNameImage1();
            array[j+2]= salesLists.get(i).getLinkToimage2();
            array[j+3]= salesLists.get(i).getNameImage2();
        }
        new DownloadImages(){
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Log.d("********************", "Downloaded - " + array.length);
                dbAdapter.insertOther(version, true);
                progress.dismiss();
            }
        }.execute(array);
    }

    private void loadChampData(){
        Log.d(debug_tag, ctx.getResources().getString(R.string.link_start) + version + ctx.getResources().getString(R.string.link_championsFull));
        new GetJsonData(){
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                ArrayList<ChampList> champLists = new ArrayList<ChampList>();
                champLists=listsGenerator.getChampData(s,version);
                dbAdapter.insertChampions(champLists);
                for(int i=0; i<champLists.size(); i++){
                    toDownloadImage.add(champLists.get(i).getLinkToImage());
                    toDownloadImage.add(champLists.get(i).getNameImage());

                    toDownloadImage.add(champLists.get(i).getPassiveLinkToImage());
                    toDownloadImage.add(champLists.get(i).getPassiveNameImage());
                    for(int j=0; j<champLists.get(i).getSpellsNameImage().length; j++){
                        toDownloadImage.add(champLists.get(i).getSpellsLinkToImage()[j]);
                        toDownloadImage.add(champLists.get(i).getSpellsNameImage()[j]);
                    }
                }
                champLists.clear();
                loadItemsData();
            }
        }.execute(ctx.getResources().getString(R.string.link_start) + version + ctx.getResources().getString(R.string.link_championsFull));
    }

    private void loadItemsData(){
        new GetJsonData(){
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                ArrayList<ItemsList>itemsLists = new ArrayList<ItemsList>();
                itemsLists=listsGenerator.getItemsData(s, version);
                dbAdapter.insertItems(itemsLists);
                for(int i=0; i<itemsLists.size(); i++){
                    toDownloadImage.add(itemsLists.get(i).getLinkToImage());
                    toDownloadImage.add(itemsLists.get(i).getNameImage());
                }
                itemsLists.clear();
                downloadImages();
            }
        }.execute(ctx.getResources().getString(R.string.link_start) + version + ctx.getResources().getString(R.string.link_items));
    }


    private void downloadImages(){
        final String[] toAT = new String[toDownloadImage.size()];

        for(int i=0; i<toDownloadImage.size(); i++)
            toAT[i]=toDownloadImage.get(i);

        toDownloadImage.clear();

        new DownloadImages(){
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Log.d("********************", "Downloaded - " + toAT.length);
                LoadRotation();
            }
        }.execute(toAT);//*/
    }

    private boolean checkInternetConnection(){
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            return false;
        } else
            return true;
    }

    public void openCloseDbAdapter(boolean open){
        if(open){
            dbAdapter.open();
        }else {
            dbAdapter.close();
        }
    }
}
