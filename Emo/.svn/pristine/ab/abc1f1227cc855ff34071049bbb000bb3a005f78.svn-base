package com.emolabs.im.net;

import java.util.Map;

public class EmoStringRequest extends EmoRequest {
	public EmoStringRequest(){
		super();
	}
	public EmoStringRequest(String url,String body,IEmoHttpListener mListener){
		super();
		this.url = url;
		this.body = body;
		this.mListener = mListener;
		this.tag = null;
	}
	public EmoStringRequest(String url,String body,IEmoHttpListener listener,boolean isCanCancel){
		super();
		this.url = url;
		this.body = body;
		this.mListener = listener;
		this.isCancelled = isCanCancel;
		this.tag = null;
	}
	public EmoStringRequest(String url,Map<String,String> headers,String body,IEmoHttpListener listener){
		super();
		this.url = url;
		this.headers = headers;
		this.body = body;
		this.mListener = listener;
		this.tag = null;
	}
}
