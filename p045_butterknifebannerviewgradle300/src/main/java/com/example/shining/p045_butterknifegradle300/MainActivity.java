package com.example.shining.p045_butterknifegradle300;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.example.shining.p045_butterknifegradle300.bannerviews.Banner1Activity;

import butterknife.BindView;

public class MainActivity extends BaseActivity {


    @BindView(R.id.tv1)
    TextView tv1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "ceshi", Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this, Banner1Activity.class));
            }
        });
    }
}
