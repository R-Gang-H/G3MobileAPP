package com.yycloud.app.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.ParseException;
import android.util.Log;

import com.yycloud.core.beans.CameraStatus;
import com.yycloud.core.cache.GlobalConst;

public class WapiUtil extends WAPI {
    private static final String TAG = "WapiUtil";
    private static String mSessionToken = "";

    public static void saveSessionToken(String session_token) {
        mSessionToken = session_token;
    }

    public static String getSessionToken() {
        //return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InRlc3QwMSIsImNyZWF0ZWRBdCI6IjIwMTctMDgtMjVUMDc6MDY6NDIuMTk0WiIsImlhdCI6MTUwMzY0NDgwMn0.o4res1z8Vk0lw59tQVHBJl6OKlVx12TpnGJV-Cdh1eE";
       // return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImhvbWV0ZXN0IiwiY3JlYXRlZEF0IjoiMjAxNi0xMi0wMVQwNTozNzowNy4zNTVaIiwiaWF0IjoxNDgwNTcwNjI3fQ.0V7g2lY9mRGeZY2UCHHpbhm_nIAGboHZG1HzYq-hQ5o";
       // return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6Inpob3V6dW93ZWkiLCJjcmVhdGVkQXQiOiIyMDE3LTA5LTA3VDAyOjM0OjM0LjE0N1oiLCJpYXQiOjE1MDQ3NTE2NzR9.tPtMiK0g5uwbuvrjbyCLhcD8vSIV5mO_li4zEpCPuAU";
        return mSessionToken;
    }

