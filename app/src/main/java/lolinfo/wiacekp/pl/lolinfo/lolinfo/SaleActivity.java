package lolinfo.wiacekp.pl.lolinfo.lolinfo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import lolinfo.wiacekp.pl.lolinfo.lolinfo.adapters.SaleAdapter;
import lolinfo.wiacekp.pl.lolinfo.lolinfo.lists.SalesList;
import lolinfo.wiacekp.pl.lolinfo.lolinfo.other.DbAdapter;


public class SaleActivity extends ActionBarActivity {
    private ArrayList<SalesList> salesLists = new ArrayList<>();
    private SaleAdapter saleAdapter;
    private ListView listView;
    private DbAdapter dbAdapter = new DbAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);
        listView=(ListView)findViewById(R.id.saleActivityLV);
        dbAdapter.open();
        salesLists =dbAdapter.getPromocjeList();
        dbAdapter.close();
        saleAdapter = new SaleAdapter(this, salesLists, this.getWindowManager().getDefaultDisplay().getWidth());
        listView.setAdapter(saleAdapter);
    }

}
