package com.edu.lzjtu.weixin.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.edu.lzjtu.weixin.bean.TabFragment;

import java.util.List;

/**
 * Created by houjie on 2016/4/16.
 */
public class TabFragmentPagerAdapter extends FragmentStatePagerAdapter{
    private List<TabFragment> mList;
    private Context mConext;

    public TabFragmentPagerAdapter(FragmentManager fm, List<TabFragment> mList, Context mConext) {
        super(fm);
        this.mList = mList;
        this.mConext = mConext;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position).getTitle();
    }
}
