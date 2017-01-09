package com.emolabs.im.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.emolabs.im.R;


public class GuideActivity extends Activity implements OnClickListener{

	protected static final String TAG = "GuideActivity";
	private View guideView1;
	private View guideView2;
	private View guideView3;
	List<View> 	mListView;
	ViewPager 	mViewPager;
	
	private ImageView	mImageViewLeft;
	private ImageView 	mImageViewMidder;
	private ImageView	mImageViewRight;
	
	private Button		mbtnRegister;
	private Button		mbtnLogin;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        
        
        initView();
        
		
		
    }

	private void initView() {
		
		mbtnRegister = (Button) findViewById(R.id.btn_guide_register);
		mbtnLogin = (Button) findViewById(R.id.btn_guide_login);
		mbtnRegister.setOnClickListener(this);
		mbtnLogin.setOnClickListener(this);
		
        mViewPager = (ViewPager) findViewById(R.id.vp_guide_guidelist); 
        guideView1 = getLayoutInflater().inflate(R.layout.guide_1,mViewPager,false);
        guideView2 = getLayoutInflater().inflate(R.layout.guide_2, mViewPager,false);
        guideView3 = getLayoutInflater().inflate(R.layout.guide_3, mViewPager,false);
        
        mListView = new ArrayList<View>();
        mListView.add(guideView1);
        mListView.add(guideView2);
        mListView.add(guideView3);
        
        mImageViewLeft = (ImageView) findViewById(R.id.iv_guide_dot_left);
        mImageViewMidder = (ImageView) findViewById(R.id.iv_guide_dot_midder);
        mImageViewRight = (ImageView) findViewById(R.id.iv_guide_dot_right);
        mImageViewLeft.setBackgroundResource(R.drawable.dot_selected);
        
        PagerAdapter pagerAdapter = new PagerAdapter() {
			
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}
			
			@Override
			public int getCount() {
				return mListView.size();
			}
			
			
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
		        container.addView(mListView.get(position), 0);//添加页卡  
	            return mListView.get(position);  
			}
			
			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				container.removeView((View)object);
			}
			
			
			
			@Override
			public int getItemPosition(Object object) {
				return super.getItemPosition(object);
			}
		};
        
		mViewPager.setAdapter(pagerAdapter);
		
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int curIndex) {
				Log.i(TAG, "onPageSelected curIndex=" + curIndex);
		
				if(curIndex == 0){
					mImageViewLeft.setBackgroundResource(R.drawable.dot_selected);
					mImageViewMidder.setBackgroundResource(R.drawable.dot_nonselected);
					mImageViewRight.setBackgroundResource(R.drawable.dot_nonselected);
				}else if(curIndex == 1){
					mImageViewMidder.setBackgroundResource(R.drawable.dot_selected);
					mImageViewLeft.setBackgroundResource(R.drawable.dot_nonselected);
					mImageViewRight.setBackgroundResource(R.drawable.dot_nonselected);
				}else if(curIndex == 2){
					mImageViewRight.setBackgroundResource(R.drawable.dot_selected);
					mImageViewLeft.setBackgroundResource(R.drawable.dot_nonselected);
					mImageViewMidder.setBackgroundResource(R.drawable.dot_nonselected);
				}
			}
			
			@Override
			public void onPageScrolled(int curIndex, float percent, int pix) {
				//Log.d(TAG, "onPageScrolled curIndex=" + curIndex + " percent=" + percent + " pix=" + pix);
//				if(percent <= 0.5){
//					return;
//				}
//				
//				if(curIndex == 0){
//					mImageViewMidder.setBackgroundResource(R.drawable.dot_selected);
//					mImageViewLeft.setBackgroundResource(R.drawable.dot_nonselected);
//					mImageViewRight.setBackgroundResource(R.drawable.dot_nonselected);
//				}else if(curIndex == 1){
//					if(pix > 50){//向右
//						mImageViewLeft.setBackgroundResource(R.drawable.dot_selected);
//						mImageViewMidder.setBackgroundResource(R.drawable.dot_nonselected);
//						mImageViewRight.setBackgroundResource(R.drawable.dot_nonselected);
//					}else{//向左
//						mImageViewRight.setBackgroundResource(R.drawable.dot_selected);
//						mImageViewLeft.setBackgroundResource(R.drawable.dot_nonselected);
//						mImageViewMidder.setBackgroundResource(R.drawable.dot_nonselected);
//					}
//				}else if(curIndex == 2){
//					mImageViewMidder.setBackgroundResource(R.drawable.dot_selected);
//					mImageViewLeft.setBackgroundResource(R.drawable.dot_nonselected);
//					mImageViewRight.setBackgroundResource(R.drawable.dot_nonselected);
//				}
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				Log.i(TAG, "onPageScrollStateChanged");
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.btn_guide_register:
			showRegister();
			break;
		case R.id.btn_guide_login:
			showLogin();
			break;
		}
	}
	
	//注册界面
	private void showRegister() {
		Intent intent = new Intent(this,RegisterActivity.class);
		startActivity(intent);
	}
	
	//登录界面
	private void showLogin() {
		Intent intent = new Intent(this,LoginActivity.class);
		startActivity(intent);
	}



}


















