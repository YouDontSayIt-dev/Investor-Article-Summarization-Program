package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import androidx.annotation.NonNull;
import com.google.firebase.auth.AuthResult;

public class logout extends AppCompatActivity {

    ImageButton logout;
    TextView username,email;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    String uid = user.getUid();
    String name = user.getEmail();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        logout = (ImageButton) findViewById(R.id.logoutButton);
        username = (TextView) findViewById(R.id.logoutUsername);
        email = (TextView) findViewById(R.id.logoutEmail);

        username.setText(uid);
        email.setText(name);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(logout.this,welcomeScreen.class);
                startActivity(intent);
                finish();
            }
        });



    }
}