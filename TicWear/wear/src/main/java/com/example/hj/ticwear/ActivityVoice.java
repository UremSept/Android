package com.example.hj.ticwear;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.widget.TextView;

import com.mobvoi.android.speech.SpeechRecognitionApi;

public class ActivityVoice extends SpeechRecognitionApi.SpeechRecogActivity {
    private TextView textView;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_voice);
        textView = (TextView) findViewById(R.id.text11);
        startRecognition();
    }

    @Override
    public void onRecognitionSuccess(String s) {
// 获得语音识别结果
        Log.d("语音识别", "Get speech recognition result: " +
                s);

        textView.setText(s);
    }

    @Override
    public void onRecognitionFailed() {
        // 当调用语音识别失败
        Log.e("语音识别", "Speech recognition failed");
    }
}
