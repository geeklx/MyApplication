package com.haiersmart.voice.process.engine;

import android.util.Log;

import com.baidu.duersdk.DuerSDKFactory;
import com.baidu.duersdk.tts.TTSInterface;
import com.haiersmart.sfnation.application.FridgeApplication;
import com.haiersmart.sfnation.common.MyLogUtil;
import com.haiersmart.voice.utils.L;

/**
 * TTS: text to speech engine 语音合成播报引擎
 * Created by JefferyLeng on 2017/2/6.
 */
public class TTSEngine implements IEngineStandard {

    private static TTSEngine ourInstance = new TTSEngine();
    private TTSInterface.TTSParam mTtsParam;

    public static TTSEngine getInstance() {
        return ourInstance;
    }

    private TTSEngine() {
    }

    /**
     * 初始化tts engine
     */
    public void initTTSEngine() {
        if (mTtsParam != null) {
            return;
        }
        //设置参数
        mTtsParam = new TTSInterface.TTSParam();
        mTtsParam.setVolume(6);
        mTtsParam.setSpeed(5);
        mTtsParam.setSpeeaker(4);

        String appid = "9208021";
        String appkey = "t9LunrMXTC1eXNcmZ6V5ebW7";
        String secretkey = "0ca377f486a16fa50bb9dbe3a6776068";

        //请在yuyin.baidu.com上申请自己的appid，apikey，scretkey，选中语音合成服务
        mTtsParam.setAppId(appid);
        mTtsParam.setApikey(appkey);
        mTtsParam.setSecretkey(secretkey);
        mTtsParam.setAudioRate(TTSInterface.TTSParam.AUDIO_BITRATE_AMR_6K6);


//        //在 yuyin.baidu.com 上申请的appid
//        ttsParam.setAppId("9150861");
//        ttsParam.setApikey("iv1FKHmdNjcQfQhFphvm7R1s");
//        ttsParam.setSecretKey("540e9bba19d418d2290c40015542d2d7");
        DuerSDKFactory.getDuerSDK().getSpeech().initTTS(FridgeApplication.get(), mTtsParam, new TTSInterface.InitTTSListener() {
            @Override
            public void onInitResult(int errorCode, String errorMessage) {
                Log.d(TAG, "onInitResult: --------> errorCode : " + errorCode + ",errorMessage : " + errorMessage);
            }
        });
        DuerSDKFactory.getDuerSDK().getSpeech().addTTSStateListener(mITTSListener);
        MyLogUtil.d(TAG,"aistatus:  "+"tts initTTSEngine");

    }



    private  boolean isWithAsr;

    public void setWithAsr(boolean withAsr) {
        isWithAsr = withAsr;
    }

    /**
     * 合成播放制定的字符串
     *
     * @param ttsStr
     */
    public void playTTS(String ttsStr,boolean isAsr) {
//        if (!NetWorkUtil.isNetworkConnected(FridgeApplication.get())) {
//            Log.d(TAG, "playTTS: --> ttsEngine 启动失败 reason : network can not be used");
//            return;
//        }
        setWithAsr(isAsr);
        MyLogUtil.d(TAG,"aistatus:  "+"tts playTTS");
        compoundByDuer(ttsStr);
    }


    /**
     * 使用百度度秘合成语音
     */
    public void compoundByDuer(String voiceStr) {
        DuerSDKFactory.getDuerSDK().getSpeech().openTTS();
        DuerSDKFactory.getDuerSDK().getSpeech().play(voiceStr);
    }

    private TTSInterface.ITTSListener mITTSListener = new TTSInterface.ITTSListener() {
        @Override
        public void onSynthesizeStart(String s) {
            Log.d(TAG, "onSynthesizeStart: -----------> 开始合成");
        }

        @Override
        public void onSynthesizeDataArrived(String s, byte[] bytes, int i) {
            Log.d(TAG, "onSynthesizeDataArrived: -------> ");
        }

        @Override
        public void onSynthesizeFinish(String s) {
            Log.d(TAG, "onSynthesizeFinish: -------> 语音合成结束");
            SpeechHandleIntermediary.build().updateEngineWorkStatus(SpeechHandleIntermediary.EngineWorkStatus.TTS_SYNTHESIZEFINISH,isWithAsr);
        }

        @Override
        public void onSpeechStart(String s) {
            Log.d(TAG, "onSpeechStart: --------> 开始语音播报");
            // finish: 2017/2/6 开始语音播报 通知中间人 更新emotion界面
//            EmotionProcessUtil.getEmotionProcessUtil().processEmotion(VoiceEmotionContants.EMOTION_CALM);
            SpeechHandleIntermediary.build().updateEngineWorkStatus(SpeechHandleIntermediary.EngineWorkStatus.TTS_START,isWithAsr);
        }

        @Override
        public void onSpeechProgressChanged(String s, int i) {
        }

        @Override
        public void onSpeechFinish(String s) {
            L.d(TAG, "语音播放结束  ------>  语音播放结束 -----> restart recognition");
            // finish: 2017/2/6 通知中间人语音播报完成  重启语音识别服务员
//            startSpeechRecogintionOnUiThread();
            SpeechHandleIntermediary.build().updateEngineWorkStatus(SpeechHandleIntermediary.EngineWorkStatus.TTS_FINISH,isWithAsr);
        }

        @Override
        public void onError(String s, TTSInterface.TtsError ttsError) {
            Log.d(TAG, "onError: ---------> error : " + s);
            // finish: 2017/2/6 通知中间人语音播报完成  重启语音识别服务员
            SpeechHandleIntermediary.build().updateEngineWorkStatus(SpeechHandleIntermediary.EngineWorkStatus.TTS_ERROR,isWithAsr);
//            startSpeechRecogintionOnUiThread();
        }
    };

    @Override
    public void startWork() {

    }

    /**
     * 当前是否正在进行tts播报
     *
     * @return
     */
    public boolean isSpeaking() {
        return DuerSDKFactory.getDuerSDK().getSpeech().isSpeaking();
    }

    @Override
    public void stopWork() {
        MyLogUtil.d(TAG,"aistatus:  "+"tts stopWork");
        try {//没初始化DuerSDKFactory.getDuerSDK().getSpeech()会crash
            DuerSDKFactory.getDuerSDK().getSpeech().stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
