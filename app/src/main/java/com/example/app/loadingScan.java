package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.*;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.sentiment.*;
import edu.stanford.nlp.util.CoreMap;

public class loadingScan extends AppCompatActivity {

    private int posCount = 0, negCount = 0, posTotal = 0, negTotal = 0;
    private String articleName, articleText, posPercent, negPercent, feedback;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_scan);

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

//        System.out.println(""+posCount+" "+negCount);
        if (negCount == posCount) {
//            System.out.println("Overall Neutral");
            feedback = "Overall Neutral";
        }else if(negCount > posCount) {
//            System.out.println("Overall Negative");
            feedback = "Overall Negative";
        }else if (negCount < posCount) {
//            System.out.println("Overall Positive");
            feedback = "Overall Positive";
        }

        posTotal = (posCount / (posCount + negCount)) * 100;
        negTotal = (negCount / (posCount + negCount)) * 100;

        posPercent = Integer.toString(posTotal);
        negPercent = Integer.toString(negTotal);


        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(loadingScan.this, result.class);
                i.putExtra("art_Name", articleName);
                i.putExtra("art_Text", articleText);
                i.putExtra("pos_Percent", posPercent);
                i.putExtra("neg_Percent", negPercent);
                i.putExtra("feedback", feedback);
                startActivity(i);
                finish();
            }
        }, 5000);

    }
}