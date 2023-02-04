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

        Button signup = findViewById(R.id.btn_signup);
        Button login = findViewById(R.id.btn_login);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(welcomeScreen.this,signup.class);
                startActivity(intent);
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(welcomeScreen.this,login.class);
                startActivity(intent);
                finish();
            }
        });
    }
}