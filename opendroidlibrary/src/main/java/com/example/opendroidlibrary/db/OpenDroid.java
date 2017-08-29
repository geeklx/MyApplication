//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.opendroidlibrary.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;


public class OpenDroid {
	private static CreateDB sOpenHelper = new CreateDB();
	public static Query query = new Query();
	private static SQLiteDatabase sSqliteDatabase;
	private int id;

	public OpenDroid() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		id = id;
	}

	public static void open() {
		if(sSqliteDatabase == null) {
			sSqliteDatabase = sOpenHelper.getWritableDatabase();
		}

	}

	public static void release() {
		if(sSqliteDatabase != null && sSqliteDatabase.isOpen()) {
			sSqliteDatabase.close();
		}

		sSqliteDatabase = null;
	}

	public long save() {
		try {
			Class e = getClass();
			ContentValues cv = new ContentValues();
			generateData(e, cv);
			return CRUD.insert(e.getSimpleName(), cv, sSqliteDatabase);
		} catch (Exception var3) {
			var3.printStackTrace();
			return -1L;
		}
	}

	protected long save(SQLiteDatabase db) {
		try {
			Class e = getClass();
			ContentValues cv = new ContentValues();
			generateData(e, cv);
			return CRUD.insert(e.getSimpleName(), cv, db);
		} catch (Exception var4) {
			var4.printStackTrace();
			return -1L;
		}
	}

	public int update(int... ids) {
		try {
			Class e = getClass();
			ContentValues cv = new ContentValues();
			generateData(e, cv);
			if(ids.length == 0) {
				return update(e, cv, null, (String[])null);
			} else {
				StringBuilder builder = new StringBuilder("_id in (");
				String[] whereArgs = new String[ids.length];
				buildIn(builder, whereArgs, ids);
				return update(e, cv, builder.toString(), whereArgs);
			}
		} catch (Exception var6) {
			var6.printStackTrace();
			return -1;
		}
	}

	public int update(String where, String... whereArgs) {
		try {
			Class e = getClass();
			ContentValues cv = new ContentValues();
			generateData(e, cv);
			return update(e, cv, where, whereArgs);
		} catch (Exception var5) {
			var5.printStackTrace();
			return -1;
		}
	}

	public static <T extends OpenDroid> int update(Class<T> klass, ContentValues cv, String where, String... whereArgs) {
		String tableName = klass.getSimpleName();
		return CRUD.update(tableName, cv, where, whereArgs, sSqliteDatabase);
	}

	public int delete(int id) {
		return delete(getClass(), new int[]{id});
	}

	public static <T extends OpenDroid> int delete(Class<T> klass, int... ids) {
		if(ids.length == 0) {
			return delete(klass, null, (String[])null);
		} else {
			StringBuilder builder = new StringBuilder("_id in (");
			String[] whereArgs = new String[ids.length];
			buildIn(builder, whereArgs, ids);
			return delete(klass, builder.toString(), whereArgs);
		}
	}

	public static <T extends OpenDroid> int delete(Class<T> klass, String where, String... whereArgs) {
		String tableName = klass.getSimpleName();
		return CRUD.delete(tableName, where, whereArgs, sSqliteDatabase);
	}

	private void generateData(Class<OpenDroid> klass, ContentValues values) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		Field[] fields = klass.getDeclaredFields();
		Field[] var7 = fields;
		int var8 = fields.length;

		for(int var9 = 0; var9 < var8; ++var9) {
			Field field = var7[var9];
			if(!field.isAccessible()) {
				Class fieldType = field.getType();
				String fieldName = field.getName();
				String methodName = Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
				Method m;
				if(fieldType != Boolean.class && fieldType != Boolean.TYPE) {
					m = klass.getDeclaredMethod("get" + methodName, new Class[0]);
				} else {
					m = klass.getDeclaredMethod("is" + methodName, new Class[0]);
				}

				Object value = m.invoke(this, new Object[0]);
				if(value != null) {
					if(fieldType != Integer.class && fieldType != Integer.TYPE) {
						if(fieldType != Boolean.class && fieldType != Boolean.TYPE) {
							values.put(fieldName, m.invoke(this, new Object[0]).toString());
						} else {
							values.put(fieldName, Boolean.valueOf(Boolean.parseBoolean(m.invoke(this, new Object[0]).toString())));
						}
					} else {
						values.put(fieldName, Integer.valueOf(Integer.parseInt(m.invoke(this, new Object[0]).toString())));
					}
				}
			}
		}

	}

	private static void buildIn(StringBuilder builder, String[] whereArgs, int... ids) {
		if(ids.length > 1) {
			for(int i = 0; i < ids.length - 1; ++i) {
				whereArgs[i] = String.valueOf(ids[i]);
				builder.append("?,");
			}
		}

		whereArgs[ids.length - 1] = String.valueOf(ids[ids.length - 1]);
		builder.append("?)");
	}

	public static void execSQL(String sql) {
		sSqliteDatabase.execSQL(sql);
	}

	static {
		sSqliteDatabase = sOpenHelper.getWritableDatabase();
	}

	public static class Query {
		private String[] mCocumns = null;
		private String mWhere = null;
		private String[] mWhereArgs = null;
		private String mOrder = null;
		private String mLimit;

		public Query() {
		}

		public Query columns(String... columns) {
			mCocumns = columns;
			return this;
		}

		public Query where(String where, String... whereArgs) {
			mWhere = where;
			mWhereArgs = whereArgs;
			return this;
		}

		public Query order(String order) {
			mOrder = order;
			return this;
		}

		public Query limit(int... limit) {
			StringBuilder builder = new StringBuilder();
			builder.append(limit[0]);
			if(limit.length == 2) {
				builder.append(",").append(limit[1]);
			}

			mLimit = builder.toString();
			return this;
		}

		public <T extends OpenDroid> List<T> find(Class<T> klass) {
			List result = CRUD.query(klass, mCocumns, mWhere, mWhereArgs, mOrder, mLimit, sSqliteDatabase);
			reset();
			return result;
		}

		public <T extends OpenDroid> T find(Class<T> klass, int id) {
			List<T> result = CRUD.query(klass, mCocumns, "_id=?",
					new String[] { String.valueOf(id) }, null, "1",
					sSqliteDatabase);
			reset();
			return result.size() > 0? result.get(0) :null;
		}

		public <T extends OpenDroid> List<T> find(Class<T> klass, int... ids) {
			StringBuilder builder = new StringBuilder("_id in (");
			String[] whereArgs = new String[ids.length];
			buildIn(builder, whereArgs, ids);
			List result = CRUD.query(klass, mCocumns, builder.toString(), whereArgs, mOrder, mLimit, sSqliteDatabase);
			reset();
			return result;
		}

		public Cursor queryBySql(String sql, String[] selectionArgs) {
			return sSqliteDatabase.rawQuery(sql, selectionArgs);
		}

		private void reset() {
			mCocumns = null;
			mWhere = null;
			mWhereArgs = null;
			mOrder = null;
			mLimit = null;
		}
	}
}
