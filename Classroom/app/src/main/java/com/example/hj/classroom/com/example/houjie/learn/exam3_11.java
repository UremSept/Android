package com.example.hj.classroom.com.example.houjie.learn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.hj.classroom.R;

public class exam3_11 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam3_11);
        Toast toast = Toast.makeText(this,"长时间显示的Toast信息提示框",Toast.LENGTH_LONG);
        toast.setGravity(BIND_AUTO_CREATE,5,10);
        toast.show();
    }
}
