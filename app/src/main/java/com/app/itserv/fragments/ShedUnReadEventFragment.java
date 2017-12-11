package com.app.itserv.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.SparseBooleanArray;
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

import com.app.commons.ToUIEvent;
import com.app.commons.UtilityClass;
import com.app.itserv.activity.ShowEventDetailActivity;
import com.google.gson.Gson;
import com.itserv.app.util.PreferencesUtils;
import com.itserv.shed.R;
import com.yycloud.app.AlarmEventManager;
import com.yycloud.app.utils.WapiUtil;
import com.yycloud.core.beans.Components;
import com.yycloud.core.beans.ShedInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import cn.yyshed.dao.DBAlarmEvent;
import de.greenrobot.event.EventBus;

/**
 * 系統公告未读
 */
public class ShedUnReadEventFragment extends BaseFragment {
    private Context mContext;
    private AlarmAdapter mAdapter;

    private boolean isMulChoice = false;// 是否长按
    private ListView listView;
    private Button cancelButton;
    private Button deleteButton;
    private CheckBox selectAllCheckBox;
    private List<String> selectId = new ArrayList<String>();// 保存选择的数据
    private SparseBooleanArray isCheck = new SparseBooleanArray();// 保存在滑动过程中CheckBox的状态
    private RelativeLayout relativeLayout;// 长按后弹出按钮的布局
    LayoutInflater inflater;
    private List<Components> onlineList;

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case AlarmEventManager.UNREAD_EVENT_WHAT_ARG:
                    reload();
                    break;
                case AlarmEventManager.SET_EVENT_READ_WHAT_ARG:
                    List<String> readEventIdList = (List<String>) msg.obj;

