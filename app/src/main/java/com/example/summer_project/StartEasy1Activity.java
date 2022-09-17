//made by hedi : startscreen before the game that shows you how much score each fish gives 
package com.example.summer_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class StartEasy1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_easy1);

        Button start_button_easy = findViewById(R.id.start_button_easy);

        start_button_easy.setOnClickListener(view -> {
            Intent intent = new Intent(StartEasy1Activity.this ,GameEasy1Activity.class);
            startActivity(intent);
        });
    }

}
