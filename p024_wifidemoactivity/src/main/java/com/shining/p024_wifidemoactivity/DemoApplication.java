package com.shining.p024_wifidemoactivity;

import android.app.Application;
import android.content.Context;

import org.loader.opendroid.db.OpenDroidUtil;

/**
 * Created by shining on 2017/6/24 0024.
 */

public class DemoApplication extends Application {

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
        mContext = getApplicationContext();
        OpenDroidUtil.setup(getApplicationContext());
    }
}
