package com.edu.lzjtu.music.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.edu.lzjtu.music.R;
import com.edu.lzjtu.music.activity.Activity_Scan_music;

/**
 * Created by houjie on 2016/4/20.
 */
@SuppressLint("ValidFragment")
public class NoMusicFragment extends Fragment{
    private Button button;
    private Context context;
    public NoMusicFragment(){

    }
    public NoMusicFragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.layout_scan_music_fragment,null);
        button = (Button) view.findViewById(R.id.scan_music);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(context, Activity_Scan_music.class);
                startActivity(intent1);

            }
        });
        return view;
    }
}
