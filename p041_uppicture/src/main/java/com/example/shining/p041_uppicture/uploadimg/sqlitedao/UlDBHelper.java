package com.example.shining.p041_uppicture.uploadimg.sqlitedao;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.shining.p041_uppicture.uploadimg.application.UlDemoApplication;
import com.example.shining.p041_uppicture.uploadimg.utils.UlConstantUtil;

/**
 * UlDBHelper
 */
public class UlDBHelper extends SQLiteOpenHelper {

    public static UlDBHelper mInstance;

    public static UlDBHelper getInstance() {
        if (mInstance == null) {
            mInstance = new UlDBHelper();
        }
        return mInstance;
    }

    public static void setNull() {
        mInstance = null;
    }

    public UlDBHelper() {
        super(UlDemoApplication.mContext, UlConstantUtil.DB_NAME, null,
                UlConstantUtil.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // content
        db.execSQL("CREATE TABLE IF NOT EXISTS "
                + UlContentDao.TABLE_NAME
                + " ( ID integer primary key autoincrement," + "dataId integer, " + "dataContent varchar )");
        // image
        db.execSQL("Create table IF NOT EXISTS  "
                + UlImgDao.TABLE_NAME
                + " ( ID integer primary key autoincrement,imgid  integer ,imgcontent varchar,imgpath varchar,imgbitmap BLOB ) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion > newVersion) {
            db.setVersion(oldVersion);
            return;
        }
        if (oldVersion == 1) {
        }
        this.onCreate(db);
    }

}
