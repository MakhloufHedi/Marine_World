package com.example.summer_project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class EnemySpaceship3 {
    Context context;
    Bitmap enemySpaceship3;
    int ex, ey;
    int enemyVelocity;
    Random random;

    public EnemySpaceship3(Context context) {
        this.context = context;
        enemySpaceship3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.submarine);
        random = new Random();
        ex = 500 + random.nextInt(700);
        ey = 0;
        enemyVelocity = 14 + random.nextInt(10);
    }

    public Bitmap getEnemySpaceship3(){
        return enemySpaceship3;
    }

    int getEnemySpaceshipWidth3(){
        return enemySpaceship3.getWidth();
    }

    int getEnemySpaceshipHeight3(){
        return enemySpaceship3.getHeight();
    }
}
