package com.example.summer_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class QuizActivity extends AppCompatActivity {

    private TextView questions;
    private TextView question;
    private AppCompatButton option1, option2, option3, option4;
    private AppCompatButton nextBtn;
    private Timer quizTimer;
    private int totalTimeInMins ;
    private int seconds = 0;
    private   ArrayList<QuestionsList> questionsLists = new ArrayList<>();
    private int currentQuestionPosition = 0;
    private String selectedOptionByUser = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        final ImageView backBtn = findViewById(R.id.backBtn);
        final TextView timer = findViewById(R.id.timer);
        final TextView levelName = findViewById(R.id.levelName);
        final String Minutes = getIntent().getStringExtra("Selected Level");
// initialisation de clock pour chaque level
        if(Minutes.equals("level1"))
        {
            timer.setText("03:00");
        } else if (Minutes.equals("level2"))
        {
            timer.setText("02:00");
        } else {
            timer.setText("01:00");
        }


        questions = findViewById(R.id.questions);
        question = findViewById(R.id.question);


        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);


        nextBtn = findViewById(R.id.nextBtn);

        final String getSelectedLevel = getIntent().getStringExtra("Selected Level");
        levelName.setText(getSelectedLevel);


        questionsLists = QuestionsBank.getQuestions(getSelectedLevel);
        startTimer(timer);
        questions.setText( (currentQuestionPosition + 1 ) + "/" + questionsLists.size());
        question.setText(questionsLists.get(0).getQuestion());


        option1.setText(questionsLists.get(0).getOption1());
        option2.setText(questionsLists.get(0).getOption2());
        option3.setText(questionsLists.get(0).getOption3());
        option4.setText(questionsLists.get(0).getOption4());

// la selection de reponse puis verification si elle est correct ou non
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selectedOptionByUser.isEmpty()) {
                    selectedOptionByUser = option1.getText().toString();
                    option1.setBackgroundResource(R.drawable.round_back_red10);
                    option1.setTextColor(getResources().getColor(R.color.white));
                    revealAnswer();
                    questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }

            }
        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()) {
                    selectedOptionByUser = option2.getText().toString();
                    option2.setBackgroundResource(R.drawable.round_back_red10);
                    option2.setTextColor(getResources().getColor(R.color.white));
                    revealAnswer();
                    questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }

            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()) {
                    selectedOptionByUser = option3.getText().toString();
                    option3.setBackgroundResource(R.drawable.round_back_red10);
                    option3.setTextColor(getResources().getColor(R.color.white));
                    revealAnswer();
                    questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }

            }
        });

        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()) {
                    selectedOptionByUser = option4.getText().toString();
                    option4.setBackgroundResource(R.drawable.round_back_red10);
                    option4.setTextColor(getResources().getColor(R.color.white));
                    revealAnswer();
                    questionsLists.get(currentQuestionPosition).setUserSelectedAnswer(selectedOptionByUser);
                }

            }
        });
// bouton pour aller a la question suivante
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOptionByUser.isEmpty()){
                    Toast.makeText(QuizActivity.this, "Please select an option", Toast.LENGTH_SHORT).show();
                }
                else {
                    changeNextQuestion();
                }
            }

        });
// bouton pour arriver en arri??re
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizTimer.purge();
                quizTimer.cancel();

                startActivity(new Intent(QuizActivity.this,MenuActivity3.class));
                finish();
            }
        });


    }
 // fonction qui controle le passage a la question suivante
    private void changeNextQuestion(){
        currentQuestionPosition++;
        if ((currentQuestionPosition+1) == questionsLists.size()){
            nextBtn.setText("Submit Quiz");
        }
        if (currentQuestionPosition < questionsLists.size()){
            selectedOptionByUser ="";

            option1.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option1.setTextColor(Color.parseColor(("#1F6BB8")));

            option2.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option2.setTextColor(Color.parseColor(("#1F6BB8")));

            option3.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option3.setTextColor(Color.parseColor(("#1F6BB8")));

            option4.setBackgroundResource(R.drawable.round_back_white_stroke2_10);
            option4.setTextColor(Color.parseColor(("#1F6BB8")));

            questions.setText( (currentQuestionPosition + 1 ) + "/" + questionsLists.size());
            question.setText(questionsLists.get(currentQuestionPosition).getQuestion());
            option1.setText(questionsLists.get(currentQuestionPosition).getOption1());
            option2.setText(questionsLists.get(currentQuestionPosition).getOption2());
            option3.setText(questionsLists.get(currentQuestionPosition).getOption3());
            option4.setText(questionsLists.get(currentQuestionPosition).getOption4());
        }
        else {
            Intent intent = new Intent(QuizActivity.this, QuizResults.class);
            intent.putExtra("correct",getCorrectAnswer());
            intent.putExtra("incorrect",getInCorrectAnswer());
            startActivity(intent);
        }
    }
