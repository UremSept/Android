package com.licrafter.happylife.mvp.views;

import com.licrafter.happylife.data.ItemData;

import java.util.List;

/**
 * Created by Administrator on 2016/2/18.
 */
public interface GoodsListView extends MvpView {

    /**
     * 获取商品列表
     *
     * @param goods   商品列表
     * @param refresh 是否刷新
     */
    void onGetGoodsSuccess(List<ItemData> goods, boolean refresh);

    /**
     * 获取首页的广告banner
     *
     * @param banner
     */
    void onGetBannerSuccess(ItemData banner);
}
