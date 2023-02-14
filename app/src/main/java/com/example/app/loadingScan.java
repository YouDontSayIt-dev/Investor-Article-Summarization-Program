package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Time;
import java.util.*;
import java.util.concurrent.TimeUnit;


import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.sentiment.*;
import edu.stanford.nlp.util.CoreMap;




public class loadingScan extends AppCompatActivity {
    long start = System.nanoTime();
    private int posCount = 0, negCount = 0;
    private double posTotal=0, negTotal=0, aveTotal=0, total=0, roundedPos, roundedNeg, roundedAve;
    private String articleName, articleText, posPercent, negPercent, feedback, timeTotal, avePercent;
    private Handler handler = new Handler();
    boolean stopLoop = true;
    private ImageView circle;

    DatabaseReference spid;
    Articles article;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String uid = user.getUid();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_scan);

         circle = (ImageView) findViewById(R.id.loadingCircle);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(stopLoop){

                    circle.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate));

                    articleName = getIntent().getStringExtra("article_Name");
//        System.out.println("Article Name: " + articleName);

                    articleText = getIntent().getStringExtra("article_Text");
//        System.out.println("Article Text: " + articleText);

                    Properties props = new Properties();
                    props.setProperty("annotators", "tokenize, ssplit, pos, lemma, parse, sentiment");
//        props.setProperty("stanford.corenlp.models", "C:\\Users\\blanc\\Downloads\\stanford-corenlp-4.5.1\\stanford-corenlp-4.5.1");

                    // create pipeline
                    StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
                    // Create an Annotation object with the text to process
                    Annotation document = new Annotation(articleText);

                    // Run the pipeline on the document
                    pipeline.annotate(document);

                    // Iterate over the sentences in the document
                    for (CoreMap sentence : document.get(CoreAnnotations.SentencesAnnotation.class)) {

                        // Get the sentiment score of the sentence
                        String sentiment = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
                        System.out.println("Sentence sentiment: " + sentiment);
                        if(sentiment.equals("Positive")|| sentiment.equals("Very Positive")) {
                            posCount ++;
                        }else if (sentiment.equals("Negative") || sentiment.equals("Very Negative")) {
                            negCount ++;
                        }
                    }

                    long end = System.nanoTime();
                    long elapsedTime = end - start;
                    int secondsConvert = 1_000_000_000;
                    double elapsedTimeinSecond = (double) elapsedTime / secondsConvert;
                    long convert = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
                    total = posCount+negCount;
                    posTotal = (double) posCount/total*100;
                    negTotal = (double) negCount/total*100;
//                    aveTotal =  total/2;

//                    double sample = (double) 2/(2+3)*100;
//                    System.out.println("HERE");
//                    System.out.println(sample);

                    System.out.println(""+posCount+" "+negCount);
                    System.out.println("HERE");
                    System.out.println(""+posTotal+" "+negTotal);
                    if(negTotal > posTotal) {
                        System.out.println("Overall Negative");
                        feedback = "Overall Negative";
                    }else if (negTotal < posTotal) {
                        System.out.println("Overall Positive");
                        feedback = "Overall Positive";
                    }else if (negTotal == posTotal) {
                        System.out.println("Overall Neutral");
                        feedback = "Overall Neutral";
                    }else if (negCount == posCount){
                        System.out.println("Overall Neutral");
                        feedback = "Overall Neutral";
                    }

                    roundedPos = (double) Math.round(posTotal * 100)/100;
                    roundedNeg = (double) Math.round(negTotal * 100)/100;
//                    roundedAve = (double) Math.round(aveTotal * 100)/100;


                    timeTotal =  Float.toString(convert);
                    posPercent = Double.toString(roundedPos);
                    negPercent = Double.toString(roundedNeg);
//                    avePercent = Double.toString(roundedAve);


                    article = new Articles();
                    spid = FirebaseDatabase.getInstance().getReference("Users").child(uid).child("Articles");
                    article.setArticle_Name(articleName);
                    article.setPosPercent(posPercent + " %");
                    article.setNegPercent(negPercent + " %") ;
//                    article.setAvePercent(avePercent);
                    article.setFeedback(feedback);
                    article.setTime(timeTotal + " seconds");
                    spid.push().setValue(article);

                    Intent i = new Intent(loadingScan.this, result.class);
                    i.putExtra("article_Name", articleName);
                    i.putExtra("art_Text", articleText);
                    i.putExtra("pos_Percent", posPercent);
                    i.putExtra("neg_Percent", negPercent);
                    i.putExtra("feedback", feedback);
                    i.putExtra("time_total",timeTotal);
                    startActivity(i);
                    finish();

                    stopLoop = false;
                }
            }
        }).start();

    }
}