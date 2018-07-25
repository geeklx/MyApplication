package com.example.shining.p043_uppictureandpaintprogbar.application;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class DemoUppictureApplication extends MultiDexApplication {

    private static DemoUppictureApplication sInstance = null;
    public static Context mContext;

    public static DemoUppictureApplication get() {
        if (sInstance == null) {
            sInstance = new DemoUppictureApplication();
        }
        return sInstance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);//mutidex
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mContext = getApplicationContext();


    }

}
