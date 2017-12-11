package com.yycloud.core.cache;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 配置信息,测试环境:config_test,开发环境:config
 */
public class GlobalConst {

	/**
	 * 配置信息,测试环境:config_test,开发环境:config config_dev 开发环境 config_test 测试环境 config
	 * 现网环境
	 */
	public static String CONFIG_FILE_NAME = "config";

	public static boolean DEBUG_MODE = true;

	public static int count = 1;

	public static Context mCtx;

	public static String MUSIC = "play";

	public static String IMSI;

	public static String CA = "AppMill";

	public static String ACTION_STRING_LOCATE = "locate:";
	public static String ACTION_STRING_JS = "javascript:";
	public static String ACTION_STRING_INTERNAL = "internal:";
	public static String ACTION_STRING_UI = "ui:";

	public static String ACTION_TARGET_SELF = "_SELF";// 本webview打开
	public static String ACTION_TARGET_LOC = "_TARGET";// 本activity新webview打开
	public static String ACTION_TARGET_NEW = "_ACTIVITY";// 新activity新webview打开

	public static String INTENT_ACTION_SINGLEPIC = "single_pic";
	public static String URL_SINGLE_PIC_FILED = "cid=";
	public static String INTENT_ACTION_URL = "home_url";
	public static String INTENT_WEBVIEW_LOCATION = "webview_location";
	public static String INTENT_PIC_INDEX = "pic_index";

	public static String ACTION_CLOSE_ALL = "action_close_all";
	public static String ACTION_VEDIO_BACK = "action_vedio_closed";

	public static int UI_ITEM_BACK = 0;
	public static int UI_ITEM_HEADER = 1;
	public static int UI_ITEM_FOOT = 2;
	public static int UI_ITEM_POPUP = 3;
	public static int UI_ITEM_SHARE = 4;
	public static int UI_ITEM_PLAYER = 5;
	public static int UI_ITEM_GALLERY = 6;
	public static int UI_ITEM_BGIMAGE = 7;
	public static int UI_ITEM_MASK = 8;
	public static int UI_ITEM_WEBVIEW = 9;

	public static int UI_ITEM_VIDEO = 10;
	public static int UI_ITEM_CONFIG = 11;

	public static String URI_TYPE = "uri_type";
	public static String URI_DATA = "uri_data";
	public static int URI_TYPE_URL_LOC = 0;
	public static int URI_TYPE_DATA = 1;
	public static int URI_TYPE_URL_NET = 2;

	// 资源请求状态
	public static int WEBCACHE_NETWORK = 0;// 先使用本地，然后下载,下载后通知更新
	public static int MANIFEST_CACHE = 1;// 直接使用本地
	public static int MANIFEST_NETWORK = 2;// 不使用本地，等下载了之后，然后使用网络的

	public static String CONFIG_CACHLIST = "app.cache.manifest?appid=";
	public static String CONFIG_GLOBAL = "layout.global.json?appid=";
	public static String CONFIG_NODE = "layout.node.json?";

	public static String AUDIOCONTROLTYPE = "audio_control_type";
	public static int SETSONGNAME = 0;
	public static int SETDURATION = 1;
	public static int SETSTATUS = 2;
	public static int PLAYERROR = 3;
	public static int PLAYBUFFER = 4;
	public static int ELSE = -1;

	public static int PLAY_STATE_STOPPED = 0;
	public static int PLAY_STATE_PLAYING = 1;
	public static int PLAY_STATE_PAUSED = 2;
	public static int PLAY_STATE_SEEK = 3;

	public static String SONGNAME_KEY = "songname";
	public static String ERROR_SONGNAME_KEY = "err_songname";
	public static String HREF_KEY = "href";
	public static String TARGET_KEY = "target";
	public static String STATUS_KEY = "status";
	public static String RESID_KEY = "resid";
	public static String DURATION_KEY = "duration";

	public static String CONFIRM_DIALOG = "_CONFIRM";
	public static String ALERT_DIALOG = "_ALERT";

	public static String ACTION_AUDIO = "com.wacosoft.appmill.AudioControlReceiver";// audio广播

	public static final int HTTP_CONNECTION_TIMEOUT = 15 * 1000;// http请求头设置的超时时间
	public static final int HTTP_READ_TIMEOUT = 15 * 1000;

	public static int SCREEN_WIDTH = 0;// 屏幕的宽度
	public static int SCREEN_HEIGHT = 0;// 屏幕的高度
	public static float DENSITY = 1.0f;// 密度

	public static final String PLAY_LIST = "play_list";// 播放列表
	public static final String PLAY_ID = "play_id";// 当前播放的音乐id

	public static boolean isPlaying = false;// 音乐播放器是否在播放
	public static boolean isExpand = false;
	public static int musicnum=1;// 歌曲总数

