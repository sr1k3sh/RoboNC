package com.arrtsm.app.woodapp.AR;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.arrtsm.app.woodapp.DrawActivity;
import com.arrtsm.app.woodapp.MaterialFileEditor;
import com.arrtsm.app.woodapp.R;
import com.arrtsm.app.woodapp.json.MainActivity;
import com.arrtsm.app.woodapp.util.Constants;
import com.arrtsm.app.woodapp.util.ImageDownloader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//import robo.arrtsm.com.robonc11.util.Constants;
//import robo.arrtsm.com.robonc11.util.ImageDownloader;
//import robo.arrtsm.com.robonc11.utils.SharedPreference;


public class ArActivity extends Activity {

    Activity context = this;
    WebView webView;
    String shape;
    //SharedPreference sharedPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);
        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        webView = (WebView) findViewById(R.id.webid);
        SharedPreferences shared = getSharedPreferences("prefID", Context.MODE_PRIVATE);
        String url1 = shared.getString("ok",null);
        String shape1= shared.getString("shape",null);
        Log.d("testing", "onCreate: "+url1+shape1);
        //webView.loadUrl("file:///android_asset/web/index.html");
        //edit mtl file before opening the ar html
        /*SharedPreferences preferences = getSharedPreferences("AOP_PREFS",Context.MODE_PRIVATE);
       String url = preferences.getString(SHARED_PREF_URL, "");*/
        //sharedPreference = new SharedPreference();
        String url = "woodtexture/"+url1;
        shape = shape1;
        Log.d("url", "onCreate: "+url+shape);
        //String url = "https://www.w3schools.com/w3css/img_lights.jpg";
        AssetManager assetManager = context.getAssets();
        String[] files = null;
        InputStream in = null;
        OutputStream out = null;
        try
        {
            in = assetManager.open("ar/asset/_4.jpg");
            out = new FileOutputStream(getExternalCacheDir()+"/_4.jpg");
            copyFile(in, out);
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
            //InputStream value = assetManager.open("");

        }
        catch(IOException e)
        {
        }


        Log.e("URL","url: " + url);

        if(url!= null && url.isEmpty()){
            Toast.makeText(this, "Please Select Slab Before Progressing.", Toast.LENGTH_SHORT).show();
            finish();
        }

        ImageDownloader downloader = new ImageDownloader(this, url);
        downloader.setImageDownloaderCallback(textureName -> {
            Log.e("Hero",textureName);
            MaterialFileEditor editor = new MaterialFileEditor(this);

            webView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    if(shape.equals("rectangle")){
                        view.loadUrl("javascript:setm('R');");
                        view.loadUrl("javascript:setMaterial('"+editor.getNewMaterialFromTexture(Constants.Rectangle,textureName)+"');");
                        Log.e("URL","url: " + editor);
                    }
                    else if (shape.equals("circle")){
                        view.loadUrl("javascript:setm('C');");
                        view.loadUrl("javascript:setMaterial('"+editor.getNewMaterialFromTexture(Constants.Circle,textureName)+"');");
                    }
                    else if(shape.equals("oval")){
                        view.loadUrl("javascript:setm('O');");
                        view.loadUrl("javascript:setMaterial('"+editor.getNewMaterialFromTexture(Constants.Oval,textureName)+"');");
                    }
                    else{
                      /*  view.loadUrl("javascript:setm('L');");
                        view.loadUrl("javascript:setMaterial('"+editor.getNewMaterialFromTexture(Constants.L_SHAPE,textureName)+"');");*/
                    }
                }
            });
        });
        downloader.copyToCache();

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);


        webView.loadUrl("file:///android_asset/ar/ar.html");

        webView.setWebChromeClient(new WebChromeClient() {
            // Grant permissions for cam
            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                //Log.d(TAG, "onPermissionRequest");
                ArActivity.this.runOnUiThread(new Runnable() {
                    @TargetApi(Build.VERSION_CODES.M)
                    @Override
                    public void run() {
                        //Log.d(TAG, request.getOrigin().toString());
                        if (request.getOrigin().toString().equals("file:///")) {
                            //Log.d(TAG, "GRANTED");
                            request.grant(request.getResources());

                        } else {
                            // Log.d(TAG, "DENIED");
                            request.deny();
                        }
                    }
                });
            }

        });
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
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //sharedPreference.save_shape1(context,"");
        Intent in = new Intent(this, DrawActivity.class);
        startActivity(in);
        this.finish();
    }
}
