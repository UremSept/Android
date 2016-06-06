package com.edu.lzjtu.music.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.edu.lzjtu.music.model.MusicFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by houjie on 2016/4/21.
 */
public class MusicDB {
    public static final String DB_NAME = "music_db";
    public static final int VRESION = 1;
    private static MusicDB musicDB;
    private SQLiteDatabase db;
    private MusicDB(Context context){
        MusicDatabaseHelper musicDatabaseHelper = new MusicDatabaseHelper(context,DB_NAME,null,VRESION);
        db = musicDatabaseHelper.getWritableDatabase();
    }
    public synchronized static MusicDB getInstance(Context context){
        if (musicDB ==null){
            musicDB = new MusicDB(context);
        }
        return musicDB;
    }
    public void saveMusicFile(MusicFile musicFile){
        if(musicFile!=null){
            ContentValues values = new ContentValues();
            values.put("music_name",musicFile.getMusicName());
            values.put("music_path",musicFile.getMusicAbsolutePath());
            values.put("music_author",musicFile.getMusicAuthor());
            db.insert("music",null,values);
        }
    }
    public void deleteMusicAll(){
       db.delete("music",null,null);
    }
    public List<MusicFile> loadMusic(){
        List<MusicFile> list = new ArrayList<>();
        Cursor cursor = db.query("music",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                MusicFile musicFile =new MusicFile();
                musicFile.setId(cursor.getInt(cursor.getColumnIndex("id")));
                musicFile.setMusicName(cursor.getString(cursor.getColumnIndex("music_name")));
                musicFile.setMusicAbsolutePath(cursor.getString(cursor.getColumnIndex("music_path")));
                musicFile.setMusicAuthor(cursor.getString(cursor.getColumnIndex("music_author")));
                list.add(musicFile);
            }while (cursor.moveToNext());
        }
        return list;
    }
}
