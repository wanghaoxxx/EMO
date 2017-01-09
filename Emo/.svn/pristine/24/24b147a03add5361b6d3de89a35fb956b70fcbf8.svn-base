package com.emolabs.im.listener;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.emolabs.im.R;
import com.emolabs.im.activity.PersonalFeedActivity;
import com.emolabs.im.struct.EmoMessage;
import com.emolabs.im.struct.EmoMessageDataItem;

public class MessageListItemOnClickListener implements OnClickListener{
	private static final String TAG = "MessageListItemOnClickListener";
	Context context;
	EmoMessage msg;
	ArrayList<EmoMessageDataItem> messageItems;
	
	public MessageListItemOnClickListener(Context context,EmoMessage msg){
		this.context = context;
		this.msg = msg;
		messageItems = msg.data.message_list;
	}
	@Override
	public void onClick(View v) {
		int position = Integer.parseInt((String)v.getTag());
		EmoMessageDataItem msgItem = messageItems.get(position);
		switch(v.getId()){
		case R.id.iv_msg_listitem_header:
		case R.id.tv_msg_listitem_name:
		case R.id.tv_msg_listitem_time:
		case R.id.tv_msg_listitem_dothings:
			Intent intent = new Intent();
			SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
			String token = sp.getString("X-Kata-Token", null);
			intent.setClass(context, PersonalFeedActivity.class);
			intent.putExtra("uid", msgItem.user.id);	//ÓÃ»§id
			intent.putExtra("X-Kata-Token", token);		//token
			context.startActivity(intent);
		case R.id.iv_msg_listitem_photo:
			Log.i(TAG,"×Ó£ºÕÕÆ¬");
			
			break;
		}
	}
}
