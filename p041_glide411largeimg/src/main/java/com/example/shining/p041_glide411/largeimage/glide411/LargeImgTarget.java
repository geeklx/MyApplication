package com.example.shining.p041_glide411.largeimage.glide411;

import android.util.Log;

import com.bumptech.glide.request.target.Target;

/**
 * Created by shining on 2017/11/14.
 */

public class LargeImgTarget extends ProgressTarget {

    public LargeImgTarget(Object model, Target target) {
        super(model, target);
    }

    @Override
    public void onProgress(long bytesRead, long expectedLength) {
        int p = 0;
        if (expectedLength >= 0) {
            p = (int) (100 * bytesRead / expectedLength);
        }
        Log.e("-onProgress-", p + "");
    }
}
