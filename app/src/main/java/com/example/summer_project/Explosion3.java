package com.example.summer_project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Explosion3 {
    Bitmap explosion3[] = new Bitmap[9];
    int explosionFrame3;
    int eX, eY;

    public Explosion3(Context context, int eX, int eY) {
        explosion3[0] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.explosion0);
        explosion3[1] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.explosion1);
        explosion3[2] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.explosion2);
        explosion3[3] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.explosion3);
        explosion3[4] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.explosion4);
        explosion3[5] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.explosion5);
        explosion3[6] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.explosion6);
        explosion3[7] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.explosion7);
        explosion3[8] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.explosion8);
        explosionFrame3 = 0;
        this.eX = eX;
        this.eY = eY;
    }

    public Bitmap getExplosion3(int explosionFrame3){
        return explosion3[explosionFrame3];
    }
}