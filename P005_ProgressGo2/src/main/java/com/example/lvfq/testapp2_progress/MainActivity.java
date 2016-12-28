package com.example.lvfq.testapp2_progress;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {


    private CustomDialog customDialog;
    private TextView tv_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customDialog = new CustomDialog(this);
        tv_1 = (TextView) findViewById(R.id.tv_1);
        tv_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        MainActivity2.class);
                startActivity(intent);
            }
        });
    }

    private int count = 0;

    public void tvClick(View view) {
        customDialog.show();
        customDialog.setProgress(70);
//        final Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                count += 10;
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (customDialog != null && customDialog.isShowing()) {
//                            customDialog.setProgress(count);
//                        }
//                    }
//                });
//                if (count >= 100) {
//                    timer.cancel();
//                }
//            }
//        }, 0, 500);
//        customDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//            @Override
//            public void onDismiss(DialogInterface dialog) {
//                if (timer != null) timer.cancel();
//                count = 0;
//            }
//        });

    }

}
