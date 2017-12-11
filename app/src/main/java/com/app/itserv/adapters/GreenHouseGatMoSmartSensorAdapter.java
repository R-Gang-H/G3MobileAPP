package com.app.itserv.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.itserv.jparser.JsonGreenHouseGatMoSmartSensorObject.ObjBean;
import com.itserv.shed.R;
import com.yycloud.app.utils.TAUtils;

import java.util.List;

public class GreenHouseGatMoSmartSensorAdapter extends BaseAdapter {

    // 传感器设备类型 MO_DEV_SENSOR_TYPE
    public static final int MO_DEV_SENSOR_TYPE_HUMIDTY_TEMPERATURE = 0;// 温湿度传感器
    public static final int MO_DEV_SENSOR_TYPE_CO = 1;// 一氧化碳传感器
    public static final int MO_DEV_SENSOR_TYPE_CO2 = 2;// 二氧化碳传感器
    public static final int MO_DEV_SENSOR_TYPE_SOIL_TH = 3;// 土壤温湿度传感器
    public static final int MO_DEV_SENSOR_TYPE_ILLUMINATION = 4;// 光照传感器
    public static final int MO_DEV_SENSOR_TYPE_SMOKE = 5;// 烟雾传感器

    private Context mContext;

    private List<ObjBean> gHouseSensorList;
    private ObjBean gHouseSensor;
    private ViewHolder vHolder;

    public GreenHouseGatMoSmartSensorAdapter(Context mContext,
                                             List<ObjBean> gHouseSensorList) {
        // TODO Auto-generated constructor stub
        this.mContext = mContext;
        this.gHouseSensorList = gHouseSensorList;
    }
    public void setList( List<ObjBean> list){
        this.gHouseSensorList = list;
    }
    public void notifyDataSetChangeds(){
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return gHouseSensorList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return gHouseSensorList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        // TODO Auto-generated method stub
        ObjBean gHouseSensor = (ObjBean) getItem(position);

        if ("空气温湿度传感器".equals(gHouseSensor.getDeviceType())) {
            return MO_DEV_SENSOR_TYPE_HUMIDTY_TEMPERATURE;
        } else if ("一氧化碳传感器".equals(gHouseSensor.getDeviceType())) {
            return MO_DEV_SENSOR_TYPE_CO;
        } else if ("二氧化碳传感器".equals(gHouseSensor.getDeviceType())) {
            return MO_DEV_SENSOR_TYPE_CO2;
        } else if ("土壤温湿度传感器".equals(gHouseSensor.getDeviceType())) {
            return MO_DEV_SENSOR_TYPE_SOIL_TH;
        } else if ("光照传感器".equals(gHouseSensor.getDeviceType())) {
            return MO_DEV_SENSOR_TYPE_ILLUMINATION;
        } else if ("烟雾传感器".equals(gHouseSensor.getDeviceType())) {
            return MO_DEV_SENSOR_TYPE_SMOKE;
        }
        return position;

    }

