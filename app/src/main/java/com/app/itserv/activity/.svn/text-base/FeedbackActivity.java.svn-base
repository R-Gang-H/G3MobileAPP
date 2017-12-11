package com.app.itserv.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.itserv.BaseActivity;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.WapiUtilEx;

public class FeedbackActivity extends BaseActivity implements
        View.OnClickListener {
    private Context mContext;
    private View mBackView;
    private TextView mTitleTv;
    private EditText feedback_text;
    private Button mCommit;
    private static String TAG = "FeedbackActivity";

    @Override
    protected int layoutViewId() {
        return R.layout.feed_back_lay;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        // 设置标题不显示
        initViews();
    }

    private void initViews() {
        mBackView = findViewById(R.id.btn_back);
        mBackView.setOnClickListener(this);
        mTitleTv = (TextView) findViewById(R.id.title_txt);
        mTitleTv.setText(this.getResources().getString(R.string.feedback_title));
        feedback_text = (EditText) findViewById(R.id.feedback_text);
        mCommit = (Button) findViewById(R.id.commit);
        mCommit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.commit:
                String feedback = feedback_text.getText().toString();
                if (feedback != null && !feedback.equals("")) {
                    advice();
                }
                break;
            default:
                break;
        }
    }

    private void advice() {
        String feedback = feedback_text.getText().toString();
        WapiUtilEx.advice(feedback, new MYCallBack() {
            @Override
            public void onError(int code, String message) {
                mHandler.sendEmptyMessage(2);
                Log.e("feedback", "null");
            }

            @Override
            public void onSuccess(String response) {
                mHandler.sendEmptyMessage(1);
                Log.e("feedback", response);
            }

        });
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    ToastUtils.makeTextShort("感谢您的反馈！");
                    break;
                case 2:
                    ToastUtils.makeTextShort("反馈失败！");
                    break;
                default:
                    break;
            }
        }

    };
}
