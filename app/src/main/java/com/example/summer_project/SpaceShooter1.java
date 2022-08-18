package com.example.summer_project;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class SpaceShooter1 extends View {
    Context context;
    Bitmap background, lifeImage;
    Handler handler;
    long UPDATE_MILLIS = 30;
    static int screenWidth, screenHeight;
    int points = 0;
    int life = 6;
    Paint scorePaint;
    int TEXT_SIZE = 80;
    boolean paused = false;
    OurSpaceship1 ourSpaceship1;
    EnemySpaceship1 enemySpaceship1;
    Random random;
    ArrayList<Shot1> enemyShots1, ourShots1;
    Explosion1 explosion1;
    ArrayList<Explosion1> explosions1;
    boolean enemyShotAction = false;
    MediaPlayer bomb1,tir1;
    final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };


    public SpaceShooter1(Context context) {
        super(context);
        this.context = context;
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        random = new Random();
        enemyShots1 = new ArrayList<>();
        ourShots1 = new ArrayList<>();
        explosions1 = new ArrayList<>();
        ourSpaceship1 = new OurSpaceship1(context);
        enemySpaceship1 = new EnemySpaceship1(context);
        handler = new Handler();
        background = BitmapFactory.decodeResource(context.getResources(), R.drawable.game);
        lifeImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.life);
        scorePaint = new Paint();
        scorePaint.setColor(Color.RED);
        scorePaint.setTextSize(TEXT_SIZE);
        scorePaint.setTextAlign(Paint.Align.LEFT);
        tir1=MediaPlayer.create(context,R.raw.tir);
        bomb1=MediaPlayer.create(context,R.raw.bomb);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Draw background, Points and life on Canvas
        canvas.drawBitmap(background, 0, 0, null);
        canvas.drawText("Pt: " + points, 0, TEXT_SIZE, scorePaint);
        for(int i=life; i>=1; i--){
            canvas.drawBitmap(lifeImage, screenWidth - lifeImage.getWidth() * i, 0, null);
        }
        // When life becomes 0, stop game and launch GameOver Activity with points
        if(life == 0){
            paused = true;
            handler = null;
            Intent intent = new Intent(context, GameOver1.class);
            intent.putExtra("points", points);
            context.startActivity(intent);
            ((Activity) context).finish();
        }
        // Move enemySpaceship
        enemySpaceship1.ex += enemySpaceship1.enemyVelocity;
        // If enemySpaceship collides with right wall, reverse enemyVelocity
        if(enemySpaceship1.ex + enemySpaceship1.getEnemySpaceshipWidth1() >= screenWidth){
            enemySpaceship1.enemyVelocity *= -1;
        }
        // If enemySpaceship collides with left wall, again reverse enemyVelocity
        if(enemySpaceship1.ex <=0){
            enemySpaceship1.enemyVelocity *= -1;
        }
        // Till enemyShotAction is false, enemy should fire shots from random travelled distance
        if(enemyShotAction == false){
            if(enemySpaceship1.ex >= 200 + random.nextInt(400)){
                Shot1 enemyShot = new Shot1(context, enemySpaceship1.ex + enemySpaceship1.getEnemySpaceshipWidth1() / 2, enemySpaceship1.ey );
                enemyShots1.add(enemyShot);
                // We're making enemyShotAction to true so that enemy can take a short at a time
                enemyShotAction = true;
            }
            if(enemySpaceship1.ex >= 400 + random.nextInt(800)){
                Shot1 enemyShot = new Shot1(context, enemySpaceship1.ex + enemySpaceship1.getEnemySpaceshipWidth1() / 2, enemySpaceship1.ey );
                enemyShots1.add(enemyShot);
                // We're making enemyShotAction to true so that enemy can take a short at a time
                enemyShotAction = true;
            }
            else{
                Shot1 enemyShot = new Shot1(context, enemySpaceship1.ex + enemySpaceship1.getEnemySpaceshipWidth1() / 2, enemySpaceship1.ey );
                enemyShots1.add(enemyShot);
                // We're making enemyShotAction to true so that enemy can take a short at a time
                enemyShotAction = true;
            }
        }
        // Draw the enemy Spaceship
        canvas.drawBitmap(enemySpaceship1.getEnemySpaceship1(), enemySpaceship1.ex, enemySpaceship1.ey, null);
        // Draw our spaceship between the left and right edge of the screen
        if(ourSpaceship1.ox > screenWidth - ourSpaceship1.getOurSpaceshipWidth1()){
            ourSpaceship1.ox = screenWidth - ourSpaceship1.getOurSpaceshipWidth1();
        }else if(ourSpaceship1.ox < 0){
            ourSpaceship1.ox = 0;
        }
        // Draw our Spaceship
        canvas.drawBitmap(ourSpaceship1.getOurSpaceship1(), ourSpaceship1.ox, ourSpaceship1.oy, null);
        // Draw the enemy shot downwards our spaceship and if it's being hit, decrement life, remove
        // the shot object from enemyShots ArrayList and show an explosion.
        // Else if, it goes away through the bottom edge of the screen also remove
        // the shot object from enemyShots.
        // When there is no enemyShots no the screen, change enemyShotAction to false, so that enemy
        // can shot.
        for(int i=0; i < enemyShots1.size(); i++){
            enemyShots1.get(i).shy += 15;
            canvas.drawBitmap(enemyShots1.get(i).getShot1(), enemyShots1.get(i).shx, enemyShots1.get(i).shy, null);
            if((enemyShots1.get(i).shx >= ourSpaceship1.ox)
                    && enemyShots1.get(i).shx <= ourSpaceship1.ox + ourSpaceship1.getOurSpaceshipWidth1()
                    && enemyShots1.get(i).shy >= ourSpaceship1.oy
                    && enemyShots1.get(i).shy <= screenHeight){
                life--;
                if(bomb1!=null)
                    bomb1.start();
                enemyShots1.remove(i);
                explosion1 = new Explosion1(context, ourSpaceship1.ox, ourSpaceship1.oy);
                explosions1.add(explosion1);
            }else if(enemyShots1.get(i).shy >= screenHeight){
                enemyShots1.remove(i);
            }
            if(enemyShots1.size() < 1){
                enemyShotAction = false;
            }
        }
        // Draw our spaceship shots towards the enemy. If there is a collision between our shot and enemy
        // spaceship, increment points, remove the shot from ourShots and create a new Explosion object.
        // Else if, our shot goes away through the top edge of the screen also remove
        // the shot object from enemyShots ArrayList.
        for(int i=0; i < ourShots1.size(); i++){
            ourShots1.get(i).shy -= 15;
            canvas.drawBitmap(ourShots1.get(i).getShot1(), ourShots1.get(i).shx, ourShots1.get(i).shy, null);
            if((ourShots1.get(i).shx >= enemySpaceship1.ex)
                    && ourShots1.get(i).shx <= enemySpaceship1.ex + enemySpaceship1.getEnemySpaceshipWidth1()
                    && ourShots1.get(i).shy <= enemySpaceship1.getEnemySpaceshipWidth1()
                    && ourShots1.get(i).shy >= enemySpaceship1.ey){
                points++;
                if(tir1!=null)
                    tir1.start();
                ourShots1.remove(i);
                explosion1 = new Explosion1(context, enemySpaceship1.ex, enemySpaceship1.ey);
                explosions1.add(explosion1);
            }else if(ourShots1.get(i).shy <=0){
                ourShots1.remove(i);
            }
        }
        // Do the explosion
        for(int i=0; i < explosions1.size(); i++){
            canvas.drawBitmap(explosions1.get(i).getExplosion1(explosions1.get(i).explosionFrame1), explosions1.get(i).eX, explosions1.get(i).eY, null);
            explosions1.get(i).explosionFrame1++;
            if(explosions1.get(i).explosionFrame1 > 8){
                explosions1.remove(i);
            }
        }
        // If not paused, we’ll call the postDelayed() method on handler object which will cause the
        // run method inside Runnable to be executed after 30 milliseconds, that is the value inside
        // UPDATE_MILLIS.
        if(!paused)
            handler.postDelayed(runnable, UPDATE_MILLIS);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int touchX = (int)event.getX();
        // When event.getAction() is MotionEvent.ACTION_UP, if ourShots arraylist size < 1,
        // create a new Shot.
        // This way we restrict ourselves of making just one shot at a time, on the screen.
        if(event.getAction() == MotionEvent.ACTION_UP){
            if(ourShots1.size() < 1){
                Shot1 ourShot = new Shot1(context, ourSpaceship1.ox + ourSpaceship1.getOurSpaceshipWidth1() / 2, ourSpaceship1.oy);
                ourShots1.add(ourShot);
            }
        }
        // When event.getAction() is MotionEvent.ACTION_DOWN, control ourSpaceship
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            ourSpaceship1.ox = touchX;
        }
        // When event.getAction() is MotionEvent.ACTION_MOVE, control ourSpaceship
        // along with the touch.
        if(event.getAction() == MotionEvent.ACTION_MOVE){
            ourSpaceship1.ox = touchX;
        }
        // Returning true in an onTouchEvent() tells Android system that you already handled
        // the touch event and no further handling is required.
        return true;
    }
}