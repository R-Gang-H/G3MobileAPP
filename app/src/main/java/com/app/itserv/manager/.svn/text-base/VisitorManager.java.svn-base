package com.app.itserv.manager;

import android.content.Context;

import com.itserv.app.util.PreferencesUtils;
import com.yycloud.app.utils.TAPreferenceUtil;

/**
 *  * @Description:访客管理
 *  * @author:axin
 *  * @time:2016-11-24 下午2:13:20
 */
public class VisitorManager {
    /**
     *  * @Description:如果是访客网关返回false,否则返回true
     *  * @param @param context
     *  * @param @param visitorname
     *  * @param @return
     *  * boolean
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-11-29 下午2:28:44
     */
    public static boolean isLogin(Context context, String visitorname) {
        String s = PreferencesUtils.getString(context, "visitorlist");
        if ((visitorname != null)) {
            if (visitorname.equals("visitor")) {
                return false;
            }
        }
        return true;
    }
}
