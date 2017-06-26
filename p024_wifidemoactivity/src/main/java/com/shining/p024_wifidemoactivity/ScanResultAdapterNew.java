package com.shining.p024_wifidemoactivity;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.shining.p024_wifidemoactivity.util.ViewHolder;
import com.shining.p024_wifidemoactivity.util.WifiDatabaseUtils;
import com.shining.p024_wifidemoactivity.util.WifiPwdUtil;
import com.shining.p024_wifidemoactivity.util.WifiUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @function: wifi设置adapter
 * @description:
 * @history: 2017年3月29日15:21:59
 * author:geek
 * modification:
 */
public class ScanResultAdapterNew extends BaseAdapter {
    private Context mContext;
    private static final String TAG = ScanResultAdapterNew.class.getSimpleName();
    private List<ScanResult> mScanResult;
    List<WifiPwdUtil.WifiInfo> mWifiPwdInfo = new ArrayList<WifiPwdUtil.WifiInfo>();
    private int sendBroadcastCount;
    Map<String, String> mWifiPwdMap = new HashMap<String, String>();

    public void refreshList(List<ScanResult> mResult) {

        mScanResult.clear();
        mScanResult.addAll(mResult);

        notifyDataSetChanged();
    }

    public List<ScanResult> getScanResult() {
        return mScanResult;
    }

    public ScanResultAdapterNew(Context context) {
        this.mContext = context;
        mScanResult = new ArrayList<>();
        sendBroadcastCount = 0;
    }

    public void refreshPwdList(List<WifiPwdUtil.WifiInfo> mResult) {

        if (mResult == null)
            return;

        mWifiPwdInfo.clear();
        mWifiPwdInfo.addAll(mResult);

        mWifiPwdMap.clear();

        for (int i = 0; i < mWifiPwdInfo.size(); i++) {

//            Log.d(TAG, "ssid = " + mWifiPwdInfo.get(i).Ssid + " pwd = " + mWifiPwdInfo.get(i).Password);

            if (mWifiPwdInfo.get(i) != null) {

                mWifiPwdMap.put(mWifiPwdInfo.get(i).Ssid, mWifiPwdInfo.get(i).Password);
            }
        }

        notifyDataSetChanged();
    }

    public void setmScanResult(List<ScanResult> mScanResult) {
        this.mScanResult = mScanResult;
    }

    @Override
    public int getCount() {
        return this.mScanResult.size();
    }

