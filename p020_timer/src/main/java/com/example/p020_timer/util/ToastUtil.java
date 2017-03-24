package com.example.p020_timer.util;

import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p020_timer.application.DemoApplication;

import java.lang.reflect.Field;


/**
 * Toast管理
 */
public class ToastUtil {

    public static Toast toast;
    private static CountDownTimer cdt;

    public static void showToastShort(String toastText) {
//        if(!NetErrorCheck.checkContent(toastText)) { return;}
        if (toast == null) {
            toast = Toast.makeText(DemoApplication.get(), "", Toast.LENGTH_SHORT);
            updToastTextSize(toast);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setText(toastText + "");
        toast.show();
    }

    public static void showToastLong(String toastText) {
        showToastShort(toastText);
    }

    public static void showToastCenter(String toastText) {
        showToastShort(toastText);
    }

    public static void showToastCenterShort(String toastText) {
        showToastShort(toastText);
    }

    public static void showToastShort(int res) {
        showToastShort(DemoApplication.get().getString(res));
    }

    public static void showToastLong(int res) {
        showToastLong(DemoApplication.get().getString(res));
    }

    private static void updToastTextSize(Toast toast) {
        try {
            Field f = toast.getClass().getDeclaredField("mNextView");
            f.setAccessible(true);
            ViewGroup view = (ViewGroup) f.get(toast);
            if (view == null) { return;}
            TextView tv = (TextView) view.getChildAt(0);
            if (tv == null) { return;}
            tv.setTextSize(50);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


/*
    public static void showTimeToast( String content) {
        if (toast == null) {
            toast = Toast.makeText(FridgeApplication.mContext, "", Toast.LENGTH_LONG);
        }
        View view = View.inflate(FridgeApplication.mContext, R.layout.my_toast, null);
        toast.setView(view);
        TextView tv_content = (TextView) view.findViewById(R.id.tv_toast);
        tv_content.setText(content);
        toast.show();
        if (cdt == null) {

            cdt = new CountDownTimer(5000, 5000) {

                @Override
                public void onTick(long arg0) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onFinish() {
                    toast.cancel();
                }
            };
        } else {
            cdt.cancel();
            cdt = new CountDownTimer(5000, 5000) {

                @Override
                public void onTick(long arg0) {
                    // TODO Auto-generated method stub

                }

                @Override
                public void onFinish() {
                    toast.cancel();
                }
            };
        }
        cdt.start();
    }*/
}