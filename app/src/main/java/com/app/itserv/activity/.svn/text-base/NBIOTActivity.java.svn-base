package com.app.itserv.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.itserv.BaseActivity;
import com.itserv.shed.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by EVA on 2017/9/29.
 */
public class NBIOTActivity extends BaseActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.aimg_add_NBIOT)
    ImageView imgAddNBIOT;
    @BindView(R.id.viewsNBIOT)
    LinearLayout viewsNBIOT;
    @BindView(R.id.tv_show)
    TextView tvShow;

    @Override
    protected int layoutViewId() {
        return R.layout.activity_nbiot_lay;
    }

    @Override
    protected void init() {
        super.init();


    }

    @OnClick({R.id.img_back, R.id.aimg_add_NBIOT})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();;
                break;
            case R.id.aimg_add_NBIOT:
//                Intent intent = new Intent(mContext, AddNBIOTActivity.class);
//                ((this).startActivityForResult(intent, 0);
                break;
        }
    }
}
