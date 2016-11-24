package com.example.p009_glide.thethree;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;

import java.lang.reflect.Field;
import java.util.List;

public class CurrentApplicationPackageRetriever {

    public Context context;

    public CurrentApplicationPackageRetriever(Context context) {
        this.context = context;
    }

    public String[] get() {
        if (Build.VERSION.SDK_INT < 21)
            return getPreLollipop();
        else
            return getLollipop();
    }

    public String[] getPreLollipop() {
        List<ActivityManager.RunningTaskInfo> tasks =
                activityManager().getRunningTasks(1);
        ActivityManager.RunningTaskInfo currentTask = tasks.get(0);
        ComponentName currentActivity = currentTask.topActivity;
        return new String[]{currentActivity.getPackageName()};
    }

    public  String[] getLollipop() {
        final int PROCESS_STATE_TOP = 2;
        try {
            Field processStateField = ActivityManager.RunningAppProcessInfo.class.getDeclaredField("processState");

            List<ActivityManager.RunningAppProcessInfo> processes =
                    activityManager().getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo process : processes) {
                if (
                    // Filters out most non-activity processes
                        process.importance <= ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                                &&
                                // Filters out processes that are just being
                                // _used_ by the process with the activity
                                process.importanceReasonCode == 0
                        ) {
                    int state = processStateField.getInt(process);

                    if (state == PROCESS_STATE_TOP)
                        /*
                         If multiple candidate processes can get here,
                         it's most likely that apps are being switched.
                         The first one provided by the OS seems to be
                         the one being switched to, so we stop here.
                         */
                        return process.pkgList;
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return new String[]{};
    }

    public ActivityManager activityManager() {
        return (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
    }
}