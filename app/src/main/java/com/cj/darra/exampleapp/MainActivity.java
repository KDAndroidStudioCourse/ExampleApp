package com.cj.darra.exampleapp;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton, button0, button1, button2, button3;
    TextView timeTextView, sumTextView, scoreTextView, resultTextView;
    GridLayout gridLayout;

    Random random;
    int a, b, maxNumber, maxTime, numberOfAnswers, locationOfCorrectAnswer;
    int score, numberOfQuestions;

    ArrayList<Integer> answers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Brain trainer");

        goButton = findViewById(R.id.goButton);

        timeTextView = findViewById(R.id.timeTextView);
        sumTextView = findViewById(R.id.sumTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        resultTextView = findViewById(R.id.resultTextView);

        gridLayout = findViewById(R.id.gridLayout);
        button0 = findViewById(R.id.answerButton0);
        button1 = findViewById(R.id.answerButton1);
        button2 = findViewById(R.id.answerButton2);
        button3 = findViewById(R.id.answerButton3);

        answers = new ArrayList<>();

        random = new Random();

        maxNumber = 20;
        maxTime = 30;
        score = 0;
        numberOfQuestions = 0;

        numberOfAnswers = gridLayout.getRowCount() * gridLayout.getColumnCount();

        resultTextView.setText("");
    }

    public void start(View view) {
        resultTextView.setText("");

        setVisibility(false);

        setUI();

        new CountDownTimer(maxTime*1000+100, 1000) {
            @Override
            public void onTick(long l) {
                timeTextView.setText(String.valueOf(l/1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("You got " + Integer.toString(score) + " right answers out of " + Integer.toString(numberOfQuestions));
                setVisibility(true);
            }
        }.start();
    }

    public void answer(View view) {
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            resultTextView.setText("Correct!");
            score++;
        } else {
            resultTextView.setText("Wrong! :(");
        }

        numberOfQuestions++;
        answers.clear();
        setUI();
    }

    private void setVisibility(Boolean active) {
        if(active) {
            timeTextView.setVisibility(View.INVISIBLE);
            sumTextView.setVisibility(View.INVISIBLE);
            scoreTextView.setVisibility(View.INVISIBLE);
            gridLayout.setVisibility(View.INVISIBLE);

            goButton.setVisibility(View.VISIBLE);
        } else {
            goButton.setVisibility(View.INVISIBLE);

            timeTextView.setVisibility(View.VISIBLE);
            sumTextView.setVisibility(View.VISIBLE);
            scoreTextView.setVisibility(View.VISIBLE);
            gridLayout.setVisibility(View.VISIBLE);
        }
    }

    private void setUI() {
        a = random.nextInt(maxNumber + 1);
        b = random.nextInt(maxNumber + 1);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
        scoreTextView.setText(Integer.toString(score) + " out of " + Integer.toString(numberOfQuestions));

        locationOfCorrectAnswer = random.nextInt(numberOfAnswers);

        for (int i = 0; i < numberOfAnswers; i++) {
            if(i == locationOfCorrectAnswer) {
                answers.add(a+b);
            } else {
                int wrongAnswer = random.nextInt(maxNumber*2+1);
                while (wrongAnswer==a+b) {
                    wrongAnswer = random.nextInt(maxNumber*2+1);
                }
                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }
}
