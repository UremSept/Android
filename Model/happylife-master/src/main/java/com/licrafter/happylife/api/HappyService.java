package com.licrafter.happylife.api;

import com.licrafter.happylife.data.BannersData;
import com.licrafter.happylife.data.CategoryData;
import com.licrafter.happylife.data.GoodsData;
import com.licrafter.happylife.data.entity.BaseBannerData;
import com.licrafter.happylife.data.entity.BaseCategoryData;
import com.licrafter.happylife.data.entity.BaseGoodsData;

import retrofit.http.*;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/2/15.
 */
public interface HappyService {
    String BASE_URL = "https://api.leancloud.cn/1.1/";

    @POST("classes/Goods")
    Observable<Void> createGoods(@Body BaseGoodsData goods);

    @POST("classes/Banner")
    Observable<Void> createBanner(@Body BaseBannerData banner);

    /**
     * 查询商品列表，按照创建时间排序
     *
     * @param order
     * @return
     */
    @GET("classes/Goods")
    Observable<GoodsData> getAllGoods(@Query("order") String order,@Query("skip") int skip,@Query("limit") int limit);

    /**
     * 根据category查询商品
     *
     * @param category
     * @return
     */
    @GET("classes/Goods")
    Observable<GoodsData> getGoodsByCategory(@Query("where") String category, @Query("order") String order,@Query("skip") int skip,@Query("limit") int limit);

    /**
     * 查询banner列表，按照创建时间排序
     *
     * @param order
     * @return
     */
    @GET("classes/Banner")
    Observable<BannersData> getAllBanners(@Query("order") String order);

    /**
     * 创建category
     *
     * @param categoryData
     * @return
     */
    @POST("classes/Category")
    Observable<Void> createCategory(@Body BaseCategoryData categoryData);

    /**
     * 获取所有的Category
     *
     * @return
     */
    @GET("classes/Category")
    Observable<CategoryData> getAllCategory();
}