package com.example.class4.learn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.class4.R;

public class exam4_4 extends AppCompatActivity {
    private TextView textView;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam4_4);
        textView = (TextView) findViewById(R.id.text_4);
        editText = (EditText) findViewById(R.id.edit_4);
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                switch (event.getAction()){
                    case KeyEvent.ACTION_UP:
                        int a = Integer.parseInt(editText.getText().toString());
                        if(a>0&&a<200){
                            textView.setText("您输入的年龄是："+a+"符合要求");
                        }else {
                            textView.setText("您输入的年龄是："+a+"不符合要求");

                        }
                }
                return false;
            }
        });
    }
}
