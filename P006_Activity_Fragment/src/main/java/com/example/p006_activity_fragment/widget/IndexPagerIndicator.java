package com.example.p006_activity_fragment.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.example.p006_activity_fragment.R;


/**
 * 首页viewpager指示符<br/>
 * Created by qibin on 2016/7/29.
 */

public class IndexPagerIndicator extends LinearLayout {

    private int mItemWidth;
    private int mItemHeight;
    private int mItemInterval;

    private int mItemColor;

    private Rect mSelectedRect;
    private Paint mSelectedPaint;
    private int mStartX;
    private int mCurrentX;

    public IndexPagerIndicator(Context context) {
        this(context, null, 0);
    }

    public IndexPagerIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndexPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundColor(Color.TRANSPARENT);
        setOrientation(LinearLayout.HORIZONTAL);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.IndexPagerIndicator, defStyleAttr, 0);
        mItemWidth = ta.getDimensionPixelSize(R.styleable.IndexPagerIndicator_indicator_width, 10);
        mItemHeight = ta.getDimensionPixelSize(R.styleable.IndexPagerIndicator_indicator_height, 2);
        mItemInterval = ta.getDimensionPixelSize(R.styleable.IndexPagerIndicator_indicator_interval, 5);
        mItemColor = ta.getColor(R.styleable.IndexPagerIndicator_indicator_item_color, Color.BLACK);
        int selectedColor = ta.getColor(R.styleable.IndexPagerIndicator_indicator_select_color, Color.WHITE);
        ta.recycle();

        mSelectedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSelectedPaint.setColor(selectedColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mStartX = (getMeasuredWidth() - (getChildCount() * mItemWidth
                + (getChildCount() - 1) * mItemInterval)) / 2;
        mCurrentX = mStartX;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.save();
        canvas.translate(mCurrentX, 0);
        canvas.drawRect(mSelectedRect, mSelectedPaint);
        canvas.restore();
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable p = super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        bundle.putInt("current", mCurrentX);
        bundle.putParcelable("state", p);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            mCurrentX = bundle.getInt("current");
            state = bundle.getParcelable("state");
        }
        super.onRestoreInstanceState(state);
    }

    public void setupWithViewPager(ViewPager pager) {
        if (pager.getAdapter() == null) {
            throw new UnsupportedOperationException("your viewpager must set adapter first");
        }

        mSelectedRect = new Rect(0, 0, mItemWidth, mItemHeight);
        create(pager.getAdapter().getCount());
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mCurrentX = (int) (mStartX + (mItemWidth + mItemInterval) * (position + positionOffset));
                invalidate();
            }

            @Override
            public void onPageSelected(int position) {
                mCurrentX = mStartX + (mItemWidth + mItemInterval) * position;
                invalidate();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void create(int count) {
        removeAllViews();
        View itemView;
        for (int i = 0; i < count - 1; i++) {
            itemView = buildView(mItemInterval);
            setOnClick(itemView, i);
            addView(itemView);
        }
        itemView = buildView(0);
        setOnClick(itemView, count - 1);
        addView(itemView);
    }

    private void setOnClick(View itemView, final int pos) {
        itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemClick(pos);
                }
            }
        });
    }

    private View buildView(int margin) {
        View view = new View(getContext());
        view.setBackgroundColor(mItemColor);
        LayoutParams p = new LayoutParams(mItemWidth, mItemHeight);
        if (margin != 0) {
            p.rightMargin = margin;
        }
        view.setLayoutParams(p);
        return view;
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener li) {
        mListener = li;
    }

    public interface OnItemClickListener {
        void onItemClick(int pos);
    }
}
