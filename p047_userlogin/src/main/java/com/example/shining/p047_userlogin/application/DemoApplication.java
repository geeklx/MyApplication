package com.example.shining.p047_userlogin.application;

import android.app.Application;
import android.content.Context;


/**
 * Created by shining on 2016/11/10 0010.
 */

public class DemoApplication extends Application {
    private static final String TAG = "DemoApplication";

    public static final int LOGIN_REQUEST_CODE = 30001;
    public static final int LOGINOUT_REQUEST_CODE = 30002;
    public static final int LOGIN_RESULT_OK = 10001;
    public static final int LOGIN_RESULT_CANCELED = 10002;
    public static final int LOGINOUT_RESULT_OK = 20001;
    public static final int LOGINOUT_RESULT_CANCELED = 20002;


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
