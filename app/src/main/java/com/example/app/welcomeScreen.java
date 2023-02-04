package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.*;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class welcomeScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        Button button = findViewById(R.id.btn_signup);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(welcomeScreen.this,signup.class);
                startActivity(intent);
                finish();
            }
        });
    }
}