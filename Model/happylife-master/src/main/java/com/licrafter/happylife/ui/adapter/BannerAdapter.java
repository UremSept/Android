package com.licrafter.happylife.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.licrafter.happylife.R;
import com.licrafter.happylife.data.entity.BaseBannerData;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/2/24.
 */
public class BannerAdapter extends PagerAdapter {

    private ArrayList<BaseBannerData> bannerDatas;
    private Context context;

    public BannerAdapter(Context context, ArrayList<BaseBannerData> bannerDatas) {
        this.bannerDatas = bannerDatas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return bannerDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View bannerLayout = LayoutInflater.from(context).inflate(R.layout.item_image_banner, container, false);
        ImageView imageView = (ImageView) bannerLayout.findViewById(R.id.bannerImageView);
        Glide.with(context).load("http://img3.imgtn.bdimg.com/it/u=2886930880,2337293511&fm=21&gp=0.jpg")
                .into(imageView);
        container.addView(bannerLayout);
        return bannerLayout;
    }
}
