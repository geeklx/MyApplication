package com.haiersmart.voice.service;//package com.haiersmart.voice.service;
//
//import android.app.Service;
//import android.content.Intent;
//import android.content.ServiceConnection;
//import android.media.MediaPlayer;
//import android.os.Binder;
//import android.os.IBinder;
//import android.text.TextUtils;
//import android.util.Log;
//
//import com.haiersmart.voice.bean.event.ChooseEvent;
//import com.haiersmart.voice.bean.event.MessageEvent;
//import com.haiersmart.voice.process.engine.SpeechHandleIntermediary;
//import com.haiersmart.voice.utils.PreparedCallBack;
//
//import org.greenrobot.eventbus.EventBus;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class MusicPlayerService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {
//	private String TAG = getClass().getSimpleName();
//	private MediaPlayer mPlayer = new MediaPlayer();
//	;
//	private String mPath;
////	private ArrayList<String> musicList;
//	private int current;
//	private boolean flag;
//	private boolean isPrepared;
//	private PreparedCallBack mCallBack;
//	private boolean isError;
//	private int errorTimes;
//	private MyBinder myBinder = new MyBinder();
//	public MediaPlayer getmPlayer() {
//		return mPlayer;
//	}
//
//	// 服务的生命周期中  创建的方法
//	@Override
//	public void onCreate() {
//		super.onCreate();
//		mPlayer.setOnPreparedListener(this);
//		mPlayer.setOnCompletionListener(this);
//		mPlayer.setOnErrorListener(this);
//		current = 0;
//		flag = true;
//	}
//
//	//销毁
//	@Override
//	public void onDestroy() {
//		super.onDestroy();
//		//在Activity 和其他有生命周期的一些控件里面发出网络请求，
//		// 务必要在 这个 Activity或者控件销毁的时候 取消网络请求
//		// 让后释放各种资源  释放资源的时候可以利用try catch 捕捉 程序所有异常
//		flag = false;
//		try {
//			if (mPlayer != null) {
//				if (mPlayer.isPlaying()) {
//					mPlayer.pause();
//				}
//				// 释放流 释放资源
//				mPlayer.release();
//				mPlayer = null;
//			}
//		} catch (Exception e) {
//		}
//	}
//	//这个方法主要是在用bind的方式开启服务的时候调用
//	@Override
//	public IBinder onBind(Intent intent) {
//		Log.e(TAG, "onBind: 音乐服务绑定成功" );
//		return myBinder;
//	}
//
//	public class MyBinder extends Binder implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {
//
//		public MusicPlayerService getService() {
//			return MusicPlayerService.this;
//		}
//
//		public void musicContinue(){
//			if (mPlayer != null) {
//				mPlayer.start();
//			}
//		}
//		public void musicStart(String url) {
//
//			if (mPlayer != null) {
//				Log.e(TAG, "musicStart: 开始播放" );
//				try {
//					mPlayer.setDataSource(url);
//					mPlayer.prepareAsync();
//				} catch (IOException e) {
//				}
//			}else {
//				Log.e(TAG, "musicStart: 创建新的MediaPlayer ---> 开始播放" );
//				mPlayer = new MediaPlayer();
//				try {
//					mPlayer.setOnPreparedListener(this);
//					mPlayer.setDataSource(url);
//					mPlayer.prepareAsync();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//		public void musicPause() {
//			if (mPlayer != null) {
//				Log.e(TAG, "musicPause: 音乐暂停" );
//				mPlayer.pause();
//			}
//		}
//		public void musicNext(String url){
//			if (mPlayer != null) {
//				Log.e(TAG, "musicNext: 播放下一曲" );
//				mPlayer.stop();
//				mPlayer.reset();
//				try {
//					mPlayer.setDataSource(url);
//					mPlayer.prepareAsync();
//				} catch (IOException e) {
//				}
//
//			}else {
//				mPlayer = new MediaPlayer();
//				try {
//					mPlayer.setOnPreparedListener(this);
//					mPlayer.setOnCompletionListener(this);
//					mPlayer.setDataSource(url);
//					mPlayer.prepareAsync();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		public void musicStop(){
//			if (mPlayer != null) {
//				Log.e(TAG, "musicStop: 音乐停止" );
//				if (mPlayer.isPlaying()) {
//					mPlayer.stop();
//				}
//
//			}
//		}
//		public void musicDestroy(){
//			Log.e(TAG, "musicDestroy: MediaPlayer销毁" );
//			if (mPlayer!=null) {
//
//				if (mPlayer.isPlaying()) {
//					mPlayer.stop();
//					Log.e(TAG, "musicDestroy: mPlayer.stop()" );
//				}
//			}
//			if (mPlayer!=null){
//				mPlayer.setOnCompletionListener(null);
//				mPlayer.setOnPreparedListener(null);
//				mPlayer.release();
//				mPlayer = null;
//				Log.e(TAG, "musicDestroy: MediaPlayer 设置为==== null" );
//			}
//
//		}
//
//		public boolean isService() {
//			return flag;
//		}
//		public boolean isPrepared(){
//			return isPrepared;
//		}
//
//		@Override
//		public void onPrepared(MediaPlayer mp) {
//			Log.d(TAG, "onPrepared: " + "new MediaPlayer 准备好了开始播放");
//			mp.start();
//			isPrepared = true;
//			if (mCallBack != null) {
//				mCallBack.isPrepared(true);
//			}
//		}
//
//		@Override
//		public void onCompletion(MediaPlayer mp) {
//			if (mp!=null&&mp.isPlaying()) {
//				mp.stop();
//				mp.release();
//				mPlayer = null;
//			}
//			isPrepared = false;
//			if (mCallBack != null) {
//				mCallBack.isPrepared(false);
//			}
//			//处理错误
//			if (doErrorThings()) {
//				return;
//			}
//		}
//	}
//
//
//
//	// 准备好了的监听
//	@Override
//	public void onPrepared(MediaPlayer mp) {
//		Log.d(TAG, "onPrepared: " + "准备好了开始播放");
//		mp.start();
//		isPrepared = true;
//		if (mCallBack != null) {
//			mCallBack.isPrepared(true);
//		}
//
//	}
//
//	@Override
//	public void onCompletion(MediaPlayer mp) {
//		//        当前音乐正在播放 切换音乐时需要重置一下
//		if (mp!=null&&mp.isPlaying()) {
//			mp.stop();
//			mp.release();
//			mPlayer = null;
//		}
//		isPrepared = false;
//		if (mCallBack != null) {
//			mCallBack.isPrepared(false);
//		}
//		//处理错误
//		if (doErrorThings()) {
//			return;
//		}
//
////		current++;
////		if (current >= musicList.size()) {
////			current = 0;
////		}
////		String path = musicList.get(current);
////		if (!TextUtils.isEmpty(path)) {
////			try {
////				mPlayer.setDataSource(path);
////				// 异步准备
////				mPlayer.prepareAsync();
////			} catch (IOException e) {
////				Log.e(TAG, "onStartCommand: " + e.getMessage());
////				return;
////			}
//////			EventBus.getDefault().post(new ChooseEvent(current));
////		}
//	}
//
//	private boolean doErrorThings() {
//		//超过十次停止
//		if (12 > errorTimes && errorTimes > 10) {
////			EventBus.getDefault().post(new MessageEvent(false));
//			if (mPlayer != null) {
//				if (mPlayer.isPlaying()) {
//					mPlayer.stop();
//				}
//				// 释放流 释放资源
//				mPlayer.release();
//			}
//			stopSelf();
//		}
//		//回调错误 返回
//		if (isError) {
//			errorTimes++;
//			return true;
//		}
//		return false;
//	}
//
//	// 在视频或者音频播放的是出错了  回调的方法
//	@Override
//	public boolean onError(MediaPlayer mp, int what, int extra) {
//		//一般的情况就会在这里让用户从新加载（dialog Toast）
//		Log.e(TAG, "onError: what= " + what + " ,extra=" + extra);
//		isError = true;
//		return false;
//	}
//
//	@Override
//	public boolean stopService(Intent name) {
//		Log.e(TAG, "stopService: 执行停止服务" );
//		return super.stopService(name);
//	}
//
//	@Override
//	public boolean onUnbind(Intent intent) {
//		Log.e(TAG, "onUnbind: 解绑服务" );
//		flag = false;
//		mPath = "";
//		try {
//			if (mPlayer != null) {
//				if (mPlayer.isPlaying()) {
//					mPlayer.stop();
//				}
//				// 释放流 释放资源
//				mPlayer.release();
//			}
//		} catch (Exception e) {
//		}
//		return super.onUnbind(intent);
//	}
//
//	@Override
//	public void unbindService(ServiceConnection conn) {
//		super.unbindService(conn);
//		flag = false;
//		Log.d(TAG, "unbindService: 执行解绑服务");
//		try {
//			if (mPlayer != null) {
//				if (mPlayer.isPlaying()) {
//					mPlayer.stop();
//				}
//				// 释放流 释放资源
//				mPlayer.release();
//			}
//		} catch (Exception e) {
//		}
//	}
//
//
//
//
//	public void setOnPrepared(PreparedCallBack callBack) {
//		this.mCallBack = callBack;
//	}
//
//}
//
//
