package com.example.hj.phonewatchchat;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobvoi.android.common.ConnectionResult;
import com.mobvoi.android.common.api.MobvoiApiClient;
import com.mobvoi.android.common.api.ResultCallback;
import com.mobvoi.android.speech.SpeechRecognitionApi;
import com.mobvoi.android.speech.synthesizer.SpeechSynthesizerApi;
import com.mobvoi.android.speech.synthesizer.SpeechSynthesizerCallbackInterface;
import com.mobvoi.android.speech.synthesizer.internal.DefaultSpeechSynthesizerCallback;
import com.mobvoi.android.wearable.MessageApi;
import com.mobvoi.android.wearable.MessageEvent;
import com.mobvoi.android.wearable.Wearable;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends SpeechRecognitionApi.SpeechRecogActivity {
    private MainApplication mainApp;
    private TextView linkFlagTextVeiw;
    private ListView showChatListView;
    private ChatAdapter chatAdapter;
    private List<Type> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainApp = (MainApplication) getApplication();
        init();
    }

    private void init() {
        linkChatListener();
        linkFlagTextVeiw = (TextView) findViewById(R.id.linkFlagTextVeiw);
        linkStaus();
        linkFlagTextVeiw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mainApp.getMobvoiApiClient().isConnected()){
                    if(mainApp.isLinkFlag()){
                        linkStaus();
                    }else {
                        mainApp.sendTestData();
                        //mainApp.getMobvoiApiClient().connect();
                        linkStaus();
                    }
                }else {
                    mainApp.getMobvoiApiClient().connect();
                }

            }
        });

        findViewById(R.id.wear_input_button).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startVoiceInput();
            }
        });

        showChatListView = (ListView) findViewById(R.id.showChatListView);
        //mainApp = (MainApplication) getApplication();
        mList = new ArrayList<>();
        chatAdapter = new ChatAdapter(this, mList);
        showChatListView.setAdapter(chatAdapter);

    }

    @Override
    public void onRecognitionSuccess(final String text) {
        if (mainApp.getMobvoiApiClient().isConnected()) {
            if (text.isEmpty()) {
                Toast.makeText(MainActivity.this, "你没有要发送的消息哦", Toast.LENGTH_SHORT).show();
                return;
            }
            Wearable.MessageApi.sendMessage(
                    mainApp.getMobvoiApiClient(), "nodeId", "path唯一标示", text.getBytes()).setResultCallback(
                    new ResultCallback<MessageApi.SendMessageResult>() {
                        @Override
                        public void onResult(MessageApi.SendMessageResult sendMessageResult) {
                            if (sendMessageResult.getStatus().isSuccess()) {
//                                Log.e("tag", "Failed to send message with status code: "
//                                        + sendMessageResult.getStatus().getStatusCode());
                                mList.add(new WearType(R.mipmap.wear, text));
                                Toast.makeText(MainActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                                RefreshListView();
                            } else {
                                Toast.makeText(MainActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
            );
        } else {
            Toast.makeText(MainActivity.this, "未连接，无法发送", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onRecognitionFailed() {
        Toast.makeText(MainActivity.this, "没有听到语音", Toast.LENGTH_SHORT).show();
    }

    private void RefreshListView() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                chatAdapter.notifyDataSetChanged();
                showChatListView.setSelection(showChatListView.getBottom());
            }
        });

    }

    private void linkStaus(){
        if(mainApp.isLinkFlag()){
            linkFlagTextVeiw.setText("蓝牙已连接");
            linkFlagTextVeiw.setBackgroundColor(Color.argb(0xFF,0x40,0xFF,0x46));
        }else {
            linkFlagTextVeiw.setText("蓝牙未连接，点击刷新");
            linkFlagTextVeiw.setBackgroundColor(Color.argb(0xFF,0xFF,0x40,0x43));
        }
    }

    private void linkChatListener(){
        Wearable.MessageApi.addListener(mainApp.getMobvoiApiClient(), new MessageApi.MessageListener() {
            @Override
            public void onMessageReceived(MessageEvent messageEvent) {
                Log.i("线程onMessageReceived",Thread.currentThread().getName().toString());
                final String ss = new String(messageEvent.getData());

                if(ss.equals("testData")){

                }else {
                    mList.add(new PhoneType(R.mipmap.phone,ss));
                    RefreshListView();
                    SpeechSynthesizerApi.startSynthesizer(getApplicationContext(),
                            new DefaultSpeechSynthesizerCallback(), ss,
                            5000);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                }
            }
        });
    }

}
