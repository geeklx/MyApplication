package com.shining.p010_recycleviewall.net;

import android.annotation.TargetApi;
import android.os.Build;
import android.text.TextUtils;

import com.shining.p010_recycleviewall.utils.DataProvider;

import org.loader.glin.Result;
import org.loader.glin.interceptor.IResultInterceptor;

/**
 * Created by qibin on 2016/8/20.
 */

public class ResultInterceptor implements IResultInterceptor {

    public static final int TOKEN_NULL = 9998;
    public static final int TOKEN_INVALID = 9999;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public boolean intercept(Result<?> result) {
        if (!result.isOK()) {
            // check token
            Object obj = result.getObj();
            if (obj != null && TextUtils.isDigitsOnly(obj.toString())) {
                int code = Integer.parseInt(obj.toString());
                if (code == TOKEN_INVALID) {

                }
            }
            // check fridge_id
            if (obj != null && TextUtils.isEmpty(DataProvider.getFridge_id())) {
            }
        }

        return false;
    }


}
