package com.example.p020_timer.application;

import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDexApplication;

import com.example.p020_timer.service.TimerDemoService;

/**
 * Created by shining on 2017/3/24 0024.
 */

public class DemoApplication extends MultiDexApplication {

    private static final String TAG = "DemoApplication";
    private static DemoApplication sInstance = null;
    private Context mContext;

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

        startService(new Intent(mContext, TimerDemoService.class));
    }
}
