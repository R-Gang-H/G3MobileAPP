package com.app.itserv.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.itserv.shed.R;

/**
 * @author Eason
 * @time 2017/9/20.
 * @des
 */

public class WeatherActivity extends Activity implements View.OnClickListener {

    private WebView webview;
    private ImageView mImgBack;
    private String RequestUrl = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        webview = (WebView) findViewById(R.id.webview);

        String weburl = (String) getIntent().getExtras().get("WebURL");

        if (weburl.equals("weather")) {
            RequestUrl = "http://m.weather.com.cn/d/town/index?lat=39.90403&lon=116.407526";
        } else if (weburl.equals("farnews")) {
            RequestUrl = " http://m.xnynews.com/";
        } else {
            RequestUrl = "https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzI5NjU5NTU4Nw==&scene=124&#wechat_redirect";
        }

        //设置WebView属性，能够执行Javascript脚本
        webview.getSettings().setJavaScriptEnabled(true);
        //加载需要显示的网页'[]
        webview.loadUrl(RequestUrl);
        //设置Web视图
        webview.setWebViewClient(new HelloWebViewClient());
        mImgBack = (ImageView) findViewById(R.id.img_back);// 返回
        mImgBack.setOnClickListener(this);
    }

    @Override
    //设置回退
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack(); //goBack()表示返回WebView的上一页面
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            default:
                break;
        }
    }

    //Web视图
    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


}