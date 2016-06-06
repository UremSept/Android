package com.licrafter.happylife.model;

import com.licrafter.happylife.data.CategoryData;
import com.licrafter.happylife.data.entity.BaseCategoryData;

import rx.Observable;

/**
 * Created by Administrator on 2016/2/26.
 */
public interface ICategoriesModel {

    /**
     * 获取所有的Category商品种类
     */
    Observable<CategoryData> getAllCategories();

    /**
     * 创建一个Category
     *
     * @param categoryData
     */
    Observable<Void> createCategory(BaseCategoryData categoryData);
}
