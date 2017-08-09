package com.example.p029_banner_lunbo.bannerutils;

import android.support.v4.widget.SwipeRefreshLayout;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewGroup;


/**
 * 用于解决BannerView和SwipeRefreshLayout的冲突
 * @author geek
 *
 */
public class BannerViewHelper {
	private BannerView mBannerView;
	private SwipeRefreshLayout mSwipeRefreshLayout;
	
	private VelocityTracker mVelocityTracker;
	
	private float mAccuracy = 1.f;
	
	public BannerViewHelper(BannerView bannerView) {
		mBannerView = bannerView;
		mSwipeRefreshLayout = findSwipeRefreshLayout();
	}
	
	public BannerViewHelper(BannerView bannerView, float accuracy) {
		this(bannerView);
		mAccuracy = accuracy;
	}
	
	public void resolveTouchConflict(MotionEvent event) {
		if(mSwipeRefreshLayout == null) return;
		
		setupVelocityTracker(event);
		switch (event.getAction()) {
		case MotionEvent.ACTION_MOVE:
			if(!mSwipeRefreshLayout.isEnabled()) return;
			mVelocityTracker.computeCurrentVelocity(1000);
			if(Math.abs(mVelocityTracker.getXVelocity()) > 
				Math.abs(mVelocityTracker.getYVelocity()) * mAccuracy) {
				mSwipeRefreshLayout.setEnabled(false);
			}
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL:
			mSwipeRefreshLayout.setEnabled(true);
			recycleVelocityTracker();
		default:
			break;
		}
	}
	
	private void setupVelocityTracker(MotionEvent event) {
		if(mVelocityTracker == null) mVelocityTracker = VelocityTracker.obtain();
		mVelocityTracker.addMovement(event);
	}
	
	private void recycleVelocityTracker() {
		if(mVelocityTracker != null) {
			mVelocityTracker.clear();
			mVelocityTracker.recycle();
			mVelocityTracker = null;
		}
	}
	
	private SwipeRefreshLayout findSwipeRefreshLayout() {
		ViewGroup parent = (ViewGroup) mBannerView.getParent();
		while(parent != null && parent.getId() != android.R.id.content) {
			if(parent instanceof SwipeRefreshLayout) return (SwipeRefreshLayout) parent;
			parent = (ViewGroup) parent.getParent();
		}
		return null;
	}
}
