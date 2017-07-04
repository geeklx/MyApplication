package com.haiersmart.voice.process.engine;


import android.app.Application;
import android.util.Log;

import com.baidu.duersdk.DuerSDK;
import com.baidu.duersdk.DuerSDKFactory;
import com.baidu.duersdk.utils.AppLogger;
import com.haiersmart.sfnation.common.MyLogUtil;
import com.haiersmart.sfnation.util.AISpeechUtil;
import com.haiersmart.voice.process.baidu.BaiduDureProcessCenter;
import com.haiersmart.voice.process.local_process_layer.LocalProcessCenter;

/**
 * 语音引擎操作中间人角色
 * Note: 所有定制化功能 导致本来独立的工作引擎之间 产生功能的依赖 均在当前类中处理
 * <p>
 * <p>
 * 可提供中间人串联各独立功能形成标准工作流环境，不确保适应全部场景(使用前提是开启自动工作流
 *
 * @link setAutoWorkFlowStatus(AUTO_WORKFLOW_STAUTS.OPEN))：
 * wakeup --> asr(stop wakeup) --> asr success(start wakeup) --> nlu --> tts --> asr
 * --> asr failure --> restart asr
 * </p>
 * <p>
 * 如不使用标准工作流环境（默认不开启） 可以调用 @link listenWorkFlowStatus() 监听状态
 * </p>
 * Created by JefferyLeng on 2017/2/6.
 */
public class SpeechHandleIntermediary {

    private final String TAG = SpeechHandleIntermediary.class.getSimpleName();

    private static final SpeechHandleIntermediary SPEECH_HANDLE_INTERMEDIARY = new SpeechHandleIntermediary();

    private ASREngine mASREngine = ASREngine.getInstance();

    private NLUEngine mNLUEngine = NLUEngine.getInstance();

    private TTSEngine mTTSEngine = TTSEngine.getInstance();

    private WakeUpEngine mWakeUpEngine = WakeUpEngine.getInstance();

    private BaiduDureProcessCenter mProcessCenter = BaiduDureProcessCenter.getInstance();

    public static SpeechHandleIntermediary build() {
        return SPEECH_HANDLE_INTERMEDIARY;
    }

    private SpeechHandleIntermediary() {
    }

    public enum EngineWorkStatus {
        ASR_READY,
        ASR_SUCCESS,
        ASR_BEGIN,
        ASR_ERROR,
        ASR_STOP,
        ASR_REC_END,
        ASR_PARTIAL,
        WAKEUP_SUCCESS,
        WAKEUP_FAILURE,
        TTS_FINISH,
        TTS_SYNTHESIZEFINISH,
        TTS_START,
        TTS_ERROR,
        NLU_FINISH,

    }

    public interface IWakeUpStatus {
        void wakeUpSucess();

        void wakeUpFailure();
    }

    public interface IGetWorkFlowStatus {

        void wakeUpSuccess(boolean istts);

        void wakeUpFailure();

        void asrStart();

        void asrFinish(String speakText);

        void asrBegin();

        void asrPartial(String speakText);

        void asrError();

        void asrStop();

        void ttsSynthesizefinish();

        void ttsStart();

        void ttsFinish();

        void ttsError();

        void asrRecEnd();
    }

    private IGetWorkFlowStatus mIGetWorkFlowStatus;

    private IWakeUpStatus mIWakeUpStatus;

    /**
     * 自动标准工作流 默认关闭
     */
    private AUTO_WORKFLOW_STAUTS mDefaultWorkFlowStauts = AUTO_WORKFLOW_STAUTS.OPEN;

    public enum AUTO_WORKFLOW_STAUTS {
        OPEN,
        CLOSE
    }

    /**
     * 初始化语音工作流
     */
    public void initSDK(Application context) {
        Log.d(TAG, "initSDK: -------> 初始化sdk");
//        initOKHttpUtils();
        initDuerSDK(context);

    }

    private void initEngine() {
        mASREngine.initASREngine();
        mTTSEngine.initTTSEngine();
    }

