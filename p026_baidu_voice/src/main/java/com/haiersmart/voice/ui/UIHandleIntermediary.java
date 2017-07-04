package com.haiersmart.voice.ui;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.haiersmart.sfnation.common.AppActionActivity;
import com.haiersmart.sfnation.common.AppManager;
import com.haiersmart.sfnation.common.NewAppManager;
import com.haiersmart.voice.process.engine.SpeechHandleIntermediary;

/**
 * UI操作中间人
 * Created by JefferyLeng on 2017/2/9.
 */
public class UIHandleIntermediary {

    public static final String TTS_OPEN_APP = "好的，已经为您打开";
    public static final String TTS_CLOSE_APP = "好的，已经为您关闭";

    public static final String TTS_UNSOPPORTED_OPERATION = "抱歉，不支持该操作呦！";

    /**
     * 传递intent的方式开启activity
     *
     * @param intent
     */
    public static void startActivityWithIntent(Intent intent, String ttsResponse) {
        if (TextUtils.isEmpty(ttsResponse)) {
            SpeechHandleIntermediary.build().ttsPlay(TTS_OPEN_APP);
        } else {
            SpeechHandleIntermediary.build().ttsPlay(ttsResponse);
        }

        Activity activity = AppManager.getInstance().top();
        activity.startActivity(intent);
    }

    public static void startActivity(Class<? extends Activity> activity) {
        Activity currentActivity = AppManager.getInstance().top();
//        SpeechHandleIntermediary.build().ttsPlay(TTS_OPEN_APP);
        currentActivity.startActivity(new Intent(currentActivity, activity));

    }

    /**
     * 根据类名打开activity
     *
     * @param className
     */
    public static void startActivity(String className) {
        //old
//        Class<?> targetClass = null;
//        try {
//            targetClass = Class.forName(className);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        Activity currentActivity = AppManager.getInstance().top();
//        SpeechHandleIntermediary.build().ttsPlay(TTS_OPEN_APP);
//        if (targetClass == null) {
//            return;
//        }
//
//        currentActivity.startActivity(new Intent(currentActivity, targetClass));
        //new
        Activity currentActivity = AppManager.getInstance().top();
        SpeechHandleIntermediary.build().ttsPlay(TTS_OPEN_APP);
        Intent intent = new Intent();
        intent.setAction(className);
        currentActivity.startActivity(intent);
        //add 已显示
        AppActionActivity appActionActivity = new AppActionActivity(className, true);
        NewAppManager.getInstance().add(appActionActivity);

    }

//    public static void exitSpeechEmotion() {
//        EventBus.getDefault().post(new CloseEvent(true));
//    }

    /**
     * 关闭某个activity
     *
     * @param activity
     */
    public static void finishActivity(Class<? extends Activity> activity) {
        Activity currentActivity = AppManager.getInstance().top();
        if (currentActivity.getClass().equals(activity)) {
            SpeechHandleIntermediary.build().ttsPlay(TTS_CLOSE_APP);
            AppManager.getInstance().finish(activity);
        }
    }

    public static void finishActivity(String className) {
        Activity currentActivity = AppManager.getInstance().top();
        Class<?> targetClass = null;
        try {
            targetClass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (currentActivity.getClass().equals(targetClass)) {
            SpeechHandleIntermediary.build().ttsPlay(TTS_CLOSE_APP);
            AppManager.getInstance().finish(targetClass);
        } else {
            SpeechHandleIntermediary.build().ttsPlay(TTS_UNSOPPORTED_OPERATION);
        }
    }
}
