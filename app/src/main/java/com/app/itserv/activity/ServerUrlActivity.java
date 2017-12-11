package com.app.itserv.activity;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.itserv.app.util.QuitDialogUtil;
import com.itserv.shed.R;
import com.yycloud.app.MyApplication;
import com.yycloud.app.utils.TAPreferenceUtil;
import com.yycloud.app.utils.TAUtils;

/**
 * @author 作者 E-mail:
 * @version 1.0
 * @date 创建时间：2017-8-12 上午10:52:10
 * @parameter
 * @return
 */

public class ServerUrlActivity extends Activity implements OnClickListener {

    private Context mContext;

    private ImageView ImgBack;
    private EditText editText1, editText2, editText3, editText4;
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serverurl_layout);
        mContext = this;
        initView();
    }

    private void initView() {
        // TODO Auto-generated method stub
        ImgBack = (ImageView) findViewById(R.id.img_back);// 返回
        ImgBack.setOnClickListener(this);
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            case R.id.button1:// 修改按钮
                String ip = editText1.getText().toString().trim();
                String popt = editText2.getText().toString().trim();
                String project = editText3.getText().toString().trim();
                if (TextUtils.isEmpty(ip) || TextUtils.isEmpty(popt)
                        || TextUtils.isEmpty(project)) {
                    TAUtils.toastMessage((Activity) mContext, "ip、端口号或项目名称为空");
                    break;
                }
                TAPreferenceUtil.getInstance(MyApplication.getContext())
                        .saveString("WAPI_IP", ip);
                TAPreferenceUtil.getInstance(MyApplication.getContext())
                        .saveString("WAPI_HTTP_PORT", popt);
                TAPreferenceUtil.getInstance(MyApplication.getContext())
                        .saveString("PROJECTNAME", project);
                editText4.setText("服务器地址已更改，当前地址为	:http://" + ip + ":" + popt + "/"
                        + project + "/,正在跳转登录页面完成系统地址更改...");

                Builder builder = new Builder(ServerUrlActivity.this);
                builder.setMessage("跳转至登录页面以完成系统地址更改");
                builder.setTitle("提示");
                builder.setCancelable(false);
                builder.setPositiveButton("确认",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                // TODO Auto-generated method stub
                                arg0.dismiss();
                                QuitDialogUtil.getInstance(ServerUrlActivity.this).exit();
                            }
                        });
                builder.create().show();
                break;
            default:
                break;
        }
    }
}
