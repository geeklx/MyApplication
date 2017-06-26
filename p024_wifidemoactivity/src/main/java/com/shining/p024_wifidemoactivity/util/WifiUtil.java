package com.shining.p024_wifidemoactivity.util;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;


import com.shining.p024_wifidemoactivity.DemoApplication;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WifiUtil {

    private static long lastSwitchTime = 0L;
    private static final long SWITCH_DURATION = 10*60*1000L ;//10分钟间隔时间
    private static final String TAG = WifiUtil.class.getSimpleName();

    public enum WifiCipherType {
        WIFICIPHER_WEP, WIFICIPHER_WPA, WIFICIPHER_NOPASS, WIFICIPHER_INVALID
    }

    public static DhcpInfo getDhcpInfo(Context mContext) {

        WifiManager wm = (WifiManager) mContext.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        DhcpInfo di = wm.getDhcpInfo();

        return di;
    }

    public static String long2ip(long ip) {

        StringBuffer sb = new StringBuffer();
        sb.append(String.valueOf((int) (ip & 0xff)));
        sb.append('.');
        sb.append(String.valueOf((int) ((ip >> 8) & 0xff)));
        sb.append('.');
        sb.append(String.valueOf((int) ((ip >> 16) & 0xff)));
        sb.append('.');
        sb.append(String.valueOf((int) ((ip >> 24) & 0xff)));
        return sb.toString();
    }

    public static WifiCipherType getWifiCipher(String capability) {

        String cipher = getEncryptString(capability);

        if (cipher.contains("WEP")) {

            return WifiCipherType.WIFICIPHER_WEP;
        } else if (cipher.contains("WPA") || cipher.contains("WPA2") || cipher.contains("WPS")) {

            return WifiCipherType.WIFICIPHER_WPA;
        } else if (cipher.contains("unknow")) {

            return WifiCipherType.WIFICIPHER_INVALID;
        } else {
            return WifiCipherType.WIFICIPHER_NOPASS;
        }
    }

    public static String getEncryptString(String capability) {


        StringBuilder sb = new StringBuilder();

        if (TextUtils.isEmpty(capability))
            return "unknow";

        if (capability.contains("WEP")) {

            sb.append("WEP");

            return sb.toString();
        }

        if (capability.contains("WPA")) {

            sb.append("WPA");

        }
        if (capability.contains("WPA2")) {

            sb.append("/");

            sb.append("WPA2");

        }

        if (capability.contains("WPS")) {

            sb.append("/");

            sb.append("WPS");

        }

        if (TextUtils.isEmpty(sb))
            return "OPEN";

        return sb.toString();
    }

    public static List<WifiConfiguration> getConfigurations(Context mContext) {

        WifiManager wm = (WifiManager) mContext.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        List<WifiConfiguration> mList = wm.getConfiguredNetworks();

        return mList;
    }

    public static boolean removeWifi(Context mContext, int networkId) {
        if (mContext == null) {
            return false;
        }
        WifiManager wm = (WifiManager) mContext.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wm == null) {
            return false;
        }
        return wm.removeNetwork(networkId);

    }

    public static boolean addNetWork(WifiConfiguration cfg, Context mContext) {
        WifiManager wm = (WifiManager) mContext.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo mInfo = wm.getConnectionInfo();

        if (mInfo != null) {

            wm.disableNetwork(mInfo.getNetworkId());
//			wm.disconnect();
        }

        boolean flag = false;


        if (cfg.networkId > 0) {

//			Log.d(WifiUtil.class.getSimpleName(), "cfg networkId = " + cfg.networkId);

            flag = wm.enableNetwork(cfg.networkId, true);

            wm.updateNetwork(cfg);
        } else {

            int netId = wm.addNetwork(cfg);


//			Log.d(WifiUtil.class.getSimpleName(), "after adding netId = " + netId);

            if (netId > 0) {
                wm.saveConfiguration();
                flag = wm.enableNetwork(netId, true);
            } else {
//				ToastUtil.showToastShort("connect err");
            }
        }

        return flag;
    }

    public static void addNetWorkThread(final WifiConfiguration cfg, final Context mContext) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                WifiManager wm = (WifiManager) mContext.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                WifiInfo mInfo = wm.getConnectionInfo();
                if (mInfo != null) {
                    wm.disableNetwork(mInfo.getNetworkId());
                }
                boolean flag = false;
                if (cfg.networkId > 0) {
                    flag = wm.enableNetwork(cfg.networkId, true);
                    wm.updateNetwork(cfg);
                } else {
                    int netId = wm.addNetwork(cfg);
                    if (netId > 0) {
                        wm.saveConfiguration();
                        flag = wm.enableNetwork(netId, true);
                    }
                }

            }
        }.start();
    }




    public static WifiConfiguration createWifiConfig(String SSID, String Password,

                                                     WifiCipherType Type) {

        WifiConfiguration config = new WifiConfiguration();

        config.allowedAuthAlgorithms.clear();

        config.allowedGroupCiphers.clear();

        config.allowedKeyManagement.clear();

        config.allowedPairwiseCiphers.clear();

        config.allowedProtocols.clear();

        if (!SSID.startsWith("\"")) {

            SSID = "\"" + SSID + "\"";
        }
        config.SSID = SSID;

//		Log.d(WifiUtil.class.getSimpleName(), config.SSID );

        // 无密码

        if (Type == WifiCipherType.WIFICIPHER_NOPASS) {

            config.wepKeys[0] = "\"" + "\"";

            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);

            config.wepTxKeyIndex = 0;

        }

        // WEP加密

        if (Type == WifiCipherType.WIFICIPHER_WEP) {

            config.preSharedKey = "\"" + Password + "\"";

            config.hiddenSSID = true;

            config.allowedAuthAlgorithms

                    .set(WifiConfiguration.AuthAlgorithm.SHARED);

            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);

            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);

            config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);

            config.allowedGroupCiphers

                    .set(WifiConfiguration.GroupCipher.WEP104);

            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);

            config.wepTxKeyIndex = 0;

        }

        // WPA加密

        if (Type == WifiCipherType.WIFICIPHER_WPA) {

            config.preSharedKey = "\"" + Password + "\"";

            config.hiddenSSID = true;

//			 config.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);

//			 config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);

            config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);

