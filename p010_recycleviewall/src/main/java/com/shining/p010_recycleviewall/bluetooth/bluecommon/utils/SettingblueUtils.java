package com.shining.p010_recycleviewall.bluetooth.bluecommon.utils;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.shining.p010_recycleviewall.bluetooth.bluecommon.bean.BlueDevice;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shining on 2017/3/8 0008.
 */

public class SettingblueUtils {

    private BlueDevice listItem;
    public static Context context;
    public static AlertDialog alertDialog;

    public static void showDailogs(Context context, String msg, DialogInterface.OnClickListener listenter) {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setMessage(msg);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog.dismiss();
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "确认", listenter);
        alertDialog.show();
    }

    public SettingblueUtils(BlueDevice listItem, Context context) {
        this.context = context;
        this.listItem = listItem;
    }

    /**
     * 取消配对bufen
     *
     * @param listItem
     */
    public static void shezhi_cancel_peidui(final BlueDevice listItem, Context context) {
        if (listItem.getDevice().getBondState() != BluetoothDevice.BOND_BONDED) {
            /**还没有配对*/
        } else {
            /**完成配对的*/
            showDailogs(context, "是否取消" + listItem.getName() + "配对？", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    stopPariBlue(listItem);
                }
            });
        }
    }

    /**
     * 开始配对蓝牙设备
     *
     * @param blueDevice
     */
    public static void startPariBlue(BlueDevice blueDevice) {
        BlueUtils blueUtils = new BlueUtils(blueDevice);
        blueUtils.doPair();
    }

    /**
     * 取消配对蓝牙设备
     *
     * @param blueDevice
     */
    public static void stopPariBlue(BlueDevice blueDevice) {
        BlueUtils.unpairDevice(blueDevice.getDevice());
    }

    /**
     * 排行连接的媒体bufen
     *
     * @param mBlueDevice1
     * @param currentBluetoothDevice_address
     * @return
     */
    public static List<BlueDevice> shezhi_ranking(List<BlueDevice> mBlueDevice1, String currentBluetoothDevice_address) {
        List<BlueDevice> bd_list = new ArrayList<>();
        for (BlueDevice bd : mBlueDevice1) {
            if (bd.getAddress().equals(currentBluetoothDevice_address)) {
                bd_list.add(0, bd);
            } else {
                bd_list.add(bd);
            }
        }
        return bd_list;
    }

    /**
     * 断开已配对连接蓝牙设备
     */
    public static void discontectBuleDevices(BlueDevice blueDevice, BluetoothAdapter mBluetoothAdapter, BluetoothA2dp a2dp) {
        /**断开A2DP协议连接设备*/
        try {
            disconnect(a2dp.getClass(), a2dp, blueDevice.getDevice());
            mBluetoothAdapter.closeProfileProxy(BluetoothProfile.A2DP, a2dp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean disconnect(Class btClass, BluetoothProfile proxy, BluetoothDevice btDevice) throws Exception {
        Method disconnectMethod = btClass.getDeclaredMethod("disconnect", BluetoothDevice.class);
        disconnectMethod.setAccessible(true);
        Boolean returnValue = (Boolean) disconnectMethod.invoke((BluetoothA2dp) proxy, btDevice);
        return returnValue.booleanValue();
    }


    public static void setDiscoverableTimeout(int timeout) {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        try {
            Method setDiscoverableTimeout = BluetoothAdapter.class.getMethod("setDiscoverableTimeout", int.class);
            setDiscoverableTimeout.setAccessible(true);
            Method setScanMode = BluetoothAdapter.class.getMethod("setScanMode", int.class, int.class);
            setScanMode.setAccessible(true);
            setDiscoverableTimeout.invoke(adapter, timeout);
            setScanMode.invoke(adapter, BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE, timeout);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeDiscoverableTimeout() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        try {
            Method setDiscoverableTimeout = BluetoothAdapter.class.getMethod("setDiscoverableTimeout", int.class);
            setDiscoverableTimeout.setAccessible(true);
            Method setScanMode = BluetoothAdapter.class.getMethod("setScanMode", int.class, int.class);
            setScanMode.setAccessible(true);

            setDiscoverableTimeout.invoke(adapter, 1);
            setScanMode.invoke(adapter, BluetoothAdapter.SCAN_MODE_CONNECTABLE, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
