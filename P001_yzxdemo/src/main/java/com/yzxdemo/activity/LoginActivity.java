package com.yzxdemo.activity;

import java.util.Timer;
import java.util.TimerTask;

import com.yzxdemo.R;
import com.yzx.api.UCSService;
import com.yzx.tools.CustomLog;
import com.yzxdemo.action.UIDfineAction;
import com.yzxdemo.restClient.JsonReqClient;
import com.yzxdemo.tools.Config;
import com.yzxdemo.tools.DataTools;
import com.yzxdemo.tools.DfineAction;
import com.yzxdemo.tools.LoginConfig;
import com.yzxdemo.ui.LoginDialog;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends BaseActivity{
	
	private ProgressDialog mProgressDialog;
	private LoginDialog mLoginDialog;
	private EditText login_admin;
	private EditText login_pwd;
	private String login_admin_str="";
	private Timer timer;//登录超时定时器
	
	private BroadcastReceiver br = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			if(mProgressDialog != null){
				mProgressDialog.dismiss();
				mProgressDialog = null;
			}
			//接收连接回调后发送的广播
			if(intent.getAction().equals(UIDfineAction.ACTION_TCP_LOGIN_RESPONSE)){
				//登入第一步后收到的回调广播，获得子庄户信息，即将在对话框中显示出来
				if(null==mLoginDialog || !mLoginDialog.isShowing()){
					int result = intent.getIntExtra(UIDfineAction.RESULT_KEY, 1);
					if(result == 0){
						CustomLog.v(DfineAction.TAG_TCP, "打开子账号对话框");
						Toast.makeText(LoginActivity.this, "登录成功",Toast.LENGTH_SHORT).show();
						//测试账号添加上去掉的前缀，modified by zhaohaojie 20150906
						if (DataTools.istest){
							LoginConfig.saveCurrentSidAndToken(LoginActivity.this, "*#*" + login_admin_str, login_pwd.getText().toString());
							LoginConfig.saveCurrentSid(LoginActivity.this, "*#*" + login_admin_str);
						}
						else {
							LoginConfig.saveCurrentSidAndToken(LoginActivity.this, login_admin_str, login_pwd.getText().toString());
							LoginConfig.saveCurrentSid(LoginActivity.this, login_admin_str);
						}
						Config.initProperties(LoginActivity.this);
						if(null!=mLoginDialog){
							mLoginDialog=null;
						}
						mLoginDialog = new LoginDialog(LoginActivity.this);
						mLoginDialog.show();
					}else{
						String str = "";
						switch(result){
						case 10:
							str = "JSON错误";
							break;
						case 11:
							str = "没有SD卡或内存不足";
							break;
						case 12:
							str = "IO错误";
							break;
						case 101111:
							str = "用户名和密码错误";
							break;
						default:
							str = result+"";
							break;
						}
						Toast.makeText(LoginActivity.this, "登录失败:"+str,Toast.LENGTH_SHORT).show();
					}
				}
			}else if(intent.getAction().equals(UIDfineAction.ACTION_TCP_LOGIN_CLIENT_RESPONSE)){
				//登入第二步后收到的回调广播，子账户登入成功，即将进入能力展示界面
				if(null!=mLoginDialog && mLoginDialog.isShowing()){
					if(intent.getIntExtra(UIDfineAction.RESULT_KEY, 1) == 0){
						Toast.makeText(LoginActivity.this, "成功",Toast.LENGTH_SHORT).show();
						new Handler().postDelayed(new Runnable() {
							@Override
							public void run() {
								startActivity(new Intent(LoginActivity.this, AbilityShowActivity.class));
								if(null!=mLoginDialog){
									mLoginDialog.dismiss();
								}
								LoginActivity.this.finish();
							}
						}, 1000);
					}else{
						Toast.makeText(LoginActivity.this, "失败:"+intent.getIntExtra(UIDfineAction.REASON_KEY, 1),Toast.LENGTH_SHORT).show();
					}
				}
			}
		}
	};
	
	protected void onCreate(android.os.Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		IntentFilter ift = new IntentFilter();
        ift.addAction(UIDfineAction.ACTION_TCP_LOGIN_RESPONSE);
        ift.addAction(UIDfineAction.ACTION_TCP_LOGIN_CLIENT_RESPONSE);
        registerReceiver(br, ift);
        
        login_admin = (EditText)findViewById(R.id.login_admin);
        login_pwd = (EditText)findViewById(R.id.login_pwd);
        
        //如果保留的配置文件中有子账户，则直接弹出子账户
//        Config.initProperties(this);
//        if(!"".equals(Config.getClient_id())){
//        	mLoginDialog = new LoginDialog(LoginActivity.this);
//    		mLoginDialog.show();
//        }
        
        //填入配置文件中保留的账户和密码
        final String token[] = LoginConfig.getCurrentSidAndToken(this);
        if(token != null && token.length >= 2){
        	login_admin.setText(token[0]!= null ? token[0]:"");
        	login_pwd.setText(token[1] != null ? token[1]:"");
        	if(getIntent().getBooleanExtra("AutoLogin", false) && mLoginDialog==null){
        		CustomLog.v(DfineAction.TAG_TCP, "开始自动登入");
        		checkTest();
        		startCallTimer();
        		showProgressDialog();
				new Thread(new Runnable() {
					@Override
					public void run() {
						//测试账号去掉前缀，added by zhaohaojie 20150906
						if(token[0].startsWith("*#*")){
							token[0] = token[0].substring(3);
						}
						JsonReqClient client = new JsonReqClient();
						String json = client.login(token[0], token[1]);
						CustomLog.v(DfineAction.TAG_TCP,"RESULT:"+json);
						Config.parseConfig(json,LoginActivity.this);
					}
				}).start();
        	}
        }
        
        //登入第一步：获得子账号信息
        findViewById(R.id.login_btn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				checkTest();
				if(login_admin.getText().length() > 0
						&& login_pwd.getText().length() > 0){
					startCallTimer();
					showProgressDialog();
					new Thread(new Runnable() {
						@Override
						public void run() {
							JsonReqClient client = new JsonReqClient();
							String json = client.login(login_admin_str, login_pwd.getText().toString());
							CustomLog.v(DfineAction.TAG_TCP,"RESULT:"+json);
							Config.parseConfig(json,LoginActivity.this);
						}
					}).start();
				}
			}
		});
        
        //打开注册说明
        findViewById(R.id.login_register).setOnClickListener(new View.OnClickListener() {
     			@Override
     			public void onClick(View v) {
     				startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
     			}
     	});
        
        String version = "SDK_VERSION:"+UCSService.getSDKVersion()+ "\r\nDEMO_VERSION:"+DataTools.getSoftVersion(this);
    	
    	((TextView)findViewById(R.id.package_version)).setText(Html.fromHtml(version));
	}
	
	public void checkTest(){
		if(login_admin.getText().toString().startsWith("*#*")){
			DataTools.istest = true;
			login_admin_str = login_admin.getText().toString().substring(3);
		}else {
			DataTools.istest = false;
			login_admin_str = login_admin.getText().toString();
		}
	}
	
	private void showProgressDialog() {
		if (mProgressDialog == null) {
			mProgressDialog = new ProgressDialog(LoginActivity.this);
		}

		mProgressDialog.setIndeterminate(true);
		mProgressDialog.setCancelable(true);
		mProgressDialog.setMessage("正在获取测试账号,请稍等...");
		mProgressDialog.show();
	}
	
	@Override
	protected void onDestroy() {
		if(mProgressDialog != null){
			mProgressDialog.dismiss();
		}
		unregisterReceiver(br);
		stopCallTimer();
		super.onDestroy();
	}
	
	/**
	 * 启动登录超时定时器
	 */
	private void startCallTimer() {
		stopCallTimer();
		if(timer == null){
			timer = new Timer();
		}
		
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				handler.sendEmptyMessage(0);
			}
		}, 30000);
	}
	
	/**
	 * 停止登录超时定时器
	 */
	private void stopCallTimer(){
		if (timer != null){
			timer.cancel();
			timer=null;
		}
	}
	
	private Handler handler = new Handler () {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == 0)
			{
				if(mProgressDialog != null){
					mProgressDialog.dismiss();
					mProgressDialog = null;
					Toast.makeText(LoginActivity.this, "登录失败",Toast.LENGTH_SHORT).show();
				}
			}

		}
	};
}
