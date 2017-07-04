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
//import android.app.Dialog;
//import android.content.Context;
//import android.graphics.drawable.ColorDrawable;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.PopupWindow;
//import android.widget.TextView;
//
//import com.haiersmart.sfnation.R;
//import com.haiersmart.voice.ui.view.MonthDateView;
//
///**
// * Created by yuzhen on 2017/1/14.
// *@time 2017/1/14  16:13
// */
//public class CalendarPop extends PopupWindow {
//    private MonthDateView monthDateView;
//    private TextView tv_date;
//    private TextView tv_week;
//    private Window window = null;
//    public Context mContext;
//    public View mMenuView;
//
//    public CalendarPop(Context context,int Layout) {
//        //super(context,R.style.custom_dialog);
//        mContext = context;
//        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        mMenuView = inflater.inflate(Layout, null);
//        setContentView(mMenuView);
//        initView(mMenuView);
//    }
//
//    private void initView(View menuView) {
//        monthDateView = (MonthDateView) menuView.findViewById(R.id.monthDateView);
//        tv_date = (TextView) menuView.findViewById(R.id.date_text);
//        tv_week  =(TextView) menuView.findViewById(R.id.week_text);
//        // 设置PopupWindow弹出窗体的宽
//        this.setWidth(453);
//        // 设置PopupWindow弹出窗体的高
//        this.setHeight(361);
//        // 设置PicPopupWindow弹出窗体可点击
//        this.setFocusable(true);
//        this.setOutsideTouchable(true);
//        // 设置PicPopupWindow弹出窗体动画效果
//        //this.setAnimationStyle(R.style.AnimBottom);
//        // 实例化一个ColorDrawable颜色为半透明
//        ColorDrawable dw = new ColorDrawable(0xb0000000);
//        // 设置SelectPicPopupWindow弹出窗体的背景
//        this.setBackgroundDrawable(dw);
//        monthDateView.setTextView(tv_date,tv_week);
//    }
//
//    public static void showPopFoodManager(Activity activity) {
//        CalendarPop mPopupwindow = new CalendarPop(activity,R.layout.dialog_calendar);
//        //Helps.setMyPopUpWindow(mPopupwindow);
//        // 显示窗口
//        mPopupwindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.TOP | Gravity.RIGHT,20, 282); // 设置layout在PopupWindow中显示的位置
//    }
//}
