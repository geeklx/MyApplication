package com.yzxdemo.activity;

import com.yzx.api.UCSService;
import com.yzxdemo.R;
import com.yzxdemo.tools.DataTools;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class AboutActivity extends BaseActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_about);
		
		
		((TextView)findViewById(R.id.sdk_version)).setText(UCSService.getSDKVersion());
		((TextView)findViewById(R.id.soft_version)).setText(DataTools.getSoftVersion(this));
		
		
		findViewById(R.id.rl_back).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
