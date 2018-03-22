package com.example.p007_emptyview.widget20;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

public class EmptyViewHelper {
    @SuppressWarnings("unchecked")
    public static <T extends View> T f(Activity activity, @IdRes int resId) {
        return (T) activity.findViewById(resId);
    }

    @SuppressWarnings("unchecked")
    public static <T extends View> T f(View rootView, @IdRes int resId) {
        return (T) rootView.findViewById(resId);
    }

    public static void click(View.OnClickListener li, View... views) {
        if (views == null || views.length == 0) return;
        for (View v : views) {
            v.setOnClickListener(li);
        }
    }

    public static void setRefreshColor(SwipeRefreshLayout swipe) {
        swipe.setColorSchemeColors(0xff33b5e5, 0xffff4444, 0xffffbb33, 0xff99cc00);
    }

    public static void setVisable(Boolean is, View... views) {
        if (views == null || views.length == 0) return;
        for (View v : views) {
            if (is) v.setVisibility(View.VISIBLE);
            else v.setVisibility(View.GONE);
        }
    }
}

