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
//import android.graphics.drawable.ColorDrawable;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.PopupWindow;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//import com.bumptech.glide.load.engine.DiskCacheStrategy;
//import com.haiersmart.sfnation.R;
//import com.haiersmart.utilslib.app.AppManager;
//import com.squareup.picasso.Picasso;
//
///**
// * Created by yuzhen on 2017/2/13.
// *@time 2017/2/13  17:13
// */
//public class WeatherPop extends PopupWindow {
//    private static Activity mContext;
//    private View mMenuView;
//    private static WeatherPop mWeatherPop = new WeatherPop();
//    private TextView mTv_weather_temp;
//    private TextView mTv_weather_change;
//    private TextView mTv_temperature_change;
//    private ImageView mLl_weahter_temp_change;
//    private TextView mTv_cloud;
//    private TextView mTv_pollute_index;
//    private TextView mTv_pollute_describe;
//
//    public static WeatherPop getWeatherPop (){
//        return mWeatherPop;
//    }
//
//    public WeatherPop() {
//        //super(context,R.style.custom_dialog);
//    }
//
//    private void initView(String temp, String weatherChange, String tempChange, String iconUrl, String cloud, String polluteIndex, String pulloteDesc) {
//        Activity top = AppManager.getInstance().top();
//        mContext = top;
//        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        mMenuView = inflater.inflate(R.layout.popupwindow_weather, null);
//        setContentView(mMenuView);
//        //温度
//        mTv_weather_temp = (TextView) mMenuView.findViewById(R.id.tv_weather_temp);
//        mTv_weather_temp.setText(temp);
//        //雨转晴
//        mTv_weather_change = (TextView) mMenuView.findViewById(R.id.tv_weather_change);
//        mTv_weather_change.setText(weatherChange);
//        //10~20度
//        mTv_temperature_change = (TextView) mMenuView.findViewById(R.id.tv_temperature_change);
//        mTv_temperature_change.setText(tempChange);
//        //天气图标
//        mLl_weahter_temp_change = (ImageView) mMenuView.findViewById(R.id.iv_weather_icon);
//        Glide.with(mContext)   //使得glide更容易使用，因为能接收context，activity，fragment对象
//                .load(iconUrl)
////             .asGif()      //判断加载的url资源是否是gif格式的资源
////              .priority(Priority.HIGH)    //设置优先级
//                .placeholder(R.mipmap.home_icon)    //加载中显示的图片
//                .error(R.mipmap.home_icon)//加载失败显示的图片
//                .centerCrop()
////             .fitCenter()    //缩放图像，整个显示在控件，尽可能的填满
//                .into(mLl_weahter_temp_change);
//        //南风2-3级
//        mTv_cloud = (TextView) mMenuView.findViewById(R.id.tv_cloud);
//        mTv_cloud.setText(cloud);
//        //污染指数
//        mTv_pollute_index = (TextView) mMenuView.findViewById(R.id.tv_pollute_index);
//        mTv_pollute_index.setText(polluteIndex);
//        //污染描述：轻度污染等
//        mTv_pollute_describe = (TextView) mMenuView.findViewById(R.id.tv_pollute_describe);
//        mTv_pollute_describe.setText(pulloteDesc);
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
////        ColorDrawable dw = new ColorDrawable(0xb0000000);
////        // 设置SelectPicPopupWindow弹出窗体的背景
//        this.setBackgroundDrawable(new BitmapDrawable());
//    }
//
//    public void showWeatherPop(String temp,String weatherChange,String tempChange,String iconUrl,String cloud,String polluteIndex,String pulloteDesc) {
//        initView(temp,weatherChange,tempChange,iconUrl,cloud,polluteIndex,pulloteDesc);
//        //Helps.setMyPopUpWindow(mPopupwindow);
//        // 显示窗口
//        if (mWeatherPop.isShowing()) {
//            mWeatherPop.dismissWeatherPop();
//            mWeatherPop.showAtLocation(mContext.getWindow().getDecorView(), Gravity.TOP | Gravity.RIGHT,20, 282); // 设置layout在PopupWindow中显示的位置
//        }else {
//            mWeatherPop.showAtLocation(mContext.getWindow().getDecorView(), Gravity.TOP | Gravity.RIGHT,20, 282); // 设置layout在PopupWindow中显示的位置
//        }
//    }
//    public void dismissWeatherPop(){
//        mWeatherPop.dismiss();
//    }
//}
