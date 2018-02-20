package com.bidata.library.common;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.AsyncTask;
import android.os.Build;

import com.bidata.library.Listener.VideoBitmapListener;

import java.util.HashMap;

/**
 * Created by Dell-OP9010 on 12/1/2017.
 */

public class Video_Bitmap_From_Url extends AsyncTask<String, Void, Bitmap> {

    VideoBitmapListener videoBitmapListener;
    public Video_Bitmap_From_Url(VideoBitmapListener videoBitmapListener){
        this.videoBitmapListener=videoBitmapListener;


    }

    @Override
    protected void onPreExecute() {


    }
    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap bitmap = null;
        MediaMetadataRetriever mediaMetadataRetriever = null;
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            if (Build.VERSION.SDK_INT >= 14)
                // no headers included
                mediaMetadataRetriever.setDataSource(params[0], new HashMap<String, String>());
            else
                mediaMetadataRetriever.setDataSource(params[0]);
            bitmap = mediaMetadataRetriever.getFrameAtTime();
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (mediaMetadataRetriever != null)
                mediaMetadataRetriever.release();
        }
        return bitmap;

    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {

        videoBitmapListener.OnBimapLoad(bitmap);
    }

}
