package com.example.computer.onlineshopping;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Login extends AppCompatActivity implements View.OnClickListener {
    EditText et_email, et_password;
    Button btn_sign1;
    ImageButton ib_login1;
    ProgressDialog progressDialog;
    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        ib_login1 = (ImageButton) findViewById(R.id.ib_login1);
        btn_sign1 = (Button) findViewById(R.id.btn_sign1);
        Log.d("Login", "onCreate");
        ib_login1.setOnClickListener(this);
        btn_sign1.setOnClickListener(this);

        SharedPreferences sharedPreferences = Login.this.getSharedPreferences("Hello", MODE_PRIVATE);
        if (sharedPreferences.contains("USER")) {
            Intent intent = new Intent(Login.this, DashboardActivity.class);
            String jsonString = sharedPreferences.getString("USER", "");
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                intent.putExtra("USERNAME", jsonObject.getString("email"));
                startActivity(intent);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        else
        {

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(" Login", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(" Login", "onResume");
        et_email.setText("");
        et_password.setText("");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(" Login", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(" Login", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(" Login", "onDestroy");
    }



    @Override
    public void onClick(View view) {
        // ImageButton btn = (ImageButton) view;
        // Button but = (Button) view;


        if (view.getId() == R.id.ib_login1) {
            String email = et_email.getText().toString();
            String password = et_password.getText().toString();


            callGetService();


        } else if (view.getId() == R.id.btn_sign1) {

            Intent intent = new Intent(Login.this, Signup.class);
            //     intent.putExtra("EMAIL",et_email.getText().toString());
            startActivity(intent);
        }
    }

    public void callGetService() {
        String url = "https://api.backendless.com/A083F2E4-5FB8-7306-FF5B-5D6AE1EAB200/1F499FF0-A11B-6F93-FF4F-DB31DDAE2A00/data/Signup?where=email%3D'" + et_email.getText().toString() + "'%20and%20password%3D'" + et_password.getText().toString() + "'";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response", response);
                 progressDialog.dismiss();

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    if (jsonArray.length() > 0) {
                        JSONObject jsonObject=jsonArray.getJSONObject(0);
                        SharedPreferences sharedPreferences=Login.this.getSharedPreferences("Hello",MODE_PRIVATE);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("USER",jsonArray.getJSONObject(0).toString());
                        editor.apply();
                        Intent intent=new Intent(Login.this,DashboardActivity.class);
                        startActivity(intent);

                    }

                    else {
                        Toast.makeText(Login.this, "incorrect email and password", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                  /*  ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);


                        arrayList.add(jsonObject.getString("email"));
                        arrayList.add(jsonObject.getString("password"));
                        progressDialog.dismiss();*/


                } catch (JSONException e1) {
                    e1.printStackTrace();
                }

                //  ArrayAdapter arrayAdapter = new ArrayAdapter( Login.this, android.R.layout.simple_list_item_1);
            }


        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", "Err" + error.getLocalizedMessage());
                        Toast.makeText(Login.this, "Error is there!", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }

                });
        progressDialog = new ProgressDialog(Login.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please wait...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
        requestQueue.add(stringRequest);

    }
}






