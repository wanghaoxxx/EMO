package com.emolabs.im.activity;

import com.emolabs.im.R;
import com.emolabs.im.widget.EmoViewPager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;


public class RegisterFragment1 extends Fragment {
	
	public static RegisterFragment1 newInstance(){
		RegisterFragment1 rf=new RegisterFragment1();
		return rf;
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.register_fragment_1, container,false);

//		view.findViewById(R.id.test_fragment).setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				EmoViewPager emoViewPager = ((RegisterActivity)getActivity()).getEmoViewPager();
//				emoViewPager.setCurrentItem(2);
//			}
//		});
		
		return view;
	}

}







