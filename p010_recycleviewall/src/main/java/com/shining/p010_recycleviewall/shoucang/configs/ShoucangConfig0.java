package com.shining.p010_recycleviewall.shoucang.configs;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v4.util.SparseArrayCompat;
import android.text.TextUtils;

import com.shining.p010_recycleviewall.application.DemoApplication;
import com.shining.p010_recycleviewall.shoucang.basefragment.BaseFragment;
import com.shining.p010_recycleviewall.utils.MyLogUtil;

/**
 * <p>function: </p>
 * <p>description:  </p>
 * <p>history:  1. 2017/3/23</p>
 * <p>Author: qibin</p>
 * <p>modification:</p>
 */
public class ShoucangConfig0 {

    private static final String INDEX_META_DATA = "SHOUCANG_CONFIG0";

    /** viewpager页大小*/
//    public static int PAGE_COUNT;
    /** viewpager每页的itemview id*/
//    public static String PAGE_ID;

    /** 默认显示第几页*/
//    public static int DEFAULT_PAGE_INDEX;

    /** fragment配置*/
    private static SparseArrayCompat<Class<? extends BaseFragment>> sIndexFragments = new SparseArrayCompat<>();

    public static void config() {
        Context ctx = DemoApplication.get();
        ApplicationInfo info = null;

        try {
            info = ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        if (info == null) {
            throw new UnsupportedOperationException();
        }

        String klassName = info.metaData.getString(INDEX_META_DATA);
        if (TextUtils.isEmpty(klassName)) {
            throw new UnsupportedOperationException("please config " + INDEX_META_DATA + " value");
        }

        if (klassName.startsWith(".")) {
            klassName = DemoApplication.get().getPackageName() + klassName;
        }

        MyLogUtil.d("geek", klassName);

        try {
            Class<?> klass = Class.forName(klassName);
            klass.getDeclaredMethod("setup").invoke(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Class<? extends BaseFragment> getFragment(int id) {
        if (sIndexFragments.indexOfKey(id) < 0) {
            throw new UnsupportedOperationException("cannot find fragment by " + id);
        }
        return sIndexFragments.get(id);
    }

    public static SparseArrayCompat<Class<? extends BaseFragment>> getFragments() {
        return sIndexFragments;
    }
}
