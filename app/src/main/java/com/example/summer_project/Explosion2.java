package com.example.summer_project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Explosion2 {
    Bitmap explosion2[] = new Bitmap[9];
    int explosionFrame2;
    int eX, eY;

    public Explosion2(Context context, int eX, int eY) {
        explosion2[0] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.explosion0);
        explosion2[1] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.explosion1);
        explosion2[2] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.explosion2);
        explosion2[3] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.explosion3);
        explosion2[4] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.explosion4);
        explosion2[5] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.explosion5);
        explosion2[6] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.explosion6);
        explosion2[7] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.explosion7);
        explosion2[8] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.explosion8);
        explosionFrame2 = 0;
        this.eX = eX;
        this.eY = eY;
    }

    public Bitmap getExplosion2(int explosionFrame2){
        return explosion2[explosionFrame2];
    }
}
//create the eight image  explosion and store in a bitmap for level medium
