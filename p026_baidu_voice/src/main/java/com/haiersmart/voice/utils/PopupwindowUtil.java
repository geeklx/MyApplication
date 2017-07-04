package com.haiersmart.voice.utils;//package com.haiersmart.voice.utils;
//
//import android.app.Activity;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.PopupWindow;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//import com.haiersmart.sfnation.R;
//
///**
// * Create with Android Studio.
// *
// * @author Hsueh
// * @email i@hsueh.top
// * @date 2017/2/8 17:02
// */
//
//public class PopupwindowUtil {
//    private String TAG = getClass().getSimpleName();
//    Activity activity = AppManager.getAppManager().currentActivity();
//    private static PopupwindowUtil popupwindowUtil = new PopupwindowUtil();
//    private ImageView mImageView;
//
//    public static PopupwindowUtil getInstance (){
//        return popupwindowUtil;
//    }
//    public void showPopuowindow(View parent,int resId) {
//
//        View view = View.inflate(activity, R.layout.popupwindow_baiduemotion,null);
//        mImageView = (ImageView) view.findViewById(R.id.iv_baidu_emotion);
//        PopupWindow window = new PopupWindow(view, 182, 182);
//        showEmotion(resId);
////		window.setTouchable(true);
////		window.setOutsideTouchable(true);
//        if (!window.isShowing()){
//            Log.d(TAG, "showPopuowindow: ----- 重新创建popupwindow");
//            window.showAtLocation(parent, Gravity.TOP,500,40);
//        }
////		window.setFocusable(true);
//    }
//    public void showEmotion(int resId){
//        Glide.with(activity).load(resId).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).centerCrop().into(mImageView);
//    }
//}
