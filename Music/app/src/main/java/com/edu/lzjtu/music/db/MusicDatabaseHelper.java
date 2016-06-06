package com.edu.lzjtu.music.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by houjie on 2016/4/20.
 */

public class MusicDatabaseHelper extends SQLiteOpenHelper{
    private Context mContent;
    public static final String CREATE_MUSIC = "create table music("
            +"id integer primary key autoincrement, "
            +"music_name text, "
            +"music_path text, "
            +"music_author text)";
    public MusicDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContent = context;

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MUSIC);
        Toast.makeText(mContent,"成功创建数据库",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
