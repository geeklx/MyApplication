package com.haiersmart.voice.service;//package com.haiersmart.voice.service;
//
//import android.app.Service;
//import android.content.Intent;
//import android.content.ServiceConnection;
//import android.media.MediaPlayer;
//import android.os.Binder;
//import android.os.IBinder;
//import android.text.TextUtils;
//import android.util.Log;
//
//import com.haiersmart.voice.bean.event.ChooseEvent;
//import com.haiersmart.voice.bean.event.MessageEvent;
//
//import org.greenrobot.eventbus.EventBus;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class MusicPlayerService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {
//    private String TAG = getClass().getSimpleName();
//    private MediaPlayer mPlayer;
//    private String mPath;
//    private ArrayList<String > musicList;
////    private int current;
//    private boolean flag;
//    private boolean prepare;
//
//    // 服务的生命周期中  创建的方法
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        mPlayer = new MediaPlayer();
//        mPlayer.setOnPreparedListener(this);
//        mPlayer.setOnCompletionListener(this);
//        mPlayer.setOnErrorListener(this);
////        current = 0;
//        flag = true;
//    }
//
//    //销毁
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        //在Activity 和其他有生命周期的一些控件里面发出网络请求，
//        // 务必要在 这个 Activity或者控件销毁的时候 取消网络请求
//        // 让后释放各种资源  释放资源的时候可以利用try catch 捕捉 程序所有异常
//        flag = false;
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
//    }
//
//    //这个方法主要是在用bind的方式开启服务的时候调用
//    @Override
//    public IBinder onBind(Intent intent) {
//        return myBinder;
//    }
//
//    public class MyBinder extends Binder {
//        public MusicPlayerService getService() {
//            return MusicPlayerService.this;
//        }
//
//        public void musicStart() {
//
//            if (mPlayer!=null) {
//                mPlayer.start();
//            }
//        }
//
//        public void musicPause() {
//            if (mPlayer!=null) {
//                mPlayer.pause();
//            }
//        }
//        public boolean isService(){
//            return flag;
//        }
//        public boolean isPrepare(){
//            return prepare;
//        }
//    }
//
//    private MyBinder myBinder = new MyBinder();
//
//    // 用startService(intent) 开始服务的时候给服务传递信息的
//    // 通过Intent传值
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//
//        if (intent!=null) {
//            musicList = intent.getStringArrayListExtra("musicList");
////            mPath = musicList.get(current);
//            mPath = musicList.get(0);
//            Log.d(TAG, "path==: " + mPath);
//
//            boolean flag = intent.getBooleanExtra("flag", false);
//            String path = intent.getStringExtra("path");
//        }
//
//        if (!TextUtils.isEmpty(mPath)) {
//            try {
//                mPlayer.setDataSource(mPath);
//                // 异步准备
//                mPlayer.prepareAsync();
//                // 同步准备
//                //Player.prepare();
////                    mPlayer.start();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
////            mPlayer.start();
////            if (!TextUtils.isEmpty(path) && !path.equals(mPath)) {
////                mPath = path;
////                if (!TextUtils.isEmpty(mPath)) {
////                    try {
////                        mPlayer.setDataSource(mPath);
////                        // 异步准备
////                        mPlayer.prepareAsync();
////                        // 同步准备
////                        //Player.prepare();
//////                    mPlayer.start();
////                    } catch (IOException e) {
////                        e.printStackTrace();
////                    }
////                }
////            }
//
//        }
//
//
//        return super.onStartCommand(intent, flags, startId);
//    }
//
//    // 准备好了的监听
//    @Override
//    public void onPrepared(MediaPlayer mp) {
//        Log.d(TAG, "onPrepared: " + "准备好了开始播放");
//        prepare = true;
//        EventBus.getDefault().post(new MessageEvent(true));
//        mp.start();
//    }
//
//    @Override
//    public void onCompletion(MediaPlayer mp) {
//        //        当前音乐正在播放 切换音乐时需要重置一下
//
//        if (mp.isPlaying()) {
//            mp.pause();
//        }
//        //切换播放地址的时候要给player重置一下
//        mp.reset();
//
////        current++;
////        if (current>=musicList.size()){
////            current=0;
////        }
////        String path = musicList.get(current);
//
////        if (!TextUtils.isEmpty(path)) {
////            try {
////                mPlayer.setDataSource(path);
////                // 异步准备
////                mPlayer.prepareAsync();
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////        }
////        EventBus.getDefault().post(new ChooseEvent(current));
//    }
//
//    // 在视频或者音频播放的是出错了  回调的方法
//    @Override
//    public boolean onError(MediaPlayer mp, int what, int extra) {
//        //一般的情况就会在这里让用户从新加载（dialog Toast）
//        return false;
//    }
//
//    @Override
//    public void unbindService(ServiceConnection conn) {
//        super.unbindService(conn);
//        Log.d(TAG, "unbindService: 方法执行了");
//        try {
//            if (mPlayer != null) {
//                if (mPlayer.isPlaying()) {
//                    mPlayer.stop();
//                }
//                // 释放流 释放资源
//                mPlayer.release();
//            }
//        } catch (Exception e) {
//        }
//    }
//
//
//}
//
//
