package com.yycloud.app;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import com.yycloud.app.db.EventDBHelper;
import com.yycloud.app.utils.TAUtils;
import com.yycloud.app.utils.WapiUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import cn.yyshed.dao.DBAlarmEvent;

/**
 * 
 * @project name：yyhttpcore
 * @type name：AlarmEventManager
 * @description：报警事件管理器
 * @author：gang
 * @date time：2017-6-8 下午1:34:36
 * @version
 */
public class AlarmEventManager {

	private static AlarmEventManager instance = null;

	public final static String READ_EVENT_STATE = "read";
	public final static String UNREAD_EVENT_STATE = "unread";

	public final static int UNREAD_EVENT_WHAT_ARG = 30000;
	public final static int READ_EVENT_WHAT_ARG = 30001;
	public final static int ALL_EVENT_WHAT_ARG = 30002;
	public final static int SET_EVENT_READ_WHAT_ARG = 30003;

	// 存储所有未读事件
	public static Lock UNREAD_EVENTS_MAP_LOCK = new ReentrantLock();
	public static Map<String, DBAlarmEvent> mUnReadEventsMap = new HashMap<String, DBAlarmEvent>();
	public static Lock UNREAD_EVENTS_LIST_LOCK = new ReentrantLock();
	public static List<DBAlarmEvent> mUnReadEventsList = new ArrayList<DBAlarmEvent>();

	public static List<DBAlarmEvent> mReadEventsList = new ArrayList<DBAlarmEvent>();

	public static AlarmEventManager getInstance() {
		if (instance == null) {
			instance = new AlarmEventManager();
		}
		return instance;
	}

	public void init(Handler handler) {
		getUnReadEvents(handler);
	}

	/**
	 * set the events' state to read
	 * 
	 * @param handler
	 * @param eventIdList
	 */
	public void setEventsRead(Handler handler, List<String> eventIdList) {
		new SetEventsRead(eventIdList, handler).executeOnExecutor(Executors
				.newCachedThreadPool());
	}

	/**
	 * load the read events from the db (not all the events but the events
	 * stored in local db)
	 * 
	 * @param username
	 */
	public void getReadEventListFromDB(String username) {
		List<DBAlarmEvent> list = EventDBHelper.getInstance().getEventList(
				username);
		if (null != list) {
			if (null == mReadEventsList) {
				mReadEventsList = new ArrayList<DBAlarmEvent>();
			}
			mReadEventsList.clear();
			for (DBAlarmEvent dbAlarmEvent : list) {
				mReadEventsList.add(dbAlarmEvent);
			}
			if (mReadEventsList.size() > 1) {
				sortEventsByTime(mReadEventsList);
			}
		}
	}

	/**
	 * store the read events to the db
	 * 
	 * @param list
	 */
	public void storeReadEventsToDB(List<DBAlarmEvent> list) {
		EventDBHelper.getInstance().insertAlarmEvents(list);
	}

	/**
	 * 删除列表中所有event_id对应的事件
	 * 
	 * @param eventIdList
	 */
	public void deleteReadEventsFromDb(List<String> eventIdList) {
		EventDBHelper.getInstance().deleteEvents(eventIdList);
	}

	/**
	 * get the unread events from the server
	 * 
	 * @param handler
	 */
	private void getUnReadEvents(Handler handler) {
		new LoadUnReadEvents(handler, UNREAD_EVENT_STATE, "", "")
				.executeOnExecutor(Executors.newCachedThreadPool());
	}

	/**
	 * load the unread-events from the server
	 * 
	 * @author shuyi
	 * 
	 */
	private class LoadUnReadEvents extends AsyncTask<Object, Void, Void> {

		private Handler mHandler = null;
		private String mReadState = "";
		private String mBeginDate = "";
		private String mEndDate = "";

		public LoadUnReadEvents(Handler handler, String readState,
								String beginDate, String endDate) {
			this.mHandler = handler;
			this.mReadState = readState;
			this.mBeginDate = beginDate;
			this.mEndDate = endDate;
		}

		@Override
		protected Void doInBackground(Object... arg0) {
			String jsonString = WapiUtil.getEvents(mReadState, mBeginDate,
					mEndDate);
			if (null != jsonString && !jsonString.equals("")) {
				storeUnReadToBuffer(jsonString, mHandler);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
		}
	}

