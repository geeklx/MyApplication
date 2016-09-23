package com.example.p006_activity_fragment.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qibin on 2016/8/22.
 */

public class IndexViewPager extends ViewPager {

    private List<View> mBindViews;
    private View mCaptureView;
    private boolean isInAction;

    public IndexViewPager(Context context) {
        super(context);
    }

    public IndexViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void bind(View view) {
        if (!hasBindViews()) { mBindViews = new ArrayList<>();}
        if (mBindViews.contains(view)) { return;}
        mBindViews.add(view);
    }

    public void remove(View view){
        if (!hasBindViews()){ return;}
        if (mCaptureView != null && mCaptureView == view) { mCaptureView = null;}
        mBindViews.remove(view);
    }

    /**
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (!hasBindViews()) { return super.dispatchTouchEvent(event);}
                if (mCaptureView != null) { return dispatchEventToTarget(event, mCaptureView);}
                if (dispatchEventToTargets(event)) {
                    isInAction = true;
                    requestDisallowInterceptTouchEvent(true);
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (hasBindViews() && isInAction) {
                    isInAction = false;
                    requestDisallowInterceptTouchEvent(false);
                    if (mCaptureView != null) {
                        MotionEvent ev = MotionEvent.obtain(event);
                        mCaptureView.dispatchTouchEvent(ev);
                        mCaptureView = null;
                    }
                }
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isInAction) { return false;}
        return super.onTouchEvent(ev);
    }
     */

    @Override
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        if (!hasBindViews()) { return super.canScroll(v, checkV, dx, x, y);}
        for (View item : mBindViews) {
            MotionEvent event = MotionEvent.obtain(0, 0, MotionEvent.ACTION_DOWN, x, y, 0);
            if (isViewInEvent(item, event)) { return true;}
        }
        return super.canScroll(v, checkV, dx, x, y);
    }

    private boolean hasBindViews() {
        return mBindViews != null && !mBindViews.isEmpty();
    }

   /* private boolean dispatchEventToTargets(MotionEvent event) {
        List<View> views = mBindViews;
        for (View view : views) {
            if (dispatchEventToTarget(event, view)) { return true;}
        }

        return false;
    }

    private boolean dispatchEventToTarget(MotionEvent event, View view) {
        boolean result = false;
        int offsetX = 0;
        int offsetY = 0;

        View current = view;
        View parent;
        for (;current != this;current = parent) {
            parent = getViewParent(current);
            if (parent == null) { return false;}
            offsetX += parent.getScrollX() - current.getLeft();
            offsetY += parent.getScrollY() - current.getTop();
        }

        event.offsetLocation(offsetX, offsetY);
        if (isViewInEvent(view, event)) {
             result = view.dispatchTouchEvent(event);
        }
        event.offsetLocation(-offsetX, -offsetY);
        if (result) { mCaptureView = view;}
        return result;
    }*/

    private boolean isViewInEvent(View view, MotionEvent event) {
        boolean result;

        int offsetX = 0;
        int offsetY = 0;
        View current = view;
        View parent;
        for (;current != this;current = parent) {
            parent = getViewParent(current);
            if (parent == null) { return false;}
            offsetX += parent.getScrollX() - current.getLeft();
            offsetY += parent.getScrollY() - current.getTop();
        }

        event.offsetLocation(offsetX, offsetY);
        Rect hitRect = new Rect();
        view.getHitRect(hitRect);
        result = hitRect.contains((int) event.getX(), (int) event.getY());
        event.offsetLocation(-offsetX, -offsetY);
        return result;
    }

    private ViewGroup getViewParent(View view) {
        return (ViewGroup) view.getParent();
    }
}
