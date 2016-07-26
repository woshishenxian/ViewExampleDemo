package com.view.example.ex;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewGroup;
import android.widget.Scroller;

public class HorizontalScrollViewEx2 extends ViewGroup {

	private static final String TAG = "HorizonalScrollViewEx";

	private int mChildrenSize;
	private int mChildWidth;
	private int mChildIndex;
	// 分别记录上次滑动的坐标
	private int mLastX = 0;
	private int mLastY = 0;
	// 分别记录上次滑动的坐标（onInterceptTouchEvent）
	private int mLastXIntercept = 0;
	private int mLastYIntercept = 0;

	private Scroller mScroller;
	private VelocityTracker mVelocityTracker;

	public HorizontalScrollViewEx2(Context context) {
		super(context);
		init();
	}

	@TargetApi(21)
	public HorizontalScrollViewEx2(Context context, AttributeSet attrs,
			int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		init();
	}

	public HorizontalScrollViewEx2(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public HorizontalScrollViewEx2(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	private void init() {
		mScroller = new Scroller(getContext());
		mVelocityTracker = VelocityTracker.obtain();
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		int x = (int) ev.getX();
		int y = (int) ev.getY();

		int action = ev.getAction();
		if (action == MotionEvent.ACTION_DOWN) {
			mLastX = x;
			mLastY = y;
			if (!mScroller.isFinished()) {
				mScroller.abortAnimation();
				return true;
			}
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mVelocityTracker.addMovement(event);
		int x = (int) event.getX();
		int y = (int) event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (!mScroller.isFinished()) {
				mScroller.abortAnimation();
			}
			break;
		case MotionEvent.ACTION_MOVE:
			int deltaX = x - mLastX;
			int deltaY = y - mLastY;
			scrollBy(deltaX, deltaY);
			break;
		case MotionEvent.ACTION_UP:
			int scrollX = getScrollX();
			int scrollToChildIndex = scrollX / mChildWidth;
			mVelocityTracker.computeCurrentVelocity(1000);
			float xVelocity = mVelocityTracker.getXVelocity();
			if (Math.abs(xVelocity) > 50) {
				mChildIndex = xVelocity > 0 ? mChildIndex - 1 : mChildIndex + 1;
			} else {
				mChildIndex = (scrollX + mChildWidth / 2) / mChildWidth;
			}
			mChildIndex = Math.max(0, Math.min(mChildIndex, mChildrenSize - 1));
			int dx = mChildIndex * mChildWidth - scrollX;
			smoothScrollBy(dx, 0);
			mVelocityTracker.clear();
			break;
		default:
			break;
		}
		mLastX = x;
		mLastY = y;

		return true;
	}

	private void smoothScrollBy(int dx, int dy) {
		mScroller.startScroll(getScrollX(), 0, dx, 0, 500);
		invalidate();
	}

	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			postInvalidate();
		}

	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub

	}

}
