package lolinfo.wiacekp.pl.lolinfo.lolinfo.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import lolinfo.wiacekp.pl.lolinfo.lolinfo.R;
import lolinfo.wiacekp.pl.lolinfo.lolinfo.lists.ChampList;

/**
 * Created by debian-piotrek on 26.05.15.
 */
public class ChampionsAdapter extends BaseAdapter {
    private ArrayList<ChampList> champLists = new ArrayList<>();
    private Context ctx;
    private LayoutInflater layoutInflater;

    private ImageView imageView;
    private TextView textViewTitle;
    private TextView textViewDescription;

    public ChampionsAdapter(Context ctx, ArrayList<ChampList>champLists){
        this.ctx=ctx;
        this.champLists=champLists;
        layoutInflater=layoutInflater.from(this.ctx);
    }
    @Override
    public int getCount() {
        return champLists.size();
    }

    @Override
    public Object getItem(int position) {
        return champLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            convertView = layoutInflater.inflate(R.layout.champion_list_layout, null);
            imageView = (ImageView)convertView.findViewById(R.id.ChampIV);
            textViewTitle = (TextView)convertView.findViewById(R.id.champTVTitle);
            textViewDescription = (TextView)convertView.findViewById(R.id.champTVdesc);
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap bitmap = BitmapFactory.decodeFile(ctx.getFilesDir().getAbsolutePath() + "/" + champLists.get(position).getNameImage(), options);
            imageView.setImageBitmap(bitmap);
        }catch (Exception e){
            e.printStackTrace();
        }
        textViewTitle.setText(champLists.get(position).getName());
        textViewDescription.setText(champLists.get(position).getTitle());
        return convertView;
    }
}
