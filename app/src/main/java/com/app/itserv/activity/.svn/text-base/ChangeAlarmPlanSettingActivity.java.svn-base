package com.app.itserv.activity;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.itserv.ActivityCollector;
import com.app.itserv.BaseActivity;
import com.google.gson.Gson;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.WapiUtil;
import com.yycloud.core.beans.AlarmPlanSettingBean;
import com.yycloud.core.beans.AlarmPlanSettingBean.AirHumidityConfigBean;
import com.yycloud.core.beans.AlarmPlanSettingBean.AirTemperatureConfigBean;
import com.yycloud.core.beans.AlarmPlanSettingBean.ChargeConfigBean;
import com.yycloud.core.beans.AlarmPlanSettingBean.Co2PpmConfigBean;
import com.yycloud.core.beans.AlarmPlanSettingBean.CoPpmConfigBean;
import com.yycloud.core.beans.AlarmPlanSettingBean.LuxConfigBean;
import com.yycloud.core.beans.AlarmPlanSettingBean.SmokePpmConfigBean;
import com.yycloud.core.beans.AlarmPlanSettingBean.SoilHumidityConfigBean;
import com.yycloud.core.beans.AlarmPlanSettingBean.SoilTemperatureConfigBean;
import com.yycloud.core.beans.AlarmStrategy;
import com.yycloud.core.beans.AlarmStrategy.Alarm;
import com.yycloud.core.beans.AlarmStrategy.AlarmStrategyItem;
import com.yycloud.core.beans.AlarmStrategy.AlarmType;
import com.yycloud.core.beans.AlarmStrategy.HighLowAlarm;

