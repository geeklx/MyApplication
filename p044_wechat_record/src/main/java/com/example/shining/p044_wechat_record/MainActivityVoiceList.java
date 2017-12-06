package com.example.shining.p044_wechat_record;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shining.p044_wechat_record.application.DemoApplication;
import com.example.shining.p044_wechat_record.audiomanagerset.domain.Recorder;
import com.example.shining.p044_wechat_record.audiomanagerset.manager.MediaManager;
import com.example.shining.p044_wechat_record.audiomanagerset.view.AudioRecorderButton;
import com.example.shining.p044_wechat_record.audiomanagerset.view.RecorderAdapter;
import com.example.shining.p044_wechat_record.greendaoset.greendao.RecorderDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivityVoiceList extends AppCompatActivity {
    private ListView mListView;
    private RecorderAdapter mAdapter;
    private List<Recorder> mDatas = new ArrayList<Recorder>();

    private AudioRecorderButton mAudioRecorderButton;

    private View mAnimView;

    private RecorderDao mRecorderDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_voice_list);
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
        mAdapter.setOnVoiceClickListener(new RecorderAdapter.OnVoiceClickListener() {
            @Override
            public void onItemVoice(TextView id_recorder_anim, int position) {
                mAdapter.setmCurPos(position);

                if (MediaManager.mMediaPlayer != null && MediaManager.mMediaPlayer.isPlaying()) {
                    mAdapter.set_anim_voice_bg(id_recorder_anim);
                    mAdapter.setmCurPos(-1);
                    MediaManager.mMediaPlayer.reset();
                    return;
                }
//                if (id_recorder_anim != null) {
//                    id_recorder_anim.setBackgroundResource(R.drawable.adj);
//                    id_recorder_anim = null;
//                }
                // 播放动画
                mAdapter.set_anim_voice(id_recorder_anim);
                // 播放音频
                final TextView finalId_recorder_anim = id_recorder_anim;
                MediaManager.playSound(mDatas.get(position).filePath,
                        new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                mAdapter.set_anim_voice_bg(finalId_recorder_anim);
                                mAdapter.setmCurPos(-1);
                            }
                        });
            }
        });
//        mListView.setOnItemClickListener(new OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//
//            }
//        });
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
