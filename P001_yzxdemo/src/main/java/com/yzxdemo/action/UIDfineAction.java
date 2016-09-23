package com.yzxdemo.action;

import java.util.HashMap;

import com.yzx.api.UCSCall;

public class UIDfineAction {

	

	public static final HashMap<Integer,String> dialState = new HashMap<Integer,String>();
	static{
		//dialState.put(UCSCall.CALL_VOIP_ANSWER, "�����绰");
		dialState.put(UCSCall.CALL_VOIP_NOT_ENOUGH_BALANCE, "Óà¶î²»×ã");
		dialState.put(UCSCall.CALL_VOIP_BUSY, "¶Ô·½ÕýÃ¦");
		dialState.put(UCSCall.CALL_VOIP_REFUSAL, "¶Ô·½¾Ü¾ø");
		dialState.put(UCSCall.CALL_VOIP_NUMBER_ERROR, "±»½ÐºÅÂë²»ÔÚÏß");
		dialState.put(UCSCall.CALL_VOIP_REJECT_ACCOUNT_FROZEN, "±»ºÅÕË»§±»¶³½á");
		dialState.put(UCSCall.CALL_VOIP_ACCOUNT_FROZEN, "ÕË»§±»¶³½á");
		dialState.put(UCSCall.CALL_VOIP_ACCOUNT_EXPIRED, "Ö÷ºÅÕË»§¹ýÆÚ");
		dialState.put(UCSCall.CALL_VOIP_CALLYOURSELF, "²»ÄÜ²¦´ò×Ô¼º°ó¶¨ºÅÂë");
		dialState.put(UCSCall.CALL_VOIP_NETWORK_TIMEOUT, "ºô½ÐÇëÇó³¬Ê±");
		dialState.put(UCSCall.CALL_VOIP_NOT_ANSWER, "¶Ô·½ÎÞÈËÓ¦´ð");
		dialState.put(UCSCall.CALL_VOIP_TRYING_183, "±»½Ð²»ÔÚÏß,×ªÖ±²¦");
		dialState.put(UCSCall.CALL_VOIP_RINGING_180, "¶Ô·½ÕýÔÚÏìÁå");
		dialState.put(UCSCall.CALL_VOIP_SESSION_EXPIRATION, "¼øÈ¨Ê§°Ü(TCPÎ´ÈÏÖ¤)");
		dialState.put(UCSCall.CALL_VOIP_ERROR, "·þÎñÆ÷´íÎó");
		dialState.put(UCSCall.HUNGUP_NOT_ANSWER, "±»½Ð·½Ã»ÓÐÓ¦´ð");
		dialState.put(UCSCall.HUNGUP_MYSELF, "×Ô¼º¹Ò¶Ïµç»°");
		dialState.put(UCSCall.HUNGUP_OTHER, "¶Ô·½¹Ò¶Ïµç»°");
		dialState.put(UCSCall.HUNGUP_WHILE_2G, "2G²»ÄÜ²¦´òÃâ·Ñ¡¢Ö±²¦¡¢ÊÓÆµºÍÖÇÄÜÍ¨»°");
		dialState.put(UCSCall.HUNGUP_REFUSAL, "¶Ô·½¾Ü¾ø½ÓÌý");
		dialState.put(UCSCall.HUNGUP_NOT_ENOUGH_BALANCE, "Óà¶î²»×ã");
		dialState.put(UCSCall.HUNGUP_MYSELF_REFUSAL, "×Ô¼º¾Ü¾ø½ÓÌý");
		dialState.put(UCSCall.CALL_VIDEO_DOES_NOT_SUPPORT, "¸ÃÉè±¸²»Ö§³ÖÊÓÆµÍ¨»°");
	}
	
	
	public static final String FROM_NUM_KEY = "fromsernum";
	public static final String TO_NUM_KEY = "tosernum";
	public static final String CALL_UID= "call_uid";
	public static final String CALL_PHONE= "call_phone";
	public static final String RESULT_KEY = "result";
	public static final String REASON_KEY = "reason";
	public static String ACTION_LOGIN = "com.yzx.login";
	public static String ACTION_LOGOUT = "com.yzx.logout";
	public static String ACTION_TCP_LOGIN_RESPONSE = "com.yzx.tcp_login_response";
	public static String ACTION_TCP_LOGIN_CLIENT_RESPONSE = "com.yzx.tcp_login_client_response";
	public static String ACTION_SEND_FILE_PROGRESS = "com.yzx.send_file";
	
	public static String ACTION_DIAL = "com.yzx.dial";
	public static String ACTION_DIAL_STATE = "com.yzx.dial.state";
	public static String ACTION_DIAL_HANGUP = "com.yzx.dial.hangup";
	public static String ACTION_NETWORK_STATE = "com.yzx.network.state";
	public static String ACTION_ANSWER = "com.yzx.answer";
	public static String ACTION_CALL_BACK = "com.yzx.callback";
	public static String ACTION_CALL_TIME = "com.yzx.call_time";
	public static String ACTION_START_TIME = "com.yzx.start_time";
	//public static final String ACTION_STOP_TIME = "com.yzx.stop_time";
	public static String ACTION = "com.yzxproject.resetList";
	public static String ACTION_MSG = "com.yzxproject.end_failed";
	public static String ACTION_STATUS = "com.yzxproject.status";
	
	public static final int LOCATION = 10;		//�Զ�����Ϣ����  λ��
	public static final int FILE = 20;		//�Զ�����Ϣ����  ����
	
	
	public static void initAction(String packName){
		if(!ACTION_LOGIN.startsWith(packName)){
			ACTION_LOGIN = packName + "_" + ACTION_LOGIN;
			ACTION_LOGOUT = packName + "_" + ACTION_LOGOUT;
			ACTION_TCP_LOGIN_RESPONSE = packName + "_" + ACTION_TCP_LOGIN_RESPONSE;
			ACTION_TCP_LOGIN_CLIENT_RESPONSE = packName + "_" + ACTION_TCP_LOGIN_CLIENT_RESPONSE; 
			ACTION_SEND_FILE_PROGRESS = packName + "_" + ACTION_SEND_FILE_PROGRESS;
			ACTION_DIAL = packName + "_" + ACTION_DIAL;
			ACTION_DIAL_STATE = packName + "_" + ACTION_DIAL_STATE;
			ACTION_DIAL_HANGUP = packName + "_" + ACTION_DIAL_HANGUP;
			ACTION_NETWORK_STATE = packName + "_" + ACTION_NETWORK_STATE;
			ACTION_ANSWER = packName + "_" + ACTION_ANSWER;
			ACTION_CALL_BACK = packName + "_" + ACTION_CALL_BACK;
			ACTION_CALL_TIME = packName + "_" + ACTION_CALL_TIME;
			ACTION_START_TIME = packName + "_" + ACTION_START_TIME;
			ACTION = packName + "_" + ACTION;
			ACTION_MSG = packName + "_" + ACTION_MSG;
			ACTION_STATUS = packName + "_" + ACTION_STATUS;
		}
	}
	
}
