package lolinfo.wiacekp.pl.lolinfo.lolinfo.other;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.util.ByteArrayBuffer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by debian-piotrek on 26.05.15.
 */
public class DownloadImages extends AsyncTask<String, Void, Void> {

    @Override
    protected Void doInBackground(String... params) {
        URL url=null;
        for(int i=1; i<params.length; i+=2){
            try {
                url = new URL(params[i]);
                File file = new File(params[0]+"/"+params[i+1]);

                long startTime = System.currentTimeMillis();
                Log.d("DownloadFile", "Begin Download URL: " + url + " Filename: " + params[i+1]);
                URLConnection ucon = url.openConnection();
                InputStream is = ucon.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                ByteArrayBuffer baf = new ByteArrayBuffer(50);
                int current = 0;
                while ((current = bis.read()) != -1)
                    baf.append((byte) current);
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(baf.toByteArray());
                fos.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
