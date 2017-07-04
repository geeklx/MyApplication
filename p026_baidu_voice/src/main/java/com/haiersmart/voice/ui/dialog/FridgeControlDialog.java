///**
// * Copyright 2016,Smart Haier.All rights reserved
// * Description:
// * Author:jiayuzhen
// * ModifyBy:
// * ModifyDate:
// * ModifyDes:
// */
//package com.haiersmart.voice.ui.dialog;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.h6ah4i.android.widget.verticalseekbar.VerticalSeekBar;
//import com.h6ah4i.android.widget.verticalseekbar.VerticalSeekBarWrapper;
//import com.haiersmart.sfnation.R;
//import com.haiersmart.voice.utils.FridgeControlUtil;
//
///**
// * Created by yuzhen on 2017/1/14.
// *
// * @time 2017/1/14  20:25
// */
//public class FridgeControlDialog extends Dialog {
//    private ImageView dialog_ai_control_fridge;
//    private TextView tv_freezer;
//    private TextView tv_freezer_temp;
//    private VerticalSeekBar dialog_seekBar1;
//    private VerticalSeekBarWrapper dialog_seekBarContainer1;
//    private LinearLayout ll_lengcang;
//    private TextView tv_bianwenshi;
//    private TextView tv_bianwenshi_temp;
//    private VerticalSeekBar dialog_seekBar2;
//    private VerticalSeekBarWrapper dialog_seekBarContainer2;
//    private LinearLayout ll_bianwen;
//    private TextView tv_lengdongshi;
//    private TextView tv_lengdongshi_temp;
//    private VerticalSeekBar dialog_seekBar3;
//    private VerticalSeekBarWrapper dialog_seekBarContainer3;
//    private LinearLayout ll_lengdong;
//    private RelativeLayout rl_temp_control;
//    int freezeLevel,middleLevel,coldLevel;
//    public FridgeControlDialog(Context context) {
//        super(context, R.style.custom_dialog);
//        setContentView(R.layout.dialog_voice_fridge_control);
//        getTemp();
//        initView();
//    }
//
//    private void getTemp() {
//         FridgeControlUtil fridgeControlUtil = FridgeControlUtil.getInstance();
//         freezeLevel = fridgeControlUtil.getFreezeLevel();
//         middleLevel = fridgeControlUtil.getMiddleLevel();
//         coldLevel = fridgeControlUtil.getColdLevel();
//    }
//
//    private void initView() {
//        tv_freezer_temp = (TextView) findViewById(R.id.tv_freezer_temp);
//        tv_bianwenshi_temp = (TextView) findViewById(R.id.tv_bianwenshi_temp);
//        tv_lengdongshi_temp = (TextView) findViewById(R.id.tv_lengdongshi_temp);
//        dialog_seekBar1 = (VerticalSeekBar) findViewById(R.id.dialog_seekBar1);
//        dialog_seekBar2 = (VerticalSeekBar) findViewById(R.id.dialog_seekBar2);
//        dialog_seekBar3 = (VerticalSeekBar) findViewById(R.id.dialog_seekBar3);
//        dialog_seekBarContainer1 = (VerticalSeekBarWrapper) findViewById(R.id.dialog_seekBarContainer1);
//        dialog_seekBarContainer2 = (VerticalSeekBarWrapper) findViewById(R.id.dialog_seekBarContainer2);
//        dialog_seekBarContainer3 = (VerticalSeekBarWrapper) findViewById(R.id.dialog_seekBarContainer3);
//        dialog_seekBar1.setMax(8);//1-9  9幅度
//        dialog_seekBar2.setMax(25);//-20-5  26幅度
//        dialog_seekBar3.setMax(8);// -23- -15 9幅度
//        dialog_seekBar1.setProgress(freezeLevel-1);
//        dialog_seekBar2.setProgress(middleLevel+20);
//        dialog_seekBar3.setProgress(coldLevel+23);
//        tv_freezer_temp.setText(freezeLevel+"℃");
//        tv_bianwenshi_temp.setText(middleLevel+"℃");
//        tv_lengdongshi_temp.setText(coldLevel+"℃");
//        dialog_seekBar1.setEnabled(false);
//        dialog_seekBar2.setEnabled(false);
//        dialog_seekBar3.setEnabled(false);
//    }
//
//
//}
