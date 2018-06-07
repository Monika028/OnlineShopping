package com.example.computer.onlineshopping;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , AdapterView.OnItemClickListener{

    GridView gv_dashboard;
    JSONObject jsonObject;


    String[] simpleArray = new String[]{"Fresh Collection","Wedding Special"," Gorgeous Jewellery",
            "Beauty & Grooming"};
    Integer[] imagesArray= new Integer[]{R.mipmap.fresh, R.mipmap.lehnga,R.mipmap.jewellery,R.mipmap.beauty,R.mipmap.bag,
            R.mipmap.special};
    String [] colorsArray = new String[]{"#81D4FA", "#81D4FA", "#81D4FA", "#81D4FA","#81D4FA","#81D4FA"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                SharedPreferences sharedPreferences=DashboardActivity.this.getSharedPreferences("Hello",MODE_PRIVATE);
                if (sharedPreferences.contains("USER"))
                {
                    Intent intent=new Intent(DashboardActivity.this,FreshCollection.class);
                    try {
                        jsonObject=new JSONObject(sharedPreferences.getString("USER",""));
                        // Intent intent=new Intent(DashboardActivity.this,FreshCollection.class);
                        startActivity(intent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        gv_dashboard = (GridView)findViewById(R.id.gv_dashboard);



        ArrayList<HashMap<String,Object>> arrayList = new ArrayList<>();

        for (int i=0;i<simpleArray.length;i++) {
            HashMap<String, Object> tempHashmap = new HashMap<>();
            tempHashmap.put("name",simpleArray[i]);
            tempHashmap.put("image",imagesArray[i]);
            tempHashmap.put("color",colorsArray[i]);


            arrayList.add(tempHashmap);
        }


        User_GridAdapter user_gridAdapter = new User_GridAdapter(DashboardActivity.this,arrayList);
        gv_dashboard.setAdapter(user_gridAdapter);

        gv_dashboard.setOnItemClickListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent=new Intent(DashboardActivity.this,AdminLogin.class);
            startActivity(intent);
        }
        else if (id==R.id.action_logout)
        {
            SharedPreferences sharedPreferences=DashboardActivity.this.getSharedPreferences("Hello",MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.remove("USER");
            editor.apply();
            DashboardActivity.this.finish();
          //  return  true;
            //Intent intent=new Intent(DashboardActivity.this,Login.class);
            //startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            // Handle the camera action
            Toast.makeText(DashboardActivity.this, "Welcome", Toast.LENGTH_LONG).show();

        } else if (id == R.id.fresh) {
            Intent intent = new Intent(DashboardActivity.this,FreshCollection.class);
            startActivity(intent);

        } else if (id == R.id.wedding) {
            Intent intent = new Intent(DashboardActivity.this,Wedding.class);
            startActivity(intent);

        } else if (id == R.id.jewellery) {
            Intent intent = new Intent(DashboardActivity.this,Jewellery.class);
            startActivity(intent);

        } else if (id == R.id.beauty) {
            Intent intent = new Intent(DashboardActivity.this,Beauty.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {

            DashboardActivity.this.finish();
            return  true;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
            {
                Intent intent = new Intent(DashboardActivity.this,FreshCollection.class);
                startActivityForResult(intent,1002);
            } break;
            case 1:
            {
                Intent intent = new Intent(DashboardActivity.this,Wedding.class);
                startActivityForResult(intent,1003);
            }
break;
            case 2:
            {
                Intent intent = new Intent(DashboardActivity.this,Jewellery.class);
                startActivityForResult(intent,1004);
            }
            break;

            case 3:
            {
                Intent intent = new Intent(DashboardActivity.this,Beauty.class);
                startActivityForResult(intent,1005);
            }
            break;

        }



    }
}
