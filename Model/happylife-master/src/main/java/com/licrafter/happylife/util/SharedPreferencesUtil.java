package com.licrafter.happylife.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * Created by Administrator on 2016/2/1.
 */
public class SharedPreferencesUtil {

    public static final String APP_CACHE_NAME = "app_cache_name";
    private static SharedPreferences sharedPreferences = null;

    public static void init(Context context) {
        if (sharedPreferences == null && context != null) {
            sharedPreferences = context.getSharedPreferences(APP_CACHE_NAME, Context.MODE_PRIVATE);
        }
    }

    private static SharedPreferences getSP() {
        return sharedPreferences;
    }

    private static SharedPreferences.Editor getEditor() {
        return getSP().edit();
    }

    public static void save(String key, boolean value) {
        getEditor().putBoolean(key, value).apply();
    }

    public static void save(String key, int value) {
        getEditor().putInt(key, value).apply();
    }

    public static void save(String key, float value) {
        getEditor().putFloat(key, value).apply();
    }

    public static void save(String key, long value) {
        getEditor().putLong(key, value).apply();
    }

    public static void save(String key, String value) {
        getEditor().putString(key, value).apply();
    }

    public static void save(String key, Set<String> value) {
        getEditor().putStringSet(key, value).apply();
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return getSP().getBoolean(key, defValue);
    }

    public static String getString(String key, String defValue) {
        return getSP().getString(key, defValue);
    }

    public static int getInt(String key, int defValue) {
        return getSP().getInt(key, defValue);
    }

    public static float getFloat(String key, float defValue) {
        return getSP().getFloat(key, defValue);
    }

    public static long getLong(String key, long defValue) {
        return getSP().getLong(key, defValue);
    }

    public static Set<String> getStringSet(String key, Set<String> defValue) {
        return getSP().getStringSet(key, defValue);
    }

    public static void contains(String key) {
        getSP().contains(key);
    }
}
