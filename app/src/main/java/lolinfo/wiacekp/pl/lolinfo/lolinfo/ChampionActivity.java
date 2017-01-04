package lolinfo.wiacekp.pl.lolinfo.lolinfo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import lolinfo.wiacekp.pl.lolinfo.lolinfo.adapters.ChampionsAdapter;
import lolinfo.wiacekp.pl.lolinfo.lolinfo.lists.ChampList;
import lolinfo.wiacekp.pl.lolinfo.lolinfo.other.DbAdapter;


public class ChampionActivity extends ActionBarActivity{
    private ChampionsAdapter championsAdapter;
    private ListView listView;
    private final String debug_tag="ChampionsClass";
    private DbAdapter dbAdapter = new DbAdapter(this);
    private ArrayList<ChampList>champLists=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champion);
        listView=(ListView)findViewById(R.id.ChampionAListView);
        dbAdapter.open();
        champLists=dbAdapter.getChampionList();
        dbAdapter.close();
        championsAdapter=new ChampionsAdapter(this, champLists);
        listView.setAdapter(championsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putString("name", champLists.get(position).getName());
                bundle.putString("title", champLists.get(position).getTitle());
                bundle.putString("nameImage", champLists.get(position).getNameImage());
                bundle.putString("lore", champLists.get(position).getLore());
                bundle.putInt("iAttack", champLists.get(position).getInfoAttack());
                bundle.putInt("iDefense", champLists.get(position).getInfoDefense());
                bundle.putInt("iMagic", champLists.get(position).getInfoMagic());
                bundle.putInt("iDifficulty", champLists.get(position).getInfoDifficulty());
                bundle.putStringArray("allyTips", champLists.get(position).getAllyTips());
                bundle.putStringArray("enemyTips", champLists.get(position).getEnemyTips());

                bundle.putString("passiveTitle", champLists.get(position).getPassiveTitle());
                bundle.putString("passiveDescription", champLists.get(position).getPassiveDescription());
                bundle.putString("passiveNameImage", champLists.get(position).getPassiveNameImage());

                bundle.putStringArray("spellTitle", champLists.get(position).getSpellsTitle());
                bundle.putStringArray("spellDescription", champLists.get(position).getSpellsDescription());
                bundle.putStringArray("spellNameImage", champLists.get(position).getSpellsNameImage());

                Intent intent = new Intent(getApplicationContext(), ChampionActivityDetail.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

}
