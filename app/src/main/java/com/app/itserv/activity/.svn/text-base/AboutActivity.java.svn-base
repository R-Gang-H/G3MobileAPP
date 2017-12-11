package com.app.itserv.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.itserv.BaseActivity;
import com.app.itserv.jparser.JsonProtocolObject;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @project name：yyshed
 * @type name：AboutActivity
 * @description：关于我们
 * @author：gang
 * @date time：2017-6-5 上午8:50:55
 */
public class AboutActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.btn_back)
    LinearLayout btnBack;
    @BindView(R.id.title_txt)
    TextView titleTxt;
    @BindView(R.id.versionCode)
    TextView versionCode;
    @BindView(R.id.copyright)
    TextView copyright;
    @BindView(R.id.content)
    TextView content;

    @Override
    protected int layoutViewId() {
        return R.layout.about_lay;
    }


    @Override
    protected void init() {
        super.init();
        titleTxt.setText(this.getResources().getString(R.string.about_title));
        String token = PreferencesUtils.getString(this, "token");// token
        String currTenantId = PreferencesUtils.getString(this, "tenantId");// 商户id
        String clientCode = "cpms";// 平台编码：
        HttpManager.getInstance().doAbout(TAG, token, currTenantId, clientCode, new HttpCallBack<JsonProtocolObject>(AboutActivity.this, true) {
            @Override
            public void onSuccess(JsonProtocolObject jsonprotocol) {
                if (jsonprotocol != null) {
                    if (!jsonprotocol.isSuccess()) {// 请求失败
                        String getmsg = jsonprotocol.getMsg();// 消息提醒
                        ToastUtils.makeTextShort(getmsg);
                        return;
                    }
                    String topicContent = "";
                    JsonProtocolObject.ObjBean objbean = jsonprotocol.getObj();
                    if (null != objbean) {
                        topicContent = objbean.getTopicContent();// 关于我们内容
                        content.setText(Html.fromHtml(topicContent));
                    }
                }
            }

            @Override
            public void onError(Throwable throwable) {
                ToastUtils.makeTextShort(R.string.network_error);
            }
        });
        versionCode.setText("V " + getVersionName());
    }

    @OnClick(R.id.btn_back)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            default:
                break;
        }
    }

    private String getVersionName() {
        String version = "";
        try {// 获取packagemanager的实例
            PackageManager packageManager = getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo;

            packInfo = packageManager.getPackageInfo(getPackageName(), 0);

            version = packInfo.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }
}
