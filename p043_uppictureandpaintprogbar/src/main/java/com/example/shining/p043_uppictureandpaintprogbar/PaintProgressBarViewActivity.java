package com.example.shining.p043_uppictureandpaintprogbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shining.p043_uppictureandpaintprogbar.countview.CountDownView;


public class PaintProgressBarViewActivity extends AppCompatActivity {
    CountDownView cdv;
    TextView tv_start;
    TextView tv_finish;
    TextView tv_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint);
        cdv = (CountDownView) findViewById(R.id.countDownView);
        tv_start = (TextView) findViewById(R.id.tv_start);
        tv_finish = (TextView) findViewById(R.id.tv_finish);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cdv.setCountdownTime(100);
                cdv.startCountDown();
            }
        });
        tv_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cdv.jieshu_set();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cdv.jieshu_set();
            }
        });

        cdv.setAddCountDownListener(new CountDownView.OnCountDownFinishListener() {
            @Override
            public void countDownFinished() {
                Toast.makeText(PaintProgressBarViewActivity.this, "倒计时结束", Toast.LENGTH_SHORT).show();
            }
        });
        cdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                cdv.setCountdownTime(100);
//                cdv.startCountDown();
            }
        });

    }
}
