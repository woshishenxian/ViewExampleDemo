package com.view.example.ex;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

public class ListViewEx extends ListView {
	private static final String TAG = "ListViewEx";
	
	private HorizontalScrollViewEx2 mHorizontalScrollViewEx2;
	
	private int mLastX = 0;
	private int mLastY = 0;
	

	public ListViewEx(Context context) {
		super(context);
	}

	@TargetApi(21)
	public ListViewEx(Context context, AttributeSet attrs, int defStyleAttr,
			int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	public ListViewEx(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public ListViewEx(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		int x = (int) ev.getX();
		int y = (int) ev.getY();

		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mHorizontalScrollViewEx2.requestDisallowInterceptTouchEvent(true);
			break;
		case MotionEvent.ACTION_MOVE:
			int deltaX = x - mLastX;
			int deltaY = x - mLastY;
			if (Math.abs(deltaX) > Math.abs(deltaY)) {
				mHorizontalScrollViewEx2.requestDisallowInterceptTouchEvent(false);
			} 
			break;
		case MotionEvent.ACTION_UP:break;
		default:break;
		}
		mLastX = x;
		mLastY = y;

		return super.dispatchTouchEvent(ev);
	}
	

}
