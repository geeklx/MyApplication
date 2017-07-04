package com.haiersmart.voice.utils.system;

import android.app.Activity;
import android.os.PowerManager;

import com.haiersmart.sfnation.common.AppManager;

import static android.content.Context.POWER_SERVICE;

/**
 * 系统工具类
 * Created by JefferyLeng on 2017/2/16.
 */
public class SystemUtil {

    /**
     * 亮屏工具类
     */
    public static void brightScreen() {
        Activity mContext = AppManager.getInstance().top();
        PowerManager pm = (PowerManager) mContext.getSystemService(POWER_SERVICE);
        PowerManager.WakeLock wakeLock = pm.newWakeLock(
                PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "MyWakeLock");
        wakeLock.acquire();
        wakeLock.release();
    }
}
