package com.app.itserv.fragments;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.app.itserv.jparser.JsonMySysNoticeSetObject;
import com.google.gson.Gson;
import com.itserv.app.http.HttpCallBack;
import com.itserv.app.http.HttpManager;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.utils.MYCallBack;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtilEx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 系统公告 0
 * isRead 是否已读(0:未读，1：已读) 此页面为未读页面
 *
 * @author yiqiang
 */
public class MySysUnReadNoticeFragment extends BaseFragment {
    private Context mContext;
    public static final String TAG = "MySysUnReadNoticeFragment";
    protected static final int READSYSNOTICESET_ERROR = 4;
    protected static final int READSYSNOTICESET_SUCCESS = 5;
    protected static final int READSYSNOTICESET_VALUES = 6;

    private List<ObjBean> objBean = new ArrayList<>();

    private boolean isMulChoice = false;// 是否长按
    private ListView listView;
    private Button cancelButton;
    private Button deleteButton;
    private CheckBox selectAllCheckBox;
    private List<String> selectId = new ArrayList<String>();// 保存选择的数据
    private RelativeLayout relativeLayout;// 长按后弹出按钮的布局
    public static MySysNoticeAdapter adapter;

    private String string = "";// 公告ID

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

        deleteButton.setText("设为已读 |");
        cancelButton.setOnClickListener(listener);
        deleteButton.setOnClickListener(listener);
        selectAllCheckBox.setOnClickListener(listener);

    }

    /**
     * Handler
     */
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case READSYSNOTICESET_ERROR:// 系统公告设为已读请求失败
                    TAUtils.toastMessage((Activity) mContext, msg.obj.toString());
                    break;
                case READSYSNOTICESET_SUCCESS:// 系统公告设置已读请求成功
                    String sysnoticeset_json = msg.obj.toString();
                    myreadsysnoticesetjson(sysnoticeset_json);
                    break;
                case READSYSNOTICESET_VALUES:
                    if (selectAllCheckBox.isChecked()) {
                        selectAllCheckBox.setChecked(false);
                        isMulChoice = false;// 是否长按
                        relativeLayout.setVisibility(View.GONE);
                    }
                    init();// 重新请求
                    break;
                default:
                    break;
            }
        }

    };
    /**
     * 点击事件
     */
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.delete:// 设为已读
                    string = "";
                    for (int i = 0; i < selectId.size(); i++) {
                        if (i == selectId.size() - 1) {// 获取公告ID
                            string = string + selectId.get(i);
                            Log.i("设为已读", i + "i==");
                        } else {
                            string = string + selectId.get(i) + ",";
                            Log.i("设为已读", i + "");
                        }
                    }
                    isMulChoice = false;//是否长按
                    relativeLayout.setVisibility(View.GONE);
                    Log.i("aaaaaaaaa", string);
                    // 创建子线程请求设为已读
                    new Thread(new MySysReadNoticeSetRequest()).start();
                    selectId.clear();// 清理集合
                    adapter.notifyDataSetInvalidated();
                    break;
                case R.id.cancle:// 撤销操作
                    isMulChoice = false;
                    selectId.clear();
                    // 遍历list条目为不选中
                    int inde = adapter.getCount();
                    for (int i = 0; i < inde; i++) {
                        // isCheck.put(i, false);
                        adapter.getSelect().set(i, false);// 设置为不选中
                    }
                    adapter.notifyDataSetChanged();// 刷新界面
                    relativeLayout.setVisibility(View.GONE);// 隐藏下面的布局
                    selectAllCheckBox.setChecked(false);// 设置全选框为不选中
                    break;
                case R.id.cb_all:// 全选
                    selectId.clear();
                    if (selectAllCheckBox.isChecked()) {
                        // int indexx = adapter.getCount();
                        int indexx = adapter.getSelect().size();
                        for (int i = 0; i < indexx; i++) {
                            // isCheck.put(i, true);
                            adapter.getSelect().set(i, true);// 设置为选中
                            selectId.add(adapter.getSelectedEventName(i));// 获得ID

						/*
                         * selectId.remove(objBeanList.get(position).getId());//
						 * 集合中移除ID //isCheck.put(position, false); //点中的时候改成相反的值
						 * linkedList.set(position, false);
						 */
                            Log.i("如果全选中....", "" + i + "");
                        }
                    } else {
                        // int index = adapter.getCount();
                        int index = adapter.getSelect().size();
                        for (int i = 0; i < index; i++) {
                            // isCheck.put(i, false);
                            adapter.getSelect().set(i, false);// 设置为不选中
                            selectId.remove(adapter.getSelectedEventName(i));
                            Log.i("如果全不选中....", "" + i + "");
                        }
                    }
                    adapter.notifyDataSetChanged();
                default:
                    break;
            }
        }
    };

    @Override
    public void onUpdateUI() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        Log.i("wwwwwwwwwww", "vvvvvvvvv");
        super.onResume();
        init();
    }

    /***
     * 适配器 内部类
     *
     * @author yiqiang
     */
    public class MySysNoticeAdapter extends BaseAdapter {
        private Context mContext;
        private List<ObjBean> objBeanList;
        private LayoutInflater inflater = null;
        // 创建一个集合 去记录选中与未选中的状态
        LinkedList<Boolean> linkedList = new LinkedList<Boolean>();

        public MySysNoticeAdapter(Context mContext, List<ObjBean> objBeanList) {
            super();
            this.mContext = mContext;
            this.objBeanList = objBeanList;
            this.inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // 设置默认值为不选中
            for (int i = 0; i < objBeanList.size(); i++) {
                linkedList.add(false);
            }
        }

        // 对外提供一个方法 获取这个集合
        private List<Boolean> getSelect() {
            return linkedList;
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

        public String getSelectedEventName(int position) {
            return objBeanList.get(position).getId();// 获得ID
        }

        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            final ViewHolder holder;
            if (null == convertView) {
                convertView = View.inflate(mContext, R.layout.item, null);
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
            holder.content.setText(TextUtils.isEmpty(objBeanList.get(position).getNoticeTitle()) ? "未知标题" : objBeanList.get(position).getNoticeTitle());// 标题

            // holder.detail.setText(string);// 内容
            holder.detail.setVisibility(View.GONE);// 内容
            holder.isSelect.setChecked(linkedList.get(position)); // 设置已读标志
            // holder.imAlarm.setBackgroundDrawable(getResources().getDrawable(R.drawable.alarmwhile));
            holder.imAlarm.setBackgroundDrawable(getResources().getDrawable(
                    R.drawable.alarmred));
            // 如果长按
            if (isMulChoice) {
                holder.isSelect.setVisibility(CheckBox.VISIBLE); // 显示
            } else {
                holder.isSelect.setVisibility(CheckBox.INVISIBLE); // 隐藏
            }

            // 长按
            convertView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    isMulChoice = true; // 长按
                    selectId.clear();// 清理集合
                    relativeLayout.setVisibility(View.VISIBLE);// 长按弹出框
                    return true;
                }
            });

            // 点击选择
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isMulChoice) { // 如果是长按
                        if (holder.isSelect.isChecked()) {// 条目中的选择框选中状态
                            holder.isSelect.setChecked(false);
                            selectId.remove(objBeanList.get(position).getId());// 集合中移除ID
                            // isCheck.put(position, false);
                            // 点中的时候改成相反的值
                            linkedList.set(position, false);
                            Log.i("如果选中....", "" + holder.isSelect.isChecked()
                                    + position + "");
                        } else {
                            holder.isSelect.setChecked(true);
                            selectId.add(objBeanList.get(position).getId());
                            // isCheck.put(position, true);
                            // 点中的时候改成相反的值
                            linkedList.set(position, true);
                            Log.i("如果未选中....", "" + holder.isSelect.isChecked()
                                    + position + "");
                        }
                        if (linkedList.contains(false)) {// 如果包含没有选中的，全选框就设置为不选中
                            selectAllCheckBox.setChecked(false);
                        } else {// 全选框设置为选中状态
                            selectAllCheckBox.setChecked(true);
                        }
                        notifyDataSetChanged();
                    } else { // 点击后item后的操作，待实现
                        String strId = objBeanList.get(position).getId();
                        Intent intent = new Intent(mContext,
                                ShowSysNoticeActivity.class);
                        intent.putExtra("strId", strId);
                        intent.putExtra("name", "unread");
                        startActivity(intent);
                    }
                }
            });
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
        HttpManager.getInstance().apiunReadNoticeControllerList(TAG, token, currTenantId, new HttpCallBack<JsonMySysNoticeObject>() {
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

    // -------------------系统公告 设为已读----------------------------

    /**
     * 系统公告设为已读请求线程
     *
     * @author changyiqiang
     * @Package com.app.itserv.activity
     * @project yyshed
     * @ClassName: MySysReadNoticeSetRequest
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @date 2017-7-19 下午4:04:21
     */
    class MySysReadNoticeSetRequest extends Thread {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                Looper.prepare();
//				String currUserId = PreferencesUtils.getString(mContext, "id");// 用户id
                String token = PreferencesUtils.getString(mContext, "token");// token
                String currTenantId = PreferencesUtils.getString(mContext,
                        "tenantId");// 商户id
                // 设置post需要传递的参数
                Map<String, String> map = new HashMap<>();
                map.put("token", token);
                map.put("currTenantId", currTenantId);
                map.put("userAdviceIds", string);// userAdviceIds
                // 公告Id（格式：公告id1,公告id2）公告Id以为逗号隔开
                Log.i(TAG, TAG + "系统公告设为已读列表请求");
                WapiUtilEx.apiNoticeControllerSet(map, new MYCallBack() {

                    @Override
                    public void onError(int code, String message) {
                        // TODO Auto-generated method stub
                        super.onError(code, message);
                        Message msg = Message.obtain();
                        msg.what = READSYSNOTICESET_ERROR;
                        msg.obj = message;
                        mHandler.sendMessage(msg);
                    }

                    @Override
                    public void onSuccess(String response) {
                        // TODO Auto-generated method stub
                        super.onSuccess(response);
                        Message msg = Message.obtain();
                        msg.what = READSYSNOTICESET_SUCCESS;
                        msg.obj = response;
                        mHandler.sendMessage(msg);
                    }
                });
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                Looper.loop();
            }

        }
    }

    /**
     * 系统公告设为已读json解析
     *
     * @param sysnoticesetjson
     */
    private void myreadsysnoticesetjson(String sysnoticesetjson) {
        if (TextUtils.isEmpty(sysnoticesetjson)) {
            TAUtils.toastMessage((Activity) mContext, "服务器异常请联系管理员!");
            return;
        }

        Gson gson = new Gson();
        JsonMySysNoticeSetObject jsonMySysNoticesetObject = gson.fromJson(
                sysnoticesetjson, JsonMySysNoticeSetObject.class);

        if (!jsonMySysNoticesetObject.equals("")
                && jsonMySysNoticesetObject != null) {
            if (jsonMySysNoticesetObject.isSuccess()) {// 成功
                Message msg = Message.obtain();
                msg.what = READSYSNOTICESET_VALUES;
                mHandler.sendMessage(msg);
            } else
                TAUtils.toastMessage((Activity) mContext,
                        jsonMySysNoticesetObject.getMsg());
        }

    }

}
