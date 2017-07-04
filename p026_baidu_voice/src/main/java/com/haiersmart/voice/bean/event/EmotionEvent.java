package com.haiersmart.voice.bean.event;


/**
 * Created by JefferyLeng on 2017/1/3.
 */

public class EmotionEvent extends BaseMessageEvent {

    private int resId;
    private int[] resArr;


    public EmotionEvent(int resId) {
        this.resId = resId;
    }

    public EmotionEvent(String message, int resId) {
        super(message);
        this.resId = resId;
    }

    public EmotionEvent(String message,int[] resArr,int resId) {
        super(message);
        this.resArr = resArr;
        this.resId = resId;
    }

    public int[] getResArr() {
        return resArr;
    }

    public void setResArr(int[] resArr) {
        this.resArr = resArr;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