    public void setAutoWorkFlowStatus(AUTO_WORKFLOW_STAUTS autoWorkFlowStatus) {
        mDefaultWorkFlowStauts = autoWorkFlowStatus;
    }

    private boolean isOpenAutoWorkFlow() {
        if (mDefaultWorkFlowStauts == AUTO_WORKFLOW_STAUTS.OPEN)
            return true;
        else
            return false;
    }

    public void listenWorkFlowStatus(IGetWorkFlowStatus iGetWorkFlowStatus) {
        if (iGetWorkFlowStatus != null) {
            this.mIGetWorkFlowStatus = iGetWorkFlowStatus;
        }
    }

    /**
     * 开启语音唤醒服务
     *
     * @param iWakeUpStatus 唤醒状态callback 传递null callback not be invoke
     */
    public void startWakeUp(IWakeUpStatus iWakeUpStatus) {
        if (iWakeUpStatus != null) {
            this.mIWakeUpStatus = iWakeUpStatus;
        }
        initEngine();
        mWakeUpEngine.startWork();
    }

    private void asrStop() {
        if (mIGetWorkFlowStatus != null) {
            mIGetWorkFlowStatus.asrStop();
        }
//        ttsPlay("我好像不太明白,有事再叫我哦", false); //不提示
        startWakeUp();
    }

    //关闭asr tts 开启wakeup
    public void toWaitWakeup() {
        if (mIGetWorkFlowStatus != null) {
            mIGetWorkFlowStatus.asrStop();
        }
        mASREngine.stopWork();
        if (mTTSEngine.isSpeaking()) {
            mTTSEngine.stopWork();
        }
        startWakeUp();
    }


    public void startWakeUp() {
        if (!mWakeUpEngine.isWork()) {
            initEngine();
            mWakeUpEngine.startWork();
        }
    }

    /**
     * 首次开启 礼貌欢迎工作流
     */
    public void welcomeWorkFlow() {
        Log.d(TAG, "welcomeWorkFlow: -----> 首次礼貌欢迎 工作流工作");
        initEngine();
        mWakeUpEngine.stopWork();
        mASREngine.startWork();
    }

    /**
     * 解决唤醒占用mic与其他使用mic功能的冲突
     */
    public void resolveConflictByMic() {
        stopWorkFlow();
    }

    /**
     * 标准工作流
     */
    public void startWorkFlow() {
        initEngine();
        mWakeUpEngine.startWork();
        Log.d(TAG, "startWorkFlow: --------> 语音引擎开启工作流");
    }

    /**
     * 关闭除了识别引擎之外的工作流环境
     */
    public void stopWorkFlowWithoutWakeUp() {
        mASREngine.stopWork();
        mNLUEngine.stopWork();
        mTTSEngine.stopWork();
    }

    /**
     * 彻底关闭工作流
     */
    public void stopWorkFlow() {
        mASREngine.stopWork();
        mNLUEngine.stopWork();
        mTTSEngine.stopWork();
        mWakeUpEngine.stopWork();
    }

    /**
     * 停止唤醒工作
     */
    public void stopWakeup() {
        if (mWakeUpEngine.isWork()) {
            mWakeUpEngine.stopWork();
        }
    }


    /**
     * 更新引擎工作状态
     *
     * @param engineWorkStatus
     */
    public void updateEngineWorkStatus(EngineWorkStatus engineWorkStatus) {
        switch (engineWorkStatus) {
            case ASR_READY:
                MyLogUtil.d(TAG, "aistatus:  " + "ASR_READY");
                asrReady();
                break;
            case ASR_BEGIN:
                MyLogUtil.d(TAG, "aistatus:  " + "ASR_BEGIN");
                Log.d(TAG, "updateEngineWorkStatus: -------> asr开始识别状态");
                if (mIGetWorkFlowStatus != null) {
                    mIGetWorkFlowStatus.asrBegin();
                }
                break;
            case ASR_STOP:
                MyLogUtil.d(TAG, "aistatus:  " + "ASR_STOP");
                asrStop();//重启wakeup
                break;
            case ASR_ERROR:
                MyLogUtil.d(TAG, "aistatus:  " + "ASR_ERROR");
                if (mIGetWorkFlowStatus != null) {
                    mIGetWorkFlowStatus.asrError();
                }
                break;
            case WAKEUP_SUCCESS:
                MyLogUtil.d(TAG, "aistatus:  " + "WAKEUP_SUCCESS");
                Log.d(TAG, "updateEngineWorkStatus: -------> 唤醒成功");
                wakeupSuccess(true);
                break;
            case WAKEUP_FAILURE:
                MyLogUtil.d(TAG, "aistatus:  " + "WAKEUP_FAILURE");
                break;
        }
    }


