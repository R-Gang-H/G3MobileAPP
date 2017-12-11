package com.app.test;

import android.content.Context;
import android.util.TypedValue;

/**
 *  * @Description:单位转换类
 *  * @author:axin
 *  * @time:2016-10-25 下午2:50:49
 */
public class UnitUtil {
    private UnitUtil() {

    }

    /**
     *  * @Description:dp 转 px
     *  * @param @param context
     *  * @param @param dpVal
     *  * @param @return
     *  * int
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午2:52:51
     */
    public static int dpToPx(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     *  * @Description:sp 转 px
     *  * @param @param context
     *  * @param @param spVal
     *  * @param @return
     *  * int
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午2:53:43
     */
    public static int spToPx(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, context.getResources().getDisplayMetrics());
    }

    /**
     *  * @Description:px 转 dp
     *  * @param @param context
     *  * @param @param pxVal
     *  * @param @return
     *  * float
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午2:55:11
     */
    public static float pxToDp(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     *  * @Description:px 转 sp
     *  * @param @param context
     *  * @param @param pxVal
     *  * @param @return
     *  * float
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-25 下午2:55:35
     */
    public static float pxToSp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }
}
