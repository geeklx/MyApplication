package com.example.p010_recycleviewall.utils;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.p010_recycleviewall.domain.AppBean;
import com.example.p010_recycleviewall.domain.DeviceBean;
import com.example.p010_recycleviewall.domain.TokenCheckBean;
import com.example.p010_recycleviewall.domain.VerifyInfo;

import java.text.DecimalFormat;
import java.util.Date;

/**
 * Created by jack_D on 2016/1/7.
 */
public class DataProvider {
    private static String fridge_id;
    private static String fridge_mode;//冰箱系统型号
    private static String fridge_type;//冰箱型号
    private static String family_id;
    private static String u_id;
    private static String uuid;
    private static String status_code;// 5001:用户已注册,5002:用户未注册,5003:验证码超过当日发送次数
    private static String jPushRegistId = "";
    public static AppBean app;
    public static DeviceBean device;
    //    public static TokenCheckBean tokenCheck;
    public static VerifyInfo verifyInfo;
    public static JSONObject verifyInfoJSON;

    private String sequence_id;
    public static String access_token = "";
    private static int current_seq = 0;
    private static final String STR_FORMAT = "0000000";
    private static final int MAX_SEQ = 10000000;

    public static String getAccess_token() {
        if (TextUtils.isEmpty(access_token)) {
            access_token = "";
        }
        return access_token;
    }

    public static void setAccess_token(String mAccess_token) {
        access_token = mAccess_token;
    }

    private DataProvider() {

        fridge_id = "";
        family_id = "";
    }

    public static String getFridge_mode() {
        if(TextUtils.isEmpty(fridge_mode)){
            fridge_mode = "";
        }
        return fridge_mode;
    }

    public static void setFridge_mode(String fridge_mode) {
        DataProvider.fridge_mode = fridge_mode;
    }

    public static String getFridge_type() {
        if(TextUtils.isEmpty(fridge_type)){
            fridge_type = "";
        }
        return fridge_type;
    }

    public static void setFridge_type(String fridge_type) {
        DataProvider.fridge_type = fridge_type;
    }

    public static AppBean getApp() {
        return app;
    }

    public static void setApp(AppBean app) {
        DataProvider.app = app;
    }

    public static DeviceBean getDevice() {
        return device;
    }

    public static void setDevice(DeviceBean device) {
        DataProvider.device = device;
    }

    public static VerifyInfo getVerifyInfo(JSONObject data) {
        updateVerifyInfo(data);
        return verifyInfo;
    }

    public static void setVerifyInfo(VerifyInfo verifyInfo) {
        DataProvider.verifyInfo = verifyInfo;
    }

    public static JSONObject getVerifyInfoJSON(JSONObject data) {
        updateVerifyInfo(data);
        return verifyInfoJSON;
    }

    public static void setVerifyInfoJSON(JSONObject verifyInfoJSON) {
        DataProvider.verifyInfoJSON = verifyInfoJSON;
    }

    public static String getUuid() {
        return uuid;
    }

    public static void setUuid(String uuid) {
        DataProvider.uuid = uuid;
    }


    /*public static DataProvider getInstance() {
        if (mInstance == null) {
            synchronized (DataProvider.class) {
                if (mInstance == null) {
                    mInstance = new DataProvider();
                }
            }
        }
        return mInstance;
    }*/

    public static String getFamily_id() {
        return family_id;
    }

    public static void setFamily_id(String family_id) {
        DataProvider.family_id = family_id;
    }

    public static String getFridge_id() {
        if (TextUtils.isEmpty(fridge_id)){
            fridge_id = "";
        }

        return fridge_id;
    }

    public static void setFridge_id(String fridge_id) {
        DataProvider.fridge_id = fridge_id;
    }

    public static String getUser_id() {
        return app.getUser_id();
    }

    public static void setUser_id(String user_id) {
        if (app != null) {
            app.setUser_id(user_id);
        }else{
            app = new AppBean(user_id);
        }
//        updateVerifyInfo();
    }

    public static String getU_id() {
        return u_id;
    }

    public static void setU_id(String u_id) {
        DataProvider.u_id = u_id;
    }

    public static String getjPushRegistId() {
        if (TextUtils.isEmpty(jPushRegistId)){
            jPushRegistId = "";
        }
        return jPushRegistId;
    }

    public static void setjPushRegistId(String jPushRegistId) {
        if(!TextUtils.isEmpty(jPushRegistId)){
            DataProvider.jPushRegistId = jPushRegistId;
        }

    }

    public static double getLongitude() {
        return device.getLongitude();
    }

    public static void setLongitude(double longitude) {
        device.setLongitude(longitude);
    }

    public static double getLatitude() {
        return device.getLatitude();
    }

    public static void setLatitude(double latitude) {
        device.setLatitude(latitude);
    }


    public static void updateVerifyInfo(JSONObject data) {
        DataProvider.verifyInfo = new VerifyInfo(DataProvider.app, DataProvider.device, new TokenCheckBean(data));
//        java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<VerifyInfo>() {
//        }.getType();
//        String body = new Gson().toJson(DataProvider.verifyInfo, type);
        DataProvider.verifyInfoJSON = (JSONObject) JSON.toJSON(DataProvider.verifyInfo);
    }

    /**
     * 生成下一个编号
     */
    public static synchronized String getSequence_id() {
        Date date = new Date();
        current_seq++;
        if (current_seq >= MAX_SEQ) {
            current_seq = 1;
        }
        DecimalFormat df = new DecimalFormat(STR_FORMAT);

        return date.getTime() + "" + df.format(current_seq);
    }

    /**
     * 生成下一个编号
     */
    public static synchronized String getSequence_id(long time) {
        current_seq++;
        if (current_seq >= MAX_SEQ) {
            current_seq = 1;
        }
        DecimalFormat df = new DecimalFormat(STR_FORMAT);

        return time + "" + df.format(current_seq);
    }

    public static String getStatus_code() {
        return status_code;
    }

    public static void setStatus_code(String status_code) {
        DataProvider.status_code = status_code;
    }
}
