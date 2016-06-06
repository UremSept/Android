package com.edu.lzjtu.music;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.lzjtu.music.activity.Activity_Scan_music;

import com.edu.lzjtu.music.model.ActivityServiceMessage;
import com.edu.lzjtu.music.model.HaveMusicFragment;

import com.edu.lzjtu.music.model.MusicPlayType;
import com.edu.lzjtu.music.model.NoMusicFragment;
import com.edu.lzjtu.music.service.PlayMusicService;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton mBtnPaly;
    private ImageButton mBtnNext;
    private ImageButton mBtnLast;
    private ImageButton mBtnPlayType;
    private TextView mTextViewPlayTime;
    private TextView mTextViewMusicTime;
    private SeekBar mSeekBarMusic;
    private TextView getmTextViewMusicName;

    private MusicBroadcast musicBroadcast;
    private FragmentManager manager;

    private PlayMusicService.ServiceBinder serviceBinder;

    private Handler MyHandle = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            SimpleDateFormat format = new SimpleDateFormat("mm:ss");
            Date date1 = new Date(serviceBinder.getPlaying_progress());
            mSeekBarMusic.setProgress(serviceBinder.getPlaying_progress());
            mTextViewPlayTime.setText(format.format(date1));
        }
    };


    public static final String ACTIVITY_BROADCAST = "com.edu.lzjtu.musicTime";

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("连接成功", "=================");
            serviceBinder = (PlayMusicService.ServiceBinder) service;
            if (serviceBinder.isFirst()) {
                setState();
                init();
            } else {
                setState();
                init();
                synchroService();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("连接不成功", "=================");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDefine();

        musicBroadcast = new MusicBroadcast();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTIVITY_BROADCAST);
        registerReceiver(musicBroadcast, filter);

        Intent intent = new Intent(MainActivity.this, PlayMusicService.class);
        startService(intent);

        manager = getSupportFragmentManager();

    }

    private void initDefine() {
        mSeekBarMusic = (SeekBar) findViewById(R.id.seekBarMusic);
        mTextViewPlayTime = (TextView) findViewById(R.id.paly_time);
        mTextViewMusicTime = (TextView) findViewById(R.id.music_time);
        mBtnPaly = (ImageButton) findViewById(R.id.start_stop);
        mBtnNext = (ImageButton) findViewById(R.id.next_song);
        mBtnLast = (ImageButton) findViewById(R.id.last_song);
        mBtnPlayType = (ImageButton) findViewById(R.id.play_type);
        getmTextViewMusicName = (TextView) findViewById(R.id.music_name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Menu_Scan_Song:
                Intent intent1 = new Intent(MainActivity.this, Activity_Scan_music.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("退出--------Activity", "000000-----------------------------------");
        unregisterReceiver(musicBroadcast);


        Log.e("退出--------Activity", "111111111111111111-----------------------------------");
        unbindService(connection);
        if (!serviceBinder.is_playing()) {
            Intent intent = new Intent(MainActivity.this, PlayMusicService.class);

            Log.e("退出--------Activity", "222222222222222-----------------------------------");
            stopService(intent);
        }


    }

    //初始化
    private void init() {
        Log.i("FileList------=========", "" + serviceBinder.getMusicFileList().size());
        if (serviceBinder.getMusicFileList().size() == 0) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.showMusicList, new NoMusicFragment(MainActivity.this));
            transaction.commit();
        } else {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.showMusicList, new HaveMusicFragment(serviceBinder.getMusicFileList(), MainActivity.this));
            transaction.commit();

            mBtnPlayType.setOnClickListener(this);

            mBtnPaly.setOnClickListener(this);

            mBtnNext.setOnClickListener(this);

            mBtnLast.setOnClickListener(this);

            mSeekBarMusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    serviceBinder.musicSeekto(seekBar.getProgress());

                }
            });
        }
    }


    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_stop:
                musicPlay();
                break;
            case R.id.next_song:
                musicNext();
                break;
            case R.id.last_song:
                musicLast();
                break;
            case R.id.play_type:
                playTypeChange();
                break;
        }
    }

    //设置播放图标
    private void setPlayOrPauseButtonImage() {
        if (serviceBinder.is_playing()) {
            mBtnPaly.setBackgroundResource(R.drawable.imagebutton_music_pause);
        } else {
            mBtnPaly.setBackgroundResource(R.drawable.imagebutton_music_start);
        }
    }

    //下一曲
    private void musicNext() {
        serviceBinder.musicNext();
        setPlayOrPauseButtonImage();
    }

    //播放或暂停
    private void musicPlay() {
        if (serviceBinder.is_playing()) {
            serviceBinder.musicPause();
        } else {
            serviceBinder.musicStart();
            MusicThread thread = new MusicThread();
            thread.start();
        }
        setPlayOrPauseButtonImage();
    }

    //播放指定的曲目
    private void musicOpenTo(int i) {
        serviceBinder.startNewMusic(i);
    }

    //上一曲
    private void musicLast() {
        serviceBinder.musicLast();
        setPlayOrPauseButtonImage();
    }


    //广播
    public class MusicBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int type = intent.getIntExtra("type", 888);
            Log.e("receive-------", "--------------------");
            switch (type) {
                case ActivityServiceMessage.FIRST_OPEN_SERVICE:
                    Intent intent1 = new Intent(MainActivity.this, PlayMusicService.class);
                    bindService(intent1, connection, BIND_AUTO_CREATE);
                    break;
                case ActivityServiceMessage.NEW:
                    SimpleDateFormat format = new SimpleDateFormat("mm:ss");
                    Date date = new Date(serviceBinder.getPlaying_all_time());
                    mSeekBarMusic.setMax(serviceBinder.getPlaying_all_time());
                    mTextViewMusicTime.setText(format.format(date));
                    getmTextViewMusicName.setText(serviceBinder.getMusicFileList().get(serviceBinder.getPlay_id()).getMusicName());
                    MusicThread thread = new MusicThread();
                    thread.start();
                    setPlayOrPauseButtonImage();
                    break;
                case ActivityServiceMessage.NO_FIRST_OPEN_SERVICE:
                    Intent intent3 = new Intent(MainActivity.this, PlayMusicService.class);
                    bindService(intent3, connection, BIND_AUTO_CREATE);

                    break;
                case ActivityServiceMessage.SEEK_TO:
                    Log.e("----------", "----------------------seekto");
                    int c = intent.getIntExtra("what", 0);
                    musicOpenTo(c);
                    break;
                case ActivityServiceMessage.RESET:
                    Intent intent2 = new Intent(MainActivity.this, PlayMusicService.class);
                    stopService(intent2);
                    unbindService(connection);
                    startService(intent2);
                    break;
                default:
                    break;
            }
        }

    }

    private void musicPlayTypeSelect() {
        switch (serviceBinder.getPlaying_type()) {
            case MusicPlayType.ORDER_ONE: {
                mBtnPlayType.setBackgroundResource(R.mipmap.one);
                Toast.makeText(this, "选择顺序播放一圈", Toast.LENGTH_SHORT).show();
            }
            break;
            case MusicPlayType.ORDER_TIMES: {
                mBtnPlayType.setBackgroundResource(R.mipmap.all);
                Toast.makeText(this, "选择顺序播放多圈", Toast.LENGTH_SHORT).show();
            }
            break;
            case MusicPlayType.RANDOM: {
                mBtnPlayType.setBackgroundResource(R.mipmap.random);
                Toast.makeText(this, "选择随机播放", Toast.LENGTH_SHORT).show();

            }
            break;
            case MusicPlayType.SINGLE: {
                mBtnPlayType.setBackgroundResource(R.mipmap.onlyone);
                Toast.makeText(this, "选择单曲循环", Toast.LENGTH_SHORT).show();
            }
            break;
            default:
                break;
        }
    }

    private void playTypeChange() {
        serviceBinder.musicChangeType();
        musicPlayTypeSelect();
    }

    private void setStartState() {
        serviceBinder.setPlay_id(0);
        serviceBinder.setIs_playing(false);
        serviceBinder.setPlaying_progress(0);
        serviceBinder.setPlaying_type(0);
        serviceBinder.setPlaying_all_time(0);
    }


    private void synchroService() {
        if (serviceBinder.getPalyer().isPlaying()) {
            mSeekBarMusic.setMax(serviceBinder.getPlaying_all_time());

            mSeekBarMusic.setProgress(serviceBinder.getPlaying_progress());

            SimpleDateFormat format = new SimpleDateFormat("mm:ss");
            Date date = new Date(serviceBinder.getPlaying_all_time());

            mTextViewMusicTime.setText(format.format(date));
            MusicThread thread = new MusicThread();
            getmTextViewMusicName.setText(serviceBinder.getMusicFileList().get(serviceBinder.getPlay_id()).getMusicName());
            thread.start();
            setPlayOrPauseButtonImage();
        }
    }

    private void setState() {
        musicPlayTypeSelect();
        setPlayOrPauseButtonImage();
    }

    private class MusicThread extends Thread {
        @Override
        public void run() {
            while (serviceBinder.is_playing()) {
                Message message = new Message();
                MyHandle.sendEmptyMessage(1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
