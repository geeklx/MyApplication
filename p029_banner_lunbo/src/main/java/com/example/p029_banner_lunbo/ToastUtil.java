package com.example.p029_banner_lunbo;

import android.app.Activity;
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

    public static void showToastShort(Activity context, String toastText) {
        if (toast == null) {
            toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
            updToastTextSize(toast);
        }
        toast.setGravity(Gravity.BOTTOM, 0, 60);
        toast.setText(toastText + "");
        toast.show();
    }

    public static void showToastLong(Activity context, String toastText) {
        if (toast == null) {
            toast = Toast.makeText(context, "", Toast.LENGTH_LONG);
            updToastTextSize(toast);
        }
        toast.setGravity(Gravity.BOTTOM, 0, 20);
        toast.setText(toastText + "");
        toast.show();
    }

    public static void showToastCenter(Activity context, String toastText) {
        if (toast == null) {
            toast = Toast.makeText(context, "", Toast.LENGTH_LONG);
            updToastTextSize(toast);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setText(toastText + "");
        toast.show();
    }

    public static void showToastShort(Activity context, int res) {
        showToastShort(context, context.getString(res));
    }

    public static void showToastLong(Activity context, int res) {
        showToastLong(context, context.getString(res));
    }

    private static void updToastTextSize(Toast toast) {
        try {
            Field f = toast.getClass().getDeclaredField("mNextView");
            f.setAccessible(true);
            ViewGroup view = (ViewGroup) f.get(toast);
            if (view == null) {
                return;
            }
            TextView tv = (TextView) view.getChildAt(0);
            if (tv == null) {
                return;
            }
            tv.setTextSize(12);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}