package com.shining.p010_recycleviewall.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by qibin on 2016/7/19.
 */

public class ParamsUtils {

    /**
     * 根据一个java bean 生成参数
     * @param bean
     * @return
     */
    public static String just(Object bean) {
        return just((JSONObject) JSON.toJSON(bean));
    }

    /**
     * 根据一个组合好的data 生成参数
     * @param data
     * @return
     */
    public static String just(JSONObject data) {
        if (data == null) { data = new JSONObject();}
        JSONObject params = new JSONObject();
        params.put("verify_info", verifyInfo(data));
        params.put("data", data);
        return params.toJSONString();
    }

    public static JSONObject verifyInfo(JSONObject data) {
//        JSONObject info = new JSONObject();
//        info.put("app", app());
//        info.put("device", device());
        return (JSONObject) JSON.toJSON(DataProvider.getVerifyInfo(data));
    }
}
