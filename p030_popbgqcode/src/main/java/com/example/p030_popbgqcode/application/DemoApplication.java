package com.example.p030_popbgqcode.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by shining on 2017/8/9.
 */

public class DemoApplication extends Application {
    private static final String TAG = "SFNationApplication";
    private static DemoApplication sInstance = null;
    public static Context mContext;

    public static DemoApplication get() {
        if (sInstance == null) {
            sInstance = new DemoApplication();
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
