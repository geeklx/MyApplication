package com.example.shining.p046_progressbar;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private Thread thread;
    private ProgressBar progressBar;
    private boolean stateChange = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progress_horizontal);
        progressBar.setVisibility(View.VISIBLE);
//        setProgresss(70, "f25252");
        //动态进度条bufen
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                while(true){
                    int currentValue = progressBar.getProgress();
                    int currentMaxValue = progressBar.getMax();
                    int currentSecondaryValue = progressBar.getSecondaryProgress();
                    if(stateChange == false){
                        if(currentValue >= currentMaxValue){
                            stateChange = true;
                        }else{
                            progressBar.setProgress(currentValue + 1);
                            progressBar.setSecondaryProgress(currentValue+1);
                        }
                    }else{
                        if(currentValue <= 0){
                            stateChange = false;
                        }else{
//                            progressBar.setProgress(currentValue - 1);
                            thread=null;
                        }
                    }
                    try{
                        Thread.sleep(50);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();

    }

    public void setProgresss(int progress, String colors) {
        int color = Integer.parseInt(colors, 16);//tag.getCoupon_color().substring(1)  FF5001  ratings.getTag_color().substring(1)
        color = 0xFF000000 + color;
//            viewHolder.pb.getProgressDrawable().setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
        LayerDrawable drawable = (LayerDrawable) progressBar.getProgressDrawable();
        drawable.getDrawable(1).setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
        progressBar.setProgress(progress);
        if (progress == 100) {
//            this.dismiss();
        }
    }
}
