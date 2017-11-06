package com.example.shining.p045_butterknifegradle300.bannerviews;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.shining.p045_butterknifegradle300.BaseActivity;
import com.example.shining.p045_butterknifegradle300.R;
import com.example.shining.p045_butterknifegradle300.applications.ToastUtil;
import com.example.shining.p045_butterknifegradle300.domains.Biaoge_listBean;
import com.example.shining.p045_butterknifegradle300.glideutil.GlideOptionsFactory;
import com.example.shining.p045_butterknifegradle300.glideutil.GlideUtil;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Banner1Activity extends BaseActivity {


    @BindView(R.id.banner)
    MZBannerView mMZBannerView;

    private List<Biaoge_listBean> mList1 = new ArrayList<>();
    private String url1 = "http://imgq.duitang.com/uploads/item/201411/28/20141128160848_PwTxC.jpeg";
    private String url2 = "http://f6.topitme.com/6/bd/ce/11307948457e8cebd6o.jpg";
    private String url3 = "http://img4.duitang.com/uploads/item/201509/02/20150902023555_CmjAk.jpeg";
    private String url4 = "http://a.hiphotos.baidu.com/image/pic/item/9e3df8dcd100baa1251fa0d64d10b912c8fc2ea8.jpg";
    private String url5 = "http://www.yiban.cn/filesystem/ns1/c3/8a/49/e4/fb0c729e2a060932.jpg";
    private String url6 = "http://a.hiphotos.baidu.com/image/pic/item/0eb30f2442a7d933c0540690a74bd11372f00150.jpg";
    private String url7 = "http://g.hiphotos.baidu.com/image/pic/item/4afbfbedab64034fd660cae3a5c379310b551da1.jpg";


    @Override
    protected int getLayoutId() {
        return R.layout.activity_banner1;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);
        Data1();

        //
        mMZBannerView.setDelayedTime(6000);
        mMZBannerView.setIndicatorRes(R.drawable.indicator_normal_blue, R.drawable.indicator_selected_black);
        mMZBannerView.setIndicatorVisible(true);
        mMZBannerView.setIndicatorAlign(MZBannerView.IndicatorAlign.CENTER);
        mMZBannerView.getmIndicatorContainer().setPadding(40,10,40,10);
        mMZBannerView.getmIndicatorContainer().setBackgroundResource(R.drawable.indicator_bg_trans10);

        mMZBannerView.addPageChangeLisnter(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setBanner(mList1);
//        mMZBannerView.start();
    }

    private void Data1() {
        mList1 = new ArrayList<Biaoge_listBean>();
        mList1.add(new Biaoge_listBean(url1, "小姐姐1"));
        mList1.add(new Biaoge_listBean(url2, "小姐姐2"));
        mList1.add(new Biaoge_listBean(url3, "小姐姐3"));
        mList1.add(new Biaoge_listBean(url4, "小姐姐4"));
        mList1.add(new Biaoge_listBean(url5, "小姐姐5"));
        mList1.add(new Biaoge_listBean(url6, "小姐姐6"));
        mList1.add(new Biaoge_listBean(url7, "小姐姐7"));
    }

    private void setBanner(List<Biaoge_listBean> mList1) {
        mMZBannerView.setPages(mList1, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });

    }

    public static class BannerViewHolder implements MZViewHolder<Biaoge_listBean> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            View view = LayoutInflater.from(context).inflate(R.layout.remote_banner_item, null);
            mImageView = (ImageView) view.findViewById(R.id.remote_item_image);
            return view;
        }

        @Override
        public void onBind(final Context context, int i, final Biaoge_listBean mBean) {
            Log.e("geek", "current position:" + i);
            GlideUtil.display(context, mImageView, mBean.getImg_url(), GlideOptionsFactory.get(GlideOptionsFactory.Type.DEFAULT));
            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtil.showToastShort((Activity) context, mBean.getText_content());
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMZBannerView.pause();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMZBannerView.pause();
//        GlideUtil.clearMemoryCache();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMZBannerView.start();
    }
}
