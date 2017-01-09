package com.emolabs.im.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.emolabs.im.R;
import com.emolabs.im.adapter.LikeListAdapter;
import com.emolabs.im.config.AppConfig;
import com.emolabs.im.struct.Like;
import com.emolabs.im.struct.LikeData;
import com.emolabs.im.struct.LikeDataItem;
import com.google.gson.Gson;

public class LikeListActivity extends Activity{
		ListView 	lvLikeList;
		ImageView 	ivBack;
		Like		like;
		LikeData 	likeData;
		ArrayList<LikeDataItem> items;
		
	
	private Handler uiHandler = new Handler(){
		public void handleMessage(Message msg) {
			boolean successed = false;
			if(msg.what == AppConfig.What.SUCCESS_GET_LIKE_LIST){
				successed = true;
			}else{
				successed = false;
			}
			SharedPreferences sp = LikeListActivity.this.getSharedPreferences("config", Context.MODE_PRIVATE);
			String uid = sp.getString("uid", null);
			Like li = (Like)msg.obj;
			LikeListActivity.this.like = li;
			LikeListActivity.this.likeData = li.data;
			LikeListActivity.this.moveOwnDataToFirst(li.data.list, uid);
			LikeListActivity.this.items = li.data.list;
			LikeListActivity.this.initView(successed,(Like)msg.obj);
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final String strLike = getIntent().getStringExtra("like");
		new Thread(new Runnable() {
			@Override
			public void run() {
				Gson gson = new Gson();
				Like like = gson.fromJson(strLike, Like.class);
				Message msg = uiHandler.obtainMessage();
				if(like.errno == 0){
					msg.what = AppConfig.What.SUCCESS_GET_LIKE_LIST;
					msg.obj = like;
				}else{
					msg.what = AppConfig.What.FAILED_GET_LIKE_LIST;
				}
				uiHandler.sendMessage(msg);
			}
		}).start();
	}
	
	protected void initView(boolean successed,Like like) {
		if(successed){
			setContentView(R.layout.activity_likelist);
			lvLikeList = (ListView) findViewById(R.id.lv_likelist_likes);
			ivBack = (ImageView) findViewById(R.id.iv_likelist_back);
			lvLikeList.setAdapter(new LikeListAdapter(this, like));
			lvLikeList.setOnItemClickListener(new OnListItemClickListener());
		}else{
			finish();
		}
	}
	
	private void moveOwnDataToFirst(ArrayList<LikeDataItem> itemList,String uid){
		if(itemList == null || itemList.size() == 0 || itemList.size() == 1){
			return ;
		}
		final int size=itemList.size();
		int index=-1;
		for(int i=0;i<size;i++){
			if(itemList.get(i).user.id.equals(uid)){
				index=i;
				break;
			}
		}
		if(index==-1){
			return;
		}
		LikeDataItem ownItem=itemList.remove(index);
		itemList.add(0, ownItem);
	}
	
	class OnListItemClickListener implements OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Log.i("like","listitem");
			//跳转到个人feed
			
			SharedPreferences sp = LikeListActivity.this.getSharedPreferences("config", Context.MODE_PRIVATE);
			String token = sp.getString("X-Kata-Token", null);
			Intent intent = new Intent();
			intent.setClass(LikeListActivity.this, PersonalFeedActivity.class);
			intent.putExtra("uid", items.get(position).user.id);	//用户id
			intent.putExtra("X-Kata-Token", token);		//token
			startActivity(intent);
		}
	}
}
