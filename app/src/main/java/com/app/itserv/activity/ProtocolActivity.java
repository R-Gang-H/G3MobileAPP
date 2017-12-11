package com.app.itserv.activity;

import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.itserv.BaseActivity;
import com.app.itserv.jparser.JsonProtocolObject;
import com.app.itserv.jparser.JsonProtocolObject.ObjBean;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @project name：yyshed
 * @type name：ProtocolActivity
 * @description：用户协议类
 * @author：gang
 * @date time：2017-6-5 上午8:51:34
 */
public class ProtocolActivity extends BaseActivity implements OnClickListener {


    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_protocol)
    TextView tvProtocol;

    @Override
    protected int layoutViewId() {
        return R.layout.protocol_lay;
    }

    @Override
    protected void init() {
        super.init();
        String token = PreferencesUtils.getString(this, "token");// token
        String currTenantId = PreferencesUtils.getString(this, "tenantId");// 商户id
        String clientCode = "cpms";// 平台编码：
        HttpManager.getInstance().doprotacol(TAG, token, currTenantId, clientCode, new HttpCallBack<JsonProtocolObject>(ProtocolActivity.this, true) {
            @Override
            public void onSuccess(JsonProtocolObject jsonprotocol) {
                if (jsonprotocol != null) {
                    if (!jsonprotocol.isSuccess()) {// 请求失败
                        String getmsg = jsonprotocol.getMsg();// 消息提醒
                        ToastUtils.makeTextShort(getmsg);
                        return;
                    }
                    // 解析json
                    String topicContent = "";
                    ObjBean objbean = jsonprotocol.getObj();
                    if (null != objbean) {
                        topicContent = objbean.getTopicContent();// 关于我们内容
                        tvProtocol.setText(Html.fromHtml(topicContent));// 用户协议信息
                    }
                }
            }

            @Override
            public void onError(Throwable throwable) {
                ToastUtils.makeTextShort(R.string.network_error);
            }
        });
    }

    @OnClick(R.id.img_back)
    public void onClick(View view) {
        finish();
    }
}
