package com.example.summer_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class MenuActivity2 extends AppCompatActivity {
    
     private SoundPlayer soundPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);

        ImageView back_button2 = findViewById(R.id.back_button2);
        ImageView level1shooter = findViewById(R.id.level1shooter);
        ImageView level2shooter = findViewById(R.id.level2shooter);
        ImageView level3shooter = findViewById(R.id.level3shooter);

        soundPlayer = new SoundPlayer(this);
//select the level
        level1shooter.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity2.this ,StartUp1.class);
            startActivity(intent);
            soundPlayer.playButtonShooterSound();
            overridePendingTransition(R.anim.static_animation,R.anim.zoom_in);
        });

        level2shooter.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity2.this ,StartUp2.class);
            startActivity(intent);
            soundPlayer.playButtonShooterSound();
            overridePendingTransition(R.anim.static_animation,R.anim.zoom_in);
        });

        level3shooter.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity2.this ,StartUp3.class);
            startActivity(intent);
            soundPlayer.playButtonShooterSound();
            overridePendingTransition(R.anim.static_animation,R.anim.zoom_in);
        });
//Back to main menu
        back_button2.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity2.this ,MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.static_animation,R.anim.zoom_in);
        });
    }

    @Override
    public void onBackPressed() {}
}
