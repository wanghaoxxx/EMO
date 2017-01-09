package com.emolabs.im.listener;

import com.emolabs.im.R;
import com.emolabs.im.activity.MessageActivity;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

public class MessageOnClickListener implements OnClickListener{
	Context context;
	
	public MessageOnClickListener(Context context){
		this.context = context;
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.iv_message_close:
			((MessageActivity)context).finish();
			break;
		}
	}

}
