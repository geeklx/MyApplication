package com.example.shining.p045_butterknifegradle300.applications;

import android.content.Context;

public class DemoApplication extends android.app.Application {

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
