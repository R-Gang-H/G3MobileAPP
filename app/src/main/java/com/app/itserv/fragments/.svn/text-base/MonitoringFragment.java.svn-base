package com.app.itserv.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.commons.ToUIEvent;
import com.app.itserv.activity.DetailshedActivity;
import com.app.itserv.activity.EnvironmentalCurveActivity;
import com.app.itserv.activity.RealMonitorActivity;
import com.app.itserv.adapters.GhosueGetMoSmartAdapter;
import com.app.itserv.jparser.JsonGreenHouseGatMoSmartObject;
import com.app.itserv.jparser.JsonGreenhouseAcerageObject;
import com.app.itserv.views.LoadingFrameView;
import com.app.itserv.views.PullToRefreshListView;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.TAUtils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 监控主界面
 * 2017年9月13日16:40:02
 *
 * @author haoruigang
 */
public class MonitoringFragment extends BaseFragment implements AdapterView.OnItemSelectedListener {
    @BindView(R.id.sp_sex)
    Spinner spSex;//监控大棚网关选择
    @BindView(R.id.btn_refresh)
    Button btnRefresh;
    @BindView(R.id.ll_gender)
    LinearLayout llGender;
    @BindView(R.id.ll_birthday)
    LinearLayout llBirthday;
    @BindView(R.id.column_list)
    PullToRefreshListView columnList;
    @BindView(R.id.ld_monitor_view)
    LoadingFrameView ldMonitorView;
    @BindView(R.id.btn_detail_shed)
    Button btnDetailShed;
    @BindView(R.id.btn_envir_curve)
    Button btnEnvirCurve;
    @BindView(R.id.btn_real_monitor)
    Button btnRealMonitor;
    Unbinder unbinder;

    private LinearLayout greenhousesEnvironmentAverage;//平均值列表显示列表View

    private List<JsonGreenHouseGatMoSmartObject.ObjBean> gHouseObjList;
    private GhosueGetMoSmartAdapter gHouseMoSmartAdapter;

    private String sgateSN;//网关SN号
    private String greenhouseId;//大棚Id
    private String DevName;//设备名称

    private JsonGreenhouseAcerageObject.ObjBean gHouseAcerageObj;//大棚监控平均值对象

