package com.app.itserv.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.itserv.BaseActivity;
import com.app.itserv.fragments.VersionUtilNew;
import com.google.gson.Gson;
import com.itserv.app.bean.Version;
import com.itserv.app.config.Config;
import com.itserv.app.util.QuitDialogUtil;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.yycloud.core.utils.http.AsyncHttpClient;
import com.yycloud.core.utils.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

/**
 * 设置
 *
 * @author haoruigang
 * @Package com.app.itserv.activity
 * @project yyShed
 * @ClassName: SettingUserActivity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2017-8-14 下午7:08:29
 */

public class SettingUserActivity extends BaseActivity implements OnClickListener {

    private ImageView ImgBack;

    private Context mContext;

    private LinearLayout mAboutMe, mCheckUpdate, lLProtocol;

    @Override
    protected int layoutViewId() {
        return R.layout.setting_user;
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        initView();
    }

    private void initView() {
        // TODO Auto-generated method stub
        ImgBack = (ImageView) findViewById(R.id.img_back);
        ImgBack.setOnClickListener(this);
        mCheckUpdate = (LinearLayout) findViewById(
                R.id.check_update);
        mCheckUpdate.setOnClickListener(this);
        mAboutMe = (LinearLayout) findViewById(R.id.aboutme);
        mAboutMe.setOnClickListener(this);
        lLProtocol = (LinearLayout) findViewById(R.id.protocol);
        lLProtocol.setOnClickListener(this);// 用户协议
        findViewById(R.id.loginout).setOnClickListener(this);
        findViewById(R.id.share).setOnClickListener(this);// 分享
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            case R.id.check_update:
                checkUpdate();
                break;
            case R.id.aboutme:// 关于我们
                Intent intent = new Intent(mContext, AboutActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.protocol:// 用户协议
                startActivity(new Intent(this, ProtocolActivity.class));
                break;
            case R.id.loginout:// 登出
                QuitDialogUtil.getInstance(this).showQuitDialog();
                break;
            case R.id.share:// 分享
                Intent intent3 = new Intent(this, ShareActivity.class);
                intent3.putExtra("type", 1);
                startActivity(intent3);
                break;
            default:
                break;
        }
    }

    /**
     *  * @Description:检查更新  * @param  * void  * @exception:  * @author: axin  *
     *
     * @time:2016-8-14 上午11:27:25
     */
    private void checkUpdate() {
        // 获取service版本号
        AsyncHttpClient updateClient = new AsyncHttpClient();
        updateClient.get(mContext, Config.VERSION_UPDATE_URL,
                new AsyncHttpResponseHandler() {

                    @Override
                    @Deprecated
                    public void onSuccess(int statusCode, Header[] headers,
                                          String content) {
                        // 获取本地版本号(versionCode)
                        int versionCodeNative = 0;
                        try {
                            versionCodeNative = mContext.getPackageManager()
                                    .getPackageInfo(mContext.getPackageName(),
                                            0).versionCode;
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }

                        Gson gson = new Gson();
                        Version version = gson.fromJson(content, Version.class);
                        if (Integer.parseInt(version.getVersionCode()) > versionCodeNative) {
                            VersionUtilNew.getInstance(SettingUserActivity.this)
                                    .showVersionUpdateDialog(versionCodeNative,
                                            version.getVersionCode(),
                                            version.getVersionName(),
                                            Config.DOWN_LOAD_URL, version.getDesc());
                        } else
                            ToastUtils.makeTextShort("已是最新版本");

                        super.onSuccess(statusCode, headers, content);
                    }
                });
    }
}
