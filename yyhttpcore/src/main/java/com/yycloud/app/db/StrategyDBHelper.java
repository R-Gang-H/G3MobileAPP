package com.yycloud.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import cn.yyshed.dao.DBAlarmStrategyCustom;
import cn.yyshed.dao.DBAlarmStrategyCustomDao;
import cn.yyshed.dao.DBAlarmStrategyCustomDao.Properties;
import cn.yyshed.dao.DBAlarmStrategyDefault;
import cn.yyshed.dao.DBAlarmStrategyDefaultDao;
import cn.yyshed.dao.DaoMaster;
import cn.yyshed.dao.DaoMaster.DevOpenHelper;
import cn.yyshed.dao.DaoSession;
import de.greenrobot.dao.query.QueryBuilder;

public class StrategyDBHelper {

    private SQLiteDatabase db;
    private DevOpenHelper helper;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private DBAlarmStrategyDefaultDao strategyDefDao;
    private DBAlarmStrategyCustomDao strategyCustomDao;

    private static StrategyDBHelper instance;

    public StrategyDBHelper(Context context) {
        helper = new DaoMaster.DevOpenHelper(context, "strategies-db", null);

        db = helper.getWritableDatabase();
        daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        strategyDefDao = daoSession.getDBAlarmStrategyDefaultDao();
        strategyCustomDao = daoSession.getDBAlarmStrategyCustomDao();
    }

    public static StrategyDBHelper getInstance() {
        return instance;
    }

    public static void Initialize(Context context) {
        if (instance == null) {
            synchronized (StrategyDBHelper.class) {
                instance = new StrategyDBHelper(context);
            }
        }
    }


    public List<DBAlarmStrategyDefault> getDefStrategyStringList() {
        QueryBuilder<DBAlarmStrategyDefault> qb = strategyDefDao.queryBuilder();

        return qb.list();
    }


    public void updateDefaultAlarmStrategies(List<DBAlarmStrategyDefault> strategies) {
        for (int i = 0; i < strategies.size(); i++) {
            DBAlarmStrategyDefault strat = strategies.get(i);
            strategyDefDao.insertOrReplace(strat);
        }
    }

    public void cleanDefaultAlarmStrategies() {
        strategyDefDao.deleteAll();
    }

    public void cleanCustomAlarmStrategies() {
        strategyCustomDao.deleteAll();
    }

    public List<DBAlarmStrategyCustom> getCustomStrategyStringList() {
        QueryBuilder<DBAlarmStrategyCustom> qb = strategyCustomDao.queryBuilder();

        return qb.list();
    }


    public void updateCustomAlarmStrategies(List<DBAlarmStrategyCustom> strategies) {
        for (int i = 0; i < strategies.size(); i++) {
            DBAlarmStrategyCustom strat = strategies.get(i);
            strategyCustomDao.insertOrReplace(strat);
        }
    }

    /*
    public List<DBAlarmStrategy> getDefaultStrategyList() {
        QueryBuilder<DBAlarmStrategy> qb = strategyDao.queryBuilder();

        return qb.where(Properties.Is_custom.eq(false)).list();
    }

    public List<DBAlarmStrategy> getCustomStrategyList() {
        QueryBuilder<DBAlarmStrategy> qb = strategyDao.queryBuilder();

        return qb.where(Properties.Is_custom.eq(true)).list();

    }

    */
    public void insertAlarmStrategyDef(DBAlarmStrategyDefault strategy) {
        strategyDefDao.insertOrReplace(strategy);
    }

    public void insertAlarmStrategyCustom(DBAlarmStrategyCustom strategy) {
        strategyCustomDao.insertOrReplace(strategy);
    }

    public void setAlarmStrategyDef(DBAlarmStrategyDefault strategy) {
        strategyDefDao.insertOrReplace(strategy);
    }

    public void setAlarmStrategyCustom(DBAlarmStrategyCustom strategy) {
        strategyCustomDao.insertOrReplace(strategy);
    }


    public void delAlarmStrategyCustom(DBAlarmStrategyCustom strategy) {
        QueryBuilder<DBAlarmStrategyCustom> qb = strategyCustomDao.queryBuilder();

        qb.where(Properties.Strategy_id.eq(strategy.getStrategy_id())).buildDelete();
        /*List<DBAlarmStrategyCustom> strats =
				qb.where(Properties.Strategy_id.eq(strategy.getStrategy_id())).
					.buildDelete();
		
		for (int i = 0; i < strats.size(); i++) {
			//strategyCustomDao.delete(strats.get(i));
			DBAlarmStrategyCustom s = strats.get(i);
			strategyCustomDao.delete(s);
		}*/
    }

    /**
     * 每次更改panid和channel后需要更新数据库中设备的
     * panid和channel
     */
    //public void updateDevicePanidChannel(String panid, String channel){
    //	db.execSQL("update DEVICE set PANID=?, CHANNEL=?", new Object[]{panid, channel});
    //}
    public void deleteAll() {
        strategyDefDao.deleteAll();
        strategyCustomDao.deleteAll();
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
