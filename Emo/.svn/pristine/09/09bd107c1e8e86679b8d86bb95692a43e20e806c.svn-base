package com.emolabs.im.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.emolabs.im.R;
import com.emolabs.im.adapter.RegisterPagerAdapter;
import com.emolabs.im.widget.EmoViewPager;

public class RegisterActivity extends FragmentActivity {

	List<Fragment> mFragmentList;
	private RegisterFragment1 fragment1 = new RegisterFragment1();
	private RegisterFragment2 fragment2 = new RegisterFragment2();
	private RegisterFragment3 fragment3 = new RegisterFragment3();
	private RegisterFragment4 fragment4 = new RegisterFragment4();

	private EmoViewPager mPager;

	
	//dot
	ImageView 	mIvRegisterDot0;
	ImageView 	mIvRegisterDot1;
	ImageView 	mIvRegisterDot2;
	ImageView 	mIvRegisterDot3;
	ImageView 	mIvRegisterDotLight;//µ±«∞¡¡µ„
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		mFragmentList = new ArrayList<Fragment>();
		mFragmentList.add(fragment1);
		mFragmentList.add(fragment2);
		mFragmentList.add(fragment3);
		mFragmentList.add(fragment4);
		
		mPager = (EmoViewPager) findViewById(R.id.fragment_pager);
		mPager.setAdapter(new RegisterPagerAdapter(getSupportFragmentManager(),mFragmentList));
		
		//dot
		mIvRegisterDot0 = (ImageView) findViewById(R.id.register_dot_0);
		mIvRegisterDot1 = (ImageView) findViewById(R.id.register_dot_1);
		mIvRegisterDot2 = (ImageView) findViewById(R.id.register_dot_2);
		mIvRegisterDot3 = (ImageView) findViewById(R.id.register_dot_3);
		mIvRegisterDotLight = mIvRegisterDot0;
		mIvRegisterDotLight.setBackgroundResource(R.drawable.dot_selected);
	}

	public void setCurrentItem(int item){
		mPager.setCurrentItem(item);
		switch(item){
		case 0:
			mIvRegisterDotLight.setBackgroundResource(R.drawable.dot_nonselected);
			mIvRegisterDotLight = mIvRegisterDot0;
			mIvRegisterDotLight.setBackgroundResource(R.drawable.dot_selected);
			break;
		case 1:
			mIvRegisterDotLight.setBackgroundResource(R.drawable.dot_nonselected);
			mIvRegisterDotLight = mIvRegisterDot1;
			mIvRegisterDotLight.setBackgroundResource(R.drawable.dot_selected);
			break;
		case 2:
			mIvRegisterDotLight.setBackgroundResource(R.drawable.dot_nonselected);
			mIvRegisterDotLight = mIvRegisterDot2;
			mIvRegisterDotLight.setBackgroundResource(R.drawable.dot_selected);
			break;
		case 3:
			mIvRegisterDotLight.setBackgroundResource(R.drawable.dot_nonselected);
			mIvRegisterDotLight = mIvRegisterDot3;
			mIvRegisterDotLight.setBackgroundResource(R.drawable.dot_selected);
			break;
		}
	}
}














