package com.haiersmart.voice.process.baidu;

import com.haiersmart.voice.bean.TrainResultBean;
import com.haiersmart.voice.process.engine.SpeechHandleIntermediary;

/**
 * Create with Android Studio.
 *
 * @author Hsueh
 * @email i@hsueh.top
 * @date 2017/2/17 15:45
 */

public class TrainProcessor extends BaiduBaseProcessor<TrainResultBean> {

    @Override
    protected void process(TrainResultBean commonResultBean, SpeechHandleIntermediary speechHandle) {
        speechHandle.ttsPlay("这个暂时不支持呦");
//        WeatherPop.getWeatherPop().dismissWeatherPop();

//        List<TrainResultBean.ResultBean.ViewsBean> views = commonResultBean.getResult().getViews();
//        String content = commonResultBean.getResult().getSpeech().getContent();
//        if (views.size()>1) {
//            List<TrainResultBean.ResultBean.ViewsBean.ListBean> list = views.get(1).getList();
//            if (list != null) {
//                if (list.size()>0) {
//                    String url = list.get(0).getUrl();
//                    if (TextUtils.isEmpty(url)) {
//                        speechHandle.ttsPlay("找不到您需要的内容,请重试");
//                        return;
//                    }
//                    Log.d(TAG, "process: baikeurl----->"+url);
////                    BaikePop.getBaikePop().showBaikePop(url);
//
//                    if (!TextUtils.isEmpty(content)) {
//                        speechHandle.ttsPlay(content);
//                    }
//                }
//            }
//        }
    }
}
