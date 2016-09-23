package com.yzxdemo.activity;

import java.lang.reflect.Method;

import android.app.Activity;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.WindowManager;

import com.android.internal.telephony.ITelephony;
import com.yzx.tools.CustomLog;
import com.yzxdemo.action.UIDfineAction;

public class ConverseActivity extends Activity{
		// 用于监听系统电话呼入
		private TelephonyManager telMgr;
		private MonitoringSystemCallListener mIncomingCallMonitor;

		private PowerManager.WakeLock mWakeLock;
		private KeyguardManager mKeyguardManager = null;
		private KeyguardLock mKeyguardLock = null;
		
		public boolean isDownHome;
		private InnerRecevier innerReceiver;

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			setVolumeControlStream(AudioManager.STREAM_RING);
			addTelophonyManagerListener();
			initProwerManager();
			enterIncallMode();
			super.onCreate(savedInstanceState);
			
			IntentFilter mFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
			innerReceiver = new InnerRecevier();
			getApplicationContext().registerReceiver(innerReceiver, mFilter);
		}

		@Override
		protected void onDestroy() {
			cancalTelophonyManagerListener();
			releaseWakeLock();
			super.onDestroy();
			getApplicationContext().unregisterReceiver(innerReceiver);
		}

		/**
		 * 添加电话状态监听器
		 * 
		 */
		private void addTelophonyManagerListener() {
			if (telMgr == null) {
				// 如果在通话时，侧自动挂断系统来电
				telMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
				mIncomingCallMonitor = new MonitoringSystemCallListener();
				telMgr.listen(mIncomingCallMonitor,PhoneStateListener.LISTEN_CALL_STATE);
			}
		}

		/**
		 * 取消电话监听
		 * 
		 */
		private void cancalTelophonyManagerListener() {
			if (telMgr != null && mIncomingCallMonitor != null) {
				telMgr.listen(mIncomingCallMonitor, PhoneStateListener.LISTEN_NONE);
			}
		}

		class MonitoringSystemCallListener extends PhoneStateListener {
			@Override
			public void onCallStateChanged(int state, String incomingNumber) {
				switch (state) {
				case TelephonyManager.CALL_STATE_RINGING:
					TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
					try {
						Class<?> c = Class.forName(tm.getClass().getName());
						Method m = c.getDeclaredMethod("getITelephony");
						m.setAccessible(true);
						ITelephony telephonyService = (ITelephony) m.invoke(tm);
						telephonyService.endCall();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				case TelephonyManager.CALL_STATE_OFFHOOK:
					break;
				case TelephonyManager.CALL_STATE_IDLE:
					break;
				}
			}
		}

		private void initProwerManager() {
			PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
			mWakeLock = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP
					| PowerManager.SCREEN_DIM_WAKE_LOCK, "CALL_ACTIVITY" + "#"
					+ getClass().getName());
			mKeyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
		}

		/**
		 * 开锁
		 * 
		 */
		private void enterIncallMode() {
			if (!mWakeLock.isHeld())
				mWakeLock.acquire();

			// 初始化键盘锁，可以锁定或解开键盘锁
			mKeyguardLock = mKeyguardManager.newKeyguardLock("");
			// 禁用显示键盘锁定
			mKeyguardLock.disableKeyguard();
		}

		/**
		 * 通话结速时重新上锁
		 * 
		 * @author: xiaozhenhua
		 * @data:2013-11-24 下午3:49:53
		 */
		private void releaseWakeLock() {
			try {
				if (mWakeLock.isHeld()) {
					if (mKeyguardLock != null) {
						mKeyguardLock.reenableKeyguard();
						mKeyguardLock = null;
					}
					mWakeLock.release();
				}
			} catch (Exception e) {
				CustomLog.e("AndroidRuntime", e.toString());
			}
		}

		@Override
		public boolean onKeyUp(int keyCode, KeyEvent event) {
			return false;
		}

		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				return false;
			} else {
				return super.onKeyDown(keyCode, event);
			}
		}
		
	class InnerRecevier extends BroadcastReceiver {
		final String SYSTEM_DIALOG_REASON_KEY = "reason";
		final String SYSTEM_DIALOG_REASON_GLOBAL_ACTIONS = "globalactions";
		final String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";
		final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
				String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
				if (reason != null) {
					if (reason.equals(SYSTEM_DIALOG_REASON_HOME_KEY)) {
						// home键
						isDownHome = true;
						CustomLog.v("------------------");
					} else if (reason.equals(SYSTEM_DIALOG_REASON_RECENT_APPS)) {
						// 长按home键
					}
				}
			}
		}
	}

}
