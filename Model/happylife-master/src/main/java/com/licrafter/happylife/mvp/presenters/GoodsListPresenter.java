package com.licrafter.happylife.mvp.presenters;

import com.licrafter.happylife.api.HappyLife;
import com.licrafter.happylife.data.BannersData;
import com.licrafter.happylife.data.GoodsData;
import com.licrafter.happylife.data.ItemData;
import com.licrafter.happylife.data.entity.BaseBannerData;
import com.licrafter.happylife.data.entity.BaseGoodsData;
import com.licrafter.happylife.model.impl.BannersModel;
import com.licrafter.happylife.model.impl.GoodsModel;
import com.licrafter.happylife.mvp.views.GoodsListView;

import java.util.ArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/2/18.
 */
public class GoodsListPresenter extends BasePresenter<GoodsListView> {

    public GoodsListPresenter() {

    }

    /**
     * 请求商品列表
     */
    public void getGoods(String category,final int page, int size) {

        this.compositeSubscription.add(GoodsModel.getInstance().getGoodsByType(category, page, size)
                .map(new Func1<GoodsData, ArrayList<BaseGoodsData>>() {

                    @Override
                    public ArrayList<BaseGoodsData> call(GoodsData goodsData) {
                        return goodsData.getResults();
                    }
                })
                .map(new Func1<ArrayList<BaseGoodsData>, ArrayList<ItemData>>() {
                    @Override
                    public ArrayList<ItemData> call(ArrayList<BaseGoodsData> baseGoodsDatas) {
                        return goodsToItemData(baseGoodsDatas);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<ArrayList<ItemData>>() {
                    @Override
                    public void onCompleted() {
                        if (GoodsListPresenter.this.compositeSubscription != null) {
                            GoodsListPresenter.this.compositeSubscription.remove(this);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        android.util.Log.d("ljx", e.toString());
                        getMvpView().onFailure(e);
                    }

                    @Override
                    public void onNext(ArrayList<ItemData> datas) {
                        getMvpView().onGetGoodsSuccess(datas, false);
                    }
                }));
    }

    /**
     * 请求广告banner
     */
    public void getBanner() {
        this.compositeSubscription.add(BannersModel.getInstance().getAllBanners()
                .map(new Func1<BannersData, ItemData>() {
                    @Override
                    public ItemData call(BannersData bannersData) {
                        return new ItemData(ItemData.TYPE_BANNER, bannersData.getResults());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<ItemData>() {
                    @Override
                    public void onCompleted() {
                        if (GoodsListPresenter.this.compositeSubscription != null) {
                            GoodsListPresenter.this.compositeSubscription.remove(this);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ItemData itemData) {
                        getMvpView().onGetBannerSuccess(itemData);
                    }
                }));
    }

    private ArrayList<ItemData> goodsToItemData(ArrayList<BaseGoodsData> goods) {
        ArrayList<ItemData> itemDatas = new ArrayList<>();
        for (BaseGoodsData data : goods) {
            itemDatas.add(new ItemData(ItemData.TYPE_GOODS, data));
        }
        return itemDatas;
    }
}
