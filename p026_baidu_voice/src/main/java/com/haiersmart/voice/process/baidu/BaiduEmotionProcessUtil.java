package com.haiersmart.voice.process.baidu;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.ImageView;

import com.haiersmart.sfnation.R;
import com.haiersmart.sfnation.common.AppManager;
import com.haiersmart.voice.utils.AnimRunUtil;
import com.haiersmart.voice.utils.FloatAnimUtil;
import com.haiersmart.voice.utils.FloatPresenter;
import com.haiersmart.voice.utils.VoiceEmotionContants;

import static com.haiersmart.voice.utils.VoiceEmotionContants.EMOTION_SPEAK;

/**
 * 表情模式处理器
 * Created by JefferyLeng on 2017/1/3.
 */
public class BaiduEmotionProcessUtil {

    private static final BaiduEmotionProcessUtil EMOTION_PROCESS_UTIL = new BaiduEmotionProcessUtil();

    public static BaiduEmotionProcessUtil getEmotionProcessUtil() {
        return EMOTION_PROCESS_UTIL;
    }

    /**
     * 处理表情
     *
     * @param emotion 表情
     */
    public void processEmotion(String emotion) {
        if (TextUtils.isEmpty(emotion)) {
            return;
        }
        int resId = 0;
        int[] resArr = new int[0];
        Activity activity = AppManager.getInstance().top();
        String message = emotion;
        switch (emotion) {
            case VoiceEmotionContants.EMOTION_HAPPY:
                resArr = AnimRunUtil.generateDrawableArray(activity, "happy_", 1, 12);
                break;
            //眨眼说话
            case VoiceEmotionContants.EMOTION_NAUGHTY:
            case VoiceEmotionContants.EMOTION_CALM:
            case VoiceEmotionContants.EMOTION_CONFUSED:
            case VoiceEmotionContants.EMOTION_EXCITED:
            case VoiceEmotionContants.EMOTION_SUSPECT:
            case VoiceEmotionContants.EMOTION_DISPITE:
            case VoiceEmotionContants.EMOTION_LIKE:
            case VoiceEmotionContants.EMOTION_PROUD:
            case VoiceEmotionContants.EMOTION_SURPRISE:
            case VoiceEmotionContants.EMOTION_POSITIVE:
            case VoiceEmotionContants.EMOTION_FRIDGECONTROL:
//				int i = new Random().nextInt(2);
//				if (0 == i) {
////					resId = R.drawable.animation_face_emotion_blink_1;
//					resArr = AnimRunUtil.generateDrawableArray(activity, "blink_", 25);
//				} else {
////					resId = R.drawable.animation_face_emotion_blink_2;
//					resArr = AnimRunUtil.generateDrawableArray(activity, "blink_", 26, 52);
//				}
                resArr = AnimRunUtil.generateDrawableArray(activity, "blink_", 57);
                break;

            //抱歉
            case VoiceEmotionContants.EMOTION_ANGRY:
            case VoiceEmotionContants.EMOTION_DISAPPOINTED:
            case VoiceEmotionContants.EMOTION_NEGATIVE:
            case VoiceEmotionContants.EMOTION_SAD:
            case VoiceEmotionContants.EMOTION_WORRY:
//				resId = R.drawable.animation_face_emotion_sorry;
                resArr = AnimRunUtil.generateDrawableArray(activity, "sorry_", 1, 25);
                break;
            case VoiceEmotionContants.EMOTION_MUSIC:
//				resId = R.drawable.animation_face_emotion_music;
                resArr = AnimRunUtil.generateDrawableArrayStartOne(activity, "dance_", 1, 15);
                break;

            case VoiceEmotionContants.EMOTION_CALENDAR:
//				resId = R.drawable.animation_calendar;
                resArr = AnimRunUtil.generateDrawableArray(activity, "calendar_", 15);
                break;
            case VoiceEmotionContants.EMOTION_INTERPRETATION:
//				resId = R.drawable.animation_face_emotion_boshi_one;
                resArr = AnimRunUtil.generateDrawableArrayStartOne(activity, "boshi_", 1, 57);
                break;
            case VoiceEmotionContants.EMOTION_WELCOME:
//                resArr = AnimRunUtil.generateDrawableArrayStartOne(activity, "entrance_", 1, 28);
                resArr = AnimRunUtil.generateDrawableArrayStartOneForth(activity, "huanxing_", 0, 173);
                break;
            case VoiceEmotionContants.EMOTION_SLEEP:
//                resArr = AnimRunUtil.generateDrawableArray(activity, "sleeply_", 1, 39);
                resArr = AnimRunUtil.generateDrawableArrayStartOneForth(activity, "sleep_", 0, 100);
                break;
            case EMOTION_SPEAK:
//                resArr = AnimRunUtil.generateDrawableArray(activity, "sleeply_", 1, 39);
                resArr = AnimRunUtil.generateDrawableArrayStartOneForth(activity, "listen_", 1, 50);
                break;
            default:
                message = VoiceEmotionContants.EMOTION_DEFAULT;
                resId = R.drawable.blink_00;
                resArr = AnimRunUtil.generateDrawableArray(activity, "blink_", 57);
                break;

        }

//        if (activity instanceof MainActivity_Voice) {dd
//			EventBus.getDefault().postSticky(new EmotionEvent(message, resId));
//            EventBus.getDefault().post(new EmotionEvent(message, resArr, resId));
//        } else {//小形象
        ImageView myFV = FloatPresenter.getMyFV();
//        FloatAnimUtil.getFloatAnimUtil().startFloatAnimUtil(myFV, resArr, 120);
        if (emotion.equals(EMOTION_SPEAK)) {
            FloatAnimUtil.getFloatAnimUtil().startFloatAnimUtil(myFV, resArr, 100);
        } else {
            FloatAnimUtil.getFloatAnimUtil().startFloatAnimUtil(myFV, resArr, 1);
        }
//        if (emotion.equals(VoiceEmotionContants.EMOTION_DEFAULT)) {
//            Log.e("Speech", "processEmotion: ");
////            FloatAnimUtil.getFloatAnimUtil().startDefaultImg(myFV, resId);
//            FloatAnimUtil.getFloatAnimUtil().startFloatAnimUtil(myFV, resArr, 120);
//        } else {
//            FloatAnimUtil.getFloatAnimUtil().startFloatAnimUtil(myFV, resArr, 120);
//            myFV.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    FloatAnimUtil.getFloatAnimUtil().stop();
//                    FloatPresenter.destory();
//                }
//            }, 3 * 1000);
//			AnimationUtil.startFrameAnimation(myFV, resId);
//            }
//        }
//
    }


    public static void stopEmotion() {
        FloatAnimUtil.getFloatAnimUtil().stop();
    }
}
