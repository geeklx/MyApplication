package com.example.shining.p038_wheelpicker;

import android.annotation.SuppressLint;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间工具类
 * 
 */
public class DateUtil {

	public static final String FORMATER_Y = "yyyy";
	public static final String FORMATER_YMD = "yyyy-MM-dd";
	public static final String FORMATER_YMD_CN = "yyyy年MM月dd日";
	public static final String FORMATER_YMDHMS = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMATER_YMDHMS_CN = "yyyy年MM月dd日  HH:mm:ss";
	public static final String FORMATER_CHAT1 = "yyyy-MM-dd";
	public static final String FORMATER_CHAT = "yyyy-MM-dd HH:mm";
	public static final String FORMATER_CHAT2 = "yyyy/MM/dd HH:mm";
	public static final String FORMATER_TIME_LINE = "MM/dd";
	public static final String FORMATER_HOME_NEW = "HH:mm";
	public static final int MINUTE60 = 3600;

	/**
	 * 格式化'秒
	 */
	public static String formatSeconds(Long useSecond) {
		int minute = (int) (useSecond / 60);
		String result = "";
		if (minute != 0) {
			result = minute + "'";
		}
		int second = (int) (useSecond - minute * 60);
		result = result + second + "''";
		return result;
	}

	/**
	 * 格式化日期(使用默认格式)
	 * 
	 * @param date
	 * @return
	 */
	public static String format_md(Date date) {
		return format(date, FORMATER_TIME_LINE);
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @param formatString
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String format(Date date, String formatString) {
		SimpleDateFormat df = new SimpleDateFormat(formatString);
		return df.format(date);
	}

	/**
	 * 格式化日期(使用默认格式)
	 * 
	 * @param date
	 * @return
	 */
	public static String format_y(Date date) {
		return format(date, FORMATER_Y);
	}

	/**
	 * 格式化日期(使用默认格式)
	 * 
	 * @param date
	 * @return
	 */
	public static String format_ymd(Date date) {
		return format(date, FORMATER_YMD);
	}

	/**
	 * 格式化日期CN(使用默认格式)
	 *
	 * @param date
	 * @return
	 */
	public static String format_ymd_cn(Date date) {
		return format(date, FORMATER_YMD_CN);
	}

	/**
	 * 格式化日期(使用默认格式)
	 * 
	 * @param date
	 * @return
	 */
	public static String format_chat1(Date date) {
		return format(date, FORMATER_CHAT1);
	}

	/**
	 * 格式化日期(使用默认格式)
	 * 
	 * @param date
	 * @return
	 */
	public static String format_chat2(Date date) {
		return format(date, FORMATER_CHAT2);
	}

	/**
	 * 格式化日期(使用默认格式)
	 * 
	 * @param date
	 * @return
	 */
	public static String format_chat(Date date) {
		return format(date, FORMATER_CHAT);
	}

	/**
	 * 格式化日期(使用默认格式)
	 * 
	 * @param date
	 * @return
	 */
	public static String format_ymdhms(Date date) {
		return format(date, FORMATER_YMDHMS);
	}

	/**
	 * 转换成日期
	 * 
	 * @param dateString
	 * @param formatString
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static Date parse(String dateString, String formatString) {
		SimpleDateFormat df = new SimpleDateFormat(formatString);
		try {
			return df.parse(dateString);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 转换成日期(使用默认格式)
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date parse_ymd(String dateString) {
		return parse(dateString, FORMATER_YMD);
	}

	/**
	 * 转换成日期(使用默认格式)
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date parse_ymdhms(String dateString) {
		return parse(dateString, FORMATER_YMDHMS);
	}

	/**
	 * 昨天
	 * 
	 * @return
	 */
	public static Date yesterday() {
		return addDay(-1);
	}

	/**
	 * 明天
	 * 
	 * @return
	 */
	public static Date tomorrow() {
		return addDay(1);
	}

	/**
	 * 现在
	 * 
	 * @return
	 */
	public static Date now() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 * 按日加
	 * 
	 * @param value
	 * @return
	 */
	public static Date addDay(int value) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_YEAR, value);
		return now.getTime();
	}

	/**
	 * 获取年月日时分秒
	 */
	public static String getYMDHMS() {
		StringBuffer current = new StringBuffer();
		current.append("本机时间:");
		current.append(DateUtil.year() + "年");

		String month = "";
		if (DateUtil.month() < 10) {
			month = "0" + DateUtil.month() + "月";
		} else {
			month = DateUtil.month() + "月";
		}
		current.append(month);

		String day = "";
		if (DateUtil.day() < 10) {
			day = "0" + DateUtil.day() + "日";
		} else {
			day = DateUtil.day() + "日";
		}
		current.append(day);

		String hour = "";
		if (DateUtil.hour() < 10) {
			hour = "0" + DateUtil.hour() + "时";
		} else {
			hour = DateUtil.hour() + "时";
		}
		current.append(hour);

		String minute = "";
		if (DateUtil.minute() < 10) {
			minute = "0" + DateUtil.minute() + "分";
		} else {
			minute = DateUtil.minute() + "分";
		}
		current.append(minute);

		String second = "";
		if (DateUtil.second() < 10) {
			second = "0" + DateUtil.second() + "秒";
		} else {
			second = DateUtil.second() + "秒";
		}
		current.append(second);

		return current.toString();
	}

	/**
	 * 年份
	 * 
	 * @return
	 */
	public static int year() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.YEAR);
	}

	/**
	 * 月份
	 * 
	 * @return
	 */
	public static int month() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.MONTH) + 1;
	}

	/**
	 * 日(号)
	 * 
	 * @return
	 */
	public static int day() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 小时(点)
	 * 
	 * @return
	 */
	public static int hour() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 分钟
	 * 
	 * @return
	 */
	public static int minute() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.MINUTE);
	}

	/**
	 * 秒
	 * 
	 * @return
	 */
	public static int second() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.SECOND);
	}

}
