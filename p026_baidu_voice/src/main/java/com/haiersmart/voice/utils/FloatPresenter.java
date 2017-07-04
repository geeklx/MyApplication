package com.haiersmart.voice.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.haiersmart.sfnation.R;
import com.haiersmart.sfnation.application.FridgeApplication;
import com.haiersmart.sfnation.util.AISpeechUtil;

/**
 * @作者:gaoruishan
 * @时间:2017/2/17/14:20
 * @邮箱:grs0515@163.com
 */

public class FloatPresenter {

    public static final int X_INT = -640;//初始位置
    public static final int Y_INT = -400;
    public static final int Y_OFF = 250;//偏移量
    public static final int X_OFF = -180;
    public static final int PARAM_WIDTH = 200;//窗体大小
    public static final int PARAM_HEIGHT = 200;
    private static String TAG = "FloatPresenter";
    private static WindowManager wm = null;
    private static WindowManager.LayoutParams wmParams;
    private static ImageView myFV = null;
    private static TextView tvSpeakText = null;
    private static VideoView videoView = null;
    private static FloatRelativeLayout linearLayout = null;

    //开始时创建
    public static void create(Context activity) {
        ActivityManager manager = (ActivityManager) activity.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.RunningAppProcessInfo runningAppProcessInfo = manager.getRunningAppProcesses().get(0);
        Log.e("TAG", "create: " + runningAppProcessInfo.processName);
        //主进程
        if (runningAppProcessInfo.processName.equals("com.haiersmart.sfnation")) {
            createView(activity);
        }

    }

    //创建view
    private static void createView(Context activity) {
        //获取WindowManager
        wm = ((FridgeApplication) activity).getMyWindowManager();
        //设置LayoutParams(全局变量）相关参数
        wmParams = ((FridgeApplication) activity).getMywmParams();

        /**
         *以下都是WindowManager.LayoutParams的相关属性
         * 具体用途可参考SDK文档
         */
        wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;   //设置window type
        wmParams.format = PixelFormat.RGBA_8888;   //设置图片格式，效果为背景透明

        //设置Window flag
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            /*
             * 下面的flags属性的效果形同“锁定”。
	         * 悬浮窗不可触摸，不接受任何事件,同时不影响后面的事件响应。
	         *wmParams.flags=LayoutParams.FLAG_NOT_TOUCH_MODAL
	                               | LayoutParams.FLAG_NOT_FOCUSABLE
	                               | LayoutParams.FLAG_NOT_TOUCHABLE;
	        */

//        wmParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.TOP;   //调整悬浮窗口至左上角，便于调整坐标
        //以屏幕左上角为原点，设置x、y初始值
        wmParams.x = (int) (X_INT * 0.9);
        wmParams.y = (int) (Y_INT * 0.9);

        //设置悬浮窗口长宽数据
//        wmParams.width = PARAM_WIDTH;
//        wmParams.height = PARAM_HEIGHT;
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        linearLayout = new FloatRelativeLayout(activity.getApplicationContext());
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AISpeechUtil.reWakeup(true);
            }
        });
        linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AISpeechUtil.breakVoice();
                return true;
            }
        });
        myFV = (ImageView) linearLayout.findViewById(R.id.iv_anim);
        tvSpeakText = (TextView) linearLayout.findViewById(R.id.tv_du_voice);

        //显示myFloatView图像
        wm.addView(linearLayout, wmParams);
    }

    //获得浮动的ImageView
    public static ImageView getMyFV() {
        if (linearLayout == null) {
            Log.e(TAG, "getMyFV: null");
            createView(FridgeApplication.get());
        }
        Log.e(TAG, "getMyFV: " + myFV.getVisibility());
        return myFV;
    }

    //获得浮动的layout
    public static FloatRelativeLayout getLayout() {
        if (linearLayout == null) {
            createView(FridgeApplication.get());
        }
        return linearLayout;
    }

    public static TextView getTvSpeakText() {
        if (tvSpeakText == null) {
            Log.e(TAG, "tvSpeakText: null");
        }
        return tvSpeakText;
    }

    public static WindowManager.LayoutParams getWmParams() {
        if (wmParams == null) {
            Log.e(TAG, "wmParams: null");
        }
        return wmParams;
    }

    //在程序退出(Activity销毁）时销毁悬浮窗口
//    public static void destory() {
//        if (wm != null && myFV != null && myFV.getWindowToken() != null) {
//            wm.removeView(myFV);
//            myFV = null;
//        }
//    }

    public static void destory() {
        if (wm != null && linearLayout != null && linearLayout.getWindowToken() != null) {
            try {
//                linearLayout.removeView(tvSpeakText);
//                linearLayout.removeView(myFV);
                wm.removeView(linearLayout);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                linearLayout = null;
                tvSpeakText = null;
                myFV = null;
            }
        }
    }

    private static void creatVedioView(Context activity) {
        videoView = new VideoView(activity.getApplicationContext());
        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destoryVedioView();
            }
        });
        wm = ((FridgeApplication) activity).getMyWindowManager();
        wmParams = ((FridgeApplication) activity).getMywmParams();
        wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;   //设置window type
        wmParams.format = PixelFormat.RGBA_8888;   //设置图片格式，效果为背景透明
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//        wmParams.gravity = Gravity.CENTER;   //调整悬浮窗口至左上角，便于调整坐标
        //以屏幕左上角为原点，设置x、y初始值
//        wmParams.x = X_INT;
//        wmParams.y = Y_INT;
        //设置悬浮窗口长宽数据
        wmParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        wmParams.height = WindowManager.LayoutParams.MATCH_PARENT;

        //显示myFloatView图像
        wm.addView(videoView, wmParams);
    }


    //获得浮动的ImageView
    public static VideoView getVedioView() {
        if (videoView == null) {
            creatVedioView(FridgeApplication.get());
        }
        return videoView;
    }

    //在程序退出(Activity销毁）时销毁悬浮窗口
    public static void destoryVedioView() {
        if (wm != null && videoView != null && videoView.getWindowToken() != null) {
            wm.removeView(videoView);
            videoView = null;
        }
    }
}
