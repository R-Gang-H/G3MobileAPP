package com.app.itserv.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.itserv.ActivityCollector;
import com.app.itserv.BaseActivity;
import com.app.itserv.adapters.ContactAdapter;
import com.app.itserv.jparser.PhoneListBean;
import com.app.itserv.views.PullToRefreshListView;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.ToastUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.WapiUtil;
import com.yycloud.core.beans.YYContact;

public class AlarmNumberActivity extends BaseActivity implements OnClickListener {
    PullToRefreshListView mPullToRefreshListView;
    private View mBackView;
    ContactAdapter mAdapter;
    private ArrayList<YYContact> ycontacts = new ArrayList<YYContact>();
    private static String TAG = "AlarmNumberActivity";
    private ImageView mAddContact;
    private Context mContext;
    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
//				String str = (String) msg.obj;
//				try {
//					if (str != null && !str.equals("")) {
//						JSONObject jsonObject = new JSONObject(str);
//						JSONArray contacts = jsonObject
//								.getJSONArray("contacts");
//						ycontacts.clear();
//						if (contacts != null) {
//							for (int i = 0; i < contacts.length(); i++) {
//								JSONObject object = contacts.getJSONObject(i);
//								YYContact yycontact = new YYContact();
//								yycontact.setName(object.getString("name"));
//								yycontact.setPhone(object.getString("phone"));
//								ycontacts.add(yycontact);
//							}
//						}
//
//					}
//					mAdapter.setDatas(ycontacts);
//					mPullToRefreshListView
//							.onRefreshComplete(getString(R.string.xlistview_header_last_time)
//									+ new Date().toLocaleString());
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
                    break;
                case 5:
                    String phone = (String) msg.obj;
                    new DelContact().execute(phone);
                    break;
                case 6:
                    String msgObj = (String) msg.obj;
                    try {
                        String result = "";
                        JSONObject jsonObject = new JSONObject(msgObj);
                        if (jsonObject.has("err"))
                            result = jsonObject.getString("err");

                        if ("remove contact ok".equals(result)) {
                            result = "删除成功";
                        }
                        if ("add".equals(result)) {
                            result = "添加成功";
                        } else if (jsonObject.has("message")) {
                            result = jsonObject.getString("message");
                            //new AddContact().execute();
                            getPhoneList();
                        }
                        ToastUtils.makeTextShort(result);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }

        ;
    };

    @Override
    protected int layoutViewId() {
        return R.layout.alarm_number_lay;
    }

    @Override
    protected void init() {
        super.init();
        mContext = this;
        mAdapter = new ContactAdapter(this, mHandler);
        initViews();
    }

    private void initViews() {
        mBackView = findViewById(R.id.btn_back);
        mBackView.setOnClickListener(this);
        TextView title_txt = (TextView) findViewById(R.id.title_txt);
        title_txt.setText("报警手机号码设置");
        mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.column_list);
        mPullToRefreshListView.setAdapter(mAdapter);
        mPullToRefreshListView
                .setOnRefreshListener(new PullToRefreshListView.OnRefreshListener() {
                    public void onRefresh() {
                        //new AddContact().execute();
                        getPhoneList();
                    }
                });
        //new AddContact().execute();
        getPhoneList();
        mAddContact = (ImageView) findViewById(R.id.addContact);
        mAddContact.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,
                        AddAlarmNumberActivity.class);
                ((Activity) mContext).startActivityForResult(intent, 0);
            }
        });
    }

    private class DelContact extends AsyncTask<Object, Void, String> {

        @Override
        protected String doInBackground(Object... arg0) {
            String phone = (String) arg0[0];
            String msg = WapiUtil.rmContact(phone);
            return msg;
        }

        @Override
        protected void onPostExecute(String jsonString) {
            if (jsonString != null) {
                Message msg = new Message();
                msg.what = 6;
                msg.obj = jsonString;
                mHandler.sendMessage(msg);
            } else
                Log.e("add device", "null");
        }

    }

    /**
     *
     */
    private void getPhoneList() {
        HttpManager.getInstance().gUserInfo(TAG, new HttpCallBack<PhoneListBean>() {

            @Override
            public void onSuccess(PhoneListBean date) {

                List<PhoneListBean.ContactsBean> contacts = date.getContacts();
                if (contacts != null) {
                    for (int i = 0; i < contacts.size(); i++) {
                        YYContact yycontact = new YYContact();
                        yycontact.setName(contacts.get(i).getName());
                        yycontact.setPhone(contacts.get(i).getPhone());
                        ycontacts.add(yycontact);
                    }


                }
                mAdapter.setDatas(ycontacts);
                mPullToRefreshListView
                        .onRefreshComplete(getString(R.string.xlistview_header_last_time)
                                + new Date().toLocaleString());
            }

            @Override
            public void onError(Throwable throwable) {
                mPullToRefreshListView.onRefreshComplete();
                ToastUtils.makeTextShort("网络不可用!");
            }
        });
    }

    private class AddContact extends AsyncTask<Object, Void, String> {

        @Override
        protected String doInBackground(Object... arg0) {
            String msg;

            msg = WapiUtil.getUserInfo();
            return msg;

        }

        @Override
        protected void onPostExecute(String jsonString) {
            if (jsonString != null) {
                Message msg = new Message();
                msg.what = 1;
                msg.obj = jsonString;
                mHandler.sendMessage(msg);
            } else
                Log.e("add device", "null");
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("0", "0");
        switch (resultCode) {
            case 0:
                Log.e("0", "0");
                break;
            case 1:
                Log.e("reload", "reload");
                //new AddContact().execute();
                getPhoneList();
                break;
            case 2:
                Log.e("cancel", "cancel");
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            default:
                break;
        }
    }
}
