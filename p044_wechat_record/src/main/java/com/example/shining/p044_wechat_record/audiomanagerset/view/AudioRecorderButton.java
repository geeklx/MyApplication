package com.example.shining.p044_wechat_record.audiomanagerset.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.shining.p044_wechat_record.R;
import com.example.shining.p044_wechat_record.audiomanagerset.manager.AudioManager;
import com.example.shining.p044_wechat_record.audiomanagerset.manager.DialogManager;
import com.example.shining.p044_wechat_record.quanxian.PermissionsChecker;

import static com.example.shining.p044_wechat_record.quanxian.PermissionsChecker.PERMISSIONS_STORAGE;
import static com.example.shining.p044_wechat_record.quanxian.PermissionsChecker.REQUEST_EXTERNAL_STORAGE;

public class AudioRecorderButton extends Button implements AudioManager.AudioStateListener {

    private static final int DISTANCE_Y_CANCEL = 50;
    private static final int STATE_NORMAL = 1;
    private static final int STATE_RECORDING = 2;
    private static final int STATE_WANT_TO_CANCEL = 3;

    private int mCurState = STATE_NORMAL;
    // 已经开始录音
    private boolean isRecording = false;

    private DialogManager mDialogManager;

    private AudioManager mAudioManager;

    private float mTime;
    // 是否触发longclick
    private boolean mReady;

    public AudioRecorderButton(Context context) {
        this(context, null);
    }

    public AudioRecorderButton(final Context context, AttributeSet attrs) {
        super(context, attrs);

        mDialogManager = new DialogManager(getContext());

        String dir = Environment.getExternalStorageDirectory()
                + "/geek_recorder_audios";
        mAudioManager = AudioManager.getInstance(dir);
        mAudioManager.setOnAudioStateListener(this);

        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PermissionsChecker mPermissionsChecker = new PermissionsChecker(context);
                // 缺少权限时, 进入权限配置页面
                if (mPermissionsChecker.lacksPermissions(PERMISSIONS_STORAGE)) {
                    // We don't have permission so prompt the user
                    ActivityCompat.requestPermissions((Activity) context, PERMISSIONS_STORAGE,
                            REQUEST_EXTERNAL_STORAGE);
                } else {
                    mReady = true;
                    mAudioManager.prepareAudio();

                }
                return false;
            }
        });
    }

    /**
     * 录音完成后的回调
     *
     * @author zhy
     */
    public interface AudioFinishRecorderListener {
        void onFinish(float seconds, String filePath);
    }

    private AudioFinishRecorderListener mListener;

    public void setAudioFinishRecorderListener(AudioFinishRecorderListener listener) {
        mListener = listener;
    }

    /**
     * 获取音量大小的Runnbale
     */
    private Runnable mGetVoiceLevelRunnable = new Runnable() {
        @Override
        public void run() {
            while (isRecording) {
                try {
                    Thread.sleep(100);
                    mTime += 0.1f;
                    if (mTime > RecorderAdapter.MAX_SECOND) {
                        isRecording = false;
                        mHandler.sendEmptyMessage(MSG_VOICE_CHANGED_MAX);
                        Log.e("--MSG_MAX--", mTime + "");
                    } else {
                        mHandler.sendEmptyMessage(MSG_VOICE_CHANGED);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    private static final int MSG_AUDIO_PREPARED = 0X110;
    private static final int MSG_VOICE_CHANGED = 0X111;
    private static final int MSG_VOICE_CHANGED_MAX = 0X113;
    private static final int MSG_DIALOG_DIMISS = 0X112;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case MSG_AUDIO_PREPARED:
                    // 显示应该在audio end prepared以后
                    mDialogManager.showRecordingDialog();
                    isRecording = true;
                    new Thread(mGetVoiceLevelRunnable).start();
                    break;
                case MSG_VOICE_CHANGED:
                    mDialogManager.updateVoiceLevel(mAudioManager.getVoiceLevel(7));
                    break;
                case MSG_VOICE_CHANGED_MAX:
                    mDialogManager.tooLong();
                    mHandler.sendEmptyMessageDelayed(MSG_DIALOG_DIMISS, 1300);
                    mAudioManager.release();
                    if (mListener != null) {
//                        Log.e("--MSG_MAX--", mTime + "");
                        mListener.onFinish(mTime, mAudioManager.getCurrentFilePath());
                    }
                    reset();
                    break;
                case MSG_DIALOG_DIMISS:
                    mDialogManager.dimissDialog();
                    break;

            }
        }

        ;
    };

    @Override
    public void wellPrepared() {
        mHandler.sendEmptyMessage(MSG_AUDIO_PREPARED);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                changeState(STATE_RECORDING);
                break;
            case MotionEvent.ACTION_MOVE:
                if (isRecording) {
                    // 根据x,y的坐标，判断是否想要取消
                    if (wantToCancel(x, y)) {
                        changeState(STATE_WANT_TO_CANCEL);
                    } else {
                        changeState(STATE_RECORDING);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (!mReady) {
                    reset();
                    return super.onTouchEvent(event);
                }
                if (!isRecording || mTime < 0.6f) {
                    Log.e("--mCurState0.6f--", mCurState + "");
                    mDialogManager.tooShort();
                    mAudioManager.cancel();
                    mHandler.sendEmptyMessageDelayed(MSG_DIALOG_DIMISS, 1300);
                } else if (mCurState == STATE_RECORDING) {// 正常录制结束
                    Log.e("--mCurState--", mCurState + "");
                    mDialogManager.dimissDialog();
                    mAudioManager.release();
                    if (mListener != null) {
                        mListener.onFinish(mTime, mAudioManager.getCurrentFilePath());
                    }

                } else if (mCurState == STATE_WANT_TO_CANCEL) {
                    mDialogManager.dimissDialog();
                    mAudioManager.cancel();
                }
                reset();
                break;

        }

        return super.onTouchEvent(event);
    }

    /**
     * 恢复状态及标志位
     */
    private void reset() {
        isRecording = false;
        mReady = false;
        mTime = 0;
        changeState(STATE_NORMAL);

    }

    private boolean wantToCancel(int x, int y) {
        if (x < 0 || x > getWidth()) {
            return true;
        }
        if (y < -DISTANCE_Y_CANCEL || y > getHeight() + DISTANCE_Y_CANCEL) {
            return true;
        }
        return false;
    }

    private void changeState(int state) {
        if (mCurState != state) {
            mCurState = state;
            switch (state) {
                case STATE_NORMAL:
                    setBackgroundResource(R.drawable.btn_recorder_normal);
                    setText(R.string.str_recorder_normal);
                    break;
                case STATE_RECORDING:
                    setBackgroundResource(R.drawable.btn_recording);
                    setText(R.string.str_recorder_recording);
                    if (isRecording) {
                        mDialogManager.recording();
                    }
                    break;
                case STATE_WANT_TO_CANCEL:
                    setBackgroundResource(R.drawable.btn_recording);
                    setText(R.string.str_recorder_want_cancel);
                    mDialogManager.wantToCancel();
                    break;

            }
        }
    }

}
