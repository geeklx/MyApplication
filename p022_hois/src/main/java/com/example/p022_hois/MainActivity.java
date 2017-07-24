package com.example.p022_hois;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.p022_hois.activity.TwoMainActivity;
import com.example.p022_hois.hioscommon.AdListItem;
import com.example.p022_hois.hoisjump.HiosHelper;

public class MainActivity extends AppCompatActivity {

    private TextView tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hios activity跳转
                AdListItem adListItem = new AdListItem();
                adListItem.setAid("1");
                adListItem.setBanner("imgUrl");
//                adListItem.setUrl("hios://jump.twomainactivity");
//                hios://com.haiersmart.sfnation.ui.ec.ShopIndexActivity?act={i}1&sku_id={s}341703311759500256
                adListItem.setUrl("hios://jump.twomainactivity?sku_id={s}1000252");//带参数
                HiosHelper.click(MainActivity.this, TwoMainActivity.class, adListItem);// 第一个是当前Activity 第二个是Fragment
            }
        });

        tv3 = (TextView) findViewById(R.id.tv3);

        findViewById(R.id.tv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hios webview跳转
//                AdListItem adListItem = new AdListItem();
//                adListItem.setAid("1");
//                adListItem.setBanner("imgUrl");
//                adListItem.setUrl("");
//                HiosHelper.configWebActivity(WebViewMainActivity.class);
//                HiosHelper.click(MainActivity.this, adListItem);//
                //actionbufen
                Intent intent = new Intent();
                intent.putExtra("youhuiquanId", "80796278");
                intent.setAction("hs.act.shop.main");
                startActivity(intent);
                //测试
//                double aa = 100.0000;
//                final BigDecimal bg = new BigDecimal(aa).setScale(2,
//                        RoundingMode.HALF_UP);
//                if (bg.doubleValue() <= 0.0) {
//                    tv3.setText("0.00");
//                } else {
//                    tv3.setText(bg + "");
//                }

            }
        });


    }
}
