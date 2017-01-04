package lolinfo.wiacekp.pl.lolinfo.lolinfo.other;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import lolinfo.wiacekp.pl.lolinfo.lolinfo.lists.ChampList;
import lolinfo.wiacekp.pl.lolinfo.lolinfo.lists.ItemsList;
import lolinfo.wiacekp.pl.lolinfo.lolinfo.lists.OtherList;
import lolinfo.wiacekp.pl.lolinfo.lolinfo.lists.SalesList;
import lolinfo.wiacekp.pl.lolinfo.lolinfo.lists.RotationList;

public class DbAdapter {

    private static final String DEBUG_TAG = "SqLiteManager";

    private static final String[] championColum = {"id", "champId", "name", "title", "nameImage", "linkToImage", "lore", "infoAttack", "infoDefense", "infoMagic", "infoDifficulty", "allyTips", "enemyTips",
                    "passiveTitle", "passiveDescription", "passiveNameImage", "passiveLinkToImage",
                    "spellsTitle", "spellsDescription", "spellsNameImage", "spellsLinkToImage"};
    private static final String[] itemsColumn = {"id", "itemId", "intoBuild", "fromBuild", "title", "description", "baseGold", "totalGold", "sellGold", "nameImage", "linkToImage"};
    private static final String[] rotacjeColumn = {"id", "champId", "nameFile", "name"};//nowosci
    private static final String[] otherColumn={"id", "version", "isFinish"};
    private static final String[] promocjeColumn={"id", "champId", "name", "oldPrice", "newPrice", "nameImage1", "linkToImage1", "nameImage2", "linkToImage2"};

    private static final String championTableName = "Champions";
    private static final String itemsTableName = "Items";
    private static final String rotacjeTableName = "Rotacje";
    private static final String otherTableName = "Other";
    private static final String promocjeTableName = "Promocje";

    //drop table if exists ...

    private static final String dropRotacjeTable = "drop table if exists "+rotacjeTableName;
    private static final String dropItemsTable = "drop table if exists "+itemsTableName;
    private static final String dropChampionTable = "drop table if exists "+championTableName;
    private static final String dropOtherTable = "drop table if exists "+otherTableName;
    private static final String dropPromocjetable = "drop table if exists "+promocjeTableName;

    private static final String createPromocjetable = "create table if not exists "+promocjeTableName+"("+
            promocjeColumn[0]+" integer primary key autoincrement,"+
            promocjeColumn[1]+" integer not null,"+
            promocjeColumn[2]+" text not null,"+
            promocjeColumn[3]+" text not null,"+
            promocjeColumn[4]+" text not null,"+
            promocjeColumn[5]+" text not null,"+
            promocjeColumn[6]+" text not null,"+
            promocjeColumn[7]+" text not null,"+
            promocjeColumn[8]+" text not null);";


    private static final String createOtherTable = "create table if not exists "+otherTableName+"("+
            otherColumn[0]+" integer primary key autoincrement,"+
            otherColumn[1]+" text not null,"+
            otherColumn[2]+" integer default 0);";

    private static final String createRotacjeTable = "create table "+rotacjeTableName+"("+
            rotacjeColumn[0]+" integer primary key autoincrement,"+
            rotacjeColumn[1]+" integer not null," +
            rotacjeColumn[2]+" text not null,"+
            rotacjeColumn[3]+" text not null);";

    private static final String createItemsTable = "create table "+itemsTableName+"("+
            itemsColumn[0]+" integer primary key autoincrement,"+
            itemsColumn[1]+" integer not null,"+
            itemsColumn[2]+" text not null,"+
            itemsColumn[3]+" text not null,"+
            itemsColumn[4]+" text not null,"+
            itemsColumn[5]+" text not null,"+
            itemsColumn[6]+" integer not null,"+
            itemsColumn[7]+" integer not null,"+
            itemsColumn[8]+" integer not null,"+
            itemsColumn[9]+" text not null,"+
            itemsColumn[10]+" text not null);";

