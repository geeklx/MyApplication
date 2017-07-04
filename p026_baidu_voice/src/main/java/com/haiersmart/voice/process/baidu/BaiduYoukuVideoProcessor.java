package com.haiersmart.voice.process.baidu;

import android.util.Log;

import com.haiersmart.voice.bean.BaiduYoukuResultBean;
import com.haiersmart.voice.process.engine.SpeechHandleIntermediary;

/**
 * 优酷视频处理器
 * Created by JefferyLeng on 2017/3/2.
 */
public class BaiduYoukuVideoProcessor extends BaiduBaseProcessor<BaiduYoukuResultBean> {

    private final String INTENT_SEARCH_FILE = "SEARCH_FILM";
    private final String RESPONSE_NOT_SUPPORT = "抱歉，暂时不支持视频功能的该操作哦";
    @Override
    protected void process(BaiduYoukuResultBean commonResultBean, SpeechHandleIntermediary speechHandle) {
        // TODO: 2017/3/2 获取到 youku result bean --> nlu  --> intent 判断 SEARCH_FILM
        // nlu --> slots --> film_type 传递到intent中
        BaiduYoukuResultBean.ResultBean.NluBean nlu = commonResultBean.getResult().getNlu();

        if (nlu == null) {
            speechHandle.ttsPlay(RESPONSE_NOT_SUPPORT);
            return;
        }
        String intent = nlu.getIntent();
        switch (intent) {
            case INTENT_SEARCH_FILE :
                searchFilm(commonResultBean,speechHandle);
                break;
            default:
                speechHandle.ttsPlay(RESPONSE_NOT_SUPPORT);
                break;
        }

    }

    /**
     * 搜索具体的视频分类
     * @param commonResultBean
     */
    private void searchFilm(BaiduYoukuResultBean commonResultBean, SpeechHandleIntermediary speechHandle) {
        BaiduYoukuResultBean.ResultBean.NluBean.SlotsBean slots = commonResultBean.getResult().getNlu().getSlots();
        String film_type = "";
        if (slots != null) {
            film_type = slots.getFilm_type();
        }
        Log.d(TAG, "process: --> 优酷视频处理器 filmType : " + film_type);

//        if (AppManager.getInstance().top() instanceof YouKuMainActivityNew) {
//            speechHandle.ttsPlay("当前已是优酷界面了");
//        } else {
//            speechHandle.ttsPlay("好的，已经为您打开优酷");
//            UIHandleIntermediary.startActivity(YouKuMainActivityNew.class);
//        }
    }
}
