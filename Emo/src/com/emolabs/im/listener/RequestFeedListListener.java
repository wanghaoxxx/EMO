package com.emolabs.im.listener;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.emolabs.im.config.AppConfig;
import com.emolabs.im.net.IEmoHttpListener;
import com.emolabs.im.struct.Feed;
import com.google.gson.Gson;

public class RequestFeedListListener implements IEmoHttpListener{
	private static final String TAG = "RequestFeedListListener";
	Context context;
	Handler handler;
	
	public RequestFeedListListener(Context context,Handler handler){
		this.context = context;
		this.handler = handler;
	}
	
	@Override
	public void onSuccess(String tag, String requestUrl, String requestResult) {
		Log.i(TAG,"requestUrl="+requestUrl + " getfeedListUrl="+AppConfig.ServiceUrl.strGetFeedList);
		if(TextUtils.equals(requestUrl, AppConfig.ServiceUrl.strGetFeedList) && tag == null){
			handleFeedList(tag,requestUrl, requestResult);
		}else if(TextUtils.equals(requestUrl, AppConfig.ServiceUrl.strGetFeedList) && tag == "request_more"){
			handleFeedListMore(tag,requestUrl, requestResult);
		}
	}
	
	@Override
	public void onFailed(String tag, String requestUrl, int errorNo, String requestResult) {
		Log.i(TAG,"requestUrl="+requestUrl + " getfeedListUrl="+AppConfig.ServiceUrl.strGetFeedList);
		if(!TextUtils.equals(requestUrl, AppConfig.ServiceUrl.strGetFeedList) && tag == null){
			Message msg = handler.obtainMessage();
			msg.what = AppConfig.What.FAILED_NETWORK_GET_FEED_LIST;
			msg.obj = null;
			handler.sendMessage(msg);
		}
	}

	private void handleFeedListMore(String tag, String requestUrl, final String requestResult) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Gson gson = new Gson();
				Feed feed = gson.fromJson(requestResult, Feed.class);
				Message msg = handler.obtainMessage();
				if(feed != null){
					if(feed.errno == 0){
						msg.what = AppConfig.What.SUCCESS_GET_FEED_LIST_MORE;
						msg.obj = feed;
					}else{
						msg.what = AppConfig.What.FAILED_GET_FEED_LIST_MORE;
						msg.obj = null;
					}
				}else{
					msg.what = AppConfig.What.FAILED_GET_FEED_LIST_MORE;
					msg.obj = null;
				}
				handler.sendMessage(msg);
			}
		}).start();
	}

	private void handleFeedList(String tag, String requestUrl, final String requestResult) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Gson gson = new Gson();
				Feed feed = gson.fromJson(requestResult, Feed.class);
				Message msg = handler.obtainMessage();
				if(feed != null){
					if(feed.errno == 0){
						msg.what = AppConfig.What.SUCCESS_GET_FEED_LIST;
						msg.obj = feed;
					}else{
						msg.what = AppConfig.What.FAILED_GET_FEED_LIST;
						msg.obj = null;
					}
				}else{
					msg.what = AppConfig.What.FAILED_GET_FEED_LIST;
					msg.obj = null;
				}
				handler.sendMessage(msg);
			}
		}).start();		
	}


}
