package com.arrtsm.app.woodapp.json;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Mysingleton {

    private static Mysingleton mInstance;
    private static Context mctx;
    private RequestQueue requestQueue;

    private Mysingleton(Context context) {
        mctx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized Mysingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Mysingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {

        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(mctx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequest(Request<T> request) {
        requestQueue.add(request);
    }

}
