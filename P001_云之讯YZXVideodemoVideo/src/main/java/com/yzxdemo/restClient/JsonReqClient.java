package com.yzxdemo.restClient;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.yzx.tools.CustomLog;
import com.yzxdemo.tools.DataTools;
import com.yzxdemo.tools.DfineAction;

public class JsonReqClient extends AbsRestClient {

	@Override
	public String login(String username, String password) {
		String result = "";
		DefaultHttpClient httpclient= getDefaultHttpClient();
		try {
			//MD5加密
			EncryptUtil encryptUtil = new EncryptUtil();
			// 构造请求URL内容
			String timestamp = dateToStr();// 时间戳
			String signature =getSignature(username,password,timestamp,encryptUtil);
			String url = getStringBuffer().append("/").append(version)
					.append("/Accounts/0")
					.append("/login")
					.append("?sig=").append(signature).toString();

			JSONObject json = new JSONObject();
			JSONObject account = new JSONObject();
			account.put("username", username);
			account.put("password", password);
			json.put("account", account);
			
			HttpResponse response=post(username, timestamp, url, httpclient, encryptUtil, json.toString());
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
				CustomLog.v("LOGIN_RESULT:"+result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			// 关闭连接
			httpclient.getConnectionManager().shutdown();
		}
		return result;
	}
	
	private static String dateToStr() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		return formatter.format(new Date());
	}

	@Override
	public String queryAccountsInfo(String account,String token) {
		String result = "";
		DefaultHttpClient httpclient= getDefaultHttpClient();
		// MD5加密
		EncryptUtil encryptUtil = new EncryptUtil();
		String timestamp = dateToStr();// 时间戳
		try {
			String signature = getSignature(account,token, timestamp, encryptUtil);
			String url = getStringBuffer().append("/").append(version)
					.append("/Accounts").append("/" + account)
					.append("?sig=" + signature).toString();
			
			HttpResponse response=get(account, timestamp, url, httpclient, encryptUtil);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			CustomLog.v(DfineAction.TAG_TCP,"QUERY_EXCEPTION:"+e.toString());
		} finally{
			// 关闭连接
			httpclient.getConnectionManager().shutdown();
		}
		return result;
	}
	
	
	public String bandClients(String accountSid, String token,String appId,
			String clientNumber,String mobile) {
		String result = "";
		DefaultHttpClient httpclient=getDefaultHttpClient();
		try {
			//MD5加密
			EncryptUtil encryptUtil = new EncryptUtil();
			// 构造请求URL内容
			String timestamp = dateToStr();// 时间戳
			String signature =getSignature(accountSid,token,timestamp,encryptUtil);
			String url = getStringBuffer().append("/").append(version)
					.append("/Accounts/").append(accountSid)
					.append("/Clients/band")
					.append("?sig=").append(signature).toString();
			
			
			JSONObject object = new JSONObject();
			object.put("appId", appId);
			object.put("clientNumber", clientNumber);
			object.put("mobile", mobile);
			JSONObject json = new JSONObject();
			json.put("client", object);
			
			//{"client":{"appId":"64cd514b3c39470d8b3a9b785655e7c4","clientNumber":"66807000000022","mobile":""}}

			HttpResponse response=post(accountSid, timestamp, url, httpclient, encryptUtil, json.toString());
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			// 关闭连接
		    httpclient.getConnectionManager().shutdown();
		}
		return result;
	}
	
	
	public String sendVerificationCode(String appid,String accountSid,String token,String code,String toPhone){
		String result = "";
		DefaultHttpClient httpclient=getDefaultHttpClient();
		try{
			//MD5加密
			EncryptUtil encryptUtil = new EncryptUtil();
			// 构造请求URL内容
			String timestamp = dateToStr();// 时间戳
			String signature =getSignature(accountSid,token,timestamp,encryptUtil);
			String url = getStringBuffer().append("/").append(version)
					.append("/Accounts/").append(accountSid)
					.append("/Messages/templateSMS")
					.append("?sig=").append(signature).toString();
			//CustomLog.v("APPID:"+appid);
			//CustomLog.v("SID:"+accountSid);
			//CustomLog.v("TOKEN:"+token);
			JSONObject object = new JSONObject();
			JSONObject json = new JSONObject();
			json.put("appId", appid);
			json.put("templateId", "1");
			json.put("to", toPhone);
			json.put("param", code);
			object.put("templateSMS", json);
			HttpResponse response=post(accountSid, timestamp, url, httpclient, encryptUtil, object.toString());
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			// 关闭连接
		    httpclient.getConnectionManager().shutdown();
		}
		return result;
	}
	
	public String sendVerificationAudio(String appid,String accountSid,String token,String code,String toPhone){
		String result = "";
		DefaultHttpClient httpclient=getDefaultHttpClient();
		try{
			//MD5加密
			EncryptUtil encryptUtil = new EncryptUtil();
			// 构造请求URL内容
			String timestamp = dateToStr();// 时间戳
			String signature =getSignature(accountSid,token,timestamp,encryptUtil);
			String url = getStringBuffer().append("/").append(version)
					.append("/Accounts/").append(accountSid)
					.append("/Calls/voiceCode")
					.append("?sig=").append(signature).toString();
			//CustomLog.v("APPID:"+appid);
			//CustomLog.v("SID:"+accountSid);
			//CustomLog.v("TOKEN:"+token);
			JSONObject object = new JSONObject();
			JSONObject json = new JSONObject();
			json.put("appId", appid);
			json.put("to", toPhone);
			json.put("verifyCode", code);
			object.put("voiceCode", json);
			HttpResponse response=post(accountSid, timestamp, url, httpclient, encryptUtil, object.toString());
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			// 关闭连接
		    httpclient.getConnectionManager().shutdown();
		}
		return result;
	}
	
}
