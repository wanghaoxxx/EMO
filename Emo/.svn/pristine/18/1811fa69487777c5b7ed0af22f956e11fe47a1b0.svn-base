package com.emolabs.im.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EmoTimeUtils {
	public static final long MILLIS_PER_MINUTE = 60;
	public static final long MILLIS_PER_HOUR = MILLIS_PER_MINUTE * 60;
	public static final long MILLIS_PER_DAY = MILLIS_PER_HOUR * 24;
	
	public static String formatTime(long millis){
		long curMillis = System.currentTimeMillis() / 1000;
		long periodMillis = curMillis - millis;
		String strFormatTime = null;
		if(periodMillis >= MILLIS_PER_DAY){
			long t = periodMillis / MILLIS_PER_DAY;
			strFormatTime = String.valueOf(t) + "天前";
		}else if(periodMillis >= MILLIS_PER_HOUR){
			long t = periodMillis / MILLIS_PER_HOUR;
			strFormatTime = String.valueOf(t) + "小时前";
		}else if(periodMillis >= MILLIS_PER_MINUTE){
			long t = periodMillis / MILLIS_PER_MINUTE;
			strFormatTime = String.valueOf(t) + "分钟前";
		}else{
			strFormatTime = "刚刚";
		}
		return strFormatTime;
	}
	
	public static String formatTime(String millis){
		int m = Integer.parseInt(millis);
		return formatTime(m);
	}
	
	public static String getFileNameByTime(){
	      SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmssSSSS");  
          Date date = new Date(System.currentTimeMillis());  
          String filename = format.format(date); 
          return filename;
	}
}
