package com.example.p022_hois.application;

import android.app.Application;
import android.content.Context;

import com.example.p022_hois.hioscommon.HiosRegister;

/**
 * Created by shining on 2017/6/2 0002.
 */

public class DemoApplication extends Application {
    private static final String TAG = "DemoApplication";
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
        HiosRegister.load();
    }
}
