package com.edu.lzjtu.shopping.model;

/**
 * Created by houjie on 2016/5/4.
 */
public class ShoppingCart {
    private int id;
    private int shopId;
    private int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
