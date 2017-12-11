package com.app.itserv.fragments;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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

import cn.yyshed.dao.DBAlarmEvent;

import com.app.commons.ToUIEvent;
import com.app.itserv.MineApplication;
import com.app.itserv.activity.ShowEventDetailActivity;
import com.itserv.shed.R;
import com.yycloud.app.AlarmEventManager;

/**
 * 系统告警已读
 */
public class ShedReadEventFragment extends BaseFragment {
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
    private static final int GET_READ_EVENTS_WHAT_ARG = 2;

    @Override
    protected int layoutViewId() {
        return R.layout.notreadfragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置标题不显示
        mContext = getActivity();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mView.setBackgroundColor(Color.parseColor("#EBEBEB"));
        initViews();
        // get the unread-events
        AlarmEventManager.getInstance().getReadEventListFromDB(
                MineApplication.getUserName());
    }

    private void initViews() {
        listView = (ListView) mView.findViewById(R.id.notReadList);
        relativeLayout = (RelativeLayout) mView.findViewById(R.id.relative);
        cancelButton = (Button) mView.findViewById(R.id.cancle);
        deleteButton = (Button) mView.findViewById(R.id.delete);
        selectAllCheckBox = (CheckBox) mView.findViewById(R.id.cb_all);

        deleteButton.setText("删除事件 |");
        cancelButton.setOnClickListener(listener);
        deleteButton.setOnClickListener(listener);
        selectAllCheckBox.setOnClickListener(listener);
        mAdapter = new AlarmAdapter(mContext, AlarmEventManager.mReadEventsList);
        listView.setAdapter(mAdapter);
    }

    /**
     * reload the read-events from the server
     */
    private void reload() {
        AlarmEventManager.getInstance().getReadEventListFromDB(
                MineApplication.getUserName());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.delete:
                    // 删除操作,未实现
                    AlarmEventManager.getInstance()
                            .deleteReadEventsFromDb(selectId);
                    isMulChoice = false;
                    selectId.clear();
                    mAdapter = new AlarmAdapter(mContext,
                            AlarmEventManager.mReadEventsList);
                    AlarmEventManager.getInstance().getReadEventListFromDB(
                            MineApplication.getUserName());
                    listView.setAdapter(mAdapter);
                    relativeLayout.setVisibility(View.GONE);
                    break;
                case R.id.cancle:
                    isMulChoice = false;
                    selectId.clear();
                    AlarmEventManager.getInstance().getReadEventListFromDB(
                            MineApplication.getUserName());
                    mAdapter = new AlarmAdapter(mContext,
                            AlarmEventManager.mReadEventsList);
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
                    mAdapter.notifyDataSetChanged();
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
            // 调试需要
            return AlarmEventManager.mReadEventsList.size();
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

            holder.content.setText(mList.get(position).getSn());

            holder.time.setText(mList.get(position).getEvent_date()
                    .toLocaleString());
            holder.detail.setText(mList.get(position).getDetail());

            holder.isSelect.setChecked(isCheck.get(position));
            //设置已读标志
            holder.imAlarm.setBackgroundDrawable(getResources().getDrawable(R.drawable.alarmwhile));
            if (isMulChoice) {
                holder.isSelect.setVisibility(CheckBox.VISIBLE);
            } else {
                holder.isSelect.setVisibility(CheckBox.INVISIBLE);
            }

            // 长按
            convertView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    isMulChoice = true;
                    selectId.clear();
                    relativeLayout.setVisibility(View.VISIBLE);
                    AlarmEventManager.getInstance().getReadEventListFromDB(
                            MineApplication.getUserName());
                    mAdapter = new AlarmAdapter(mContext,
                            AlarmEventManager.mReadEventsList);
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
                        // 点击后item后的操作，待实现
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

    @Override
    public void onUpdateUI() {
        // TODO Auto-generated method stub
    }

    public void onEvent(ToUIEvent toUIEvent) {
        if (toUIEvent.getWhat() == ToUIEvent.SET_EVENT_READ) {
            reload();
        }
    }
}
