package com.yycloud.app.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 首选项工具类
 * 
 * @author cstdingran@gmail.com
 * 
 */
public class TAPreferenceUtil {

	private static final String PREFERENCE_NAME = "WEIBO_SDK_DEMO";
	private static final String loginFile = "login";
	private static final String PROFILE_NAME = "my.config";
	private static TAPreferenceUtil preferenceUtil;
	public static final String REMEMBER_USER = "remember_user";
	public static final String REMEMBER_PSD = "remember_psd";
	public static final String IS_REMEMBER_USER = "is_remember_user";
	public static final String IS_REMEMBER_PSD = "is_remember_psd";
	public static final String IS_AUTOLOGIN = "is_autologin";
	public static final String IS_SHOW_PWD = "is_show_pwd";
	public static final String IS_COMFIRM_PWD = "is_comfirm_pwd";
	public static final String IS_CHANGE_PWD = "is_change_pwd";

	private SharedPreferences sp, loginsp;

	private Editor ed, logined;

	private TAPreferenceUtil(Context context) {
		init(context);
	}

	public void init(Context context) {
		if (sp == null || ed == null) {
			try {
				sp = context.getSharedPreferences(PREFERENCE_NAME, 0);
				ed = sp.edit();
			} catch (Exception e) {
			}
		}
		if (loginsp == null || logined == null) {
			try {
				loginsp = context.getSharedPreferences(loginFile, 0);
				logined = loginsp.edit();
			} catch (Exception e) {
			}
		}
	}

	public static TAPreferenceUtil getInstance(Context context) {
		if (preferenceUtil == null) {
			preferenceUtil = new TAPreferenceUtil(context);
		}
		return preferenceUtil;
	}

	public void saveUser(String username) {
		logined.putString("username", username);
		logined.commit();
	}

	public String getUser() {
		String user = loginsp.getString("username", "");
		return user;
	}

	public void saveSessionToken(String session_token) {
		logined.putString("session_token", session_token);
		logined.commit();
	}

	public String getSessionToken() {
		String user = loginsp.getString("session_token", "");
		return user;
	}

	public void savePsd(String session_token) {
		logined.putString("psd", session_token);
		logined.commit();
	}

	public String getPsd() {
		String user = loginsp.getString("psd", "");
		return user;
	}

	public void saveLong(String key, long l) {
		ed.putLong(key, l);
		ed.commit();
	}

	public long getLong(String key, long defaultlong) {
		return sp.getLong(key, defaultlong);
	}

	public void saveBoolean(String key, boolean value) {
		ed.putBoolean(key, value);
		ed.commit();
	}

	public boolean getBoolean(String key, boolean defaultboolean) {
		return sp.getBoolean(key, defaultboolean);
	}

	public void saveInt(String key, int value) {
		if (ed != null) {
			ed.putInt(key, value);
			ed.commit();
		}
	}

	public int getInt(String key, int defaultInt) {
		return sp.getInt(key, defaultInt);
	}

	public String getString(String key, String defaultInt) {
		return sp.getString(key, defaultInt);
	}

	public String getString(Context context, String key, String defaultValue) {
		if (sp == null || ed == null) {
			sp = context.getSharedPreferences(PREFERENCE_NAME, 0);
			ed = sp.edit();
		}
		if (sp != null) {
			return sp.getString(key, defaultValue);
		}
		return defaultValue;
	}

	public void saveString(String key, String value) {
		ed.putString(key, value);
		ed.commit();
	}

	public void remove(String key) {
		ed.remove(key);
		ed.commit();
	}

	public void destroy() {
		sp = null;
		ed = null;
		preferenceUtil = null;
	}

	public static boolean isFirstStart(Context context) {
		String key = "apkisfirststart";
		SharedPreferences pre = context.getSharedPreferences(PROFILE_NAME,
				Context.MODE_WORLD_READABLE);

		int ret = pre.getInt(key, 1);
		if (ret == 1) {// 第一次加载
			SharedPreferences.Editor editor = context.getSharedPreferences(
					PROFILE_NAME, Context.MODE_WORLD_WRITEABLE).edit();
			editor.putInt(key, 0);
			editor.commit();
			return true;
		} else {
			return false;
		}
	}
}
