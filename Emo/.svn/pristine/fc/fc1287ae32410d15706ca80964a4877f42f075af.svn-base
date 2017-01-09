package com.emolabs.im.activity;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.emolabs.im.R;
import com.emolabs.im.config.AppConfig;
import com.emolabs.im.config.EmoApplication;
import com.emolabs.im.net.EmoHttpClient;
import com.emolabs.im.net.EmoRequest;
import com.emolabs.im.net.EmoStringRequest;
import com.emolabs.im.net.IEmoHttpListener;
import com.emolabs.im.struct.FeedDataItem;
import com.emolabs.im.utils.EmoTimeUtils;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;

public class SingleFeedActivity extends Activity {
	RelativeLayout rlTitle;
	ImageView ivBack;
	ImageView ivHead;
	TextView tvName;
	TextView tvPublishTime;
	ImageView ivPhoto;
	ImageView ivReplyPhoto;
	TextView tvContent;
	ImageView ivLike;
	TextView tvLikeCount;
	ImageView ivMore;
	ImageView ivTakePhoto;
	TextView tvReplyCount;
	SingleFeedOnClickListener listener;
	FeedDataItem feedDataItem;

	String token;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_feed);
		initView();
		initData();
		getViewData();
		setClickListener();
		showViewData();
	}

	private void initData() {
		SharedPreferences sp = getSharedPreferences("config", Context.MODE_PRIVATE);
		token = sp.getString("X-Kata-Token", null);
	}

	private void showViewData() {
		ImageLoader.getInstance().displayImage(feedDataItem.user.head, ivHead, EmoApplication.options_circle);
		tvName.setText(feedDataItem.user.name);
		String time = EmoTimeUtils.formatTime(feedDataItem.publish_time);
		tvPublishTime.setText(time);

		// 图片大小
		RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ivPhoto.getLayoutParams();
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenWidth = dm.widthPixels;
		int imageWidth = screenWidth;
		int imageHeight = 0;

		// 显示发的图
		if (feedDataItem.image.direction == 0) {// 坚屏
			imageHeight = imageWidth * 4 / 3;
		} else {// 横屏
			imageHeight = imageWidth * 3 / 4;
		}
		params.width = imageWidth;
		params.height = imageHeight;
		ivPhoto.setLayoutParams(params);
		ImageLoader.getInstance().displayImage(feedDataItem.image.url, ivPhoto, EmoApplication.options_normal);

		// 显示回图
		if (feedDataItem.is_repost) {
			ivReplyPhoto.setVisibility(View.VISIBLE);
			ivReplyPhoto.setClickable(true);
			ImageLoader.getInstance().displayImage(feedDataItem.repost.repost_image.url, ivReplyPhoto,
					EmoApplication.options_normal);
		}else{
			ivReplyPhoto.setVisibility(View.INVISIBLE);
			ivReplyPhoto.setClickable(false);
		}

		tvContent.setText(feedDataItem.content);
		tvLikeCount.setText(feedDataItem.like_count + "");
		tvReplyCount.setText(feedDataItem.repost_count + "");
	}

	private void setClickListener() {
		rlTitle.setOnClickListener(listener);
		ivBack.setOnClickListener(listener);
		ivHead.setOnClickListener(listener);
		tvName.setOnClickListener(listener);
		tvPublishTime.setOnClickListener(listener);
		ivPhoto.setOnClickListener(listener);
		ivReplyPhoto.setOnClickListener(listener);
		tvContent.setOnClickListener(listener);
		ivLike.setOnClickListener(listener);
		tvLikeCount.setOnClickListener(listener);
		ivMore.setOnClickListener(listener);
		ivTakePhoto.setOnClickListener(listener);
		tvReplyCount.setOnClickListener(listener);
	}

	private void getViewData() {
		Intent intent = getIntent();
		String strFeedItem = intent.getStringExtra("feed_data_item");
		Gson gson = new Gson();
		this.feedDataItem = gson.fromJson(strFeedItem, FeedDataItem.class);
		this.listener = new SingleFeedOnClickListener(this, feedDataItem);
	}

	private void initView() {
		rlTitle = (RelativeLayout) findViewById(R.id.rl_title);
		ivBack = (ImageView) findViewById(R.id.iv_single_feed_back);
		ivHead = (ImageView) findViewById(R.id.iv_single_feed_head);
		tvName = (TextView) findViewById(R.id.tv_single_feed_name);
		tvPublishTime = (TextView) findViewById(R.id.tv_single_feed_publish_time);
		ivPhoto = (ImageView) findViewById(R.id.iv_single_feed_photo);
		ivReplyPhoto = (ImageView) findViewById(R.id.iv_single_feed_reply_photo);
		tvContent = (TextView) findViewById(R.id.tv_single_feed_content);
		ivLike = (ImageView) findViewById(R.id.iv_single_feed_like);
		tvLikeCount = (TextView) findViewById(R.id.tv_single_feed_likecount);
		ivMore = (ImageView) findViewById(R.id.iv_single_feed_more);
		ivTakePhoto = (ImageView) findViewById(R.id.iv_single_feed_take_photo);
		tvReplyCount = (TextView) findViewById(R.id.tv_single_feed_reply_photo_count);
	}

	class SingleFeedOnClickListener implements OnClickListener {
		Context context;
		FeedDataItem feedDataItem;
		long preMillis = 0;

		public SingleFeedOnClickListener(Context context, FeedDataItem feedDataItem) {
			this.context = context;
			this.feedDataItem = feedDataItem;
		}

		@Override
		public void onClick(View v) {
			Log.i("doubleEvent", "click 事件");
			long currMillis = System.currentTimeMillis();
			long interval = 0;
			if (preMillis == 0) {
				preMillis = currMillis;
			} else {
				interval = currMillis - preMillis;
				preMillis = currMillis;
				if (interval > 100 && interval < 1000) {// 双击事件
					Log.i("doubleEvent", "双击事件...:" + interval);
					onDoubleEvent(v);
				} else {
					Log.i("doubleEvent", "单击事件...:" + interval);
					onClickEvent(v);
				}
			}
		}

		// 双击事件
		private void onDoubleEvent(View v) {
			if (v.getId() == R.id.iv_single_feed_photo) {
				if (!feedDataItem.is_like) {
					// 显示红心
					int likeCount = Integer.parseInt(tvLikeCount.getText().toString()) + 1;
					ivLike.setImageResource(R.drawable.heart_red);
					tvLikeCount.setText(likeCount + "");
					feedDataItem.is_like = true;
					requestLike(true);// 请求赞
				}
			}
		}

		// 单击事件
		private void onClickEvent(View v) {
			switch (v.getId()) {
			case R.id.iv_single_feed_back:
				onSingleFeedBack();
				break;
			case R.id.rl_title:
			case R.id.iv_single_feed_head:
			case R.id.tv_single_feed_name:
			case R.id.tv_single_feed_publish_time:
				onSingleFeedTitle();
			case R.id.iv_single_feed_photo:
				break;
			case R.id.iv_single_feed_reply_photo:
				onSingFeedReplyPhoto();
				break;
			case R.id.tv_single_feed_content:
				break;
			case R.id.iv_single_feed_like:
				onSingleFeedLike();
				break;
			case R.id.tv_single_feed_likecount:
				onSingleFeedLikeCount();
				break;
			case R.id.iv_single_feed_more:
				onSingleFeedMore();
				break;
			case R.id.iv_single_feed_take_photo:
				onSingleTakePhoto();
				break;
			case R.id.tv_single_feed_reply_photo_count:
				break;
			}
		}

		private void requestLike(final boolean isLike) {
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("X-Kata-Token", token);
			String fid = feedDataItem.id;
			JSONObject json = new JSONObject();
			try {
				json.put("fid", fid);
				json.put("is_like", isLike + "");// 点赞
			} catch (JSONException e) {
				e.printStackTrace();
			}
			EmoRequest request = new EmoStringRequest(AppConfig.ServiceUrl.strLikeFeed, headers, json.toString(),
					new IEmoHttpListener() {
						@Override
						public void onSuccess(String tag, String requestUrl, String requestResult) {
							if (TextUtils.equals(AppConfig.ServiceUrl.strLikeFeed, requestUrl)) {
								try {
									JSONObject json = new JSONObject(requestResult);
									int errno = json.getInt("errno");
									if (errno == 0) {
										feedDataItem.is_like = isLike;
									}
								} catch (JSONException e) {
									e.printStackTrace();
								}

							}
						}

						@Override
						public void onFailed(String tag, String requestUrl, int errorNo, String requestResult) {
						}
					});
			EmoHttpClient.getInstance().doPost(request);

		}

		private void onSingleTakePhoto() {

		}

		private void onSingleFeedMore() {

		}

		private void onSingleFeedLikeCount() {
			Map<String, String> headers = new HashMap<String, String>();
			if (!TextUtils.isEmpty(token)) {
				headers.put("X-Kata-Token", token);
			}
			JSONObject json = null;
			String fid = feedDataItem.id;
			json = new JSONObject();
			try {
				json.put("fid", fid);
			} catch (JSONException e) {
				e.printStackTrace();
				return;
			}

			EmoRequest request = new EmoStringRequest(AppConfig.ServiceUrl.strGetLikerList, headers, json.toString(),
					new IEmoHttpListener() {
						@Override
						public void onSuccess(String tag, String requestUrl, String requestResult) {
							Intent intent = new Intent();
							intent.setClass(context, LikeListActivity.class);
							intent.putExtra("like", requestResult);
							context.startActivity(intent);
						}

						@Override
						public void onFailed(String tag, String requestUrl, int errorNo, String requestResult) {
						}
					});
			EmoHttpClient.getInstance().doPost(request);
		}

		private void onSingleFeedLike() {
			int sourceId = 0;
			int count = Integer.parseInt(tvLikeCount.getText().toString());
			if (feedDataItem.is_like) {
				count--;
				sourceId = R.drawable.heart_white;
			} else {
				count++;
				sourceId = R.drawable.heart_red;
			}
			tvLikeCount.setText(count + "");
			ivLike.setImageResource(sourceId);
			feedDataItem.is_like = !feedDataItem.is_like;
			requestLike(!feedDataItem.is_like);
		}

		private void onSingFeedReplyPhoto() {
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("X-Kata-Token", token);
			JSONObject json = new JSONObject();
			try {
				json.put("fid", feedDataItem.repost.repost_id);
			} catch (JSONException e) {
				e.printStackTrace();
			}

			EmoRequest request = new EmoStringRequest(AppConfig.ServiceUrl.strGetFeed, headers, json.toString(),
					new IEmoHttpListener() {
						@Override
						public void onSuccess(String tag, String requestUrl, String requestResult) {
							JSONObject json = null;
							try {
								json = new JSONObject(requestResult);
							} catch (JSONException e) {
								e.printStackTrace();
							}
							int errno = -1;
							try {
								errno = json.getInt("errno");
							} catch (JSONException e) {
								e.printStackTrace();
							}
							if (errno == 0) {
								Intent intent = new Intent();
								intent.setClass(context, SingleFeedActivity.class);
								String strJson = "";
								try {
									strJson = json.getString("data").toString();
								} catch (JSONException e) {
									e.printStackTrace();
								}
								intent.putExtra("feed_data_item", strJson);
								context.startActivity(intent);
							}
						}

						@Override
						public void onFailed(String tag, String requestUrl, int errorNo, String requestResult) {

						}
					});
			EmoHttpClient.getInstance().doPost(request);
		}

		private void onSingleFeedTitle() {

			// 跳转到个人feed
			Intent intent = new Intent();
			intent.setClass(context, PersonalFeedActivity.class);
			intent.putExtra("uid", feedDataItem.user.id); // 用户id
			intent.putExtra("fid", feedDataItem.id); // feed id
			intent.putExtra("X-Kata-Token", token); // token
			context.startActivity(intent);
		}

		private void onSingleFeedBack() {
			((SingleFeedActivity) context).finish();
		}
	}
}
