package com.licrafter.happylife.ui.activity;

import android.os.Bundle;

import com.licrafter.happylife.R;
import com.licrafter.happylife.base.BaseToolbarActivity;
import com.licrafter.happylife.ui.fragment.GodsListFragment;
import com.licrafter.happylife.util.FragmentUtil;

/**
 * author: shell
 * date 16/3/13 下午1:09
 **/
public class CategoryGodsActivity extends BaseToolbarActivity {

    private String category = "";
    private String title = "";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_category_gods;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        if (getIntent().hasExtra("category") && getIntent().hasExtra("title")) {
            category = getIntent().getStringExtra("category");
            title = getIntent().getStringExtra("title");
            FragmentUtil.replace(getSupportFragmentManager(), R.id.containerFrameLayout, GodsListFragment.newInstance(category, title), false, "");
        }
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void initListener() {

    }
}
