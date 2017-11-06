package com.github.geeklxbanner.holder;

import android.content.Context;
import android.view.View;

public interface LXViewHolder<T> {
    /**
     * 创建View
     *
     * @param context
     * @return
     */
    View createView(Context context);

    /**
     * 绑定数据
     *
     * @param context
     * @param position
     * @param data
     */
    void onBind(Context context, int position, T data);
}
