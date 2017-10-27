package com.example.shining.p041_glide411.application;

import android.app.Application;
import android.content.Context;

public class DemoApplication extends Application {
	public static Context mContext;
	public static int userPhoneId = 1;

	@Override
	public void onCreate() {
		super.onCreate();
		mContext = getApplicationContext();
	}

}
