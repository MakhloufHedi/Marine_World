package com.example.summer_project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class EnemySpaceship1 {
    Context context;
    Bitmap enemySpaceship1;
    int ex, ey;
    int enemyVelocity;
    Random random;

    public EnemySpaceship1(Context context) {
        this.context = context;
        enemySpaceship1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.submarine);
        random = new Random();
        ex = 200 + random.nextInt(400);
        ey = 0;
        enemyVelocity = 14 + random.nextInt(10);
    }

    public Bitmap getEnemySpaceship1(){
        return enemySpaceship1;
    }

    int getEnemySpaceshipWidth1(){
        return enemySpaceship1.getWidth();
    }

    int getEnemySpaceshipHeight1(){
        return enemySpaceship1.getHeight();
    }
}
