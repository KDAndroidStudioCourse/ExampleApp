package com.cj.darra.exampleapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    DownloadImageTask imageTask;
    ImageView imageView;
    Button button0, button1, button2, button3;

    ArrayList<String> celebURLs = new ArrayList<>();
    ArrayList<String> celebNames = new ArrayList<>();
    String[] answers = new String[4];

    Random random = new Random();
    int chosenCeleb;
    int correctAnswer;

    public static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            URL url;
            HttpURLConnection urlConnection;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.connect();

                InputStream in = urlConnection.getInputStream();

                return BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();

                return null;
            }
        }
    }

    public static class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            URL url;
            HttpURLConnection urlConnection;
            String result = "";

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.connect();

                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;

                    result += current;

                    data = reader.read();
                }

                return result;
            } catch (Exception e) {
                e.printStackTrace();

                return null;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Guess The Celebrity");

        imageView = findViewById(R.id.imageView);
        button0 = findViewById(R.id.button1);
        button1 = findViewById(R.id.button2);
        button2 = findViewById(R.id.button3);
        button3 = findViewById(R.id.button4);

        DownloadTask task = new DownloadTask();
        imageTask = new DownloadImageTask();

        String result;

        try {
            result = task.execute("http://www.posh24.se/kandisar").get();

            String[] splitResult = result.split("<div class=\"listedArticles\">");

            Pattern p = Pattern.compile("img src=\"(.*?)\"");
            Matcher m = p.matcher(splitResult[0]);

            while (m.find()) {
                celebURLs.add(m.group(1));
            }

            p = Pattern.compile("alt=\"(.*?)\"");
            m = p.matcher(splitResult[0]);

            while (m.find()) {
                celebNames.add(m.group(1));
            }

            nexrCeleb();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void nexrCeleb() {
        try {
            chosenCeleb = random.nextInt(celebURLs.size());

            Bitmap celebImage = imageTask.execute(celebURLs.get(chosenCeleb)).get();
            imageView.setImageBitmap(celebImage);

            correctAnswer = random.nextInt(4);

            int incorrectAnswer;

            for (int i = 0; i < 4; i++) {
                if (i == correctAnswer) {
                    answers[i] = celebNames.get(chosenCeleb);
                } else {
                    incorrectAnswer = random.nextInt(celebURLs.size());

                    while (incorrectAnswer == chosenCeleb) {
                        incorrectAnswer = random.nextInt(celebURLs.size());
                    }

                    answers[i] = celebNames.get(incorrectAnswer);
                }
            }

            button0.setText(answers[0]);
            button1.setText(answers[1]);
            button2.setText(answers[2]);
            button3.setText(answers[3]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void guessCeleb(View view) {
        if(view.getTag().toString().equals(Integer.toString(correctAnswer))) {
            Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Wrong! It was " + celebNames.get(chosenCeleb), Toast.LENGTH_SHORT).show();
        }

        nexrCeleb();
    }
}