    @Override
    public int getViewTypeCount() {
        // TODO Auto-generated method stub
        return 6;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        int type = getItemViewType(position);
        if (convertView == null) {
            vHolder = new ViewHolder();
            switch (type) {
                case MO_DEV_SENSOR_TYPE_HUMIDTY_TEMPERATURE:
                    convertView = View.inflate(mContext,
                            R.layout.sensor_temperature_humidity_lay, null);
                    vHolder.tvSeTemphumName = (TextView) convertView
                            .findViewById(R.id.tv_se_temphum_name);
                    vHolder.tvSeTemphumTime = (TextView) convertView
                            .findViewById(R.id.tv_se_temphum_time);
                    vHolder.tvSeTemphumErr = (TextView) convertView
                            .findViewById(R.id.tv_se_temphum_err);
                    vHolder.tvSeTemperature = (TextView) convertView
                            .findViewById(R.id.tv_se_temperature);
                    vHolder.tvSeHumidity = (TextView) convertView
                            .findViewById(R.id.tv_se_humidity);
                    vHolder.tvSeTemphumStatus = (TextView) convertView
                            .findViewById(R.id.tv_se_temphum_status);
                    vHolder.tvSeTemphumStatusLay = (RelativeLayout) convertView
                            .findViewById(R.id.tv_se_temphum_status_ray);
                    vHolder.tvSeTemphumSnLay = (LinearLayout) convertView
                            .findViewById(R.id.tv_se_temphum_sn_lay);
                    vHolder.tvSeTemphumSn = (TextView) convertView
                            .findViewById(R.id.tv_se_temphum_sn);

                    vHolder.tvSeTemphumSnLay.setVisibility(View.VISIBLE);
                    break;
                case MO_DEV_SENSOR_TYPE_CO:
                    convertView = View.inflate(mContext, R.layout.sensor_co_lay,
                            null);
                    vHolder.tvSeCoName = (TextView) convertView
                            .findViewById(R.id.tv_se_co_name);
                    vHolder.tvSeCoTime = (TextView) convertView
                            .findViewById(R.id.tv_se_co_time);
                    vHolder.tvSeCoErr = (TextView) convertView
                            .findViewById(R.id.tv_se_co_err);
                    vHolder.tvSeCo = (TextView) convertView
                            .findViewById(R.id.tv_se_co);

                    vHolder.tvSeCoStatus = (TextView) convertView
                            .findViewById(R.id.tv_se_co_status);
                    vHolder.tvSeCoStatusLay = (RelativeLayout) convertView
                            .findViewById(R.id.tv_se_co_status_ray);
                    vHolder.tvSeCoSnLay = (LinearLayout) convertView
                            .findViewById(R.id.tv_se_co_sn_lay);
                    vHolder.tvSeCoSn = (TextView) convertView
                            .findViewById(R.id.tv_se_co_sn);
                    vHolder.tvSeCoSnLay.setVisibility(View.VISIBLE);
                    break;
                case MO_DEV_SENSOR_TYPE_CO2:
                    convertView = View.inflate(mContext, R.layout.sensor_co2_lay,
                            null);
                    vHolder.tvSeCo2Name = (TextView) convertView
                            .findViewById(R.id.tv_se_co2_name);
                    vHolder.tvSeCo2Time = (TextView) convertView
                            .findViewById(R.id.tv_se_co2_time);
                    vHolder.tvSeCo2Err = (TextView) convertView
                            .findViewById(R.id.tv_se_co2_err);
                    vHolder.tvSeCo2 = (TextView) convertView
                            .findViewById(R.id.tv_se_co2);

                    vHolder.tvSeCo2Status = (TextView) convertView
                            .findViewById(R.id.tv_se_co2_status);
                    vHolder.tvSeCo2StatusLay = (RelativeLayout) convertView
                            .findViewById(R.id.tv_se_co2_status_ray);
                    vHolder.tvSeCo2SnLay = (LinearLayout) convertView
                            .findViewById(R.id.tv_se_co2_sn_lay);
                    vHolder.tvSeCo2Sn = (TextView) convertView
                            .findViewById(R.id.tv_se_co2_sn);
                    vHolder.tvSeCo2SnLay.setVisibility(View.VISIBLE);
                    break;
                case MO_DEV_SENSOR_TYPE_SOIL_TH:
                    convertView = View.inflate(mContext,
                            R.layout.sensor_soil_temperature_humidity_lay, null);
                    vHolder.tvSeSoilTemphumName = (TextView) convertView
                            .findViewById(R.id.tv_se_soil_temphum_name);
                    vHolder.tvSeSoilTemphumTime = (TextView) convertView
                            .findViewById(R.id.tv_se_soil_temphum_time);
                    vHolder.tvSeSoilTemphumErr = (TextView) convertView
                            .findViewById(R.id.tv_se_soil_temphum_err);
                    vHolder.tvSeSoilTemperature = (TextView) convertView
                            .findViewById(R.id.tv_se_soil_temperature);
                    vHolder.tvSeSoilHumidity = (TextView) convertView
                            .findViewById(R.id.tv_se_soil_humidity);

                    vHolder.tvSeSoilTemphumStatus = (TextView) convertView
                            .findViewById(R.id.tv_se_soil_temphum_status);
                    vHolder.tvSeSoilTemphumStatusLay = (RelativeLayout) convertView
                            .findViewById(R.id.tv_se_soil_temphum_status_ray);
                    vHolder.tvSeSoilTemphumSnLay = (LinearLayout) convertView
                            .findViewById(R.id.tv_se_soil_temphum_sn_lay);
                    vHolder.tvSeSoilTemphumSn = (TextView) convertView
                            .findViewById(R.id.tv_se_soil_temphum_sn);
                    vHolder.tvSeSoilTemphumSnLay.setVisibility(View.VISIBLE);

                    break;
                case MO_DEV_SENSOR_TYPE_ILLUMINATION:
                    convertView = View.inflate(mContext, R.layout.sensor_lux_lay,
                            null);
                    vHolder.tvSeLuxName = (TextView) convertView
                            .findViewById(R.id.tv_se_lux_name);
                    vHolder.tvSeLuxTime = (TextView) convertView
                            .findViewById(R.id.tv_se_lux_time);
                    vHolder.tvSeLuxErr = (TextView) convertView
                            .findViewById(R.id.tv_se_lux_err);
                    vHolder.tvSeLux = (TextView) convertView
                            .findViewById(R.id.tv_se_lux);

                    vHolder.tvSeLuxStatus = (TextView) convertView
                            .findViewById(R.id.tv_se_lux_status);
                    vHolder.tvSeLuxStatusLay = (RelativeLayout) convertView
                            .findViewById(R.id.tv_se_lux_status_ray);
                    vHolder.tvSeLuxSnLay = (LinearLayout) convertView
                            .findViewById(R.id.tv_se_lux_sn_lay);
                    vHolder.tvSeLuxSn = (TextView) convertView
                            .findViewById(R.id.tv_se_lux_sn);

                    vHolder.tvSeLuxSnLay.setVisibility(View.VISIBLE);
                    break;
                case MO_DEV_SENSOR_TYPE_SMOKE:
                    convertView = View.inflate(mContext, R.layout.sensor_smoke_lay,
                            null);
                    vHolder.tvSeSmokeName = (TextView) convertView
                            .findViewById(R.id.tv_se_smoke_name);
                    vHolder.tvSeSmokeTime = (TextView) convertView
                            .findViewById(R.id.tv_se_smoke_time);
                    vHolder.tvSeSmokeErr = (TextView) convertView
                            .findViewById(R.id.tv_se_smoke_err);
                    vHolder.tvSeSmoke = (TextView) convertView
                            .findViewById(R.id.tv_se_smoke);

                    vHolder.tvSeSmokeStatus = (TextView) convertView
                            .findViewById(R.id.tv_se_smoke_status);
                    vHolder.tvSeSmokeStatusLay = (RelativeLayout) convertView
                            .findViewById(R.id.tv_se_smoke_status_ray);
                    vHolder.tvSeSmokeSnLay = (LinearLayout) convertView
                            .findViewById(R.id.tv_se_smoke_sn_lay);
                    vHolder.tvSeSmokeSn = (TextView) convertView
                            .findViewById(R.id.tv_se_smoke_sn);

                    vHolder.tvSeSmokeSnLay.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }
        gHouseSensor = (ObjBean) getItem(position);

        switch (type) {
            case MO_DEV_SENSOR_TYPE_HUMIDTY_TEMPERATURE:

                vHolder.tvSeTemphumName.setText(TextUtils.isEmpty(gHouseSensor
                        .getDeviceName()) ? gHouseSensor.getDeviceType()
                        : gHouseSensor.getDeviceName());
                vHolder.tvSeTemphumTime.setText(TextUtils.isEmpty(gHouseSensor
                        .getUpdateDate()) ? "" : gHouseSensor.getUpdateDate());
                vHolder.tvSeTemphumErr.setText(gHouseSensor.getMessage());
                vHolder.tvSeTemperature.setText( TAUtils.getFloatValue(TextUtils.isEmpty(gHouseSensor
                        .getTemperature()) ? "" : gHouseSensor.getTemperature()));

                vHolder.tvSeHumidity.setText( TAUtils.getFloatValue(TextUtils.isEmpty(gHouseSensor
                        .getHumidity()) ? "" : gHouseSensor.getHumidity()));

                vHolder.tvSeTemphumSn.setText("sn: " + (TextUtils.isEmpty(gHouseSensor
                        .getSn()) ? "" : gHouseSensor.getSn()));

                showStatus(vHolder.tvSeTemphumStatus, vHolder.tvSeTemphumStatusLay,
                        gHouseSensor);

                break;
            case MO_DEV_SENSOR_TYPE_CO:
                vHolder.tvSeCoName.setText(TextUtils.isEmpty(gHouseSensor
                        .getDeviceName()) ? gHouseSensor.getDeviceType()
                        : gHouseSensor.getDeviceName());

                vHolder.tvSeCoTime.setText(TextUtils.isEmpty(gHouseSensor
                        .getUpdateDate()) ? "" : String.valueOf(gHouseSensor
                        .getUpdateDate()));

                vHolder.tvSeCo.setText(TextUtils.isEmpty(gHouseSensor
                        .getCurrentData()) ? "" : gHouseSensor.getCurrentData());

                vHolder.tvSeCoSn
                        .setText("sn: " + (TextUtils.isEmpty(gHouseSensor.getSn()) ? ""
                                : gHouseSensor.getSn()));

                showStatus(vHolder.tvSeCoStatus, vHolder.tvSeCoStatusLay,
                        gHouseSensor);
                break;
            case MO_DEV_SENSOR_TYPE_CO2:
                vHolder.tvSeCo2Name.setText(TextUtils.isEmpty(gHouseSensor
                        .getDeviceName()) ? gHouseSensor.getDeviceType()
                        : gHouseSensor.getDeviceName());

                vHolder.tvSeCo2Time.setText(TextUtils.isEmpty(gHouseSensor
                        .getUpdateDate()) ? "" : gHouseSensor.getUpdateDate());

                vHolder.tvSeCo2.setText(TextUtils.isEmpty(gHouseSensor
                        .getCurrentData()) ? "" : gHouseSensor.getCurrentData());

                vHolder.tvSeCo2Sn
                        .setText("sn: " + (TextUtils.isEmpty(gHouseSensor.getSn()) ? ""
                                : gHouseSensor.getSn()));

                showStatus(vHolder.tvSeCo2Status, vHolder.tvSeCo2StatusLay,
                        gHouseSensor);
                break;
            case MO_DEV_SENSOR_TYPE_SOIL_TH:
                vHolder.tvSeSoilTemphumName.setText(TextUtils.isEmpty(gHouseSensor
                        .getDeviceName()) ? gHouseSensor.getDeviceType()
                        : gHouseSensor.getDeviceName());

                vHolder.tvSeSoilTemphumTime.setText( TextUtils.isEmpty(gHouseSensor
                        .getUpdateDate()) ? "" : gHouseSensor.getUpdateDate());
                vHolder.tvSeSoilTemperature.setText(TAUtils.getFloatValue(TextUtils.isEmpty(gHouseSensor
                        .getTemperature()) ? "" : gHouseSensor.getTemperature()));

                vHolder.tvSeSoilHumidity.setText( TAUtils.getFloatValue(TextUtils.isEmpty(gHouseSensor
                        .getHumidity()) ? "" : gHouseSensor.getHumidity()));

                vHolder.tvSeSoilTemphumSn.setText("sn: " + (TextUtils.isEmpty(gHouseSensor
                        .getSn()) ? "" : gHouseSensor.getSn()));


                showStatus(vHolder.tvSeSoilTemphumStatus,
                        vHolder.tvSeSoilTemphumStatusLay, gHouseSensor);
                break;
            case MO_DEV_SENSOR_TYPE_ILLUMINATION:
                vHolder.tvSeLuxName.setText(TextUtils.isEmpty(gHouseSensor
                        .getDeviceName()) ? gHouseSensor.getDeviceType()
                        : gHouseSensor.getDeviceName());

                vHolder.tvSeLuxTime.setText(TextUtils.isEmpty(gHouseSensor
                        .getUpdateDate()) ? "" : gHouseSensor.getUpdateDate());

                vHolder.tvSeLux.setText(TextUtils.isEmpty(gHouseSensor
                        .getCurrentData()) ? "" : gHouseSensor.getCurrentData());

                vHolder.tvSeLuxSn
                        .setText("sn: " + (TextUtils.isEmpty(gHouseSensor.getSn()) ? ""
                                : gHouseSensor.getSn()));

                showStatus(vHolder.tvSeLuxStatus, vHolder.tvSeLuxStatusLay,
                        gHouseSensor);
                break;
            case MO_DEV_SENSOR_TYPE_SMOKE:
                vHolder.tvSeSmokeName.setText(TextUtils.isEmpty(gHouseSensor
                        .getDeviceName()) ? gHouseSensor.getDeviceType()
                        : gHouseSensor.getDeviceName());

                vHolder.tvSeSmokeTime.setText(TextUtils.isEmpty(gHouseSensor
                        .getUpdateDate()) ? "" : gHouseSensor.getUpdateDate());

                vHolder.tvSeSmoke.setText(TextUtils.isEmpty(gHouseSensor
                        .getCurrentData()) ? "" : gHouseSensor.getCurrentData());

                vHolder.tvSeSmokeSn
                        .setText("sn: " + (TextUtils.isEmpty(gHouseSensor.getSn()) ? ""
                                : gHouseSensor.getSn()));

                showStatus(vHolder.tvSeSmokeStatus, vHolder.tvSeSmokeStatusLay,
                        gHouseSensor);
                break;
            default:
                break;
        }
        return convertView;
    }

    private void showStatus(TextView tvSeStatus,
                            RelativeLayout tvSeStatusLay, ObjBean gHouseSensor) {
        // TODO Auto-generated method stub
        tvSeStatus.setVisibility(View.VISIBLE);
        String status = gHouseSensor.getStatus();

        if (status == null || "".equals(status) || "offline".equals(status)) {
            tvSeStatus.setText("状态: 离线");
            tvSeStatusLay.setBackgroundResource(R.drawable.monitor_err_bg);
        } else {
            tvSeStatus.setText("状态: 在线");
            tvSeStatusLay.setBackgroundResource(R.drawable.monitor_bg);
        }


    }

    static final class ViewHolder {
        private TextView tvSeTemphumName, tvSeTemphumTime, tvSeTemphumErr,
                tvSeTemphumStatus, tvSeTemphumSn, tvSeTemperature,
                tvSeSoilTemphumStatus, tvSeSoilTemphumSn, tvSeCoStatus,
                tvSeCoSn, tvSeCo2Status, tvSeCo2Sn, tvSeHumidity, tvSeCoName,
                tvSeLuxStatus, tvSeLuxSn, tvSeCoTime, tvSeCoErr, tvSeCo,
                tvSeSmokeStatus, tvSeSmokeSn, tvSeCo2Name, tvSeCo2Time,
                tvSeCo2Err, tvSeCo2, tvSeSoilTemphumName, tvSeSoilTemphumTime,
                tvSeSoilTemphumErr, tvSeSoilTemperature, tvSeSoilHumidity,
                tvSeLuxName, tvSeLuxTime, tvSeLuxErr, tvSeLux, tvSeSmokeName,
                tvSeSmokeTime, tvSeSmokeErr, tvSeSmoke;

        private LinearLayout tvSeTemphumSnLay, tvSeCoSnLay, tvSeCo2SnLay,
                tvSeSmokeSnLay, tvSeLuxSnLay, tvSeSoilTemphumSnLay;
        private RelativeLayout tvSeTemphumStatusLay, tvSeCoStatusLay,
                tvSeLuxStatusLay, tvSeSoilTemphumStatusLay, tvSeCo2StatusLay,
                tvSeSmokeStatusLay;

    }
}
