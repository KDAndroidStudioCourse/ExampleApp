package com.cj.darra.exampleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Times Table");

        final SeekBar seekBar = findViewById(R.id.seekBar);

        final ListView myListView = findViewById(R.id.myListView);

        seekBar.setMax(20);
        seekBar.setProgress(10);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int min = 1;
                int progress;

                if (i < min) {
                    progress = min;
                    seekBar.setProgress(progress);
                } else {
                    progress = i;
                }

                final ArrayList<String> numbers = new ArrayList<>();

                for (int j = 1; j<=100; j++) {
                    numbers.add(Integer.toString(j*progress));
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, numbers);

                myListView.setAdapter(arrayAdapter);
                myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(MainActivity.this,"" + numbers.get(i),Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final ArrayList<String> numbers = new ArrayList<>();

        for (int j = 1; j<=100; j++) {
            numbers.add(Integer.toString(j*10));
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, numbers);

        myListView.setAdapter(arrayAdapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,"" + numbers.get(i),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void  play(View view) {

    }
}
