package com.licrafter.happylife.base;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.licrafter.happylife.R;

import butterknife.Bind;

/**
 * Created by Administrator on 2016/1/31.
 */
public abstract class BaseToolbarActivity extends BaseAppCompatActivity {

    @Bind(R.id.toolbar)
    protected Toolbar toolbar;
    @Bind(R.id.appBarLayout)
    protected AppBarLayout appBarLayout;

    protected ActionBarHelper actionBarHelper;

    @Override
    protected void initToolbar(Bundle savedInstanceState) {
        this.initToolbarHelper();
    }

    protected void initToolbarHelper() {
        if (this.toolbar == null || this.appBarLayout == null) {
            return;
        }
        this.setSupportActionBar(toolbar);
        this.actionBarHelper = new ActionBarHelper();
        this.actionBarHelper.init();

//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
//            appBarLayout.setElevation(6.0f);
//        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }

    public void setAppBarLayoutVisible(boolean visible) {
        this.appBarLayout.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public void setAppBarShadow(boolean isShown) {
        final int elevation = isShown
                ? getResources().getDimensionPixelSize(R.dimen.appbar_elevation) : 0;
        ViewCompat.setElevation(appBarLayout, elevation);
    }

    public class ActionBarHelper {
        private ActionBar mActionBar;
        private CharSequence mDrawerTitle;
        private CharSequence mTitle;

        public ActionBarHelper() {
            this.mActionBar = getSupportActionBar();
        }

        public void init() {
            if (mActionBar == null) return;
            this.mActionBar.setDisplayHomeAsUpEnabled(false);
            this.mActionBar.setDisplayShowHomeEnabled(false);
            this.mTitle = mDrawerTitle = getTitle();
        }

        public void onDrawerClosed() {
            if (mActionBar == null) return;
            this.mActionBar.setTitle(this.mTitle);
        }

        public void onDrawerOpened() {
            if (mActionBar == null) return;
            this.mActionBar.setTitle(this.mDrawerTitle);
        }

        public void setTitle(CharSequence title) {
            this.mTitle = title;
        }

        public void setDrawerTitle(CharSequence title) {
            this.mDrawerTitle = title;
        }
    }
}
