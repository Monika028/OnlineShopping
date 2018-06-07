package com.example.computer.onlineshopping;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ContactUs extends AppCompatActivity implements View.OnClickListener{
    TextView tv;
    ProgressDialog progressDialog;
    EditText et_name,et_email,et_phone,et_address,et_query;
    ImageButton ib_login1;
    JSONObject jsonObject;
// ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        tv = (TextView) findViewById(R.id.tv);
        et_name = (EditText) findViewById(R.id.et_name);
        et_email = (EditText) findViewById(R.id.et_email);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_address = (EditText) findViewById(R.id.et_address);
        et_query = (EditText) findViewById(R.id.et_query);
        ib_login1 = (ImageButton) findViewById(R.id.ib_login1);
        ib_login1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String name = et_name.getText().toString();
        String email = et_email.getText().toString();
        String phone=et_phone.getText().toString();
        String address = et_address.getText().toString();
        String query = et_query.getText().toString();

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("username", name);
            jsonObject.put("email", email);
            jsonObject.put("Phone",phone);
            jsonObject.put("address",address);
            jsonObject.put("query",query);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = "https://api.backendless.com/795C991F-669A-ABF0-FF67-31B62CF72F00/0ADC0EAC-6068-F002-FFD4-53430D25CB00/data/Contactus";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Response", response.toString());
                progressDialog.dismiss();
                Toast.makeText(ContactUs.this, "Thank you", Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", "Err" + error.getLocalizedMessage());
                progressDialog.dismiss();
                Toast.makeText(ContactUs.this, "Error is there!", Toast.LENGTH_LONG).show();
            }
        });


        progressDialog = new ProgressDialog(ContactUs.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Loading !");
        progressDialog.setMessage("Please wait......");
        progressDialog.show();
        RequestQueue requestQueue = Volley.newRequestQueue(ContactUs.this);
        requestQueue.add(jsonObjectRequest);


    }

}




