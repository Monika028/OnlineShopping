package com.example.computer.onlineshopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


import java.util.ArrayList;
import java.util.HashMap;

public class User_GridView extends AppCompatActivity implements AdapterView.OnItemClickListener {


    GridView gv_dashboard;

    String[] simpleArray = new String[]{"Fresh Collection","Wedding Special","Gorgeous Jewellery","Dress Material",
            "Beauty deals","Special Offers"};
    Integer[] imagesArray= new Integer[]{R.mipmap.fresh, R.mipmap.spcl,R.mipmap.special,R.mipmap.beauty,R.mipmap.beauty,R.mipmap.ic_launcher};
    String [] colorsArray = new String[]{"#82B1FF", "#BBDEFB", "#82B1FF", "#81D4FA","#82B1FF","#82B1FF"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        gv_dashboard = (GridView)findViewById(R.id.gv_dashboard);



        ArrayList<HashMap<String,Object>> arrayList = new ArrayList<>();

        for (int i=0;i<simpleArray.length;i++) {
            HashMap<String, Object> tempHashmap = new HashMap<>();
            tempHashmap.put("name",simpleArray[i]);
            tempHashmap.put("image",imagesArray[i]);
            tempHashmap.put("color",colorsArray[i]);


            arrayList.add(tempHashmap);
        }


        User_GridAdapter user_gridAdapter = new User_GridAdapter(User_GridView.this,arrayList);
        gv_dashboard.setAdapter(user_gridAdapter);

        gv_dashboard.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (i == 0) {
            Intent intent = new Intent(User_GridView.this, AddProducts.class);
            startActivity(intent);
        } else if (i == 1) {
            Intent intent = new Intent(User_GridView.this, Signup.class);
            startActivity(intent);

        }
    }
}