	/**
	 * store the new unread events to the buffer
	 * 
	 * @param jsonString
	 * @param handler
	 */
	private void storeUnReadToBuffer(String jsonString, Handler handler) {
		List<DBAlarmEvent> evtlist = JSONStr2List(jsonString);

		try {
			UNREAD_EVENTS_MAP_LOCK.lock();
			UNREAD_EVENTS_LIST_LOCK.lock();

			if (null == mUnReadEventsMap) {
				mUnReadEventsMap = new HashMap<String, DBAlarmEvent>();
			}

			if (null == mUnReadEventsList) {
				mUnReadEventsList = new ArrayList<DBAlarmEvent>();
			}

			mUnReadEventsList.clear();
			mUnReadEventsMap.clear();
			for (DBAlarmEvent dbAlarmEvent : evtlist) {
				mUnReadEventsMap.put(dbAlarmEvent.getEvent_id(), dbAlarmEvent);
				mUnReadEventsList.add(dbAlarmEvent);
			}

			if (mUnReadEventsList.size() > 1) {
				sortEventsByTime(mUnReadEventsList);
			}
			if (null != handler) {
				Message message = new Message();
				message.obj = mUnReadEventsMap.size();
				message.what = UNREAD_EVENT_WHAT_ARG;
				handler.sendMessage(message);
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			UNREAD_EVENTS_MAP_LOCK.unlock();
			UNREAD_EVENTS_LIST_LOCK.unlock();
		}
	}

	/**
	 * parse the string to DBAlarmEvent object list
	 * 
	 * @param jsonString
	 * @return
	 */
	List<DBAlarmEvent> JSONStr2List(String jsonString) {
		JSONObject obj;
		List<DBAlarmEvent> evtlst = new ArrayList<DBAlarmEvent>();
		try {
			JSONArray evtarr = new JSONArray(jsonString);
			int n = evtarr.length();
			for (int i = 0; i < n; i++) {
				DBAlarmEvent evt = new DBAlarmEvent();
				JSONObject o = evtarr.getJSONObject(i);
				evt.setBeread((byte) 0);
				if (o.has("event_state"))
					evt.setEvent_state(o.getString("event_state"));
				if (o.has("detail"))
					evt.setDetail(o.getString("detail"));
				if (o.has("event_id"))
					evt.setEvent_id(o.getString("event_id"));
				if (o.has("event_level"))
					evt.setEvent_level(o.getString("event_level"));
				if (o.has("event_type"))
					evt.setEvent_type(o.getString("event_type"));
				if (o.has("event_name"))
					evt.setEvent_name(o.getString("event_name"));
				if (o.has("sn"))
					evt.setSn(o.getString("sn"));
				if (o.has("device_sn"))
					evt.setDevice_sn(o.getString("device_sn"));
				if (o.has("smartgate_sn"))
					evt.setSmartgate_sn(o.getString("smartgate_sn"));
				if (o.has("smartgate_name"))
					evt.setSmartgate_name(o.getString("smartgate_name"));
				if (o.has("user_name"))
					evt.setUser_name(o.getString("user_name"));
				if (o.has("event_state")) {
					String dtstr = o.getString("event_date");
					evt.setEvent_date(TAUtils.isoTimeFormatTrans(dtstr));
				}

				evtlst.add(evt);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return evtlst;
	}

	/**
	 * set the events' state from unread to read
	 * 
	 * @author shuyi
	 * 
	 */
	private class SetEventsRead extends AsyncTask<Object, Void, Void> {

		private Handler mHandler;
		private List<String> mEventIdList = new ArrayList<String>();

		public SetEventsRead(List<String> eventIdList, Handler handler) {
			this.mHandler = handler;
			this.mEventIdList = eventIdList;
		}

		@Override
		protected Void doInBackground(Object... arg0) {
			String jsonString = WapiUtil.setEventsState(mEventIdList);
			if (null != jsonString && !jsonString.equals("")) {
				try {
					JSONObject jsonObject = new JSONObject(jsonString);
					if (jsonObject.getString("result").equals("OK")) {
						Message message = new Message();
						message.what = SET_EVENT_READ_WHAT_ARG;
						message.obj = mEventIdList;
						mHandler.sendMessage(message);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

		}
	}

	/**
	 * sort the list by the event_time
	 * 
	 * @param list
	 */
	private void sortEventsByTime(List<DBAlarmEvent> list) {
		Collections.sort(list, new Comparator<DBAlarmEvent>() {

			@Override
			public int compare(DBAlarmEvent arg0, DBAlarmEvent arg1) {
				// TODO Auto-generated method stub
				// return arg0.getEvent_date().after(arg1.getEvent_date());
				return arg1.getEvent_date().compareTo(arg0.getEvent_date());
			}
		});
	}
}
