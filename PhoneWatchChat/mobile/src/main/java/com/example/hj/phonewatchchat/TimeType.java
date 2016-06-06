package com.example.hj.phonewatchchat;

/**
 * Created by houjie on 2016/4/8.
 */
public class TimeType implements Type{
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int getType() {
        return Type.timeType;
    }
}
