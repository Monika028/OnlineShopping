package com.example.computer.onlineshopping;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
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

import java.util.ArrayList;

public class Delete extends AppCompatActivity implements AdapterView.OnItemClickListener,View.OnClickListener {
    GridView gv_dashboard;
    TextView textView,tv1;
    NetworkImageView networkImageView;
    JSONArray jsonArray;
    JSONObject jsonObject;
    ImageLoader imageLoader;
    ProgressDialog progressDialog;
    ArrayList arrayList;
    Button btn_delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        gv_dashboard = (GridView) findViewById(R.id.gv_dashboard);
        textView = (TextView) findViewById(R.id.tv_title);
        tv1 = (TextView) findViewById(R.id.tv1);
        networkImageView = (NetworkImageView) findViewById(R.id.nv_icon);
        btn_delete=(Button)findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(this);

        callGetService();

    }
    public void callGetService() {
        String url = "https://api.backendless.com/A083F2E4-5FB8-7306-FF5B-5D6AE1EAB200/1F499FF0-A11B-6F93-FF4F-DB31DDAE2A00/data/Freshcollection?pageSize=50";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("RESPONSE", response);
                try {
                    jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonObject = jsonArray.getJSONObject(i);

                        arrayList.add(jsonObject.getString("title"));
                        SharedPreferences sharedPreferences=getSharedPreferences("notice",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("mynotice",jsonObject.toString());
                        editor.apply();

                    }
                    FreshAdapter freshAdapter=new FreshAdapter(Delete.this,jsonArray);
                    gv_dashboard.setAdapter(freshAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR", error.getLocalizedMessage());

            }
        }
        );
        RequestQueue requestQueue = Volley.newRequestQueue(Delete.this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, final int pos, long l) {

        //  JSONObject jsonObject= null;


        AlertDialog.Builder builder=new AlertDialog.Builder(Delete.this);
        builder.setTitle("conformation");
        builder.setMessage("Are you sure you want to delete record");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i==DialogInterface.BUTTON_POSITIVE)
                {
                    try {
                        jsonObject = jsonArray.getJSONObject(pos);
                        callDeleteService();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }}

        });
        builder.setNegativeButton("no",null);
        builder.show();

    }
    public  void callDeleteService()
    {
        String objId="";
        try {
            objId=jsonObject.getString("objectId");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url="https://api.backendless.com/A083F2E4-5FB8-7306-FF5B-5D6AE1EAB200/1F499FF0-A11B-6F93-FF4F-DB31DDAE2A00/data/Freshcollection/"+objId;
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                Toast.makeText(Delete.this, "delete record", Toast.LENGTH_LONG).show();
               Delete.this.finish();
            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Delete.this,"error for deleting a record",Toast.LENGTH_LONG).show();
                    }
                }
        );
        RequestQueue requestQueue= Volley.newRequestQueue(Delete.this);
        requestQueue.add(stringRequest);


    }


    @Override
    public void onClick(View v) {

    }
}

