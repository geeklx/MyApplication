package com.example.p029_banner_lunbo.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.p029_banner_lunbo.bannerutils.BannerView;
import com.example.p029_banner_lunbo.domain.Biaoge_listBean;

import java.util.List;


public class BannerAdapter extends BannerView.Adapter {

    private List<Biaoge_listBean> mAdList;
    private Object mReceiver;

    public BannerAdapter(Object receiver, List<Biaoge_listBean> items) {
        mReceiver = receiver;
        mAdList = items;
    }

    @Override
    public int getRealCount() {
        return mAdList.size();
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        int size = mAdList.size();
        if (size == 0) {
            return null;
        }

        final int pos = position % size;

        final Biaoge_listBean item = mAdList.get(pos);

        ViewGroup.LayoutParams p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        ImageView iv = new ImageView(container.getContext());
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        iv.setAdjustViewBounds(true);
        container.addView(iv, p);

        iv.setBackgroundResource(item.getImg_url());

//        GlideUtil.display(container.getContext(), iv, item.getPic_url(),
//                GlideOptionsFactory.get(GlideOptionsFactory.Type.RADIUS));

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MobEventHelper.onEvent(container.getContext(), "UI2_ec_index_banner");
//                    MobEventHelper.onEvent(container.getContext(), "UI2_ec_index_banner_" + String.valueOf(pos + 1));
                if (container.getContext() instanceof Activity) {
                    Activity act = (Activity) container.getContext();
                    //跳转bufen
//                    HiosHelper.resolveAd(act, mReceiver, item.getUrl());
                }
            }
        });

        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}