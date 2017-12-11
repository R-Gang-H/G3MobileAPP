package com.app.itserv.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.app.itserv.jparser.JsonGreenhouseAcerageCodeObject;
import com.app.itserv.views.LoadingFrameView;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.core.beans.ChartDatas;
import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 环境曲线
 * 2017年9月14日09:24:29
 *
 * @author Administrator
 */
public class EnvironmentalCurveActivity extends Activity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private static final String TAG = "EnvironmentalCurveActivity";
    private Context mContext;
    String devtype = null;
    static int width, height;
    // 填充器
    private LinearLayout sensorDevlay;
    private RadioButton rbToDay, rbThisWeek, rbThisMonth, rbScope0, rbScope1,
            rbScope2;//今天，本周，本月，小时，20分钟，5分钟
    private TextView tvEnvironName;//标题
    private ImageView imgBack;
    private LoadingFrameView ldCurveView;
    private String sgateSN, DevName, scope, updateDateBegin,
            updateDateEnd, devSN, devType;

    // 范围0-100
    private List<ChartDatas> mChartDatas = new ArrayList<ChartDatas>();
    private static Lock CHARTDATAS_LOCK = new ReentrantLock();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.environ_curve);
        mContext = this;
        initView();
        init();
    }

    private void init() {
        Intent intent = getIntent();
        devSN = (String) intent.getExtras().get("devSN");
        devType = (String) intent.getExtras().get("devType");
        sgateSN = (String) intent.getExtras().get("sgateSN");
        DevName = (String) intent.getExtras().get("DevName");

        if (scope == null) {
            scope = "scope2";
        }



        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date time = null;
        try {
            time = sdf.parse(sdf.format(new Date()));
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        Date nowDate = new Date();
        String startDate = TAUtils.getDateToString("yyyy-MM-dd HH:mm:ss", time);
        String endDate = TAUtils
                .getDateToString("yyyy-MM-dd HH:mm:ss", nowDate);
        String date = "[" + startDate.replace(" ", "%20") + ","
                + endDate.replace(" ", "%20") + "]";

        if (updateDateBegin == null) {
            updateDateBegin = startDate;//date+" 00:00:00";
        }
        if (updateDateEnd == null) {
            updateDateEnd = endDate;//date+" 23:59:59";
        }

        if (devType != null && devSN != null && DevName != null) {// 传感器
            if ("空气温湿度传感器".equals(devType)) {
                devtype = "humidity-temperature";
            } else if ("土壤温湿度传感器".equals(devType)) {
                devtype = "soil-th";
            } else if ("二氧化碳传感器".equals(devType)) {
                devtype = "co2";
            } else if ("光照传感器".equals(devType)) {
                devtype = "illumination";
            } else if ("烟雾传感器".equals(devType)) {
                devtype = "smoke";
            } /*
             * else if ("一氧化碳传感器".equals(devType)) { devtype = ""; }
			 */
            tvEnvironName.setText(DevName + " 环境曲线");
            getGreenHouseAverageSensor(devtype, devSN, scope,
                    updateDateBegin, updateDateEnd);
        } else if (sgateSN != null && DevName != null) {// 环境曲线平均值
            tvEnvironName.setText(DevName + " 环境曲线");
            getGreenHouseAveragSensor(sgateSN, scope, updateDateBegin,
                    updateDateEnd);
        }
    }

    /**
     * 初始化View
     */
    private void initView() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        width = dm.widthPixels;
        height = dm.heightPixels;
        Log.i("系统信息", "该设备的分辨是：" + width + "*" + height);
        ldCurveView = (LoadingFrameView) findViewById(R.id.ld__curve_view);
        sensorDevlay = (LinearLayout) findViewById(R.id.sensor_dev_lay);//填充器
        rbToDay = (RadioButton) findViewById(R.id.rb_today);//今天
        rbToDay.setOnCheckedChangeListener(this);//今日选中事件
        rbThisWeek = (RadioButton) findViewById(R.id.rb_this_week);//本周
        rbThisWeek.setOnCheckedChangeListener(this);//本周选中事件
        rbThisMonth = (RadioButton) findViewById(R.id.rb_this_month);//本月
        rbThisMonth.setOnCheckedChangeListener(this);//本月选中事件
        rbScope2 = (RadioButton) findViewById(R.id.rb_scope2);//小时
        rbScope2.setOnCheckedChangeListener(this);//小时选中事件
        rbScope1 = (RadioButton) findViewById(R.id.rb_scope1);//20分钟
        rbScope1.setOnCheckedChangeListener(this);//20分钟选中事件
        rbScope0 = (RadioButton) findViewById(R.id.rb_scope0);//5分钟
        rbScope0.setOnCheckedChangeListener(this);//5分钟选中事件
        tvEnvironName = (TextView) findViewById(R.id.tv_environ_name);//动态显示折线图标题
        imgBack = (ImageView) findViewById(R.id.img_back);// 退出
        imgBack.setOnClickListener(this);//退出点击事件
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked == false)
            return;
        switch (buttonView.getId()) {
            case R.id.rb_today://今天
                updateDateBegin = "";
                updateDateEnd = "";
                break;
            case R.id.rb_this_week://本周
                updateDateBegin = "";
                updateDateEnd = "";
                break;
            case R.id.rb_this_month://本月
                updateDateBegin = "";
                updateDateEnd = "";
                break;
            case R.id.rb_scope0://5
                scope = "scope0";
                ldCurveView.setProgressShown(true);
                if (devtype != null && devSN != null && DevName != null) {// 传感器
                    getGreenHouseAverageSensor(devtype, devSN, scope,
                            updateDateBegin, updateDateEnd);
                } else if (sgateSN != null && DevName != null) {// 平均值
                    getGreenHouseAveragSensor(sgateSN, scope, updateDateBegin,
                            updateDateEnd);
                }
                break;
            case R.id.rb_scope1://20
                scope = "scope1";
                ldCurveView.setProgressShown(true);
                if (devtype != null && devSN != null && DevName != null) {// 传感器

                    getGreenHouseAverageSensor(devtype, devSN, scope,
                            updateDateBegin, updateDateEnd);
                } else if (sgateSN != null && DevName != null) {// 平均值
                    getGreenHouseAveragSensor(sgateSN, scope, updateDateBegin,
                            updateDateEnd);
                }
                break;
            case R.id.rb_scope2://小时
                scope = "scope2";
                ldCurveView.setProgressShown(true);
                if (devtype != null && devSN != null && DevName != null) {// 传感器
                    getGreenHouseAverageSensor(devtype, devSN, scope,
                            updateDateBegin, updateDateEnd);
                } else if (sgateSN != null && DevName != null) {// 平均值
                    getGreenHouseAveragSensor(sgateSN, scope, updateDateBegin,
                            updateDateEnd);
                }
                break;
            default:
                break;
        }
    }

    /*-------------------------------大棚环境平均值数据Start-----------------------------------------*/

    /**
     * @author jcy
     * @Package com.app.itserv.activity
     * @project yyshed
     * @Description: TODO(大棚环境平均值折线图请求)
     * @date 2017-9-20 20:47:27
     */

    private void getGreenHouseAveragSensor(final String sgateSN, final String scope,
                                           final String updateDate_begin, final String updateDate_end) {
        String token = PreferencesUtils.getString(mContext, "token");// 用户token
        String currTenantId = PreferencesUtils.getString(mContext, "tenantId");// 商户id
        HttpManager.getInstance().gHouseAveragSensor(TAG, token, currTenantId, sgateSN, scope, updateDate_begin, updateDate_end, new HttpCallBack<JsonGreenhouseAcerageCodeObject>() {
            @Override
            public void onSuccess(JsonGreenhouseAcerageCodeObject date) {
                if (date != null) {
                    String message = date.getMsg();// 提示消息
                    Log.v("Request", "Request :" + message);
                    mChartDatas.clear();
                    if (date.isSuccess()) {
                        if (date.getObj().size() <= 0 || date.getObj() == null) {
                            ldCurveView.setNoShown(true);
                            return;
                        }
                        parseData(date);
                        initTemperatureChart();
                    } else {  // 失败
                        if (!TextUtils.isEmpty(message)) {
                            ToastUtils.makeTextShort(message);
                        } else {
                            ToastUtils.makeTextShort("网络不可用!");
                        }
                        ldCurveView.setNoShown(true);
                    }
                }else {
                    ldCurveView.setNoShown(true);
                }
            }

            @Override
            public void onError(Throwable throwable) {
                ldCurveView.setErrorShown(true, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ldCurveView.setProgressShown(true);
                        getGreenHouseAveragSensor(sgateSN, scope, updateDate_begin, updateDate_end);
                    }
                });
                ToastUtils.makeTextShort("网络不可用!");
            }
        });
    }

    /**
     * @param @param msg
     * @Description:解析环境平均值
     * @exception:
     * @author: jcy
     * @time:2016-5-5 下午1:37:53
     */
    private void parseData(JsonGreenhouseAcerageCodeObject gHouse_manager) {

        ArrayList<String> hours = new ArrayList<String>();
        // 空气湿度图表数据
        ArrayList<Double> humidityDatas = new ArrayList<Double>();// 空气
        ChartDatas humidityChartDatas = new ChartDatas();
        // 空气温度图表数据
        ChartDatas temperatureChartDatas = new ChartDatas();// 空气
        ArrayList<Double> temperatureDatas = new ArrayList<Double>();
        //土壤湿度图表数据
        ChartDatas soil_humidityChartDatas = new ChartDatas();// 土壤
        ArrayList<Double> soil_humidityDatas = new ArrayList<Double>();
        //土壤温度图表数据
        ChartDatas soil_temperatureChartDatas = new ChartDatas();// 土壤
        ArrayList<Double> soil_temperatureDatas = new ArrayList<Double>();
        //二氧化碳图表数据
        ChartDatas co2PpmChartDatas = new ChartDatas();// 二氧化碳
        ArrayList<Double> co2PpmDatas = new ArrayList<Double>();
        //光照图表数据
        ChartDatas luxChartDatas = new ChartDatas();// 光照
        ArrayList<Double> luxDatas = new ArrayList<Double>();
        // 烟雾图标绘制
        ChartDatas smokeChartDatas = new ChartDatas();// 烟雾
        ArrayList<Double> smokeDatas = new ArrayList<Double>();
        int count = 0;
        for (int i = gHouse_manager.getObj().size() - 1; i >= 0; i--) {
            JsonGreenhouseAcerageCodeObject.ObjBean houseAcerage = gHouse_manager.getObj().get(i);
            if (count == 75) {
                break;
            }
            Double f = 0.0;
            hours.add(formatTimes(houseAcerage.getUpdate_date()));
            if (houseAcerage.getAir_temperature() != null) {//空气温度
                temperatureChartDatas.setType("air_temperature");
                temperatureDatas.add(TAUtils.parseStrToDouble(TAUtils
                        .getFloatValue(houseAcerage.getAir_temperature()), f));
            }
            if (houseAcerage.getAir_humidity() != null) {// 空气湿度

                humidityChartDatas.setType("air_humidity");
                humidityDatas.add(TAUtils.parseStrToDouble(
                        TAUtils.getFloatValue(houseAcerage.getAir_humidity()),
                        f));

            }
            if (houseAcerage.getSoil_temperature() != null) {//土壤温度
                soil_temperatureChartDatas.setType("soil_temperature");
                soil_temperatureDatas.add(TAUtils.parseStrToDouble(TAUtils
                        .getFloatValue(houseAcerage.getSoil_temperature()), f));
            }
            if (houseAcerage.getSoil_humidity() != null) {// 土壤湿度
                soil_humidityChartDatas.setType("soil_humidity");
                soil_humidityDatas.add(TAUtils.parseStrToDouble(
                        TAUtils.getFloatValue(houseAcerage.getSoil_humidity()),
                        f));
            }

            if (houseAcerage.getCo2_ppm() != null) {// 二氧化碳
                co2PpmChartDatas.setType("co2");
                co2PpmDatas.add(TAUtils.parseStrToDouble(
                        TAUtils.getFloatValue(houseAcerage.getCo2_ppm()), f));
            }
            if (houseAcerage.getLux() != null) {// 光照
                luxChartDatas.setType("lux");
                luxDatas.add(TAUtils.parseStrToDouble(
                        TAUtils.getFloatValue(houseAcerage.getLux()), f));
            }

            if (houseAcerage.getSmoke() != null) {// 烟雾
                smokeChartDatas.setType("smoke");
                smokeDatas.add(TAUtils.parseStrToDouble(
                        TAUtils.getFloatValue(houseAcerage.getSmoke()), f));
            }

            count++;

        }
        Collections.reverse(hours);
        //空气湿度绘制
        Collections.reverse(humidityDatas);
        humidityChartDatas.setDatas(humidityDatas);
        humidityChartDatas.setHours(hours);
        //空气温度绘制
        Collections.reverse(temperatureDatas);
        temperatureChartDatas.setDatas(temperatureDatas);
        temperatureChartDatas.setHours(hours);
        //土壤湿度绘制
        Collections.reverse(soil_humidityDatas);
        soil_humidityChartDatas.setDatas(soil_humidityDatas);
        soil_humidityChartDatas.setHours(hours);
        //土壤温度绘制
        Collections.reverse(soil_temperatureDatas);
        soil_temperatureChartDatas.setDatas(soil_temperatureDatas);
        soil_temperatureChartDatas.setHours(hours);
        //二氧化碳绘制
        Collections.reverse(co2PpmDatas);
        co2PpmChartDatas.setDatas(co2PpmDatas);
        co2PpmChartDatas.setHours(hours);
        //光照绘制
        Collections.reverse(luxDatas);
        luxChartDatas.setDatas(luxDatas);
        luxChartDatas.setHours(hours);
        // 烟雾绘制
        Collections.reverse(smokeDatas);
        smokeChartDatas.setDatas(smokeDatas);
        smokeChartDatas.setHours(hours);

        mChartDatas.add(temperatureChartDatas);

        mChartDatas.add(humidityChartDatas);

        mChartDatas.add(soil_temperatureChartDatas);

        mChartDatas.add(soil_humidityChartDatas);

        mChartDatas.add(co2PpmChartDatas);

        mChartDatas.add(luxChartDatas);

        mChartDatas.add(smokeChartDatas);

    }

    /**
     * 绘制折线图
     */
    protected void initTemperatureChart() {
        sensorDevlay.removeAllViews();
        int color = mContext.getResources().getColor(R.color.charengine_line_color);
        // TODO Auto-generated method stub
        for (ChartDatas chartData : mChartDatas) {
            String type = chartData.getType();
            if ("air_temperature".equals(type)) {//类型是空气温度
                LinearLayout chart = new LinearLayout(mContext);// 左右区域，左图标，右边区域为值
                chart.setPadding(0, TAUtils.dip2px(mContext, 10), 0,
                        TAUtils.dip2px(mContext, 10));
                chart.setOrientation(LinearLayout.VERTICAL);
                LinearLayout.LayoutParams chartlp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT, TAUtils.dip2px(
                        mContext, 200));
                int yLow = -10;
                Double max = Collections.max(chartData.getDatas());
                int yMax = max.intValue() +20;
                color = mContext.getResources().getColor(R.color.red);
                TextView textView = new TextView(mContext);
                textView.setText("空气温度平均值");
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(15);
                textView.setBackgroundColor(Color.parseColor("#146414"));
                chart.addView(textView);
                chart.addView(lineView(yLow, yMax, color, 6, "℃ 空气温度",
                        chartData.getDatas(), chartData.getHours(), 40, 5,
                        sgateSN));
                sensorDevlay.addView(chart, chartlp);

            } else if ("air_humidity".equals(type)) {
                LinearLayout chart = new LinearLayout(mContext);// 左右区域，左图标，右边区域为值
                chart.setOrientation(LinearLayout.VERTICAL);
                chart.setPadding(0, TAUtils.dip2px(mContext, 10), 0,
                        TAUtils.dip2px(mContext, 10));
                LinearLayout.LayoutParams chartlp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT, TAUtils.dip2px(
                        mContext, 200));
                int yLow = 0;
                Double max = Collections.max(chartData.getDatas());
                int yMax = max.intValue() +20;
                color = mContext.getResources().getColor(R.color.blue);
                TextView textView = new TextView(mContext);
                textView.setText("空气湿度平均值");
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(15);
                textView.setBackgroundColor(Color.parseColor("#146414"));
                chart.addView(textView);
                chart.addView(lineView(yLow, yMax, color, 5, "%RH 空气湿度",
                        chartData.getDatas(), chartData.getHours(), 95, 20,
                        sgateSN));
                sensorDevlay.addView(chart, chartlp);

            } else if ("soil_temperature".equals(type)) {
                // 土壤温度
                LinearLayout chart = new LinearLayout(mContext);
                chart.setOrientation(LinearLayout.VERTICAL);
                chart.setPadding(0, TAUtils.dip2px(mContext, 10), 0,
                        TAUtils.dip2px(mContext, 10));
                LinearLayout.LayoutParams chartlp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT, TAUtils.dip2px(
                        mContext, 200));
                int yLow = -10;
                Double max = Collections.max(chartData.getDatas());
                int yMax = max.intValue() +20;
                color = mContext.getResources().getColor(R.color.red);
                TextView textView = new TextView(mContext);
                textView.setText("土壤温度平均值");
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(15);
                textView.setBackgroundColor(Color.parseColor("#146414"));
                chart.addView(textView);
                chart.addView(lineView(yLow, yMax, color, 6, "℃ 土壤温度",
                        chartData.getDatas(), chartData.getHours(), 40, 5,
                        sgateSN));
                sensorDevlay.addView(chart, chartlp);

            } else if ("soil_humidity".equals(type)) {
                LinearLayout chart = new LinearLayout(mContext);
                chart.setPadding(0, TAUtils.dip2px(mContext, 10), 0,
                        TAUtils.dip2px(mContext, 10));
                chart.setOrientation(LinearLayout.VERTICAL);
                LinearLayout.LayoutParams chartlp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT, TAUtils.dip2px(
                        mContext, 200));
                int yLow = 0;
                Double max = Collections.max(chartData.getDatas());
                int yMax = max.intValue() +1;
                color = mContext.getResources().getColor(R.color.blue);
                TextView textView = new TextView(mContext);
                textView.setText("土壤湿度平均值");
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(15);
                textView.setBackgroundColor(Color.parseColor("#146414"));
                chart.addView(textView);
                chart.addView(lineView(yLow, yMax, color, 5, "%RH 土壤湿度",
                        chartData.getDatas(), chartData.getHours(), 95, 20,
                        sgateSN));
                sensorDevlay.addView(chart, chartlp);

            } else if ("lux".equals(type)) {
                LinearLayout chart = new LinearLayout(mContext);// 左右区域，左图标，右边区域为值
                chart.setOrientation(LinearLayout.VERTICAL);
                chart.setPadding(0, TAUtils.dip2px(mContext, 10), 0,
                        TAUtils.dip2px(mContext, 10));
                LinearLayout.LayoutParams chartlp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT, TAUtils.dip2px(
                        mContext, 200));
                int yLow = 0;
                Double max = Collections.max(chartData.getDatas());
                int yMax = max.intValue() +1000;
                color = mContext.getResources().getColor(R.color.goldyellow);
                TextView textView = new TextView(mContext);
                textView.setText("光照强度平均值");
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(15);
                textView.setGravity(Gravity.CENTER);
                textView.setBackgroundColor(Color.parseColor("#146414"));
                chart.addView(textView);
                chart.addView(lineView(yLow, yMax, color, 8, "lux 光照强度",
                        chartData.getDatas(), chartData.getHours(), 100000,
                        100, sgateSN));
                sensorDevlay.addView(chart, chartlp);

            } else if ("smoke".equals(type)) {
                LinearLayout chart = new LinearLayout(mContext);// 左右区域，左图标，右边区域为值
                chart.setOrientation(LinearLayout.VERTICAL);
                chart.setPadding(0, TAUtils.dip2px(mContext, 10), 0,
                        TAUtils.dip2px(mContext, 10));
                LinearLayout.LayoutParams chartlp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT, TAUtils.dip2px(
                        mContext, 200));
                int yLow = 0;
                Double max = Collections.max(chartData.getDatas());
                int yMax = max.intValue()+1000;
                color = mContext.getResources().getColor(R.color.goldyellow);
                TextView textView = new TextView(mContext);
                textView.setText("烟雾浓度平均值");
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(15);
                textView.setGravity(Gravity.CENTER);
                textView.setBackgroundColor(Color.parseColor("#146414"));
                chart.addView(textView);
                chart.addView(lineView(yLow, yMax, color, 8, "ppm 烟雾浓度",
                        chartData.getDatas(), chartData.getHours(), 100000,
                        100, sgateSN));
                sensorDevlay.addView(chart, chartlp);

            } else if ("co2".equals(type)) {
                LinearLayout chart = new LinearLayout(mContext);// 左右区域，左图标，右边区域为值
                chart.setOrientation(LinearLayout.VERTICAL);
                chart.setPadding(0, TAUtils.dip2px(mContext, 10), 0,
                        TAUtils.dip2px(mContext, 10));
                LinearLayout.LayoutParams chartlp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT, TAUtils.dip2px(
                        mContext, 300));
                int yLow = 0;
                Double max = Collections.max(chartData.getDatas());
                int yMax = max.intValue() +200 ;
                color = mContext.getResources().getColor(R.color.brown);
                TextView textView = new TextView(mContext);
                textView.setText("二氧化碳浓度平均值");
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(15);
                textView.setGravity(Gravity.CENTER);
                textView.setBackgroundColor(Color.parseColor("#146414"));
                chart.addView(textView);
                chart.addView(lineView(yLow, yMax, color, 8, "ppm 二氧化碳浓度",
                        chartData.getDatas(), chartData.getHours(), 1800, 300,
                        sgateSN));
                sensorDevlay.addView(chart, chartlp);
            }
        }
        ldCurveView.delayShowContainer(true);
    }

    /**
     * @param @param  yLow　Y轴最小值  
     * @param @param  yMax　Ｙ轴最大值  
     * @param @param  color　折线颜色  
     * @param @param  yNum　Ｙ轴上点的数量  
     * @param @param  seriesTitle　传感器名称  
     * @param @param  seriesDatas　传感器返回的数据  
     * @param @param  textLabels　传感器获取当前数据的时间
     * @param @param  maxThreshold　阀值线最大值  
     * @param @param  minThreshold　阀值线最小值  
     * @param @return 　折线图 View  
     * @throws
     * @Description:折线图的具体绘制  
     * @author shuy  
     * @time 2017年9月14日14:35:54
     */
    public View lineView(int yLow, int yMax, int color, int yNum,
                         String seriesTitle, List<Double> seriesDatas,
                         List<String> textLabels, int maxThreshold, int minThreshold,
                         String snOnline) {
        /*
         * AChartEngine简介： AChartEngine 是 Android 平台的图表开发库.每个图表都需要一个数据集
		 * (Dataset) 和 渲染器集合 (Renderer); -- 数据集 : 又由许多数据组成, -- 渲染器 :
		 * 也由不同的子渲染器组成, 图表工厂 (ChartFactory) 通过调用 数据集 (Dataset) 和 渲染器集合
		 * (Renderer) 可以生成带图表的 GraphicalView 或者 GraphicalActivity; AChartEngine
		 * 相关类介绍 : -- ChartFactory : 图表生成的工厂类, 通过你传入 数据集 和 渲染器集合, 即可生成
		 * GraphicalView 或者 GraphicalActivity; -- XYMultipleSeriesDataset : 数据集,
		 * 其中可以封装图表所需的数据; -- XYSeries : 属于 图表数据集的一部分, 每个都代表了一个数据集合 例如 折线,
		 * 一个图表中可以有多条折线, 所有的数据放在一起就是 数据集 XYMultipleSeriesDataset ; --
		 * XYMultipleSeriesRenderer : 渲染器集合, 图表中多个曲线的渲染器; -- XYSeriesRenderer :
		 * 单个曲线或单元的渲染器, 一个图表中可能有多条曲线或者柱状图等, 每个都需要一个渲染器, 所有的渲染器放在一起就是渲染器集合
		 * XYMultipleSeriesRenderer;
		 */
        /**
         * 数据集合类XYMultipleSeriesDataset
         */
        XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();

        /**
         * 渲染集合类XYMultipleSeriesRenderer
         */
        XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
        // 设置图表的X轴的当前方向
        mRenderer
                .setOrientation(XYMultipleSeriesRenderer.Orientation.HORIZONTAL);

        mRenderer.setXTitle(null);// 设置为X轴的标题
        mRenderer.setYTitle(null);// 设置y轴的标题
        mRenderer.setAxisTitleTextSize(20);// 设置轴标题文本大小
        mRenderer.setChartTitle(null);// 设置图表标题
        mRenderer.setChartTitleTextSize(30);// 设置图表标题文字的大小
        mRenderer.setLabelsTextSize(16f);// 设置标签的文字大小
        mRenderer.setLegendTextSize(20);// 设置图例文本大小
        mRenderer.setPointSize(5f);// 设置点的大小
        mRenderer.setYAxisMin(yLow);// 设置y轴最小值是35
        mRenderer.setXAxisMin(0.5);// 设置x轴的最小值
        mRenderer.setYAxisMax(yMax);// 设置y轴最大值
        mRenderer.setYLabels(yNum);// 设置Y轴刻度个数（貌似不太准确）
        mRenderer.setXAxisMax(6);// 设置一页显示X轴数量
        // mRenderer.setXAxisMax(4, 1);
        mRenderer.setShowGrid(true);// 显示网格
        mRenderer.setShowGridY(true);// 显示y轴网格
        mRenderer.setShowGridX(true);// 显示x轴网格
        mRenderer.setZoomEnabled(false, false);// 设置放大
        mRenderer.setPanEnabled(true, false);// 设置是否可移动
        // 将x标签栏目显示如：1,2,3,4替换为显示1月，2月，3月，4月
        for (int i = 0; i < textLabels.size(); i++) {
            mRenderer.addXTextLabel(i + 1.0, textLabels.get(i));
        }
        mRenderer.setXLabels(0);// 设置只显示如1月，2月等替换后的东西，不显示1,2,3等
        mRenderer.setMargins(new int[]{20, 30, 15, 20});// 设置视图位置
        mRenderer.setApplyBackgroundColor(true);// 设置是否能改变背景色
        mRenderer.setBackgroundColor(Color.WHITE);// 设置背景色
        mRenderer.setYLabelsAlign(Paint.Align.RIGHT);// 设置Y轴刻度与Y轴之间的相对位置
        mRenderer.setXLabelsAlign(Paint.Align.CENTER);
        mRenderer.setMarginsColor(Color.WHITE);// 设置边缘颜色
        // 设置拖动时X轴Y轴允许的最大值最小值(滑动密度)
        mRenderer.setPanLimits(new double[]{-50, 80, 0, 0});
        // mRenderer.setXLabelsPadding(200);
        // mRenderer.setXLabelsAngle(-60f);//设置X轴标签的倾斜度
        // mRenderer.setZoomEnabled(false);
        mRenderer.setYLabelsPadding(-25);
        mRenderer.setYLabelsColor(0, Color.BLACK);// 设置Y轴标签颜色
        mRenderer.setXLabelsColor(Color.BLACK);// 设置X轴标签颜色

        // 设置初始显示的位置 从最右端开始
        mRenderer.setRange(new double[]{seriesDatas.size() - 5,
                seriesDatas.size() + 1, yLow, yMax});

        // if (isChartView) {
        /**
         * 单个数据类XYSeries
         */
        XYSeries series = new XYSeries(seriesTitle);
        for (int i = 0; i < seriesDatas.size(); i++) {
            double Yvalue = seriesDatas.get(i);
            double OriginalYvalue = seriesDatas.get(i);
            if (i >= 1) {
                // 原始值为0.5或者状态为offline,即离线
                if (OriginalYvalue == 0.5) {
                    // seriesDatas.set(i, seriesDatas.get(i-1));
                    // 测试
                    Yvalue = 0.0;
                    seriesDatas.set(i, 0.0);
                    // Log.v("Yvalue-0.0", String.valueOf(i));
                    // Log.v("Yvalue-1", String.valueOf(seriesDatas.get(i -
                    // 1)));
                    series.addAnnotation("离线", i + 1.0, Yvalue);
                }
            }
            series.add(i + 1.0, Yvalue);
            // Log.v("Yvalue", String.valueOf(Yvalue));
        }

        mDataset.addSeries(series);
        // Log.v("series", "seriesDatas大小：" + seriesDatas.size());
        /**
         * 单个渲染类XYSeriesRenderer
         */
        XYSeriesRenderer r = new XYSeriesRenderer();// 折线
        r.setPointStyle(PointStyle.CIRCLE);// 设置点的样式
        r.setFillPoints(true);// 填充点（显示的点是空心还是实心）
        r.setDisplayChartValues(true);// 将点的值显示出来
        r.setChartValuesSpacing(10);// 显示的点的值与图的距离
        r.setChartValuesTextSize(18);// 点的值的文字大小
        r.setDisplayChartValuesDistance(5);// 折线点的值距离折线点的距离
        r.setColor(color);// 设置颜色
        r.setLineWidth(3);// 设置线宽
        // r.setFillBelowLine(true);//是否填充折线图的下方
        // r.setFillBelowLineColor(getResources().getColor(R.color.chartcolor));//填充的颜色，如果不设置就默认与线的颜色一致
        mRenderer.addSeriesRenderer(r);

        // }

        // 阀值线(直线1，直线2)
        XYSeries xyseries_up, xyseries_down;
        xyseries_up = new XYSeries("合理范围");
        xyseries_down = new XYSeries("");
        xyseries_up.add(0, maxThreshold);
        xyseries_up.add(seriesDatas.size(), maxThreshold);
        xyseries_down.add(0, minThreshold);
        xyseries_down.add(seriesDatas.size(), minThreshold);
        mDataset.addSeries(1, xyseries_up);
        mDataset.addSeries(2, xyseries_down);

        XYSeriesRenderer datarenderer_down, datarenderer_up;
        datarenderer_up = new XYSeriesRenderer();// 直线1
        datarenderer_down = new XYSeriesRenderer();// 直线2
        datarenderer_up.setColor(Color.GREEN);
        datarenderer_down = datarenderer_up;
        mRenderer.addSeriesRenderer(datarenderer_up);
        mRenderer.addSeriesRenderer(datarenderer_down);

        /**
         * 视图工厂类ChartFactory
         */
        GraphicalView view = ChartFactory.getLineChartView(mContext, mDataset,
                mRenderer);
        /*
         * // 设置传感器当前状态和数据 if (seriesDatas.size() != 0) {
		 * setSensorDataAndStatu(seriesTitle, seriesDatas); }
		 */
        return view;
    }

    /*-------------------------------大棚环境平均值数据End-----------------------------------------*/

    /*-------------------------------大棚传感器数据Start-----------------------------------------*/

    /**
     * @author jcy
     * @Package com.app.itserv.activity
     * @project yyshed
     * @Description: TODO(传感器折线图请求)
     * @date 2017-9-20 20:44:39
     */
    private void getGreenHouseAverageSensor(final String devType, final String devSN,
                                            final String scope, final String updateDate_begin, final String updateDate_end) {
        String token = PreferencesUtils.getString(mContext, "token");// 用户token
        String currTenantId = PreferencesUtils.getString(mContext, "tenantId");// 商户id
        HttpManager.getInstance().gHouseSingleSensor(TAG, token, currTenantId, devType, devSN, scope, updateDate_begin, updateDate_end, new HttpCallBack<JsonGreenhouseAcerageCodeObject>() {

            @Override
            public void onSuccess(JsonGreenhouseAcerageCodeObject date) {
                if (date != null) {
                    String message = date.getMsg();// 提示消息
                    Log.v("Request", "Request :" + message);
                    if (date.isSuccess()) {
                        if (date.getObj().size() <= 0 || date.getObj() == null) {
                            ldCurveView.setNoShown(true);
                            return;
                        }
                        mChartDatas.clear();
                        parseDataSensor(date);//解析数据
                        initTemperatureChart();//绘制图形

                    } else {   // 失败
                        if (!TextUtils.isEmpty(message)) {
                            ToastUtils.makeTextShort(message);
                        } else {
                            ToastUtils.makeTextShort("网络不可用!");
                        }
                       ldCurveView.setNoShown(true);
                    }
                }else {
                    ldCurveView.setNoShown(true);
                }
            }

            @Override
            public void onError(Throwable throwable) {
                ToastUtils.makeTextShort("网络不可用!");
                ldCurveView.setErrorShown(true, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ldCurveView.setProgressShown(true);
                        getGreenHouseAverageSensor(devType, devSN,
                                scope, updateDate_begin, updateDate_end);
                    }
                });
            }
        });

    }

    /**
     * @param @param msg
     * @param @param chardata void
     * @Description:解析温湿度、光照传感器数据
     * @exception:
     * @author: shuy
     * @time:2016-5-5 下午1:37:53
     */
    private void parseDataSensor(JsonGreenhouseAcerageCodeObject gHouse_manager) {
        // TODO Auto-generated method stub
        ArrayList<String> hours = new ArrayList<String>();
        ArrayList<Double> Datas = new ArrayList<Double>();
        ChartDatas ChartDatas = new ChartDatas();
        ChartDatas ChartDatass = new ChartDatas();//
        ArrayList<Double> Datass = new ArrayList<Double>();
        int count = 0;

        for (int i = gHouse_manager.getObj().size() - 1; i >= 0; i--) {
            JsonGreenhouseAcerageCodeObject.ObjBean houseAcerage = gHouse_manager.getObj().get(i);
            if (count == 75) {
                break;
            }
            Double f = 0.0;
            hours.add(houseAcerage.getUpdateDate().substring(10));

            if ("humidity-temperature".equals(houseAcerage.getDeviceType())) {

                ChartDatas.setType("air_temperature");
                ChartDatass.setType("air_humidity");
                Datas.add(TAUtils.parseStrToDouble(TAUtils
                        .getFloatValue(houseAcerage.getTemperature()), f));
                Datass.add(TAUtils.parseStrToDouble(TAUtils
                        .getFloatValue(houseAcerage.getHumidity()), f));

            } else if ("soil-th".equals(houseAcerage.getDeviceType())) {

                ChartDatas.setType("soil_temperature");
                ChartDatass.setType("soil_humidity");
                Datas.add(TAUtils.parseStrToDouble(TAUtils
                        .getFloatValue(houseAcerage.getTemperature()), f));
                Datass.add(TAUtils.parseStrToDouble(TAUtils
                        .getFloatValue(houseAcerage.getHumidity()), f));

            } else if ("co2".equals(houseAcerage.getDeviceType())) {
                ChartDatas.setType("co2");
                Datas.add(TAUtils.parseStrToDouble(TAUtils
                        .getFloatValue(houseAcerage.getCurrentData()), f));

            } else if ("illumination".equals(houseAcerage.getDeviceType())) {
                ChartDatas.setType("lux");

                Datas.add(TAUtils.parseStrToDouble(TAUtils
                        .getFloatValue(houseAcerage.getCurrentData()), f));

            } else if ("smoke".equals(houseAcerage.getDeviceType())) {
                ChartDatas.setType("smoke");
                Datas.add(TAUtils.parseStrToDouble(TAUtils
                        .getFloatValue(houseAcerage.getCurrentData()), f));
            }
            count++;
        }

        Collections.reverse(hours);
        Collections.reverse(Datas);
        ChartDatas.setDatas(Datas);
        ChartDatas.setHours(hours);

        Collections.reverse(Datass);
        ChartDatass.setDatas(Datass);
        ChartDatass.setHours(hours);
        mChartDatas.add(ChartDatas);
        mChartDatas.add(ChartDatass);
    }
    /*-------------------------------大棚传感器数据End-----------------------------------------*/

    // 将时间转换成HH:mm:ss，并且将原有时间加上8个小时
    private String formatTime(String time) {
        SimpleDateFormat newTime = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date date = null;
        try {
            date = newTime.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
       // ca.add(Calendar.HOUR_OF_DAY, 8);
        DateFormat df3 = DateFormat.getTimeInstance();// 只显示出时分秒
        String timeValue = df3.format(ca.getTime());
        return timeValue;
    }

    /**
     * 6 * 时间戳转换成日期格式字符串 7 * @param seconds 精确到秒的字符串 8 * @param formatStr 9 * @return
     * 10
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds)));
    }

    // 将时间转换成HH:mm:ss，并且将原有时间加上8个小时
    private String formatTimes(Long time) {
        SimpleDateFormat newTime = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            Long times = new Long(time);
            String dateStr = newTime.format(times);
            date = newTime.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        DateFormat df3 = DateFormat.getTimeInstance();// 只显示出时分秒
        String timeValue = df3.format(ca.getTime());
        return timeValue;
    }
}
