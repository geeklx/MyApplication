package com.yzxdemo.activity;

import java.util.ArrayList;

import com.reason.UcsReason;
import com.yzxdemo.R;
import com.yzx.api.UCSMessage;
import com.yzx.http.DownloadThread;
import com.yzx.listenerInterface.MessageListener;
import com.yzx.listenerInterface.RecordListener;
import com.yzx.tcp.packet.UcsMessage;
import com.yzx.tools.CustomLog;
import com.yzx.tools.FileTools;
import com.yzxdemo.tools.DfineAction;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 收到语音文件下载关播放介面
 *
 */
public class IMAudioActivity extends BaseActivity implements RecordListener,MessageListener{

	String msgid;
	DownloadThread downloadThread;
	String remotePath;
	TextView process;
	Button download;
	Button stop;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_imaudio);
		
		process = (TextView)findViewById(R.id.process);
		download = (Button)findViewById(R.id.download_button);
		stop = (Button)findViewById(R.id.download_stop);
		
		msgid = getIntent().getStringExtra("msgid");
		remotePath = getIntent().getStringExtra("path");
		final String formuid = getIntent().getStringExtra("formuid");
		if(remotePath.startsWith("http:")){
			download.setText("下载");
		}else{
			download.setText("播放");
			stop.setVisibility(View.GONE);
		}
		
		download.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(remotePath.startsWith("http:")){
					String localPath = FileTools.createAudioFileName(formuid);
					downloadThread = UCSMessage.downloadAttached(remotePath, localPath, msgid, IMAudioActivity.this);
				}else{
					UCSMessage.startPlayerVoice(remotePath, IMAudioActivity.this);
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

	@Override
	protected void onDestroy() {
		UCSMessage.stopPlayerVoice();
		UCSMessage.removeMessageListener(IMAudioActivity.this);
		super.onDestroy();
	}

	@Override
	public void onSendUcsMessage(UcsReason reason, UcsMessage message) {
		
	}

	@Override
	public void onSendFileProgress(int progress) {
		
	}



	@Override
	public void onDownloadAttachedProgress(String msgId, String filePaht, final int sizeProgrss,final int currentProgress) {
		if(msgId.equals(msgid) && currentProgress>=sizeProgrss){
			remotePath = filePaht;
			CustomLog.v(DfineAction.TAG_TCP,"AUDIO_CURRENT_PATH:"+filePaht);
			UCSMessage.startPlayerVoice(filePaht, IMAudioActivity.this);
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					download.setText("播放");
					stop.setVisibility(View.GONE);
				}
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
	public void onReceiveUcsMessage(final UcsReason reason, UcsMessage arg1) {
		if(reason.getReason() != 0){
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Toast.makeText(IMAudioActivity.this, "下载文件失败:"+reason.getReason(), Toast.LENGTH_SHORT).show();
				}
			});
			}
	}

	@Override
	public void onFinishedPlayingVoice() {
		CustomLog.v(DfineAction.TAG_TCP,"播放完闭  ... ");
		UCSMessage.stopPlayerVoice();
	}

	@Override
	public void onFinishedRecordingVoice(int arg0) {
		CustomLog.v(DfineAction.TAG_TCP,"录音完成,时间为:"+arg0);
	}

	@Override
	public void onUserState(ArrayList arg0) {
		
	}
}
