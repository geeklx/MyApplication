package com.shining.p010_recycleviewall.tablayout.fragmentframelayout.mvp;


import com.shining.p010_recycleviewall.net.Net;

import java.lang.ref.WeakReference;


/**
 * presenter基类 <br />
 * created by shining
 */

public class Presenter<T extends IView>{
    /**
     * 保存view
     */
    private WeakReference<T> mView;

    /**
     * 在view创建的时候调用
     *
     * @param view
     */
    public void onCreate(T view) {
        mView = new WeakReference<>(view);
    }

    /**
     * 在view销毁的时候调用
     */
    public void onDestory() {
        if (hasView()) {
            mView.clear();
        }
        mView = null;
        //自定义自己的网络请求断开连接bufen
        Net.getInstance().get().cancel(getClass().getName());
    }

    /**
     * 获取view
     *
     * @return
     */
    protected T getView() {
        if (hasView()) {
            return mView.get();
        }

        return null;
    }

    /**
     * 判断view时候为空
     *
     * @return
     */
    protected boolean hasView() {
        return mView != null && mView.get() != null;
    }
}
