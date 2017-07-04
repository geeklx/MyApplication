package com.haiersmart.voice.activity;//package com.haiersmart.voice.activity;
//
//import android.app.Activity;
//import android.os.Bundle;
//
//import com.haiersmart.voice.utils.ToastUtil;
//
//import org.greenrobot.eventbus.EventBus;
//
//import butterknife.ButterKnife;
//
///**
// *
// * Created by JefferyLeng on 2016/12/6.
// */
//public class BaseVoiceActivity extends Activity {
//
//    protected final String TAG = this.getClass().getSimpleName();
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        ButterKnife.bind(this);
//
//        EventBus.getDefault().register(this);
//    }
//
//    /**
//     * 初始化监听。
//     */
////    protected InitListener mInitListener = new InitListener() {
////        @Override
////        public void onInit(int code) {
////            Log.d(TAG, "InitListener init() code = " + code);
////            if (code != ErrorCode.SUCCESS) {
////                showTip("初始化失败,错误码：" + code);
////            } else {
////                // 初始化成功，之后可以调用startSpeaking方法
////                // 注：有的开发者在onCreate方法中创建完合成对象之后马上就调用startSpeaking进行合成，
////                // 正确的做法是将onCreate中的startSpeaking调用移至这里
////
////            }
////        }
////    };
//
//    protected void showTip(final String str) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                ToastUtil.showShort(BaseVoiceActivity.this,str);
//            }
//        });
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        EventBus.getDefault().unregister(this);
//        AppManager.getAppManager().finishActivity(this);
//    }
//}
