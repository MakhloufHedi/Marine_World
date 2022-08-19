package com.example.summer_project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Explosion1 {
    Bitmap explosion1[] = new Bitmap[9];
    int explosionFrame1;
    int eX, eY;

    public Explosion1(Context context, int eX, int eY) {
        explosion1[0] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.explosion0);
        explosion1[1] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.explosion1);
        explosion1[2] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.explosion2);
        explosion1[3] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.explosion3);
        explosion1[4] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.explosion4);
        explosion1[5] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.explosion5);
        explosion1[6] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.explosion6);
        explosion1[7] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.explosion7);
        explosion1[8] = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.explosion8);
        explosionFrame1 = 0;
        this.eX = eX;
        this.eY = eY;
    }

    public Bitmap getExplosion1(int explosionFrame1) {
        return explosion1[explosionFrame1];
    }
}
