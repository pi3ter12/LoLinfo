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
import lolinfo.wiacekp.pl.lolinfo.lolinfo.lists.RotationList;

/**
 * Created by debian-piotrek on 27.05.15.
 */
public class RotationAdapter extends BaseAdapter {
    private Context ctx;
    private ArrayList<RotationList> rotationLists = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private int sWidth;

    private ImageView imageView;
    private TextView textView;

    public RotationAdapter(Context ctx, ArrayList<RotationList> rotationLists, int sWidth){
        this.ctx=ctx;
        this.rotationLists = rotationLists;
        this.sWidth=sWidth;
        layoutInflater=layoutInflater.from(this.ctx);
    }
    @Override
    public int getCount() {
        return rotationLists.size();
    }

    @Override
    public Object getItem(int position) {
        return rotationLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.rotation_list_layout, null);
        imageView = (ImageView)convertView.findViewById(R.id.rotationListIV);
        textView = (TextView)convertView.findViewById(R.id.rotationListTV);
        textView.setText(rotationLists.get(position).getName());
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;

            Bitmap bitmap = BitmapFactory.decodeFile(ctx.getFilesDir().getAbsolutePath() + "/" + rotationLists.get(position).getNameImage(), options);
            bitmap = bitmap.createScaledBitmap(bitmap, sWidth, (int)(sWidth*0.6), true);
            imageView.setImageBitmap(bitmap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return convertView;
    }
}
