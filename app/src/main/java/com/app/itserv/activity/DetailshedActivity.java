package com.app.itserv.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.itserv.jparser.JsonGreenHouseViewObject;
import com.app.itserv.jparser.JsonGreenHouseViewObject.ObjBean;
import com.app.itserv.jparser.JsonGreenHouseViewObject.ObjBean.GreenhouseBean;
import com.app.itserv.jparser.JsonGreenHouseViewObject.ObjBean.YyPlantingplanEntityListBean;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.DateLocalUtil;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

import java.util.List;

/**
 * 大棚详情
 * 2017年9月14日09:07:57
 *
 * @author Administrator
 */
public class DetailshedActivity extends Activity implements OnClickListener {


    public static final String TAG = "DetailshedActivity";

    // 填充器
    private LayoutInflater inflater;
    private ImageView imgBack;

    private ObjBean gHouseViewBean;

    private TextView plantLocation, tvGreenHouseName,

    tvGreenHouseCode, tvTotalArea, tvWidth, tvHeight, tvPlantingArea,

    startDistriTime;
    private LinearLayout detailHeadLay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_shed_lay);

        initView();
        init();

    }

    private void init() {
        // TODO Auto-generated method stub
        String greenhouseId = (String) getIntent().getExtras().get(
                "greenhouseId");
        getGreenHouseParticulars(greenhouseId);
    }

    private void initView() {
        inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        tvGreenHouseName = (TextView) findViewById(R.id.tv_green_house_name);// 大棚名字
        detailHeadLay = (LinearLayout) findViewById(R.id.detailHead_lay);//种植计划
        imgBack = (ImageView) findViewById(R.id.img_back);// 退出
        tvGreenHouseCode = (TextView) findViewById(R.id.tv_green_house_code);// 大棚编号
        tvTotalArea = (TextView) findViewById(R.id.tv_total_area);// 总面积
        tvWidth = (TextView) findViewById(R.id.tv_width);// 长度
        tvHeight = (TextView) findViewById(R.id.tv_height);// 宽度
        tvPlantingArea = (TextView) findViewById(R.id.tv_Planting_area);// 种植面积detailHead_lay
        startDistriTime = (TextView) findViewById(R.id.start_distri_time);// 启动时间
        plantLocation = (TextView) findViewById(R.id.plant_location);// 地址
        imgBack.setOnClickListener(this);
    }

    /**
     * 大棚种植作物信息赋值
     *
     * @param yyPlantingplanEntityListBean
     */
    private void setgHousePlanTing(YyPlantingplanEntityListBean yyPlantingplanEntityListBean) {
        View setgHousePlanTingView = View.inflate(DetailshedActivity.this, R.layout.shed_crops_lay,
                null);

        TextView plantName = (TextView) setgHousePlanTingView
                .findViewById(R.id.plant_name);//作物名称
        TextView plantTime = (TextView) setgHousePlanTingView
                .findViewById(R.id.plant_time);//种植时间
        TextView harvestArea = (TextView) setgHousePlanTingView
                .findViewById(R.id.harvest_area);//种植面积
        TextView harvestTime = (TextView) setgHousePlanTingView
                .findViewById(R.id.harvest_time);//收获时间
        TextView expectation = (TextView) setgHousePlanTingView
                .findViewById(R.id.expectation);//预估产量

        plantName.setText(TextUtils.isEmpty(yyPlantingplanEntityListBean
                .getCropCategoryDefine()) ? "" : yyPlantingplanEntityListBean
                .getCropCategoryDefine());

        plantTime.setText(DateLocalUtil.getDate(TextUtils.isEmpty(String
                .valueOf(yyPlantingplanEntityListBean.getActualPlantdateStart())) ? "" : String
                .valueOf(yyPlantingplanEntityListBean.getActualPlantdateStart())));

        harvestArea.setText(TextUtils.isEmpty(yyPlantingplanEntityListBean
                .getPlanArea()) ? "" : yyPlantingplanEntityListBean
                .getPlanArea());

        harvestTime.setText(DateLocalUtil.getDate(TextUtils.isEmpty(String
                .valueOf(yyPlantingplanEntityListBean.getActualPlantdateEnd())) ? "" : String
                .valueOf(yyPlantingplanEntityListBean.getActualPlantdateEnd())));

        expectation.setText(TextUtils.isEmpty(yyPlantingplanEntityListBean
                .getPlanYield()) ? "" : yyPlantingplanEntityListBean
                .getPlanYield());
        detailHeadLay.addView(setgHousePlanTingView);
    }

    /**
     * 大棚详情对象赋值
     *
     * @param greenhouse
     */
    private void setgHouseViewText(GreenhouseBean greenhouse) {
        // TODO Auto-generated method stub
        tvGreenHouseName.setText(TextUtils.isEmpty(greenhouse
                .getGhouseFullname()) ? "" : greenhouse.getGhouseFullname());//大棚名称

        tvGreenHouseCode
                .setText(TextUtils.isEmpty(greenhouse.getGhouseCode()) ? ""
                        : greenhouse.getGhouseCode());//大棚编号

        tvTotalArea.setText(TextUtils.isEmpty(greenhouse.getCoveredArea()) ? ""
                : greenhouse.getCoveredArea());//总面积

        tvPlantingArea.setText(TextUtils.isEmpty(greenhouse.getUsedArea()) ? ""
                : greenhouse.getUsedArea());//种植面积

        tvHeight.setText(TextUtils.isEmpty(greenhouse.getAreaWidth()) ? ""
                : greenhouse.getAreaWidth());//长度

        tvWidth.setText(TextUtils.isEmpty(greenhouse.getAreaLength()) ? ""
                : greenhouse.getAreaLength());//宽度

        startDistriTime.setText(DateLocalUtil.getDate(TextUtils.isEmpty(String
                .valueOf(greenhouse.getOpenDateOpen())) ? "" : String
                .valueOf(greenhouse.getOpenDateOpen())));//启动日期

        plantLocation.setText(greenhouse.getRegionIdCountry()+greenhouse.getRegionIdProvince() + greenhouse.getRegionIdCity()
                + greenhouse.getRegionIdDistrict()) ;//大棚位置
    }

    /**
     * @author jcy
     * @Package com.app.itserv.activity
     * @project yyshed
     * @Description: TODO(大棚查看请求)
     * @date 2017-9-20 20:03:32
     */
    private void getGreenHouseParticulars(String greenhouseId) {
        String token = PreferencesUtils.getString(this, "token");// 用户token
        String currTenantId = PreferencesUtils.getString(this, "tenantId");// 商户id
        HttpManager.getInstance().gHouseParticulars(TAG, token, currTenantId, greenhouseId, new HttpCallBack<JsonGreenHouseViewObject>(DetailshedActivity.this,true) {

            @Override
            public void onSuccess(JsonGreenHouseViewObject date) {
                if (date != null) {
                    String message = date.getMsg();// 提示消息
                    Log.v("Request", "Request :" + message);
                    if (date.isSuccess()) {
                        gHouseViewBean = date.getObj();
                        detailHeadLay.removeAllViews();
                        setgHouseViewText(gHouseViewBean.getGreenhouse());// 赋值 （大棚详情对象）
                        List<YyPlantingplanEntityListBean> yyPlantingplanEntityList = gHouseViewBean
                                .getYyPlantingplanEntityList();
                        for (YyPlantingplanEntityListBean yyPlantingplanEntityListBean : yyPlantingplanEntityList) {
                            setgHousePlanTing(yyPlantingplanEntityListBean);
                        }
                    } else {// 失败
                        if (!TextUtils.isEmpty(message)) {
                            ToastUtils.makeTextShort(message);
                        } else {
                            ToastUtils.makeTextShort("网络不可用!");
                        }
                    }
                }
            }

            @Override
            public void onError(Throwable throwable) {
                ToastUtils.makeTextShort("网络不可用!");
            }
        });
    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.img_back:
                finish();
                break;
            default:
                break;
        }
    }
}
