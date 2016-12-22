package com.example.p018_activity_fragmenta_b.mywalletchangemima;

import android.content.Context;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;


/**
 * Toast管理
 */
public class ToastUtil {

    public static Toast toast;
    private static CountDownTimer cdt;
    public static Context mContext;

    public ToastUtil(Context mContext){
        this.mContext =mContext;
    }

    public static void showToastShort(String toastText) {
        if (toast == null) {
            toast = Toast.makeText(mContext.getApplicationContext(), "", Toast.LENGTH_SHORT);
            updToastTextSize(toast);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setText(toastText + "");
        toast.show();
    }

    public static void showToastLong(String toastText) {
        if (toast == null) {
            toast = Toast.makeText(mContext.getApplicationContext(), "", Toast.LENGTH_LONG);
            updToastTextSize(toast);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setText(toastText + "");
        toast.show();
    }

    public static void showToastCenter(String toastText) {
        if (toast == null) {
            toast = Toast.makeText(mContext.getApplicationContext(), "", Toast.LENGTH_LONG);
            updToastTextSize(toast);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setText(toastText + "");
        toast.show();
    }

    public static void showToastShort(int res) {
        showToastShort(mContext.getApplicationContext().getString(res));
    }

    public static void showToastLong(int res) {
        showToastLong(mContext.getApplicationContext().getString(res));
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

}