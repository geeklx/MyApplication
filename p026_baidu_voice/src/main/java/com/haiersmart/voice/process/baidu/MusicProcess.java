package com.haiersmart.voice.process.baidu;

import android.os.Handler;
import android.text.TextUtils;

import com.haiersmart.sfnation.util.AISpeechUtil;
import com.haiersmart.voice.bean.BaiduMusicResultBean;
import com.haiersmart.voice.process.engine.SpeechHandleIntermediary;
import com.haiersmart.voice.utils.VoiceConstants;

import static com.haiersmart.voice.utils.VoiceEmotionContants.EMOTION_MUSIC;

/**
 * 音乐处理器
 * Created by JefferyLeng on 2016/12/26.
 */
public class MusicProcess extends BaiduBaseProcessor<BaiduMusicResultBean> {
    private Handler handler = new Handler();

    @Override
    protected void process(BaiduMusicResultBean commonResultBean, SpeechHandleIntermediary speechHandle) {
        String intent = commonResultBean.getResult().getNlu().getIntent();
        final String content = commonResultBean.getResult().getSpeech().getContent();
        if (!TextUtils.isEmpty(intent)) {
            switch (intent) {
                case VoiceConstants.BAIDU_INTENT_TYPE_MUSIC://播放音乐
                    AISpeechUtil.startPlayTTS("正在为您播放" + content, new AISpeechUtil.TTSStateListener() {
                        @Override
                        public void stop() {
                            AISpeechUtil.stopAsrAndStartWakeup();
                            AISpeechUtil.playAnim(EMOTION_MUSIC, false);
                            if ((content).equals("")) {//播放音乐
//                                NetsSearchUtil.playMusicByKeyword("null");
                            } else {
//                                NetsSearchUtil.playMusicByKeyword(content);
                            }
                        }
                    });

//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            if ((content).equals("")) {//播放音乐
//                                NetsSearchUtil.playMusicByKeyword("null");
//                            } else {
//                                NetsSearchUtil.playMusicByKeyword(content);
//                            }
//                        }
//                    }, 1000 * 3);
                    break;
                default:
                    break;
            }
        }

//        if (VoiceConstants.BAIDU_INTENT_TYPE_MUSIC.equals(intent)
//                || VoiceConstants.BAIDU_INTENT_TYPE_MUSIC_NEXT.equals(intent)) {
//            if (commonResultBean != null) {
//                BaiduMusicResultBean.ResultBean result = commonResultBean.getResult();
//                if (result != null) {
//                    List<BaiduMusicResultBean.ResultBean.DirectivesBean> directives = result.getDirectives();
//                    if (directives != null) {
//                        BaiduMusicResultBean.ResultBean.DirectivesBean.PayloadBean payload = directives.get(0).getPayload();
//                        if (payload != null) {
//                            BaiduMusicResultBean.ResultBean.DirectivesBean.PayloadBean.AudioItemBean audio_item = payload.getAudio_item();
//                            if (audio_item != null) {
//                                BaiduMusicResultBean.ResultBean.DirectivesBean.PayloadBean.AudioItemBean.StreamBean stream = audio_item.getStream();
//                                if (stream != null) {
//                                    String url = stream.getUrl();
//                                    if (url != null) {
//                                        EventBus.getDefault().post(new AudioEvent("","",content));
////                                        AudioPop.getAudioPop().showAudioPopWindowInVoice(url,null,content,0);
//                                    }
//                                }
//                            }
//
//                        }
//                    }else {
//                        speechHandle.ttsPlay(content);
//                    }
//                }
//            }
//        }else if (VoiceConstants.BAIDU_INTENT_TYPE_MUSIC_ASK.equals(intent)){
//
//            if (content != null && !TextUtils.isEmpty(content)) {
//                speechHandle.ttsPlay(content);
//            }
//        }else {
//            if (content != null && !TextUtils.isEmpty(content)) {
//                speechHandle.ttsPlay(content);
//            }
//        }

    }
}
