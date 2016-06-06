package com.example.hj.classroom.com.example.houjie.learn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.hj.classroom.R;

public class exam3_13 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam3_13);
        RelativeLayout r1 = (RelativeLayout) findViewById(R.id.exam3_13_RelativeLayout);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.FILL_PARENT
        );
        params.addRule(RelativeLayout.BELOW,R.id.exam3_13_button1);
        params.addRule(RelativeLayout.RIGHT_OF,R.id.exam3_12_image1);
        EditText editText =new EditText(this);
        r1.addView(editText);
    }
}
