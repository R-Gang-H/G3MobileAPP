package com.app.itserv.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.itserv.activity.ShowSysNoticeActivity;
import com.app.itserv.jparser.JsonMySysNoticeObject;
import com.app.itserv.jparser.JsonMySysNoticeObject.AttributesBean;
import com.app.itserv.jparser.JsonMySysNoticeObject.ObjBean;
import com.google.gson.Gson;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

/**
 * 系统公告 1
 * isRead 是否已读(0:未读，1：已读) 此页面为已读页面
 *
 * @author yiqiang
 */
public class MySysReadNoticeFragment extends BaseFragment {

    public Context mContext;
    public static final String TAG = "MySysReadNoticeFragment";

    private List<ObjBean> objBean = new ArrayList<ObjBean>();
    private ListView listView;
    private Button cancelButton;
    private Button deleteButton;
    private CheckBox selectAllCheckBox;
    private RelativeLayout relativeLayout;// 长按后弹出按钮的布局
    public static MySysNoticeAdapter adapter;

    @Override
    protected int layoutViewId() {
        return R.layout.notreadfragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        // 设置标题不显示
        mContext = getActivity();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mView.setBackgroundColor(Color.parseColor("#EBEBEB"));
        initViews();
        init();
    }

    public void init() {
        MySysReadNoticeRequest();
    }

    private void initViews() {
        listView = (ListView) mView.findViewById(R.id.notReadList);// listview
        relativeLayout = (RelativeLayout) mView.findViewById(R.id.relative);
        cancelButton = (Button) mView.findViewById(R.id.cancle);// 撤销操作
        deleteButton = (Button) mView.findViewById(R.id.delete);// 设为已读
        selectAllCheckBox = (CheckBox) mView.findViewById(R.id.cb_all);// 全选

        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                String strId = objBean.get(arg2).getId();
                Intent intent = new Intent(mContext,
                        ShowSysNoticeActivity.class);
                intent.putExtra("strId", strId);
                intent.putExtra("name", "read");
                startActivity(intent);
            }
        });
    }


    @Override
    public void onUpdateUI() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        init();
        Log.i("wwwwwwwwwww", "vvvvvvvvv");
    }

    /***
     * 适配器 内部类
     *
     * @author yiqiang
     *
     */
    public class MySysNoticeAdapter extends BaseAdapter {
        private Context context;
        private List<ObjBean> objBeanList;

        public MySysNoticeAdapter(Context context, List<ObjBean> objBeanList) {
            super();
            this.context = context;
            this.objBeanList = objBeanList;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return objBeanList.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return objBeanList.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return arg0;
        }

        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            final ViewHolder holder;
            if (null == convertView) {
                convertView = View.inflate(context, R.layout.item, null);
                holder = new ViewHolder();
                holder.content = (TextView) convertView
                        .findViewById(R.id.listItem);// 内容
                holder.time = (TextView) convertView.findViewById(R.id.time);// 时间
                holder.detail = (TextView) convertView
                        .findViewById(R.id.detail);// 细节
                holder.isSelect = (CheckBox) convertView
                        .findViewById(R.id.checkItem);// 单选框
                holder.imAlarm = (ImageView) convertView
                        .findViewById(R.id.imAlarm);// 报警标志
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            // 赋值
            if (!TextUtils.isEmpty(objBeanList.get(position).getCreateTime())) {
                String strtime = objBeanList.get(position).getCreateTime();// 时间
                strtime = strtime.replace(".0", "");
                holder.time.setText(strtime);// 时间
            } else {
                holder.time.setText("未知");// 时间
            }

            // 过滤<p>标签
            String string = objBeanList.get(position).getNoticeContent();//内容
            string = string.replace("<p>", "");
            string = string.replace("</p>", "");
            holder.content.setText(TextUtils.isEmpty(objBeanList.get(position).getNoticeTitle()) ? "未知标题" : objBeanList.get(position).getNoticeTitle());// 标题

            holder.detail.setText(string);// 内容
            holder.detail.setVisibility(View.GONE);// 内容

            holder.isSelect.setChecked(Boolean.parseBoolean(objBeanList.get(position).getId().toString())); //设置已读标志
            holder.imAlarm.setBackgroundDrawable(getResources().getDrawable(R.drawable.alarmwhile));
//            holder.imAlarm.setBackgroundDrawable(getResources().getDrawable(R.drawable.alarmgreen));
            return convertView;
        }

        final class ViewHolder {
            public TextView content;
            public TextView time;
            public TextView detail;
            public CheckBox isSelect;
            public ImageView imAlarm;
        }
    }

    /**
     * 系统公告请求线程
     *
     * @author changyiqiang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: MySysReadNoticeRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-19 下午4:04:21
     */
    public void MySysReadNoticeRequest() {
        String token = PreferencesUtils.getString(getActivity(), "token");// token
        String currTenantId = PreferencesUtils.getString(getActivity(),
                "tenantId");// 商户id
        HttpManager.getInstance().apiReadNoticeControllerList(TAG, token, currTenantId, new HttpCallBack<JsonMySysNoticeObject>() {
            @Override
            public void onError(Throwable throwable) {
            }

            @Override
            public void onSuccess(JsonMySysNoticeObject date) {
                if (TextUtils.isEmpty(date.toString())) {
                    TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
                    return;
                }
                if (!date.equals("") && date != null) {
                    if (date.isSuccess()) {// 成功
                        objBean = date.getObj();
                        if (objBean != null) {
                            adapter = new MySysNoticeAdapter(mContext, objBean);
                            listView.setAdapter(adapter);
                        }
                    } else
                        TAUtils.toastMessage((Activity) mContext,
                                date.getMsg());
                }
            }
        });
    }
}
