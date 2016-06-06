package com.edu.lzjtu.weixin.dao;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.edu.lzjtu.weixin.R;
import com.edu.lzjtu.weixin.adapter.WeiXinAdapter;
import com.edu.lzjtu.weixin.bean.ChatMessage;

import java.util.List;

/**
 * Created by houjie on 2016/4/16.
 */
public class FragmentWeiXin extends Fragment{
    private ListView mListView;
    private List<ChatMessage> mList;
    private Context mContext;

    public FragmentWeiXin(List<ChatMessage> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_menu_weixin,null);
        mListView = (ListView) view.findViewById(R.id.mWeiXinListView);
        WeiXinAdapter weiXinAdapter = new WeiXinAdapter(mList,mContext);
        mListView.setAdapter(weiXinAdapter);
        return view;
    }
}
