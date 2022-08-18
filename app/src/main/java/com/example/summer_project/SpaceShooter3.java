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

public class SpaceShooter3 extends View {
    Context context;
    Bitmap background, lifeImage;
    Handler handler;
    long UPDATE_MILLIS = 30;
    static int screenWidth, screenHeight;
    int points = 0;
    int life = 2;
    Paint scorePaint;
    int TEXT_SIZE = 80;
    boolean paused = false;
    OurSpaceship3 ourSpaceship3;
    EnemySpaceship3 enemySpaceship3;
    Random random;
    ArrayList<Shot3> enemyShots3, ourShots3;
    Explosion3 explosion3;
    ArrayList<Explosion3> explosions3;
    boolean enemyShotAction = false;
    MediaPlayer bomb3,tir3;
    final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };


    public SpaceShooter3(Context context) {
        super(context);
        this.context = context;
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        random = new Random();
        enemyShots3 = new ArrayList<>();
        ourShots3 = new ArrayList<>();
        explosions3 = new ArrayList<>();
        ourSpaceship3 = new OurSpaceship3(context);
        enemySpaceship3 = new EnemySpaceship3(context);
        handler = new Handler();
        background = BitmapFactory.decodeResource(context.getResources(), R.drawable.game);
        lifeImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.life);
        scorePaint = new Paint();
        scorePaint.setColor(Color.RED);
        scorePaint.setTextSize(TEXT_SIZE);
        scorePaint.setTextAlign(Paint.Align.LEFT);
        tir3=MediaPlayer.create(context,R.raw.tir);
        bomb3=MediaPlayer.create(context,R.raw.bomb);
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
        enemySpaceship3.ex += enemySpaceship3.enemyVelocity;
        // If enemySpaceship collides with right wall, reverse enemyVelocity
        if(enemySpaceship3.ex + enemySpaceship3.getEnemySpaceshipWidth3() >= screenWidth){
            enemySpaceship3.enemyVelocity *= -1;
        }
        // If enemySpaceship collides with left wall, again reverse enemyVelocity
        if(enemySpaceship3.ex <=0){
            enemySpaceship3.enemyVelocity *= -1;
        }
        // Till enemyShotAction is false, enemy should fire shots from random travelled distance
        if(enemyShotAction == false){
            if(enemySpaceship3.ex >= 200 + random.nextInt(400)){
                Shot3 enemyShot = new Shot3(context, enemySpaceship3.ex + enemySpaceship3.getEnemySpaceshipWidth3() / 2, enemySpaceship3.ey );
                enemyShots3.add(enemyShot);
                // We're making enemyShotAction to true so that enemy can take a short at a time
                enemyShotAction = true;
            }
            if(enemySpaceship3.ex >= 400 + random.nextInt(800)){
                Shot3 enemyShot = new Shot3(context, enemySpaceship3.ex + enemySpaceship3.getEnemySpaceshipWidth3() / 2, enemySpaceship3.ey );
                enemyShots3.add(enemyShot);
                // We're making enemyShotAction to true so that enemy can take a short at a time
                enemyShotAction = true;
            }
            else{
                Shot3 enemyShot = new Shot3(context, enemySpaceship3.ex + enemySpaceship3.getEnemySpaceshipWidth3() / 2, enemySpaceship3.ey );
                enemyShots3.add(enemyShot);
                // We're making enemyShotAction to true so that enemy can take a short at a time
                enemyShotAction = true;
            }
        }
        // Draw the enemy Spaceship
        canvas.drawBitmap(enemySpaceship3.getEnemySpaceship3(), enemySpaceship3.ex, enemySpaceship3.ey, null);
        // Draw our spaceship between the left and right edge of the screen
        if(ourSpaceship3.ox > screenWidth - ourSpaceship3.getOurSpaceshipWidth3()){
            ourSpaceship3.ox = screenWidth - ourSpaceship3.getOurSpaceshipWidth3();
        }else if(ourSpaceship3.ox < 0){
            ourSpaceship3.ox = 0;
        }
        // Draw our Spaceship
        canvas.drawBitmap(ourSpaceship3.getOurSpaceship3(), ourSpaceship3.ox, ourSpaceship3.oy, null);
        // Draw the enemy shot downwards our spaceship and if it's being hit, decrement life, remove
        // the shot object from enemyShots ArrayList and show an explosion.
        // Else if, it goes away through the bottom edge of the screen also remove
        // the shot object from enemyShots.
        // When there is no enemyShots no the screen, change enemyShotAction to false, so that enemy
        // can shot.
        for(int i=0; i < enemyShots3.size(); i++){
            enemyShots3.get(i).shy += 15;
            canvas.drawBitmap(enemyShots3.get(i).getShot3(), enemyShots3.get(i).shx, enemyShots3.get(i).shy, null);
            if((enemyShots3.get(i).shx >= ourSpaceship3.ox)
                    && enemyShots3.get(i).shx <= ourSpaceship3.ox + ourSpaceship3.getOurSpaceshipWidth3()
                    && enemyShots3.get(i).shy >= ourSpaceship3.oy
                    && enemyShots3.get(i).shy <= screenHeight){
                life--;
                if(bomb3!=null)
                    bomb3.start();
                enemyShots3.remove(i);
                explosion3 = new Explosion3(context, ourSpaceship3.ox, ourSpaceship3.oy);
                explosions3.add(explosion3);
            }else if(enemyShots3.get(i).shy >= screenHeight){
                enemyShots3.remove(i);
            }
            if(enemyShots3.size() < 1){
                enemyShotAction = false;
            }
        }
        // Draw our spaceship shots towards the enemy. If there is a collision between our shot and enemy
        // spaceship, increment points, remove the shot from ourShots and create a new Explosion object.
        // Else if, our shot goes away through the top edge of the screen also remove
        // the shot object from enemyShots ArrayList.
        for(int i=0; i < ourShots3.size(); i++){
            ourShots3.get(i).shy -= 15;
            canvas.drawBitmap(ourShots3.get(i).getShot3(), ourShots3.get(i).shx, ourShots3.get(i).shy, null);
            if((ourShots3.get(i).shx >= enemySpaceship3.ex)
                    && ourShots3.get(i).shx <= enemySpaceship3.ex + enemySpaceship3.getEnemySpaceshipWidth3()
                    && ourShots3.get(i).shy <= enemySpaceship3.getEnemySpaceshipWidth3()
                    && ourShots3.get(i).shy >= enemySpaceship3.ey){
                points++;
                if(tir3!=null)
                    tir3.start();
                ourShots3.remove(i);
                explosion3 = new Explosion3(context, enemySpaceship3.ex, enemySpaceship3.ey);
                explosions3.add(explosion3);
            }else if(ourShots3.get(i).shy <=0){
                ourShots3.remove(i);
            }
        }
        // Do the explosion
        for(int i=0; i < explosions3.size(); i++){
            canvas.drawBitmap(explosions3.get(i).getExplosion3(explosions3.get(i).explosionFrame3), explosions3.get(i).eX, explosions3.get(i).eY, null);
            explosions3.get(i).explosionFrame3++;
            if(explosions3.get(i).explosionFrame3 > 8){
                explosions3.remove(i);
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
            if(ourShots3.size() < 1){
                Shot3 ourShot = new Shot3(context, ourSpaceship3.ox + ourSpaceship3.getOurSpaceshipWidth3() / 2, ourSpaceship3.oy);
                ourShots3.add(ourShot);
            }
        }
        // When event.getAction() is MotionEvent.ACTION_DOWN, control ourSpaceship
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            ourSpaceship3.ox = touchX;
        }
        // When event.getAction() is MotionEvent.ACTION_MOVE, control ourSpaceship
        // along with the touch.
        if(event.getAction() == MotionEvent.ACTION_MOVE){
            ourSpaceship3.ox = touchX;
        }
        // Returning true in an onTouchEvent() tells Android system that you already handled
        // the touch event and no further handling is required.
        return true;
    }
}
