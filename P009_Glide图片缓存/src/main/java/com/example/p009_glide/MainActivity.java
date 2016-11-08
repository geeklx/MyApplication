package com.example.p009_glide;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.example.p009_glide.glide.GlideOptions;
import com.example.p009_glide.glide.GlideOptionsFactory;
import com.example.p009_glide.glide.GlideUtil;
import com.example.p009_glide.util.ColorArcProgressBar;

public class MainActivity extends AppCompatActivity {

    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private ImageView iv4;
    private ColorArcProgressBar bar4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        iv3 = (ImageView) findViewById(R.id.iv3);
        iv4 = (ImageView) findViewById(R.id.iv4);
        bar4 = (ColorArcProgressBar) findViewById(R.id.bar4);
        ;       //1
        GlideUtil.display(MainActivity.this, iv1, "http://img0.bdstatic.com/img/image/touxiang01.jpg", GlideOptionsFactory.get(GlideOptionsFactory.Type.RADIUS));
        //2
        GlideOptions glideOptions = new GlideOptions(R.drawable.pic_head, R.drawable.pic_head, 300);
        GlideUtil.display(MainActivity.this, iv2, "http://img0.bdstatic.com/img/image/touxiang01.jpg", glideOptions);
        //3
        GlideUtil.display(MainActivity.this, iv3, "http://img0.bdstatic.com/img/image/touxiang01.jpg");
        //4
        bar4.setTextSize(120);//sudu字体大小 60dip  120
        bar4.setHintSize(30);//单位字体大小   15dip  30
        Log.e("###",dipToPx(60)+"");
        Log.e("###",dipToPx(15)+"");
        bar4.setMaxValues(100);//最大百分比显示值
//        bar4.setDiameter_r(5);//圆形大小

        bar4.setTitleString("新鲜度");
        bar4.setCurrentValues(80);//最大显示值
        bar4.setUnit("%");

        bar4.setNeedTitle(true);//title
        bar4.setNeedContent(true);//content
        bar4.setNeedUnit(true);//单位

        int[] colors = new int[]{Color.GREEN, Color.YELLOW, Color.RED, Color.RED};
        int color1 = getResources().getColor(R.color.front_color1);
        int color2 = getResources().getColor(R.color.front_color2);
        int color3 = getResources().getColor(R.color.front_color3);
        colors = new int[]{color1, color2, color3, color3};
        bar4.setColors(colors);

        GlideOptions glideOptions4 = new GlideOptions(R.drawable.pic_head, R.drawable.pic_head, 300);
        GlideUtil.display(MainActivity.this, iv4, "http://img0.bdstatic.com/img/image/touxiang01.jpg", glideOptions4);
    }

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
}
