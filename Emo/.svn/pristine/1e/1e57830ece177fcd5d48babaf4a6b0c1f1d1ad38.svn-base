package com.emolabs.im.net;

import java.util.Map;


public abstract class EmoRequest {
	protected String url;
	protected String body;
	protected IEmoHttpListener mListener;
	protected boolean isCancelled;
	protected Map<String,String> headers;
	protected String tag;
	
	public String getTag(){
		return tag;
	}
	public void setTag(String tag){
		this.tag = tag;
	}

	public String getUrl() {
		return url;
	}

	public String getBody() {
		return body;
	}

	public IEmoHttpListener getListener() {
		return mListener;
	}

	public boolean isCancelled() {
		return isCancelled;
	}
	
	public Map<String,String> getHeaders(){
		return headers;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setListener(IEmoHttpListener l) {
		this.mListener = l;
	}

	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}
	
	public void setHeaders(Map<String,String> headers){
		this.headers = headers;
	}

}
