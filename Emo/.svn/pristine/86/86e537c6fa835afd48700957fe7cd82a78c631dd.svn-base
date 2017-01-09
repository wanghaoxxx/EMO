package com.emolabs.im.listener;

import java.util.ArrayList;
import com.emolabs.im.activity.PersonalFeedActivity;
import com.emolabs.im.struct.EmoMessage;
import com.emolabs.im.struct.EmoMessageDataItem;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class MessageListItemOnItemClickListener implements OnItemClickListener{
	public static final String TAG = "MessageListItemOnItemClickListener";
	Context context;
	EmoMessage msg;
	ArrayList<EmoMessageDataItem> messageItems;
	
	public MessageListItemOnItemClickListener(Context context,EmoMessage msg){
		this.context = context;
		this.msg = msg;
		this.messageItems = msg.data.message_list;
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Log.i("MessageListItemOnClickListener","position="+position);
		EmoMessageDataItem msgItem = messageItems.get(position);
		Intent intent = new Intent();
		SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		String token = sp.getString("X-Kata-Token", null);
		intent.setClass(context, PersonalFeedActivity.class);
		intent.putExtra("uid", msgItem.user.id);	//”√ªßid
		intent.putExtra("X-Kata-Token", token);		//token
		context.startActivity(intent);
	}
}









