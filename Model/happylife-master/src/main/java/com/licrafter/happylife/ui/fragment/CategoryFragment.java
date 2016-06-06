package com.licrafter.happylife.ui.fragment;

import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.GridView;

import com.licrafter.happylife.R;
import com.licrafter.happylife.base.BaseFragment;
import com.licrafter.happylife.data.entity.BaseCategoryData;
import com.licrafter.happylife.mvp.presenters.CategoryListPresenter;
import com.licrafter.happylife.mvp.views.CategoryListView;
import com.licrafter.happylife.ui.activity.CategoryGodsActivity;
import com.licrafter.happylife.ui.adapter.CategoryAdapter;
import com.licrafter.happylife.util.FragmentUtil;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/2/18.
 */
public class CategoryFragment extends BaseFragment implements CategoryListView {

    @Bind(R.id.categoryGridView)
    GridView categoryGridView;

    private CategoryListPresenter categoryListPresenter;
    private ArrayList<BaseCategoryData> categoryDatas;
    private int categoryCount;
    private CategoryAdapter categoryAdapter;

    public static CategoryFragment newInstance() {
        CategoryFragment fragment = new CategoryFragment();
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_category;
    }

    @Override
    public void initViews(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            categoryGridView.setNestedScrollingEnabled(true);
        }
        categoryDatas = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(getContext());
        categoryGridView.setAdapter(categoryAdapter);
    }

    @Override
    public void setListeners() {
        categoryAdapter.setOnItemClickListener(listener);
    }

    @Override
    public void loadData() {

        if (categoryDatas.size() == 0) {
            categoryListPresenter.getCategories();
        }
    }

    @Override
    public void bind() {
        this.categoryListPresenter = new CategoryListPresenter();
        this.categoryListPresenter.attachView(this);
    }

    @Override
    public void unbind() {
        this.categoryListPresenter.detachView();
    }

    @Override
    public void onGetCategorySuccess(ArrayList<BaseCategoryData> datas) {
        categoryDatas = datas;
        categoryAdapter.setData(categoryDatas);
    }

    private CategoryAdapter.OnItemClickListener listener = new CategoryAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(String category,String title) {
            Intent intent = new Intent(getBaseActivity(), CategoryGodsActivity.class);
            intent.putExtra("category",category);
            intent.putExtra("title",title);
            startActivity(intent);
        }
    };

    @Override
    public void onFailure(Throwable e) {

    }

}
