package com.example.cx.musicplayer;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import java.io.IOException;

/**
 * Created by zqt on 2016/5/23.
 */
public class PlayService extends Service implements MediaPlayer.OnCompletionListener {

    private MediaPlayer mediaPlayer;
    private int duration;
    public boolean isPlaying = false;

    @Override
    public IBinder onBind(Intent intent) {
        return new PlayBinder();
    }

    class PlayBinder extends Binder {
        public PlayService getPlayService() {
            return PlayService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
       // mediaPlayer = MediaPlayer.create(this, R.raw.daerwen);
        mediaPlayer.setOnCompletionListener(this);
        duration = mediaPlayer.getDuration();
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mediaPlayer.seekTo(mediaPlayer.getCurrentPosition());
        }
    };

    public void play() {

        try {
            mediaPlayer.reset();
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        mediaPlayer.pause();
        isPlaying = false;
    }

    public void start() {
        mediaPlayer.start();
        isPlaying = true;
    }

    public void setTo(int msec) {
        mediaPlayer.seekTo(msec);
    }

    public int getDuration() {
        return duration;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        isPlaying = false;
    }
}
