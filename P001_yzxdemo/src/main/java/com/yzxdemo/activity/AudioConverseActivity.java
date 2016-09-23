package com.yzxdemo.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yzx.api.UCSCall;
import com.yzx.tools.CustomLog;
import com.yzxdemo.R;
import com.yzxdemo.action.UIDfineAction;
import com.yzxdemo.tools.DfineAction;
import com.yzxdemo.tools.DialConfig;
import com.yzxdemo.tools.LoginConfig;

public class AudioConverseActivity extends ConverseActivity{

	public String calledUid ;
	public String calledPhone ;
	public boolean inCall = false;
	private TextView converse_client;
	private TextView converse_information, audio_converse_network;
	private TextView dial_close;
	private EditText dial_number;
	private AudioManager mAudioManager;
	private ImageButton converse_call_mute;
	private ImageButton converse_call_dial;
	private ImageButton converse_call_speaker;
	private ImageButton converse_call_hangup;
	private ImageButton converse_call_answer;
	private ImageButton converse_call_endcall;
	private ImageButton dial_endcall;
	private LinearLayout key_layout;
	private LinearLayout converse_main;
	private boolean open_headset=false;
	private int max = 0 ;
	private int current= 0;
	private int calltype = 2;
	private boolean speakerPhoneState = false;
	private int sound;	// 触摸提示音的状态，0：关，1：开
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_audio_converse);
		initviews();
		
		mAudioManager = ((AudioManager) getSystemService(Context.AUDIO_SERVICE));
		max = mAudioManager.getStreamMaxVolume( AudioManager.STREAM_VOICE_CALL );
		current = mAudioManager.getStreamVolume( AudioManager.STREAM_VOICE_CALL );
		try {
			//如果系统触摸音是关的就不用管，开的就把它给关掉，因为在个别手机上有可能会影响音质
			sound = Settings.System.getInt(getContentResolver(), Settings.System.SOUND_EFFECTS_ENABLED);
			CustomLog.v("AudioConverseActivity sound: " + sound);
			if(sound == 1) {
				Settings.System.putInt(getContentResolver(), Settings.System.SOUND_EFFECTS_ENABLED, 0); 
				mAudioManager.unloadSoundEffects();	
			}
		} catch (SettingNotFoundException e) {
			e.printStackTrace();  
			CustomLog.v("SettingNotFound SOUND_EFFECTS_ENABLED ...");
		}
		IntentFilter ift = new IntentFilter();
		ift.addAction(UIDfineAction.ACTION_DIAL_STATE);
		ift.addAction(UIDfineAction.ACTION_CALL_BACK);
		ift.addAction(UIDfineAction.ACTION_ANSWER);
		ift.addAction(UIDfineAction.ACTION_CALL_TIME);
		ift.addAction(UIDfineAction.ACTION_DIAL_HANGUP);
		ift.addAction("android.intent.action.HEADSET_PLUG");
		registerReceiver(br, ift);
		if(getIntent().hasExtra("call_type")){
			calltype = getIntent().getIntExtra("call_type", 2);
		}
		
		//判断是否是来电信息，默认是去电，（来电信息是由ConnectionService中的onIncomingCall回调中发送广播，开启通话界面，inCall为true）
		if(getIntent().hasExtra("inCall")){
			inCall = getIntent().getBooleanExtra("inCall", false);
		}
		
		//获得要拨打的号码，智能拨打和免费通话：phoneNumber代表ClientID，直拨和回拨代表ClientID绑定的手机号码
		if(getIntent().hasExtra("call_client")){
			calledUid = getIntent().getStringExtra("call_client");
		}
		if(getIntent().hasExtra("call_phone")){
			calledPhone = getIntent().getStringExtra("call_phone");
		}
		DialConfig.saveCallType(this, "1");
		if(inCall){
			//来电
			if("".equals(getIntent().getStringExtra("nickName"))){
				converse_client.setText(getIntent().getStringExtra("phoneNumber"));
			}else {
				converse_client.setText(getIntent().getStringExtra("nickName"));
			}
			converse_call_answer.setVisibility(View.VISIBLE);
			converse_call_hangup.setVisibility(View.VISIBLE);
			converse_call_endcall.setVisibility(View.GONE);
			UCSCall.setSpeakerphone(true);
			UCSCall.startRinging(true);
		}else{
			//去电
			converse_call_answer.setVisibility(View.GONE);
			converse_call_hangup.setVisibility(View.VISIBLE);
			converse_call_endcall.setVisibility(View.GONE);
			//进行拨号
			dial();
			if(calltype == 1){
				converse_information.setText("免费电话呼叫中");
			}else if(calltype == 0){
				converse_information.setText("直拨电话呼叫中");
			}else if(calltype == 5){
				converse_information.setText("智能电话呼叫中");
			}else{
				converse_information.setText("回拨呼叫中");
			}
		}
	}

	private void initviews() {
		converse_client = (TextView) findViewById(R.id.converse_client);
		converse_information = (TextView) findViewById(R.id.converse_information);
		audio_converse_network = (TextView) findViewById(R.id.audio_converse_network);
		if(getIntent().getStringExtra("call_client") != null){
			converse_client.setText(getIntent().getStringExtra("call_client"));
		}else {
			converse_client.setText(getIntent().getStringExtra("call_phone"));
		}
		converse_call_mute = (ImageButton) findViewById(R.id.converse_call_mute);
		converse_main = (LinearLayout) findViewById(R.id.converse_main);
		key_layout = (LinearLayout) findViewById(R.id.key_layout);
		dial_endcall = (ImageButton) findViewById(R.id.dial_endcall);
		dial_number = (EditText) findViewById(R.id.text_dtmf_number);
		
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
		
		//扬声器
		converse_call_speaker = (ImageButton)findViewById(R.id.converse_call_speaker);
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
		
		//接通
		converse_call_answer = (ImageButton)findViewById(R.id.audio_call_answer);
		converse_call_answer.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CustomLog.v(DfineAction.TAG_TCP,"接通电话");
				UCSCall.stopRinging();
				UCSCall.answer("");
				UCSCall.setSpeakerphone(false);
			}
		});
				
		//挂断
		converse_call_hangup = (ImageButton)findViewById(R.id.audio_call_hangup);
		converse_call_hangup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DialConfig.saveCallType(AudioConverseActivity.this, "");
				UCSCall.stopRinging();
				UCSCall.hangUp("");
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						AudioConverseActivity.this.finish();
					}
				}, 1500);
			}
		});
		
		//结束通话
		converse_call_endcall  = (ImageButton)findViewById(R.id.audio_call_endcall);
		converse_call_endcall.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CustomLog.v(DfineAction.TAG_TCP,"结束电话");
				UCSCall.stopRinging();
				UCSCall.setSpeakerphone(false);
				UCSCall.hangUp("");
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						AudioConverseActivity.this.finish();
					}
				}, 1500);
			}
		});
		
		//结束通话（键盘界面中的按钮）
		dial_endcall  = (ImageButton)findViewById(R.id.dial_endcall);
		dial_endcall.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CustomLog.v(DfineAction.TAG_TCP, "结束电话");
				UCSCall.stopRinging();
				UCSCall.setSpeakerphone(false);
				UCSCall.hangUp("");
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						AudioConverseActivity.this.finish();
					}
				}, 1500);
			}
		});
		//打开键盘
		converse_call_dial  = (ImageButton)findViewById(R.id.converse_call_dial);
		converse_call_dial.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CustomLog.v(DfineAction.TAG_TCP,"打开键盘");
				key_layout.setVisibility(View.VISIBLE);
				converse_main.setVisibility(View.GONE);
			}
		});
		
		//关闭键盘
		dial_close = (TextView) findViewById(R.id.dial_close);
		dial_close.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CustomLog.v(DfineAction.TAG_TCP,"关闭键盘");
				key_layout.setVisibility(View.GONE);
				converse_main.setVisibility(View.VISIBLE);
			}
		});
		findViewById(R.id.digit0).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				UCSCall.sendDTMF(AudioConverseActivity.this, KeyEvent.KEYCODE_0,dial_number);
			}
		});
		findViewById(R.id.digit1).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				UCSCall.sendDTMF(AudioConverseActivity.this, KeyEvent.KEYCODE_1,dial_number);
			}
		});
		findViewById(R.id.digit2).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				UCSCall.sendDTMF(AudioConverseActivity.this, KeyEvent.KEYCODE_2,dial_number);
			}
		});
		findViewById(R.id.digit3).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				UCSCall.sendDTMF(AudioConverseActivity.this, KeyEvent.KEYCODE_3,dial_number);
			}
		});
		findViewById(R.id.digit4).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				UCSCall.sendDTMF(AudioConverseActivity.this, KeyEvent.KEYCODE_4,dial_number);
			}
		});
		findViewById(R.id.digit5).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				UCSCall.sendDTMF(AudioConverseActivity.this, KeyEvent.KEYCODE_5,dial_number);
			}
		});
		findViewById(R.id.digit6).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				UCSCall.sendDTMF(AudioConverseActivity.this, KeyEvent.KEYCODE_6,dial_number);
			}
		});
		findViewById(R.id.digit7).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				UCSCall.sendDTMF(AudioConverseActivity.this, KeyEvent.KEYCODE_7,dial_number);
			}
		});
		findViewById(R.id.digit8).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				UCSCall.sendDTMF(AudioConverseActivity.this, KeyEvent.KEYCODE_8,dial_number);
			}
		});
		findViewById(R.id.digit9).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				UCSCall.sendDTMF(AudioConverseActivity.this, KeyEvent.KEYCODE_9,dial_number);
			}
		});
		findViewById(R.id.digit_star).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				UCSCall.sendDTMF(AudioConverseActivity.this, KeyEvent.KEYCODE_STAR,dial_number);
			}
		});
		findViewById(R.id.digit_husa).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				UCSCall.sendDTMF(AudioConverseActivity.this, KeyEvent.KEYCODE_POUND,dial_number);
			}
		});
	}
	
	//免费通话、直拨、回拨和智能拨打  （智能拨打和免费通话：phoneNumber代表ClientID，直拨和回拨代表ClientID绑定的手机号码
	public void dial(){
		UCSCall.setSpeakerphone(false);
		//UCSCall.startCallRinging("dialling_tone.pcm");
		Intent intent = new Intent(UIDfineAction.ACTION_DIAL);
		//回拨中要用到FROM_NUM_KEY（主叫显号）和TO_NUM_KEY（被叫显号），仅仅作为回拨的显示用：可以为空
		if(getIntent().hasExtra(UIDfineAction.FROM_NUM_KEY)){
			intent.putExtra(UIDfineAction.FROM_NUM_KEY, getIntent().getStringExtra(UIDfineAction.FROM_NUM_KEY));
		}
		if(getIntent().hasExtra(UIDfineAction.TO_NUM_KEY)){
			intent.putExtra(UIDfineAction.TO_NUM_KEY, getIntent().getStringExtra(UIDfineAction.TO_NUM_KEY));
		}
		
		//type:
		//		0：直拨
		//		1：免费
		//		2:回拨
		//		3:视频点对点
		//		4:会议
		//      5:智能拨打
		switch (calltype) {
		//视频点对点、免费通话
		case 0:
			//直拨传入电话号码
			sendBroadcast(intent.putExtra(UIDfineAction.CALL_PHONE, calledPhone).putExtra("type", calltype));
			break;
		case 1:
			//免费传入clientid
			sendBroadcast(intent.putExtra(UIDfineAction.CALL_UID, calledUid).putExtra("type", calltype));
			break;
		case 2:
			//回拨传入电话号码
			sendBroadcast(intent.putExtra(UIDfineAction.CALL_PHONE, calledPhone).putExtra("type", calltype));
			break;
		case 3:
			//视频点对点 传入clientid
			sendBroadcast(intent.putExtra(UIDfineAction.CALL_UID, calledPhone).putExtra("type", calltype));
			break;
		case 5:
			//智能拨打 clientid和电话号码同时传入，先选择clientid进行免费拨打，如果对方不在线再选择手机号码进行直拨
			sendBroadcast(intent.putExtra(UIDfineAction.CALL_UID, calledUid).putExtra(UIDfineAction.CALL_PHONE, calledPhone).putExtra("type", calltype));
			break;
		}
	}
	
	
	private BroadcastReceiver br = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			if(intent.getAction().equals(UIDfineAction.ACTION_DIAL_STATE)){
				int state = intent.getIntExtra("state", 0);
				CustomLog.v(DfineAction.TAG_TCP, "AUDIO_CALL_STATE:"+state);
				if(UIDfineAction.dialState.keySet().contains(state)){
					CustomLog.v(state+UIDfineAction.dialState.get(state));
					//获得通话状态信息
					converse_information.setText(UIDfineAction.dialState.get(state));
				}else{
					if(state >= 300233 && state <= 300243){
						switch(state){
						//case 300010:session过期
						case 300211:
							converse_information.setText("余额不足");
							break;
						case 300219:
							converse_information.setText("不能拨打自己绑定号码");
							break;
						case 300233:
							converse_information.setText("回拨主叫没有绑定手机号码");
							break;
						case 300234:
							converse_information.setText("回拨绑定手机号码异常");
							break;
						case 300235:
							converse_information.setText("回拨鉴权错误");
							break;
						case 300236:
							converse_information.setText("回拨IO错误");
							break;
						case 300237:
							converse_information.setText("回拨请求成功但反回JSON错误");
							break;
						case 300238:
							converse_information.setText("回拨请求超时");
							break;
						case 300239:
							converse_information.setText("回拨服务器繁忙");
							break;
						case 300240:
							converse_information.setText("回拨服务器内部错误");
							break;
						case 300241:
							converse_information.setText("回拨被叫号码错误");
							break;
						case 300242:
							converse_information.setText("充值后才可以拨打国际电话");
							break;
						case 300243:
							converse_information.setText("回拨未知错误");
							break;
						default:
							converse_information.setText("回拨未知错误");
							break;
						}    
					}
				}
				if((calltype == 0 && state == UCSCall.CALL_VOIP_RINGING_180)
						|| (calltype == 5 && state == UCSCall.CALL_VOIP_TRYING_183)){
					stopRing180();
					UCSCall.stopRinging();
				}
				if(state == UCSCall.HUNGUP_REFUSAL
						|| state == UCSCall.HUNGUP_MYSELF
						|| state == UCSCall.HUNGUP_OTHER
						|| state == UCSCall.HUNGUP_MYSELF_REFUSAL){
					CustomLog.v("收到挂断信息");
					UCSCall.stopRinging();
					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							AudioConverseActivity.this.finish();
						}
					}, 1500);
				}else{
					if((state >= 300210 && state <= 300249)&&
							(state != 300221 && state != 300222 && state !=300247)
							|| state == UCSCall.HUNGUP_NOT_ANSWER){
						new Handler().postDelayed(new Runnable() {
							@Override
							public void run() {
								AudioConverseActivity.this.finish();
							}
						}, 1000);
					}
				}
			}else if(intent.getAction().equals(UIDfineAction.ACTION_CALL_BACK)){
				//回拨成功后关闭回铃音，1.5秒后推出Activity，等待回拨电话
				converse_information.setText("回拨成功");
				CustomLog.e("回拨成功后关闭回铃音，1.5秒后推出Activity，等待回拨电话");
				stopRing180();
				DialConfig.saveCallType(AudioConverseActivity.this,"");
				UCSCall.stopRinging();
				DialConfig.saveCallType(AudioConverseActivity.this,"");
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						AudioConverseActivity.this.finish();
					}
				}, 1500);
			}else if(intent.getAction().equals(UIDfineAction.ACTION_ANSWER)){
				//接通后通知服务开启计时器
				//sendBroadcast(new Intent(UIDfineAction.ACTION_START_TIME));
				//接通后关闭回铃音
				converse_call_answer.setVisibility(View.GONE);
				converse_call_hangup.setVisibility(View.GONE);
				converse_call_endcall.setVisibility(View.VISIBLE);
				stopRing180();
				UCSCall.setSpeakerphone(false);
				converse_call_speaker.setBackgroundResource(R.drawable.converse_speaker);
				open_headset = true;
			}else if(intent.getAction().equals(UIDfineAction.ACTION_DIAL_HANGUP)){
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						AudioConverseActivity.this.finish();
					}
				}, 1500);
			}else if(intent.getAction().equals(UIDfineAction.ACTION_CALL_TIME)){
				String timer = intent.getStringExtra("timer");
				if(converse_information != null){
					converse_information.setText(timer);
				}
			} else if(intent.getAction().equals(UIDfineAction.ACTION_NETWORK_STATE)){
				switch (intent.getIntExtra("state", -1)) {
//				case 0:
//					converse_network.setText("无法获取网络状态");
//					break;
				case 1:
					audio_converse_network.setText("网络状态极好");
					break;
				case 2:
					audio_converse_network.setText("网络状态良好");
					break;
				case 3:
					audio_converse_network.setText("网络状态一般");
					break;
				case 4:
					audio_converse_network.setText("网络状态极差");
					break;
				case 10:
					audio_converse_network.setText("对端视频处于远程视频模式");
					break;
				}
			} else if(intent.getAction().equals("android.intent.action.HEADSET_PLUG")){
				//发现个别手机会接通电话前收到这个广播并把扬声器打开了，所以open_headset作为判断必须接通后再接收耳机插拔广播才有效
				if(intent.getIntExtra("state", 0) == 1 && open_headset){
					CustomLog.e(DfineAction.TAG_TCP,"Speaker false");
					converse_call_speaker.setBackgroundResource(R.drawable.converse_speaker);
					speakerPhoneState = UCSCall.isSpeakerphoneOn();
					UCSCall.setSpeakerphone(false);
                }else if(intent.getIntExtra("state", 0) == 0 && open_headset){//headset disconnected
                	//CustomLog.e("headset unplug");
                	if(speakerPhoneState){
                		//CustomLog.e("Speaker true");
                		converse_call_speaker.setBackgroundResource(R.drawable.converse_speaker_down);
                		UCSCall.setSpeakerphone(true);
                	}
                } 
			}
		}
	};
	
	
	public void stopRing180(){
		UCSCall.stopCallRinging();
	}
	
	@Override
	public void finish() {
		LoginConfig.saveCurrentCall(this,0);
		super.finish();
	}
	
	
	@Override
	protected void onDestroy() {
		unregisterReceiver(br);
		DialConfig.saveCallType(this, "");
		stopRing180();
		if(sound == 1) {	// 如果系统触摸提示音是开的，前面把它给关系，现在退出页面要把它还原
			Settings.System.putInt(getContentResolver(), Settings.System.SOUND_EFFECTS_ENABLED, 1); 
			mAudioManager.loadSoundEffects();	
		}
		super.onDestroy();
	}
}
