package com.app.itserv.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.app.itserv.BaseActivity;
import com.itserv.shed.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 根据url显示网页内容 参数： url getIntent().getStringExtra("url"); supportzoom
 * 1为支持，0为不支持,默认支持
 */
@SuppressLint("JavascriptInterface")
public class WebViewActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.pb)
    ProgressBar pb;
    @BindView(R.id.webview)
    WebView webview;
    private String tag = "WebViewActivity";
    private WebViewActivity instance;
    int progressStatus = 0; // 记录进度对话框的完成百分比
    private Context mContext;

    @Override
    protected int layoutViewId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        instance = this;
        initMainViews();
    }

    /**
     * 初始化主界面
     */
    @SuppressLint("SetJavaScriptEnabled")
    private void initMainViews() {
        // 设置WebView属性，能够执行Javascript脚本
        this.webview.getSettings().setJavaScriptEnabled(true);
        this.webview.getSettings().setDomStorageEnabled(true);
        this.webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(
                true);
        // 设置隐藏滚动栏白边
        this.webview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        int choice = getIntent().getIntExtra("supportzoom", 0);
        // 加载需要显示的网页
        String url = getIntent().getStringExtra("url");
        if (url == null) {
            url = "";
        }
        // 设置Web视图
        this.webview.setWebViewClient(new MyWebViewClient());
        this.webview.setWebChromeClient(new MyWebChromeClient());
        this.webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        this.webview.clearCache(true);
        this.webview.loadUrl(url);
        webview.addJavascriptInterface(new Util(), "util");
    }

    @OnClick(R.id.img_back)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }

    // 插件类
    private final class Util {
        @JavascriptInterface
        public void goBack() {
            goback();
        }
    }

    private void goback() {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                if (webview.canGoBack()) {
                    webview.goBack(); // goBack()表示返回WebView的上一页面
                } else {
                    finish();
                }
            }
        });
    }

    @Override
    // 设置回退
    // 覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            // System.out.println("--------getOriginalUrl:"+webview.getOriginalUrl());
            // System.out.println("--------getUrl:"+webview.getUrl());
            String cururl = webview.getUrl();

            // webview.getContentDescription()
            if (webview.canGoBack()) {
                webview.goBack(); // goBack()表示返回WebView的上一页面
            } else {
                finish();
            }
            return true;
        }
        return false;
    }

    private class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (null == pb)
                return;
            if (newProgress < 100)
                pb.setVisibility(View.VISIBLE);
            else
                pb.setVisibility(View.GONE);
            pb.setProgress(newProgress);
        }
    }

    // Web视图
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.indexOf("bike") != -1 || url.indexOf(".gif") != -1
                    || url.indexOf(".png") != -1) {
                Uri uri = Uri.parse(url);
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                // Intent it = new Intent();
                // it.setAction("android.intent.action.VIEW");
                // it.setData(uri);
                startActivity(it);
            } else if (url.startsWith("popall:")) {
                finish();
            } else if (url.startsWith("openapk:")) {
                // view.loadData(data, mimeType, encoding);
            } else {
                view.loadUrl(url);
            }

            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }

    final class InJavaScriptLocalObj {
        public void showSource(String html) {
        }
    }


}