                    if (readEventIdList.size() > 0) {
                        // 1.store the read-events to the list
                        List<DBAlarmEvent> readEventList = new ArrayList<DBAlarmEvent>();

                        for (int i = 0; i < readEventIdList.size(); i++) {
                            readEventList.add(AlarmEventManager.mUnReadEventsMap
                                    .get(readEventIdList.get(i)));
                        }
                        // 2. refresh unread-event from the server
                        AlarmEventManager.getInstance().init(mHandler);
                        // 3.store the read-events list to the db
                        AlarmEventManager.getInstance().storeReadEventsToDB(
                                readEventList);
                        ToUIEvent toUIEvent = new ToUIEvent(
                                ToUIEvent.SET_EVENT_READ, null);
                        EventBus.getDefault().post(toUIEvent);

                        selectId.clear();
                        isMulChoice = false;
                        relativeLayout.setVisibility(View.GONE);
                    }
                    break;
            }
        }
    };


    @Override
    protected int layoutViewId() {
        return R.layout.notreadfragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // 设置标题不显示
        mContext = getActivity();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 初始化数据
        initViews();
        AlarmEventManager.getInstance().init(mHandler);
    }

    private void initViews() {

        listView = (ListView) mView.findViewById(R.id.notReadList);
        relativeLayout = (RelativeLayout) mView.findViewById(R.id.relative);
        cancelButton = (Button) mView.findViewById(R.id.cancle);//撤销操作
        deleteButton = (Button) mView.findViewById(R.id.delete);//设为已读
        selectAllCheckBox = (CheckBox) mView.findViewById(R.id.cb_all);//全选

        cancelButton.setOnClickListener(listener);
        deleteButton.setOnClickListener(listener);
        selectAllCheckBox.setOnClickListener(listener);
        mAdapter = new AlarmAdapter(mContext,
                AlarmEventManager.mUnReadEventsList);
        listView.setAdapter(mAdapter);
    }

    /**
     * reload the unread-events from the server
     */
    private void reload() {
        try {
            AlarmEventManager.UNREAD_EVENTS_LIST_LOCK.lock();
            mAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            AlarmEventManager.UNREAD_EVENTS_LIST_LOCK.unlock();
        }
    }

    @Override
    public void onUpdateUI() {

    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.delete:
                    // 删除操作,selectId存储要删除数据列表，
                    AlarmEventManager.getInstance().setEventsRead(mHandler,
                            selectId);
                    isMulChoice = false;
                    relativeLayout.setVisibility(View.GONE);
                    break;
                case R.id.cancle:
                    isMulChoice = false;
                    selectId.clear();
                    mAdapter = new AlarmAdapter(mContext,
                            AlarmEventManager.mUnReadEventsList);
                    listView.setAdapter(mAdapter);
                    relativeLayout.setVisibility(View.GONE);
                    break;
                case R.id.cb_all:
                    selectId.clear();
                    if (selectAllCheckBox.isChecked()) {
                        int index = mAdapter.getCount();
                        for (int i = 0; i < index; i++) {
                            isCheck.put(i, true);
                            selectId.add(mAdapter.getSelectedEventName(i));
                        }
                    } else {
                        int index = mAdapter.getCount();
                        for (int i = 0; i < index; i++) {
                            isCheck.put(i, false);
                        }
                    }
                    reload();
                    break;
                default:
                    break;
            }
        }
    };

    class AlarmAdapter extends BaseAdapter {

        private Context mContext;
        private List<DBAlarmEvent> mList;
        private LayoutInflater inflater = null;

        public AlarmAdapter(Context context, List<DBAlarmEvent> list) {
            this.mContext = context;
            this.mList = list;
            this.inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            for (int i = 0; i < mList.size(); i++) {
                isCheck.put(i, false);
            }
        }

        @Override
        public int getCount() {
            return AlarmEventManager.mUnReadEventsMap.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public String getSelectedEventName(int position) {
            return mList.get(position).getEvent_id();
        }

        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {

            final ViewHolder holder;

            if (null == convertView) {
                convertView = inflater.inflate(R.layout.item, null);
                holder = new ViewHolder();
                holder.content = (TextView) convertView
                        .findViewById(R.id.listItem);
                holder.time = (TextView) convertView.findViewById(R.id.time);
                holder.detail = (TextView) convertView
                        .findViewById(R.id.detail);
                holder.isSelect = (CheckBox) convertView
                        .findViewById(R.id.checkItem);
                //报警标志
                holder.imAlarm = (ImageView) convertView.findViewById(R.id.imAlarm);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
          //  Log.v("alarm", mList.get(position).getDetail());
            holder.content.setText(mList.get(position).getSmartgate_name() + " : " + mList.get(position).getSmartgate_sn());
            holder.time.setText(convertDateFormat(mList.get(position)
                    .getEvent_date()));
            holder.detail.setText(mList.get(position).getDetail());
            holder.isSelect.setChecked(isCheck.get(position));

            if (isMulChoice) {
                holder.isSelect.setVisibility(CheckBox.VISIBLE);
            } else {
                holder.isSelect.setVisibility(CheckBox.INVISIBLE);
            }
            //设置报警标志
            new LoadShedInfoTask().execute();
            setAlarmImage(holder.imAlarm, onlineList, mList.get(position).getDetail());
            // 长按
            convertView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    isMulChoice = true;
                    selectId.clear();
                    relativeLayout.setVisibility(View.VISIBLE);
                    mAdapter = new AlarmAdapter(mContext,
                            AlarmEventManager.mUnReadEventsList);
                    listView.setAdapter(mAdapter);

                    return true;
                }
            });

            // 点击选择
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isMulChoice) {
                        if (holder.isSelect.isChecked()) {
                            holder.isSelect.setChecked(false);
                            selectId.remove(mList.get(position).getEvent_id());
                            isCheck.put(position, false);

                        } else {
                            holder.isSelect.setChecked(true);
                            selectId.add(mList.get(position).getEvent_id());
                            isCheck.put(position, true);

                        }
                    } else {
                        // 点击后显示详细内容
                        Intent intent = new Intent(getActivity(),
                                ShowEventDetailActivity.class);
                        intent.putExtra("sn", mList.get(position).getSn());
                        intent.putExtra("event_id", mList.get(position)
                                .getEvent_id());
                        intent.putExtra("event_name", mList.get(position)
                                .getEvent_name());
                        intent.putExtra("event_type", mList.get(position)
                                .getEvent_type());
                        intent.putExtra("event_level", mList.get(position)
                                .getEvent_level());
                        intent.putExtra("event_date", mList.get(position)
                                .getEvent_date().toString());
                        intent.putExtra("user_name", mList.get(position)
                                .getUser_name());
                        intent.putExtra("smartgate_sn", mList.get(position)
                                .getSmartgate_sn());
                        intent.putExtra("smartgate_name", mList.get(position)
                                .getSmartgate_name());
                        intent.putExtra("detail", mList.get(position)
                                .getDetail());

                        startActivity(intent);

                    }
                }
            });

            return convertView;
        }
    }

    final class ViewHolder {
        public TextView content;
        public TextView time;
        public TextView detail;
        public CheckBox isSelect;
        public ImageView imAlarm;
    }

    public void onEvent(ToUIEvent toUIEvent) {
        if (toUIEvent.getWhat() == ToUIEvent.NEW_UNREAD_EVENT) {
            AlarmEventManager.getInstance().init(mHandler);
        }
    }

    /**
     * 将GMT 0类型的时间字符串转为本地时间
     *
     * @param date
     * @return
     */
    private String convertDateFormat(Date date) {
        date = UtilityClass.changeTimeZone(date, TimeZone.getTimeZone("GMT"),
                TimeZone.getDefault());
        return date.toLocaleString();
    }

    /**
     *  * @Description:加载大棚信息
     *  * @author:axin
     *  * @time:2016-11-17 上午9:56:13
     */
    private class LoadShedInfoTask extends AsyncTask<Object, Void, Void> {

        private ShedInfo shedInfo;


        @Override
        protected Void doInBackground(Object... params) {
            String devuuid = PreferencesUtils.getString(mContext, "devuuid");
            String msg = null;
            if (devuuid != null) {
                msg = WapiUtil.getDeviceInfo(devuuid);
            }
            if (msg != null) {
                Gson gson = new Gson();
                shedInfo = gson.fromJson(msg, ShedInfo.class);
                onlineList = shedInfo.getComponents();
                Log.v("shed", "shed component size:" + onlineList.size());
                Log.v("msg", msg);
            } else
                Log.v("shed", "shed msg is null...");
            return null;
        }

    }

    /**
     *  * @Description:设置报警标志
     *  * @param @param imageView 控件
     *  * @param @param list 所有设备
     *  * @param @param detail 报警详细信息
     *  * void
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-11-17 上午10:00:27
     */
    public void setAlarmImage(ImageView imageView, List<Components> list, String detail) {
        //获取报警详情中的设备sn
        String alarmSnString = getAlarmSnString(detail);
        String alarmSnTag = alarmSnString.substring(0, 1);
        //获取报警详情中的设备上下线字段
        String alarmIsOnlineString = getIsOnLine(detail);
     //   Log.v("alarmsn", alarmIsOnlineString + ", sn ： " + alarmSnTag);
        //网关
        if (!alarmSnTag.equals("(")) {
            if (alarmIsOnlineString.equals("下线")) {
                imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.alarmred));
            } else {
                imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.alarmgreen));
            }
            //设备
        } else {
            if (alarmIsOnlineString.equals("下线")) {
                imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.alarmyellow));
            } else {
                imageView.setBackgroundDrawable(getResources().getDrawable(R.drawable.alarmgreen));
            }
        }
    }

    /**
     *  * @Description:截取报警详情中的sn字段
     *  * @param @param detailString
     *  * @param @return
     *  * String
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-11-17 上午10:13:52
     */
    public String getAlarmSnString(String detailString) {
        String subDetailString = detailString.substring(detailString.length() - 22, detailString.length() - 5);
        return subDetailString;
    }

    /**
     *  * @Description:截取上下线字段
     *  * @param @param detailString
     *  * @param @return
     *  * String
     *  * @exception:
     *  * @author: axin
     *  * @time:2016-11-17 上午10:59:51
     */
    public String getIsOnLine(String detailString) {
        String subIsOnLineString = detailString.substring(detailString.length() - 3, detailString.length() - 1);
        return subIsOnLineString;
    }
}
