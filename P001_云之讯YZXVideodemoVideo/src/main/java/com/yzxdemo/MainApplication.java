package com.yzxdemo;

import android.app.Application;
import android.content.Intent;

import com.yzx.tools.CustomLog;
import com.yzxdemo.service.ConnectionService;

public class MainApplication extends Application{
	@Override
	public void onCreate() {
		super.onCreate();
		CustomLog.v("MainApplication.onCreate()  ... ");
		startService(new Intent(this, ConnectionService.class));
	}
}
