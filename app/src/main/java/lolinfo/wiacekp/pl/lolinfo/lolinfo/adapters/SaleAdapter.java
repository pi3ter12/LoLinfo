package lolinfo.wiacekp.pl.lolinfo.lolinfo.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import lolinfo.wiacekp.pl.lolinfo.lolinfo.R;
import lolinfo.wiacekp.pl.lolinfo.lolinfo.lists.SalesList;

/**
 * Created by debian-piotrek on 27.05.15.
 */
public class SaleAdapter extends BaseAdapter {
    private Context ctx;
    private ArrayList<SalesList> salesLists;
    private int sWidth;
    private LayoutInflater layoutInflater;
    private TextView textViewName, textViewOldPrice, textViewNewPrice;
    private ImageView imageView1, imageView2;

    public SaleAdapter(Context ctx, ArrayList<SalesList> salesLists, int sWidth){
        this.ctx=ctx;
        this.salesLists = salesLists;
        this.sWidth=sWidth;
        layoutInflater=layoutInflater.from(this.ctx);
    }
    @Override
    public int getCount() {
        return salesLists.size();
    }

    @Override
    public Object getItem(int position) {
        return salesLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.sale_list_layout, null);

        imageView1 = (ImageView)convertView.findViewById(R.id.saleListIVone);
        imageView2 = (ImageView)convertView.findViewById(R.id.saleListIVtwo);
        textViewName = (TextView)convertView.findViewById(R.id.saleListTVname);
        textViewOldPrice = (TextView)convertView.findViewById(R.id.saleListTVoldPrice);
        textViewNewPrice = (TextView)convertView.findViewById(R.id.saleListTVnewPrice);

        textViewName.setText(salesLists.get(position).getName());
        textViewOldPrice.setPaintFlags(textViewOldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        textViewOldPrice.setText(salesLists.get(position).getOldPrice());
        textViewNewPrice.setText(salesLists.get(position).getNewPrice());
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;

            Bitmap bitmap = BitmapFactory.decodeFile(ctx.getFilesDir().getAbsolutePath() + "/" + salesLists.get(position).getNameImage1(), options);
            bitmap = bitmap.createScaledBitmap(bitmap, sWidth, (int)(sWidth*0.6), true);
            imageView1.setImageBitmap(bitmap);

            bitmap = BitmapFactory.decodeFile(ctx.getFilesDir().getAbsolutePath() + "/" + salesLists.get(position).getNameImage2(), options);
            bitmap = bitmap.createScaledBitmap(bitmap, sWidth, (int)(sWidth*0.6), true);
            imageView2.setImageBitmap(bitmap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return convertView;
    }
}
