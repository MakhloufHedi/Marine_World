package com.example.summer_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class EndMedium1Activity extends AppCompatActivity {

    private SoundPlayer soundPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_medium1);

        TextView final_score = findViewById(R.id.final_score);
        TextView high_score2 = findViewById(R.id.high_score2);

        int score = getIntent().getIntExtra("SCORE", 0);
        final_score.setText(score + "");

        //High Score
        SharedPreferences sharedPreferences = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE);
        int highScore = sharedPreferences.getInt("HIGH_SCORE2", 0);

        if (score > highScore) {
            //Update high score
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("HIGH_SCORE2", score);
            editor.apply();

            high_score2.setText("High Score : " + score);
        } else {
            high_score2.setText("High Score : " + highScore);
        }

        Button retry_button_medium = findViewById(R.id.retry_button_medium);
        Button game_button_medium = findViewById(R.id.game_button_medium);
        Button menu_button_medium = findViewById(R.id.menu_button_medium);

        soundPlayer = new SoundPlayer(this);

        retry_button_medium.setOnClickListener(view -> {
            soundPlayer.playButtonSound();
            Intent intent = new Intent(EndMedium1Activity.this ,GameMedium1Activity.class);
            startActivity(intent);
        });

        game_button_medium.setOnClickListener(view -> {
            soundPlayer.playButtonSound();
            Intent intent = new Intent(EndMedium1Activity.this ,MenuActivity1.class);
            startActivity(intent);
        });

        menu_button_medium.setOnClickListener(view -> {
            soundPlayer.playButtonSound();
            Intent intent = new Intent(EndMedium1Activity.this ,MainActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {}
}