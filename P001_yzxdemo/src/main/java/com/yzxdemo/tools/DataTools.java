package com.yzxdemo.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.widget.Toast;

public class DataTools {
	public static boolean istest = false;
	
	public static String getDate(long time) {
		String date = "";

		Calendar currentC = Calendar.getInstance();
		int currYear = currentC.get(Calendar.YEAR);
		int currDay = currentC.get(Calendar.DAY_OF_YEAR);

		Calendar msgC = Calendar.getInstance();
		Date msgData = new Date(time);
		msgC.setTime(msgData);
		int msgYear = msgC.get(Calendar.YEAR);
		int msgDay = msgC.get(Calendar.DAY_OF_YEAR);

		if (msgYear != currYear) {
			SimpleDateFormat sDateFormatYMD = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
			date = sDateFormatYMD.format(msgData);
		} else {
			if (msgDay + 1 == currDay) {
				SimpleDateFormat sDFormat = new SimpleDateFormat("昨天 HH:mm:ss");
				date = sDFormat.format(msgData);
			} else if (msgDay == currDay) {
				SimpleDateFormat sDFormat = new SimpleDateFormat("HH:mm:ss");
				date = sDFormat.format(msgData);
			} else {
				SimpleDateFormat sDateFormatMD = new SimpleDateFormat("MM月dd日HH:mm:ss");
				date = sDateFormatMD.format(msgData);
			}
		}
		return date;
	}
	
	public static boolean checkMobilePhoneNumber(String phone){
		return phone == null ? false:Pattern.compile("^1[3,4,5,7,8]\\d{9}$").matcher(phone).matches();
	} 
	
	
	public static String getSoftVersion(Context context){
		String version = "";
		try {
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			version = pi.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return version;
	}
}
