package com.example.kd.project;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * Created by Alex on 14.01.2018.
 */
public class Downloader extends AsyncTask<String, Void, Bitmap> {
    private ImageView w;
    private Integer defW,defH;
    private double x,y,x1,y1;

    public Downloader(ImageView w, Integer defW, Integer defH, double x, double y, double x1, double y1) {
        this.w = w;
        this.defW = defW;
        this.defH = defH;
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
    }

    @Override
    protected Bitmap doInBackground(String... URL) {

        String imageURL = URL[0];

        Bitmap bitmap = null;
        try {
            InputStream input = new java.net.URL(imageURL).openStream();
            bitmap = BitmapFactory.decodeStream(input);
            bitmap = VKManager.cutImage(bitmap, bitmap.getWidth() / 100.0 * x,
                    bitmap.getHeight() / 100.0 * y,
                    bitmap.getWidth() / 100.0 * x1,
                    bitmap.getHeight() / 100.0 * y1);
            if(defH != null && defW != null)
                bitmap = Bitmap.createScaledBitmap(bitmap, defW, defH, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        w.setImageBitmap(result);
    }
}
