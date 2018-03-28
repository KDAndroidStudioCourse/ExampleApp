package com.cj.darra.exampleapp;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView textView;
    Button button;
    Boolean active = false;
    CountDownTimer timer;

    int max = 600;
    int start = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Egg timer");

        seekBar = findViewById(R.id.seekBar);

        textView = findViewById(R.id.timer);

        seekBar.setMax(max);
        seekBar.setProgress(start);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void  play(View view) {
        button = (Button) view;
        if(!active) {
            timer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
                public void onTick(long millisecondsUntilDone) {
                    int i = seekBar.getProgress() - 1;
                    seekBar.setProgress(i);

                    progress(i);
                }

                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mediaPlayer.start();
                    reset();
                }
            };

            timer.start();

            seekBar.setEnabled(false);

            button.setText("Stop");

            active = true;
        } else {
            reset();
        }
    }

    public void progress(int secondsLeft) {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft % 60;

        if(seconds < 10) {
            textView.setText(Integer.toString(minutes) + ":0" + Integer.toString(seconds));
        } else {
            textView.setText(Integer.toString(minutes) + ":" + Integer.toString(seconds));
        }
    }

    public void reset() {
        timer.cancel();

        textView.setText("0:" + Integer.toString(start));
        seekBar.setProgress(start);
        seekBar.setEnabled(true);

        button.setText("Start");

        active = false;
    }
}
