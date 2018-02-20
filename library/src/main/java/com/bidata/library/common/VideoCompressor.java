package com.bidata.library.common;

import android.content.Context;
import android.os.AsyncTask;

import com.bidata.library.Listener.VideoCompressListener;
import com.iceteck.silicompressorr.SiliCompressor;
import java.io.File;

/**
 * Created by Dell-OP9010 on 11/29/2017.
 */

public class VideoCompressor extends AsyncTask<String, Void, String> {
    Context context;
    File infile;
    File savedir;
    VideoCompressListener listener;

    public VideoCompressor(Context context, File infile, File savedir, VideoCompressListener listener){
        this.context=context;
        this.infile=infile;
        this.savedir=savedir;
        this.listener=listener;

    }

    @Override
    protected String doInBackground(String... params) {
        String filePath=infile.getAbsolutePath();
        try {
            filePath = SiliCompressor.with(context).compressVideo(infile.getAbsolutePath(), savedir.getAbsolutePath());
            if(new File(filePath).exists()){
                infile.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return filePath;
    }

    @Override
    protected void onPostExecute(String filepath) {
        listener.Oncomplete(new File(filepath));
    }
}