	public static String USERAGENT = "unKnown";

	// 切换动画类型
	public static String anim_style_popup = "popup";
	public static String anim_style_slide = "slide";

	// activity request code
	public static int ACTIVITY_REQUEST_CODE = 1;
	// public static int activity_request_code_camera = 17,18,33,34,49,50;

	public static String STRING_RETURN_DATA = "return_data";
	public static String STRING_CALLBACK_FUN = "onCallback";

	/**
	 * 解析十六进制串
	 * 
	 * @param str
	 * @return
	 */
	public static int parseHexStr(String str) {
		if (str == null) {
			return -1;
		} else if (str.length() == 0) {
			return 0;
		}

		if (str.startsWith("0x") || str.startsWith("0X")) {
			str = str.substring(2);
		}

		if (str.startsWith("#")) {
			str = str.substring(1);
		}

		int len = str.length();
		int sum = 0;

		for (int i = 0; i < len; i++) {

			char c = str.charAt(len - 1 - i);

			int n = Character.digit(c, 16);

			sum += n * (1 << (4 * i));

		}
		return sum;
	}

	/**
	 * 把Url加密
	 * 
	 * @param plainText
	 * @return
	 */
	public static String Md5(String plainText) {
		if (plainText == null) {
			plainText = "";
		}
		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取网页的真实url，非跳转前地址
	 * 
	 * @param srcUrl
	 * @return
	 * @throws IOException
	 */
	public static String getRealUrl(String srcUrl) throws IOException {
		URL url = new URL(srcUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.getResponseCode();
		String realUrl = conn.getURL().toString();
		conn.disconnect();
		return realUrl;
	}

	public static String getUserAgent(Context context) {
		return USERAGENT;
	}

	public static void initUserAgent(Context context, String initialUserAgent) {
		if (initialUserAgent == null) {
			return;
		}
		String versionName = getAppVersionName(context);
		USERAGENT = initialUserAgent;
		USERAGENT = USERAGENT + " App(iMusicApp/V" + versionName + ") IMSI_"
				+ GlobalConst.getIMSI();
	}

	/**
	 * 获取versionName信息
	 * 
	 * @return
	 */
	public static String getAppVersionName(Context context) {
		String versionName = "";
		try {
			// ---get the package info---
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionName = pi.versionName;
			if (versionName == null || versionName.length() <= 0) {
				return "";
			}
		} catch (Exception e) {
			Log.v("VersionInfo", "Exception", e);
		}
		return versionName;
	}

	/**
	 * 获取IMSI号
	 * 
	 * @return
	 */
	public static String getIMSI() {
		if (IMSI == null || IMSI.equals("")) {
			return new String("unknown");
		} else {
			return IMSI;
		}
	}

	/**
	 * 判断字符串是不是由数字组成的串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDigit(String str) {
		int size = str.length();
		if (0 == size) {
			return false;
		}

		for (int i = 0; i < size; i++) {
			char c = str.charAt(i);
			if (!Character.isDigit(c)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 枚举屏幕类型
	 * 
	 * @author user
	 * 
	 */
	enum ScreenType {
		NORMAL, LARGE, SMALL
	}

	public static ScreenType SCREEN_TYPE = ScreenType.NORMAL;
	public static boolean IS_LAGGE_SCREEN = false;
	public static boolean IS_NORMAL_SCREEN = false;
	public static boolean IS_SMALL_SCREEN = false;

	/**
	 * 保存屏幕的宽高作为全局参数，并设置SCREEN_TYPE(屏幕类型）和屏幕分类
	 * 
	 * @param w
	 * @param h
	 */
	public static void setScreenSize(int w, int h) {
		SCREEN_WIDTH = w;
		SCREEN_HEIGHT = h;

		if (w >= 480 && h >= 800) {
			SCREEN_TYPE = ScreenType.LARGE;
			IS_LAGGE_SCREEN = true;
		} else if (w >= 320 && h >= 480) {
			SCREEN_TYPE = ScreenType.NORMAL;
			IS_NORMAL_SCREEN = true;
		} else if (w >= 240 && h >= 320) {
			SCREEN_TYPE = ScreenType.SMALL;
			IS_SMALL_SCREEN = true;
		} else {
			SCREEN_TYPE = ScreenType.NORMAL;
			IS_NORMAL_SCREEN = true;
		}
	}

	/**
	 * 获取屏幕的物理尺寸
	 * 
	 * @param dimension
	 * @return
	 */
	public static int getDimension(int dimension) {
		return Math.round(DENSITY * dimension);
	}

	/**
	 * 检查外置存贮是否存在.
	 * 
	 * @return boolean
	 */
	public static boolean checkStore() {
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);

	}
}
