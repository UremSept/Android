package com.example.hj.classroom.com.example.houjie.learn;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class exam3_3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView txt = new TextView(this);
        txt.setText("www.hnist.com");
        txt.setBackgroundColor(Color.GREEN);
        txt.setTextColor(Color.RED);
        txt.setTextSize(30);
        setContentView(txt);
    }
}
