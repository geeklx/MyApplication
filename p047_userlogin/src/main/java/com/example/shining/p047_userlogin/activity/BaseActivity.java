package com.example.shining.p047_userlogin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by shining on 2017/11/8.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //登录和未登录成功状态
        if (LoginUtil.get().login_activity_result(requestCode, resultCode, data)) {
//            if (LoginUtil.get().isUserLogin()) {
//                onUserLogined(LoginUtil.get().userId());
//            } else {
//                onUserLoginCanceled();
//            }
            return;
        }
        //正常状态
        onActivityResult2(requestCode, resultCode, data);
    }

    protected void onActivityResult2(int requestCode, int resultCode, Intent data) {
    }
}
