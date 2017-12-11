package com.itserv.app.http;

import android.util.Log;

import com.app.itserv.jparser.JsonAdvisoryProposalObject;
import com.app.itserv.jparser.JsonBaseDelObject;
import com.app.itserv.jparser.JsonBaseManagerObject;
import com.app.itserv.jparser.JsonDataDictionaryObject;
import com.app.itserv.jparser.JsonFarTaskDistrObject;
import com.app.itserv.jparser.JsonGreenhouseManagerObject;
import com.app.itserv.jparser.JsonMerchantInfoObject;
import com.app.itserv.jparser.JsonMySysNoticeObject;
import com.app.itserv.jparser.JsonPlanSchemeObject;
import com.yycloud.app.utils.WapiUtil;
import com.yycloud.core.config.ECMSConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者： 周作威 on 2017/9/19 11:11.
 * 类描述：网络帮助类(主要用来管理参数)
 */
public class HttpManager {
    public HttpUtils httpUtils;

    private HttpManager() {
        httpUtils = HttpUtils.getInstance();
    }

    private static class SingletonHolder {
        static HttpManager INSTANCE = new HttpManager();
    }

    public static HttpManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 作者： 周作威 on 2017/9/19 11:16
     * 描述: 登录
     */
    public void doLoginRequest(String tag, String username, String pwd, HttpCallBack callBack) {
        final Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("psd", pwd);
        String url = WapiUtil.WAPI_USER_LOGIN_URL1;
        httpUtils.httpPost(tag, url, map, callBack);
    }

    /**
     * 登录日志
     * haoruigang
     *
     * @param tag
     * @param token
     * @param currTenantId
     * @param callBack
     */
    public void doLoginLog(String tag, String token, String currTenantId, HttpCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        String url = WapiUtil.WAPI_USER_LOGIN_LOG_URL1;
        httpUtils.httpPost(tag, url, map, callBack);
    }

    /**
     * 退出  周作威  2017/9/21 13:17
     */
    public void exit(String token, String currTenantId, String userName, HttpCallBack callBack) {
        String url = WapiUtil.WAPI_USER_EXIT_URL;
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        map.put("username", userName);
        httpUtils.httpPost(null, url, map, callBack);
    }

    /**
     * 关于我们  周作威 2017年9月19日 19:52:49
     *
     * @param tag
     * @param token
     * @param currTenantId
     * @param clientCode
     * @param callBack
     */
    public void doAbout(String tag, String token, String currTenantId, String clientCode, HttpCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        map.put("clientCode", clientCode);
        String url = WapiUtil.WAPI_USER_ABOUT_URL;
        httpUtils.httpPost(tag, url, map, callBack);
    }

    /**
     * 用户协议  周作威 2017年9月19日 19:52:49
     *
     * @param tag
     * @param token
     * @param currTenantId
     * @param clientCode
     * @param callBack
     */
    public void doprotacol(String tag, String token, String currTenantId, String clientCode, HttpCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        map.put("clientCode", clientCode);
        String url = WapiUtil.WAPI_USER_PROTACOL_URL;
        httpUtils.httpPost(tag, url, map, callBack);
    }

    /**
     * 广告  周作威 2017年9月19日 19:52:49
     *
     * @param tag
     * @param currTenantId
     * @param token
     * @param clientCode
     * @param callBack
     * @description：广告列表请求方法
     * @author：gang
     * @date time：2017-6-9 上午9:52:52
     */
    public void advertising(String tag, String currTenantId, String token, String clientCode, HttpCallBack callBack) {
        // 设置post需要传递的参数
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        map.put("clientCode", clientCode);
        // v3广告列表路径
        String url = WapiUtil.WAPI_USER_ADVER_URL;
        Log.i(tag, "广告列表_url -> " + url);
        httpUtils.httpPost(tag, url, map, callBack);
    }

    /**
     * @param tag
     * @param token
     * @param currTenantId
     * @param tenantId
     * @param httpCallBack
     * @description：商户信息
     * @author：haoruigang
     * @date time：2017-6-12 下午4:55:00
     */
    public void domerchantInfo(String tag, String token, String currTenantId, String tenantId, HttpCallBack<JsonMerchantInfoObject> httpCallBack) {
        // 设置post需要传递的参数
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        map.put("tenantId", tenantId);
        // v3商户信息路径
        String url = WapiUtil.WAPI_MERCHANT_INFO_URL;
        httpUtils.httpPost(tag, url, map, httpCallBack);
    }


