package com.example.p009_glide.glide.transformer;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.FitCenter;

/**
 * Created by geek on 2016/8/10.
 */

public class PublicFitCenter extends FitCenter {

    public PublicFitCenter(Context context) {
        super(context);
    }

    public PublicFitCenter(BitmapPool bitmapPool) {
        super(bitmapPool);
    }

    @Override
    public Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return super.transform(pool, toTransform, outWidth, outHeight);
    }
}
