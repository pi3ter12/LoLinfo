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
import lolinfo.wiacekp.pl.lolinfo.lolinfo.lists.ItemsList;

/**
 * Created by debian-piotrek on 30.05.15.
 */
public class ItemsAdapter extends BaseAdapter {
    private ArrayList<ItemsList>itemsLists = new ArrayList<>();
    private Context ctx;
    private LayoutInflater layoutInflater;
    private ImageView imageView;
    private TextView textView;

    public ItemsAdapter(ArrayList<ItemsList>itemsLists, Context ctx){
        this.itemsLists=itemsLists;
        this.ctx=ctx;
        layoutInflater=layoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return itemsLists.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.items_list_layout, null);
        imageView = (ImageView)convertView.findViewById(R.id.itemListIV);
        textView = (TextView)convertView.findViewById(R.id.itemListTV);
        textView.setText(itemsLists.get(position).getTitle());
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;

            Bitmap bitmap = BitmapFactory.decodeFile(ctx.getFilesDir().getAbsolutePath() + "/" + itemsLists.get(position).getNameImage(), options);
            //bitmap = bitmap.createScaledBitmap(bitmap, sWidth, (int)(sWidth*0.6), true);
            imageView.setImageBitmap(bitmap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return convertView;
    }
}
