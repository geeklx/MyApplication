package com.shining.p010_recycleviewall.application;

import android.app.Application;
import android.content.Context;

import com.shining.p010_recycleviewall.domain.AppBean;
import com.shining.p010_recycleviewall.domain.DeviceBean;
import com.shining.p010_recycleviewall.net.glide.GlideOptionsFactory;
import com.shining.p010_recycleviewall.utils.DataProvider;


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
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(mContext);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mContext = getApplicationContext();

        setAPPandDeviceInfo();


//        MultiDex.install(mContext);

        GlideOptionsFactory.init(this, com.shining.p010_recycleviewall.R.drawable.ic_def_loading);


    }


    private void setAPPandDeviceInfo() {
//        String userId = (String) SpUtils.getInstance(this).get(ConstantUtil.USER_ID, "");
        String userId = "201605262127400016";
        this.setAPPandDeviceInfo(mContext, userId);
    }

    public static void setAPPandDeviceInfo(Context mContext, String userId) {
        DataProvider.app = new AppBean(userId);
        DataProvider.device = new DeviceBean(mContext);
    }

}
