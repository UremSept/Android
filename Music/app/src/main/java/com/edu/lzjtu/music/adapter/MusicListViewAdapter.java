package com.edu.lzjtu.music.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.edu.lzjtu.music.R;
import com.edu.lzjtu.music.model.MusicFile;


import java.util.List;

/**
 * Created by houjie on 2016/4/17.
 */
public class MusicListViewAdapter extends BaseAdapter{
    private Context mContext;
    private List<MusicFile> mFiles;

    public MusicListViewAdapter(Context mContext, List<MusicFile> mFiles) {
        this.mContext = mContext;
        this.mFiles = mFiles;
    }

    @Override
    public int getCount() {
        return mFiles.size();
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
        if(convertView ==null){
            viewHolder =new ViewHolder();
            convertView =LayoutInflater.from(mContext).inflate(R.layout.music_listview,null);
            viewHolder.textViewMusicName = (TextView) convertView.findViewById(R.id.textview_music_name);
            viewHolder.textViewAuthor = (TextView) convertView.findViewById(R.id.textview_music_author);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textViewMusicName.setText(mFiles.get(position).getMusicName());
        viewHolder.textViewAuthor.setText(mFiles.get(position).getMusicAuthor());
        return convertView;
    }
    class ViewHolder{
        TextView textViewMusicName;
        TextView textViewAuthor;
    }
}
