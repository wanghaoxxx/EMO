package com.emolabs.im.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.emolabs.im.R;
import com.emolabs.im.config.EmoApplication;
import com.emolabs.im.struct.FeedDataItem;
import com.nostra13.universalimageloader.core.ImageLoader;

public class FindAlbumsAdapter extends RecyclerView.Adapter<FindAlbumsAdapter.ViewHolder>{
	private static final String TAG = "FindAlbumsAdapter";
	protected Context context;
	protected ArrayList<FeedDataItem> itemList;
	
	public FindAlbumsAdapter(Context context,ArrayList<FeedDataItem> itemList) {
		this.context = context;
		this.itemList = itemList;
	}
	class ViewHolder extends RecyclerView.ViewHolder{
		public ImageView ivHPhotos;
		public ViewHolder(View view) {
			super(view);
			ivHPhotos = (ImageView) view.findViewById(R.id.iv_horizontal_list_item_photo);
		}
	}
	
	public void setFeedDataItemList(ArrayList<FeedDataItem> itemList){
		this.itemList = itemList;
	}

	@Override
	public int getItemCount() {
		return itemList != null ? itemList.size() : 0;
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int position) {
		String imgUrl = itemList.get(position).image.url;
		if(!TextUtils.isEmpty(imgUrl)){
			ImageLoader.getInstance().displayImage(imgUrl, viewHolder.ivHPhotos, EmoApplication.options_normal);
		}
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(context);
		 View view = inflater.inflate(R.layout.discover_horizontal_list_item, parent, false);
	     ViewHolder vh = new ViewHolder(view);
		return vh;
	}


}



