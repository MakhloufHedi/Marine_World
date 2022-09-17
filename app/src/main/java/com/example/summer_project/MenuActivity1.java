//made by hedi the foody fish level selection menu
package com.example.summer_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class MenuActivity1 extends AppCompatActivity {

    private SoundPlayer soundPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1);

        ImageView level1 = findViewById(R.id.level1);
        ImageView level2 = findViewById(R.id.level2);
        ImageView level3 = findViewById(R.id.level3);
        ImageView back_button1 = findViewById(R.id.back_button1);

        soundPlayer = new SoundPlayer(this);

        level1.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity1.this ,StartEasy1Activity.class);
            startActivity(intent);
            soundPlayer.playButtonSound();
        });

        level2.setOnClickListener(view -> {
            soundPlayer.playButtonSound();
            Intent intent = new Intent(MenuActivity1.this ,StartMedium1Activity.class);
            startActivity(intent);
        });

        level3.setOnClickListener(view -> {
            soundPlayer.playButtonSound();
            Intent intent = new Intent(MenuActivity1.this ,StartHard1Activity.class);
            startActivity(intent);
        });

        back_button1.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity1.this ,MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.static_animation,R.anim.zoom_in);
        });

    }

    @Override
    public void onBackPressed() {}
}
