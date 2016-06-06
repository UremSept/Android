package com.licrafter.happylife.mvp.views;

import com.licrafter.happylife.data.entity.BaseCategoryData;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/2/26.
 */
public interface CategoryListView extends MvpView {

    void onGetCategorySuccess(ArrayList<BaseCategoryData> categoryDatas);
}
