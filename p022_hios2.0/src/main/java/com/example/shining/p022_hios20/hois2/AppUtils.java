package com.example.shining.p022_hios20.hois2;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import java.util.List;

public class AppUtils {

    public static boolean isProcessAs(String pkgName, Context ctx) {
        return pkgName.equals(getCurProcessName(ctx));
    }

    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    public static boolean isIntentAvailable(Context ctx, Intent intent) {
        return queryIntentActivitiesSize(ctx, intent) > 0;
    }

    public static int queryIntentActivitiesSize(Context ctx, Intent intent) {
        PackageManager packageManager = ctx.getPackageManager();
        List list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size();
    }
}
