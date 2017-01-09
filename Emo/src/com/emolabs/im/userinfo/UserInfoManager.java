package com.emolabs.im.userinfo;

public class UserInfoManager {
	static UserInfoManager instance;
	private UserBasicInfo userBasicInfo;
	
	private UserInfoManager() {
		userBasicInfo = new UserBasicInfo();
	}
	
	public static UserInfoManager getInstance(){
		if(instance == null){
			instance = new UserInfoManager(); 
		}
		return instance;
	}
	
	public void setUserBasicInfo(UserBasicInfo userBasicInfo){
		this.userBasicInfo = userBasicInfo;
	}
	
	public UserBasicInfo getUserBasicInfo(){
		return userBasicInfo;
	}
}
