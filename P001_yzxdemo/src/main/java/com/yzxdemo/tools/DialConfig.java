package com.yzxdemo.tools;

import android.content.Context;

public class DialConfig {
	
	
	public static void saveDialPhone(Context mContext,String phone){
		String[] phones = getDialPhone(mContext);
		if(phones.length >= 20){
			phones[0] = "";
		}
		StringBuffer buffer = new StringBuffer();
		if(phones != null && phones.length > 0){
			for(int i = 0 ; i < phones.length ; i ++){
				if(phones[i].length() > 2){
					buffer.append(phones[i]+",");
				}
			}
		}
		
		if(buffer.toString().contains(phone)){
			buffer.replace(buffer.toString().indexOf(phone), buffer.toString().indexOf(phone)+phone.length() + 1, "");
		}
		buffer.append(phone+",");
		String phoneNumber = buffer.toString().substring(0,buffer.toString().length()-1);
		mContext.getSharedPreferences(LoginConfig.YUNZHIXUN_DEMO, 0).edit().putString(LoginConfig.YUNZHIXUN_DEMO+"_DIAL_PHONE", phoneNumber).commit();
	}
	
	public static String[] getDialPhone(Context mContext){
		return mContext.getSharedPreferences(LoginConfig.YUNZHIXUN_DEMO, 0).getString(LoginConfig.YUNZHIXUN_DEMO+"_DIAL_PHONE", "").split(",");
	}

	
	public static String getCallType(Context mContext){
		return mContext.getSharedPreferences(LoginConfig.YUNZHIXUN_DEMO, 0).getString(LoginConfig.YUNZHIXUN_DEMO+"_CALL_TYPE", "");
	}
	public static void saveCallType(Context mContext,String type){
		mContext.getSharedPreferences(LoginConfig.YUNZHIXUN_DEMO, 0).edit().putString(LoginConfig.YUNZHIXUN_DEMO+"_CALL_TYPE", type).commit();
	}
	
	
}
