package com.haiersmart.voice.ui.popupwindow;//package com.haiersmart.voice.ui.popupwindow;
//
///**
// * pop window 管理器
// * Created by JefferyLeng on 2017/2/18.
// */
//public class PopManager {
//
//    private static PopManager sPopManager = new PopManager();
//
//    private PopManager() {}
//
//    public static PopManager build() {
//        return sPopManager;
//    }
//
//    /**
//     * 清洁界面  dismiss all popwindows
//     */
//    public void clearPage() {
//        BaikePop.getBaikePop().dismissBaikePop();
//        WeatherPop.getWeatherPop().dismissWeatherPop();
//        MusicPop.getMusicPop().dissMissMusicPop();
//        ImagePop.getImagePop().dismissImagePop();
//        AudioPop.getAudioPop().dismiss();
//    }
//    public void clearAllandPauseAudioPop(){
//        BaikePop.getBaikePop().dismissBaikePop();
//        WeatherPop.getWeatherPop().dismissWeatherPop();
//        MusicPop.getMusicPop().dissMissMusicPop();
//        ImagePop.getImagePop().dismissImagePop();
//        AudioPop.getAudioPop().pauseAudioPopWindow();
//    }
//
//    public void clearBaikePop() {
//        BaikePop.getBaikePop().dismissBaikePop();
//    }
//
//    public void clearWeatherPop() {
//        WeatherPop.getWeatherPop().dismissWeatherPop();
//    }
//
//    public void clearMusicPop() {
//        MusicPop.getMusicPop().dissMissMusicPop();
//    }
//    public void clearImagePop(){
//        ImagePop.getImagePop().dismissImagePop();
//    }
//    public void clearAudioPop(){
//        AudioPop.getAudioPop().dismiss();
//    }
//
//
//}