public class ChangeAlarmPlanSettingActivity extends BaseActivity implements
		OnClickListener {
	private Context mContext;
	private EditText mTitelEditText;
	private ImageView btnPlanBack, btnPlanSubmit;
	private AlertDialog localBuilder;
	public static AlarmStrategy mAlarmStrategy = new AlarmStrategy();
	
	// 电量
	private CheckBox air_psa_ch1, air_psa_ch2, air_psa_ch3;
	private EditText air_psa_et1, air_psa_et2, air_psa_et3;
	// 空气温度
	private CheckBox air_temp_h_ch1, air_temp_h_ch2, air_temp_h_ch3,
			air_temp_l_ch1, air_temp_l_ch2, air_temp_l_ch3;
	private EditText air_temp_h_ed1, air_temp_h_ed2, air_temp_h_ed3,
			air_temp_l_ed1, air_temp_l_ed2, air_temp_l_ed3;
	// 空气湿度
	private CheckBox air_humid_h_ch1, air_humid_h_ch2, air_humid_h_ch3,
			air_humid_l_ch1, air_humid_l_ch2, air_humid_l_ch3;
	private EditText air_humid_h_ed1, air_humid_h_ed2, air_humid_h_ed3,
			air_humid_l_ed1, air_humid_l_ed2, air_humid_l_ed3;
	// 土壤温度
	private CheckBox air_soil_temp_h_ch1, air_soil_temp_h_ch2,
			air_soil_temp_h_ch3, air_soil_temp_l_ch1, air_soil_temp_l_ch2,
			air_soil_temp_l_ch3;
	private EditText air_soil_temp_h_ed1, air_soil_temp_h_ed2,
			air_soil_temp_h_ed3, air_soil_temp_l_ed1, air_soil_temp_l_ed2,
			air_soil_temp_l_ed3;
	// 空气湿度
	private CheckBox air_soil_humid_h_ch1, air_soil_humid_h_ch2,
			air_soil_humid_h_ch3, air_soil_humid_l_ch1, air_soil_humid_l_ch2,
			air_soil_humid_l_ch3;
	private EditText air_soil_humid_h_ed1, air_soil_humid_h_ed2,
			air_soil_humid_h_ed3, air_soil_humid_l_ed1, air_soil_humid_l_ed2,
			air_soil_humid_l_ed3;
	// 光照
	private CheckBox air_lux_h_ch1, air_lux_h_ch2, air_lux_h_ch3,
			air_lux_l_ch1, air_lux_l_ch2, air_lux_l_ch3;
	private EditText air_lux_h_ed1, air_lux_h_ed2, air_lux_h_ed3,
			air_lux_l_ed1, air_lux_l_ed2, air_lux_l_ed3;
	// co
	private CheckBox air_co_h_ch1, air_co_h_ch2, air_co_h_ch3, air_co_l_ch1,
			air_co_l_ch2, air_co_l_ch3;
	private EditText air_co_h_ed1, air_co_h_ed2, air_co_h_ed3, air_co_l_ed1,
			air_co_l_ed2, air_co_l_ed3;
	// co2
	private CheckBox air_co2_h_ch1, air_co2_h_ch2, air_co2_h_ch3,
			air_co2_l_ch1, air_co2_l_ch2, air_co2_l_ch3;
	private EditText air_co2_h_ed1, air_co2_h_ed2, air_co2_h_ed3,
			air_co2_l_ed1, air_co2_l_ed2, air_co2_l_ed3;
	// 烟雾
	private CheckBox air_smoke_h_ch1, air_smoke_h_ch2, air_smoke_h_ch3;
	private EditText air_smoke_h_ed1, air_smoke_h_ed2, air_smoke_h_ed3;
	AlarmStrategy strategy = null;
	String strategy_id;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		ActivityCollector.addActivity(this);
		super.onCreate(savedInstanceState);
		mContext = this;
		setContentView(R.layout.alarm_plan_setting_lay);
		initViews();
		Intent intent = this.getIntent();
		strategy = (AlarmStrategy) intent.getSerializableExtra("strategy");

		initEditCheckedState();
		editInputLimit();
		initdata();
	}

	private void initdata() {
		String strategy_name = strategy.getStrategy_name();
		mTitelEditText.setText(strategy_name);
		strategy_id = strategy.getStrategy_id();
		String strategy_type = strategy.getStrategy_type();
		List<AlarmStrategyItem> strategyitems = strategy.getStrategyitems();
		for (AlarmStrategyItem alarmStrategyItem : strategyitems) {
			AlarmType type = alarmStrategyItem.getType();
			initTypeData(type, alarmStrategyItem);
		}
	}

	private void initTypeData(AlarmType type,
			AlarmStrategyItem alarmStrategyItem) {
		Alarm[] ha = alarmStrategyItem.getAlarms(HighLowAlarm.High);
		Alarm[] la = alarmStrategyItem.getAlarms(HighLowAlarm.Low);
		// 电量
		if (AlarmType.charge_config == type) {

			setLaData(la, air_psa_et1, air_psa_et2, air_psa_et3, air_psa_ch1,
					air_psa_ch2, air_psa_ch3);

		}
		// 空气温度
		if (AlarmType.air_temperature_config == type) {

			setHaData(ha, air_temp_h_ed1, air_temp_h_ed2, air_temp_h_ed3,
					air_temp_h_ch1, air_temp_h_ch2, air_temp_h_ch3);

			setLaData(la, air_temp_l_ed1, air_temp_l_ed2, air_temp_l_ed3,
					air_temp_l_ch1, air_temp_l_ch2, air_temp_l_ch3);

		}
		// 空气湿度
		if (AlarmType.air_humidity_config == type) {

			setHaData(ha, air_humid_h_ed1, air_humid_h_ed2, air_humid_h_ed3,
					air_humid_h_ch1, air_humid_h_ch2, air_humid_h_ch3);

			setLaData(la, air_humid_l_ed1, air_humid_l_ed2, air_humid_l_ed3,
					air_humid_l_ch1, air_humid_l_ch2, air_humid_l_ch3);
		}
		// 土壤温度
		if (AlarmType.soil_temperature_config == type) {

			setHaData(ha, air_soil_temp_h_ed1, air_soil_temp_h_ed2,
					air_soil_temp_h_ed3, air_soil_temp_h_ch1,
					air_soil_temp_h_ch2, air_soil_temp_h_ch3);

			setLaData(la, air_soil_temp_l_ed1, air_soil_temp_l_ed2,
					air_soil_temp_l_ed3, air_soil_temp_l_ch1,
					air_soil_temp_l_ch2, air_soil_temp_l_ch3);
		}
		if (AlarmType.soil_humidity_config == type) {

			setHaData(ha, air_soil_humid_h_ed1, air_soil_humid_h_ed2,
					air_soil_humid_h_ed3, air_soil_humid_h_ch1,
					air_soil_humid_h_ch2, air_soil_humid_h_ch3);

			setLaData(la, air_soil_humid_l_ed1, air_soil_humid_l_ed2,
					air_soil_humid_l_ed3, air_soil_humid_l_ch1,
					air_soil_humid_l_ch2, air_soil_humid_l_ch3);
		}
		// 光照
		if (AlarmType.lux_config == type) {

			setHaData(ha, air_lux_h_ed1, air_lux_h_ed2, air_lux_h_ed3,
					air_lux_h_ch1, air_lux_h_ch2, air_lux_h_ch3);

			setLaData(la, air_lux_l_ed1, air_lux_l_ed2, air_lux_l_ed3,
					air_lux_l_ch1, air_lux_l_ch2, air_lux_l_ch3);
		}
		// co
		if (AlarmType.co_ppm_config == type) {

			setHaData(ha, air_co_h_ed1, air_co_h_ed2, air_co_h_ed3,
					air_co_h_ch1, air_co_h_ch2, air_co_h_ch3);

			setLaData(la, air_co_l_ed1, air_co_l_ed2, air_co_l_ed3,
					air_co_l_ch1, air_co_l_ch2, air_co_l_ch3);
		}
		// co2
		if (AlarmType.co2_ppm_config == type) {

			setHaData(ha, air_co2_h_ed1, air_co2_h_ed2, air_co2_h_ed3,
					air_co2_h_ch1, air_co2_h_ch2, air_co2_h_ch3);

			setLaData(la, air_co2_l_ed1, air_co2_l_ed2, air_co2_l_ed3,
					air_co2_l_ch1, air_co2_l_ch2, air_co2_l_ch3);
		}
		// 烟雾
		if (AlarmType.smoke_ppm_config == type) {

			setHaData(ha, air_smoke_h_ed1, air_smoke_h_ed2, air_smoke_h_ed3,
					air_smoke_h_ch1, air_smoke_h_ch2, air_smoke_h_ch3);

		}

	}

	private void setHaData(Alarm[] ha, EditText editText1, EditText editText2,
			EditText editText3, CheckBox checkBox1, CheckBox checkBox2,
			CheckBox checkBox3) {
		for (int j = 0; j < ha.length; j++) {
			if (0 == j) {
				if (ha[j].alarmTriggerVal != null
						&& !ha[j].alarmTriggerVal.equals("")) {
					editText1.setText(ha[j].alarmTriggerVal + "");
					checkBox1.setChecked(true);
				}
			}
			if (1 == j) {
				if (ha[j].alarmTriggerVal != null
						&& !ha[j].alarmTriggerVal.equals("")) {
					editText2.setText(ha[j].alarmTriggerVal + "");
					checkBox2.setChecked(true);
				}
			}
			if (2 == j) {
				if (ha[j].alarmTriggerVal != null
						&& !ha[j].alarmTriggerVal.equals("")) {
					editText3.setText(ha[j].alarmTriggerVal + "");
					checkBox3.setChecked(true);
				}
			}
		}
	}

	private void setLaData(Alarm[] la, EditText editText1, EditText editText2,
			EditText editText3, CheckBox checkBox1, CheckBox checkBox2,
			CheckBox checkBox3) {
		for (int j = 0; j < la.length; j++) {
			if (0 == j) {
				if (la[j].alarmTriggerVal != null
						&& !la[j].alarmTriggerVal.equals("")) {
					editText1.setText(la[j].alarmTriggerVal + "");
					checkBox1.setChecked(true);
				}
			}
			if (1 == j) {
				if (la[j].alarmTriggerVal != null
						&& !la[j].alarmTriggerVal.equals("")) {
					editText2.setText(la[j].alarmTriggerVal + "");
					checkBox2.setChecked(true);
				}
			}
			if (2 == j) {
				if (la[j].alarmTriggerVal != null
						&& !la[j].alarmTriggerVal.equals("")) {
					editText3.setText(la[j].alarmTriggerVal + "");
					checkBox3.setChecked(true);
				}
			}
		}
	}

	private AlarmPlanSettingBean getSettingAlarm() {
		AlarmPlanSettingBean alarmPlanSettingBean = new AlarmPlanSettingBean();
		
		// 电源
		String psa_et1 = getEditString(air_psa_ch1, air_psa_et1);
		String psa_et2 = getEditString(air_psa_ch2, air_psa_et2);
		String psa_et3 = getEditString(air_psa_ch3, air_psa_et3);
		// log.v("psa_et1-"+psa_et1+"psa_et2-"+psa_et2+"psa_et3-"+psa_et3);
		// 空气温度
		String temp_h_et1 = getEditString(air_temp_h_ch1, air_temp_h_ed1);
		String temp_h_et2 = getEditString(air_temp_h_ch2, air_temp_h_ed2);
		String temp_h_et3 = getEditString(air_temp_h_ch3, air_temp_h_ed3);
		// log.v("temp_h_et1-"+temp_h_et1+"temp_h_et2-"+temp_h_et2+"temp_h_et3-"+temp_h_et3);
		String temp_l_et1 = getEditString(air_temp_l_ch1, air_temp_l_ed1);
		String temp_l_et2 = getEditString(air_temp_l_ch2, air_temp_l_ed2);
		String temp_l_et3 = getEditString(air_temp_l_ch3, air_temp_l_ed3);
		// log.v("temp_l_et1-"+temp_l_et1+"temp_l_et2-"+temp_l_et2+"temp_l_et3-"+temp_l_et3);
		// 空气湿度
		String humid_h_et1 = getEditString(air_humid_h_ch1, air_humid_h_ed1);
		String humid_h_et2 = getEditString(air_humid_h_ch2, air_humid_h_ed2);
		String humid_h_et3 = getEditString(air_humid_h_ch3, air_humid_h_ed3);
		// log.v("humid_h_et1-"+humid_h_et1+"humid_h_et2-"+humid_h_et2+"humid_h_et3-"+humid_h_et3);
		String humid_l_et1 = getEditString(air_humid_l_ch1, air_humid_l_ed1);
		String humid_l_et2 = getEditString(air_humid_l_ch2, air_humid_l_ed2);
		String humid_l_et3 = getEditString(air_humid_l_ch3, air_humid_l_ed3);
		// log.v("humid_l_et1-"+humid_l_et1+"humid_l_et2-"+humid_l_et2+"humid_l_et3-"+humid_l_et3);
		// 土壤温度
		String soil_temp_h_et1 = getEditString(air_soil_temp_h_ch1,
				air_soil_temp_h_ed1);
		String soil_temp_h_et2 = getEditString(air_soil_temp_h_ch2,
				air_soil_temp_h_ed2);
		String soil_temp_h_et3 = getEditString(air_soil_temp_h_ch3,
				air_soil_temp_h_ed3);
		// log.v("soil_temp_h_et1-"+soil_temp_h_et1+"soil_temp_h_et2-"+soil_temp_h_et2+"soil_temp_h_et3-"+soil_temp_h_et3);
		String soil_temp_l_et1 = getEditString(air_soil_temp_l_ch1,
				air_soil_temp_l_ed1);
		String soil_temp_l_et2 = getEditString(air_soil_temp_l_ch2,
				air_soil_temp_l_ed2);
		String soil_temp_l_et3 = getEditString(air_soil_temp_l_ch3,
				air_soil_temp_l_ed3);
		// log.v("soil_temp_l_et1-"+soil_temp_l_et1+"soil_temp_l_et2-"+soil_temp_l_et2+"soil_temp_l_et3-"+soil_temp_l_et3);
		// 土壤湿度
		String soil_humid_h_et1 = getEditString(air_soil_humid_h_ch1,
				air_soil_humid_h_ed1);
		String soil_humid_h_et2 = getEditString(air_soil_humid_h_ch2,
				air_soil_humid_h_ed2);
		String soil_humid_h_et3 = getEditString(air_soil_humid_h_ch3,
				air_soil_humid_h_ed3);
		// log.v("soil_humid_h_et1-"+soil_humid_h_et1+"soil_humid_h_et2-"+soil_humid_h_et2+"soil_humid_h_et3-"+soil_humid_h_et3);
		String soil_humid_l_et1 = getEditString(air_soil_humid_l_ch1,
				air_soil_humid_l_ed1);
		String soil_humid_l_et2 = getEditString(air_soil_humid_l_ch2,
				air_soil_humid_l_ed2);
		String soil_humid_l_et3 = getEditString(air_soil_humid_l_ch3,
				air_soil_humid_l_ed3);
		// log.v("soil_humid_l_et1-"+soil_humid_l_et1+"soil_humid_l_et2-"+soil_humid_l_et2+"soil_humid_l_et3-"+soil_humid_l_et3);
		// 光照
		String lux_h_et1 = getEditString(air_lux_h_ch1, air_lux_h_ed1);
		String lux_h_et2 = getEditString(air_lux_h_ch2, air_lux_h_ed2);
		String lux_h_et3 = getEditString(air_lux_h_ch3, air_lux_h_ed3);
		// log.v("lux_h_et1-"+lux_h_et1+"lux_h_et2-"+lux_h_et2+"lux_h_et3-"+lux_h_et3);
		String lux_l_et1 = getEditString(air_lux_l_ch1, air_lux_l_ed1);
		String lux_l_et2 = getEditString(air_lux_l_ch2, air_lux_l_ed2);
		String lux_l_et3 = getEditString(air_lux_l_ch3, air_lux_l_ed3);
		// log.v("lux_l_et1-"+lux_l_et1+"lux_l_et2-"+lux_l_et2+"lux_l_et3-"+lux_l_et3);

		// co
		String co_h_et1 = getEditString(air_co_h_ch1, air_co_h_ed1);
		String co_h_et2 = getEditString(air_co_h_ch2, air_co_h_ed2);
		String co_h_et3 = getEditString(air_co_h_ch3, air_co_h_ed3);
		// log.v("co_h_et1-"+co_h_et1+"co_h_et2-"+co_h_et2+"co_h_et3-"+co_h_et3);
		String co_l_et1 = getEditString(air_co_l_ch1, air_co_l_ed1);
		String co_l_et2 = getEditString(air_co_l_ch2, air_co_l_ed2);
		String co_l_et3 = getEditString(air_co_l_ch3, air_co_l_ed3);
		// log.v("co_l_et1-"+co_l_et1+"co_l_et2-"+co_l_et2+"co_l_et3-"+co_l_et3);
		// co2
		String co2_h_et1 = getEditString(air_co2_h_ch1, air_co2_h_ed1);
		String co2_h_et2 = getEditString(air_co2_h_ch2, air_co2_h_ed2);
		String co2_h_et3 = getEditString(air_co2_h_ch3, air_co2_h_ed3);
		// log.v("co2_h_et1-"+co2_h_et1+"co2_h_et2-"+co2_h_et2+"co2_h_et3-"+co2_h_et3);
		String co2_l_et1 = getEditString(air_co2_l_ch1, air_co2_l_ed1);
		String co2_l_et2 = getEditString(air_co2_l_ch2, air_co2_l_ed2);
		String co2_l_et3 = getEditString(air_co2_l_ch3, air_co2_l_ed3);
		// log.v("co2_l_et1-"+co2_l_et1+"co2_l_et2-"+co2_l_et2+"co2_l_et3-"+co2_l_et3);

		// 烟雾
		String smoke_h_et1 = getEditString(air_smoke_h_ch1, air_smoke_h_ed1);
		String smoke_h_et2 = getEditString(air_smoke_h_ch2, air_smoke_h_ed2);
		String smoke_h_et3 = getEditString(air_smoke_h_ch3, air_smoke_h_ed3);
		// log.v("smoke_h_et1-"+smoke_h_et1+"smoke_h_et2-"+smoke_h_et2+"smoke_h_et3-"+smoke_h_et3);

		if (air_psa_ch1.isChecked() || air_psa_ch2.isChecked()
				|| air_psa_ch3.isChecked()) {

			ChargeConfigBean chargeConfigBean = new ChargeConfigBean();

			// 电源
			if (psa_et1 != null && psa_et1.length() > 0) {
					chargeConfigBean.setL_value_y(Integer.parseInt(psa_et1));
			}
			if (psa_et2 != null && psa_et2.length() > 0) {
					chargeConfigBean.setL_value_o(Integer.parseInt(psa_et2));
			}
			if (psa_et3 != null && psa_et3.length() > 0) {
					chargeConfigBean.setL_value_r(Integer.parseInt(psa_et3));
			}
			alarmPlanSettingBean.setCharge_config(chargeConfigBean);

		}

		// 空气温度
		if (air_temp_h_ch1.isChecked() || air_temp_h_ch2.isChecked()
				|| air_temp_h_ch3.isChecked() || air_temp_l_ch1.isChecked()
				|| air_temp_l_ch2.isChecked() || air_temp_l_ch3.isChecked()) {

			AirTemperatureConfigBean airTemperatureConfigBean = new AirTemperatureConfigBean();
			if (temp_h_et1 != null && temp_h_et1.length() > 0) {
				airTemperatureConfigBean.setH_value_y(Integer
						.parseInt(temp_h_et1));
			}
			if (temp_h_et2 != null && temp_h_et2.length() > 0) {
				airTemperatureConfigBean.setH_value_o(Integer
						.parseInt(temp_h_et2));
			}
			if (temp_h_et3 != null && temp_h_et3.length() > 0) {
				airTemperatureConfigBean.setH_value_r(Integer
						.parseInt(temp_h_et3));
			}
			if (temp_l_et1 != null && temp_l_et1.length() > 0) {
				airTemperatureConfigBean.setL_value_y(Integer
						.parseInt(temp_l_et1));
			}
			if (temp_l_et2 != null && temp_l_et2.length() > 0) {
				airTemperatureConfigBean.setL_value_o(Integer
						.parseInt(temp_l_et2));
			}
			if (temp_l_et3 != null && temp_l_et3.length() > 0) {
				airTemperatureConfigBean.setL_value_r(Integer
						.parseInt(temp_l_et3));
			}
			alarmPlanSettingBean
					.setAir_temperature_config(airTemperatureConfigBean);
		}
		// 空气湿度
		if (air_humid_h_ch1.isChecked() || air_humid_h_ch2.isChecked()
				|| air_humid_h_ch3.isChecked() || air_humid_l_ch1.isChecked()
				|| air_humid_l_ch2.isChecked() || air_humid_l_ch3.isChecked()) {

			AirHumidityConfigBean airHumidityConfigBean = new AirHumidityConfigBean();
			if (humid_h_et1 != null && humid_h_et1.length() > 0) {
				airHumidityConfigBean.setH_value_y(Integer
						.parseInt(humid_h_et1));
			}
			if (humid_h_et2 != null && humid_h_et2.length() > 0) {
				airHumidityConfigBean.setH_value_o(Integer
						.parseInt(humid_h_et2));
			}
			if (humid_h_et3 != null && humid_h_et3.length() > 0) {
				airHumidityConfigBean.setH_value_r(Integer
						.parseInt(humid_h_et3));
			}

			if (humid_l_et1 != null && humid_l_et1.length() > 0) {
				airHumidityConfigBean.setL_value_y(Integer
						.parseInt(humid_l_et1));
			}
			if (humid_l_et2 != null && humid_l_et2.length() > 0) {
				airHumidityConfigBean.setL_value_o(Integer
						.parseInt(humid_l_et2));
			}
			if (humid_l_et3 != null && humid_l_et3.length() > 0) {
				airHumidityConfigBean.setL_value_r(Integer
						.parseInt(humid_l_et3));
			}
			alarmPlanSettingBean.setAir_humidity_config(airHumidityConfigBean);
		}
		// 土壤温度
		if (air_soil_temp_h_ch1.isChecked() || air_soil_temp_h_ch2.isChecked()
				|| air_soil_temp_h_ch3.isChecked()
				|| air_soil_temp_l_ch1.isChecked()
				|| air_soil_temp_l_ch2.isChecked()
				|| air_soil_temp_l_ch3.isChecked()) {

			SoilTemperatureConfigBean soilTemperatureConfigBean = new SoilTemperatureConfigBean();
			if (soil_temp_h_et1 != null && soil_temp_h_et1.length() > 0) {
				soilTemperatureConfigBean.setH_value_y(Integer
						.parseInt(soil_temp_h_et1));
			}
			if (soil_temp_h_et2 != null && soil_temp_h_et2.length() > 0) {
				soilTemperatureConfigBean.setH_value_o(Integer
						.parseInt(soil_temp_h_et2));
			}
			if (soil_temp_h_et3 != null && soil_temp_h_et3.length() > 0) {
				soilTemperatureConfigBean.setH_value_r(Integer
						.parseInt(soil_temp_h_et3));
			}

			if (soil_temp_l_et1 != null && soil_temp_l_et1.length() > 0) {
				soilTemperatureConfigBean.setL_value_y(Integer
						.parseInt(soil_temp_l_et1));
			}
			if (soil_temp_l_et2 != null && soil_temp_l_et2.length() > 0) {
				soilTemperatureConfigBean.setL_value_o(Integer
						.parseInt(soil_temp_l_et2));
			}
			if (soil_temp_l_et3 != null && soil_temp_l_et3.length() > 0) {
				soilTemperatureConfigBean.setL_value_r(Integer
						.parseInt(soil_temp_l_et3));
			}
			alarmPlanSettingBean
					.setSoil_temperature_config(soilTemperatureConfigBean);
		}
		// 土壤湿度
		if (air_soil_humid_h_ch1.isChecked()
				|| air_soil_humid_h_ch2.isChecked()
				|| air_soil_humid_h_ch3.isChecked()
				|| air_soil_humid_l_ch1.isChecked()
				|| air_soil_humid_l_ch2.isChecked()
				|| air_soil_humid_l_ch3.isChecked()) {

			SoilHumidityConfigBean soilHumidityConfigBean = new SoilHumidityConfigBean();
			if (soil_humid_h_et1 != null && soil_humid_h_et1.length() > 0) {
				soilHumidityConfigBean.setH_value_y(Integer
						.parseInt(soil_humid_h_et1));
			}
			if (soil_humid_h_et2 != null && soil_humid_h_et2.length() > 0) {
				soilHumidityConfigBean.setH_value_o(Integer
						.parseInt(soil_humid_h_et2));
			}
			if (soil_humid_h_et3 != null && soil_humid_h_et3.length() > 0) {
				soilHumidityConfigBean.setH_value_r(Integer
						.parseInt(soil_humid_h_et3));
			}

			if (soil_humid_l_et1 != null && soil_humid_l_et1.length() > 0) {
				soilHumidityConfigBean.setL_value_y(Integer
						.parseInt(soil_humid_l_et1));
			}
			if (soil_humid_l_et2 != null && soil_humid_l_et2.length() > 0) {
				soilHumidityConfigBean.setL_value_o(Integer
						.parseInt(soil_humid_l_et2));
			}
			if (soil_humid_l_et3 != null && soil_humid_l_et3.length() > 0) {
				soilHumidityConfigBean.setL_value_r(Integer
						.parseInt(soil_humid_l_et3));
			}
			alarmPlanSettingBean
					.setSoil_humidity_config(soilHumidityConfigBean);
		}
		// 光照
		if (air_lux_h_ch1.isChecked() || air_lux_h_ch2.isChecked()
				|| air_lux_h_ch3.isChecked() || air_lux_l_ch1.isChecked()
				|| air_lux_l_ch2.isChecked() || air_lux_l_ch3.isChecked()) {

			LuxConfigBean luxConfigBean = new LuxConfigBean();
			if (lux_h_et1 != null && lux_h_et1.length() > 0) {
				luxConfigBean.setH_value_y(Integer.parseInt(lux_h_et1));
			}
			if (lux_h_et2 != null && lux_h_et2.length() > 0) {
				luxConfigBean.setH_value_o(Integer.parseInt(lux_h_et2));
			}
			if (lux_h_et3 != null && lux_h_et3.length() > 0) {
				luxConfigBean.setH_value_r(Integer.parseInt(lux_h_et3));
			}

			if (lux_l_et1 != null && lux_l_et1.length() > 0) {
				luxConfigBean.setL_value_y(Integer.parseInt(lux_l_et1));
			}
			if (lux_l_et2 != null && lux_l_et2.length() > 0) {
				luxConfigBean.setL_value_o(Integer.parseInt(lux_l_et2));
			}
			if (lux_l_et3 != null && lux_l_et3.length() > 0) {
				luxConfigBean.setL_value_r(Integer.parseInt(lux_l_et3));
			}
			alarmPlanSettingBean.setLux_config(luxConfigBean);
		}
		// co
		if (air_co_h_ch1.isChecked() || air_co_h_ch2.isChecked()
				|| air_co_h_ch3.isChecked() || air_co_l_ch1.isChecked()
				|| air_co_l_ch2.isChecked() || air_co_l_ch3.isChecked()) {

			CoPpmConfigBean coPpmConfigBean = new CoPpmConfigBean();
			if (co_h_et1 != null && co_h_et1.length() > 0) {
				coPpmConfigBean.setH_value_y(Integer.parseInt(co_h_et1));
			}
			if (co_h_et2 != null && co_h_et2.length() > 0) {
				coPpmConfigBean.setH_value_o(Integer.parseInt(co_h_et2));
			}
			if (co_h_et3 != null && co_h_et3.length() > 0) {
				coPpmConfigBean.setH_value_r(Integer.parseInt(co_h_et3));
			}

			if (co_l_et1 != null && co_l_et1.length() > 0) {
				coPpmConfigBean.setL_value_y(Integer.parseInt(co_l_et1));
			}
			if (co_l_et2 != null && co_l_et2.length() > 0) {
				coPpmConfigBean.setL_value_o(Integer.parseInt(co_l_et2));
			}
			if (co_l_et3 != null && co_l_et3.length() > 0) {
				coPpmConfigBean.setL_value_r(Integer.parseInt(co_l_et3));
			}
			alarmPlanSettingBean.setCo_ppm_config(coPpmConfigBean);
		}
		// co2
		if (air_co2_h_ch1.isChecked() || air_co2_h_ch2.isChecked()
				|| air_co2_h_ch3.isChecked() || air_co2_l_ch1.isChecked()
				|| air_co2_l_ch2.isChecked() || air_co2_l_ch3.isChecked()) {

			Co2PpmConfigBean co2PpmConfigBean = new Co2PpmConfigBean();
			if (co2_h_et1 != null && co2_h_et1.length() > 0) {
				co2PpmConfigBean.setH_value_y(Integer.parseInt(co2_h_et1));
			}
			if (co2_h_et2 != null && co2_h_et2.length() > 0) {
				co2PpmConfigBean.setH_value_o(Integer.parseInt(co2_h_et2));
			}
			if (co2_h_et3 != null && co2_h_et3.length() > 0) {
				co2PpmConfigBean.setH_value_r(Integer.parseInt(co2_h_et3));
			}

			if (co2_l_et1 != null && co2_l_et1.length() > 0) {
				co2PpmConfigBean.setL_value_y(Integer.parseInt(co2_l_et1));
			}
			if (co2_l_et2 != null && co2_l_et2.length() > 0) {
				co2PpmConfigBean.setL_value_o(Integer.parseInt(co2_l_et2));
			}
			if (co2_l_et3 != null && co2_l_et3.length() > 0) {
				co2PpmConfigBean.setL_value_r(Integer.parseInt(co2_l_et3));
			}
			alarmPlanSettingBean.setCo2_ppm_config(co2PpmConfigBean);
		}
		// 烟雾
		if (air_smoke_h_ch1.isChecked() || air_smoke_h_ch2.isChecked()
				|| air_smoke_h_ch3.isChecked()) {

			SmokePpmConfigBean smokePpmConfigBean = new SmokePpmConfigBean();
			if (smoke_h_et1 != null && smoke_h_et1.length() > 0) {
				smokePpmConfigBean.setH_value_y(Integer.parseInt(smoke_h_et1));
			}
			if (smoke_h_et2 != null && smoke_h_et2.length() > 0) {
				smokePpmConfigBean.setH_value_o(Integer.parseInt(smoke_h_et2));
			}
			if (smoke_h_et3 != null && smoke_h_et3.length() > 0) {
				smokePpmConfigBean.setH_value_r(Integer.parseInt(smoke_h_et3));
			}
			alarmPlanSettingBean.setSmoke_ppm_config(smokePpmConfigBean);
		}

		alarmPlanSettingBean.setStrategy_name(mTitelEditText.getText()
				.toString().trim());
		return alarmPlanSettingBean;

	}

	private String getEditString(CheckBox checkBox, EditText editText) {
		// log.v(checkBox.isChecked()+"");
		if (checkBox.isChecked()) {
			if (editText.getText().toString() != null
					&& editText.getText().toString().length() > 0) {
				return editText.getText().toString();
			}
		}
		return null;

	}

	private void initEditCheckedState() {
		// 电量
		setCheckedlistenerEdit(air_psa_ch1, air_psa_et1);
		setCheckedlistenerEdit(air_psa_ch2, air_psa_et2);
		setCheckedlistenerEdit(air_psa_ch3, air_psa_et3);
		// 温度
		setCheckedlistenerEdit(air_temp_h_ch1, air_temp_h_ed1);
		setCheckedlistenerEdit(air_temp_h_ch2, air_temp_h_ed2);
		setCheckedlistenerEdit(air_temp_h_ch3, air_temp_h_ed3);

		setCheckedlistenerEdit(air_temp_l_ch1, air_temp_l_ed1);
		setCheckedlistenerEdit(air_temp_l_ch2, air_temp_l_ed2);
		setCheckedlistenerEdit(air_temp_l_ch3, air_temp_l_ed3);
		// 湿度
		setCheckedlistenerEdit(air_humid_h_ch1, air_humid_h_ed1);
		setCheckedlistenerEdit(air_humid_h_ch2, air_humid_h_ed2);
		setCheckedlistenerEdit(air_humid_h_ch3, air_humid_h_ed3);

		setCheckedlistenerEdit(air_humid_l_ch1, air_humid_l_ed1);
		setCheckedlistenerEdit(air_humid_l_ch2, air_humid_l_ed2);
		setCheckedlistenerEdit(air_humid_l_ch3, air_humid_l_ed3);
		// 土壤温度
		setCheckedlistenerEdit(air_soil_temp_h_ch1, air_soil_temp_h_ed1);
		setCheckedlistenerEdit(air_soil_temp_h_ch2, air_soil_temp_h_ed2);
		setCheckedlistenerEdit(air_soil_temp_h_ch3, air_soil_temp_h_ed3);

		setCheckedlistenerEdit(air_soil_temp_l_ch1, air_soil_temp_l_ed1);
		setCheckedlistenerEdit(air_soil_temp_l_ch2, air_soil_temp_l_ed2);
		setCheckedlistenerEdit(air_soil_temp_l_ch3, air_soil_temp_l_ed3);
		// 土壤湿度
		setCheckedlistenerEdit(air_soil_humid_h_ch1, air_soil_humid_h_ed1);
		setCheckedlistenerEdit(air_soil_humid_h_ch2, air_soil_humid_h_ed2);
		setCheckedlistenerEdit(air_soil_humid_h_ch3, air_soil_humid_h_ed3);

		setCheckedlistenerEdit(air_soil_humid_l_ch1, air_soil_humid_l_ed1);
		setCheckedlistenerEdit(air_soil_humid_l_ch2, air_soil_humid_l_ed2);
		setCheckedlistenerEdit(air_soil_humid_l_ch3, air_soil_humid_l_ed3);
		// 光照
		setCheckedlistenerEdit(air_lux_h_ch1, air_lux_h_ed1);
		setCheckedlistenerEdit(air_lux_h_ch2, air_lux_h_ed2);
		setCheckedlistenerEdit(air_lux_h_ch3, air_lux_h_ed3);

		setCheckedlistenerEdit(air_lux_l_ch1, air_lux_l_ed1);
		setCheckedlistenerEdit(air_lux_l_ch2, air_lux_l_ed2);
		setCheckedlistenerEdit(air_lux_l_ch3, air_lux_l_ed3);
		// co
		setCheckedlistenerEdit(air_co_h_ch1, air_co_h_ed1);
		setCheckedlistenerEdit(air_co_h_ch2, air_co_h_ed2);
		setCheckedlistenerEdit(air_co_h_ch3, air_co_h_ed3);

		setCheckedlistenerEdit(air_co_l_ch1, air_co_l_ed1);
		setCheckedlistenerEdit(air_co_l_ch2, air_co_l_ed2);
		setCheckedlistenerEdit(air_co_l_ch3, air_co_l_ed3);
		// co2
		setCheckedlistenerEdit(air_co2_h_ch1, air_co2_h_ed1);
		setCheckedlistenerEdit(air_co2_h_ch2, air_co2_h_ed2);
		setCheckedlistenerEdit(air_co2_h_ch3, air_co2_h_ed3);

		setCheckedlistenerEdit(air_co2_l_ch1, air_co2_l_ed1);
		setCheckedlistenerEdit(air_co2_l_ch2, air_co2_l_ed2);
		setCheckedlistenerEdit(air_co2_l_ch3, air_co2_l_ed3);
		// 烟雾
		setCheckedlistenerEdit(air_smoke_h_ch1, air_smoke_h_ed1);
		setCheckedlistenerEdit(air_smoke_h_ch2, air_smoke_h_ed2);
		setCheckedlistenerEdit(air_smoke_h_ch3, air_smoke_h_ed3);
	}

	private void setCheckedlistenerEdit(CheckBox air_psa_ch,
			final EditText editText) {
		editText.setEnabled(false);
		air_psa_ch
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							editText.setEnabled(true);
						} else {
							editText.setEnabled(false);
						}
					}
				});

	}

	private void editInputLimit() {
		setEditTextlistener_l(air_psa_et1, air_psa_et2, air_psa_et3);// air_temp_h_ed2
		// 空气我温度
		setEditTextlistener_h(air_temp_h_ed1, air_temp_h_ed2, air_temp_h_ed3);
		setEditTextlistener_l(air_temp_l_ed1, air_temp_l_ed2, air_temp_l_ed3);
		// 空气湿度
		setEditTextlistener_h(air_humid_h_ed1, air_humid_h_ed2, air_humid_h_ed3);
		setEditTextlistener_l(air_humid_l_ed1, air_humid_l_ed2, air_humid_l_ed3);
		// 土壤温度
		setEditTextlistener_h(air_soil_temp_h_ed1, air_soil_temp_h_ed2,
				air_soil_temp_h_ed3);
		setEditTextlistener_l(air_soil_temp_l_ed1, air_soil_temp_l_ed2,
				air_soil_temp_l_ed3);
		// 土壤湿度
		setEditTextlistener_h(air_soil_humid_h_ed1, air_soil_humid_h_ed2,
				air_soil_humid_h_ed3);
		setEditTextlistener_l(air_soil_humid_l_ed1, air_soil_humid_l_ed2,
				air_soil_humid_l_ed3);
		// 光照
		setEditTextlistener_h(air_lux_h_ed1, air_lux_h_ed2, air_lux_h_ed3);
		setEditTextlistener_l(air_lux_l_ed1, air_lux_l_ed2, air_lux_l_ed3);
		// co
		setEditTextlistener_h(air_co_h_ed1, air_co_h_ed2, air_co_h_ed3);
		setEditTextlistener_l(air_co_l_ed1, air_co_l_ed2, air_co_l_ed3);
		// co2
		setEditTextlistener_h(air_co2_h_ed1, air_co2_h_ed2, air_co2_h_ed3);
		setEditTextlistener_l(air_co2_l_ed1, air_co2_l_ed2, air_co2_l_ed3);
		// 烟雾
		setEditTextlistener_h(air_smoke_h_ed1, air_smoke_h_ed2, air_smoke_h_ed3);
	}

	private void setEditTextlistener_l(final EditText editText1,
			final EditText editText2, final EditText editText3) {
		editText2.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// log.v("onTextChanged");
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// log.v("beforeTextChanged");
			}

			@Override
			public void afterTextChanged(Editable arg0) {
				editText2
						.setOnFocusChangeListener(new View.OnFocusChangeListener() {

							@Override
							public void onFocusChange(View v, boolean hasFocus) {
								if (hasFocus) {// 获得焦点

								} else {// 失去焦点

									String edittext1 = editText1.getText()
											.toString();
									String edittext2 = editText2.getText()
											.toString();
									if (edittext1 != null && edittext2 != null
											&& !edittext1.equals("")
											&& !edittext2.equals("")) {
										int parseIntstring = Integer
												.parseInt(edittext1);
										int parseIntstring2 = Integer
												.parseInt(edittext2);
										if (parseIntstring2 >= parseIntstring) {
											// Toast.makeText(mContext,
											// "当前值应小于上一级别,请从新输入",
											// Toast.LENGTH_SHORT).show();
											localBuilder = new AlertDialog.Builder(
													mContext).create();
											localBuilder.show();
											View fragmentSetshedDeldialog = LayoutInflater
													.from(mContext)
													.inflate(
															R.layout.fragment_setshed_deldialog,
															null);
											localBuilder
													.addContentView(
															fragmentSetshedDeldialog,
															new ViewGroup.LayoutParams(
																	(int) (mContext
																			.getResources()
																			.getDisplayMetrics().widthPixels * 0.9),
																	ViewGroup.LayoutParams.WRAP_CONTENT));
											localBuilder
													.setCanceledOnTouchOutside(true);
											Button btnEnsure = (Button) fragmentSetshedDeldialog
													.findViewById(R.id.btn_ensure);
											Button btnCancel = (Button) fragmentSetshedDeldialog
													.findViewById(R.id.btn_cancel);
											TextView tv_deldialog_content = (TextView) fragmentSetshedDeldialog
													.findViewById(R.id.tv_deldialog_content);
											tv_deldialog_content
													.setText("文本输入,当前值应小于上一级别,请从新输入!");
											// 确认
											btnEnsure
													.setOnClickListener(new OnClickListener() {

														@Override
														public void onClick(
																View arg0) {
															editText2
																	.setText("");
															localBuilder
																	.dismiss();
														}
													});
											// 取消
											btnCancel
													.setOnClickListener(new OnClickListener() {

														@Override
														public void onClick(
																View arg0) {
															localBuilder
																	.dismiss();
														}
													});
										}
									}
								}
							}
						});

			}
		});
		editText3.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {
				editText3
						.setOnFocusChangeListener(new View.OnFocusChangeListener() {

							@Override
							public void onFocusChange(View v, boolean hasFocus) {
								if (hasFocus) {// 获得焦点

								} else {// 失去焦点
									String edittext1 = editText1.getText()
											.toString();
									String edittext2 = editText2.getText()
											.toString();
									String edittext3 = editText3.getText()
											.toString();
									if (edittext2 != null && edittext2 != null
											&& edittext3 != null
											&& !edittext1.equals("")
											&& !edittext2.equals("")
											&& !edittext3.equals("")) {
										int parseIntstring1 = Integer
												.parseInt(edittext1);
										int parseIntstring2 = Integer
												.parseInt(edittext2);
										int parseIntstring3 = Integer
												.parseInt(edittext3);
										if (parseIntstring3 >= parseIntstring2
												|| parseIntstring3 >= parseIntstring1) {
											// Toast.makeText(mContext,
											// "当前值应小于上一级别,请从新输入",
											// Toast.LENGTH_SHORT).show();
											localBuilder = new AlertDialog.Builder(
													mContext).create();
											localBuilder.show();
											View fragmentSetshedDeldialog = LayoutInflater
													.from(mContext)
													.inflate(
															R.layout.fragment_setshed_deldialog,
															null);
											localBuilder
													.addContentView(
															fragmentSetshedDeldialog,
															new ViewGroup.LayoutParams(
																	(int) (mContext
																			.getResources()
																			.getDisplayMetrics().widthPixels * 0.9),
																	ViewGroup.LayoutParams.WRAP_CONTENT));
											localBuilder
													.setCanceledOnTouchOutside(true);
											Button btnEnsure = (Button) fragmentSetshedDeldialog
													.findViewById(R.id.btn_ensure);
											Button btnCancel = (Button) fragmentSetshedDeldialog
													.findViewById(R.id.btn_cancel);
											TextView tv_deldialog_content = (TextView) fragmentSetshedDeldialog
													.findViewById(R.id.tv_deldialog_content);
											tv_deldialog_content
													.setText("文本输入错误,当前值应小于上一级别,请从新输入!");
											// 确认
											btnEnsure
													.setOnClickListener(new OnClickListener() {

														@Override
														public void onClick(
																View arg0) {
															editText3
																	.setText("");
															localBuilder
																	.dismiss();
														}
													});
											// 取消
											btnCancel
													.setOnClickListener(new OnClickListener() {

														@Override
														public void onClick(
																View arg0) {
															localBuilder
																	.dismiss();
														}
													});
										}
									}
								}
							}
						});

			}
		});
	}

	private void setEditTextlistener_h(final EditText editText1,
			final EditText editText2, final EditText editText3) {
		editText2.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// log.v("onTextChanged");
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// log.v("beforeTextChanged");
			}

			@Override
			public void afterTextChanged(Editable arg0) {
				editText2
						.setOnFocusChangeListener(new View.OnFocusChangeListener() {

							@Override
							public void onFocusChange(View v, boolean hasFocus) {
								if (hasFocus) {// 获得焦点

								} else {// 失去焦点
									String edittext1 = editText1.getText()
											.toString();
									String edittext2 = editText2.getText()
											.toString();
									if (edittext1 != null && edittext2 != null
											&& !edittext1.equals("")
											&& !edittext2.equals("")) {
										int parseIntstring = Integer
												.parseInt(edittext1);
										int parseIntstring2 = Integer
												.parseInt(edittext2);
										if (parseIntstring2 <= parseIntstring) {
											// Toast.makeText(mContext,
											// "当前值应大于上一级别,请从新输入",
											// Toast.LENGTH_SHORT).show();
											localBuilder = new AlertDialog.Builder(
													mContext).create();
											localBuilder.show();
											View fragmentSetshedDeldialog = LayoutInflater
													.from(mContext)
													.inflate(
															R.layout.fragment_setshed_deldialog,
															null);
											localBuilder
													.addContentView(
															fragmentSetshedDeldialog,
															new ViewGroup.LayoutParams(
																	(int) (mContext
																			.getResources()
																			.getDisplayMetrics().widthPixels * 0.9),
																	ViewGroup.LayoutParams.WRAP_CONTENT));
											localBuilder
													.setCanceledOnTouchOutside(true);
											Button btnEnsure = (Button) fragmentSetshedDeldialog
													.findViewById(R.id.btn_ensure);
											Button btnCancel = (Button) fragmentSetshedDeldialog
													.findViewById(R.id.btn_cancel);
											TextView tv_deldialog_content = (TextView) fragmentSetshedDeldialog
													.findViewById(R.id.tv_deldialog_content);
											tv_deldialog_content
													.setText("文本输入错误,当前值应大于上一级别,请从新输入!");
											// 确认
											btnEnsure
													.setOnClickListener(new OnClickListener() {

														@Override
														public void onClick(
																View arg0) {
															editText2
																	.setText("");
															localBuilder
																	.dismiss();
														}
													});
											// 取消
											btnCancel
													.setOnClickListener(new OnClickListener() {

														@Override
														public void onClick(
																View arg0) {
															localBuilder
																	.dismiss();
														}
													});
										}
									}
								}
							}
						});

			}
		});
		editText3.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable arg0) {

				editText3
						.setOnFocusChangeListener(new View.OnFocusChangeListener() {

							@Override
							public void onFocusChange(View v, boolean hasFocus) {
								if (hasFocus) {// 获得焦点

								} else {// 失去焦点
									String edittext1 = editText1.getText()
											.toString();
									String edittext2 = editText2.getText()
											.toString();
									String edittext3 = editText3.getText()
											.toString();
									// log.v("333333333333333333333");
									if (edittext1 != null && edittext2 != null
											&& edittext3 != null
											&& !edittext1.equals("")
											&& !edittext2.equals("")
											&& !edittext3.equals("")) {
										int parseIntstring1 = Integer
												.parseInt(edittext1);
										int parseIntstring2 = Integer
												.parseInt(edittext2);
										int parseIntstring3 = Integer
												.parseInt(edittext3);
										if (parseIntstring3 <= parseIntstring2
												|| parseIntstring3 <= parseIntstring1) {
											// Toast.makeText(mContext,
											// "当前值应大于上一级别,请从新输入",
											// Toast.LENGTH_SHORT).show();
											localBuilder = new AlertDialog.Builder(
													mContext).create();
											localBuilder.show();
											View fragmentSetshedDeldialog = LayoutInflater
													.from(mContext)
													.inflate(
															R.layout.fragment_setshed_deldialog,
															null);
											localBuilder
													.addContentView(
															fragmentSetshedDeldialog,
															new ViewGroup.LayoutParams(
																	(int) (mContext
																			.getResources()
																			.getDisplayMetrics().widthPixels * 0.9),
																	ViewGroup.LayoutParams.WRAP_CONTENT));
											localBuilder
													.setCanceledOnTouchOutside(true);
											Button btnEnsure = (Button) fragmentSetshedDeldialog
													.findViewById(R.id.btn_ensure);
											Button btnCancel = (Button) fragmentSetshedDeldialog
													.findViewById(R.id.btn_cancel);
											TextView tv_deldialog_content = (TextView) fragmentSetshedDeldialog
													.findViewById(R.id.tv_deldialog_content);
											tv_deldialog_content
													.setText("文本输入错误,当前值应大于上一级别,请从新输入!");
											// 确认
											btnEnsure
													.setOnClickListener(new OnClickListener() {

														@Override
														public void onClick(
																View arg0) {
															editText3
																	.setText("");
															localBuilder
																	.dismiss();
														}
													});
											// 取消
											btnCancel
													.setOnClickListener(new OnClickListener() {

														@Override
														public void onClick(
																View arg0) {
															localBuilder
																	.dismiss();
														}
													});
										}
									}
								}
							}
						});
			}
		});
	}

	private void initViews() {
		btnPlanBack = (ImageView) findViewById(R.id.btn_plan_back);
		mTitelEditText = (EditText) findViewById(R.id.Strategy_plan_name);
		btnPlanBack.setOnClickListener(this);
		btnPlanSubmit = (ImageView) findViewById(R.id.btn_plan_submit);
		btnPlanSubmit.setOnClickListener(this);
		setEditTextInhibitInputSpace(mTitelEditText);
		setEditTextInhibitInputSpeChat(mTitelEditText);
		initPower();
		initTemperature();
		initHumidity();
		initSoilTemperature();
		initSoilHumidity();
		initLux();
		initCo();
		initCo2();
		initSmoke();
	}

	private void initSmoke() {
		air_smoke_h_ch1 = (CheckBox) findViewById(R.id.air_smoke_h_ch1);
		air_smoke_h_ch2 = (CheckBox) findViewById(R.id.air_smoke_h_ch2);
		air_smoke_h_ch3 = (CheckBox) findViewById(R.id.air_smoke_h_ch3);

		air_smoke_h_ed1 = (EditText) findViewById(R.id.air_smoke_h_ed1);
		air_smoke_h_ed2 = (EditText) findViewById(R.id.air_smoke_h_ed2);
		air_smoke_h_ed3 = (EditText) findViewById(R.id.air_smoke_h_ed3);
	}

	private void initCo2() {
		air_co2_h_ch1 = (CheckBox) findViewById(R.id.air_co2_h_ch1);
		air_co2_h_ch2 = (CheckBox) findViewById(R.id.air_co2_h_ch2);
		air_co2_h_ch3 = (CheckBox) findViewById(R.id.air_co2_h_ch3);

		air_co2_h_ed1 = (EditText) findViewById(R.id.air_co2_h_ed1);
		air_co2_h_ed2 = (EditText) findViewById(R.id.air_co2_h_ed2);
		air_co2_h_ed3 = (EditText) findViewById(R.id.air_co2_h_ed3);

		air_co2_l_ch1 = (CheckBox) findViewById(R.id.air_co2_l_ch1);
		air_co2_l_ch2 = (CheckBox) findViewById(R.id.air_co2_l_ch2);
		air_co2_l_ch3 = (CheckBox) findViewById(R.id.air_co2_l_ch3);

		air_co2_l_ed1 = (EditText) findViewById(R.id.air_co2_l_ed1);
		air_co2_l_ed2 = (EditText) findViewById(R.id.air_co2_l_ed2);
		air_co2_l_ed3 = (EditText) findViewById(R.id.air_co2_l_ed3);

	}

	// co
	private void initCo() {
		air_co_h_ch1 = (CheckBox) findViewById(R.id.air_co_h_ch1);
		air_co_h_ch2 = (CheckBox) findViewById(R.id.air_co_h_ch2);
		air_co_h_ch3 = (CheckBox) findViewById(R.id.air_co_h_ch3);

		air_co_h_ed1 = (EditText) findViewById(R.id.air_co_h_ed1);
		air_co_h_ed2 = (EditText) findViewById(R.id.air_co_h_ed2);
		air_co_h_ed3 = (EditText) findViewById(R.id.air_co_h_ed3);

		air_co_l_ch1 = (CheckBox) findViewById(R.id.air_co_l_ch1);
		air_co_l_ch2 = (CheckBox) findViewById(R.id.air_co_l_ch2);
		air_co_l_ch3 = (CheckBox) findViewById(R.id.air_co_l_ch3);

		air_co_l_ed1 = (EditText) findViewById(R.id.air_co_l_ed1);
		air_co_l_ed2 = (EditText) findViewById(R.id.air_co_l_ed2);
		air_co_l_ed3 = (EditText) findViewById(R.id.air_co_l_ed3);

	}

	// 光照
	private void initLux() {
		air_lux_h_ch1 = (CheckBox) findViewById(R.id.air_lux_h_ch1);
		air_lux_h_ch2 = (CheckBox) findViewById(R.id.air_lux_h_ch2);
		air_lux_h_ch3 = (CheckBox) findViewById(R.id.air_lux_h_ch3);

		air_lux_h_ed1 = (EditText) findViewById(R.id.air_lux_h_ed1);
		air_lux_h_ed2 = (EditText) findViewById(R.id.air_lux_h_ed2);
		air_lux_h_ed3 = (EditText) findViewById(R.id.air_lux_h_ed3);

		air_lux_l_ch1 = (CheckBox) findViewById(R.id.air_lux_l_ch1);
		air_lux_l_ch2 = (CheckBox) findViewById(R.id.air_lux_l_ch2);
		air_lux_l_ch3 = (CheckBox) findViewById(R.id.air_lux_l_ch3);

		air_lux_l_ed1 = (EditText) findViewById(R.id.air_lux_l_ed1);
		air_lux_l_ed2 = (EditText) findViewById(R.id.air_lux_l_ed2);
		air_lux_l_ed3 = (EditText) findViewById(R.id.air_lux_l_ed3);

	}

	// 土壤湿度
	private void initSoilHumidity() {
		air_soil_humid_h_ch1 = (CheckBox) findViewById(R.id.air_soil_humid_h_ch1);
		air_soil_humid_h_ch2 = (CheckBox) findViewById(R.id.air_soil_humid_h_ch2);
		air_soil_humid_h_ch3 = (CheckBox) findViewById(R.id.air_soil_humid_h_ch3);

		air_soil_humid_h_ed1 = (EditText) findViewById(R.id.air_soil_humid_h_ed1);
		air_soil_humid_h_ed2 = (EditText) findViewById(R.id.air_soil_humid_h_ed2);
		air_soil_humid_h_ed3 = (EditText) findViewById(R.id.air_soil_humid_h_ed3);

		air_soil_humid_l_ch1 = (CheckBox) findViewById(R.id.air_soil_humid_l_ch1);
		air_soil_humid_l_ch2 = (CheckBox) findViewById(R.id.air_soil_humid_l_ch2);
		air_soil_humid_l_ch3 = (CheckBox) findViewById(R.id.air_soil_humid_l_ch3);

		air_soil_humid_l_ed1 = (EditText) findViewById(R.id.air_soil_humid_l_ed1);
		air_soil_humid_l_ed2 = (EditText) findViewById(R.id.air_soil_humid_l_ed2);
		air_soil_humid_l_ed3 = (EditText) findViewById(R.id.air_soil_humid_l_ed3);

	}

	// 土壤温度
	private void initSoilTemperature() {
		air_soil_temp_h_ch1 = (CheckBox) findViewById(R.id.air_soil_temp_h_ch1);
		air_soil_temp_h_ch2 = (CheckBox) findViewById(R.id.air_soil_temp_h_ch2);
		air_soil_temp_h_ch3 = (CheckBox) findViewById(R.id.air_soil_temp_h_ch3);

		air_soil_temp_h_ed1 = (EditText) findViewById(R.id.air_soil_temp_h_ed1);
		air_soil_temp_h_ed2 = (EditText) findViewById(R.id.air_soil_temp_h_ed2);
		air_soil_temp_h_ed3 = (EditText) findViewById(R.id.air_soil_temp_h_ed3);

		air_soil_temp_l_ch1 = (CheckBox) findViewById(R.id.air_soil_temp_l_ch1);
		air_soil_temp_l_ch2 = (CheckBox) findViewById(R.id.air_soil_temp_l_ch2);
		air_soil_temp_l_ch3 = (CheckBox) findViewById(R.id.air_soil_temp_l_ch3);

		air_soil_temp_l_ed1 = (EditText) findViewById(R.id.air_soil_temp_l_ed1);
		air_soil_temp_l_ed2 = (EditText) findViewById(R.id.air_soil_temp_l_ed2);
		air_soil_temp_l_ed3 = (EditText) findViewById(R.id.air_soil_temp_l_ed3);

	}

	// 空气湿度
	private void initHumidity() {
		air_humid_h_ch1 = (CheckBox) findViewById(R.id.air_humid_h_ch1);
		air_humid_h_ch2 = (CheckBox) findViewById(R.id.air_humid_h_ch2);
		air_humid_h_ch3 = (CheckBox) findViewById(R.id.air_humid_h_ch3);

		air_humid_h_ed1 = (EditText) findViewById(R.id.air_humid_h_ed1);
		air_humid_h_ed2 = (EditText) findViewById(R.id.air_humid_h_ed2);
		air_humid_h_ed3 = (EditText) findViewById(R.id.air_humid_h_ed3);

		air_humid_l_ch1 = (CheckBox) findViewById(R.id.air_humid_l_ch1);
		air_humid_l_ch2 = (CheckBox) findViewById(R.id.air_humid_l_ch2);
		air_humid_l_ch3 = (CheckBox) findViewById(R.id.air_humid_l_ch3);

		air_humid_l_ed1 = (EditText) findViewById(R.id.air_humid_l_ed1);
		air_humid_l_ed2 = (EditText) findViewById(R.id.air_humid_l_ed2);
		air_humid_l_ed3 = (EditText) findViewById(R.id.air_humid_l_ed3);
	}

	// 空气温度
	private void initTemperature() {
		air_temp_h_ch1 = (CheckBox) findViewById(R.id.air_temp_h_ch1);
		air_temp_h_ch2 = (CheckBox) findViewById(R.id.air_temp_h_ch2);
		air_temp_h_ch3 = (CheckBox) findViewById(R.id.air_temp_h_ch3);

		air_temp_h_ed1 = (EditText) findViewById(R.id.air_temp_h_ed1);
		air_temp_h_ed2 = (EditText) findViewById(R.id.air_temp_h_ed2);
		air_temp_h_ed3 = (EditText) findViewById(R.id.air_temp_h_ed3);

		air_temp_l_ch1 = (CheckBox) findViewById(R.id.air_temp_l_ch1);
		air_temp_l_ch2 = (CheckBox) findViewById(R.id.air_temp_l_ch2);
		air_temp_l_ch3 = (CheckBox) findViewById(R.id.air_temp_l_ch3);

		air_temp_l_ed1 = (EditText) findViewById(R.id.air_temp_l_ed1);
		air_temp_l_ed2 = (EditText) findViewById(R.id.air_temp_l_ed2);
		air_temp_l_ed3 = (EditText) findViewById(R.id.air_temp_l_ed3);
	}

	// 电量
	private void initPower() {
		air_psa_ch1 = (CheckBox) findViewById(R.id.air_psa_ch1);
		air_psa_ch2 = (CheckBox) findViewById(R.id.air_psa_ch2);
		air_psa_ch3 = (CheckBox) findViewById(R.id.air_psa_ch3);

		air_psa_et1 = (EditText) findViewById(R.id.air_psa_et1);
		air_psa_et2 = (EditText) findViewById(R.id.air_psa_et2);
		air_psa_et3 = (EditText) findViewById(R.id.air_psa_et3);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.btn_plan_back:
			finish();
			break;
		case R.id.btn_plan_submit:
			AlarmPlanSettingBean alarmPlanSettingBean = getSettingAlarm();
			alarmPlanSettingBean.setStrategy_id(strategy_id);
			Gson gson = new Gson();
			final String json = gson.toJson(alarmPlanSettingBean);

			final String strategy_name = mTitelEditText.getText().toString()
					.trim();

			if (strategy_name == null || "".equals(strategy_name)) {
				String str = "请添加策略名称";
				ToastUtils.makeTextShort(str);
				return;
			}
			// {"strategy_id":"a3707e1e-6147-4f9d-830f-dfddc96f117a","strategy_name":"455"}
			if (json == null
					|| json.equals("")
					|| json.equals("{}")
					|| json.equals("{" + "\"" + "strategy_id" + "\"" + ":"
							+ "\"" + strategy_id + "\"" + "/," + "\""
							+ "strategy_name" + "\"" + ":" + "\""
							+ strategy_name + "\"" + "}")) {
				String str = "请至少添加一项设置";
				ToastUtils.makeTextShort(str);
				return;
			}

			ChargeConfigBean charge_config = alarmPlanSettingBean
					.getCharge_config();
			if (charge_config != null && !"".equals(charge_config)) {
				Integer l_value_y = charge_config.getL_value_y();
				Integer l_value_o = charge_config.getL_value_o();
				Integer l_value_r = charge_config.getL_value_r();
				String checkout_l = checkout_l(l_value_y, l_value_o, l_value_r,
						air_psa_et2, air_psa_et3, "低电量");
				if ("NO".equals(checkout_l)) {
					return;
				}

			}
			AirTemperatureConfigBean air_temperature_config = alarmPlanSettingBean
					.getAir_temperature_config();
			if (air_temperature_config != null
					&& !"".equals(air_temperature_config)) {
				Integer l_value_y = air_temperature_config.getL_value_y();
				Integer l_value_o = air_temperature_config.getL_value_o();
				Integer l_value_r = air_temperature_config.getL_value_r();
				Integer h_value_y = air_temperature_config.getH_value_y();
				Integer h_value_o = air_temperature_config.getH_value_o();
				Integer h_value_r = air_temperature_config.getH_value_r();

				String checkout_l = checkout_l(l_value_y, l_value_o, l_value_r,
						air_temp_l_ed2, air_temp_l_ed3, "空气低温");
				if ("NO".equals(checkout_l)) {
					return;
				}
				String checkout_h = checkout_h(h_value_y, h_value_o, h_value_r,
						air_temp_h_ed2, air_temp_h_ed3, "空气高温");
				if ("NO".equals(checkout_h)) {
					return;
				}
				String checkout_hl = checkout_hl(l_value_y, h_value_y,
						air_temp_l_ch1, air_temp_h_ch1, air_temp_l_ed1, "空气温度");
				if ("NO".equals(checkout_hl)) {
					return;
				}
			}
			AirHumidityConfigBean air_humidity_config = alarmPlanSettingBean
					.getAir_humidity_config();
			if (air_humidity_config != null && !"".equals(air_humidity_config)) {
				Integer l_value_y = air_humidity_config.getL_value_y();
				Integer l_value_o = air_humidity_config.getL_value_o();
				Integer l_value_r = air_humidity_config.getL_value_r();
				Integer h_value_y = air_humidity_config.getH_value_y();
				Integer h_value_o = air_humidity_config.getH_value_o();
				Integer h_value_r = air_humidity_config.getH_value_r();

				String checkout_l = checkout_l(l_value_y, l_value_o, l_value_r,
						air_humid_l_ed2, air_humid_l_ed3, "空气低湿");
				if ("NO".equals(checkout_l)) {
					return;
				}
				String checkout_h = checkout_h(h_value_y, h_value_o, h_value_r,
						air_humid_h_ed2, air_humid_h_ed3, "空气高湿");
				if ("NO".equals(checkout_h)) {
					return;
				}
				String checkout_hl = checkout_hl(l_value_y, h_value_y,
						air_humid_l_ch1, air_humid_h_ch1, air_humid_l_ed1,
						"空气湿度");
				if ("NO".equals(checkout_hl)) {
					return;
				}
			}
			SoilTemperatureConfigBean soil_temperature_config = alarmPlanSettingBean
					.getSoil_temperature_config();
			if (soil_temperature_config != null
					&& !"".equals(soil_temperature_config)) {
				Integer l_value_y = soil_temperature_config.getL_value_y();
				Integer l_value_o = soil_temperature_config.getL_value_o();
				Integer l_value_r = soil_temperature_config.getL_value_r();
				Integer h_value_y = soil_temperature_config.getH_value_y();
				Integer h_value_o = soil_temperature_config.getH_value_o();
				Integer h_value_r = soil_temperature_config.getH_value_r();

				String checkout_l = checkout_l(l_value_y, l_value_o, l_value_r,
						air_soil_temp_l_ed2, air_soil_temp_l_ed3, "土壤低温");
				if ("NO".equals(checkout_l)) {
					return;
				}
				String checkout_h = checkout_h(h_value_y, h_value_o, h_value_r,
						air_soil_temp_h_ed2, air_soil_temp_h_ed3, "土壤高温");
				if ("NO".equals(checkout_h)) {
					return;
				}
				String checkout_hl = checkout_hl(l_value_y, h_value_y,
						air_soil_temp_l_ch1, air_soil_temp_h_ch1,
						air_soil_temp_l_ed1, "土壤温度");
				if ("NO".equals(checkout_hl)) {
					return;
				}
			}

			SoilHumidityConfigBean soil_humidity_config = alarmPlanSettingBean
					.getSoil_humidity_config();
			if (soil_humidity_config != null
					&& !"".equals(soil_humidity_config)) {
				Integer l_value_y = soil_humidity_config.getL_value_y();
				Integer l_value_o = soil_humidity_config.getL_value_o();
				Integer l_value_r = soil_humidity_config.getL_value_r();
				Integer h_value_y = soil_humidity_config.getH_value_y();
				Integer h_value_o = soil_humidity_config.getH_value_o();
				Integer h_value_r = soil_humidity_config.getH_value_r();

				String checkout_l = checkout_l(l_value_y, l_value_o, l_value_r,
						air_soil_humid_l_ed2, air_soil_humid_l_ed3, "土壤低湿");
				if ("NO".equals(checkout_l)) {
					return;
				}
				String checkout_h = checkout_h(h_value_y, h_value_o, h_value_r,
						air_soil_humid_h_ed2, air_soil_humid_h_ed3, "土壤高湿");
				if ("NO".equals(checkout_h)) {
					return;
				}
				String checkout_hl = checkout_hl(l_value_y, h_value_y,
						air_soil_humid_l_ch1, air_soil_humid_h_ch1,
						air_soil_humid_l_ed1, "土壤湿度");
				if ("NO".equals(checkout_hl)) {
					return;
				}
			}
			LuxConfigBean lux_config = alarmPlanSettingBean.getLux_config();
			if (lux_config != null && !"".equals(lux_config)) {
				Integer l_value_y = lux_config.getL_value_y();
				Integer l_value_o = lux_config.getL_value_o();
				Integer l_value_r = lux_config.getL_value_r();
				Integer h_value_y = lux_config.getH_value_y();
				Integer h_value_o = lux_config.getH_value_o();
				Integer h_value_r = lux_config.getH_value_r();

				String checkout_l = checkout_l(l_value_y, l_value_o, l_value_r,
						air_lux_l_ed2, air_lux_l_ed3, "高光照");
				if ("NO".equals(checkout_l)) {
					return;
				}
				String checkout_h = checkout_h(h_value_y, h_value_o, h_value_r,
						air_lux_h_ed2, air_lux_h_ed3, "低光照");
				if ("NO".equals(checkout_h)) {
					return;
				}
				String checkout_hl = checkout_hl(l_value_y, h_value_y,
						air_lux_l_ch1, air_lux_h_ch1, air_lux_l_ed1, "光照");
				if ("NO".equals(checkout_hl)) {
					return;
				}
			}
			CoPpmConfigBean co_ppm_config = alarmPlanSettingBean
					.getCo_ppm_config();
			if (co_ppm_config != null && !"".equals(co_ppm_config)) {
				Integer l_value_y = co_ppm_config.getL_value_y();
				Integer l_value_o = co_ppm_config.getL_value_o();
				Integer l_value_r = co_ppm_config.getL_value_r();
				Integer h_value_y = co_ppm_config.getH_value_y();
				Integer h_value_o = co_ppm_config.getH_value_o();
				Integer h_value_r = co_ppm_config.getH_value_r();

				String checkout_l = checkout_l(l_value_y, l_value_o, l_value_r,
						air_co_l_ed2, air_co_l_ed3, "低一氧化碳");
				if ("NO".equals(checkout_l)) {
					return;
				}
				String checkout_h = checkout_h(h_value_y, h_value_o, h_value_r,
						air_co_h_ed2, air_co_h_ed3, "高一氧化碳");
				if ("NO".equals(checkout_h)) {
					return;
				}
				String checkout_hl = checkout_hl(l_value_y, h_value_y,
						air_co_l_ch1, air_co_h_ch1, air_co_l_ed1, "一氧化碳");
				if ("NO".equals(checkout_hl)) {
					return;
				}
			}
			Co2PpmConfigBean co2_ppm_config = alarmPlanSettingBean
					.getCo2_ppm_config();
			if (co2_ppm_config != null && !"".equals(co2_ppm_config)) {
				Integer l_value_y = co2_ppm_config.getL_value_y();
				Integer l_value_o = co2_ppm_config.getL_value_o();
				Integer l_value_r = co2_ppm_config.getL_value_r();
				Integer h_value_y = co2_ppm_config.getH_value_y();
				Integer h_value_o = co2_ppm_config.getH_value_o();
				Integer h_value_r = co2_ppm_config.getH_value_r();

				String checkout_l = checkout_l(l_value_y, l_value_o, l_value_r,
						air_co2_l_ed2, air_co2_l_ed3, "低二氧化碳");
				if ("NO".equals(checkout_l)) {
					return;
				}
				String checkout_h = checkout_h(h_value_y, h_value_o, h_value_r,
						air_co2_h_ed2, air_co2_h_ed3, "高二氧化碳");
				if ("NO".equals(checkout_h)) {
					return;
				}
				String checkout_hl = checkout_hl(l_value_y, h_value_y,
						air_co2_l_ch1, air_co2_h_ch1, air_co2_l_ed1, "二氧化碳");
				if ("NO".equals(checkout_hl)) {
					return;
				}
			}
			SmokePpmConfigBean smoke_ppm_config = alarmPlanSettingBean
					.getSmoke_ppm_config();
			if (smoke_ppm_config != null && !"".equals(smoke_ppm_config)) {

				Integer h_value_y = smoke_ppm_config.getH_value_y();
				Integer h_value_o = smoke_ppm_config.getH_value_o();
				Integer h_value_r = smoke_ppm_config.getH_value_r();

				String checkout_h = checkout_h(h_value_y, h_value_o, h_value_r,
						air_smoke_h_ed2, air_smoke_h_ed3, "烟雾");
				if ("NO".equals(checkout_h)) {
					return;
				}
			}
			// dataCheck();
			// log.v(strategy_name);
			localBuilder = new AlertDialog.Builder(mContext).create();
			localBuilder.show();
			View fragmentSetshedDeldialog = LayoutInflater.from(mContext)
					.inflate(R.layout.fragment_setshed_deldialog, null);
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
			tv_deldialog_content.setText("是否修改<" + strategy_name + ">策略?");
			// 确认
			btnEnsure.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					new changeAlarmSetting().execute(json, strategy_id);
					localBuilder.dismiss();
				}
			});
			// 取消
			btnCancel.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					localBuilder.dismiss();
				}
			});

			break;
		default:
			break;
		}
	}

	private String checkout_h(Integer h_value_y, Integer h_value_o,
			Integer h_value_r, EditText editText2, EditText editText3,
			String conige) {
		if (h_value_y != null && h_value_o != null) {
			if (h_value_o <= h_value_y) {
				editText2.setText("");
				ToastUtils.makeTextShort(conige + "值设置错误请从新设置!");

				return "NO";
			}
		}
		if (h_value_o != null && h_value_r != null) {
			if (h_value_r <= h_value_o) {
				editText3.setText("");
				ToastUtils.makeTextShort(conige + "值设置错误请从新设置!");
				return "NO";
			}
		}
		if (h_value_y != null && h_value_r != null) {
			if (h_value_r <= h_value_y) {
				editText3.setText("");
				ToastUtils.makeTextShort(conige + "值设置错误请从新设置!");
				return "NO";
			}
		}
		return "OK";

	}

	private String checkout_l(Integer l_value_y, Integer l_value_o,
			Integer l_value_r, EditText editText2, EditText editText3,
			String conige) {
		if (l_value_y != null && l_value_o != null) {
			if (l_value_o >= l_value_y) {
				// showDialog(air_psa_et2);
				editText2.setText("");
				ToastUtils.makeTextShort(conige + "值设置错误请从新设置!");

				return "NO";
			}
		}
		if (l_value_o != null && l_value_r != null) {
			if (l_value_r >= l_value_o) {
				editText3.setText("");
				ToastUtils.makeTextShort(conige + "值设置错误请从新设置!");
				return "NO";
			}
		}
		if (l_value_y != null && l_value_r != null) {
			if (l_value_r >= l_value_y) {
				editText3.setText("");
				ToastUtils.makeTextShort(conige + "值设置错误请从新设置!");
				return "NO";
			}
		}
		return "OK";
	}
	@Override
	protected int layoutViewId() {
		return R.layout.alarm_plan_setting_lay;
	}
	private String checkout_hl(Integer l_value_y, Integer h_value_y,
			CheckBox checkBox1, CheckBox checkBox2, EditText editText,
			String conige) {
		if (l_value_y != null && h_value_y != null && checkBox1.isChecked()
				&& checkBox2.isChecked()) {
			if (h_value_y <= l_value_y) {
				editText.setText("");
				ToastUtils.makeTextShort(conige + "值设置错误请从新设置!");
				return "NO";
			}
		}
		return "OK";

	}

	private class changeAlarmSetting extends AsyncTask<Object, Void, String> {

		@Override
		protected String doInBackground(Object... arg0) {
			String json = (String) arg0[0];
			String strategyId = (String) arg0[1];
			String staString = "";
			String result = WapiUtil.changeCustomAlarmStrategies(strategyId,
					json);
			// log.v(result);

			try {
				JSONObject jsonObject = new JSONObject(result);
				String addState = jsonObject.getString("result");
				if (addState.equals("OK")) {
					staString = "修改策略成功";
					finish();
				} else {
					staString = "修改策略失败";
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

	private void dataCheck() {
		// 电源
		check_l(air_psa_ch1, air_psa_ch2, air_psa_ch3, air_psa_et1,
				air_psa_et2, air_psa_et3);
		// 温度
		check_h(air_temp_h_ch1, air_temp_h_ch2, air_temp_h_ch3, air_temp_h_ed1,
				air_temp_h_ed2, air_temp_h_ed3);
		check_l(air_temp_l_ch1, air_temp_l_ch2, air_temp_l_ch3, air_temp_l_ed1,
				air_temp_l_ed2, air_temp_l_ed3);
		// 湿度
		check_h(air_humid_h_ch1, air_humid_h_ch2, air_humid_h_ch3,
				air_humid_h_ed1, air_humid_h_ed2, air_humid_h_ed3);
		check_l(air_humid_l_ch1, air_humid_l_ch2, air_humid_l_ch3,
				air_humid_l_ed1, air_humid_l_ed2, air_humid_l_ed3);
		// 土壤温度
		check_h(air_soil_temp_h_ch1, air_soil_temp_h_ch2, air_soil_temp_h_ch3,
				air_soil_temp_h_ed1, air_soil_temp_h_ed2, air_soil_temp_h_ed3);
		check_l(air_soil_temp_l_ch1, air_soil_temp_l_ch2, air_soil_temp_l_ch3,
				air_soil_temp_l_ed1, air_soil_temp_l_ed2, air_soil_temp_l_ed3);
		// 湿度
		check_h(air_soil_humid_h_ch1, air_soil_humid_h_ch2,
				air_soil_humid_h_ch3, air_soil_humid_h_ed1,
				air_soil_humid_h_ed2, air_soil_humid_h_ed3);
		check_l(air_soil_humid_l_ch1, air_soil_humid_l_ch2,
				air_soil_humid_l_ch3, air_soil_humid_l_ed1,
				air_soil_humid_l_ed2, air_soil_humid_l_ed3);
		// 光照
		check_h(air_lux_h_ch1, air_lux_h_ch2, air_lux_h_ch3, air_lux_h_ed1,
				air_lux_h_ed2, air_lux_h_ed3);
		check_l(air_lux_l_ch1, air_lux_l_ch2, air_lux_l_ch3, air_lux_l_ed1,
				air_lux_l_ed2, air_lux_l_ed3);
		// co
		check_h(air_co_h_ch1, air_co_h_ch2, air_co_h_ch3, air_co_h_ed1,
				air_co_h_ed2, air_co_h_ed3);
		check_l(air_co_l_ch1, air_co_l_ch2, air_co_l_ch3, air_co_l_ed1,
				air_co_l_ed2, air_co_l_ed3);
		// co2
		check_h(air_co2_h_ch1, air_co2_h_ch2, air_co2_h_ch3, air_co2_h_ed1,
				air_co2_h_ed2, air_co2_h_ed3);
		check_l(air_co2_l_ch1, air_co2_l_ch2, air_co2_l_ch3, air_co2_l_ed1,
				air_co2_l_ed2, air_co2_l_ed3);
		// 烟雾
		check_h(air_smoke_h_ch1, air_smoke_h_ch2, air_smoke_h_ch3,
				air_smoke_h_ed1, air_smoke_h_ed2, air_smoke_h_ed3);
	}

	private void check_h(CheckBox checkBox1, CheckBox checkBox2,
			CheckBox checkBox3, EditText editText1, EditText editText2,
			EditText editText3) {
		String ed1 = null, ed2 = null, ed3 = null;
		if (checkBox1.isChecked()) {
			ed1 = editText1.getText().toString();
		}
		if (checkBox2.isChecked()) {
			ed2 = editText2.getText().toString();
		}
		if (checkBox3.isChecked()) {
			ed3 = editText3.getText().toString();
		}

		if (ed1 != null && ed2 != null && !ed1.equals("") && !ed2.equals("")) {
			Integer ed1Int = Integer.parseInt(ed1);
			Integer ed2Int = Integer.parseInt(ed2);
			if (ed2Int <= ed1Int) {
				showDialog(editText2);

			}
		}

		if (ed1 != null && ed3 != null && !ed1.equals("") && !ed3.equals("")) {
			Integer ed1Int = Integer.parseInt(ed1);
			Integer ed2Int = Integer.parseInt(ed3);
			if (ed2Int <= ed1Int) {
				showDialog(editText3);

			}
		}
		if (ed2 != null && ed3 != null && !ed2.equals("") && !ed3.equals("")) {
			Integer ed1Int = Integer.parseInt(ed2);
			Integer ed2Int = Integer.parseInt(ed3);
			if (ed2Int <= ed1Int) {
				showDialog(editText3);

			}
		}

	}

	private void check_l(CheckBox checkBox1, CheckBox checkBox2,
			CheckBox checkBox3, EditText editText1, EditText editText2,
			EditText editText3) {
		String ed1 = null, ed2 = null, ed3 = null;
		if (checkBox1.isChecked()) {
			ed1 = editText1.getText().toString();
		}
		if (checkBox2.isChecked()) {
			ed2 = editText2.getText().toString();
		}
		if (checkBox3.isChecked()) {
			ed3 = editText3.getText().toString();
		}

		if (ed1 != null && ed2 != null && !"".equals(ed1) && !"".equals(ed2)) {
			Integer ed1Int = Integer.parseInt(ed1);
			Integer ed2Int = Integer.parseInt(ed2);
			if (ed2Int >= ed1Int) {
				showDialog(editText2);

			}
		}

		if (ed1 != null && ed3 != null && !ed1.equals("") && !ed3.equals("")) {
			Integer ed1Int = Integer.parseInt(ed1);
			Integer ed2Int = Integer.parseInt(ed3);
			if (ed2Int >= ed1Int) {
				showDialog(editText3);

			}
		}
		if (ed2 != null && ed3 != null && !ed2.equals("") && !ed3.equals("")) {
			Integer ed1Int = Integer.parseInt(ed2);
			Integer ed2Int = Integer.parseInt(ed3);
			if (ed2Int >= ed1Int) {
				showDialog(editText3);

			}
		}

	}

	private void showDialog(final EditText editText) {
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
		tv_deldialog_content.setText("请检查您舍得值是否正确!");
		// 确认
		btnEnsure.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				editText.setText("");
				localBuilder.dismiss();
			}
		});
		// 取消
		btnCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				localBuilder.dismiss();
			}
		});
	}

	/**
	 * 禁止EditText输入空格
	 * 
	 * @param editText
	 */
	public static void setEditTextInhibitInputSpace(EditText editText) {
		InputFilter filter = new InputFilter() {
			@Override
			public CharSequence filter(CharSequence source, int start, int end,
					Spanned dest, int dstart, int dend) {
				if (source.equals(" ") || source.toString().contentEquals("\n"))
					return "";

				else
					return null;
			}
		};
		editText.setFilters(new InputFilter[] { filter });
	}

	/**
	 * 禁止EditText输入特殊字符
	 * 
	 * @param editText
	 */
	public static void setEditTextInhibitInputSpeChat(EditText editText) {

		InputFilter filter = new InputFilter() {
			@Override
			public CharSequence filter(CharSequence source, int start, int end,
					Spanned dest, int dstart, int dend) {
				String speChat = "[`~!@#$%^&*()-+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
				Pattern pattern = Pattern.compile(speChat);
				Matcher matcher = pattern.matcher(source.toString());
				if (matcher.find())
					return "";
				else
					return null;
			}
		};
		editText.setFilters(new InputFilter[] { filter });
	}
}
