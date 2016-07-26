package com.view.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {
	
	private static final String TAG = "MainActivity";
	
	//手势检测
		GestureDetector gestureDetector  ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		gestureDetector = new GestureDetector(this, new OnGestureListenerImpl());
//		gestureDetector.setIsLongpressEnabled(false);
//		gestureDetector.setOnDoubleTapListener(new OnDoubleTapListenerImpl());
	}

//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		return gestureDetector.onTouchEvent(event);
//	}

	
	private class OnGestureListenerImpl implements OnGestureListener{

		@Override
		public boolean onDown(MotionEvent e) {
			//手指轻轻触摸屏幕一瞬间，由1个ACITION_DOWN触发
			Log.i(TAG, "手指轻轻触摸屏幕一瞬间，由1个ACITION_DOWN触发");
			return false;
		}

		@Override
		public void onShowPress(MotionEvent e) {
			//手指轻轻触摸屏幕，尚未松开或拖动，由一个ACITION_DOWN触发，
			Log.i(TAG, "手指轻轻触摸屏幕，尚未松开或拖动，由一个ACITION_DOWN触发");
		}

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			//单击事件
			Log.i(TAG, "单击事件");
			return false;
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			//手指按下屏幕并拖动
			Log.i(TAG, "手指按下屏幕并拖动");
			return false;
		}

		@Override
		public void onLongPress(MotionEvent e) {
			//长按事件
			Log.i(TAG, "长按事件");
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			//用户按下触摸屏，快速滑动后松开
			Log.i(TAG, "用户按下触摸屏，快速滑动后松开");
			return false;
		}
	}
	
	private class OnDoubleTapListenerImpl implements android.view.GestureDetector.OnDoubleTapListener{

		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onDoubleTap(MotionEvent e) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean onDoubleTapEvent(MotionEvent e) {
			// TODO Auto-generated method stub
			return false;
		}
		
	}
	
}
