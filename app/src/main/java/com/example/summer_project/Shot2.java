package com.example.summer_project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;

public class Shot2 {
    Bitmap shot2;
    Context context;
    int shx, shy;
    MediaPlayer sh2;

    public Shot2(Context context, int shx, int shy) {
        this.context = context;
        shot2 = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.bullet);
        this.shx = shx;
        this.shy = shy;
        sh2=MediaPlayer.create(context,R.raw.rocket);
        sh2.start();
    }
    public Bitmap getShot2(){
        return shot2;
    }
    public int getShotWidth2() {
        return shot2.getWidth();
    }
    public int getShotHeight2() {
        return shot2.getHeight();
    }
}
