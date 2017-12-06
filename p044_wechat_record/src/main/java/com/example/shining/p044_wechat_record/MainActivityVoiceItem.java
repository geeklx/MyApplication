package com.example.shining.p044_wechat_record;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.shining.p044_wechat_record.audiomanagerset.manager.MediaManager;
import com.example.shining.p044_wechat_record.audiomanagerset.view.VoiceWiget;

/**
 * Created by shining on 2017/11/16.
 */

public class MainActivityVoiceItem extends AppCompatActivity {

    private VoiceWiget fl1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_voice_item);
        fl1 = findViewById(R.id.fl1);

        fl1.setup("/storage/emulated/0/geek_recorder_audios/c3c0912b-2f28-48c1-bc97-f9bc5abd103e.amr");
    }

    @Override
    protected void onPause() {
        super.onPause();
        MediaManager.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MediaManager.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MediaManager.release();
    }
}
