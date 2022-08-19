package com.example.summer_project;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class StageActivity1 extends AppCompatActivity {
    MediaPlayer sonbtn1;
    MediaPlayer sonbtn2;
    MediaPlayer sonbtn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage1);
        ImageView stage_one = findViewById(R.id.stage_one);
        ImageView stage_two = findViewById(R.id.stage_two);
        ImageView stage_three = findViewById(R.id.stage_three);


        stage_one.setOnClickListener(view -> {
            sonbtn1=MediaPlayer.create(this,R.raw.startbutton);
            if(sonbtn1!=null)
                sonbtn1.start();
            Intent intent = new Intent(StageActivity1.this, MainActivity1.class);
            startActivity(intent);
            finish();

        });
        stage_two.setOnClickListener(view -> {
            sonbtn2=MediaPlayer.create(this,R.raw.startbutton);
            if(sonbtn2!=null)
                sonbtn2.start();
            Intent intent = new Intent(StageActivity1.this, MainActivity2.class);
            startActivity(intent);
            finish();

        });
        stage_three.setOnClickListener(view -> {
            sonbtn3=MediaPlayer.create(this,R.raw.startbutton);
            if(sonbtn3!=null)
                sonbtn3.start();
            Intent intent = new Intent(StageActivity1.this, MainActivity3.class);
            startActivity(intent);
            finish();

        });
    }
}