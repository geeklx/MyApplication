package com.shining.p024_wifidemoactivity.util;

import com.shining.p024_wifidemoactivity.domain.Wifi;

import org.loader.opendroid.db.OpenDroid;

import java.util.List;

/**
 * wifi信息数据库操作类
 * Created by qibin on 2016/6/3.
 */

public class WifiDatabaseUtils {

    public static List<Wifi> all() {
        return OpenDroid.query.find(Wifi.class);
    }

    /**
     * 保存wifi信息(成功的)
     *
     * @param ssid
     * @param cap
     * @param pwd
     */
    public static void save(String ssid, String cap, String pwd) {
        if (ssid == null) {
            return;
        }
        // 先删除老数据
        OpenDroid.delete(Wifi.class, "ssid=?", ssid);

        Wifi wifi = new Wifi();
        wifi.setSsid(ssid);
        wifi.setCapabilities(cap);
        wifi.setPassword(pwd);
        wifi.save();
    }

    public static void save(Wifi wifi) {
        if (wifi.getSsid() == null) {
            return;
        }
        // 先删除老数据
        OpenDroid.delete(Wifi.class, "ssid=?", wifi.getSsid());
        wifi.save();
    }

    /**
     * 获取最后连接成功的wifi信息
     *
     * @return
     */
    public static Wifi getLastWifi() {
        List<Wifi> wifiList = OpenDroid.query.order("_id DESC").find(Wifi.class);
        if (wifiList != null && !wifiList.isEmpty()) {
            return wifiList.get(0);
        }

        return null;
    }

    public static void delete(String ssid) {
        if (ssid == null) {
            return;
        }
        OpenDroid.delete(Wifi.class, "ssid=?", ssid);
    }

    public static Wifi find(String ssid) {
        List<Wifi> wifiList = OpenDroid.query.where("ssid=?", ssid).limit(1).find(Wifi.class);
        if (wifiList != null && !wifiList.isEmpty()) {
            return wifiList.get(0);
        }

        return null;
    }

    public static boolean isExisted(String ssid) {
        List<Wifi> wifiList = OpenDroid.query.where("ssid=?", ssid).limit(1).find(Wifi.class);
        if (wifiList != null && !wifiList.isEmpty()) {
            return true;
        }
        return false;
    }

}
