package com.example.hj.ticwear;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mobvoi.android.common.ConnectionResult;
import com.mobvoi.android.common.api.MobvoiApiClient;
import com.mobvoi.android.common.api.ResultCallback;
import com.mobvoi.android.wearable.MessageApi;
import com.mobvoi.android.wearable.Wearable;

public class MainActivity extends AppCompatActivity implements MobvoiApiClient.ConnectionCallbacks, MobvoiApiClient.OnConnectionFailedListener {
    private MobvoiApiClient mobvoiApiClient;
    private TextView textView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textview1);
        button = (Button) findViewById(R.id.button1);
        mobvoiApiClient = new MobvoiApiClient.Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        mobvoiApiClient.connect();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Wearable.MessageApi.sendMessage(
                        mobvoiApiClient, "nodeId", "path唯一标示", "传输的内容".getBytes()).setResultCallback(
                        new ResultCallback<MessageApi.SendMessageResult>() {
                            @Override
                            public void onResult(MessageApi.SendMessageResult sendMessageResult) {
                                if (!sendMessageResult.getStatus().isSuccess()) {
                                    Log.e("tag", "Failed to send message with status code: "
                                            + sendMessageResult.getStatus().getStatusCode());
                                }
                            }
                        }
                );
            }
        });
    }

    @Override
    public void onConnected(Bundle bundle) {
        textView.setText("连接成功");
    }

    @Override
    public void onConnectionSuspended(int i) {
        textView.setText("状态未知");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        textView.setText("连接失败");
    }
}











