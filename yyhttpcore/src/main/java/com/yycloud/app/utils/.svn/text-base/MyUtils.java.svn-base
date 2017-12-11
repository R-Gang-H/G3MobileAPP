package com.yycloud.app.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.ThumbnailUtils;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtils {

	public static final int NETWORK_NONE = 0;
	public static final int NETWORK_WIFI = 1;
	public static final int NETWORK_MOBILE = 2;

	/**
	 * dip转px
	 * 
	 * @param context
	 * @param dipValue
	 * @return
	 */
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * px转dip
	 * 
	 * @param context
	 * @param pxValue
	 * @return
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 打印log
	 * 
	 * @param log
	 */
	public static void showLog(String log) {
	}

	/**
	 * @param duration
	 * @return 转换成时间
	 */
	public static String get_duration_string(int duration) {
		if (duration < 0)
			duration = 0;

		String s = "";

		if (duration < 60) // 秒

			s = String.format("%d秒", duration);
		else if (duration < 60 * 60) // 分钟
		{
			int minute = duration / 60;
			int sec = duration % 60;
			s = String.format("%d分%d秒", minute, sec);
		} else {
			int hour = duration / 3600;
			int minute = (duration % 3600) / 60;
			int sec = (duration % 3600) % 60;
			s = String.format("%d小时%d分%d秒", hour, minute, sec);
		}

		return s;
	}

	/**
	 * 从url中得到文件名
	 * 
	 * @param url
	 * @param bHasExt
	 * @return
	 */
	public static String get_filename_from_url(String url, boolean bHasExt) {
		String[] p1 = url.split("\\/", -1);
		if (p1.length <= 0)
			return "";

		String fname = p1[p1.length - 1];

		if (bHasExt)
			return fname;

		String[] p2 = fname.split("\\.", -1);
		if (p2.length <= 0)
			return "";

		return p2[0];
	}

	public static String get_filename_ext_from_url(String url) {
		String[] p1 = url.split("\\/", -1);
		if (p1.length <= 0)
			return "";

		String fname = p1[p1.length - 1];

		String[] p2 = fname.split("\\.", -1);
		if (p2.length <= 1)
			return "";

		return p2[1];
	}

	/**
	 * 得到格式化的当前时间
	 * 
	 * @return
	 */
	public static String getTimeMaskString() {
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddhhmmssSS");
		String date = sDateFormat.format(new java.util.Date());

		return date;
	}

	/**
	 * 得到毫秒数
	 *
	 * @return
	 */
	public static long getTickCount() {
		Date dt = new Date();
		return dt.getTime();
	}

	/**
	 * 判断sd卡是否存在
	 *
	 * @return
	 */
	public static boolean checkSdCardExists() {
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);

	}

	/**
	 * 判断字符串是否是合法url
	 * 
	 * @param url
	 * @return
	 */
	public static boolean isHttpUrl(String url) {
		return url != null && url.length() > 10
				&& url.substring(0, 7).compareToIgnoreCase("http://") == 0;

	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param filename
	 * @return
	 */
	public static boolean FileExist(String filename) {
		File file = new File(filename);
		return file.exists();

	}

	/**
	 * 判断文件是否私有化
	 * 
	 * @param context
	 * @param filename
	 * @return
	 */
	public static boolean PrivateFileExist(Context context, String filename) {
		if (filename == null || filename.length() < 1)
			return false;

		try {
			FileInputStream fin = context.openFileInput(filename);
			fin.close();
			return true;
		} catch (FileNotFoundException e) {

		} catch (Exception e) {

		}

		return false;
	}

	/**
	 * 判断图片是否能被读取
	 * 
	 * @param context
	 * @param filename
	 * @return
	 */
	public static BitmapDrawable loadPrivateBitmapFile(Context context,
													   String filename) {
		if (context == null || filename == null)
			return null;

		BitmapDrawable bd = null;
		try {
			FileInputStream fin = context.openFileInput(filename);

			BitmapDrawable bd1 = new BitmapDrawable(fin);
			fin.close();

			bd = bd1;
		} catch (Exception e) {

		}

		return bd;
	}

	/**
	 * 通过文件名返回图标对象
	 * 
	 * @param filename
	 * @return
	 */
	public static Bitmap loadBitmapFile(String filename) {
		return BitmapFactory.decodeFile(filename);
	}

	/**
	 * 将文件从一个文件夹copy到另外一个文件夹
	 * 
	 * @param context
	 * @param src_fname
	 * @param dst_fname
	 * @return
	 */
	public static boolean copyPrivateFileToSDCard(Context context,
												  String src_fname, String dst_fname) {
		try {
			FileInputStream fin = context.openFileInput(src_fname);

			FileOutputStream fout = new FileOutputStream(dst_fname);

			byte[] buf = new byte[1024];
			int len;

			while ((len = fin.read(buf)) > 0) {
				fout.write(buf, 0, len);
			}

			fout.close();
			fin.close();

			return true;
		} catch (Exception e) {

		}
		return false;
	}

	/**
	 * 判断文件是否存在,如果否就创建改文件
	 * 
	 * @param dirname
	 * @return
	 */
	public static boolean makeSureDirExists(String dirname) {
		File file = new File(dirname);
		if (file.exists())
			return true;

		return file.mkdirs();
	}

	/**
	 * 删除文件
	 * 
	 * @param filename
	 * @return
	 */
	public static boolean deleteFile(String filename) {
		File file = new File(filename);
		if (file.exists())
			return file.delete();

		return true;
	}

	/**
	 * 返回sd卡根路径
	 * 
	 * @return
	 */
	public static String getStorageRootPath() {
		return Environment.getExternalStorageDirectory().getPath();
	}

	public static String GetSizeString(long size) {
		DecimalFormat df = new DecimalFormat();
		String style = "0.0";// 定义要显示的数字的格式
		df.applyPattern(style);// 将格式应用于格式化器

		if (size > 1024 * 1024 * 1024) // G
		{
			// sprintf(ch, "%.02fG", (float)size / (float)(1024 * 1024 * 1024));
			double d = (double) size / (double) (1024 * 1024 * 1024);
			df.applyPattern("0.0G");
			return df.format(d);
		} else if (size > 1024 * 1024) // M
		{
			// sprintf(ch, "%.02fM", (float)size / (float)(1024 * 1024));
			double d = (double) size / (double) (1024 * 1024);
			df.applyPattern("0.0M");
			return df.format(d);
		} else if (size > 1024) {

			// sprintf(ch, "%.02fK", (float)size / (float)1024);
			double d = (double) size / (double) 1024;
			df.applyPattern("0.0K");
			return df.format(d);
		} else {

			// sprintf(ch, "%.0fB", (float)size);
			double d = size;
			df.applyPattern("0.0B");
			return df.format(d);
		}
	}

	public static int getNetworkType(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);// 获取系统的连接服务
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();// 获取网络的连接情况

		if (activeNetInfo != null) {

			if (activeNetInfo.isAvailable()) {
				if (activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
					// 判断WIFI网
					return NETWORK_WIFI;
				} else if (activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
					// 判断3G网
					return NETWORK_MOBILE;
				}

				return NETWORK_MOBILE;
			}
		}

		return NETWORK_NONE;
	}

	public static boolean isValidURLString(String urlString) {
		if (urlString == null)
			return false;

		String s = urlString.toLowerCase();

		return s.startsWith("http://");

	}

	public static boolean isSDCardMounted() {
		String state = Environment.getExternalStorageState();
		return Environment.MEDIA_MOUNTED.equals(state);

	}

	/**
	 * 保存图片到指定路径，指定文件名
	 * 
	 * @param bitmap
	 * @param dir
	 *            如: /sdcard/xxx
	 * @param name
	 *            如： temp.jpg
	 * @return
	 */
	public static boolean saveBitmap(Bitmap bitmap, String dir, String filename) {
		try {
			File fileDir = new File(dir);
			File file = new File(dir + "/" + filename);
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}

			if (!file.exists()) {
				file.createNewFile();
			}

			if (bitmap != null) {
				BufferedOutputStream bos = new BufferedOutputStream(
						new FileOutputStream(file));
				bitmap.compress(Bitmap.CompressFormat.JPEG, 75, bos);
				bos.flush();
				bos.close();

				return true;
			}
		} catch (Exception e) {
			//
		}

		return false;
	}

	/**
	 * 生成缩略图
	 * 
	 * @param source
	 * @param width
	 * @param height
	 * @return
	 */
	public static Bitmap extractThumbnail(Bitmap source, int width, int height) {
		int options = ThumbnailUtils.OPTIONS_RECYCLE_INPUT;
		// 利用Bitmpap对象创建缩略图<br>
		return ThumbnailUtils.extractThumbnail(source, width, height, options);
	}

	/**
	 * 根据路径，载入bitmap
	 * 
	 * @param filepath
	 *            文件路径，如：/sdcard/xxx/aa.jpg
	 * @return
	 */
	public static Bitmap loadBitmap(String filepath) {
		File file = new File(filepath);
		// 检查图片是否存在
		if (file.exists()) {
			Bitmap bitmap = BitmapFactory.decodeFile(filepath, null);
			return bitmap;
		}

		return null;
	}

	/**
	 * 去除由于采用了UTF8-BOM格式编码而引入的编码头
	 * @param jsonString
	 * @return
	 */
	public static String JSONTokener(String jsonString) {
		// consume an optional byte order mark (BOM) if it exists
		if (jsonString != null && jsonString.startsWith("\ufeff")) {
			jsonString = jsonString.substring(1);
		}
		return jsonString;
	}
}
