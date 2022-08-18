package com.example.summer_project;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;

public class Shot3 {
    Bitmap shot3;
    Context context;
    int shx, shy;
    MediaPlayer sh3;

    public Shot3(Context context, int shx, int shy) {
        this.context = context;
        shot3 = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.shoot);
        this.shx = shx;
        this.shy = shy;
        sh3= MediaPlayer.create(context,R.raw.rocket);
        sh3.start();
    }
    public Bitmap getShot3(){
        return shot3;
    }
    public int getShotWidth3() {
        return shot3.getWidth();
    }
    public int getShotHeight3() {
        return shot3.getHeight();
    }
}