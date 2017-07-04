package com.haiersmart.voice.ui.popupwindow;///**
// * Copyright 2016,Smart Haier.All rights reserved
// * Description:百度食材识别弹窗
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
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.PopupWindow;
//import android.widget.TextView;
//
//import com.haiersmart.sfnation.R;
//import com.haiersmart.sfnation.widget.HorizontalListView;
//
///**
// * Created by yuzhen on 2017/2/17.
// *
// * @time 2017/2/17  13:55
// */
//public class FoodRecPop extends PopupWindow {
//    private static Activity mContext;
//    private View mMenuView;
//    private static String mUrl;
//    private static FoodRecPop mFoodRecPop = new FoodRecPop();
//    private ImageView iv_pop_baidu_foodpic;
//    private TextView tv_pop_baidu_foodname;
//    private TextView tv_pop_baidu_benefit;
//    private TextView tv_pop_baidu_benefit_detail;
//    private LinearLayout ll_pop_baidu_foodbenefit;
//    private TextView tv_pop_baidu_fooddesc;
//    private TextView tv_pop_baidu_fooddesc_detail;
//    private LinearLayout ll_pop_baidu_fooddetail;
//    private TextView tv_pop_baidu_buyhelp;
//    private TextView tv_pop_baidu_buyhelp_detail;
//    private LinearLayout ll_pop_baidu_foodbuy;
//    private TextView tv_pop_baidu_recipe;
//    private HorizontalListView lv_pop_baidu_recipe;
//    private LinearLayout ll_pop_baidu_recipe;
//    private LinearLayout ll_pop__baidu_background;
//    private ImageView iv_pop_baidu_close;
//
//    public static FoodRecPop getFoodRecPop() {
//        return mFoodRecPop;
//    }
//
//    public FoodRecPop() {
//        Activity activity = AppManager.getAppManager().currentActivity();
//        mContext = activity;
//        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        mMenuView = inflater.inflate(R.layout.popupwindow_foodrec, null);
//        setContentView(mMenuView);
//    }
//
//    private void initView() {
//        // 设置PopupWindow弹出窗体的宽
//        this.setWidth(788);
//        // 设置PopupWindow弹出窗体的高
//        this.setHeight(1108);
//        // 设置PicPopupWindow弹出窗体可点击
//        this.setFocusable(true);
//        this.setOutsideTouchable(true);
//        // 设置PicPopupWindow弹出窗体动画效果
//        //this.setAnimationStyle(R.style.AnimBottom);
//        // 实例化一个ColorDrawable颜色为半透明
//        ColorDrawable dw = new ColorDrawable(0xb0000000);
//        // 设置SelectPicPopupWindow弹出窗体的背景
//        this.setBackgroundDrawable(dw);
//        //食材图片
//        iv_pop_baidu_foodpic = (ImageView) mMenuView.findViewById(R.id.iv_pop_baidu_foodpic);
//        //食材名
//        tv_pop_baidu_foodname = (TextView) mMenuView.findViewById(R.id.tv_pop_baidu_foodname);
//        //食材好处
//        tv_pop_baidu_benefit_detail = (TextView) mMenuView.findViewById(R.id.tv_pop_baidu_benefit_detail);
//        //食材说明
//        tv_pop_baidu_fooddesc_detail = (TextView) mMenuView.findViewById(R.id.tv_pop_baidu_fooddesc_detail);
//        //选购指南
//        tv_pop_baidu_buyhelp_detail = (TextView) mMenuView.findViewById(R.id.tv_pop_baidu_buyhelp_detail);
//        //菜谱推荐列表
//        lv_pop_baidu_recipe = (HorizontalListView) mMenuView.findViewById(R.id.lv_pop_baidu_recipe);
//
//    }
//
//    public void showFoodRecPop(String url) {
//        mUrl = url;
//        initView();
//        //Helps.setMyPopUpWindow(mPopupwindow);
//        // 显示窗口
//        mFoodRecPop.showAtLocation(mContext.getWindow().getDecorView(), Gravity.TOP | Gravity.RIGHT, 20, 282); // 设置layout在PopupWindow中显示的位置
//    }
//
//    public void dismissFoodRecPop() {
//        if (mFoodRecPop.isShowing()) {
//
//        }
//        mFoodRecPop.dismiss();
//    }
//
//}
