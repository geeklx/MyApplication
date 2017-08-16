package com.example.p031_mokuaihua_viewpager_fragment.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

/**
 * 点击非EditText区域自动隐藏软键盘
 * Created by geek on 2016/6/2.
 */

public class AutoHideInputMethodFrameLayout extends FrameLayout {

    public AutoHideInputMethodFrameLayout(Context context) {
        super(context);
    }

    public AutoHideInputMethodFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoHideInputMethodFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction() == MotionEvent.ACTION_DOWN) {
            Context context = getContext();
            if (context == null || !(context instanceof Activity)) {
                return super.dispatchTouchEvent(ev);
            }

            Activity activity = (Activity) context;
            View focusView = activity.getCurrentFocus();

            if (focusView != null && shouldHideInputMethod(focusView, ev)) {
                hideInputMethod(activity);
            }
        }

        return super.dispatchTouchEvent(ev);
    }

    private boolean shouldHideInputMethod(View focusView, MotionEvent event) {
        Rect rect = new Rect();
        focusView.getHitRect(rect);
        if(rect.contains((int)event.getX(), (int)event.getY())) {
            return false;
        }
        return true;
    }

    private void hideInputMethod(Activity activity) {
        if (activity.getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}