package com.example.shining.p045_butterknifegradle300.glideutil.options;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.shining.p045_butterknifegradle300.applications.DemoApplication;

public class GlideUtil {

    public static void display(Context context, final ImageView imageView, final String url) {
        display(context, imageView, url, GlideOptionsFactory.get(GlideOptionsFactory.Type.DEFAULT));
    }

    public static void display(Context context, final ImageView imageView, final String url, GlideOptions op) {
        if (context == null) {
            return;
        }
        if (context instanceof AppCompatActivity) {
            display((AppCompatActivity) context, imageView, url, op);
            return;
        }

        RequestBuilder<?> builder = Glide.with(context).load(url);
        display(builder, imageView, op);
    }

    public static void display(AppCompatActivity activity, final ImageView imageView, final String url) {
        display(activity, imageView, url, GlideOptionsFactory.get(GlideOptionsFactory.Type.DEFAULT));
    }

    public static void display(AppCompatActivity activity, final ImageView imageView, final String url, GlideOptions op) {
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        RequestBuilder<?> builder = Glide.with(activity).load(url);
        display(builder, imageView, op);
    }

    public static void display(Fragment fragment, final ImageView imageView, final String url) {
        display(fragment, imageView, url, GlideOptionsFactory.get(GlideOptionsFactory.Type.DEFAULT));
    }

    public static void display(Fragment fragment, final ImageView imageView, final String url, GlideOptions op) {
        if (fragment == null || fragment.isRemoving() || fragment.getActivity() == null) {
            return;
        }
        RequestBuilder<?> builder = Glide.with(fragment).load(url);
        display(builder, imageView, op);
    }

    /**
     * diskCacheStrategy(DiskCacheStrategy strategy).
     * <p>
     * glide3.7设置缓存策略。
     * DiskCacheStrategy.SOURCE：缓存原始数据，
     * DiskCacheStrategy.RESULT：缓存变换(如缩放、裁剪等)后的资源数据，
     * DiskCacheStrategy.NONE：什么都不缓存，
     * DiskCacheStrategy.ALL：缓存SOURC和RESULT。
     * 默认采用DiskCacheStrategy.RESULT策略，
     * 对于download only操作要使用DiskCacheStrategy.SOURCE。
     * <p>
     * DiskCacheStrategy有五个常量：
     * DiskCacheStrategy.ALL 使用DATA和RESOURCE缓存远程数据，仅使用RESOURCE来缓存本地数据。
     * DiskCacheStrategy.NONE 不使用磁盘缓存
     * DiskCacheStrategy.DATA 在资源解码前就将原始数据写入磁盘缓存
     * DiskCacheStrategy.RESOURCE 在资源解码后将数据写入磁盘缓存，即经过缩放等转换后的图片资源。
     * DiskCacheStrategy.AUTOMATIC 根据原始图片数据和资源编码策略来自动选择磁盘缓存策略。
     *
     * @param builder
     * @param imageView
     * @param op
     */
    public static void display(final RequestBuilder<?> builder, final ImageView imageView, GlideOptions op) {
        final RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(op.getReplaceImage())
                .error(op.getReplaceImage())
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .priority(Priority.HIGH)
                .transform(new RoundedCornersTransformation(imageView.getContext(), op.getRadius(), 0));

        ViewGroup.LayoutParams p = imageView.getLayoutParams();
        int width = imageView.getMeasuredWidth();
        int height = imageView.getMeasuredHeight();
        if (p != null && p.width > 0 && p.height > 0) {
            width = p.width;
            height = p.height;
        }
        if (width == 0 || height == 0) {
            imageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    imageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    Log.v("glide", "delay display...");
                    builder.apply(options).into(imageView);
                }
            });
        } else {
            Log.v("glide", "display...");
            builder.apply(options).into(imageView);
        }
    }

    public static void clearMemoryCache() {
        Glide.get(DemoApplication.get()).clearMemory();
    }

    public static void clearDiskCache() {
        Glide.get(DemoApplication.get()).clearDiskCache();
    }
}
