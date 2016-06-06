package com.example.hj.phonewatchchat;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.mobvoi.android.common.ConnectionResult;
import com.mobvoi.android.common.api.MobvoiApiClient;
import com.mobvoi.android.common.api.ResultCallback;
import com.mobvoi.android.wearable.MessageApi;

import com.mobvoi.android.wearable.Wearable;
import com.turing.androidsdk.InitListener;
import com.turing.androidsdk.SDKInit;
import com.turing.androidsdk.SDKInitBuilder;
import com.turing.androidsdk.TuringApiManager;

import turing.os.http.core.ErrorMessage;
import turing.os.http.core.HttpConnectionListener;
import turing.os.http.core.RequestResult;

/**
 * Created by hj on 2016/6/4.
 */
public class MainApplication extends Application{
    private boolean linkFlag;
    private MobvoiApiClient mobvoiApiClient;

    public MobvoiApiClient getMobvoiApiClient() {
        return mobvoiApiClient;
    }

    public boolean isLinkFlag() {
        return linkFlag;
    }

    public void setLinkFlag(boolean linkFlag) {
        linkFlag = linkFlag;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mobvoiApiClient = new MobvoiApiClient.Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(new MobvoiApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle bundle) {
                        if(mobvoiApiClient.isConnected()){
                            sendTestData();
                        }
                    }

                    @Override
                    public void onConnectionSuspended(int i) {
                        linkFlag = false;
                    }
                })
                .addOnConnectionFailedListener(new MobvoiApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult connectionResult) {
                        linkFlag =false;
                    }
                })
                .build();
        mobvoiApiClient.connect();

    }

    public void sendTestData(){
        Wearable.MessageApi.sendMessage(
                mobvoiApiClient, "nodeId", "testData", "testData".getBytes()).setResultCallback(
                new ResultCallback<MessageApi.SendMessageResult>() {
                    @Override
                    public void onResult(MessageApi.SendMessageResult sendMessageResult) {
                        if (sendMessageResult.getStatus().isSuccess()) {
                            linkFlag=true;
                        }else {
                            linkFlag=false;
                        }
                    }
                }
        );
    }
}
