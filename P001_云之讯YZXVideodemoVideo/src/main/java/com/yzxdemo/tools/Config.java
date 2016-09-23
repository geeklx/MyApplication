package com.yzxdemo.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.yzx.tools.CustomLog;
import com.yzx.tools.FileTools;
import com.yzxdemo.action.UIDfineAction;

import android.content.Context;
import android.content.Intent;

/**
 * 加载账号/token配置文件
 * @author xiaozhenhua
 *
 */
public class Config {
	private static Properties properties;

	private static String main_account;
	private static String main_token;
	private static String client_id;
	private static String client_token;
	private static String token;
	private static String mobile;
	private static String appid;
	private static String isTest;
	private static boolean isToken = false;
	//private static String verificationCodeSid;
	//private static String verificationCodeAppid;

	public static String getMain_account() {
		return main_account != null && main_account.length() > 0 ? main_account : "";
	}

	public static String getMain_token() {
		return main_token != null && main_token.length() > 0 ? main_token : "";
	}

	public static String getClient_id() {
		return client_id != null && client_id.length() > 0 ? client_id : "";
	}

	public static String getClient_token() {
		return client_token != null && client_token.length() > 0 ? client_token : "";
	}

	public static String getToken(){
		return token != null && token.length() > 0 ? token : "";
	}
	
	public static String getMobile() {
		return mobile != null && mobile.length() > 0 ? mobile : "";
	}
	
	public static String getAppid() {
		return appid != null && appid.length() > 0 ? appid : "";
	}

	/*public static String getVerificationCodeSid() {
		return verificationCodeSid != null && verificationCodeSid.length() > 0 ? verificationCodeSid:"";
	}

	public static String getVerificationCodeAppid() {
		return verificationCodeAppid != null && verificationCodeAppid.length() > 0 ? verificationCodeAppid:"";
	}*/

	public static String getMobile(String client){
		HashMap<String,String> mobileMap = new HashMap<String,String>();
		String[] clients = getClient_id().split(",");
		String[] mobiles = getMobile().split(",");
		for(int i = 0 ; i < mobiles.length ; i ++){
			if(mobiles != null && mobiles[i] != null && mobiles[i].length() > 0){
				mobileMap.put(clients[i], mobiles[i]);
			}
		}
		return mobileMap.get(client);
	}
	
	
	public static boolean isToken(){
		return isToken;
	}
	
