package com.yzxdemo.activity;

import com.yzxdemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

public class RegisterActivity extends BaseActivity{

	private RelativeLayout rl_back;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		rl_back = (RelativeLayout) findViewById(R.id.rl_back);
		rl_back.setOnClickListener(new View.OnClickListener() {  
            public void onClick(View v) {  
            	finish();
            }  
        });
	}
}
