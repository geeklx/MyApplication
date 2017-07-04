package com.haiersmart.sfnation.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.haiersmart.sfnation.util.AISpeechUtil;

/**
 * Created by shining on 2017/7/4 0004.
 */

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction()) || "com.haier.boot".equals(intent.getAction())) {
            initVoice();
        }
    }

    private void initVoice() {
        if (AISpeechUtil.iSVoiceOpen()) {
            AISpeechUtil.startSpeechWork();
        } else {
            AISpeechUtil.stopSpeechWork();
        }
    }
}
