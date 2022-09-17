package com.example.summer_project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameOver3 extends AppCompatActivity {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over1);
        int points = getIntent().getExtras().getInt("points");
        SharedPreferences sharedPreferences = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore = sharedPreferences.getInt("HIGH_SCORE1", 0);
        TextView final_score = findViewById(R.id.final_score);
        TextView high_score3 = findViewById(R.id.high_score1);

        final_score.setText(points + "");
//end activity of the hard level
        if (points > highScore) {
            //Update high score
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("HIGH_SCORE3", points);
            editor.apply();

            high_score3.setText("High Score : " + points);
        } else {
            high_score3.setText("High Score : " + highScore);
        }
    }
//button to retry the level
    public void restart(View view) {
        Intent intent = new Intent(GameOver3.this, MenuActivity2.class);
        startActivity(intent);
        finish();
    }
//button to go on Main Menu
    public void exit(View view) {
        Intent intent = new Intent(GameOver3.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}
