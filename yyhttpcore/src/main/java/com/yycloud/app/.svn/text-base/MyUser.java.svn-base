package com.yycloud.app;

import com.yycloud.app.db.ShedStrategyDBHelper;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.WapiUtil;
import com.yycloud.app.utils.WapiUtilEx;
import com.yycloud.core.beans.AlarmStrategy;
import com.yycloud.core.beans.Components;
import com.yycloud.core.beans.ShedInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.yyshed.dao.DBShedStrategy;

/**
 * 
 * @project name：yyhttpcore
 * @type name：MyUser
 * @description：我的用户
 * @author：gang
 * @date time：2017-6-8 下午1:33:43
 * @version
 */
public class MyUser {
	private static MyUser instance = null;

	public static MyUser getInstance() {
		if (instance == null) {
			instance = new MyUser();
		}
		return instance;
	}

	public static final String ST_default = "default";
	public static final String ST_customer = "customer";
	public static final String ST_temp = "temp";

	private List<ShedInfo> shedinfos = new ArrayList<ShedInfo>();

	private Map<String, AlarmStrategy> shedStrategyMap = new HashMap<String, AlarmStrategy>();

	// private List<AlarmStrategy> shedStrategyList;

	public abstract static class AlarmStrategyCallBack extends MYCallBack {
		abstract public void onSuccess(AlarmStrategy s);
	}

	public void init() {

		List<DBShedStrategy> dbsslist = ShedStrategyDBHelper.getInstance()
				.getShedStrategyList();
		if (null == dbsslist)
			return;

		for (int i = 0; i < dbsslist.size(); i++) {
			DBShedStrategy dbss = dbsslist.get(i);
			AlarmStrategy s = new AlarmStrategy();
			String content = dbss.getContent();
			s.fromJSONString(content);
			// s.setStrategy_id(s.);
			shedStrategyMap.put(dbss.getShed_id(), s);
		}
	}

	public void updateShedStrategyMap(String shedid, AlarmStrategy strategy) {
		shedStrategyMap.put(shedid, strategy);
	}

	public void cleanShedStrategyMap(String shedid) {
		shedStrategyMap.remove(shedid);
	}

	public ShedInfo getShedInfo(String sn) {
		for (int i = 0; i < shedinfos.size(); i++) {
			ShedInfo shed = shedinfos.get(i);
			if (shed.getSmartgate().getSn().equals(sn)) {
				return shed;
			}
		}
		return null;
	}

	public int updateShedInfo(ShedInfo shedinfo) {
		if (shedinfos.size() == 0) {
			shedinfos.add(shedinfo);
			return 0;
		}
		boolean isExisted = false;
		for (int i = 0; i < shedinfos.size(); i++) {
			ShedInfo shed = shedinfos.get(i);
			if (shed.getSmartgate().getSn()
					.equals(shedinfo.getSmartgate().getSn())) {
				isExisted = true;
				shedinfos.set(i, shedinfo);
			}
		}

		if (!isExisted) {
			shedinfos.add(shedinfo);
		}
		return 0;
	}

	public int setShedStrategy(final String shedId, final String strategy_type,
							   final AlarmStrategy strategy, final MYCallBack cb) {

		if (!strategy_type.equals(ST_default)
				&& !strategy_type.equals(ST_customer)
				&& !strategy_type.equals(ST_temp))
			return -1;

		WapiUtilEx.addOrUpdateShedStrategy(shedId, strategy_type, strategy,
				new MYCallBack() {
					@Override
					public void onError(int code, String message) {
						if (null != cb)
							cb.onError(code, message);
					}

					@Override
					public void onSuccess(String jsonString) {
						try {
							JSONObject obj = new JSONObject(jsonString);
							if (obj.getString("result").equals("OK")) {
								DBShedStrategy dbss = new DBShedStrategy();
								dbss.setShed_id(shedId);
								dbss.setStrategy_type(strategy_type);
								dbss.setContent(strategy.toJson().toString());
								ShedStrategyDBHelper.getInstance()
										.insertShedStrategy(dbss);
								if (null != cb)
									cb.onSuccess(jsonString);
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});

		return 0;
	}

	public int getShedStrategy(final String shedId,
			final AlarmStrategyCallBack cb) {

		WapiUtilEx.getShedStrategy(shedId, new MYCallBack() {
			@Override
			public void onError(int code, String message) {
				if (null != cb)
					cb.onError(code, message);
			}

			@Override
			public void onSuccess(String jsonString) {
				try {
					JSONObject obj = new JSONObject(jsonString);
					if (obj.getString("result").equals("OK")) {
						String content = obj.getString("strategy");
						AlarmStrategy s = new AlarmStrategy();
						s.fromJSONString(content);

						// String strategy_type =
						// obj.getString("strategy_type");
						DBShedStrategy dbss = new DBShedStrategy();
						dbss.setShed_id(shedId);
						dbss.setStrategy_type("no-default");
						dbss.setContent(content);
						ShedStrategyDBHelper.getInstance().insertShedStrategy(
								dbss);

						updateShedStrategyMap(shedId, s);
						if (null != cb)
							cb.onSuccess(s);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		return 0;
	}

	public int cleanShedStrategy(final String shedId, final MYCallBack cb) {
		WapiUtilEx.delShedStrategy(shedId, new MYCallBack() {
			@Override
			public void onError(int code, String message) {
				if (null != cb)
					cb.onError(code, message);
			}

			@Override
			public void onSuccess(String jsonString) {
				try {
					JSONObject obj = new JSONObject(jsonString);
					if (obj.getString("result").equals("OK")) {
						ShedStrategyDBHelper.getInstance().deleteShedStrategy(
								shedId);
						cleanShedStrategyMap(shedId);
						if (null != cb)
							cb.onSuccess(jsonString);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		return 0;
	}

	public String updateDevComponentAlias(String devUuid, String componentId,
										  String alias, final MYCallBack cb) {

		String retMsg = "修改失败";

		try {
			String result = WapiUtil.updateDevComponentAlias(/* devUuid, */
			componentId, alias);
			if (result == null || "".equals(result)) {
				cb.onError(1, retMsg);
				return retMsg;
			}

			JSONObject object = new JSONObject(result);
			// JSONObject component_alias = (JSONObject) object
			// .get("alias");
			String ret = object.getString("alias");
			if (!alias.equals(ret)) {
				cb.onError(1, retMsg);
				return retMsg;
			}

			for (int i = 0; i < shedinfos.size(); i++) {
				ShedInfo shed = shedinfos.get(i);
				if (!shed.getSmartgate().getSn().equals(devUuid))
					continue;

				int size = shed.getComponents().size();
				for (int k = 0; k < size; k++) {
					Components component = shed.getComponents().get(k);
					if (component.getSn().equals(componentId)) {
						component.setDev_alias(alias);

						retMsg = "修改成功";
						cb.onSuccess(retMsg);
						return retMsg;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		cb.onError(1, retMsg);
		return retMsg;
	}
}
