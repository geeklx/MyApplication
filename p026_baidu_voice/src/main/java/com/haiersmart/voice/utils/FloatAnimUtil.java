package com.haiersmart.voice.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.widget.ImageView;

import com.haiersmart.sfnation.R;

/**
 * @作者:gaoruishan
 * @时间:2017/2/15/09:39
 * @邮箱:grs0515@163.com
 */

public class FloatAnimUtil {

	private ImageView mImageView;   //播方动画的相应布局
	private int[] mImageRes;
	private int durations;
	private int mpImageNo;
	private static boolean isStop = false;
	private static FloatAnimUtil floatAnimUtil = new FloatAnimUtil();

	public static FloatAnimUtil getFloatAnimUtil() {
		return floatAnimUtil;
	}

	public FloatAnimUtil() {
		init();
	}

	private void init() {
		isStop = true;//停止
		if (mImageView != null) {
			mImageView.removeCallbacks(runnable);//移除
		}
		durations = 0;
		mImageRes = null;
		isStop = false;//初始化
	}


	//停止
	public void stop() {
		init();
	}

	public void startDefaultImg(ImageView pImageView, int resId) {
		init();
		mImageView = pImageView;
		if (resId == 0) {//默认
			resId = R.drawable.blink_00;
		}
		mImageView.setImageResource(resId);
	}

	//开始
	public void startFloatAnimUtil(ImageView pImageView, int[] pImageRes, int duration) {
		init();
		mImageView = pImageView;
		durations = duration;
		mImageRes = pImageRes;
//		mImageView.setImageResource(mImageRes[0]);
		play(0);
	}

	Runnable  runnable = new Runnable() {     //采用延迟启动子线程的方式
		public void run() {
			if (isStop) return;//返回
			if (mImageRes != null && mImageRes.length > 0)
				mImageView.setImageResource(mImageRes[mpImageNo]);
			if (isStop) return;//返回
			if (mImageRes!=null&&mpImageNo == mImageRes.length - 1) {
				play(0);//重新开始
				return;
			} else if (mImageRes!=null){
				play(mpImageNo + 1);
			}
		}
	};

	//执行动画
	private void play(int pImageNo) {
		if (isStop) return;//返回
		mpImageNo = pImageNo;
		mImageView.postDelayed(runnable, durations);
	}


	//获得资源id数组
	public synchronized static int[] generateDrawableArray(Activity context, String name, int size) {
		int[] drawaleArr = new int[size];
		Resources res = context.getResources();
		for (int i = 0; i < size; i++) {
			//name:图片的名，defType：资源类型（drawable，string。。。），defPackage:工程的包名
			//xxx01 格式
			String _name = name;
			// TODO 根据需求修改
			if (i <= 9) {
				_name = _name + "0" + i;
			} else {
				_name = _name + i;
			}
			Log.e("_name", "generateDrawableArray: " + _name + "   ,context.getPackageName()=" + context.getPackageName());
			int id = res.getIdentifier(_name, "drawable", "com.haiersmart.sfnation");
//			int id = res.getIdentifier(_name, "drawable", context.getPackageName());
			//获得drawable
//			Drawable drawable=res.getDrawable(id);
			drawaleArr[i] = id;
		}
		return drawaleArr;
	}

	public synchronized static int[] generateDrawableArray(Activity context, String name, int start, int end) {
		int[] drawaleArr = new int[end - start];
		Resources res = context.getResources();
		for (int i = start; i < end; i++) {
			//name:图片的名，defType：资源类型（drawable，string。。。），defPackage:工程的包名
			//xxx01 格式
			String _name = name;
			_name = _name + i;
			Log.e("_name", "generateDrawableArray: " + _name + "   ,context.getPackageName()=" + context.getPackageName());
			int id = res.getIdentifier(_name, "drawable", "com.haiersmart.sfnation");
//			int id = res.getIdentifier(_name, "drawable", context.getPackageName());
			//获得drawable
//			Drawable drawable=res.getDrawable(id);
			drawaleArr[i - start] = id;
		}
		return drawaleArr;
	}

	//获得资源id
	public static int generateDrawable(Context context, String name, String type) {
		Resources res = context.getResources();
		//name:图片的名，defType：资源类型（drawable，string。。。），defPackage:工程的包名
		int id = res.getIdentifier(name, type, context.getPackageName());
		return id;
	}

}
