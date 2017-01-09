package com.emolabs.im.utils;

import android.text.TextUtils;

public class EmoStringUtils {
	
	/*
	 * 根据发布的内容，获取相应的标签
	 * */
	public static String[] getLabel(String content){
		if(TextUtils.isEmpty(content)){
			return null;
		}
		
		int pos1 = content.indexOf("#");
		int pos2 = content.lastIndexOf("#");
		if(pos1 == -1 || pos2 == -1){
			return null;
		}
		
		String strSub = content.substring(pos1, pos2);
		String[] labelArr = strSub.split("#");
		return labelArr;
	}
	
	/*
	 * 根据发布的内容，获取@的用户的名字
	 * */
	public static String getAtName(String content){
		if(content == null)
			return null;
		
		int pos = content.indexOf("@");
		if(pos != -1){
			String strSub = content.substring(pos);
			int pos1 = strSub.indexOf("@");
			int pos2 = strSub.indexOf(" ");
			return strSub.substring(pos1 + 1, pos2);
		}
		return null;
	}
	
	

}
