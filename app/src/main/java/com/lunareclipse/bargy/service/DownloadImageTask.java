package com.lunareclipse.bargy.service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import java.io.InputStream;


public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    ImageView mImage;

    public DownloadImageTask(ImageView mImage) {
        this.mImage = mImage;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        Bitmap bmp = null;
        try{
            InputStream in = new java.net.URL(url).openStream();
            bmp = BitmapFactory.decodeStream(in);
        } catch (Exception e){
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return bmp;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        mImage.setImageBitmap(bitmap);
    }
}
