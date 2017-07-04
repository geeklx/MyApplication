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
//import android.graphics.drawable.ColorDrawable;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.PopupWindow;
//
//import com.example.jean.jcplayer.JcAudio;
//import com.example.jean.jcplayer.JcPlayerView;
//import com.haiersmart.sfnation.R;
//import com.haiersmart.utilslib.app.AppManager;
//
//import java.util.List;
//
///**
// * Created by yuzhen on 2017/2/13.
// *@time 2017/2/13  17:13
// */
//public class MusicPop extends PopupWindow {
//    private static Activity mContext;
//    private View mMenuView;
//    private static MusicPop mMusicPop = new MusicPop();
//
//    private JcPlayerView player ;
//
//
//    public static MusicPop getMusicPop(){
//        return mMusicPop;
//    }
//
//    public MusicPop() {
//
//    }
//
//    private void initView(List<JcAudio> jcAudios,JcAudio jcAudio) {
//
//        Activity top = AppManager.getInstance().top();
//        mContext = top;
//        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        mMenuView = inflater.inflate(R.layout.popupwindow_music, null);
//        setContentView(mMenuView);
////        player = (JcPlayerView) mMenuView.findViewById(R.id.jcplayer);
//
//        // 设置PopupWindow弹出窗体的宽
//        this.setWidth(464);
//        // 设置PopupWindow弹出窗体的高
//        this.setHeight(361);
//        // 设置PopupWindow弹出窗体可点击
//        this.setFocusable(true);
//        this.setOutsideTouchable(true);
//        // 设置PopupWindow弹出窗体动画效果
//        //this.setAnimationStyle(R.style.AnimBottom);
//        // 实例化一个ColorDrawable颜色为半透明
//        ColorDrawable dw = new ColorDrawable(0xb0000000);
//        // 设置SelectPicPopupWindow弹出窗体的背景
//        this.setBackgroundDrawable(dw);
//
//        player.initPlaylist(jcAudios);
////        player.playAudio(jcAudio);
//
//    }
//
//    public void showMusicPop(List<JcAudio> jcAudios, JcAudio jcAudio){
//        //Helps.setMyPopUpWindow(mPopupwindow);
//        initView(jcAudios,jcAudio);
//        player.createNotification();
//        // 显示窗口
//        mMusicPop.showAtLocation(mContext.getWindow().getDecorView(), Gravity.TOP | Gravity.RIGHT,20, 282); // 设置layout在PopupWindow中显示的位置
//    }
//    public void dissMissMusicPop(){
//        mMusicPop.dismiss();
//        if (player != null) {
//            player.kill();
//        }
//    }
//}
