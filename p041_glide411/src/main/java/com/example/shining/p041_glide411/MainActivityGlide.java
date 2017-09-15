package com.example.shining.p041_glide411;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.shining.glide411library.progress.CircleProgressView;
import com.example.shining.glide411library.progress.OnGlideImageViewListener;
import com.example.shining.glide411library.progress.OnProgressListener;
import com.example.shining.glide411library.view.GlideImageLoader;
import com.example.shining.glide411library.view.GlideImageView;
import com.example.shining.glide411library.view.ShapeImageView;
import com.example.shining.p041_glide411.image.SingleImageActivity;

import java.util.Random;

import static com.example.shining.p041_glide411.image.SingleImageActivity.KEY_IMAGE_URL;
import static com.example.shining.p041_glide411.image.SingleImageActivity.KEY_IMAGE_URL_THUMBNAIL;


public class MainActivityGlide extends AppCompatActivity {

    private GlideImageView image11;
    private GlideImageView image12;
    private GlideImageView image13;
    private GlideImageView image14;
    private String url1 = "https://s3.51cto.com/wyfs02/M01/89/BA/wKioL1ga-u7QnnVnAAAfrCiGnBQ946_middle.jpg";

    private GlideImageView image21;
    private GlideImageView image22;
    private GlideImageView image23;
    private GlideImageView image24;

    private GlideImageView image31;
    private GlideImageView image32;
    private GlideImageView image33;
    private GlideImageView image34;
    private String gif1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1505394298896&di=98e0e804231a282ef80360e94fa7dca6&imgtype=0&src=http%3A%2F%2Fimg.qqai.net%2Fuploads%2Fi_3_2854471891x1596414192_21.jpg";

    private GlideImageView image41;
    private CircleProgressView progressView1;
    private String image41BigUrl = "https://raw.githubusercontent.com/geeklx/MyApplication/master/p040_glide4.0/screenshot/1232.png";
    private String image41SmallUrl = "https://raw.githubusercontent.com/geeklx/MyApplication/master/p040_glide4.0/screenshot/1231.png";

    private GlideImageView image42;
    private CircleProgressView progressView2;
    private String image42BigUrl = "https://raw.githubusercontent.com/geeklx/MyApplication/master/p040_glide4.0/screenshot/1234.png";
    private String image42SmallUrl = "https://raw.githubusercontent.com/geeklx/MyApplication/master/p040_glide4.0/screenshot/1233.png";