    public void updateEngineWorkStatus(EngineWorkStatus engineWorkStatus, boolean isWithAsr) {
        switch (engineWorkStatus) {
            case TTS_ERROR:
            case TTS_FINISH:
                MyLogUtil.d(TAG, "aistatus:  " + "TTS_FINISH");
                if (isWithAsr) {
                    restartRecognition();//tts结束开启ars
                }
                if (mIGetWorkFlowStatus != null) {
                    Log.d(TAG, "updateEngineWorkStatus: ------> 非自动工作流 tts完成");
                    mIGetWorkFlowStatus.ttsFinish();
                }
                break;
            case TTS_SYNTHESIZEFINISH:
                MyLogUtil.d(TAG, "aistatus:  " + "TTS_SYNTHESIZEFINISH");
                if (mIGetWorkFlowStatus != null) {
                    mIGetWorkFlowStatus.ttsSynthesizefinish();
                }
                break;
            case TTS_START:
                MyLogUtil.d(TAG, "aistatus:  " + "TTS_START");
                Log.d(TAG, "updateEngineWorkStatus: ------> 开始播报 更新界面emotion");
                if (isWithAsr) {
                    stopAsr();//tts开始关闭识别
                }
                if (mIGetWorkFlowStatus != null) {
                    mIGetWorkFlowStatus.ttsStart();
                }
                break;
        }
    }


    public void updateEngineWorkStatus(EngineWorkStatus engineWorkStatus, String speakText) {
        switch (engineWorkStatus) {
            case ASR_SUCCESS:
                MyLogUtil.d(TAG, "aistatus:  " + "ASR_SUCCESS");
                asrSuccess(speakText);
                break;
            case ASR_PARTIAL:
                MyLogUtil.d(TAG, "aistatus:  " + "ASR_PARTIAL");
                if (mIGetWorkFlowStatus != null) {
                    mIGetWorkFlowStatus.asrPartial(speakText);
                }
                break;
            case ASR_REC_END:
                MyLogUtil.d(TAG, "aistatus:  " + "ASR_REC_END");
                if (mIGetWorkFlowStatus != null) {
                    mIGetWorkFlowStatus.asrRecEnd();
                }
                break;
            default:
                break;
        }
    }

    /**
     * asr engine就绪
     */
    private void asrReady() {
        stopWakeup();//asr与wake不共存
        if (mIGetWorkFlowStatus != null) {
            mIGetWorkFlowStatus.asrStart();

        }
//        Intent intent = new Intent();
//        intent.putExtra(VoiceConstants.VOICE_REC_EXTAR_KEIY, "");
//        intent.setAction(VoiceConstants.ACTION_GET_VOICE_REC_TEXT);
//        AppManager.getAppManager().currentActivity().sendBroadcast(intent);
    }

    /**
     * 停止识别
     */
    public void stopAsr() {
//        if (mASREngine.getRecognitionStatus()) {
        mASREngine.stopWork();
//        }
//        if (mTTSEngine.isSpeaking()) {
//            mTTSEngine.stopWork();
//        }
    }


    /**
     * 开启识别
     */
    public void startAsr() {
        mASREngine.startWork();
    }


    /**
     * 重启识别服务
     */
    private void restartRecognition() {
        Log.d(TAG, "restartRecognition: tts finish/error 重启asr引擎。。。");
//        if (mASREngine.getRecognitionStatus()) {
        mASREngine.startWork();
//        }
    }

