package com.yzxdemo.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.yzxdemo.R;
import com.yzxdemo.restClient.JsonReqClient;
import com.yzxdemo.tools.Config;
import com.yzxdemo.tools.DfineAction;
import com.yzx.api.UCSService;
import com.yzx.tools.CustomLog;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MyCenterActivity extends BaseActivity{
	
	private TextView mycenter_account;
	private TextView mycenter_type;
	private TextView mycenter_balance;
	private RelativeLayout rl_mycenter;
	private RelativeLayout mycenter_out;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mycenter);
		initviews();
		initdata();
	}

	private void initdata() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				JsonReqClient client = new JsonReqClient();
				String result = client.queryAccountsInfo(Config.getMain_account(),Config.getMain_token());
				CustomLog.v(DfineAction.TAG_TCP,result);
				if(result != null){
					Message msg = new Message();
					msg.obj = result;
					mHandler.sendMessage(msg);
				}
			}
		}).start();
	}

	private void initviews() {
		mycenter_account = (TextView) findViewById(R.id.mycenter_account);
		mycenter_type = (TextView) findViewById(R.id.mycenter_type);
		mycenter_balance = (TextView) findViewById(R.id.mycenter_balance);
		rl_mycenter = (RelativeLayout) findViewById(R.id.rl_mycenter);
		mycenter_out = (RelativeLayout) findViewById(R.id.mycenter_out);
		mycenter_account.setText(getIntent().getStringExtra("account"));
		mycenter_type.setText(getIntent().getStringExtra("type"));
		mycenter_balance.setText(getIntent().getStringExtra("balance"));
		rl_mycenter.setOnClickListener(new View.OnClickListener() {  
            public void onClick(View v) {  
            	finish();
            }  
        });  
		
		mycenter_out.setOnClickListener(new View.OnClickListener() {  
            public void onClick(View v) {  
            	UCSService.uninit();
        		startActivity(new Intent(MyCenterActivity.this,LoginActivity.class));
        		finish();
            }  
        });  
	}
	
	private Handler mHandler = new Handler(){
		@Override
		public void dispatchMessage(Message msg) {
			try {
				JSONObject json = new JSONObject((String)msg.obj);
				if(json.has("resp")){
					JSONObject respCode = json.getJSONObject("resp");
					if(respCode.has("respCode") && respCode.getString("respCode").equals("000000")){
						if(respCode.has("account")){
							JSONObject account = respCode.getJSONObject("account");
							if(account.has("balance")){
								mycenter_balance.setText("￥"+account.getString("balance"));
							}
							if(account.has("mobile")){
							}
							if(account.has("type")){
								mycenter_type.setText(Integer.parseInt(account.getString("type")) == 1?"个人开发者":"企业开发");
							}
							if(account.has("email")){
								mycenter_account.setText(account.getString("email"));
							}
						}
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	};
}
