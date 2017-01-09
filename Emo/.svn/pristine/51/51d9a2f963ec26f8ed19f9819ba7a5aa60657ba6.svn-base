package com.emolabs.im.struct;

public class FeedManager {
	static FeedManager mInstance;
	public Feed feed;

	public static FeedManager getInstance() {
		if (mInstance == null) {
			synchronized (FeedManager.class) {
				if (mInstance == null) {
					mInstance = new FeedManager();
				}
			}
		}
		return mInstance;
	} 
}