    // 填充器
    private LayoutInflater inflater;
    private int se;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        initView();
        init();
    }

    private void init() {
        getGreenHouseList();//大棚列表请求
    }

    @Override
    protected int layoutViewId() {
        return R.layout.monitor_lay;
    }

    @Override
    public void onUpdateUI() {

    }


    private void initView() {
        inflater = (LayoutInflater) getActivity()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // TODO Auto-generated method stub
        View inflate = inflater.inflate(R.layout.environmenta_verage_view, null);
        greenhousesEnvironmentAverage = (LinearLayout) inflate.findViewById(
                R.id.greenhouses_environment_average);// view 容器
        columnList.addHeaderView(inflate);
        columnList.setAdapter(null);
        spSex.setOnItemSelectedListener(this);
        columnList.setOnRefreshListener(new PullToRefreshListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getGreenHouseAverage(sgateSN);
            }
        });
    }

    @OnClick({R.id.btn_refresh, R.id.btn_detail_shed, R.id.btn_envir_curve, R.id.btn_real_monitor})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_refresh:// 刷新数据
                ldMonitorView.setProgressShown(true);
                getGreenHouseList();
                break;
            case R.id.btn_detail_shed:
                if (greenhouseId == null) {// 大棚详情
                    return;
                }
                startActivity(new Intent(getActivity(), DetailshedActivity.class).putExtra("greenhouseId", greenhouseId));//大棚Id
                break;
            case R.id.btn_envir_curve:// 环境曲线
                if (DevName == null && sgateSN == null) {
                    return;
                }
                startActivity(new Intent(getActivity(), EnvironmentalCurveActivity.class).putExtra("sgateSN", sgateSN).putExtra("DevName", DevName));//设备SN号,设备名称
                break;
            case R.id.btn_real_monitor:// 实时监控
                if (DevName == null && sgateSN == null) {
                    return;
                }
                startActivity(new Intent(getActivity(), RealMonitorActivity.class).putExtra("DevName", DevName).putExtra("sgateSN", sgateSN));//设备名称，设备SN号
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        JsonGreenHouseGatMoSmartObject.ObjBean greenHouse = gHouseObjList.get(position);// 显示环境平均值
        DevName = greenHouse.getDev_name();
        sgateSN = greenHouse.getSn();
        greenhouseId = greenHouse.getGhouseid();
        ldMonitorView.setProgressShown(true);
        se = position;
        getGreenHouseAverage(sgateSN);//大棚环境平均值
        try {
            //以下三行代码是解决问题所在
            Field field = AdapterView.class.getDeclaredField("mOldSelectedPosition");
            field.setAccessible(true);  //设置mOldSelectedPosition可访问
            field.setInt(spSex, AdapterView.INVALID_POSITION); //设置mOldSelectedPosition的值
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    /**
     * @author jcy
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: GreenHouseAction
     * @Description: TODO(大棚网关列表数据请求)
     * @date 2017-9-20 20:00:58
     */
    private void getGreenHouseList() {
        String token = PreferencesUtils.getString(getActivity(), "token");// 用户token
        String currTenantId = PreferencesUtils.getString(getActivity(), "tenantId");// 商户id
        HttpManager.getInstance().gHouseGateMoSmartList(TAG, token, currTenantId, new HttpCallBack<JsonGreenHouseGatMoSmartObject>() {

            @Override
            public void onSuccess(JsonGreenHouseGatMoSmartObject date) {
                if (date != null) {
                    String message = date.getMsg();// 提示消息
                    if (date.isSuccess()) {
                        gHouseObjList = date.getObj();
                        if (gHouseObjList.size() <=0){
                            ldMonitorView.setNoShown(true);
                            return;
                        }
                        if (gHouseMoSmartAdapter == null) {
                            gHouseMoSmartAdapter = new GhosueGetMoSmartAdapter(getActivity(),
                                    gHouseObjList);
                            spSex.setAdapter(gHouseMoSmartAdapter);
                        } else {
                            gHouseMoSmartAdapter.setListobjBeans(gHouseObjList);
                            gHouseMoSmartAdapter.notifyDataSetChanged();
                        }
                        if (spSex.getSelectedItemPosition() == 0 && sgateSN != null) {

                            getGreenHouseAverage(sgateSN);
                        } else {
                            spSex.setSelection(se, true);
                        }
                    } else { // 失败
                        if (!TextUtils.isEmpty(message)) {
                            ToastUtils.makeTextShort(message);
                        } else {
                            ToastUtils.makeTextShort("网络不可用!");
                        }
                        ldMonitorView.setNoShown(true);
                    }
                }
            }

            @Override
            public void onError(Throwable throwable) {
                ToastUtils.makeTextShort("网络不可用!");
                ldMonitorView.setErrorShown(true, new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ldMonitorView.setProgressShown(true);
                        getGreenHouseList();
                    }
                });
            }
        });
    }


    /**
     * @author jcy
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: GreenHouseAction
     * @Description: TODO(大棚环境平均值数据请求)
     * @date 2017-9-20 20:00:48
     */
    private void getGreenHouseAverage(final String sgateSN) {
        String token = PreferencesUtils.getString(getActivity(), "token");// 用户token
        String currTenantId = PreferencesUtils.getString(getActivity(), "tenantId");// 商户id
        HttpManager.getInstance().gHouseAverage(TAG, token, currTenantId, sgateSN, new HttpCallBack<JsonGreenhouseAcerageObject>() {
            @Override
            public void onSuccess(JsonGreenhouseAcerageObject date) {
                columnList
                        .onRefreshComplete(getString(R.string.xlistview_header_last_time)
                                + new Date().toLocaleString());
                if (date != null) {
                    String message = date.getMsg();// 提示消息
                    if (date.isSuccess()) {
                        gHouseAcerageObj = date.getObj();
                        ShowSensorView(gHouseAcerageObj);
                    } else { // 失败
                        if (!TextUtils.isEmpty(message)) {
                            ToastUtils.makeTextShort(message);
                        } else {
                            ToastUtils.makeTextShort("网络不可用!");
                        }
                        ldMonitorView.setNoShown(true);
                    }
                } else {
                    ldMonitorView.setNoShown(true);
                }
            }

            @Override
            public void onError(Throwable throwable) {
                columnList
                        .onRefreshComplete(getString(R.string.xlistview_header_last_time)
                                + new Date().toLocaleString());
                ToastUtils.makeTextShort("网络不可用!");
                ldMonitorView.setErrorShown(true, new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ldMonitorView.setProgressShown(true);
                        getGreenHouseAverage(sgateSN);
                    }
                });
            }
        });
    }

    /**
     * 动态添加传感器
     *
     * @param gHouseAcerageObj
     */
    private void ShowSensorView(
            JsonGreenhouseAcerageObject.ObjBean gHouseAcerageObj) {
        greenhousesEnvironmentAverage.removeAllViews();// 清空view
        if (null == gHouseAcerageObj)
            return;

        if (gHouseAcerageObj.getSmoke() == null
                && gHouseAcerageObj.getAir_humidity() == null
                && gHouseAcerageObj.getAir_temperature() == null
                && gHouseAcerageObj.getCo2_ppm() == null
                && gHouseAcerageObj.getLux() == null
                && gHouseAcerageObj.getSoil_humidity() == null
                && gHouseAcerageObj.getSoil_temperature() == null) {
            ldMonitorView.setNoShown(true);
            return;
        } else {
            ldMonitorView.delayShowContainer(true);
        }


        if (!TextUtils.isEmpty(gHouseAcerageObj.getAir_humidity())
                || !TextUtils.isEmpty(gHouseAcerageObj.getAir_temperature())) {// 空气温湿度
            View sensorTemperatureHumidity = inflater.inflate(
                    R.layout.sensor_temperature_humidity_lay, null);
            TextView tvSeTemphumTime = (TextView) sensorTemperatureHumidity
                    .findViewById(R.id.tv_se_temphum_time);
            TextView tvSeTemperature = (TextView) sensorTemperatureHumidity
                    .findViewById(R.id.tv_se_temperature);
            TextView tvSeHumidity = (TextView) sensorTemperatureHumidity
                    .findViewById(R.id.tv_se_humidity);

            tvSeTemphumTime.setText(getStrTimes(String.valueOf(gHouseAcerageObj
                    .getUpdate_date())));// 时间
            tvSeTemperature.setText(TAUtils.getFloatValue(gHouseAcerageObj
                    .getAir_temperature()));// 温度
            tvSeHumidity.setText(TAUtils.getFloatValue(gHouseAcerageObj
                    .getAir_humidity()));// 湿度
            greenhousesEnvironmentAverage.addView(sensorTemperatureHumidity);
        }
        if (gHouseAcerageObj.getSoil_humidity() != null
                || gHouseAcerageObj.getSoil_temperature() != null) {// 土壤
            View sensorSoilTemperatureHumidity_lay = inflater.inflate(
                    R.layout.sensor_soil_temperature_humidity_lay, null);
            TextView tvSeSoilTemphumTime = (TextView) sensorSoilTemperatureHumidity_lay
                    .findViewById(R.id.tv_se_soil_temphum_time);
            TextView tvSeSoilTemperature = (TextView) sensorSoilTemperatureHumidity_lay
                    .findViewById(R.id.tv_se_soil_temperature);
            TextView tvSeSoilHumidity = (TextView) sensorSoilTemperatureHumidity_lay
                    .findViewById(R.id.tv_se_soil_humidity);

            tvSeSoilTemphumTime.setText(
                    getStrTimes(String.valueOf(gHouseAcerageObj
                            .getUpdate_date())));// 时间
            tvSeSoilTemperature.setText(TAUtils.getFloatValue(gHouseAcerageObj
                    .getSoil_temperature()));// 温度
            tvSeSoilHumidity.setText(TAUtils.getFloatValue(gHouseAcerageObj
                    .getSoil_humidity()));// 湿度
            greenhousesEnvironmentAverage
                    .addView(sensorSoilTemperatureHumidity_lay);
        }

        if (gHouseAcerageObj.getCo2_ppm() != null) {// 二氧化碳
            View sensorCo2_lay = inflater
                    .inflate(R.layout.sensor_co2_lay, null);
            TextView tvSeCoTime = (TextView) sensorCo2_lay
                    .findViewById(R.id.tv_se_co2_time);
            TextView tvSeCo = (TextView) sensorCo2_lay
                    .findViewById(R.id.tv_se_co2);
            tvSeCoTime.setText(getStrTimes(String.valueOf(gHouseAcerageObj
                    .getUpdate_date())));// 时间
            tvSeCo.setText(Double.valueOf(String.valueOf(gHouseAcerageObj.getCo2_ppm())).intValue() + "");
            greenhousesEnvironmentAverage.addView(sensorCo2_lay);
        }
        if (gHouseAcerageObj.getLux() != null) {// 光照
            View sensorLux_lay = inflater
                    .inflate(R.layout.sensor_lux_lay, null);
            TextView tvSeLuxTime = (TextView) sensorLux_lay
                    .findViewById(R.id.tv_se_lux_time);
            TextView tvSeLux = (TextView) sensorLux_lay
                    .findViewById(R.id.tv_se_lux);
            tvSeLuxTime.setText(getStrTimes(String.valueOf(gHouseAcerageObj
                    .getUpdate_date())));// 时间
            tvSeLux.setText(Double.valueOf(String.valueOf(gHouseAcerageObj.getLux())).intValue() + "");
            greenhousesEnvironmentAverage.addView(sensorLux_lay);
        }
        if (gHouseAcerageObj.getSmoke() != null) {// 烟雾
            View sensorSmoke_lay = inflater.inflate(R.layout.sensor_smoke_lay,
                    null);
            TextView tvSeSmokeTime = (TextView) sensorSmoke_lay
                    .findViewById(R.id.tv_se_smoke_time);
            TextView tvSeSmoke = (TextView) sensorSmoke_lay
                    .findViewById(R.id.tv_se_smoke);

            tvSeSmokeTime.setText(getStrTimes(String.valueOf(gHouseAcerageObj
                    .getUpdate_date())));// 时间
            tvSeSmoke.setText(Double.valueOf(String.valueOf(gHouseAcerageObj.getSmoke())).intValue() + "");
            greenhousesEnvironmentAverage.addView(sensorSmoke_lay);
        }

    }


    /**
     * 时间转换
     *
     * @param cc_time
     * @return
     */
    public static String getStrTimes(String cc_time) {
        String re_StrTime = null;
        // 同理也可以转为其它样式的时间格式.例如："yyyy/MM/dd HH:mm"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        if (cc_time != null) {
            long lcc_time = Long.valueOf(cc_time);
            // re_StrTime = sdf.format(new Date(lcc_time));
            Calendar ca = Calendar.getInstance();
            ca.add(Calendar.HOUR_OF_DAY, 8);
            ca.setTime(new Date(lcc_time));
            re_StrTime = sdf.format(ca.getTime());
        }

        return re_StrTime;
    }

    @Override
    public void onEvent(ToUIEvent toUIEvent) {
        super.onEvent(toUIEvent);
        if (ToUIEvent.UPDATE_MONITORING_INTERFACE == toUIEvent.getWhat()) {
            getGreenHouseList();//大棚列表请求
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
