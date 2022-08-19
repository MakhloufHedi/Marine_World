package com.example.summer_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StartUp1 extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up1);

    }

    public void startGame(View view) {
        startActivity(new Intent(StartUp1.this, StageActivity1.class));
        finish();
    }
}