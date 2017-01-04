package lolinfo.wiacekp.pl.lolinfo.lolinfo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ChampionActivityDetail extends ActionBarActivity {
    private String name="";
    private String title="";
    private String nameImage="";
    private String lore="";
    private int iAttack=0;
    private int iDefense=0;
    private int iMagic=0;
    private int iDifficulty=0;
    private String[] allytips;
    private String[] enemyTips;
    private String pTitle="";
    private String pDescription="";
    private String pNameImage="";
    private String[] sTitle;
    private String[] sDescription;
    private String[] sNameImage;

    private ImageView ivChamp;
    private TextView tvName, tvTitle;
    private ImageView ivPassive, ivSpell1, ivSpell2, ivSpell3, ivSpell4;
    private TextView tvPTitle, tvPDescription, tvS1Title, tvS1Description, tvS2Title, tvS2Description, tvS3Title, tvS3Description, tvS4Title, tvS4Description;
    private TextView tvAttack, tvDefense, tvMagic, tvDifficulty;
    private TextView tvAllyTips, tvEnemyTips, tvLore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champion_activity_detail);

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        getFromBundle(getIntent().getExtras());

        this.setTitle(name);

        ivChamp= (ImageView)findViewById(R.id.champDetailIVchamp);
        tvName=(TextView)findViewById(R.id.champDetailTVname);
        tvTitle=(TextView)findViewById(R.id.champDetailTVtitle);

        tvPTitle=(TextView)findViewById(R.id.champDetailTVpassiveTitle);
        tvPDescription=(TextView)findViewById(R.id.champDetailTVpassiveDescription);
        tvS1Title=(TextView)findViewById(R.id.champDetailTVspell1Title);
        tvS1Description=(TextView)findViewById(R.id.champDetailTVspell1Description);
        tvS2Title=(TextView)findViewById(R.id.champDetailTVspell2Title);
        tvS2Description=(TextView)findViewById(R.id.champDetailTVspell2Description);
        tvS3Title=(TextView)findViewById(R.id.champDetailTVspell3Title);
        tvS3Description=(TextView)findViewById(R.id.champDetailTVspell3Description);
        tvS4Title=(TextView)findViewById(R.id.champDetailTVspell4Title);
        tvS4Description=(TextView)findViewById(R.id.champDetailTVspell4Description);

        ivPassive= (ImageView)findViewById(R.id.champDetailIVpssive);
        ivSpell1= (ImageView)findViewById(R.id.champDetailIVspell1);
        ivSpell2= (ImageView)findViewById(R.id.champDetailIVspell2);
        ivSpell3= (ImageView)findViewById(R.id.champDetailIVspell3);
        ivSpell4= (ImageView)findViewById(R.id.champDetailIVspell4);

        tvAttack=(TextView)findViewById(R.id.champDetailTVAttack);
        tvDefense=(TextView)findViewById(R.id.champDetailTVDefense);
        tvMagic=(TextView)findViewById(R.id.champDetailTVMagic);
        tvDifficulty=(TextView)findViewById(R.id.champDetailTVDifficulty);

        tvAllyTips=(TextView)findViewById(R.id.champDetailTVallyTips);
        tvEnemyTips=(TextView)findViewById(R.id.champDetailTVenemyTips);
        tvLore=(TextView)findViewById(R.id.champDetailTVlore);

        try {
            Bitmap bitmap = BitmapFactory.decodeFile(this.getFilesDir().getAbsolutePath() + "/" + nameImage, options);
            ivChamp.setImageBitmap(bitmap);
            bitmap = BitmapFactory.decodeFile(this.getFilesDir().getAbsolutePath() + "/" + pNameImage, options);
            ivPassive.setImageBitmap(bitmap);
            bitmap = BitmapFactory.decodeFile(this.getFilesDir().getAbsolutePath() + "/" + sNameImage[0], options);
            ivSpell1.setImageBitmap(bitmap);
            bitmap = BitmapFactory.decodeFile(this.getFilesDir().getAbsolutePath() + "/" + sNameImage[1], options);
            ivSpell2.setImageBitmap(bitmap);
            bitmap = BitmapFactory.decodeFile(this.getFilesDir().getAbsolutePath() + "/" + sNameImage[2], options);
            ivSpell3.setImageBitmap(bitmap);
            bitmap = BitmapFactory.decodeFile(this.getFilesDir().getAbsolutePath() + "/" + sNameImage[3], options);
            ivSpell4.setImageBitmap(bitmap);
        }catch (Exception e){e.printStackTrace();}
        tvName.setText(name);
        tvTitle.setText(title);

        tvPTitle.setText(pTitle);
        tvPDescription.setText(usunNawiasy(pDescription));
        tvS1Title.setText(sTitle[0]);
        tvS1Description.setText(usunNawiasy(sDescription[0]));
        tvS2Title.setText(sTitle[1]);
        tvS2Description.setText(usunNawiasy(sDescription[1]));
        tvS3Title.setText(sTitle[2]);
        tvS3Description.setText(usunNawiasy(sDescription[2]));
        tvS4Title.setText(sTitle[3]);
        tvS4Description.setText(usunNawiasy(sDescription[3]));

        tvAttack.setText(iAttack+getResources().getString(R.string.naDziesiec));
        tvDefense.setText(iDefense+getResources().getString(R.string.naDziesiec));
        tvMagic.setText(iMagic+getResources().getString(R.string.naDziesiec));
        tvDifficulty.setText(iDifficulty+getResources().getString(R.string.naDziesiec));

        String toTips="";
        for(int i=0; i<allytips.length; i++){
            if(i!=0)
                toTips+="\n\n";
            toTips+=usunNawiasy(allytips[i]);
        }
        tvAllyTips.setText(toTips);
        toTips="";
        for(int i=0; i<enemyTips.length; i++){
            if(i!=0)
                toTips+="\n\n";
            toTips+=usunNawiasy(enemyTips[i]);
        }
        tvEnemyTips.setText(toTips);
        tvLore.setText(usunNawiasy(lore));
    }


    private void getFromBundle(Bundle bundle){
        name = bundle.getString("name");
        title = bundle.getString("title");
        nameImage = bundle.getString("nameImage");
        lore = bundle.getString("lore");
        iAttack = bundle.getInt("iAttack");
        iDefense = bundle.getInt("iDefense");
        iMagic = bundle.getInt("iMagic");
        iDifficulty = bundle.getInt("iDifficulty");
        allytips = bundle.getStringArray("allyTips");
        enemyTips = bundle.getStringArray("enemyTips");
        pTitle = bundle.getString("passiveTitle");
        pDescription = bundle.getString("passiveDescription");
        pNameImage = bundle.getString("passiveNameImage");
        sTitle = bundle.getStringArray("spellTitle");
        sDescription = bundle.getStringArray("spellDescription");
        sNameImage = bundle.getStringArray("spellNameImage");
    }

    private String usunNawiasy(String input){
        String out="";
        boolean skipThis=false;
        for(int i=0; i<input.length(); i++){
            if(input.charAt(i)=='<'){
                skipThis=true;
            }
            if(skipThis==false){
                out+=input.charAt(i);
            }
            if(input.charAt(i)=='>'){
                skipThis=false;
            }
        }
        return out;
    }
}
