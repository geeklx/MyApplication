package com.example.shining.p044_wechat_record.greendaoset;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.shining.p044_wechat_record.greendaoset.greendao.DaoMaster;

import java.io.IOException;

public class DBHelper extends DaoMaster.OpenHelper {
    public static final String DBNAME = "geekrecord.db";
  
    public DBHelper(Context context) {
        super(context, DBNAME, null);  
    }  
  
    @Override  
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
//        if (oldVersion > newVersion) {//TODO 存在BUG，卸载后进入ROM自带数据库的时候会出现数据库版本回退 会走这段逻辑  会出现崩溃
//            runCMD("pm clear com.example.p034_greendao_sqlite");
////            db.setVersion(oldVersion);
////            MyLogUtil.d("DBOpenHelper--->onUpgrade-----oldVersion > newVersion");
//            this.onCreate(db);
//            return;
//        }
//        if (newVersion == 6) {//版本2相对于版本1更改了FridgeStatus表，增加了sterilization_mode字段   5taginfo增加字段foodStatus
////            db.execSQL("drop table if exists FridgeStatus");
////            db.execSQL("drop table if exists RFIDDataStatus");
////            db.execSQL("drop table if exists taginfo");
//        }
//
//        this.onCreate(db);
    }


    public static String runCMD(String cmd) {
        String result = null;
        try {
            String[] cmdx = {"/system/bin/sh", "-c", cmd}; // file must

            int ret = ShellExeUtil.execCommand(cmdx);
            result = String.valueOf(ret);

        } catch (IOException e) {

            result = "ERR.JE";
        } catch (OutOfMemoryError e) {
            System.gc();
            System.runFinalization();

            result = "ERR.JE";
        }
        return result;
    }

} 