    /**
     * 我的每日农事任务
     * 2017-9-22 20:03:16 haoruigang
     *
     * @param tag
     * @param token
     * @param currTenantId
     * @param businessType
     * @param currUserId
     * @param callBack
     */
    public void myTodayTaskList(String tag, String token, String currTenantId, String businessType, String currUserId, HttpCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        map.put("businessType", businessType);
        map.put("headBy", currUserId);
        String url = WapiUtil.WAPI_TODAY_TASK_LIST_URL;
        httpUtils.httpPost(tag, url, map, callBack);
    }

    /**
     * 当日任务明细
     *
     * @param tag
     * @param token
     * @param currTenantId
     * @param todayTaskId
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017年09月05日15:59:30
     */
    public void todaytaskDetail(String tag, String token, String currTenantId, String todayTaskId, HttpCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        map.put("workTaskDayId", todayTaskId);
        String url = WapiUtil.WAPI_TODAY_TASK_DETAIL_URL;
        httpUtils.httpPost(tag, url, map, callBack);
    }


    /**
     * 取消每日农事任务
     *
     * @param tag
     * @param token
     * @param currTenantId
     * @param todayTaskId
     * @param callBack
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017年09月09日13:05:15
     */
    public void mytodayfartaskCancel(String tag, String token, String currTenantId, String todayTaskId, HttpCallBack<JsonFarTaskDistrObject> callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        map.put("allotContent ", null);
        map.put("workTaskDayId", todayTaskId);//每日农事任务Id
        // v3取消每日农事任务取消
        String url = WapiUtil.WAPI_MY_FAR_TASK_CANCEL_URL;
        httpUtils.httpPost(tag, url, map, callBack);
    }


    /**
     * 农事记录列表
     * haoruigang   2017-9-26 17:36:19
     *
     * @param tag
     * @param token
     * @param currTenantId
     * @param currUserId
     * @param ghouseId
     * @param taskId
     * @param farmingCategory
     * @param createBy
     * @param createDateStart
     * @param createDateEnd
     * @param callBack
     */
    public void apiFramingRecordGetList(String tag, String token, String currTenantId, String currUserId,
                                        String ghouseId, String taskId, String farmingCategory, String createBy,
                                        String createDateStart, String createDateEnd, HttpCallBack callBack) {
        // 设置post需要传递的参数
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        map.put("headBy", currUserId);// 负责人
        map.put("ghouseId", ghouseId);// 大棚ID
        map.put("taskId", taskId);// 任务ID
        map.put("farmingCategory", farmingCategory);// 农事分类
        map.put("createBy", createBy);// 记录人
        map.put("createDateStart", createDateStart);// 记录开始时间
        map.put("createDateEnd", createDateEnd);// 记录结束时间
        String url = WapiUtil.WAPI_FRAMING_RECORD_GETLIST_URL;
        httpUtils.httpPost(tag, url, map, callBack);
    }

    /**
     * 所属大棚
     * haoruigang 2017-9-26 17:36:55
     *
     * @param tag
     * @param token
     * @param currTenantId
     * @param callBack
     */
    public void usergreenhouselist(String tag, String token, String currTenantId, HttpCallBack callBack) {
        String url = WapiUtil.WAPI_USER_GREEN_HOUSE_LIST_URL;
        // 设置post需要传递的参数
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        httpUtils.httpPost(tag, url, map, callBack);
    }

    /**
     * 记录人(员工列表)
     * haoruigang 2017-9-26 17:37:13
     *
     * @param tag
     * @param token
     * @param currTenantId
     * @param ghouseId
     * @param callBack
     */
    public void ghouseemp(String tag, String token, String currTenantId, String ghouseId, HttpCallBack callBack) {
        String url = WapiUtil.WAPI_GHOUSE_USER_LIST_URL;
        // 设置post需要传递的参数
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        map.put("greenhouseId", ghouseId);
        httpUtils.httpPost(tag, url, map, callBack);
    }

    /**
     * 农事分类数据字典表
     * hoaruigang 2017-9-26 17:37:29
     *
     * @param tag
     * @param key
     * @param callBack
     */
    public void apiTypeGetList(String tag, String key, HttpCallBack callBack) {
        // 设置post需要传递的参数
        Map<String, String> map = new HashMap<String, String>();
        map.put("key", key);
        String url = WapiUtil.WAPI_DATA_DICTINOARY_URL;
        httpUtils.httpPost(tag, url, map, callBack);
    }

