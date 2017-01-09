package com.emolabs.im.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.emolabs.im.R;
import com.emolabs.im.config.EmoApplication;
import com.emolabs.im.listener.MessageListItemOnClickListener;
import com.emolabs.im.listener.MessageListItemOnItemClickListener;
import com.emolabs.im.struct.EmoMessage;
import com.emolabs.im.struct.EmoMessageData;
import com.emolabs.im.struct.EmoMessageDataItem;
import com.emolabs.im.utils.EmoTimeUtils;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MessageListAdapter extends BaseAdapter {
	Context context;
	EmoMessage msg;
	EmoMessageData messageData;
	ArrayList<EmoMessageDataItem> messageDataItems;
	MessageListItemOnClickListener onClickListener;

	public MessageListAdapter(Context context, EmoMessage msg) {
		this.context = context;
		this.msg = msg;
		messageData = msg.data;
		messageDataItems = msg.data.message_list;
		onClickListener = new MessageListItemOnClickListener(context,msg);
	}

	class ViewHolder {
		ImageView ivHeader;
		TextView tvName;
		TextView tvTime;
		TextView tvDothings;
		ImageView ivPhoto;
	}

	@Override
	public int getCount() {
		return messageDataItems.size();
	}

	@Override
	public Object getItem(int position) {
		return messageDataItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.message_list_item, parent, false);
			holder = new ViewHolder();
			holder.ivHeader = (ImageView) convertView.findViewById(R.id.iv_msg_listitem_header);
			holder.tvName = (TextView) convertView.findViewById(R.id.tv_msg_listitem_name);
			holder.tvTime = (TextView) convertView.findViewById(R.id.tv_msg_listitem_time);
			holder.tvDothings = (TextView) convertView.findViewById(R.id.tv_msg_listitem_dothings);
			holder.ivPhoto = (ImageView) convertView.findViewById(R.id.iv_msg_listitem_photo);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		//为每个item中的子控件设置监听器和索引
		setOnClickListener(holder,onClickListener);
		holder.ivHeader.setTag(String.valueOf(position));
		holder.tvName.setTag(String.valueOf(position));
		holder.tvTime.setTag(String.valueOf(position));
		holder.tvDothings.setTag(String.valueOf(position));
		holder.ivPhoto.setTag(String.valueOf(position));
		
		//显示头像
		String url = messageDataItems.get(position).user.head;
		if (!TextUtils.isEmpty(url)) {
			ImageLoader.getInstance().displayImage(url, holder.ivHeader, EmoApplication.options_circle);
		}
		
		//显示姓名 
		String name = messageDataItems.get(position).user.name;
		if(!TextUtils.isEmpty(name)){
			holder.tvName.setText(name);
		}
		
		//显示发表时间
		String time = messageDataItems.get(position).time;
		String formatTime = EmoTimeUtils.formatTime(time);
		if(!TextUtils.isEmpty(formatTime)){
			holder.tvTime.setText(formatTime);
		}
		
		//显示dothings
		int type = Integer.parseInt(messageDataItems.get(position).type);
		String dothings = "";
		String imageUrl = null;
		if(type == 1){//点赞
			dothings = "关注了你";
			imageUrl = messageDataItems.get(position).feed.image.url;
		}else if(type == 2){//回复
			dothings = "回复了你";
			imageUrl = messageDataItems.get(position).feed.image.url;
		}else if(type == 3){//关注
			dothings = "赞了你的照片";
		}
		if(!TextUtils.isEmpty(dothings)){
			holder.tvDothings.setText(dothings);
		}
		
		//显示图片
		if(!TextUtils.isEmpty(imageUrl)){
			ImageLoader.getInstance().displayImage(imageUrl, holder.ivPhoto, EmoApplication.options_normal);
		}
		
		return convertView;
	}
	
	void setOnClickListener(ViewHolder holder,MessageListItemOnClickListener listener){
		holder.ivHeader.setOnClickListener(listener);
		holder.tvName.setOnClickListener(listener);
		holder.tvTime.setOnClickListener(listener);
		holder.tvDothings.setOnClickListener(listener);
		holder.ivPhoto.setOnClickListener(listener);
	}
}











