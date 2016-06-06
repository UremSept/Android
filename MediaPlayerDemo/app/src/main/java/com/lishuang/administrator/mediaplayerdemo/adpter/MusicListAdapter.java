package com.lishuang.administrator.mediaplayerdemo.adpter;

import android.media.MediaMetadataRetriever;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lishuang.administrator.mediaplayerdemo.R;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

/**
 * Created by Administrator on 2015/9/10.
 */
public class MusicListAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    protected List<File> mFiles;

    public MusicListAdapter(LayoutInflater mInflater, List<File> files) {
        this.mInflater = mInflater;
        mFiles = files;
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_music, null);
            viewHolder.textviewMusicName = (TextView) convertView.findViewById(R.id.textview_music_name);
            viewHolder.textviewAuthor = (TextView) convertView.findViewById(R.id.textview_author);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.textviewMusicName.setText(mFiles.get(position).getName());

        //通过这个类来获取Mmp3文件的内容，包括作者，图片等…我们这里只获取作者，没有作者的我们定义为“<未知>”
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        Log.d("data", "" + mFiles.get(position).getAbsolutePath());
        mmr.setDataSource(mFiles.get(position).getAbsolutePath());
        // 调用extractMetadata()方法，通过参数来货去不同的信息，具体参数可以看API
        String author = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
        if (author != null) {
            viewHolder.textviewAuthor.setText(author);
        } else {
            viewHolder.textviewAuthor.setText("<未知>");
        }
        return convertView;
    }

    class ViewHolder {
        TextView textviewMusicName;
        TextView textviewAuthor;
    }

}
