package com.edu.lzjtu.shopping.model;

/**
 * Created by houjie on 2016/5/4.
 */
public class Shop {
    private int id;
    private String name;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    private int image;
    public Shop(){

    }
    public Shop(String name, int image, String say, int price) {
        this.name = name;
        this.image = image;
        this.say = say;
        this.price = price;
    }

    private String say;
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getSay() {
        return say;
    }

    public void setSay(String say) {
        this.say = say;
    }
}
