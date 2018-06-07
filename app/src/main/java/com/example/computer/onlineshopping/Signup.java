package com.example.computer.onlineshopping;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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


public class Signup extends AppCompatActivity implements View.OnClickListener
{
    EditText et_username,et_email,et_phoneno,et_password,et_retype;
    ImageButton but_sign1;
    CheckBox cb_terms;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        et_username=(EditText) findViewById(R.id.et_username);
        et_email=(EditText)findViewById(R.id.et_email);
        et_password=(EditText)findViewById(R.id.et_password);
        et_retype=(EditText)findViewById(R.id.et_retype);
        et_phoneno=(EditText)findViewById(R.id.et_phoneno);
        but_sign1=(ImageButton)findViewById(R.id.but_sign1);
        but_sign1.setOnClickListener(this);
        cb_terms = (CheckBox) findViewById(R.id.cb_terms);

    }
    @Override
    public void onClick(View view)
    {
        //ImageButton but=(ImageButton)view;
        String username = et_username.getText().toString();
        String email = et_email.getText().toString();
        String phone_number = et_phoneno.getText().toString();
        String password = et_password.getText().toString();
        String retype_password = et_retype.getText().toString();

        if (cb_terms.isChecked()) {

        } else {
            Toast.makeText(Signup.this,"Please agree to terms and conditions",Toast.LENGTH_LONG).show();
            return;
        }

        if(password.equalsIgnoreCase(retype_password)) {

        }
        else
        {
            Toast.makeText(Signup.this,"please enter a correct password",Toast.LENGTH_LONG).show();
            return;
        }

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("email", email);
            jsonObject.put("Password", password);
            jsonObject.put("phone_number", phone_number);
            jsonObject.put("retype_password", retype_password);
            jsonObject.put("username", username);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = "https://api.backendless.com/A083F2E4-5FB8-7306-FF5B-5D6AE1EAB200/1F499FF0-A11B-6F93-FF4F-DB31DDAE2A00/data/Signup";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Response", response.toString());
                progressDialog.dismiss();
                Toast.makeText(Signup.this, "Data Saved", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Signup.this, Login.class);
                //  intent.putExtra("USERNAME",et_username.getText().toString());
             //   startActivity(intent);


            }

        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", "Err" + error.getLocalizedMessage());
                        progressDialog.dismiss();
                        Toast.makeText(Signup.this, "Error is there!", Toast.LENGTH_LONG).show();

                    }
                }

        );
        progressDialog = new ProgressDialog(Signup.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setTitle("Loading !");
        progressDialog.setMessage("Sending data to server");
        progressDialog.show();


        RequestQueue requestQueue = Volley.newRequestQueue(Signup.this);
        requestQueue.add(jsonObjectRequest);

    }
}

