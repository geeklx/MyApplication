package com.haiersmart.voice.utils;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;

import com.haiersmart.sfnation.application.FridgeApplication;

import static com.haiersmart.voice.utils.FloatPresenter.X_INT;
import static com.haiersmart.voice.utils.FloatPresenter.Y_INT;

public class FloatImageView extends ImageView {
    public static final int MAX_HEIGHT = 1200;
    public static final int MARGIN_LEFT = 50;
    public static final int MAX_WIDTH = 1200;
    private static final int MARGIN_TOP = 10;
    private float mTouchStartX;
    private float mTouchStartY;
    private float x = X_INT;//初始值
    private float y = Y_INT;
    private float mStartX;
    private float mStartY;
    private OnClickListener mOnClickListener;

    private WindowManager wm = ((FridgeApplication) getContext().getApplicationContext()).getMyWindowManager();
    //此wmParams变量为获取的全局变量，用以保存悬浮窗口的属性
    private WindowManager.LayoutParams wmParams = ((FridgeApplication) getContext().getApplicationContext()).getMywmParams();


    public FloatImageView(Context context) {
        super(context);
//        setPadding(30, 30, 30, 30);
        setScaleType(ScaleType.FIT_XY);
    }

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
        Log.e("currP", "currX" + x + "====currY" + y);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:    //捕获手指触摸按下动作
                //获取相对View的坐标，即以此View左上角为原点
                mTouchStartX = event.getX();
                mTouchStartY = event.getY();
                mStartX = x;
                mStartY = y;
                Log.e("startP", "startX" + mTouchStartX + "====startY" + mTouchStartY);
                break;

            case MotionEvent.ACTION_MOVE:   //捕获手指触摸移动动作
                updateViewPosition();
                break;

            case MotionEvent.ACTION_UP:    //捕获手指触摸离开动作
                updateViewPosition();
                Log.e("currP", "currX= " + (x - mStartX) + "====currY= " + (y - mStartY));
                mTouchStartX = mTouchStartY = 0;
                if ((x - mStartX) < 5 && (y - mStartY) < 5) {
                    // 设置监听
                    if (mOnClickListener != null) {
                        mOnClickListener.onClick(this);
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
    public void setOnTouchListener(OnTouchListener l) {
        super.setOnTouchListener(l);
    }

    private void updateViewPosition() {
        //更新浮动窗口位置参数
        int i = (int) (x - mTouchStartX);
        int j = (int) (y - mTouchStartY);
        //移动过小
//		if (i < 3 || j < 3) return;
        if (i < MARGIN_LEFT) {//左边距
            i = MARGIN_LEFT;
        }
        if (i >= MAX_WIDTH) {//最大宽度
            i = MAX_WIDTH;
        }
        if (j < MARGIN_TOP) {//上边距
            j = MARGIN_TOP;
        }
        if (j >= MAX_HEIGHT) {//最大高度
            j = MAX_HEIGHT;
        }
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