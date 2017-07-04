package com.haiersmart.voice.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.baidu.duersdk.DuerSDKFactory;
import com.haiersmart.voice.utils.VoiceConstants;

/**
 * 语音相关操作receiver
 * Created by JefferyLeng on 2016/12/24.
 */

public class SpeechOperationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (VoiceConstants.ACTION_BREAK_OFF_CURRENT_TTS_VOICE.equals(action)) {
            //语音break off
            DuerSDKFactory.getDuerSDK().getSpeech().stop();
        } else if (VoiceConstants.ACTION_RESTART_SPEECH_RECOGNITION.equals(action)) {
            //重新激活语音识别机制 一般用于 播放完声音、视频之后 并未通过语音合成的操作
            //SpeechHelper.getInstance().startRecognition(context);
        }
    }
}
