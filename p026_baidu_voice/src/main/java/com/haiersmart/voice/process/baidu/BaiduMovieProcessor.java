package com.haiersmart.voice.process.baidu;

import com.haiersmart.voice.bean.BaiduMovieResultBean;
import com.haiersmart.voice.process.engine.SpeechHandleIntermediary;

/**
 * 视频相关业务处理器
 * Created by JefferyLeng on 2017/2/20.
 */

public class BaiduMovieProcessor extends BaiduBaseProcessor<BaiduMovieResultBean> {

    @Override
    protected void process(BaiduMovieResultBean commonResultBean, SpeechHandleIntermediary speechHandle) {
//        speechHandle.ttsPlay(mContext.getString(R.string.ai_voice_response_youku_open));

//        if (AppManager.getInstance().top() instanceof YouKuMainActivityNew) {
//            speechHandle.ttsPlay("当前已是优酷界面了");
//        } else {
//            speechHandle.ttsPlay("好的，已经为您打开优酷");
//            UIHandleIntermediary.startActivity(YouKuMainActivityNew.class);
//        }
    }
}
