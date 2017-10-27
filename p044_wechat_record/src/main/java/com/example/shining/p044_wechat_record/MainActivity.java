package com.example.shining.p044_wechat_record;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.shining.p044_wechat_record.application.DemoApplication;
import com.example.shining.p044_wechat_record.audiomanagerset.view.AudioRecorderButton;
import com.example.shining.p044_wechat_record.audiomanagerset.manager.MediaManager;
import com.example.shining.p044_wechat_record.audiomanagerset.domain.Recorder;
import com.example.shining.p044_wechat_record.audiomanagerset.view.RecorderAdapter;
import com.example.shining.p044_wechat_record.greendaoset.greendao.RecorderDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    private RecorderAdapter mAdapter;
    private List<Recorder> mDatas = new ArrayList<Recorder>();

    private AudioRecorderButton mAudioRecorderButton;

    private View mAnimView;

    private RecorderDao mRecorderDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecorderDao = DemoApplication.get().getDaoSession().getRecorderDao();
        mListView = (ListView) findViewById(R.id.id_listview);
        mAudioRecorderButton = (AudioRecorderButton) findViewById(R.id.id_recorder_button);
        mAudioRecorderButton
                .setAudioFinishRecorderListener(new AudioRecorderButton.AudioFinishRecorderListener() {
                    @Override
                    public void onFinish(float seconds, String filePath) {
                        Log.e("--seconds--", seconds + "");
                        Recorder recorder = new Recorder(null, seconds, filePath);
                        mRecorderDao.insert(recorder);
                        mDatas.add(recorder);
                        mAdapter.notifyDataSetChanged();
                        mListView.setSelection(mDatas.size() - 1);
                    }
                });

        //获取本地音频文件
        mDatas = mRecorderDao.loadAll();
        mAdapter = new RecorderAdapter(this, mDatas);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                mAdapter.setmCurPos(position);

                if (mAnimView != null) {
                    mAnimView.setBackgroundResource(R.drawable.adj);
                    mAnimView = null;
                }
                // 播放动画
                mAnimView = view.findViewById(R.id.id_recorder_anim);
                mAnimView.setBackgroundResource(R.drawable.play_anim);
                AnimationDrawable anim = (AnimationDrawable) mAnimView
                        .getBackground();
                anim.start();
                // 播放音频
                MediaManager.playSound(mDatas.get(position).filePath,
                        new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                mAnimView.setBackgroundResource(R.drawable.adj);
                                mAdapter.setmCurPos(-1);
                            }
                        });
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        MediaManager.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MediaManager.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MediaManager.release();
    }

}
