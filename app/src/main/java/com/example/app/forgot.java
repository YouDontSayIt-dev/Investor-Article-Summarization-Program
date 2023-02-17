package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import android.util.Log;


public class forgot extends AppCompatActivity {

    private EditText forgotPass;
    private Button emailRst;
    private FirebaseAuth authProfile;
    private final static String TAG = "forgot";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        forgotPass = (EditText) findViewById(R.id.forgotPass_txt);
        emailRst = (Button) findViewById(R.id.btn_reset_acct);

        emailRst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailTXT = forgotPass.getText().toString();

                if (TextUtils.isEmpty(emailTXT)) {
                    Toast.makeText(forgot.this, "Enter email", Toast.LENGTH_SHORT).show();
                    forgotPass.setError("Email is required");
                    forgotPass.requestFocus();

                } else if (!Patterns.EMAIL_ADDRESS.matcher(emailTXT).matches()) {
                    Toast.makeText(forgot.this, "Valid email is required", Toast.LENGTH_SHORT).show();
                    forgotPass.setError("Valid email is required");
                    forgotPass.requestFocus();
                } else {
                    resetPassword(emailTXT);
                }
            }
        });
    }
    private void resetPassword(String email){
        authProfile = FirebaseAuth.getInstance();
        authProfile.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(forgot.this, "Please check you inbox for the password reset link",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), login.class);
                    startActivity(intent);
                }else{
                    try{
                        throw task.getException();
                    }catch (FirebaseAuthInvalidUserException e){
                        forgotPass.setError("User does not exist. Please register again");
                    }catch (Exception e){
                        Log.e(TAG, e.getMessage());
                        Toast.makeText(forgot.this, e.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}



