package com.yzxdemo.activity;

import com.yzxdemo.tools.DialConfig;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class BaseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		String type = DialConfig.getCallType(this);
		if(type.length() > 0){
			if(type.equals("1")){
//				BaseActivity.this.startActivity(new Intent(BaseActivity.this,AudioConverseActivity.class));
			}else if(type.equals("2")){
				BaseActivity.this.startActivity(new Intent(BaseActivity.this,VideoConverseActivity.class));
			}
		}
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onRestart() {
		String type = DialConfig.getCallType(this);
		if(type.length() > 0){
			if(type.equals("1")){
//				BaseActivity.this.startActivity(new Intent(BaseActivity.this,AudioConverseActivity.class));
			}else if(type.equals("2")){
				BaseActivity.this.startActivity(new Intent(BaseActivity.this,VideoConverseActivity.class));
			}
		}
		super.onRestart();
	}

}
