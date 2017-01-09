package com.emolabs.im.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/*
 * û�л����¼���ViewPager
 * */
public class EmoViewPager extends ViewPager {

	/*
	 * noScroll true���ܻ���
	 * noScroll false ���Ի���
	 * */
	private boolean noScroll = false;

	public EmoViewPager(Context context) {
		super(context);
	}

	public EmoViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		if (noScroll)
			return false;
		else
			return super.onTouchEvent(arg0);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		if (noScroll)
			return false;
		else
			return super.onInterceptTouchEvent(arg0);
	}

}
