package com.view.example;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;

public class DemoView extends View {

	// 速度追踪，用于追踪手指在滑动过程中的速度，包括水平和竖直方向的速度。
	VelocityTracker velocityTracker;

	//最小滑动误差
	private int mTouchSlop;
	//最小滑动速度
	private int mMinimumVelocity;
	//最大滑动速度
	private int mMaximumVelocity;
	
	
	public DemoView(Context context) {
		super(context);
		final ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledTouchSlop();
        mMinimumVelocity = configuration.getScaledMinimumFlingVelocity();
        mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			initOrResetVelocityTracker();
			velocityTracker.addMovement(event);
			break;
		case MotionEvent.ACTION_MOVE:
			initVelocityTrackerIfNotExists();
			break;
		case MotionEvent.ACTION_UP:
			//获取速度前必须先计算速度
			velocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
			//获取速度
			float xVelocity = velocityTracker.getXVelocity();
			float yVelocity = velocityTracker.getYVelocity();
			break;

		default:
			break;
		}

		return super.onTouchEvent(event);
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