    /**
     * 所属任务名称
     * haoruigang 2017-9-26 17:37:42
     *
     * @param tag
     * @param token
     * @param currTenantId
     * @param callBack
     */
    public void fartaskList(String tag, String token, String currTenantId, HttpCallBack callBack) {
        String url = WapiUtil.WAPI_FAR_TASK_LIST_URL;
        // 设置post需要传递的参数
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        map.put("assignStatus", "ALLOCATED");
        httpUtils.httpPost(tag, url, map, callBack);
    }

    /**
     * hoaruigang
     * 2017-6-9 下午7:52:14
     * 用户列表请求
     *
     * @param tag
     * @param token
     */
    public void doUserGetList(String tag, String token, String currTenantId, HttpCallBack callback) {
        // v3用户列表路径
        String url = WapiUtil.WAPI_USER_GET_LIST_URL;
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        httpUtils.httpPost(tag, url, map, callback);
    }

    /**
     * haoruigang
     * 2017-7-25 上午11:25:15
     * 数据字典
     *
     * @param tag
     * @param key
     * @param callback
     */
    public void doApiTypeGetList(String tag, String key, HttpCallBack<JsonDataDictionaryObject> callback) {
        // v3数据字典路径
        String url = WapiUtil.WAPI_DATA_DICTINOARY_URL;
        Log.i(tag, "数据字典请求_url -> " + url);
        Map<String, String> map = new HashMap<>();
        map.put("key", key);// 使用状态
        Log.i(tag, "数据字典请求 map-> " + map);
        httpUtils.httpPost(tag, url, map, callback);
    }

    /**
     * 大棚网关列表
     * haoruigang 2017-9-26 17:38:02
     *
     * @param tag
     * @param token
     * @param currTenantId
     * @param callBack
     */
    public void gHouseGateMoSmartList(String tag, String token, String currTenantId, HttpCallBack callBack) {
        String url = WapiUtil.WAPI_GHOUSE_GATE_MOSMART_LIST_URL;
        // 设置post需要传递的参数
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        map.put("teantId", currTenantId);
        httpUtils.httpPost(tag, url, map, callBack);
    }

    /**
     * 大棚环境平均值
     * haoruigang 2017-9-26 17:38:13
     *
     * @param tag
     * @param token
     * @param currTenantId
     * @param sgateSN
     * @param callBack
     */
//    public void gHouseAverage(String tag, String token, String currTenantId, String sgateSN, HttpCallBack callBack) {
//        String url = WapiUtil.WAPI_GHOUSE_AVERAGE_URL;
//        // 设置post需要传递的参数
//        Map<String, String> map = new HashMap<>();
//        map.put("token", token);
//        map.put("currTenantId", currTenantId);
//        map.put("teantId", currTenantId);
//        map.put("sgateSN", sgateSN);
//        httpUtils.httpPost(tag, url, map, callBack);
//    }
    public void gHouseAverage(String tag, String token, String currTenantId, String gHouseId, HttpCallBack callBack) {
        String url = WapiUtil.WAPI_GHOUSE_AVERAGE_URL;
        // 设置post需要传递的参数
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        map.put("teantId", currTenantId);
        map.put("gHouseId", gHouseId);
        httpUtils.httpPost(tag, url, map, callBack);
    }

    /**
     * 大棚详情
     * haoruigang 2017-9-26 17:38:25
     *
     * @param tag
     * @param token
     * @param currTenantId
     * @param greenhouseId
     * @param callBack
     */
    public void gHouseParticulars(String tag, String token, String currTenantId, String greenhouseId, HttpCallBack callBack) {
        String url = WapiUtil.WAPI_GHOUSE_EXAVIEW_URL;
        // 设置post需要传递的参数
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        map.put("teantId", currTenantId);
        map.put("greenhouseId", greenhouseId);
        httpUtils.httpPost(tag, url, map, callBack);
    }

    /**
     * 传感器折线图
     * haoruigang 2017-9-26 17:38:41
     *
     * @param tag
     * @param token
     * @param currTenantId
     * @param devType
     * @param devSN
     * @param scope
     * @param updateDate_begin
     * @param updateDate_end
     * @param callBack
     */
    public void gHouseSingleSensor(String tag, String token, String currTenantId, String devType, String devSN,
                                   String scope, String updateDate_begin, String updateDate_end, HttpCallBack callBack) {
        String url = WapiUtil.WAPI_GHOUSE_Env_AVERAGE_SENSOR_URL;
        // 设置post需要传递的参数
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        map.put("devType", devType);
        map.put("devSN", devSN);
        map.put("scope", scope);
        map.put("updateDate_begin", updateDate_begin);
        map.put("updateDate_end", updateDate_end);
        httpUtils.httpPost(tag, url, map, callBack);
    }

