package com.yycloud.app;

import android.os.AsyncTask;
import android.os.Handler;

import com.yycloud.app.db.StrategyDBHelper;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.WapiUtil;
import com.yycloud.app.utils.WapiUtilEx;
import com.yycloud.core.beans.AlarmStrategy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.yyshed.dao.DBAlarmStrategyCustom;
import cn.yyshed.dao.DBAlarmStrategyDefault;

/**
 * @project name：yyhttpcore
 * @type name：AlarmStrategyManager
 * @description：报警策略管理器
 * @author：gang
 * @date time：2017-6-8 下午1:30:41
 */
public class AlarmStrategyManager {

    private static AlarmStrategyManager instance = null;

    // strategy type. same to the server
    public final static String DEFAULT_STRATEGY_TYPE = "default";
    public final static String CUSTOM_STRATEGY_TYPE = "customer"; // customer
    // not
    // custom
    public final static String TEMP_STRATEGY_TYPE = "temp";

    public static AlarmStrategyManager getInstance() {
        if (instance == null) {
            instance = new AlarmStrategyManager();
        }
        return instance;
    }

    public void init(Handler handler) {
        cleanDBStrategies();
        getAlarmStrategies(handler);
    }

    /**
     * 每次登陆成功之后需要清除数据库里存的策略
     */
    private void cleanDBStrategies() {
        StrategyDBHelper.getInstance().cleanDefaultAlarmStrategies();
        StrategyDBHelper.getInstance().cleanCustomAlarmStrategies();
    }

    /**
     * get all the alarm strategies from the db include default and custom
     *
     * @return
     */
    public List<AlarmStrategy> getAllAlarmStrategies() {
        List<DBAlarmStrategyDefault> dbstrategydeflist = StrategyDBHelper
                .getInstance().getDefStrategyStringList();
        List<AlarmStrategy> strategyDefList = DBStratDefList2StrategyList(dbstrategydeflist);

        List<DBAlarmStrategyCustom> dbstrategycuslist = StrategyDBHelper
                .getInstance().getCustomStrategyStringList();
        List<AlarmStrategy> strategyCusList = DBStratCusList2StrategyList(dbstrategycuslist);

        List<AlarmStrategy> totalList = new ArrayList<AlarmStrategy>();
        if (null != strategyDefList) {
            totalList.addAll(strategyDefList);
        }
        if (null != strategyCusList) {
            totalList.addAll(strategyCusList);
        }
        return totalList;
    }

    /**
     * get the default&custom alarm strategies from the server store the
     * strategies into the database
     */
    private void getAlarmStrategies(Handler handler) {
        new LoadDefaultAlarmStrategies(handler).execute();
        new LoadCustomAlarmStrategies(handler).execute();
    }

    /**
     * get the default alarm strategies from the server
     */
    private void getDefaultAlarmStrategies() {
        String jsonString = WapiUtil.getDefaultAlarmStrategies();
        List<DBAlarmStrategyDefault> dbstrategylist = JSONStr2DBStrategyDefList(jsonString);
        if (dbstrategylist != null) {
            List<AlarmStrategy> strategyDefList = DBStratDefList2StrategyList(dbstrategylist);
            if (null != strategyDefList) {
                // StrategyDBHelper.getInstance().cleanDefaultAlarmStrategies();
                StrategyDBHelper.getInstance().updateDefaultAlarmStrategies(
                        dbstrategylist);
            }
        }
    }

    /**
     * get the custom alarm strategies from the server
     */
    private void getCustomAlarmStrategies() {
        String jsonString = WapiUtil.getCustomAlarmStrategies();
        List<DBAlarmStrategyCustom> dbstrategylist = JSONStr2DBStrategyCusList(jsonString);
        if (dbstrategylist != null) {
            List<AlarmStrategy> strategyCusList = DBStratCusList2StrategyList(dbstrategylist);
            if (null != strategyCusList) {
                // StrategyDBHelper.getInstance().cleanCustomAlarmStrategies();
                StrategyDBHelper.getInstance().updateCustomAlarmStrategies(
                        dbstrategylist);
            }
        }
    }

