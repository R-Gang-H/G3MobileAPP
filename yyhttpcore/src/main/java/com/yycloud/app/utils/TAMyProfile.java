package com.yycloud.app.utils;


public class TAMyProfile {

	private static TAMyProfile myProfileInstance = null;

	public final static boolean USE_ENCRYPT_API = false;
	// 新浪微博======================================================

	/** 申请的appkey,修改成你自己的才能使用 **/
	public static String SINA_APP_KEY = "966196697";

	/** 申请的App Secret,修改成你自己的才能使用 **/
	public static String SINA_APP_SECRET = "3c407b593d07ffa6426cb058da4a9d2e";
	//云洋微信号 cloudyoungdata
	public static String WEIXIN_APP_ID = "wxa074b2e297d7bb58";
	public static String WEIXIN_SECRET = "c5b8c07cc8682b82141d99950557a1f5";
	// 回调地址，移动APP使用官方默认地址
	public static String SINA_REDIRECT_URL = "http://www.sina.com";

	// 新支持scope 支持传入多个scope权限，用逗号分隔，暂时用不上
	public static String SINA_SCOPE = "email,direct_messages_read,direct_messages_write,"
			+ "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
			+ "follow_app_official_microblog," + "invitation_write";

	public final static String http_user_agent = "Mozilla/5.0 (Android; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/1.0 Mobile/EC99E CUTV/A312 Safari/525.13";

	public static TAMyProfile getInstance() {
		if (myProfileInstance == null)
			myProfileInstance = new TAMyProfile();

		return myProfileInstance;
	}



}
