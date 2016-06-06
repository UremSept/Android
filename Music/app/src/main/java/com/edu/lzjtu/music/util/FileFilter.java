package com.edu.lzjtu.music.util;

import android.util.Log;

import java.io.File;

/**
 * Created by houjie on 2016/4/21.
 */
public abstract class FileFilter{

    private String scaningPath;

    public String getScaningPath() {
        return scaningPath;
    }

    private String startPath;
    private String endWithString;
    public void init(String path, String endWith, Object object){
        this.startPath = path;
        this.scaningPath = path;
        this.endWithString = endWith;
    }
    private void filter(String path){
        File dir = new File(path);
        //Log.i(path,"-------------------------");
        if(dir.isFile()){
            Log.i("提示","这是一个文件无法遍历");
            return;
        }
        if (dir.isDirectory()){

            scaningPath = dir.getAbsolutePath();
            File[] list =dir .listFiles();
            if(list==null)return;
            for(File file:list){
                if(file.getName().endsWith(endWithString)){
                    findDo(file);
                }
                if(file.isDirectory()){
                    filter(file.getAbsolutePath());
                }
            }
        }else {
            Log.i("提示","无法识别");
        }
    }

    public  void start(){
        filter(startPath);
    }

    public abstract void findDo(File file);
}
