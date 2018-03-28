package com.cj.darra.exampleapp;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Timers");

        /*final Handler handler = new Handler();

        Runnable run = new Runnable() {
            @Override
            public void run() {
                Log.i("Hey", "A second passed");

                handler.postDelayed(this, 1000);
            }
        };

        handler.post(run);*/

        new CountDownTimer(10000, 1000) {
            public void onTick(long millisecondsUntilDone) {
                Log.i("Seconds left", "" + millisecondsUntilDone/1000);
            }

            public void onFinish() {
                Log.i("We are done", "");
            }
        }.start();
    }

    public void  play(View view) {

    }
}
