package com.example.lvfq.testapp2_progress;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * --------------------------------------------
 * auther :  Lvfq
 * 2016/6/25 22:26
 * description ：
 * <p>
 * -------------------------------------------
 **/
public class CustomDialog extends Dialog {
    public CustomDialog(Context context) {
        super(context);
    }

    private TextView tv_loading;
    private ProgressBar progressBar;

    private Timer timer;
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_progress);
        tv_loading = (TextView) findViewById(R.id.tv_loading);
        progressBar = (ProgressBar) findViewById(R.id.pb);

        // 设置Dialog显示的宽度，
        Display d = getWindow().getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        //这里设置为屏幕宽度的百分之八十
        lp.width = (int) (d.getWidth() * 0.8);
        getWindow().setAttributes(lp);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        }, 300, 300);
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (timer != null) {
                    timer.cancel();
                }
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            count++;
            if (count > 3) {
                count = 1;
            }
            switch (count) {
                case 1:
                    tv_loading.setText("加载中.");
                    break;
                case 2:
                    tv_loading.setText("加载中..");
                    break;
                case 3:
                    tv_loading.setText("加载中...");
                    break;
            }
        }
    };

    public void setProgress(int progress) {
        int color = Integer.parseInt("709E2D", 16);//tag.getCoupon_color().substring(1)  FF5001  ratings.getTag_color().substring(1)
        color = 0xFF000000 + color;
//            viewHolder.pb.getProgressDrawable().setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
        LayerDrawable drawable = (LayerDrawable) progressBar.getProgressDrawable();
        drawable.getDrawable(1).setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
        progressBar.setProgress(progress);
        if (progress == 100) {
            this.dismiss();
        }
    }

}
