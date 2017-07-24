package com.haiersmart.sfnation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.haiersmart.sfnation.common.AppManager;
import com.haiersmart.sfnation.util.AISpeechUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        setContentView(R.layout.activity_main);
        AppManager.getInstance().add(MainActivity.this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        initVoice();
    }

    private void initVoice() {
        if (AISpeechUtil.iSVoiceOpen()) {
            AISpeechUtil.startSpeechWork();
        } else {
            AISpeechUtil.stopSpeechWork();
        }
//        getWindow().getDecorView().post(new Runnable() {
//            @Override
//            public void run() {
//                onBackPressed();
//            }
//        });
    }

}
