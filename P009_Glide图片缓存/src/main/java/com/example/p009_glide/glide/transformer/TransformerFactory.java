package com.example.p009_glide.glide.transformer;

import android.content.Context;
import android.graphics.Bitmap;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.example.p009_glide.util.MyLogUtil;


/**
 * Created by geek on 2016/8/9.
 */

public class TransformerFactory extends BitmapTransformation {

    private Context mContext;

    public TransformerFactory(Context context) {
        super(context);
        mContext = context;
    }

    public TransformerFactory(BitmapPool bitmapPool) {
        super(bitmapPool);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        MyLogUtil.d("outWidth=" + outWidth + ",outHeight=" + outHeight + ",imageWidth=" + toTransform.getWidth() + ",imageHeight=" + toTransform.getHeight());
//        if (toTransform.getWidth() == outWidth && toTransform.getHeight() == outHeight) {
//            return toTransform;
//        }
//
//        float widthScale = toTransform.getWidth() / (float) outWidth;
//        if (toTransform.getHeight() / widthScale < outHeight) {
//            return new PublicCenterCrop(mContext).transform(pool, toTransform, outWidth, outHeight);
//        }

//        if (toTransform.getWidth() < outWidth && toTransform.getHeight() < outHeight) {
//            return new PublicFitCenter(mContext).transform(pool, toTransform, outWidth, outHeight);
//        }

        return new PublicCenterCrop(mContext).transform(pool, toTransform, outWidth, outHeight);
    }

    @Override
    public String getId() {
        return "TransformerFactory.com.bumptech.glide.load.resource.bitmap";
    }
}
