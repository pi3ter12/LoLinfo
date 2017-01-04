package lolinfo.wiacekp.pl.lolinfo.lolinfo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import lolinfo.wiacekp.pl.lolinfo.lolinfo.adapters.RotationAdapter;
import lolinfo.wiacekp.pl.lolinfo.lolinfo.lists.RotationList;
import lolinfo.wiacekp.pl.lolinfo.lolinfo.other.DbAdapter;


public class RotationActivity extends ActionBarActivity {
    private RotationAdapter rotationAdapter;
    private ListView listView;
    private DbAdapter dbAdapter = new DbAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotation);
        listView=(ListView)findViewById(R.id.rotationLV);
        ArrayList<RotationList> rotationLists =new ArrayList<>();
        dbAdapter.open();
        rotationLists =dbAdapter.getRotationList();
        dbAdapter.close();
        rotationAdapter=new RotationAdapter(this, rotationLists, this.getWindowManager().getDefaultDisplay().getWidth());
        listView.setAdapter(rotationAdapter);
    }


}
