package com.example.hj.phonewatchchat;


/**
 * Created by houjie on 2016/4/8.
 */
public class PhoneType implements Type {
    private int image;
    private String phoneText;

    public PhoneType(int image, String phoneText) {
        this.image = image;
        this.phoneText = phoneText;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getPhoneText() {
        return phoneText;
    }

    public void setPhoneText(String phoneText) {
        this.phoneText = phoneText;
    }

    @Override
    public int getType() {
        return Type.phoneType;
    }
}
