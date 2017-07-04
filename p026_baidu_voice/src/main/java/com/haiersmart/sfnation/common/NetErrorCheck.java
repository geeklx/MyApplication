package com.haiersmart.sfnation.common;

import com.haiersmart.sfnation.R;

/**
 * Created by qibin on 2016/7/21.
 */

public class NetErrorCheck {

    public static boolean checkContent(String content) {
        if (StringUtil.isEmpty(content)) { return false;}
        String lowerContent = content.toLowerCase();
        //OkClient.MSG_ERROR_HTTP
        if (lowerContent.contains("msg_error_http:okhttp")) {
            ToastUtil.showToastShort(R.string.network_error);
            return false;
        }
        if (lowerContent.equals("canceled")) { return false;}
        if (lowerContent.contains("okhttp")) { return false;}
        if (lowerContent.contains("java")) { return false;}
//        if (lowerContent.contains("token")) { return false;}
        if (lowerContent.contains("null")) { return false;}
        if (lowerContent.contains("hostname") || (lowerContent.contains("failed")
                && lowerContent.contains("connect"))
                || lowerContent.contains("timeout")) {
            ToastUtil.showToastShort(R.string.network_error);
            return false;
        }
        // more ...

        return true;
    }
}
