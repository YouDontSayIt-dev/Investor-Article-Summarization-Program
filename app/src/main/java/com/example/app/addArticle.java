package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class addArticle extends AppCompatActivity {
    ImageButton btn_return;
    ImageButton btn_scan;

    private String articleText;
    private String articleName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);
        btn_return = (ImageButton) findViewById(R.id.btn_return);

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
                finish();
            }
        });

        //scan
        btn_scan = (ImageButton) findViewById(R.id.btn_scan);
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), loadingScan.class);
                //get article name
                EditText input_article_name = findViewById(R.id.articleName);
                articleName = input_article_name.getText().toString();

                //get article text
                EditText input_article_text = findViewById(R.id.multiLineArticleText);
                articleText = input_article_text.getText().toString();

                intent.putExtra("article_Name", articleName);
                intent.putExtra("article_Text", articleText);
                startActivity(intent);
                finish();
            }
        });
    }
}