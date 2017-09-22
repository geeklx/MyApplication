package com.example.shining.p009_glide411.glide.options;

import android.content.res.Resources;

import com.example.shining.p009_glide411.R;

import java.util.HashMap;

public class GlideOptionsFactory {
    private static HashMap<Type, GlideOptions> mOptions;

    private GlideOptionsFactory() {
    }

    private static void init() {
        if (mOptions == null) {
            mOptions = new HashMap<>();
            mOptions.put(Type.DEFAULT, new GlideOptions(R.drawable.ic_def_loading, 0));
            mOptions.put(Type.RADIUS, new GlideOptions(R.drawable.ic_def_loading, dip2px(6)));
        }
    }

    public static GlideOptions get(Type type) {
        init();
        if (mOptions.containsKey(type)) {
            return mOptions.get(type);
        }

        throw new IllegalArgumentException();
    }

    public enum Type {
        DEFAULT(1), RADIUS(2);
        private int type;

        private Type(int type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "type:" + type;
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
