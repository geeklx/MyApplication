package com.haiersmart.voice.process.baidu;

import android.text.TextUtils;
import android.util.Log;

import com.haiersmart.voice.bean.BaiduAudioResultBean;
import com.haiersmart.voice.bean.BaiduBaseResultBean;
import com.haiersmart.voice.bean.BaiduCommonResultBean;
import com.haiersmart.voice.bean.BaiduCookbookResultBean;
import com.haiersmart.voice.bean.BaiduFridgeResultBean;
import com.haiersmart.voice.bean.BaiduMovieResultBean;
import com.haiersmart.voice.bean.BaiduMusicResultBean;
import com.haiersmart.voice.bean.BaiduOtherResultBean;
import com.haiersmart.voice.bean.BaiduWeatherResultBean;
import com.haiersmart.voice.bean.BaiduYoukuResultBean;
import com.haiersmart.voice.bean.ControlResultBean;
import com.haiersmart.voice.bean.MessageBoardResultBean;
import com.haiersmart.voice.bean.TimerResultBean;
import com.haiersmart.voice.bean.TrainResultBean;
import com.haiersmart.voice.process.engine.SpeechHandleIntermediary;
import com.haiersmart.voice.utils.JsonUtils;
import com.haiersmart.voice.utils.VoiceConstants;

/**
 * Create with Android Studio.
 *
 * @author Hsueh
 * @email i@hsueh.top
 * @date 2017/2/8 21:17
 */

public class BaiduDureProcessCenter {

    private static String TAG = BaiduDureProcessCenter.class.getSimpleName();

    private static BaiduDureProcessCenter processCenter = new BaiduDureProcessCenter();


    public static BaiduDureProcessCenter getInstance() {
        return processCenter;
    }

    public void process(String voiceResult) {
        String domain = getDomainByResponse(voiceResult);
        String intent = getIntentByResponse(voiceResult);
        if (TextUtils.isEmpty(domain)) {
            Log.e(TAG, "process----: domain  is  empty ");
            return;
        }

        Log.d(TAG, "process: -----> " + "domain:" + domain + "-----intent:" + intent);
        if (TextUtils.isEmpty(domain) || TextUtils.isEmpty(intent)) {
            SpeechHandleIntermediary.build().ttsPlay("抱歉！小馨暂时不支持该功能哦");
            return;
        }
        BaiduBaseResultBean baseResultBean = buildByService(voiceResult, domain, intent);

        if (baseResultBean == null) {
//            SpeechHandleIntermediary.build().ttsPlay("这个暂时不支持呦");
            return;
        }
        processByType(domain, intent, baseResultBean);
    }

    /**
     * 根据类型进行处理
     */
    private void processByType(String domain, String intent, BaiduBaseResultBean response) {

        BaiduBaseProcessor baseProcessor = BaiduProcessorFactory.getInstance().createProcessor(domain, intent);
        if (baseProcessor != null) {
            baseProcessor.processSemantic(response);
        }
    }

    private BaiduBaseResultBean buildByService(String jsonBody, String domain, String intent) {
        Log.d(TAG, "buildByService: -------> jsonBody : " + jsonBody);
        BaiduBaseResultBean baseResultBean = null;
        // TODO: 2016/12/26 这里需要根据domain进行语义解析的判断转换
        try {
            if (VoiceConstants.BAIDU_DOMAIN_TYPE_MUSIC.equals(domain)) {
                baseResultBean = JsonUtils.fromJson(jsonBody, BaiduMusicResultBean.class);
            } else if (VoiceConstants.BAIDU_DOMAIN_TYPE_DEFAULT.equals(domain)) {
                baseResultBean = JsonUtils.fromJson(jsonBody, BaiduOtherResultBean.class);
            } else if (VoiceConstants.BAIDU_DOMAIN_TYPE_WEATHER.equals(domain)) {
                baseResultBean = JsonUtils.fromJson(jsonBody, BaiduWeatherResultBean.class);
            } else if (VoiceConstants.BAIDU_DOMAIN_TYPE_COOKBOOK.equals(domain)) {
                baseResultBean = JsonUtils.fromJson(jsonBody, BaiduCookbookResultBean.class);
            } else if (VoiceConstants.BAIDU_DOMAIN_TYPE_FRIDGE_CONTROL.equals(domain)) {
                baseResultBean = JsonUtils.fromJson(jsonBody, BaiduFridgeResultBean.class);
            } else if (VoiceConstants.BAIDU_DOMAIN_TYPE_AUDIO_UNICAST_CONTROL.equals(domain)) {
                baseResultBean = JsonUtils.fromJson(jsonBody, BaiduAudioResultBean.class);
            } else if (VoiceConstants.BAIDU_DOMAIN_TYPE_HARDWARE_CONTROL.equals(domain)) {
                baseResultBean = JsonUtils.fromJson(jsonBody, ControlResultBean.class);
            } else if (VoiceConstants.BAIDU_DOMAIN_TYPE_TRAIN_TICKET.equals(domain)
                    || VoiceConstants.BAIDU_DOMAIN_TYPE_QICHE_TICKET.equals(domain)
                    || VoiceConstants.BAIDU_DOMAIN_TYPE_FEIJI_TICKET.equals(domain)) {
                baseResultBean = JsonUtils.fromJson(jsonBody, TrainResultBean.class);
            } else if (VoiceConstants.BAIDU_DOMAIN_TYPE_MOVIE.equals(domain)) {
                baseResultBean = JsonUtils.fromJson(jsonBody, BaiduMovieResultBean.class);
            } else if (VoiceConstants.BAIDU_DOMAIN_TYPE_MESSAGEBOARD.equals(domain)) {
                baseResultBean = JsonUtils.fromJson(jsonBody, MessageBoardResultBean.class);
            } else if (VoiceConstants.BAIDU_DOMAIN_TYPE_TIMER.equals(domain)) {
                baseResultBean = JsonUtils.fromJson(jsonBody, TimerResultBean.class);
            } else if (VoiceConstants.BAIDU_DOMAIN_TYPE_YOUKU_VIDEO.equals(domain)) {
                baseResultBean = JsonUtils.fromJson(jsonBody, BaiduYoukuResultBean.class);
            } else {
                baseResultBean = JsonUtils.fromJson(jsonBody, BaiduOtherResultBean.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (baseResultBean != null) {
            baseResultBean.setDomain(domain);
            baseResultBean.setIntent(intent);
        }
        return baseResultBean;
    }


    private String getDomainByResponse(String jsonBody) {

        BaiduCommonResultBean commonResultBean = JsonUtils.fromJson(jsonBody, BaiduCommonResultBean.class);
        if (commonResultBean == null) {
            return "";
        }
        BaiduCommonResultBean.ResultBean.NluBean nlu = commonResultBean.getResult().getNlu();
        if (nlu != null) {
            String domain = nlu.getDomain();
            if (!TextUtils.isEmpty(domain)) {
                return domain;
            }
        }
        return "";
    }

    private String getIntentByResponse(String jsonBody) {

        BaiduCommonResultBean commonResultBean = JsonUtils.fromJson(jsonBody, BaiduCommonResultBean.class);
        if (commonResultBean == null) {
            return "";
        }
        BaiduCommonResultBean.ResultBean.NluBean nlu = commonResultBean.getResult().getNlu();
        if (nlu != null) {
            String intent = nlu.getIntent();
            if (!TextUtils.isEmpty(intent)) {
                return intent;
            }
        }
        return "";
    }


}
