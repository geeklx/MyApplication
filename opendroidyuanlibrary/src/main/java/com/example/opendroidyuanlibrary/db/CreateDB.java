package com.example.opendroidyuanlibrary.db;

import android.database.Cursor;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * SqliteOpenHelper
 * @author qibin
 *
 */
public class CreateDB extends SQLiteOpenHelper {
	private ArrayList<OpenDroid> mOldData = new ArrayList<OpenDroid>();
	
	public CreateDB() {
		super(OpenDroidYuanUtil.context, OpenDroidHelper.getDBInfoBean().getName(),
				null, OpenDroidHelper.getDBInfoBean().getVersion(), new DefaultDatabaseErrorHandler());
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		for(String sql : OpenDroidHelper.getDBInfoBean().getSqls()) {
			db.execSQL(sql);
		}
		
		// 还原数据
		if(!mOldData.isEmpty()) {
			for(OpenDroid item : mOldData) {
				item.save(db);
			}
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		System.out.println("upgrade database");
		upgrade(db);
	}
	
	/**
	 * 升级数据库
	 * @param db 数据库链接
	 */
	private <T extends OpenDroid> void upgrade(SQLiteDatabase db) {
		try {
			XmlPullParser pullParser = Xml.newPullParser();
			InputStream inputStream = OpenDroidYuanUtil.context.getAssets().open("open_droid.xml");
			pullParser.setInput(inputStream, "utf-8");
			
			int type = pullParser.getEventType();
			while(type != XmlPullParser.END_DOCUMENT) {
				if(type == XmlPullParser.START_TAG) {
					// 获取mapping
					if(pullParser.getName().equals(OpenDroidHelper.TAG_MAPPING)) {
						dumpData(db, pullParser);
					}
				}
				type = pullParser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 执行创建数据库
		onCreate(db);
	}

	/**
	 * 将数据库中的数据转储到程序中
	 * @param db 数据库连接
	 * @param pullParser 
	 * @throws Exception
	 */
	private <T extends OpenDroid> void dumpData(SQLiteDatabase db, XmlPullParser pullParser)
			throws Exception {
		Class<OpenDroid> klass = (Class<OpenDroid>) Class.forName(pullParser.getAttributeValue(null, "class"));
		String tableName = klass.getSimpleName(); // 表名
		Cursor cursor = db.rawQuery("select * from " + tableName, null);
		
		T t;
		Method m;
		String methodName;
		String columnName;
		
		// 遍历游标
		for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()) {
			t = (T) klass.newInstance();  // 通过反射进行实例化
			final int columnCount = cursor.getColumnCount();
			for(int i=0;i<columnCount;i++) {
				columnName = cursor.getColumnName(i); // 获取字段名
				// try一下，如果没有该字段对应的方法，则消化异常，并继续
				try {
					switch (cursor.getType(i)) {
					case Cursor.FIELD_TYPE_INTEGER:
						methodName = columnName.equals("_id") ? "setId" : 
							CRUD.getMethodName(cursor.getColumnName(i));
						m = klass.getMethod(methodName, int.class); // 反射出方法
						m.invoke(t, cursor.getInt(i)); // 执行方法
						break;
					case Cursor.FIELD_TYPE_FLOAT:
						methodName = CRUD.getMethodName(cursor.getColumnName(i));
						m = klass.getMethod(methodName, float.class);
						m.invoke(t, cursor.getFloat(i));
						break;
					default:
						methodName = CRUD.getMethodName(cursor.getColumnName(i));
						m = klass.getMethod(methodName, String.class);
						m.invoke(t, cursor.getString(i));
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mOldData.add(t);
		}
		cursor.close();
		db.execSQL("drop table if exists " + tableName); // 删除旧的数据库
	}
}
