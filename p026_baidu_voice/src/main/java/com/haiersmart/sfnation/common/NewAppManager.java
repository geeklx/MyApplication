package com.haiersmart.sfnation.common;

import android.text.TextUtils;

import java.util.Stack;

/**
 * Created by qibin on 2016/7/21.
 */
public class NewAppManager {

    private Stack<AppActionActivity> mActivities = new Stack<AppActionActivity>();
    private static NewAppManager sInstance;

    public static NewAppManager getInstance() {
        if (sInstance == null) {
            sInstance = new NewAppManager();
        }
        return sInstance;
    }

    private NewAppManager() {
    }

    /**
     * 添加activity
     *
     * @param activity
     */
    public void add(AppActionActivity activity) {
        mActivities.add(activity);
    }


    /**
     * 移除activity
     *
     * @param activity
     */
    public void remove(AppActionActivity activity) {
        if (activity != null) {
            mActivities.remove(activity);
        }
    }


    /**
     * 获取指定activity
     *
     * @param klass
     * @return
     */
    public AppActionActivity get(String klass) {
        if (TextUtils.isEmpty(klass)) {
            return null;
        }
        for (AppActionActivity a : mActivities) {
            if (klass.equals(a.getClassName())) return a;
        }
        return null;
    }


    /* 获取栈顶的activity
     *
     * @return
     */
    public AppActionActivity top() {
        if (mActivities.isEmpty()) {
            return null;
        }
        return mActivities.peek();
    }

    public boolean isTop(String klassName) {
        AppActionActivity appActionActivity = top();
        if (appActionActivity == null) {
            return false;
        }
        if (appActionActivity.getClassName().equals(klassName)) {
            return true;
        }
        return false;
    }
}
