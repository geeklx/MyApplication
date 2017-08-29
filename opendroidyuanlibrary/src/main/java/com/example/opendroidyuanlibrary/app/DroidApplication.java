package com.example.opendroidyuanlibrary.app;

import android.app.Application;
import android.content.Context;

public class DroidApplication extends Application {
	public static Context sContext;
	
	@Override
	public void onCreate() {
		super.onCreate();
		sContext = getApplicationContext();
	}
}
