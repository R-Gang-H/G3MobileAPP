package com.app.itserv.activity;

import com.itserv.shed.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

/**
 * 绑定大棚
 *
 * @author Administrator
 */
public class BoundGreenHouseActivity extends Activity implements
        OnClickListener {

    private Context mContext;
    private ImageView ImgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bound_gre_hou);
        mContext = this;
        initView();
    }

    private void initView() {
        // TODO Auto-generated method stub
        ImgBack = (ImageView) findViewById(R.id.img_back);
        ImgBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.img_back:// 返回
                finish();
                break;
            default:
                break;
        }
    }
}
