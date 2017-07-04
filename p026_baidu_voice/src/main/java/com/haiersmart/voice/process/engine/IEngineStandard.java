package com.haiersmart.voice.process.engine;

/**
 * 引擎执行工作接口规范
 * Created by JefferyLeng on 2017/2/6.
 */
public interface IEngineStandard {

    String TAG = "SpeechEngine";

    void startWork();

    void stopWork();
}
