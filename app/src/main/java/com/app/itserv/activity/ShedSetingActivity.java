package com.app.itserv.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.itserv.MineApplication;
import com.app.itserv.adapters.ShedSettingAdapter;
import com.app.itserv.jparser.AlarmIgnoreBean;
import com.google.gson.Gson;
import com.itserv.app.util.GsonUtils;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.yycloud.app.AlarmStrategyManager;
import com.yycloud.app.MyUser;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.WAPI;
import com.yycloud.app.utils.WapiUtil;
import com.yycloud.app.utils.WapiUtilEx;
import com.yycloud.core.beans.AlarmStrategy;
import com.yycloud.core.beans.Components;
import com.yycloud.core.beans.ShedInfo;
import com.yycloud.core.config.Constants;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShedSetingActivity extends Activity implements
        RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    // Logger logger = Logger.getLogger(ShedSettingFragment.class);
    final int LOAD_CONTENT = 1100;
    private Context mContext;
    private ListView mShedSettingListView;
    private ShedSettingAdapter mAdapter;

    private String devUuid,DevName;
    private RadioGroup mSettingRG;
    private LinearLayout mAliasSetLL, shed_setting_lay,
            shed_setting_ignore_Layout;
    private LinearLayout mOtherSetLL;
    private ScrollView mAlarmSetLL;
    private Spinner mAlarmSetSpinner;
    private ImageView mAddStrategy, mSaveStrategy, mRefreshStrategy,
            ignore_greenhouse_ok, ignore_device_ok, mDeleteStrategy,imgBack,
            change_strategy;
    List<AlarmStrategy> mAllAlarmStrategys = null;
    AlarmStrategy strategy = null;
    private String mSeletorStrategyName = "";
    private int mSeletorStrategyId = 0;
    private LinearLayout mAlarmView;
    private Button mDeleteSmartGateButton;
    // 删除大棚弹框
    private AlertDialog localBuilder;
    private RadioButton alarmSet;

    private TextView shed_setting_tv, alarm_setting_ieee_tv;
    private CheckBox shed_setting_ch;
    private LayoutInflater inflater;
    private ListView lv_alarm_ignore;
    Map<String, CheckBox> IgnoreviewMap = new HashMap<String, CheckBox>();
    List<AlarmIgnoreBean> ignores = new ArrayList<AlarmIgnoreBean>();
    private ShedInfo shedInfo = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shed_seting);
        mContext = this;
         shedInfo = (ShedInfo) getIntent().getSerializableExtra("shedInfo");
        devUuid = shedInfo.getSmartgate().getSn();
        initViews();
        initAlram();
        // 加载大棚信息
        mHandler.sendEmptyMessage(WAPI.MSG_ONLOADSHEDINFO);
    }

    private void initIgnore() {
        if (shedInfo != null) {

            shed_setting_tv.setText(shedInfo.getSmartgate().getDev_name());
            alarm_setting_ieee_tv.setText(shedInfo.getSmartgate().getSn());

            List<Components> components = shedInfo.getComponents();
            shed_setting_ignore_Layout.removeAllViews();
            for (final Components components2 : components) {
                View inflate = inflater.inflate(R.layout.alarm_setting_item,
                        null);
                TextView shed_setting_item_tv = (TextView) inflate
                        .findViewById(R.id.shed_setting_item_tv);
                TextView alarm_setting_item_ieee_tv = (TextView) inflate
                        .findViewById(R.id.alarm_setting_item_ieee_tv);

                final CheckBox checkBox = (CheckBox) inflate
                        .findViewById(R.id.shed_setting_item_ch);

                shed_setting_item_tv.setText(components2.getDev_name());
                alarm_setting_item_ieee_tv.setText(components2.getSn());
                IgnoreviewMap.put(components2.getSn(), checkBox);
                MineApplication.executorService.submit(new Runnable() {

                    @Override
                    public void run() {
                        String alarmIgnore = WapiUtil.getAlarmIgnore();
                        final List<AlarmIgnoreBean> parseArray = GsonUtils.parseJsonArrayWithGson(alarmIgnore, AlarmIgnoreBean[].class);
                        ((Activity) mContext).runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                if (parseArray != null) {

                                    for (AlarmIgnoreBean components3 : parseArray) {
                                        if (components3.getSn()
                                                .equals(shedInfo.getSmartgate()
                                                        .getSn())) {
                                            shed_setting_ch.setChecked(true);
                                        }

                                        if (components2.getSn().equals(
                                                components3.getSn())) {
                                            checkBox.setChecked(true);
                                        }
                                    }
                                }
                            }
                        });
                    }
                });
                shed_setting_ignore_Layout.addView(inflate);
            }
        }
        // lv_alarm_ignore.setAdapter(new IgnoreAdapter(mContext));
        shed_setting_ch
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        if (isChecked) {
                            shed_setting_ignore_Layout
                                    .setVisibility(View.INVISIBLE);
                        } else {
                            shed_setting_ignore_Layout
                                    .setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    private void initViews() {
        inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mShedSettingListView = (ListView) findViewById(R.id.shedsettinglist);
        // 大棚设置adapter
        // mAdapter = new ShedSettingAdapter(mContext, devUuid, mHandler);
        // mShedSettingListView.setAdapter(mAdapter);
        mSettingRG = (RadioGroup) findViewById(R.id.setting);
        mSettingRG.setOnCheckedChangeListener(this);
        imgBack = (ImageView) findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);
        mAlarmView = (LinearLayout) findViewById(R.id.alarm_view);
        mAliasSetLL = (LinearLayout) findViewById(R.id.alias_set_ll);
        mOtherSetLL = (LinearLayout) findViewById(R.id.other_set_ll);
        mAlarmSetLL = (ScrollView) findViewById(R.id.alarm_set_ll);
        alarmSet = (RadioButton) findViewById(R.id.alarm_set);
        mAlarmSetSpinner = (Spinner) findViewById(R.id.alram_setting_spinner);
        mAddStrategy = (ImageView) findViewById(R.id.add_strategy);
        mAddStrategy.setOnClickListener(this);
        mSaveStrategy = (ImageView) findViewById(R.id.save_strategy);
        mSaveStrategy.setOnClickListener(this);
        mRefreshStrategy = (ImageView) findViewById(R.id.strategy_refresh);
        mRefreshStrategy.setOnClickListener(this);
     //   mDeleteSmartGateButton = (Button) findViewById(R.id.btn_delete_shed);
       // mDeleteSmartGateButton.setOnClickListener(this);
        // 20170620
        shed_setting_lay = (LinearLayout) findViewById(R.id.shed_setting_lay);
        shed_setting_ignore_Layout = (LinearLayout) findViewById(R.id.shed_setting_ignore_Layout);
        shed_setting_tv = (TextView) findViewById(R.id.shed_setting_name_tv);
        alarm_setting_ieee_tv = (TextView) findViewById(R.id.alarm_setting_ieee_tv);
        shed_setting_ch = (CheckBox) findViewById(R.id.shed_setting_ch);
        // lv_alarm_ignore = (ListView) findViewById(R.id.lv_alarm_ignore);
        ignore_greenhouse_ok = (ImageView) findViewById(R.id.ignore_greenhouse_ok);
        ignore_greenhouse_ok.setOnClickListener(this);
        ignore_device_ok = (ImageView) findViewById(R.id.ignore_device_ok);
        ignore_device_ok.setOnClickListener(this);
        mDeleteStrategy = (ImageView) findViewById(R.id.delete_strategy);
        mDeleteStrategy.setOnClickListener(this);
        change_strategy = (ImageView) findViewById(R.id.change_strategy);
        change_strategy.setOnClickListener(this);
    }



    /**
     * init alias setting
     */
    private void initAlias() {
        mAlarmSetLL.setVisibility(View.GONE);
        mOtherSetLL.setVisibility(View.GONE);
        mAliasSetLL.setVisibility(View.VISIBLE);
    }

    /**
     * init alarm strategy setting
     */
    private void initAlram() {
        alarmSet.setBackgroundResource(R.drawable.comment_bg);
        initProducts();
        mAlarmSetLL.setVisibility(View.VISIBLE);
        mAliasSetLL.setVisibility(View.GONE);
        mOtherSetLL.setVisibility(View.GONE);
        getShedStrategy();
    }

    /**
     * init other setting
     */
    private void initOthers() {
        mAlarmSetLL.setVisibility(View.GONE);
        mAliasSetLL.setVisibility(View.GONE);
        mOtherSetLL.setVisibility(View.VISIBLE);
    }


    private void initProducts() {
        mAllAlarmStrategys = null;
        mAllAlarmStrategys = new ArrayList<AlarmStrategy>();
        List<CharSequence> mAllStrategyNames = new ArrayList<CharSequence>();
        AlarmStrategy nostrategy = new AlarmStrategy();
        //默认报警策略暂时不可用20170712
        nostrategy.setStrategy_name(" <未设置报警策略>");
        mAllAlarmStrategys.add(nostrategy);
        mAllStrategyNames.add(" <未设置报警策略>");

        // get all the alarm
        List<AlarmStrategy> stratlist = AlarmStrategyManager.getInstance()
                .getAllAlarmStrategies();

        if (stratlist != null) {
            for (int i = 0; i < stratlist.size(); i++) {
                AlarmStrategy as = stratlist.get(i);
                // log.v(as.toJson() + "");
                mAllAlarmStrategys.add(as);
                String postfix = "";
                if (as.getStrategy_type().equals(
                        AlarmStrategyManager.DEFAULT_STRATEGY_TYPE)) {
                    postfix = "默认策略";
                } else {
                    postfix = "自定义策略";
                }
                mAllStrategyNames.add(as.getStrategy_name() + "(" + postfix
                        + ")");
            }
        }

        ArrayAdapter<CharSequence> prAdapter1 = new ArrayAdapter<CharSequence>(
                mContext, android.R.layout.simple_spinner_item,
                mAllStrategyNames);
        prAdapter1
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 将数据绑定到Spinner视图上
        mAlarmSetSpinner.setAdapter(prAdapter1);
        mAlarmSetSpinner
                .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id) {
                        Spinner spinner = (Spinner) parent;
                        mSeletorStrategyName = (String) spinner
                                .getItemAtPosition(position);
                        mSeletorStrategyId = position;
                        strategy = mAllAlarmStrategys.get(position);
                        loadStrategyContent(mAllAlarmStrategys.get(position));
                        if (mSeletorStrategyName.contains("默认策略")) {
                            change_strategy.setVisibility(View.INVISIBLE);
                            mDeleteStrategy.setVisibility(View.INVISIBLE);
                        } else {
                            change_strategy.setVisibility(View.VISIBLE);
                            mDeleteStrategy.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        /******************************/

    }

    public void getShedStrategy() {

        MyUser.getInstance().getShedStrategy(devUuid,
                new MyUser.AlarmStrategyCallBack() {
                    @Override
                    public void onError(int code, String message) {
                    }

                    @Override
                    public void onSuccess(AlarmStrategy s) {
                        for (int i = 0; i < mAllAlarmStrategys.size(); i++) {
                            AlarmStrategy ass = mAllAlarmStrategys.get(i);
                            if (s.getStrategy_id().equals(ass.getStrategy_id())) {
                                Message msg = new Message();
                                msg.obj = s;
                                msg.arg1 = i;
                                msg.what = LOAD_CONTENT;
                                mHandler.sendMessage(msg);

                                break;
                            }
                        }
                    }
                });
    }

    private void loadStrategyContent(AlarmStrategy s) {
        LinearLayout lltemperature = (LinearLayout) findViewById(R.id.alram_setting);
        lltemperature.removeAllViews();
        if (s.getStrategyitems().size() > 0) {
            mAlarmView.setVisibility(View.VISIBLE);
        }
        for (int i = 0; i < s.getStrategyitems().size(); i++) {
            AlarmStrategy.AlarmStrategyItem itm = s.getStrategyitems().get(i);
            if (AlarmStrategy.AlarmType.charge_config == itm.getType()) {
                loadStrategyTemperature(lltemperature, itm, "电量报警门限设置", "高电量",
                        "低电量", " %");
            }
            if (AlarmStrategy.AlarmType.air_temperature_config == itm.getType()) {
                loadStrategyTemperature(lltemperature, itm, "空气温度报警门限设置", "高温",
                        "低温", " ℃");
            }

            if (AlarmStrategy.AlarmType.air_humidity_config == itm.getType()) {
                loadStrategyTemperature(lltemperature, itm, "空气湿度报警门限设置",
                        "高湿度", "低湿度", " RH");
            }
            if (AlarmStrategy.AlarmType.co_ppm_config == itm.getType()) {
                loadStrategyTemperature(lltemperature, itm, "co浓度报警门限设置",
                        "高co浓度", "低co浓度", " ppm");
            }
            if (AlarmStrategy.AlarmType.co2_ppm_config == itm.getType()) {
                loadStrategyTemperature(lltemperature, itm, "co2浓度报警门限设置",
                        "高co2浓度", "低co2浓度", " ppm");
            }
            if (AlarmStrategy.AlarmType.lux_config == itm.getType()) {
                loadStrategyTemperature(lltemperature, itm, "光照强度报警门限设置",
                        "高光照强度", "低光照强度", " lux");
            }
            if (AlarmStrategy.AlarmType.soil_temperature_config == itm.getType()) {
                loadStrategyTemperature(lltemperature, itm, "土壤温度报警门限设置",
                        "高土壤温度", "低土壤温度", " ℃");
            }
            if (AlarmStrategy.AlarmType.soil_humidity_config == itm.getType()) {
                loadStrategyTemperature(lltemperature, itm, "土壤湿度报警门限设置",
                        "高土壤湿度", "低土壤湿度", " RH");
            }
            if (AlarmStrategy.AlarmType.soil_moisture_config == itm.getType()) {
                loadStrategyTemperature(lltemperature, itm, "土壤水分报警门限设置",
                        "高土壤水分", "低土壤水分", " lux");
            }
            if (AlarmStrategy.AlarmType.rain_config == itm.getType()) {
                loadStrategyTemperature(lltemperature, itm, "降雨量报警门限设置",
                        "高降雨量", "低降雨量", " lux");
            }
            if (AlarmStrategy.AlarmType.windvelocity_config == itm.getType()) {
                loadStrategyTemperature(lltemperature, itm, "风速报警门限设置", "高风速",
                        "低风速", " lux");
            }
            if (AlarmStrategy.AlarmType.pm2_5_ppm_config == itm.getType()) {
                loadStrategyTemperature(lltemperature, itm, "pm2.5报警门限设置",
                        "高pm2.5", "低pm2.5", " lux");
            }
            if (AlarmStrategy.AlarmType.smoke_ppm_config == itm.getType()) {
                loadStrategyTemperature(lltemperature, itm, "烟雾浓度报警门限设置",
                        "高烟雾浓度", "低烟雾浓度", " lux");
            }
            if (AlarmStrategy.AlarmType.pm10_ppm_config == itm.getType()) {
                loadStrategyTemperature(lltemperature, itm, "pm10报警门限设置",
                        "高pm10", "低pm10", " lux");
            }
        }

    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    private void loadStrategyTemperature(LinearLayout alarmView,
                                         AlarmStrategy.AlarmStrategyItem itm, String title, String highTitle,
                                         String lowTitle, String unit) {

        AlarmStrategy.Alarm[] ha = itm.getAlarms(AlarmStrategy.HighLowAlarm.High);
        AlarmStrategy.Alarm[] la = itm.getAlarms(AlarmStrategy.HighLowAlarm.Low);
        TextView itemName = new TextView(mContext);

        itemName.setClickable(true);
        itemName.setGravity(Gravity.LEFT);
        int p = dip2px(mContext, 10);
        itemName.setPadding(p, 0, p, 0);
        itemName.setSingleLine(true);
        itemName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        itemName.setText(title);

        LinearLayout leftAndRight = new LinearLayout(mContext);// 左右区域，左图标，右边区域为值
        leftAndRight.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams leftAndRigthlp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        leftAndRigthlp.setMargins(dip2px(mContext, 10), dip2px(mContext, 10),
                0, 0);
        alarmView.addView(itemName);
        alarmView.addView(leftAndRight, leftAndRigthlp);

        ImageView imageView = new ImageView(mContext);// 图标
        imageView.setImageResource(getAlarmIcoByType(itm.getType().name()));
        leftAndRight.addView(imageView);

        // 上下区域，上区域为大值，下区域为小值
        LinearLayout upAndDown = new LinearLayout(mContext);
        upAndDown.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams upAndDownlp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        upAndDownlp.setMargins(dip2px(mContext, 20), 0, 0, 0);
        leftAndRight.addView(upAndDown, upAndDownlp);
        // 电量无大值
        if (!itm.getType().name()
                .equals(AlarmStrategy.AlarmType.charge_config.name())) {

            LinearLayout high = new LinearLayout(mContext);// 大值区域
            high.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams highlp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            upAndDown.addView(high, highlp);

            TextView highName = new TextView(mContext);
            highName.setGravity(Gravity.LEFT);
            highName.setSingleLine(true);
            highName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            highName.setText(highTitle);
            high.addView(highName);

            LinearLayout highValueUpAndDown = new LinearLayout(mContext);// 多个值，上下排列
            highValueUpAndDown.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams highValueUpAndDownlp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            highValueUpAndDownlp.setMargins(dip2px(mContext, 20), 0, 0, 0);
            high.addView(highValueUpAndDown, highValueUpAndDownlp);
            for (int j = 0; j < ha.length; j++) {
                // if (ha[j].enable == false)
                // break;
                if (ha[j].alarmTriggerVal == null)
                    continue;
                LinearLayout highValue = new LinearLayout(mContext);// 多个值，上下排列
                highValue.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams highValuelp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.FILL_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                highValueUpAndDown.addView(highValue, highValuelp);

                TextView hValue = new TextView(mContext);
                hValue.setGravity(Gravity.LEFT);
                int hValuep = dip2px(mContext, 5);
                hValue.setPadding(hValuep, hValuep, hValuep, hValuep);
                hValue.setSingleLine(true);
                hValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                if (j == 0) {
                    hValue.setText("黄色");
                }
                if (j == 1) {
                    hValue.setText("橙色");
                }
                if (j == 2) {
                    hValue.setText("红色");
                }
                highValue.addView(hValue);

                TextView hValue1 = new TextView(mContext);
                hValue1.setGravity(Gravity.LEFT);
                int hValue1p = dip2px(mContext, 5);
                hValue1.setPadding(hValue1p, hValue1p, hValue1p, hValue1p);
                hValue1.setSingleLine(true);
                hValue1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                hValue1.setText(ha[j].alarmTriggerVal + unit);
                highValue.addView(hValue1);

            }
        }

        // 小值区域
        LinearLayout low = new LinearLayout(mContext);
        low.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams lowlp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        upAndDown.addView(low, lowlp);

        TextView lowName = new TextView(mContext);
        lowName.setGravity(Gravity.LEFT);
        lowName.setSingleLine(true);
        lowName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        lowName.setText(lowTitle);
        low.addView(lowName);

        LinearLayout lowValueUpAndDown = new LinearLayout(mContext);// 多个值，上下排列
        lowValueUpAndDown.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams lowValueUpAndDownlp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.FILL_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lowValueUpAndDownlp.setMargins(dip2px(mContext, 20), 0, 0, 0);
        low.addView(lowValueUpAndDown, lowValueUpAndDownlp);

        for (int j = 0; j < la.length; j++) {
            // log.v(la[j].alarmTriggerVal+"");
            if (la[j].alarmTriggerVal == null)
                continue;
            LinearLayout lowValue = new LinearLayout(mContext);// 多个值，上下排列
            lowValue.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams highValuelp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            lowValueUpAndDown.addView(lowValue, highValuelp);

            TextView lValue = new TextView(mContext);
            lValue.setGravity(Gravity.LEFT);
            int lValuep = dip2px(mContext, 5);
            lValue.setPadding(lValuep, lValuep, lValuep, lValuep);
            lValue.setSingleLine(true);
            lValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            if (j == 0) {
                lValue.setText("黄色");
            }
            if (j == 1) {
                lValue.setText("橙色");
            }
            if (j == 2) {
                lValue.setText("红色");
            }
            lowValue.addView(lValue);

            TextView lValue1 = new TextView(mContext);
            lValue1.setGravity(Gravity.LEFT);
            int lValue1p = dip2px(mContext, 5);
            lValue1.setPadding(lValue1p, lValue1p, lValue1p, lValue1p);
            lValue1.setSingleLine(true);
            lValue1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            lValue1.setText(la[j].alarmTriggerVal + unit);
            lowValue.addView(lValue1);

        }
    }

    /**
     * get the strategy ico by the alarm config type
     *
     * @param alarm_type
     * @return
     */
    private int getAlarmIcoByType(String alarm_type) {
        if (alarm_type.equals(AlarmStrategy.AlarmType.charge_config.name())) {
            return R.drawable.alarm_battery;
        } else if (alarm_type
                .equals(AlarmStrategy.AlarmType.air_temperature_config.name())) {
            return R.drawable.alarm_air_t;
        } else if (alarm_type
                .equals(AlarmStrategy.AlarmType.air_humidity_config.name())) {
            return R.drawable.alarm_air_h;
        } else if (alarm_type.equals(AlarmStrategy.AlarmType.co2_ppm_config
                .name())) {
            return R.drawable.alarm_co2;
        } else if (alarm_type.equals(AlarmStrategy.AlarmType.co_ppm_config
                .name())) {
            return R.drawable.alarm_co;
        } else if (alarm_type.equals(AlarmStrategy.AlarmType.lux_config.name())) {
            return R.drawable.alarm_lux;
        } else if (alarm_type
                .equals(AlarmStrategy.AlarmType.soil_temperature_config.name())) {
            return R.drawable.alarm_air_t;
        } else if (alarm_type
                .equals(AlarmStrategy.AlarmType.soil_humidity_config.name())) {
            return R.drawable.alarm_air_h;
        } else {
            return R.drawable.unknown;
        }
    }

    public Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case WAPI.MSG_ONLOADSHEDINFO:
                  //  shedInfo = MyUser.getInstance().getShedInfo(devUuid);
                    if (null != shedInfo)

                     //   new ShedSetting().execute();
                    initIgnore();
                    break;
                case 1:
                    String result = (String) msg.obj;
                    ToastUtils.makeTextShort(result);
                    break;
                case 10:
                    initProducts();
                    break;

                case LOAD_CONTENT:
                    AlarmStrategy s = (AlarmStrategy) msg.obj;
                    loadStrategyContent(s);
                    int index = msg.arg1;
                    mAlarmSetSpinner.setSelection(index, true);
                    mAlarmSetSpinner.invalidate();

                    break;
                default:
                    break;
            }
        }

        ;
    };

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.alarm_set:
                initAlram();
                break;

            case R.id.other_set:
                alarmSet.setBackgroundResource(R.drawable.unseletor_setting_tab_bg);
                initOthers();
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        Gson gson = new Gson();
        switch (v.getId()) {
            case R.id.ignore_greenhouse_ok:
                ignores.clear();
                AlarmIgnoreBean alarmIgnoreBean = new AlarmIgnoreBean();
                alarmIgnoreBean.setSn(shedInfo.getSmartgate().getSn());

                if (shed_setting_ch.isChecked()) {
                    // str = "忽略大棚成功";
                    alarmIgnoreBean.setChecked(true);
                    shed_setting_ch.setChecked(true);
                } else {
                    // String str = "是否忽略大棚,请勾选下方勾选框";
                    // Toast.makeText(mContext, str, Toast.LENGTH_SHORT).show();
                    alarmIgnoreBean.setChecked(false);
                    shed_setting_ch.setChecked(false);
                }
                ignores.add(alarmIgnoreBean);
                List<Components> components2 = shedInfo.getComponents();

                for (Components components : components2) {

                    final AlarmIgnoreBean alarmIgnore = new AlarmIgnoreBean();
                    alarmIgnore.setSn(components.getSn());
                    alarmIgnore.setChecked(false);
                    ignores.add(alarmIgnore);
                }
                String j = gson.toJson(ignores);
                new addAlarmIgnore().execute(j);

                break;

            case R.id.ignore_device_ok:
                ignores.clear();
                List<Components> componentsList = shedInfo.getComponents();

                AlarmIgnoreBean alarmIgnoreB = new AlarmIgnoreBean();
                alarmIgnoreB.setSn(shedInfo.getSmartgate().getSn());
                alarmIgnoreB.setChecked(false);
                ignores.add(alarmIgnoreB);
                for (Components components : componentsList) {

                    final CheckBox checkBox = IgnoreviewMap.get(components.getSn());

                    final AlarmIgnoreBean alarmIgnore = new AlarmIgnoreBean();

                    alarmIgnore.setSn(components.getSn());
                    alarmIgnore.setChecked(false);
                    // log.v(components.getSn());
                    if (checkBox.isChecked()) {
                        alarmIgnore.setChecked(true);
                        checkBox.setChecked(true);
                    } else {
                        checkBox.setChecked(false);
                    }
                    ignores.add(alarmIgnore);

                }
                String json = gson.toJson(ignores);
                new addAlarmIgnore().execute(json);
                // log.v(json);
                break;
            case R.id.add_strategy:

                Intent intent = new Intent(mContext, AlarmPlanSettingActivity.class);
                startActivity(intent);
                break;

            case R.id.strategy_refresh:// 刷新方法
                // refresh the strategy
                AlarmStrategyManager.getInstance().init(mHandler);
                break;

            case R.id.save_strategy:
                if (mSeletorStrategyId == 0)
                    WapiUtilEx.delShedStrategy(devUuid,
                            new MYCallBack() {

                                @Override
                                public void onError(int code, String message) {
                                    Log.v("save_strategy", message);
                                    Message msg = new Message();
                                    msg.obj = "err " + message;
                                    msg.what = 1;
                                    mHandler.sendMessage(msg);
                                }

                                @Override
                                public void onSuccess(String result) {
                                    Log.v("save_strategy", result);
                                    Message msg = new Message();
                                    msg.obj = "设置成功";
                                    msg.what = 1;
                                    mHandler.sendMessage(msg);
                                }

                            });
                else {
                    AlarmStrategy strategy = mAllAlarmStrategys
                            .get(mSeletorStrategyId);
                    strategy.toJson();
                    String s = strategy.getStrategy_type();
                    if (s == null || s.equals(""))
                        s = Constants.STRATEGYTYPE_DEFAULT;
                    WapiUtilEx.addOrUpdateShedStrategy(devUuid, s,
                            strategy, new MYCallBack() {

                                @Override
                                public void onError(int code, String message) {
                                    Log.e("on error", "null");
                                    Message msg = new Message();
                                    msg.obj = "error" + message;
                                    msg.what = 1;
                                    mHandler.sendMessage(msg);
                                }

                                @Override
                                public void onSuccess(String s) {
                                    MyUser.getInstance().getShedStrategy(
                                         devUuid,
                                            new MyUser.AlarmStrategyCallBack() {
                                                @Override
                                                public void onError(int code,
                                                                    String message) {
                                                    Log.e("on error1", "null");
                                                    Message msg = new Message();
                                                    msg.obj = "error ";
                                                    msg.what = 1;
                                                    mHandler.sendMessage(msg);
                                                }

                                                @Override
                                                public void onSuccess(
                                                        AlarmStrategy s) {
                                                    Message msg = new Message();
                                                    msg.obj = "设置成功";
                                                    msg.what = 1;
                                                    mHandler.sendMessage(msg);
                                                }
                                            });
                                }

                            });
                }
                break;
            case R.id.img_back: // back
                finish();
                break;
            case R.id.delete_strategy: // delete
                deleteStrategy();
                break;
            case R.id.change_strategy: // change
                Intent intent2 = new Intent(mContext,
                        ChangeAlarmPlanSettingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("strategy", strategy);
                intent2.putExtras(bundle);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }


    private class DeleteAlarm extends AsyncTask<Object, Void, String> {

        @Override
        protected String doInBackground(Object... arg0) {
            String staString = "";
            String result = WapiUtil.deleteDefaultAlarmStrategies(strategy
                    .getStrategy_id());

            try {
                JSONObject jsonObject = new JSONObject(result);

                String addState = jsonObject.getString("result");

                if (addState.equals("OK")) {
                    staString = "删除成功";
                } else {
                    String msg = jsonObject.getString("msg");
                    staString = "删除失败:" + msg;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return staString;
        }

        protected void onPostExecute(String re) {
            if ("删除成功".equals(re)) {
                localBuilder.dismiss();
                AlarmStrategyManager.getInstance().init(mHandler);
            }
            ToastUtils.makeTextShort(re);
        }

    }

    /**
     * @param
     * @Description:删除策略
     * @exception:
     * @author: axin
     * @time:2016-5-5 下午4:13:10
     */
    private void deleteStrategy() {
        localBuilder = new AlertDialog.Builder(mContext).create();
        localBuilder.show();
        View fragmentSetshedDeldialog = LayoutInflater.from(mContext).inflate(
                R.layout.fragment_setshed_deldialog, null);
        localBuilder.addContentView(fragmentSetshedDeldialog,
                new ViewGroup.LayoutParams((int) (mContext.getResources()
                        .getDisplayMetrics().widthPixels * 0.9),
                        ViewGroup.LayoutParams.WRAP_CONTENT));
        localBuilder.setCanceledOnTouchOutside(true);
        Button btnEnsure = (Button) fragmentSetshedDeldialog
                .findViewById(R.id.btn_ensure);
        Button btnCancel = (Button) fragmentSetshedDeldialog
                .findViewById(R.id.btn_cancel);
        TextView tv_deldialog_content = (TextView) fragmentSetshedDeldialog
                .findViewById(R.id.tv_deldialog_content);
        tv_deldialog_content.setText("是否删除<" + strategy.getStrategy_name()
                + ">策略?");
        // 确认
        btnEnsure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                new DeleteAlarm().execute();
            }
        });
        // 取消
        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                localBuilder.dismiss();
            }
        });

    }


    private class addAlarmIgnore extends AsyncTask<Object, Void, String> {

        @Override
        protected String doInBackground(Object... arg0) {
            String json = (String) arg0[0];
            String staString = "";
            String result = WapiUtil.setAlarmIgnore(json);
            //log.v(result);

            try {
                JSONObject jsonObject = new JSONObject(result);
                String addState = jsonObject.getString("result");
                if (addState.equals("OK")) {
                    staString = "忽略成功";
                } else {
                    staString = "忽略失败";
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return staString;
        }

        @Override
        protected void onPostExecute(String re) {

            ToastUtils.makeTextShort(re);
        }
    }
}