// fonction qui met le clock en cours
    private void startTimer(TextView timerTextView)
    {
        final String getCatLevel = getIntent().getStringExtra("Cat Level");
        if (getCatLevel.equals("level1")){
            // clock pour level 1
            quizTimer = new Timer();
            totalTimeInMins =3;
            quizTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (seconds == 0 && totalTimeInMins != 0)
                    {
                        totalTimeInMins--;
                        seconds = 59;

                    }
                    else if (seconds == 0 && totalTimeInMins == 0)
                    {
                        quizTimer.purge();
                        quizTimer.cancel();
                        Intent intent = new Intent(QuizActivity.this,QuizResults2.class);
                        intent.putExtra("correct",getCorrectAnswer());
                        intent.putExtra("incorrect",getInCorrectAnswer());
                        startActivity(intent);
                    }
                    else {
                        seconds--;
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {


                            String finalMinutes = String.valueOf(totalTimeInMins);
                            String finalSeconds = String.valueOf(seconds);

                            if (finalMinutes.length() == 1)
                            {
                                finalMinutes = "0" + finalMinutes;
                            }
                            if (finalSeconds.length() == 1)
                            {
                                finalSeconds = "0"+finalSeconds;
                            }
                            timerTextView.setText(finalMinutes + ":" + finalSeconds);
                        }
                    });
                }
            }, 1000,500);}

        else if (getCatLevel.equals("level2")) {
            // clock pour level 2

            quizTimer = new Timer();
            totalTimeInMins =2;
            quizTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {

                    if (seconds == 0 && totalTimeInMins != 0)
                    {
                        totalTimeInMins--;
                        seconds = 59;

                    }
                    else if (seconds == 0 && totalTimeInMins == 0)
                    {
                        quizTimer.purge();
                        quizTimer.cancel();
                        Intent intent = new Intent(QuizActivity.this,QuizResults2.class);
                        intent.putExtra("correct",getCorrectAnswer());
                        intent.putExtra("incorrect",getInCorrectAnswer());
                        startActivity(intent);
                    }
                    else {
                        seconds--;
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            String finalMinutes = String.valueOf(totalTimeInMins);
                            String finalSeconds = String.valueOf(seconds);

                            if (finalMinutes.length() == 1)
                            {
                                finalMinutes = "0" + finalMinutes;
                            }
                            if (finalSeconds.length() == 1)
                            {
                                finalSeconds = "0"+finalSeconds;
                            }
                            timerTextView.setText(finalMinutes +":"+finalSeconds);
                        }
                    });
                }
            }, 1000,500);
        }
        else {
            // clock pour level 3
            quizTimer = new Timer();
            totalTimeInMins =1;
            quizTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (seconds == 0 && totalTimeInMins != 0)
                    {
                        totalTimeInMins--;
                        seconds = 59;

                    }
                    else if (seconds == 0 && totalTimeInMins == 0)
                    {
                        quizTimer.purge();
                        quizTimer.cancel();
                        Intent intent = new Intent(QuizActivity.this,QuizResults2.class);
                        intent.putExtra("correct",getCorrectAnswer());
                        intent.putExtra("incorrect",getInCorrectAnswer());
                        startActivity(intent);
                    }
                    else {
                        seconds--;
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            String finalMinutes = String.valueOf(totalTimeInMins);
                            String finalSeconds = String.valueOf(seconds);

                            if (finalMinutes.length() == 1)
                            {
                                finalMinutes = "0" + finalMinutes;
                            }
                            if (finalSeconds.length() == 1)
                            {
                                finalSeconds = "0"+finalSeconds;
                            }
                            timerTextView.setText(finalMinutes +":"+finalSeconds);
                        }
                    });
                }
            }, 1000,600);}

    }
// fonction qui calcul nombre des questions correctes
    private int getCorrectAnswer()
    {
        int correctAnswers = 0;
        for(int i=0; i<questionsLists.size();i++){
            final String getUserSelectedAnswer = questionsLists.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsLists.get(i).getUserSelectedAnswer();
            if (getUserSelectedAnswer.equals(questionsLists.get(i).getAnswer()))
            {
                correctAnswers++;
            }
        }
        return correctAnswers;
    }

// fonction qui calcul nombre des questions incorrectes
    private int getInCorrectAnswer()
    {
        int IncorrectAnswers = 0;
        for(int i=0; i<questionsLists.size();i++){
            final String getUserSelectedAnswer = questionsLists.get(i).getUserSelectedAnswer();
            final String getAnswer = questionsLists.get(i).getUserSelectedAnswer();
            if (!getUserSelectedAnswer.equals(questionsLists.get(i).getAnswer()))
            {
                IncorrectAnswers++;
            }
        }
        return IncorrectAnswers;
    }
     // fonction qui controle de transition a la page derniere si le temps est termin??
    @Override
    public void onBackPressed(){
        quizTimer.purge();
        quizTimer.cancel();

        startActivity(new Intent(QuizActivity.this,MainActivity.class));
        finish();
    }
    // la fonction qui test l'option selectionn?? s'il est correct ou non
    private void revealAnswer(){

        final String getAnswer = questionsLists.get(currentQuestionPosition).getAnswer();

        if (option1.getText().toString().equals(getAnswer)) {
            option1.setBackgroundResource(R.drawable.round_back_green10);
            option1.setTextColor(getResources().getColor(android.R.color.white));

        } else if (option2.getText().toString().equals(getAnswer)) {
            option2.setBackgroundResource(R.drawable.round_back_green10);
            option2.setTextColor(getResources().getColor(android.R.color.white));

        }else if (option3.getText().toString().equals(getAnswer)) {
            option3.setBackgroundResource(R.drawable.round_back_green10);
            option3.setTextColor(getResources().getColor(android.R.color.white));

        }else if (option4.getText().toString().equals(getAnswer)) {
            option4.setBackgroundResource(R.drawable.round_back_green10);
            option4.setTextColor(getResources().getColor(android.R.color.white));
        }
    }
}
