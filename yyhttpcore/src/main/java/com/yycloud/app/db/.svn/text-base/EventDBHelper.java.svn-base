package com.yycloud.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;
import java.util.List;

import cn.yyshed.dao.DBAlarmEvent;
import cn.yyshed.dao.DBAlarmEventDao;
import cn.yyshed.dao.DBAlarmEventDao.Properties;
import cn.yyshed.dao.DaoMaster;
import cn.yyshed.dao.DaoMaster.DevOpenHelper;
import cn.yyshed.dao.DaoSession;
import de.greenrobot.dao.query.DeleteQuery;
import de.greenrobot.dao.query.QueryBuilder;

public class EventDBHelper {

	private SQLiteDatabase db;
	private DevOpenHelper helper;
	private DaoMaster daoMaster;
	private DaoSession daoSession;
	private DBAlarmEventDao eventDao;

	private static EventDBHelper instance;

	public EventDBHelper(Context context) {
		helper = new DaoMaster.DevOpenHelper(context, "events-db", null);

		db = helper.getWritableDatabase();
		daoMaster = new DaoMaster(db);
		daoSession = daoMaster.newSession();
		eventDao = daoSession.getDBAlarmEventDao();
	}

	public static EventDBHelper getInstance() {
		return instance;
	}

	public static void Initialize(Context context) {
		if (instance == null) {
			synchronized (EventDBHelper.class) {
				instance = new EventDBHelper(context);
			}
		}
	}

	public Date getEventLatestDate() {
		QueryBuilder<DBAlarmEvent> qb = eventDao.queryBuilder();
		List<DBAlarmEvent> evts =
				qb.orderDesc(Properties.Event_date).limit(1).list();
		if (null != evts) {
			for (int i = 0; i < evts.size(); i++) {
				DBAlarmEvent evt = evts.get(0);
				return evt.getEvent_date();
			}
		}
			
		return null;
	}
	
	public Date getEventOldestDate() {
		QueryBuilder<DBAlarmEvent> qb = eventDao.queryBuilder();
		List<DBAlarmEvent> evts =
				qb.orderAsc(Properties.Event_date).limit(1).list();
		if (null != evts) {
			for (int i = 0; i < evts.size(); i++) {
				DBAlarmEvent evt = evts.get(0);
				return evt.getEvent_date();
			}
		}
			
		return null;
	}
	
	public boolean setBeread(DBAlarmEvent event) {
		QueryBuilder<DBAlarmEvent> qb = eventDao.queryBuilder();
		List<DBAlarmEvent> evts =
				qb.where(Properties.Event_id.eq(event.getEvent_id())).list();
		
		for (int i = 0; i < evts.size(); i++) {
			DBAlarmEvent evt = evts.get(i);
			evt.setBeread((byte) 1);
			eventDao.update(evt);
		}
		return true;
	}

	public List<DBAlarmEvent> getEventList(String username){
		QueryBuilder<DBAlarmEvent> qb = eventDao.queryBuilder();
		return qb.where(Properties.User_name.eq(username)).list();
	}
	
	public List<DBAlarmEvent> getAlarmEventList(Date start, Date end) {
		QueryBuilder<DBAlarmEvent> qb = eventDao.queryBuilder();
		if (start.getTime() > end.getTime())
			return null;

		return qb.where(Properties.Event_date.ge(start),
				Properties.Event_date.lt(end)).list();
	}

	public void insertAlarmEvents(List<DBAlarmEvent> events) {
		if (events == null)
			return;
		for (int i = 0; i < events.size(); i++) {
			DBAlarmEvent event = events.get(i);
			eventDao.insertOrReplace(event);
		}
	}

	/**
	 * 每次更改panid和channel后需要更新数据库中设备的
	 * panid和channel
	 */
	//public void updateDevicePanidChannel(String panid, String channel){
	//	db.execSQL("update DEVICE set PANID=?, CHANNEL=?", new Object[]{panid, channel});
	//}

	public void deleteAll() {
		eventDao.deleteAll();
	}
	
	/**
	 * 删除列表中的所有event_id对应的事件
	 * @param eventIdList
	 */
	public void deleteEvents(List<String> eventIdList){
		for (String eventId : eventIdList) {
			QueryBuilder<DBAlarmEvent> qb = eventDao.queryBuilder();
			DeleteQuery<DBAlarmEvent> dq = qb.where(Properties.Event_id.eq(eventId)).buildDelete();
			dq.executeDeleteWithoutDetachingEntities();
		}
	}

	public void doTest() {
	}
}
