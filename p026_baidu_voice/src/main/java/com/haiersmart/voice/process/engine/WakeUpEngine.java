package com.haiersmart.voice.process.engine;

import android.text.TextUtils;
import android.util.Log;

import com.baidu.duersdk.DuerSDKFactory;
import com.baidu.duersdk.voice.VoiceInterface;
import com.haiersmart.sfnation.application.FridgeApplication;
import com.haiersmart.sfnation.common.MyLogUtil;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 语音唤醒工作引擎
 * Created by JefferyLeng on 2017/2/6.
 */
public class WakeUpEngine implements IEngineStandard {

    private static WakeUpEngine ourInstance = new WakeUpEngine();

    public static WakeUpEngine getInstance() {
        return ourInstance;
    }

    boolean isRecording = false;
//    public static RecordThread mRecordingThread = null;

    private WakeUpEngine() {
    }

    private  boolean isWork;

    public boolean isWork() {
        return isWork;
    }

    public void setWork(boolean work) {
        isWork = work;
    }

    @Override
    public void startWork() {
        setWork(true);
        Log.d(TAG, "startWakeUp: --------> wake up startWork be invoke");
//        if (!NetWorkUtil.isNetworkConnected(FridgeApplication.get())) {
//            Log.d(TAG, "startWork: ---> 网络未连接 唤醒失败");
//            return;
//        }
        ;
        //start wakeup
        /*VoiceInterface.VoiceParam voiceParam = new VoiceInterface.VoiceParam();
        voiceParam.setWakemode(VoiceInterface.WAKEMODE.WAKEMIC);
        DuerSDKFactory.getDuerSDK().getVoiceRecognize().startWakeUp(mContext, voiceParam);*/


        VoiceInterface.VoiceParam voiceParam = new VoiceInterface.VoiceParam();
        voiceParam.setAsrAppid("9208021");
        voiceParam.setAsrAppKey("t9LunrMXTC1eXNcmZ6V5ebW7");
        voiceParam.setAsrSecretKey("0ca377f486a16fa50bb9dbe3a6776068");
        voiceParam.setWakemode(VoiceInterface.WAKEMODE.WAKEMIC);
        JSONArray wakeWords = new JSONArray();
//        wakeWords.put("小度小度").put("你好小度").put("你好小馨").put("小馨小馨");
        wakeWords.put("小度小度").put("你好小馨").put("大熊大熊");
        voiceParam.setWakeupWord(wakeWords);

        DuerSDKFactory.getDuerSDK().getVoiceRecognize().registerWpEventManagerListener(FridgeApplication.get(), mIWakeUpEventListener);
        DuerSDKFactory.getDuerSDK().getVoiceRecognize().startWakeUp(FridgeApplication.get(), voiceParam);
//        startRecording();
        MyLogUtil.d(TAG,"aistatus:  "+"wakeup startWork");
    }

    @Override
    public void stopWork() {
        setWork(false);
        /*boolean isStopSuccess = DuerSDKFactory.getDuerSDK().getVoiceRecognize().stopWakeUp();
        boolean isUnRegisterSuccess = DuerSDKFactory.getDuerSDK().getVoiceRecognize().unRegisterWpEventManagerListener(mIWakeUpEventListener);

        if (isStopSuccess && isUnRegisterSuccess)
            L.d(TAG, "stopWakeUp  -----> 停止唤醒成功");
        else
            L.d(TAG, "stopWakeUp  -----> 停止唤醒失败");*/

        //打断当前的识别流程
//        stopRecording();
        VoiceInterface.VoiceParam voiceParam = new VoiceInterface.VoiceParam();
        voiceParam.setVoiceMode(VoiceInterface.VOICEMODE.AUTO_REC);
        voiceParam.setKeyworld("");
        DuerSDKFactory.getDuerSDK().getVoiceRecognize().stopWakeUp();
        MyLogUtil.d(TAG,"aistatus:  "+"wakeup stopwork");

    }

