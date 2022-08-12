package com.example.summer_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class QuizResults extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        final AppCompatButton startNewBtn = findViewById(R.id.startNewQuizBtn);
        final TextView correctAnswers = findViewById(R.id.correctAnswers);
        final TextView incorrectAnswers = findViewById(R.id.incorrectAnswers);

        final int getCorrectAnswers = getIntent().getIntExtra("correct",0);
        final int getIncorrectAnswers = getIntent().getIntExtra("incorrect",0);
        correctAnswers.setText("Correct Answer(s) : "+String.valueOf(getCorrectAnswers));
        incorrectAnswers.setText("Wrong Answer(s) : "+String.valueOf(getIncorrectAnswers));


        startNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuizResults.this,MenuActivity3.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(QuizResults.this,MenuActivity3.class));
    }
}