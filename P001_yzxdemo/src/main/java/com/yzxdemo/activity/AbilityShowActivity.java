package com.yzxdemo.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.yzx.api.UCSService;
import com.yzx.tools.CustomLog;
import com.yzxdemo.R;
import com.yzxdemo.action.UIDfineAction;
import com.yzxdemo.restClient.JsonReqClient;
import com.yzxdemo.service.ConnectionService;
import com.yzxdemo.tools.Config;
import com.yzxdemo.tools.DfineAction;
import com.yzxdemo.tools.LoginConfig;
import com.yzxdemo.ui.MenuDialog;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AbilityShowActivity extends BaseActivity implements OnClickListener{

	private LinearLayout ll_audio;
	private LinearLayout ll_im;
	private LinearLayout ll_verification;
	private LinearLayout ll_video;
	private LinearLayout ll_conference;
	private RelativeLayout rl_more;
	private MenuDialog mMenuDialog;
	private String menu_account;
	private String menu_type;
	private String menu_balance;
	private long mExitTime=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_abilityshow);
		initviews();
		initMenu();
		IntentFilter ift = new IntentFilter();
		ift.addAction(UIDfineAction.ACTION_LOGOUT);
		registerReceiver(br, ift);
	}


	private void initviews() {
		ll_audio = (LinearLayout) findViewById(R.id.ll_audio);
		ll_im = (LinearLayout) findViewById(R.id.ll_im);
		ll_verification = (LinearLayout) findViewById(R.id.ll_verification);
		ll_video = (LinearLayout) findViewById(R.id.ll_video);
		ll_conference = (LinearLayout) findViewById(R.id.ll_conference);
		rl_more = (RelativeLayout) findViewById(R.id.rl_more);
		ll_audio.setOnClickListener(this);
		ll_im.setOnClickListener(this);
		ll_verification.setOnClickListener(this);
		ll_video.setOnClickListener(this);
		ll_conference.setOnClickListener(this);
		rl_more.setOnClickListener(this);
		((TextView)findViewById(R.id.tv_bottom)).setText(UCSService.isConnected()?"在线:"+LoginConfig.getCurrentClientId(AbilityShowActivity.this):"离线");
		if(!UCSService.isConnected()){
			Toast.makeText(AbilityShowActivity.this, "您已掉线，请重新连接", Toast.LENGTH_LONG).show();
		}
	}
	
	
	

	@Override
	protected void onRestart() {
		((TextView)findViewById(R.id.tv_bottom)).setText(UCSService.isConnected()?"在线:"+LoginConfig.getCurrentClientId(AbilityShowActivity.this):"离线");
		super.onRestart();
	}


	private void initMenu() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				JsonReqClient client = new JsonReqClient();
				String result = client.queryAccountsInfo(Config.getMain_account(),Config.getMain_token());
				CustomLog.v("query accounts info :"+result);
				if(result != null){
					Message msg = new Message();
					msg.obj = result;
					mHandler.sendMessage(msg);
				}
			}
		}).start();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_audio:
			startActivity(new Intent(this, AudioActivity.class));
			break;
		case R.id.ll_im:
			startActivity(new Intent(this, IMActivity.class));
			break;
		case R.id.ll_verification:
			startActivity(new Intent(this, VerificationActivity.class));
			break;
		case R.id.ll_video:
			startActivity(new Intent(this, VideoActivity.class));
			break;
		case R.id.ll_conference:
			break;
		case R.id.rl_more:
			mMenuDialog = new MenuDialog(AbilityShowActivity.this, menu_account,menu_type,menu_balance);
			Window window = mMenuDialog.getWindow(); 
			WindowManager.LayoutParams wmlp =window.getAttributes();
			wmlp.gravity=Gravity.TOP|Gravity.RIGHT;
			wmlp.x=10;
			DisplayMetrics dm = getResources().getDisplayMetrics();
			if(dm.heightPixels>1700){
				wmlp.y=124;
			}else if (dm.heightPixels>1400) {
				wmlp.y=110;
			}else if (dm.heightPixels>1100) {
				wmlp.y=100;
			}else if (dm.heightPixels>900) {
				wmlp.y=90;
			}else if (dm.heightPixels>700) {
				wmlp.y=80;
			}else if (dm.heightPixels>500) {
				wmlp.y=65;
			}else{
				wmlp.y=50;
			}
			wmlp.dimAmount=0.0f;
			window.setAttributes(wmlp);
			mMenuDialog.setCancelable(true);
			mMenuDialog.show();
			break;
		default:
			break;
		}
	}
	
	private BroadcastReceiver br = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			if(intent.getAction().equals(UIDfineAction.ACTION_LOGOUT)){
				((TextView)findViewById(R.id.tv_bottom)).setText("离线");
			}
		}
	};
	
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
								menu_balance = "￥"+account.getString("balance");
							}
							if(account.has("mobile")){
							}
							if(account.has("type")){
								menu_type = Integer.parseInt(account.getString("type")) == 1?"个人开发者":"企业开发";
							}
							if(account.has("email")){
								menu_account = account.getString("email");
							}
						}
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	};
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				Toast.makeText(AbilityShowActivity.this, "再按一次退出程序", Toast.LENGTH_LONG).show();
				mExitTime = System.currentTimeMillis();
				return true;
			} else {
				finish();
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	protected void onDestroy() {
		if(mMenuDialog!=null){
			mMenuDialog.dismiss();
		}
		unregisterReceiver(br);
		super.onDestroy();
	}
}
