package com.licrafter.happylife.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.licrafter.happylife.R;
import com.licrafter.happylife.data.ItemData;
import com.licrafter.happylife.data.entity.BaseBannerData;
import com.licrafter.happylife.widget.BannerView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/2/19.
 */
public abstract class GoodsListAdapter extends RecyclerView.Adapter {

    protected LayoutInflater layoutInflater;
    protected Context context;
    protected ArrayList<ItemData> mDatas;
    private boolean hasNextPage;

    public GoodsListAdapter(Context context, ArrayList<ItemData> data) {
        this.context = context;
        this.mDatas = data;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ItemData.TYPE_FOOTER) {
            View footer = layoutInflater.inflate(R.layout.item_footer, parent, false);
            return new FooterViewHolder(footer);
        } else if (viewType == ItemData.TYPE_BANNER) {
            BannerView bannerView = new BannerView(context, null);
            return new BannerViewHolder(bannerView);
        } else {
            View goods = layoutInflater.inflate(R.layout.item_goods, parent, false);
            return new GoodsViewHolder(goods);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == mDatas.size()) {
            if (hasNextPage) {
                ((FooterViewHolder) holder).loadingProgress.setVisibility(View.VISIBLE);
                ((FooterViewHolder) holder).loadingTextView.setText(context.getResources().getText(R.string.goodsIsLoading));
            } else {
                ((FooterViewHolder) holder).loadingProgress.setVisibility(View.GONE);
                ((FooterViewHolder) holder).loadingTextView.setText(context.getResources().getText(R.string.noMoreGoods));
            }
        } else {
            if (mDatas.get(position).getType() == ItemData.TYPE_GOODS) {
                bindData(holder, position);
            } else {
                //广告banner
            }
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size() > 0 ? mDatas.size() + 1 : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position >= mDatas.size()) {
            return ItemData.TYPE_FOOTER;
        } else {
            if (mDatas.get(position).getType() == ItemData.TYPE_BANNER) {
                return ItemData.TYPE_BANNER;
            } else {
                return ItemData.TYPE_GOODS;
            }
        }
    }

    protected abstract void bindData(RecyclerView.ViewHolder holder, int position);


    public void setHasNextPage(boolean hasNextPage){
        this.hasNextPage = hasNextPage;
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.loadingProgress)
        ProgressBar loadingProgress;
        @Bind(R.id.loadingTextView)
        TextView loadingTextView;

        public FooterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {

        public BannerViewHolder(View itemView) {
            super(itemView);
            BannerView bannerView = (BannerView) itemView;
            ArrayList<BaseBannerData> bannerDatas = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                BaseBannerData bannerData = new BaseBannerData();
                bannerDatas.add(bannerData);
            }
            bannerView.init(bannerDatas);
        }
    }

    public class GoodsViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.goodsPicImageView)
        public ImageView goodsPicImageView;
        @Bind(R.id.goodsInfoTextView)
        public TextView goodsInfoTextView;
        @Bind(R.id.goodsPriceTextView)
        public TextView goodsPriceTextView;
        @Bind(R.id.goodsOldPriceTextView)
        public TextView goodsOldPriceTextView;

        public GoodsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
