package com.yzxdemo.activity;

import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;
import org.json.JSONObject;

import com.yzxdemo.R;
import com.yzx.api.UCSService;
import com.yzx.tools.CustomLog;
import com.yzx.tools.PhoneNumberTools;
import com.yzxdemo.restClient.JsonReqClient;
import com.yzxdemo.tools.Config;
import com.yzxdemo.tools.DfineAction;
import com.yzxdemo.tools.LoginConfig;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class VerificationActivity extends BaseActivity{
	
	private Button verification_getaudio;
	private Button verification_getsms;
	private EditText verification_phone;
	private EditText verification_num;
	private TextView verification_bt;
	private TextView verification_tv_bottom;
	private RelativeLayout rl_back;
	private Timer timer = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_verification);
		initviews();
	}

	private void initviews() {
		verification_getaudio = (Button) findViewById(R.id.verification_getaudio);
		verification_getsms = (Button) findViewById(R.id.verification_getsms);
		verification_phone = (EditText)findViewById(R.id.verification_phone);
		verification_num = (EditText)findViewById(R.id.verification_num);
		verification_bt = (TextView) findViewById(R.id.verification_bt);
		verification_tv_bottom  = (TextView) findViewById(R.id.verification_tv_bottom);
		rl_back = (RelativeLayout) findViewById(R.id.rl_back);
		rl_back.setOnClickListener(new View.OnClickListener() {  
            public void onClick(View v) {  
            	finish();
            }  
        });
		verification_tv_bottom.setText(UCSService.isConnected()?"在线:"+LoginConfig.getCurrentClientId(VerificationActivity.this):"离线");
		
		//获取短信验证码
		verification_getsms.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final String phone = verification_phone.getText().toString();
				final String code = Math.round(Math.random() * 10000) + "";
				verification_bt.setTag(code);
				if(phone != null && phone.length() > 0){
					if(PhoneNumberTools.checkMobilePhoneNumber(phone)){
						startCallTimer(1);
						
						new Thread(new Runnable() {
							public void run() {
								JsonReqClient client = new JsonReqClient();
								String result = client.sendVerificationCode(Config.getAppid(),Config.getMain_account(),Config.getMain_token(), code, phone);
								//{"resp":{"respCode":"000000","templateSMS":{"createDate":"20140820145658","smsId":"d2c49329f363b802fb3531d9c67b54f8"}}}
								if(result != null && result.length() > 0){
									try {
										CustomLog.v(DfineAction.TAG_TCP, "RESULT:"+result);
										JSONObject object = new JSONObject(result);
										if(object.has("resp")){
											JSONObject item = (JSONObject)object.getJSONObject("resp");
											if(item.has("respCode") && item.getString("respCode").equals("000000")){
												mHandler.sendEmptyMessage(0);
											}else{
												mHandler.sendEmptyMessage(1);
											}
										}else{
											mHandler.sendEmptyMessage(1);
										}
									} catch (JSONException e) {
										e.printStackTrace();
										mHandler.sendEmptyMessage(1);
									}
								}
							}
						}).start();
					}else{
						Toast.makeText(VerificationActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
					}
				}else {
					Toast.makeText(VerificationActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		//获取语音验证码
		verification_getaudio.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick( View v) {
				final String phone = verification_phone.getText().toString();
				final String code = Math.round(Math.random() * 10000) + "";
				verification_bt.setTag(code);
				if(phone != null && phone.length() > 0){
					if(PhoneNumberTools.checkMobilePhoneNumber(phone)){
						startCallTimer(2);
						new Thread(new Runnable() {
							public void run() {
								JsonReqClient client = new JsonReqClient();
								String result = client.sendVerificationAudio(Config.getAppid(),Config.getMain_account(),Config.getMain_token(), code,phone);
								//{"resp":{"respCode":"000000","templateSMS":{"createDate":"20140820145658","smsId":"d2c49329f363b802fb3531d9c67b54f8"}}}
								if(result != null && result.length() > 0){
									try {
										CustomLog.v(DfineAction.TAG_TCP, "RESULT:"+result);
										JSONObject object = new JSONObject(result);
										if(object.has("resp")){
											JSONObject item = (JSONObject)object.getJSONObject("resp");
											if(item.has("respCode") && item.getString("respCode").equals("000000")){
												mHandler.sendEmptyMessage(2);
											}else{
												mHandler.sendEmptyMessage(3);
											}
										}else{
											mHandler.sendEmptyMessage(3);
										}
									} catch (JSONException e) {
										e.printStackTrace();
										mHandler.sendEmptyMessage(3);
									}
								}
							}
						}).start();
					}else{
						Toast.makeText(VerificationActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
					}
				}else {
					Toast.makeText(VerificationActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		
		//进行验证 下发的验证码和之前提交的随机数一致
		verification_bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(verification_bt.getTag()!= null && verification_bt.getTag().equals(verification_num.getText().toString())){
					mHandler.sendEmptyMessage(4);
				}else{
					mHandler.sendEmptyMessage(5);
				}
			}
		});
	}
	
	/**
	 * 
	 * 1.短信 验证倒计时
	 * 2.语音验证倒计时
	 * 
	 */
	@SuppressLint("HandlerLeak") 
	private Handler mUiHandler = new Handler(){
		@Override
		public void dispatchMessage(Message msg) {
			switch(msg.what){
			case 1:
				int sencond1=180-(Integer) msg.obj;
				if(sencond1<1){
					stopCallTimer();
				}else {
					verification_getsms.setText(sencond1+"秒");
				}
				break;
			case 2:
				int sencond2=180-(Integer) msg.obj;
				if(sencond2<1){
					stopCallTimer();
				}else {
					verification_getaudio.setText(sencond2+"秒");
				}
				break;
			}
		}
	};

	/**
	 * 
	 * 验证码定时器开启
	 * 
	 */
	private int sencond = 0;
	public void startCallTimer(final int type){
		sencond=0;
		stopCallTimer();
		if(timer == null){
			timer = new Timer();
		};
		verification_getaudio.setClickable(false);
		verification_getsms.setClickable(false);
		verification_getsms.setTextColor(Color.parseColor("#6f6f6f"));
		verification_getaudio.setTextColor(Color.parseColor("#6f6f6f"));
		verification_getsms.setBackgroundResource(R.drawable.login_input);
		verification_getaudio.setBackgroundResource(R.drawable.login_input);
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				sencond++;
				Message message = new Message();   
                message.what = type; 
                message.obj=sencond;
				mUiHandler.sendMessage(message);
			}
		}, 0, 1000);
	}
	
	
	/**
	 * 
	 * 验证码定时器关闭
	 * 
	 */
	public void stopCallTimer(){
		verification_getaudio.setClickable(true);
		verification_getsms.setClickable(true);
		verification_getsms.setTextColor(Color.parseColor("#4f4f4f"));
		verification_getaudio.setTextColor(Color.parseColor("#4f4f4f"));
		verification_getsms.setBackgroundResource(R.drawable.verification_bg);
		verification_getaudio.setBackgroundResource(R.drawable.verification_bg);
		verification_getsms.setText("获取短信验证码");
		verification_getaudio.setText("获取语音验证码");
		if (timer != null){
			timer.cancel();
			timer=null;
		}
	}
	
	private Handler mHandler = new Handler(){
		@Override
		public void dispatchMessage(Message msg) {
			switch(msg.what){
			case 0:
				Toast.makeText(VerificationActivity.this, "短信验证码发送成功", Toast.LENGTH_SHORT).show();
				break;
			case 1:
				Toast.makeText(VerificationActivity.this, "短信验证码发送失败", Toast.LENGTH_SHORT).show();
				break;
			case 2:
				Toast.makeText(VerificationActivity.this, "语音验证码发送成功", Toast.LENGTH_SHORT).show();
				break;
			case 3:
				Toast.makeText(VerificationActivity.this, "语音验证码发送失败", Toast.LENGTH_SHORT).show();
				break;
			case 4:
				Toast.makeText(VerificationActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
				stopCallTimer();
				break;
			case 5:
				Toast.makeText(VerificationActivity.this, "验证失败", Toast.LENGTH_SHORT).show();
				break;
			}
		}
		
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
