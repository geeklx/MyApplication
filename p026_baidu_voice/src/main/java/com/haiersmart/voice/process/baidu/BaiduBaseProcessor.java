package com.haiersmart.voice.process.baidu;

import android.app.Activity;

import com.haiersmart.sfnation.common.AppManager;
import com.haiersmart.voice.bean.BaiduBaseResultBean;
import com.haiersmart.voice.process.engine.SpeechHandleIntermediary;
import com.haiersmart.voice.utils.L;

/**
 * 所有AI VOICE的处理器基类
 * Created by JefferyLeng on 2016/12/22.
 */
public abstract class BaiduBaseProcessor<T extends BaiduBaseResultBean> {

    protected final String TAG = this.getClass().getSimpleName();

    protected Activity mContext = AppManager.getInstance().top();

    private SpeechHandleIntermediary mSpeechHandle;

    protected BaiduBaseProcessor() {
        mSpeechHandle = SpeechHandleIntermediary.build();
    }

    /**
     * 暴露方法给外部调用 所有的实现细节由子类实现
     *
     * @param baseResultBean 海致服务器解析的语义对象
     */
    public final void processSemantic(BaiduBaseResultBean baseResultBean) {
        L.d(TAG, TAG + " --> process --> execute");
        T commonResultBean = (T) baseResultBean;
        process(commonResultBean, mSpeechHandle);
        String domain = baseResultBean.getDomain();
//        if (domain != null) {
//            BaiduEmotionProcessUtil.getEmotionProcessUtil().processEmotion(domain);
//        }
    }


    /**
     * implementation by subclass
     */
    protected abstract void process(T commonResultBean, SpeechHandleIntermediary speechHandle);

    /**
     * 数据验证 验证通过 返回原数据 验证不通过 返回null
     *
     * @param baiduBaseResultBean 需要验证的数据
     * @return
     */
    @Deprecated
    protected BaiduBaseResultBean verifyData(BaiduBaseResultBean baiduBaseResultBean) {
        return null;
    }

}
