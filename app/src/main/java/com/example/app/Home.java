package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.Intent;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.ValueEventListener;
import android.view.LayoutInflater;

import java.util.ArrayList;


public class Home extends AppCompatActivity implements SelectListener {
    Button addArt;

    ImageView logout;

    String articleName, articleText, posPercent, negPercent, feedback, timeTotal, avePercent;
    RecyclerView recyclerView;
    DatabaseReference spid;
    rViewAdapter rViewAdapter;
    ArrayList<Articles> list;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
     String uid = user.getUid();
     String name = user.getEmail();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        addArt = findViewById(R.id.addArt_btn);
        logout = (ImageView) findViewById(R.id.logoutIcon);

        TextView hello_txt = (TextView) findViewById(R.id.hello);
        hello_txt.setText("Hello, " + name);
        addArt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, addArticle.class);
                startActivity(intent);
                finish();
            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,logout.class);
                startActivity(intent);
                finish();
            }
        });

        recyclerView = findViewById(R.id.articleList);
        spid = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Articles");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        rViewAdapter = new rViewAdapter(this,list,this);
        recyclerView.setAdapter(rViewAdapter);
        spid.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Articles articles = dataSnapshot.getValue(Articles.class);
                    list.add(articles);
                }
                rViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
                @Override
                public void onItemClicked(Articles articles) {
                    String articleName, articleText, posPercent, negPercent, feedback, timeTotal;
                    articleName = articles.getArticle_Name();
                    posPercent = articles.getPosPercent();
                    negPercent = articles.getNegPercent();
                    feedback = articles.getFeedback();
                    timeTotal = articles.getTime();
                    Intent intent = new Intent(Home.this, result.class);
                    intent.putExtra("article_Name", articleName);
                    intent.putExtra("pos_Percent", posPercent);
                    intent.putExtra("neg_Percent", negPercent);
                    intent.putExtra("feedback", feedback);
                    intent.putExtra("time_total",timeTotal);
                    startActivity(intent);
                    finish();
                }
}