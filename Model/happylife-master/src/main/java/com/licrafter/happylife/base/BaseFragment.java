package com.licrafter.happylife.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.licrafter.happylife.MainActivity;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/2/2.
 */
public abstract class BaseFragment extends Fragment {

    private BaseToolbarActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (BaseToolbarActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(this.getLayoutId(), container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        bind();
        loadData();
        setListeners();
    }

    @Override
    public void onDestroyView() {
        unbind();
        super.onDestroyView();
    }

    protected BaseToolbarActivity getBaseActivity() {
        return activity;
    }

    public abstract int getLayoutId();

    public abstract void initViews(View view);

    public abstract void setListeners();

    public abstract void loadData();

    public abstract void bind();

    public abstract void unbind();
}
