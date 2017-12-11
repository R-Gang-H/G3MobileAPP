package com.app.itserv.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.itserv.MineApplication;
import com.app.itserv.activity.CameraActivity;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * 作者： 周作威 on 2017/10/10 09:39.
 * 类描述：添加多张图片集合控件
 */
public class ImageListView extends LinearLayout implements View.OnClickListener {
    @BindViews({R.id.layou_img_img1, R.id.layou_img_img2, R.id.layou_img_img3, R.id.layou_img_img4, R.id.layou_img_img5})
    List<ImageView> images;
    @BindViews({R.id.layou_img_close1, R.id.layou_img_close2, R.id.layou_img_close3, R.id.layou_img_close4, R.id.layou_img_close5})
    List<ImageView> imgCloses;
    @BindViews({R.id.layou_img_fl1, R.id.layou_img_fl2, R.id.layou_img_fl3, R.id.layou_img_fl4, R.id.layou_img_fl5})
    List<FrameLayout> layoutFls;
    List<String> thumb;
    private boolean isUpdate;//是否可修改

    public ImageListView(Context context) {
        super(context);
        init();
    }

    public ImageListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ImageListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.layout_imagelistview, this);
        ButterKnife.bind(this, view);
        for (int i = 0; i < images.size(); i++) {
            images.get(i).setOnClickListener(this);
            imgCloses.get(i).setOnClickListener(this);
        }
        setList(new ArrayList<String>());
    }

    public void setUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
        setView();
    }

    public void setList(List<String> thumb) {
        this.thumb = thumb;
        setView();
    }

    public void setView() {
        if (thumb != null && images.size() > 0) {
            for (int i = 0; i < images.size(); i++) {
                if (i < thumb.size()) {
                    layoutFls.get(i).setVisibility(View.VISIBLE);
                    imgCloses.get(i).setVisibility(isUpdate ? View.VISIBLE : View.INVISIBLE);
                    ImageLoader.getInstance().displayImage(thumb.get(i), images.get(i), MineApplication.default_img);
                } else if (thumb.size() < 5 && i == thumb.size()) {
                    if (isUpdate) {
                        layoutFls.get(i).setVisibility(View.VISIBLE);
                        imgCloses.get(i).setVisibility(View.INVISIBLE);
                        images.get(i).setImageResource(R.drawable.adds);
                    } else {
                        layoutFls.get(i).setVisibility(View.INVISIBLE);
                        imgCloses.get(i).setVisibility(View.INVISIBLE);
                    }
                } else {
                    layoutFls.get(i).setVisibility(View.INVISIBLE);
                    imgCloses.get(i).setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (ToastUtils.isFastClick())
            return;
        switch (v.getId()) {
            case R.id.layou_img_img1:
                setTranSdvs(0);
                break;
            case R.id.layou_img_img2:
                setTranSdvs(1);
                break;
            case R.id.layou_img_img3:
                setTranSdvs(2);
                break;
            case R.id.layou_img_img4:
                setTranSdvs(3);
                break;
            case R.id.layou_img_img5:
                setTranSdvs(4);
                break;
            case R.id.layou_img_close1:
                deleteTranSdvs(0);
                break;
            case R.id.layou_img_close2:
                deleteTranSdvs(1);
                break;
            case R.id.layou_img_close3:
                deleteTranSdvs(2);
                break;
            case R.id.layou_img_close4:
                deleteTranSdvs(3);
                break;
            case R.id.layou_img_close5:
                deleteTranSdvs(4);
                break;

        }
    }

    private void setTranSdvs(int index) {
        if (thumb.size() <= index) {
            Intent intent = new Intent(getContext(), CameraActivity.class);
            Bundle bundle = new Bundle();
            bundle.putBoolean(CameraActivity.ISUPDATE, true);
            intent.putExtras(bundle);
            ((Activity) getContext()).startActivityForResult(intent, CameraActivity.ACTIVITY_RESULT);
        }
    }

    //删除图片
    public void deleteTranSdvs(int index) {
        if (thumb.size() > index) {
            thumb.remove(index);
            setView();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK)
            if (requestCode == CameraActivity.ACTIVITY_RESULT) {
                thumb.add("http://yyossbucket.oss-cn-beijing.aliyuncs.com/"+data.getData().toString());
                setView();
            }
    }
}