    // 环境平均折线图
    public void gHouseAveragSensor(String tag, String token, String currTenantId, String sgateSN,
                                   String scope, String updateDate_begin, String updateDate_end, HttpCallBack callBack) {
        String url = WapiUtil.WAPI_GHOUSE_LINE_CHART_URL;
        // 设置post需要传递的参数
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        map.put("sgateSN", sgateSN);
        map.put("scope", scope);
        map.put("updateDate_begin", updateDate_begin);
        map.put("updateDate_end", updateDate_end);
        httpUtils.httpPost(tag, url, map, callBack);
    }

    // 网关下的所有设备
    public void gAllDeviceInfo(String tag, String sgateSN, HttpCallBack callBack) {
        String url = WapiUtil.WAPI_HTTP_SMARTGATE_TOW_URL + sgateSN + WapiUtil.INFO_URL;
        // 设置post需要传递的参数
        Map<String, String> map = new HashMap<>();
        httpUtils.httpGet(tag, url, map, callBack);
    }

    // 网关下的所有传感器
    public void gAllSensorInfo(String tag, String token, String currTenantId, String sgateSN, HttpCallBack callBack) {
        String url = WapiUtil.WAPI_GHOUSE_DATA_MOSMART_SENSOR_LIST_URL;
        // 设置post需要传递的参数
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        map.put("sgateSN", sgateSN);
        httpUtils.httpPost(tag, url, map, callBack);
    }

    public void getLoadCategory(String tag, HttpCallBack callBack) {
        String url = ECMSConfig.CONFIG_GET_COLUMN_LIST_URL + "&columnid=" + ECMSConfig.PID + "&level=1";
        httpUtils.httpPost(tag, url, null, callBack);
    }


    //获取账户信息
    public void gUserInfo(String tag, HttpCallBack callBack) {
        String url = WapiUtil.WAPI_USER_INFO_URL;
        Map<String, String> map = new HashMap<>();
        httpUtils.httpGet(tag, url, map, callBack);
    }

    /**
     * 基地列表
     * haoruigang 2017-9-26 17:40:54
     *
     * @param tag
     * @param token
     * @param currTenantId
     * @param callBack
     */
    public void basemanagerlist(String tag, String token, String currTenantId, HttpCallBack<JsonBaseManagerObject> callBack) {
        // v3基地列表路径
        String url = WapiUtil.WAPI_BASE_MANAGER_LIST_URL;
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        httpUtils.httpPost(tag, url, map, callBack);
    }

    /**
     * 删除基地
     * haoruigang 2017-9-26 17:40:42
     *
     * @param tag
     * @param token
     * @param currTenantId
     * @param baseId
     * @param callBack
     */
    public void basedelete(String tag, String token, String currTenantId, String baseId, HttpCallBack<JsonBaseDelObject> callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        map.put("greenbaseId", baseId);
        // v3基地删除路径
        String url = WapiUtil.WAPI_BASE_DELETE_URL;
        httpUtils.httpPost(tag, url, map, callBack);
    }

    /**
     * 基地排序
     * haoruigang 2017-9-26 17:40:29
     *
     * @param tag
     * @param token
     * @param currTenantId
     * @param greenbaseIds
     * @param callBack
     */
    public void basesort(String tag, String token, String currTenantId, String greenbaseIds, HttpCallBack<JsonBaseDelObject> callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        map.put("greenbaseIds", greenbaseIds);
        // v3基地排序路径
        String url = WapiUtil.WAPI_BASE_SORT_URL;
        httpUtils.httpPost(tag, url, map, callBack);
    }

    /**
     * 大棚排序
     * haoruigang 2017-9-26 17:40:18
     *
     * @param tag
     * @param token
     * @param currTenantId
     * @param baseId
     * @param callBack
     */
    public void basegreenHouse(String tag, String token, String currTenantId, String baseId, HttpCallBack callBack) {
        // v3基地中大棚列表路径
        String url = WapiUtil.WAPI_BASE_GREEN_HOUSE_URL;
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        map.put("greenbaseId", baseId);
        httpUtils.httpPost(tag, url, map, callBack);
    }

    /**
     * 大棚排序提交
     * haoruigang 2017-9-26 17:40:04
     *
     * @param tag
     * @param token
     * @param currTenantId
     * @param baseId
     * @param greenhouseIds
     * @param callBack
     */
    public void greenHouseSort(String tag, String token, String currTenantId, String baseId, String greenhouseIds, HttpCallBack callBack) {
        // v3基地排序路径
        String url = WapiUtil.WAPI_BASE_GHOUSE_SORT_URL;
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        map.put("greenbaseId", baseId);
        map.put("greenhouseIds", greenhouseIds);
        httpUtils.httpPost(tag, url, map, callBack);
    }


