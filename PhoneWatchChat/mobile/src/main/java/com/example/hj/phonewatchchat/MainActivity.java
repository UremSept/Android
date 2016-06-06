package com.example.hj.phonewatchchat;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobvoi.android.common.ConnectionResult;
import com.mobvoi.android.common.api.MobvoiApiClient;
import com.mobvoi.android.common.api.ResultCallback;
import com.mobvoi.android.wearable.MessageApi;
import com.mobvoi.android.wearable.MessageEvent;
import com.mobvoi.android.wearable.Wearable;
import com.turing.androidsdk.InitListener;
import com.turing.androidsdk.SDKInit;
import com.turing.androidsdk.SDKInitBuilder;
import com.turing.androidsdk.TuringApiManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import turing.os.http.core.ErrorMessage;
import turing.os.http.core.HttpConnectionListener;
import turing.os.http.core.RequestResult;

public class MainActivity extends AppCompatActivity {
    private TextView linkFlagTextVeiw;
    private EditText chatInputEditView;
    private Button chatSendButton;
    private ListView showChatListView;
    private ChatAdapter chatAdapter;
    private MainApplication mainApp;
    private List<Type> mList;
    private TuringApiManager m;
    private Boolean mFlag;

    //  private MobvoiApiClient mobvoiApiClient;
    //  private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainApp = (MainApplication) getApplication();
        init();
    }

    private void init() {
        //mobvoiApiClient = mainApp.getMobvoiApiClient();
        linkChatListener();
        linkFlagTextVeiw = (TextView) findViewById(R.id.linkFlagTextVeiw);
        linkStaus();
        linkFlagTextVeiw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainApp.getMobvoiApiClient().isConnected()) {
                    if (mainApp.isLinkFlag()) {
                        linkStaus();
                    } else {
                        mainApp.sendTestData();
                        //mainApp.getMobvoiApiClient().connect();
                        linkStaus();
                    }
                } else {
                    mainApp.getMobvoiApiClient().connect();
                }

            }
        });

        chatInputEditView = (EditText) findViewById(R.id.chatInputEditView);
        chatSendButton = (Button) findViewById(R.id.chatSendButton);
        chatSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String s = chatInputEditView.getText().toString();
                if (mainApp.isLinkFlag()) {
                    if (s.isEmpty()) {
                        Toast.makeText(MainActivity.this, "你没有要发送的消息哦", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    sendToWearMessage(s);
                } else {
                    Toast.makeText(MainActivity.this, "未连接，无法发送", Toast.LENGTH_SHORT).show();
                }
            }
        });

        showChatListView = (ListView) findViewById(R.id.showChatListView);
        //mainApp = (MainApplication) getApplication();
        mList = new ArrayList<>();
        chatAdapter = new ChatAdapter(this, mList);
        showChatListView.setAdapter(chatAdapter);
        initYX();
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


    private void linkStaus() {
        if (mainApp.isLinkFlag()) {
            linkFlagTextVeiw.setText("蓝牙已连接");
            linkFlagTextVeiw.setBackgroundColor(Color.argb(0xFF, 0x40, 0xFF, 0x46));
        } else {
            linkFlagTextVeiw.setText("蓝牙未连接，点击刷新");
            linkFlagTextVeiw.setBackgroundColor(Color.argb(0xFF, 0xFF, 0x40, 0x43));
        }
    }

    private void linkChatListener() {
        Wearable.MessageApi.addListener(mainApp.getMobvoiApiClient(), new MessageApi.MessageListener() {
            @Override
            public void onMessageReceived(MessageEvent messageEvent) {
                Log.i("线程onMessageReceived", Thread.currentThread().getName().toString());
                final String ss = new String(messageEvent.getData());
                if (ss.equals("testData")) {

                } else {
                    if (mFlag) {
                        m.requestTuringAPI(ss);
                    }
                    mList.add(new WearType(R.mipmap.wear, ss));
                    RefreshListView();
                }
            }
        });
    }


    private void initYX() {
        SDKInitBuilder sdkInitBuilder = new SDKInitBuilder(MainActivity.this).setSecret("80a75f505021abe2")
                .setTuringKey("163dafce9de2abcc21b063b642088e47")
                .setUniqueId("houjie_It@163.com");
        SDKInit.init(sdkInitBuilder, new InitListener() {
            @Override
            public void onComplete() {
                Log.i("语音系统初始化成功", "-----------------------");
                m = new TuringApiManager(MainActivity.this);
                mFlag = true;
                m.setHttpListener(new HttpConnectionListener() {
                    @Override
                    public void onError(ErrorMessage errorMessage) {
                        Toast.makeText(MainActivity.this, "网络请求失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(RequestResult requestResult) {
                        sendToWearMessage(requestResult.toString());
                    }
                });
            }

            @Override
            public void onFail(String s) {
                Log.i("语音系统初始化失败", "-----------------------");
            }
        });
    }

    private void sendToWearMessage(final String text) {
        Wearable.MessageApi.sendMessage(
                mainApp.getMobvoiApiClient(), "nodeId", "path唯一标示", text.getBytes()).setResultCallback(
                new ResultCallback<MessageApi.SendMessageResult>() {
                    @Override
                    public void onResult(MessageApi.SendMessageResult sendMessageResult) {
                        if (sendMessageResult.getStatus().isSuccess()) {
//                                        Log.e("tag", "Failed to send message with status code: "
//                                                + sendMessageResult.getStatus().getStatusCode());
                            mList.add(new PhoneType(R.mipmap.phone, text));
                            Toast.makeText(MainActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                            RefreshListView();
                        } else {
                            Toast.makeText(MainActivity.this, "发送失败", Toast.LENGTH_SHORT).show();
                            mainApp.sendTestData();
                            linkStaus();
                        }
                    }
                }
        );
    }
}
