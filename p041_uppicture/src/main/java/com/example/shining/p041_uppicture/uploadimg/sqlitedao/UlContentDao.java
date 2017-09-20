package com.example.shining.p041_uppicture.uploadimg.sqlitedao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shining.p041_uppicture.uploadimg.domain.UlDaoContentModel;

import java.util.List;

public class UlContentDao {

    public static final String TABLE_NAME = "UlContentDao";
    private static SQLiteDatabase sdb;
    private static UlContentDao mInstance;
    private static Object mDBLock = new Object();

    public static UlContentDao getInstance() {
        sdb = UlDBHelper.getInstance().getWritableDatabase();
        if (mInstance == null) {
            mInstance = new UlContentDao();
        }
        return mInstance;
    }

    public void save(UlDaoContentModel bean) {
        synchronized (mDBLock) {
            UlDaoContentModel cml = getUlDaoContentModelByDataId(String.valueOf(bean.getDataId()));
            if (cml != null) {
                if ((bean.getDataContent() == null || bean.getDataContent().length() == 0)) {
                    cml.setDataContent(bean.getDataContent());
                    bean = cml;
                }
            }
            deleteById(bean.getDataId());
            sdb.execSQL("insert into "
                            + TABLE_NAME
                            + " (  dataId, dataContent) " + "values(?,?)",
                    new Object[]{bean.getDataId(), bean.getDataContent()});
        }
    }

    public void save(List<UlDaoContentModel> items) {
        if (items == null || items.size() <= 0)
            return;
        for (UlDaoContentModel item : items) {
            save(item);
        }
    }

    public void deleteAll() {
        synchronized (mDBLock) {
            sdb.execSQL("delete from " + TABLE_NAME, new Object[]{});
        }
    }

    public void deleteById(Integer dataId) {
        synchronized (mDBLock) {
            sdb.execSQL("delete from " + TABLE_NAME + " where dataId=?",
                    new Integer[]{dataId});
        }
    }

    private UlDaoContentModel cursorToBean(Cursor cursor) {
        UlDaoContentModel mUlDaoContentModel = new UlDaoContentModel();
        mUlDaoContentModel.setDataId(cursor.getInt(cursor.getColumnIndex("dataId")));
        mUlDaoContentModel.setDataContent(cursor.getString(cursor.getColumnIndex("dataContent")));
        return mUlDaoContentModel;

    }

	/*
     * public ArrayList<UlDaoContentModel> getContacts() { ArrayList<UlDaoContentModel>
	 * UlDaoContentModel = new ArrayList<UlDaoContentModel>(); synchronized (mDBLock) { Cursor
	 * cursor = sdb.rawQuery("select * from " + TABLE_NAME, new String[] {}); if
	 * (cursor == null || cursor.getCount() == 0) return UlDaoContentModel; UlDaoContentModel
	 * bean = null;
	 * 
	 * cursor.moveToFirst(); while (true) { bean = cursorToBean(cursor);
	 * UlDaoContentModel.add(bean); if (cursor.isLast()) break; else
	 * cursor.moveToNext();
	 * 
	 * } cursor.close(); return UlDaoContentModel; } }
	 */

    public UlDaoContentModel getUlDaoContentModelByDataId(String dataId) {
        synchronized (mDBLock) {
            Cursor cursor = sdb.rawQuery("select * from " + TABLE_NAME
                    + " where dataId=?", new String[]{dataId});
            UlDaoContentModel bean = null;
            if (cursor.moveToNext()) {
                bean = cursorToBean(cursor);
            }
            cursor.close();
            return bean;
        }
    }

}
