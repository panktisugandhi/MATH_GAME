package com.example.maths_game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Random;

public class GameActivity extends AppCompatActivity {

    //Banner Ad

    TextView txtTime, txtScore, txtResult, txtFirstQuestion, txtSecondQuestion;
    ImageButton btnCorrect, btnIncorrect;
    boolean isResultCorrect;
    int seconds = 30;
    private int score, negscore = 0;
    private boolean stopTimer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        txtFirstQuestion = (TextView) findViewById(R.id.txtFirstValue);
        txtSecondQuestion = (TextView) findViewById(R.id.txtSecondValue);
        txtResult = (TextView) findViewById(R.id.txtResult);
        txtTime = (TextView) findViewById(R.id.txtTime);
        txtScore = (TextView) findViewById(R.id.txtScore);
        btnCorrect = (ImageButton) findViewById(R.id.btnCorrect);

        if (negscore != 3) {
            timer();

        }

        btnIncorrect = (ImageButton) findViewById(R.id.btnIncorrect);
        btnCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyAnswer(true);
            }
        });
        btnIncorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyAnswer(false);
            }
        });
        generateQuestion();
    }

    //Generate Random Number between 1 to 100
    //Count 3 Negative Answer if True then Called ScoreActivity.
    private void generateQuestion() {

        if (negscore == 3) {
            seconds = 30;
            stopTimer = true;

            Intent intent = new Intent(this, ScoreActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
            Toast.makeText(this, "Result", Toast.LENGTH_SHORT).show();
        } else {
            isResultCorrect = true;
            Random random = new Random();
            int a = random.nextInt(100);
            int b = random.nextInt(100);
            int result = a + b;
            float f = random.nextFloat();
            if (f > 0.5f) {
                result = random.nextInt(100);
                isResultCorrect = false;
            }
            txtFirstQuestion.setText(a + "");
            txtSecondQuestion.setText("" + b);
            txtResult.setText("" + result);
        }
    }

    //Verify Answer if Answer is Right then add 5 in Score
    //or Wrong then Count 3 Wrong answer
    public void verifyAnswer(boolean answer) {
        if (answer == isResultCorrect) {
            score += 5;
            txtScore.setText("SCORE : " + score);
        } else {
            negscore += 1;
            txtScore.setText("SCORE : " + score);
            Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(100);
        }
        generateQuestion();
    }

    //30 Secs end then Game Over and Show Result
    private void timer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                txtTime.setText("TIME : " + seconds);
                seconds--;
                if (seconds < 0) {
                    Intent i = new Intent(GameActivity.this, ScoreActivity.class);
                    i.putExtra("score", score);
                    startActivity(i);
                    stopTimer = true;
//                    showInterstitial();
                }
                if (stopTimer == false) {
                    handler.postDelayed(this, 1000);
                }
            }
        });
    }

    //Show Full Screen Ads

    @Override
    protected void onPause() {
        super.onPause();
        stopTimer = false;
        finish();
    }
}
