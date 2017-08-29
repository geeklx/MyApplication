package com.example.opendroidyuanlibrary.db;

import android.util.Xml;

import com.example.opendroidyuanlibrary.bean.DBBean;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

public class OpenDroidHelper {
	public static final String TAG_DROID = "open-droid";
	public static final String TAG_VERSION = "version";
	public static final String TAG_NAME = "name";
	public static final String TAG_MAPPING = "mapping";
	
	private static DBBean sDBBean;
	
	public static DBBean getDBInfoBean() {
		if(sDBBean == null) {
			generateDBInfoBean();
		}
		
		return sDBBean;
	}
	
	/**
	 * 解析Asserts目录下的open_droid.xml文件，生成DBInfoBean
	 */
 	private static void generateDBInfoBean() {
		try {
			XmlPullParser pullParser = Xml.newPullParser();
			InputStream inputStream = OpenDroidYuanUtil.context.getAssets().open("open_droid.xml");
			pullParser.setInput(inputStream, "utf-8");
			
			int type = pullParser.getEventType();
			String tagName = null;
			
			while(type != XmlPullParser.END_DOCUMENT) {
				if(type == XmlPullParser.START_TAG) {
					tagName = pullParser.getName();
					if(tagName.equals(TAG_DROID)) {
						sDBBean = new DBBean();
					}else if(tagName.equals(TAG_VERSION)) {
						// 获取版本号
						sDBBean.setVersion(Integer.parseInt(pullParser.getAttributeValue(null, "value")));
					}else if(tagName.equals(TAG_NAME)) {
						// 获取数据库名
						sDBBean.setName(pullParser.getAttributeValue(null, "value"));
					}else if(tagName.equals(TAG_MAPPING)) {
						// 获取所有建表语句
						sDBBean.addSql(generateSql(pullParser));
					}
				}
				type = pullParser.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

 	/**
 	 * 生成建表sql语句
 	 * @param pullParser
 	 * @return
 	 * @throws ClassNotFoundException
 	 * @throws XmlPullParserException
 	 * @throws IOException
 	 */
	private static String generateSql(XmlPullParser pullParser)
			throws ClassNotFoundException, XmlPullParserException, IOException {
		// 反射获取class
		Class<OpenDroid> klass = (Class<OpenDroid>) Class.forName(pullParser.getAttributeValue(null, "class"));
		
		StringBuilder sql = new StringBuilder("create table ");
		// 获取类名， getSimpleName获取类名, getName()获取包名+类名
		sql.append(klass.getSimpleName()).append("(");
		// 自动创建一个_id
		sql.append("_id integer primary key autoincrement,");
		
		// 获取所有的字段
		Field[] fields = klass.getDeclaredFields();
		for(Field field : fields) {
			// 如果是public的， 则表示不是一个表的字段
			if(field.isAccessible()) {
				continue;
			}
			
			// 获取字段名
			String name = field.getName();
			sql.append(name).append(" ");
			
			// 获取字段类型
			Class<?> fieldType = field.getType();
			if(fieldType == String.class) {  // 如果是String
				sql.append("text,");
			}else if(fieldType == Integer.class || fieldType == int.class) {
				sql.append("integer,");
			}else if(fieldType == Long.class || fieldType == long.class){
				sql.append("integer,");
			}else if(fieldType == Boolean.class || fieldType == boolean.class) {
				sql.append("boolean,");
			}else if(fieldType == Float.class || fieldType == float.class) {
				sql.append("float,");
			}
		}
		sql.replace(sql.length() - 1, sql.length(), "");
		sql.append(");");
		
		return sql.toString();
	}
}
