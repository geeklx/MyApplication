package com.example.shining.p041_uppicture.uploadimg.sqlitedao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.shining.p041_uppicture.uploadimg.domain.UlDaoImgModel;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 关注与对比以及数据存储类
 */
public class UlImgDao {

    public final static String TABLE_NAME = "UlImgDao";
    private static SQLiteDatabase sdb;
    private static UlImgDao mInstance;
    private static Object mDBLock = new Object();

    public static UlImgDao getInstance() {
        sdb = UlDBHelper.getInstance().getWritableDatabase();
        if (mInstance == null) {
            mInstance = new UlImgDao();
        }
        return mInstance;
    }

    public void save(UlDaoImgModel content) {
        synchronized (mDBLock) {
            // deleteByContent(content.getPatientid());
            final ByteArrayOutputStream os = new ByteArrayOutputStream();
            content.getImgbitmap().compress(Bitmap.CompressFormat.PNG, 100, os);
            sdb.execSQL("insert into " + TABLE_NAME
                            + " ( imgid,imgcontent,imgpath,imgbitmap) " + "values(?,?,?,?)",
                    new Object[]{content.getImgid(), content.getImgcontent(),
                            content.getImgpath(), os.toByteArray()});
        }
    }

    public void saveList(List<UlDaoImgModel> beans) {
        if (beans == null || beans.size() <= 0) {
            deleteAll();
            return;
        }
        deleteByContent(beans.get(0).getImgid());
        for (UlDaoImgModel bean : beans) {
            save(bean);
        }

    }

    public void deleteByContent(int id) {
        synchronized (mDBLock) {
            sdb.execSQL("delete from " + TABLE_NAME + " where imgid=?",
                    new Object[]{id});
        }
    }

    public void deleteAll() {
        synchronized (mDBLock) {
            sdb.execSQL("delete from " + TABLE_NAME, new Object[]{});
        }
    }

    private UlDaoImgModel cursorToBean(Cursor cursor) {
        UlDaoImgModel pi = new UlDaoImgModel();
//        pi.setId(cursor.getInt(cursor.getColumnIndex("ID")));
        pi.setImgid(cursor.getInt(cursor.getColumnIndex("imgid")));
        pi.setImgcontent(cursor.getString(cursor.getColumnIndex("imgcontent")));
        pi.setImgpath(cursor.getString(cursor.getColumnIndex("imgpath")));
        byte[] in = cursor.getBlob(cursor.getColumnIndex("imgbitmap"));
        pi.setImgbitmap(BitmapFactory.decodeByteArray(in, 0, in.length));
        return pi;

    }

	/*
     * public ArrayList<UlDaoImgModel> getContacts() { ArrayList<UlDaoImgModel>
	 * UlDaoImgModel = new ArrayList<UlDaoImgModel>(); synchronized (mDBLock) { Cursor
	 * cursor = sdb.rawQuery("select * from " + TABLE_NAME, new String[] {}); if
	 * (cursor == null || cursor.getCount() == 0) return UlDaoImgModel; UlDaoImgModel
	 * bean = null;
	 * 
	 * cursor.moveToFirst(); while (true) { bean = cursorToBean(cursor);
	 * UlDaoImgModel.add(bean); if (cursor.isLast()) break; else
	 * cursor.moveToNext();
	 * 
	 * } cursor.close(); return UlDaoImgModel; } }
	 */

    public List<UlDaoImgModel> getUlDaoImgModelByImgId(int patientid) {
        List<UlDaoImgModel> UlDaoImgModels = new ArrayList<UlDaoImgModel>();
        synchronized (mDBLock) {
            Cursor cursor = sdb.rawQuery("select * from " + TABLE_NAME + " where imgid=?",
                    new String[]{String.valueOf(patientid)});
            if (cursor == null || cursor.getCount() == 0)
                return UlDaoImgModels;
            UlDaoImgModel bean = null;

            cursor.moveToFirst();
            while (true) {
                bean = cursorToBean(cursor);
                UlDaoImgModels.add(bean);
                if (cursor.isLast())
                    break;
                else
                    cursor.moveToNext();

            }
            cursor.close();
            return UlDaoImgModels;
        }
    }
    /*
     * public UlDaoImgModel getContactsByPatientId(int patientid) { synchronized
	 * (mDBLock) { Cursor cursor = sdb.rawQuery("select * from " + TABLE_NAME +
	 * " where patientid=?", new String[] { String.valueOf(patientid) });
	 * UlDaoImgModel bean = null; if (cursor.moveToNext()) { bean =
	 * cursorToBean(cursor); } cursor.close(); return bean; } }
	 */

}
