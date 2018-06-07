package com.example.computer.onlineshopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class GridViewActivity extends AppCompatActivity implements  AdapterView.OnItemClickListener {
    GridView gv_dashboard;
    String[] simpleArray = new String[]{"Add Products", "View Products", "Update Products", "Delete Products"};
    //Integer[] imagesArray = new Integer[]{R.mipmap.on, R.mipmap.on, R.mipmap.on, R.mipmap.on,};
    String[] colorsArray = new String[]{"#2196F3", "#9E9E9E", "#4CAF50", "#9C27B0"};

    //for Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.actionbar, menu);
        MenuItem item = menu.findItem(R.id.add);
        item.setVisible(true);
        return true;
    }


    //for Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.add) {
            Intent intent = new Intent(GridViewActivity.this, AddProducts.class);
            startActivity(intent);

        }else if (item.getItemId() == R.id.edit)
        {
            Toast.makeText(GridViewActivity.this,"Some error occured",Toast.LENGTH_LONG).show();

        }
        return true;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        gv_dashboard = (GridView) findViewById(R.id.gv_dashboard);
        // gv_dashboard.setOnClickListener(this);
        //  ArrayAdapter arrayAdapter = new ArrayAdapter(Gridview.this,android.R.layout.simple_list_item_1,simpleArray);
        //  gv_dashboard.setAdapter(arrayAdapter);
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<>();
        for (int i = 0; i < simpleArray.length; i++) {
            HashMap<String, Object> tempHashmap = new HashMap<>();
            try {
                tempHashmap.put("name", simpleArray[i]);
                //   tempHashmap.put("image", imagesArray[i]);
                tempHashmap.put("color", colorsArray[i]);
                Log.d("TEMP HASHMAP", tempHashmap.toString());
                arrayList.add(tempHashmap);
            } catch (Exception ae) {
                Log.e("Exception raised", ae.getMessage());
            } finally {
            }
        }
        GridAdapter gridApater = new GridAdapter(GridViewActivity.this, arrayList);
        gv_dashboard.setAdapter(gridApater);
        gv_dashboard.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (i == 0) {
            Intent intent = new Intent(GridViewActivity.this, AddProducts.class);
            startActivity(intent);
        } else if (i == 1) {
            Intent intent = new Intent(GridViewActivity.this, null);
            startActivity(intent);

        }
    }
}


