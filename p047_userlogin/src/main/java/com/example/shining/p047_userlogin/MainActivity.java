package com.example.shining.p047_userlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.shining.p047_userlogin.activity.BaseActivity;
import com.example.shining.p047_userlogin.activity.LoginUtil;
import com.example.shining.p047_userlogin.utils.ToastUtil;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginUtil.get().loginTowhere(MainActivity.this, new Runnable() {
                    @Override
                    public void run() {
                        //登录to
                        ToastUtil.showToastCenter("可以跳转了~");
                        startActivity(new Intent(MainActivity.this, MainActivity2.class));
                    }
                });
            }
        });


//        LoginUtil.get().loginOutTowhere(MainActivity.this, new Runnable() {
//            @Override
//            public void run() {
//               //退出登录to
//
//            }
//        });

    }
}
