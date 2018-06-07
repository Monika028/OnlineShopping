package com.example.computer.onlineshopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Welcomepage extends AppCompatActivity {
    private ProgressBar pgsBar;

    TextView tv;
    ImageView iv_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomepage);
        pgsBar = (ProgressBar) findViewById(R.id.pBar);
        tv = (TextView) findViewById(R.id.tv);
        iv_icon = (ImageView) findViewById(R.id.iv_icon);
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.anim);
        tv.startAnimation(myanim);
        iv_icon.startAnimation(myanim);

        final Intent i = new Intent(Welcomepage.this, Login.class);
        //  startActivity(intent);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(4000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }
}
