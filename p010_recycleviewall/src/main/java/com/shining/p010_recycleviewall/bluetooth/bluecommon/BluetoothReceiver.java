package com.shining.p010_recycleviewall.bluetooth.bluecommon;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BluetoothReceiver extends BroadcastReceiver {

    public BluetoothReceiver() {

    }

    //广播接收器，当远程蓝牙设备被发现时，回调函数onReceiver()会被执行
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        String action = intent.getAction();
        // 获得已经搜索到的蓝牙设备
        if (action.equals(BluetoothDevice.ACTION_FOUND)) {
            BluetoothDevice device1 = intent
                    .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            // 搜索到的不是已经绑定的蓝牙设备
//            if (device1.getBondState() != BluetoothDevice.BOND_BONDED) {
//                Stack<Activity> as = AppManager.getInstance().getAll();
//                for (Activity a : as) {
//                    if (a instanceof MainActivity_BlueTooth) {
//                        ((MainActivity_BlueTooth) a).receiver1(device1);
//                        break;
//                    }
//                }
//            }
            /**当绑定的状态改变时*/
        } else if (action.equals(BluetoothDevice.ACTION_BOND_STATE_CHANGED)) {
            BluetoothDevice device2 = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//            Stack<Activity> as = AppManager.getInstance().getAll();
//            for (Activity a : as) {
//                if (a instanceof MainActivity_BlueTooth) {
//                    ((MainActivity_BlueTooth) a).receiver2(device2, context);
//                    break;
//                }
//            }
        } else if (action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {
//            Stack<Activity> as = AppManager.getInstance().getAll();
//            for (Activity a : as) {
//                if (a instanceof MainActivity_BlueTooth) {
//                    ((MainActivity_BlueTooth) a).receiver3();
//                    break;
//                }
//            }
        } else if (action.equals(BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED)) {
            int state = intent.getIntExtra(BluetoothA2dp.EXTRA_STATE, BluetoothA2dp.STATE_DISCONNECTED);
//            Stack<Activity> as = AppManager.getInstance().getAll();
//            for (Activity a : as) {
//                if (a instanceof MainActivity_BlueTooth) {
//                    ((MainActivity_BlueTooth) a).receiver4(state);
//                    break;
//                }
//            }
//            if (state == BluetoothA2dp.STATE_DISCONNECTED) {
//                if (MainActivity_BlueTooth.a2dp != null) {
//                    MainActivity_BlueTooth.a2dp = null;
//                }
//            }
        } else if (action.equals(BluetoothA2dp.ACTION_PLAYING_STATE_CHANGED)) {
            int state = intent.getIntExtra(BluetoothA2dp.EXTRA_STATE, BluetoothA2dp.STATE_NOT_PLAYING);
//            Stack<Activity> as = AppManager.getInstance().getAll();
//            for (Activity a : as) {
//                if (a instanceof MainActivity_BlueTooth) {
//                    ((MainActivity_BlueTooth) a).receiver5(state);
//                    break;
//                }
//            }
        }
    }
}