package com.example.shining.p042_largeimage.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.example.shining.p042_largeimage.R;
import com.example.shining.p042_largeimage.glide37.OkHttpProgressGlideModule;
import com.example.shining.p042_largeimage.glide37.ProgressTarget;
import com.shizhefei.view.largeimage.LargeImageView;
import com.shizhefei.view.largeimage.factory.FileBitmapDecoderFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;

public class MainActivityLargeImageGlide extends AppCompatActivity {

    private LargeImageView largeImageView;
    private RingProgressBar ringProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_large_image_view);

        largeImageView = (LargeImageView) findViewById(R.id.networkDemo_photoView);
        ringProgressBar = (RingProgressBar) findViewById(R.id.networkDemo_ringProgressBar);
//        String url = "http://short.im.rockhippo.cn/uploads/msg/201703/20170309/1485/1489068660846.jpg";
//        URL fileUrl = null;
//        File file = null;
//        try {
//            fileUrl = new URL(url);
//            file = new File(fileUrl.toURI());
//        } catch (MalformedURLException | URISyntaxException e) {
//            e.printStackTrace();
//        }
//
//        assert file != null;
//        largeImageView.setImage(new FileBitmapDecoderFactory(file));

//        String url = "https://s2.51cto.com/wyfs02/M02/06/F4/wKiom1nA9iSRwF1BADe7ZVL2w4Q127.jpg";
//        String url = "https://s2.51cto.com/wyfs02/M00/06/F4/wKiom1nA-Aiy9qMkAAR3_qzZ1is031.jpg";
        String url = "https://s3.51cto.com/wyfs02/M00/A5/A5/wKioL1nA-WrQ8NSkAADpAlDnsrM054.jpg";
        final Glide glide = Glide.get(this);
        OkHttpProgressGlideModule a = new OkHttpProgressGlideModule();
        a.registerComponents(this, glide);
        new Thread() {
            @Override
            public void run() {
                super.run();
                Glide.get(getApplicationContext()).clearDiskCache();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(getApplicationContext(), "清除缓存成功", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }.start();
        Glide.with(this).load(url).downloadOnly(new ProgressTarget<String, File>(url, null) {
            @Override
            public void onLoadStarted(Drawable placeholder) {
                super.onLoadStarted(placeholder);
                ringProgressBar.setVisibility(View.VISIBLE);
                ringProgressBar.setProgress(0);
            }

            @Override
            public void onProgress(long bytesRead, long expectedLength) {
                int p = 0;
                if (expectedLength >= 0) {
                    p = (int) (100 * bytesRead / expectedLength);
                }
                ringProgressBar.setProgress(p);
            }

            @Override
            public void onResourceReady(File resource, GlideAnimation<? super File> animation) {
                super.onResourceReady(resource, animation);
                ringProgressBar.setVisibility(View.GONE);
//                largeImageView.setEnabled(false);
                largeImageView.setImage(new FileBitmapDecoderFactory(resource));
            }

            @Override
            public void getSize(SizeReadyCallback cb) {
                cb.onSizeReady(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
            }
        });

    }

    /**
     * 根据图片的url路径获得Bitmap对象
     *
     * @param url
     * @return
     */
    private Bitmap returnBitmap(String url) {
        URL fileUrl = null;
        Bitmap bitmap = null;
        try {
            fileUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection) fileUrl.openConnection();
            conn.setDoInput(true);
            conn.connect();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;

    }
}
