package com.example.computer.onlineshopping;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AddProducts extends AppCompatActivity implements View.OnClickListener {

    EditText et_pname, et_price,et_preview, et_bigprice,et_details;
    Button btn;
    ImageButton ib_save;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproducts);
        et_pname = (EditText) findViewById(R.id.et_pname);
        et_price = (EditText) findViewById(R.id.et_price);
        et_preview = (EditText) findViewById(R.id.et_preview);
        et_bigprice = (EditText) findViewById(R.id.et_bigprice);
        et_details = (EditText) findViewById(R.id.et_details);
        ib_save = (ImageButton) findViewById(R.id.ib_save);
        ib_save.setOnClickListener(this);
        btn= (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.ib_save) {

            Toast.makeText(AddProducts.this, "Error is there!", Toast.LENGTH_LONG).show();

        } else if (view.getId() == R.id.btn) {

            Intent intent = new Intent(AddProducts.this, Delete.class);
            startActivity(intent);
        }

        String pname = et_pname.getText().toString();
        String price = et_price.getText().toString();
        String preview=et_preview.getText().toString();
        String bigprice = et_bigprice.getText().toString();
        String details = et_details.getText().toString();



        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("image", pname);
            jsonObject.put("Price", price);
            jsonObject.put("imagebig",preview);
            jsonObject.put("bigprice",bigprice);
            jsonObject.put("Details",details);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = "https://api.backendless.com/A083F2E4-5FB8-7306-FF5B-5D6AE1EAB200/1F499FF0-A11B-6F93-FF4F-DB31DDAE2A00/data/Freshcollection?pageSize=50";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Response", response.toString());
                Toast.makeText(AddProducts.this, "Data Saved", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();





            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", "Err" + error.getLocalizedMessage());
                progressDialog.dismiss();

            }
        });


        progressDialog = new ProgressDialog(AddProducts.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Loading !");
        progressDialog.setMessage("Sending data to server");
        progressDialog.show();
        RequestQueue requestQueue = Volley.newRequestQueue(AddProducts.this);
        requestQueue.add(jsonObjectRequest);


    }
}