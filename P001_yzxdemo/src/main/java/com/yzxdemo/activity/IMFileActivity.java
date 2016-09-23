package com.yzxdemo.activity;

import java.io.File;
import java.util.ArrayList;

import com.reason.UcsReason;
import com.yzxdemo.R;
import com.yzx.api.UCSMessage;
import com.yzx.http.DownloadThread;
import com.yzx.listenerInterface.MessageListener;
import com.yzx.tcp.packet.UcsMessage;
import com.yzx.tools.CustomLog;
import com.yzx.tools.FileTools;
import com.yzxdemo.tools.DfineAction;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class IMFileActivity extends BaseActivity implements MessageListener {

	String msgid;
	DownloadThread downloadThread;
	String path;
	String fileName;
	TextView process;
	Button download;
	Button stop;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_imfile);
		
		process = (TextView)findViewById(R.id.process);
		download = (Button)findViewById(R.id.download_button);
		stop = (Button)findViewById(R.id.download_stop);
		msgid = getIntent().getStringExtra("msgid");
		path = getIntent().getStringExtra("path");
		fileName = getIntent().getStringExtra("fileName");
		final String formuid = getIntent().getStringExtra("formuid");
		
		if(path.startsWith("http:")){
			download.setText("下载");
		}else{
			download.setText("打开");
			stop.setVisibility(View.GONE);
		}
		download.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(path.startsWith("http:")){
					String localPath = FileTools.createFilePath(formuid)+fileName;
					downloadThread = UCSMessage.downloadAttached(path, localPath, msgid, IMFileActivity.this);
				}else{
					openFile(path);
				}
			}
		});
		stop.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(downloadThread != null){
					downloadThread.stopDownload();
				}
			}
		});
	}
	
	public void openFile(String filePath){
		Intent intent = new Intent();
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setAction(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(new File(filePath)), "*/*");
		startActivity(intent);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		UCSMessage.removeMessageListener(IMFileActivity.this);
	}

	@Override
	public void onReceiveUcsMessage(final UcsReason reason, UcsMessage arg1) {
		if(reason.getReason() != 0){
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(IMFileActivity.this, "下载文件失败:"+reason.getReason(), Toast.LENGTH_SHORT).show();
			}
		});
		}
	}

	@Override
	public void onSendFileProgress(int arg0) {
		
	}

	@Override
	public void onSendUcsMessage(UcsReason arg0, UcsMessage arg1) {
		
	}

	@Override
	public void onDownloadAttachedProgress(String msgId, String filePaht, final int sizeProgrss,final int currentProgress) {
		if(msgId.equals(msgid) && currentProgress>=sizeProgrss){
			path = filePaht;
			CustomLog.v(DfineAction.TAG_TCP,"AUDIO_CURRENT_PATH:"+filePaht);
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					download.setText("打开");}
			});
		}else{
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					process.setText("当前:"+currentProgress+"    总大小:"+sizeProgrss);
				}
			});
		}
	}

	@Override
	public void onUserState(ArrayList arg0) {
		
	}

}
