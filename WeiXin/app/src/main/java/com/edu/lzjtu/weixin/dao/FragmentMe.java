package com.edu.lzjtu.weixin.dao;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.edu.lzjtu.weixin.R;

/**
 * Created by houjie on 2016/4/16.
 */
public class FragmentMe extends Fragment{
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.onlyimage,null);
        imageView = (ImageView) view.findViewById(R.id.mFragmentWeiXin);
        imageView.setImageResource(R.mipmap.ic_launcher);
        return view;
    }
}
