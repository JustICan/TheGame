package com.example.ilysh.myapplication;

import android.app.Application;
import android.media.MediaPlayer;
import android.os.Bundle;

public class TheGameApplication extends Application {

    MediaPlayer mediaPlayer;
    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.music);
        mediaPlayer.setLooping(true);
    }
}
