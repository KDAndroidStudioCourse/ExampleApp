package com.cj.darra.exampleapp;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Basic Phrases");
    }

    public void  play(View view) {
        Button buttonPressed = (Button) view;

        Log.i("Tag", "" + buttonPressed.getTag().toString());

        if (mediaPlayer!= null) {
            mediaPlayer.release();
            mediaPlayer= null;
        }

        mediaPlayer = MediaPlayer.create(this, getResources().getIdentifier("" + buttonPressed.getTag().toString(), "raw", getPackageName()));

        mediaPlayer.start();
    }
}
