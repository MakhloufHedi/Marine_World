package com.example.summer_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

public class MenuActivity3 extends AppCompatActivity {

    public String selectedLevel ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu3);

        final LinearLayout easy = findViewById(R.id.easyLayout);
        final LinearLayout medium = findViewById(R.id.mediumLayout);
        final LinearLayout hard = findViewById(R.id.hardLayout);
        final Button startBtn = findViewById(R.id.startQuizBtn);
// Ajuster le background lors d"un click sur level easy
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedLevel = "level1";
                easy.setBackgroundResource(R.drawable.round_back_white_stroke);
                medium.setBackgroundResource(R.drawable.round_back_wrrite10);
                hard.setBackgroundResource(R.drawable.round_back_wrrite10);
            }
        });
// Ajuster le background lors d"un click sur level medium
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedLevel = "level2";
                medium.setBackgroundResource(R.drawable.round_back_white_stroke);
                easy.setBackgroundResource(R.drawable.round_back_wrrite10);
                hard.setBackgroundResource(R.drawable.round_back_wrrite10);

            }
        });
// Ajuster le background lors d"un click sur level hard
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedLevel="level3";
                hard.setBackgroundResource(R.drawable.round_back_white_stroke);
                easy.setBackgroundResource(R.drawable.round_back_wrrite10);
                medium.setBackgroundResource(R.drawable.round_back_wrrite10);

            }
        });
// aller a la page suivante
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedLevel.isEmpty())
                {
                    Toast.makeText(MenuActivity3.this, "Please select the level", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(MenuActivity3.this, QuizActivity.class);
                    intent.putExtra("Selected Level", selectedLevel);
                    intent.putExtra("Cat Level",selectedLevel);
                    intent.putExtra("Mins",selectedLevel);
                    startActivity(intent);
                }

            }
        });
    }
}
