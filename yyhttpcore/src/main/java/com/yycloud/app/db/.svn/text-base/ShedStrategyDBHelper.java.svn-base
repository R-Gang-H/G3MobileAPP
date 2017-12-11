package com.yycloud.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import cn.yyshed.dao.DBShedStrategy;
import cn.yyshed.dao.DBShedStrategyDao;
import cn.yyshed.dao.DBShedStrategyDao.Properties;
import cn.yyshed.dao.DaoMaster;
import cn.yyshed.dao.DaoMaster.DevOpenHelper;
import cn.yyshed.dao.DaoSession;
import de.greenrobot.dao.query.QueryBuilder;

public class ShedStrategyDBHelper {

	private SQLiteDatabase db;
	private DevOpenHelper helper;
	private DaoMaster daoMaster;
	private DaoSession daoSession;
	private DBShedStrategyDao shedStrategyDao;

	private static ShedStrategyDBHelper instance;

	public ShedStrategyDBHelper(Context context) {
		helper = new DaoMaster.DevOpenHelper(context, "shedstrategy-db", null);

		db = helper.getWritableDatabase();
		daoMaster = new DaoMaster(db);
		daoSession = daoMaster.newSession();
		shedStrategyDao = daoSession.getDBShedStrategyDao();
	}

	public static ShedStrategyDBHelper getInstance() {
		return instance;
	}

	public static void Initialize(Context context) {
		if (instance == null) {
			synchronized (ShedStrategyDBHelper.class) {
				instance = new ShedStrategyDBHelper(context);
			}
		}
	}

	public List<DBShedStrategy> getShedStrategyList() {
		QueryBuilder<DBShedStrategy> qb = shedStrategyDao.queryBuilder();

		if (qb.count() == 0)
			return null;
		return qb.list();
	}

	public DBShedStrategy getShedStrategy(String shed_id) {
		QueryBuilder<DBShedStrategy> qb = shedStrategyDao.queryBuilder();
		List<DBShedStrategy> ss = qb.limit(1).where(Properties.Shed_id.eq(shed_id)).list();
		if (null != ss)
			return ss.get(0);

		return null;
	}
	
	public void insertShedStrategy(DBShedStrategy strategy) {
		shedStrategyDao.insertOrReplace(strategy);
	}
	
	public void insertShedStrategies(List<DBShedStrategy> strategies) {
		for (int i = 0; i < strategies.size(); i++) {
			DBShedStrategy s = strategies.get(i);
			shedStrategyDao.insertOrReplace(s);
		}
	}

	public void deleteShedStrategy(String shedid) {
		QueryBuilder<DBShedStrategy> qb = shedStrategyDao.queryBuilder();
		qb.where(Properties.Shed_id.eq(shedid)).buildDelete();
	}
	
	/**
	 * 每次更改panid和channel后需要更新数据库中设备的
	 * panid和channel
	 */
	//public void updateDevicePanidChannel(String panid, String channel){
	//	db.execSQL("update DEVICE set PANID=?, CHANNEL=?", new Object[]{panid, channel});
	//}

	public void deleteAll() {
		shedStrategyDao.deleteAll();
	}

	public void doTest() {
	}

	public void test() {
	/*	List<Device> l = deviceDao.queryBuilder().where(Properties.Name.eq("Sensor")).list();

		// 为测试语法， 条件有矛盾
		QueryBuilder qb = deviceDao.queryBuilder();
		qb.where(
				Properties.Name.eq("Sensor"),
				qb.or(Properties.Type.gt((byte) 0x01),
						qb.and(Properties.Name.eq("Sensor"), Properties.Type.ge(10))));
		List<Device> l1 = qb.list();

		// 获得某一列对象
		Device d = deviceDao.loadByRowId(0);

		// Insert
		deviceDao.insert(new Device());

		// Insert or replace
		deviceDao.insertOrReplace(new Device());

		// deleteAll
		deviceDao.deleteAll();

		// 删除某个对象
		QueryBuilder<Device> qb1 = deviceDao.queryBuilder();
		DeleteQuery<Device> bd = qb1.where(Properties.Name.eq("Sensor")).buildDelete();
		bd.executeDeleteWithoutDetachingEntities();
		*/
	}
}
