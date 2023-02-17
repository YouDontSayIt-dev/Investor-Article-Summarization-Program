package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class result extends AppCompatActivity implements GestureDetector.OnGestureListener {

    ImageButton result;

    public static final int SWIPE_THRESHOLD = 100;
    public static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private GestureDetectorCompat resultDetect;

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
        resultDetect = new GestureDetectorCompat(this,this);
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
        Intent intent = new Intent(result.this, Home.class);
        startActivity(intent);
        finish();
    }

    private void onSwipeLeft() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        resultDetect.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}
