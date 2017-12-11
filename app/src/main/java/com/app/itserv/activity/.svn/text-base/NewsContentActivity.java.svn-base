package com.app.itserv.activity;

import org.apache.http.protocol.HTTP;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.TextView;

import com.app.itserv.BaseActivity;
import com.itserv.shed.R;
import com.yycloud.core.beans.ECMSColumnContentList;

public class NewsContentActivity extends BaseActivity implements
        View.OnClickListener {
    private Context mContext;
    private View mBackView;
    private TextView mTitleTv;
    private int fontSize = 1;
    private final int FONT_SIZE_SMALL = 0, FONT_SIZE_NORMAL = 1,
            FONT_SIZE_LARGE = 2;
    private WebView webView;
    private ECMSColumnContentList mContent;

    @Override
    protected int layoutViewId() {
        return R.layout.news_content_lay;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContent = (ECMSColumnContentList) this.getIntent()
                .getSerializableExtra("content");
        mContext = this;
        initViews();
    }

    private void initViews() {
        mTitleTv = (TextView) findViewById(R.id.title_txt);
        mTitleTv.setText(mContent.getTitle());
        mBackView = findViewById(R.id.btn_back);
        mBackView.setOnClickListener(this);
        webView = (WebView) findViewById(R.id.webview);
        // 设置webview的宽度自适应
        WebSettings webSettings = webView.getSettings(); // webView: 类WebView的实例
        webSettings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        // webSettings.setPluginsEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setJavaScriptEnabled(true);
        // txt_font_btn_colorset();
        if (fontSize == FONT_SIZE_LARGE) {
            webSettings.setTextSize(WebSettings.TextSize.LARGER);
        } else if (fontSize == FONT_SIZE_SMALL) {
            webSettings.setTextSize(WebSettings.TextSize.SMALLER);
        }
        webView.loadDataWithBaseURL("", mContent.getContent(), "text/html",
                HTTP.UTF_8, "");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            default:
                break;
        }
    }

}
