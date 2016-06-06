package com.example.hj.classroom.com.example.houjie.learn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.hj.classroom.R;

public class exam3_6 extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam3_6);
        imageView = (ImageView) findViewById(R.id.exam3_6_img2);
        imageView.setImageResource(R.mipmap.a1);
    }
}
