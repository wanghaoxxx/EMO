package com.emolabs.im.activity;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import com.emolabs.im.R;
import com.emolabs.im.adapter.FindListAdapter;
import com.emolabs.im.config.AppConfig;
import com.emolabs.im.net.EmoHttpClient;
import com.emolabs.im.net.EmoRequest;
import com.emolabs.im.net.EmoStringRequest;
import com.emolabs.im.net.IEmoHttpListener;
import com.emolabs.im.struct.Find;
import com.emolabs.im.struct.FindData;
import com.emolabs.im.struct.FindDataAlbums;
import com.google.gson.Gson;

public class DiscoverActivity extends Activity implements IEmoHttpListener{
	private static final String TAG = "DiscoverActivity";
	private RecyclerView  mListFindView;
	LinearLayoutManager   mLayoutManager;
	Find find;
	FindData findData;
	FindDataAlbums findDataAlbums;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_discover);
		mListFindView = (RecyclerView) findViewById(R.id.rv_discover_find);
		mLayoutManager = new LinearLayoutManager(this);
		mListFindView.setLayoutManager(mLayoutManager);
		
		//准备数据
		SharedPreferences sp = getSharedPreferences("config", Context.MODE_PRIVATE);
		String token = sp.getString("X-Kata-Token", null);
		String latlng = "123.13131, 13.23111";
		
		//请求头
		Map<String,String> headers = new HashMap<String, String>();
		headers.put("X-Kata-Token", token);
		headers.put("latlng", "123.13131, 13.23111");
		
		//请求体
		JSONObject json = new JSONObject();
		try {
			json.put("latlng","123.13131, 13.23111");
			json.put("X-Kata-Token",token);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		//请求发现页数据
		EmoRequest request = new EmoStringRequest(AppConfig.ServiceUrl.strFindInit, headers, json.toString(), this);
		EmoHttpClient.getInstance().doPost(request);
	}
	
	private Handler uiHandler = new Handler(){
		public void handleMessage(Message msg) {
			if(msg.what == AppConfig.What.SUCCESS){
				Find find = (Find) msg.obj;
				FindListAdapter adapter = new FindListAdapter(DiscoverActivity.this,find);
				mListFindView.setAdapter(adapter);
			}else if(msg.what == AppConfig.What.FAILED){
				
			}else{
				
			}
		};
	};
	
	
	@Override
	public void onSuccess(String tag,String requestUrl, final String requestResult) {
		if(TextUtils.equals(requestUrl,AppConfig.ServiceUrl.strFindInit) && tag == null){
			Log.i(TAG,"findInit:success:request:"+requestResult);
			new Thread(new Runnable() {
				@Override
				public void run() {
					Gson gson = new Gson();
					Find find = gson.fromJson(requestResult, Find.class);
					Message msg=uiHandler.obtainMessage();
					if(find!=null){
						if(find.errno==0){
							msg.obj=find;
							msg.what=AppConfig.What.SUCCESS;
							uiHandler.sendMessage(msg);
						}else {
							msg.what=AppConfig.What.FAILED;
							uiHandler.sendMessage(msg);
						}
					}else {
						msg.what=AppConfig.What.FAILED;
						uiHandler.sendMessage(msg);
					}
				}
			}).start();
		}
	}
	@Override
	public void onFailed(String tag,String requestUrl, int errorNo, String requestResult) {
		if(TextUtils.equals(requestUrl,AppConfig.ServiceUrl.strFindInit) && tag == null){
			Log.i(TAG,"findInit:failed:request:"+requestResult);
			
		}
	}
	
	

}
