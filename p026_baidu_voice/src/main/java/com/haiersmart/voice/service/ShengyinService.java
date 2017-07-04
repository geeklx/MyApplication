package com.haiersmart.voice.service;//package com.haiersmart.voice.service;
//
//import android.app.Service;
//import android.content.Intent;
//import android.media.MediaPlayer;
//import android.os.IBinder;
//import android.text.TextUtils;
//import android.util.Log;
//
//import java.io.IOException;
//
//public class ShengyinService extends Service implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener {
//
//    private String TAG=getClass().getSimpleName();
//    private MediaPlayer mPlayer;
//
//    public ShengyinService() {
//    }
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        mPlayer = new MediaPlayer();
//        mPlayer.setOnCompletionListener(this);
//        mPlayer.setOnPreparedListener(this);
//
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        if (intent!=null) {
//            String voice_url = intent.getStringExtra("voice_url");
//            if (!TextUtils.isEmpty(voice_url)){
//                try {
//                    mPlayer.setDataSource(voice_url);
//                    mPlayer.prepareAsync();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        return super.onStartCommand(intent, flags, startId);
//
//    }
//
//    @Override
//    public IBinder onBind(Intent intent) {
//        throw new UnsupportedOperationException("Not yet implemented");
//    }
//
//    @Override
//    public void onCompletion(MediaPlayer mp) {
//        try {
//            if (mPlayer != null) {
//                if (mPlayer.isPlaying()) {
//                    mPlayer.pause();
//                }
//                // 释放流 释放资源
//                mPlayer.release();
//            }
//        } catch (Exception e) {
//            Log.d(TAG, "onCompletion: MediaPlayer Exception");
//        }
//        stopSelf();
//        //AppManager.getAppManager().currentActivity().sendBroadcast(new Intent(VoiceConstants.ACTION_RESTART_SPEECH_RECOGNITION));
//    }
//
//    @Override
//    public void onPrepared(MediaPlayer mp) {
//        mp.start();
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        try {
//            if (mPlayer != null) {
//                if (mPlayer.isPlaying()) {
//                    mPlayer.pause();
//                }
//                // 释放流 释放资源
//                mPlayer.release();
//            }
//        } catch (Exception e) {
//        }
//        stopSelf();
//    }
//}
