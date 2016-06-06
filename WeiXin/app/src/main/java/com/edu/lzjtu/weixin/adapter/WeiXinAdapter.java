package com.edu.lzjtu.weixin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.edu.lzjtu.weixin.R;
import com.edu.lzjtu.weixin.bean.ChatMessage;
import com.edu.lzjtu.weixin.bean.TabFragment;

import java.util.List;

/**
 * Created by houjie on 2016/4/16.
 */
public class WeiXinAdapter extends BaseAdapter{
    private List<ChatMessage> mList;
    private Context mContent;

    public WeiXinAdapter(List<ChatMessage> mList, Context mContent) {
        this.mList = mList;
        this.mContent = mContent;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContent).inflate(R.layout.layout_weixin_chat,null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.mWeiXinListViewImageview);
            viewHolder.nickName = (TextView) convertView.findViewById(R.id.mWeiXinListViewTextViewNickName);
            viewHolder.briefMessage= (TextView) convertView.findViewById(R.id.mWeiXinListViewTextViewBriefMessage);
            viewHolder.time = (TextView) convertView.findViewById(R.id.mWeiXinListViewTextViewTime);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ChatMessage chatMessage = mList.get(position);
        viewHolder.imageView.setImageResource(chatMessage.getImage());
        viewHolder.nickName.setText(chatMessage.getNickName());
        viewHolder.briefMessage.setText(chatMessage.getBriefMessage());
        viewHolder.time.setText(chatMessage.getTime());

        return convertView;
    }
    class ViewHolder{
        ImageView imageView;
        TextView nickName,briefMessage,time;
    }
}
