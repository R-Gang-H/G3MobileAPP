/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yycloud.core.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * This class holds our bitmap caches (memory and disk).
 */
public class ImageCache {
	private static final String TAG = "ImageCache";
	private static String PATH = "";
	private FileOutputStream fos = null;
	private FileInputStream fis = null;
	private BufferedOutputStream bos = null;
	private static ImageCache Singleton_Instance = null;
	private Context mCtx;
	private int FREE_SD_SPACE_NEEDED_TO_CACHE = 1024 * 1024 * 8; // 10MB
	private long mTimeDiff = 0;

	List<String> s_fileList = new ArrayList<String>();// 缓存清单

	public ImageCache(Context context) {
		super();
		this.mCtx = context;
		if (GlobalConst.checkStore()) {// sd卡如果存在，就把图片缓存在sd卡，并且把手机存储空间缓存的图片删除
			FREE_SD_SPACE_NEEDED_TO_CACHE = 30 * 1024 * 1024;
			createDirInSD();
			File dir = new File(mCtx.getFilesDir() + File.separator + "images");
			dir.delete();
		} else {// sd卡如果不存在，就把图片缓存在手机存储空间缓存
			File dir = new File(mCtx.getFilesDir() + File.separator + "images");
			if (!dir.exists()) {
				dir.mkdirs();
				PATH = dir.getPath();
			} else {
				PATH = dir.getPath();
			}
		}
		// freeSpaceOnSd();
		if ((s_fileList.size() == 0) && PATH.length() > 0) {
			// 初始化缓存清单
			File dir = new File(PATH);
			File[] files = dir.listFiles();
			if (files != null) {
				for (int i = 0; i < files.length; i++) {
					s_fileList.add(files[i].getName());
				}
			}
		}
	}

	public void createDirInSD() {
		// 获取扩展存储设备的文件目录
		File sdDIR = android.os.Environment.getExternalStorageDirectory();

		// 打开文件
		File myFile = new File(sdDIR.getAbsolutePath() + File.separator
				+ "shed" + File.separator + getFolderName() + File.separator
				+ "images");

		// 判断是否存在,不存在则创建
		if (!myFile.exists()) {
			myFile.mkdirs();
			PATH = myFile.getPath();
		} else {
			PATH = myFile.getPath();
		}
	}

	private String getFolderName() {
		String pkgName = mCtx.getPackageName();
		String[] names = pkgName.split("\\.");
		if (names != null && names.length > 0) {
			return names[names.length - 1];
		} else {
			return pkgName;
		}
	}

	public static ImageCache getInstance(Context ctx) {
		if (Singleton_Instance == null) {
			Singleton_Instance = new ImageCache(ctx);
		}
		return Singleton_Instance;
	}

