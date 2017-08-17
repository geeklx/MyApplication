package com.example.p031_mokuaihua_viewpager_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.p031_mokuaihua_viewpager_fragment.demo1.Demo1Activity;
import com.example.p031_mokuaihua_viewpager_fragment.demo2.Demo2Activity;
import com.example.p031_mokuaihua_viewpager_fragment.demo3.Demo3Activity;
import com.example.p031_mokuaihua_viewpager_fragment.demo4.Demo4Activity;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private TextView tv_demo1;
    private TextView tv_demo2;
    private TextView tv_demo3;
    private TextView tv_demo4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findview();
        onclickListener();
        doNetWork();
    }

    private void doNetWork() {

    }

    private void onclickListener() {
        tv_demo1.setOnClickListener(this);
        tv_demo2.setOnClickListener(this);
        tv_demo3.setOnClickListener(this);
        tv_demo4.setOnClickListener(this);
    }

    private void findview() {
        tv_demo1 = (TextView) findViewById(R.id.tv_demo1);
        tv_demo2 = (TextView) findViewById(R.id.tv_demo2);
        tv_demo3 = (TextView) findViewById(R.id.tv_demo3);
        tv_demo4 = (TextView) findViewById(R.id.tv_demo4);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        switch (i) {
            case R.id.tv_demo1:
                //TODO 单Activity页面多模块单版本
                startActivity(new Intent(this, Demo1Activity.class));
                break;
            case R.id.tv_demo2:
                //TODO 单Activity页面多模块多版本
                startActivity(new Intent(this, Demo2Activity.class));
//                String a = "DemoAPK2_D_1_0002";
//                String currentMode = a.split("_")[3];
//                ToastUtil.showToastLong(currentMode);
                break;
            case R.id.tv_demo3:
                //TODO 单Activity页面多模块单版本两个Viewpager
                startActivity(new Intent(this, Demo3Activity.class));
                break;
            case R.id.tv_demo4:
                //TODO 单Activity页面多模块多版本三个Viewpager
                startActivity(new Intent(this, Demo4Activity.class));
                break;
            default:
                break;
        }
    }
}
