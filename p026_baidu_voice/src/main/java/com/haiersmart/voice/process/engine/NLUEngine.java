package com.haiersmart.voice.process.engine;

/**
 * Created by JefferyLeng on 2017/2/6.
 */
public class NLUEngine implements IEngineStandard {

    private static NLUEngine ourInstance = new NLUEngine();

    public static NLUEngine getInstance() {
        return ourInstance;
    }

    private NLUEngine() {
    }

    @Override
    public void startWork() {

    }

    @Override
    public void stopWork() {

    }
}
