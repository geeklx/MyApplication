package com.example.p009_glide;

import android.content.Context;

/**
 * Created by liangxiao on 2016/9/13.
 */
public class Application extends android.app.Application {

    private static Application sInstance = null;
    public static Context mContext;

    public static Application get() {
        if (sInstance == null) {
            sInstance = new Application();
        }
        return sInstance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mContext = getApplicationContext();
    }
}
