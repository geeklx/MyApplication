package com.haiersmart.voice.process.baidu;

import android.text.TextUtils;

import com.haiersmart.sfnation.common.MyLogUtil;
import com.haiersmart.sfnation.util.AISpeechUtil;
import com.haiersmart.voice.bean.BaiduOtherResultBean;
import com.haiersmart.voice.process.engine.SpeechHandleIntermediary;

import java.util.List;

import static com.haiersmart.voice.utils.VoiceEmotionContants.EMOTION_INTERPRETATION;

/**
 * 没有具体制定的类型 处理
 * example: 你好 service为null
 * Created by JefferyLeng on 2016/12/22.
 */
public class OtherProcessor extends BaiduBaseProcessor<BaiduOtherResultBean> {

    @Override
    protected void process(BaiduOtherResultBean commonResultBean, SpeechHandleIntermediary speechHandle) {
//        speechHandle.ttsPlay("这个暂时不支持呦");
////        WeatherPop.getWeatherPop().dismissWeatherPop();
////        AudioPop.getAudioPop().dismiss();
        BaiduOtherResultBean.ResultBean result = commonResultBean.getResult();
        if (result != null) {
            String se_query = commonResultBean.getSe_query();
            String content = result.getSpeech().getContent();
            String domain = result.getNlu().getDomain();
            List<BaiduOtherResultBean.ResultBean.ViewsBean> views = result.getViews();
            if (views != null && views.size() > 0) {
                String type = views.get(0).getType();
                List<BaiduOtherResultBean.ResultBean.ViewsBean.ListBean> list = views.get(0).getList();
                if (!TextUtils.isEmpty(type)) {
                    switch (type) {
                        case "list":
                            if (list != null) {
                                if (list.size() > 0) {
                                    String url = null;
                                    if (list.size() <= 1) {
                                        url = list.get(0).getUrl();
                                    } else if (list.size() >= 2) {
                                        url = list.get(1).getUrl();
                                    }
                                    if (!TextUtils.isEmpty(url)) {
                                        MyLogUtil.d(TAG, "process: baikeurl----->" + url);
//                                        BaikePop.getBaikePop().showBaikePop(url);
//                                        ImagePop.getImagePop().dismissImagePop();
//                                        speechHandle.ttsPlay(content);
                                        playTtsAndAnim(content, speechHandle);
                                    }
                                }
                            }
                            break;
                        case "image":
                            if (list != null && list.size() > 0) {
                                String src = list.get(0).getSrc();
                                if (src != null && !TextUtils.isEmpty(src)) {
//                                    ImagePop.getImagePop().showImagePop(src);
//                                    BaikePop.getBaikePop().dismissBaikePop();
//                                    speechHandle.ttsPlay(content);
                                    playTtsAndAnim(content, speechHandle);
                                }
                            }
                            break;
                        default:
                            if (!TextUtils.isEmpty(content)) {
//                                speechHandle.ttsPlay(content);
                                playTtsAndAnim(content, speechHandle);
//                                BaikePop.getBaikePop().dismissBaikePop();
//                                ImagePop.getImagePop().dismissImagePop();
                            } else {
//                                speechHandle.ttsPlay("对不起,人家听不懂啦...");
                                playTtsAndAnim("对不起,人家听不懂啦", speechHandle);
                            }
                            break;
                    }
                }
            } else {

                if (!TextUtils.isEmpty(content)) {
                    //                    PopManager.clearPage();
//                    speechHandle.ttsPlay(content);
                    playTtsAndAnim(content, speechHandle);
//                    BaikePop.getBaikePop().dismissBaikePop();
//                    ImagePop.getImagePop().dismissImagePop();
                } else {
//                    if (!TextUtils.isEmpty(se_query) && "设置".equals("设置")){
//                        speechHandle.ttsPlay("小馨不知道您要设置什么呀 , 您可以这样说, 打开设置...");
//                    }else {
//                        SpeechHandleIntermediary.build().ttsPlay("对不起，人家还在不停的成长呦，这个暂时不支持呦~");
//                    }
                }

            }
        } /*else {
            speechHandle.ttsPlay("小馨不知道您要设置什么呀 , 您可以这样说, 打开设置...");
        }*/


//            switch (type) {
//                case "image":
//                    SpeechHandleIntermediary.build().ttsPlay("对不起，人家还在不停的成长呦，这个暂时不支持呦~");
//                    return;
//
//            }


    }


    private void playTtsAndAnim(String content, SpeechHandleIntermediary speechHandle) {
        if (content.contains("为你找到以下内容") || content.contains("不出门，把世界带回家")) {
            speechHandle.ttsPlay("这个暂时不支持呦");
            return;
        }
        AISpeechUtil.playAnim(EMOTION_INTERPRETATION, true);
        speechHandle.ttsPlay(content);
    }
}
