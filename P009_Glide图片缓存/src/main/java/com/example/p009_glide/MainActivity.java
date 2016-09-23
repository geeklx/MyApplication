package com.example.p009_glide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.p009_glide.glide.GlideOptions;
import com.example.p009_glide.glide.GlideOptionsFactory;
import com.example.p009_glide.glide.GlideUtil;

public class MainActivity extends AppCompatActivity {

    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        iv3 = (ImageView) findViewById(R.id.iv3);
;       //1
        GlideUtil.display(MainActivity.this, iv1, "http://img0.bdstatic.com/img/image/touxiang01.jpg", GlideOptionsFactory.get(GlideOptionsFactory.Type.RADIUS));
        //2
        GlideOptions glideOptions = new GlideOptions(R.drawable.pic_head, R.drawable.pic_head, 300);
        GlideUtil.display(MainActivity.this, iv2, "http://img0.bdstatic.com/img/image/touxiang01.jpg", glideOptions);
        //3
        GlideUtil.display(MainActivity.this, iv3, "http://img0.bdstatic.com/img/image/touxiang01.jpg");
    }
}
