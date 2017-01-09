package com.emolabs.im.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class RegisterPagerAdapter extends FragmentStatePagerAdapter {
	
	
	private List<Fragment> fragments;
	
	public RegisterPagerAdapter(FragmentManager fm,List<Fragment> fragments) {
		super(fm);
		this.fragments=fragments;
	}

	@Override
	public Fragment getItem(int position) {
		return fragments!=null?fragments.get(position):null;
	}

	@Override
	public int getCount() {
		return fragments!=null?fragments.size():0;
	}
	

}
