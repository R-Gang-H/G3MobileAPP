package com.app.itserv.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.itserv.BaseActivity;
import com.app.itserv.adapters.SmartGateListViewGetLimitAdapter;
import com.app.itserv.views.MyPullToRefreshListView;
import com.app.itserv.views.MyPullToRefreshListView.OnRefreshListener;
import com.itserv.app.bean.SmartLimit;
import com.itserv.app.config.Config;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.WAPI;
import com.yycloud.app.utils.WapiUtil;

public class GetLimitActivity extends BaseActivity implements View.OnClickListener {

    private GetLimitActivity context;
    private ImageView backGetLimit;
    private final static int MSG_REFRESH_GETLIMIT = 881;
    private final static int MSG_UPDATE_ADAPTER_GETLIMIT = 661;
    private final static int MSG_ERROR_GETLIMIT = 771;
    private MyPullToRefreshListView mySmartGateListViewGetLimit;
    private ArrayList<SmartLimit> list = new ArrayList<SmartLimit>();
    private SmartGateListViewGetLimitAdapter smartGateListViewGetLimitAdapter;
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_REFRESH_GETLIMIT:
                    //new GetSmartGateTask().execute(Config.QUERY_SMARTGATE_URL);
                    break;
                case MSG_UPDATE_ADAPTER_GETLIMIT:
                    onUpdateAdapter();
                    break;
                case MSG_ERROR_GETLIMIT:
                    ToastUtils.makeTextShort((String) msg.obj);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }

    };

    @Override
    protected int layoutViewId() {
        return R.layout.activity_get_limit_smartgates;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        initView();
    }

    /**
     *  * @Description:初始化view
     *  * @param
     *  * void
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-11-25 下午3:18:56
     */
    public void initView() {

        backGetLimit = (ImageView) findViewById(R.id.activityBackGetLimit);
        mySmartGateListViewGetLimit = (MyPullToRefreshListView) findViewById(R.id.mySmartGateListView_GetLimit);
        //设置下拉是否可用
        mySmartGateListViewGetLimit.setPullDownable(true);
        //设置上拉刷新是否可用
        mySmartGateListViewGetLimit.setPullUpable(false);
        //设置刷新处理
        mySmartGateListViewGetLimit.setOnStartRefreshListener(new OnRefreshListener() {

            @Override
            public void onRefresh() {
                GetSmartGateLimit();
            }
        });

        //加载数据
        new GetSmartGateTask().execute(Config.QUERY_SMARTGATE_URL);


        //设置adapter
        if (list != null) {
            smartGateListViewGetLimitAdapter = new SmartGateListViewGetLimitAdapter(context, list);
            mySmartGateListViewGetLimit.setAdapter(smartGateListViewGetLimitAdapter);
        }
        //点击进入已授权网关
        mySmartGateListViewGetLimit.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if ((list != null) && (list.size() > 0)) {
                    SmartLimit smartLimitNew = list.get(position - 1);//
                    String smartSnNew = smartLimitNew.getSmartsn();
                    String smartNameNew = smartLimitNew.getSmartname();
                    Intent smartLimitDetailIntent = new Intent(context, SmartLimitDetailActivity.class);
                    smartLimitDetailIntent.putExtra("smartlimitsn", smartSnNew);
                    smartLimitDetailIntent.putExtra("smartlimitname", smartNameNew);
                    //获取访客网管标记
                    smartLimitDetailIntent.putExtra("smartvisitortag", list.get(position - 1).getSmartVisitorTag());//
                    startActivity(smartLimitDetailIntent);
                }
            }
        });
        setOnClickListener();
    }

    public void setOnClickListener() {
        backGetLimit.setOnClickListener(this);
    }

    @Override
    protected void onResume() {


        Message msg = handler.obtainMessage();
        msg.what = MSG_REFRESH_GETLIMIT;
        handler.sendMessage(msg);
        super.onResume();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.activityBackGetLimit:
                finish();
                break;

            default:
                break;
        }
    }

    /**
     *  * @Description:更新adapter
     *  * @param
     *  * void
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-11-25 下午3:33:02
     */
    public void onUpdateAdapter() {
        if (smartGateListViewGetLimitAdapter != null) {
            smartGateListViewGetLimitAdapter.notifyDataSetChanged();
        }
    }

    /**
     *  * @Description:获取所有授权网关列表
     *  * @param
     *  * void
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-11-25 下午6:57:32
     */
    public void GetSmartGateLimit() {
        new GetSmartGateTask().execute(Config.QUERY_SMARTGATE_URL);
        mySmartGateListViewGetLimit.onRefreshComplete();
    }

    /**
     *  * @Description:获取网关task
     *  * @author:axin
     *  * @time:2016-11-25 下午2:37:20
     */
    class GetSmartGateTask extends AsyncTask<Object, Void, Void> {

        @Override
        protected Void doInBackground(Object... params) {
            if (list != null) {
                list.clear();
            }
            String querysmarturl = (String) params[0];
            String token = WapiUtil.getSessionToken();
            String querysmartResult = WAPI.http_get_content(querysmarturl, token);
            try {
                JSONObject json = new JSONObject(querysmartResult);
                if (json.has("result")) {
                    if (json.getString("result").equals("OK")) {
                        JSONArray jsonArray = json.getJSONArray("smartgates");
                        Log.v("sss", jsonArray.toString());
                        if (jsonArray != null) {
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonsmart = new JSONObject(jsonArray.get(i).toString());
                                JSONObject jsonsmartlimit = new JSONObject(jsonsmart.getString("smartgate"));
                                SmartLimit smartLimit = new SmartLimit();
                                String smartsn = jsonsmartlimit.getString("sn");
                                String smartname = jsonsmartlimit.getString("dev_name");
                                String smartalias = jsonsmartlimit.getString("dev_alias");
                                //设置SN
                                smartLimit.setSmartsn(smartsn);
                                //设置原名
                                if ((smartname != null) && (!smartname.equals(""))) {
                                    smartLimit.setSmartname(smartname);
                                }
                                //设置访客网关TAG(区别管理员网关)
                                smartLimit.setSmartVisitorTag("visitor");
                                if (list != null) {
                                    addSmartLimit(smartLimit, list);
                                }
                            }
                            //移除相同网关
//							removeSmartLimit(list);
                        }
                        Log.v("limitsmart", jsonArray.toString());
                    } else if (json.getString("result").equals("Failed")) {
                        Message msgfail = handler.obtainMessage();
                        msgfail.what = MSG_ERROR_GETLIMIT;
                        msgfail.obj = json.getString("msg");
                        handler.sendMessage(msgfail);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Message msg = handler.obtainMessage();
            msg.what = MSG_UPDATE_ADAPTER_GETLIMIT;
            handler.sendMessage(msg);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            super.onPostExecute(result);
        }

    }

    /**
     *  * @Description:添加已授权网关
     *  * @param @param smartlimit
     *  * @param @param list
     *  * void
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-11-25 下午7:01:56
     */
    public void addSmartLimit(SmartLimit smartlimit, List<SmartLimit> list) {
        if (smartlimit != null) {
            list.add(smartlimit);
        }
    }

    /**
     *  * @Description:移除相同已授权网关
     *  * @param @param list
     *  * void
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-11-26 下午3:56:26
     */
    public void removeSmartLimit(List<SmartLimit> list) {
        if (list != null) {
            if (list.size() > 1) {
                for (int i = 0; i < list.size() - 1; i++) {
                    for (int j = list.size() - 1; j > i; j--) {
                        if (list.get(j).getSmartsn().equals(list.get(i).getSmartsn())) {
                            list.remove(j);
                        }
                    }
                }
            }
        }
    }
}
