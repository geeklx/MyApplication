//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.opendroidlibrary.db;

import android.util.Xml;

import com.example.opendroidlibrary.bean.DBBean;

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

	public OpenDroidHelper() {
	}

	public static DBBean getDBInfoBean() {
		if(sDBBean == null) {
			generateDBInfoBean();
		}

		return sDBBean;
	}

	private static void generateDBInfoBean() {
		try {
			XmlPullParser e = Xml.newPullParser();
			InputStream inputStream = OpenDroidUtil.context.getAssets().open("open_droid.xml");
			e.setInput(inputStream, "utf-8");
			int type = e.getEventType();

			for(String tagName = null; type != 1; type = e.next()) {
				if(type == 2) {
					tagName = e.getName();
					if(tagName.equals("open-droid")) {
						sDBBean = new DBBean();
					} else if(tagName.equals("version")) {
						sDBBean.setVersion(Integer.parseInt(e.getAttributeValue(null, "value")));
					} else if(tagName.equals("name")) {
						sDBBean.setName(e.getAttributeValue(null, "value"));
					} else if(tagName.equals("mapping")) {
						sDBBean.addSql(generateSql(e));
					}
				}
			}
		} catch (Exception var4) {
			var4.printStackTrace();
		}

	}

	private static String generateSql(XmlPullParser pullParser) throws ClassNotFoundException, XmlPullParserException, IOException {
		Class klass = Class.forName(pullParser.getAttributeValue(null, "class"));
		StringBuilder sql = new StringBuilder("create table ");
		sql.append(klass.getSimpleName()).append("(");
		sql.append("_id integer primary key autoincrement,");
		Field[] fields = klass.getDeclaredFields();
		Field[] var4 = fields;
		int var5 = fields.length;

		for(int var6 = 0; var6 < var5; ++var6) {
			Field field = var4[var6];
			if(!field.isAccessible()) {
				String name = field.getName();
				sql.append(name).append(" ");
				Class fieldType = field.getType();
				if(fieldType == String.class) {
					sql.append("text,");
				} else if(fieldType != Integer.class && fieldType != Integer.TYPE) {
					if(fieldType != Long.class && fieldType != Long.TYPE) {
						if(fieldType != Boolean.class && fieldType != Boolean.TYPE) {
							if(fieldType == Float.class || fieldType == Float.TYPE) {
								sql.append("float,");
							}
						} else {
							sql.append("boolean,");
						}
					} else {
						sql.append("integer,");
					}
				} else {
					sql.append("integer,");
				}
			}
		}

		sql.replace(sql.length() - 1, sql.length(), "");
		sql.append(");");
		return sql.toString();
	}
}
