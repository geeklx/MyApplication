package com.example.shining.p047_userlogin.activity;

import android.app.Activity;
import android.content.Intent;

import com.example.shining.p047_userlogin.application.DemoApplication;

/**
 * Created by shining on 2017/11/8.
 */

public class LoginUtil {
    
    public static final int LOGIN_REQUEST_CODE = 3000001;
    public static final int LOGINOUT_REQUEST_CODE = 3000002;
    public static final int LOGIN_RESULT_OK = 1000001;
    public static final int LOGIN_RESULT_CANCELED = 1000002;
    public static final int LOGINOUT_RESULT_OK = 2000001;
    public static final int LOGINOUT_RESULT_CANCELED = 2000002;
    
    private static LoginUtil sInstance;
    private static final Object lock = new Object();
    private Runnable mLastRunnnable;


    public LoginUtil() {
    }

    public static LoginUtil get() {
        if (sInstance == null) {
            synchronized (lock) {
                sInstance = new LoginUtil();
            }
        }
        return sInstance;
    }

    /**
     * 用户是否登录
     *
     * @return
     */
    public static boolean isUserLogin() {
        // step 1 判断内存中是否有user_id
//        if (!TextUtils.isEmpty(DataProvider.getUser_id())) {
//            return true;
//        }
//        // step 2 如果内存中没有， 则去文件中找
//        String uid = (String) SpUtils.get(get()).get(ConstantUtil.USER_ID, null);
//        // step 3 如果文件中有， 则提到内存中
//        if (!TextUtils.isEmpty(uid)) {
//            DataProvider.setUser_id(uid);
//            return true;
//        }
        // 未登录
        return false;
    }



    public void loginTowhere(Activity activity, Runnable runnable) {
        if (isUserLogin()) {
            if (runnable != null) {
                runnable.run();
            }
        }
        mLastRunnnable = runnable;
        login(activity);
    }

    public void login(Activity activity) {
        Intent intent = new Intent("hs.act.loginactivity");
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivityForResult(intent, LOGIN_REQUEST_CODE);
        }
    }

    public void loginOutTowhere(Activity activity, Runnable runnable) {
        if (isUserLogin()) {
            if (runnable == null) {
                runnable.run();
            }
        }
        mLastRunnnable = runnable;
        loginOut(activity);
    }

    public void loginOut(Activity activity) {
        Intent intent = new Intent("hs.act.loginoutactivity");
        if (intent.resolveActivity(activity.getPackageManager()) != null) {
            activity.startActivityForResult(intent, LOGINOUT_REQUEST_CODE);
        }
    }

    public boolean login_activity_result(int requestCode, int resultCode, Intent data) {
        Runnable runnable = mLastRunnnable;
        mLastRunnnable = null;
        //已登录
        if (requestCode == LOGIN_REQUEST_CODE) {
            if (resultCode == LOGIN_RESULT_OK && runnable != null) {
                runnable.run();
            }
            return true;
        }
        //未登录
        if (requestCode == LOGINOUT_REQUEST_CODE) {
            if (resultCode == LOGINOUT_RESULT_OK && runnable != null) {
                runnable.run();
            }
            return true;
        }

        return false;
    }

}
