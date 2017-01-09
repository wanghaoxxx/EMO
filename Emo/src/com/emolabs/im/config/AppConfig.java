package com.emolabs.im.config;


public class AppConfig {
	
	//-------------���������ʾ---------
    public static final int INIT=0;
    public static final int REFRESH=1;
    public static final int MORE=2;
    
	public static class ServiceUrl{
		public static String baseUrl = "http://120.24.60.21/kata";
		
		
		/*
		 * �û�ϵͳ
		 * */
		//�ֻ����Ƿ�ע���
		public static String strPhoneIsRegistered = baseUrl + "/u/r/is_reg.php";
		
		//�ֻ���ע��
		public static String strPhoneRegister = baseUrl + "/u/r/phone_reg.php";
		
		//��ȡ��֤��
		public static String strIdentifyCode = baseUrl + "/u/r/get_code.php";
		
		//�ϴ�������Ϣ
		public static String strUploadUserInfo = baseUrl + "/u/r/upload_user.php";
		
		//��¼
		public static String strLogin = baseUrl + "/u/l/login.php";
		
		//�Զ���¼
		public static String strAutoLogin = baseUrl + "/u/l/auto_login.php";
		
		//��עȡ��
		public static String strFollowCancel = baseUrl + "/u/follow.php";
		
		//��ȡ��˿�б�
		public static String strFollowerList = baseUrl + "/u/get_follower_list.php";
		
		//��ȡ��ע�б�
		public static String strFollowingList = baseUrl + "/u/get_following_list.php";
		
		//��ȡͨѶ¼�����б�
		public static String strLinkmanList = baseUrl + "/u/get_linkman_list.php";
		
		//��ȡ΢�������б�
		public static String strWeiboFriendList = baseUrl + "/u/get_weibo_friend_list.php";
		
		//��ȡuser��ϸ��Ϣ
		public static String strUserInfo = baseUrl + "/u/get_user_info.php";
		
		//��ȡ�û�feed����¼
		public static String strUserFeedList = baseUrl + "/u/get_feed_list.php";
		
		//���¸�����Ϣ
		public static String strUpdateUserInfo = baseUrl + "/u/update_user_info.php";
		
		//��΢��
		public static String strBindWeibo = baseUrl + "/u/bind_weibo.php";
		
		//�˳���¼
		public static String strLogout = baseUrl + "/u/l/logout.php";
		
		//��ȡ�Ƽ���ע�б�
		public static String strRecommandList = baseUrl + "/u/get_recommendation_list.php";
		
		//�û����Ƿ����
		public static String strIsNameExist = baseUrl + "/u/is_name_exist.php";
		
		//�����û�
		public static String strSearchUser = baseUrl + "/u/search_user.php";
		
		//һ����ע
		public static String strDirectlyFollow = baseUrl + "/u/directly_follow.php";
		
		//ͨ�����ֻ�ȡ�û���Ϣ
		public static String strGetUserInfoByName = baseUrl + "/u/get_user_info_by_name.php";
		
		
		/*
		 * feed ϵͳ
		 * 
		 * */
		//��ҳfeed��
		public static String strGetFeedList = baseUrl + "/f/get_feed_list.php";
		
		//����
		public static String strPublish = baseUrl + "/f/publish.php";
		
		//ת��
		public static String strRepost = baseUrl + "/f/repost.php";
		
		//����
		public static String strLikeFeed = baseUrl + "/f/like_feed.php";
		
		//ת����Ƭ�б�
		public static String strGetImageList = baseUrl + "/f/get_image_list.php";
		
		//��ȡ����feed
		public static String strGetFeed = baseUrl + "/f/get_feed.php";
		
		//��ȡ�����б�
		public static String strGetLikerList = baseUrl + "/f/get_liker_list.php";
		
		//��ȡupyun�ϴ�����
		public static String strGetImageParam = baseUrl + "/get_image_param.php";
		
		//ɾ��feed
		public static String strDeleteFeed = baseUrl + "/f/delete_feed.php";
		
		
		/*
		 * ��Ϣϵͳ
		 * 
		 * */
		
		//��Ϣ�б�
		public static String strMessageList = baseUrl + "/m/get_message_list.php";
		
		//�ϴ�apns_token
		public static String strUploadApns = baseUrl + "/m/upload_apns.php";
		
		
		/*
		 * ����ϵͳ
		 * */
		
		//������ҳ
		public static String strFindInit = baseUrl + "/find/find_init.php";
		
		//��ȡ��ǩ�µ�feed
		public static String strGetFeedListByLabel = baseUrl + "/find/get_feed_list_by_label.php";
		
		//��λ�û�ȡfeed
		public static String strGetFeedListByPosition = baseUrl + "/find/get_feed_list_by_position.php";
		
		//��ȡ�Ƽ���ǩ
		public static String strGetRecommandLabel = baseUrl + "/find/get_recommended_label.php";
		
		
		
		
	}
	
	
	
	public interface What{
		public static final int SUCCESS=0;
		public static final int FAILED=-1;
		
		public static final int SUCCESS_USER_FEED_INFO_MORE = 1;
		
		//������ҳfeed
		public static final int SUCCESS_GET_FEED_LIST = 2;
		public static final int FAILED_GET_FEED_LIST = 3;
		public static final int FAILED_NETWORK_GET_FEED_LIST = 4;
		
		//������ҳ������Ϣ
		public static final int SUCCESS_GET_FEED_LIST_MORE = 5;
		public static final int FAILED_GET_FEED_LIST_MORE = 6;
		public static final int FAILED_NETWORK_ERROR_GET_FEED_LIST_MORE = 7;
		
		//��������б�
		public static final int SUCCESS_GET_LIKE_LIST = 10;
		public static final int FAILED_GET_LIKE_LIST = 11;
		public static final int FAILED_NETWORK_GET_LIKE_LIST = 12;
		
	}
	
	public interface RESULT{
		public static final int RESULT_SUCCESS = 0;
		public static final int RESULT_FAILED = -1;
	}
	
	public interface Relation{
		public static final int NO_REGISTER = -1;	//����δ��ƽ̨��ע���ֻ���
		public static final int NO_RELATION = 0;	//û�й�ϵ
		public static final int HE_FOLLOW_ME = 1;	//����ע��
		public static final int ME_FOLLOW_HE = 2;	//�ҹ�ע��
		public static final int EACH_OTHER_FOLLOW = 3;//�����ע
	}
	
	
	
	

}































