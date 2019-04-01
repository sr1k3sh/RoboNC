package com.arrtsm.app.woodapp.json;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arrtsm.app.woodapp.OnMenuClickedListener2;
import com.arrtsm.app.woodapp.R;
import com.arrtsm.app.woodapp.dao.displayfragmentdao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DisplayFragment extends Fragment implements DisplayRecyclerAdapter.OnItemClickListener {
    RecyclerView recyclerView;
    DisplayRecyclerAdapter adapter;
    String a = "Materials";
    OnMenuClickedListener2 onMenuClickedListener;

    ArrayList<displayfragmentdao> arrayList = new ArrayList<>();

    //String json_url = NetworkAPI.BASE_URL+"/texture/api";
    String json_url ="192.168.0.118/testfolder/texture.txt";

    private SwipeRefreshLayout sr;

    public void setOnMenuClickedListener(OnMenuClickedListener2 onMenuClickedListener) {
        this.onMenuClickedListener = onMenuClickedListener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_display_list, container, false);
        recyclerView = v.findViewById(R.id.recycleView);

        //refresh while dragging
        sr = v.findViewById(R.id.swiperefresh);
        sr.setOnRefreshListener(() -> {
            if (arrayList != null) {
                arrayList.clear();
            }
            loadData();
            sr.setRefreshing(false);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new DisplayRecyclerAdapter(arrayList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new DisplayRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                onMenuClickedListener.OnMenuClicked(arrayList.get(position).getImage());
            }
        });
        loadData();

        return v;
    }
    public void loadData(){
        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());

            JSONArray countries=obj.getJSONArray("Materials");
            Log.d("tsst", "onCreateView: "+countries);
            for (int i=0;i<countries.length();i++){
                JSONObject jsonObject=countries.getJSONObject(i);
                displayfragmentdao dis = new displayfragmentdao(
                        jsonObject.getString("material_type"),
                        jsonObject.getString("image_path"));
                arrayList.add(dis);
                adapter.notifyDataSetChanged();
                Log.d("tsst", "loadData: "+jsonObject.getString("image_path"));
                //countryList.add(new Country(code,nameAr,nameEn));
            }


            //DBHelper.getInstance(activity).insertCountries(countryList);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("woodtexture/imagepath.json");
            Log.d("tsst", "loadJSONFromAsset: "+is);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

    //JSON FOR TEXTURE IMAGE
   /* public void loadData() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, json_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray array = response.getJSONArray(a);
                    Log.d("array", "onResponse: "+array);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject jsonObject = array.getJSONObject(i);
                        displayfragmentdao contact = new displayfragmentdao(
                                jsonObject.getString("material_type"),
                                jsonObject.getString("image_path"));
                        Log.e("material", "onResponse: "+jsonObject.getString("image_path") );;
                        *//*Material material = new Material(jsonObject.getString("material_type"),
                                jsonObject.getString("image_path"));

                        AppExecutors.getInstance().diskIO().execute(() -> {
                            MaterialDao materialDao = MaterialDatabase.materialDao();
                            materialDao.insert(material);
                        });*//*
                        arrayList.add(contact);
                        adapter.notifyDataSetChanged();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(DisplayFragment.this.getContext(), "Internet Problem", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    Cache.Entry cacheEntry = HttpHeaderParser.parseCacheHeaders(response);
                    if (cacheEntry == null) {
                        cacheEntry = new Cache.Entry();
                    }
                    final long cacheHitButRefreshed = 3 * 60 * 1000; // in 1 minutes cache will be hit, but also refreshed on background
                    final long cacheExpired = 24 * 60 * 60 * 1000; // in 24 hours this cache entry expires completely
                    long now = System.currentTimeMillis();
                    final long softExpire = now + cacheHitButRefreshed;
                    final long ttl = now + cacheExpired;
                    cacheEntry.data = response.data;
                    cacheEntry.softTtl = softExpire;
                    cacheEntry.ttl = ttl;
                    String headerValue;
                    headerValue = response.headers.get("Date");
                    if (headerValue != null) {
                        cacheEntry.serverDate = HttpHeaderParser.parseDateAsEpoch(headerValue);
                    }
                    headerValue = response.headers.get("Last-Modified");
                    if (headerValue != null) {
                        cacheEntry.lastModified = HttpHeaderParser.parseDateAsEpoch(headerValue);
                    }
                    cacheEntry.responseHeaders = response.headers;
                    final String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    return Response.success(new JSONObject(jsonString), cacheEntry);
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException e) {
                    return Response.error(new ParseError(e));
                }
            }

            @Override
            protected void deliverResponse(JSONObject response) {
                super.deliverResponse(response);
            }

            @Override
            public void deliverError(VolleyError error) {
                //super.deliverError(error);
            }

            @Override
            protected VolleyError parseNetworkError(VolleyError volleyError) {
                return super.parseNetworkError(volleyError);
            }
        };


        Mysingleton.getInstance(getContext()).addToRequest(jsonObjectRequest);

    }*/


    @Override
    public void onItemClick(int position) {
        Toast.makeText(getContext(),"clicked"+arrayList.get(position).getImage(), Toast.LENGTH_SHORT).show();
        FragmentManager b = ((FragmentActivity) getContext()).getSupportFragmentManager();
        //InnerFragment d = new InnerFragment();
        //d.setOnMenuClickedListener(onMenuClickedListener);

        Bundle bundel = new Bundle();
        bundel.putString("EXTRA_SESSION_ID", arrayList.get(position).getImage());
        //Log.e("material", "onItemClick: "+arrayList.get(position).getName() );
        //d.setArguments(bundel);
       /* b.beginTransaction()
                .add(R.id.left_list, d, "LEFT_MENU")
                .addToBackStack("null")
                .commit();*/

    }

}