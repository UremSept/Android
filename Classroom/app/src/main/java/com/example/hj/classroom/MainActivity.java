package com.example.hj.classroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hj.classroom.com.example.houjie.learn.exam3_1;
import com.example.hj.classroom.com.example.houjie.learn.exam3_10;
import com.example.hj.classroom.com.example.houjie.learn.exam3_11;
import com.example.hj.classroom.com.example.houjie.learn.exam3_12;
import com.example.hj.classroom.com.example.houjie.learn.exam3_13;
import com.example.hj.classroom.com.example.houjie.learn.exam3_14;
import com.example.hj.classroom.com.example.houjie.learn.exam3_15;
import com.example.hj.classroom.com.example.houjie.learn.exam3_16;
import com.example.hj.classroom.com.example.houjie.learn.exam3_17;
import com.example.hj.classroom.com.example.houjie.learn.exam3_18;
import com.example.hj.classroom.com.example.houjie.learn.exam3_19;
import com.example.hj.classroom.com.example.houjie.learn.exam3_2;
import com.example.hj.classroom.com.example.houjie.learn.exam3_20;
import com.example.hj.classroom.com.example.houjie.learn.exam3_3;
import com.example.hj.classroom.com.example.houjie.learn.exam3_4;
import com.example.hj.classroom.com.example.houjie.learn.exam3_5;
import com.example.hj.classroom.com.example.houjie.learn.exam3_6;
import com.example.hj.classroom.com.example.houjie.learn.exam3_7;
import com.example.hj.classroom.com.example.houjie.learn.exam3_8;
import com.example.hj.classroom.com.example.houjie.learn.exam3_9;

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
    private Button button17;
    private Button button18;
    private Button button19;

    private Button button20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
        private void init() {
            button1 = (Button) findViewById(R.id.exam3_1);
            button1.setOnClickListener(this);
            button2 = (Button) findViewById(R.id.exam3_2);
            button2.setOnClickListener(this);
            button3 = (Button) findViewById(R.id.exam3_3);
            button3.setOnClickListener(this);
            button5 = (Button) findViewById(R.id.exam3_5);
            button5.setOnClickListener(this);
            button4 = (Button) findViewById(R.id.exam3_4);
            button4.setOnClickListener(this);
            button6 = (Button) findViewById(R.id.exam3_6);
            button6.setOnClickListener(this);
            button7 = (Button) findViewById(R.id.exam3_7);
            button7.setOnClickListener(this);
            button8 = (Button) findViewById(R.id.exam3_8);
            button8.setOnClickListener(this);
            button9 = (Button) findViewById(R.id.exam3_9);
            button9.setOnClickListener(this);
            button10 = (Button) findViewById(R.id.exam3_10);
            button10.setOnClickListener(this);
            button11 = (Button) findViewById(R.id.exam3_11);
            button11.setOnClickListener(this);
            button12 = (Button) findViewById(R.id.exam3_12);
            button12.setOnClickListener(this);
            button13 = (Button) findViewById(R.id.exam3_13);
            button13.setOnClickListener(this);
            button14 = (Button) findViewById(R.id.exam3_14);
            button14.setOnClickListener(this);
            button15 = (Button) findViewById(R.id.exam3_15);
            button15.setOnClickListener(this);
            button16 = (Button) findViewById(R.id.exam3_16);
            button16.setOnClickListener(this);
            button17 = (Button) findViewById(R.id.exam3_17);
            button17.setOnClickListener(this);
            button18 = (Button) findViewById(R.id.exam3_18);
            button18.setOnClickListener(this);
            button19 = (Button) findViewById(R.id.exam3_19);
            button19.setOnClickListener(this);
            button20 = (Button) findViewById(R.id.exam3_20);
            button20.setOnClickListener(this);



        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.exam3_1:exam3_1();break;
                case R.id.exam3_2:exam3_2();break;
                case R.id.exam3_3:exam3_3();break;
                case R.id.exam3_4:exam3_4();break;
                case R.id.exam3_5:exam3_5();break;
                case R.id.exam3_6:exam3_6();break;
                case R.id.exam3_7:exam3_7();break;
                case R.id.exam3_8:exam3_8();break;
                case R.id.exam3_9:exam3_9();break;
                case R.id.exam3_10:exam3_10();break;
                case R.id.exam3_12:exam3_12();break;
                case R.id.exam3_13:exam3_13();break;
                case R.id.exam3_14:exam3_14();break;
                case R.id.exam3_15:exam3_15();break;
                case R.id.exam3_16:exam3_16();break;
                case R.id.exam3_17:exam3_17();break;
                case R.id.exam3_18:exam3_18();break;
                case R.id.exam3_19:exam3_19();break;
                case R.id.exam3_20:exam3_20();break;
                case R.id.exam3_11:exam3_11();break;
                default:break;
            }
        }
        private  void exam3_1(){
            Intent intent = new Intent(MainActivity.this,exam3_1.class);
            startActivity(intent);
        }
        private  void exam3_2(){
            Intent intent = new Intent(MainActivity.this,exam3_2.class);
            startActivity(intent);
        }
        private  void exam3_3(){
            Intent intent = new Intent(MainActivity.this,exam3_3.class);
            startActivity(intent);
        }
        private  void exam3_4(){
            Intent intent = new Intent(MainActivity.this,exam3_4.class);
            startActivity(intent);
        }
        private  void exam3_5(){
            Intent intent = new Intent(MainActivity.this,exam3_5.class);
            startActivity(intent);
        }    private  void exam3_6(){
            Intent intent = new Intent(MainActivity.this,exam3_6.class);
            startActivity(intent);
        }    private  void exam3_7(){
            Intent intent = new Intent(MainActivity.this,exam3_7.class);
            startActivity(intent);
        }
        private  void exam3_8(){
            Intent intent = new Intent(MainActivity.this,exam3_8.class);
            startActivity(intent);
        }
        private  void exam3_9(){
            Intent intent = new Intent(MainActivity.this,exam3_9.class);
            startActivity(intent);
        }
        private  void exam3_10(){
            Intent intent = new Intent(MainActivity.this,exam3_10.class);
            startActivity(intent);
        }
        private  void exam3_11(){
            Intent intent = new Intent(MainActivity.this,exam3_11.class);
            startActivity(intent);
        }
        private  void exam3_12(){
            Intent intent = new Intent(MainActivity.this,exam3_12.class);
            startActivity(intent);
        }
        private  void exam3_13(){
            Intent intent = new Intent(MainActivity.this,exam3_13.class);
            startActivity(intent);
        }
        private  void exam3_14(){
            Intent intent = new Intent(MainActivity.this,exam3_14.class);
            startActivity(intent);
        }
        private  void exam3_15(){
            Intent intent = new Intent(MainActivity.this,exam3_15.class);
            startActivity(intent);
        }    private  void exam3_16(){
            Intent intent = new Intent(MainActivity.this,exam3_16.class);
            startActivity(intent);
        }    private  void exam3_17(){
            Intent intent = new Intent(MainActivity.this,exam3_17.class);
            startActivity(intent);
        }
        private  void exam3_18(){
            Intent intent = new Intent(MainActivity.this,exam3_18.class);
            startActivity(intent);
        }
        private  void exam3_19(){
            Intent intent = new Intent(MainActivity.this,exam3_19.class);
            startActivity(intent);
        }
        private  void exam3_20(){
            Intent intent = new Intent(MainActivity.this,exam3_20.class);
            startActivity(intent);
        }


}

