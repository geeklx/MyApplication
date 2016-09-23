package com.yzxdemo.activity;

import com.yzxdemo.R;
import com.yzxdemo.tools.NetWorkTools;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class AudioCallActivity extends BaseActivity{
	
	private ImageView audio_call_head;
	private ImageView audio_call_iv2;
	private ImageView audio_call_iv3;
	private ImageView audio_call_iv4;
	private TextView audio_call_admin;
	private TextView audio_call_phone;
	private TextView audio_call_tv2_1;
	private TextView audio_call_tv2_2;
	private TextView audio_call_tv3_1;
	private TextView audio_call_tv3_2;
	private TextView audio_call_tv4_1;
	private TextView audio_call_tv4_2;
	private RelativeLayout audio_call_back;
	private RelativeLayout audio_call_intelligence;
	private RelativeLayout audio_call_free;
	private RelativeLayout audio_call_direct;
	private RelativeLayout audio_call_backcall;
	private String call_phone="";
	
	private static final String NETSTATE_ERROR = "网络连接错误，请检查网络是否已连接";
	private static final String SIMCARD_ERROR = "SIM 卡状态错误";
	private static final String EDGE_ERROR = "2G 网络下无法呼叫";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_audio_call);
		initviews();
	}

	private void initviews() {
		audio_call_head = (ImageView) findViewById(R.id.audio_call_head);
		audio_call_iv2 = (ImageView) findViewById(R.id.audio_call_iv2);
		audio_call_iv3 = (ImageView) findViewById(R.id.audio_call_iv3);
		audio_call_iv4 = (ImageView) findViewById(R.id.audio_call_iv4);
		audio_call_admin = (TextView) findViewById(R.id.audio_call_admin);
		audio_call_phone = (TextView) findViewById(R.id.audio_call_phone);
		audio_call_tv2_1 = (TextView) findViewById(R.id.audio_call_tv2_1);
		audio_call_tv2_2 = (TextView) findViewById(R.id.audio_call_tv2_2);
		audio_call_tv3_1 = (TextView) findViewById(R.id.audio_call_tv3_1);
		audio_call_tv3_2 = (TextView) findViewById(R.id.audio_call_tv3_2);
		audio_call_tv4_1 = (TextView) findViewById(R.id.audio_call_tv4_1);
		audio_call_tv4_2 = (TextView) findViewById(R.id.audio_call_tv4_2);
		audio_call_back = (RelativeLayout) findViewById(R.id.audio_call_back);
		audio_call_intelligence = (RelativeLayout) findViewById(R.id.audio_call_intelligence);
		audio_call_free = (RelativeLayout) findViewById(R.id.audio_call_free);
		audio_call_direct = (RelativeLayout) findViewById(R.id.audio_call_direct);
		audio_call_backcall = (RelativeLayout) findViewById(R.id.audio_call_backcall);
		if(null != getIntent().getStringExtra("call_phone")){
			call_phone = getIntent().getStringExtra("call_phone");
		}
		getCallHead();
		audio_call_admin.setText(getIntent().getStringExtra("call_client"));
		
		//关闭Activity，返回上个界面
		audio_call_back.setOnClickListener(new View.OnClickListener() {  
            public void onClick(View v) {  
            	finish();
            }  
        });  
		
		//VOIP免费电话，传递ClientID信息到呼叫界面，呼叫界面通过ClientID进行免费电话拨打
		audio_call_free.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (checkNetwork(AudioCallActivity.this, false) == false) {
					return;
				}
				Intent intent = new Intent(AudioCallActivity.this,AudioConverseActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				intent.putExtra("call_client",getIntent().getStringExtra("call_client"));
				intent.putExtra("call_type", 1);
				startActivity(intent);
			}
		}); 
		
		//如果有绑定电话号码，则可以进行直拨、回拨和智能拨打
		if(!"".equals(call_phone)){
			//智能拨打，传递ClientID信息到呼叫界面，呼叫界面通过ClientID进行智能拨打
			audio_call_intelligence.setOnClickListener(new View.OnClickListener() {  
	            public void onClick(View v) {  
					if(checkNetwork(AudioCallActivity.this, false) == false){
	            		return;
	            	}
	            	Intent intent = new Intent(AudioCallActivity.this, AudioConverseActivity.class);
	            	intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
					intent.putExtra("call_phone", getIntent().getStringExtra("call_phone"));
					intent.putExtra("call_client",getIntent().getStringExtra("call_client"));
					intent.putExtra("call_type", 5);
					startActivity(intent);
	            }  
	        }); 
			//直拨，传递ClientID绑定的手机号码信息到呼叫界面，呼叫界面通过ClientID绑定的手机号码进行直拨
			audio_call_direct.setOnClickListener(new View.OnClickListener() {  
	            public void onClick(View v) {  
					if(checkNetwork(AudioCallActivity.this, false) == false){
	            		return;
	            	}
	            	Intent intent = new Intent(AudioCallActivity.this, AudioConverseActivity.class);
	            	intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
					intent.putExtra("call_phone", getIntent().getStringExtra("call_phone"));
					intent.putExtra("call_type", 0);
					startActivity(intent);
	            }  
	        });  
			//回拨，传递ClientID绑定的手机号码信息到呼叫界面，呼叫界面通过ClientID绑定的手机号码进行回拨
			audio_call_backcall.setOnClickListener(new View.OnClickListener() {  
	            public void onClick(View v) {  
					if(checkNetwork(AudioCallActivity.this, true) == false){
	            		return;
	            	}
	            	Intent intent = new Intent(AudioCallActivity.this, AudioConverseActivity.class); 
	            	intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
					intent.putExtra("call_phone", getIntent().getStringExtra("call_phone"));
					startActivity(intent);
	            }  
	        });  
		}
	}

	private void getCallHead() {
		switch(getIntent().getIntExtra("call_position", 0)){
		case 0:
			audio_call_head.setBackgroundResource(R.drawable.head1_big);
			break;
		case 1:
			audio_call_head.setBackgroundResource(R.drawable.head2_big);
			break;
		case 2:
			audio_call_head.setBackgroundResource(R.drawable.head3_big);
			break;
		case 3:
			audio_call_head.setBackgroundResource(R.drawable.head4_big);
			break;
		case 4:
			audio_call_head.setBackgroundResource(R.drawable.head5_big);
			break;
		case 5:
			audio_call_head.setBackgroundResource(R.drawable.head6_big);
			break;
		}
		if(!"".equals(call_phone)){
			audio_call_phone.setText("绑定号码:"+call_phone);
		}else{
			audio_call_iv2.setBackgroundResource(R.drawable.phone_not);
			audio_call_iv3.setBackgroundResource(R.drawable.phone_not);
			audio_call_iv4.setBackgroundResource(R.drawable.phone_not);
			audio_call_tv2_1.setTextColor(Color.parseColor("#ACACAC"));
			audio_call_tv2_2.setTextColor(Color.parseColor("#ACACAC"));
			audio_call_tv3_1.setTextColor(Color.parseColor("#ACACAC"));
			audio_call_tv3_2.setTextColor(Color.parseColor("#ACACAC"));
			audio_call_tv4_1.setTextColor(Color.parseColor("#ACACAC"));
			audio_call_tv4_2.setTextColor(Color.parseColor("#ACACAC"));
		}
	}
	
	/**
	 * 检查网络(语音通话前)
	 * @param context
	 * @param edgeCanDial
	 * @return
	 */
	private boolean checkNetwork(Context context, boolean edgeCanDial) {
		
		int netstate = NetWorkTools.getCurrentNetWorkType(AudioCallActivity.this);
		
		if(netstate == NetWorkTools.NETWORK_ON){
			Toast.makeText(AudioCallActivity.this, NETSTATE_ERROR, Toast.LENGTH_SHORT).show();
			return false;
		}else if (netstate == NetWorkTools.NETWORK_EDGE && !edgeCanDial) {
			Toast.makeText(AudioCallActivity.this, EDGE_ERROR, Toast.LENGTH_SHORT).show();
			return false;
		}else if(edgeCanDial){
			TelephonyManager mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);  
        	int simState = mTelephonyManager.getSimState();
        	if(TelephonyManager.SIM_STATE_READY != simState){
        		Toast.makeText(AudioCallActivity.this, SIMCARD_ERROR, Toast.LENGTH_SHORT).show();
        		return false;
        	}
		}
		return true;
	}
}
