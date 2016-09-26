package com.example.p011_swipebacklayout.geekshining.lib.app;


import com.example.p011_swipebacklayout.geekshining.lib.SwipeBackLayout;

/**
 * @author geek
 */
public interface SwipeBackActivityBase {
    /**
     * @return the SwipeBackLayout associated with this activity.
     */
    public abstract SwipeBackLayout getSwipeBackLayout();

    public abstract void setSwipeBackEnable(boolean enable);

    /**
     * Scroll out contentView and finish the activity
     */
    public abstract void scrollToFinishActivity();

}
