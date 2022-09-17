//made by hedi : startscreen before the game that shows you how much score each fish gives 
package com.example.summer_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class StartMedium1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_medium1);

        Button start_button_med = findViewById(R.id.start_button_med);

        start_button_med.setOnClickListener(view -> {
            Intent intent = new Intent(StartMedium1Activity.this ,GameMedium1Activity.class);
            startActivity(intent);
        });
    }

}
