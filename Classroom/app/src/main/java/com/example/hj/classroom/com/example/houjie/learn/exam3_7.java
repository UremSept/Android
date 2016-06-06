package com.example.hj.classroom.com.example.houjie.learn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.hj.classroom.R;

public class exam3_7 extends AppCompatActivity {
private ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam3_7);
        imageButton = (ImageButton) findViewById(R.id.exam3_7_img1);
        imageButton.setImageResource(R.mipmap.a1);
    }
}
