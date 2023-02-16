package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;

import android.util.Log;


    public class signup extends AppCompatActivity implements OnGestureListener {
        public static final int SWIPE_THRESHOLD = 100;
        public static final int SWIPE_VELOCITY_THRESHOLD = 100;
        private GestureDetectorCompat signupDetect;
        EditText username, email, password, confirmpassword;
        Button signupbtn;

        TextView loginTXT;

        String TAG = "signup";

        FirebaseAuth mAuth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_signup);
            mAuth = FirebaseAuth.getInstance();
            username = (EditText) findViewById(R.id.SIusernameInput);
            email = (EditText) findViewById(R.id.SIemailInput);
            password = (EditText) findViewById(R.id.SIpasswordInput);
            confirmpassword = (EditText) findViewById(R.id.SIconfirmpasswordInput);
            signupbtn = (Button) findViewById(R.id.btn_signin);
            loginTXT = (TextView) findViewById(R.id.logintxt);

            loginTXT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(signup.this, login.class);
                    startActivity(intent);
                }
            });
            signupbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String TXTusername, TXTemail, TXTpassword, TXTconfirmPassword;
                    TXTusername = String.valueOf(username.getText());
                    TXTemail = String.valueOf(email.getText());
                    TXTpassword = String.valueOf(password.getText());
                    TXTconfirmPassword = String.valueOf(confirmpassword.getText());
                    if (!TXTpassword.equals(TXTconfirmPassword)) {
                        Toast.makeText(signup.this, "Password does not match", Toast.LENGTH_SHORT).show();
                        confirmpassword.setError("Password must be the same");
                        confirmpassword.requestFocus();
                        return;
                    }
                    if (TextUtils.isEmpty(TXTemail)) {
                        Toast.makeText(signup.this, "Enter email", Toast.LENGTH_SHORT).show();
                        email.setError("Email is required");
                        email.requestFocus();
                        return;
                    }else if (!Patterns.EMAIL_ADDRESS.matcher(TXTemail).matches()) {
                        Toast.makeText(signup.this, "Valid email is required", Toast.LENGTH_SHORT).show();
                        email.setError("Valid email is required");
                        email.requestFocus();
                    }
                    if (TextUtils.isEmpty(TXTusername)) {
                        Toast.makeText(signup.this, "Enter username", Toast.LENGTH_SHORT).show();
                        username.setError("Username is required");
                        username.requestFocus();
                        return;
                    }
                    if (TextUtils.isEmpty(TXTpassword)) {
                        Toast.makeText(signup.this, "Enter password", Toast.LENGTH_SHORT).show();
                        password.setError("Password is required");
                        password.requestFocus();
                        return;
                    }

                    mAuth.createUserWithEmailAndPassword(TXTemail, TXTpassword)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "createUserWithEmail:success");
                                        Toast.makeText(signup.this, "Account created.",
                                                Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(signup.this, welcomeScreen.class);
                                        startActivity(intent);
                                        finish();

                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(signup.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });




                }
            });

            signupDetect = new GestureDetectorCompat(this,this);

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
            Intent intent = new Intent(signup.this, welcomeScreen.class);
            startActivity(intent);
        }

        private void onSwipeLeft() {

        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            signupDetect.onTouchEvent(event);
            return super.onTouchEvent(event);
        }
    }

//        EditText username, email, password, confirm;
//        Button signup;
//        DBHelper DB;
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_signup);
//
//            username = (EditText) findViewById(R.id.usernameInput);
//            email = (EditText) findViewById(R.id.emailInput);
//            password = (EditText) findViewById(R.id.passwordInput);
//            confirm = (EditText) findViewById(R.id.confirmpasswordInput);
//
//            signup = (Button) findViewById(R.id.btn_signin);
//            DB = new DBHelper(this);
//
//            signup.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                    String user = username.getText().toString();
//                    String mail = email.getText().toString();
//                    String pass = password.getText().toString();
//                    String conf = confirm.getText().toString();
//
//                    if (user.equals("") || mail.equals("") || pass.equals("") || conf.equals(""))
//                        Toast.makeText(signup.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
//                    else {
//                        if (pass.equals(conf)) {
//                            Boolean checkuser = DB.checkusername(user);
//                            if (checkuser == false) {
//                                Boolean insert = DB.insertData(user, pass);
//                                if (insert == true) {
//                                    Toast.makeText(signup.this, "Successfully signed up", Toast.LENGTH_SHORT).show();
//                                    Intent intent = new Intent(getApplicationContext(), Home.class);
//                                    startActivity(intent);
//                                } else {
//                                    Toast.makeText(signup.this, "Sign up failed", Toast.LENGTH_SHORT).show();
//                                }
//                            } else {
//                                Toast.makeText(signup.this, "User already exists", Toast.LENGTH_SHORT).show();
//                            }
//                        } else {
//                            Toast.makeText(signup.this, "Password does not match", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    Intent intent = new Intent(getApplicationContext(), login.class);
//                    startActivity(intent);
//
//                }
//            });
//
//        }
//    }

