package com.licrafter.happylife.mvp.presenters;

import com.licrafter.happylife.mvp.views.MvpView;

/**
 * Created by Administrator on 2016/2/18.
 */
public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}
