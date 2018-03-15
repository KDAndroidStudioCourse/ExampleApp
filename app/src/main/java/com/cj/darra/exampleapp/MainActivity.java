package com.cj.darra.exampleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Currency Converter");
    }

    public void  clickFunction(View view) {
        EditText amountEditText = (EditText) findViewById(R.id.amountEditText);

        Log.i("Info", "Button pressed!");

        Log.i("Amount in $", "" + amountEditText.getText().toString());

        double amount = Double.parseDouble(amountEditText.getText().toString()) * 0.808668931;

        Log.i("Amount in €", "" + amount);

        Toast.makeText(this, "It is " + String.format("%.2f", amount) + "€!", Toast.LENGTH_LONG).show();
    }
}
