package com.app.test;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.widget.ImageView;

import com.itserv.app.util.ToastUtils;

import java.io.InputStream;

/**
 *  * @Description:关于图片的内存缓存工具类
 *  * @author:axin
 *  * @time:2016-10-8 下午3:13:13
 */
public class BitmapCacheUtil {
    private static BitmapCacheUtil lruCacheUtil = null;
    private Context context;
    private int maxCacheSize;
    private LruCache<String, Bitmap> memoryCache = null;
    public static final int DECODETYPE_RESOURCE = 1;
    public static final int DECODETYPE_FILE = 2;
    public static final int DECODETYPE_STREAM = 3;
    private int decodeType;

    private BitmapCacheUtil(Context context, int maxCache, int decodeType) {
        super();
        this.context = context;
        this.maxCacheSize = maxCache;
        this.decodeType = decodeType;
    }

    /**
     *  * @Description:获取LruCacheUtil实例
     *  * @param @param context 上下文环境
     *  * @param @param diviseCache 分割最大缓存倍数(如设置为4,则把最大缓存的1/4设置为内存缓存的空间大小)
     *  * @param @return
     *  * LruCacheUtil
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-8 下午6:38:36
     */
    public static synchronized BitmapCacheUtil getInstance(Context context, int diviseCache, int decodeType) {
        if (lruCacheUtil == null) {
            int maxCache = (int) ((Runtime.getRuntime().maxMemory() / 1024) / diviseCache);
            lruCacheUtil = new BitmapCacheUtil(context, maxCache, decodeType);
        }
        return lruCacheUtil;
    }

    /**
     *  * @Description:从资源文件中加载图片
     *  * @param @param bitmapId 存储bitmap图片的键名
     *  * @param @param defaultBitmapId 默认bitmap图片
     *  * @param @param imageView 显示图片的控件
     *  * @param @param reqWidth 压缩后的图片宽度
     *  * @param @param reqHeight 压缩后的图片高度
     *  * void
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-8 下午6:36:14
     */
    public void loadBitmapFromResource(int bitmapId, int defaultBitmapId, ImageView imageView, int reqWidth, int reqHeight) {
        final String bitmapKey = String.valueOf(bitmapId);
        memoryCache = new LruCache<String, Bitmap>(maxCacheSize) {
            protected int sizeOf(String key, Bitmap value) {
                //返回当前图片的大小
                return value.getByteCount() / 1024;
            }

        };
        final Bitmap bitmapValue = getBitmapByKeyFromLruCache(bitmapKey);
        if (bitmapValue != null) {
            imageView.setImageBitmap(bitmapValue);
        } else {
            //设置默认图片
            imageView.setImageResource(defaultBitmapId);
            //参数1：imageview控件，参数2：请求图片的宽度，参数3：请求图片的高度
            BitmapLoadBackgroundTask bitmapLoadBackgroundTask = new BitmapLoadBackgroundTask();
            bitmapLoadBackgroundTask.execute(imageView, reqWidth, reqHeight);
        }
    }

    /**
     *  * @Description:从流中加载图片
     *  * @param @param bitmapId 存储bitmap图片的键名
     *  * @param @param defaultBitmapId 默认bitmap图片
     *  * @param @param imageView 显示图片的控件
     *  * @param @param reqWidth 压缩后的图片宽度
     *  * @param @param reqHeight 压缩后的图片高度
     *  * @param @param inputStream 输入流
     *  * void
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-8 下午7:19:36
     */
    public void loadBitmapFromStream(int bitmapId, int defaultBitmapId, ImageView imageView, int reqWidth, int reqHeight, InputStream inputStream) {
        final String bitmapKey = String.valueOf(bitmapId);
        memoryCache = new LruCache<String, Bitmap>(maxCacheSize) {
            protected int sizeOf(String key, Bitmap value) {
                //返回当前图片的大小
                return value.getByteCount() / 1024;
            }

        };
        final Bitmap bitmapValue = getBitmapByKeyFromLruCache(bitmapKey);
        if (bitmapValue != null) {
            imageView.setImageBitmap(bitmapValue);
        } else {
            //设置默认图片
            imageView.setImageResource(defaultBitmapId);
            //参数1：imageview控件，参数2：请求图片的宽度，参数3：请求图片的高度
            BitmapLoadBackgroundTask bitmapLoadBackgroundTask = new BitmapLoadBackgroundTask();
            bitmapLoadBackgroundTask.execute(imageView, reqWidth, reqHeight, inputStream);
        }
    }

    /**
     *  * @Description:从文件中加载图片
     *  * @param @param bitmapId
     *  * @param @param defaultBitmapId
     *  * @param @param imageView
     *  * @param @param reqWidth
     *  * @param @param reqHeight
     *  * @param @param filePath
     *  * void
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-8 下午7:36:57
     */
    public void loadBitmapFromFile(int bitmapId, int defaultBitmapId, ImageView imageView, int reqWidth, int reqHeight, String filePath) {
        final String bitmapKey = String.valueOf(bitmapId);
        memoryCache = new LruCache<String, Bitmap>(maxCacheSize) {
            protected int sizeOf(String key, Bitmap value) {
                //返回当前图片的大小
                return value.getByteCount() / 1024;
            }

        };
        final Bitmap bitmapValue = getBitmapByKeyFromLruCache(bitmapKey);
        if (bitmapValue != null) {
            imageView.setImageBitmap(bitmapValue);
        } else {
            //设置默认图片
            imageView.setImageResource(defaultBitmapId);
            //参数1：imageview控件，参数2：请求图片的宽度，参数3：请求图片的高度
            BitmapLoadBackgroundTask bitmapLoadBackgroundTask = new BitmapLoadBackgroundTask();
            bitmapLoadBackgroundTask.execute(imageView, reqWidth, reqHeight, filePath);
        }
    }

