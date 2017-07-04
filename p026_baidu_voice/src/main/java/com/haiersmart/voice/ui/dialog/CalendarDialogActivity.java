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
//import android.app.Activity;
//import android.os.Bundle;
//import android.view.View;
//import android.view.Window;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.haiersmart.sfnation.R;
//import com.haiersmart.voice.ui.view.MonthDateView;
//
///**
// * Created by yuzhen on 2017/1/16.
// *@time 2017/1/16  11:25
// */
//public class CalendarDialogActivity extends Activity {
//    private MonthDateView monthDateView;
//    private TextView tv_date;
//    private TextView tv_week;
//    private RelativeLayout rl_calendar_finish;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.dialog_calendar);
//        initView();
//
//
//    }
//    private void initView() {
//        rl_calendar_finish = (RelativeLayout) findViewById(R.id.rl_calendar_finish);
//        monthDateView = (MonthDateView) findViewById(R.id.monthDateView);
//        tv_date = (TextView) findViewById(R.id.date_text);
//        tv_week  =(TextView) findViewById(R.id.week_text);
//        monthDateView.setTextView(tv_date,tv_week);
//        rl_calendar_finish.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
//    }
//}
