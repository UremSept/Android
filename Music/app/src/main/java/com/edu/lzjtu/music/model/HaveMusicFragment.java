package com.edu.lzjtu.music.model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.edu.lzjtu.music.MainActivity;
import com.edu.lzjtu.music.R;
import com.edu.lzjtu.music.adapter.MusicListViewAdapter;
import com.edu.lzjtu.music.service.PlayMusicService;

import java.util.List;


/**
 * Created by houjie on 2016/4/21.
 */
@SuppressLint("ValidFragment")
public class HaveMusicFragment extends Fragment {
    private List<MusicFile> mList;
    private ListView mListView;
    private Context context;
    public HaveMusicFragment(){

    }
    public HaveMusicFragment(List<MusicFile> list, Context context) {
        this.mList = list;
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.layout_have_music_fragment,null);
        MusicListViewAdapter musicListViewAdapter = new MusicListViewAdapter(context,mList);
        mListView = (ListView) view.findViewById(R.id.music_listview);
        mListView.setAdapter(musicListViewAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent intent1 = new Intent(MainActivity.ACTIVITY_BROADCAST);
                    intent1.putExtra("type",ActivityServiceMessage.SEEK_TO);
                    intent1.putExtra("what",position);
                    context.sendBroadcast(intent1);
                MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();

                String absolutePath = mList.get(position).getMusicAbsolutePath();

                mediaMetadataRetriever.setDataSource(absolutePath);
                String auther = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
                String name = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);

                if (auther==null){
                    auther ="<未知>";
                }
                String s =mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);

                Log.e("---------",name+"--------"+auther+"----"+s);
            }
        });
        return view;
    }
}
