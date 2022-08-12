package com.example.summer_project;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;

public class SoundPlayer {

    private static SoundPool soundPool;
    private static  int damage , game_over , gain_score , button;

    public SoundPlayer(Context context){

        //soundPool() is deprecated in api lvl 21 lollipop
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                .setMaxStreams(3)
                .build();

        damage = soundPool.load(context, R.raw.damage,1);
        game_over = soundPool.load(context , R.raw.game_over,1);
        gain_score = soundPool.load(context,R.raw.gain_score,1);
        button = soundPool.load(context,R.raw.button,1);
    }

    public void playDamageSound() {
        soundPool.play(damage,1.0f,1.0f,1,0,1.0f);
    }

    public void playGameOverSound() {
        soundPool.play(game_over,1.0f,1.0f,1,0,1.0f);
    }

    public void playScoreSound() {
        soundPool.play(gain_score,1.0f,1.0f,1,0,1.0f);
    }

    public void playButtonSound() {
        soundPool.play(button,1.0f,1.0f,1,0,1.0f);
    }

}
