package com.example.p010_recycleviewall.application;

import android.content.Context;
import android.support.multidex.MultiDexApplication;


/**
 * Created by shining on 2016/11/10 0010.
 */

public class DemoApplication extends MultiDexApplication {
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
