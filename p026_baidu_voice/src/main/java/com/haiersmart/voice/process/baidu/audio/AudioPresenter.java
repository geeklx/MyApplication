package com.haiersmart.voice.process.baidu.audio;//package com.haiersmart.voice.process.baidu.audio;
//
//import android.app.Activity;
//import android.content.ComponentName;
//import android.content.Intent;
//import android.content.ServiceConnection;
//import android.graphics.drawable.BitmapDrawable;
//import android.os.IBinder;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.PopupWindow;
//import android.widget.TextView;
//
//import com.haiersmart.sfnation.R;
//import com.haiersmart.voice.process.baidu.BaiduEmotionProcessUtil;
//import com.haiersmart.voice.service.MusicPlayerService;
//import com.haiersmart.voice.utils.VoiceEmotionContants;
//import com.playerview.MusicPlayerView;
//
//import java.util.ArrayList;
//
//
///**
// * @作者:gaoruishan
// * @时间:2017/2/9/19:02
// * @邮箱:grs0515@163.com
// */
//
//public class AudioPresenter {
//
//	private final Activity activity;
//	private ArrayList<String> musicList = new ArrayList<String>();
//	private ArrayList<String> coverList = new ArrayList<String>();
//	private ArrayList<String> musicTitles = new ArrayList<String>();
//	private MusicPlayerView mMpv;
//	private TextView mTvTitle;
////	private Button btnStop, btnBack;
//	private PopupWindow popupWindow;
//	private boolean flag;
//	private MusicPlayerService.MyBinder mBinder;
//
//
//	public AudioPresenter(Activity activity) {
//		this.activity = activity;
//	}
//
//	public PopupWindow getPopupWindow(String mp3_audio_url, String cover_url, String title) {
//		musicList.clear();
//		coverList.clear();
//		musicTitles.clear();
//		musicList.add(mp3_audio_url);
//		coverList.add(cover_url);
//		musicTitles.add(title);
//		View contentView = LayoutInflater.from(activity).inflate(R.layout.audio_player_layout, null);
//		contentView.setFocusable(true);
//		contentView.setFocusableInTouchMode(true);
//		mMpv = (MusicPlayerView) contentView.findViewById(R.id.mpv);
//		mMpv.setEnabled(false);
//		mMpv.setProgressVisibility(false);
//		if (!TextUtils.isEmpty(cover_url)) {
//			mMpv.setCoverURL(cover_url);
//		}else {
//			mMpv.setCoverDrawable(R.drawable.album_default);
//		}
//
//		mMpv.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				mMpv.isPrepare(mBinder.isPrepare());
//				if (mMpv.isRotating()) {
//					if (flag) {
//						mMpv.stop();
//						mBinder.musicPause();
//						BaiduEmotionProcessUtil.getEmotionProcessUtil().processEmotion(VoiceEmotionContants.EMOTION_DEFAULT);
//					}
//
//				} else {
//					if (flag) {
//						mMpv.start();
//						mBinder.musicStart();
//						BaiduEmotionProcessUtil.getEmotionProcessUtil().processEmotion(VoiceEmotionContants.EMOTION_MUSIC);
//					}
//				}
//			}
//		});
////		btnStop = (Button) contentView.findViewById(R.id.btn_stop);
////		btnStop.setOnClickListener(new View.OnClickListener() {
////			@Override
////			public void onClick(View view) {
////
////			}
////		});
////		btnBack = (Button) contentView.findViewById(btn_back);
////		btnBack.setOnClickListener(new View.OnClickListener() {
////			@Override
////			public void onClick(View view) {
////
////			}
////		});
//		mTvTitle = (TextView) contentView.findViewById(R.id.tv_title_music);
//		if (!TextUtils.isEmpty(title)) {
//			mTvTitle.setText(title);
//		}
//		popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
//		popupWindow.setFocusable(true);
//		popupWindow.setOutsideTouchable(true);
//		popupWindow.setBackgroundDrawable(new BitmapDrawable());//点击window外围有响应
//		popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//			@Override
//			public void onDismiss() {
//				dismiss();
//			}
//		});
////        popupWindow.setAnimationStyle(R.style.pop_ani);
//		// 点击其他地方消失
//		contentView.setOnTouchListener(new View.OnTouchListener() {
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				dismiss();
//				return false;
//			}
//		});
//		bindService();
////		popupWindow.showAsDropDown(mIvMainVoiceRec);
////		popupWindow.showAsDropDown(view,280,0);
//		return popupWindow;
//	}
//
//	private void bindService() {
//		Intent intent = new Intent(activity, MusicPlayerService.class);
//		intent.putStringArrayListExtra("musicList", musicList);
//		activity.startService(intent);
//		activity.bindService(intent, conn, activity.BIND_AUTO_CREATE);
//	}
//
//	public void dismiss() {
//		BaiduEmotionProcessUtil.getEmotionProcessUtil().processEmotion(VoiceEmotionContants.EMOTION_DEFAULT);
//		if (conn != null) {
//			activity.unbindService(conn);
//			activity.stopService(new Intent(activity, MusicPlayerService.class));
//		}
//		if (mMpv != null)
//			mMpv.stop();
//		if (popupWindow != null && popupWindow.isShowing()) {
//			popupWindow.dismiss();
//			popupWindow = null;
//		}
//	}
//
//	ServiceConnection conn = new ServiceConnection() {
//		@Override
//		public void onServiceConnected(ComponentName name, IBinder service) {
//			mBinder = (MusicPlayerService.MyBinder) service;
//			flag = true;
//		}
//
//		@Override
//		public void onServiceDisconnected(ComponentName name) {
//
//		}
//	};
//
//
//	public MusicPlayerView getMpv() {
//		return mMpv;
//	}
//
//	public TextView getTvTitle() {
//		return mTvTitle;
//	}
//
//	public MusicPlayerService.MyBinder getBinder() {
//		return mBinder;
//	}
//
//	public ArrayList<String> getCoverList() {
//		return coverList;
//	}
//
//	public ArrayList<String> getMusicTitles() {
//		return musicTitles;
//	}
//}
