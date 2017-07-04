package com.haiersmart.voice.process.engine;

import android.speech.SpeechRecognizer;
import android.text.TextUtils;
import android.util.Log;

import com.baidu.duersdk.DuerSDKFactory;
import com.baidu.duersdk.utils.NetWorkUtil;
import com.baidu.duersdk.voice.VoiceInterface;
import com.haiersmart.sfnation.application.FridgeApplication;
import com.haiersmart.sfnation.common.AppManager;
import com.haiersmart.sfnation.common.MyLogUtil;
import com.haiersmart.sfnation.util.AISpeechUtil;
import com.haiersmart.voice.utils.L;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import static com.haiersmart.voice.utils.LocationUtil.latitude;
import static com.haiersmart.voice.utils.LocationUtil.longitude;

/**
 * 语音识别工作引擎(百度度秘版本)
 * Created by JefferyLeng on 2017/2/6.
 */
public class ASREngine implements IEngineStandard {

    private static final ASREngine ASR_ENGINE = new ASREngine();

    private int errorRestartTimes = 0;

    /**
     * VOICE_ONLY 仅识别结果
     * VOICE_DUER 返回度秘解析结果
     */
    private VoiceInterface.VOICERESULTMODE voiceResultMode = VoiceInterface.VOICERESULTMODE.VOICE_DUER;

    /**
     * 是否开启后台识别服务
     */
    private boolean mIsRecognitionOpen;

    /**
     * the flag of close recognition
     */
    private final boolean CLOSE_RECOGNITION = false;

    /**
     * the flag of open recognition
     */
    private final boolean OPEN_RECOGNITION = true;

    private VoiceInterface.VoiceParam mVoiceParam;

    private String mRecognitionResult;

    private String mDuerResultStr;

    public String getDuerResultStr() {
        return mDuerResultStr;
    }

    private void setDuerResultStr(String duerResultStr) {
        mDuerResultStr = duerResultStr;
    }

    private ASREngine() {
    }

    public static ASREngine getInstance() {
        return ASR_ENGINE;
    }

    public String getRecognitionResult() {
        return mRecognitionResult;
    }

    private void setRecognitionResult(String recognitionResult) {
        mRecognitionResult = recognitionResult;
    }

//    private NetAccessHelper netAccessHelper = new NetAccessHelper();

