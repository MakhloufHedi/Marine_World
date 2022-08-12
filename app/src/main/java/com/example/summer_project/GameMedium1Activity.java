package com.example.summer_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class GameMedium1Activity extends AppCompatActivity {

    //elements
    private TextView score_label , start_label ;
    private ImageView main_fish , small_fish , big_fish , shark , heart1 , heart2 , heart3;

    private int screenWidth;
    private int frameHeight;
    private int mainSize;

    //positions
    private float mainY;
    private float smallX,smallY;
    private float bigX,bigY;
    private float sharkX,sharkY;

    //score
    private int score;

    //timer
    private Timer timer = new Timer();
    private Handler handler = new Handler();

    //status
    private boolean action_flg = false ;
    private boolean start_flag = false ;

    //sound player
    private SoundPlayer soundPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_medium1);

        soundPlayer = new SoundPlayer(this);

        score_label = findViewById(R.id.score_label);
        start_label = findViewById(R.id.start_label);
        main_fish = findViewById(R.id.main_fish);
        small_fish = findViewById(R.id.small_fish);
        big_fish = findViewById(R.id.big_fish);
        shark = findViewById(R.id.shark);

        heart1 = findViewById(R.id.heart1);
        heart2 = findViewById(R.id.heart2);
        heart3 = findViewById(R.id.heart3);


//screen size
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        screenWidth = size.x;
        //size
        int screenHeight = size.y;

//initial positions
        big_fish.setX(-90.0f);
        big_fish.setY(-90.0f);
        small_fish.setX(-90.0f);
        small_fish.setY(-90.0f);
        shark.setX(-100.0f);
        shark.setY(-100.0f);

        score_label.setText("Score : " + score);
    }

    public void changePos() {

        hitCheck();

//small fish generation
        smallX -= 12;
        if (smallX < 0) {
            smallX = screenWidth + 20;
            smallY = (float) Math.floor(Math.random() * (frameHeight - small_fish.getHeight()));
        }
        small_fish.setX(smallX);
        small_fish.setY(smallY);

//shark generation
        sharkX -= 16;
        if (sharkX < 0) {
            sharkX = screenWidth + 10;
            sharkY = (float) Math.floor(Math.random() * (frameHeight - shark.getHeight()));
        }
        shark.setX(sharkX);
        shark.setY(sharkY);

//big fish generation
        bigX -= 20;
        if (bigX < 0) {
            bigX = screenWidth + 5000;
            bigY = (float) Math.floor(Math.random() * (frameHeight - big_fish.getHeight()));
        }
        big_fish.setX(bigX);
        big_fish.setY(bigY);

        if (action_flg){
            //touch
            mainY -= 20;
        }else {
            //release
            mainY += 20;
        }

        if (mainY < 0) mainY = 0;
        if (mainY > frameHeight - mainSize) mainY = frameHeight - mainSize;

        main_fish.setY(mainY);

        score_label.setText("Score : " + score);

    }

    public void hitCheck() {

//small fish hit
        float smallCenterX = smallX + small_fish.getWidth() / 2.0f;
        float smallCenterY = smallY + small_fish.getHeight() /2.0f;

        if (0 <= smallCenterX && smallCenterX <= mainSize &&
                mainY <= smallCenterY && smallCenterY <= mainY + mainSize) {
            smallX = -100.0f;
            score += 10;
            soundPlayer.playScoreSound();
        }

//big fish hit
        float bigCenterX = bigX + big_fish.getWidth() / 2.0f;
        float bigCenterY = bigY + big_fish.getHeight() /2.0f;

        if (0 <= bigCenterX && bigCenterX <= mainSize &&
                mainY <= bigCenterY && bigCenterY <= mainY + mainSize) {
            bigX = -100.0f;
            score += 30;
            soundPlayer.playScoreSound();
        }

//shark hit
//        float sharkCenterX = sharkX + shark.getWidth() / 2.0f;
        float sharkCenterY = sharkY + shark.getHeight() /2.0f;

//        int s ;
//        s = 0;
//
//        if (0 <= sharkX && sharkX <= mainSize &&
//                mainY <= sharkCenterY && sharkCenterY <= mainY + mainSize ) {
//            heart1.setVisibility(View.GONE);
//            s = s+1;
//        }
//        if (0 <= sharkX && sharkX <= mainSize &&
//                mainY <= sharkCenterY && sharkCenterY <= mainY + mainSize && s == 1) {
//            heart2.setVisibility(View.GONE);
//            s = s+1 ;
//        }
        if (0 <= sharkX && sharkX <= mainSize &&
                mainY <= sharkCenterY && sharkCenterY <= mainY + mainSize){
            soundPlayer.playDamageSound();
            soundPlayer.playGameOverSound();
            //game over
            heart3.setVisibility(View.GONE);
            if (timer != null){
                timer.cancel();
                timer = null;
            }

            //show result
            Intent intent = new Intent(getApplicationContext(), EndMedium1Activity.class);
            intent.putExtra("SCORE",score);
            startActivity(intent);
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (!start_flag){
            start_flag = true;

            //frameHeight
            FrameLayout frame = findViewById(R.id.frame);
            frameHeight = frame.getHeight();

            //main fish
            mainY = main_fish.getY();
            mainSize = main_fish.getHeight();

            start_label.setVisibility(View.GONE);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });

                }
            },0,20);

        } else {
            if (event.getAction() == MotionEvent.ACTION_DOWN){
                action_flg = true ;

            } else if (event.getAction() == MotionEvent.ACTION_UP){
                action_flg = false ;
            }
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void onBackPressed() {}


}