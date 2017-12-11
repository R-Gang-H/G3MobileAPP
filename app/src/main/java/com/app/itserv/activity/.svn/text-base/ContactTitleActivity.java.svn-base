package com.app.itserv.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itserv.app.util.PackageUtils;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;

/**
 * 联系客服弹出的窗体
 *
 * @author Administrator
 */
public class ContactTitleActivity extends Activity implements OnClickListener {

    private static final String TAG = "ContactTitleActivity";
    /* 弹窗start */
    private RelativeLayout rlClick;
    private LinearLayout ll_Phone_Call, ll_QQ_Ser, ll_Internet;
    private TextView tv_Phone_Nub, tv_QQ_Nub, tv_Web_Text;
    private Context context;
    /* 弹窗end */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_title);
        this.context = this;
        initView();
    }

    private void initView() {
        rlClick = (RelativeLayout) findViewById(R.id.rl_click);
        rlClick.setOnClickListener(this);
        ll_Phone_Call = (LinearLayout) findViewById(R.id.ll_phone_call);
        ll_QQ_Ser = (LinearLayout) findViewById(R.id.ll_qq_ser);
        ll_Internet = (LinearLayout) findViewById(R.id.ll_internet);
        ll_Phone_Call.setOnClickListener(this);
        ll_QQ_Ser.setOnClickListener(this);
        ll_Internet.setOnClickListener(this);
        tv_Phone_Nub = (TextView) findViewById(R.id.tv_phone_nub);
        tv_QQ_Nub = (TextView) findViewById(R.id.tv_qq_nub);
        tv_Web_Text = (TextView) findViewById(R.id.tv_web_text);

    }

    /***
     * 联系客服点击事件
     */
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.rl_click://全局点击
                finish();
                break;
            case R.id.ll_phone_call:// 拨打电话
                String mobileText = tv_Phone_Nub.getText().toString()
                        .replace("-", "").trim();
                Log.i(TAG, mobileText);
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mobileText));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.ll_qq_ser:// 联系QQ
                String qqNum = "643138413";
                if (PackageUtils.isApkInstall(ContactTitleActivity.this, "com.tencent.mobileqq")) {
                    Uri uri = Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=" + qqNum + "&version=1");
                    Intent it = new Intent(Intent.ACTION_VIEW, uri);
                    it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(it);
                } else {
                    ToastUtils.makeTextShort("请先安装QQ");
                }
                break;
            case R.id.ll_internet:// 跳转网页
                String inter = tv_Web_Text.getText().toString().trim();
                Uri uri = Uri.parse("http://" + inter + "/");
                Intent it = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(it);
                break;
            default:
                break;
        }
    }
}
