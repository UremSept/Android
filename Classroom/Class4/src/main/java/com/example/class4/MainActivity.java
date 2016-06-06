package com.example.class4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.class4.learn.exam4_1;
import com.example.class4.learn.exam4_2;
import com.example.class4.learn.exam4_3;
import com.example.class4.learn.exam4_4;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;

    private Button button10;
    private Button button11;
    private Button button12;
    private Button button13;
    private Button button14;
    private Button button15;
    private Button button16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init() {
        button1 = (Button) findViewById(R.id.exam4_1);
        button1.setOnClickListener(this);
        button2 = (Button) findViewById(R.id.exam4_2);
        button2.setOnClickListener(this);
        button3 = (Button) findViewById(R.id.exam4_3);
        button3.setOnClickListener(this);
        button5 = (Button) findViewById(R.id.exam4_5);
        button5.setOnClickListener(this);
        button4 = (Button) findViewById(R.id.exam4_4);
        button4.setOnClickListener(this);
        button6 = (Button) findViewById(R.id.exam4_6);
        button6.setOnClickListener(this);
        button7 = (Button) findViewById(R.id.exam4_7);
        button7.setOnClickListener(this);
        button8 = (Button) findViewById(R.id.exam4_8);
        button8.setOnClickListener(this);
        button9 = (Button) findViewById(R.id.exam4_9);
        button9.setOnClickListener(this);
        button10 = (Button) findViewById(R.id.exam4_10);
        button10.setOnClickListener(this);
        button11 = (Button) findViewById(R.id.exam4_11);
        button11.setOnClickListener(this);
        button12 = (Button) findViewById(R.id.exam4_12);
        button12.setOnClickListener(this);
        button13 = (Button) findViewById(R.id.exam4_13);
        button13.setOnClickListener(this);
        button14 = (Button) findViewById(R.id.exam4_14);
        button14.setOnClickListener(this);
        button15 = (Button) findViewById(R.id.exam4_15);
        button15.setOnClickListener(this);
        button16 = (Button) findViewById(R.id.exam4_16);
        button16.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.exam4_1:exam4_1();break;
            case R.id.exam4_2:exam4_2();break;
            case R.id.exam4_3:exam4_3();break;
            case R.id.exam4_4:exam4_4();break;
            case R.id.exam4_5:exam4_5();break;
            case R.id.exam4_6:exam4_6();break;
            case R.id.exam4_7:exam4_7();break;
            case R.id.exam4_8:exam4_8();break;
            case R.id.exam4_9:exam4_9();break;
            case R.id.exam4_10:exam4_10();break;
            case R.id.exam4_12:exam4_12();break;
            case R.id.exam4_13:exam4_13();break;
            case R.id.exam4_14:exam4_14();break;
            case R.id.exam4_15:exam4_15();break;
            case R.id.exam4_16:exam4_16();break;
            case R.id.exam4_11:exam4_11();break;
            default:break;
        }
    }
    private  void exam4_1(){
        Intent intent = new Intent(MainActivity.this,exam4_1.class);
        startActivity(intent);
    }
    private  void exam4_2(){
        Intent intent = new Intent(MainActivity.this,exam4_2.class);
        startActivity(intent);
    }
    private  void exam4_3(){
        Intent intent = new Intent(MainActivity.this,exam4_3.class);
        startActivity(intent);
    }
    private  void exam4_4(){
        Intent intent = new Intent(MainActivity.this,exam4_4.class);
        startActivity(intent);
    }
    private  void exam4_5(){
        Intent intent = new Intent(MainActivity.this,exam3_5.class);
        startActivity(intent);
    }    private  void exam4_6(){
        Intent intent = new Intent(MainActivity.this,exam3_6.class);
        startActivity(intent);
    }    private  void exam4_7(){
        Intent intent = new Intent(MainActivity.this,exam3_7.class);
        startActivity(intent);
    }
    private  void exam4_8(){
        Intent intent = new Intent(MainActivity.this,exam3_8.class);
        startActivity(intent);
    }
    private  void exam4_9(){
        Intent intent = new Intent(MainActivity.this,exam3_9.class);
        startActivity(intent);
    }
    private  void exam4_10(){
        Intent intent = new Intent(MainActivity.this,exam3_10.class);
        startActivity(intent);
    }
    private  void exam4_11(){
        Intent intent = new Intent(MainActivity.this,exam3_11.class);
        startActivity(intent);
    }
    private  void exam4_12(){
        Intent intent = new Intent(MainActivity.this,exam3_12.class);
        startActivity(intent);
    }
    private  void exam4_13(){
        Intent intent = new Intent(MainActivity.this,exam3_13.class);
        startActivity(intent);
    }
    private  void exam4_14(){
        Intent intent = new Intent(MainActivity.this,exam3_14.class);
        startActivity(intent);
    }
    private  void exam4_15(){
        Intent intent = new Intent(MainActivity.this,exam3_15.class);
        startActivity(intent);
    }    private  void exam4_16(){
        Intent intent = new Intent(MainActivity.this,exam3_16.class);
        startActivity(intent);
    }


}


