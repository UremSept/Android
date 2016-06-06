package com.licrafter.happylife.model;

import com.licrafter.happylife.data.GoodsData;

import rx.Observable;

/**
 * Created by Administrator on 2016/2/17.
 */
public interface IGoodsModel {

    /**
     * 分页查询商品列表，type是种类，page是页码，size是每页数量
     *
     * @param type
     * @param page
     * @param size
     * @return
     */
    Observable<GoodsData> getGoodsByType(String type, int page, int size);

    /**
     * 分页查询所有商品列表 page页码 size每页数量
     *
     * @param page
     * @param size
     * @return
     */
    Observable<GoodsData> getAllGoods(int page, int size);
}
