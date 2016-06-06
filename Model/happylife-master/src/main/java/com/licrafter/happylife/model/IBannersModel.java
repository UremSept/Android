package com.licrafter.happylife.model;

import com.licrafter.happylife.data.BannersData;
import com.licrafter.happylife.data.entity.BaseBannerData;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by Administrator on 2016/2/19.
 */
public interface IBannersModel {

    /**
     * 查询所有的广告banner
     * @return
     */
    Observable<BannersData> getAllBanners();
}
