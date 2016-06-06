package com.edu.lzjtu.music.service;

import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import com.edu.lzjtu.music.MainActivity;
import com.edu.lzjtu.music.R;
import com.edu.lzjtu.music.db.MusicDB;
import com.edu.lzjtu.music.model.ActivityServiceMessage;
import com.edu.lzjtu.music.model.MusicFile;
import com.edu.lzjtu.music.model.MusicPlayType;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by houjie on 2016/4/19.
 */
public class PlayMusicService extends Service {

    private ServiceBroadcast serviceBroadcast;
    private int openFlag =0;
    public static final String SERVICE_BROADCAST="com.edu.lzjtu.musicService";
  //  Notification notification;
  //  RemoteViews remoteViews;
    private ServiceBinder serviceBinder;


    @Override
    public void onCreate() {
        super.onCreate();
  //      notification = new Notification();
//        remoteViews =new RemoteViews(getPackageName(),R.layout.notification);
//        notification.contentView  =remoteViews;
//
//        remoteViews.set
//
//        remoteViews.setOnClickPendingIntent();
//        startForeground(1,notification);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return serviceBinder;
    }

    public class ServiceBinder extends Binder{

        private boolean first =false;

        public boolean isFirst() {
            return first;
        }

        private MediaPlayer palyer;
        private List<MusicFile> musicFileList;
        private int play_id;
        private boolean is_playing; ////false 没有播放，此时状态应该是三角 true 播放 此时状态应该是等号
        private int playing_progress;
        private int playing_type;
        private int playing_all_time;

        public MediaPlayer getPalyer() {
            return palyer;
        }

        public void setPalyer(MediaPlayer palyer) {
            this.palyer = palyer;
        }

        public List<MusicFile> getMusicFileList() {
            return musicFileList;
        }

        public void setMusicFileList(List<MusicFile> musicFileList) {
            this.musicFileList = musicFileList;
        }

        public int getPlay_id() {
            return play_id;
        }

        public void setPlay_id(int play_id) {
            this.play_id = play_id;
        }

        public boolean is_playing() {
            return is_playing;
        }

        public void setIs_playing(boolean is_playing) {
            this.is_playing = is_playing;
        }

        public int getPlaying_progress() {
            if(palyer!=null){
                playing_progress =palyer.getCurrentPosition();
            }
            return playing_progress;
        }

        public void setPlaying_progress(int playing_progress) {
            this.playing_progress = playing_progress;
        }

        public int getPlaying_type() {
            return playing_type;
        }

        public void setPlaying_type(int playing_type) {
            this.playing_type = playing_type;
        }

        public int getPlaying_all_time() {
            return playing_all_time;
        }

        public void setPlaying_all_time(int playing_all_time) {
            this.playing_all_time = playing_all_time;
        }

        public void musicStart(){
            Log.e("打开音乐------","---------------");
            if(palyer==null){
                Log.e("打开音乐---null---","---------------");
                startNewMusic(play_id);
            }else {
                Log.e("打开音乐----ffff--","---------------");
                is_playing=true;
                palyer.start();
            }
        }

        public void musicPause(){
            if(palyer ==null){

            }else{
                is_playing=false;
                palyer.pause();
            }
        }


        public void startNewMusic(int i){
            play_id =i;
            String musicPath =musicFileList.get(i).getMusicAbsolutePath();
            Log.i("TO--------------service",musicPath);
            if(palyer!=null){
                palyer.release();
            }
            palyer=new MediaPlayer();
            try {
                palyer.setDataSource(musicPath);
                palyer.prepare();
                palyer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        palyer.start();//播放开始
                        is_playing =true;
                        playing_all_time = palyer.getDuration();
                        Intent intent =new Intent(MainActivity.ACTIVITY_BROADCAST);
                        intent.putExtra("type",ActivityServiceMessage.NEW);
                        sendBroadcast(intent);
                    }
                });
                palyer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        next();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void musicChangeType() {
            playing_type++;
            if(playing_type>3){
                playing_type=0;
            }
        }

        public void musicNext(){
            play_id++;
            if(play_id>=musicFileList.size()){
                play_id=0;
            }
            startNewMusic(play_id);
        }

        public void musicLast(){
            play_id--;
            if(play_id<0){
                play_id = musicFileList.size()-1;
            }
            startNewMusic(play_id);
        }

        public void next() {
            switch (playing_type) {
                case MusicPlayType.ORDER_ONE: {
                    play_id++;
                    if (play_id >= musicFileList.size()) {
                        stopSelf();
                    } else {
                        startNewMusic(play_id);
                    }
                }
                break;
                case MusicPlayType.ORDER_TIMES: {
                    play_id++;
                    if (play_id >= musicFileList.size()) {
                        play_id = 0;
                    }
                    startNewMusic(play_id);
                }
                break;
                case MusicPlayType.RANDOM: {
                    Random r = new Random();
                    int ccc = r.nextInt(musicFileList.size());
                    play_id = ccc;
                    startNewMusic(play_id);

                }
                break;
                case MusicPlayType.SINGLE: {
                    startNewMusic(play_id);
                }
                break;
                default:
                    break;
            }
        }

        public void musicSeekto(int pogress){
            if(palyer!=null){
                palyer.seekTo(pogress);
            }
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(openFlag==0){
            //服务是第一次被启动，以前不存在，需要注册广播，并且向MainActivity发送这是第一次启动服务；
            serviceBroadcast = new ServiceBroadcast();
            IntentFilter filter = new IntentFilter();
            filter.addAction(SERVICE_BROADCAST);
            registerReceiver(serviceBroadcast, filter);


            //初始化数据
            serviceBinder = new ServiceBinder();
            serviceBinder.setMusicFileList(getDBMusicList());

            Log.i("第一次启动","=================");
            Intent intent1 =new Intent(MainActivity.ACTIVITY_BROADCAST);
            intent1.putExtra("type",ActivityServiceMessage.FIRST_OPEN_SERVICE);
            sendBroadcast(intent1);
            serviceBinder.first=true;
            openFlag++;
        }else{
            Intent intent1 =new Intent(MainActivity.ACTIVITY_BROADCAST);
            intent1.putExtra("type",ActivityServiceMessage.NO_FIRST_OPEN_SERVICE);
            sendBroadcast(intent1);
            Log.i("不是第一次启动","=================");
            serviceBinder.first=false;
        }
        return super.onStartCommand(intent, flags, startId);
    }








    public class ServiceBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        //serviceBinder.getPalyer().release();
        unregisterReceiver(serviceBroadcast);
        Log.i("Service Destroy","--------------");
    }



    private List<MusicFile> getDBMusicList() {
        List<MusicFile> FileList;
        MusicDB musicDB = MusicDB.getInstance(PlayMusicService.this);
        FileList = musicDB.loadMusic();
        return  FileList;
    }



}
