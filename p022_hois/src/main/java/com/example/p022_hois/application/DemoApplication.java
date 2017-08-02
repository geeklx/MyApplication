package com.example.p022_hois.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.example.p022_hois.hioscommon.HiosRegister;
import com.example.p022_hois.hois2.HiosHelper2;

/**
 * Created by shining on 2017/6/2 0002.
 */

public class DemoApplication extends Application {
    private static final String TAG = "DemoApplication";
    private static DemoApplication sInstance = null;
    public static Context mContext;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

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
        configHios();
    }

    protected void configHios() {
        HiosRegister.load();
        HiosHelper2.config("ad.web.page", "web.page");
    }
}
