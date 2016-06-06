package com.licrafter.happylife;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import com.licrafter.happylife.base.BaseToolbarActivity;
import com.licrafter.happylife.ui.fragment.MainFragment;
import com.licrafter.happylife.util.FragmentUtil;

public class MainActivity extends BaseToolbarActivity {


    Handler handler;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        switchFragment(MainFragment.newInstance(),false);
    }

    @Override
    protected void loadData() {
        handler = new Handler();
    }

    @Override
    protected void initListener() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void switchFragment(final Fragment fragment, final boolean addToStack) {
        //延时200ms跳转fragment，减缓drawerlayout卡顿现象
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean show = fragment instanceof MainFragment;
                setAppBarShadow(!show);
                FragmentUtil.replaceWithAnim(getSupportFragmentManager(), R.id.containerFrameLayout
                        , fragment, addToStack, "");
                handler.removeCallbacks(this);
            }
        }, 200);

    }
}
