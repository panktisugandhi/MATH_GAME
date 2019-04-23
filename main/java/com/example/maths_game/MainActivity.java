package com.example.maths_game;

import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {


    ImageButton btnPlay, btnShare, btnRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btnShare = (ImageButton) findViewById(R.id.btnShare);
        btnRate = (ImageButton) findViewById(R.id.btnRate);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, GameActivity.class);
                startActivity(i);
                Toast.makeText(MainActivity.this, "Let's Game Begin", Toast.LENGTH_LONG).show();

            }
        });
        btnRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/panktisugandhi"));
                startActivity(in);
                Toast.makeText(MainActivity.this, "Rate your App on Google Play Store...", Toast.LENGTH_LONG).show();
            }
        });
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Maths Game - Fun way to learn Maths. http://www.play.google.com");
                startActivity(intent);
            }
        });
    }

    //Show Full Screen Ads
}
