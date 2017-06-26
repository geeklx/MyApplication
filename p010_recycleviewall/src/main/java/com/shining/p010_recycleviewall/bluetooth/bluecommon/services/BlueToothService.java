package com.shining.p010_recycleviewall.bluetooth.bluecommon.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


/**
 * @function: 闹钟service 跳转到alarmscreen
 * @description:
 * @history: 1.  date:2016/2/16 14:07
 * author:PengLiang
 * modification:
 */
public class BlueToothService extends Service {

    public static String TAG = BlueToothService.class.getSimpleName();



    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);

    }

    public boolean is_open_close() {
        boolean is_o_c = false;

        return is_o_c;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}