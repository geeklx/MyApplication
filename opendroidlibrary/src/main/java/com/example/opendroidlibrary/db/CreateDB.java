//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.opendroidlibrary.db;

import android.database.Cursor;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

public class CreateDB extends SQLiteOpenHelper {
	private ArrayList<OpenDroid> mOldData = new ArrayList();

	public CreateDB() {
		super(OpenDroidUtil.context, OpenDroidHelper.getDBInfoBean().getName(), null, OpenDroidHelper.getDBInfoBean().getVersion(), new DefaultDatabaseErrorHandler());
	}

	public void onCreate(SQLiteDatabase db) {
		Iterator var2 = OpenDroidHelper.getDBInfoBean().getSqls().iterator();

		while(var2.hasNext()) {
			String item = (String)var2.next();
			db.execSQL(item);
		}

		if(!mOldData.isEmpty()) {
			var2 = mOldData.iterator();

			while(var2.hasNext()) {
				OpenDroid item1 = (OpenDroid)var2.next();
				item1.save(db);
			}
		}

	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		System.out.println("upgrade database");
		upgrade(db);
	}

	private <T extends OpenDroid> void upgrade(SQLiteDatabase db) {
		try {
			XmlPullParser e = Xml.newPullParser();
			InputStream inputStream = OpenDroidUtil.context.getAssets().open("open_droid.xml");
			e.setInput(inputStream, "utf-8");

			for(int type = e.getEventType(); type != 1; type = e.next()) {
				if(type == 2 && e.getName().equals("mapping")) {
					dumpData(db, e);
				}
			}
		} catch (Exception var5) {
			var5.printStackTrace();
		}

		onCreate(db);
	}

	private <T extends OpenDroid> void dumpData(SQLiteDatabase db, XmlPullParser pullParser) throws Exception {
		Class klass = Class.forName(pullParser.getAttributeValue(null, "class"));
		String tableName = klass.getSimpleName();
		Cursor cursor = db.rawQuery("select * from " + tableName, null);
		cursor.moveToFirst();

		while(!cursor.isAfterLast()) {
			OpenDroid t = (OpenDroid)klass.newInstance();
			int columnCount = cursor.getColumnCount();

			for(int i = 0; i < columnCount; ++i) {
				String columnName = cursor.getColumnName(i);

				try {
					Method m;
					String methodName;
					switch(cursor.getType(i)) {
						case 1:
							methodName = columnName.equals("_id")?"setId":CRUD.getMethodName(cursor.getColumnName(i));
							m = klass.getMethod(methodName, new Class[]{Integer.TYPE});
							m.invoke(t, new Object[]{Integer.valueOf(cursor.getInt(i))});
							break;
						case 2:
							methodName = CRUD.getMethodName(cursor.getColumnName(i));
							m = klass.getMethod(methodName, new Class[]{Float.TYPE});
							m.invoke(t, new Object[]{Float.valueOf(cursor.getFloat(i))});
							break;
						default:
							methodName = CRUD.getMethodName(cursor.getColumnName(i));
							m = klass.getMethod(methodName, new Class[]{String.class});
							m.invoke(t, new Object[]{cursor.getString(i)});
					}
				} catch (Exception var13) {
					var13.printStackTrace();
				}
			}

			mOldData.add(t);
			cursor.moveToNext();
		}

		cursor.close();
		db.execSQL("drop table if exists " + tableName);
	}
}
