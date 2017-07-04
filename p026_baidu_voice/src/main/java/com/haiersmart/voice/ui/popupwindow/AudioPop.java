package com.haiersmart.voice.ui.popupwindow;///**
// * Copyright 2016,Smart Haier.All rights reserved
// * Description:
// * Author:jiayuzhen
// * ModifyBy:
// * ModifyDate:
// * ModifyDes:
// */
//package com.haiersmart.voice.ui.popupwindow;
//
//import android.app.Activity;
//import android.content.Context;
//import android.graphics.drawable.BitmapDrawable;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.widget.PopupWindow;
//import android.widget.TextView;
//
//import com.haiersmart.sfnation.R;
//import com.haiersmart.sfnation.application.FridgeApplication;
//import com.haiersmart.utilslib.app.AppManager;
//import com.haiersmart.voice.playerview.MusicPlayerView;
//import com.haiersmart.voice.service.MusicPlayerService;
//import com.haiersmart.voice.utils.FloatPresenter;
//import com.haiersmart.voice.utils.PreparedCallBack;
//
//import java.util.ArrayList;
//
//import static com.haiersmart.voice.utils.FloatPresenter.X_OFF;
//import static com.haiersmart.voice.utils.FloatPresenter.Y_OFF;
//
///**
// * Created by yuzhen on 2017/1/14.
// *
// * @time 2017/1/14  16:13
// */
//public class AudioPop extends PopupWindow {
//    private String TAG = "MusicPlayerService";
//    public static final String MUSIC_LIST = "musicList";
//    private ArrayList<String> musicList = new ArrayList<String>();
//    private ArrayList<String> coverList = new ArrayList<String>();
//    private ArrayList<String> musicTitles = new ArrayList<String>();
//    private ArrayList<Integer> durations = new ArrayList<Integer>();
//    private View mMenuView;
//    private static AudioPop audioPop = new AudioPop();
//    private MusicPlayerView mMpv;
//    private MusicPlayerService.MyBinder mBinder;
//    private boolean flag;
//    private TextView mTvTitle;
//    private int index;
//
//
//    private boolean isBind;
//    private boolean isStart;
//    private boolean mService;
//    private Activity mActivity;
//
//    public static AudioPop getAudioPop() {
//        return audioPop;
//    }
//
//    public AudioPop() {
//        //super(context,R.style.custom_dialog);
////        EventBus.getDefault().register(this);
//    }
//
//    private void initView() {
//        mBinder = FridgeApplication.get().getMusicBinder();
//        flag = mBinder.isService();
//        mMpv = (MusicPlayerView) mMenuView.findViewById(R.id.mpv);
//        mTvTitle = (TextView) mMenuView.findViewById(R.id.tv_title_music);
//        if (coverList.size() > 0 && coverList.get(0) != null) {
//            mMpv.setCoverURL(coverList.get(0));// 覆盖图
//        } else {
//            mMpv.setCoverDrawable(R.drawable.album_default);
//        }
//        if (durations.size() > 0 && durations.get(0) != 0) {
//            mMpv.setMax(durations.get(0));
//        } else {
//            mMpv.setProgressVisibility(false);
//        }
////		mMpv.setEnabled(false);
//        if (mBinder != null) {
//            mService = mBinder.isService();
//        }
//        mMpv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mMpv.isRotating()) {
//                    if (flag) {
//                        mMpv.stop();
//                        mBinder.musicPause();
//                        Log.e(TAG, "onClick: mMpv.stop()-停止播放---isRotating" + mMpv.isRotating());
//                    }
//
//                } else {
//                    if (flag) {
//                        mMpv.start();
//                        mBinder.musicContinue();
//                        Log.e(TAG, "onClick: mMpv.start()-开始播放---isRotating" + mMpv.isRotating());
//                    }
//                }
//            }
//        });
//        mBinder.getService().setOnPrepared(new PreparedCallBack() {
//            @Override
//            public void isPrepared(boolean prepared) {
//                if (prepared) {
//                    if (mMpv != null) {
//                        mMpv.start();
//                    }
//                } else {
//                    if (mMpv != null) {
//                        mMpv.stop();
//                    }
//                }
//            }
//        });
//
//
////		mTvTitle.setText(musicTitles.get(0));
//        // 设置PopupWindow弹出窗体的宽
//        this.setWidth(470);
//        // 设置PopupWindow弹出窗体的高
//        this.setHeight(365);
//        // 设置PicPopupWindow弹出窗体可点击
//        this.setFocusable(true);
//        this.setOutsideTouchable(true);
//        // 设置PicPopupWindow弹出窗体动画效果
//        //this.setAnimationStyle(R.style.AnimBottom);
//        // 实例化一个ColorDrawable颜色为半透明
////		ColorDrawable dw = new ColorDrawable(0xb0000000);
//        // 设置SelectPicPopupWindow弹出窗体的背景
//        this.setBackgroundDrawable(new BitmapDrawable());
//        mMenuView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                dismiss();
//                return false;
//            }
//        });
//    }
//
//
//    //    private void bindService(Context mContext) {
////        isBind = true;
////        Intent intent = new Intent(mContext, MusicPlayerService.class);
////        intent.putStringArrayListExtra(MUSIC_LIST, musicList);
////        mContext.startService(intent);
////    }
////    private void startService(Context mContext){
////
////    }
//
//
//    //显示
//    public void showAudioPopWindow(String mp3_audio_url, String cover_url, String title, int duration) {
//        mActivity = createView(mp3_audio_url, cover_url, title, duration);
//        // 显示窗口
//        int x = (int) FloatPresenter.getMyFV().getmTouchStartX() + X_OFF;
//        int y = (int) FloatPresenter.getMyFV().getmTouchStartY() + Y_OFF;
//        audioPop.showAtLocation(mActivity.getWindow().getDecorView(), Gravity.TOP | Gravity.LEFT, x, y);
//    }    //显示
//
//    public void showAudioPopWindowInVoice(String mp3_audio_url, String cover_url, String title, int duration) {
//
//        if (index < 1) {
//            Log.e(TAG, "showAudioPopWindowInVoice: 第一次调用show");
//            mActivity = createView(mp3_audio_url, cover_url, title, duration);
//            if (mBinder != null) {
//                mBinder.musicStart(mp3_audio_url);
//            } else {
//                Log.e(TAG, "mBinder is null");
//            }
//            // 显示窗口
//            audioPop.showAtLocation(mActivity.getWindow().getDecorView(), Gravity.TOP | Gravity.RIGHT, 20, 282);
//        } else {
//            Log.e(TAG, "showAudioPopWindowInVoice: 第二次调用show");
////            audioPop.dismissAudioPopWindow();
//
//            mActivity = createView(mp3_audio_url, cover_url, title, duration);
//            if (mBinder != null) {
//                mBinder.musicNext(mp3_audio_url);
//                Log.e(TAG, "showAudioPopWindowInVoice: 开始旋转--isRotating" + mMpv.isRotating());
//            } else {
//                Log.e(TAG, "mBinder is null");
//            }
//            // 显示窗口
//            audioPop.showAtLocation(mActivity.getWindow().getDecorView(), Gravity.TOP | Gravity.RIGHT, 20, 282);
//        }
//        index++;
//
//    }
//
//    private Activity createView(String mp3_audio_url, String cover_url, String title, int duration) {
//        musicList.clear();
//        coverList.clear();
//        musicTitles.clear();
//        durations.clear();
//        musicList.add(mp3_audio_url);
//        coverList.add(cover_url);
//        musicTitles.add(title);
//        durations.add(duration);
//        mActivity = AppManager.getAppManager().currentActivity();
//        Log.e(TAG, "createView: --------->" + mActivity);
//        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        mMenuView = inflater.inflate(R.layout.audio_player_layout, null);
//        setContentView(mMenuView);
//        initView();
//
//        return mActivity;
//    }
//
//    //重写dismiss方法
//    @Override
//    public void dismiss() {
//        super.dismiss();
//        if (mBinder != null) {
//            mBinder.musicDestroy();
//        }
//        Log.e(TAG, "dismiss(): 执行Override的dimiss方法");
//        index = 0;
//    }
//
//
//
//    public void pauseAudioPopWindow() {
//
//        if (mBinder != null) {
//            mBinder.musicPause();
//        }
//        if (mMpv != null) {
//            mMpv.stop();
//        }
//    }
//
//
//}
