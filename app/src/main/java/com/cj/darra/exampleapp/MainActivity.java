package com.cj.darra.exampleapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data;

                do {
                    data = reader.read();

                    char current = (char) data;

                    result += current;
                } while(data != -1);

                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return "Failed";
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Downloading Web Content");

        DownloadTask task = new DownloadTask();
        String result = null;

        try {
            result = task.execute("http://www.inf.uniroma3.it/~patrigna/didactic/imp_elab/esempi_html/esempio_1.html").get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.i("Result", "" + result);
    }
}
