package com.haiersmart.voice.utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haiersmart.sfnation.R;
import com.haiersmart.sfnation.application.FridgeApplication;
import com.haiersmart.sfnation.common.MyLogUtil;

public class FloatRelativeLayout extends RelativeLayout {
    public static final int MAX_HEIGHT = 1200;
    public static final int MARGIN_LEFT = 0;
    public static final int MAX_WIDTH = 1200;
    private static final int MARGIN_TOP = 50;
    private float mTouchStartX;
    private float mTouchStartY;
    //    private float x = X_INT;//初始值
//    private float y = Y_INT;
    private float x;//初始值
    private float y;
    private float mStartX;
    private float mStartY;
    private OnClickListener mOnClickListener;
    private OnLongClickListener mOnLongClickListener;
    private int pmWidth;//屏幕宽度
    private int pmHeight;//屏幕高度

    private WindowManager wm = ((FridgeApplication) getContext().getApplicationContext()).getMyWindowManager();
    //此wmParams变量为获取的全局变量，用以保存悬浮窗口的属性
    private WindowManager.LayoutParams wmParams = ((FridgeApplication) getContext().getApplicationContext()).getMywmParams();

    private TextView tv_du_voice;
    private ImageView iv_anim;

    public FloatRelativeLayout(Context context) {
        super(context);
//        setPadding(30, 30, 30, 30);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        pmHeight = dm.heightPixels;
        pmWidth = dm.widthPixels;
        MyLogUtil.d("ssssssssheigth : " + pmHeight);
        MyLogUtil.d("sssssssswidth : " + pmWidth);
        LayoutInflater.from(context).inflate(R.layout.layout_float_relative, this);
        tv_du_voice = (TextView) findViewById(R.id.tv_du_voice);
        iv_anim = (ImageView) findViewById(R.id.iv_anim);

    }


    public void toSpeak(boolean isSpeak) {
        if (tv_du_voice == null || iv_anim == null) {
            return;
        }
        if (isSpeak) {
            tv_du_voice.setVisibility(VISIBLE);
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    FloatRelativeLayout.this.setBackgroundResource(R.drawable.bg_voice);
                }
            }, 300);
        } else {
            tv_du_voice.setVisibility(INVISIBLE);
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    FloatRelativeLayout.this.setBackground(null);
                }
            }, 300);
        }
    }

    // 重写，返回true 拦截触摸事件
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    private long startTime;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 获取到状态栏的高度
//		Rect frame = new Rect();
//		getWindowVisibleDisplayFrame(frame);
//		int statusBarHeight = frame.top;
//		Log.e("currP", "statusBarHeight:" + statusBarHeight);
        //获取相对屏幕的坐标，即以屏幕左上角为原点
        x = event.getRawX();
        y = event.getRawY();
//		y = event.getRawY() - 25;   //25是系统状态栏的高度
//        Log.e("currP", "currX" + x + "====currY" + y);
        MyLogUtil.d("=======", "xxxx  " + x + "   yyyyy  " + y);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:    //捕获手指触摸按下动作
                //获取相对View的坐标，即以此View左上角为原点
//                mTouchStartX = event.getX();
//                mTouchStartY = event.getY();
                mTouchStartX = x;
                mTouchStartY = y;
                mStartX = x;
                mStartY = y;
                startTime = System.currentTimeMillis();
                break;

            case MotionEvent.ACTION_MOVE:   //捕获手指触摸移动动作
                updateViewPosition();
                break;

            case MotionEvent.ACTION_UP:    //捕获手指触摸离开动作
                updateViewPosition();
                mTouchStartX = mTouchStartY = 0;
//                if ((x - mStartX) < 1 && (y - mStartY) < 1) {
//                    // 设置监听
//                    if (mOnClickListener != null) {
//                        mOnClickListener.onClick(this);
//                    }
//                }
                if (System.currentTimeMillis() - startTime < 200 && (x - mStartX) < 1 && (y - mStartY) < 1) {//点击
                    if (mOnClickListener != null) {
                        mOnClickListener.onClick(this);
                    }
                }

                boolean isLongClick = System.currentTimeMillis() - startTime > 1000 * 3 && Math.abs((x - mStartX)) < 10 && Math.abs((y - mStartY)) < 10;
                MyLogUtil.d("aaaaaaaaax:", "" + Math.abs((x - mStartX)));
                MyLogUtil.d("aaaaaaaaay:", "" + Math.abs((y - mStartY)));
                MyLogUtil.d("aaaaaaaaatime:", "" + (System.currentTimeMillis() - startTime));
                MyLogUtil.d("aaaaaaaaaisLongClick:", "" + isLongClick);
                if (isLongClick) {//长按
                    if (mOnLongClickListener != null) {
                        mOnLongClickListener.onLongClick(this);
                    }
                }
                break;
        }
        return false;
    }


    @Override
    public void setOnClickListener(OnClickListener listener) {
        mOnClickListener = listener;
    }


    @Override
    public void setOnLongClickListener(@Nullable OnLongClickListener l) {
        this.mOnLongClickListener = l;
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        super.setOnTouchListener(l);
    }

    private void updateViewPosition() {
        //更新浮动窗口位置参数
//        int i = (int) (x - mTouchStartX);
//        int j = (int) (y - mTouchStartY);
//        if (i == 0 && j == 0) {
//        }

        int i = (int) (x - pmWidth / 2);
        int j = (int) (y - pmHeight / 2);
        //移动过小
//		if (i < 3 || j < 3) return;
//        if (i < MARGIN_LEFT) {//左边距
//            i = MARGIN_LEFT;
//        }
//        if (i >= MAX_WIDTH) {//最大宽度
//            i = MAX_WIDTH;
//        }
//        if (j < MARGIN_TOP) {//上边距
//            j = MARGIN_TOP;
//        }
//        if (j >= MAX_HEIGHT) {//最大高度
//            j = MAX_HEIGHT;
//
//        }

        MyLogUtil.d("======", "iiiii  " + i + "   jjjjj  " + j);
        wmParams.x = i;
        wmParams.y = j;
        wm.updateViewLayout(this, wmParams);  //刷新显示
    }

    public float getmTouchStartX() {
        return (int) (x - mTouchStartX);
    }

    public float getmTouchStartY() {
        return (int) (y - mTouchStartY);
    }
}