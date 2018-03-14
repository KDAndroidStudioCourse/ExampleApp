package com.cj.darra.exampleapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void  clickFunction(View view) {
        EditText usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        EditText passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        Log.i("Info", "Button pressed!");

        Log.i("Values", "Username: " + usernameEditText.getText().toString() + ", Password: " + passwordEditText.getText().toString());

        Toast.makeText(this, "Hi there!", Toast.LENGTH_SHORT).show();
    }
}
