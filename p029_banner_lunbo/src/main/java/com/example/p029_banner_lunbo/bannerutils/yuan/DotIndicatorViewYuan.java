package com.example.p029_banner_lunbo.bannerutils.yuan;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

/**
 * 圆点导航
 * @author geek
 *
 */
public class DotIndicatorViewYuan extends View {
	private Paint mPaint;

	private int mPadding;
	private int mCircleRadius;
	
	private int mCurrentColor;
	private int mNormalColor;
	
	private int mCount = 3;
	
	private int mCurrent;
	
	public DotIndicatorViewYuan(Context context) {
		super(context);
		initView();
	}
	
	public DotIndicatorViewYuan(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DotIndicatorViewYuan(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}

	private void initView() {
		DisplayMetrics dm = getResources().getDisplayMetrics();
		mPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, dm);
		mCircleRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, dm);
		mCurrentColor = Color.WHITE;
		mNormalColor = Color.argb(88, 91, 91, 91);
		
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = mCircleRadius * 2 * mCount + mPadding * (mCount -1);
		int height = mCircleRadius * 2;
		
		setMeasuredDimension(width, height);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		for (int i = 0; i < mCount; i++) {
			canvas.save();
			canvas.translate((mCircleRadius * 2 + mPadding) * i, 0);
			
			if(i == mCurrent) mPaint.setColor(mCurrentColor);
			else mPaint.setColor(mNormalColor);
			
			canvas.drawCircle(mCircleRadius , mCircleRadius, mCircleRadius, mPaint);
			canvas.restore();
		}
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
}
