package com.example.p029_banner_lunbo.bannerutils.yuan;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.example.p029_banner_lunbo.R;

import java.lang.ref.WeakReference;


public class BannerViewYuan extends FrameLayout {
    private static final int MSG_RUN = 1;

    private ViewPager mViewPager;
    /**
     * 导航的小点
     */
    private DotIndicatorViewYuan mIndicator;

    private Adapter mAdapter;

    /**
     * 是否处于滑动的状态
     */
    private boolean isScrolling;

    private int mIndicatorPosition; // center_hori  left
    /**
     * 是否显示indicator
     */
    private boolean isShowIndicator = true;

    private boolean isTouch;

    private Handler mHandler;

    private BannerViewHelperYuan mHelper;

    private int mScrollTime;

    public BannerViewYuan(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerViewYuan(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BannerView,
                defStyle, 0);
        mIndicatorPosition = ta.getInt(R.styleable.BannerView_indicator_position, 0);
        isShowIndicator = ta.getBoolean(R.styleable.BannerView_indicator_visible, true);
        mScrollTime = ta.getInteger(R.styleable.BannerView_scroll_time, 3000);
        isScrolling = ta.getBoolean(R.styleable.BannerView_auto_start, false);
        ta.recycle();
        initView(context);
    }

    private void initView(Context context) {
        mViewPager = new ViewPager(context);
        mViewPager.addOnPageChangeListener(mOnPageChangeListener);
        addView(mViewPager);//, viewPagerParams);

        mHandler = new H(mViewPager);
        mIndicator = new DotIndicatorViewYuan(context);
        LayoutParams indicatorParams =
                new LayoutParams(LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT);
        if (mIndicatorPosition == 0) {
            indicatorParams.gravity = Gravity.BOTTOM | Gravity.LEFT;
        } else if (mIndicatorPosition == 1) {
            indicatorParams.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        } else if (mIndicatorPosition == 2) {
            indicatorParams.gravity = Gravity.BOTTOM | Gravity.RIGHT;
        }
        indicatorParams.bottomMargin = 20;

        if (!isShowIndicator) {
            return;
        }
        addView(mIndicator, indicatorParams);

    }

    @Override
    protected void onAttachedToWindow() {
        if (isScrolling) {
            startScroll();
        }
        super.onAttachedToWindow();
        mHelper = new BannerViewHelperYuan(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mHelper != null) {
            mHelper.resolveTouchConflict(ev);
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected android.view.ViewGroup.LayoutParams generateLayoutParams(
            android.view.ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    @Override
    protected boolean checkLayoutParams(android.view.ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    public int pos = 0;
    private OnPageChangeListener mOnPageChangeListener = new OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            int relCount = mAdapter.getRealCount();

            if (relCount <= 0) {
                pos = 0;
                mIndicator.current(0);
            } else {
                pos = position % relCount;
                mIndicator.current(position % relCount);
            }
            //
            if (mOnBannerChangeListener != null) {
                mOnBannerChangeListener.onPageSelected(pos);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            //
            if (mOnBannerChangeListener != null) {
                mOnBannerChangeListener.onPageScrolled(pos);
            }
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
            isTouch = arg0 == ViewPager.SCROLL_STATE_DRAGGING;
            if (arg0 == ViewPager.SCROLL_STATE_IDLE && isScrolling) {
                mHandler.removeMessages(MSG_RUN);
                Message msg = mHandler.obtainMessage(MSG_RUN, isScrolling ? 1 : 0, isTouch ? 1 : 0);
                mHandler.sendMessageDelayed(msg, mScrollTime);
            }
            //
            if (mOnBannerChangeListener != null) {
                mOnBannerChangeListener.onPageScrollStateChanged(pos);
            }

        }
    };

    public interface OnBannerChangeListener {
        void onPageSelected(int pos);

        void onPageScrolled(int pos);

        void onPageScrollStateChanged(int pos);

    }

    public OnBannerChangeListener mOnBannerChangeListener;

    public OnBannerChangeListener getmOnBannerChangeListener() {
        return mOnBannerChangeListener;
    }

    public void setmOnBannerChangeListener(OnBannerChangeListener mOnBannerChangeListener) {
        this.mOnBannerChangeListener = mOnBannerChangeListener;
    }

    /**
     * 开始自动滚动
     */
    public void startScroll() {
        mHandler.removeMessages(MSG_RUN);
        isScrolling = true;
        Message msg = mHandler.obtainMessage(MSG_RUN, 1, isTouch ? 1 : 0);
        mHandler.sendMessageDelayed(msg, mScrollTime);
    }

    /**
     * 停止自动滚动
     */
    public void stopScroll() {
        isScrolling = false;
        if (mHandler != null) {
            mHandler.removeMessages(MSG_RUN);
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("scroll", isScrolling);
        bundle.putParcelable("state", super.onSaveInstanceState());
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        Bundle bundle = (Bundle) state;
        isScrolling = bundle.getBoolean("scroll");
        state = bundle.getParcelable("state");
        if (state != null) {
            super.onRestoreInstanceState(state);
        }
    }

//	@Override
//	protected void onAttachedToWindow() {
//		super.onAttachedToWindow();
//		if(isScrolling) mHandler.sendEmptyMessage(MSG_RUN);
//	}

    @Override
    protected void onDetachedFromWindow() {
        mHandler.removeMessages(MSG_RUN);
        super.onDetachedFromWindow();
    }

    /**
     * 设置banner的数据集合
     */
    public void setAdapter(Adapter adapter) {
        mAdapter = adapter;
        mViewPager.setAdapter(adapter);
        mIndicator.create(adapter.getRealCount());
    }

    /**
     * 获取adapter
     */
    public Adapter getAdapter() {
        return mAdapter;
    }

    public int getCurrent() {
        return mViewPager.getCurrentItem();
    }

    public void setCurrent(int current) {
        mViewPager.setCurrentItem(current);
    }

    private static class H extends Handler {
        private WeakReference<ViewPager> mViewPager;

        public H(ViewPager pager) {
            mViewPager = new WeakReference<>(pager);
        }

        public void handleMessage(Message msg) {
            if (mViewPager.get() == null) return;

            boolean isScrolling = msg.arg1 == 1;
            boolean isTouch = msg.arg2 == 1;

            if (msg.what == MSG_RUN && isScrolling) {
                if (!isTouch) {
                    int pos = mViewPager.get().getCurrentItem() + 1;
                    mViewPager.get().setCurrentItem(pos);
//					sendEmptyMessageDelayed(MSG_RUN, 3000);
                } else {
                    removeMessages(MSG_RUN);
                }
            }
        }
    }

    public static abstract class Adapter extends PagerAdapter {
        @Override
        public final int getCount() {
            return Integer.MAX_VALUE;
        }

        /**
         * 获取真实的数据统计
         *
         * @return
         */
        public abstract int getRealCount();

        @Override
        public final boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        public Object getItem(int position) {
            return position;
        }
    }
}
