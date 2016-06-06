package com.example.class4.learn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.class4.R;

public class exam4_3 extends AppCompatActivity {
    private TextView textView1,textView2;
    private EditText editText1,editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam3);
        textView1 = (TextView) findViewById(R.id.text_3);
        textView2 = (TextView) findViewById(R.id.text1_3);
        editText1 = (EditText) findViewById(R.id.edit_3);
        editText2 = (EditText) findViewById(R.id.edit1_3);
        editText1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(editText1.hasFocus()){
                    textView2.setText("第一个文本框组件获得了焦点");
                }else {
                    if(editText1.getText().toString().matches("\\w+@\\w+\\.\\w+")){
                        textView2.setText("您输入的邮箱是："+editText1.getText().toString()+"符合要求");
                    }else {
                        textView2.setText("您输入的邮箱是："+editText1.getText().toString()+"不符合要求");
                    }
                }
            }
        });
    }
}
