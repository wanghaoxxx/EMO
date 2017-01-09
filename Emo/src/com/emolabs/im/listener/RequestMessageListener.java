package com.emolabs.im.listener;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.emolabs.im.config.AppConfig;
import com.emolabs.im.net.IEmoHttpListener;
import com.emolabs.im.struct.EmoMessage;
import com.google.gson.Gson;

public class RequestMessageListener implements IEmoHttpListener{
	public static final String TAG = "RequestMessageListener";
	public static final int REQUEST_MSG_LIST_SUCCESS = 0;
	Context context;
	Handler handler;
	public RequestMessageListener(Context context,Handler handler){
		this.context = context;
		this.handler = handler;
	}
	
	@Override
	public void onSuccess(String tag, String requestUrl, final String requestResult) {
		if(TextUtils.equals(requestUrl, AppConfig.ServiceUrl.strMessageList) && tag == null){
			Log.i(TAG,"success:"+requestResult);
			new Thread(new Runnable() {
				@Override
				public void run() {
					Gson gson = new Gson();
					EmoMessage emoMsg = gson.fromJson(requestResult, EmoMessage.class);
					if(emoMsg != null){
						if(emoMsg.errno == 0){
							Message msg = new Message();
							msg.what = RequestMessageListener.REQUEST_MSG_LIST_SUCCESS;
							msg.obj = emoMsg;
							handler.sendMessage(msg);
						}else{
							
						}
					}else {
						
					}
				}
			}).start();
		}
	}
	@Override
	public void onFailed(String tag, String requestUrl, int errorNo, String requestResult) {
		Log.i(TAG,"failed:"+requestResult);
	}
}
