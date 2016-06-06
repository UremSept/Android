package com.licrafter.happylife.model.impl;

import com.licrafter.happylife.api.HappyLife;
import com.licrafter.happylife.data.CategoryData;
import com.licrafter.happylife.data.entity.BaseCategoryData;
import com.licrafter.happylife.model.ICategoriesModel;

import rx.Observable;

/**
 * Created by Administrator on 2016/2/26.
 */
public class CategoriesModel implements ICategoriesModel {

    private static CategoriesModel instance;

    public static CategoriesModel getInstance() {
        if (instance == null) {
            instance = new CategoriesModel();
        }
        return instance;
    }

    /**
     * 获取所有的商品种类
     */
    @Override
    public Observable<CategoryData> getAllCategories() {

        return HappyLife.getInstance().getHappyService().getAllCategory();
    }

    /**
     * 创建一个商品种类
     *
     * @param categoryData
     */
    @Override
    public Observable<Void> createCategory(BaseCategoryData categoryData) {

        return HappyLife.getInstance().getHappyService().createCategory(categoryData);
    }
}
