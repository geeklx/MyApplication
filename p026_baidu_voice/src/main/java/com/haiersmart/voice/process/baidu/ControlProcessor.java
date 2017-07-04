package com.haiersmart.voice.process.baidu;

import android.text.TextUtils;

import com.haiersmart.voice.bean.ControlResultBean;
import com.haiersmart.voice.process.engine.SpeechHandleIntermediary;
import com.haiersmart.voice.utils.VoiceConstants;

/**
 * Create with Android Studio.
 *
 * @author Hsueh
 * @email i@hsueh.top
 * @date 2017/2/16 20:09
 */

public class ControlProcessor extends BaiduBaseProcessor<ControlResultBean> {

    /*音量调节的值*/
    private static final int VOLUME = 10;
    /*屏幕亮度*/
    private static final int BRIGHT = 30;

    @Override
    protected void process(ControlResultBean commonResultBean, SpeechHandleIntermediary speechHandle) {
        ControlResultBean.ResultBean.NluBean.SlotsBean slots = commonResultBean.getResult().getNlu().getSlots();
//        FridgeControlUtil mFridgeControlUtil = FridgeControlUtil.getInstance();
//        mFridgeControlUtil.init();
        String intent = commonResultBean.getResult().getNlu().getIntent();
        switch (intent) {
            case VoiceConstants.BAIDU_INTENT_TYPE_VOLUME:
                if (slots != null) {
                    String vol_down = slots.getVol_down();
                    String vol_up = slots.getVol_up();
                    String vol_mute = slots.getVol_mute();
                    if (!TextUtils.isEmpty(vol_down)) {
//                        RomSystemSetting.RaiseOrLowerVolume(FridgeApplication.get(), false, VOLUME);
                        speechHandle.ttsPlay("已帮您调小音量");
                    } else if (!TextUtils.isEmpty(vol_up)) {
//                        RomSystemSetting.RaiseOrLowerVolume(FridgeApplication.get(), true, VOLUME);
                        speechHandle.ttsPlay("已帮您调大音量");
                    } else if (!TextUtils.isEmpty(vol_mute)) {
//                        RomSystemSetting.setMinVolume(FridgeApplication.get());
                        speechHandle.ttsPlay("已帮您静音");
                    } else {
                        speechHandle.ttsPlay("小馨没有听清楚您的命令哦 , 请重试");
                    }
                }
                break;
            case VoiceConstants.BAIDU_INTENT_TYPE_BRIGHT:
                String down = slots.getDown();
                String up = slots.getUp();

                if (!TextUtils.isEmpty(up) && TextUtils.isEmpty(down)) {
//                    mFridgeControlUtil.changeScreenBrightness("up", BRIGHT);
                } else if (!TextUtils.isEmpty(down) && TextUtils.isEmpty(up)) {
//                    mFridgeControlUtil.changeScreenBrightness("down", BRIGHT);
                }
                break;
            default:
                break;
        }


    }
}
