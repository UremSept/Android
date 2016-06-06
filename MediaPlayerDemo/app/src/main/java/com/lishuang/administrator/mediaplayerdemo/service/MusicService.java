package com.lishuang.administrator.mediaplayerdemo.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import com.lishuang.administrator.mediaplayerdemo.Config;
import com.lishuang.administrator.mediaplayerdemo.MainActivity;

import java.io.IOException;

/**
 * Created by Administrator on 2015/9/10.
 */
public class MusicService extends Service {

    private MediaPlayer player;
    public static final int DURATION_TYPE = 0;
    public static final int CURRENT_TIME_TYPE = 1;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int startType = intent.getIntExtra("start_type", Config.START_TYPE_NEW_MUSIC);
        switch (startType) {
            case Config.START_TYPE_NEW_MUSIC:
                startNewMusic(intent);
                break;
            case Config.START_TYPE_SEEK_TO:
                if (player != null) {
                    int progress = intent.getIntExtra("progress", 0);
                    player.seekTo(progress);
                }
                break;
            case Config.START_TYPE_OPERATION: {
                int operation = intent.getIntExtra("operation", Config.OPEARTION_PLAY);
                switch (operation) {
                    case Config.OPEARTION_PLAY:
                        playOrPauseMusic(intent);//开始和暂停播放音乐
                        break;
                    case Config.OPEARTION_NEXT_MUSIC:
                        if (player != null) {
                            player.stop();
                            player=null;
                            startNewMusic(intent);
                        }
                        break;
                    case Config.OPEARTION_PREVIOUS_MUSIC:
                        if (player != null) {
                            player.stop();
                            player=null;
                            startNewMusic(intent);
                        }
                        break;
                }
            }
            break;
            default:
                break;
        }
        return super.onStartCommand(intent, flags, startId);
    }

    /*
    开始和暂停操作
     */
    private void playOrPauseMusic(Intent intent) {
        if (player == null) {
            startNewMusic(intent);
        } else {
            if (intent.getBooleanExtra("state", false)) {
                player.pause();
            } else {
                player.start();
            }
            MusicThread thread = new MusicThread();
            thread.start();
        }
    }

    /*
    开始播放一首新的音乐
     */
    private void startNewMusic(Intent intent) {
        String musicPath = intent.getStringExtra("MusicName");
        if (player == null) {
            player = new MediaPlayer();
        }
        player.reset();
        try {
            player.setDataSource(musicPath);
            player.prepare();
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    player.start();

                    int time = player.getDuration();//获得所有的时间
                    Intent intent = new Intent("com.lishuang.musictime");
                    intent.putExtra("time", time);
                    intent.putExtra("type", DURATION_TYPE);
                    sendBroadcast(intent);
                    MusicThread thread = new MusicThread();
                    thread.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    class MusicThread extends Thread {
        @Override
        public void run() {
            //获得当前的时长
            while (player.isPlaying()) {
                int time = player.getCurrentPosition();
                Intent intent = new Intent("com.lishuang.musictime");
                intent.putExtra("time", time);
                intent.putExtra("type", CURRENT_TIME_TYPE);
                sendBroadcast(intent);
                //每隔一秒发送一次
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
