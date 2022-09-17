package com.example.summer_project;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class EnemySpaceship2 {
    Context context;
    Bitmap enemySpaceship2;
    int ex, ey;
    int enemyVelocity;
    Random random;

    public EnemySpaceship2(Context context) {
        this.context = context;
        enemySpaceship2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.submarine);
        random = new Random();
        ex = 400 + random.nextInt(600);
        ey = 0;
        enemyVelocity = 14 + random.nextInt(10);
    }

    public Bitmap getEnemySpaceship2(){
        return enemySpaceship2;
    }

    int getEnemySpaceshipWidth2(){
        return enemySpaceship2.getWidth();
    }

    int getEnemySpaceshipHeight2(){
        return enemySpaceship2.getHeight();
    }
}
//create the enemySpaceship for level medium
