package com.example.summer_project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class OurSpaceship2 {
    Context context;
    Bitmap ourSpaceship2;
    int ox, oy;
    Random random;

    public OurSpaceship2(Context context) {
        this.context = context;
        ourSpaceship2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.rocket1);
        random = new Random();
        ox = random.nextInt(SpaceShooter2.screenWidth);
        oy = SpaceShooter2.screenHeight - ourSpaceship2.getHeight();
    }

    public Bitmap getOurSpaceship2(){
        return ourSpaceship2;
    }

    int getOurSpaceshipWidth2(){
        return ourSpaceship2.getWidth();
    }
}