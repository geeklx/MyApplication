package com.example.opendroidyuanlibrary.db;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class OpenDroid {
	private static CreateDB sOpenHelper = new CreateDB();
	public static Query query = new Query();

	private static SQLiteDatabase sSqliteDatabase = sOpenHelper.getWritableDatabase();

	private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 打开数据库
	 */
	public static void open() {
		if(sSqliteDatabase == null) {
			sSqliteDatabase = sOpenHelper.getWritableDatabase();
		}
	}

	/**
	 * 释放数据库
	 */
	public static void release() {
		if(sSqliteDatabase != null && sSqliteDatabase.isOpen()) {
			sSqliteDatabase.close();
		}
		sSqliteDatabase = null;
	}

	/**
	 * 插入数据
	 * @return 最后插入的id
	 */
	public long save() {
		try {
			@SuppressWarnings("unchecked")
			Class<OpenDroid> klass = (Class<OpenDroid>) getClass();
			ContentValues cv = new ContentValues();
			generateData(klass, cv);

			return CRUD.insert(klass.getSimpleName(), cv, sSqliteDatabase);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 为数据库升级转储数据提供的
	 * @param db 从SqliteOpenHelper.onCreate()传过来的db
	 * @see {@link CreateDB.onCreate}
	 * @return
	 */
	protected long save(SQLiteDatabase db) {
		try {
			Class<OpenDroid> klass = (Class<OpenDroid>) getClass();
			ContentValues cv = new ContentValues();
			generateData(klass, cv);
			return CRUD.insert(klass.getSimpleName(), cv, db);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 更新数据
	 * @param ids 要更新数据的id
	 * @return 影响行数
	 */
	public int update(int... ids) {
		try {
			Class<OpenDroid> klass = (Class<OpenDroid>) getClass();
			ContentValues cv = new ContentValues();
			generateData(klass, cv);

			if(ids.length == 0) {
				return update(klass, cv, null, null);
			}

			StringBuilder builder = new StringBuilder("_id in (");
			String[] whereArgs = new String[ids.length];
			buildIn(builder, whereArgs, ids);

			return update(klass, cv, builder.toString(), whereArgs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 更新数据
	 * @param where 条件
	 * @param whereArgs 条件参数
	 * @return 影响行数
	 */
	public int update(String where, String... whereArgs) {
		try {
			Class<OpenDroid> klass = (Class<OpenDroid>) getClass();
			ContentValues cv = new ContentValues();
			generateData(klass, cv);

			return update(klass, cv, where, whereArgs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * 更新数据
	 * @param klass 要更新的表对应的class
	 * @param cv 要更新的数据
	 * @param where where条件
	 * @param whereArgs 条件的参数
	 * @return 影响行数
	 */
	public static <T extends OpenDroid> int update(Class<T> klass, ContentValues cv,
												   String where, String... whereArgs) {
		String tableName = klass.getSimpleName();
		return CRUD.update(tableName, cv, where, whereArgs, sSqliteDatabase);
	}

	/**
	 * 删除数据
	 * @param id 数据的id
	 * @return 影响行数
	 */
	public int delete(int id) {
		return delete(getClass(), id);
	}

	/**
	 * 删除数据
	 * @param klass 要删除的表对应的class
	 * @param ids 数据的ids
	 * @return 影响行数
	 */
	public static <T extends OpenDroid> int delete(Class<T> klass, int... ids) {
		if(ids.length == 0) {
			return delete(klass, null, null);
		}

		StringBuilder builder = new StringBuilder("_id in (");
		String[] whereArgs = new String[ids.length];
		buildIn(builder, whereArgs, ids);

		return delete(klass, builder.toString(), whereArgs);
	}

	/**
	 * 删除数据
	 * @param klass 要删除的表对应的class
	 * @param where where条件
	 * @param whereArgs where条件的参数
	 * @return 影响行数
	 */
	public static <T extends OpenDroid> int delete(Class<T> klass, String where,
												   String... whereArgs) {
		String tableName = klass.getSimpleName();
		return CRUD.delete(tableName, where, whereArgs, sSqliteDatabase);
	}

	/**
	 * 生成数据
	 * @param tableName 要获取的表名
	 * @param values 要获取的数据
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private void generateData(Class<OpenDroid> klass, ContentValues values)
			throws NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		Field[] fields = klass.getDeclaredFields(); // 获取类中的所有字段
		Method m;
		String fieldName;
		String methodName;

		for (Field field : fields) {
			// 如果是public，则忽略
			if (field.isAccessible()) {
				continue;
			}

			// 获取字段的类型
			Class<?> fieldType = field.getType();

			fieldName = field.getName(); // 获取字段名称
			// 将字段名称的首字母大写，准备拼装getter(getName)
			methodName = Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);

			// 这里还要判断一下类型是不是boolean，
			// 如果是boolean， 就不是getXXX了,而是isXXX
			if (fieldType == Boolean.class || fieldType == boolean.class) {
				m = klass.getDeclaredMethod("is" + methodName);
			} else {
				m = klass.getDeclaredMethod("get" + methodName); // 获取方法
			}

			// 获取方法的返回值
			Object value = m.invoke(this);
			// 对于一些类型不支持。。
			// 未找到解决方案
			if (value == null) {
				// 如果是null，则在contentValues里添加一个null
//				values.putNull(fieldName);
				continue;
			}

			// 通过判断field的类型，向contentValues插入对应的数据
			if (fieldType == Integer.class || fieldType == int.class) {
				values.put(fieldName, Integer.parseInt(m.invoke(this).toString()));
			} else if (fieldType == Boolean.class || fieldType == boolean.class) {
				values.put(fieldName, Boolean.parseBoolean(m.invoke(this).toString()));
			} else {
				values.put(fieldName, m.invoke(this).toString());
			}
		}
	}

	/**
	 * 组装IN语句
	 * @param builder in语句
	 * @param whereArgs in的内容
	 * @param ids 要拼装的ids
	 */
	private static void buildIn(StringBuilder builder, String[] whereArgs,
								int... ids) {
		if(ids.length > 1) {
			for(int i=0;i<ids.length-1;i++) {
				whereArgs[i] = String.valueOf(ids[i]);
				builder.append("?,");
			}
		}

		whereArgs[ids.length - 1] = String.valueOf(ids[ids.length - 1]);
		builder.append("?)");
	}

	/**
	 * 执行sql语句
	 * @param sql sql语句
	 */
	public static void execSQL(String sql) {
		sSqliteDatabase.execSQL(sql);
	}

	/**
	 * 查询
	 * @author qibin
	 */
	public static class Query {
		private String[] mCocumns = null;  // 要查询的字段
		private String mWhere = null;	// 查询的条件
		private String[] mWhereArgs = null; // 查询的条件的参数
		private String mOrder = null; // order语句
		private String mLimit; // limit语句

		/**
		 * 设置查询的字段
		 * @param columns 要查询的字段
		 * @return Query对象
		 */
		public Query columns(String... columns) {
			mCocumns = columns;
			return this;
		}

		/**
		 * 设置查询的where条件
		 * @param where where条件
		 * @param whereArgs where参数
		 * @return Query对象
		 */
		public Query where(String where, String... whereArgs) {
			mWhere = where;
			mWhereArgs = whereArgs;
			return this;
		}

		/**
		 * 设置查询的order
		 * @param order order语句
		 * @return Query对象
		 */
		public Query order(String order) {
			mOrder = order;
			return this;
		}

		/**
		 * 设置查询的limit
		 * @param limit limit语句
		 * @return Query对象
		 */
		public Query limit(int... limit) {
			StringBuilder builder = new StringBuilder();
			builder.append(limit[0]);

			if(limit.length == 2) {
				builder.append(",").append(limit[1]);
			}

			mLimit = builder.toString();
			return this;
		}

		/**
		 * 查询
		 * @param klass 映射的bean
		 * @return 查询结果
		 */
		public <T extends OpenDroid> List<T> find(Class<T> klass) {
			List<T> result =  CRUD.query(klass, mCocumns, mWhere, mWhereArgs,
					mOrder, mLimit, sSqliteDatabase);
			reset();
			return result;
		}

		/**
		 * 根据id查询数据
		 * @param klass klass 映射的bean
		 * @param id 要查询数据的id
		 * @return	查询结果
		 */
		public <T extends OpenDroid> T find(Class<T> klass, int id) {
			List<T> result = CRUD.query(klass, mCocumns, "_id=?",
					new String[] { String.valueOf(id) }, null, "1",
					sSqliteDatabase);
			reset();
			return result.size() > 0 ? result.get(0) : null;
		}

		public <T extends OpenDroid> List<T> find(Class<T> klass, int... ids) {
			StringBuilder builder = new StringBuilder("_id in (");
			String[] whereArgs = new String[ids.length];
			buildIn(builder, whereArgs, ids);

			List<T> result = CRUD.query(klass, mCocumns, builder.toString(), whereArgs,
					mOrder, mLimit, sSqliteDatabase);
			reset();
			return result;
		}

		/**
		 * 使用sql语句查询
		 * @param sql sql语句
		 * @param selectionArgs 预编译参数
		 * @return
		 */
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
