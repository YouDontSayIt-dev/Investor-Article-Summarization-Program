package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity {

    EditText username, email, password, confirm;
    Button signup;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = (EditText) findViewById(R.id.usernameInput);
        email = (EditText) findViewById(R.id.emailInput);
        password = (EditText) findViewById(R.id.passwordInput);
        confirm = (EditText) findViewById(R.id.confirmpasswordInput);

        signup = (Button) findViewById(R.id.btn_signin);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String mail = email.getText().toString();
                String pass = password.getText().toString();
                String conf = confirm.getText().toString();

                if(user.equals("")||mail.equals("")||pass.equals("")||conf.equals(""))
                    Toast.makeText(signup.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                else {
                    if(pass.equals(conf)){
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser==false){
                            Boolean insert = DB.insertData(user, pass);
                            if(insert==true){
                                Toast.makeText(signup.this, "Successfully signed up", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Home.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(signup.this, "Sign up failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(signup.this, "User already exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(signup.this, "Password does not match", Toast.LENGTH_SHORT).show();
                    }
                }

                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);

            }
        });

    }
}