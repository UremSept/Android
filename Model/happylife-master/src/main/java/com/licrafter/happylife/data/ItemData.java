package com.licrafter.happylife.data;

/**
 * Created by Administrator on 2016/2/18.
 */
public class ItemData<T> {
    private int type;
    private T data;

    public static final int TYPE_GOODS = 0x1;
    public static final int TYPE_BANNER = 0x2;
    public static final int TYPE_FOOTER = 0x3;

    public ItemData(int type, T data) {
        this.type = type;
        this.data = data;
    }

    public ItemData() {

    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