	public static void initProperties(Context mContext) {
		if (properties == null) {
			properties = new Properties();
		}else{
			properties.clear();
		}
		
		File sdfile = new File(FileTools.getDefaultSdCardPath()+"/config.properties");
		File rootfile = new File(FileTools.getExternalSdCardPath()+"/config.properties");
		
		if(sdfile.exists()){
			String str = loadConfigFile(sdfile.getPath());
			if(str != null && str.length() > 0){
				try {
					properties.load(new FileInputStream(sdfile));
					loadConfig();
					//CustomLog.v(DfineAction.TAG_TCP,"读取SD卡  ... ");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else if(rootfile.exists()){
			String str = loadConfigFile(rootfile.getPath());
			if(str != null && str.length() > 0){
				try {
					properties.load(new FileInputStream(rootfile));
					loadConfig();
					//CustomLog.v(DfineAction.TAG_TCP,"读取手机内存  ... ");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else{
			try {
				properties.load(mContext.getResources().getAssets().open("config.properties"));
				loadConfig();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void loadConfig() {
		if(properties.containsKey("main_account")){
			isToken = false;
			main_account = properties.getProperty("main_account");
			main_token = properties.getProperty("main_token");
			client_id = properties.getProperty("client_id");
			client_token = properties.getProperty("client_token");
			mobile = properties.getProperty("mobile");
			appid = properties.getProperty("appid");
			isTest = properties.getProperty("istest");
			if(isTest!=null && isTest.equals("test")){
				DataTools.istest=true;
			}else {
				DataTools.istest=false;
			}
			//verificationCodeAppid = properties.getProperty("verappid");
			//verificationCodeSid = properties.getProperty("versid");
			token = null;
		}else{
			main_account = null;
			main_token = null;
			client_id = null;
			client_token = null;
			appid = null;
			isTest = null;
			isToken = true;
			token = properties.getProperty("token");
		}
	}
	
	
	private static String loadConfigFile(String filePaht){
		String config = null;
		BufferedReader reader = null;
		try {
			String line = null;
			File file = new File(filePaht);
			if (file.exists()) {
				StringBuilder sbf = new StringBuilder();
				reader = new BufferedReader(new FileReader(file));
				while ((line = reader.readLine()) != null) {
					sbf.append(line.trim());
				}
				config = sbf.toString().trim();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return config;
	}
	
	
	public static void parseConfig(String json,Context mContext){
		File file = new File(FileTools.getDefaultSdCardPath()+"/config.properties");
		if(file.exists()){
			file.delete();
		}
		boolean createFileSuccess = false;
		try {
			createFileSuccess = file.createNewFile();
			CustomLog.v("1-CREATE_FILE:"+createFileSuccess);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(!createFileSuccess){
			file = new File(FileTools.getExternalSdCardPath()+"/config.properties");
			if(file.exists()){
				file.delete();
			}
			try {
				CustomLog.v("2-CREATE_FILE:"+file.createNewFile());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		StringBuffer buffer = new StringBuffer();
		int result = 1;
		try {
			JSONObject jsonInput = new JSONObject(json);
			if(jsonInput.has("resp")){
				JSONObject jsonObj = jsonInput.getJSONObject("resp");
				if(jsonObj.has("respCode") && jsonObj.getString("respCode").equals("000000")){
					buffer.append("#time:"+System.currentTimeMillis()).append("\r\n\r\n");
					if(jsonObj.has("sid") && jsonObj.has("token")){
						buffer.append("main_account="+jsonObj.get("sid").toString()).append("\r\n\r\n");
						buffer.append("main_token="+jsonObj.get("token").toString()).append("\r\n\r\n");
					}
					if(jsonObj.has("appId")){
						buffer.append("appid="+jsonObj.get("appId").toString()).append("\r\n\r\n");
					}
					StringBuffer buffer_client = new StringBuffer("client_id=");
					StringBuffer buffer_client_pwd = new StringBuffer("client_token=");
					StringBuffer buffer_mobile = new StringBuffer("mobile=");
					StringBuffer buffer_istest = new StringBuffer("istest=");
					if(jsonObj.has("client")){
						JSONArray array = jsonObj.getJSONArray("client");
						for(int i = 0 ; i < array.length() ; i ++){
							JSONObject jsonClient = (JSONObject) array.get(i);
							if(jsonClient.has("client_number") && jsonClient.has("client_pwd") && jsonClient.has("mobile")){
								buffer_client.append(jsonClient.getString("client_number")+",");
								buffer_client_pwd.append(jsonClient.getString("client_pwd")+",");
								String mobile = jsonClient.getString("mobile");
								if(mobile != null && mobile.length() > 0){
									buffer_mobile.append(mobile+",");
								}else{
									buffer_mobile.append(",");
								}
							}
						}
					}
					if(DataTools.istest){
						buffer_istest.append("test");
					}else {
						buffer_istest.append("notest");
					}
					buffer_client.append("\r\n\r\n");
					buffer_client_pwd.append("\r\n\r\n");
					buffer_mobile.append("\r\n\r\n");
					buffer_istest.append("\r\n\r\n");
					RandomAccessFile raf = new RandomAccessFile(file, "rw");
					raf.seek(file.length());
					raf.write(buffer.toString().getBytes());
					raf.write(buffer_client.toString().getBytes());
					raf.write(buffer_client_pwd.toString().getBytes());
					raf.write(buffer_mobile.toString().getBytes());
					raf.write(buffer_istest.toString().getBytes());
					raf.close();
					result = 0;
				}else{
					if(jsonObj.has("respCode")){
						result = Integer.parseInt(jsonObj.getString("respCode"));
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
			result = 10;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			result = 11;
		} catch (IOException e) {
			e.printStackTrace();
			result = 12;
		}finally{
			mContext.sendBroadcast(new Intent(UIDfineAction.ACTION_TCP_LOGIN_RESPONSE).putExtra(UIDfineAction.RESULT_KEY, result));
		}
	}

}