    /**
     * child thread to request the default alarm strategies
     *
     * @author shuyi
     */
    private class LoadDefaultAlarmStrategies extends
            AsyncTask<Object, Void, Void> {

        private Handler mHandler = null;

        public LoadDefaultAlarmStrategies(Handler handler) {
            this.mHandler = handler;
        }

        @Override
        protected Void doInBackground(Object... arg0) {
            getDefaultAlarmStrategies();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mHandler.sendEmptyMessage(10);
        }
    }

    /**
     * child thread to request the custom alarm strategies
     *
     * @author shuyi
     */
    private class LoadCustomAlarmStrategies extends
            AsyncTask<Object, Void, Void> {

        private Handler mHandler = null;

        public LoadCustomAlarmStrategies(Handler handler) {
            this.mHandler = handler;
        }

        @Override
        protected Void doInBackground(Object... arg0) {
            getCustomAlarmStrategies();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            mHandler.sendEmptyMessage(10);
        }
    }

    /**
     * load the default alarm strategies from the database
     *
     * @return
     */
    public List<AlarmStrategy> getDefaultAlarmStrategyList() {
        List<DBAlarmStrategyDefault> dbstrategydeflist = StrategyDBHelper
                .getInstance().getDefStrategyStringList();
        List<AlarmStrategy> strategyDefList = DBStratDefList2StrategyList(dbstrategydeflist);
        return strategyDefList;
    }

    /**
     * load the custom alarm strategies from the database
     *
     * @return
     */
    public List<AlarmStrategy> getCustomAlarmStrategyList() {
        List<DBAlarmStrategyCustom> dbstrategycuslist = StrategyDBHelper
                .getInstance().getCustomStrategyStringList();
        List<AlarmStrategy> strategyCusList = DBStratCusList2StrategyList(dbstrategycuslist);
        return strategyCusList;
    }

    /**
     * add a custom alarm strategy to the server
     *
     * @param strategy
     * @param cb
     * @return
     */
    public int addCustomStrategy(final AlarmStrategy strategy,
                                 final MYCallBack cb) {
        WapiUtilEx.addCustomAlarmStrategy(strategy, new MYCallBack() {
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
                        String strategy_id = obj.getString("strategy_id");
                        strategy.setStrategy_id(strategy_id);
                        DBAlarmStrategyCustom dbstrategy = strategyCustom2DBStruct(strategy);
                        StrategyDBHelper.getInstance()
                                .insertAlarmStrategyCustom(dbstrategy);

						/*
                         * mStrategyCustomList.add(strategy); for (int i = 0; i
						 * < mStrategyCustomList.size(); i++) { AlarmStrategy s
						 * = mStrategyCustomList.get(i); Log.e("wwww",
						 * s.getStrategy_id()); }
						 */
                        if (null != cb)
                            cb.onSuccess(strategy_id);
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });
        return 0;
    }

