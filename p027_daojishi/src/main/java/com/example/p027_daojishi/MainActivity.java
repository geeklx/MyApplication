package com.example.p027_daojishi;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.p027_daojishi.util.TimeUtil;

import java.lang.ref.WeakReference;

import static com.example.p027_daojishi.util.TimeUtil.time_change_one;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    TextView tv0;
    TextView tp_hour;
    TextView tp_min;
    TextView tp_sec;
    TextView tv_hour_decade;

    private H mHandler2 = new H(this);
    private static final int MSG_RUN = 189;
    private static final int DELAY_MILLIS = 1000;
    private long mCurrentTime;

    private static class H extends Handler {
        private WeakReference<MainActivity> mActivity;
        private long mNextTime;

        public H(MainActivity activity) {
            mActivity = new WeakReference<MainActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            MainActivity activity = mActivity.get();
            if (activity == null || msg.what != MSG_RUN) {
                return;
            }
            if (activity.decrTime(SystemClock.uptimeMillis() - mNextTime)) {
                mNextTime = SystemClock.uptimeMillis();
                sendEmptyMessageDelayed(MSG_RUN, DELAY_MILLIS);
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv0 = (TextView) findViewById(R.id.tv0);
        tp_hour = (TextView) findViewById(R.id.tp_hour);
        tp_min = (TextView) findViewById(R.id.tp_min);
        tp_sec = (TextView) findViewById(R.id.tp_sec);
        tv_hour_decade = (TextView) findViewById(R.id.tv_hour_decade);

        tv0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = Integer.parseInt(tp_hour.getText().toString());
                int min = Integer.parseInt(tp_min.getText().toString());
                int sec = Integer.parseInt(tp_sec.getText().toString());
                if (hour > 0 || min > 0 || sec > 0) {
                    //计时bufen
//                    startTimer(hour, min, sec);
                    startTimer2(60000);
                }
            }
        });
    }


    private void startTimer(int hour, int min, int sec) {
        //设置时分秒bufen
        setTime(hour, min, sec);
        startTime();
    }

    private void startTimer2(long current) {
        //设置时分秒bufen
        mCurrentTime = current;
        startTime();
    }

    private void startTime() {
        handler_chushihua();
        //启动handlerbufen
        mHandler2.sendEmptyMessageDelayed(MSG_RUN, DELAY_MILLIS);
    }

    private void handler_chushihua() {
        mHandler2.removeMessages(MSG_RUN);
        mHandler2.mNextTime = SystemClock.uptimeMillis();
        decrTime(0);
    }

    /**
     * @param
     * @return void
     * @throws Exception
     * @throws
     * @Description: 设置倒计时的时长
     */
    public void setTime(int hour, int min, int sec) {
        mCurrentTime = (hour * 60 * 60 + min * 60 + sec) * 1000;
    }

    /**
     * 倒计时
     *
     * @param ms
     * @return true 继续倒计时， false停止倒计时
     */
    private boolean decrTime(long ms) {
        mCurrentTime -= ms;
        if (mCurrentTime <= 0) {
            // 已结束
            doCancel();
            return false;
        }

        long[] times = TimeUtil.compute(mCurrentTime);
        tv_hour_decade.setText(time_change_one(times[1]) + " : " + time_change_one(times[2]) + " : " + time_change_one(times[3]));
        return true;
    }

    private void doCancel() {
        mHandler2.removeMessages(MSG_RUN);
        setTime(0, 0, 0);
        tp_hour.setText("00");
        tp_min.setText("00");
        tp_sec.setText("00");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        doCancel();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
        mHandler2.removeMessages(MSG_RUN);
    }
}