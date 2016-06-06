package com.edu.lzjtu.weixin;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.edu.lzjtu.weixin.adapter.TabFragmentPagerAdapter;
import com.edu.lzjtu.weixin.bean.ChatMessage;
import com.edu.lzjtu.weixin.bean.TabFragment;
import com.edu.lzjtu.weixin.dao.FragmentAddressList;
import com.edu.lzjtu.weixin.dao.FragmentDiscover;
import com.edu.lzjtu.weixin.dao.FragmentMe;
import com.edu.lzjtu.weixin.dao.FragmentWeiXin;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private List<TabFragment>  mList;
    private ViewPager mViewPager;
    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private TabLayout.Tab four;
    private List<ChatMessage> mChatMessageList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.TabPager);
        mTabLayout = (TabLayout) findViewById(R.id.TablayoutFragment);
        mList = new ArrayList<>();
        mChatMessageList = new ArrayList<>();
        mChatMessageList.add(new ChatMessage(R.mipmap.al8,"张三","你好","昨天"));
        mChatMessageList.add(new ChatMessage(R.mipmap.al_,"张三","你好","昨天"));
        mChatMessageList.add(new ChatMessage(R.mipmap.ic_launcher,"张三","你好你好你好你好你好你好你好你好你好你好你好你好你好","昨天"));
        mChatMessageList.add(new ChatMessage(R.mipmap.alc,"张三","你好","昨天"));
        mChatMessageList.add(new ChatMessage(R.mipmap.ic_launcher,"张三","你好","昨天"));
        mChatMessageList.add(new ChatMessage(R.mipmap.ic_launcher,"张三","你好","昨天"));
        mChatMessageList.add(new ChatMessage(R.mipmap.ic_launcher,"张三","你好","昨天"));
        mChatMessageList.add(new ChatMessage(R.mipmap.ic_launcher,"张三","你好","昨天"));
        mChatMessageList.add(new ChatMessage(R.mipmap.ic_launcher,"张三","你好你好你好你好你好你好你好你好你好你好你好你好你好","昨天"));
        mChatMessageList.add(new ChatMessage(R.mipmap.ic_launcher,"张三","你好","昨天"));
        mChatMessageList.add(new ChatMessage(R.mipmap.ic_launcher,"张三","你好","昨天"));
        mChatMessageList.add(new ChatMessage(R.mipmap.ic_launcher,"张三","你好","昨天"));
        mChatMessageList.add(new ChatMessage(R.mipmap.ic_launcher,"张三","你好","昨天"));
        mChatMessageList.add(new ChatMessage(R.mipmap.ic_launcher,"张三","你好","昨天"));
        mChatMessageList.add(new ChatMessage(R.mipmap.ic_launcher,"张三","你好你好你好你好你好你好你好你好你好你好你好你好你好","昨天"));
        mChatMessageList.add(new ChatMessage(R.mipmap.ic_launcher,"张三","你好","昨天"));
        mChatMessageList.add(new ChatMessage(R.mipmap.ic_launcher,"张三","你好","昨天"));
        mChatMessageList.add(new ChatMessage(R.mipmap.ic_launcher,"张三","你好","昨天"));
        mChatMessageList.add(new ChatMessage(R.mipmap.ic_launcher,"张三","你好","昨天"));
        mChatMessageList.add(new ChatMessage(R.mipmap.ic_launcher,"张三","你好","昨天"));
        mChatMessageList.add(new ChatMessage(R.mipmap.ic_launcher,"张三","你好你好你好你好你好你好你好你好你好你好你好你好你好","昨天"));
        mChatMessageList.add(new ChatMessage(R.mipmap.ic_launcher,"张三","你好","昨天"));
        mChatMessageList.add(new ChatMessage(R.mipmap.ic_launcher,"张三","你好","昨天"));
        mChatMessageList.add(new ChatMessage(R.mipmap.ic_launcher,"张三","你好","昨天"));
        mChatMessageList.add(new ChatMessage(R.mipmap.ic_launcher,"张三","你好","昨天"));
        mChatMessageList.add(new ChatMessage(R.mipmap.ic_launcher,"张三","你好","昨天"));
        mChatMessageList.add(new ChatMessage(R.mipmap.ic_launcher,"张三","你好你好你好你好你好你好你好你好你好你好你好你好你好","昨天"));
        mChatMessageList.add(new ChatMessage(R.mipmap.ic_launcher,"张三","你好","昨天"));
        mChatMessageList.add(new ChatMessage(R.mipmap.ic_launcher,"张三","你好","昨天"));
        mChatMessageList.add(new ChatMessage(R.mipmap.ic_launcher,"张三","你好","昨天"));
        mList.add(new TabFragment("微信",new FragmentWeiXin(mChatMessageList,MainActivity.this)));
        mList.add(new TabFragment("通讯录",new FragmentAddressList()));
        mList.add(new TabFragment("发现",new FragmentDiscover()));
        mList.add(new TabFragment("我",new FragmentMe()));
        FragmentManager manager = getSupportFragmentManager();
        TabFragmentPagerAdapter tabFragmentPagerAdapter = new TabFragmentPagerAdapter(manager,mList,MainActivity.this);
        mViewPager.setAdapter(tabFragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        one = mTabLayout.getTabAt(0);
        two = mTabLayout.getTabAt(1);
        three = mTabLayout.getTabAt(2);
        four = mTabLayout.getTabAt(3);
        one.setIcon(R.mipmap.al_);
        two.setIcon(R.mipmap.al9);
        three.setIcon(R.mipmap.alc);
        four.setIcon(R.mipmap.ale);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int count =0;
                if(tab ==mTabLayout.getTabAt(0)){
                    count = 0;
                }
                if(tab ==mTabLayout.getTabAt(1)){
                    count = 1;
                }
                if(tab ==mTabLayout.getTabAt(2)){
                    count = 2;
                }
                if(tab ==mTabLayout.getTabAt(3)){
                    count = 3;
                }
                change(count);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
    private void change(int pp){
        //Toast.makeText(this,"ssdfsdf",Toast.LENGTH_SHORT).show();
        one.setIcon(R.mipmap.ala);
        two.setIcon(R.mipmap.al9);
        three.setIcon(R.mipmap.alc);
        four.setIcon(R.mipmap.ale);
        switch (pp){
            case 0: one.setIcon(R.mipmap.al_);mViewPager.setCurrentItem(0,true);break;
            case 1:two.setIcon(R.mipmap.al8);mViewPager.setCurrentItem(1,true);break;
            case 2:three.setIcon(R.mipmap.alb);mViewPager.setCurrentItem(2,true);break;
            case 3:four.setIcon(R.mipmap.ald);mViewPager.setCurrentItem(3,true);break;
        }
    }
}