    /**
     * update some custom alarm strategy
     *
     * @param strategy
     * @param cb
     * @return
     */
    public int setCustomStrategy(final AlarmStrategy strategy,
                                 final MYCallBack cb) {
        WapiUtilEx.setCustomAlarmStrategy(strategy, new MYCallBack() {
            @Override
            public void onError(int code, String message) {

            }

            @Override
            public void onSuccess(String jsonString) {
                try {
                    JSONObject obj = new JSONObject(jsonString);
                    if (obj.getString("result").equals("OK")) {
                        // String strategy_id = obj.getString("strategy_id");
                        // if (strategy_id != strategy.getStrategy_id())

                        DBAlarmStrategyCustom dbstrategy = strategyCustom2DBStruct(strategy);
                        StrategyDBHelper.getInstance().setAlarmStrategyCustom(
                                dbstrategy);
						/*
						 * for (int i = 0; i < mStrategyCustomList.size(); i++)
						 * { AlarmStrategy s = mStrategyCustomList.get(i);
						 * 
						 * if
						 * (s.getStrategy_id().equals(strategy.getStrategy_id(
						 * ))) mStrategyCustomList.set(i, strategy); }
						 */
                        if (null != cb)
                            cb.onSuccess(strategy.getStrategy_id());
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        return 0;
    }

    /**
     * delete a custom alarm strategy from the server
     *
     * @param strategy
     * @param cb
     * @return
     */
    public int delCustomStrategy(final AlarmStrategy strategy,
                                 final MYCallBack cb) {

        // WapiUtilEx.delCustomAlarmStrategy("xx", cb)
        WapiUtilEx.delCustomAlarmStrategy(strategy.getStrategy_id(),
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
                                DBAlarmStrategyCustom dbstrategy = strategyCustom2DBStruct(strategy);
                                StrategyDBHelper.getInstance()
                                        .delAlarmStrategyCustom(dbstrategy);

								/*
								 * for (int i = 0; i <
								 * mStrategyCustomList.size(); i++) {
								 * AlarmStrategy s = mStrategyCustomList.get(i);
								 * if (s.getStrategy_id().equals(strategy.
								 * getStrategy_id()))
								 * mStrategyCustomList.remove(i); }
								 */

                                if (null != cb)
                                    cb.onSuccess(strategy.getStrategy_id());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        return 0;
    }

    DBAlarmStrategyCustom strategyCustom2DBStruct(AlarmStrategy strategy) {
        DBAlarmStrategyCustom dbstrategy = new DBAlarmStrategyCustom();
        dbstrategy.setStrategy_id(strategy.getStrategy_id());
        dbstrategy.setContent(strategy.toJson().toString());

        return dbstrategy;
    }

    // private
    List<DBAlarmStrategyDefault> JSONStr2DBStrategyDefList(String jsonString) {
        JSONArray objStrategyArr;
        List<DBAlarmStrategyDefault> stralst = null;// new
        // ArrayList<DBAlarmStrategyDefault>();
        try {
            objStrategyArr = new JSONArray(jsonString);
            // JSONArray evtarr = obj.getJSONArray("");

            for (int i = 0; i < objStrategyArr.length(); i++) {
                if (null == stralst)
                    stralst = new ArrayList<DBAlarmStrategyDefault>();
                JSONObject obj = objStrategyArr.getJSONObject(i);
                DBAlarmStrategyDefault stra = new DBAlarmStrategyDefault();
                stra.setStrategy_id(obj.getString("strategy_id"));
                stra.setContent(obj.toString());
                stralst.add(stra);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return stralst;
    }

    List<DBAlarmStrategyCustom> JSONStr2DBStrategyCusList(String jsonString) {
        JSONArray objStrategyArr;
        List<DBAlarmStrategyCustom> stralst = null;
        // new ArrayList<DBAlarmStrategyCustom>();
        try {
            objStrategyArr = new JSONArray(jsonString);

            for (int i = 0; i < objStrategyArr.length(); i++) {
                if (null == stralst)
                    stralst = new ArrayList<DBAlarmStrategyCustom>();

                JSONObject obj = objStrategyArr.getJSONObject(i);
                DBAlarmStrategyCustom stra = new DBAlarmStrategyCustom();
                stra.setStrategy_id(obj.getString("strategy_id"));
                stra.setContent(obj.toString());
                stralst.add(stra);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stralst;
    }

    List<AlarmStrategy> DBStratDefList2StrategyList(
            List<DBAlarmStrategyDefault> dbstrategydeflist) {
        List<AlarmStrategy> slist = null;
        for (int i = 0; i < dbstrategydeflist.size(); i++) {
            if (null == slist)
                slist = new ArrayList<AlarmStrategy>();
            DBAlarmStrategyDefault dbstra = dbstrategydeflist.get(i);
            String content = dbstra.getContent();
            AlarmStrategy strat = new AlarmStrategy();
            strat.setStrategy_type(DEFAULT_STRATEGY_TYPE);
            strat.fromJSONString(content);
            slist.add(strat);
        }

        return slist;
    }

    List<AlarmStrategy> DBStratCusList2StrategyList(
            List<DBAlarmStrategyCustom> dbstrategycuslist) {
        List<AlarmStrategy> slist = null;
        for (int i = 0; i < dbstrategycuslist.size(); i++) {
            if (null == slist)
                slist = new ArrayList<AlarmStrategy>();
            DBAlarmStrategyCustom dbstra = dbstrategycuslist.get(i);
            String content = dbstra.getContent();
            AlarmStrategy strat = new AlarmStrategy();
            strat.setStrategy_type(CUSTOM_STRATEGY_TYPE); // strategy type
            strat.fromJSONString(content);
            slist.add(strat);
        }

        return slist;
    }
}
