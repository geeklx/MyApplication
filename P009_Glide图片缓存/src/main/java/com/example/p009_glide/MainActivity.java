package com.example.p009_glide;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.p009_glide.glide.options.GlideOptions;
import com.example.p009_glide.glide.options.GlideOptionsFactory;
import com.example.p009_glide.glide.options.GlideUtil;
import com.example.p009_glide.thethree.WindowService;
import com.example.p009_glide.util.ColorArcProgressBar;

import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public static final String PACKGETNAME = "com.haiersmart.sfnation";//com.tencent.mobileqq   com.example.shining.makejaraar com.haiersmart.sfnation
    public static final String MainActivity_PACKGETNAME = MainActivity.class.getName();
    private TextView tv1;
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private ImageView iv4;
    private ColorArcProgressBar bar4;

    private Button aaaBtn;
    private Button bbbBtn;
    private ImageView bbbImg;
    private Button cccBtn;
    private TextView aaaText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//        Log.e("--geekwidthPixels---",dm.widthPixels+"");
//        Log.e("--geekheightPixels---",dm.heightPixels+"");
//        Log.e("--geekdensity---",dm.density+"");


        tv1 = (TextView) findViewById(R.id.tv1);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivityDemo1.class));
//                Animation a = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scalebig);
//                a.setFillAfter(true);
//                v.startAnimation(a);
//                Animation a2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scalesmall);
//                a2.setFillAfter(true);
//                v.startAnimation(a2);
            }
        });

        iv1 = (ImageView) findViewById(R.id.iv1);
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openApp(PACKGETNAME);

            }
        });
        iv2 = (ImageView) findViewById(R.id.iv2);
        iv3 = (ImageView) findViewById(R.id.iv3);
        iv4 = (ImageView) findViewById(R.id.iv4);
        bar4 = (ColorArcProgressBar) findViewById(R.id.bar4);
        //1
        GlideUtil.display(MainActivity.this, iv1, "http://img0.bdstatic.com/img/image/touxiang01.jpg", GlideOptionsFactory.get(GlideOptionsFactory.Type.RADIUS));
        //2
        GlideOptions glideOptions = new GlideOptions(R.drawable.pic_head, R.drawable.pic_head, 500);
        GlideUtil.display(MainActivity.this, iv2, "http://img0.bdstatic.com/img/image/touxiang01.jpg", glideOptions);
//        Glide.with(context).load(ratings.getSku_image()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(viewHolder.iv_imgurl);
        //3
        GlideUtil.display(MainActivity.this, iv3, "http://img0.bdstatic.com/img/image/touxiang01.jpg");
        //4
        bar4.setTextSize(120);//sudu字体大小 60dip  120
        bar4.setHintSize(30);//单位字体大小   15dip  30
        Log.e("###", dipToPx(60) + "");
        Log.e("###", dipToPx(15) + "");
        bar4.setMaxValues(100);//最大百分比显示值
//        bar4.setDiameter_r(5);//圆形大小

        bar4.setTitleString("新鲜度");
        bar4.setCurrentValues(70);//最大显示值当你遇到
        bar4.setUnit("%");

        bar4.setNeedTitle(false);//title
        bar4.setNeedContent(true);//content
        bar4.setNeedUnit(false);//单位
        bar4.setNeedDial(true);

        int[] colors = new int[]{Color.GREEN, Color.YELLOW, Color.RED, Color.RED};
        int color1 = getResources().getColor(R.color.front_color1);
        int color2 = getResources().getColor(R.color.front_color2);
        int color3 = getResources().getColor(R.color.front_color3);
        colors = new int[]{color1, color2, color3, color3};
        bar4.setColors(colors);

        GlideOptions glideOptions4 = new GlideOptions(R.drawable.pic_head, R.drawable.pic_head, 300);
        GlideUtil.display(MainActivity.this, iv4, "http://img0.bdstatic.com/img/image/touxiang01.jpg", glideOptions4);

        String a = "02";
        Log.e(TAG, Integer.valueOf(a) + "", null);
    }

    private static final String TAG = "MainActivity";

    /**
     * dip 转换成px
     *
     * @param dip
     * @return
     */
    private int dipToPx(float dip) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f * (dip >= 0 ? 1 : -1));

    }

    private PackageManager mPackageManager;
    private List<ResolveInfo> mAllApps;

    /**
     * 检查系统应用程序，并打开
     */
    private void openApp(String packagename) {
        //应用过滤条件
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        mPackageManager = getPackageManager();
        mAllApps = mPackageManager.queryIntentActivities(mainIntent, 0);
        //按报名排序
        Collections.sort(mAllApps, new ResolveInfo.DisplayNameComparator(mPackageManager));

        for (ResolveInfo res : mAllApps) {
            //该应用的包名和主Activity
            String pkg = res.activityInfo.packageName;
            String cls = res.activityInfo.name;

            // 打开QQ
            if (pkg.contains(packagename)) {
                ComponentName componet = new ComponentName(pkg, cls);
                Intent intent = new Intent();
                intent.setComponent(componet);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                Intent intent0 = new Intent(MainActivity.this, WindowService.class);
                startService(intent0);
            }
        }
    }

    public static void ToBack(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

}
