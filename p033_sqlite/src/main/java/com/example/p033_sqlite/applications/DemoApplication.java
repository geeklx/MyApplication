package com.example.p033_sqlite.applications;

import android.app.Application;
import android.content.Context;

import com.example.opendroidlibrary.db.OpenDroidUtil;
import com.example.opendroidyuanlibrary.db.OpenDroidYuanUtil;


/**
 * Created by shining on 2016/11/10 0010.
 */

public class DemoApplication extends Application {
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
        OpenDroidUtil.setup(mContext);
        OpenDroidYuanUtil.setup(mContext);
    }

}
