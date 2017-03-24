package com.example.p020_timer.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.p020_timer.R;
import com.example.p020_timer.application.DemoApplication;
import com.example.p020_timer.util.ConstantUtil;
import com.example.p020_timer.util.SpUtils;

import java.io.IOException;

/**
 * Created by Pengliang on 2016/8/30.
 */
public class TimerFinishActivity extends Activity implements MediaPlayer.OnPreparedListener {
    private Button bt_ok;
    private RelativeLayout rl_timer_finish;
    private MediaPlayer mediaPlayer;
    private PowerManager.WakeLock wakeLock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_finish);
        this.setFinishOnTouchOutside(false);
        bt_ok = (Button) findViewById(R.id.bt_ok);
        rl_timer_finish = (RelativeLayout) findViewById(R.id.rl_timer_finish);
        //此处发广播来判断当前是否有占用MediaPlayer的应用 如果有就暂停ta 等待倒计时铃声结束 继续bufen
        sendBroadcast(new Intent(ConstantUtil.BROADCAST_ACTION_ALARM_START));
        //另外的地方接收广播的方法bufen
//        haveMediaPlayer();

        PowerManager pm = (PowerManager) getSystemService(this.POWER_SERVICE);
        wakeLock = pm.newWakeLock(
                PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "MyWakeLock");
        wakeLock.acquire();
        wakeLock.release();

        playMusic((int) SpUtils.getInstance(DemoApplication.get()).get(ConstantUtil.CURRENTALARMSOUND, 1));

        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                }
                sendBroadcast(new Intent(ConstantUtil.BROADCAST_ACTION_ALARM_STOP));
                TimerFinishActivity.this.finish();
            }
        });
    }

    private void haveMediaPlayer() {
        IntentFilter intentFiltervolInput = new IntentFilter();
        intentFiltervolInput.addAction(ConstantUtil.BROADCAST_ACTION_ALARM_START);
        intentFiltervolInput.addAction(ConstantUtil.BROADCAST_ACTION_ALARM_STOP);
        registerReceiver(alarmReceive, intentFiltervolInput);
    }

    private boolean alarmPlay = false;
    private BroadcastReceiver alarmReceive = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(ConstantUtil.BROADCAST_ACTION_ALARM_START)) {//闹钟开启
//                if (MusicController.isPlaying()){
//                    MusicController.pause();
//                    alarmPlay = true;
//                }

            } else if (intent.getAction().equals(ConstantUtil.BROADCAST_ACTION_ALARM_STOP)) {//闹钟结束
//                if (alarmPlay){
//                    MusicController.play();
//                    alarmPlay = false;
//                }
            }
        }
    };

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
//            MobEventHelper.onEvent(this, "effective_click");
        }

        return super.dispatchTouchEvent(ev);
    }

    private void playMusic(int video) {
        switch (video) {
            case 1:
                mediaPlayer = MediaPlayer.create(this, R.raw.alarm1);
                break;
            case 2:
                mediaPlayer = MediaPlayer.create(this, R.raw.alarm2);
                break;
            case 3:
                mediaPlayer = MediaPlayer.create(this, R.raw.alarm3);
                break;
        }
        mediaPlayer.setOnPreparedListener(this);
        try {
            mediaPlayer.prepare();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // prepare async to not block main thread
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mediaPlayer.start();
    }
}

