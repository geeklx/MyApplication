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
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.PopupWindow;
//
//import com.bumptech.glide.Glide;
//import com.haiersmart.sfnation.R;
//
///**
// * Created by yuzhen on 2017/1/14.
// *@time 2017/1/14  16:13
// */
//public class ImagePop extends PopupWindow {
//    private String TAG = ImagePop.class.getSimpleName();
//    private ImageView mImageView;
//    private static Activity mContext ;
//    private View mMenuView;
//    private static ImagePop mImagePop = new ImagePop();
//
//    public static ImagePop getImagePop (){
//        return mImagePop;
//    }
//
//    public ImagePop() {
//        //super(context,R.style.custom_dialog);
//
//    }
//
//    private void initView(String url) {
//        Activity top = com.haiersmart.utilslib.app.AppManager.getInstance().top();
//        mContext = top;
//        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        mMenuView = inflater.inflate(R.layout.popupwindow_image, null);
//        setContentView(mMenuView);
//        mImageView = (ImageView) mMenuView.findViewById(R.id.iv_pop_image);
//        Log.d(TAG, "initView: url->>"+url);
////        mWebView.setWebChromeClient(new WebChromeClient());
//        Glide.with(mContext).load(url).into(mImageView);
//
//        // 设置PopupWindow弹出窗体的宽
//        this.setWidth(800);
//        // 设置PopupWindow弹出窗体的高
//        this.setHeight(600);
//        // 设置PicPopupWindow弹出窗体可点击
//        this.setFocusable(true);
//        this.setOutsideTouchable(true);
//        // 设置PicPopupWindow弹出窗体动画效果
//        //this.setAnimationStyle(R.style.AnimBottom);
//        // 实例化一个ColorDrawable颜色为半透明
////        ColorDrawable dw = new ColorDrawable(0xb0000000);
//        // 设置SelectPicPopupWindow弹出窗体的背景
//        this.setBackgroundDrawable(new BitmapDrawable());
//    }
//
//    public void showImagePop(String url) {
//        initView(url);
//        //Helps.setMyPopUpWindow(mPopupwindow);
//        // 显示窗口
//        if (mImagePop.isShowing()) {
//            mImagePop.dismissImagePop();
//            mImagePop.showAtLocation(mContext.getWindow().getDecorView(), Gravity.TOP | Gravity.RIGHT,20, 282); // 设置layout在PopupWindow中显示的位置
//        } else {
//            mImagePop.showAtLocation(mContext.getWindow().getDecorView(), Gravity.TOP | Gravity.RIGHT,20, 282); // 设置layout在PopupWindow中显示的位置
//        }
//    }
//    public void dismissImagePop(){
//        if (mImagePop !=null && mImagePop.isShowing()) {
//            mImagePop.dismiss();
//        }
//    }
//
//}