    /**
     * 投诉建议列表请求方法
     *
     * @param tag
     * @param token
     * @param currTenantId
     * @param replyStatus
     * @param callBack
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-6-24 下午3:55:19
     */
    public void doProposal(String tag, String token, String currTenantId, String replyStatus, HttpCallBack<JsonAdvisoryProposalObject> callBack) {
        // v3投诉建议列表路径
        String url = WapiUtil.WAPI_USER_PROPOSAL_URL;
        // 设置post需要传递的参数
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        map.put("adviceReplyStatus", replyStatus);
        httpUtils.httpPost(tag, url, map, callBack);
    }

    /**
     * 系统公告，未读
     *
     * @param token
     * @param currTenantId
     * @param callBack
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-18 上午11:25:15
     */
    public void apiunReadNoticeControllerList(String tag, String token, String currTenantId, HttpCallBack<JsonMySysNoticeObject> callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        map.put("isRead", "0");
        // v3系统公告列表请求路径
        String url = WapiUtil.WAPI_NOTICE_CONTEOLLER_LIST_URL;
        httpUtils.httpPost(tag, url, map, callBack);
    }

    /**
     * 系统公告，已读
     *
     * @param token
     * @param currTenantId
     * @param callBack
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-18 上午11:25:15
     */
    public void apiReadNoticeControllerList(String tag, String token, String currTenantId, HttpCallBack<JsonMySysNoticeObject> callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        map.put("isRead", "1");
        // v3系统公告列表请求路径
        String url = WapiUtil.WAPI_NOTICE_CONTEOLLER_LIST_URL;
        httpUtils.httpPost(tag, url, map, callBack);
    }

    /**
     * 大棚管理列表请求方法
     *
     * @param tag
     * @param token
     * @param currTenantId
     * @param callBack
     * @return void
     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-10 下午4:00:04
     */
    public void ghousemanagerlist(String tag, String token, String currTenantId, HttpCallBack<JsonGreenhouseManagerObject> callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        // v3大棚管理列表路径
        String url = WapiUtil.WAPI_GHOUSE_MANAGER_LIST_URL;
        httpUtils.httpPost(tag, url, map, callBack);
    }

    /**
     * 农事种植计划列表
     *
     * @param tag
     * @param token
     * @param currTenantId
     * @param strchemebase
     * @param strgreenhouse
     * @param strplanname
     * @param strquicode
     * @param strstartplantime
     * @param strstopplantime
     * @param httpCallBack     * @author 作者 E-mail: haoruigang
     * @date 创建时间：2017-7-5 上午11:25:15
     */
    public void planschemelist(String tag, String token, String currTenantId, String strchemebase, String strgreenhouse, String strplanname, String strquicode, String strstartplantime, String strstopplantime, HttpCallBack<JsonPlanSchemeObject> callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        map.put("gbaseId", strchemebase);
        map.put("ghouseId", strgreenhouse);
        map.put("planFullname", strplanname);
        map.put("cropCategoryDefine", strquicode);
        map.put("planPlantdateStart", strstartplantime);
        map.put("planPlantdateEnd", strstopplantime);
        // v3农事种植列表路径
        String url = WapiUtil.WAPI_PLAN_SCHEME_LIST_URL;
        Log.i(tag, "种植计划列表_url -> " + url);
        httpUtils.httpPost(tag, url, map, callBack);
    }
    //系统公告，未读
    public void gHouseGetList(String tag, String token, String currTenantId,  HttpCallBack callBack){
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        // v3系统公告列表请求路径
        String url = WapiUtil.WAPI_GHOUSE_MANAGER_LIST_URL;
        httpUtils.httpPost(tag, url, map, callBack);
    }
    //大棚下所有设备
    public void gHouseGetAllRelayList(String tag, String token, String currTenantId,String gHouseId,  HttpCallBack callBack){
        Map<String, String> map = new HashMap<String, String>();
        map.put("token", token);
        map.put("currTenantId", currTenantId);
        map.put("gHouseId", gHouseId);
        //大棚下所有设备
        String url = WapiUtil.WAPI_GHOUSE_DATA_ALL_RELAY_LIST_URL;
        httpUtils.httpPost(tag, url, map, callBack);
    }

}


