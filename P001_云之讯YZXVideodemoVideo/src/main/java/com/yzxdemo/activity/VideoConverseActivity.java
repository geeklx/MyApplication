package com.yzxdemo.activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.yzx.api.RotateType;
import com.yzx.api.UCSCall;
import com.yzx.api.UCSCameraType;
import com.yzx.tools.CustomLog;
import com.yzx.tools.FileTools;
import com.yzxdemo.R;
import com.yzxdemo.action.UIDfineAction;
import com.yzxdemo.tools.DialConfig;
import com.yzxdemo.tools.LoginConfig;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class VideoConverseActivity extends ConverseActivity{
	private LinearLayout remotelayout,locallayout;
	private ImageButton video_call_answer;
	private ImageButton video_call_hangup;
	private ImageButton converse_call_mute;
	private ImageButton converse_call_video;
	private ImageButton converse_call_speaker;
	private ImageButton converse_call_switch;
	//private ImageButton control_flashing;
	private TextView converse_information;
	private TextView converse_client;
	private TextView converse_network;
	private String phoneNumber;
	private boolean open_headset=false;
	private boolean closeVideo = false;
	private boolean inCall;
	private boolean speakerPhoneState = false;
	private Sensor sensor;
	private SensorEventListener lsn;
	private SensorManager mSensor;
	
	private BroadcastReceiver br = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			if(intent.getAction().equals(UIDfineAction.ACTION_DIAL_STATE)){
				int state = intent.getIntExtra("state", 0);
				CustomLog.i("VIDEO_CALL_STATE:"+state);
				if(UIDfineAction.dialState.keySet().contains(state)){
					converse_information.setText(UIDfineAction.dialState.get(state));
				}
				if(state == UCSCall.HUNGUP_REFUSAL 
						|| state == UCSCall.HUNGUP_MYSELF 
						|| state == UCSCall.HUNGUP_OTHER
						|| state == UCSCall.HUNGUP_MYSELF_REFUSAL){
					UCSCall.stopCallRinging();
					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							VideoConverseActivity.this.finish();
						}
					}, 1000);
				}else{
					if((state >= 300210 && state <= 300249)&&
							(state != 300221 && state != 300222 && state !=300247)
							|| state == UCSCall.HUNGUP_NOT_ANSWER){
						new Handler().postDelayed(new Runnable() {
							@Override
							public void run() {
								VideoConverseActivity.this.finish();
							}
						}, 3000);
					}
				
				}
				//对方正在响铃
				if(!inCall && state == UCSCall.CALL_VOIP_RINGING_180 ){
					UCSCall.refreshCamera(UCSCameraType.ALL);
				}
			}else if(intent.getAction().equals(UIDfineAction.ACTION_ANSWER)){
				UCSCall.setSpeakerphone(true);
				converse_call_speaker.setBackgroundResource(R.drawable.converse_speaker_down);
				UCSCall.stopCallRinging();
				UCSCall.stopRinging();
				locallayout.setVisibility(View.VISIBLE);
	        	remotelayout.setVisibility(View.VISIBLE);
	        	converse_call_mute.setVisibility(View.VISIBLE);
	        	converse_call_video.setVisibility(View.VISIBLE);
	        	converse_call_speaker.setVisibility(View.VISIBLE);
	        	converse_call_switch.setVisibility(View.VISIBLE);
	        	//control_flashing.setVisibility(View.VISIBLE);
	        	video_call_answer.setVisibility(View.GONE);
	        	open_headset = true;
	        	if(!UCSCall.isCameraPreviewStatu(VideoConverseActivity.this)){
	        		//刷新摄像头发送和接收，重要，一定要加这个
	        		mHandler.sendEmptyMessageDelayed(0, 1000);
	        	}
	        	//sendBroadcast(new Intent(UIDfineAction.ACTION_START_TIME));
			}else if(intent.getAction().equals(UIDfineAction.ACTION_CALL_TIME)){
				String timer = intent.getStringExtra("timer");
				if(converse_information != null){
					converse_information.setText(timer);
				}
			}else if(intent.getAction().equals(UIDfineAction.ACTION_NETWORK_STATE)){
				switch (intent.getIntExtra("state", 0)) {
				case 0:
					converse_network.setText("网络质量一般");
					break;
				case 1:
					converse_network.setText("网络质量好");
					break;
				case 2:
					converse_network.setText("网络质量差");
					break;
				}
			}else if(intent.getAction().equals("android.intent.action.HEADSET_PLUG")){
				//发现个别手机会接通电话前收到这个广播并把扬声器打开了，所以open_headset作为判断必须接通后再接收耳机插拔广播才有效
				if(intent.getIntExtra("state", 0) == 1 && open_headset){
					CustomLog.e("Speaker false");
					converse_call_speaker.setBackgroundResource(R.drawable.converse_speaker);
					speakerPhoneState = UCSCall.isSpeakerphoneOn();
					UCSCall.setSpeakerphone(false);
                }else if(intent.getIntExtra("state", 0) == 0 && open_headset){
                	CustomLog.e("headset unplug");
                	if(speakerPhoneState){
	                	CustomLog.e("Speaker true");
	                	converse_call_speaker.setBackgroundResource(R.drawable.converse_speaker_down);
	                    UCSCall.setSpeakerphone(true);
                	}
                }
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, 
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.activity_video_converse);
		
		IntentFilter ift = new IntentFilter();
		ift.addAction(UIDfineAction.ACTION_DIAL_STATE);
		ift.addAction(UIDfineAction.ACTION_ANSWER);
		ift.addAction(UIDfineAction.ACTION_CALL_TIME);
		ift.addAction(UIDfineAction.ACTION_NETWORK_STATE);
		ift.addAction("android.intent.action.HEADSET_PLUG");
		registerReceiver(br, ift);
		LoginConfig.saveCurrentCall(VideoConverseActivity.this,2);
		
		initviews();
		monitorRoation();
		//初始化视频
		UCSCall.initCameraConfig(VideoConverseActivity.this, remotelayout, locallayout);
		//设置视频参数
		//VideoEncParam.getInstance().usStartBitrate = 500;
		//VideoEncParam.getInstance().usMaxBitrate = 500;
		//VideoEncParam.getInstance().usMinBitrate = 30;
		//将参数设置到SDK
		//UCSCall.setVideoAttr(VideoDecParam.getInstance(), VideoEncParam.getInstance());
		
		DialConfig.saveCallType(this, "2");
		if(getIntent().hasExtra("phoneNumber")){
			phoneNumber = getIntent().getStringExtra("phoneNumber");
		}

        if(getIntent().hasExtra("inCall")){
			inCall = getIntent().getBooleanExtra("inCall", false);
		}
        
        converse_client.setText(phoneNumber);
        
        
        if(inCall){//来电
			video_call_hangup.setVisibility(View.VISIBLE);
			video_call_answer.setVisibility(View.VISIBLE);
			mHandler.sendEmptyMessageDelayed(0, 1000);
			converse_information.setText("视频电话来电");
			UCSCall.setSpeakerphone(true);
			UCSCall.startRinging(true);
        }else{
        	video_call_answer.setVisibility(View.GONE);
        	video_call_hangup.setVisibility(View.VISIBLE);
        	dial();
        	converse_information.setText("正在呼叫");
        	//UCSCall.refreshCamera(UCSCameraType.ALL);
        	locallayout.setVisibility(View.VISIBLE);
        	remotelayout.setVisibility(View.VISIBLE);
        }
        //默认一开始使用前摄像头 0：后摄像头,1:前摄像头
    	UCSCall.switchCameraDevice(1,RotateType.DEFAULT);
    	if(inCall){
    		new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					
				}
			}, 3000);
    		
    	}
	}

	private void initviews() {
		remotelayout = (LinearLayout) findViewById(R.id.remotelayout);
		locallayout = (LinearLayout) findViewById(R.id.locallayout);
		video_call_answer = (ImageButton) findViewById(R.id.video_call_answer);
		video_call_hangup = (ImageButton) findViewById(R.id.video_call_hangup);
		converse_call_mute = (ImageButton) findViewById(R.id.converse_call_mute);
		converse_call_video = (ImageButton) findViewById(R.id.converse_call_video);
		converse_call_speaker = (ImageButton) findViewById(R.id.converse_call_speaker);
		converse_call_switch = (ImageButton) findViewById(R.id.converse_call_switch);
		//control_flashing =(ImageButton) findViewById(R.id.control_flashing);
		converse_client = (TextView) findViewById(R.id.converse_client);
		converse_information = (TextView) findViewById(R.id.converse_information);
		converse_network = (TextView) findViewById(R.id.converse_network);
		//静音
		converse_call_mute.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(UCSCall.isMicMute()){
					converse_call_mute.setBackgroundResource(R.drawable.converse_mute);
				}else{
					converse_call_mute.setBackgroundResource(R.drawable.converse_mute_down);
				}
				UCSCall.setMicMute(!UCSCall.isMicMute());
			}
		});
		
		//摄像头开关
		converse_call_video.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(closeVideo){
					//开启自己的摄像头
					closeVideo = false;
					converse_call_video.setBackgroundResource(R.drawable.converse_video);
					UCSCall.openCamera(UCSCameraType.LOCALCAMERA);
				}else{
					//关闭自己的摄像头
					closeVideo = true;
					converse_call_video.setBackgroundResource(R.drawable.converse_video_down);
					UCSCall.closeCamera(UCSCameraType.LOCALCAMERA);
				}
			}
		});
		
		
		//扬声器
		converse_call_speaker.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(UCSCall.isSpeakerphoneOn()){
					converse_call_speaker.setBackgroundResource(R.drawable.converse_speaker);
				}else{
					converse_call_speaker.setBackgroundResource(R.drawable.converse_speaker_down);
				}
				UCSCall.setSpeakerphone(!UCSCall.isSpeakerphoneOn());
			}
		});
		
		//闪关灯 控制
		/*control_flashing.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				CustomLog.v("控制闪光灯调用... ");
				UCSCall.setFlashCode(true);
			}
		});*/
		converse_call_speaker.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
				//String localName = "local_"+phoneNumber+"_"+format.format(new Date())+".jpg";
				String remoteName = "remote_"+phoneNumber+"_"+format.format(new Date())+".jpg";
				//UCSCall.videoCapture(UCSCameraType.LOCALCAMERA, localName, FileTools.getSdCardFilePath());
				UCSCall.videoCapture(UCSCameraType.REMOTECAMERA, remoteName, FileTools.getSdCardFilePath());
				return false;
			}
		});	
		//切换摄像头
		converse_call_switch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CustomLog.e("当前摄像头："+UCSCall.getCurrentCameraIndex());
				if(UCSCall.getCurrentCameraIndex()==0){  // 横屏状态是RETATE_0
					UCSCall.switchCameraDevice(1,RotateType.DEFAULT);
				}else{
					UCSCall.switchCameraDevice(0,RotateType.DEFAULT);
				}
			}
		});
		//接通
		video_call_answer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CustomLog.v("接通电话");
				UCSCall.stopRinging();
				UCSCall.answer("");
				UCSCall.setSpeakerphone(false);
			}
		});
						
		//挂断
		video_call_hangup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DialConfig.saveCallType(VideoConverseActivity.this, "");
				UCSCall.stopRinging();
				UCSCall.hangUp("");
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						VideoConverseActivity.this.finish();
					}
				}, 1500);
			}
		});
	}

	private void dial() {
		if (phoneNumber != null && phoneNumber.length() > 0) {
			UCSCall.setSpeakerphone(false);
			sendBroadcast(new Intent(UIDfineAction.ACTION_DIAL).putExtra(UIDfineAction.CALL_UID, phoneNumber).putExtra("type",3));
		}
	}
	
	@Override
	protected void onDestroy() {
		DialConfig.saveCallType(this, "");
		mHandler.sendEmptyMessageDelayed(1, 100);
		UCSCall.stopRinging();
		UCSCall.stopCallRinging();
		unregisterReceiver(br);
		mSensor.unregisterListener(lsn);
		super.onDestroy();
	}

	
	@Override
	protected void onResume() {
		mHandler.sendEmptyMessageDelayed(0, 1000);
		super.onResume();
	}
	
	@Override
	protected void onRestart() {
		//mHandler.sendEmptyMessageDelayed(0, 1000);
		super.onRestart();
	}

	@Override
	protected void onPause() {
		mHandler.sendEmptyMessageDelayed(1, 1000);
		super.onPause();
	}
	
	private Handler mHandler = new Handler(){
		@Override
		public void dispatchMessage(Message msg) {
			switch(msg.what){
			case 0:
				converse_call_video.setBackgroundResource(R.drawable.converse_video);
				UCSCall.refreshCamera(UCSCameraType.ALL);
				CustomLog.i("Video status refresh ....");
				break;
			case 1:
				UCSCall.closeCamera(UCSCameraType.ALL);
				CustomLog.v("Video status close ....");
				break;
			}
		}
	};

	@Override
	public void finish() {
		LoginConfig.saveCurrentCall(VideoConverseActivity.this,0);
		super.finish();
	}


	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
	}
	
	/**
	 * 控制视频通话页面
	 * @author maoyuanqing	
	 * @data 2015-7-30
	 */
	private void monitorRoation() {
		mSensor = (SensorManager) getSystemService(SENSOR_SERVICE);
		sensor = mSensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		lsn = new SensorEventListener() {

			@Override
			public void onSensorChanged(SensorEvent e) {
				float x, y, z;
				x = e.values[SensorManager.DATA_X];
				y = e.values[SensorManager.DATA_Y];
				z = e.values[SensorManager.DATA_Z];
				setTitle("x=" + (int) x + "," + "y=" + (int) y + "," + "z=" + (int) z);
				if (y > 5)
					UCSCall.videoSetSendReciveRotation(0, 0);
				else if (x > 5)
					UCSCall.videoSetSendReciveRotation(90, 90);
				else if (x < -5)
					UCSCall.videoSetSendReciveRotation(270, 270);
				else if (y < -5)
					UCSCall.videoSetSendReciveRotation(180, 180);
			
			}

			@Override
			public void onAccuracyChanged(Sensor arg0, int arg1) {
			}
		};
		mSensor.registerListener(lsn, sensor, SensorManager.SENSOR_DELAY_GAME);
	}
}
