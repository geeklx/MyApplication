package com.haiersmart.voice.utils;

import android.util.Log;
import android.widget.ImageView;

/**
 * Create with Android Studio.
 *
 * @author Hsueh
 * @email i@hsueh.top
 * @date 2017/2/14 14:24
 */

class ArrayAnimationUtil {
    private ImageView mImageView;   //播方动画的相应布局
    private int[] mImageRes;
    //    private int[] durations;
    private boolean stop;
    private int pImageNo;

    public void init(ImageView pImageView, int[] pImageRes){
        stop = true;
//        System.gc();
        if (mImageView!=null) {
            mImageView.removeCallbacks(mRunnable);
        }
        this.mImageRes = null;
        this.mImageView = pImageView;
        this.mImageRes = pImageRes;
    }

    private static ArrayAnimationUtil arrayAnimationUtil = new ArrayAnimationUtil();

    static ArrayAnimationUtil getArrayAnimationUtil(){
        return arrayAnimationUtil;
    }


    public void play(int pImageNo) {
        stop = false;
        this.pImageNo = pImageNo;
        mImageView.postDelayed(mRunnable, 65);
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            int i=1;
            i++;
            Log.d("AnimationRun", "run: "+i);
            if (stop) {
                return;
            } else {
                mImageView.setImageResource(mImageRes[pImageNo]);
//                Glide.with(AppManager.getAppManager().currentActivity()).load(mImageRes[pImageNo]).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mImageView);
                if (pImageNo>=mImageRes.length-1){
                    play(1);
                }else {
                    play(pImageNo+1);
                }
            }
        }
    };

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public void stop(){
        setStop(true);
        if (mImageView!=null) {
            mImageView.removeCallbacks(mRunnable);
            System.gc();
        }

    }
}
