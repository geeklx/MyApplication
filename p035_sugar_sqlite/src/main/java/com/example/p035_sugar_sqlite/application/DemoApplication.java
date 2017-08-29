package com.example.p035_sugar_sqlite.application;

import android.app.Application;
import android.content.Context;

import com.orm.SugarContext;

/**
 * Created by shining on 2017/8/29.
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
        mContext = getApplicationContext();
        SugarContext.init(mContext);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}
