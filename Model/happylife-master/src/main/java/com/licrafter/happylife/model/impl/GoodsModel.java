package com.licrafter.happylife.model.impl;

import com.licrafter.happylife.api.HappyLife;
import com.licrafter.happylife.api.Query;
import com.licrafter.happylife.data.GoodsData;
import com.licrafter.happylife.model.IGoodsModel;
import com.licrafter.happylife.util.Constants;

import rx.Observable;

/**
 * Created by Administrator on 2016/2/17.
 */
public class GoodsModel implements IGoodsModel {

    private static GoodsModel instance;

    public static GoodsModel getInstance() {
        if (instance == null) {
            instance = new GoodsModel();
        }
        return instance;
    }

    /**
     * 分页查询商品列表
     *
     * @param category 种类
     * @param page     页码
     * @param size     每页数量
     * @return
     */
    @Override
    public Observable<GoodsData> getGoodsByType(String category, int page, int size) {
        if (category.equals(Constants.TYPE_ALL)) {
            return getAllGoods(page, size);
        } else {
            return HappyLife.getInstance().getHappyService().getGoodsByCategory(Query.CateGoryQuery(category), Query.CREATED_ASC, (page - 1) * size, size);
        }
    }

    /**
     * 分页查询所有商品列表
     *
     * @param page 页码
     * @param size 每页数量
     * @return
     */
    @Override
    public Observable<GoodsData> getAllGoods(int page, int size) {

        return HappyLife.getInstance().getHappyService().getAllGoods("createdAt", (page - 1) * size, size);
    }
}
