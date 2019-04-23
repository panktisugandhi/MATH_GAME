package com.example.maths_game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class ScoreActivity extends AppCompatActivity {


    ImageButton btnShare;
    TextView textScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);


        textScore = (TextView) findViewById(R.id.score);
        int score = getIntent().getIntExtra("score", 0);
        textScore.setText("" + score);

        btnShare = (ImageButton) findViewById(R.id.btnShare);

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Math Game - Fun way to learn Maths. http://www.play.google.com");
                startActivity(intent);
//                showInterstitial();
            }
        });
    }

}
