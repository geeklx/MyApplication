package com.haiersmart.sfnation.common;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by qibin on 2016/5/23.
 */

public class MobileUtils {
    public static int sScreenWidth;
    public static int sScreenHeight;

    private static void initSize(Context ctx) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        sScreenWidth = dm.widthPixels;
        sScreenHeight = dm.heightPixels;
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static int screenWidth(Context ctx) {
        if (sScreenWidth == 0) initSize(ctx);
        return sScreenWidth;
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    public static int screenHeight(Context ctx) {
        if (sScreenHeight == 0) initSize(ctx);
        return sScreenHeight;
    }

    /**
     * 是否开启丁文
     * @param context
     * @return
     */
    public static final boolean isGPSOpened(final Context context) {
        LocationManager locationManager
                = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (gps || network) {
            return true;
        }

        return false;
    }

    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /**
     * 是否有摄像头
     * @return
     */
    public static boolean hasCamera(String mode) {
//        if (FridgeApplication.get().getPackageManager().hasSystemFeature(
//                PackageManager.FEATURE_CAMERA)) {
//            return true;
//        }
        if (TextUtils.isEmpty(mode)) { return false;}
        return mode.contains("256") || mode.contains("476");
}
}
