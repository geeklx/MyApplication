package com.example.opendroidyuanlibrary.db;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 数据库的CRUD操作
 * @author qibin
 */
public class CRUD {
	/**
	 * 插入数据
	 * @param t 对应的bean
	 * @param db 数据库操作
	 * @return	最新插入的id
	 */
	protected static long insert(String tableName, ContentValues cv, SQLiteDatabase db) {
		long id = db.insert(tableName, null, cv);
		return id;
	}
	
	/**
	 * 删除数据
	 * @param tableName 表名
	 * @param where where条件
	 * @param whereArgs where条件的参数
	 * @param db	数据库
	 * @return 影响行数
	 */
	protected static <T extends OpenDroid> int delete(String tableName, String where,
			String[] whereArgs, SQLiteDatabase db) {
		int count = db.delete(tableName, where, whereArgs);
		return count;
	}
	
	/**
	 * 更新数据
	 * @param tableName 表名
	 * @param cv 更新的数据
	 * @param where 更新的条件
	 * @param whereArgs 更新的条件参数
	 * @param db 数据库
	 * @return 影响行数
	 */
	protected static <T extends OpenDroid> int update(String tableName, ContentValues cv,
			String where, String[] whereArgs, SQLiteDatabase db) {
		int count = db.update(tableName, cv, where, whereArgs);
		return count;
	}
	
	/**
	 * 查询数据
	 * @param klass 要映射的类
	 * @param columns 查询的字段， null 取所有
	 * @param where	where条件， null 忽略条件
	 * @param whereArgs	where的参数， null无参数
	 * @param order	order语句， null忽略order by
	 * @param limit	limit语句， null忽略limit
	 * @param db	数据库句柄
	 * @return 查询后的数据
	 */
	protected static <T extends OpenDroid> List<T> query(Class<T> klass, String[] columns, 
			String where, String[] whereArgs, String order, String limit, SQLiteDatabase db) {
		List<T> resultList = new ArrayList<T>(); // 存放结果
		String tableName = klass.getSimpleName(); // 获取类名（表名）
		Cursor cursor = null;
		
		try {
			cursor = db.query(tableName, columns, where, whereArgs, null, null, order, limit);
			foreachCursor(klass, cursor, resultList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(cursor != null) {
				cursor.close();
			}
		}
		
		return resultList;
	}
	
	/**
	 * 遍历数据库游标
	 * @param klass	要映射的类
	 * @param cursor 要遍历的游标
	 * @param resultList 存放返回的结果
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private static <T extends OpenDroid> void foreachCursor(Class<T> klass,
			Cursor cursor, List<T> resultList) throws InstantiationException,
			IllegalAccessException {
		T t; // 反射出的实例
		String columnName; // 数据库字段名
		String methodName; // 方法名
		Method m; // 反射出的方法
		
		for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()) {
			t = klass.newInstance();  // 通过反射进行实例化
			for(int i=0;i<cursor.getColumnCount();i++) {
				columnName = cursor.getColumnName(i); // 获取数据库字段名
				try {
					switch (cursor.getType(i)) {
					case Cursor.FIELD_TYPE_INTEGER:
						// 如果字段名是_id的话， 对应的方法是setId
						methodName = columnName.equals("_id") ? "setId" : 
							getMethodName(cursor.getColumnName(i));
						m = klass.getMethod(methodName, int.class); // 反射出方法
						m.invoke(t, cursor.getInt(i)); // 执行方法
						break;
					case Cursor.FIELD_TYPE_FLOAT:
						methodName = getMethodName(cursor.getColumnName(i));
						m = klass.getMethod(methodName, float.class);
						m.invoke(t, cursor.getFloat(i));
						break;
					default:
						methodName = getMethodName(cursor.getColumnName(i));
						m = klass.getMethod(methodName, String.class);
						m.invoke(t, cursor.getString(i));
						break;
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			resultList.add(t);
		}
	}
	
	/**
	 * 根据字段名构建方法名
	 * @param columnName
	 * @return
	 */
	public static String getMethodName(String columnName) {
		String methodName = columnName;
		methodName = Character.toUpperCase(methodName.charAt(0)) + methodName.substring(1);
		methodName = "set" + methodName;
		
		return methodName;
	}
}
