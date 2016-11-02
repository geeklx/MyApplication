package com.example.p012_welcomepage.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by shining on 2016/11/2 0002.
 */

public class DemoApplication extends Application {

    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}
