package com.lishuang.administrator.mediaplayerdemo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.lishuang.administrator.mediaplayerdemo.adpter.MusicListAdapter;
import com.lishuang.administrator.mediaplayerdemo.service.MusicService;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {
    //UI界面的控件对象
    private ImageButton mButtonPlay;
    private ImageButton mButtonNextMusic;
    private ImageButton mButtonPreviousMusic;
    private ListView mListViewMusic;
    private TextView mTextViewDuration;
    private TextView mTextViewCurrentTime;
    private SeekBar mSeekBar;

    private MusicListAdapter mAdapter;// 将文件内容适配到列表上适配器
    private MusicBroadcast musicBroadcast;//定义广播
    private MusicFilter musicFilter;//筛选mp3文件的过滤器
    private List<File> mFiles;//筛选之后的文件mp3格式后的文件
    private File[] mPreFiles;//筛选之前的文件

    private int currentPosition=0;//当前文件的位置，默认为第一个也就是0
    private boolean mIsPause=true;//记录是否是停止状态





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取UI界面的对象
        mListViewMusic = (ListView) findViewById(R.id.music_list);
        mButtonPlay = (ImageButton) findViewById(R.id.button_play);
        mButtonNextMusic = (ImageButton) findViewById(R.id.button_next_music);
        mButtonPreviousMusic = (ImageButton) findViewById(R.id.button_previous_music);
        mTextViewDuration = (TextView) findViewById(R.id.textview_duration);
        mTextViewCurrentTime = (TextView) findViewById(R.id.textview_current_time);
        mSeekBar = (SeekBar) findViewById(R.id.seekbar);

        //定义播放（暂停）， 上一首，下一首的按钮
        mButtonPlay.setOnClickListener(this);
        mButtonNextMusic.setOnClickListener(this);
        mButtonPreviousMusic.setOnClickListener(this);

        //注册一个广播，用于服务练习Activity，设置SeekBar的进度
        musicBroadcast = new MusicBroadcast();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.lishuang.musictime");
        registerReceiver(musicBroadcast,filter);

        //获得音乐的文件列表
        File musicDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        Log.d("data", "音乐的路径是："+musicDir.getAbsolutePath());
        //将MP3文件筛选出来
        musicFilter = new MusicFilter();
        mFiles = new ArrayList<File>();
        mPreFiles = musicDir.listFiles();
        for(File file:mPreFiles){
            if(musicFilter.accept(musicDir, file.getName())){
                mFiles.add(file);
            }
        }

        //将mp3文件添加到ListView中
        mAdapter = new MusicListAdapter(getLayoutInflater(),mFiles );
        mListViewMusic.setAdapter(mAdapter);
        mListViewMusic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //点击列上的歌曲，就会播放
                Intent intent = new Intent(MainActivity.this, MusicService.class);
                intent.putExtra("start_type", Config.START_TYPE_NEW_MUSIC);
                intent.putExtra("operate",Config.OPEARTION_PLAY );
                intent.putExtra("MusicName", mFiles.get(position).getAbsolutePath());
                startService(intent);//启动服务播放歌曲

                mIsPause=false; //设置暂停的状态
                setPlayOrPauseButtonImage();//设置播放（暂停）按钮的图片

                currentPosition = position;//更改当前播放文件的位置
                Log.d("currentPosition", "点击列表时，当前歌曲的位置是"+currentPosition);
            }
        });
        //设置SeekBar的进度
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Intent intent = new Intent(MainActivity.this, MusicService.class);
                intent.putExtra("start_type", Config.START_TYPE_SEEK_TO);
                intent.putExtra("progress", seekBar.getProgress());
                startService(intent);
            }
        });


    }
    /*
    筛选MP3文件的类
     */
    class MusicFilter implements FilenameFilter {
        public boolean accept(File dir, String filename) {
            return (filename.endsWith(".mp3"));
        }
    }


    /*
    定义广播
     */
    public class MusicBroadcast extends BroadcastReceiver {
        public static final String MUSIC_TIME_ACTION = "com.lishuang.musictime";
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("data", "接收到一个广播 ");
            int  type= intent.getIntExtra("type", 0);
            switch (type){
                case MusicService.DURATION_TYPE:
                    int time = intent.getIntExtra("time", 0);
                    Log.d("data", "这是总时间"+time);
                    //将时间转化为分钟秒
//                    int durationMinute = time/1000/60;
//                    int durationSecond  = time/1000%60;
                    Date dateDuration = new Date(time);
                    SimpleDateFormat formatDuration = new SimpleDateFormat("mm:ss");
                    mSeekBar.setMax(time);
//                    mTextViewDuration.setText(durationMinute+":"+durationSecond);
                    mTextViewDuration.setText(formatDuration.format(dateDuration));
                    break;
                case MusicService.CURRENT_TIME_TYPE:
                    int currenttime=intent.getIntExtra("time", 0);
                    //将时间转化为分钟秒
//                    int dcurrenttimeMinute = currenttime/1000/60;
//                    int currenttimeSecond  = currenttime/1000%60;
                    Date dateCurrentTime = new Date(currenttime);
                    SimpleDateFormat formatCurrent = new SimpleDateFormat("mm:ss");
                    Log.d("data", "这是当前时间"+currenttime);
                    mSeekBar.setProgress(currenttime);
//                    mTextViewCurrentTime.setText(dcurrenttimeMinute+":"+currenttimeSecond);
                    mTextViewCurrentTime.setText(formatCurrent.format(dateCurrentTime));
                    break;
                default:
                    break;
            }
        }
    }



    /*
    点击事件
     */
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.button_play:
                mIsPause=!mIsPause;
                Log.d("pause", "点击按钮后的暂停状态"+mIsPause);
                Intent intent1 = new Intent(MainActivity.this,MusicService.class);
                intent1.putExtra("start_type", Config.START_TYPE_OPERATION);
                intent1.putExtra("MusicName", mFiles.get(currentPosition).getAbsolutePath());
                intent1.putExtra("operation",Config.OPEARTION_PLAY );
                intent1.putExtra("state", mIsPause);
                startService(intent1);
                // 切换按钮的图片
                setPlayOrPauseButtonImage();
                break;
            case  R.id.button_next_music:
                currentPosition = currentPosition+1;
                Intent intent2 = new Intent(MainActivity.this,MusicService.class);
                intent2.putExtra("start_type", Config.START_TYPE_OPERATION);
                intent2.putExtra("operation",Config.OPEARTION_NEXT_MUSIC);
                intent2.putExtra("MusicName", mFiles.get(currentPosition).getAbsolutePath());
                startService(intent2);
                mIsPause=false; //设置暂停的状态
                setPlayOrPauseButtonImage();//设置播放（暂停）按钮的图片
                Log.d("currentPosition", "播放下一首时， 当前播放的文件的位置！"+currentPosition);
                Log.d("currentPosition", "开启了服务 ");
                break;
            case  R.id.button_previous_music:
                Intent intent3 = new Intent(MainActivity.this,MusicService.class);
                intent3.putExtra("start_type", Config.START_TYPE_OPERATION);
                intent3.putExtra("operation",Config.OPEARTION_PREVIOUS_MUSIC);
                if(currentPosition>0){
                    currentPosition = currentPosition-1;
                }
                intent3.putExtra("MusicName", mFiles.get(currentPosition).getAbsolutePath());
                Log.d("currentPosition", "播放上一首时， 当前播放的文件的位置！"+currentPosition);
                startService(intent3);
                mIsPause=false; //设置暂停的状态
                setPlayOrPauseButtonImage();//设置播放（暂停）按钮的图片
                Log.d("currentPosition", "开启了服务 ");
                break;
            default:
                break;
        }
    }
    /*
    Activity销毁执行的方法
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(musicBroadcast);//取消注册广播
    }

    /*
    设置开始（暂停按钮的图片）
     */
    private void setPlayOrPauseButtonImage() {
        if(!mIsPause){
            mButtonPlay.setBackgroundResource(R.mipmap.img_lockscreen_pause_normal);
        }else  if(mIsPause){
            mButtonPlay.setBackgroundResource(R.mipmap.img_lockscreen_play_normal);
        }
    }
}
