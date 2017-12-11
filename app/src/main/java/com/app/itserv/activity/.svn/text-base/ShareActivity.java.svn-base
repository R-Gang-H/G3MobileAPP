package com.app.itserv.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.itserv.BaseActivity;
import com.itserv.app.http.MyProgressDialog;
import com.itserv.app.util.ImageUtils;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 分享(长按保存)
 *
 * @author Administrator
 */
public class ShareActivity extends BaseActivity {

    @BindView(R.id.tv_img_upload)
    TextView tvImgUpload;
    @BindView(R.id.img_app_upload)
    ImageView imgAppUpload;
    private int type;//1分享，2公众号
    private MyProgressDialog dialog;

    @Override
    protected int layoutViewId() {
        return R.layout.activity_share;
    }

    @Override
    protected void init() {
        super.init();
        dialog = new MyProgressDialog(this);
        type = getIntent().getIntExtra("type", 1);
        if (type == 1) {
            imgAppUpload.setImageResource(R.drawable.share);
            tvImgUpload.setText("扫码下载APP（Android版本）\n长按二维码保存");
        } else {
            imgAppUpload.setImageResource(R.drawable.qr_public);
            tvImgUpload.setText("微信扫码关注“棚联网”微信公众号\n长按二维码保存");
        }
        imgAppUpload.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPreservation();
                return true;
            }
        });
    }

    public void setPreservation() {
        if (null != dialog && !dialog.isShowing())
            dialog.show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Uri pathUri = ImageUtils.createImagePathUri("QR_" + System.currentTimeMillis() + ".jpg");
                Bitmap bmp;
                if (type == 1)
                    bmp = BitmapFactory.decodeResource(getResources(), R.drawable.share);
                else
                    bmp = BitmapFactory.decodeResource(getResources(), R.drawable.qr_public);
                final String s = ImageUtils.compressBitmap(bmp, pathUri.getPath());
                ShareActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (null != dialog && dialog.isShowing())
                            dialog.dismiss();
                        ToastUtils.makeTextShort("图片成功保存至" + s);
                        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                        Uri uri = Uri.parse(s);
                        intent.setData(uri);
                        ShareActivity.this.sendBroadcast(intent);
                    }
                });
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != dialog && dialog.isShowing())
            dialog.dismiss();
    }

    // 添加返回点击事件  周作威  2017/9/19 17:53
    @OnClick(R.id.img_back)
    public void onClick(View view) {
        finish();
    }
}
