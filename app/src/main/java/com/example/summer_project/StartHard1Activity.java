//made by hedi : startscreen before the game that shows you how much score each fish gives 
package com.example.summer_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class StartHard1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_hard1);

        Button start_button_hard = findViewById(R.id.start_button_hard);

        start_button_hard.setOnClickListener(view -> {
            Intent intent = new Intent(StartHard1Activity.this ,GameHard1Activity.class);
            startActivity(intent);
        });
    }

}