	// 保存图片
	public synchronized void saveImage(String ImageUrl, final Bitmap bitmap) {
		try {
			if (bitmap == null) {
				Log.w(TAG, " trying to savenull bitmap");
				return;
			}
			if (isExistInSD(ImageUrl)) {
				updateFileTime(ImageUrl);
				Log.i("bmp", "放入磁盘,存在，更新时间：" + bitmap);
				return;
			}
			/*
			 * ByteArrayOutputStream baos = new ByteArrayOutputStream();
			 * //把图片压缩到输出流中 bitmap.compress(Bitmap.CompressFormat.PNG, 100,
			 * baos); byte[] data = baos.toByteArray(); int size =data.length;
			 * baos.close();32708 52470
			 */
			bitmap.setDensity(160);
			int size = bitmap.getRowBytes() * bitmap.getHeight();
			// 判断sdcard上的空间,判断空间时，得判断是否能存下这张图片。
			if (FREE_SD_SPACE_NEEDED_TO_CACHE < freeSpaceOnSd()) {
				File dir = new File(PATH);
				removeCache(dir);
			}

			// 把Url编码成字符串
			String bitmapName = GlobalConst.Md5(ImageUrl);
			// 打开一个文件输出流，并给这个文件指定名称
			fos = new FileOutputStream(PATH + File.separator + bitmapName);
			// 套一层缓冲流，以提高效率
			bos = new BufferedOutputStream(fos);
			// 把图片压缩到输出流中
			bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);

			bos.flush();
			bos.close();
			fos.close();
			if (!s_fileList.contains(ImageUrl)) {
				s_fileList.add(ImageUrl);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Log.i("bmp", "放入磁盘,不存在，保存：" + bitmap);
	}

	/**
	 * 计算剩余空间
	 * 
	 * @return
	 */
	private long freeSpaceOnSd() {
		File dir = new File(PATH);
		long size = dirSize(dir);
		return size;
	}

	private long dirSize(File dir) {
		long result = 0;
		try {
			File[] fileList = dir.listFiles();
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].isDirectory()) {
					result += dirSize(fileList[i]);
				} else {
					// Sum the file size in bytes
					result += fileList[i].length();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

	/**
	 * 修改文件的最后修改时间
	 * 
	 * @param dir
	 * @param fileName
	 */
	public void updateFileTime(String fileName) {
		fileName = GlobalConst.Md5(fileName);
		File file = new File(PATH, fileName);
		long newModifiedTime = System.currentTimeMillis();
		file.setLastModified(newModifiedTime);
	}

	/**
	 * 计算存储目录下的文件大小，当文件总大小大于规定的CACHE_SIZE或者sdcard
	 * 剩余空间小于FREE_SD_SPACE_NEEDED_TO_CACHE的规定 那么删除40%最近没有被使用的文件
	 * 
	 * @param dirPath
	 * @param filename
	 */
	private void removeCache(File dir) {
		File[] files = dir.listFiles();
		if (files == null) {
			return;
		}
		int removeFactor = (int) ((0.4 * files.length) + 1);

		Arrays.sort(files, new FileLastModifSort());

		Log.i(TAG, "Clear some expiredcache files ");

		for (int i = 0; i < removeFactor; i++) {
			files[i].length();
			files[i].delete();
			if (s_fileList.contains(files[i].getName())) {
				s_fileList.remove(files[i].getName());
			}
		}
	}

	/**
	 * 删除过期文件
	 * 
	 * @param dirPath
	 * @param filename
	 */
	private void removeExpiredCache(String dirPath, String filename) {

		File file = new File(dirPath, filename);

		if (System.currentTimeMillis() - file.lastModified() > mTimeDiff) {

			Log.i(TAG, "Clear some expiredcache files ");

			file.delete();

		}
	}

	/**
	 * TODO 根据文件的最后修改时间进行排序 *
	 */
	class FileLastModifSort implements Comparator<File> {
		public int compare(File arg0, File arg1) {
			if (arg0.lastModified() > arg1.lastModified()) {
				return 1;
			} else if (arg0.lastModified() == arg1.lastModified()) {
				return 0;
			} else {
				return -1;
			}
		}
	}

	// 根据Url获取图片
	public synchronized Bitmap getImage(String ImageUrl) {
		Bitmap bitmap = null;
		String bitmapName = null;
		try {
			// 把Url编码成字符串
			bitmapName = GlobalConst.Md5(ImageUrl);
			// 打开一个指定的文件
			fis = new FileInputStream(PATH + File.separator + bitmapName);
			bitmap = BitmapFactory.decodeStream(fis);
			fis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.i("bmpfile", "name:" + bitmapName + " error:" + e.getMessage());
			return null;
		}

		if (bitmap != null) {
			bitmap.setDensity(160);
		}
		return bitmap;
	}

	private final int hardCachedSize = 4 * 1024 * 1024;
	private final LruCache<String, Bitmap> sHardBitmapCache = new LruCache<String, Bitmap>(
			hardCachedSize) {
		@Override
		public int sizeOf(String key, Bitmap value) {
			return value.getRowBytes() * value.getHeight();
		}

		@Override
		protected void entryRemoved(boolean evicted, String key,
									Bitmap oldValue, Bitmap newValue) {
			// 硬引用缓存区满，将一个最不经常使用的oldvalue推入到本存缓存起来
			Log.i("bmp", "内存满，放入磁盘：" + oldValue);
			// saveImage(key, oldValue);
		}
	};

	// 缓存bitmap
	public boolean putBitmap(String key, Bitmap bitmap) {
		if (bitmap != null) {
			synchronized (sHardBitmapCache) {
				Log.i("bmp", "放入内存：" + bitmap);
				sHardBitmapCache.put(key, bitmap);
			}
		}
		return true;
	}

	public boolean putAndSaveBitmap(String key, Bitmap bitmap) {
		saveImage(key, bitmap);
		putBitmap(key, bitmap);
		return true;
	}

	// 从缓存中获取bitmap
	public Bitmap getBitmap(String key) {
		Bitmap returnBitmap = null;
		synchronized (sHardBitmapCache) {
			returnBitmap = sHardBitmapCache.get(key);
			Log.i("bmp", "从内存获取：" + returnBitmap);
		}
		if (returnBitmap == null) {
			if (isExistInSD(key)) {
				returnBitmap = getImage(key);
				Log.i("bmp", "从磁盘获取：" + returnBitmap);
				if (returnBitmap != null) {
					putBitmap(key, returnBitmap);
					updateFileTime(key);
				}
			}
		}
		return returnBitmap;
	}

	// 判断是否存在该图片,sd卡中
	public boolean isExistInSD(String ImageUrl) {
		String bitmapName = GlobalConst.Md5(ImageUrl);
		boolean flag = false;

		// File ff = new File(PATH + "/" + bitmapName);
		// if (ff.exists()) {
		// flag = true;
		// } else {
		// flag = false;
		// }
		if (s_fileList.contains(bitmapName)) {
			flag = true;
		}

		Log.i("bmp", "isExistIN SD:" + flag);

		return flag;
	}

	/**
	 * 是否存在图片，sd卡或者内存中
	 * 
	 * @param ImageUrl
	 * @return
	 */
	public boolean isExist(String ImageUrl) {
		boolean ret = false;
		if (isExistInRam(ImageUrl) || isExistInSD(ImageUrl)) {
			ret = true;
		}
		return ret;
	}

	/**
	 * 是否存在图片，内存中
	 * 
	 * @param ImageUrl
	 * @return
	 */
	public boolean isExistInRam(String ImageUrl) {
		boolean ret = false;
		Bitmap returnBitmap = null;
		synchronized (sHardBitmapCache) {
			returnBitmap = sHardBitmapCache.get(ImageUrl);
		}
		if (returnBitmap != null) {
			ret = true;
		}
		Log.i("bmp", "isExistIN RAM:" + ret);
		return ret;
	}

	public static String getTokenString(String url, int bmpW, int bmpH) {
		return url + bmpW + "x" + bmpH;
	}
}
