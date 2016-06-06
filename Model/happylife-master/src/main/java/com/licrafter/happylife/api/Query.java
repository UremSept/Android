package com.licrafter.happylife.api;

/**
 * Created by Administrator on 2016/2/17.
 * leancloud的rest api查询条件的拼接好蛋碎
 */
public class Query {

    //按创建时间降序查询
    public static String CREATED_DESC = "-createdAt";

    //按创建时间升序查询
    public static String CREATED_ASC = "createdAt";

    //按照商品种类查询
    public static String CateGoryQuery(String category) {
        return "{\"category\":\"" + category + "\"}";
    }

}
