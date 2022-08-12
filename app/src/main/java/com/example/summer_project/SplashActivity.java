package com.example.summer_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;


@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    LottieAnimationView splash_background , splash_fish_animation ;
    TextView app_title;
    ImageView app_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splash_background = findViewById(R.id.splash_background);
        splash_fish_animation = findViewById(R.id.splash_fish_animation);
        app_logo = findViewById(R.id.splash_logo);

        Thread thread = new Thread()
        {
            @Override
            public void run() {
                try {
                    sleep(5000);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    Intent mainIntent = new Intent(SplashActivity.this ,MainActivity.class);
                    startActivity(mainIntent);
                    overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
                }
            }
        };
        thread.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}