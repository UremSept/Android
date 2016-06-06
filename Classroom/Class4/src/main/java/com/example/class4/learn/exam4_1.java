package com.example.class4.learn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.class4.R;

public class exam4_1 extends AppCompatActivity {
    private Button button;
    private TextView textView;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam4_1);
        button = (Button) findViewById(R.id.mybut_1);
        textView = (TextView) findViewById(R.id.mytext_1);
        editText = (EditText) findViewById(R.id.myedit_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("您的手机号是："+editText.getText().toString());
            }
        });
    }
}
