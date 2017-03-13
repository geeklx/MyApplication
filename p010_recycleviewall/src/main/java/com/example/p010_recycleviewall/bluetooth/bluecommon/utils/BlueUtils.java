package com.example.p010_recycleviewall.bluetooth.bluecommon.utils;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import com.example.p010_recycleviewall.bluetooth.bluecommon.bean.BlueDevice;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

public class BlueUtils {

    private final BlueDevice blueDevice;
    private Handler mOthHandler;
    private BluetoothSocket socket;       //蓝牙连接socket

    public BlueUtils(BlueDevice blueDevice) {
        this.blueDevice = blueDevice;
    }


    /**
     * 配对指定的蓝牙设备
     *
     * @param btDevice
     * @return
     */
    public static boolean createBond(BluetoothDevice btDevice) {
        boolean result = false;
        try {
            Method m = btDevice.getClass().getDeclaredMethod("createBond", new Class[]{});
            m.setAccessible(true);
            Boolean originalResult = (Boolean) m.invoke(btDevice);
            result = originalResult.booleanValue();
        } catch (Exception ex) {
        }
        return result;
    }


    /**
     * 配对
     */
    public void doPair() {
        if (null == mOthHandler) {
            HandlerThread handlerThread = new HandlerThread("other_thread");
            handlerThread.start();
            mOthHandler = new Handler(handlerThread.getLooper());
        }
        mOthHandler.post(new Runnable() {
            @Override
            public void run() {
                initSocket();   //取得socket
                try {
                    socket.connect();   //请求配对
//						mAdapterManager.updateDeviceAdapter();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    /**
     * 取消蓝牙配对
     *
     * @param device
     */
    public static void unpairDevice(BluetoothDevice device) {
        try {
            Method m = device.getClass()
                    .getMethod("removeBond", (Class[]) null);
            m.invoke(device, (Object[]) null);
        } catch (Exception e) {
            Log.d("BlueUtils", e.getMessage());
        }
    }


    /**
     * 取得BluetoothSocket
     */
    public void initSocket() {
        BluetoothSocket temp = null;
        try {
//			temp = mTouchObject.bluetoothDevice.createRfcommSocketToServiceRecord(UUID.fromString(CONNECT_UUID));
            //以上取得socket方法不能正常使用， 用以下方法代替
            Method m = blueDevice.getDevice().getClass().getMethod("createRfcommSocket", new Class[]{int.class});
            temp = (BluetoothSocket) m.invoke(blueDevice.getDevice(), 1);
            //怪异错误： 直接赋值给socket,对socket操作可能出现异常，  要通过中间变量temp赋值给socket
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        socket = temp;
    }

    /**
     * 取得BluetoothSocket
     */
    public void initSocket2() {
        BluetoothSocket temp = null;
        try {
            temp = blueDevice.getDevice().createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        socket = temp;
    }


    /**
     * 通过蓝牙发送文件
     */
//    public void doSendFileByBluetooth(int mSendFileNameid) {
//        final String mSendFileName= SDCardUtils.getSDCardPath()+"usb.txt";
//
//        MyLogUtil.d("mSendFileName="+mSendFileName);
//        if(!mSendFileName.equals("null")){
//            if(null == mOthHandler){
//                HandlerThread handlerThread = new HandlerThread("other_thread");
//                handlerThread.start();
//                mOthHandler = new Handler(handlerThread.getLooper());
//            }
//            mOthHandler.post(new Runnable() {
//                @Override
//                public void run() {
//                    //调用系统程序发送文件
//				Intent intent = new Intent();
//				intent.setAction(Intent.ACTION_SEND);
//				String filePath = "file:///mnt/external_sd/blue.jpg";
//				String extension = filePath.substring(filePath.lastIndexOf(".")+1);
//				String type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
//				Log.i("BluetoothDemo", "extension=" + extension + "     type:=" + type);
//				intent.setType("image/jpg");
////                    intent.setType("*/*");
////                    intent.setType("text/plain");
//				intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(filePath));
////				intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(filePath)));
//				activity.startActivityForResult(intent,2000);
//                }
//            });
//        }else {
//            //   file:///file%3A/mnt/internal_sd/usb.txt@28dcdf27
//            Toast.makeText(activity, "请选择要发送的文件!", Toast.LENGTH_LONG).show();
//        }
//    }


}
