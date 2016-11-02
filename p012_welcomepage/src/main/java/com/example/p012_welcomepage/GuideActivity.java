package com.example.p012_welcomepage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.p012_welcomepage.guide.*;

import java.util.ArrayList;
import java.util.List;



public class GuideActivity extends Activity implements OnPageChangeListener {
    private ViewPager vp;
    private ViewPagerAdapter vpAdapter;
    private ImageView[] dots;
    private int[] ids = {R.id.iv1, R.id.iv2, R.id.iv3, R.id.iv4};
    private int[] idss = {R.drawable.guide_one, R.drawable.guide_two,
            R.drawable.guide_three,R.drawable.guide_four};
    private List<View> views;
    private ImageButton start_btn;
    private LinearLayout ll_img;


    @SuppressLint("WorldReadableFiles")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ydy_guide);
        initViews();
        initDots();
        createDeskShortCut();
    }

    /**
     * 创建快捷方式
     */
    public void createDeskShortCut() {

        Log.i("coder", "------createShortCut--------");
        // 创建快捷方式的Intent
        Intent shortcutIntent = new Intent(
                "com.android.launcher.action.INSTALL_SHORTCUT");
        // 不允许重复创建
        shortcutIntent.putExtra("duplicate", false);
        // 需要现实的名称
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME,
                getString(R.string.app_name));

        // 快捷图片
        Parcelable icon = Intent.ShortcutIconResource.fromContext(
                getApplicationContext(), R.mipmap.ic_launcher);

        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);

        Intent intent = new Intent(getApplicationContext(), GuideActivity.class);
        // 下面两个属性是为了当应用程序卸载时桌面 上的快捷方式会删除
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");

        // 点击快捷图片，运行的程序主入口
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, intent);
        // 发送广播。OK
        sendBroadcast(shortcutIntent);
    }

    private void initViews() {

        LayoutInflater inflater = LayoutInflater.from(this);
        views = new ArrayList<View>();
        views.add(inflater.inflate(R.layout.activity_ydy_one, null));
        views.add(inflater.inflate(R.layout.activity_ydy_two, null));
        views.add(inflater.inflate(R.layout.activity_ydy_three, null));
        views.add(inflater.inflate(R.layout.activity_ydy_four, null));

        // adapter
        vpAdapter = new ViewPagerAdapter(views, this);
        vp = (ViewPager) findViewById(R.id.viewpager);
        vp.setPageTransformer(true, new ZoomOutPageTransformer());
        vp.setAdapter(vpAdapter);
        vp.setOnPageChangeListener(this);

        start_btn = (ImageButton) views.get(3).findViewById(R.id.start_btn);
        ll_img = (LinearLayout) views.get(3).findViewById(R.id.ll_img);
        ll_img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(GuideActivity.this,AdvertisementActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void initDots() {
        dots = new ImageView[views.size()];
        for (int i = 0; i < views.size(); i++) {
            dots[i] = (ImageView) findViewById(ids[i]);
        }
    }

    private Boolean misScrolled = false;

    @Override
    public void onPageScrollStateChanged(int state) {
        switch (state) {
            case ViewPager.SCROLL_STATE_DRAGGING:
                misScrolled = false;
                break;
            case ViewPager.SCROLL_STATE_SETTLING:
                misScrolled = true;
                break;
            case ViewPager.SCROLL_STATE_IDLE:
                if (vp.getCurrentItem() == vp.getAdapter().getCount() - 1
                        && !misScrolled) {
                    startActivity(new Intent(this,AdvertisementActivity.class));
                    GuideActivity.this.finish();
                }
                misScrolled = true;
                break;
        }
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int arg0) {
        for (int i = 0; i < ids.length; i++) {
            if (arg0 == i) {
                dots[i].setImageResource(R.drawable.guidecheck);
            } else {
                dots[i].setImageResource(R.drawable.guideencheck);
            }
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
