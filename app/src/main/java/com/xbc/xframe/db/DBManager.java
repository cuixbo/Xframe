package com.xbc.xframe.db;

import android.content.Context;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.xbc.xframe.app.Config;

import java.util.List;

/**
 * Created by xiaobo.cui on 2017/1/13.
 */
public class DBManager {

    private static DBManager INSTANCE = new DBManager();
    public static String DB_NAME;
    public static LiteOrm mLiteOrm;
    public static boolean mDebug = Config.IS_DEBUG;

    public static DBManager getInstance() {
        return INSTANCE;
    }

    private DBManager() {

    }

    public void init(Context context, String uid) {
        if (mLiteOrm == null) {
            DB_NAME = "xframe_" + uid + ".db";
            mLiteOrm = LiteOrm.newSingleInstance(context, DB_NAME);
            mLiteOrm.setDebugged(mDebug);
        }
    }

    /**
     * 插入一条记录
     *
     * @param t
     * @param <T>
     * @return 表中记录总数
     */
    public <T> long insert(T t) {
        if (mLiteOrm == null) {
            return 0;
        }
        return mLiteOrm.insert(t);
    }

    /**
     * 插入多条记录
     *
     * @param list
     * @param <T>
     * @return 表中记录总数
     */
    public <T> long insert(List<T> list) {
        if (mLiteOrm == null) {
            return 0;
        }
        return mLiteOrm.save(list);
    }

    /**
     * 插入或者替换一条记录
     *
     * @param t
     * @param <T>
     * @return 表中记录总数
     */
    public <T> long save(T t) {
        if (mLiteOrm == null) {
            return 0;
        }
        return mLiteOrm.save(t);
    }

    /**
     * 插入或者替换多条记录
     *
     * @param list
     * @param <T>
     * @return 表中记录总数
     */
    public <T> long save(List<T> list) {
        if (mLiteOrm == null) {
            return 0;
        }
        return mLiteOrm.save(list);
    }

    /**
     * 根据id查找某条记录
     *
     * @param clazz
     * @param id
     * @param <T>
     * @return
     */
    public <T> T queryById(Class<T> clazz, long id) {
        if (mLiteOrm == null) {
            return null;
        }
        return mLiteOrm.queryById(id, clazz);
    }

    /**
     * 根据id查找某条记录
     *
     * @param clazz
     * @param id
     * @param <T>
     * @return
     */
    public <T> T queryById(Class<T> clazz, String id) {
        if (mLiteOrm == null) {
            return null;
        }
        return mLiteOrm.queryById(id, clazz);
    }

    /**
     * 根据=条件查找某条记录
     *
     * @param clazz
     * @param col
     * @param val
     * @param <T>
     * @return
     */
    public <T> T queryByEqual(Class<T> clazz, String col, Object val) {
        if (mLiteOrm == null) {
            return null;
        }
        List<T> list = mLiteOrm.query(new QueryBuilder<T>(clazz).whereEquals(col, val));
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 根据where条件查找某条记录
     *
     * @param clazz
     * @param where
     * @param objArgs
     * @param <T>
     * @return
     */
    public <T> T queryByWhere(Class<T> clazz, String where, Object[] objArgs) {
        if (mLiteOrm == null) {
            return null;
        }
        List<T> list = mLiteOrm.query(new QueryBuilder<T>(clazz).where(where, objArgs));
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 查找表中所有记录
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> queryAll(Class<T> clazz) {
        if (mLiteOrm == null) {
            return null;
        }
        return mLiteOrm.query(clazz);
    }

    /**
     * 根据=条件查找记录
     *
     * @param clazz
     * @param col
     * @param val
     * @param <T>
     * @return
     */
    public <T> List<T> queryAllByEqual(Class<T> clazz, String col, Object val) {
        if (mLiteOrm == null) {
            return null;
        }
        return mLiteOrm.query(new QueryBuilder<T>(clazz).whereEquals(col, val));
    }

    /**
     * 根据where条件查找记录
     *
     * @param clazz
     * @param where
     * @param objArgs
     * @param <T>
     * @return
     */
    public <T> List<T> queryAllByWhere(Class<T> clazz, String where, Object[] objArgs) {
        if (mLiteOrm == null) {
            return null;
        }
        return mLiteOrm.query(new QueryBuilder<T>(clazz).where(where, objArgs));
    }

    /**
     * 查找表中记录总数
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> long queryCount(Class<T> clazz) {
        if (mLiteOrm == null) {
            return 0;
        }
        return mLiteOrm.queryCount(clazz);
    }

    /**
     * 根据=条件查找记录总数
     *
     * @param clazz
     * @param col
     * @param val
     * @param <T>
     * @return
     */
    public <T> long queryCountByEqual(Class<T> clazz, String col, Object val) {
        if (mLiteOrm == null) {
            return 0;
        }
        return mLiteOrm.queryCount(new QueryBuilder<T>(clazz).whereEquals(col, val));
    }


    /**
     * 根据where条件查找记录总数
     *
     * @param clazz
     * @param where
     * @param objArgs
     * @param <T>
     * @return
     */
    public <T> long queryCountByWhere(Class<T> clazz, String where, Object[] objArgs) {
        if (mLiteOrm == null) {
            return 0;
        }
        return mLiteOrm.queryCount(new QueryBuilder<T>(clazz).where(where, objArgs));
    }

    /**
     * 更新某条记录
     *
     * @param t
     * @param <T>
     * @return
     */
    public <T> long update(T t) {
        if (mLiteOrm == null) {
            return 0;
        }
        return mLiteOrm.update(t);
    }


    /**
     * 更新多条记录
     *
     * @param list
     * @param <T>
     * @return
     */
    public <T> long updateAll(List<T> list) {
        if (mLiteOrm == null) {
            return 0;
        }
        return mLiteOrm.update(list);
    }

    /**
     * 删除某条记录
     *
     * @param t
     * @param <T>
     * @return
     */
    public <T> long delete(T t) {
        if (mLiteOrm == null) {
            return 0;
        }
        return mLiteOrm.delete(t);
    }

    /**
     * 根据=条件删除记录
     *
     * @param clazz
     * @param col
     * @param val
     * @param <T>
     * @return
     */
    public <T> long deleteByEqual(Class<T> clazz, String col, Object val) {
        if (mLiteOrm == null) {
            return 0;
        }
        return mLiteOrm.delete(new WhereBuilder(clazz).equals(col, val));
    }

    /**
     * 根据where条件删除记录
     *
     * @param clazz
     * @param where
     * @param objArgs
     * @param <T>
     * @return
     */
    public <T> long deleteByWhere(Class<T> clazz, String where, Object[] objArgs) {
        if (mLiteOrm == null) {
            return 0;
        }
        return mLiteOrm.delete(new WhereBuilder(clazz, where, objArgs));
    }

    /**
     * 删除表中所有记录
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> long deleteAll(Class<T> clazz) {
        if (mLiteOrm == null) {
            return 0;
        }
        return mLiteOrm.delete(clazz);
    }

    /**
     * 关闭database
     */
    public void closeDB() {
        mLiteOrm.close();
    }

}
