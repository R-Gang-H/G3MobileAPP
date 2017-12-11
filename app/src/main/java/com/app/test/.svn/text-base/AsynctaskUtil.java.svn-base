package com.app.test;

import com.blueware.com.google.gson.internal.T;

import android.os.AsyncTask;

/**
 *  * @Description:asynctask封装
 *  * @author:axin
 *  * @time:2016-10-8 下午3:25:00
 */
public class AsynctaskUtil<T> extends AsyncTask<T, Integer, T> {
    private AsyncTaskListener asyncTaskListener;

    public AsynctaskUtil() {
        super();
    }

    /**
     * 准备
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (asyncTaskListener != null) {
            asyncTaskListener.excutePrepare();
        }
    }

    /**
     * 子线程执行
     */
    @Override
    protected T doInBackground(T... params) {
        if (asyncTaskListener != null) {
            T t = (T) asyncTaskListener.excuteInBackGround(params[0]);
            return t;
        }
        return null;
    }

    /**
     * 进度更新
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (asyncTaskListener != null) {
            asyncTaskListener.excuteProgress(values[0]);
        }
    }

    /**
     * 主线程执行
     */
    @Override
    protected void onPostExecute(T result) {
        super.onPostExecute(result);
        if (asyncTaskListener != null) {
            asyncTaskListener.excuteInPost(result);
        }
    }

    /**
     *  * @Description:取消执行
     *  * @param
     *  * void
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-9 下午2:50:21
     */
    public void cancel() {
        if (!isCancelled()) {
            cancel(true);
        }
    }

    /**
     *  * @Description:设置监听
     *  * @param @param asyncTaskListener
     *  * @param @return
     *  * AsynctaskUtil<T>
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-10-9 下午3:02:02
     */
    public AsynctaskUtil setAsyncTaskListener(AsyncTaskListener asyncTaskListener) {
        this.asyncTaskListener = asyncTaskListener;
        return this;
    }

    /**
     *  * @Description:监听接口
     *  * @author:axin
     *  * @time:2016-10-9 下午3:02:19
     */
    interface AsyncTaskListener<T> {
        void excutePrepare();

        T excuteInBackGround(T t);

        void excuteInPost(T t);

        void excuteProgress(int progress);

        void excuteCancel();
    }
}
