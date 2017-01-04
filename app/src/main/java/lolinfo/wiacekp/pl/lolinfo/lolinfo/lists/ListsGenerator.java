package lolinfo.wiacekp.pl.lolinfo.lolinfo.lists;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by debian-piotrek on 25.05.15.
 */
public class ListsGenerator {
    private final String debug_tag = "ListsGeneratorClass";
    public String getVersion(String in){
        String t="";
        try {
            JSONArray jsonArray = new JSONArray(in);
            t= jsonArray.getString(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return t;
    }

    public ArrayList<ItemsList> getItemsData(String in, String version){
        ArrayList<ItemsList>itemsLists = new ArrayList<ItemsList>();

        int id=0;
        int itemID;
        int[] intoBuild;
        int[] fromBuild;
        String title;
        String description;
        int baseGold;
        int totalGold;
        int sellGold;
        String nameImage;
        String linkToImage;

        try {
            JSONObject jsonObject = new JSONObject(in);
            jsonObject=jsonObject.getJSONObject("data");
            JSONArray jsonArray = null;
            Iterator<String> keys = jsonObject.keys();
            while(keys.hasNext()){
                String key = keys.next();

                try{
                    JSONObject value = jsonObject.getJSONObject(key);
                    itemID=Integer.parseInt(key);

                    intoBuild=new int[1]; intoBuild[0]=-1;
                    try{
                        if(value.getJSONArray("into").length()!=0) {
                            intoBuild = new int[value.getJSONArray("into").length()];
                            for (int i = 0; i < value.getJSONArray("into").length(); i++) {
                                intoBuild[i] = value.getJSONArray("into").getInt(i);
                            }
                        }else{Log.d(debug_tag, "no Value INTO - "+intoBuild.length);}
                    }catch (JSONException e){Log.d(debug_tag, "no Value INTO - "+intoBuild.length);}
                    fromBuild=new int[1]; fromBuild[0]=-1;
                    try{
                        if(value.getJSONArray("from").length()!=0) {
                            fromBuild = new int[value.getJSONArray("from").length()];
                            for (int i = 0; i < value.getJSONArray("from").length(); i++) {
                                fromBuild[i] = value.getJSONArray("from").getInt(i);
                            }
                        }else{Log.d(debug_tag, "no Value FROM - "+fromBuild.length);}
                    }catch (JSONException e){Log.d(debug_tag, "no Value FROM - "+fromBuild.length);}

                    title=value.getString("name");
                    description=value.getString("description");
                    baseGold=value.getJSONObject("gold").getInt("base");
                    totalGold=value.getJSONObject("gold").getInt("total");
                    sellGold=value.getJSONObject("gold").getInt("sell");

                    nameImage=value.getJSONObject("image").getString("full");
                    linkToImage="http://ddragon.leagueoflegends.com/cdn/"+version+"/img/item/"+nameImage;

                    Log.d(debug_tag, key);
                    itemsLists.add(new ItemsList(id, itemID, intoBuild, fromBuild, title, description, baseGold, totalGold, sellGold, nameImage, linkToImage));

                    id++;
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return itemsLists;
    }

    public ArrayList<ChampList> getChampData(String in, String version){
        ArrayList<ChampList>champLists = new ArrayList<ChampList>();
        int id=0;
        int champID;
        String name;
        String title;
        String nameImage;
        String linkToImage;
        String lore;
        int infoAttack;
        int infoDefense;
        int infoMagic;
        int infoDifficulty;
        String[] allyTips;
        String[] enemyTips;

        String passiveTitle;
        String passiveDescription;
        String passiveNameImage;
        String passiveLinkToImage;

        String[] spellsTitle;
        String[] spellsDescription;
        String[] spellsNameImage;
        String[] spellsLinkToImage;
        try {
            JSONObject jsonObject = new JSONObject(in);
            jsonObject=jsonObject.getJSONObject("data");
            JSONArray jsonArray = null;
            Iterator<String> keys = jsonObject.keys();
            while(keys.hasNext()){
                String key = keys.next();

                try{
                    JSONObject value = jsonObject.getJSONObject(key);
                    champID=value.getInt("key");
                    name=value.getString("name");
                    title=value.getString("title");
                    nameImage=value.getJSONObject("image").getString("full");
                    linkToImage="http://ddragon.leagueoflegends.com/cdn/"+version+"/img/champion/"+nameImage;
                    lore=value.getString("lore");
                    infoAttack=value.getJSONObject("info").getInt("attack");
                    infoDefense=value.getJSONObject("info").getInt("defense");
                    infoMagic=value.getJSONObject("info").getInt("magic");
                    infoDifficulty=value.getJSONObject("info").getInt("difficulty");

                    //allytips i enemytips
                    jsonArray=value.getJSONArray("allytips");
                    allyTips=new String[jsonArray.length()];
                    for(int j=0; j<jsonArray.length(); j++){
                        allyTips[j]=jsonArray.getString(j);
                    }
                    jsonArray=value.getJSONArray("enemytips");
                    enemyTips=new String[jsonArray.length()];
                    for(int j=0; j<jsonArray.length(); j++){
                        enemyTips[j]=jsonArray.getString(j);
                    }
                    passiveTitle=value.getJSONObject("passive").getString("name");
                    passiveDescription=value.getJSONObject("passive").getString("description");
                    passiveNameImage=value.getJSONObject("passive").getJSONObject("image").getString("full");
                    passiveLinkToImage="http://ddragon.leagueoflegends.com/cdn/"+version+"/img/passive/"+passiveNameImage;

                    spellsTitle= new String[4];
                    spellsDescription= new String[4];
                    spellsNameImage= new String[4];
                    spellsLinkToImage= new String[4];
                    for(int i=0; i<4; i++){
                        spellsTitle[i]=value.getJSONArray("spells").getJSONObject(i).getString("name");
                        spellsDescription[i]=value.getJSONArray("spells").getJSONObject(i).getString("description");
                        spellsNameImage[i]=value.getJSONArray("spells").getJSONObject(i).getJSONObject("image").getString("full");
                        spellsLinkToImage[i]="http://ddragon.leagueoflegends.com/cdn/"+version+"/img/spell/"+spellsNameImage[i];
                    }


                    champLists.add(new ChampList(id, champID, name, title, nameImage, linkToImage,
                            lore, infoAttack, infoDefense, infoMagic, infoDifficulty, allyTips, enemyTips,
                            passiveTitle, passiveDescription, passiveNameImage, passiveLinkToImage,
                            spellsTitle, spellsDescription, spellsNameImage, spellsLinkToImage));
                    Log.d(debug_tag, value.getString("name"));



                    id++;
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return champLists;
    }
}
