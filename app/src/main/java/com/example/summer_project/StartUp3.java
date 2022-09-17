package com.example.summer_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StartUp3 extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up3);

    }
//start activity of the hard level , it shows the meun of three level
    public void startGame(View view) {
        startActivity(new Intent(StartUp3.this, MainActivity3.class));
        finish();
    }
}
