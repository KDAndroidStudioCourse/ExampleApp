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

        simpson.animate().scaleX(0.5f).scaleY(0.5f).setDuration(2000);
    }
}
