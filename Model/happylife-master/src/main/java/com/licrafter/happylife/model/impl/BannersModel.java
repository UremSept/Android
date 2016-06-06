package com.licrafter.happylife.model.impl;

import com.licrafter.happylife.api.HappyLife;
import com.licrafter.happylife.api.Query;
import com.licrafter.happylife.data.BannersData;
import com.licrafter.happylife.data.entity.BaseBannerData;
import com.licrafter.happylife.model.IBannersModel;

import java.util.ArrayList;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016/2/19.
 */
public class BannersModel implements IBannersModel {

    private static BannersModel instance;

    public static BannersModel getInstance() {
        if (instance == null) {
            instance = new BannersModel();
        }
        return instance;
    }

    /**
     * 查询所有的广告banner
     *
     * @return
     */
    @Override
    public Observable<BannersData> getAllBanners() {
        return HappyLife.getInstance().getHappyService().getAllBanners(Query.CREATED_ASC);
    }
}
