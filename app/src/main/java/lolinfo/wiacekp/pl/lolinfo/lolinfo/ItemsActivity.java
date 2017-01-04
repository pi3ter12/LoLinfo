package lolinfo.wiacekp.pl.lolinfo.lolinfo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import lolinfo.wiacekp.pl.lolinfo.lolinfo.adapters.ItemsAdapter;
import lolinfo.wiacekp.pl.lolinfo.lolinfo.lists.ItemsList;
import lolinfo.wiacekp.pl.lolinfo.lolinfo.other.DbAdapter;


public class ItemsActivity extends ActionBarActivity {
    private DbAdapter dbAdapter = new DbAdapter(this);
    private ArrayList<ItemsList> itemsLists = new ArrayList<>();
    private GridView gridView;
    private ItemsAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        dbAdapter.open();
        itemsLists=dbAdapter.getItemsList();
        dbAdapter.close();
        gridView=(GridView) findViewById(R.id.gridview);
        itemsAdapter = new ItemsAdapter(itemsLists, this);
        gridView.setAdapter(itemsAdapter);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), itemsLists.get(position).getTitle(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), ItemDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("selectedId", position);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


}
