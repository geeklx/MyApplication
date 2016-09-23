package com.yzxdemo.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import com.yzxdemo.R;
import com.yzx.tools.CustomLog;
import com.yzxdemo.action.UIDfineAction;
import com.yzxdemo.tools.Config;
import com.yzxdemo.tools.DfineAction;
import com.yzxdemo.tools.LoginConfig;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginDialog extends Dialog{

	private ListView login_list;
	private Context mcontext;
	private String currentSelectClient = "";
	private LoginAdapter adapter;
	private long mTime = 0;
	private ProgressDialog mProgressDialog;//登录进度
	private Timer timer;//登录超时定时器
	
	public LoginDialog(Context context) {
		super(context);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.dialog_login);
		mcontext = context;
		initViews();
	}

	private void initViews() {	
		login_list = (ListView)findViewById(R.id.login_list);
		adapter = new LoginAdapter(mcontext);
		login_list.setAdapter(adapter);
		//登入第二步：选中子账户进行登入
		findViewById(R.id.login_client_bt).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(currentSelectClient.length() > 0 && Integer.parseInt(currentSelectClient) < Config.getClient_id().split(",").length){
					if ((System.currentTimeMillis() - mTime) > 5000) {
						mTime = System.currentTimeMillis();
						LoginConfig.saveCurrentClientId(mcontext, "");
						startCallTimer();
						showProgressDialog();
						mcontext.sendBroadcast(new Intent(UIDfineAction.ACTION_LOGIN).putExtra("cliend_id", Config.getClient_id().split(",")[Integer.parseInt(currentSelectClient)]).putExtra("cliend_pwd", Config.getClient_token().split(",")[Integer.parseInt(currentSelectClient)])
								.putExtra("sid", Config.getMain_account()).putExtra("sid_pwd", Config.getMain_token()));
						//Toast.makeText(mcontext, "正在登入账号,请稍等！5秒钟没反应，请再次点击！", Toast.LENGTH_SHORT).show();	
						CustomLog.i(DfineAction.TAG_TCP, "cliend_id  "+Config.getClient_id().split(",")[Integer.parseInt(currentSelectClient)]);
						CustomLog.i(DfineAction.TAG_TCP, "cliend_pwd  "+Config.getClient_token().split(",")[Integer.parseInt(currentSelectClient)]);
						CustomLog.i(DfineAction.TAG_TCP, "sid  "+Config.getMain_account());
						CustomLog.i(DfineAction.TAG_TCP, "sid_pwd  "+Config.getMain_token());
					}
				}else{
					LoginConfig.saveCurrentClientId(mcontext, "");
					Toast.makeText(mcontext, "选择一个测试用户", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	

	class LoginAdapter extends BaseAdapter{
		private ArrayList<String> clientList = new ArrayList<String>();
		private ArrayList<String> phoneList = new ArrayList<String>();
		private LayoutInflater mInflater;
		private ArrayList<ChildClient> clients = new ArrayList<ChildClient>();
		
		public LoginAdapter(Context context) {
			clientList.addAll(Arrays.asList(Config.getClient_id().split(",")));
			phoneList.addAll(Arrays.asList(Config.getMobile().split(",")));
			mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			for(int i=0;i<clientList.size();i++){
				ChildClient child = new ChildClient();
				child.setClientid(clientList.get(i));
				clients.add(child);
			}
		}
		
		@Override
		public int getCount() {
			return clientList.size();
		}


		@Override
		public Object getItem(int position) {
			return clientList.get(position);
		}

		@Override
		public long getItemId(int position) {

			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder = null;
			if (null == convertView) {
				viewHolder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.listitem_login, null);
				viewHolder.login_list_iv = (ImageView) convertView.findViewById(R.id.login_list_iv);
				viewHolder.login_list_cb = (CheckBox) convertView.findViewById(R.id.login_list_cb);
				viewHolder.login_list_tv_client = (TextView) convertView.findViewById(R.id.login_list_tv_client);
				viewHolder.login_list_tv_phone = (TextView) convertView.findViewById(R.id.login_list_tv_phone);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			
			switch(position){
			case 0:
				viewHolder.login_list_iv.setBackgroundResource(R.drawable.head1_small);
				break;
			case 1:
				viewHolder.login_list_iv.setBackgroundResource(R.drawable.head2_small);
				break;
			case 2:
				viewHolder.login_list_iv.setBackgroundResource(R.drawable.head3_small);
				break;
			case 3:
				viewHolder.login_list_iv.setBackgroundResource(R.drawable.head4_small);
				break;
			case 4:
				viewHolder.login_list_iv.setBackgroundResource(R.drawable.head5_small);
				break;
			case 5:
				viewHolder.login_list_iv.setBackgroundResource(R.drawable.head6_small);
				break;
			}
			viewHolder.login_list_tv_client.setText(clientList.get(position));
			viewHolder.login_list_tv_phone.setText(Config.getMobile(clientList.get(position)));
			if(clients.size()>position){
				viewHolder.login_list_cb.setChecked(clients.get(position).getIscheck());
			}
			viewHolder.login_list_cb.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					for(int i=0;i<clients.size();i++){
						clients.get(i).setIscheck(false);
					}
					if(clients.size()>position){
						clients.get(position).setIscheck(((CompoundButton) v).isChecked());
						if(((CompoundButton) v).isChecked()){
							currentSelectClient = position+"";
						}else {
							currentSelectClient = "";
						}
					}
					notifyDataSetChanged();
				}
			});

			return convertView;
		}
		
		class ViewHolder{
			public ImageView login_list_iv;
			public CheckBox login_list_cb;
			public TextView login_list_tv_client;
			public TextView login_list_tv_phone;
		}
		
	}
	
	class ChildClient{
		private String clientid="";
		private String phone="";
		private Boolean ischeck=false;
	
		public String getClientid() {
			return clientid;
		}
		
		public void setClientid(String clientid) {
			this.clientid = clientid;
		}
		
		public String getPhone() {
			return phone;
		}
		
		public void setPhone(String phone) {
			this.phone = phone;
		}
		
		public Boolean getIscheck() {
			return ischeck;
		}
		
		public void setIscheck(Boolean ischeck) {
			this.ischeck = ischeck;
		}
	}
	
	/**
	 * 显示登录进度
	 */
	private void showProgressDialog() {
		if (mProgressDialog == null) {
			mProgressDialog = new ProgressDialog(mcontext);
		}

		mProgressDialog.setIndeterminate(true);
		mProgressDialog.setCancelable(true);
		mProgressDialog.setMessage("正在登入账号,请稍等...");
		mProgressDialog.show();
	}
	
	/**
	 * 启动登录超时定时器
	 */
	private void startCallTimer() {
		stopCallTimer();
		if(timer == null){
			timer = new Timer();
		}
		
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				handler.sendEmptyMessage(0);
			}
		}, 30000);
	}
	
	/**
	 * 停止登录超时定时器
	 */
	private void stopCallTimer(){
		if (timer != null){
			timer.cancel();
			timer=null;
		}
	}
	
	private Handler handler = new Handler () {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == 0)
			{
				if(mProgressDialog != null){
					mProgressDialog.dismiss();
					mProgressDialog = null;
					Toast.makeText(mcontext, "登录失败",Toast.LENGTH_SHORT).show();
				}
			}

		}
	};

	@Override
	public void dismiss() {
		// TODO Auto-generated method stub
		super.dismiss();
		stopCallTimer();
	}
}
