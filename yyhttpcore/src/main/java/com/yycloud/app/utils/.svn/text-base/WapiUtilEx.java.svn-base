package com.yycloud.app.utils;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.ParseError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonRequest;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.HttpHeaderParser;
import com.yycloud.core.beans.AlarmStrategy;
import com.yycloud.core.config.ECMSConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @project name：yyhttpcore
 * @type name：WapiUtilEx
 * @description：
 * @author：gang
 * @date time：2017-6-3 下午3:30:44
 */
public class WapiUtilEx extends WAPI {

    private static String TAG = "WapiUtilEx";
    private static String resp = "";

    public static void init(Application application) {
        MyVolley.init(application);
    }

    /**
     * 登录请求方法
     *
     * @param map
     * @param callback
     */
    public static void doLoginRequest(final Map<String, String> map,
                                      final MYCallBack callback) {

        RequestQueue queue = MyVolley.getRequestQueue();

        // v3请求路径
        String url = WapiUtil.WAPI_USER_LOGIN_URL1;
        Log.i(TAG, "登录_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 登录-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 请求传递的参数
                Log.i(TAG, "登录   map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        queue.add(jsonRequest);
    }

    /**
     * @description：登出请求方法
     * @author：gang
     * @date time：2017-6-9 上午9:52:52
     */
    public static void exit(final Map<String, String> map,
                            final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3登出路径
        String url = WapiUtil.WAPI_USER_EXIT_URL;
        Log.i(TAG, "登出_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 登出-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "登出  map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * @description：账户明细请求方法
     * @author：gang
     * @date time：2017-6-4 下午4:07:11
     */
    public static void canverSation(final Map<String, String> map,
                                    final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3账户明细路径
        String url = WapiUtil.WAPI_USER_CONVER_SATION_URL;
        Log.i(TAG, "账户明细_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 账户明细-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "账户明细  map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 修改当前用户密码请求方法
     *
     * @param map
     * @param callback
     */
    public static void changePsd(final Map<String, String> map,
                                 final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();

        // v3修改登录密码路径
        String url = WapiUtil.WAPI_USER_CHANGE_PASSWORD_URL;
        Log.i(TAG, "修改当前用户密码_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 修改当前用户密码-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "修改当前用户密码   map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        queue.add(jsonRequest);
    }

    /**
     * @description：关于我们请求方法
     * @author：gang
     * @date time：2017-6-5 上午9:11:18
     */
    public static void doabout(final Map<String, String> map,
                               final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3关于我们路径
        String url = WapiUtil.WAPI_USER_ABOUT_URL;
        Log.i(TAG, "关于我们_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 关于我们-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "关于我们  map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * @description：用户协议请求方法
     * @author：gang
     * @date time：2017-6-5 上午10:02:07
     */
    public static void doprotacol(final Map<String, String> map,
                                  final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3用户协议路径
        String url = WapiUtil.WAPI_USER_PROTACOL_URL;
        Log.i(TAG, "用户协议_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 用户协议-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "用户协议  map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * @description：广告列表请求方法
     * @author：gang
     * @date time：2017-6-9 上午9:52:52
     */
    public static void advertising(final Map<String, String> map,
                                   final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3广告列表路径
        String url = WapiUtil.WAPI_USER_ADVER_URL;
        Log.i(TAG, "广告列表_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 广告列表-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "广告列表 map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }


    /**
     * 咨询建议问题详情请求
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-1 下午5:54:39
     */
    public static void doProposalExa(final Map<String, String> map,
                                     final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3投诉建议列表路径
        String url = WapiUtil.WAPI_USER_PROPOSAL_EXA_URL;
        Log.i(TAG, "投诉建议问题详情_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 投诉建议问题详情-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "投诉建议问题详情 map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 新增咨询建议请求方法
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-6-30 下午4:08:07
     */
    public static void addAdvisory(final Map<String, String> map,
                                   final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3投诉建议列表路径
        String url = WapiUtil.WAPI_USER_ADVISORY_URL;
        Log.i(TAG, "新增投诉建议_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 新增投诉建议-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "新增投诉建议 map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 咨询建议追加提问请求方法
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: changyiqiang
     * @date 创建时间：2017-7-12 下午4:08:07
     */
    public static void replyAdvisory(final Map<String, String> map,
                                     final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3投诉建议追加提问路径
        String url = WapiUtil.WAPI_USER_PROPOSAL_REPLY_URL;
        Log.i(TAG, "投诉建议追加提问_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 投诉建议追加提问-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "投诉建议追加提问 map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 咨询建议关闭问题请求方法
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: changyiqiang
     * @date 创建时间：2017-7-12 下午4:08:07
     */
    public static void closeAdvisory(final Map<String, String> map,
                                     final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3投诉建议关闭问题路径
        String url = WapiUtil.WAPI_USER_ADVISORY_CLOSE_URL;
        Log.i(TAG, "投诉建议关闭问题_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 投诉建议关闭问题-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "投诉建议关闭问题 map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * @description：商户信息
     * @author：gang
     * @date time：2017-6-12 下午4:55:00
     */
    public static void domerchantInfo(final Map<String, String> map,
                                      final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3商户信息路径
        String url = WapiUtil.WAPI_MERCHANT_INFO_URL;
        Log.i(TAG, "商户信息_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 商户信息---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "商户信息   map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * @description：商户信息修改请求方法
     * @author：gang
     * @date time：2017-6-14 下午8:11:10
     */
    public static void domerchantUpdate(final Map<String, String> map,
                                        final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3商户信息修改路径
        String url = WapiUtil.WAPI_MERCHANT_UPDATE_URL;
        Log.i(TAG, "商户信息修改_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 商户信息修改---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "商户信息修改   map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }


    /**
     * @description：用户编辑明细请求方法
     * @author：gang
     * @date time：2017-6-12 下午8:29:24
     */
    public static void usergetedit(final Map<String, String> map,
                                   final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3用户编辑明细路径
        String url = WapiUtil.WAPI_USER_GET_EDIT_URL;
        Log.i(TAG, "用户编辑明细_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 用户编辑明细  ---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "用户编辑明细   map---> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * @description：用户编辑修改请求方法
     * @author：gang
     * @date time：2017-6-15 上午10:22:16
     */
    public static void usergetupdate(final Map<String, String> map,
                                     final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3用户编辑修改路径
        String url = WapiUtil.WAPI_USER_GET_UPDATE_URL;
        Log.i(TAG, "用户编辑修改_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 用户编辑修改  ---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "用户编辑修改   map---> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * @description：用户新增请求方法
     * @author：gang
     * @date time：2017-6-15 下午2:51:36
     */
    public static void usergetadd(final Map<String, String> map,
                                  final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3用户编辑修改路径
        String url = WapiUtil.WAPI_USER_GET_ADD_URL;
        Log.i(TAG, "用户新增_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 用户新增  ---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "用户新增   map---> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * @description：用户注销请求方法
     * @author：gang
     * @date time：2017-6-15 下午2:51:36
     */
    public static void usergetCancell(final Map<String, String> map,
                                      final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3用户注销路径
        String url = WapiUtil.WAPI_USER_CANCELL_URL;
        Log.i(TAG, "用户注销_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 用户注销  ---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "用户注销   map---> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * @description：用户激活请求方法
     * @author：gang
     * @date time：2017-6-15 下午2:51:36
     */
    public static void usergetActivat(final Map<String, String> map,
                                      final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3用户注销路径
        String url = WapiUtil.WAPI_USER_ACTIVAT_URL;
        Log.i(TAG, "用户注销_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 用户注销  ---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "用户注销   map---> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * @description：用户管理---查看大棚列表请求方法
     * @author：gang
     * @date time：2017-6-12 下午12:32:46
     */
    public static void usergreenhouselist(final Map<String, String> map,
                                          final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3用户管理---查看大棚列表路径
        String url = WapiUtil.WAPI_USER_GREEN_HOUSE_LIST_URL;
        Log.i(TAG, "用户管理---查看大棚列表_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 用户管理---查看大棚-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "用户管理---查看大棚  map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * @description：角色列表请求方法
     * @author：gang
     * @date time：2017-6-10 上午10:36:34
     */
    public static void rolegetlist(final Map<String, String> map,
                                   final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3角色列表路径
        String url = WapiUtil.WAPI_ROLE_GET_LIST_URL;
        Log.i(TAG, "角色列表_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 角色列表-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "角色列表  map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * @description：角色编辑明细请求方法
     * @author：gang
     * @date time：2017-6-10 上午10:36:34
     */
    public static void rolegetedit(final Map<String, String> map,
                                   final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3角色编辑明细路径
        String url = WapiUtil.WAPI_ROLE_GET_EDIT_URL;
        Log.i(TAG, "角色编辑明细_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 角色编辑明细-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "角色编辑明细   map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * @description：角色新增、修改请求方法
     * @author：gang
     * @date time：2017-6-16 上午9:01:05
     */
    public static void roleAdd(final Map<String, String> map,
                               final MYCallBack callback) {
        String url;

        RequestQueue queue = MyVolley.getRequestQueue();
        if (!TextUtils.isEmpty(map.get("roleId"))) {// 角色修改
            resp = "角色修改";
            url = WapiUtil.WAPI_ROLE_UPDATE_URL;
            Log.i(TAG, resp + "_url ---> " + url);
        } else {
            resp = "角色新增";
            url = WapiUtil.WAPI_ROLE_ADD_URL;
            Log.i(TAG, resp + "_url ---> " + url);
        }
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response " + resp + " ---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, resp + "  map---> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * @description：角色删除请求方法
     * @author：gang
     * @date time：2017-6-17 上午11:25:42
     */
    public static void roledelete(final Map<String, String> map,
                                  final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3角色列表路径
        String url = WapiUtil.WAPI_ROLE_DEL_URL;
        Log.i(TAG, "角色删除_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 角色列表-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "角色列表  map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * @description：机构删除请求方法
     * @author：gang
     * @date time：2017-6-17 上午11:25:42
     */
    public static void orgadelete(final Map<String, String> map,
                                  final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3角色列表路径
        String url = WapiUtil.WAPI_ORGA_DEL_URL;
        Log.i(TAG, "机构删除_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 机构列表-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "机构列表  map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * @description：角色管理---查看用户列表|机构管理---查看用户列表
     * @author：gang
     * @date time：2017-6-13 下午12:03:13
     */

    public static void roleManagOrguser(final Map<String, String> map,
                                        final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        String url = "";

        if (!TextUtils.isEmpty(map.get("roleId"))) {
            // v3角色管理---查看用户路径
            url = WapiUtil.WAPI_ROLE_MANAGER_ORG_USER_URL;
            resp = "角色管理---查看用户";
            Log.i(TAG, resp + "列表_url -> " + url);
        }
        if (!TextUtils.isEmpty(map.get("departId"))) {
            // v3机构管理---查看用户路径
            url = WapiUtil.WAPI_ORGANIZA_MANAGER_USER_URL;
            resp = "机构管理---查看用户";
            Log.i(TAG, resp + "列表_url -> " + url);
        }
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response" + resp + "---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, resp + "  map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * @description：组织机构列表请求方法
     * @author：gang
     * @date time：2017-6-13 下午7:35:35
     */
    public static void doorganiza(final Map<String, String> map,
                                  final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3组织机构列表路径
        String url = WapiUtil.WAPI_ORGANIZATION_LIST_URL;
        Log.i(TAG, "组织机构列表_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 组织机构列表---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "组织机构    map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * @description：员工管理---组织机构--编辑明细接口请求方法
     * @author：gang
     * @date time：2017-6-14 上午9:50:19
     */
    public static void doOrganedit(final Map<String, String> map,
                                   final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3组织机构编辑明细列表路径
        String url = WapiUtil.WAPI_ORGANIZATION_Edit_URL;
        Log.i(TAG, "组织机构编辑明细_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 组织机构编辑明细---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "组织机构编辑明细    map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * @description：机构管理---新增、修改请求方法
     * @author：gang
     * @date time：2017-6-16 下午5:39:08
     */
    public static void doOrganAddUpdate(final Map<String, String> map,
                                        final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        String url;
        if (!TextUtils.isEmpty(map.get("departId"))) {// 修改
            // v3组织机构修改路径
            url = WapiUtil.WAPI_ORGANIZATION_UPDATE_URL;
            Log.i(TAG, "组织机构修改_url -> " + url);
        } else {
            // v3组织机构新增路径
            url = WapiUtil.WAPI_ORGANIZATION_ADD_URL;
            Log.i(TAG, "组织机构新增_url -> " + url);
        }
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 组织机构新增---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "组织机构新增    map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 基地管理列表
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-6 下午4:17:04
     */
    public static void basemanagerlist(final Map<String, String> map,
                                       final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3基地列表路径
        String url = WapiUtil.WAPI_BASE_MANAGER_LIST_URL;
        Log.i(TAG, "基地列表_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 基地列表-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "基地列表  map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 基地新增、编辑
     *
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-6 下午4:17:39
     * @param map
     * @param callback
     * @return void
     */
    private static String logStr = "";

    public static void baseaddupdate(final Map<String, String> map,
                                     final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        String greenbaseId = map.get("greenbaseId");
        String url = null;

        if (TextUtils.isEmpty(greenbaseId)) {
            url = WapiUtil.WAPI_BASE_ADD_URL;
            logStr = "新增";
            Log.i(TAG, "基地" + logStr + "_url -> " + url);
        } else {
            url = WapiUtil.WAPI_BASE_UPDATE_URL;
            logStr = "编辑";
            Log.i(TAG, "基地" + logStr + "_url -> " + url);
        }
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 基地" + logStr + "-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "基地" + logStr + "  map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 基地查看
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-7 上午10:52:04
     */
    public static void baseexa(final Map<String, String> map,
                               final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3基地删除路径
        String url = WapiUtil.WAPI_BASE_EXA_URL;
        Log.i(TAG, "基地查看_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 基地查看 ---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "基地查看 map---> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 基地排序
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-8 上午9:53:10
     */
    public static void basesort(final Map<String, String> map,
                                final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3基地排序路径
        String url = WapiUtil.WAPI_BASE_SORT_URL;
        Log.i(TAG, "基地排序_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 基地排序 ---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "基地排序  map---> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 基地中大棚排序
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017年09月05日19:45:56
     */
    public static void greenHouseSort(final Map<String, String> map,
                                      final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3基地排序路径
        String url = WapiUtil.WAPI_BASE_GHOUSE_SORT_URL;
        Log.i(TAG, "基地中大棚排序_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 基地中大棚排序 ---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "基地中大棚排序  map---> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 基地删除
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-7 上午10:52:04
     */
    public static void basedelete(final Map<String, String> map,
                                  final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3基地删除路径
        String url = WapiUtil.WAPI_BASE_DELETE_URL;
        Log.i(TAG, "基地删除_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 基地删除 ---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "基地删除  map---> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 基地中大棚显示列表请求方法
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-8 上午11:08:28
     */
    public static void basegreenHouse(final Map<String, String> map,
                                      final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3基地中大棚列表路径
        String url = WapiUtil.WAPI_BASE_GREEN_HOUSE_URL;
        Log.i(TAG, "基地中大棚列表_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 基地中大棚列表 ---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "基地中大棚列表  map---> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 大棚管理列表请求方法
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-10 下午4:00:04
     */
    public static void ghousemanagerlist(final Map<String, String> map,
                                         final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3大棚管理列表路径
        String url = WapiUtil.WAPI_GHOUSE_MANAGER_LIST_URL;
        Log.i(TAG, "大棚管理列表_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 大棚管理列表-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "大棚管理列表  map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 大棚新增、编辑请求方法
     *
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-11 上午9:50:01
     * @param map
     * @param callback
     * @return void
     */
    private static String gHouse;

    public static void ghouseAddEdit(final Map<String, String> map,
                                     final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        String url = null;
        if (TextUtils.isEmpty(map.get("greenhouseId"))) {
            // v3大棚新增路径
            url = WapiUtil.WAPI_GHOUSE_ADD_URL;
            gHouse = "新增";
            Log.i(TAG, "大棚" + gHouse + "_url -> " + url);
        } else {
            // v3大棚编辑路径
            url = WapiUtil.WAPI_GHOUSE_EDIT_URL;
            gHouse = "编辑";
            Log.i(TAG, "大棚" + gHouse + "_url -> " + url);
        }
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 大棚" + gHouse + "-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "大棚" + gHouse + "  map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 大棚查看详情
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-12 上午11:06:58
     */
    public static void ghouseexaview(final Map<String, String> map,
                                     final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3大棚查看路径
        String url = WapiUtil.WAPI_GHOUSE_EXAVIEW_URL;
        Log.i(TAG, "大棚查看_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 大棚查看 ---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "大棚查看 map--> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 大棚删除
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-12 上午11:06:58
     */
    public static void ghousedelete(final Map<String, String> map,
                                    final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3大棚删除路径
        String url = WapiUtil.WAPI_GHOUSE_DELETE_URL;
        Log.i(TAG, "大棚删除_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 大棚删除 ---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "大棚删除  map--> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 大棚图片列表
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-12 下午3:57:43
     */
    public static void ghouseimage(final Map<String, String> map,
                                   final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3大棚图片路径
        String url = WapiUtil.WAPI_GHOUSE_IMAGE_URL;
        Log.i(TAG, "大棚图片_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 大棚图片 ---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "大棚图片  map--> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 大棚图片新增
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-12 下午6:46:31
     */
    public static void ghouseaddimage(final Map<String, String> map,
                                      final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        String url = null;
        String ghouseimg = "";
        if (TextUtils.isEmpty(map.get("ghouseDocId"))) {
            url = WapiUtil.WAPI_GHOUSE_ADD_IMAGE_URL;
            ghouseimg = "新增";
            Log.i(TAG, "大棚图片" + ghouseimg + "_url -> " + url);
        } else {
            url = WapiUtil.WAPI_GHOUSE_EDIT_IMAGE_URL;
            ghouseimg = "编辑";
            Log.i(TAG, "大棚图片" + ghouseimg + "_url -> " + url);
        }
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 大棚图片新增 ---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "大棚图片新增  map--> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 大棚图片查看
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-13 下午3:02:46
     */
    public static void ghouseimgexa(final Map<String, String> map,
                                    final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3大棚图片新增路径
        String url = WapiUtil.WAPI_GHOUSE_IMAGE_EXA_URL;
        Log.i(TAG, "大棚图片查看_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 大棚图片查看 ---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "大棚图片查看  map--> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 大棚图片删除
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-13 下午4:14:48
     */
    public static void ghouseimagedel(final Map<String, String> map,
                                      final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3大棚图片新增路径
        String url = WapiUtil.WAPI_GHOUSE_IMAGE_DEL_URL;
        Log.i(TAG, "大棚图片删除_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 大棚图片删除 ---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "大棚图片删除  map--> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 大棚网关列表请求
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-8-11 下午2:14:23
     */
    public static void ghousegatelist(final Map<String, String> map,
                                      final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3大棚图片新增路径
        String url = WapiUtil.WAPI_GHOUSE_GATE_LIST_URL;
        Log.i(TAG, "大棚网关列表_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 大棚网关列表 ---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "大棚网关列表  map--> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 大棚网关绑定
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-8-11 下午4:36:16
     */
    public static void ghousegatebind(final Map<String, String> map,
                                      final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3大棚网关绑定路径
        String url = WapiUtil.WAPI_GHOUSE_GATE_BIND_URL;
        Log.i(TAG, "大棚网关绑定_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 大棚网关绑定 ---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "大棚网关绑定  map--> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 大棚网关二代数据迁移列表
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017年08月30日11:18:22
     */
    public static void migrationdata(final Map<String, String> map,
                                     final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3大棚网关二代数据迁移路径
        String url = WapiUtil.WAPI_MIGRA_DATA_LIST_URL;
        Log.i(TAG, "大棚网关二代数据迁移_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 大棚网关二代数据迁移 ---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "大棚网关二代数据迁移  map--> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 大棚网关解绑
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-8-11 下午4:36:16
     */
    public static void ghousegateunbind(final Map<String, String> map,
                                        final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3大棚网关解绑路径
        String url = WapiUtil.WAPI_GHOUSE_GATE_UNBIND_URL;
        Log.i(TAG, "大棚网关解绑_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 大棚网关解绑 ---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "大棚网关解绑  map--> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 大棚网关详情
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017年08月31日12:57:39
     */
    public static void gatedetails(final Map<String, String> map,
                                   final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3大棚网关详情路径
        String url = WapiUtil.WAPI_GATE_DETAILS_URL;
        Log.i(TAG, "大棚网关解绑_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 大棚网关详情 ---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "大棚网关详情  map--> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 大棚用户列表请求 大棚员工
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-13 下午5:14:11
     */
    public static void ghouseemp(final Map<String, String> map,
                                 final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3大棚用户列表路径
        String url = WapiUtil.WAPI_GHOUSE_USER_LIST_URL;
        Log.i(TAG, "大棚用户列表_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 大棚用户列表 ---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "大棚用户列表  map--> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 大棚用户列表查看
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-14 上午11:25:44
     */
    public static void ghouexaemploy(final Map<String, String> map,
                                     final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3大棚用户明细路径
        String url = WapiUtil.WAPI_GHOUSE_USER_EXA_URL;
        Log.i(TAG, "大棚用户明细_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 大棚用户明细 ---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "大棚用户明细  map--> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 大棚员工绑定
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-8-8 下午5:12:57
     */
    public static void ghouseUserBind(final Map<String, String> map,
                                      final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3大棚用户明细路径
        String url = WapiUtil.WAPI_GHOUSE_USER_BIND_URL;
        Log.i(TAG, "大棚员工绑定_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 大棚员工绑定 ---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "大棚员工绑定  map--> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }


    /**
     * 农事种植计划新增
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: changyiqiang
     * @date 创建时间：2017-7-13 上午11:25:15
     */
    public static void planschemeCreateInfo(final Map<String, String> map,
                                            final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3农事种植计划 新增 路径
        String url = WapiUtil.WAPI_PLAN_SCHEME_CREATEINFO_URL;
        Log.i(TAG, "种植计划新增_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 农事种植计划新增-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "农事种植计划新增  map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 农事种植计划修改
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: changyiqiang
     * @date 创建时间：2017-7-13 上午11:25:15
     */
    public static void planschemeUpdateInfo(final Map<String, String> map,
                                            final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3农事种植计划修改路径
        String url = WapiUtil.WAPI_PLAN_SCHEME_UPDATEINFO_URL;
        Log.i(TAG, "种植计划修改_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 农事种植计划修改-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "农事种植计划修改  map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 农事种植计划复制
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: changyiqiang
     * @date 创建时间：2017-7-5 上午11:25:15
     */
    public static void planschemeCopyInfo(final Map<String, String> map,
                                          final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3农事种植计划复制路径
        String url = WapiUtil.WAPI_PLAN_SCHEME_COPYINFO_URL;
        Log.i(TAG, "种植计划复制_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 农事种植计划复制-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "农事种植计划复制  map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 农事种植计划明细
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: changyiqiang
     * @date 创建时间：2017-7-13 上午11:25:15
     */
    public static void planschemeGetInfo(final Map<String, String> map,
                                         final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3农事种植计划明细路径
        String url = WapiUtil.WAPI_PLAN_SCHEME_GETINFO_URL;
        Log.i(TAG, "种植计划明细_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 农事种植计划明细-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "农事种植计划明细 map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }


    /**
     * 系统公告已读设置
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: changyiqiang
     * @date 创建时间：2017-7-18 上午11:25:15
     */
    public static void apiNoticeControllerSet(final Map<String, String> map,
                                              final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3系统公告已读设置请求路径
        String url = WapiUtil.WAPI_NOTICE_CONTEOLLER_SET_URL;
        Log.i(TAG, "系统公告已读设置请求_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 系统公告已读设置请求-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "系统公告已读设置请求 map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 系统公告明细
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: changyiqiang
     * @date 创建时间：2017-7-18 上午11:25:15
     */
    public static void apiNoticeControllerGetInfo(
            final Map<String, String> map, final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3系统公告明细请求路径
        String url = WapiUtil.WAPI_NOTICE_CONTEOLLER_GETINFO_URL;
        Log.i(TAG, "系统公告明细请求_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 系统公告明细请求-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "系统公告明细请求 map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 农事任务列表
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-21 下午2:03:46
     */
    public static void fartaskList(final Map<String, String> map,
                                   final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3农事任务列表请求路径
        String url = WapiUtil.WAPI_FAR_TASK_LIST_URL;
        Log.i(TAG, "农事任务列表请求_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 农事任务列表请求-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "农事任务列表请求 map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 新增农事任务
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-22 下午3:39:48
     */
    public static void fartaskaddedit(final Map<String, String> map,
                                      final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3农事任务新增请求路径
        String url = WapiUtil.WAPI_FAR_TASK_ADD_URL;
        Log.i(TAG, "农事任务新增请求_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 农事任务新增请求-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "农事任务新增请求 map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 农事任务明细
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-25 下午7:00:50
     */
    public static void fartaskDetail(final Map<String, String> map,
                                     final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3农事任务新增请求路径
        String url = WapiUtil.WAPI_FAR_TASK_DETAIL_URL;
        Log.i(TAG, "农事任务明细请求_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 农事任务明细请求-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "农事任务明细请求 map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 农事任务编辑
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-25 下午7:20:24
     */
    public static void fartaskSave(final Map<String, String> map,
                                   final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3农事任务新增请求路径
        String url = WapiUtil.WAPI_FAR_TASK_EDIT_URL;
        Log.i(TAG, "农事任务编辑请求_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 农事任务编辑请求-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "农事任务编辑请求 map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 农事任务分配
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-26 上午10:10:42
     */
    public static void fartaskDistr(final Map<String, String> map,
                                    final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3农事任务分配请求路径
        String url = WapiUtil.WAPI_FAR_TASK_DISTR_URL;
        Log.i(TAG, "农事任务分配请求_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 农事任务分配请求-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "农事任务分配请求 map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 农事任务取消
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-26 下午5:32:19
     */
    public static void fartaskCancel(final Map<String, String> map,
                                     final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3农事任务分配请求路径
        String url = WapiUtil.WAPI_FAR_TASK_CANCEL_URL;
        Log.i(TAG, "农事任务取消请求_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 农事任务取消请求-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "农事任务取消请求 map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 农事任务关闭
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-26 上午11:29:19
     */
    public static void fartaskClose(final Map<String, String> map,
                                    final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3农事任务分配请求路径
        String url = WapiUtil.WAPI_FAR_TASK_CLOSE_URL;
        Log.i(TAG, "农事任务关闭请求_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 农事任务关闭请求-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "农事任务关闭请求 map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 我的每日农事任务关闭
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017年09月09日13:29:49
     */
    public static void myfartaskClose(final Map<String, String> map,
                                      final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3我的每日农事任务请求路径
        String url = WapiUtil.WAPI_MY_FAR_TASK_CLOSE_URL;
        Log.i(TAG, "我的每日农事任务关闭请求_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 我的每日农事任务关闭请求-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "我的每日农事任务关闭请求 map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 农事任务复制
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-26 下午5:33:01
     */
    public static void fartaskCopy(final Map<String, String> map,
                                   final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3农事任务分配请求路径
        String url = WapiUtil.WAPI_FAR_TASK_COPY_URL;
        Log.i(TAG, "农事任务复制请求_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 农事任务复制请求-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "农事任务复制请求 map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 执行农事任务列表
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-27 上午9:38:50
     */
    public static void taskExecuteList(final Map<String, String> map,
                                       final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3农事任务分配请求路径
        String url = WapiUtil.WAPI_TASK_EXECUTE_URL;
        Log.i(TAG, "执行任务请求_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 执行任务请求-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "执行任务请求 map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 执行任务明细
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-27 下午4:03:21
     */
    public static void taskExecDetail(final Map<String, String> map,
                                      final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3农事任务分配请求路径
        String url = WapiUtil.WAPI_TASK_EXECUTE_DETAIL_URL;
        Log.i(TAG, "执行任务明细请求_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 执行任务明细请求-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "执行任务明细请求 map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 取消执行任务
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-27 下午7:08:38
     */
    public static void taskExecuCancel(final Map<String, String> map,
                                       final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3取消执行任务请求路径
        String url = WapiUtil.WAPI_TASK_EXECUTE_CANCEL_URL;
        Log.i(TAG, "取消执行任务请求_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 取消执行任务请求-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "取消执行任务请求 map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 取消每日农事任务
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017年09月09日13:05:15
     */
    public static void mytodayfartaskCancel(final Map<String, String> map,
                                            final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3取消每日农事任务请求路径
        String url = WapiUtil.WAPI_MY_FAR_TASK_CANCEL_URL;
        Log.i(TAG, "取消每日农事任务请求_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 取消每日农事任务请求-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "取消每日农事任务请求 map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 关闭执行任务
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-27 下午7:20:25
     */
    public static void taskExecuClose(final Map<String, String> map,
                                      final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3农事任务分配请求路径
        String url = WapiUtil.WAPI_TASK_EXECUTE_CLOSE_URL;
        Log.i(TAG, "关闭执行任务请求_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 关闭执行任务请求-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "关闭执行任务请求 map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 农事记录列表
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: changyiqiang
     * @date 创建时间：2017-7-22上午11:25:15
     */
    public static void apiFramingRecordGetList(final Map<String, String> map,
                                               final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3农事记录列表请求路径
        String url = WapiUtil.WAPI_FRAMING_RECORD_GETLIST_URL;
        Log.i(TAG, "农事记录列表请求_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 农事记录列表请求-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "农事记录列表请求 map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 农事记录查看
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: changyiqiang
     * @date 创建时间：2017-7-22上午11:25:15
     */
    public static void apiFramingRecordGetInfo(final Map<String, String> map,
                                               final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3农事记录查看请求路径
        String url = WapiUtil.WAPI_FRAMING_RECORD_GETINFO_URL;
        Log.i(TAG, "农事记录查看请求_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 农事记录查看请求-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "农事记录查看请求 map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 农事记录新增
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: changyiqiang
     * @date 创建时间：2017-7-22 上午11:25:15
     */
    public static void apiFramingRecordCreateInfo(
            final Map<String, String> map, final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3农事记录新增路径
        String url = WapiUtil.WAPI_FRAMING_RECORD_CREATEINFO_URL;
        Log.i(TAG, "农事记录新增请求_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 农事记录新增请求-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "农事记录新增请求 map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }


    /**
     * 当日任务列表
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-25 上午11:25:15
     */
    public static void myTodayTaskList(final Map<String, String> map,
                                       final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3当日任务列表路径
        String url = WapiUtil.WAPI_TODAY_TASK_LIST_URL;
        Log.i(TAG, "当日任务列表请求_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 当日任务列表请求-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "当日任务列表请求 map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }


    /**
     * 当日任务领用
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017年09月07日10:50:02
     */
    public static void todaytaskCollect(final Map<String, String> map,
                                        final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3当日任务领用路径
        String url = WapiUtil.WAPI_TODAY_TASK_COLLECT_URL;
        Log.i(TAG, "当日任务明细请求_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 当日任务领用请求-> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "当日任务领用请求 map-> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }


    /**
     * 大棚网关列表监控请求
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: jcy
     * @date 创建时间：2017-8-11 下午2:14:23
     */
    public static void gHouseGateMoSmartList(final Map<String, String> map,
                                             final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3大棚网关列表监控请求路径
        String url = WapiUtil.WAPI_GHOUSE_GATE_MOSMART_LIST_URL;
        Log.i(TAG, "大棚网关列表监控_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 大棚网关列表监控 ---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "大棚网关列表监控  map--> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 大棚环境平均值
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: jcy
     * @date 创建时间：2017-7-14 上午11:25:44
     */
    public static void gHoueEnvAverage(final Map<String, String> map,
                                       final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3大棚用户明细路径
        String url = WapiUtil.WAPI_GHOUSE_AVERAGE_URL;
        Log.i(TAG, "大棚环境平均值_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 大棚环境平均值 ---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "大棚环境平均值 map--> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }


    /**
     * 大棚环境平均值折线图
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: jcy
     * @date 创建时间：2017-7-14 上午11:25:44
     */
    public static void gHoueEnvAverageSgateCode(final Map<String, String> map,
                                                final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3大棚环境平均值折线图路径
        String url = WapiUtil.WAPI_GHOUSE_LINE_CHART_URL;
        Log.i(TAG, "大棚环境平均值折线图_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 大棚环境平均值 折线图---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "大棚环境平均值折线图 map--> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 大棚传感器折线图
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: jcy
     * @date 创建时间：2017-7-14 上午11:25:44
     */
    public static void gHoueEnvAverageSensor(final Map<String, String> map,
                                             final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3大棚传感器折线图
        String url = WapiUtil.WAPI_GHOUSE_Env_AVERAGE_SENSOR_URL;
        Log.i(TAG, "大棚传感器折线图_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 大棚传感器 折线图---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "大棚传感器折线图 map--> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 大棚网关传感器列表监控请求
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: jcy
     * @date 创建时间：2017-8-11 下午2:14:23
     */
    public static void gHouseGateMoSmartSensorList(
            final Map<String, String> map, final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3大棚网关传感器列表监控路径
        String url = WapiUtil.WAPI_GHOUSE_DATA_MOSMART_SENSOR_LIST_URL;
        Log.i(TAG, "大棚网关传感器列表监控_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 大棚网关传感器列表监控 ---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "大棚网关传感器列表监控  map--> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 大棚网关继电器列表监控请求
     *
     * @param map
     * @param callback
     * @return void
     * @author 作者 E-mail: jcy
     * @date 创建时间：2017-8-11 下午2:14:23
     */
    public static void gHouseGateMoSmartRelayList(
            final Map<String, String> map, final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3大棚网关继电器查询路径
        String url = WapiUtil.WAPI_GHOUSE_DATA_MOSMART_RELAY_LIST_URL;
        Log.i(TAG, "大棚网关继电器列表监控_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 大棚网关继电器列表监控 ---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // 在这里设置需要post的参数
                Log.i(TAG, "大棚网关继电器列表监控  map--> " + map);
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * 大棚网关继电器列表监控请求2
     *
     * @param devUuid
     * @param callback
     * @return void
     * @author 作者 E-mail: jcy
     * @date 创建时间：2017-8-11 下午2:14:23
     */
    public static void gHouseGateMoSmartRelayAllList(String devUuid, final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        // v3大棚网关继电器查询路径2
        String url = WAPI_HTTP_SMARTGATE_TOW_URL + devUuid + INFO_URL;
        Log.i(TAG, "大棚网关继电器列表2监控_url -> " + url);
        final StringRequest jsonRequest = new StringRequest(
                Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "response 大棚网关继电器列表2监控 ---> " + response);
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
                callback.onError(0, error.getMessage());
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // TODO Auto-generated method stub
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InRlc3QwMSIsImNyZWF0ZWRBdCI6IjIwMTctMDgtMjVUMDc6MDY6NDIuMTk0WiIsImlhdCI6MTUwMzY0NDgwMn0.o4res1z8Vk0lw59tQVHBJl6OKlVx12TpnGJV-Cdh1eE");
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }


    /*-------------------------------------------------------------------------------EndEndEndEndEndEndV3手机APPEndEndEndEndEndEndEnd-----------------------------------------------------------------------------------------/


    /**
     * @description：第三方登录请求方法
     * @author：gang
     * @date time：2017-6-9 上午9:50:18
     */
    public static void loginThird(final String type, final String openid,
                                  final String access_token, final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        Map<String, String> map = new HashMap<String, String>();
        map.put("type", type);
        map.put("open_id", openid);
        map.put("access_token", access_token);
        String url = WapiUtil.WAPI_LOGIN_THIRD_URL;
        JSONObject jsonObject = new JSONObject(map);
        JsonRequest<JSONObject> jsonRequest = new JsonRequest<JSONObject>(
                Method.POST, url, jsonObject.toString(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "response -> " + response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);

            }
        }) {

            @Override
            protected Response<JSONObject> parseNetworkResponse(
                    NetworkResponse response) {
                try {
                    String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    String content = jsonString;

                    if (null != callback) {
                        callback.onSuccess(content);
                    }
                    /*
                     * if (content != null && !content.equals("")) { try {
					 * JSONObject jsonObject = new JSONObject(content); if
					 * (jsonObject.has("err")) { Message message = new
					 * Message(); LOGIN login = new LOGIN(); login.type = type;
					 * login.openid = openid; login.session_token =
					 * access_token; message.obj = login; message.what =
					 * LOGIN_THIRD_BACK_ERROR; mHandler.sendMessage(message); }
					 * else { TAPreferenceUtil preferenceUtil = TAPreferenceUtil
					 * .getInstance(mContext); if (type.equals("qq")) { if
					 * (jsonObject.has("username")) { String username =
					 * jsonObject .getString("username");
					 * preferenceUtil.saveUser(username); } if
					 * (jsonObject.has("session_token")) { String username =
					 * jsonObject .getString("session_token"); preferenceUtil
					 * .saveSessionToken(username);
					 * WapiUtil.saveSessionToken(username); } } Intent intent =
					 * new Intent(mContext, HomeAcitity.class);
					 * mContext.startActivity(intent); finish(); } } catch
					 * (JSONException e) { e.printStackTrace(); } }
					 */
                    return Response.success(new JSONObject(jsonString),
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }
        };
        queue.add(jsonRequest);
    }

    /**
     * @description：注册请求方法
     * @author：gang
     * @date time：2017-6-9 上午9:50:59
     */
    public static void Register(String username, String psdTwo, String phone,
                                String sms_code, final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        Map<String, String> map = new HashMap<String, String>();
        // String psdTwo = mPsdTwo.getText().toString();
        map.put("username", username);
        map.put("password", psdTwo);
        map.put("phone", phone);
        map.put("sms_code", sms_code);
        String url = WapiUtil.WAPI_REGISTER_PHONE_URL;
        JSONObject jsonObject = new JSONObject(map);
        JsonRequest<JSONObject> jsonRequest = new JsonRequest<JSONObject>(
                Method.POST, url, jsonObject.toString(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "response -> " + response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);

            }
        }) {

            @Override
            protected Response<JSONObject> parseNetworkResponse(
                    NetworkResponse response) {
                try {
                    String msg = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    if (null != callback) {
                        callback.onSuccess(msg);
                    }

                    return Response.success(new JSONObject(msg),
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * @param phone
     * @param callback
     * @description：获取短息验证码请求方法
     * @author：gang
     * @date time：2017-6-9 上午9:51:33
     */
    public static void SmsCode(String phone, final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        /*
         * Map<String, String> map = new HashMap<String, String>();
		 * map.put("phone", phone);
		 */
        String url = WapiUtil.WAPI_USER_SEND_SMSCODE_URL + "?phone=" + phone;
        // JSONObject jsonObject = new JSONObject(map);
        JsonRequest<JSONObject> jsonRequest = new JsonRequest<JSONObject>(
                // Method.GET , url, jsonObject.toString(),
                Method.GET, url, "", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "response -> " + response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);

            }
        }) {

            @Override
            protected Response<JSONObject> parseNetworkResponse(
                    NetworkResponse response) {
                try {
                    String msg = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    callback.onSuccess(msg);
                    // strContent = msg;
                    return Response.success(new JSONObject(msg),
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
    }

    /**
     * @description：第三方注册请求方法
     * @author：gang
     * @date time：2017-6-9 上午9:52:04
     */
    public static void registerThird(String user, String psd,
                                     final String type, final String openid, final String access_token,
                                     final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        Map<String, String> map = new HashMap<String, String>();
        map.put("username", user);
        map.put("password", psd);
        map.put("type", type);
        map.put("open_id", openid);
        map.put("access_token", access_token);
        String url = WapiUtil.WAPI_REGISTER_THIRD_URL;
        JSONObject jsonObject = new JSONObject(map);
        JsonRequest<JSONObject> jsonRequest = new JsonRequest<JSONObject>(
                Method.POST, url, jsonObject.toString(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            protected Response<JSONObject> parseNetworkResponse(
                    NetworkResponse response) {
                try {
                    String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    String content = jsonString;
                    Log.e("content", content);
                    if (null != callback)
                        callback.onSuccess(content);
                    /*
                     * if (content != null && !content.equals("")) { try {
					 * JSONObject jsonObject = new JSONObject(content); if
					 * (jsonObject.has("id")) { Intent it = new Intent();
					 * it.putExtra("type", type); it.putExtra("openid", openid);
					 * it.putExtra("access_token", access_token); setResult(2,
					 * it); ((Activity) mContext).finish(); } } catch
					 * (JSONException e) { e.printStackTrace(); } }
					 */
                    return Response.success(new JSONObject(jsonString),
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }
        };
        queue.add(jsonRequest);

    }

    /**
     * @description：添加设备请求方法
     * @author：gang
     * @date time：2017-6-9 上午9:48:46
     */
    public static void addDevice(final String devUuid, String alias,
                                 final MYCallBack callback) {

        RequestQueue queue = MyVolley.getRequestQueue();
        String url = WAPI_USER_SMARTGATE_URL + devUuid;
        // Map<String, String> map = new HashMap<String, String>();

        // map.put("dev_uuid", devUuid);
        // map.put("alias", alias);
        // String url = WapiUtil.WAPI_USER_ADD_DEVICE_URL;
        // JSONObject jsonObject = new JSONObject(map);
        JsonRequest<JSONObject> jsonRequest = new JsonRequest<JSONObject>(
                Method.POST, url, "",// jsonObject.toString(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "response -> " + response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);

            }
        }) {

            @Override
            protected Response<JSONObject> parseNetworkResponse(
                    NetworkResponse response) {
                try {
                    String msg = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    if (msg != null && callback != null) {

                        callback.onSuccess(msg);
                    }
                    return Response.success(new JSONObject(msg),
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization",
                        "bearer " + WapiUtil.getSessionToken());
                return params;
            }
        };
        queue.add(jsonRequest);
    }

    /**
     * @description：删除设备请求方法
     * @author：gang
     * @date time：2017-6-9 上午9:49:22
     */
    public static void delDevice(final String devUuid, final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        String url = WAPI.WAPI_USER_URL + WAPI.SMARTGATE_URL + devUuid;
        JsonRequest<JSONObject> jsonRequest = new JsonRequest<JSONObject>(
                Method.DELETE, url, "", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "response -> " + response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);

            }
        }) {

            @Override
            protected Response<JSONObject> parseNetworkResponse(
                    NetworkResponse response) {
                try {
                    String msg = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    if (msg != null && callback != null) {
                        callback.onSuccess(msg);
                    } else
                        Log.e("delete device", "" + devUuid);
                    return Response.success(new JSONObject(msg),
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization",
                        "bearer " + WapiUtil.getSessionToken());
                return params;
            }
        };
        queue.add(jsonRequest);
    }

    /**
     * @description：设备请求方法
     * @author：gang
     * @date time：2017-6-9 上午9:49:37
     */
    public static void advice(String feedback, final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        Map<String, String> map = new HashMap<String, String>();
        // String feedback = feedback_text.getText().toString();
        map.put("advice", feedback);
        String url = WapiUtil.WAPI_ADVICE_URL;
        JSONObject jsonObject = new JSONObject(map);
        JsonRequest<JSONObject> jsonRequest = new JsonRequest<JSONObject>(
                Method.POST, url, jsonObject.toString(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "response -> " + response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);

            }
        }) {

            @Override
            protected Response<JSONObject> parseNetworkResponse(
                    NetworkResponse response) {
                try {
                    String msg = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    if (null != callback) {
                        if (msg != null) {
                            callback.onSuccess(msg);
                        } else {
                            callback.onError(1, msg);
                        }
                    }
                    return Response.success(new JSONObject(msg),
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization",
                        "bearer " + WapiUtil.getSessionToken());
                return params;
            }
        };
        queue.add(jsonRequest);
    }

    public static void getDeviceList(String url, final MYCallBack callback) {
        RequestQueue queue = MyVolley.getRequestQueue();
        JsonRequest<JSONObject> jsonRequest = new JsonRequest<JSONObject>(
                Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "response -> " + response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);

            }
        }) {

            @Override
            protected Response<JSONObject> parseNetworkResponse(
                    NetworkResponse response) {
                try {
                    String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    if (null != callback) {
                        callback.onSuccess(jsonString);
                    }
                    /*
                     * final String content = jsonString; try {
					 * contentWebView.post(new Runnable() {
					 *
					 * @Override public void run() {
					 * contentWebView.loadUrl("javascript:show(" + content +
					 * ")"); } }); } catch (Exception e) { e.printStackTrace();
					 * }
					 */
                    return Response.success(new JSONObject(jsonString),
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }
        };
        queue.add(jsonRequest);
    }

    public static void requestColumns(final MYCallBack cb) {
        RequestQueue queue = MyVolley.getRequestQueue();
        String url = ECMSConfig.CONFIG_GET_COLUMN_LIST_URL;
        JsonRequest<JSONObject> jsonRequest = new JsonRequest<JSONObject>(
                Method.GET, url, "", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "response -> " + response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);

            }
        }) {

            @Override
            protected Response<JSONObject> parseNetworkResponse(
                    NetworkResponse response) {
                try {
                    String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    Log.e("response", jsonString);
                    if (null != cb) {
                        cb.onSuccess(jsonString);
                    }
                    // parseColumns(jsonString);
                    // initViewPager();
                    return Response.success(new JSONObject(jsonString),
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return super.getHeaders();
            }
        };
        queue.add(jsonRequest);
    }

    public static int getRelayStatus(/* String devUuid, */String componentid,
                                     final MYCallBack cb) {
        String url = WAPI_ENDDEVICE_URL + componentid + INFO_URL;
        RequestQueue queue = MyVolley.getRequestQueue();
        JsonRequest<JSONObject> jsonRequest = new JsonRequest<JSONObject>(
                Method.GET, url, "",// jsonObject.toString(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "response -> " + response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
            }
        }) {

            @Override
            protected Response<JSONObject> parseNetworkResponse(
                    NetworkResponse response) {
                try {
                    String msg = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    if (null != cb) {
                        cb.onSuccess(msg);
                    }

                    return Response.success(new JSONObject(msg),
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization",
                        "bearer " + WapiUtil.getSessionToken());

                return params;
            }
        };
        queue.add(jsonRequest);
        return 0;
    }

    public static int addCustomAlarmStrategy(AlarmStrategy strategy,
                                             final MYCallBack cb) {
        String url = WAPI_USER_TOW_URL + CUSTOM_ALARM_STRATEGY_URL;

        RequestQueue queue = MyVolley.getRequestQueue();
        Map<String, String> map = new HashMap<String, String>();
        // map.put("dev_uuid", devUuid);
        map.put("alarm_strategy", strategy.toJson().toString());

        JSONObject jsonObject = new JSONObject(map);
        JsonRequest<JSONObject> jsonRequest = new JsonRequest<JSONObject>(
                Method.POST, url, jsonObject.toString(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "response -> " + response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
            }
        }) {

            @Override
            protected Response<JSONObject> parseNetworkResponse(
                    NetworkResponse response) {
                try {
                    String msg = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    if (null != cb) {
                        cb.onSuccess(msg);
                    }

                    return Response.success(new JSONObject(msg),
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization",
                        "bearer " + WapiUtil.getSessionToken());

                return params;
            }
        };
        queue.add(jsonRequest);
        return 0;
    }

    public static int setCustomAlarmStrategy(AlarmStrategy strategy,
                                             final MYCallBack cb) {
        String url = WAPI_USER_URL + CUSTOM_ALARM_STRATEGY_URL
                + strategy.getStrategy_id();

        RequestQueue queue = MyVolley.getRequestQueue();
        Map<String, String> map = new HashMap<String, String>();
        // map.put("dev_uuid", devUuid);
        map.put("alarm_strategy", strategy.toJson().toString());
        // remove("strategy_id").
        JSONObject jsonObject = new JSONObject(map);
        JsonRequest<JSONObject> jsonRequest = new JsonRequest<JSONObject>(
                Method.PUT, url, jsonObject.toString(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "response -> " + response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
            }
        }) {

            @Override
            protected Response<JSONObject> parseNetworkResponse(
                    NetworkResponse response) {
                try {
                    String msg = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    if (null != cb) {
                        cb.onSuccess(msg);
                    }

                    return Response.success(new JSONObject(msg),
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization",
                        "bearer " + WapiUtil.getSessionToken());

                return params;
            }
        };
        queue.add(jsonRequest);
        return 0;
    }

    public static int delCustomAlarmStrategy(String strategyId,
                                             final MYCallBack cb) {
        String url = WAPI_USER_TOW_URL + CUSTOM_ALARM_STRATEGY_URL + strategyId;

        RequestQueue queue = MyVolley.getRequestQueue();
        Map<String, String> map = new HashMap<String, String>();
        // map.put("dev_uuid", devUuid);

        JSONObject jsonObject = new JSONObject(map);
        JsonRequest<JSONObject> jsonRequest = new JsonRequest<JSONObject>(
                Method.DELETE, url, jsonObject.toString(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "response -> " + response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
            }
        }) {

            @Override
            protected Response<JSONObject> parseNetworkResponse(
                    NetworkResponse response) {
                try {
                    String msg = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    if (null != cb) {
                        cb.onSuccess(msg);
                    }

                    return Response.success(new JSONObject(msg),
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization",
                        "bearer " + WapiUtil.getSessionToken());

                return params;
            }
        };
        queue.add(jsonRequest);
        return 0;
    }

    // Strategy for Shed, Shedid==devUuid
    public static int getShedStrategy(String devUuid, final MYCallBack cb) {
        String url = WAPI_USER_TOW_URL + SMARTGATE_URL + devUuid
                + USER_STRATEGYSETTINGS_URL;

        RequestQueue queue = MyVolley.getRequestQueue();

        // JSONObject jsonObject = new JSONObject(map);
        JsonRequest<JSONObject> jsonRequest = new JsonRequest<JSONObject>(
                Method.GET, url, "", // jsonObject.toString(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "response -> " + response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
            }
        }) {

            @Override
            protected Response<JSONObject> parseNetworkResponse(
                    NetworkResponse response) {
                try {
                    String msg = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    if (null != cb) {
                        cb.onSuccess(msg);
                    }

                    return Response.success(new JSONObject(msg),
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization",
                        "bearer " + WapiUtil.getSessionToken());

                return params;
            }
        };
        jsonRequest.setShouldCache(false);
        queue.add(jsonRequest);
        return 0;
    }

    public static int addOrUpdateShedStrategy(String devUuid,
                                              String strategy_type, AlarmStrategy strategy, final MYCallBack cb) {
        String url = WAPI_USER_TOW_URL + SMARTGATE_URL + devUuid
                + USER_STRATEGYSETTINGS_URL;

        RequestQueue queue = MyVolley.getRequestQueue();
        Map<String, String> map = new HashMap<String, String>();
        // map.put("dev_uuid", devUuid);
        map.put("alarm_strategy", strategy.toJson().toString());
        map.put("strategy_type", strategy_type);
        map.put("strategy_id", strategy.getStrategy_id());

        JSONObject jsonObject = new JSONObject(map);
        JsonRequest<JSONObject> jsonRequest = new JsonRequest<JSONObject>(
                Method.POST, url, jsonObject.toString(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "response -> " + response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
            }
        }) {

            @Override
            protected Response<JSONObject> parseNetworkResponse(
                    NetworkResponse response) {
                Log.d(TAG, "response -> " + response.toString());
                try {
                    String msg = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    if (null != cb) {
                        cb.onSuccess(msg);
                    }

                    return Response.success(new JSONObject(msg),
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization",
                        "bearer " + WapiUtil.getSessionToken());

                return params;
            }
        };
        queue.add(jsonRequest);
        return 0;
    }

    // url：/api/analyses/histories
    public static int getAnalysesHistories(String sn, String device_type,
                                           String date, String scope, final MYCallBack cb) {
        String url = WAPI_ANALYSES_HISTORIES_URL;

        RequestQueue queue = MyVolley.getRequestQueue();
        Map<String, String> map = new HashMap<String, String>();
        map.put("sn", sn);
        map.put("device_type", device_type);
        map.put("date", date);
        map.put("scope", scope);

        JSONObject jsonObject = new JSONObject(map);
        JsonRequest<JSONObject> jsonRequest = new JsonRequest<JSONObject>(
                Method.GET, url, jsonObject.toString(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "response -> " + response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
            }
        }) {

            @Override
            protected Response<JSONObject> parseNetworkResponse(
                    NetworkResponse response) {
                try {
                    String msg = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    if (null != cb) {
                        cb.onSuccess(msg);
                    }

                    return Response.success(new JSONObject(msg),
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization",
                        "bearer " + WapiUtil.getSessionToken());

                return params;
            }
        };
        queue.add(jsonRequest);
        return 0;
    }

    public static int delShedStrategy(String devUuid, final MYCallBack cb) {
        String url = WAPI_USER_TOW_URL + SMARTGATE_URL + devUuid
                + USER_STRATEGYSETTINGS_URL;

        RequestQueue queue = MyVolley.getRequestQueue();
        Map<String, String> map = new HashMap<String, String>();
        // map.put("dev_uuid", devUuid);
        // map.put("strategy", strategy.toJson().toString());

        // JSONObject jsonObject = new JSONObject(map);
        JsonRequest<JSONObject> jsonRequest = new JsonRequest<JSONObject>(
                Method.DELETE, url, "", // jsonObject.toString(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "response -> " + response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
            }
        }) {

            @Override
            protected Response<JSONObject> parseNetworkResponse(
                    NetworkResponse response) {
                try {
                    String msg = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    if (null != cb) {
                        cb.onSuccess(msg);
                    }

                    return Response.success(new JSONObject(msg),
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization",
                        "bearer " + WapiUtil.getSessionToken());

                return params;
            }
        };
        queue.add(jsonRequest);
        return 0;
    }

    private static String makeEventsString(List<String> events) {
        String s = "";
        for (int i = 0; i < events.size(); i++) {
            s += events.get(i);
            if (i != events.size() - 1) {
                s += ",";
            }
        }
        return s;
    }

    public static int markReadAlarmEvent(List<String> eventIds,
                                         final MYCallBack cb) {
        String url = USER_ALARMEVENTS_URL;

        RequestQueue queue = MyVolley.getRequestQueue();
        Map<String, String> map = new HashMap<String, String>();
        String ss = makeEventsString(eventIds);
        map.put("event_id_arr", ss);
        // map.put("strategy", strategy.toJson().toString());

        JSONObject jsonObject = new JSONObject(map);
        JsonRequest<JSONObject> jsonRequest = new JsonRequest<JSONObject>(
                Method.PUT, url, jsonObject.toString(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "response -> " + response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);
            }
        }) {

            @Override
            protected Response<JSONObject> parseNetworkResponse(
                    NetworkResponse response) {
                try {
                    String msg = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    if (null != cb) {
                        cb.onSuccess(msg);
                    }

                    return Response.success(new JSONObject(msg),
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization",
                        "bearer " + WapiUtil.getSessionToken());

                return params;
            }
        };
        queue.add(jsonRequest);
        return 0;
    }

    public static void getAccessTokenAndOpenidBycode(final String code,
                                                     final MYCallBack callback) {

        RequestQueue queue = MyVolley.getRequestQueue();

        String url = WAPI.WEIXIN_authorization_code_URL + "appid="
                + TAMyProfile.WEIXIN_APP_ID + "&secret="
                + TAMyProfile.WEIXIN_SECRET + "&code=" + code
                + "&grant_type=authorization_code";
        Log.e("weixin", url);
        JsonRequest<JSONObject> jsonRequest = new JsonRequest<JSONObject>(
                Method.GET, url, "", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "response -> " + response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.getMessage(), error);

            }
        }) {

            @Override
            protected Response<JSONObject> parseNetworkResponse(
                    NetworkResponse response) {
                try {
                    String msg = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    if (msg != null && callback != null) {
                        callback.onSuccess(msg);
                    } else
                        Log.e("check out", "" + code);
                    return Response.success(new JSONObject(msg),
                            HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                } catch (JSONException je) {
                    return Response.error(new ParseError(je));
                }
            }

        };
        queue.add(jsonRequest);
    }
}
