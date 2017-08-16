package com.example.p031_mokuaihua_viewpager_fragment.utils;

import android.view.View;
import android.view.ViewGroup;


/**
 * Created by geek on 2016/8/4.
 */

public class IMEUtil {
    public static View wrap(View contentView) {
        if (contentView.getParent() != null) {
            throw new UnsupportedOperationException("use HideIMEUtil.wrap instead");
        }

        ViewGroup.LayoutParams p = contentView.getLayoutParams();
        AutoHideInputMethodFrameLayout layout = new AutoHideInputMethodFrameLayout(contentView.getContext());
        layout.setLayoutParams(p);
        layout.addView(contentView);
        return layout;
    }
}
