package com.emolabs.im.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.emolabs.im.R;
import com.emolabs.im.struct.FeedDataItem;
import com.emolabs.im.struct.Find;
import com.emolabs.im.struct.FindDataAlbums;

public class FindListAdapter extends RecyclerView.Adapter<FindListAdapter.ViewHolder> {
	protected Context context;
	protected Find find;
	protected ArrayList<FindDataAlbums> findDataAlbums;
	
	public FindListAdapter(Context context,Find find){
		this.context = context;
		this.find = find;
		findDataAlbums = this.find.data.albums;
	}
	class ViewHolder extends RecyclerView.ViewHolder{
		TextView tvDiscoverLabel;
		TextView tvDiscoverLabelDesc;
		RecyclerView hlHorizontalListView;
		LinearLayoutManager mLayoutManager;
		public ViewHolder(View view) {
			super(view);
			mLayoutManager = new LinearLayoutManager(context);
			mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
			tvDiscoverLabel = (TextView) view.findViewById(R.id.tv_discover_label);
			tvDiscoverLabelDesc = (TextView) view.findViewById(R.id.tv_discover_label_desc);
			hlHorizontalListView = (RecyclerView) view.findViewById(R.id.rv_horizontal_list_images);
			hlHorizontalListView.setLayoutManager(mLayoutManager);
		}
	}
	
	@Override
	public int getItemCount() {
		return findDataAlbums != null ? findDataAlbums.size() : 0;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		String label = findDataAlbums.get(position).title;
		String labelDesc = findDataAlbums.get(position).subtitle;
		if(!TextUtils.isEmpty(label)){
			holder.tvDiscoverLabel.setText(label);
		}
		if(!TextUtils.isEmpty(labelDesc)){
			holder.tvDiscoverLabelDesc.setText(labelDesc);
		}
		ArrayList<FeedDataItem> feedList = findDataAlbums.get(position).list;
		if(feedList != null && feedList.size() > 0){
			holder.hlHorizontalListView.setAdapter(new FindAlbumsAdapter(context, feedList));
		}
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.discover_list_item, parent, false);
		ViewHolder vh = new ViewHolder(view);
		return vh;
	}

}
