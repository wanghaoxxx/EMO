package com.emolabs.im.listener;

import java.io.File;

import com.emolabs.im.R;
import com.emolabs.im.activity.AddFriendActivity;
import com.emolabs.im.activity.DiscoverActivity;
import com.emolabs.im.activity.HomeActivity;
import com.emolabs.im.activity.MessageActivity;
import com.emolabs.im.activity.PersonalFeedActivity;
import com.emolabs.im.config.EmoApplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class HomeOnClickListener implements OnClickListener{
	public static final int TAKE_PHOTO = 0; //≈ƒ’’

	Context context;
	
	public HomeOnClickListener(Context context){
		this.context = context;
	}
	
	@Override
	public void onClick(View v) {}

	private void showTakePhotoUI() {
		
	}

	private void showDiscoverUI() {
		Intent intentDiscover = new Intent();
		intentDiscover.setClass(context, DiscoverActivity.class);
		context.startActivity(intentDiscover);		
	}

	private void showMessageUI() {
		Intent intent = new Intent();
		intent.setClass(context, MessageActivity.class);
		context.startActivity(intent);		
	}
	private void showPersonalUI() {

	}

}
