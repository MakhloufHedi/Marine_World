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

public class SpaceShooter2 extends View {
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
    OurSpaceship2 ourSpaceship2;
    EnemySpaceship2 enemySpaceship2;
    Random random;
    ArrayList<Shot2> enemyShots2, ourShots2;
    Explosion2 explosion2;
    ArrayList<Explosion2> explosions2;
    boolean enemyShotAction = false;
    MediaPlayer bomb2,tir2;
    final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };


    public SpaceShooter2(Context context) {
        super(context);
        this.context = context;
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        random = new Random();
        enemyShots2 = new ArrayList<>();
        ourShots2 = new ArrayList<>();
        explosions2 = new ArrayList<>();
        ourSpaceship2 = new OurSpaceship2(context);
        enemySpaceship2 = new EnemySpaceship2(context);
        handler = new Handler();
        background = BitmapFactory.decodeResource(context.getResources(), R.drawable.game);
        lifeImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.life);
        scorePaint = new Paint();
        scorePaint.setColor(Color.RED);
        scorePaint.setTextSize(TEXT_SIZE);
        scorePaint.setTextAlign(Paint.Align.LEFT);
        tir2=MediaPlayer.create(context,R.raw.tir);
        bomb2=MediaPlayer.create(context,R.raw.bomb);
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
            Intent intent = new Intent(context, GameOver2.class);
            intent.putExtra("points", points);
            context.startActivity(intent);
            ((Activity) context).finish();
        }
        // Move enemySpaceship
        enemySpaceship2.ex += enemySpaceship2.enemyVelocity;
        // If enemySpaceship collides with right wall, reverse enemyVelocity
        if(enemySpaceship2.ex + enemySpaceship2.getEnemySpaceshipWidth2() >= screenWidth){
            enemySpaceship2.enemyVelocity *= -1;
        }
        // If enemySpaceship collides with left wall, again reverse enemyVelocity
        if(enemySpaceship2.ex <=0){
            enemySpaceship2.enemyVelocity *= -1;
        }
        // Till enemyShotAction is false, enemy should fire shots from random travelled distance
        if(enemyShotAction == false){
            if(enemySpaceship2.ex >= 400 + random.nextInt(400)){
                Shot2 enemyShot = new Shot2(context, enemySpaceship2.ex + enemySpaceship2.getEnemySpaceshipWidth2() / 2, enemySpaceship2.ey );
                enemyShots2.add(enemyShot);
                // We're making enemyShotAction to true so that enemy can take a short at a time
                enemyShotAction = true;
            }
            if(enemySpaceship2.ex >= 800 + random.nextInt(800)){
                Shot2 enemyShot = new Shot2(context, enemySpaceship2.ex + enemySpaceship2.getEnemySpaceshipWidth2() / 2, enemySpaceship2.ey );
                enemyShots2.add(enemyShot);
                // We're making enemyShotAction to true so that enemy can take a short at a time
                enemyShotAction = true;
            }
            else{
                Shot2 enemyShot = new Shot2(context, enemySpaceship2.ex + enemySpaceship2.getEnemySpaceshipWidth2() / 2, enemySpaceship2.ey );
                enemyShots2.add(enemyShot);
                // We're making enemyShotAction to true so that enemy can take a short at a time
                enemyShotAction = true;
            }
        }
        // Draw the enemy Spaceship
        canvas.drawBitmap(enemySpaceship2.getEnemySpaceship2(), enemySpaceship2.ex, enemySpaceship2.ey, null);
        // Draw our spaceship between the left and right edge of the screen
        if(ourSpaceship2.ox > screenWidth - ourSpaceship2.getOurSpaceshipWidth2()){
            ourSpaceship2.ox = screenWidth - ourSpaceship2.getOurSpaceshipWidth2();
        }else if(ourSpaceship2.ox < 0){
            ourSpaceship2.ox = 0;
        }
        // Draw our Spaceship
        canvas.drawBitmap(ourSpaceship2.getOurSpaceship2(), ourSpaceship2.ox, ourSpaceship2.oy, null);
        // Draw the enemy shot downwards our spaceship and if it's being hit, decrement life, remove
        // the shot object from enemyShots ArrayList and show an explosion.
        // Else if, it goes away through the bottom edge of the screen also remove
        // the shot object from enemyShots.
        // When there is no enemyShots no the screen, change enemyShotAction to false, so that enemy
        // can shot.
        for(int i=0; i < enemyShots2.size(); i++){
            enemyShots2.get(i).shy += 15;
            canvas.drawBitmap(enemyShots2.get(i).getShot2(), enemyShots2.get(i).shx, enemyShots2.get(i).shy, null);
            if((enemyShots2.get(i).shx >= ourSpaceship2.ox)
                    && enemyShots2.get(i).shx <= ourSpaceship2.ox + ourSpaceship2.getOurSpaceshipWidth2()
                    && enemyShots2.get(i).shy >= ourSpaceship2.oy
                    && enemyShots2.get(i).shy <= screenHeight){
                life--;
                if(bomb2!=null)
                    bomb2.start();
                enemyShots2.remove(i);
                explosion2 = new Explosion2(context, ourSpaceship2.ox, ourSpaceship2.oy);
                explosions2.add(explosion2);
            }else if(enemyShots2.get(i).shy >= screenHeight){
                enemyShots2.remove(i);
            }
            if(enemyShots2.size() < 1){
                enemyShotAction = false;
            }
        }
        // Draw our spaceship shots towards the enemy. If there is a collision between our shot and enemy
        // spaceship, increment points, remove the shot from ourShots and create a new Explosion object.
        // Else if, our shot goes away through the top edge of the screen also remove
        // the shot object from enemyShots ArrayList.
        for(int i=0; i < ourShots2.size(); i++){
            ourShots2.get(i).shy -= 15;
            canvas.drawBitmap(ourShots2.get(i).getShot2(), ourShots2.get(i).shx, ourShots2.get(i).shy, null);
            if((ourShots2.get(i).shx >= enemySpaceship2.ex)
                    && ourShots2.get(i).shx <= enemySpaceship2.ex + enemySpaceship2.getEnemySpaceshipWidth2()
                    && ourShots2.get(i).shy <= enemySpaceship2.getEnemySpaceshipWidth2()
                    && ourShots2.get(i).shy >= enemySpaceship2.ey){
                points++;
                if(tir2!=null)
                    tir2.start();
                ourShots2.remove(i);
                explosion2 = new Explosion2(context, enemySpaceship2.ex, enemySpaceship2.ey);
                explosions2.add(explosion2);
            }else if(ourShots2.get(i).shy <=0){
                ourShots2.remove(i);
            }
        }
        // Do the explosion
        for(int i=0; i < explosions2.size(); i++){
            canvas.drawBitmap(explosions2.get(i).getExplosion2(explosions2.get(i).explosionFrame2), explosions2.get(i).eX, explosions2.get(i).eY, null);
            explosions2.get(i).explosionFrame2++;
            if(explosions2.get(i).explosionFrame2 > 8){
                explosions2.remove(i);
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
            if(ourShots2.size() < 1){
                Shot2 ourShot = new Shot2(context, ourSpaceship2.ox + ourSpaceship2.getOurSpaceshipWidth2() / 2, ourSpaceship2.oy);
                ourShots2.add(ourShot);
            }
        }
        // When event.getAction() is MotionEvent.ACTION_DOWN, control ourSpaceship
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            ourSpaceship2.ox = touchX;
        }
        // When event.getAction() is MotionEvent.ACTION_MOVE, control ourSpaceship
        // along with the touch.
        if(event.getAction() == MotionEvent.ACTION_MOVE){
            ourSpaceship2.ox = touchX;
        }
        // Returning true in an onTouchEvent() tells Android system that you already handled
        // the touch event and no further handling is required.
        return true;
    }
}
