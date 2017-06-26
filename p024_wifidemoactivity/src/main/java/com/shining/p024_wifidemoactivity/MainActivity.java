package com.shining.p024_wifidemoactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.shining.p024_wifidemoactivity.util.WifiUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WifiUtil.autoSwitchWifi(MainActivity.this);//请求升级数据失败，可能是没有网络，重启WIFI模块
//        WifiUtil.autoSwitchWifi(MainActivity.this);//请求升级数据失败，可能是没有网络，重启WIFI模块
        findViewById(R.id.tv_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, WifiSettingActivity.class));
            }
        });
    }
}
