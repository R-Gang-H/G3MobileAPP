package com.yycloud.core.config;

public class ECMSConfig {
	public final static int PID = 2436;
	//资讯页面原地址：
	public static String BASE_URL2 = "http://112.124.119.190:8080/common/api/app/interface.jsp?";
	//资讯页面新服地址：
	public static String BASE_URL = "http://newsapi.yunyangdata.com:8080/cms/common/api/app/interface.jsp?";
	public static String appid = "1234567890";
	public static String appsecret = "abc123";
	public static String APPID_AND_APPSECRET = "&appid=" + appid
			+ "&appsecret=" + appsecret;
	public final static int start = 0;
	public final static int limit = 10;
	/**
	 * 获取栏目
	 */
	public final static String CONFIG_GET_COLUMN_LIST_URL = BASE_URL
			+ "func=getColumnList" + APPID_AND_APPSECRET;
	/**
	 * 根据栏目id获取栏目下面的内容
	 */
	public final static String CONFIG_GET_CONTENT_LIST_URL = BASE_URL
			+ "func=getContentList" + APPID_AND_APPSECRET;
}
