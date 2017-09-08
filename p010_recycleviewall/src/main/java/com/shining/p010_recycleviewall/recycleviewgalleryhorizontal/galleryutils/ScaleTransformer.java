package com.shining.p010_recycleviewall.recycleviewgalleryhorizontal.galleryutils;

import android.view.View;

public class ScaleTransformer implements GalleryLayoutManager.ItemTransformer {

    private static final String TAG = "CurveTransformer";

    @Override
    public void transformItem(GalleryLayoutManager layoutManager, View item, float fraction) {
        item.setPivotX(item.getWidth() / 2.0f);
        item.setPivotY(item.getHeight() / 2.0f);
        float scale = 1 - 0.3f * Math.abs(fraction);//这个参数控制图片显示大小
        item.setScaleX(scale);
        item.setScaleY(scale);
    }
}
