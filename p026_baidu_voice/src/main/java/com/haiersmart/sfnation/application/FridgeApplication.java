package com.haiersmart.sfnation.application;

import android.app.Application;
import android.content.Context;
import android.view.WindowManager;

import com.haiersmart.sfnation.common.MyLogUtil;
import com.haiersmart.voice.process.engine.SpeechHandleIntermediary;

/**
 * Created by shining on 2017/7/3 0003.
 */

public class FridgeApplication extends Application {
    private static final String TAG = "SFNationApplication";
    private static FridgeApplication sInstance = null;
    public static Context mContext;

    public static FridgeApplication get() {
        if (sInstance == null) {
            sInstance = new FridgeApplication();
        }
        return sInstance;
    }


    private WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();
    private WindowManager wm;

    public WindowManager.LayoutParams getMywmParams() {
        return wmParams;
    }

    @SuppressWarnings("WrongConstant")
    public WindowManager getMyWindowManager() {
        if (wm == null) {
            wm = (WindowManager) getApplicationContext().getSystemService("window");
        }
        return wm;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mContext = getApplicationContext();
        MyLogUtil.setLogEnable(true);

        //初始化语音sdk
        SpeechHandleIntermediary.build().initSDK(this);

    }
}
