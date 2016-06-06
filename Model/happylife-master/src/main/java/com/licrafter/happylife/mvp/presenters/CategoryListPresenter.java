package com.licrafter.happylife.mvp.presenters;

import com.licrafter.happylife.data.CategoryData;
import com.licrafter.happylife.data.entity.BaseCategoryData;
import com.licrafter.happylife.model.impl.CategoriesModel;
import com.licrafter.happylife.mvp.views.CategoryListView;

import java.util.ArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/2/26.
 */
public class CategoryListPresenter extends BasePresenter<CategoryListView> {

    public CategoryListPresenter() {

    }

    /**
     * 获取所有商品种类列表
     */
    public void getCategories() {
        this.compositeSubscription.add(CategoriesModel.getInstance().getAllCategories()
                .map(new Func1<CategoryData, ArrayList<BaseCategoryData>>() {
                    @Override
                    public ArrayList<BaseCategoryData> call(CategoryData categoryData) {
                        return categoryData.getResults();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<ArrayList<BaseCategoryData>>() {
                    @Override
                    public void onCompleted() {
                        if (CategoryListPresenter.this.compositeSubscription != null) {
                            CategoryListPresenter.this.compositeSubscription.remove(this);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        android.util.Log.d("ljx", e.toString());
                    }

                    @Override
                    public void onNext(ArrayList<BaseCategoryData> baseCategoryDatas) {
                        getMvpView().onGetCategorySuccess(baseCategoryDatas);
                    }
                }));
    }

    /**
     * c创建一个商品种类
     *
     * @param categoryData
     */
    public void createCategory(BaseCategoryData categoryData) {
        this.compositeSubscription.add(CategoriesModel.getInstance().createCategory(categoryData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {
                        if (CategoryListPresenter.this.compositeSubscription != null) {
                            CategoryListPresenter.this.compositeSubscription.remove(this);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        android.util.Log.d("ljx", e.toString());
                    }

                    @Override
                    public void onNext(Void aVoid) {
                        android.util.Log.d("ljx", "创建成功");
                    }
                }));
    }
}
