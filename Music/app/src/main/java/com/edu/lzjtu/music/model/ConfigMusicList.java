package com.edu.lzjtu.music.model;

import java.io.File;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.util.Log;

import com.edu.lzjtu.music.db.MusicDB;
import com.edu.lzjtu.music.util.FileFilter;

/**
 * Created by houjie on 2016/4/18.
 */
public class ConfigMusicList extends FileFilter {
    private MusicDB musicDB;

    public ConfigMusicList( MusicDB musicDB) {
        this.musicDB = musicDB;
    }


    @Override
    public void findDo(File file) {
        Log.d("在搜索","____________________________");
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        String absolutePath = file.getAbsolutePath();

        mediaMetadataRetriever.setDataSource(absolutePath);
        String name = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        String s =mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        Log.e(file.length()+"","----------------------------filelength----");
        if(Integer.parseInt(s)>60000&&file.length()>1024*1024){
            String auther = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);


            if (auther==null){
                auther ="<未知>";
            }
            musicDB.saveMusicFile(new MusicFile(name,absolutePath,auther));
        }

    }

}
