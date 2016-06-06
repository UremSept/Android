package com.example.class4.learn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.class4.R;

import java.io.IOException;

public class exam4_2 extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam4_2);
        imageView = (ImageView) findViewById(R.id.image_2);
        textView = (TextView) findViewById(R.id.text_2);
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                try {
                    clearWallpaper();
                    setWallpaper(imageView.getResources().openRawResource(R.drawable.hq));
                    textView.setText("设置成功");
                } catch (IOException e) {
                    e.printStackTrace();
                    textView.setText("设置没有成功");
                }
                return true;
            }
        });
    }
}
