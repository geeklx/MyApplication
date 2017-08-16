/**
 * Copyright  2015,  Smart  Haier
 * All  rights  reserved.
 * Description:  没有滑屏效果的ViewPager
 * Author:  geyanyan
 * Date:  2016/11/18
 * FileName:  TabFragmentAdapter.java
 * History:
 * 1.  Date:2016/11/21 21:45
 * Author:geyanyan
 * Modification:
 */
package com.example.p031_mokuaihua_viewpager_fragment.demo3.utils;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


public class NoScrollViewPager extends ViewPager {
    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
