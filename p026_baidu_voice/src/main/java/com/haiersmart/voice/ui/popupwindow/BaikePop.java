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
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.PopupWindow;
//
//import com.haiersmart.sfnation.R;
//
///**
// * Created by yuzhen on 2017/1/14.
// *@time 2017/1/14  16:13
// */
//public class BaikePop extends PopupWindow {
//    private WebView mWebView;
//    private static Activity mContext ;
//    private View mMenuView;
//    private static BaikePop mBaikePop = new BaikePop();
//
//    public static BaikePop getBaikePop (){
//        return mBaikePop;
//    }
//
//    public BaikePop() {
//        //super(context,R.style.custom_dialog);
//
//    }
//
//    private void initView(String url) {
//        Activity top = com.haiersmart.utilslib.app.AppManager.getInstance().top();
//        mContext = top;
//        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        mMenuView = inflater.inflate(R.layout.popupwindow_baike, null);
//        setContentView(mMenuView);
//
//        mWebView = (WebView) mMenuView.findViewById(R.id.web_baike);
////        mWebView.setWebChromeClient(new WebChromeClient());
//        mWebView.setWebViewClient(new WebViewClient());
//        WebSettings settings = mWebView.getSettings();
//        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
//        settings.setDomStorageEnabled(true);
//        settings.setJavaScriptEnabled(true);
//        mWebView.loadUrl(url);
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
//    public void showBaikePop(String url) {
//        initView(url);
//        //Helps.setMyPopUpWindow(mPopupwindow);
//        // 显示窗口
//
//        if (mBaikePop.isShowing()) {
//            mBaikePop.dismissBaikePop();
//            mBaikePop.showAtLocation(mContext.getWindow().getDecorView(), Gravity.TOP | Gravity.RIGHT,20, 282); // 设置layout在PopupWindow中显示的位置
//        }else {
//            mBaikePop.showAtLocation(mContext.getWindow().getDecorView(), Gravity.TOP | Gravity.RIGHT,20, 282); // 设置layout在PopupWindow中显示的位置
//        }
//    }
//    public void dismissBaikePop(){
//        if (mBaikePop!=null && mBaikePop.isShowing()) {
//            mBaikePop.dismiss();
//        }
//        if (mWebView!=null){
//            mWebView.destroy();
//        }
//    }
//
//}
