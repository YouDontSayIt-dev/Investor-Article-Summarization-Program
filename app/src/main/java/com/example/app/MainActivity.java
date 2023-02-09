package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.*;

import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    Handler h = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        h.postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext, welcomeScreen.class);
                startActivity(i);
                finish();
            }
        }, 5000);


    }
}