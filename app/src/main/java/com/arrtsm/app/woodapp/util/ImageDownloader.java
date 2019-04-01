package com.arrtsm.app.woodapp.util;
import android.app.Activity;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Hitech on 3/30/2018.
 */

public class ImageDownloader {

    private Activity context;
    private String url;

    private ImageDownloaderCallback callback;

    public ImageDownloader(Activity context, String url){
        this.context = context;
        this.url = url;
    }

    public void copyToCache(){
        AssetManager assetManager = context.getAssets();
        String[] files = null;
        InputStream in = null;
        OutputStream out = null;
        File textureFile = null;
        try
        {
            textureFile = File.createTempFile("texture", ".jpg", context.getExternalCacheDir());
            in = assetManager.open(url);
            out = new FileOutputStream(textureFile);
            copyFile(in, out);
            in.close();
            out.flush();
            out.close();
            callback.imageDownloadCompleted(textureFile.getName());
        }
        catch(IOException e)
        {
            Log.e("tag", "Failed to copy asset file: " + url, e);
        }
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException
    {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1)
        {
            out.write(buffer, 0, read);
        }
    }

    public void setImageDownloaderCallback(ImageDownloaderCallback callback){
        this.callback = callback;
    }
}
