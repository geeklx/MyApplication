package com.example.shining.p047_userlogin.application;

import android.app.Application;
import android.content.Context;


/**
 * Created by shining on 2016/11/10 0010.
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
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);//mutidex
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mContext = getApplicationContext();
    }

}
