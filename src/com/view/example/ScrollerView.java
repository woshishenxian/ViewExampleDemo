package com.view.example;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.Scroller;

public class ScrollerView extends FrameLayout {

	private Scroller scroller;

	private float mLastY;
	private VelocityTracker velocityTracker;

	private int mTouchSlop;
	private int mMinimumVelocity;
	private int mMaximumVelocity;
	private DIRECTION mDirection;

	
	 /**
     * 滑动方向 *
     */
    enum DIRECTION {
        UP,// 向上划
        DOWN// 向下划
    }
    
	public ScrollerView(Context context) {
		super(context);
		scroller = new Scroller(context);
	}

	@TargetApi(21)
	public ScrollerView(Context context, AttributeSet attrs, int defStyleAttr,
			int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		scroller = new Scroller(context);
	}

	public ScrollerView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub scroller = new
		// Scroller(context);
		scroller = new Scroller(context);
	}

	public ScrollerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		scroller = new Scroller(context);
	}

	private void init(Context context) {
		final ViewConfiguration configuration = ViewConfiguration.get(context);
		mTouchSlop = configuration.getScaledTouchSlop();
		mMinimumVelocity = configuration.getScaledMinimumFlingVelocity();
		mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float currentY = event.getY();
		float deltaY;

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mLastY = currentY;
			initOrResetVelocityTracker();
			velocityTracker.addMovement(event);
			break;
		case MotionEvent.ACTION_MOVE:
			initVelocityTrackerIfNotExists();
			velocityTracker.addMovement(event);
			deltaY = mLastY - currentY;
			scrollBy(0, (int) (deltaY + 0.5));
			mLastY = currentY;
			break;
		case MotionEvent.ACTION_UP:
			 int yVelocity = (int) -velocityTracker.getYVelocity();
			 mDirection = yVelocity > 0 ? DIRECTION.UP : DIRECTION.DOWN;
			 scroller.fling(0, getScrollY(), 0, yVelocity, 0, 0, -Integer.MAX_VALUE, Integer.MAX_VALUE);
			 invalidate();
			break;

		default:
			break;
		}
		super.onTouchEvent(event);
		return true;
	}

	@Deprecated
	private void smoothScrollTo(int destX, int destY) {
		int scrollY = getScrollY();
		int deltaY = destY - scrollY;
		scroller.startScroll(0, scrollY, 0, deltaY, 1000);
		invalidate();
	}
	
	@Override
	public void computeScroll() {
		if (scroller.computeScrollOffset()) {
			scrollTo(scroller.getCurrX(), scroller.getCurrY());
			postInvalidate();
		}
	}

	private void initOrResetVelocityTracker() {
		if (velocityTracker == null) {
			velocityTracker = VelocityTracker.obtain();
		} else {
			velocityTracker.clear();
		}
	}

	private void initVelocityTrackerIfNotExists() {
		if (velocityTracker == null) {
			velocityTracker = VelocityTracker.obtain();
		}
	}

	// 重置
	private void velocityTrackerClear() {
		if (velocityTracker != null) {
			velocityTracker.clear();
		}
	}

	// 回收
	private void velocityTrackerRecyle() {
		if (velocityTracker != null) {
			velocityTracker.recycle();
		}
	}

}
