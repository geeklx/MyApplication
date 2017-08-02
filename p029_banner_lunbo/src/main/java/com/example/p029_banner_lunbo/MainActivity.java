package com.example.p029_banner_lunbo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.p029_banner_lunbo.adapter.BannerAdapter;
import com.example.p029_banner_lunbo.bannerutils.BannerView;
import com.example.p029_banner_lunbo.domain.Biaoge_listBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BannerView mBannerView;
    private List<Biaoge_listBean> mList1;

    private TextView tv_left;
    private TextView tv_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_left = (TextView) findViewById(R.id.tv_left);
        tv_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //左←
                ToastUtil.showToastShort(MainActivity.this, "左");

                mBannerView.setCurrent(mBannerView.getCurrent() - 2);
            }
        });
        tv_right = (TextView) findViewById(R.id.tv_right);
        tv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //右→
                ToastUtil.showToastShort(MainActivity.this, "右");

                mBannerView.setCurrent(mBannerView.getCurrent());
            }
        });
        mBannerView = (BannerView) findViewById(R.id.banner);

        Data1();
        mBannerView.setAdapter(new BannerAdapter(this, mList1));

        mBannerView.stopScroll();
        mBannerView.startScroll();

    }

    private void Data1() {
        mList1 = new ArrayList<Biaoge_listBean>();
        mList1.add(new Biaoge_listBean(R.drawable.img_avatar_01, "小姐姐1"));
        mList1.add(new Biaoge_listBean(R.drawable.img_avatar_02, "小姐姐2"));
        mList1.add(new Biaoge_listBean(R.drawable.img_avatar_03, "小姐姐3"));
        mList1.add(new Biaoge_listBean(R.drawable.img_avatar_04, "小姐姐4"));
        mList1.add(new Biaoge_listBean(R.drawable.img_avatar_05, "小姐姐5"));
        mList1.add(new Biaoge_listBean(R.drawable.img_avatar_06, "小姐姐6"));
        mList1.add(new Biaoge_listBean(R.drawable.img_avatar_07, "小姐姐7"));
    }

}
