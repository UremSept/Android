package com.licrafter.happylife.widget;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.licrafter.happylife.R;
import com.licrafter.happylife.data.entity.BaseBannerData;
import com.licrafter.happylife.ui.adapter.BannerAdapter;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator;

/**
 * Created by Administrator on 2016/2/19.
 */
public class BannerView extends FrameLayout {

    ViewPager bannerViewPager;
    CircleIndicator bannerPagerIndicator;
    private ArrayList<BaseBannerData> bannerDatas = new ArrayList<>();
    private boolean bannerAdapterSets;
    private boolean isNotDragging = true;
    public static final int PAGER_DEFAULT_POSITION = 1000;
    public static final int BANNER_SLIDE_DELAY = 4 * 1000;
    private Handler handler = new Handler();


    public BannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.item_banner, this, true);
        bannerViewPager = (ViewPager) findViewById(R.id.bannerViewPager);
        bannerPagerIndicator = (CircleIndicator) findViewById(R.id.bannerPagerIndicator);

        bannerViewPager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        isNotDragging = false;
                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        isNotDragging = true;
                        break;
                }
                return false;
            }
        });

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec / 2), MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private Runnable bannerSlideRunnable = new Runnable() {
        @Override
        public void run() {
            if (bannerDatas.size() > 1 && isNotDragging) {
                int childCount = bannerViewPager.getChildCount();
                int currentItem = bannerViewPager.getCurrentItem();
                if (currentItem < childCount && currentItem >= 0) {
                    bannerViewPager.setCurrentItem(bannerViewPager.getCurrentItem() + 1);
                } else {
                    bannerViewPager.setCurrentItem(0, true);
                }
            }
            handler.postDelayed(bannerSlideRunnable, BANNER_SLIDE_DELAY);
        }
    };

    public void init(ArrayList<BaseBannerData> bannerDatas) {
        this.bannerDatas.clear();
        this.bannerDatas.addAll(bannerDatas);
        bannerViewPager.setAdapter(new BannerAdapter(getContext(), this.bannerDatas));
        bannerViewPager.setCurrentItem(0, false);
        bannerPagerIndicator.setViewPager(bannerViewPager);
        bannerAdapterSets = true;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        handler.removeCallbacks(bannerSlideRunnable);
        handler.postDelayed(bannerSlideRunnable, BANNER_SLIDE_DELAY);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        android.util.Log.d("ljx", "BannerView detached From Window!");
        handler.removeCallbacks(bannerSlideRunnable);
    }

}
