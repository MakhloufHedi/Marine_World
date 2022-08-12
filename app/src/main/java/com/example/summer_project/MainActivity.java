package com.example.summer_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;


import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    LottieAnimationView welcome_anim;
    CardView game1, game2, game3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcome_anim = findViewById(R.id.welcome_anim);
        game1 = findViewById(R.id.game1);
        game2 = findViewById(R.id.game2);
        game3 = findViewById(R.id.game3);

        game1.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MenuActivity1.class);
            startActivity(intent);
            overridePendingTransition(R.anim.zoom_out,R.anim.static_animation);
        });

        game2.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MenuActivity2.class);
            startActivity(intent);
            overridePendingTransition(R.anim.zoom_out,R.anim.static_animation);
        });

        game3.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, MenuActivity3.class);
            startActivity(intent);
            overridePendingTransition(R.anim.zoom_out,R.anim.static_animation);
        });
    }

    @Override
    public void onBackPressed() {}
}