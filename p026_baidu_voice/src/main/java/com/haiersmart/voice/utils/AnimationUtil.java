package com.haiersmart.voice.utils;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.haiersmart.sfnation.common.AppManager;

/**
 * 帧动画播放以及gif播放 工具类
 * Created by JefferyLeng on 2017/1/3.
 *
 * Modified by xuhua on 2017/1/15
 * Modified by xuejinge on 2017/1/16
 */
public class AnimationUtil {

    private static String TAG = "AnimationUtil";

    public static void startGif(ImageView targetImageView,int animationDrawableId) {
        if (targetImageView == null || animationDrawableId <= 0) {
            return;
        }
        Glide.with(AppManager.getInstance().top()).load(animationDrawableId).asGif().centerCrop().dontAnimate().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(targetImageView);
    }

    public static void resetGif(ImageView targetImageView,int animationDrawableId) {
        if (targetImageView == null || animationDrawableId <= 0) {
            return;
        }
        Glide.with(AppManager.getInstance().top()).load(animationDrawableId).asGif().centerCrop().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(targetImageView);
    }

    public static void startFrameAnimation(ImageView targetImageView,int animationDrawableId) {
        if (targetImageView == null || animationDrawableId <= 0) {
            return;
        }
        targetImageView.setImageResource(animationDrawableId);
        AnimationDrawable animationDrawable = (AnimationDrawable) targetImageView.getDrawable();
        animationDrawable.start();
    }

    public static void resetAnimation(ImageView targetImageView,int animationDrawableId) {
        if (targetImageView == null || animationDrawableId <= 0) {
            return;
        }
        Glide.with(AppManager.getInstance().top()).load(animationDrawableId).asGif().centerCrop().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(targetImageView);
    }

    /**
     * 开始播放 数组动画
     * @param imageView
     * @param images
     */
    public static void startArrayAnimation(ImageView imageView,int[] images){
        Log.d(TAG, "startArrayAnimation: ---->执行数组帧动画");
        ArrayAnimationUtil arrayAnimationUtil = ArrayAnimationUtil.getArrayAnimationUtil();
        arrayAnimationUtil.init(imageView,images);
        arrayAnimationUtil.play(1);
    }

    /**
     * 停止
     */
    public static void stopArrayAnimation(ImageView imageView){
        ArrayAnimationUtil arrayAnimationUtil = ArrayAnimationUtil.getArrayAnimationUtil();
        arrayAnimationUtil.init(imageView,null);
        arrayAnimationUtil.stop();
    }


    /**
     * 停止播放target view的帧动画
     * @param targetImageView
     * @param animationDrawableId
     */
    public static void stopFrameAnimation(ImageView targetImageView,int animationDrawableId) {
        if (targetImageView == null || animationDrawableId <= 0) {
            return;
        }
        AnimationDrawable animationDrawable = (AnimationDrawable) targetImageView.getDrawable();
        animationDrawable.stop();

    }

    /**
     * 停止当前正在播放的动画
     * @param targetImageView
     */
    public static void stopFrameAnimation(ImageView targetImageView) {
        if (targetImageView == null) {
            return;
        }
        if (runnable != null)
            handler.removeCallbacks(runnable);

        AnimationDrawable animationDrawable = (AnimationDrawable) targetImageView.getDrawable();
        animationDrawable.stop();
        targetImageView.clearAnimation();

    }


    static Handler handler = new Handler();
    static Runnable runnable;

    /**
     *  讲解动画第一步
     * @param targetImageView
     * @param animationDrawableId1
     * @param animationDrawableId2
     * @param animationDrawableId3
     */
    public static void listenerFrameAnimation(final ImageView targetImageView, final int animationDrawableId1, final int animationDrawableId2, final int animationDrawableId3){
        if (targetImageView == null || animationDrawableId1 <= 0) {
            return;
        }
//        targetImageView.clearAnimation();
        targetImageView.setImageResource(animationDrawableId1);
        AnimationDrawable animationDrawable = (AnimationDrawable) targetImageView.getDrawable();
        animationDrawable.setOneShot(true);
        animationDrawable.start();
        int duration = 0;
        for(int i=0;i<animationDrawable.getNumberOfFrames()-1;i++){
            duration += animationDrawable.getDuration(i);
        }

        runnable = new Runnable() {

            public void run() {
                //此处调用第二个动画播放方法
                listenerFrameAnimationTwo(targetImageView, animationDrawableId1, animationDrawableId2, animationDrawableId3);
            }

        };

        handler.postDelayed(runnable, duration);

    }


    /**
     * 讲解动画第二步
     * @param targetImageView
     * @param animationDrawableId1
     * @param animationDrawableId2
     * @param animationDrawableId3
     */
    public static void listenerFrameAnimationTwo(final ImageView targetImageView,final int animationDrawableId1,final int animationDrawableId2, final int animationDrawableId3){
        if (targetImageView == null || animationDrawableId2 <= 0) {
            return;
        }
//        targetImageView.clearAnimation();
        targetImageView.setImageResource(animationDrawableId2);
        AnimationDrawable animationDrawable = (AnimationDrawable) targetImageView.getDrawable();
        animationDrawable.start();
        int duration = 0;
        for(int i=0;i<animationDrawable.getNumberOfFrames()-1;i++){
            duration += animationDrawable.getDuration(i);

        }

        runnable = new Runnable() {

            public void run() {
                //此处调用第三个动画播放方法
                listenerFrameAnimationThree(targetImageView, animationDrawableId1,animationDrawableId2 ,animationDrawableId3);
            }

        };

        handler.postDelayed(runnable, duration);


    }

    /**
     * 讲解动画第三部
     * @param targetImageView
     * @param animationDrawableId3
     */
    public static void listenerFrameAnimationThree(final ImageView targetImageView, final int animationDrawableId1,final int animationDrawableId2, final int animationDrawableId3){
        if (targetImageView == null || animationDrawableId3 <= 0) {
            return;
        }
//        targetImageView.clearAnimation();
        targetImageView.setImageResource(animationDrawableId3);
        AnimationDrawable animationDrawable = (AnimationDrawable) targetImageView.getDrawable();
        animationDrawable.start();

        int duration = 0;
        for(int i=0;i<animationDrawable.getNumberOfFrames();i++){
            duration += animationDrawable.getDuration(i);
        }
//
        runnable = new Runnable() {

            public void run() {
                //此处调用第一个动画播放方法
                listenerFrameAnimation(targetImageView,animationDrawableId1, animationDrawableId2, animationDrawableId3);
            }
        };

        handler.postDelayed(runnable, duration);
    }

    public static void startDefaultImg(ImageView targetImageView,int drawableId) {
        if (targetImageView == null) {
            return;
        }
        targetImageView.setImageResource(drawableId);
    }

}
