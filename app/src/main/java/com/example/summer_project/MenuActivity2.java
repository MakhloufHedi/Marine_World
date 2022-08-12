package com.example.summer_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

public class MenuActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);

        ImageView back_button2 = findViewById(R.id.back_button2);

        back_button2.setOnClickListener(view -> {
            Intent intent = new Intent(MenuActivity2.this ,MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.static_animation,R.anim.zoom_in);
        });
    }

    @Override
    public void onBackPressed() {}
}