package com.haiersmart.voice.process.baidu;

import com.haiersmart.voice.utils.VoiceConstants;

/**
 * 处理器工厂
 * Created by JefferyLeng on 2016/12/22.
 */
public class BaiduProcessorFactory {

    private String TAG = getClass().getSimpleName();

    private static final BaiduProcessorFactory mProcessFactory = new BaiduProcessorFactory();

    public static BaiduProcessorFactory getInstance() {
        return mProcessFactory;
    }

    /**
     * 构建处理器
     */
    public BaiduBaseProcessor createProcessor(String domain, String intent) {
        if (VoiceConstants.BAIDU_DOMAIN_TYPE_MUSIC.equals(domain)) {
//            BaikePop.getBaikePop().dismissBaikePop();
//            ImagePop.getImagePop().dismissImagePop();
//            WeatherPop.getWeatherPop().dismissWeatherPop();
            return new MusicProcess();
        } else if (VoiceConstants.BAIDU_DOMAIN_TYPE_WEATHER.equals(domain)) {
//            AudioPop.getAudioPop().dismiss();
//            BaikePop.getBaikePop().dismissBaikePop();
//            ImagePop.getImagePop().dismissImagePop();
            return new WeatherProcessor();
        } else if (VoiceConstants.BAIDU_DOMAIN_TYPE_DEFAULT.equals(domain)) {
            return new OtherProcessor();
        } else if (VoiceConstants.BAIDU_DOMAIN_100.equals(domain)) {
            return new OtherProcessor();
        } else if (VoiceConstants.BAIDU_DOMAIN_TYPE_COOKBOOK.equals(domain)) {
//            BaikePop.getBaikePop().dismissBaikePop();
//            AudioPop.getAudioPop().dismiss();
//            ImagePop.getImagePop().dismissImagePop();
//            WeatherPop.getWeatherPop().dismissWeatherPop();
            return new CookBookProcessor();
        } else if (VoiceConstants.BAIDU_DOMAIN_TYPE_FRIDGE_CONTROL.equals(domain)) {
//            BaikePop.getBaikePop().dismissBaikePop();
//            AudioPop.getAudioPop().dismiss();
//            ImagePop.getImagePop().dismissImagePop();
//            WeatherPop.getWeatherPop().dismissWeatherPop();
            return new BaiduFridgeProcessor();
        } else if (VoiceConstants.BAIDU_DOMAIN_TYPE_AUDIO_UNICAST_CONTROL.equals(domain)) {
            return new BaiduAudioProcessor();
        } else if (VoiceConstants.BAIDU_DOMAIN_TYPE_HARDWARE_CONTROL.equals(domain)) {
//            BaikePop.getBaikePop().dismissBaikePop();
//            ImagePop.getImagePop().dismissImagePop();
//            WeatherPop.getWeatherPop().dismissWeatherPop();
            return new ControlProcessor();
        } else if (VoiceConstants.BAIDU_DOMAIN_TYPE_TRAIN_TICKET.equals(domain)
                || VoiceConstants.BAIDU_DOMAIN_TYPE_QICHE_TICKET.equals(domain)
                || VoiceConstants.BAIDU_DOMAIN_TYPE_FEIJI_TICKET.equals(domain)) {
//            BaikePop.getBaikePop().dismissBaikePop();
//            AudioPop.getAudioPop().dismiss();
//            ImagePop.getImagePop().dismissImagePop();
//            WeatherPop.getWeatherPop().dismissWeatherPop();
            return new TrainProcessor();
        } else if (VoiceConstants.BAIDU_DOMAIN_TYPE_MOVIE.equals(domain)) {
//            BaikePop.getBaikePop().dismissBaikePop();
//            AudioPop.getAudioPop().dismiss();
//            ImagePop.getImagePop().dismissImagePop();
//            WeatherPop.getWeatherPop().dismissWeatherPop();
            return new BaiduMovieProcessor();
        } else if (VoiceConstants.BAIDU_DOMAIN_TYPE_MESSAGEBOARD.equals(domain)) {
//            BaikePop.getBaikePop().dismissBaikePop();
//            AudioPop.getAudioPop().dismiss();
//            ImagePop.getImagePop().dismissImagePop();
//            WeatherPop.getWeatherPop().dismissWeatherPop();
            return new MessageBoardProcessor();
        } else if (VoiceConstants.BAIDU_DOMAIN_TYPE_TIMER.equals(domain)) {
//            BaikePop.getBaikePop().dismissBaikePop();
//            AudioPop.getAudioPop().dismiss();
//            ImagePop.getImagePop().dismissImagePop();
//            WeatherPop.getWeatherPop().dismissWeatherPop();
            return new TimerProcessor();
        } else if (VoiceConstants.BAIDU_DOMAIN_TYPE_YOUKU_VIDEO.equals(domain)) {
//            BaikePop.getBaikePop().dismissBaikePop();
//            AudioPop.getAudioPop().dismiss();
//            ImagePop.getImagePop().dismissImagePop();
//            WeatherPop.getWeatherPop().dismissWeatherPop();
            return new BaiduYoukuVideoProcessor();
        } else {
            return new OtherProcessor();
        }
    }
}
