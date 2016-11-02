package com.example.p012_welcomepage.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import android.util.TypedValue;

import com.example.p012_welcomepage.application.DemoApplication;

import java.io.File;
import java.lang.reflect.Field;
import java.util.UUID;


/**
 * 设备信息工具类
 */
public class DeviceUtil {

    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;
    public static int STATUS_BAR_HEIGHT;

    // 方法一
    public static float getRawSize(int unit, float value) {
        Resources res = DemoApplication.mContext.getResources();
        return TypedValue.applyDimension(unit, value, res.getDisplayMetrics());
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        final float scale = DemoApplication.mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        final float scale = DemoApplication.mContext.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 sp 的单位 转成为 px(像素)
     */
    public static float sp2px(float spValue) {
        final float scale = DemoApplication.mContext.getResources().getDisplayMetrics().scaledDensity;
        return spValue * scale;
    }

    /**
     * 根据手机的分辨率从  px(像素) 的单位 转成为 sp
     */
    public static float px2sp(float pxValue) {
        final float scale = DemoApplication.mContext.getResources().getDisplayMetrics().scaledDensity;
        return pxValue / scale;
    }

    /**
     * 状态栏高度
     */
    public static int getStatusBarHeight(Activity mContext) {
        if (STATUS_BAR_HEIGHT != 0) {
            return STATUS_BAR_HEIGHT;
        }
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            STATUS_BAR_HEIGHT = mContext.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return STATUS_BAR_HEIGHT;
    }

    /**
     * 除状态栏标题栏的屏幕高度
     */
    public static int getAppInnerHeight(Activity mContext) {
        return DeviceUtil.SCREEN_HEIGHT - getStatusBarHeight(mContext) - DeviceUtil.dip2px(48);
    }

    /**
     * 获取机器iMei值
     */
    public static String getImei() {
        TelephonyManager telephonyManager = (TelephonyManager) DemoApplication.mContext
                .getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();
        if (imei == null || imei.equals("")) {
            imei = getMac();
        }
        return imei;
    }

    /**
     * 获取机器mac值
     */
    @SuppressLint("DefaultLocale")
    public static String getMac() {
        WifiManager wifi = (WifiManager) DemoApplication.mContext.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        String mac = info.getMacAddress();
        if (mac == null) {
            mac = "00:00:00:00:00";
        }
        return mac.toLowerCase();
    }

    /**
     * 获取UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取手机厂商
     */
    public static String getFactory() {
        return android.os.Build.MANUFACTURER;
    }

    /**
     * 获取MODEL
     */
    public static String getModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取density
     */
    public static float getDenstiy() {
        return DemoApplication.mContext.getResources().getDisplayMetrics().density;
    }

    /**
     * 获取版本号
     */
    public static String getVersionName() {
        try {
            PackageInfo pi = DemoApplication.mContext.getPackageManager().getPackageInfo(
                    DemoApplication.mContext.getPackageName(), 0);
            return pi.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取版本构建号
     */
    public static int getVersionNumber() {
        try {
            PackageInfo pi = DemoApplication.mContext.getPackageManager().getPackageInfo(
                    DemoApplication.mContext.getPackageName(), 0);
            return pi.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 启动其他应用
     */
    public static void openApp(Context mContext, String pkgName) {
        PackageManager pkgManager = mContext.getPackageManager();
        Intent intent = new Intent();
        intent = pkgManager.getLaunchIntentForPackage(pkgName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
                | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(intent);
    }

    public static final int REQUEST_TAKE_PICTURE = 110;

    /**
     * 调用相机
     */
    public static int takePicture(Activity activity, File mPhotoFile, String photoPath) {
        int exception = 0;
        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            mPhotoFile = new File(photoPath);
            if (mPhotoFile.exists()) {
                mPhotoFile.delete();
            }
            mPhotoFile.createNewFile();
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mPhotoFile));
            intent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, Configuration.ORIENTATION_PORTRAIT);
            activity.startActivityForResult(intent, REQUEST_TAKE_PICTURE);
        } catch (Exception e) {
            e.printStackTrace();
            exception = -1;
        }
        return exception;
    }

    public static int getVersionCode(Context context) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getImei(Context context) {
        TelephonyManager phonyManager = ((TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE));

        return getDeviceID(phonyManager);
    }

    public static String getDeviceID(TelephonyManager phonyManager) {

        String id = phonyManager.getDeviceId();
        if (id == null) {
            id = "not available";
        }

        int phoneType = phonyManager.getPhoneType();
        switch (phoneType) {
            case TelephonyManager.PHONE_TYPE_NONE:
                return "NONE: " + id;

            case TelephonyManager.PHONE_TYPE_GSM:
                return "GSM: IMEI=" + id;

            case TelephonyManager.PHONE_TYPE_CDMA:
                return "CDMA: MEID/ESN=" + id;

 /*
  *  for API Level 11 or above
  *  case TelephonyManager.PHONE_TYPE_SIP:
  *   return "SIP";
  */

            default:
                return "UNKNOWN: ID=" + id;
        }

    }

    public static String getLocalMacAddress(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifiManager.getConnectionInfo();
        return info.getMacAddress();
    }


}
