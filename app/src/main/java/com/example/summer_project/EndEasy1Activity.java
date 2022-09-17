// made by hedi : end activity of the easy level , it shows highscore , score and buttons to retry , go to levels or change game
package com.example.summer_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class EndEasy1Activity extends AppCompatActivity {

    private SoundPlayer soundPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_easy1);

        TextView final_score = findViewById(R.id.final_score);
        TextView high_score1 = findViewById(R.id.high_score1);

        int score = getIntent().getIntExtra("SCORE", 0);
        final_score.setText(score + "");

        //get High Score using a shared preferance
        SharedPreferences sharedPreferences = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore = sharedPreferences.getInt("HIGH_SCORE1", 0);

        if (score > highScore) {
            //Update high score
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("HIGH_SCORE1", score);
            editor.apply();  

            high_score1.setText("High Score : " + score);
        } else {
            high_score1.setText("High Score : " + highScore);
        }

        Button retry_button_easy = findViewById(R.id.retry_button_easy);
        Button game_button_easy = findViewById(R.id.game_button_easy);
        Button menu_button_easy = findViewById(R.id.menu_button_easy);

        soundPlayer = new SoundPlayer(this);
// 3 buttons , retry button , go to level select area button and go to game select area button
        retry_button_easy.setOnClickListener(view -> {
            soundPlayer.playButtonSound();
            Intent intent = new Intent(EndEasy1Activity.this ,GameEasy1Activity.class);
            startActivity(intent);
        });

        game_button_easy.setOnClickListener(view -> {
            soundPlayer.playButtonSound();
            Intent intent = new Intent(EndEasy1Activity.this ,MenuActivity1.class);
            startActivity(intent);
        });

        menu_button_easy.setOnClickListener(view -> {
            soundPlayer.playButtonSound();
            Intent intent = new Intent(EndEasy1Activity.this ,MainActivity.class);
            startActivity(intent);
        });
    }
// only go back using the bottons i made not by pressing the back button 
    @Override
    public void onBackPressed() {}
}
