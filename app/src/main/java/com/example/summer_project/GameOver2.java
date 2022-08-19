package com.example.summer_project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameOver2 extends AppCompatActivity {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over1);
        int points = getIntent().getExtras().getInt("points");
        SharedPreferences sharedPreferences = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore = sharedPreferences.getInt("HIGH_SCORE1", 0);
        TextView final_score = findViewById(R.id.final_score);
        TextView high_score2 = findViewById(R.id.high_score1);

        final_score.setText(points + "");

        if (points > highScore) {
            //Update high score
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("HIGH_SCORE2", points);
            editor.apply();

            high_score2.setText("High Score : " + points);
        } else {
            high_score2.setText("High Score : " + highScore);
        }
    }

    public void restart(View view) {
        Intent intent = new Intent(GameOver2.this, StageActivity1.class);
        startActivity(intent);
        finish();
    }

    public void exit(View view) {
        Intent intent = new Intent(GameOver2.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}