package com.example.p010_recycleviewall.net.glide;


import android.content.Context;

import com.example.p010_recycleviewall.utils.DeviceUtil;

import java.util.HashMap;

/**
 * Created by geek on 2016/7/28.
 */

public class GlideOptionsFactory {
    private static HashMap<Type, GlideOptions> mOptions;

    private GlideOptionsFactory() {}

    public static void init(Context ctx, int defLoading) {
        if(mOptions == null) {
            mOptions = new HashMap();
            mOptions.put(GlideOptionsFactory.Type.DEFAULT, new GlideOptions(defLoading, 0));
            mOptions.put(GlideOptionsFactory.Type.RADIUS, new GlideOptions(defLoading, DeviceUtil.dip2px(ctx, 10.0F)));
        }

    }

    public static GlideOptions get(GlideOptionsFactory.Type type) {
        if(mOptions.containsKey(type)) {
            return (GlideOptions)mOptions.get(type);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static enum Type {
        DEFAULT(1),
        RADIUS(2);

        private int type;

        private Type(int type) {
            this.type = type;
        }

        public String toString() {
            return "type:" + this.type;
        }
    }
}
