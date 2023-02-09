package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class result extends AppCompatActivity {

    private String articleName, articleText, posPercent, negPercent, feedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        articleName = getIntent().getStringExtra("article_Name");
//        System.out.println("Article Name: " + articleName);

        articleText = getIntent().getStringExtra("article_Text");
//        System.out.println("Article Text: " + articleText);

    }
}