//			 config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);

//			 config.allowedProtocols.set(WifiConfiguration.Protocol.WPA);

            config.status = WifiConfiguration.Status.ENABLED;

        }

        return config;

    }

    public static WifiInfo getConnectedWifiInfo(Context mContext) {

        WifiManager wm = (WifiManager) mContext.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        return wm.getConnectionInfo();

    }

    /**
     * 获取扫描结果
     *
     * @param mContext
     * @return
     */
    public static List<ScanResult> getWifiScanResult(Context mContext) {


        List<ScanResult> res = new ArrayList<ScanResult>();

        WifiManager wm = (WifiManager) mContext.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        res = wm.getScanResults();

        if (res != null) {
//			for(ScanResult mRs : mResult){
//				Log.d(WifiUtil.class.getSimpleName(), mRs.toString());
//			}
        }

        return res;
    }

    public static boolean isWifiOpen(Context mContext) {

        WifiManager wm = (WifiManager) mContext.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        return wm.isWifiEnabled();

    }

    public static void openWifi(final Context mContext, final IWifiOpen mCallBack) {

        new Thread(
                new Runnable() {

                    @Override
                    public void run() {

                        WifiManager wm = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);

                        wm.setWifiEnabled(true);

                        while (wm.getWifiState() == WifiManager.WIFI_STATE_ENABLING) {

                        }

//						Log.d(WifiUtil.class.getSimpleName(), "openWifi finish... " + wm.getWifiState());

                        if (mCallBack != null) {

                            mCallBack.onWifiOpen(wm.getWifiState());
                        }
                    }


                }).start();

    }

    public static void openWifi(final Context mContext, boolean open) {
        WifiManager wm = (WifiManager) mContext.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wm.setWifiEnabled(open);
    }

    public static void reStartWifi(final Context mContext) {
        if (isWifiOpen(mContext)) {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    openWifi(mContext, false);
                    try {
                        sleep(1000 * 2);
                        openWifi(mContext, true);
                    } catch (InterruptedException e) {
                        try {
                            openWifi(mContext, true);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        e.printStackTrace();
                    }
                }
            }.start();

        } else {
            openWifi(mContext, true);
        }

    }


    public static void closeWifi(final Context mContext) {

        new Thread(
                new Runnable() {

                    @Override
                    public void run() {

                        WifiManager wm = (WifiManager) mContext.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

                        wm.setWifiEnabled(false);

                    }


                }).start();

    }


    public interface IWifiOpen {

        public void onWifiOpen(int state);
    }


    //将搜索到的wifi根据信号强度从强到弱进行排序
    public static void sortByLevel(List<ScanResult> list) {
        for (int i = 0; i < list.size(); i++)
            for (int j = 1; j < list.size(); j++) {
                if (list.get(i).level > list.get(j).level)    //level属性即为强度
                {
                    ScanResult temp = null;
                    temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
    }

    /**
     * 排序 信号强的放前面
     */
    public static Comparator<ScanResult> comparator = new Comparator<ScanResult>() {
        @Override
        public int compare(ScanResult lhs, ScanResult rhs) {
            WifiInfo info = WifiUtil.getConnectedWifiInfo(DemoApplication.mContext);

            if (info != null && info.getSSID() != null) {
                if (info.getSSID().equals(lhs.SSID)
                        || info.getSSID().equals("\"" + lhs.SSID + "\"")) {
                    return -1;
                }

                if (info.getSSID().equals(rhs.SSID)
                        || info.getSSID().equals("\"" + rhs.SSID + "\"")) {
                    return 1;
                }
            }

            return 0 - WifiManager.compareSignalLevel(lhs.level, rhs.level);
        }
    };


    /**
     * 排序 信号强的放前面
     */
    public static Comparator<ScanResult> comparatorForDelete = new Comparator<ScanResult>() {
        @Override
        public int compare(ScanResult lhs, ScanResult rhs) {
            WifiInfo info = WifiUtil.getConnectedWifiInfo(DemoApplication.mContext);

            if (info != null && info.getSSID() != null) {
                if (info.getSSID().equals(lhs.SSID)
                        || info.getSSID().equals("\"" + lhs.SSID + "\"")) {
                    return 1;
                }

                if (info.getSSID().equals(rhs.SSID)
                        || info.getSSID().equals("\"" + rhs.SSID + "\"")) {
                    return -1;
                }
            }

            return 0 - WifiManager.compareSignalLevel(rhs.level, lhs.level);
        }
    };

    public static boolean autoSwitchWifi(Context context){
        if(context == null || !WifiUtil.isWifiOpen(context)){
            return false;
        }
        long currentTime = System.currentTimeMillis();
        if((currentTime-lastSwitchTime)>SWITCH_DURATION){
            WifiUtil.reStartWifi(context);//请求数据失败，可能是没有网络，重启WIFI模块
            lastSwitchTime = System.currentTimeMillis();
            return true;
        }else {
            return false;
        }

    }
}

