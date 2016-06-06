package com.licrafter.happylife.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.licrafter.happylife.R;
import com.licrafter.happylife.base.BaseFragment;
import com.licrafter.happylife.data.ItemData;
import com.licrafter.happylife.data.entity.BaseGoodsData;
import com.licrafter.happylife.listener.OnScrollBottomListener;
import com.licrafter.happylife.mvp.presenters.GoodsListPresenter;
import com.licrafter.happylife.mvp.views.GoodsListView;
import com.licrafter.happylife.ui.adapter.GoodsListAdapter;
import com.licrafter.happylife.util.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/2/2.
 */
public class GodsListFragment extends BaseFragment implements GoodsListView {

    private static final int PAGE_SIZE = 6;

    @Bind(R.id.goodsRecyclerView)
    RecyclerView goodsRecyclerView;
    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    GoodsListPresenter goodsListPresenter;
    private ArrayList<ItemData> goodsList;
    private GoodsAdapter goodsAdapter;
    private String category;

    private String title;
    private int pageIndex = 1;
    private boolean hasNextPage;
    private boolean isLoading;

    public static GodsListFragment newInstance(String category, String title) {
        GodsListFragment fragment = new GodsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("category", category);
        bundle.putString("title", title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            category = getArguments().getString("category");
            title = getArguments().getString("title");
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_goods_list;
    }

    @Override
    public void initViews(View view) {
        if (!category.equals(Constants.TYPE_ALL) && getBaseActivity().getSupportActionBar() != null) {
            getBaseActivity().getSupportActionBar().setTitle(title);
            getBaseActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        goodsList = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        gridLayoutManager.setSpanSizeLookup(new AutoSpanSizeLookUp());
        goodsRecyclerView.setLayoutManager(gridLayoutManager);
        goodsAdapter = new GoodsAdapter(getContext(), goodsList);
        goodsRecyclerView.setAdapter(goodsAdapter);
    }

    @Override
    public void setListeners() {
        goodsRecyclerView.addOnScrollListener(onScrollBottomListener);
        swipeRefreshLayout.setOnRefreshListener(onRefreshListener);
    }

    @Override
    public void loadData() {
        if (goodsList.size() == 0) {
            this.goodsListPresenter.getGoods(category,pageIndex,PAGE_SIZE);
            if (category.equals(Constants.TYPE_ALL)) {
                this.goodsListPresenter.getBanner();
            }
        }
    }

    private SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            pageIndex = 1;
            goodsListPresenter.getGoods(category,pageIndex,PAGE_SIZE);
        }
    };
    private OnScrollBottomListener onScrollBottomListener = new OnScrollBottomListener() {
        @Override
        public void onBottom() {
            super.onBottom();
            android.util.Log.d("ljx","onBottom");
            if (hasNextPage&&!isLoading){
                goodsListPresenter.getGoods(category, pageIndex, PAGE_SIZE);
                isLoading = true;
            }
        }
    };

    @Override
    public void bind() {
        this.goodsListPresenter = new GoodsListPresenter();
        this.goodsListPresenter.attachView(this);
    }

    @Override
    public void unbind() {
        this.goodsListPresenter.detachView();
    }


    @Override
    public void onGetGoodsSuccess(List<ItemData> goods, boolean refresh) {
        if (swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
        isLoading = false;
        if (goods.size()<PAGE_SIZE){
            hasNextPage = false;
        }else {
            hasNextPage = true;
            pageIndex+=1;
        }
        goodsList.addAll(goods);
        goodsAdapter.setHasNextPage(hasNextPage);
        goodsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetBannerSuccess(ItemData banner) {
        goodsList.add(0, banner);
        goodsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(Throwable e) {
        isLoading = false;
    }


    public class GoodsAdapter extends GoodsListAdapter {

        public GoodsAdapter(Context context, ArrayList<ItemData> data) {
            super(context, data);
        }

        @Override
        protected void bindData(RecyclerView.ViewHolder holder, int position) {
            GoodsViewHolder vh = (GoodsViewHolder) holder;
            BaseGoodsData goods = (BaseGoodsData) mDatas.get(position).getData();
            Glide.with(context).load(goods.getIconUrl()).into(vh.goodsPicImageView);
            vh.goodsInfoTextView.setText(goods.getInfoStr());
            vh.goodsPriceTextView.setText("￥" + goods.getPrice());
            vh.goodsOldPriceTextView.setText(goods.getOldPrice() + "元");
        }
    }

    public class AutoSpanSizeLookUp extends GridLayoutManager.SpanSizeLookup {

        @Override
        public int getSpanSize(int position) {
            switch (goodsAdapter.getItemViewType(position)) {
                case ItemData.TYPE_BANNER:
                    return 2;
                case ItemData.TYPE_GOODS:
                    return 1;
                case ItemData.TYPE_FOOTER:
                    return 1;
                default:
                    return -1;
            }
        }
    }

}
