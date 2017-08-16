package com.example.p031_mokuaihua_viewpager_fragment.utils;

import android.util.Log;


/**
 * 日志定管理
 */
public class MyLogUtil {

    public static final String TAG = "test";
    /**
     * 常量
     */
    public static final boolean DEBUG = true;  //true debug版本 false 正式版本
    public static boolean on = DEBUG;

    /**
     * 开关
     */
    public static void setLogEnable(boolean flag) {
        on = flag;
    }

    public static void i(String msg) {
        if (on) {
            Log.i(TAG, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (on) {
            Log.i(tag, msg);
        }
    }

    public static void e(String msg) {
        if (on) {
            Log.e(TAG, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (on) {
            Log.e(tag, msg);
        }
    }

    public static void d(String msg) {
        if (on) {
            Log.d(TAG, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (on) {
            Log.d(tag, msg);
        }
    }

    public static void v(String msg) {
        if (on) {
            Log.v(TAG, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (on) {
            Log.v(tag, msg);
        }
    }
}