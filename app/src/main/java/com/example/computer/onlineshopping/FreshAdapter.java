package com.example.computer.onlineshopping;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FreshAdapter extends BaseAdapter {
    JSONArray wArray;
    Context context;
    LayoutInflater inflater;
    RequestQueue requestQueue;
    ImageLoader imageLoader;

    public FreshAdapter(Context context, JSONArray jsonArray) {
        this.context = context;
        this.wArray = jsonArray;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        requestQueue = Volley.newRequestQueue(context);
        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mcache = new LruCache<>(10);

            @Override
            public Bitmap getBitmap(String url) {
                return mcache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                mcache.put(url, bitmap);
            }
        });
    }

    @Override
    public int getCount() {
        return this.wArray.length();
    }

    @Override
    public Object getItem(int position) {
        try {
            return this.wArray.get(position);
        } catch (JSONException e) {
            e.printStackTrace();

        }
        return null;
    }


    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View myView = view;
        if (myView == null)
            myView = inflater.inflate(R.layout.freshcollection,null);

        TextView textView = (TextView) myView.findViewById(R.id.tv_title);
        NetworkImageView networkImageView=(NetworkImageView)myView.findViewById(R.id.nv_icon);

        try {
            JSONObject jsonObject=this.wArray.getJSONObject(position);
            networkImageView.setImageUrl(jsonObject.getString("image"),imageLoader);
            textView.setText(jsonObject.getString("Price"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return myView;
    }


}
