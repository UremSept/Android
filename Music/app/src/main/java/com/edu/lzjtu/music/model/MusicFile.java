package com.edu.lzjtu.music.model;

import java.io.Serializable;

/**
 * Created by houjie on 2016/4/18.
 */
public class MusicFile implements Serializable {
    private String musicName;
    private String musicAbsolutePath;
    private String musicAuthor;
    private int  id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MusicFile(String musicName, String musicAbsolutePath, String musicAuthor, int id) {
        this.musicName = musicName;
        this.musicAbsolutePath = musicAbsolutePath;
        this.musicAuthor = musicAuthor;
        this.id = id;
    }

    public MusicFile(String musicName, String musicAbsolutePath, String musicAuthor) {
        this.musicName = musicName;
        this.musicAbsolutePath = musicAbsolutePath;
        this.musicAuthor = musicAuthor;
    }

    public MusicFile(){

    }
    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getMusicAbsolutePath() {
        return musicAbsolutePath;
    }

    public void setMusicAbsolutePath(String musicAbsolutePath) {
        this.musicAbsolutePath = musicAbsolutePath;
    }

    public String getMusicAuthor() {
        return musicAuthor;
    }

    public void setMusicAuthor(String musicAuthor) {
        this.musicAuthor = musicAuthor;
    }
}
