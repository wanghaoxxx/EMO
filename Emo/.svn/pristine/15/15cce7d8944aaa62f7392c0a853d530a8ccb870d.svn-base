package com.emolabs.im.struct;



public class FeedDataItem {
	
	public String id;
	public FeedItemUser user;
	public String publish_time;
	public String content;
	public int like_count;
	public String location;
	public boolean is_like;
	public int repost_count;
	public FeedItemImage image;
	public boolean is_repost;
	public FeedItemRepost repost;
	
	public static class FeedItemUser{
		public String head;
		public String name;
		public String id;
	}
	
	public static class FeedItemImage{
		public String url;
		public int direction;
	}
	
	public static class FeedItemRepost{
		public String repost_id;
		public FeedItemImage repost_image;
		public FeedItemImage original_image;
		public int count;
	}

}
