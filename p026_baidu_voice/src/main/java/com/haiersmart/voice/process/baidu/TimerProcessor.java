/**
 * Copyright 2016,Smart Haier.All rights reserved
 * Description:定时器processor
 * Author:jiayuzhen
 * ModifyBy:
 * ModifyDate:
 * ModifyDes:
 */
package com.haiersmart.voice.process.baidu;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.haiersmart.voice.bean.TimerResultBean;
import com.haiersmart.voice.process.engine.SpeechHandleIntermediary;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by yuzhen on 2017/1/23.
 * 计时器处理器
 *
 * @time 2017/1/23  9:39
 */
public class TimerProcessor extends BaiduBaseProcessor<TimerResultBean> {

    private Timer mTimer;
    private TimerTask mTimerTask;
    //计时器时间 , 毫秒
    private int mTime;
    //是否在计时执行
    private boolean isExecuting = false;

    private String type;

    @Override
    protected void process(TimerResultBean commonResultBean, SpeechHandleIntermediary speechHandle) {
        speechHandle.ttsPlay("这个暂时不支持呦");

        String content = commonResultBean.getResult().getSpeech().getContent();
//        TimerResultBean.ResultBean.NluBean.SlotsBean slots = commonResultBean.getResult().getNlu().getSlots();
//        /**
//         * 服务器返回的时间字段插槽
//         * second 秒
//         * minute 分钟
//         * hour 小时
//         */
//        String second = slots.getSecond();
//        String minute = slots.getMinute();
//        String hour = slots.getHour();
//        //根据返回的字段进行判断和转换,结果 毫秒
//        if (second != null && !TextUtils.isEmpty(second) && TextUtils.isEmpty(minute) && TextUtils.isEmpty(hour)) {
//            mTime = Integer.parseInt(second) * 1000;
//            type = "second";
//        } else if (minute != null && !TextUtils.isEmpty(minute) && TextUtils.isEmpty(second) && TextUtils.isEmpty(hour)) {
//            mTime = Integer.parseInt(minute) * 60 * 1000;
//            type = "minute";
//        } else /*if (hour!=null && !TextUtils.isEmpty(hour) && TextUtils.isEmpty(second) && TextUtils.isEmpty(second)){
//            mTime = Integer.parseInt(hour)*60*60;
//        }*/ {
//            speechHandle.ttsPlay("小馨还在不停成长 , 暂时不支持设置一小时以及一小时以上的计时器哦 ");
//            return;
//        }
//
//
//        if (!isExecuting) {
//            if ("second".equals(type)){
//                speechHandle.ttsPlay("好的,已为您设置了一个"+second+"秒的计时器");
//            }else if ("minute".equals(type)){
//                speechHandle.ttsPlay("好的,已为您设置了一个"+minute+"分钟的计时器");
//            }
//            startTime();
//        } else {
//            speechHandle.ttsPlay("计时器已经在执行");
//        }

    }

    //计时器
    private Handler timerHandler = new Handler() {
        public void handleMessage(Message msg) {
            mTime -= 1000;
            if (mTime <= 0) {
                isExecuting = false;
                Log.i(TAG, "计时结束");
                if (mTimer != null) {
                    mTimer.cancel();
                    mTimer = null;
                }
                if (mTimerTask != null) {
                    mTimerTask.cancel();
                    mTimerTask = null;
                }

                //TODO 倒计时结束
//                Activity activity = AppManager.getAppManager().currentActivity();
//                Intent intent = new Intent(activity, TimerFinishActivity_630.class);
//                activity.startActivity(intent);


            }
        }
    };

    //开始计时计时
    private void startTime() {
        isExecuting = true;
        if (mTimer == null) {
            mTimer = new Timer();
        }
        if (mTimerTask == null) {
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    Message msg = new Message();
                    msg.what = 0;
                    timerHandler.sendMessage(msg);
                }
            };
            mTimer.schedule(mTimerTask, 0, 1000);
        }

    }


}
