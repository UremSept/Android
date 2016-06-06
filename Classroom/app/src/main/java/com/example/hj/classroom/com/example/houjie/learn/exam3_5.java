package com.example.hj.classroom.com.example.houjie.learn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.hj.classroom.R;

public class exam3_5 extends AppCompatActivity {
    private EditText myEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam3_5);
        myEdit = (EditText) findViewById(R.id.exam3_5_edit5);
        myEdit.setText("请输入你的姓名");
    }
}