    VoiceInterface.IWakeUpEventListener mIWakeUpEventListener = new VoiceInterface.IWakeUpEventListener() {
        @Override
        public void onEvent(String name, String params, byte[] data, int offset, int length) {
            try {
                MyLogUtil.i(TAG, "唤醒返回数据为：name=" + name + " params=" + params + " data.size=" + (data != null ? data.length : "null") + " offset=" + offset + " length=" + length);
                //每次唤醒成功，将会回调name=wp.data的时间，被激活的唤醒词在params的word字段
                if ("wp.data".equals(name)) {
                    if (!TextUtils.isEmpty(params)) {
                        JSONObject jsonObject = new JSONObject(params);
                        //根据返回的错误码判断是否有正确结果
                        //拿到唤醒词
                        String word = jsonObject.getString("word");
                        MyLogUtil.i(TAG, "results:" + word.toString() + " " + System.currentTimeMillis());
//                            Toast.makeText(mContext, "语音唤醒：" + word.toString(), Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "onEvent: ----> 语音唤醒 ：" + word.toString());
                        // finish: 2017/1/15 通知中间人唤醒成功 如果当前正在播报 停止tts播报
//                            changeTTSStatus();
                        // finish: 2017/1/15 语音唤醒成功 通知调用者当前唤醒状态
//                        iWakeUpStatusInterface.onReceiveWakeUpStatus(SpeechHelper.WakeUpStatus.SUCCESS);
                        SpeechHandleIntermediary.build().updateEngineWorkStatus(SpeechHandleIntermediary.EngineWorkStatus.WAKEUP_SUCCESS);

                    } else {
                        MyLogUtil.i(TAG, "results: params = null");
//                        iWakeUpStatusInterface.onReceiveWakeUpStatus(SpeechHelper.WakeUpStatus.FAILURE);
                        // finish: 2017/2/6 通知中间人唤醒失败
//                        SpeechHandleIntermediary.build().updateEngineWorkStatus(SpeechHandleIntermediary.EngineWorkStatus.WAKEUP_FAILURE);
                    }
                } else if ("wp.exit".equals(name)) {
                    //唤醒已经停止
                    MyLogUtil.i(TAG, "唤醒停止");
                }
            } catch (Exception e) {
                e.printStackTrace();
                //唤醒已经停止
                MyLogUtil.i(TAG, "语音唤醒出错");
                // finish: 2017/2/6 通知中间人唤醒失败
//                iWakeUpStatusInterface.onReceiveWakeUpStatus(SpeechHelper.WakeUpStatus.FAILURE);
                SpeechHandleIntermediary.build().updateEngineWorkStatus(SpeechHandleIntermediary.EngineWorkStatus.WAKEUP_FAILURE);
            }
        }
    };


    /*****     new sdk implmention       *****/

    /**
     * 开始录音
     */
//    private void startRecording() {
//        if (mRecordingThread == null) {
//            Log.i(TAG, "new a thread");
//            mRecordingThread = new RecordThread();
//        }
//        Log.i(TAG, "start a thread");
//        mRecordingThread.start();
//        isRecording = true;
//    }
//
//    /**
//     * 停止录音
//     */
//    private void stopRecording() {
//        if (mRecordingThread != null) {
//            mRecordingThread.pause();
//            mRecordingThread = null;
//        }
//        isRecording = false;
//    }

    /**
     * 收音线程
     */
//    public class RecordThread extends Thread {
//        private boolean isRun = false;
//
//        private AudioRecord audioRecord;
//        private int bufferSize;
//        private int SAMPLE_RATE_HZ = 16000;
//        private final Object mLock = new Object();
//
//        public RecordThread() {
//            super();
//            Log.i(TAG, "thread is initializing");
//            bufferSize = AudioRecord.getMinBufferSize(SAMPLE_RATE_HZ, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
//            audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, SAMPLE_RATE_HZ, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT, bufferSize);
//        }
//
//        public void run() {
//            Log.i(TAG, "thread run");
//            super.run();
//            isRun = true;
//            try {
//                if (audioRecord.getState() == AudioRecord.STATE_INITIALIZED) {
//                    audioRecord.startRecording();
//                    //用于读取的buffer
//                    byte[] buffer = new byte[bufferSize];
//
//                    while (isRun) {
//                        int lengyh = audioRecord.read(buffer, 0, bufferSize);
//                        Log.i(TAG, "microphone write buffer:" + lengyh);
//                        try {
//                            boolean isSucess = DuerSDKFactory.getDuerSDK().getVoiceRecognize().writeWakeByte(buffer,lengyh,16000);
//                            Log.i(TAG, "microphone write sucess:" + isSucess);
//                        }catch (Exception error){
//                            Log.i(TAG, "microphone write buffer: error");
//                        }
//                    }
//
//                    audioRecord.stop();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//
//        public void pause() {
//            isRun = false;
//
//            Log.i(TAG, "thread pause");
//        }
//
//        public void start() {
//            if (!isRecording) {
//                super.start();
//            }
//        }
//    }

}
