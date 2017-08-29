//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.opendroidlibrary.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CRUD {
	public CRUD() {
	}

	protected static long insert(String tableName, ContentValues cv, SQLiteDatabase db) {
		long id = db.insert(tableName, null, cv);
		return id;
	}

	protected static <T extends OpenDroid> int delete(String tableName, String where, String[] whereArgs, SQLiteDatabase db) {
		int count = db.delete(tableName, where, whereArgs);
		return count;
	}

	protected static <T extends OpenDroid> int update(String tableName, ContentValues cv, String where, String[] whereArgs, SQLiteDatabase db) {
		int count = db.update(tableName, cv, where, whereArgs);
		return count;
	}

	protected static <T extends OpenDroid> List<T> query(Class<T> klass, String[] columns, String where, String[] whereArgs, String order, String limit, SQLiteDatabase db) {
		ArrayList resultList = new ArrayList();
		String tableName = klass.getSimpleName();
		Cursor cursor = null;

		try {
			cursor = db.query(tableName, columns, where, whereArgs, null, null, order, limit);
			foreachCursor(klass, cursor, resultList);
		} catch (Exception var14) {
			var14.printStackTrace();
		} finally {
			if(cursor != null) {
				cursor.close();
			}

		}

		return resultList;
	}

	private static <T extends OpenDroid> void foreachCursor(Class<T> klass, Cursor cursor, List<T> resultList) throws InstantiationException, IllegalAccessException {
		cursor.moveToFirst();

		while(!cursor.isAfterLast()) {
			T t = klass.newInstance();

			for(int i = 0; i < cursor.getColumnCount(); ++i) {
				String columnName = cursor.getColumnName(i);

				try {
					String methodName;
					Method m;
					switch(cursor.getType(i)) {
						case 1:
							methodName = columnName.equals("_id")?"setId":getMethodName(cursor.getColumnName(i));
							m = klass.getMethod(methodName, new Class[]{Integer.TYPE});
							m.invoke(t, new Object[]{Integer.valueOf(cursor.getInt(i))});
							break;
						case 2:
							methodName = getMethodName(cursor.getColumnName(i));
							m = klass.getMethod(methodName, new Class[]{Float.TYPE});
							m.invoke(t, new Object[]{Float.valueOf(cursor.getFloat(i))});
							break;
						default:
							methodName = getMethodName(cursor.getColumnName(i));
							m = klass.getMethod(methodName, new Class[]{String.class});
							m.invoke(t, new Object[]{cursor.getString(i)});
					}
				} catch (Exception var9) {
					var9.printStackTrace();
				}
			}

			resultList.add(t);
			cursor.moveToNext();
		}

	}

	public static String getMethodName(String columnName) {
		String methodName = Character.toUpperCase(columnName.charAt(0)) + columnName.substring(1);
		methodName = "set" + methodName;
		return methodName;
	}
}
