package com.example.p009_glide.thethree;

/**
 * Created by shining on 2016/11/22 0022.
 */

import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.p009_glide.MainActivity;
import com.example.p009_glide.R;

public class BigWindowActivity extends LinearLayout {

    //记录大悬浮窗的宽度
    public static int viewWidth;

    //记录大悬浮窗高度
    public static int viewHeight;

    private Button phone, sms, app, music;
    int screenWidth, screenHeight;
    int lastX, lastY;//记录移动的最后的位置
    int dx, dy;
    private Context context;

    public BigWindowActivity(final Context context) {
        super(context);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.activity_back_ll, this);
        View view = findViewById(R.id.big_window_layout);
        viewWidth = view.getLayoutParams().width;
        viewHeight = view.getLayoutParams().height;
        view.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        initUI();

    }

    private void initUI() {
        // TODO Auto-generated method stub
        //获取屏幕的分辨率
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels - 50;
        Button back = (Button) findViewById(R.id.fanhui);
        //添加触摸监听
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                comeback(getContext());
            }
        });
//        back.setOnTouchListener(new OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                // TODO Auto-generated method stub
//                //获取Action
//                int ea = event.getAction();
//                Log.i("TAG", "Touch:" + ea);
//                switch (ea) {
//                    case MotionEvent.ACTION_DOWN:
//                        lastX = (int) event.getRawX();
//                        lastY = (int) event.getRawY();
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        //移动中动态设置位置
//                        mobilesetting(v, event);
//                        break;
//                    case MotionEvent.ACTION_UP://当手指离开的时候执行
//                        Log.e("#########",dx+", "+dy);
//                        if (dx == dy) {
//                            comeback(getContext());
//                        }
//                        break;
//                }
//                return false;
//            }
//        });
        //发短信Jump
//        sms = (Button) findViewById(R.id.SMS);
//        sms.setOnTouchListener(new OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                // TODO Auto-generated method stub
//                int ea = event.getAction();
//                Log.i("TAG", "Touch:" + ea);
//                switch (ea) {
//                    case MotionEvent.ACTION_DOWN:
//                        lastX = (int) event.getRawX();
//                        lastY = (int) event.getRawY();
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        //移动中动态设置位置
//                        mobilesetting(v, event);
//                        break;
//                    case MotionEvent.ACTION_UP://当手指离开的时候执行
//                        if (dx == dy) {
//                            sms();
//                        }
//                        break;
//                }
//
//                return false;
//            }
//        });
    }

    /**
     * 点击返回移除大窗口创建小窗口
     *
     * @param context
     */
    public void comeback(Context context) {
        // 点击返回的时候，移除大悬浮窗，创建小悬浮窗
        MyWindowManager.removeBigWindow(context);
        //返回APP
        Toast.makeText(context, "返回APP", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(context, WindowService.class);
        context.stopService(intent);

        Intent intent2 = new Intent(context, MainActivity.class);
        intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent2);
//        ((Activity) context).finish();
//        MyWindowManager.createSmallWindow(context);
    }

    //发短信
    public void sms() {
        Intent it = new Intent(Intent.ACTION_VIEW);
        it.putExtra("sms_body", "The SMS text");
        it.setType("vnd.android-dir/mms-sms");
        it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getContext().startActivity(it);
        comeback(getContext());
    }

    /**
     * 移动控件设置位置
     *
     * @param v
     * @param event
     */
    public void mobilesetting(View v, MotionEvent event) {
        //移动中动态设置位置
        dx = (int) event.getRawX() - lastX;//移动中x当前位置
        dy = (int) event.getRawY() - lastY;

        int left = v.getLeft() + dx;
        int top = v.getTop() + dy;
        int right = v.getRight() + dx;
        int bottom = v.getBottom() + dy;

        if (left < 0) {
            left = 0;
            right = left + v.getWidth();//0
        }
        if (right > screenWidth) {
            right = screenWidth;
            left = right - v.getWidth();//max
        }
        if (top < 0) {
            top = 0;
            bottom = top + v.getHeight();
        }
        if (bottom > screenHeight) {
            bottom = screenHeight;
            top = bottom - v.getHeight();
        }
        v.layout(left, top, right, bottom);
        //将当前的位置再次设置
        lastX = (int) event.getRawX();
        lastY = (int) event.getRawY();
    }
//     @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        // TODO Auto-generated method stub
//        //点击屏幕弹出的框会消失
//        //popupWindow.dismiss();
//        return super.onTouchEvent(event);
//    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            //返回键逻辑
            Toast.makeText(context, "返回", Toast.LENGTH_LONG).show();
        }
        return super.dispatchKeyEvent(event);
    }
}