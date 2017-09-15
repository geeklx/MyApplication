package com.example.shining.p040_glide40.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shining.p040_glide40.R;
import com.github.chrisbanes.photoview.PhotoView;

public class MainActivityPhotoView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_photoview);

        //PhotoView
        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
        photoView.setImageResource(R.drawable.img_avatar_01);


    }
}
