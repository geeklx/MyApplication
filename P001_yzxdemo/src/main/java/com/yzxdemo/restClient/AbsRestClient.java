package com.yzxdemo.restClient;

import java.io.ByteArrayInputStream;
import java.security.KeyStore;


import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import com.yzx.tcp.packet.PacketDfineAction;
import com.yzx.tools.CustomLog;
import com.yzxdemo.tools.DataTools;

public abstract class AbsRestClient {

	// public static String ip = "172.16.10.32";
	// public static int port = 8080;
	//public static String ip = "api.ucpaas.com";
	//public static String ip = "113.31.89.144";

	public static String version = "2014-06-30";

	public StringBuffer getStringBuffer() {
		StringBuffer sb = new StringBuffer("https://");
		if(DataTools.istest){
			//公司测试专用
			sb.append("113.31.89.144").append(":").append("443");
		}else{
			//开发者使用
			sb.append("api.ucpaas.com").append(":").append("443");//.append("/rest");// /impl
		}
		
		return sb;
	}

	public DefaultHttpClient getDefaultHttpClient() {
		if(DataTools.istest){
			//公司测试专用
			HttpClient httpclient = getNewHttpClient();
			return (DefaultHttpClient) httpclient;
		}else{
			//开发者使用
			DefaultHttpClient httpclient = new DefaultHttpClient();
			return httpclient;
		}
	}

	/**
	 * 
	 * @param version
	 * @param auth
	 * @param sig
	 * @param account
	 * @return String login
	 */
	public abstract String login(String username, String password);
	
	public abstract String queryAccountsInfo(String account,String token);

	public String getSignature(String accountSid, String authToken,
			String timestamp, EncryptUtil encryptUtil) throws Exception {
		String sig = accountSid + authToken + timestamp;
		String signature = encryptUtil.md5Digest(sig);
		return signature;
	}
	
	public HttpResponse post(String accountSid, String timestamp,
			String url, DefaultHttpClient httpclient, EncryptUtil encryptUtil,
			String body) throws Exception {
		
		HttpPost httppost = new HttpPost(url);
		CustomLog.v("BODY:" + body);
		CustomLog.v("REST_URL:" + url);
		httppost.setHeader("Accept", "application/json");
		httppost.setHeader("Content-Type", "application/json;charset=utf-8");
		String src = accountSid + ":" + timestamp;
		String auth = encryptUtil.base64Encoder(src);
		httppost.setHeader("Authorization", auth);
		if(body!= null && body.length() > 0){
			BasicHttpEntity requestBody = new BasicHttpEntity();
			requestBody.setContent(new ByteArrayInputStream(body.getBytes("UTF-8")));
			requestBody.setContentLength(body.getBytes("UTF-8").length);
			httppost.setEntity(requestBody);
		}
		// 执行客户端请求
		return httpclient.execute(httppost);
	}

	public HttpResponse get(String accountSid, String timestamp,
			String url, DefaultHttpClient httpclient, EncryptUtil encryptUtil) throws Exception{
		CustomLog.v("REST_GET_URL:" + url);
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json;charset=utf-8");
		String src = accountSid + ":" + timestamp;
		String auth = encryptUtil.base64Encoder(src);
		httpGet.setHeader("Authorization", auth);
		// 执行客户端请求
		return httpclient.execute(httpGet);
	}
	
	public static HttpClient getNewHttpClient() { 
		   try { 
		       KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType()); 
		       trustStore.load(null, null); 

		       SSLSocketFactoryEx sf = new SSLSocketFactoryEx(trustStore); 
		       sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER); 

		       HttpParams params = new BasicHttpParams(); 
		       HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1); 
		       HttpProtocolParams.setContentCharset(params, HTTP.UTF_8); 

		       SchemeRegistry registry = new SchemeRegistry(); 
		       registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80)); 
		       registry.register(new Scheme("https", sf, 443)); 

		       ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry); 

		       return new DefaultHttpClient(ccm, params); 
		   } catch (Exception e) { 
		       return new DefaultHttpClient(); 
		   } 
		} 
}
