package com.example.summer_project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;

public class Shot1 {
    Bitmap shot1;
    Context context;
    int shx, shy;
    MediaPlayer sh1;

    public Shot1(Context context, int shx, int shy) {
        this.context = context;
        shot1 = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.bullet);
        this.shx = shx;
        this.shy = shy;
        sh1=MediaPlayer.create(context,R.raw.rocket);
        sh1.start();
    }
    public Bitmap getShot1(){
        return shot1;
    }
    public int getShotWidth1() {
        return shot1.getWidth();
    }
    public int getShotHeight1() {
        return shot1.getHeight();
    }
}
//Create a Shot for level easy
