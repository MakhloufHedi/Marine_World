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
//start activity of the easy level , it shows the meun of three level.
    public void startGame(View view) {
        startActivity(new Intent(StartUp1.this, MainActivity1.class));
        finish();
    }
}