    private static final String createChampionTable = "create table "+championTableName+"("+
            championColum[0]+" integer primary key autoincrement,"+
            championColum[1]+" integer not null,"+
            championColum[2]+" text not null,"+
            championColum[3]+" text not null,"+
            championColum[4]+" text not null,"+
            championColum[5]+" text not null,"+
            championColum[6]+" text not null,"+
            championColum[7]+" integer not null,"+
            championColum[8]+" integer not null,"+
            championColum[9]+" integer not null,"+
            championColum[10]+" integer not null,"+
            championColum[11]+" text not null,"+
            championColum[12]+" text not null,"+
            championColum[13]+" text not null,"+    //passive
            championColum[14]+" text not null,"+
            championColum[15]+" text not null,"+
            championColum[16]+" text not null,"+
            championColum[17]+" text not null,"+    //spells
            championColum[18]+" text not null,"+
            championColum[19]+" text not null,"+
            championColum[20]+" text not null);";


    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "loldatabase.db";

    private SQLiteDatabase db;
    private Context context;
    private DatabaseHelper dbHelper;

    private static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context, String name,
                              CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(createOtherTable);
            db.execSQL(createChampionTable);
            db.execSQL(createItemsTable);
            db.execSQL(createRotacjeTable);
            db.execSQL(createPromocjetable);
            Log.d(DEBUG_TAG, createChampionTable);
            Log.d(DEBUG_TAG, "Database creating...");
            Log.d(DEBUG_TAG, "Tables ver." + DB_VERSION + " created");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d(DEBUG_TAG, createChampionTable);
            db.execSQL(dropChampionTable);
            db.execSQL(dropItemsTable);
            db.execSQL(dropRotacjeTable);
        }
    }

    public void dropOtherTable(){
        db.execSQL(dropOtherTable);
    }

    public OtherList getLastValueFromOtherList() {
        db.execSQL(createOtherTable);
        Cursor cursor = db.query(otherTableName, otherColumn, null, null, null, null, null);
        OtherList otherList = null;
        if(cursor != null && cursor.moveToLast()) {
            int id = cursor.getInt(0);
            String version = cursor.getString(1);
            boolean completed = cursor.getInt(2) > 0 ? true : false;
            otherList = new OtherList(id, version, completed);
        }else{
            otherList = new OtherList(0, "", false);
        }
        return otherList;
    }

    public ArrayList<SalesList> getPromocjeList(){
        Cursor c = db.query(promocjeTableName, promocjeColumn, null, null, null, null, null);
        ArrayList<SalesList> salesLists = new ArrayList<>();
        c.moveToFirst();
        for(int i=0; i<c.getCount(); i++){
            salesLists.add(new SalesList(c.getInt(0), c.getInt(1), c.getString(2), c.getString(3)
                    , c.getString(4), c.getString(5), c.getString(6), c.getString(7), c.getString(8)));

            c.moveToNext();
        }

        return salesLists;
    }

    public ArrayList<ItemsList> getItemsList(){
        Cursor c = db.query(itemsTableName, itemsColumn, null, null, null, null, itemsColumn[4]);
        ArrayList<ItemsList>itemsLists = new ArrayList<>();
        c.moveToFirst();
        for(int i=0; i<c.getCount(); i++){
            itemsLists.add(new ItemsList(c.getInt(0), c.getInt(1), convertStringToArrayInt(c.getString(2)),
                    convertStringToArrayInt(c.getString(3)), c.getString(4), c.getString(5), c.getInt(6),
                    c.getInt(7), c.getInt(8), c.getString(9), c.getString(10)));
            c.moveToNext();
        }

        return itemsLists;
    }

    public ArrayList<RotationList> getRotationList(){
        Cursor c = db.query(rotacjeTableName, rotacjeColumn, null, null, null, null, null);
        ArrayList<RotationList> rotationLists = new ArrayList<>();
        c.moveToFirst();
        for(int i=0; i<c.getCount(); i++){
            rotationLists.add(new RotationList(c.getInt(0), c.getInt(1), c.getString(2), c.getString(3)));

            c.moveToNext();
        }

        return rotationLists;
    }

    public ChampList getChampionListByChampId(int ChampId){
        String where = championColum[1] + "=" + ChampId;
        Cursor c = db.query(championTableName, championColum, where, null, null, null, null);
        ChampList champList= null;
        if(c != null && c.moveToFirst()) {
            champList=new ChampList(c.getInt(0), c.getInt(1),
                    c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),
                    c.getInt(7),c.getInt(8),c.getInt(9),c.getInt(10), convertStringToArray(c.getString(11)),convertStringToArray(c.getString(12)),
                    c.getString(13),c.getString(14),c.getString(15),c.getString(16),
                    convertStringToArray(c.getString(17)),convertStringToArray(c.getString(18)),convertStringToArray(c.getString(19)),convertStringToArray(c.getString(20)));
        }
        return champList;
    }

    public ArrayList<ChampList> getChampionList() {
        Cursor c = db.query(championTableName, championColum, null, null, null, null, championColum[2]);
        ArrayList<ChampList>champLists = new ArrayList<>();
        c.moveToFirst();
        for(int i=0; i<c.getCount(); i++){
            champLists.add(new ChampList(c.getInt(0), c.getInt(1),
                    c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),
                    c.getInt(7),c.getInt(8),c.getInt(9),c.getInt(10), convertStringToArray(c.getString(11)),convertStringToArray(c.getString(12)),
                    c.getString(13),c.getString(14),c.getString(15),c.getString(16),
                    convertStringToArray(c.getString(17)),convertStringToArray(c.getString(18)),convertStringToArray(c.getString(19)),convertStringToArray(c.getString(20))));

            c.moveToNext();
        }

        return champLists;
    }

    public void insertOther(String version, boolean finish){
        int isFinish=(finish)? 1:0;
        ContentValues newValues = new ContentValues();
        newValues.put(otherColumn[1], version);
        newValues.put(otherColumn[2], isFinish);
        db.insert(otherTableName, null, newValues);
    }

    public void insertPromocje(ArrayList<SalesList> salesLists){
        db.execSQL(dropPromocjetable);
        db.execSQL(createPromocjetable);
        Log.d("&&&&", "Pobieram promocje");
        ContentValues newValues;
        for(int i = 0; i< salesLists.size(); i++){
            newValues= new ContentValues();
            newValues.put(promocjeColumn[0], salesLists.get(i).getId());
            newValues.put(promocjeColumn[1], salesLists.get(i).getChampId());
            newValues.put(promocjeColumn[2], salesLists.get(i).getName());
            newValues.put(promocjeColumn[3], salesLists.get(i).getOldPrice());
            newValues.put(promocjeColumn[4], salesLists.get(i).getNewPrice());
            newValues.put(promocjeColumn[5], salesLists.get(i).getNameImage1());
            newValues.put(promocjeColumn[6], salesLists.get(i).getLinkToImage1());
            newValues.put(promocjeColumn[7], salesLists.get(i).getNameImage2());
            newValues.put(promocjeColumn[8], salesLists.get(i).getLinkToimage2());
            db.insert(promocjeTableName, null, newValues);
        }
    }

    public void insertRotacje(ArrayList<RotationList> rotationLists){
        db.execSQL(dropRotacjeTable);
        db.execSQL(createRotacjeTable);
        ContentValues newValues;
        for(int i = 0; i< rotationLists.size(); i++){
            newValues = new ContentValues();
            newValues.put(rotacjeColumn[0], rotationLists.get(i).getId());
            newValues.put(rotacjeColumn[1], rotationLists.get(i).getChampId());
            newValues.put(rotacjeColumn[2], rotationLists.get(i).getNameImage());
            newValues.put(rotacjeColumn[3], rotationLists.get(i).getName());
            db.insert(rotacjeTableName, null, newValues);
        }
    }


    public void insertItems(ArrayList<ItemsList>itemsLists){
        db.execSQL(dropItemsTable);
        db.execSQL(createItemsTable);
        ContentValues newValues;
        for(int i=0; i<itemsLists.size(); i++){
            newValues = new ContentValues();
            newValues.put(itemsColumn[0], itemsLists.get(i).getId());
            newValues.put(itemsColumn[1], itemsLists.get(i).getItemID());
            newValues.put(itemsColumn[2], convertArrayToString(itemsLists.get(i).getIntoBuild()));
            newValues.put(itemsColumn[3], convertArrayToString(itemsLists.get(i).getFromBuild()));
            newValues.put(itemsColumn[4], itemsLists.get(i).getTitle());
            newValues.put(itemsColumn[5], itemsLists.get(i).getDescription());

            newValues.put(itemsColumn[6], itemsLists.get(i).getBaseGold());
            newValues.put(itemsColumn[7], itemsLists.get(i).getTotalGold());
            newValues.put(itemsColumn[8], itemsLists.get(i).getSellGold());
            newValues.put(itemsColumn[9], itemsLists.get(i).getNameImage());
            newValues.put(itemsColumn[10], itemsLists.get(i).getLinkToImage());
            db.insert(itemsTableName, null, newValues);
        }
    }

    public void insertChampions(ArrayList<ChampList>champLists) {
        db.execSQL(dropChampionTable);
        db.execSQL(createChampionTable);
        ContentValues newValues;
        for(int i=0; i<champLists.size(); i++){
            newValues = new ContentValues();
            newValues.put(championColum[0], champLists.get(i).getId());
            newValues.put(championColum[1], champLists.get(i).getChampID());
            newValues.put(championColum[2], champLists.get(i).getName());
            newValues.put(championColum[3], champLists.get(i).getTitle());
            newValues.put(championColum[4], champLists.get(i).getNameImage());
            newValues.put(championColum[5], champLists.get(i).getLinkToImage());
            newValues.put(championColum[6], champLists.get(i).getLore());

            newValues.put(championColum[7], champLists.get(i).getInfoAttack());
            newValues.put(championColum[8], champLists.get(i).getInfoDefense());
            newValues.put(championColum[9], champLists.get(i).getInfoMagic());
            newValues.put(championColum[10], champLists.get(i).getInfoDifficulty());

            newValues.put(championColum[11], convertArrayToString(champLists.get(i).getAllyTips()));
            newValues.put(championColum[12], convertArrayToString(champLists.get(i).getEnemyTips()));

            newValues.put(championColum[13], champLists.get(i).getPassiveTitle());
            newValues.put(championColum[14], champLists.get(i).getPassiveDescription());
            newValues.put(championColum[15], champLists.get(i).getPassiveNameImage());
            newValues.put(championColum[16], champLists.get(i).getPassiveLinkToImage());

            newValues.put(championColum[17], convertArrayToString(champLists.get(i).getSpellsTitle()));
            newValues.put(championColum[18], convertArrayToString(champLists.get(i).getSpellsDescription()));
            newValues.put(championColum[19], convertArrayToString(champLists.get(i).getSpellsNameImage()));
            newValues.put(championColum[20], convertArrayToString(champLists.get(i).getSpellsLinkToImage()));
            db.insert(championTableName, null, newValues);
        }
    }

    public DbAdapter(Context context) {
        this.context = context;
    }

    public DbAdapter open(){
        Log.d(DEBUG_TAG, createChampionTable);
        Log.d(DEBUG_TAG, createItemsTable);
        Log.d(DEBUG_TAG, createRotacjeTable);
        dbHelper = new DatabaseHelper(context, DB_NAME, null, DB_VERSION);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLException e) {
            db = dbHelper.getReadableDatabase();
        }

        return this;
    }

    public void close() {
        dbHelper.close();
    }


    private static String strSeparator = "__,__";
    private String convertArrayToString(String[] array){
        String str = "";
        for (int i = 0;i<array.length; i++) {
            str = str+array[i];

            if(i<array.length-1){
                str = str+strSeparator;
            }
        }
        return str;
    }
    private String convertArrayToString(int[] array){
        String str = "";
        for (int i = 0;i<array.length; i++) {
            str = str+array[i];

            if(i<array.length-1){
                str = str+strSeparator;
            }
        }
        return str;
    }
    private String[] convertStringToArray(String str){
        String[] arr = str.split(strSeparator);
        return arr;
    }
    private int[] convertStringToArrayInt(String str){
        String[] arr = str.split(strSeparator);
        int[] out=new int[arr.length];
        for(int i=0; i<arr.length; i++){
            out[i]=Integer.parseInt(arr[i]);
        }
        return out;
    }
}
