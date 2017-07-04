package com.haiersmart.voice.process.baidu;

import android.text.TextUtils;

import com.haiersmart.sfnation.util.AISpeechUtil;
import com.haiersmart.voice.bean.BaiduAudioResultBean;
import com.haiersmart.voice.process.engine.SpeechHandleIntermediary;

import static com.haiersmart.voice.utils.VoiceEmotionContants.EMOTION_MUSIC;

/**
 * 百度音频处理器
 * Created by xuejinge on 2017/2/14.
 */
public class BaiduAudioProcessor extends BaiduBaseProcessor<BaiduAudioResultBean> {

    private final String TTS_NO_RESPONSE = "没有找到目标节目，可以换一个试试";

    @Override
    protected void process(BaiduAudioResultBean commonResultBean, SpeechHandleIntermediary speechHandle) {
//        BaiduAudioResultBean.ResultBean.DirectivesBean.PayloadBean payload = commonResultBean.getResult().getDirectives().get(0).getPayload();
//        BaiduAudioResultBean.ResultBean.DirectivesBean.PayloadBean payload = null;
        BaiduAudioResultBean.ResultBean result = commonResultBean.getResult();
        if (result != null) {
            BaiduAudioResultBean.ResultBean.SpeechBean speech = result.getSpeech();
            if (speech != null) {
                String ttsContent = speech.getContent();
                final String[] strs = ttsContent.split(" ");
                String name = "";
                if (strs.length >= 1) {
                    name = strs[0].substring(2);
                }
                if (!TextUtils.isEmpty(name)) {
                    final String finalName = name;
                    AISpeechUtil.startPlayTTS("好的", new AISpeechUtil.TTSStateListener() {
                        @Override
                        public void stop() {
                            AISpeechUtil.stopAsrAndStartWakeup();
                            AISpeechUtil.playAnim(EMOTION_MUSIC, false);
//                            MusicControllerUtil.playMusicByKeyWord(finalName);//播放fm
                        }
                    });
                }
            }
        }

//        if (result != null) {
//            BaiduAudioResultBean.ResultBean.SpeechBean speech = result.getSpeech();
//            if (speech != null) {
//                ttsContent = speech.getContent();
//            }
//            List<BaiduAudioResultBean.ResultBean.DirectivesBean> directives = result.getDirectives();
//            if (directives != null && directives.size() > 0) {
//                BaiduAudioResultBean.ResultBean.DirectivesBean directivesBean = directives.get(0);
//                if (directivesBean != null) {
//                    payload = directivesBean.getPayload();
//                } else {
//                    SpeechHandleIntermediary.build().ttsPlay(ttsContent);
//                    return;
//                }
//            } else {
//                SpeechHandleIntermediary.build().ttsPlay(ttsContent);
//                return;
//            }
//        } else {
//            SpeechHandleIntermediary.build().ttsPlay(ttsContent);
//            return;
//        }
////        String content = commonResultBean.getResult().getSpeech().getContent();
//        if (null != payload) {
//            BaiduAudioResultBean.ResultBean.DirectivesBean.PayloadBean.AudioItemBean audio_item = payload.getAudio_item();
//            if (null != audio_item) {
//                BaiduAudioResultBean.ResultBean.DirectivesBean.PayloadBean.AudioItemBean.StreamBean stream = audio_item.getStream();
//                if (null != stream) {
//                    String url = stream.getUrl();
//                    if (TextUtils.isEmpty(url)) {
//                        speechHandle.ttsPlay(TTS_NO_RESPONSE);
//                        return;
//                    }
//                    Log.d("BaiduAudioProcessor", "播放音乐 url: " + url);
////                    EventBus.getDefault().post(new AudioEvent(url, "", ""));
////                    AudioPop.getAudioPop().showAudioPopWindowInVoice(url,null,ttsContent,0);
////                    BaikePop.getBaikePop().dismissBaikePop();
////                    ImagePop.getImagePop().dismissImagePop();
////                    WeatherPop.getWeatherPop().dismissWeatherPop();
//                }
//            } else {
//                if (!TextUtils.isEmpty(ttsContent)) {
//                    speechHandle.ttsPlay(ttsContent);
////                    BaikePop.getBaikePop().dismissBaikePop();
////                    AudioPop.getAudioPop().dismiss();
////                    ImagePop.getImagePop().dismissImagePop();
////                    WeatherPop.getWeatherPop().dismissWeatherPop();
//                }
//            }
//        } else {
//            SpeechHandleIntermediary.build().ttsPlay(ttsContent);
//        }


    }
}
