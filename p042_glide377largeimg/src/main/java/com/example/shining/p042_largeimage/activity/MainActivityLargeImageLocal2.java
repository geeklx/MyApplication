package com.example.shining.p042_largeimage.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shining.p042_largeimage.R;
import com.shizhefei.view.largeimage.BlockImageLoader;
import com.shizhefei.view.largeimage.LargeImageView;
import com.shizhefei.view.largeimage.factory.InputStreamBitmapDecoderFactory;

import java.io.IOException;
import java.io.InputStream;


public class MainActivityLargeImageLocal2 extends AppCompatActivity {

    private LargeImageView largeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_largeimage_local);
        largeImageView = (LargeImageView) findViewById(R.id.localDemo_photoView);

        try {
            String fileName = getIntent().getStringExtra("file_name");
            //通过文件的方式加载sd卡中的大图
//            localDemo_photoView.setImage(new FileBitmapDecoderFactory(file));
            //通过流的方式加载assets文件夹里面的大图
            InputStream inputStream = getAssets().open("qm.jpg");
            largeImageView.setImage(new InputStreamBitmapDecoderFactory(inputStream));
//            localDemo_photoView.setImage(new InputStreamBitmapDecoderFactory(inputStream), getResources().getDrawable(R.drawable.mvc));
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    largeImageView.setScale(0.5f);
                    largeImageView.setOnImageLoadListener(new BlockImageLoader.OnImageLoadListener() {
                        @Override
                        public void onBlockImageLoadFinished() {

                        }

                        @Override
                        public void onLoadImageSize(int imageWidth, int imageHeight) {
                            String a = imageHeight + "";
                        }

                        @Override
                        public void onLoadFail(Exception e) {

                        }
                    });

//                    largeImageView.setCriticalScaleValueHook(new LargeImageView.CriticalScaleValueHook() {
//                        @Override
//                        public float getMinScale(LargeImageView largeImageView, int imageWidth, int imageHeight, float suggestMinScale) {
//                            return 15;
//                        }
//
//                        @Override
//                        public float getMaxScale(LargeImageView largeImageView, int imageWidth, int imageHeight, float suggestMaxScale) {
//                            return 30;
//                        }
//                    });
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
