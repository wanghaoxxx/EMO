package com.emolabs.im.net;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.ParseException;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.HeaderGroup;
import org.json.JSONException;
import org.json.JSONObject;

public class EmoHttpClient {

	private FinalHttp mFinalHttp;
	private static final String CONTENT_TYPE = "application/x-www-form-urlencoded; charset=UTF-8";
	private static EmoHttpClient mInstance;

	private EmoHttpClient() {
		init();
	}

	public static EmoHttpClient getInstance() {
		if (mInstance == null) {
			synchronized (EmoHttpClient.class) {
				if (mInstance == null) {
					mInstance = new EmoHttpClient();
				}
			}
		}
		return mInstance;
	}

	private void init() {
		mFinalHttp = new FinalHttp();
	}


	public void doPost(final EmoRequest request) {
		if (request == null)
			return;
		final IEmoHttpListener listener = request.getListener();
		Map<String,String> headers = request.getHeaders();
		String body = request.getBody();
		
		Header[] fixHeaders = null;
		if (headers != null) {
			int size = headers.size();
			fixHeaders = new BasicHeader[size];
			int i = 0;
			for (Map.Entry<String, String> entry : headers.entrySet()) {
				fixHeaders[i++] = new BasicHeader(entry.getKey(),entry.getValue());
			}
		}
		try {
			StringEntity entity = null;
			if(body != null){
				entity =new StringEntity(body);
			}
			mFinalHttp.post(request.getUrl(), fixHeaders, entity, CONTENT_TYPE,
					new AjaxCallBack<String>() {
						@Override
						public void onFailure(Throwable t, int errorNo,
								String strMsg) {
							if (listener != null) {
								listener.onFailed(request.getTag(),request.getUrl(), 404, strMsg);
							}
						}

						@Override
						public void onSuccess(String t) {
							if (listener != null) {
								listener.onSuccess(request.getTag(),request.getUrl(), t);
							}
						}
					});
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			if (listener != null) {
				listener.onFailed(request.getTag(),request.getUrl(), 404, "“Ï≥£");
			}
		}
	}
}
