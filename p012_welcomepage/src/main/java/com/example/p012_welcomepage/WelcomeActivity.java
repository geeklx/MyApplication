package com.example.p012_welcomepage;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.p012_welcomepage.util.DeviceUtil;


public class WelcomeActivity extends Activity {
    private TextView tv_versioncode;
    private Animation animation;
    private SharedPreferences preferences;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ydy_welcome);
        tv_versioncode = (TextView) findViewById(R.id.tv_versioncode);
        tv_versioncode.setText(getResources().getString(R.string.bbsm)
                + DeviceUtil.getVersionName());
//        animation = AnimationUtils.loadAnimation(this, R.anim.lunch_anim);
//        imageView.setAnimation(animation);
        jumpto();

    }

    private Handler mHandler = new Handler();

    private void jumpto() {
        preferences = getSharedPreferences("count11", MODE_PRIVATE);
        int count = preferences.getInt("count11", 0);
        // 判断程序与第几次运行，如果是第一次运行则跳转到引导页
        if (count == 0) {
            SharedPreferences.Editor editor = preferences.edit();
            // 存入数据
            editor.putInt("count11", ++count);
            // 提交修改
            editor.commit();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(WelcomeActivity.this, GuideActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 2000);
        } else {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(WelcomeActivity.this, AdvertisementActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 2000);
        }
    }
}
