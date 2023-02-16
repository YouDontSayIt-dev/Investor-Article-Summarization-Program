package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;

import android.util.Log;

public class login extends AppCompatActivity implements OnGestureListener {
    public static final int SWIPE_THRESHOLD = 100;
    public static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private GestureDetectorCompat loginDetect;
    FirebaseAuth mAuth;
    String TAG = "login";
    EditText email, password;
    Button loginbtn;

    TextView forgot;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.LOemailInput);
        password = (EditText) findViewById(R.id.LOpasswordInput);
        loginbtn = (Button) findViewById(R.id.btn_login_acct);
        forgot = (TextView) findViewById(R.id.forgot);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String TXTemail, TXTpassword;
                TXTemail = String.valueOf(email.getText());
                TXTpassword = String.valueOf(password.getText());

                if (TextUtils.isEmpty(TXTemail)) {
                    Toast.makeText(login.this, "Enter email", Toast.LENGTH_SHORT).show();
                    email.setError("Email is required");
                    email.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(TXTpassword)) {
                    Toast.makeText(login.this, "Enter password", Toast.LENGTH_SHORT).show();
                    password.setError("Password is required");
                    password.requestFocus();
                    return;
                }

                mAuth.signInWithEmailAndPassword(TXTemail, TXTpassword)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(login.this, "Login Success",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Home.class);
                                    startActivity(intent);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), forgot.class);
                startActivity(intent);
            }
        });
        loginDetect = new GestureDetectorCompat(this,this);
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
                onSwipeLeft();
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
        Intent intent = new Intent(login.this, welcomeScreen.class);
        startActivity(intent);
    }

    private void onSwipeLeft() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        loginDetect.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
//
//        username = (EditText) findViewById(R.id.usernamelogInput);
//        password = (EditText) findViewById(R.id.passwordlogInput);
//
//        login = (Button) findViewById(R.id.btn_login);
//        DB = new DBHelper(this);
//
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String user = username.getText().toString();
//                String pass = password.getText().toString();
//
//                if(user.equals("")||pass.equals(""))
//                    Toast.makeText(login.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
//                else {
//                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
//                    if(checkuserpass==true){
//                        Toast.makeText(login.this, "Logged in", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(getApplicationContext(), Home.class);
//                        startActivity(intent);
//                    }
//                    else{
//                        Toast.makeText(login.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        });
//
//    }
//}