    private void asrSuccess(String speakText) {

        if (mIGetWorkFlowStatus != null) {
            mIGetWorkFlowStatus.asrFinish(speakText);
        }
//        if (mDefaultWorkFlowStauts == AUTO_WORKFLOW_STAUTS.OPEN) {
//            mWakeUpEngine.startWork();
//        }
//        mWakeUpEngine.startWork();

        String recognitionResult = mASREngine.getRecognitionResult();

        // 本地处理语音关闭
        if (LocalProcessCenter.isExit(recognitionResult)) {
            AISpeechUtil.breakVoice();
            return;
        }

        // 要求本地搞
        if ("随便听听".equals(recognitionResult)) {
//            NetsSearchUtil.playMusicByKeyword("null");
            return;
        }

        if (LocalProcessCenter.isWakeVoice(recognitionResult)) {
            AISpeechUtil.startPlayTTS("您好呀");
            return;
        }

        //TODO 本地处理
        if (LocalProcessCenter.matchLocalResource(recognitionResult)) {
            return;
        }
        //处理asr result
        Log.d(TAG, "asrSuccess: ----->asr  result : " + mASREngine.getDuerResultStr());
        mProcessCenter.process(mASREngine.getDuerResultStr());

    }

    public String getASRResult() {
        return mASREngine.getRecognitionResult();
    }

    public String getDuerResult() {
        return mASREngine.getDuerResultStr();
    }

    public void breakVoice() {
        if (mTTSEngine.isSpeaking()) {
            mTTSEngine.stopWork();
        }
    }

    public void reWakeUp(boolean istts) {
        wakeupSuccess(istts);
    }

    /**
     * 唤醒成功之后 执行的工作流
     */
    private void wakeupSuccess(boolean istts) {
        /*
        1，判断tts是否正在播放 实现打断功能
        2，open asr engine(open auto workflow)
         */
        Log.d(TAG, "wakeupSuccess: -----> 唤醒成功！");
        if (mDefaultWorkFlowStauts == AUTO_WORKFLOW_STAUTS.OPEN) {
//            mWakeUpEngine.stopWork();
            stopWakeup();
        }

        if (mTTSEngine.isSpeaking()) {
            mTTSEngine.stopWork();
//            mTTSEngine.playTTS("");
            Log.d(TAG, "wakeupSuccess: -----> 执行语音打断");
        }

        if (mIWakeUpStatus != null) {
            mIWakeUpStatus.wakeUpSucess();
        }

        if (mIGetWorkFlowStatus != null) {
            mIGetWorkFlowStatus.wakeUpSuccess(istts);
        }

        if (!istts) {
            mASREngine.startWork();//用tts带起识别
        }

    }


    /**
     * @param ttsStr
     * @param isWithAsr true为tts开始关闭识别，tts结束开启识别；为false则不管
     */
    public void ttsPlay(String ttsStr, boolean isWithAsr) {
        mTTSEngine.playTTS(ttsStr, isWithAsr);
    }

    public void ttsPlay(String ttsStr) {
        ttsPlay(ttsStr, true);
    }

//    private void initOKHttpUtils() {
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("VoiceNetTag"))
//                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
//                .readTimeout(10000L, TimeUnit.MILLISECONDS)
//                //其他配置
//                .build();
//
//        OkHttpUtils.initClient(okHttpClient);
//
//    }

    /**
     * 初始化度秘的sdk
     */
    private void initDuerSDK(Application context) {
        //初始化duersdk
        DuerSDK duerSDK = DuerSDKFactory.getDuerSDK();

        // release版
        /*appid:dmB2756AFDF2954223
        appkey:8FE179415DBF4A79A74EB7ABFCA77AD5*/
        String appid = "dmB2756AFDF2954223";
        String appkey = "8FE179415DBF4A79A74EB7ABFCA77AD5";

        // TODO: 2017/1/4 开发阶段打印log 使用
        //初始化SDK
        duerSDK.initSDK(context, appid, appkey);

        AppLogger.setDEBUG(true);
    }


}
