package cn.yyshed.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import cn.yyshed.dao.DBShedStrategy;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table DBSHED_STRATEGY.
*/
public class DBShedStrategyDao extends AbstractDao<DBShedStrategy, Void> {

    public static final String TABLENAME = "DBSHED_STRATEGY";

    /**
     * Properties of entity DBShedStrategy.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Shed_id = new Property(0, String.class, "shed_id", false, "SHED_ID");
        public final static Property Strategy_type = new Property(1, String.class, "strategy_type", false, "STRATEGY_TYPE");
        public final static Property Content = new Property(2, String.class, "content", false, "CONTENT");
    }


    public DBShedStrategyDao(DaoConfig config) {
        super(config);
    }
    
    public DBShedStrategyDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'DBSHED_STRATEGY' (" + //
                "'SHED_ID' TEXT UNIQUE ," + // 0: shed_id
                "'STRATEGY_TYPE' TEXT," + // 1: strategy_type
                "'CONTENT' TEXT);"); // 2: content
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'DBSHED_STRATEGY'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, DBShedStrategy entity) {
        stmt.clearBindings();
 
        String shed_id = entity.getShed_id();
        if (shed_id != null) {
            stmt.bindString(1, shed_id);
        }
 
        String strategy_type = entity.getStrategy_type();
        if (strategy_type != null) {
            stmt.bindString(2, strategy_type);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(3, content);
        }
    }

    /** @inheritdoc */
    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    /** @inheritdoc */
    @Override
    public DBShedStrategy readEntity(Cursor cursor, int offset) {
        DBShedStrategy entity = new DBShedStrategy( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // shed_id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // strategy_type
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // content
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, DBShedStrategy entity, int offset) {
        entity.setShed_id(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setStrategy_type(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setContent(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    /** @inheritdoc */
    @Override
    protected Void updateKeyAfterInsert(DBShedStrategy entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    /** @inheritdoc */
    @Override
    public Void getKey(DBShedStrategy entity) {
        return null;
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}