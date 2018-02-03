package com.avengers.android.avengerstest;

/**
 * Created by MarianCocota on 03/02/2018.
 */

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RatingBar;
import android.widget.Toast;

public class Results extends AppCompatActivity {

    int correctAnswers;
    String playerName;

    MediaPlayer mysound;

    @Override
    protected void onPause() {
        super.onPause();
        mysound.release();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_final);
        mysound = MediaPlayer.create(Results.this,R.raw.themesong);
        mysound.start();


        //** Star rating of score and display a Toast with the number of correct answers
        Intent Results = getIntent();
        correctAnswers = Results.getIntExtra("correct_answers", 0);
        playerName = Results.getStringExtra("player_name");
        RatingBar stars = findViewById(R.id.stars);
        stars.setRating(correctAnswers);
        Toast.makeText(this, playerName + getString(R.string.scoreMessage) + String.valueOf(correctAnswers), Toast.LENGTH_SHORT).show();
    }

    //** Share the results with other apps
    public void shareResults(View view) {
        String scoreMessage = playerName + getString(R.string.scoreMessage) + String.valueOf(correctAnswers);
        Intent shareScore = new Intent(Intent.ACTION_SEND);
        shareScore.setType("text/plain");
        shareScore.putExtra(Intent.EXTRA_TEXT, scoreMessage);
        // Create intent to show the chooser dialog for sharing to other apps
        Intent chooser = Intent.createChooser(shareScore, scoreMessage);
        startActivity(chooser);
    }
}
