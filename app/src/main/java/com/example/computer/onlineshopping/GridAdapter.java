package com.example.computer.onlineshopping;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;



public class GridAdapter extends BaseAdapter {
    ArrayList<HashMap<String, Object>> dataHashMap;
    Context context;
    LayoutInflater inflater;
    public GridAdapter(Context context , ArrayList<HashMap<String,Object>> names) {
        this.context = context;
        this.dataHashMap = names;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


        @Override
    public int getCount() {
            return this.dataHashMap.size();

        }

    @Override
    public Object getItem(int i) {
        return this.dataHashMap.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View myView = view;
        if (myView == null)
            myView = inflater.inflate(R.layout.grid_view,null);
       // ImageView imageView=(ImageView)myView.findViewById(R.id.iv_icon);

        TextView textView = (TextView) myView.findViewById(R.id.tv_title);
        RelativeLayout rl_inner_grid = (RelativeLayout) myView.findViewById(R.id.rl_inner_grid);

        HashMap<String, Object> tempObj = this.dataHashMap.get(i);
        textView.setText(tempObj.get("name").toString());
        //imageView.setImageResource((Integer) tempObj.get("image"));


       // imageView.setImageUrl("https://www.avidphone.com/wp-content/uploads/2016/07/change-pixabay.jpg",imageLoader);

        rl_inner_grid.setBackgroundColor(Color.parseColor(tempObj.get("color").toString()));

        Log.e("CLASS APP",tempObj.get("name").toString());

        textView.setTextColor(Color.WHITE);

        return myView;
    }

}
