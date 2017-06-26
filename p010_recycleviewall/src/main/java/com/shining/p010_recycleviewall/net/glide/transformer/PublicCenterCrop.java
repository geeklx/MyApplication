package com.shining.p010_recycleviewall.net.glide.transformer;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

/**
 * Created by geek on 2016/8/10.
 */

public class PublicCenterCrop extends CenterCrop {

    public PublicCenterCrop(Context context) {
        super(context);
    }

    public PublicCenterCrop(BitmapPool bitmapPool) {
        super(bitmapPool);
    }

    @Override
    public Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return super.transform(pool, toTransform, outWidth, outHeight);
    }
}
