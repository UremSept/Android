package com.edu.lzjtu.shopping.model;

import java.util.List;

/**
 * Created by houjie on 2016/5/4.
 */

public class User {
    private int id;
    private String name;
    private String password;
    private List<ShoppingCart> list;

    public List<ShoppingCart> getList() {
        return list;
    }

    public void setList(List<ShoppingCart> list) {
        this.list = list;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
