package com.example.p023_jcenter_hios;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.library_hios.hioscommon.AdListItem;
import com.example.library_hios.hioscommon.HiosRegister;
import com.example.library_hios.hoisjump.HiosAlias;
import com.example.library_hios.hoisjump.HiosHelper;

public class MainActivity extends AppCompatActivity {

    private static final String PKG_SFNATION = "com.example.p023_jcenter_hios";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv111).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HiosRegister.load();
                HiosAlias.register("jump.secondmainactivity", PKG_SFNATION, ".SecondMainActivity");
                //hios activity跳转
                AdListItem adListItem = new AdListItem();
                adListItem.setAid("1");
                adListItem.setBanner("imgUrl");
                adListItem.setUrl("hios://jump.secondmainactivity?sku_id={s}1000252");//带参数
                HiosHelper.click(MainActivity.this, SecondMainActivity.class, adListItem);// 第一个是当前Activity 第二个是Fragment
            }
        });

    }
}
