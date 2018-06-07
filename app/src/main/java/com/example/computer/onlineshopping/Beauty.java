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

public class Beauty extends AppCompatActivity implements AdapterView.OnItemClickListener {
    GridView gv_dashboard;
    TextView textView;
    NetworkImageView networkImageView;
    JSONArray jsonArray;
    JSONObject jsonObject;
    ImageLoader imageLoader;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beauty);

        gv_dashboard = (GridView) findViewById(R.id.gv_dashboard);
        textView = (TextView) findViewById(R.id.tv_title);
        networkImageView = (NetworkImageView) findViewById(R.id.nv_icon);

        callGetService();
    }



    public void callGetService() {
        String url = "https://api.backendless.com/795C991F-669A-ABF0-FF67-31B62CF72F00/0ADC0EAC-6068-F002-FFD4-53430D25CB00/data/Beauty";
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
                    FreshAdapter freshAdapter=new FreshAdapter(Beauty.this,jsonArray);
                    gv_dashboard.setAdapter(freshAdapter);

                    gv_dashboard.setOnItemClickListener(Beauty.this);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error){
                Log.d("Error", "Err" + error.getLocalizedMessage());

                Toast.makeText(Beauty.this, "Error is there!", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }


        });
        progressDialog=new ProgressDialog(Beauty.this);
        RequestQueue requestQueue = Volley.newRequestQueue(Beauty.this);
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
            Intent intent = new Intent(Beauty.this, FreshPreview.class);
            intent.putExtra("hello",jsonObject.toString());
            startActivity(intent);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}

