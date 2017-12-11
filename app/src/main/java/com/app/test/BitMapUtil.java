package com.app.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

/**
 *  * @Description:bitmap处理工具类
 *  * @author:axin
 *  * @time:2016-10-25 下午3:46:01
 */
public class BitMapUtil {

    private BitMapUtil() {
        super();
    }

    /**
     *  * @Description:保存图片到sd卡
     *  * @param @param bitmap 图片
     *  * @param @param imageName 图片名称
     *  * @param @param imageFilePath 保存路径
     *  * void
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午3:47:57
     */
    public static void saveBitmapToSdCard(Bitmap bitmap, String imageName,
                                          String imageFilePath) {
        File file = new File(imageFilePath);
        if (!file.exists()) {
            file.mkdir();
        }
        File imagefile = new File(file, imageName + ".jpg");
        try {
            imagefile.createNewFile();
            FileOutputStream fos = new FileOutputStream(imagefile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  * @Description:保存图片到系统图库
     *  * @param @param bitmap
     *  * @param @param dir
     *  * @param @param name
     *  * @param @param isShowPhotos
     *  * @param @return
     *  * boolean
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午3:50:11
     */
    public static boolean saveBitmapToAlbum(Bitmap bitmap, String dir, String name, Application application, boolean isShowPhotos) {
        File path = new File(dir);
        if (!path.exists()) {
            path.mkdirs();
        }
        File file = new File(path + "/" + name);
        if (file.exists()) {
            file.delete();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return true;
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100,
                    fileOutputStream);
            fileOutputStream.flush();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 其次把文件插入到系统图库
        if (isShowPhotos) {
            try {
                MediaStore.Images.Media.insertImage(application.getContentResolver(),
                        file.getAbsolutePath(), name, null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // 最后通知图库更新
            application.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file)));
        }
        return true;
    }

    /**
     *  * @Description:从sd卡取图片
     *  * @param @param imageFilePath 路径
     *  * @param @param picName 图片名
     *  * @param @return
     *  * Bitmap
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午3:56:57
     */
    public static Bitmap getBitmapBySd(String imageFilePath, String picName) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return null;
        }
        try {
            File file = new File(imageFilePath, picName + ".jpg");
            FileInputStream inputStream = new FileInputStream(file);
            byte[] b = new byte[inputStream.available()];
            inputStream.read(b);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            return bitmap;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     *  * @Description:从本地获取图片
     *  * @param @param context
     *  * @param @param drawableId
     *  * @param @param screenWidth
     *  * @param @param screenHight
     *  * @param @return
     *  * Bitmap
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午4:01:36
     */
    public static Bitmap getBitmapById(Context context, int drawableId,
                                       int screenWidth, int screenHight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inInputShareable = true;
        options.inPurgeable = true;
        InputStream stream = context.getResources().openRawResource(drawableId);
        Bitmap bitmap = BitmapFactory.decodeStream(stream, null, options);
        return getBitmap(bitmap, screenWidth, screenHight);
    }

    private static Bitmap getBitmap(Bitmap bitmap, int screenWidth,
                                    int screenHight) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        Matrix matrix = new Matrix();
        float scale = (float) screenWidth / w;
        float scale2 = (float) screenHight / h;

        //取比例小的值 可以把图片完全缩放在屏幕内
        scale = scale < scale2 ? scale : scale2;

        //都按照宽度scale 保证图片不变形.根据宽度来确定高度
        matrix.postScale(scale, scale);
        // w,h是原图的属性.
        return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
    }

    /**
     *  * @Description:drawable 转 bitmap
     *  * @param @param drawable
     *  * @param @return
     *  * Bitmap
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午4:05:36
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap
                .createBitmap(
                        drawable.getIntrinsicWidth(),
                        drawable.getIntrinsicHeight(),
                        drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                                : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        // canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     *  * @Description:bitmap 转 byte[]
     *  * @param @param bmp
     *  * @param @param needRecycle
     *  * @param @return
     *  * byte[]
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午4:07:48
     */
    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 80, output);
        if (needRecycle) {
            bmp.recycle();
        }
        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *  * @Description:从网络获取图片
     *  * @param @param path 路径
     *  * @param @return
     *  * @param @throws Exception
     *  * Bitmap
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午4:09:47
     */
    public Bitmap getImageByInternet(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10 * 1000);
        conn.setConnectTimeout(10 * 1000);
        conn.setRequestMethod("GET");
        InputStream in = null;
        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            in = conn.getInputStream();
        } else {
            in = null;
        }
        if (in == null) {
            throw new RuntimeException("stream is null");
        } else {
            try {
                // 调用getBytes(in)方法
                byte[] data = getBytes(in);
                if (data != null) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                    return bitmap;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            in.close();
            return null;
        }
    }

    private static byte[] getBytes(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inStream.close();
        return outStream.toByteArray();
    }

    /**
     *  * @Description:从网络获取 drawable
     *  * @param @param imageUrl
     *  * @param @return
     *  * Drawable
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午4:14:40
     */
    public Drawable getDrawableByInternet(String imageUrl) {
        Drawable drawable = null;
        try {
            // 可以在这里通过文件名来判断，是否本地有此图片
            drawable = Drawable.createFromStream(
                    new URL(imageUrl).openStream(), "image.jpg");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return drawable;
    }

    /**
     *  * @Description:从view中获取bitmap
     *  * @param @param view
     *  * @param @return
     *  * Bitmap
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午4:22:16
     */
    public static Bitmap getBitmapFromView(View view) {
        view.destroyDrawingCache();
        view.measure(View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED), View.MeasureSpec
                .makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = view.getDrawingCache(true);
        return bitmap;
    }

    /**
     *  * @Description:bitmap 旋转一定角度
     *  * @param @param b 图片
     *  * @param @param degrees 角度
     *  * @param @return
     *  * Bitmap
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午4:16:19
     */
    public static Bitmap rotateBitmap(Bitmap b, int degrees) {
        if (degrees != 0 && b != null) {
            Matrix m = new Matrix();
            m.setRotate(degrees, (float) b.getWidth() / 2,
                    (float) b.getHeight() / 2);
            try {
                Bitmap b2 = Bitmap.createBitmap(b, 0, 0, b.getWidth(),
                        b.getHeight(), m, true);
                return b2;// 正常情况下返回旋转角度的图
            } catch (OutOfMemoryError ex) {
                return b;// 内存溢出返回原图
            } finally {
                b.recycle();// 释放资源
            }
        }
        return b;
    }
}
