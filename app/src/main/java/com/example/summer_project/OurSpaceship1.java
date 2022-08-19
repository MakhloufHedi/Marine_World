package com.example.summer_project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class OurSpaceship1 {
    Context context;
    Bitmap ourSpaceship1;
    int ox, oy;
    Random random;

    public OurSpaceship1(Context context) {
        this.context = context;
        ourSpaceship1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.rocket1);

        random = new Random();
        ox = random.nextInt(SpaceShooter1.screenWidth);
        oy = SpaceShooter1.screenHeight - ourSpaceship1.getHeight();
    }

    public Bitmap getOurSpaceship1(){
        return ourSpaceship1;
    }

    int getOurSpaceshipWidth1(){
        return ourSpaceship1.getWidth();
    }
}
