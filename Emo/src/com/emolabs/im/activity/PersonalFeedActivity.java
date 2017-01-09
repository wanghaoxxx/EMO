package com.emolabs.im.activity;

import java.util.ArrayList;
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
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.emolabs.im.R;
import com.emolabs.im.adapter.ListFeedAdapter;
import com.emolabs.im.config.AppConfig;
import com.emolabs.im.config.EmoApplication;
import com.emolabs.im.net.EmoHttpClient;
import com.emolabs.im.net.EmoRequest;
import com.emolabs.im.net.EmoStringRequest;
import com.emolabs.im.net.IEmoHttpListener;
import com.emolabs.im.struct.Feed;
import com.emolabs.im.struct.FeedDataItem;
import com.emolabs.im.struct.PersonalFeed;
import com.emolabs.im.struct.UserDetailInfo;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

//用户feed流
public class PersonalFeedActivity extends Activity {
	private static final String TAG = "OnUserInfoListener";
	ListView mPersonalFeedList;
	ImageView mIvBack;
	TextView mTvName;
	View mHeaderView;

	String mUserId;
	String mFeedId;
	String mToken;

	boolean isLoadFinished;
	int hasMore;
	String lastFid;
	ListFeedAdapter listFeedAdapter;
	SharedPreferences sp;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_feed);
		isLoadFinished = true;
		sp = getSharedPreferences("config", Context.MODE_PRIVATE);
		mHeaderView = getLayoutInflater().inflate(R.layout.personal_feed_list_item_header, mPersonalFeedList, false);
		mPersonalFeedList = (ListView) findViewById(R.id.lv_personal_feed_feedlist);
		mIvBack = (ImageView) findViewById(R.id.iv_personal_feed_back);
		mTvName = (TextView) findViewById(R.id.tv_personal_feed_name);
		mIvBack.setOnClickListener(new HeaderClickListener());
		
		mPersonalFeedList.addHeaderView(mHeaderView);
		mPersonalFeedList.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {

			}
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				int lastItem = firstVisibleItem + visibleItemCount - 1;
				if (hasMore == 1 && isLoadFinished && mPersonalFeedList.getChildCount() == lastItem) {
					isLoadFinished = false;
					Map<String, String> headers = new HashMap<String, String>();
					String token = sp.getString("X-Kata-Token", null);
					headers.put("X-Kata-Token", token);
					headers.put("last_fid", lastFid);
					JSONObject json = new JSONObject();
					try {
						json.put("uid", mUserId);
					} catch (JSONException e) {
						e.printStackTrace();
					}
					String body = json.toString();
					EmoStringRequest request = new EmoStringRequest(AppConfig.ServiceUrl.strUserFeedList, headers,
							body, new OnRequestMoreListener());
					request.setTag("request_more");
					EmoHttpClient.getInstance().doPost(request);
				}
			}
		});

		// 得到用户信息
		getExtraInfo();
		getUserInfo();
		getPersonalFeed();
	}

	private void getExtraInfo() {
		Bundle bundle = getIntent().getExtras();
		mUserId = bundle.getString("uid");
		mFeedId = bundle.getString("fid");
		mToken = bundle.getString("X-Kata-Token");
	}

	private void getUserInfo() {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("X-Kata-Token", mToken);
		headers.put("uid", mUserId);
		JSONObject json = new JSONObject();
		try {
			json.put("X-Kata-Token", mToken);
			json.put("uid", mUserId);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		Log.i("OnUserInfoListener", "userId = " + mUserId + ":token = " + mToken);
		EmoRequest request = new EmoStringRequest(AppConfig.ServiceUrl.strUserInfo, headers, json.toString(),
				new OnUserInfoListener());
		EmoHttpClient.getInstance().doPost(request);
	}

	private void getPersonalFeed() {

	}

	private Handler uiHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == AppConfig.What.SUCCESS) {
				PersonalFeed feed = (PersonalFeed) msg.obj;
				UserDetailInfo userDetailInfo = feed.data.user;
				listFeedAdapter = new ListFeedAdapter(PersonalFeedActivity.this, feed.data.feed_list);
				mPersonalFeedList.setAdapter(listFeedAdapter);
				ImageView ivHeader = (ImageView) mHeaderView.findViewById(R.id.iv_list_item_header);
				TextView tvLikeCount = (TextView) mHeaderView.findViewById(R.id.tv_list_item_header_like_count);
				TextView tvFollowings = (TextView) mHeaderView.findViewById(R.id.tv_list_item_header_followings);
				TextView tvFollowers = (TextView) mHeaderView.findViewById(R.id.tv_list_item_header_followers);

				Log.i("handler", userDetailInfo.head);
				ImageLoader.getInstance().displayImage(userDetailInfo.head, ivHeader, EmoApplication.options_circle);
				tvLikeCount.setText(userDetailInfo.like_count);
				tvFollowings.setText(userDetailInfo.followings + " 关注");
				tvFollowers.setText(userDetailInfo.followers + " 粉丝");
				mTvName.setText(userDetailInfo.name);
			} else if (msg.what == AppConfig.What.FAILED) {

			} else if (msg.what == AppConfig.What.SUCCESS_USER_FEED_INFO_MORE){
				Feed feed = (Feed) msg.obj;
				ArrayList<FeedDataItem> list = feed.data.feed_list;
				listFeedAdapter.feedItemList.addAll(list);
				listFeedAdapter.notifyDataSetChanged();
			}
		}
	};

	class OnUserInfoListener implements IEmoHttpListener {
		private static final String TAG = "OnUserInfoListener";

		@Override
		public void onSuccess(String tag, String requestUrl, final String requestResult) {
			if (TextUtils.equals(requestUrl, AppConfig.ServiceUrl.strUserInfo) && tag == null) {
				Log.i(TAG, "userInfo success Request:" + requestResult);
				new Thread(new Runnable() {
					@Override
					public void run() {
						Gson gson = new Gson();
						PersonalFeed feed = gson.fromJson(requestResult, PersonalFeed.class);
						hasMore = feed.data.has_more;
						lastFid = feed.data.last_fid;
						Message msg = uiHandler.obtainMessage();
						if (feed != null) {
							if (feed.errno == 0) {
								msg.obj = feed;
								msg.what = AppConfig.What.SUCCESS;
								uiHandler.sendMessage(msg);
							} else {
								msg.what = AppConfig.What.FAILED;
								uiHandler.sendMessage(msg);
							}
						} else {
							msg.what = AppConfig.What.FAILED;
							uiHandler.sendMessage(msg);
						}
					}
				}).start();
			}
		}

		@Override
		public void onFailed(String tag, String requestUrl, int errorNo, String requestResult) {
			if (TextUtils.equals(requestUrl, AppConfig.ServiceUrl.strGetUserInfoByName) && tag == null) {
				Log.i(TAG, "userInfo failed Request:" + requestResult);
			}
		}
	}
	class OnRequestMoreListener implements IEmoHttpListener {
		@Override
		public void onSuccess(String tag, String requestUrl, final String requestResult) {
			if (TextUtils.equals(requestUrl, AppConfig.ServiceUrl.strUserFeedList) && TextUtils.equals(tag, "request_more")) {
				Log.i("strUserFeedList","strUserFeedList:request:"+requestResult);
				new Thread(new Runnable() {
					@Override
					public void run() {
						Gson gson = new Gson();
						Feed feed = gson.fromJson(requestResult, Feed.class);
						if(feed != null){
							if(feed.errno == 0){
								hasMore = feed.data.has_more ? 1 : 0;
								lastFid = feed.data.last_fid;
								Message msg = uiHandler.obtainMessage();
								msg.what = AppConfig.What.SUCCESS_USER_FEED_INFO_MORE;
								msg.obj = feed;
								uiHandler.sendMessage(msg);
							}else{
								//error
							}
						}else{
							
						}
						isLoadFinished = true;
					}
				}).start();
			}
		}
		@Override
		public void onFailed(String tag, String requestUrl, int errorNo, String requestResult) {
			if (TextUtils.equals(requestUrl, AppConfig.ServiceUrl.strUserFeedList) && TextUtils.equals(tag, "request_more")) {
				Log.i("strUserFeedList","strUserFeedList:request:"+requestResult);
			}
		}
	}
	class HeaderClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			if(v.getId() == R.id.iv_personal_feed_back){
				finish();
			}else if(v.getId() == R.id.tv_personal_feed_name){
				
			}
		}
	}
}







