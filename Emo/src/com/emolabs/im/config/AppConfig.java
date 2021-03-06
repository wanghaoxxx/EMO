package com.emolabs.im.config;


public class AppConfig {
	
	//-------------网络请求标示---------
    public static final int INIT=0;
    public static final int REFRESH=1;
    public static final int MORE=2;
    
	public static class ServiceUrl{
		public static String baseUrl = "http://120.24.60.21/kata";
		
		
		/*
		 * 用户系统
		 * */
		//手机号是否注册过
		public static String strPhoneIsRegistered = baseUrl + "/u/r/is_reg.php";
		
		//手机号注册
		public static String strPhoneRegister = baseUrl + "/u/r/phone_reg.php";
		
		//获取验证码
		public static String strIdentifyCode = baseUrl + "/u/r/get_code.php";
		
		//上传个人信息
		public static String strUploadUserInfo = baseUrl + "/u/r/upload_user.php";
		
		//登录
		public static String strLogin = baseUrl + "/u/l/login.php";
		
		//自动登录
		public static String strAutoLogin = baseUrl + "/u/l/auto_login.php";
		
		//关注取消
		public static String strFollowCancel = baseUrl + "/u/follow.php";
		
		//获取粉丝列表
		public static String strFollowerList = baseUrl + "/u/get_follower_list.php";
		
		//获取关注列表
		public static String strFollowingList = baseUrl + "/u/get_following_list.php";
		
		//获取通讯录好友列表
		public static String strLinkmanList = baseUrl + "/u/get_linkman_list.php";
		
		//获取微博好友列表
		public static String strWeiboFriendList = baseUrl + "/u/get_weibo_friend_list.php";
		
		//获取user详细信息
		public static String strUserInfo = baseUrl + "/u/get_user_info.php";
		
		//获取用户feed流纪录
		public static String strUserFeedList = baseUrl + "/u/get_feed_list.php";
		
		//更新个人信息
		public static String strUpdateUserInfo = baseUrl + "/u/update_user_info.php";
		
		//绑定微博
		public static String strBindWeibo = baseUrl + "/u/bind_weibo.php";
		
		//退出登录
		public static String strLogout = baseUrl + "/u/l/logout.php";
		
		//获取推荐关注列表
		public static String strRecommandList = baseUrl + "/u/get_recommendation_list.php";
		
		//用户名是否存在
		public static String strIsNameExist = baseUrl + "/u/is_name_exist.php";
		
		//搜索用户
		public static String strSearchUser = baseUrl + "/u/search_user.php";
		
		//一键关注
		public static String strDirectlyFollow = baseUrl + "/u/directly_follow.php";
		
		//通过名字获取用户信息
		public static String strGetUserInfoByName = baseUrl + "/u/get_user_info_by_name.php";
		
		
		/*
		 * feed 系统
		 * 
		 * */
		//首页feed流
		public static String strGetFeedList = baseUrl + "/f/get_feed_list.php";
		
		//发布
		public static String strPublish = baseUrl + "/f/publish.php";
		
		//转发
		public static String strRepost = baseUrl + "/f/repost.php";
		
		//点赞
		public static String strLikeFeed = baseUrl + "/f/like_feed.php";
		
		//转发照片列表
		public static String strGetImageList = baseUrl + "/f/get_image_list.php";
		
		//获取单个feed
		public static String strGetFeed = baseUrl + "/f/get_feed.php";
		
		//获取点赞列表
		public static String strGetLikerList = baseUrl + "/f/get_liker_list.php";
		
		//获取upyun上传参数
		public static String strGetImageParam = baseUrl + "/get_image_param.php";
		
		//删除feed
		public static String strDeleteFeed = baseUrl + "/f/delete_feed.php";
		
		
		/*
		 * 消息系统
		 * 
		 * */
		
		//消息列表
		public static String strMessageList = baseUrl + "/m/get_message_list.php";
		
		//上传apns_token
		public static String strUploadApns = baseUrl + "/m/upload_apns.php";
		
		
		/*
		 * 发现系统
		 * */
		
		//发现首页
		public static String strFindInit = baseUrl + "/find/find_init.php";
		
		//获取标签下的feed
		public static String strGetFeedListByLabel = baseUrl + "/find/get_feed_list_by_label.php";
		
		//按位置获取feed
		public static String strGetFeedListByPosition = baseUrl + "/find/get_feed_list_by_position.php";
		
		//获取推荐标签
		public static String strGetRecommandLabel = baseUrl + "/find/get_recommended_label.php";
		
		
		
		
	}
	
	
	
	public interface What{
		public static final int SUCCESS=0;
		public static final int FAILED=-1;
		
		public static final int SUCCESS_USER_FEED_INFO_MORE = 1;
		
		//请求首页feed
		public static final int SUCCESS_GET_FEED_LIST = 2;
		public static final int FAILED_GET_FEED_LIST = 3;
		public static final int FAILED_NETWORK_GET_FEED_LIST = 4;
		
		//请求首页更多信息
		public static final int SUCCESS_GET_FEED_LIST_MORE = 5;
		public static final int FAILED_GET_FEED_LIST_MORE = 6;
		public static final int FAILED_NETWORK_ERROR_GET_FEED_LIST_MORE = 7;
		
		//请求点赞列表
		public static final int SUCCESS_GET_LIKE_LIST = 10;
		public static final int FAILED_GET_LIKE_LIST = 11;
		public static final int FAILED_NETWORK_GET_LIKE_LIST = 12;
		
	}
	
	public interface RESULT{
		public static final int RESULT_SUCCESS = 0;
		public static final int RESULT_FAILED = -1;
	}
	
	public interface Relation{
		public static final int NO_REGISTER = -1;	//此人未在平台上注册手机号
		public static final int NO_RELATION = 0;	//没有关系
		public static final int HE_FOLLOW_ME = 1;	//他关注我
		public static final int ME_FOLLOW_HE = 2;	//我关注他
		public static final int EACH_OTHER_FOLLOW = 3;//互相关注
	}
	
	
	
	

}
































