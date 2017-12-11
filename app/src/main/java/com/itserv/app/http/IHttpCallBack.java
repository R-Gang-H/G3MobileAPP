package com.itserv.app.http;

/**
 * 作者： 周作威 on 2017/9/19 09:42.
 * 类描述：网络回调接口类
 */
public interface IHttpCallBack<T> {
    void onError(Throwable throwable);

    void onSuccess(T date);
}
