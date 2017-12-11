package com.yycloud.app;

import android.app.Application;
import android.content.Context;

import com.yycloud.app.utils.TAPreferenceUtil;

/**
 * 修改服务器路径
 *
 * @author 作者 E-mail:
 * @version 1.0
 * @date 创建时间：2017-8-21 下午1:53:13
 * @parameter
 * @return
 */

public class MyApplication extends Application {
    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    //	 v3产品服务器
    public static String WAPI_IP = TAPreferenceUtil.getInstance(getContext())
            .getString("WAPI_IP", "test.www.yunyangdata.com");

    // v3产品服务器端口
    public static String WAPI_HTTP_PORT = TAPreferenceUtil.getInstance(
            getContext()).getString("WAPI_HTTP_PORT", "9080");

    public static String PROJECTNAME = TAPreferenceUtil.getInstance(
            getContext()).getString("PROJECTNAME", "cpms");// 项目名称
}
