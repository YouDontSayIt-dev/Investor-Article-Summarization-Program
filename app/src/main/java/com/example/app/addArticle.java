package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;

public class addArticle extends AppCompatActivity implements GestureDetector.OnGestureListener {
    ImageButton btn_return;
    ImageButton btn_scan;

    public static final int SWIPE_THRESHOLD = 100;
    public static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private GestureDetectorCompat addArtDetect;

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
        addArtDetect = new GestureDetectorCompat(this,this);
    }
    @Override
    public boolean onDown(@NonNull MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(@NonNull MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(@NonNull MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(@NonNull MotionEvent motionEvent, @NonNull MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(@NonNull MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(@NonNull MotionEvent downEvent, @NonNull MotionEvent moveEvent, float velocityX, float velocityY) {
        boolean result = false;
        float diffY = moveEvent.getY() - downEvent.getY();
        float diffX = moveEvent.getX() - downEvent.getX();

        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
            if (diffX > 0) {
                onSwipeRight();
            } else {
                //         onSwipeLeft();
            }
            result = true;
        } else {
            if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY > 0) {
//                        onSwipeBottom();
                } else {
//                        onSwipeUp();
                }
            }
            result = true;
        }
        return result;
    }

    private void onSwipeRight() {
        Intent intent = new Intent(addArticle.this, Home.class);
        startActivity(intent);
        finish();
    }

    private void onSwipeLeft() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        addArtDetect.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}