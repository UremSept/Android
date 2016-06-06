package com.example.hj.phonewatchchat;


/**
 * Created by houjie on 2016/4/8.
 */
public class WearType implements Type {
    private int image;

    public WearType(int image, String wearText) {
        this.image = image;
        this.wearText = wearText;
    }

    public int getImage() {

        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getWearText() {
        return wearText;
    }

    public void setWearText(String wearText) {
        this.wearText = wearText;
    }

    private String wearText;
    @Override
    public int getType() {
        return Type.wearType;
    }
}
