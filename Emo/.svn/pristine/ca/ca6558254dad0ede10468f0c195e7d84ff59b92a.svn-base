package com.emolabs.im.activity;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.emolabs.im.R;
import com.emolabs.im.adapter.MessageListAdapter;
import com.emolabs.im.config.AppConfig;
import com.emolabs.im.listener.MessageListItemOnItemClickListener;
import com.emolabs.im.listener.MessageOnClickListener;
import com.emolabs.im.listener.RequestMessageListener;
import com.emolabs.im.net.EmoHttpClient;
import com.emolabs.im.net.EmoRequest;
import com.emolabs.im.net.EmoStringRequest;
import com.emolabs.im.struct.EmoMessage;

public class MessageActivity extends Activity {
	ImageView ivClose;
	ListView lvMsgList;
	SharedPreferences sp;
	MessageListAdapter msgListAdapter;
	MessageListItemOnItemClickListener onItemClickListener;//listitem里面小控件的监听器
	MessageOnClickListener onClickListener;
	
	private Handler uiHandler = new Handler() {
		public void handleMessage(Message msg) {
			if(msg.what == RequestMessageListener.REQUEST_MSG_LIST_SUCCESS){
				EmoMessage message = (EmoMessage) msg.obj;
				onItemClickListener = new MessageListItemOnItemClickListener(MessageActivity.this,message);
				msgListAdapter = new MessageListAdapter(MessageActivity.this, message);
				lvMsgList.setAdapter(msgListAdapter);
				lvMsgList.setOnItemClickListener(onItemClickListener);
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);
		onClickListener = new MessageOnClickListener(this);
		ivClose = (ImageView) findViewById(R.id.iv_message_close);
		lvMsgList = (ListView) findViewById(R.id.lv_message_msgs);
		ivClose.setOnClickListener(onClickListener);
		sp = getSharedPreferences("config", Context.MODE_PRIVATE);
		getMessageList();
	}

	private void getMessageList() {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("X-Kata-Token", sp.getString("X-Kata-Token", null));
		RequestMessageListener listener = new RequestMessageListener(this, uiHandler);
		EmoRequest request = new EmoStringRequest(AppConfig.ServiceUrl.strMessageList, headers, null, listener);
		EmoHttpClient.getInstance().doPost(request);
	}
}















