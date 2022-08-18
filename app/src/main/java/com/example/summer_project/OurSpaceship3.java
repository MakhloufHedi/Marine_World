package com.example.summer_project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class OurSpaceship3 {
    Context context;
    Bitmap ourSpaceship3;
    int ox, oy;
    Random random;

    public OurSpaceship3(Context context) {
        this.context = context;
        ourSpaceship3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.rocket1);
        random = new Random();
        ox = random.nextInt(SpaceShooter3.screenWidth);
        oy = SpaceShooter3.screenHeight - ourSpaceship3.getHeight();
    }

    public Bitmap getOurSpaceship3(){
        return ourSpaceship3;
    }

    int getOurSpaceshipWidth3(){
        return ourSpaceship3.getWidth();
    }
}
