package com.licrafter.happylife.mvp.presenters;

import com.licrafter.happylife.mvp.views.MvpView;

import java.lang.ref.WeakReference;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Administrator on 2016/2/18.
 */
public class BasePresenter<T extends MvpView> implements Presenter<T> {

    private WeakReference<T> mvpView;
    public CompositeSubscription compositeSubscription;

    @Override
    public void attachView(T mvpView) {
        this.mvpView = new WeakReference<T>(mvpView);
        this.compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void detachView() {
        this.mvpView.clear();
        this.compositeSubscription.unsubscribe();
        this.compositeSubscription = null;
    }

    public boolean isViewAttached() {
        return mvpView != null;
    }

    public T getMvpView() {
        return mvpView.get();
    }
}
