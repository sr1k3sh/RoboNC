package com.arrtsm.app.woodapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class IsServerOnline extends AsyncTask<String, Void, String> {
    public Activity mActivity;
    String result = "";
    ProgressDialog progressDialog;


    String string;

    IsServerOnline(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    protected void onPreExecute() {
        progressDialog=new ProgressDialog(mActivity);
        progressDialog.setTitle("Connecting to server");
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        //super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {

        Log.d("string", "" + strings[0]);
        string = strings[0];

        try {
            URL urlServer = new URL(string);
            HttpURLConnection urlConn = (HttpURLConnection) urlServer.openConnection();
            urlConn.setConnectTimeout(5000);
            urlConn.setRequestMethod("GET");
            urlConn.connect();

            if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                result = String.valueOf(urlConn.getResponseCode());

            } else {
                result = "404";

            }
        } catch (MalformedURLException e1) {
            result = "404";

        } catch (IOException e) {
            e.printStackTrace();
            result = "404";
        }
        return result;
    }


    @Override
    protected void onPostExecute(String s) {

        if (s != null) {
            if (result.equals("200")) {
                progressDialog.dismiss();
                SharedPreferences shared = mActivity.getSharedPreferences("IpAddress", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = shared.edit();
                editor.putString("ip", string);
                editor.commit();
                editor.apply();
                Toast.makeText(mActivity.getApplicationContext(), "server online", Toast.LENGTH_SHORT).show();

            } else {
                progressDialog.dismiss();
                Toast.makeText(mActivity.getApplicationContext(), "server offline", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
                builder.setMessage("server offline")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do things
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        }
        //super.onPostExecute(s);
    }

}