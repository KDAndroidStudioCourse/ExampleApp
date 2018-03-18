package com.cj.darra.exampleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    boolean bart = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void  clickFunction(View view) {
        Log.i("Info", "Image pressed!");

        ImageView simpson = (ImageView) findViewById(R.id.simpsonImageView);

        simpson.animate().alpha(0).setDuration(2000);

        if(bart) {
            simpson.setImageResource(R.drawable.homer);
            bart = false;
        } else {
            simpson.setImageResource(R.drawable.bart);
            bart = true;
        }

        simpson.animate().alpha(1).setDuration(2000);
    }
}
