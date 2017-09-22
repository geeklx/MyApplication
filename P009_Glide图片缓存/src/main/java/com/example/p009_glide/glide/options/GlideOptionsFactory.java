package com.example.p009_glide.glide.options;



import com.example.p009_glide.util.DeviceUtil;
import com.example.p009_glide.R;

import java.util.HashMap;

/**
 * Created by geek on 2016/7/28.
 */

public class GlideOptionsFactory {
    private static HashMap<Type, GlideOptions> mOptions;

    private GlideOptionsFactory() {}

    private static void init() {
        if (mOptions == null) {
            mOptions = new HashMap<>();
            mOptions.put(Type.DEFAULT, new GlideOptions(R.drawable.ic_def_loading, 0));
            mOptions.put(Type.RADIUS, new GlideOptions(R.drawable.ic_def_loading, DeviceUtil.dip2px(10)));
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
        DEFAULT (1), RADIUS (2);
        private int type;

        private Type(int type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "type:" + type;
        }
    }
}
