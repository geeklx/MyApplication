package com.haiersmart.voice.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.haiersmart.sfnation.common.AppManager;


/**
 * Create with Android Studio.
 *
 * @author Hsueh
 * @email i@hsueh.top
 * @date 2017/2/14 14:50
 */

public class DrawableArray {
    private static DrawableArray drawableArray = new DrawableArray();
    public static DrawableArray getDrawableArray(){
        return drawableArray;
    }

    /**
     * 唤醒表情数组
     * @return
     */
    public int[] wakeImages(){
        return generateDrawableArray(AppManager.getInstance().top(),"wake_",1,47);
    }

    /**
     * 睡眠
     * @return
     */
    public int[] sleepImages(){
        return generateDrawableArray(AppManager.getInstance().top(),"sleep_",1,52);
    }

    /**
     * 正常状态表情/默认表情
     * @return
     */
    public int[] normalImages(){
        return generateDrawableArray(AppManager.getInstance().top(),"speak_",1,37);
    }

    /**
     * 聆听状态
     * @return
     */
    public int[] listenImages(){
        return generateDrawableArray(AppManager.getInstance().top(),"listen_",1,51);
    }


    /**
     * 时长
     * @param size
     * @param duration
     * @return
     */
    public int[] durations(int size,int duration){
        int [] durations = new int[size];

        for (int i = 0; i < size; i++) {
            durations[i] = duration;
        }

        return durations;
    }

    /**
     * 获得资源id数组
     * @param context
     * @param name
     * @param size
     * @return
     */
    public static int[] generateDrawableArray(Activity context, String name, int size) {
        int[] drawaleArr = new int[size];
        Resources res = context.getResources();
        for (int i = 0; i < size; i++) {
            //name:图片的名，defType：资源类型（drawable，string。。。），defPackage:工程的包名
            //xxx01 格式
            String _name = name;
            // TODO 根据需求修改
            if (i <= 9) {
                _name = _name + "0" + i;
            } else {
                _name = _name + i;
            }
            Log.e("_name", "generateDrawableArray: " + _name + "   ,context.getPackageName()=" + context.getPackageName());
            int id = res.getIdentifier(_name, "drawable", "com.haiersmart.sfnation");
//			int id = res.getIdentifier(_name, "drawable", context.getPackageName());
            //获得drawable
//			Drawable drawable=res.getDrawable(id);
            drawaleArr[i] = id;
        }
        return drawaleArr;
    }

    /**
     * 获取资源数组 可以定义开始和结束位置
     * @param context
     * @param name
     * @param start
     * @param end
     * @return
     */
    public static int[] generateDrawableArray(Activity context, String name, int start, int end) {
        int[] drawaleArr = new int[end - start];
        Resources res = context.getResources();
        for (int i = start; i < end; i++) {
            //name:图片的名，defType：资源类型（drawable，string。。。），defPackage:工程的包名
            //xxx01 格式
            String _name = name;
            if (i <= 9) {
                _name = _name + "0" + i;
            } else {
                _name = _name + i;
            }
//            Log.e("_name", "generateDrawableArray: " + _name + "   ,context.getPackageName()=" + context.getPackageName());
            int id = res.getIdentifier(_name, "drawable", "com.haiersmart.sfnation");
//			int id = res.getIdentifier(_name, "drawable", context.getPackageName());
            //获得drawable
//			Drawable drawable=res.getDrawable(id);
            drawaleArr[i - start] = id;
        }
        return drawaleArr;
    }

    /**
     * 获得资源id
     * @param context
     * @param name
     * @param type
     * @return
     */
    public static int generateDrawable(Context context, String name, String type) {
        Resources res = context.getResources();
        //name:图片的名，defType：资源类型（drawable，string。。。），defPackage:工程的包名
        int id = res.getIdentifier(name, type, context.getPackageName());
        return id;
    }



}
