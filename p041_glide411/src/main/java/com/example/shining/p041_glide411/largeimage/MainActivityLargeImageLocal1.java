package com.example.shining.p041_glide411.largeimage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shining.p041_glide411.R;
import com.shizhefei.view.largeimage.LargeImageView;

public class MainActivityLargeImageLocal1 extends AppCompatActivity {

    private LargeImageView localDemo_photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_largeimage_local);
        localDemo_photoView = (LargeImageView) findViewById(R.id.localDemo_photoView);
        localDemo_photoView.setImage(getResources().getDrawable(R.drawable.qm3));
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                localDemo_photoView.setScale(0.5f);
            }
        });

    }

}