    @Override
    public Object getItem(int position) {
        return this.mScanResult.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {

            convertView = LayoutInflater.from(mContext).inflate(R.layout.scan_result_item_new, null);
        }

        TextView mSsidTv = ViewHolder.getView(convertView, R.id.ssid_tv);
//        TextView mConnectTv = ViewHolder.getView(convertView, R.id.connect_tv);
        ImageView img_level = ViewHolder.getView(convertView, R.id.img_wifi_level);
        TextView mLevelTv = ViewHolder.getView(convertView, R.id.level_tv);
        TextView mEncryptTv = ViewHolder.getView(convertView, R.id.encrypt_tv);

        WifiInfo mInfo = WifiUtil.getConnectedWifiInfo(mContext);

        if (getItem(position) != null) {
            mEncryptTv.setTextColor(mContext.getResources().getColor(R.color.mine_textcolor));
            mSsidTv.setTextColor(mContext.getResources().getColor(R.color.mine_textcolor));
            ScanResult mResult = (ScanResult) getItem(position);

            mSsidTv.setText(mResult.SSID);
            mLevelTv.setText(mResult.level + "");
            if (mResult.level > -55) {
                img_level.setBackgroundResource(R.mipmap.wifi_leavel11);
            } else if (mResult.level > -70 && mResult.level <= -50) {
                img_level.setBackgroundResource(R.mipmap.wifi_leavel22);
            } else if (mResult.level > -85 && mResult.level <= -70) {
                img_level.setBackgroundResource(R.mipmap.wifi_leavel33);
            } else if (mResult.level > -100 && mResult.level <= -80) {
                img_level.setBackgroundResource(R.mipmap.wifi_leavel44);
            } else if (mResult.level <= -100) {
                img_level.setBackgroundResource(R.mipmap.wifi_leavel55);
            }


            mEncryptTv.setText(WifiUtil.getEncryptString(mResult.capabilities));

            if ("OPEN".equals(WifiUtil.getEncryptString(mResult.capabilities))) {
                mEncryptTv.setText("开放");
            } else {
                mEncryptTv.setText("安全");
            }

            if (WifiDatabaseUtils.isExisted(mResult.SSID)) {
                mEncryptTv.setText("已保存");
            }

            if (mInfo != null) {
                if (mInfo.getSSID() != null && (mInfo.getSSID().equals(mResult.SSID) || mInfo.getSSID().equals("\"" + mResult.SSID + "\""))) {

//                    mConnectTv.setVisibility(View.VISIBLE);

                    int Ip = mInfo.getIpAddress();

//                    Log.d(TAG, "ip = " + Ip);

                    String strIp = "" + (Ip & 0xFF) + "." + ((Ip >> 8) & 0xFF) + "." + ((Ip >> 16) & 0xFF) + "." + ((Ip >> 24) & 0xFF);
                    if (mInfo.getBSSID() != null && mInfo.getSSID() != null && strIp != null && !strIp.equals("0.0.0.0")) {
                        mEncryptTv.setText("已连接");
                        mEncryptTv.setTextColor(mContext.getResources().getColor(R.color.blue3));
                        mSsidTv.setTextColor(mContext.getResources().getColor(R.color.blue3));
//                        WifiSettingFragment.getInstance().updateWifiListView(mResult, position);
                        //TODO not get pwd
//                        WifiDatabaseUtils.save(mResult.SSID, mResult.capabilities, mEdPwd.getText().toString());
                        if (sendBroadcastCount <= 3) {
                            mContext.sendBroadcast(new Intent("com.haiersmart.app.network_connected"));
                            sendBroadcastCount++;
                        }

                    } else {
                        mEncryptTv.setText("正在连接...");
                        mEncryptTv.setTextColor(mContext.getResources().getColor(R.color.blue3));
                        mSsidTv.setTextColor(mContext.getResources().getColor(R.color.blue3));
//                        WifiSettingFragment.getInstance().updateWifiListView(mResult, position);

                    }
                } else {
//                    mConnectTv.setVisibility(View.GONE);
                }
            } else {
//                mConnectTv.setVisibility(View.GONE);
            }

        }

        return convertView;
    }

    /*   @Override
       public void notifyDataSetChanged() {
           Log.d(TAG,mScanResult.size()+"  mScanResultSize1");
           deleteSameSsid();
           Collections.sort(mScanResult, WifiUtil.comparator);
           Log.d(TAG,mScanResult.size()+"  mScanResultSize2");
           super.notifyDataSetChanged();
       }
   */
    private void deleteSameSsid() {
        for (int i = 0; i < mScanResult.size(); i++) {
            if (i + 1 < mScanResult.size()) {
                ScanResult firstWifi = mScanResult.get(i);
                ScanResult lastWifi = mScanResult.get(i + 1);
                if (firstWifi.SSID.equals(lastWifi.SSID)) {
                    if (firstWifi.level >= lastWifi.level) {
                        mScanResult.remove(i + 1);
                    } else {
                        mScanResult.remove(i);
                    }
                }
            } else break;
        }
    }

//    public void deleteSameSsid() {
//        if (mScanResult.size() > 0) {
//            for (int i = mScanResult.size()-1; i > 0; i--) {
//                for (int j = mScanResult.size() - 2; j < i && j > 0; j--) {
//                    if (mScanResult.get(i).SSID.equals(mScanResult.get(j).SSID)) {
//                        if(mScanResult.get(i).level>mScanResult.get(j).level){
//                            mScanResult.remove(j);
//                        }else{
//                            mScanResult.remove(i);
//                        }
//                    }
//                }
//            }
//        }
//    }
}


