package com.haiersmart.voice.process.baidu;

import android.text.TextUtils;
import android.util.Log;

import com.haiersmart.sfnation.util.AISpeechUtil;
import com.haiersmart.voice.bean.BaiduCookbookResultBean;
import com.haiersmart.voice.process.engine.SpeechHandleIntermediary;

/**
 * Create with Android Studio.
 *
 * @author Hsueh
 * @email i@hsueh.top
 * @date 2017/2/13 14:31
 */

public class CookBookProcessor extends BaiduBaseProcessor<BaiduCookbookResultBean> {

    private final String COOKBOOK_INTENT_OPEN = "cookbook.open";

    private final String COOKBOOK_INTENT_COLLECT = "cookbook.collect";

    private final String COOKBOOK_INTENT_SYMPTOM = "cookbook.symptom";

    @Override
    protected void process(BaiduCookbookResultBean commonResultBean, SpeechHandleIntermediary speechHandle) {
        String cookbookIntent = commonResultBean.getResult().getNlu().getIntent();
        switch (cookbookIntent) {
            case COOKBOOK_INTENT_OPEN:
            case COOKBOOK_INTENT_SYMPTOM:
                handleOpen(commonResultBean);
                break;
            case COOKBOOK_INTENT_COLLECT:
                handleCollect();
                break;

        }


    }

    /**
     * 处理收藏类操作
     */
    private void handleCollect() {
//        if (!UserUtils.isUserLogin()) {
//            //未登录状态
//            SpeechHandleIntermediary.build().ttsPlay("您还没有登录呦~");
//        } else {
//            if (AppManager.getInstance().top() instanceof MyCollectActivity) {
//                SpeechHandleIntermediary.build().ttsPlay("当前已是收藏页了");
//            } else {
//                SpeechHandleIntermediary.build().ttsPlay("已为您打开收藏页");
//                Intent collectIntent = new Intent();
//                collectIntent.setClass(mContext, MyCollectActivity.class);
//                mContext.startActivity(collectIntent);
//            }
//
//        }
    }

    /**
     * 处理打开类操作
     */
    private void handleOpen(BaiduCookbookResultBean commonResultBean) {
        String dish = commonResultBean.getResult().getNlu().getSlots().getDish();
        String material = commonResultBean.getResult().getNlu().getSlots().getMaterial();
        if (!TextUtils.isEmpty(dish) || !TextUtils.isEmpty(material)) {
//            Intent intent = new Intent(mContext, CookBookSearchResultActivity.class);
//            if (!TextUtils.isEmpty(material)) {
//                intent.putExtra(ConstantUtil.INTENT_INFO1, material);
//            }
//            if (!TextUtils.isEmpty(dish)) {
//                intent.putExtra(ConstantUtil.INTENT_INFO1, dish);
//            }
//            intent.putExtra("voice", true);
//            mContext.startActivity(intent);
            SpeechHandleIntermediary.build().ttsPlay("好的,已帮您搜索完成");
        } else {
            BaiduCookbookResultBean.ResultBean.NluBean.SlotsBean slots = commonResultBean.getResult().getNlu().getSlots();
            //获取到菜系
            String system = slots.getSystem();
            //口味
            String flavor = slots.getFlavor();
            //食疗
            String symptom = slots.getSymptom();
            Log.d(TAG, "handleOpen: ----> system : " + system + ",flavor : " + flavor + ",symptom : " + symptom);
            if (!TextUtils.isEmpty(system)) {
                String uri = "hios://com.haiersmart.sfnation.ui.cookbooknew.CookBookActivityNew?i00={s}home&i03={s}菜系&i04={s}" + system;
//                BannerClickHelper.shouldOverrideUrl(mContext, uri);
            }
            if (!TextUtils.isEmpty(flavor)) {
                String uri = "hios://com.haiersmart.sfnation.ui.cookbooknew.CookBookActivityNew?i00={s}home&i03={s}口味&i04={s}" + flavor;
//                BannerClickHelper.shouldOverrideUrl(mContext, uri);
            }
            if (!TextUtils.isEmpty(symptom)) {
                String uri = "hios://com.haiersmart.sfnation.ui.cookbooknew.CookBookActivityNew?i00={s}home&i03={s}食疗&i04={s}" + symptom;
//                BannerClickHelper.shouldOverrideUrl(mContext, uri);
            }
            AISpeechUtil.startPlayTTS("好的，已经为您打开菜谱");
        }
    }
}
