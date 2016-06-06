package com.edu.lzjtu.music.activity;

import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.edu.lzjtu.music.MainActivity;
import com.edu.lzjtu.music.R;
import com.edu.lzjtu.music.db.MusicDB;
import com.edu.lzjtu.music.model.ActivityServiceMessage;
import com.edu.lzjtu.music.model.ConfigMusicList;

public class Activity_Scan_music extends AppCompatActivity {
    private Button button;
    private TextView textView;
    private boolean flag =false;
    MusicScanThread musicScanThread;
    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.arg1 ==98){
                button.setText("正在扫描");
                musicScanThread.getHhandler().sendEmptyMessage(1);
            }else if(msg.arg1==99){
                button.setText("扫描完成，点击返回");
                button.setClickable(true);
                flag=false;
            }else {
                String str = (String) msg.obj;
                textView.setText(str);
                musicScanThread.getHhandler().sendEmptyMessage(1);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_music);
        musicScanThread = new MusicScanThread();
        //MusicDB musicDB =MusicDB.getInstance(Activity_Scan_music.this);
        button = (Button) findViewById(R.id.begin_scan_music);
        textView = (TextView) findViewById(R.id.show_scaning);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("点击了开始扫描","----------");
                if(button.getText().toString().equals("扫描完成，点击返回")){
                    Intent intent =new Intent(MainActivity.ACTIVITY_BROADCAST);
                    intent.putExtra("type",ActivityServiceMessage.RESET);
                    sendBroadcast(intent);
                    finish();
                }else {
                    musicScanThread.start();
                    button.setClickable(false);
                }

            }
        });

    }
    class MusicScanThread extends Thread{
        MusicDB musicDB =MusicDB.getInstance(Activity_Scan_music.this);
        ConfigMusicList configMusicList = new ConfigMusicList(musicDB);
        private Handler hhandler = null;
        public  Handler getHhandler(){
            return hhandler;
        }
        @Override
        public void run() {
            Looper.prepare();
            hhandler=new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    Message msg1 = new Message();
                    msg1.obj = configMusicList.getScaningPath();
                    if(flag){
                        Log.i("收到主线程发送来的消息","----------"+msg1.arg1);
                        handler.sendMessageDelayed(msg1,100);
                    }
                }
            };
            Log.i("线程启动","----------"+"Looper.loop()");
            flag=true;
            Message msg2 = new Message();
            msg2.arg1 =98;
            handler.sendMessage(msg2);
            new Thread(){
                @Override
                public void run() {
                    musicDB.deleteMusicAll();
                    Log.e("===================",Environment.getExternalStorageDirectory().getAbsolutePath());
                    configMusicList.init(Environment.getExternalStorageDirectory().getAbsolutePath(),".mp3",null);
                    configMusicList.start();
                    Message msg1 = new Message();
                    msg1.arg1 =99;
                    handler.sendMessage(msg1);
                }
            }.start();
            Looper.loop();
            Log.i("线程启动","----------"+"Looper.loop()");


        }
    }

}
