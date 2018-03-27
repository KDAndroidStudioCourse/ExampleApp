package com.cj.darra.exampleapp;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Video Demo");

        mediaPlayer = MediaPlayer.create(this, R.raw.smalldog);
    }

    public void  play(View view) {
        Log.i("Info", "Sound played!");

        mediaPlayer.start();
    }

    public void  pause(View view) {
        Log.i("Info", "Sound paused!");

        mediaPlayer.pause();
    }
}
