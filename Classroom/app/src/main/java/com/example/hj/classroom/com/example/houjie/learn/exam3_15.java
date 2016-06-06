package com.example.hj.classroom.com.example.houjie.learn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.LinearLayout;

import android.widget.TextView;

public class exam3_15 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
LinearLayout layout =new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        TextView textView =new TextView(this);
        textView.setLayoutParams(layoutParams);
        textView.setText("更多精彩尽在按钮中");
        textView.setTextSize(25);
        layout.addView(textView,layoutParams);
        Button button =new Button(this);
        button.setLayoutParams(layoutParams);
        button.setText("去看看");
        button.setTextSize(20);
        layout.addView(button,layoutParams);
        addContentView(layout,layoutParams);
    }
}
