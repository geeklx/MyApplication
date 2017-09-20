package com.example.shining.p041_uppicture.uploadimg.application;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

public class UlDemoApplication extends Application {
	private static final String TAG = "DemoApplication";
	private static UlDemoApplication sInstance = null;
	public static Context mContext;
	public static int userPhoneId = 2;


	public static UlDemoApplication get() {
		if (sInstance == null) {
			sInstance = new UlDemoApplication();
		}
		return sInstance;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		sInstance = this;
		mContext = getApplicationContext();
		// android 7.0系统解决拍照的问题
		StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
		StrictMode.setVmPolicy(builder.build());
		builder.detectFileUriExposure();
	}

}