    /**
     * 初始化asr工作引擎
     */
    public void initASREngine() {
        if (mVoiceParam == null) {
            mVoiceParam = new VoiceInterface.VoiceParam();
        }
        mVoiceParam.setAsrAppid("9208021");
        mVoiceParam.setAsrAppKey("t9LunrMXTC1eXNcmZ6V5ebW7");
        mVoiceParam.setAsrSecretKey("0ca377f486a16fa50bb9dbe3a6776068");
        mVoiceParam.setVoiceMode(VoiceInterface.VOICEMODE.AUTO_REC);
        //设置位置信息
        JSONObject duerParam = new JSONObject();
        try {

            // 坐标系名称 wgs84为标准经纬度
            duerParam.put("location_system", "wgs84");
//                Log.d(TAG, "initASREngine: -----> 定位信息： latitude : " + LocationUtil.latitude + ", longitude : " + LocationUtil.longitude);
//                double latitude = DataProvider.getLatitude();
//                double longitude = DataProvider.getLongitude();
//            double latitude = DataProvider.getLatitude();
//            double longitude = DataProvider.getLongitude();
            Log.d(TAG, "initASREngine: -----> 定位信息： latitude : " + latitude + ", longitude : " + longitude);
            // 经度；double类型
//                duerParam.put("longitude",LocationUtil.longitude);
            // 纬度；double类型
//                duerParam.put("latitude",LocationUtil.latitude);

            // 经度；double类型
            duerParam.put("longitude", longitude);
            // 纬度；double类型
            duerParam.put("latitude", latitude);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = new JSONArray();
        jsonArray.put("audio");
        jsonArray.put("speech");
        mVoiceParam.setSupportedContentType(jsonArray);
        mVoiceParam.setExtraParam(duerParam.toString());
        //拿到度秘语义解析result
        mVoiceParam.setVoiceResultMode(voiceResultMode);
        mVoiceParam.setAudioInputMode(VoiceInterface.AUDIOINPUTMODE.INPUTMIC);
        mVoiceParam.setKeyworld("");
        Log.d(TAG, "initASREngine: -------> asr engine初始化成功");

    }


    public void setRecognitionStatus(boolean isOpen) {
        mIsRecognitionOpen = isOpen;
    }

    /**
     * @return if current status is open return ture,other return false
     */
    public boolean getRecognitionStatus() {
        return mIsRecognitionOpen;
    }

    @Override
    public void startWork() {
        if (!NetWorkUtil.isNetworkConnected(FridgeApplication.get())) {
//            setRecognitionStatus(CLOSE_RECOGNITION);
//            AISpeechUtil.startPlayTTS("请检查网络连接");
            return;
        }
        setRecognitionStatus(OPEN_RECOGNITION);
        startRecognitionOnUIThread();
        MyLogUtil.d(TAG, "aistatus:  " + "asr startWork");
    }

    /**
     * 确保在ui线程中开启语音识别机制
     */
    private synchronized void startRecognitionOnUIThread() {
        if (!NetWorkUtil.isNetworkConnected(FridgeApplication.get())) {
            Log.d(TAG, "startRecognitionOnUIThread: ---------> 网络未连接 无法重启识别服务");
            return;
        }
        AppManager.getInstance().top().runOnUiThread(mRunnable);
        MyLogUtil.d(TAG, "aistatus:  " + "asr startRecognitionOnUIThread");

    }

    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (mIsRecognitionOpen == OPEN_RECOGNITION) {
//                    startWork();
                Log.d(TAG, "run: ----> ui thread to open recognition..");
                DuerSDKFactory.getDuerSDK().getVoiceRecognize().startRecognition(FridgeApplication.get(), mVoiceParam, listener);
            }
        }
    };

    @Override
    public void stopWork() {
        setRecognitionStatus(CLOSE_RECOGNITION);
        DuerSDKFactory.getDuerSDK().getVoiceRecognize().cancelRecognition(FridgeApplication.get());
//        DuerSDKFactory.getDuerSDK().getVoiceRecognize().recognitionFinish(mContext);

        MyLogUtil.d(TAG, "aistatus:  " + "asr stopWork");

    }

    private boolean isAsrBegin = false;//用看判断ASRBEGIN
    private boolean isFirstAsrError = false;//第一次asr error
    /**
     * 百度度秘 sdk语音识别
     */
    private VoiceInterface.IVoiceEventListener listener = new VoiceInterface.IVoiceEventListener() {
        @Override
        public void onVoiceEvent(final VoiceInterface.VoiceResult voiceResult) {
            if (null != voiceResult) {
                MyLogUtil.v(TAG, "" + voiceResult.getStatus());
                switch (voiceResult.getStatus()) {
                    /** 引擎就绪 **/
                    case READY: {
                        isAsrBegin = false;
                        L.d(TAG, "VoiceInterface onVoiceEvent --------> READY READY READY ----->");
                        //recognitionResultTxt.setText("");
                        // 可以加动画
                        //通知中间人更新asr状态
                        SpeechHandleIntermediary.build().updateEngineWorkStatus(SpeechHandleIntermediary.EngineWorkStatus.ASR_READY);
                    }
                    break;
                    /** 引擎开始 开始识别状态 **/
                    case BEGIN: {
                        isAsrBegin = true;
                        // 可以加动画
                        L.d(TAG, "VoiceInterface onVoiceEvent --------> BEGIN BEGIN BEGIN ----->");
                        //通知中间人更新asr状态
                        SpeechHandleIntermediary.build().updateEngineWorkStatus(SpeechHandleIntermediary.EngineWorkStatus.ASR_BEGIN);
                    }
                    break;
                    /** 上屏状态 **/
                    case PARTIAL: {
                        stopTimeOut();
//                        partialResultTxt.setText("上屏状态 :" + voiceResult.getSpeakText());
                        SpeechHandleIntermediary.build().updateEngineWorkStatus(SpeechHandleIntermediary.EngineWorkStatus.ASR_PARTIAL, voiceResult.getSpeakText());
                        setRecognitionResult(voiceResult.getSpeakText());
                        L.d(TAG, "VoiceInterface onVoiceEvent --------> PARTIAL PARTIAL PARTIAL ----->");

                    }
                    break;
                    /** 上屏语音音量 **/
                    case VOLUME: {
//                        voiceVolumeTxt.setText("语音音量 :" + voiceResult.getVolume());
                    }
                    break;
                    /** 已经检测到语音终点，等待网络返回 **/
                    case REC_END: {
                        // 可以加动画
                        L.d(TAG, "VoiceInterface onVoiceEvent --------> REC_END REC_END REC_END ----->");
//                        AppManager.getAppManager().currentActivity().sendBroadcast(new Intent(VoiceConstants.ACTION_CHANGE_UI_BY_CURRENT_VOICE_STATAUS_START));
                    }
                    break;
                    /** 识别结束 **/
                    case FINISH: {
//                        if (!TextUtils.isEmpty(voiceResult.getDuerResult())) {
//
//                        }
                        //通知ui改变状态
                        // TODO: 2017/2/6 识别结束 通知中间人状态和识别结果
//                        AppManager.getAppManager().currentActivity().sendBroadcast(new Intent(VoiceConstants.ACTION_CHANGE_UI_BY_CURRENT_VOICE_STATAUS_OVER));
//                        if (!TextUtils.isEmpty(voiceResult.getSpeakText())) {
//                            String speakText = voiceResult.getSpeakText();
//                            Log.d(TAG, "onVoiceEvent: --------> duer 识别语音信息 ： " + speakText);
////                            parseByHaizhi(speakText);
//                            // TODO: 2016/12/29 临时Debug使用  后期不需要
//                            Intent intent = new Intent();
//                            intent.putExtra(VoiceConstants.VOICE_REC_EXTAR_KEIY, speakText);
//                            intent.setAction(VoiceConstants.ACTION_GET_VOICE_REC_TEXT);
//                            AppManager.getAppManager().currentActivity().sendBroadcast(intent);
//                            mProcessCenter.process(mContext, speakText);
//                        }

                        String speakText = voiceResult.getSpeakText();
                        //度秘的result
                        String duerResult = voiceResult.getDuerResult();
                        if (!TextUtils.isEmpty(speakText) && !TextUtils.isEmpty(duerResult)) {
                            MyLogUtil.d("jigeyuyinjieguo:  speak: ", "" + speakText);
                            MyLogUtil.d("jigeyuyinjieguo:  duer:  ", "" + duerResult);
                            setRecognitionResult(speakText);
                            setDuerResultStr(duerResult);
                        }

                        SpeechHandleIntermediary.build().updateEngineWorkStatus(SpeechHandleIntermediary.EngineWorkStatus.ASR_SUCCESS, speakText);
                        L.d(TAG, "VoiceInterface onVoiceEvent --------> FINISH FINISH FINISH ----->");

                    }
                    break;
                    /** 识别错误 **/
                    case ERROR: {
                        String mErrMsg = "";
                        String mErrInfo = "";
                        int errorInfo = voiceResult.getErrorCode();
                        Log.d(TAG, "识别错误---errorInfo: ------->" + errorInfo);
                        switch (errorInfo) {
                            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                            case SpeechRecognizer.ERROR_NETWORK:
                                mErrMsg = "网络出问题了";
                                mErrInfo = "请检查网络设置";
                                break;
                            case SpeechRecognizer.ERROR_AUDIO:
                            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                                mErrMsg = "麦克风貌似不可用哦";
                                mErrInfo = "请检查麦克风设置";
                                break;
                            case SpeechRecognizer.ERROR_SERVER:
                            case SpeechRecognizer.ERROR_CLIENT:
                            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                            case SpeechRecognizer.ERROR_NO_MATCH:
                            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                                mErrMsg = "抱歉，我没听清";
                                mErrInfo = "请说话大声些或换一个安静的环境再试试";
                                Log.d(TAG, "识别错误---主标题: ------->" + mErrMsg);
                                Log.d(TAG, "识别错误---附标题: ------->" + mErrInfo);
                                Log.d(TAG, "------->   不进行语音播报 重启识别服务: ------->");
                                break;
                            default:
                                mErrMsg = "好像哪里不对劲";
                                mErrInfo = "建议再试一次";
                                break;
                        }
                        Log.d(TAG, "识别错误---主标题: ------->" + mErrMsg);
                        Log.d(TAG, "识别错误---附标题: ------->" + mErrInfo);
                        //处理错误信息
//                        mProcessCenter.process(mContext,mErsg + mErrInfo);
//                        compoundByDuer(mErrMsg);
                        // finish: 2017/2/6 出现错误重启语音识别服务
//                        startSpeechRecogintionOnUiThread();
//                        errorRestartTimes++;
                        MyLogUtil.d(TAG, "aistatus:  " + "errorRestartTimes  " + errorRestartTimes);


                        if (!isFirstAsrError && isAsrBegin) {
//                        if (isAsrBegin) {
                            isFirstAsrError = true;
                            stopTimeOut();
                            MyLogUtil.d(TAG, "aistatus:  " + "isFirstAsrError");
                            AISpeechUtil.startPlayTTS("我没听清楚能再说一下吗", true);
                        } else {
                            startRecognitionOnUIThread();
                            startTimeOut();
                        }
                        SpeechHandleIntermediary.build().updateEngineWorkStatus(SpeechHandleIntermediary.EngineWorkStatus.ASR_ERROR);

//                        if (errorRestartTimes >= 3) {
//                            errorRestartTimes = 0;
//                            isFirstAsrError = false;
//                            Log.d(TAG, "onVoiceEvent: ----->重启3次 停止重新识别 ");
//                            stopWork();
//                            SpeechHandleIntermediary.build().updateEngineWorkStatus(SpeechHandleIntermediary.EngineWorkStatus.ASR_STOP);
//                            return;
//                        }
                    }
                    break;
                    default: {
                        break;
                    }
                }
            }
        }
    };

    private void asrStop() {
        isFirstAsrError = false;
        timeOutSec = 12;
        Log.d(TAG, "onVoiceEvent: ----->重启3次 停止重新识别 ");
        stopWork();
        SpeechHandleIntermediary.build().updateEngineWorkStatus(SpeechHandleIntermediary.EngineWorkStatus.ASR_STOP);
        stopTimeOut();
    }


    private Timer timer;
    private TimerTask timerTask;
    private long timeOutSec = 12;

    private void startTimeOut() {
        if (timer == null) {
            timer = new Timer();
        }
        if (timerTask == null) {
            MyLogUtil.d(TAG, "aistatus:  " + "startTimeOut");
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    MyLogUtil.d(TAG, "---->timeOutSec  " + timeOutSec);
                    MyLogUtil.d(TAG, "aistatus:  " + "timeOutSec  " + timeOutSec);
                    if (timeOutSec > 0) {
                        timeOutSec--;
                    } else {
                        asrStop();
                    }
                }
            };
        }

        if (timer != null && timeOutSec == 12) {
            MyLogUtil.d(TAG, "aistatus:  " + "schedule");
            timer.schedule(timerTask, 0, 1000);
        }
    }

    private void reStartTimeOut() {
        stopTimeOut();
        startTimeOut();
    }

    private void stopTimeOut() {
        MyLogUtil.d(TAG, "aistatus:  " + "stopTimeOut");
        timeOutSec = 12;
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }

}
