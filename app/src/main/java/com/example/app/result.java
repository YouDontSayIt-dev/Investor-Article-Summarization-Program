package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class result extends AppCompatActivity {

    ImageButton result;

    private String articleName, posPercent, negPercent, feedback, timeTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        articleName = getIntent().getStringExtra("article_Name");
//        System.out.println("Article Name: " + articleName);

        posPercent = getIntent().getStringExtra("pos_Percent");
        negPercent = getIntent().getStringExtra("neg_Percent");
        feedback = getIntent().getStringExtra("feedback");
        timeTotal = getIntent().getStringExtra("time_total");

        TextView articleNameResult = (TextView) findViewById(R.id.articleName);
        TextView positiveResult = (TextView) findViewById(R.id.textViewPositiveResult);
        TextView negativeResult = (TextView) findViewById(R.id.textViewNegativeResult);
        TextView feedbackResult = (TextView) findViewById(R.id.textViewGrade);
        TextView timeResult = (TextView) findViewById(R.id.textViewTime);

        articleNameResult.setText(articleName);
        positiveResult.setText(posPercent);
        negativeResult.setText(negPercent);
        feedbackResult.setText(feedback);
        timeResult.setText(timeTotal);


        result = (ImageButton) findViewById(R.id.return_btn);

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(result.this, Home.class);
                startActivity(intent);
                finish();
            }
        });

    }


}