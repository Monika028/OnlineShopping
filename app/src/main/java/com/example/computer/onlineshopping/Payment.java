package com.example.computer.onlineshopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Payment extends AppCompatActivity {

    TextView tv_title,tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv = (TextView) findViewById(R.id.tv);
    }
}
