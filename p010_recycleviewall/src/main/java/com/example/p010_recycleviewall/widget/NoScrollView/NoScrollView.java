package com.example.p010_recycleviewall.widget.NoScrollView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

public class NoScrollView extends ScrollView {

	private int mTouchSlop;

	public NoScrollView(Context context) {
		super(context);
		init(context);
	}

	public NoScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public NoScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
	}

	float downX;
	float downY;

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		int action = ev.getAction();

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			downX = ev.getX();
			downY = ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			if (Math.abs(ev.getX() - downX) > mTouchSlop) {
				return false;
			}
			break;
		default:
			break;
		}
		return super.onInterceptTouchEvent(ev);
	}

	// GestureDetector gestureDetector;
	//
	// public void setGestureDetector(GestureDetector gestureDetector) {
	// this.gestureDetector = gestureDetector;
	// }
	//
	// @Override
	// public boolean onTouchEvent(MotionEvent ev) {
	// // TODO Auto-generated method stub
	// super.onTouchEvent(ev);
	// return gestureDetector.onTouchEvent(ev);
	// }
	//
	// @Override
	// public boolean dispatchTouchEvent(MotionEvent ev) {
	// gestureDetector.onTouchEvent(ev);
	// super.dispatchTouchEvent(ev);
	// return true;
	// }
}
