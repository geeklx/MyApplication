package com.example.p031_mokuaihua_viewpager_fragment.demo4.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import com.example.p031_mokuaihua_viewpager_fragment.R;


/**
 * 圆点导航
 * @author geek
 *
 */
public class DotIndicatorView extends View {
	private Paint mPaint;

	private int mPadding;
	private int mCircleRadius;

	private int mCurrentColor;
	private int mNormalColor;

	private int mCount = 3;

	private int mCurrent;

	private int mCurrentStyle;
	private int mCurrentSize;
	private RectF mLargeRectF;

	public DotIndicatorView(Context context, int normalColor, int currentColor,
							int currentStyle, int currentSize, int circleRadius, int padding) {
		super(context);
		initView(normalColor, currentColor, currentStyle, currentSize, circleRadius, padding);
	}

	public DotIndicatorView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DotIndicatorView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BannerView, defStyle, 0);
		int normalColor = ta.getColor(R.styleable.BannerView_indicator_normal_color, 0);
		int currentColor = ta.getColor(R.styleable.BannerView_indicator_current_color, 0);
		int currentStyle = ta.getInt(R.styleable.BannerView_indicator_current_style, 0);
		int currentSize = ta.getDimensionPixelSize(R.styleable.BannerView_indicator_current_size, 0);
		int circleRadius = ta.getDimensionPixelSize(R.styleable.BannerView_indicator_circle_radius, 0);
		int paddings = ta.getDimensionPixelSize(R.styleable.BannerView_indicator_padding, 0);
		ta.recycle();

		initView(normalColor, currentColor, currentStyle, currentSize, circleRadius, paddings);
	}

	private void initView(int normalColor, int currentColor, int currentStyle, int currentSize, int circleRadius, int padding) {
		DisplayMetrics dm = getResources().getDisplayMetrics();
		mPadding = padding == 0 ? (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, dm) : padding;

		mCircleRadius = circleRadius == 0 ? (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, dm) : circleRadius;

		mCurrentColor = currentColor == 0 ? Color.WHITE : currentColor;
		mNormalColor = normalColor == 0 ? Color.argb(88, 91, 91, 91) : normalColor;

		mCurrentStyle = currentStyle;
		mCurrentSize = currentStyle == 0 ? mCircleRadius * 2 : currentSize;

		if (mCurrentStyle != 0) {
			mLargeRectF = new RectF(0, 0, mCurrentSize, mCircleRadius * 2);
		}

		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = mCircleRadius * 2 * (mCount - 1) + mCurrentSize + mPadding * (mCount - 1);
		int height = mCircleRadius * 2;

		setMeasuredDimension(width, height);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.save();
		for (int i = 0; i < mCount; i++) {
			if (i != mCurrent) {
				drawDot(canvas, false, i);
			} else {
				if (mCurrentStyle == 0) {
					drawDot(canvas, true, i);
				} else {
					drawLarge(canvas, i);
				}
			}
		}

		canvas.restore();
	}

	private int mLastSize;

	private void drawDot(Canvas canvas, boolean current, int index) {
		if (index != 0) { canvas.translate(mLastSize, 0);}
		mPaint.setColor(current ? mCurrentColor : mNormalColor);
		canvas.drawCircle(mCircleRadius, mCircleRadius, mCircleRadius, mPaint);

		mLastSize = mCircleRadius * 2 + mPadding;
	}

	private void drawLarge(Canvas canvas, int index) {
		if (index != 0) { canvas.translate(mLastSize, 0);}
		mPaint.setColor(mCurrentColor);
		canvas.drawRoundRect(mLargeRectF, 45.f, 45.f, mPaint);

		mLastSize = mCurrentSize + mPadding;
	}

	public void setupWithViewPager(ViewPager viewPager) {
		if (viewPager == null) { return;}
		viewPager.addOnPageChangeListener(mViewPagerChangeListener);
	}

	/**
	 * 创建导航
	 * @param count 个数
	 */
	public void create(int count) {
		mCount = count;
		requestLayout();
		invalidate();
	}

	/**
	 * 选中当前
	 * @param position 选中位置
	 */
	public void current(int position) {
		mCurrent = position;
		invalidate();
	}

	/**
	 * 获取当前选中的位置
	 * @return
	 */
	public int current() {
		return mCurrent;
	}

	private ViewPager.OnPageChangeListener mViewPagerChangeListener = new ViewPager.OnPageChangeListener() {
		@Override
		public void onPageSelected(int position) {
			current(position);
		}

		@Override
		public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

		}

		@Override
		public void onPageScrollStateChanged(int state) {

		}
	};
}
