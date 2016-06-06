package com.licrafter.happylife.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.licrafter.happylife.R;

/**
 * Created by Administrator on 2016/2/2.
 */
public class FragmentUtil {

    public static void replace(FragmentManager fragmentManager, int containerId, Fragment fragment, boolean addToBackStack, String tag) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(tag);
        }
        fragmentTransaction.replace(containerId, fragment, tag);
        fragmentTransaction.commit();
    }

    public static void replaceWithAnim(FragmentManager fragmentManager, int containerId, Fragment fragment, boolean addToBackStack, String tag) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fragment_in, R.anim.fragment_out, R.anim.fragment_pop_in, R.anim.fragment_pop_out);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(tag);
        }
        fragmentTransaction.replace(containerId, fragment, tag);
        fragmentTransaction.commit();
    }
}
