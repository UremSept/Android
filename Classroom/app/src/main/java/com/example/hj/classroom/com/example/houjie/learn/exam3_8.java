package com.example.hj.classroom.com.example.houjie.learn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;

import com.example.hj.classroom.R;

public class exam3_8 extends AppCompatActivity {
    private RadioButton radioButton1;
    private RadioButton radioButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam3_8);
        radioButton1 = (RadioButton) findViewById(R.id.exam3_8_RBut3);
        radioButton2 = (RadioButton) findViewById(R.id.exam3_8_RBut4);
        radioButton1.setText("1+2=3");
        radioButton2.setText("1+2=4");
    }
}
