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

    String articleName, articleText, posPercent, negPercent, feedback, timeTotal, avePercent;
    RecyclerView recyclerView;
    DatabaseReference spid;
    rViewAdapter rViewAdapter;
    ArrayList<Articles> list;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String uid = user.getUid();

    @Override
    public void onItemClicked(Articles articles) {
        Intent intent = new Intent(Home.this, result.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        addArt = findViewById(R.id.addArt_btn);


        addArt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, result.class);
                intent.putExtra("article_Name", articleName);
                intent.putExtra("art_Text", articleText);
                intent.putExtra("pos_Percent", posPercent);
                intent.putExtra("neg_Percent", negPercent);
                intent.putExtra("feedback", feedback);
                intent.putExtra("time_total",timeTotal);
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
}