    /**
     * 用户添加需要查看的设备
     *
     * @param devUuid
     *            id
     * @return
     */
    public static String addDevice(String devUuid) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("sn", devUuid));
        return http_post_content(WAPI_USER_SMARTGATE_URL, params,
                getSessionToken());
    }

    /**
     * 获取设备列表
     */
    public static String getDeviceList(int page, int limit) {
        String url = WAPI_USER_SMARTGATE_URL + "?page=" + page + "&limit="
                + limit;
        String msg = get_content_from_remote_url(url, getSessionToken());
        return msg;
    }

    public static String getDeviceinfos(String sn) {
        //String url = WAPI_URL + "/enddevices/" + sn + "/infos";
        String url =  WAPI_HTTP_TOW_BASE +":" +SERVER_PORT +"/api"  + "/enddevices/" + sn + "/infos";
        String msg = get_content_from_remote_url(url, getSessionToken());
        return msg;

    }

    /**
     * 获取设备(主机)信息
     */
    public static String getDeviceInfo(String devUuid) {
        // String url = WAPI_DEVICE_INFO_URL + "?dev_uuid=" + devUuid;
        String url = WAPI_SMARTGATE_URL + devUuid + INFO_URL;
        String msg = get_content_from_remote_url(url, getSessionToken());

        return msg;
    }

    /**
     * 获取设备信息
     */
    public static String getDeviceInfoByParams(String devUuid, String common,
                                               String components) {
        // String url = WAPI_DEVICE_INFO_URL + "?dev_uuid=" + devUuid;
        String url = WAPI_SMARTGATE_URL + devUuid + INFO_URL;
        if (!"".equals(components)) {
            url = url + "&components=" + components;
        }
        if (!"".equals(common)) {
            url = url + "&common=" + common;
        }
        String msg = get_content_from_remote_url(url, getSessionToken());
        return msg;
    }
    /**
     * 获取告警忽略列表
     */
    public static String getAlarmIgnore() {
        String url = WAPI_USER_ALARMSTARATEGIES_IGORE;
        String msg = get_content_from_remote_url(url, getSessionToken());
        return msg;
    }
    /**
     * 设置告警忽略列表
     */
    public static String setAlarmIgnore(String json) {
        String url = WAPI_USER_ALARMSTARATEGIES_IGORE;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("alarmIgnore", json));

        return http_post_content(url, params,
                getSessionToken());
    }
    /**
     删除策略
     */
    public static String deleteDefaultAlarmStrategies(String strategyId){
        String url = WAPI.WAPI_CUSTOM_ALARM_STRATEGY_URL+strategyId;
        String msg = WAPI.http_delete_content(url, getSessionToken());
        return msg;
    }

    /**
     * 获取策略 alarm
     * @return
     */
    public static String getCustomAlarmStrategies(){
        String url = WAPI.WAPI_CUSTOM_ALARM_STRATEGY_URL;
        String msg = WAPI.get_content_from_remote_url(url, getSessionToken());
        return msg;
    }
    /**
     * 添加策略 alarm
     * @return
     */
    public static String addCustomAlarmStrategies(String json){
        String url = WAPI.WAPI_CUSTOM_ALARM_STRATEGY_URL;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("alarm_strategy", json));
        String msg = WAPI.http_post_content(url, params, getSessionToken());
        return msg;
    }
    /**
     * 修改策略 alarm
     * @return
     */
    public static String changeCustomAlarmStrategies(String strategyId, String json){
        String url = WAPI.WAPI_CUSTOM_ALARM_STRATEGY_URL+strategyId;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("alarm_strategy", json));
        String msg = WAPI.http_put_content(url, params, getSessionToken());
        return msg;
    }
    /**
     * 获取终端设备的信息
     *
     * @param sn
     * @return
     */
    public static String getEndDeviceInfo(String sn) {
        String url = WAPI_HTTP_SMARTGATE_TOW_URL + sn + INFO_URL;
        String msg = get_content_from_remote_url(url,
                getSessionToken());
        return msg;
    }

    /**
     * 关闭单继电机的开关
     *
     * @param devUuid
     * @param componentId
     * @param
     * @return
     */
    public static String opeErelay(String devUuid, String componentId,
                                   String action) {

        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("ccp_token", ccp_token));
        list.add(new BasicNameValuePair("smartgate_sn", devUuid));

        JSONObject params = new JSONObject();
        try {
            params.put("cmd", CMD_ERELAY_SWITCH);
            params.put("status", action);
            params.put("sn", componentId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        list.add(new BasicNameValuePair("params", params.toString()));

        // for (int i = 0; i < list.size(); i++) {
        // Log.v("htt", list.get(i).getName() + "====" +
        // list.get(i).getValue());
        // }
        //String msg = http_post_content(WAPI_REMOTE_DO_CMD_URL, list,
        //	getSessionToken());
        String msg = http_post_content(WAPI_REMOTE_DO2_CMD_URL, list,
                getSessionToken());

        return msg;
    }

    /**
     * 电气柜的单继电机的开关
     * */
    public static String opeErelayRealy(String devUuid, String componentId,
                                        String action, String poolIndex) {

        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("ccp_token", ccp_token));
        list.add(new BasicNameValuePair("smartgate_sn", devUuid));

        JSONObject params = new JSONObject();
        try {
            params.put("cmd", CMD_ERELAY_SWITCH);
            params.put("status", action);
            params.put("sn", componentId);
            params.put("poolIndex", poolIndex);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        list.add(new BasicNameValuePair("params", params.toString()));

        //String msg = http_post_content(WAPI_REMOTE_DO_CMD_URL, list,
        //	getSessionToken());

        String msg = http_post_content(WAPI_REMOTE_DO2_CMD_URL, list,
                getSessionToken());

        return msg;
    }

    /**
     * 操作双继电器的开关
     *
     * @param devUuid
     * @param componentId
     * @param action
     *            forward：前进 back：后退
     * @return
     */
    public static String opeErelay2(String devUuid, String componentId,
                                    String action) {

        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("ccp_token", ccp_token));
        list.add(new BasicNameValuePair("smartgate_sn", devUuid));

        JSONObject params = new JSONObject();
        try {
            params.put("cmd", CMD_ERELAY2_SWITCH);
            params.put("action", action);
            params.put("sn", componentId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        list.add(new BasicNameValuePair("params", params.toString()));

        //String msg = http_post_content(WAPI_REMOTE_DO_CMD_URL, list,
        //	getSessionToken());
        String msg = http_post_content(WAPI_REMOTE_DO2_CMD_URL, list,
                getSessionToken());
        // Log.e("wapiutil-list====", list.toString());

        return msg;
    }

    /**
     * 操作双继电器的策略
     *
     * @param devUuid
     * @param componentId
     * @param
     *
     * @return
     */
    public static String temperatureControlStrategy(String devUuid,
                                                    String componentId, JSONObject json) {

        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("ccp_token", ccp_token));
        list.add(new BasicNameValuePair("smartgate_sn", devUuid));

        JSONObject params = new JSONObject();
        try {
            params.put("cmd", CMD_ERELAY2_SWITCH);
            params.put("action", "ventilationone12config");
            params.put("configinfo", json);
            params.put("sn", componentId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        list.add(new BasicNameValuePair("params", params.toString()));

        //String msg = http_post_content(WAPI_REMOTE_DO_CMD_URL, list,
        //	getSessionToken());
        String msg = http_post_content(WAPI_REMOTE_DO2_CMD_URL, list,
                getSessionToken());

        return msg;
    }

    /**
     * 操作水阀的策略
     *
     * @param devUuid
     * @param componentId
     * @param
     *
     * @return
     */
    public static String waterControlStrategy(String devUuid, String componentId,
                                              JSONObject json) {

        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("ccp_token", ccp_token));
        list.add(new BasicNameValuePair("smartgate_sn", devUuid));

        JSONObject params = new JSONObject();
        try {
            params.put("cmd", CMD_ERELAY_SWITCH);
            params.put("action", "setwatervalveconfig");
            params.put("configinfo", json);
            params.put("sn", componentId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        list.add(new BasicNameValuePair("params", params.toString()));

        //String msg = http_post_content(WAPI_REMOTE_DO_CMD_URL, list,
        //	getSessionToken());
        String msg = http_post_content(WAPI_REMOTE_DO2_CMD_URL, list,
                getSessionToken());

        return msg;
    }
    /**
     * 操作电器柜
     *
     * @param devUuid
     * @param componentId
     * @param action
     *            forward：前进 back：后退
     * @return
     */
    public static String opeErelay2Realy(String devUuid, String componentId,
                                         String action, String poolIndex) {

        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("ccp_token", ccp_token));
        list.add(new BasicNameValuePair("smartgate_sn", devUuid));

        JSONObject params = new JSONObject();
        try {
            params.put("cmd", CMD_ERELAY2_SWITCH);
            params.put("action", action);
            params.put("sn", componentId);
            params.put("poolIndex", poolIndex);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        list.add(new BasicNameValuePair("params", params.toString()));

        //	String msg = http_post_content(WAPI_REMOTE_DO_CMD_URL, list,
        //	getSessionToken());
        String msg = http_post_content(WAPI_REMOTE_DO2_CMD_URL, list,
                getSessionToken());
        // Log.e("wapiutil-list====", list.toString());

        return msg;
    }

    /**
     * 获取指定设备的图片信息
     *
     * @param devUuid
     *            指定大棚设备
     * @param startDate
     *            指定日期，按 YYYY-MM-DD来传
     * @param page
     *            限定传第几页的图片, 默认为0
     * @param limit
     *            限定传几张图片
     * @param
     * @return
     */
    public static String getPictureList(String devUuid, String startDate,
                                        int page, int limit) {

        String url = WAPI_PICTURE_LIST_URL + "?dev_uuid=" + devUuid
                + "&start_date=" + startDate + "&page=" + page + "&limit="
                + limit;
        String msg = get_content_from_remote_url(url, getSessionToken());

        return msg;
    }

    /**
     * 用户删除需要查看的设备
     *
     * @param devUuid
     *            id
     * @return
     */
    public static String rmDevice(String devUuid) {
		/*
		 * List<NameValuePair> params = new ArrayList<NameValuePair>();
		 * params.add(new BasicNameValuePair("dev_uuid", devUuid));
		 *
		 * return http_post_content(WAPI_USER_RM_DEVICE_URL, params,
		 * getSessionToken());
		 */
        String url = WAPI_USER_SMARTGATE_URL + "?dev_uuid=" + devUuid;
        return http_delete_content(url, getSessionToken());
    }

    /**
     * 获取指定省市和农作物的总体统计信息(InfomationPage)
     *
     * @param province_name
     *            请按设备信息中给定的省市名称来填写
     * @param city_name
     *            可以指定市，未指定将为'all', 如果添'all',将统计所有市
     * @param plant_name
     *            农作物名称，请按plant农作物列表获取接口中的名称来填写
     * @return
     */
    public static String devgeogroupInfo(String province_name,
                                         String city_name, String plant_name) {
        // 设置HTTP POST请求参数必须用NameValuePair对象
        String url = WAPI_DEVGEOGROUP_INFO_URL + "?province_name="
                + province_name + "&city_name=" + city_name + "&plant_name="
                + plant_name;
        String msg = get_content_from_remote_url(url, getSessionToken());

        return msg;
    }

    /**
     * 获取指定省市和农作物的总体地位位置经纬度坐标信息(InfomationPage)
     *
     * @param province_name
     *            请按设备信息中给定的省市名称来填写
     * @param city_name
     *            可以指定市，未指定将为'all', 如果添'all',将统计所有市
     * @param plant_name
     *            农作物名称，请按plant农作物列表获取接口中的名称来填写
     * @return
     */
    public static String devgeogroupLocationInfo(String province_name,
                                                 String city_name, String plant_name) {
        // 设置HTTP POST请求参数必须用NameValuePair对象
        String url = WAPI_DEVGEOGROUP_LOCATION_FINFO_URL + "?province_name="
                + province_name + "&city_name=" + city_name + "&plant_name="
                + plant_name;
        String msg = get_content_from_remote_url(url, getSessionToken());

        return msg;
    }

    /**
     * 获取用户帐号信息
     *
     * @return
     */
    public static String getUserInfo() {
        String msg = get_content_from_remote_url(WAPI_USER_INFO_URL,
                getSessionToken());
        return msg;
    }

    /**
     * 用户添加报警手机
     *
     * @param contactName
     *            联系人名字
     * @param contactNumber
     *            联系人的电话号码
     * @return
     */
    public static String addContact(String contactName, String contactNumber) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("contact_name", contactName));
        params.add(new BasicNameValuePair("contact_number", contactNumber));
        String msg = http_post_content(WAPI_USER_CONTACT_URL, params,
                getSessionToken());
        return msg;
    }

    /**
     * 用户删除报警手机
     *
     * @param contactNumber
     *            联系人的电话号码
     * @return
     */
    public static String rmContact(String contactNumber) {

		/*
		 * List<NameValuePair> params = new ArrayList<NameValuePair>();
		 * params.add(new BasicNameValuePair("contact_number", contactNumber));
		 * Log.v("11111111111", WAPI_USER_CONTACT_URL+params); return
		 * http_post_content(WAPI_USER_CONTACT_URL, params, getSessionToken());
		 */

        String url = WAPI_USER_CONTACT_URL + "?contact_number=" + contactNumber;
        return http_delete_content(url, getSessionToken());
    }

    /**
     * 更新大棚的别名
     *
     * @param devUuid
     * @param alias
     * @return
     */
    public static String updateDevAlias(String devUuid, String alias) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("dev_uuid", devUuid));
        params.add(new BasicNameValuePair("alias", alias));
        // String msg = http_post_content(WAPI_DEVICE_DEV_ALIAS_URL, params,
        // getSessionToken());

        // String url = WAPI_SMARTGATE_URL + "devUuid" + "/alias";
        String url = WAPI_SMARTGATE_URL + devUuid + "/alias";
        // /api/smartgates/:id/alias'
        String msg = http_put_content(url, params, getSessionToken());
        return msg;
    }

    /**
     * 更新设备的别名
     *
     * @param
     * @param componentId
     * @param alias
     * @return
     */
    public static String updateDevComponentAlias(/* String devUuid, */
                                                 String componentId, String alias) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        // params.add(new BasicNameValuePair("dev_uuid", devUuid));
        params.add(new BasicNameValuePair("component_id", componentId));
        params.add(new BasicNameValuePair("alias", alias));
        // String msg = http_post_content(WAPI_DEVICE_DEV_COMPONENT_ALIAS_URL,
        // params, getSessionToken());

        String url = WAPI_ENDDEVICE_URL + componentId + ALIAS_URL;
        String msg = http_put_content(url, params, getSessionToken());
        return msg;
    }

    /**
     * 获取设备中组件的自动化时间表
     *
     * @param devUuid
     * @param componentId
     * @return
     */
    public static String getComponentSchedule(String devUuid, String componentId) {
        String url = WAPI_DEVICE_COMPONENT_SCHEDULE_URL + "?dev_uuid="
                + devUuid + "&component_id=" + componentId;
        String msg = get_content_from_remote_url(url, getSessionToken());
        return msg;
    }

    /**
     * delete the smartgate from some user
     *
     * @param id
     * @return
     */
    public static String deleteShed(String id) {
        String url = WAPI_DELETE_SHED + id;
        String msg = http_delete_content(url, getSessionToken());
        return msg;
    }

	/*
	 * 控制器的特点是：
	 */

    public static String getAnalysisResult(String sn, String device_type,
                                           String scope) {
        String url = getAnalysisResultUrl(sn, device_type, scope);

        String msg = get_content_from_remote_url(url);
        return msg;
    }

    public static int getTimesmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (int) (cal.getTimeInMillis() / 1000);
    }

    public static String getAnalysisResultUrl(String sn, String type,
                                              String scope) {
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
        String url = WAPI_ANALYSIS_RESULT_RUL + "?sn=" + sn + "&device_type="
                + type + "&date=" + date + "&scope=" + scope;

        return url;
    }

    /**
     * 获取设备中组件的自动化时间表
     *
     * @param
     * @param
     * @return
     */
    public static String getVersion() {
        String url = WAPI.WAPI_GET_VERSION_URL;
        String msg = WAPI
                .get_content_from_remote_url(WAPI.WAPI_GET_VERSION_URL);
        return msg;
    }

    public static int parse_client_version_info(String scontent,
                                                HashMap<String, String> hashmap) {
        if (scontent == null || "".equals(scontent))
            return 1;
        Log.v("updateshed", "检查更新的内容是：scontent==" + scontent);
        int ret = 0;
        try {
            JSONObject jsonObject = new JSONObject(scontent);
            Log.v("updateshed",
                    "jsonObject.getString(version)=="
                            + jsonObject.getString("version"));
            hashmap.put("version", jsonObject.getString("version"));
            hashmap.put("desc", jsonObject.getString("desc"));
            hashmap.put("url", jsonObject.getString("url"));
            hashmap.put("force", jsonObject.getString("force"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    // ///////////////////////////////////////////////////////////////////
    // AlarmStrategy

    /**
     * get the default alarm strategy
     *
     * @return
     */
    public static String getDefaultAlarmStrategies() {
        String url = WAPI.WAPI_DEFAULT_ALARM_STRATEGY_URL;
        String msg = WAPI.get_content_from_remote_url(url, getSessionToken());
        return msg;
    }

    /**
     * set the user's installationId
     *
     * @param installationId
     * @return
     */
    public static String setUserInstallationId(String installationId) {
        String url = WAPI.SET_USER_INSTALLATION_URL;
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("installationId", installationId));
        String msg = WAPI.http_post_content(url, params, getSessionToken());
        return msg;
    }

    // ///////////////////////////////////////////////////////////////// Event

    /**
     * get the events from the server
     *
     * @param readState
     *            read/unread
     * @param beginDate
     * @param endDate
     * @return
     */
    public static String getEvents(String readState, String beginDate,
                                   String endDate) {
        String url = WAPI.USER_ALARMEVENTS_URL;
        url += "?read_state=" + readState + "&begin_date=" + beginDate
                + "&end_date=" + endDate;
        String msg = WAPI.get_content_from_remote_url(url, getSessionToken());
        return msg;
    }

    /**
     * set the events' state from unread to read
     *
     * @param readEventsList
     * @return
     */
    public static String setEventsState(List<String> readEventsList) {
        String readEventsListStr = makeReadEventsList(readEventsList);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("event_id_arr", readEventsListStr));
        String url = WAPI.USER_ALARMEVENTS_URL;
        String msg = WAPI.http_put_content(url, params, getSessionToken());
        return msg;
    }

    /**
     * contact the events' eventIds to string
     *
     * @param readEventsList
     * @return
     */
    private static String makeReadEventsList(List<String> readEventsList) {
        String readEventsListStr = "";
        for (int i = 0; i < readEventsList.size(); i++) {
            readEventsListStr += readEventsList.get(i);
            if (i != readEventsList.size() - 1) {
                readEventsListStr += ",";
            }
        }
        return readEventsListStr;
    }

}
