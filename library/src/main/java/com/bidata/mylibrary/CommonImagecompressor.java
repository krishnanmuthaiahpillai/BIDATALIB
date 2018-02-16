package com.bidata.mylibrary;

import android.content.Context;
import android.os.Build;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import id.zelory.compressor.Compressor;

/**
 * Created by Dell-OP9010 on 11/29/2017.
 */

public class CommonImagecompressor {

     public static File Compress(Context context, File imagepath){
         try {
             File  compressedImageFile = new Compressor(context)
                     .setMaxWidth(540)
                     .setMaxHeight(380)
                     .setQuality(25)
                     .compressToFile(imagepath);
             if(compressedImageFile.exists()){
                 imagepath.delete();
                 imagepath= copy(compressedImageFile,imagepath);
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
         return imagepath;
     }


    public static File copy(File src, File dst) throws IOException {
        if (Build.VERSION.SDK_INT >= 19) {
            try (InputStream in = new FileInputStream(src)) {
                try (OutputStream out = new FileOutputStream(dst)) {
                    // Transfer bytes from in to out
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                }
            }
        }else{
            InputStream in = new FileInputStream(src);
            try {
                OutputStream out = new FileOutputStream(dst);
                try {
                    // Transfer bytes from in to out
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                } finally {
                    out.close();
                }
            } finally {
                in.close();
            }
        }
        return dst;
    }
}
