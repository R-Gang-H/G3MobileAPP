package com.app.test;

import java.io.File;

import android.os.Environment;
import android.os.StatFs;

/**
 *  * @Description:SD卡工具类
 *  * @author:axin
 *  * @time:2016-10-26 上午9:47:17
 */
public class SdCardUtil {

    private SdCardUtil() {
        super();

    }

    /**
     *  * @Description:判断SD卡是否可用
     *  * @param @return
     *  * boolean
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-26 上午9:52:32
     */
    public static boolean isSdCardEnable() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);

    }

    /**
     *  * @Description:获取SD卡路径
     *  * @param @return
     *  * String
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-26 上午9:53:39
     */
    public static String getSdCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator;
    }

    /**
     *  * @Description:获取SD卡剩余容量
     *  * @param @return 字节
     *  * long
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-26 上午9:56:27
     */
    public static long getSdCardAvailableSize() {
        if (isSdCardEnable()) {
            StatFs stat = new StatFs(getSdCardPath());
            // 获取空闲的数据块的数量
            long availableBlocks = (long) stat.getAvailableBlocks() - 4;
            // 获取单个数据块的大小（byte）
            long freeBlocks = stat.getAvailableBlocks();
            return freeBlocks * availableBlocks;
        }
        return 0;
    }

    /**
     *  * @Description:获取指定路径所在空间的剩余可用容量字节数
     *  * @param @param filePath SD卡或内部存储路径
     *  * @param @return 字节
     *  * long
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-26 上午10:00:03
     */
    public static long getAvailableBytes(String filePath) {
        // 如果是sd卡下的路径，则获取sd卡可用容量
        if (filePath.startsWith(getSdCardPath())) {
            filePath = getSdCardPath();
        } else {
            // 如果是内部存储的路径，则获取内存存储的可用容量
            filePath = Environment.getDataDirectory().getAbsolutePath();
        }
        StatFs stat = new StatFs(filePath);
        long availableBlocks = (long) stat.getAvailableBlocks() - 4;
        return stat.getBlockSize() * availableBlocks;
    }

    /**
     *  * @Description:获取系统存储路径
     *  * @param @return
     *  * String
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-26 上午10:01:19
     */
    public static String getRootDirectoryPath() {
        return Environment.getRootDirectory().getAbsolutePath();
    }
}
