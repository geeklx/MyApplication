package com.example.p020_timer.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.p020_timer.activity.TimerFinishActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Pengliang on 2016/8/29.
 */
public class TimerDemoService extends Service {

    private static final String TAG = TimerDemoService.class.getSimpleName();
    private long count;
    private Timer timer;
    private TimerTask timerTask;
    private boolean isStart, isPause, isGoon;
    private String voice;
    private int tabPosition;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MsgBinder();
    }

    public class MsgBinder extends Binder {
        public TimerDemoService getService() {
            return TimerDemoService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        timer = new Timer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && !TextUtils.isEmpty(intent.getAction())) {
            String action = intent.getAction();
//            MyLogUtil.d(TAG, "alarm set repeat " + Calendar.getInstance().getTime().toString() + "     " + action);
//            if (ConstantUtil.BROADCAST_ACTION_START_TIMER_LOOP.equals(action)) {
//
//            }
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //以下为计时器timer
    private long timerTime;

    public void startTimer() {
        if (timer == null) {
            timer = new Timer();
        }
        timerTime = count;
        if (timerTask == null) {
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    if (timerTime > 0) {
                        setCount(timerTime);
                        timerTime--;
                    } else {
                        timerFinsh();
                        closeTimer();
                    }
                }
            };
            if (timer != null && timerTask != null) {
                timer.schedule(timerTask, 0, 1000);
            }
        }
    }


    public void timerFinsh() {
        setCount(0);   
        setGoon(false);
        setPause(false);
        setStart(true);
        Intent intent = new Intent(this, TimerFinishActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void closeTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }

    public void initTime() {
        count = 0;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public boolean isStart() {
        return isStart;
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    public boolean isPause() {
        return isPause;
    }

    public void setPause(boolean pause) {
        isPause = pause;
    }

    public boolean isGoon() {
        return isGoon;
    }

    public void setGoon(boolean goon) {
        isGoon = goon;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public int getTabPosition() {
        return tabPosition;
    }

    public void setTabPosition(int tabPosition) {
        this.tabPosition = tabPosition;
    }
}
