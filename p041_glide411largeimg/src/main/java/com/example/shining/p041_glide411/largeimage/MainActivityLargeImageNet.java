package com.example.shining.p041_glide411.largeimage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.example.shining.glide411library.progress.CircleProgressView;
import com.example.shining.p041_glide411.R;
import com.example.shining.p041_glide411.largeimage.glide411.LargeImgTarget;
import com.example.shining.p041_glide411.largeimage.glide411.OkHttpProgressGlideModule;
import com.shizhefei.view.largeimage.LargeImageView;

public class MainActivityLargeImageNet extends AppCompatActivity {

    private LargeImageView networkDemo_photoView;
    private CircleProgressView progressView2;
    //    String url = "http://short.im.rockhippo.cn/uploads/msg/201703/20170309/1485/1489068660846.jpg";
    String url = "https://s3.51cto.com/wyfs02/M02/06/ED/wKiom1nAst7gJXLWAApAOtlw0r4105.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_largeimage_net);
        networkDemo_photoView = (LargeImageView) findViewById(R.id.networkDemo_photoView);
        progressView2 = (CircleProgressView) findViewById(R.id.progressView2);

        final Glide glide = Glide.get(this);
        OkHttpProgressGlideModule a = new OkHttpProgressGlideModule();
        a.registerComponents(this, glide, glide.getRegistry());
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

        Glide.with(this).load(url).into(new LargeImgTarget(url, null) {
            @Override
            public void onLoadStarted(Drawable placeholder) {
                super.onLoadStarted(placeholder);
                progressView2.setVisibility(View.VISIBLE);
                progressView2.setProgress(0);
            }

            @Override
            public void onProgress(long bytesRead, long expectedLength) {
                int p = 0;
                if (expectedLength >= 0) {
                    p = (int) (100 * bytesRead / expectedLength);
                }
                progressView2.setProgress(p);
            }

            @Override
            public void onResourceReady(Object resource, Transition animation) {
                super.onResourceReady(resource, animation);
                progressView2.setVisibility(View.GONE);
//                largeImageView.setEnabled(false);
//                networkDemo_photoView.setImage((Bitmap) resource);
                networkDemo_photoView.setImage(drawable2Bitmap((Drawable) resource));
            }

            @Override
            public void getSize(SizeReadyCallback cb) {
                cb.onSizeReady(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
            }
        });

//        Glide.with(this).load(url).into(new Target<Drawable>() {
//            @Override
//            public void onLoadStarted(@Nullable Drawable placeholder) {
//                progressView2.setVisibility(View.VISIBLE);
//                progressView2.setProgress(0);
//            }
//
//            @Override
//            public void onLoadFailed(@Nullable Drawable errorDrawable) {
////                int p = 0;
////                if (expectedLength >= 0) {
////                    p = (int) (100 * bytesRead / expectedLength);
////                }
////                progressView2.setProgress(p);
//            }
//
//            @Override
//            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
//                progressView2.setVisibility(View.GONE);
////                largeImageView.setEnabled(false);
//                networkDemo_photoView.setImage(resource);
//            }
//
//            @Override
//            public void onLoadCleared(@Nullable Drawable placeholder) {
//
//            }
//
//            @Override
//            public void getSize(SizeReadyCallback cb) {
//                cb.onSizeReady(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
//            }
//
//            @Override
//            public void removeCallback(SizeReadyCallback cb) {
//
//            }
//
//            @Override
//            public void setRequest(@Nullable Request request) {
//
//            }
//
//            @Nullable
//            @Override
//            public Request getRequest() {
//                return null;
//            }
//
//            @Override
//            public void onStart() {
//
//            }
//
//            @Override
//            public void onStop() {
//
//            }
//
//            @Override
//            public void onDestroy() {
//
//            }
//        });

//        Glide.with(this).load(url).downloadOnly(new ProgressTarget<String, File>(url,null) {
//            @Override
//            public void onLoadStarted(Drawable placeholder) {
//                super.onLoadStarted(placeholder);
//                progressView2.setVisibility(View.VISIBLE);
//                progressView2.setProgress(0);
//            }
//
//            @Override
//            public void onProgress(long bytesRead, long expectedLength) {
//                int p = 0;
//                if (expectedLength >= 0) {
//                    p = (int) (100 * bytesRead / expectedLength);
//                }
//                progressView2.setProgress(p);
//            }
//
//            @Override
//            public void onResourceReady(File resource, Transition<? super File> animation) {
//                super.onResourceReady(resource, animation);
//                progressView2.setVisibility(View.GONE);
////                largeImageView.setEnabled(false);
//                networkDemo_photoView.setImage(new FileBitmapDecoderFactory(resource));
//            }
//
//            @Override
//            public void getSize(SizeReadyCallback cb) {
//                cb.onSizeReady(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
//            }
//        });
//    }

    }

    private Bitmap drawable2Bitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof NinePatchDrawable) {
            Bitmap bitmap = Bitmap
                    .createBitmap(
                            drawable.getIntrinsicWidth(),
                            drawable.getIntrinsicHeight(),
                            drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                    : Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight());
            drawable.draw(canvas);
            return bitmap;
        } else {
            return null;
        }
    }
}
