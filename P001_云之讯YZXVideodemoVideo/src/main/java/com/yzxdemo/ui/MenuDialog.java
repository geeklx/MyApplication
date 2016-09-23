package com.yzxdemo.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.forwarddevelopmenttools.ForwardingListener;
import com.reason.UcsReason;
import com.yzx.api.UCSCall;
import com.yzx.tools.CustomLog;
import com.yzxdemo.R;
import com.yzxdemo.activity.LoginActivity;
import com.yzxdemo.tools.DfineAction;

public class MenuDialog extends Dialog{

	private Activity mContext;
	private LinearLayout menu_person;
	private LinearLayout menu_replace;
	private LinearLayout menu_feedback;
	private LinearLayout menu_about;
	private TextView menu_account;
	private TextView menu_type;
	private TextView menu_forwarding;
	private CheckBox menu_checkbox;
	private String mycenter_account;
	private String mycenter_type;
	private String mycenter_balance;
	

	public MenuDialog(Activity context, String account, String type, String balance) {
		super(context,R.style.menudialog);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_menu);
		setCanceledOnTouchOutside(true);
		mContext = context;
		mycenter_account = account;
		mycenter_type = type;
		mycenter_balance = balance;
		initViews();
		loadForwording();
		menu_account.setText(account);
		menu_type.setText(type);
		menu_forwarding.setText(UCSCall.isCallForwarding()?"呼转功能已开启":"呼转功能已关闭");
	}
	

	private void initViews() {
		menu_person = (LinearLayout) findViewById(R.id.menu_person);
		menu_replace = (LinearLayout) findViewById(R.id.menu_replace);
		menu_feedback = (LinearLayout) findViewById(R.id.menu_feedback);
		menu_about = (LinearLayout) findViewById(R.id.menu_about);
		menu_account  = (TextView) findViewById(R.id.menu_account);
		menu_type  = (TextView) findViewById(R.id.menu_type);
		menu_checkbox = (CheckBox) findViewById(R.id.menu_checkbox);
		menu_forwarding = (TextView) findViewById(R.id.menu_forwarding);
		
		//进入个人中心
		menu_person.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
//				Intent intent = new Intent(mContext,MyCenterActivity.class); 
//				intent.putExtra("account", mycenter_account);
//				intent.putExtra("type", mycenter_type);
//				intent.putExtra("balance", mycenter_balance);
//				mContext.startActivity(intent);
			}
		});
		//更换Client账号
		menu_replace.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(mContext,LoginActivity.class);
				intent.putExtra("AutoLogin", true);
				mContext.startActivity(intent);
				mContext.finish();
			}
		});
		//意见反馈
		menu_feedback.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				Intent intent = new Intent(mContext,FeedBackActivity.class); 
//				mContext.startActivity(intent);
			}
		});
		//关于
		menu_about.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
//				Intent intent = new Intent(mContext,AboutActivity.class); 
//				mContext.startActivity(intent);
			}
		});
		//开启关闭呼转功能
		menu_forwarding.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				UCSCall.setCallForwardingOption(mContext,!UCSCall.isCallForwarding(), new ForwardingListener() {
					@Override
					public void onCallForwardingIndicatorChanged(UcsReason reason) {
						CustomLog.i(DfineAction.TAG_TCP, "FORWORDINGl:"+reason.getReason());
						loadForwording();
					}
				});
			}
		});
	}
	
	private void loadForwording(){
		menu_checkbox.setChecked(UCSCall.isCallForwarding());
		menu_forwarding.setText(UCSCall.isCallForwarding()?"呼转功能已开启":"呼转功能已关闭");
	}
}
