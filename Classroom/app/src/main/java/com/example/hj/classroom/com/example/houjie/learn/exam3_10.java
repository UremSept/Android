package com.example.hj.classroom.com.example.houjie.learn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.hj.classroom.R;

import java.util.ArrayList;
import java.util.List;

public class exam3_10 extends AppCompatActivity {
    private Spinner spinner1;
    private Spinner spinner2;
    private ArrayAdapter<CharSequence> arrayAdapter1;
    private ArrayAdapter<CharSequence> arrayAdapter2;
    private List<CharSequence> mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam3_10);
        spinner1 = (Spinner) findViewById(R.id.exam3_10_Spinner2);
        spinner2 = (Spinner) findViewById(R.id.exam3_10_Spinner3);
        spinner1.setPrompt("请选择你的专业");
        spinner2.setPrompt("请选择你的班级");
        arrayAdapter1 =ArrayAdapter.createFromResource(this,R.array.eduzy,android.R.layout.simple_spinner_item);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(arrayAdapter1);
        mList =new ArrayList<CharSequence>();
        mList.add("通信12-2");
        mList.add("信工12-2");
        mList.add("电信12-2");
        mList.add("机电12-2");
        arrayAdapter2 =new ArrayAdapter<CharSequence>(this,android.R.layout.simple_spinner_item,mList);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(arrayAdapter2);



    }
}