    /**
     *  * @Description:从内存缓存中获取图片
     *  * @param @param bitmapKey
     *  * @param @return
     *  * Bitmap
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-8 下午3:30:00
     */
    private Bitmap getBitmapByKeyFromLruCache(String bitmapKey) {
        Bitmap bitmap = null;
        if (memoryCache != null) {
            bitmap = memoryCache.get(bitmapKey);
        } else {
            ToastUtils.makeTextShort("内存缓存对象为NULL");
        }
        return bitmap;
    }

    /**
     *  * @Description:如果bitmap不存在，则后台加载图片
     *  * @author:axin
     *  * @time:2016-10-8 下午3:39:08condition
     */
    class BitmapLoadBackgroundTask extends AsyncTask<Object, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(Object... params) {
            int newDecodeType = (Integer) params[3];
            Bitmap decodeBitmap = null;
            if (newDecodeType == DECODETYPE_RESOURCE) {
                //从资源中解析bitmap
                decodeBitmap = decodeBitmapFromResource(context.getResources(), (Integer) params[0], (Integer) params[1], (Integer) params[2]);
            } else if (newDecodeType == DECODETYPE_STREAM) {
                //从网络中解析bitmap
                decodeBitmap = decodeBitmapFromStream(context.getResources(), (Integer) params[0], (Integer) params[1], (Integer) params[2], (InputStream) params[3]);
            } else if (newDecodeType == DECODETYPE_FILE) {
                //从文件中解析bitmap
                decodeBitmap = decodeBitmapFromFile(context.getResources(), (Integer) params[0], (Integer) params[1], (Integer) params[2], (String) params[3]);
            }
            //添加bitmap到lrucache:
            addBitmapToLruCache(String.valueOf(params[0]), decodeBitmap);
            return decodeBitmap;
        }
    }

    /**
     *  * @Description:从资源文件中解析bitmap
     *  * @param @return
     *  * Bitmap
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-8 下午3:41:55
     */
    private Bitmap decodeBitmapFromResource(Resources resources, int resId, int reqWidth, int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        //图片未压缩前，不把图片加载入内存
        options.inJustDecodeBounds = true;
        //注：这里仅仅提供从资源文件中获取bitmap,以后应扩展bitmap的获取方式
        BitmapFactory.decodeResource(resources, resId, options);
        int newCalculateInSimpleSize = calculateInSimpleSize(options, reqWidth, reqHeight);
        //图片已经压缩，此时把图片加载进入内存
        options.inJustDecodeBounds = false;
        //返回压缩后的图片(这里仅仅返回从资源文件中获取的bitmap,以后应返回扩展后的bitmap)
        return BitmapFactory.decodeResource(resources, resId, options);
    }

    /**
     *  * @Description:从流中解析bitmap
     *  * @param @param resources
     *  * @param @param resId
     *  * @param @param reqWidth
     *  * @param @param reqHeight
     *  * @param @param inputStream
     *  * @param @return
     *  * Bitmap
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-8 下午7:25:26
     */
    private Bitmap decodeBitmapFromStream(Resources resources, int resId, int reqWidth, int reqHeight, InputStream inputStream) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, options);
        int newCalculateInSimpleSize = calculateInSimpleSize(options, reqWidth, reqHeight);
        //图片已经压缩，此时把图片加载进入内存
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(inputStream, null, options);
    }

    /**
     *  * @Description:从文件中解析bitmap
     *  * @param @param resources
     *  * @param @param resId
     *  * @param @param reqWidth
     *  * @param @param reqHeight
     *  * @param @param filePath
     *  * @param @return
     *  * Bitmap
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-8 下午7:30:34
     */
    private Bitmap decodeBitmapFromFile(Resources resources, int resId, int reqWidth, int reqHeight, String filePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        int newCalculateInSimpleSize = calculateInSimpleSize(options, reqWidth, reqHeight);
        //图片已经压缩，此时把图片加载进入内存
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    /**
     *  * @Description:添加bitmap到lrucache中
     *  * @param
     *  * void
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-8 下午4:10:05
     */
    private void addBitmapToLruCache(String bitmapKey, Bitmap bitmapValue) {
        if (memoryCache != null) {
            memoryCache.put(bitmapKey, bitmapValue);
        } else {
            ToastUtils.makeTextShort("内存缓存对象为NULL");
        }
    }

    /**
     *  * @Description:根据图片要求的宽高计算图片压缩比例(这里未采用图片质量压缩法)
     *  * @param @return
     *  * int
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-8 下午6:15:21
     */
    private int calculateInSimpleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        //原始图片的宽高
        final int originalWidth = options.outWidth;
        final int originalHeight = options.outHeight;
        int inSimpleSize = 1;
        //取图片宽高压缩比率中最小的作为图片的压缩比率
        if ((originalWidth > reqWidth) || (originalHeight > reqHeight)) {
            final int heightRate = Math.round(originalHeight / reqHeight);
            final int widthRate = Math.round(originalWidth / reqWidth);
            inSimpleSize = heightRate > widthRate ? widthRate : heightRate;
        }
        return inSimpleSize;
    }
}
