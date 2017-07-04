package com.haiersmart.voice.process.baidu;///**
// * Copyright 2016,Smart Haier.All rights reserved
// * Description:日历小馨
// * Author:jiayuzhen
// * ModifyBy:
// * ModifyDate:
// * ModifyDes:
// */
//package com.haiersmart.voice.process.baidu;
//
//import android.app.Activity;
//import android.text.TextUtils;
//
//import com.haiersmart.utilslib.app.AppManager;
//import com.haiersmart.voice.bean.BaiduCalendarResultBean;
//import com.haiersmart.voice.process.engine.SpeechHandleIntermediary;
//
///**
// * Created by yuzhen on 2017/1/15.
// *@time 2017/1/15  18:06
// */
//public class CalendarProcessor extends BaiduBaseProcessor<BaiduCalendarResultBean> {
//
//
//    @Override
//    protected void process(BaiduCalendarResultBean commonResultBean, SpeechHandleIntermediary speechHandle) {
//        String content = commonResultBean.getResult().getSpeech().getContent();
//        if (content != null && !TextUtils.isEmpty(content)) {
//            Activity top = AppManager.getInstance().top();
////            CalendarPop.showPopFoodManager(top);
//            speechHandle.ttsPlay(content);
//        }
//    }
//}
