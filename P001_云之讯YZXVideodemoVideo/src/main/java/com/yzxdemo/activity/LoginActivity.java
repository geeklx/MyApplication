package com.yzxdemo.activity;

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

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
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
	
	private BroadcastReceiver br = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			if(mProgressDialog != null){
				mProgressDialog.dismiss();
			}
			//接收连接回调后发送的广播
			if(intent.getAction().equals(UIDfineAction.ACTION_TCP_LOGIN_RESPONSE)){
				//登入第一步后收到的回调广播，获得子庄户信息，即将在对话框中显示出来
				if(null==mLoginDialog || !mLoginDialog.isShowing()){
					int result = intent.getIntExtra(UIDfineAction.RESULT_KEY, 1);
					if(result == 0){
						CustomLog.v("打开子账号对话框");
						Toast.makeText(LoginActivity.this, "登录成功",Toast.LENGTH_SHORT).show();
						LoginConfig.saveCurrentSidAndToken(LoginActivity.this, login_admin_str, login_pwd.getText().toString());
						LoginConfig.saveCurrentSid(LoginActivity.this, login_admin_str);
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
        		CustomLog.v("开始自动登入");
        		showProgressDialog();
				new Thread(new Runnable() {
					@Override
					public void run() {
						checkTest();
						JsonReqClient client = new JsonReqClient();
						String json = client.login(token[0], token[1]);
						CustomLog.v("RESULT:"+json);
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
					showProgressDialog();
					new Thread(new Runnable() {
						@Override
						public void run() {
							JsonReqClient client = new JsonReqClient();
							String json = client.login(login_admin_str, login_pwd.getText().toString());
							CustomLog.v("RESULT:"+json);
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
//     				startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
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
		} else {
			mProgressDialog.setIndeterminate(true);
			mProgressDialog.setCancelable(true);
			mProgressDialog.setMessage("正在获取测试账号,请稍等...");
			mProgressDialog.show();
			mProgressDialog.dismiss();
		}
	}
	
	@Override
	protected void onDestroy() {
		if(mProgressDialog != null){
			mProgressDialog.dismiss();
		}
		unregisterReceiver(br);
		super.onDestroy();
	}
}