    public static boolean isLoadAgain = false; // Just for fun when loading images!


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_glide);

        //Glide
        findview();

        isLoadAgain = new Random().nextInt(3) == 1;

        line1();
        line2();
        line3();
        line41();
        line42();
    }

    private void findview() {
        image11 = (GlideImageView) findViewById(R.id.image11);
        image12 = (GlideImageView) findViewById(R.id.image12);
        image13 = (GlideImageView) findViewById(R.id.image13);
        image14 = (GlideImageView) findViewById(R.id.image14);

        image21 = (GlideImageView) findViewById(R.id.image21);
        image22 = (GlideImageView) findViewById(R.id.image22);
        image23 = (GlideImageView) findViewById(R.id.image23);
        image24 = (GlideImageView) findViewById(R.id.image24);

        image31 = (GlideImageView) findViewById(R.id.image31);
        image32 = (GlideImageView) findViewById(R.id.image32);
        image33 = (GlideImageView) findViewById(R.id.image33);
        image34 = (GlideImageView) findViewById(R.id.image34);

        image41 = (GlideImageView) findViewById(R.id.image41);
        progressView1 = (CircleProgressView) findViewById(R.id.progressView1);
        image42 = (GlideImageView) findViewById(R.id.image42);
        progressView2 = (CircleProgressView) findViewById(R.id.progressView2);
    }

    private void line41() {
        image41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityGlide.this, SingleImageActivity.class);
                intent.putExtra(KEY_IMAGE_URL, image41BigUrl);
                intent.putExtra(KEY_IMAGE_URL_THUMBNAIL, image41SmallUrl);
                ActivityOptionsCompat compat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(MainActivityGlide.this, image41, getString(R.string.transitional_image));
                ActivityCompat.startActivity(MainActivityGlide.this, intent, compat.toBundle());
            }
        });

        RequestOptions requestOptions = image41.requestOptions(R.color.placeholder_color).centerCrop();
        if (isLoadAgain) {
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true);
        }

        // 第一种方式加载
        image41.load(image41SmallUrl, requestOptions).listener(new OnGlideImageViewListener() {
            @Override
            public void onProgress(int percent, boolean isDone, GlideException exception) {
                if (exception != null && !TextUtils.isEmpty(exception.getMessage())) {
                    Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                }
                progressView1.setProgress(percent);
                progressView1.setVisibility(isDone ? View.GONE : View.VISIBLE);
            }
        });
    }

    private void line42() {
        image42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityGlide.this, SingleImageActivity.class);
                intent.putExtra(KEY_IMAGE_URL, image42BigUrl);
                intent.putExtra(KEY_IMAGE_URL_THUMBNAIL, image42BigUrl);
                ActivityOptionsCompat compat = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(MainActivityGlide.this, image42, getString(R.string.transitional_image));
                ActivityCompat.startActivity(MainActivityGlide.this, intent, compat.toBundle());
            }
        });

        RequestOptions requestOptions = image42.requestOptions(R.color.placeholder_color).centerCrop();
        if (isLoadAgain) {
            requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true);
        }

        // 第二种方式加载：可以解锁更多功能
        GlideImageLoader imageLoader = image42.getImageLoader();
        imageLoader.setOnGlideImageViewListener(image42SmallUrl, new OnGlideImageViewListener() {
            @Override
            public void onProgress(int percent, boolean isDone, GlideException exception) {
                if (exception != null && !TextUtils.isEmpty(exception.getMessage())) {
                    Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                }
                progressView2.setProgress(percent);
                progressView2.setVisibility(isDone ? View.GONE : View.VISIBLE);
            }
        });
        imageLoader.requestBuilder(image42SmallUrl, requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(image42);
    }

    private void line3() {
        image31.loadLocalImage(R.drawable.gif_robot_walk, R.drawable.ic_def_loading);

        image32.loadCircleImage(gif1, R.mipmap.ic_launcher).listener(new OnGlideImageViewListener() {
            @Override
            public void onProgress(int percent, boolean isDone, GlideException exception) {
                Log.d("--->image32", "percent: " + percent + " isDone: " + isDone);
            }
        });

        image33.loadImage(gif1, R.drawable.ic_def_loading);
        image34.loadImage(gif1, R.drawable.ic_def_loading);
    }

    private void line2() {
        image21.loadImage(url1, R.drawable.ic_def_loading);
        image22.loadImage("", R.drawable.ic_def_loading);
        image23.loadImage(url1, R.color.placeholder_color);
        image24.loadImage(url1, R.color.placeholder_color);
    }

    private void line1() {
        image11.loadImage(url1, R.color.black).listener(new OnProgressListener() {
            @Override
            public void onProgress(String imageUrl, long bytesRead, long totalBytes, boolean isDone, GlideException exception) {
                Log.d("--->image11", "bytesRead: " + bytesRead + " totalBytes: " + totalBytes + " isDone: " + isDone);
            }
        });
        image12.setShapeType(ShapeImageView.ShapeType.CIRCLE);
        image12.setBorderWidth(3);
        image12.setBorderColor(R.color.transparent20);
        image12.loadCircleImage(url1, R.color.black);
        image12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivityGlide.this, "image12", Toast.LENGTH_SHORT).show();
            }
        });

        image13.setShapeType(ShapeImageView.ShapeType.RECTANGLE);
        image13.setRadius(15);
        image13.setBorderWidth(3);
        image13.setBorderColor(R.color.blue);
        image13.setPressedAlpha(0.3f);
        image13.setPressedColor(R.color.blue);
        image13.loadImage(url1, R.color.placeholder_color);

        image14.setShapeType(ShapeImageView.ShapeType.CIRCLE);
        image14.setBorderWidth(3);
        image14.setBorderColor(R.color.blue);
        image14.setPressedAlpha(0.2f);
        image14.setPressedColor(R.color.black);
        image14.loadImage(url1, R.color.placeholder_color);

    }


}
