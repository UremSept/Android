package com.edu.lzjtu.weixin.bean;

/**
 * Created by houjie on 2016/4/16.
 */
public class ChatMessage {
    private int image;
    private String nickName;

    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ChatMessage(int image, String nickName, String briefMessage, String time) {

        this.image = image;
        this.nickName = nickName;
        this.time = time;
        this.briefMessage = briefMessage;
    }

    private String briefMessage;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getBriefMessage() {
        return briefMessage;
    }

    public void setBriefMessage(String briefMessage) {
        this.briefMessage = briefMessage;
    }
}
