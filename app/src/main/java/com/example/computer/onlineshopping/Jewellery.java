package com.example.computer.onlineshopping;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Jewellery extends AppCompatActivity implements AdapterView.OnItemClickListener {
    GridView gv_dashboard;
    TextView textView,tv1;
    NetworkImageView networkImageView;
    JSONArray jsonArray;
    JSONObject jsonObject;
    ImageLoader imageLoader;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jewellery);

        gv_dashboard = (GridView) findViewById(R.id.gv_dashboard);
        textView = (TextView) findViewById(R.id.tv_title);
        tv1 = (TextView) findViewById(R.id.tv1);
        networkImageView = (NetworkImageView) findViewById(R.id.nv_icon);

        callGetService();
    }



    public void callGetService() {
        String url = "https://api.backendless.com/A083F2E4-5FB8-7306-FF5B-5D6AE1EAB200/1F499FF0-A11B-6F93-FF4F-DB31DDAE2A00/data/Jewellery?pageSize=50";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("RESPONSE", response);
                try {
                    jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject = jsonArray.getJSONObject(i);

                        jsonObject.getString("image");
                        jsonObject.getString("Price");
                        progressDialog.dismiss();




                    }
                    FreshAdapter freshAdapter=new FreshAdapter(Jewellery.this,jsonArray);
                    gv_dashboard.setAdapter(freshAdapter);

                    gv_dashboard.setOnItemClickListener(Jewellery.this);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){
                Log.d("Error", "Err" + error.getLocalizedMessage());

                Toast.makeText(Jewellery.this, "Error is there!", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }


        });
        progressDialog=new ProgressDialog(Jewellery.this);
        RequestQueue requestQueue = Volley.newRequestQueue(Jewellery.this);
        requestQueue.add(stringRequest);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Open page");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long i) {
        try {
            jsonObject=jsonArray.getJSONObject(position);
            Intent intent = new Intent(Jewellery.this, FreshPreview.class);
            intent.putExtra("hello",jsonObject.toString());
            startActivity(intent);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}


