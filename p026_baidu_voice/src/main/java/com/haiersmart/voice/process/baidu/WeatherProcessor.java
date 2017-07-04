package com.haiersmart.voice.process.baidu;

import com.haiersmart.sfnation.util.AISpeechUtil;
import com.haiersmart.voice.bean.BaiduWeatherResultBean;
import com.haiersmart.voice.process.engine.SpeechHandleIntermediary;

import java.util.List;

import static com.haiersmart.voice.utils.VoiceEmotionContants.EMOTION_INTERPRETATION;

/**
 * 天气处理器：目前只支持语音播报天气
 * Created by JefferyLeng on 2016/12/22.
 */
public class WeatherProcessor extends BaiduBaseProcessor<BaiduWeatherResultBean> {
    @Override
    protected void process(BaiduWeatherResultBean commonResultBean, SpeechHandleIntermediary speechHandle) {
        String content = commonResultBean.getResult().getSpeech().getContent();
        speechHandle.ttsPlay(content);
        //城市
        String city = commonResultBean.getResult().getResource().getData().getCity();
        //时间
        String time = commonResultBean.getResult().getResource().getData().getTimeX();
        //当前温度
        String current_temp = commonResultBean.getResult().getResource().getData().getCurrent_temp();
        //污染指数
        String pm25 = commonResultBean.getResult().getResource().getData().getPm25();
        //温度变化
        String temp = commonResultBean.getResult().getResource().getData().getTemp();
        //天气情况 比如雨转晴
        String weather = commonResultBean.getResult().getResource().getData().getWeather();
        //风力
        String wind = commonResultBean.getResult().getResource().getData().getWind();
        String icon = "";
        String pm_level = "";
        List<BaiduWeatherResultBean.ResultBean.ResourceBean.DataBean.WeatherInfoBean> weather_info = commonResultBean.getResult().getResource().getData().getWeather_info();
//        if (weather_info != null && weather_info.size() > 0) {
//            int size = weather_info.size();
//            for (int i = 0; i < size; i++) {
//                //匹配json内部时间相同
//                if (time.equals(weather_info.get(i).getTimeX())) {
//                    //icon的url
//                    icon = weather_info.get(i).getIcon();
//                    //pm污染指数，比如轻度污染
//                    pm_level = weather_info.get(i).getPm_level();
//                    if (pm_level == null || pm_level.equals("")) {
//                        pm_level = "";
//                    }
//                }
//            }
//            WeatherPop.getWeatherPop().showWeatherPop(current_temp,weather,temp,icon,wind,pm25,pm_level);
//        } else {
//            speechHandle.ttsPlay("呜呜~小馨没有找到该城市的天气数据哦~ ");
//        }
        AISpeechUtil.playAnim(EMOTION_INTERPRETATION, true);
    }
}
