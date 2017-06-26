package com.shining.p010_recycleviewall.application;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.shining.p010_recycleviewall.domain.AppBean;
import com.shining.p010_recycleviewall.domain.DeviceBean;
import com.shining.p010_recycleviewall.net.glide.GlideOptionsFactory;
import com.shining.p010_recycleviewall.utils.DataProvider;


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

        setAPPandDeviceInfo();

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
