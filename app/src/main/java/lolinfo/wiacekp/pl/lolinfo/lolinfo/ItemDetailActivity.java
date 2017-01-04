package lolinfo.wiacekp.pl.lolinfo.lolinfo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import lolinfo.wiacekp.pl.lolinfo.lolinfo.adapters.ItemDetailAdapter;
import lolinfo.wiacekp.pl.lolinfo.lolinfo.adapters.ItemsAdapter;
import lolinfo.wiacekp.pl.lolinfo.lolinfo.lists.ItemsList;
import lolinfo.wiacekp.pl.lolinfo.lolinfo.other.DbAdapter;


public class ItemDetailActivity extends ActionBarActivity {
    private ArrayList<ItemsList>itemsLists = new ArrayList<>();
    private TextView tvTitle, tvTotalGold, tvSellGold, tvBaseGold, tvDescription, tvIntoBuild, tvFromBuild;
    private ImageView ivMain;
    private ListView lvIntoBuild, lvFromBuild;
    private int selectedId=0;
    private DbAdapter dbAdapter = new DbAdapter(this);
    private ItemDetailAdapter itemDetailAdapterIntoBuild, itemDetailAdapterFromBuild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        dbAdapter.open();
        itemsLists=dbAdapter.getItemsList();
        dbAdapter.close();
        Bundle bundle = getIntent().getExtras();
        selectedId = bundle.getInt("selectedId");
        tvTitle=(TextView)findViewById(R.id.itemDetailTitle);
        tvTotalGold=(TextView)findViewById(R.id.itemDetailTVtotalGold);
        tvSellGold=(TextView)findViewById(R.id.itemDetailTVsellGold);
        tvBaseGold=(TextView)findViewById(R.id.itemDetailTVbaseGold);
        tvDescription=(TextView)findViewById(R.id.itemDetailDescription);
        ivMain=(ImageView)findViewById(R.id.itemDetailIVmain);
        tvIntoBuild=(TextView)findViewById(R.id.itemDetailTVintoBuild);
        lvIntoBuild = (ListView)findViewById(R.id.itemDetailLVInto);
        tvFromBuild=(TextView)findViewById(R.id.itemDetailTVFromBuild);
        lvFromBuild = (ListView)findViewById(R.id.itemDetailLVFrom);


        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;

            Bitmap bitmap = BitmapFactory.decodeFile(this.getFilesDir().getAbsolutePath() + "/" + itemsLists.get(selectedId).getNameImage(), options);

            ivMain.setImageBitmap(bitmap);
        }catch (Exception e){
            e.printStackTrace();
        }
        tvTitle.setText(itemsLists.get(selectedId).getTitle());
        this.setTitle(itemsLists.get(selectedId).getTitle());
        tvTotalGold.setText(getResources().getString(R.string.itemListDetailTotal)+" - "+itemsLists.get(selectedId).getTotalGold()+"");
        tvSellGold.setText(getResources().getString(R.string.itemListDetailSell)+" - "+itemsLists.get(selectedId).getSellGold()+"");
        tvBaseGold.setText(getResources().getString(R.string.itemListDetailBase)+" - "+itemsLists.get(selectedId).getBaseGold()+"");
        tvDescription.setText(deleteHtmlCode(itemsLists.get(selectedId).getDescription())+"");
        if(getItemsListsForAdapter(false).size()==0){
            tvIntoBuild.setVisibility(View.GONE);
            lvIntoBuild.setVisibility(View.GONE);
        }else {
            itemDetailAdapterIntoBuild = new ItemDetailAdapter(getItemsListsForAdapter(false), this);
            lvIntoBuild.setAdapter(itemDetailAdapterIntoBuild);
        }
        if(getItemsListsForAdapter(true).size()==0){
            tvFromBuild.setVisibility(View.GONE);
            lvFromBuild.setVisibility(View.GONE);
        }else {
            itemDetailAdapterFromBuild = new ItemDetailAdapter(getItemsListsForAdapter(true), this);
            lvFromBuild.setAdapter(itemDetailAdapterFromBuild);
        }
        tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvDescription.getVisibility()==View.VISIBLE){
                    tvDescription.setVisibility(View.GONE);
                }else {
                    tvDescription.setVisibility(View.VISIBLE);
                }
            }
        });
        tvIntoBuild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lvIntoBuild.getVisibility()==View.VISIBLE)
                    lvIntoBuild.setVisibility(View.GONE);
                else
                    lvIntoBuild.setVisibility(View.VISIBLE);
            }
        });
        tvFromBuild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lvFromBuild.getVisibility()==View.VISIBLE)
                    lvFromBuild.setVisibility(View.GONE);
                else
                    lvFromBuild.setVisibility(View.VISIBLE);
            }
        });
        lvIntoBuild.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openActivity(false, position);
            }
        });
        lvFromBuild.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openActivity(true, position);
            }
        });
    }

    private void openActivity(boolean from, int position){
        Intent intent = new Intent(getApplicationContext(), ItemDetailActivity.class);
        Bundle bundle2 = new Bundle();
        int itemID = getItemsListsForAdapter(from).get(position).getItemID();
        int sID=0;
        for(int i=0; i<itemsLists.size(); i++){
            if(itemsLists.get(i).getItemID()==itemID){
                sID=i;
                break;
            }
        }
        bundle2.putInt("selectedId", sID);
        intent.putExtras(bundle2);
        startActivity(intent);
    }

    private ArrayList<ItemsList> getItemsListsForAdapter(boolean from){
        ArrayList<ItemsList>intoBuild = new ArrayList<>();

        int[] items = (from)?itemsLists.get(selectedId).getIntoBuild():itemsLists.get(selectedId).getFromBuild();

        for(int i=0; i<items.length; i++){
            for(int j=0; j<itemsLists.size(); j++){
                if(itemsLists.get(j).getItemID()==items[i]){
                    intoBuild.add(itemsLists.get(j));
                    break;
                }
            }
        }

        return intoBuild;
    }


    private String deleteHtmlCode(String input){
        String out="";
        input+=" ";
        boolean skipThis=false;
        boolean start=false;

        for(int i=0; i<input.length(); i++){
            if(input.charAt(i)=='<'){
                skipThis=true;
            }

            if(!skipThis){
                start=true;
                out+=input.charAt(i);
            }

            if(input.charAt(i)=='>'){
                skipThis=false;
                if((input.charAt(i+1)!='<')&&(start)){
                    out+="\n";
                }
            }
        }

        return out;
    }
}
