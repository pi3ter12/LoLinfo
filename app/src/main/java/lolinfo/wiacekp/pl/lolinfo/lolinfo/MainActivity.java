package lolinfo.wiacekp.pl.lolinfo.lolinfo;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.net.*;
import android.os.*;
import android.support.v7.app.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import lolinfo.wiacekp.pl.lolinfo.lolinfo.lists.*;
import lolinfo.wiacekp.pl.lolinfo.lolinfo.other.*;
import org.json.*;


public class MainActivity extends ActionBarActivity {

    private final String debug_tag="MainActivityClass";
    private CharSequence mTitle;

	private ImageButton ibChamp, ibItems, ibRotation, ibSale;

    private DownloadData downloadData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		ibChamp= (ImageButton) findViewById(R.id.ChampionButton);
		ibItems=(ImageButton)findViewById(R.id.ItemsButton);
		ibRotation=(ImageButton)findViewById(R.id.rotationButton);
		ibSale=(ImageButton)findViewById(R.id.saleButton);
		
		downloadData = new DownloadData(this, this);
    }

    public void MainActivityOnClick(View v){
        Intent intent;
        if(v==findViewById(R.id.ChampionButton)){
            intent= new Intent(this, ChampionActivity.class);
            startActivity(intent);
        }else if(v == findViewById(R.id.ItemsButton)){
            intent= new Intent(this, ItemsActivity.class);
            startActivity(intent);
        }else if(v == findViewById(R.id.rotationButton)){
            intent= new Intent(this, RotationActivity.class);
            startActivity(intent);
        }else if(v == findViewById(R.id.saleButton)){
            intent= new Intent(this, SaleActivity.class);
            startActivity(intent);
        }else if(v == findViewById(R.id.helpButton)){
            intent= new Intent(this, HelpActivity.class);
            startActivity(intent);
        }else if(v == findViewById(R.id.loadDataButton)){
            downloadData.actionLoadData();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        downloadData.openCloseDbAdapter(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        downloadData.openCloseDbAdapter(false);
    }

    







}
