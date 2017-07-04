package com.haiersmart.voice.activity;//package com.haiersmart.voice.activity;
//
//import android.content.ComponentName;
//import android.content.Intent;
//import android.content.ServiceConnection;
//import android.os.Bundle;
//import android.os.IBinder;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import com.haiersmart.sfnation.R;
//import com.haiersmart.voice.bean.event.ChooseEvent;
//import com.haiersmart.voice.bean.event.MessageEvent;
//import com.haiersmart.voice.service.MusicPlayerService;
//
//import org.greenrobot.eventbus.EventBus;
//import org.greenrobot.eventbus.Subscribe;
//import org.greenrobot.eventbus.ThreadMode;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.OnClick;
//import co.mobiwise.playerview.MusicPlayerView;
//
//public class MusicPlayerActivity extends BaseVoiceActivity implements View.OnClickListener {
//
//    @BindView(R.id.mpv)
//    MusicPlayerView mMpv;
//    @BindView(R.id.btn_stop)
//    Button mBtnStop;
//    @BindView(R.id.btn_back)
//    Button mBtnBack;
//    @BindView(R.id.tv_title_music)
//    TextView mTvTitle;
//
//    private String mMp3_audio_url;
//    private boolean flag;
//    private MusicPlayerService.MyBinder mBinder;
//    //存储列表的歌曲url
//    private ArrayList<String> musicList;
//    //存储列表歌曲的封面url
//    private ArrayList<String> coverList;
//    //存储歌曲的名字
//    private ArrayList<String> musicTitles;
////    private AudioResultBean mAudioResultBean;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        setContentView(R.layout.activity_music_player);
//        super.onCreate(savedInstanceState);
//        mMpv.setProgressVisibility(false);
//        initData();
//    }
//
//    private void initData() {
//
//        musicList = new ArrayList<>();
//        coverList = new ArrayList<>();
//        musicTitles = new ArrayList<>();
//        //获取传递过来的数据
//        Intent intent = getIntent();
//        if (intent == null) {
//            Log.d(TAG, "intent == null ");
//            return;
//        }
//        AudioResultBean audio = (AudioResultBean) intent.getSerializableExtra("audio");
//        AudioResultBean.ResultBeanX.IntentsBean.ResultBean.TracksBean tracks = audio.getResult().getIntents().get(0).getResult().getTracks();
//        List<AudioResultBean.ResultBeanX.IntentsBean.ResultBean.TrackListBean> track_list = audio.getResult().getIntents().get(0).getResult().getTrack_list();
//        if (track_list!=null&&track_list.size()>0) {
//            for (int i = 0; i < track_list.size(); i++) {
//                musicList.add(track_list.get(i).getMedia_url());
//                coverList.add(track_list.get(i).getCover_url());
//                musicTitles.add(track_list.get(i).getTrack_title());
//            }
//            mMp3_audio_url = tracks.getPlay_url_64();
//            if (TextUtils.isEmpty(mMp3_audio_url)) {
//                Log.d(TAG, "getPlay_url_64 == null");
//                mMp3_audio_url = tracks.getPlay_url_32();
//
//            }
//            String cover_url = tracks.getCover_url_large();
//
//            String title = tracks.getTrack_title();
//            if (!TextUtils.isEmpty(title)) {
//                mTvTitle.setText(title);
//            }else {
//                Log.d(TAG, "title is empty!!! ");
//            }
//            //设置MusicPlayerView的进度时长
//            if (!TextUtils.isEmpty(cover_url)) {
//                mMpv.setCoverURL(cover_url);
//            }else {
//                Log.d(TAG, "cover_url is empty!!! ");
//            }
//        }
//        bindService();
//    }
//
//    private void bindService() {
//        Intent intent = new Intent(this, MusicPlayerService.class);
//        intent.putStringArrayListExtra("musicList", musicList);
//        startService(intent);
//        bindService(intent, conn, BIND_AUTO_CREATE);
//    }
//
//    ServiceConnection conn = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            mBinder = (MusicPlayerService.MyBinder) service;
//            flag = true;
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//
//        }
//    };
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void ChooseCover(ChooseEvent event) {
//        int current = event.current;
//        Log.d(TAG, "ChooseCover: "+current);
//        mMpv.setCoverURL(coverList.get(current));
//        mTvTitle.setText(musicTitles.get(current));
//        if (mBinder!=null) {
//            mBinder.musicStart();
//        }
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void EventBus(MessageEvent event) {
//        boolean prepared = event.prepared;
//        if (prepared) {
//            mMpv.start();
//        }
//    }
//
//    @OnClick({R.id.mpv, R.id.btn_stop,R.id.btn_back})
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.mpv:
//                if (mMpv.isRotating()) {
////                    flag=true;
////                    Intent intent = new Intent(this, MusicPlayerService.class);
////                    intent.putExtra("path", mMp3_audio_url);
////                    intent.putExtra("flag", flag);
////                    bindService(intent,conn,BIND_AUTO_CREATE);
//                    if (flag) {
//                        mMpv.stop();
//                        mBinder.musicPause();
//                    }
//
//                } else {
////                    flag=false;
////                    Intent intent = new Intent(this, MusicPlayerService.class);
////                    intent.putExtra("flag", flag);
////                    bindService(intent,conn,BIND_AUTO_CREATE);
//                    if (flag) {
//                        mMpv.start();
//                        mBinder.musicStart();
//                    }
//                }
//                break;
//            case R.id.btn_stop:
//                mMpv.stop();
//                if (flag) {
//                    unbindService(conn);
//                    stopService(new Intent(this, MusicPlayerService.class));
//                    flag = false;
//                }
//                break;
//            case R.id.btn_back:
//                if (mBinder.isService()) {
//                    unbindService(conn);
//                    stopService(new Intent(this, MusicPlayerService.class));
//                    this.finish();
//                } else {
//                    this.finish();
//                }
//                break;
//            default:
//                break;
//        }
//
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        EventBus.getDefault().unregister(this);
//    }
//}
