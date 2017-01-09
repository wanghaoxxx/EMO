package com.emolabs.im.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.emolabs.im.R;
import com.emolabs.im.config.AppConfig;
import com.emolabs.im.config.EmoApplication;
import com.emolabs.im.struct.Like;
import com.emolabs.im.struct.LikeData;
import com.emolabs.im.struct.LikeDataItem;
import com.nostra13.universalimageloader.core.ImageLoader;

public class LikeListAdapter extends BaseAdapter {
	Context context;
	Like like;
	LikeData likeData;
	ArrayList<LikeDataItem> items;
	String uid;

	class ViewHolder {
		ImageView ivHeader; // 头像
		TextView tvName; // 名称
		ImageView ivFollow; // 关注
	}

	public LikeListAdapter(Context context, Like like) {
		this.context = context;
		this.like = like;
		this.likeData = like.data;
		this.items = like.data.list;
		SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		this.uid = sp.getString("uid", null);
	}

	@Override
	public int getCount() {
		return items != null ? items.size() : 0;
	}

	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.likelist_list_item, parent, false);
			holder.ivHeader = (ImageView) convertView.findViewById(R.id.iv_header);
			holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
			holder.ivFollow = (ImageView) convertView.findViewById(R.id.iv_follow);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.ivHeader.setTag(position + "");
		holder.tvName.setTag(position + "");
		holder.ivFollow.setTag(position + "");
		
		//设置监听器
		setWidgetOnClickListener(holder);
		
		LikeDataItem item = items.get(position);
		
		//显示头像
		ImageLoader.getInstance().displayImage(item.user.head, holder.ivHeader, EmoApplication.options_circle);
		
		//显示名称
		holder.tvName.setText(item.user.name);
		
		//显示关注
		SharedPreferences sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		String uid = sp.getString("uid", null);
		if(TextUtils.equals(uid, item.user.id)){
			holder.ivFollow.setVisibility(View.INVISIBLE);
			holder.ivFollow.setClickable(false);
		}else{
			holder.ivFollow.setVisibility(View.VISIBLE);
			holder.ivFollow.setClickable(true);	
			
			switch (item.relation) {
			case AppConfig.Relation.NO_REGISTER:		//此手机号没有在平台上注册
				break;
			case AppConfig.Relation.NO_RELATION:		//没有关系
			case AppConfig.Relation.HE_FOLLOW_ME:		//他关注我
				//没有关注的,显示关注
				holder.ivFollow.setImageResource(R.drawable.follow_normal);
				break;
			case AppConfig.Relation.ME_FOLLOW_HE:		//我关注他
			case AppConfig.Relation.EACH_OTHER_FOLLOW:	//互相关注 
				//已关注,显示已关注 
				holder.ivFollow.setImageResource(R.drawable.follow_already);
			}
		}
		return convertView;
	}

	private void setWidgetOnClickListener(ViewHolder holder) {
		holder.ivFollow.setOnClickListener(new OnWidgetClickListener(holder, items));
	}
	
	//listitem中的小控件的监听器
	class OnWidgetClickListener implements OnClickListener{
		ViewHolder holder;
		ArrayList<LikeDataItem> itemLists;
		
		public OnWidgetClickListener(ViewHolder holder,ArrayList<LikeDataItem> itemLists){
			this.holder = holder;
			this.itemLists = itemLists;
		}
		
		@Override
		public void onClick(View v) {
			if(v.getId() == R.id.iv_follow){
				Log.i("like","widget follow");
			}
		}
	}
}














