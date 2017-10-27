package com.example.shining.p042_largeimage.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shining.p042_largeimage.R;
import com.shizhefei.view.largeimage.LargeImageView;

public class MainActivityLargeImageLocal1 extends AppCompatActivity {

    private LargeImageView localDemo_photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_largeimage_local);
        localDemo_photoView = (LargeImageView) findViewById(R.id.localDemo_photoView);

        String fileName = getIntent().getStringExtra("file_name");
        localDemo_photoView.setImage(getResources().getDrawable(R.drawable.qm3));
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                localDemo_photoView.setScale(0.5f);
            }
        });

    }

}
