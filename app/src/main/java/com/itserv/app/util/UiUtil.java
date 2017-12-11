package com.itserv.app.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.app.itserv.MineApplication;
import java.math.BigDecimal;


/**
 * 作者： 周作威 on 2017/2/21.
 * 邮箱： 2780450026@qq.com
 * 类描述：
 */

public class UiUtil {
    public static final String TAG = "UiUtil";

    private static int displayWidth = 0; // 显示器宽
    private static int displayHeight = 0; // 显示器高

    public static int getDisplayWidth() {
        refresh();
        return displayWidth;
    }

    public static int getDisplayHeight() {
        refresh();
        return displayHeight;
    }

    private static void refresh() {
        if (displayWidth > 0 && displayHeight > 0) {
            return;
        }
        init();
    }

    private static void init() {
        try {
            DisplayMetrics dm = new DisplayMetrics();
            WindowManager wm = (WindowManager) MineApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(dm);
            displayWidth = dm.widthPixels;
            displayHeight = dm.heightPixels;
        } catch (Exception e) {
            Log.e(TAG, "init", e);
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {
        final float scale = MineApplication.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        final float scale = MineApplication.getInstance().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @return
     */
    public static int px2sp(float pxValue) {
        final float fontScale = MineApplication.getInstance().getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(float spValue) {
        final float fontScale = MineApplication.getInstance().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 四舍五入取整
     *
     * @param s
     * @return
     */
    public static int setBigDecimal(float s) {
        BigDecimal bigDecimal = new BigDecimal(s).setScale(0, BigDecimal.ROUND_HALF_UP);
        return bigDecimal.intValue();
    }
    /**
     * 获得状态栏的高度
     *
     * @return
     */
    public static int getStatusBarHeight() {
        int result = 0;
        int resourceId = MineApplication.getInstance().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = MineApplication.getInstance().getResources().getDimensionPixelSize(resourceId);
        }
        if (result<=0)
            result = getStatusHeight();
        return result;
    }
    /**
     * 获得状态栏的高度
     *
     * @return
     */
    private static int getStatusHeight() {

        int statusHeight = -1;
        try {
            Class clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = MineApplication.getInstance().getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }
}
