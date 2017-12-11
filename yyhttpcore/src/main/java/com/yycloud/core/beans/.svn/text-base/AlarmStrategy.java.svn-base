package com.yycloud.core.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AlarmStrategy implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8038738539655332788L;

	//public static int MAXNUM = 3;
	public static String[] MAXNUM = {"y","o","r"};

	String strategy_id;
	String strategy_name;
	String strategy_type;



	public static class Alarm implements Serializable {
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;
		public boolean enable;
		public Integer alarmTriggerVal;
	}

	public enum AlarmType {
		charge_config,
		air_temperature_config,
		air_humidity_config,
		co_ppm_config,
		co2_ppm_config,
		lux_config,
		soil_temperature_config,
		soil_humidity_config,
		soil_moisture_config,
		rain_config,
		windvelocity_config,
		pm2_5_ppm_config,
		smoke_ppm_config,
		pm10_ppm_config,
		default_config
	}

	public enum HighLowAlarm {
		High, Low
	}

	/**
	 * @return the strategy_id
	 */
	public String getStrategy_id() {
		return strategy_id;
	}

	/**
	 * @param strategy_id
	 *            the strategy_id to set
	 */
	public void setStrategy_id(String strategy_id) {
		this.strategy_id = strategy_id;
	}

	/**
	 * @return the strategy_name
	 */
	public String getStrategy_name() {
		return strategy_name;
	}

	/**
	 * @param strategy_name
	 *            the strategy_name to set
	 */
	public void setStrategy_name(String strategy_name) {
		this.strategy_name = strategy_name;
	}


	/**
	 * @return the strategy_type
	 */
	public String getStrategy_type() {
		return strategy_type;
	}

	/**
	 * @param strategy_type the strategy_type to set
	 */
	public void setStrategy_type(String strategy_type) {
		this.strategy_type = strategy_type;
	}

	public static class AlarmStrategyItem implements Serializable {
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;
		// tring category;
		AlarmType type; // air temperature, air humidity, ppm, illumination,
		// soil temperature,
		// soil humidity, soil moisture unit %
		// rain mmh
		// windvelocity m/s
		// String name; //the name of this alarm strategy
		Alarm high_alarms[] = new Alarm[MAXNUM.length];
		Alarm low_alarms[] = new Alarm[MAXNUM.length];

		public AlarmStrategyItem() {
			type = AlarmType.default_config;
			for (int i = 0; i < MAXNUM.length; i++) {
				high_alarms[i] = new Alarm();
				low_alarms[i] = new Alarm();
				//high_alarms[i].enable = false;
				//low_alarms[i].enable = false;
			}
		}

		/**
		 * @return the type
		 */
		public AlarmType getType() {
			return type;
		}

		/**
		 * @param type
		 *            the type to set
		 */
		public void setType(AlarmType type) {
			this.type = type;
		}

		/**
		 * @return the alarm
		 */
		public Alarm[] getAlarms(HighLowAlarm highlow) {
			if (highlow == HighLowAlarm.High)
				return high_alarms;
			else
				return low_alarms;
		}

		/**
		 * @param
		 *
		 */
		public Alarm[] setAlarms(Alarm[] alarms, HighLowAlarm highlow) {
			//if (index >= MAXNUM)
			//	return null;

			if (highlow == HighLowAlarm.High)
				this.high_alarms = alarms;
			else
				this.low_alarms = alarms;

			return alarms;
		}
	}

	AlarmStrategyItem JSON2Item(JSONObject obj) {
		AlarmStrategyItem item = new AlarmStrategyItem();

		for (int i = 0; i < MAXNUM.length; i++) {

			/*if (obj.has("enable_l_" + (i + 1))) {
				boolean b = false;
				try {
					b = obj.getBoolean("enable_l_" + (i + 1));
				} catch (JSONException e) {
					e.printStackTrace();
				}
				item.low_alarms[i].enable = b;
			}

			if (obj.has("enable_h_" + (i + 1))) {
				boolean b = false;
				try {
					b = obj.getBoolean("enable_h_" + (i + 1));
				} catch (JSONException e) {
					e.printStackTrace();
				}
				item.high_alarms[i].enable = b;
			}*/

			if (obj.has("l_value_" + MAXNUM[i])) {
				int f = 0;
				try {
					f = (Integer) obj.getInt("l_value_" +  MAXNUM[i]);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				item.low_alarms[i].alarmTriggerVal = f;
			}

			if (obj.has("h_value_" + MAXNUM[i])) {
				int f = 0;
				try {
					f = (Integer) obj.getInt("h_value_" +  MAXNUM[i]);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				item.high_alarms[i].alarmTriggerVal = f;
			}
		}
		return item;
	}

	AlarmStrategyItem tryFillWithType(JSONObject obj, AlarmType type) {
		AlarmStrategyItem item = null;
		if (obj.has(type.name())) {

			JSONObject chargeObj;
			try {
				chargeObj = obj.getJSONObject(type.name());
				item = JSON2Item(chargeObj);
				item.setType(type);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return item;
	}

	List<AlarmStrategyItem> strategyitems = new ArrayList<AlarmStrategyItem>();

	/**
	 * @return the strategyitems
	 */
	public List<AlarmStrategyItem> getStrategyitems() {
		return strategyitems;
	}

	/**
	 * @param strategyitems the strategyitems to set
	 */
	public void setStrategyitems(List<AlarmStrategyItem> strategyitems) {
		this.strategyitems = strategyitems;
	}

	public void addOrUpdateStrategyAlarmItem(AlarmStrategyItem item) {
		boolean isExist = false;
		for (int i = 0; i < strategyitems.size(); i++) {
			if (item.getType() == strategyitems.get(i).getType()) {
				strategyitems.remove(i);
				strategyitems.add(item);
				isExist = true;
				break;
			}
		}
		if (!isExist) {
			strategyitems.add(item);
		}
	}

	public int fromJSONString(String jsonString) {
		// AlarmStrategy strategy = new AlarmStrategy();

		try {
			JSONObject obj = new JSONObject(jsonString);
			this.strategy_id = obj.getString("strategy_id");
			this.strategy_name = obj.getString("strategy_name");

			AlarmStrategyItem item = tryFillWithType(obj,
					AlarmType.charge_config);
			if (null != item)
				this.strategyitems.add(item);

			item = null;
			item = tryFillWithType(obj, AlarmType.air_temperature_config);
			if (null != item)
				this.strategyitems.add(item);

			item = null;
			item = tryFillWithType(obj, AlarmType.air_humidity_config);
			if (null != item)
				this.strategyitems.add(item);

			item = null;
			item = tryFillWithType(obj, AlarmType.co_ppm_config);
			if (null != item)
				this.strategyitems.add(item);

			item = null;
			item = tryFillWithType(obj, AlarmType.co2_ppm_config);
			if (null != item)
				this.strategyitems.add(item);

			item = null;
			item = tryFillWithType(obj, AlarmType.lux_config);
			if (null != item)
				this.strategyitems.add(item);

			item = null;
			item = tryFillWithType(obj, AlarmType.soil_temperature_config);
			if (null != item)
				this.strategyitems.add(item);

			item = null;
			item = tryFillWithType(obj, AlarmType.soil_humidity_config);
			if (null != item)
				this.strategyitems.add(item);

			item = null;
			item = tryFillWithType(obj, AlarmType.soil_moisture_config);
			if (null != item)
				this.strategyitems.add(item);

			item = null;
			item = tryFillWithType(obj, AlarmType.rain_config);
			if (null != item)
				this.strategyitems.add(item);

			item = null;
			item = tryFillWithType(obj, AlarmType.windvelocity_config);
			if (null != item)
				this.strategyitems.add(item);

			item = null;
			item = tryFillWithType(obj, AlarmType.pm2_5_ppm_config);
			if (null != item)
				this.strategyitems.add(item);

			item = null;
			item = tryFillWithType(obj, AlarmType.smoke_ppm_config);
			if (null != item)
				this.strategyitems.add(item);

			item = null;
			item = tryFillWithType(obj, AlarmType.pm10_ppm_config);
			if (null != item)
				this.strategyitems.add(item);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public JSONObject toJson() {
		// Map<String, String> map = new HashMap<String, String>();
		JSONObject objout = new JSONObject();
		for (int i = 0; i < strategyitems.size(); i++) {
			JSONObject itemobj = new JSONObject();
			// JSONObject obj = new JSONObject();
			AlarmStrategyItem item = strategyitems.get(i);
			for (int j = 0; j < item.high_alarms.length; j++) {
				// JSONObject member = new JSONObject();
				try {
					//itemobj.put("enable_h_" + (j ),
					//		item.high_alarms[j].enable);
					itemobj.put("h_value_" + (j ),
							item.high_alarms[j].alarmTriggerVal);
					//	itemobj.put("enable_l_" + (j ),
					//		item.low_alarms[j].enable);
					itemobj.put("l_value_" + (j ),
							item.low_alarms[j].alarmTriggerVal);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				itemobj.put("type", item.type.toString());
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				objout.put(item.type.name(), itemobj);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			objout.put("strategy_id", strategy_id);
			objout.put("strategy_name", strategy_name);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return objout;
	}
}
