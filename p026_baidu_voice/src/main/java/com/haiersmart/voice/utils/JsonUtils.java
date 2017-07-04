package com.haiersmart.voice.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class JsonUtils {

	/**
	 * 对象转json
	 * 
	 * @param object
	 * @param
	 * @return
	 */
	public static <T> String toJson(Object object) {
		Gson gson = new Gson();
		String jsonStr = gson.toJson(object);
		return jsonStr;
	}

	/**
	 * json转对象
	 * 
	 * @param jsonStr
	 * @param type
	 * @return
	 */
	public static <T> T fromJson(String jsonStr, Type type) {
		Gson gson = new Gson();
		T response = gson.fromJson(jsonStr, type);
		return response;
	}

	// 去除换行符
	public static String filteLineBreak(String jsonStr) {
		return jsonStr.replaceAll("\r|\n", "");
	}

	public static String filteLineBreakAnd2Short(String jsonStr) {
		String str = jsonStr.replaceAll("\r|\n", "");
		if (str.length() > 30) {
			str = str.substring(0, 22)+"...";
		}
		return str;
	}
}
