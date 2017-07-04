package com.haiersmart.voice.ui.dialog;//package com.haiersmart.sfnation630.ui.activity.main;
//
//import android.app.Activity;
//import android.app.Service;
//import android.content.BroadcastReceiver;
//import android.content.ComponentName;
//import android.content.ContentResolver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.content.ServiceConnection;
//import android.content.SharedPreferences;
//import android.media.AudioManager;
//import android.media.MediaPlayer;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.IBinder;
//import android.os.Message;
//import android.provider.Settings;
//import android.support.annotation.Nullable;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.CompoundButton;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.SeekBar;
//import android.widget.TextView;
//import android.widget.ToggleButton;
//
//import com.h6ah4i.android.widget.verticalseekbar.VerticalSeekBar;
//import com.haiersmart.sfnation.R;
//import com.haiersmart.sfnation.application.FridgeApplication;
//import com.haiersmart.sfnation.base.BaseActivity;
//import com.haiersmart.sfnation.bizutils.AnimUtil;
//import com.haiersmart.sfnation.bizutils.ToastUtil;
//import com.haiersmart.sfnation.bizutils.UvLoopTimerUtil;
//import com.haiersmart.sfnation.constant.ConstantUtil;
//import com.haiersmart.sfnation.dboper.status.FridgeStatusStatus;
//import com.haiersmart.sfnation.domain.FridgeStatus;
//import com.haiersmart.sfnation.enumbean.FridgeStatusTableName;
//import com.haiersmart.sfnation.fragment.setting.VolAndBrightnessSettingFragment;
//import com.haiersmart.sfnation.receiver.RemoteControllerReceiver;
//import com.haiersmart.sfnation.service.TimerService;
//import com.haiersmart.sfnation.service.modbusService;
//import com.haiersmart.sfnation.service.powerctl.PowerCtrlCmd;
//import com.haiersmart.sfnation.service.powerctl.PowerStateUpload;
//import com.haiersmart.sfnation.ui.mine.TftActivity;
//import com.haiersmart.sfnation630.ui.activity.wifi.WifiSettingDialog_630;
//import com.haiersmart.sfnation630.ui.dialog.SystemUpdateDialog_630;
//import com.haiersmart.utilslib.app.MyLogUtil;
//import com.haiersmart.utilslib.data.SpUtils;
//import com.haiersmart.utilslib.data.StringUtil;
//import com.unilife.common.utils.ToastMng;
//
//import java.util.Timer;
//import java.util.TimerTask;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//
///**
// * Created by Pengliang on 2016/8/15.
// */
//public class SettingDialogActivity extends BaseActivity implements View.OnClickListener {
//    @BindView(R.id.settings_back_img)
//    ImageView settingsBackImg;
//    private Button mBtSmart, mBtHoliday, mBtQuickCold, mBtQuickFreeze, mBtTitbit;
//    private TextView mTvCold, mTvFreeze, mTvChange, tv_colding, tv_freezing, tv_holiday, tv_smart,
//            tv_clearup, tvColdingText, btn_xunhuanzhouqi, test_code, to_tft_actibity, cold_tv;
//    private TextView tempercolding251, temperchanging251, temperfreezing251,
//            tempercolding401, temperfreezing401_1, temperfreezing401_2,
//            tv_cold_temp_476, tv_freeze_temp_476, tv_freeze_temp1_476,
//            temper_cold_630, temper_freeze_630, temper_change_630, tv_timer, mTvBright, bright_textView_show, screen_textView_show;
//    private RelativeLayout wifiLayout;
//    private RelativeLayout updateLayout;
//    private int[] currentMode = new int[]{0, 1, 0, 0};
//    private CheckBox peopleFeelCheckbox;
//    private VerticalSeekBar mColdTemperatureSeekbar;
//    private VerticalSeekBar mFreezeTemperatureSeekbar;
//    private VerticalSeekBar mChangeTemperatureSeekbar;
//    private VerticalSeekBar mVoiceSeekBar;
//    private VerticalSeekBar mScreenSeekBar;
//    private VerticalSeekBar mBrightSeekBar;
//    private LinearLayout tv_detail_layout;
//    @BindView(R.id.left_view)
//    View left_view;
//    @BindView(R.id.right_view)
//    View right_view;
//    @BindView(R.id.iv_guangbiao)
//    ImageView iv_guangbiao;
//
//    private int clickForTestNum = 5;
//    private long startTestClickTime;
//    private int times = 5 * 1000;//屏幕熄灭时间
//    private int COLDLEVEL = 2;
//    private int FREEZELEVEL = 2;
//    private int CHANGELEVEL = 2;
//    private SharedPreferences mySharedPreferences;
//    private static final int UPDATE_FRIDGE_STATUS = 1;
//    private modbusService msgService;
//    private TimerService timerService;
//    private FridgeStatus mFridgeStatus;
//    private FridgeStatusStatus mFridgeStatusStatus;
//
//    private TimerTask mTimerTask;
//
//    private boolean isHoldiday, isSmart, isQuickCold, isQuickFreeze, isCleanUp, isTibit;
//    private int[] mode1 = new int[]{0, 0, 0, 0};//holiday smart freeze cold
//    private int[] mode2 = new int[]{1, 0, 0, 0};
//    private int[] mode3 = new int[]{0, 1, 0, 0};
//    private int[] mode4 = new int[]{0, 0, 1, 0};
//    private int[] mode5 = new int[]{0, 0, 0, 1};
//    private int[] mode6 = new int[]{1, 0, 1, 1};
//    private int[] mode7 = new int[]{1, 0, 1, 0};
//    private int[] mode8 = new int[]{0, 0, 1, 1};
//    private int[] mode9 = new int[]{1, 0, 0, 1};
//    private int[] mode10 = new int[]{0, 1, 0, 1};
//    private int[] modeDetail = new int[4];
//    private boolean isLeavel;
//    private String frideMode;
//    private AudioManager audioManager;
//    private ContentResolver mCr;
//    private MediaPlayer mediaPlayer;
//    private int mBrightness;
//    private Activity mActivity;
//    private VolAndBrightnessSettingFragment.SettingsContentObserver settingsContentObserver;
//    private LinearLayout bianwenshi_layout;
//    public static final String FRIDGEMODE_251 = "251";
//    public static final String FRIDGEMODE_401 = "401";
//    public static final String FRIDGEMODE_595 = "595";
//    public static final String FRIDGEMODE_256 = "256";
//    public static final String FRIDGEMODE_476 = "476";
//    public static final String FRIDGEMODE_630 = "630";
//    public static String CURRENTFRIDGEMODE = "";
//    private ToggleButton tbColdingOpen;
//    private RelativeLayout binxgaing401_layout, binxgaing252_layout, rl_normal_mode, rl_476_mode, rl_timer_smart, rl_timer_stronger,
//            bingxiang476_layout, rl_uv_layout, rl_timer_476, bingxiang630_layout;
//    private boolean isModebusConnected, isColdingOpenOn, isTimerServiceBind;
//    private LinearLayout cold_layout, change_layout, freeze_layout, rl_timer_byself;
//    private boolean isFirstIn;
//    private Button rb_3_hour, rb_4_hour, rb_5_hour, rb_6_hour, rb_7_hour, rb_8_hour, rb_9_hour, btn_shajun_smart,
//            btn_shajun_strong, btn_shajun_byself, btn_smart_476, btn_holiday_476, btn_freeze_476, btn_cold_476;
//
//    @Override
//    protected int getLayoutId() {
//        //        return R.layout.activity_fridge_setting;
//        return R.layout.activity_setting_ai_control;
//    }
//
//    ServiceConnection conn = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            //            MyLogUtil.i("### onServiceConnected");
//            isModebusConnected = true;
//            msgService = ((modbusService.MsgBinder) service).getService();
//            msgService.setPowerCtrlFast(true);
//            //            sendFirstCommand();
//            startTimer();
//
//            if ((boolean) SpUtils.getInstance(SettingDialogActivity.this).get(ConstantUtil.ISSTATECODEOPEN, false)) {
//                test_code.setVisibility(View.VISIBLE);
//                test_code.setText(getSerialString(msgService.getFrameDataBuff()));
//            } else {
//                test_code.setVisibility(View.GONE);
//            }
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//            //            MyLogUtil.i("### onServiceDisconnected");
//            isModebusConnected = false;
//        }
//    };
//
//
//    ServiceConnection timerConn = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            isTimerServiceBind = true;
//            timerService = ((TimerService.MsgBinder) service).getService();
//            updateUvUI();
//
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//            isTimerServiceBind = false;
//        }
//    };
//
//
//    private String getSerialString(byte[] bytes) {
//        StringBuffer stringBuffer = new StringBuffer();//by holy
//        for (int counts = 0; counts < bytes.length; counts++) {//by holy
//            stringBuffer.append(String.format("%02x", bytes[counts]));//by holy
//            stringBuffer.append(" ");//by holy
//        }//by holy
//        return stringBuffer.toString();
//    }
//
//    private int coldTemperTrue;
//    private int changeTemperTrue;
//    private int freezeTemperTrue;
//
//    private Timer mTimer = new Timer();
//    private Handler mUpdateHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what) {
//                case UPDATE_FRIDGE_STATUS:
//                    FridgeStatus mFridgeStatus = (FridgeStatus) msg.obj;
//                    isHoldiday = mFridgeStatus.isHoliday();
//                    isSmart = mFridgeStatus.isSmart();
//                    isQuickCold = mFridgeStatus.isQuick_cooling();
//                    isQuickFreeze = mFridgeStatus.isQuick_freeze();
//                    isCleanUp = mFridgeStatus.isCleanup();
//                    isTibit = mFridgeStatus.isTitbit();
//                    FridgeStatus mFridgeStatus1 = PowerStateUpload.getInstance().getmFridgeStatus();
//                    coldTemperTrue = mFridgeStatus1.getCold_temp_show();
//                    changeTemperTrue = mFridgeStatus1.getMiddle_temp_show();
//                    freezeTemperTrue = mFridgeStatus1.getFreeze_temp_show();
//
//                    updateMode(isHoldiday, isSmart, isQuickCold, isQuickFreeze, isCleanUp, isTibit);
//                    //                        Log.d(TAG, "isHoldiday:" + isHoldiday + " isSmart:" + isSmart + " isQuickCold:" + isQuickCold + " isQuickFreeze:" + isQuickFreeze + " isTibit：" + isTibit);
//                    updateTemperUi(coldTemperTrue, changeTemperTrue, freezeTemperTrue);
//
//                    if ((boolean) SpUtils.getInstance(SettingDialogActivity.this).get(ConstantUtil.ISSTATECODEOPEN, false)) {
//                        test_code.setText(getSerialString(msgService.getFrameDataBuff()));
//                    } else {
//                        test_code.setVisibility(View.GONE);
//                    }
//
//                    updateTimerUI();
//
//                    break;
//                default:
//                    break;
//            }
//        }
//    };
//
//    private void updateTimerUI() {
//        if (isTimerServiceBind) {
//            if (timerService.uvTime <= 0) {
//                //                            timerEndShow();
//            } else {
//                long min = timerService.uvTime / 60;
//                long sec = timerService.uvTime % 60;
//                if (min < 10) {
//                    if (sec < 10) {
//                        tv_timer.setText("0" + min + ":0" + sec);
//                    } else {
//                        tv_timer.setText("0" + min + ":" + sec);
//                    }
//                } else {
//                    if (sec < 10) {
//                        tv_timer.setText(min + ":0" + sec);
//                    } else {
//                        tv_timer.setText(min + ":" + sec);
//                    }
//                }
//            }
//        }
//    }
//
//    private void updateTemperUi(int coldTemperTrue, int changeTemperTrue, int freezeTemperTrue) {
//        if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_401)) {
//           /* tempercolding401.setText(coldTemperTrue + "℃");
//            temperfreezing401_1.setText(freezeTemperTrue + "℃");
//            temperfreezing401_2.setText(freezeTemperTrue + "℃");*/
//            if (isModebusConnected) {
//                tempercolding401.setText(coldTemperTrue + "℃");
//                temperfreezing401_1.setText(freezeTemperTrue + "℃");
//                temperfreezing401_2.setText(freezeTemperTrue + "℃");
//            }
//        } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_251)) {
//           /* tempercolding251.setText(coldTemperTrue + "℃");
//            temperchanging251.setText(changeTemperTrue + "℃");
//            temperfreezing251.setText(freezeTemperTrue + "℃");*/
//            if (isModebusConnected) {
//                tempercolding251.setText(coldTemperTrue + "℃");
//                temperchanging251.setText(changeTemperTrue + "℃");
//                temperfreezing251.setText(freezeTemperTrue + "℃");
//            }
//        } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_256)) {
//           /* tempercolding251.setText(coldTemperTrue + "℃");
//            temperchanging251.setText(changeTemperTrue + "℃");
//            temperfreezing251.setText(freezeTemperTrue + "℃");*/
//            if (isModebusConnected) {
//                tempercolding251.setText(coldTemperTrue + "℃");
//                temperchanging251.setText(changeTemperTrue + "℃");
//                temperfreezing251.setText(freezeTemperTrue + "℃");
//            }
//        } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_476)) {
//            if (isModebusConnected) {
//                tv_cold_temp_476.setText(coldTemperTrue + "℃");
//                tv_freeze_temp_476.setText(freezeTemperTrue + "℃");
//                tv_freeze_temp1_476.setText(freezeTemperTrue + "℃");
//            }
//        } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_630)) {
//            if (isModebusConnected) {
//                temper_cold_630.setText(coldTemperTrue + "℃");
//                temper_freeze_630.setText(freezeTemperTrue + "℃");
//                temper_change_630.setText(changeTemperTrue + "℃");
//            }
//        }
//    }
//
//
//    @Override
//    protected void setup(@Nullable Bundle savedInstanceState) {
//        super.setup(savedInstanceState);
////        SmartBarInject.inject(this).show(SmartBar.HOME | SmartBar.BACK);
//        mySharedPreferences = getSharedPreferences("haier", Activity.MODE_PRIVATE);
//        findViews();
//
//        initRb476();
//        //        mCr = getContentResolver();
//        //        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
//        //        mediaPlayer = new MediaPlayer();
//        //        //TODO just hide auto
//        ////        mBrightnessSeekBar.setEnabled(!BrightnessTools.isAutoBrightness(mCr));
//        //        mBrightness = BrightnessTools.getScreenBrightness(FridgeSettingActivity.this);
//        //        setSeekBar();
//        //        setAutoBrightness();
//        //        settingsContentObserver = new SettingsContentObserver(FridgeSettingActivity.this, handler);
//        //        getContentResolver().registerContentObserver(android.provider.Settings.System.CONTENT_URI, true, settingsContentObserver);
//        //        //        frideMode = (String) SpUtils.getInstance(FridgeApplication.get()).get(ConstantUtil.FRIDGEMODE, "401");
//        //        //        MyLogUtil.d(TAG, "frideMode:::" + frideMode);
//        setCurrentMode();
//
//        init();//设置初始进度条
//        initVolumeValue();
//        setTouchListen();
//
//
//    }
//
//    //    public class SettingsContentObserver extends ContentObserver {
//    //        private AudioManager audioManager;
//    //
//    //        public SettingsContentObserver(Context context, Handler handler) {
//    //            super(handler);
//    //            audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
//    //        }
//    //
//    //        @Override
//    //        public boolean deliverSelfNotifications() {
//    //            return false;
//    //        }
//    //
//    //        @Override
//    //        public void onChange(boolean selfChange) {
//    //            int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
//    ////            int currentTZVolume = audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION);
//    //            MyLogUtil.d(TAG, "Volume now " + currentVolume);
//    //            mMediaSeekBar.setProgress(currentVolume);
//    ////            mNoticeSeekBar.setProgress(currentTZVolume);
//    //
//    //        }
//    //    }
//
//    private void findViews() {
//        mScreenSeekBar = (VerticalSeekBar) findViewById(R.id.screen_seekBar_set);
//        mBrightSeekBar = (VerticalSeekBar) findViewById(R.id.bright_seekBar_set);
//        bright_textView_show = (TextView) findViewById(R.id.bright_textView_show);
//        screen_textView_show = (TextView) findViewById(R.id.screen_textView_show);
//
//
//        bianwenshi_layout = (LinearLayout) findViewById(R.id.bianwenshi_layout);
//        mBtHoliday = (Button) findViewById(R.id.button16);
//        tbColdingOpen = (ToggleButton) findViewById(R.id.toggle_colding);
//        binxgaing401_layout = (RelativeLayout) findViewById(R.id.binxgaing401_layout);
//        binxgaing252_layout = (RelativeLayout) findViewById(R.id.binxgaing252_layout);
//        bingxiang630_layout = (RelativeLayout) findViewById(R.id.bingxiang630_layout);
//        rl_normal_mode = (RelativeLayout) findViewById(R.id.moshi_layout);
//        rl_476_mode = (RelativeLayout) findViewById(R.id.moshi_layout_476);
//        tv_detail_layout = (LinearLayout) findViewById(R.id.tv_detail_layout);
//        tempercolding251 = (TextView) findViewById(R.id.textView28);
//        temperchanging251 = (TextView) findViewById(R.id.textView68);
//        temperfreezing251 = (TextView) findViewById(R.id.textView54);
//        mColdTemperatureSeekbar = (VerticalSeekBar) findViewById(R.id.seekBar1);
//        mFreezeTemperatureSeekbar = (VerticalSeekBar) findViewById(R.id.seekBar2);
//        mChangeTemperatureSeekbar = (VerticalSeekBar) findViewById(R.id.seekBar3);
//        mVoiceSeekBar = (VerticalSeekBar) findViewById(R.id.voice_seekBar1);
//        peopleFeelCheckbox = (CheckBox) findViewById(R.id.people_feel_checkbox);
//        mTvBright = (TextView) findViewById(R.id.textView161);
//        mTvCold = (TextView) findViewById(R.id.textView69);
//        mTvFreeze = (TextView) findViewById(R.id.textView113);
//        mTvChange = (TextView) findViewById(R.id.textView70);
//
//        tv_colding = (TextView) findViewById(R.id.tv_colding);
//        tv_freezing = (TextView) findViewById(R.id.tv_freezing);
//        tv_holiday = (TextView) findViewById(R.id.tv_holiday);
//        tv_smart = (TextView) findViewById(R.id.tv_smart);
//        tv_clearup = (TextView) findViewById(R.id.tv_clearup);
//        test_code = (TextView) findViewById(R.id.test_code);
//
//        tvColdingText = (TextView) findViewById(R.id.textView150);
//
//        tempercolding401 = (TextView) findViewById(R.id.textView27);
//        temperfreezing401_1 = (TextView) findViewById(R.id.textView66);
//        temperfreezing401_2 = (TextView) findViewById(R.id.textView67);
//        cold_layout = (LinearLayout) findViewById(R.id.cold_layout);
//        change_layout = (LinearLayout) findViewById(R.id.change_layout);
//        freeze_layout = (LinearLayout) findViewById(R.id.freeze_layout);
//        mBtTitbit = (Button) findViewById(R.id.button3);
//        mBtSmart = (Button) findViewById(R.id.button15);
//        mBtQuickCold = (Button) findViewById(R.id.button18);
//        mBtQuickFreeze = (Button) findViewById(R.id.button17);
//        wifiLayout = (RelativeLayout) findViewById(R.id.wifi_layout);
//        updateLayout = (RelativeLayout) findViewById(R.id.update_layout);
//        wifiLayout.setOnClickListener(this);
//        updateLayout.setOnClickListener(this);
//        mBtSmart.setOnClickListener(this);
//        mBtHoliday.setOnClickListener(this);
//        mBtQuickCold.setOnClickListener(this);
//        mBtQuickFreeze.setOnClickListener(this);
//        mBtTitbit.setOnClickListener(this);
//        tbColdingOpen.setOnClickListener(this);
//        left_view.setOnClickListener(this);
//        right_view.setOnClickListener(this);
//
//        temper_cold_630 = (TextView) findViewById(R.id.temper_cold_630);
//        temper_freeze_630 = (TextView) findViewById(R.id.temper_freeze_630);
//        temper_change_630 = (TextView) findViewById(R.id.temper_change_630);
//        to_tft_actibity = (TextView) findViewById(R.id.text_view_300);
//        cold_tv = (TextView) findViewById(R.id.text_view_301);
//        cold_tv.setOnClickListener(this);
//        to_tft_actibity.setOnClickListener(this);
//    }
//
//    private void setTouchListen() {
//        tbColdingOpen.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (modeDetail[0] == 1) {
//                    shakeButton("假日");
//                    if (tbColdingOpen.isChecked()) {
//                        ToastUtil.showToastLong("假日模式已开启，如要关闭冷藏室请先退出假日模式");
//                    } else {
//                        ToastUtil.showToastLong("假日模式已开启，如要开启冷藏室请先退出假日模式");
//                    }
//                    return true;
//                } else if (modeDetail[1] == 1) {
//                    shakeButton("智能");
//                    if (tbColdingOpen.isChecked()) {
//                        ToastUtil.showToastLong("智能模式已开启，如要关闭冷藏室请先退出智能模式");
//                    } else {
//                        ToastUtil.showToastLong("智能模式已开启，如要开启冷藏室请先退出智能模式");
//                    }
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//        });
//        tbColdingOpen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (!isChecked) {
//                    //关闭冷藏室
//                    closeCold();
//                    mTvCold.setTextColor(getResources().getColor(R.color.black2));
//                    tvColdingText.setTextColor(getResources().getColor(R.color.black2));
//                } else {//开启冷藏室
//                    openCold();
//                    mTvCold.setTextColor(getResources().getColor(R.color.mine_others_background));
//                    tvColdingText.setTextColor(getResources().getColor(R.color.mine_others_background));
//                }
//                query();
//            }
//        });
//
//
//        cold_layout.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (!mColdTemperatureSeekbar.isEnabled() && isColdingOn()) {
//                    ToastUtil.showToastLong(getItMode(1) + "模式已开启，如要调节温度请先退出" + getItMode(1) + "模式");
//                } else if (!mColdTemperatureSeekbar.isEnabled() && !isColdingOn()) {
//                    ToastUtil.showToastLong("冷藏室已关闭，如要调节温度请先开启冷藏室");
//                }
//                shakeButton(getItMode(1));
//                return false;
//            }
//        });
//        change_layout.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (!mChangeTemperatureSeekbar.isEnabled()) {
//                    ToastUtil.showToastLong(getItMode(2) + "模式已开启，如要调节温度请先退出" + getItMode(2) + "模式");
//                }
//                shakeButton(getItMode(2));
//                return false;
//            }
//        });
//        freeze_layout.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (!mFreezeTemperatureSeekbar.isEnabled()) {
//                    ToastUtil.showToastLong(getItMode(3) + "模式已开启，如要调节温度请先退出" + getItMode(3) + "模式");
//                }
//                shakeButton(getItMode(3));
//                return false;
//            }
//        });
//
//
//        mBrightSeekBar.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                setScreenBrightness(((VerticalSeekBar) v).getProgress());
//                bright_textView_show.setText(((VerticalSeekBar) v).getProgress() + "");
//                return false;
//            }
//        });
//        mScreenSeekBar.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                // setScreenBrightness(((VerticalSeekBar)v).getProgress());
//                int percentage = ((VerticalSeekBar) v).getProgress();
//
//                if (percentage >= 0 && percentage < 14) {
//                    times = 15 * 1000;//15s
//                    screen_textView_show.setText("15秒");
//                } else if (percentage >= 14 && percentage < 28) {
//                    times = 30 * 1000;//30s
//                    screen_textView_show.setText("30秒");
//                } else if (percentage >= 28 && percentage < 42) {
//                    times = 60 * 1000;//1m
//                    screen_textView_show.setText("1分钟");
//                } else if (percentage >= 42 && percentage < 56) {
//                    times = 2 * 60 * 1000;//2m
//                    screen_textView_show.setText("2分钟");
//                } else if (percentage >= 56 && percentage < 70) {
//                    times = 5 * 60 * 1000;//5m
//                    screen_textView_show.setText("5分钟");
//                } else if (percentage >= 70 && percentage < 84) {
//                    times = 10 * 60 * 1000;//10m
//                    screen_textView_show.setText("10分钟");
//                } else {
//                    times = 15 * 60 * 1000;//15m
//                    screen_textView_show.setText("15分钟");
//                }
//                setTimeOfSetting(times);
//                //                SP.put(SetActivity.this, Config.SP_SCREEN_TIME, time);
//
//                return false;
//            }
//        });
//
//        peopleFeelCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    mFridgeStatusStatus.update(FridgeStatusTableName.pir_monitor, true);
//                    msgService.PowerSendCmd(PowerCtrlCmd.cmdSetPirOn630);
//                } else {
//                    mFridgeStatusStatus.update(FridgeStatusTableName.pir_monitor, false);
//                    msgService.PowerSendCmd(PowerCtrlCmd.cmdSetPirOff630);
//                }
//            }
//        });
//    }
//
//    private Button[] btnArrays;
//
//    private void initRb476() {
//        rb_3_hour = (Button) findViewById(R.id.rb_3_hour);
//        rb_4_hour = (Button) findViewById(R.id.rb_4_hour);
//        rb_5_hour = (Button) findViewById(R.id.rb_5_hour);
//        rb_6_hour = (Button) findViewById(R.id.rb_6_hour);
//        rb_7_hour = (Button) findViewById(R.id.rb_7_hour);
//        rb_8_hour = (Button) findViewById(R.id.rb_8_hour);
//        rb_9_hour = (Button) findViewById(R.id.rb_9_hour);
//        btn_xunhuanzhouqi = (TextView) findViewById(R.id.btn_xunhuanzhouqi);
//        btn_shajun_smart = (Button) findViewById(R.id.btn_shajun_smart);
//        btn_shajun_strong = (Button) findViewById(R.id.btn_shajun_strong);
//        btn_shajun_byself = (Button) findViewById(R.id.btn_shajun_byself);
//        rl_timer_smart = (RelativeLayout) findViewById(R.id.rl_timer_smart);
//        rl_timer_stronger = (RelativeLayout) findViewById(R.id.rl_timer_stronger);
//        rl_timer_byself = (LinearLayout) findViewById(R.id.rl_timer_byself);
//        btn_smart_476 = (Button) findViewById(R.id.btn_smart_476);
//        btn_holiday_476 = (Button) findViewById(R.id.btn_holiday_476);
//        btn_freeze_476 = (Button) findViewById(R.id.btn_freeze_476);
//        btn_cold_476 = (Button) findViewById(R.id.btn_cold_476);
//        bingxiang476_layout = (RelativeLayout) findViewById(R.id.bingxiang476_layout);
//        rl_uv_layout = (RelativeLayout) findViewById(R.id.rl_uv_layout);
//        rl_timer_476 = (RelativeLayout) findViewById(R.id.rl_timer_476);
//        tv_cold_temp_476 = (TextView) findViewById(R.id.tv_cold_temp_476);
//        tv_freeze_temp_476 = (TextView) findViewById(R.id.tv_freeze_temp_476);
//        tv_freeze_temp1_476 = (TextView) findViewById(R.id.tv_freeze_temp1_476);
//        tv_timer = (TextView) findViewById(R.id.tv_timer);
//        btn_smart_476.setOnClickListener(this);
//        btn_holiday_476.setOnClickListener(this);
//        btn_freeze_476.setOnClickListener(this);
//        btn_cold_476.setOnClickListener(this);
//        btnArrays = new Button[]{rb_3_hour, rb_4_hour, rb_5_hour, rb_6_hour, rb_7_hour, rb_8_hour, rb_9_hour,};
//        rb_3_hour.setOnClickListener(this);
//        rb_4_hour.setOnClickListener(this);
//        rb_5_hour.setOnClickListener(this);
//        rb_6_hour.setOnClickListener(this);
//        rb_7_hour.setOnClickListener(this);
//        rb_8_hour.setOnClickListener(this);
//        rb_9_hour.setOnClickListener(this);
//        btn_shajun_smart.setOnClickListener(this);
//        btn_shajun_strong.setOnClickListener(this);
//        btn_shajun_byself.setOnClickListener(this);
//
//
//    }
//
//
//    private void pressRb(Button button) {
//        for (Button btn : btnArrays) {
//            if (btn.getId() == button.getId()) {
//                if (btn.getTextColors().getDefaultColor() == -1) {
//                    btn.setBackground(getResources().getDrawable(R.drawable.btn_shajun_mode_white));
//                    btn.setTextColor(getResources().getColor(R.color.black));
//                } else {
//                    btn.setBackground(getResources().getDrawable(R.drawable.btn_shajun_mode));
//                    btn.setTextColor(getResources().getColor(R.color.white));
//                }
//            } else {
//                btn.setBackground(getResources().getDrawable(R.drawable.btn_shajun_mode_white));
//                btn.setTextColor(getResources().getColor(R.color.black));
//            }
//        }
//    }
//
//
//    private void pressRbforUI(Button button) {
//        for (Button btn : btnArrays) {
//            if (btn.getId() == button.getId()) {
//                btn.setBackground(getResources().getDrawable(R.drawable.btn_shajun_mode));
//                btn.setTextColor(getResources().getColor(R.color.white));
//            } else {
//                btn.setBackground(getResources().getDrawable(R.drawable.btn_shajun_mode_white));
//                btn.setTextColor(getResources().getColor(R.color.black));
//            }
//        }
//    }
//
//
//    private void setCurrentMode() {
//        frideMode = (String) SpUtils.getInstance(FridgeApplication.get()).get(ConstantUtil.FRIDGEMODE, ConstantUtil.DEFAULTMODE);
//        // TODO: 2016/11/4 测试用 用完注释掉
////        frideMode = "630";
//        if (frideMode.contains(FRIDGEMODE_251)) {
//            CURRENTFRIDGEMODE = FRIDGEMODE_251;
//        } else if (frideMode.contains(FRIDGEMODE_401)) {
//            CURRENTFRIDGEMODE = FRIDGEMODE_401;
//        } else if (frideMode.contains(FRIDGEMODE_256)) {
//            CURRENTFRIDGEMODE = FRIDGEMODE_256;
//        } else if (frideMode.contains(FRIDGEMODE_401)) {
//            CURRENTFRIDGEMODE = FRIDGEMODE_401;
//        } else if (frideMode.contains(FRIDGEMODE_476)) {
//            CURRENTFRIDGEMODE = FRIDGEMODE_476;
//        } else if (frideMode.contains(FRIDGEMODE_630)) {
//            CURRENTFRIDGEMODE = FRIDGEMODE_630;
//        } else {
//            CURRENTFRIDGEMODE = FRIDGEMODE_630;
//        }
//
//        if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_251)) {
//            tbColdingOpen.setVisibility(View.VISIBLE);
//            binxgaing252_layout.setVisibility(View.VISIBLE);
//            binxgaing401_layout.setVisibility(View.GONE);
//            bingxiang476_layout.setVisibility(View.GONE);
//            rl_normal_mode.setVisibility(View.VISIBLE);
//            rl_476_mode.setVisibility(View.GONE);
//            tv_detail_layout.setVisibility(View.GONE);
//        } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_401)) {
//            mBtHoliday.setText("净化");
//            //            tv_colding.setText("速冷模式：在一定时间内令冷藏室快速制冷");
//            tbColdingOpen.setVisibility(View.GONE);
//            bianwenshi_layout.setVisibility(View.GONE);
//            binxgaing252_layout.setVisibility(View.GONE);
//            binxgaing401_layout.setVisibility(View.VISIBLE);
//            bingxiang476_layout.setVisibility(View.GONE);
//            bingxiang630_layout.setVisibility(View.GONE);
//            rl_normal_mode.setVisibility(View.VISIBLE);
//            rl_476_mode.setVisibility(View.GONE);
//            tv_detail_layout.setVisibility(View.GONE);
//        } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_595)) {
//            bianwenshi_layout.setVisibility(View.INVISIBLE);
//        } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_256)) {
//            tbColdingOpen.setVisibility(View.VISIBLE);
//            tv_detail_layout.setVisibility(View.GONE);
//            mBtTitbit.setVisibility(View.VISIBLE);
//            binxgaing252_layout.setVisibility(View.VISIBLE);
//            binxgaing401_layout.setVisibility(View.GONE);
//            bingxiang476_layout.setVisibility(View.GONE);
//            bingxiang630_layout.setVisibility(View.GONE);
//            rl_normal_mode.setVisibility(View.VISIBLE);
//            bianwenshi_layout.setVisibility(View.VISIBLE);
//            rl_476_mode.setVisibility(View.GONE);
//        } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_476)) {//476
//            tbColdingOpen.setVisibility(View.GONE);
//            tv_detail_layout.setVisibility(View.GONE);
//            mBtTitbit.setVisibility(View.GONE);
//            bianwenshi_layout.setVisibility(View.GONE);
//            rl_normal_mode.setVisibility(View.GONE);
//            rl_476_mode.setVisibility(View.VISIBLE);
//            binxgaing252_layout.setVisibility(View.GONE);
//            binxgaing401_layout.setVisibility(View.GONE);
//            bingxiang476_layout.setVisibility(View.VISIBLE);
//            bingxiang630_layout.setVisibility(View.GONE);
//
//        } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_630)) {//630
//            tbColdingOpen.setVisibility(View.GONE);
//            tv_detail_layout.setVisibility(View.GONE);
//            mBtTitbit.setVisibility(View.GONE);
//            bianwenshi_layout.setVisibility(View.VISIBLE);
//            rl_normal_mode.setVisibility(View.VISIBLE);
//            rl_476_mode.setVisibility(View.GONE);
//            binxgaing252_layout.setVisibility(View.GONE);
//            binxgaing401_layout.setVisibility(View.GONE);
//            bingxiang476_layout.setVisibility(View.GONE);
//            bingxiang630_layout.setVisibility(View.VISIBLE);
//
//        }
//    }
//
//
//    public void startTimer() {
//        mTimerTask = new TimerTask() {
//            @Override
//            public void run() {
//                if (isLeavel) {
//                    mFridgeStatus = mFridgeStatusStatus.selectFridgeStatus();
//                    Message message = new Message();
//                    message.what = UPDATE_FRIDGE_STATUS;
//                    message.obj = mFridgeStatus;
//                    mUpdateHandler.sendMessage(message);
//                }
//            }
//        };
//        mTimer.schedule(mTimerTask, 0, 500);
//
//
//    }
//
//
//    public String getItMode(int which) {//1cold 2 change 3 freeze
//        switch (which) {
//            case 1:
//                return getStringMode(mTvCold.getText().toString());
//            case 2:
//                return getStringMode(mTvChange.getText().toString());
//            case 3:
//                return getStringMode(mTvFreeze.getText().toString());
//            default:
//                break;
//        }
//        return "";
//    }
//
//    public String getStringMode(String modeStr) {
//        if (modeStr.contains("智能")) {
//            return "智能";
//        } else if (modeStr.contains("假日")) {
//            return "假日";
//        } else if (modeStr.contains("速冻")) {
//            return "速冻";
//        } else if (modeStr.contains("速冷")) {
//            return "速冷";
//        } else if (modeStr.contains("珍品")) {
//            return "珍品";
//        }
//        return "";
//    }
//
//    public void shakeButton(String modeString) {
//        if (modeString.equals("智能")) {
//            unPress(mBtSmart);
//            unPress(btn_smart_476);
//            mBtSmart.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    if (isLeavel) {
//                        press(mBtSmart);
//                        press(btn_smart_476);
//                    }
//                }
//            }, 1000);
//
//        } else if (modeString.equals("假日")) {
//            unPress(mBtHoliday);
//            unPress(btn_holiday_476);
//            mBtHoliday.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    if (isLeavel) {
//                        press(mBtHoliday);
//                        press(btn_holiday_476);
//                    }
//                }
//            }, 1000);
//        } else if (modeString.equals("速冻")) {
//            unPress(mBtQuickFreeze);
//            unPress(btn_freeze_476);
//            mBtQuickFreeze.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    if (isLeavel) {
//                        press(mBtQuickFreeze);
//                        press(btn_freeze_476);
//                    }
//                }
//            }, 1000);
//        } else if (modeString.equals("速冷")) {
//            unPress(mBtQuickCold);
//            unPress(btn_cold_476);
//            mBtQuickCold.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    if (isLeavel) {
//                        press(mBtQuickCold);
//                        press(btn_cold_476);
//                    }
//                }
//            }, 1000);
//        } else if (modeString.equals("珍品")) {
//            unPress(mBtTitbit);
//            mBtTitbit.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    if (isLeavel) {
//                        press(mBtTitbit);
//                    }
//                }
//            }, 1000);
//        }
//    }
//
//
//    public void closeCold() {
//        SpUtils.getInstance(FridgeApplication.get()).put(ConstantUtil.ISCOLDOPENON, false);
//        mColdTemperatureSeekbar.setEnabled(false);
//        mTvCold.setEnabled(false);
//        tvColdingText.setEnabled(false);
//        if (isModebusConnected) {
//            if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_251)) {
//                msgService.PowerSendCmd(PowerCtrlCmd.cmdGearSetFreeze252Model, (byte) (0x00));
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_401)) {
//                msgService.PowerSendCmd(PowerCtrlCmd.cmdGearSetFreeze401Model, (byte) (0x00));
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_256)) {
//                msgService.PowerSendCmd(PowerCtrlCmd.cmdGearSetFreeze256Model, (byte) (0x00));
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_630)) {
//                msgService.PowerSendCmd(PowerCtrlCmd.cmdSetColdBar630, (byte) (0x00));
//            }
//        }
//    }
//
//
//    public void openCold() {
//        SpUtils.getInstance(FridgeApplication.get()).put(ConstantUtil.ISCOLDOPENON, true);
//        mColdTemperatureSeekbar.setEnabled(true);
//        mTvCold.setEnabled(true);
//        tvColdingText.setEnabled(true);
//        if (isModebusConnected) {
//            if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_251)) {
//                msgService.PowerSendCmd(PowerCtrlCmd.cmdGearSetFreeze252Model, (byte) (0x02 + mColdTemperatureSeekbar.getProgress()));
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_401)) {
//                msgService.PowerSendCmd(PowerCtrlCmd.cmdGearSetFreeze401Model, (byte) (0x01 + mColdTemperatureSeekbar.getProgress()));
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_256)) {
//                msgService.PowerSendCmd(PowerCtrlCmd.cmdGearSetFreeze256Model, (byte) (0x02 + mColdTemperatureSeekbar.getProgress()));
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_630)) {
//                msgService.PowerSendCmd(PowerCtrlCmd.cmdSetColdBar630, (byte) (0x01 + mColdTemperatureSeekbar.getProgress()));
//            }
//        }
//    }
//
//    @Override
//    public void onResume() {
//        isLeavel = true;
//        isFirstIn = true;
//        if (isModebusConnected) {
//            msgService.setPowerCtrlFast(true);
//        } else {
//            if (ConstantUtil.MODBUS_SERVICE_STARTUP) {
//                bindService(new Intent(this, modbusService.class), conn, Context.BIND_AUTO_CREATE);
//            }
//        }
//        if (!isTimerServiceBind) {
//            bindService(new Intent(this, TimerService.class), timerConn, Context.BIND_AUTO_CREATE);
//        }
//        oldtime1 = System.currentTimeMillis();
//        oldtime2 = System.currentTimeMillis();
//        oldtime3 = System.currentTimeMillis();
//        oldtime4 = System.currentTimeMillis();
//
//        IntentFilter intentFiltervolInput = new IntentFilter();
//        intentFiltervolInput.addAction(ConstantUtil.BROADCAST_ACTION_UPDATE_TEMPER_UI);
//        registerReceiver(receiveUpdateRemperUI, intentFiltervolInput);
//
//        super.onResume();
//    }
//
//    public void init() {
//        initLevel();
//        initBrightScreen();
//        mFridgeStatusStatus = FridgeStatusStatus.getInstance();
//        if (ConstantUtil.MODBUS_SERVICE_STARTUP) {
//
//            bindService(new Intent(this, modbusService.class), conn, Context.BIND_AUTO_CREATE);
//        }
//        if (!isTimerServiceBind) {
//            bindService(new Intent(this, TimerService.class), timerConn, Context.BIND_AUTO_CREATE);
//        }
//        mFridgeStatus = mFridgeStatusStatus.selectFridgeStatus();
//        peopleFeelCheckbox.setChecked(mFridgeStatus.isPir_monitor());   //初始化人感开关
//        mColdTemperatureSeekbar.setMax(COLDLEVEL);
//        //        mColdTemperatureSeekbar.setProgress(2);//TODO 获取冷藏室温度
//        mColdTemperatureSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                //                          seekBar.setProgress(getMediaCurrentVol());//TODO 设置冷藏室温度
//                if (b) {
//                    if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_251)) {
//                        mTvCold.setText((i + 2) + "℃");
//                    } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_401)) {
//                        mTvCold.setText((i + 1) + "℃");
//                    } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_595)) {
//                        mTvCold.setText((i + 2) + "℃");
//                    } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_256)) {
//                        mTvCold.setText((i + 2) + "℃");
//                    } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_476)) {
//                        mTvCold.setText((i + 1) + "℃");
//                    } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_630)) {
//                        mTvCold.setText((i + 1) + "℃");
//                    }
//                }
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                int progress = seekBar.getProgress();
//                if (isModebusConnected) {
//                    if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_251)) {
//                        msgService.PowerSendCmd(PowerCtrlCmd.cmdGearSetFreeze252Model, (byte) (0x02 + progress));
//                        mFridgeStatusStatus.update(FridgeStatusTableName.cold_level, 2 + progress);
//                    } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_401)) {
//                        msgService.PowerSendCmd(PowerCtrlCmd.cmdGearSetFreeze401Model, (byte) (0x01 + progress));
//                        mFridgeStatusStatus.update(FridgeStatusTableName.cold_level, 1 + progress);
//                    } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_256)) {
//                        msgService.PowerSendCmd(PowerCtrlCmd.cmdGearSetFreeze256Model, (byte) (0x02 + progress));
//                        mFridgeStatusStatus.update(FridgeStatusTableName.cold_level, 2 + progress);
//                    } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_476)) {
//                        msgService.PowerSendCmd(PowerCtrlCmd.cmdGearSetFreeze476Model, (byte) (0x01 + progress));
//                        mFridgeStatusStatus.update(FridgeStatusTableName.cold_level, 1 + progress);
//                    } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_630)) {
//                        msgService.PowerSendCmd(PowerCtrlCmd.cmdSetColdBar630, (byte) (0x01 + progress));
//                        mFridgeStatusStatus.update(FridgeStatusTableName.cold_level, 1 + progress);
//                    }
//                    query();
//                }
//            }
//        });
//        mFreezeTemperatureSeekbar.setMax(FREEZELEVEL);
//        //        mFreezeTemperatureSeekbar.setProgress(2);//TODO 获取冷冻室温度
//        mFreezeTemperatureSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_251)) {
//                    mTvFreeze.setText((i - 24) + "℃");
//                } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_401)) {
//                    mTvFreeze.setText((i - 23) + "℃");
//                } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_595)) {
//                    //                    MyLogUtil.d(TAG, "seekbari:::" + i);
//                    mTvFreeze.setText((i - 24) + "℃");
//                } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_256)) {
//                    //                    MyLogUtil.d(TAG, "seekbari:::" + i);
//                    //                    mTvFreeze.setText((i - 26) + "℃");
//                    mTvFreeze.setText((i - 24) + "℃");
//                } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_476)) {
//                    mTvFreeze.setText((i - 24) + "℃");
//                } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_630)) {
//                    mTvFreeze.setText((i - 23) + "℃");
//                }
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                int progress = seekBar.getProgress();
//                if (isModebusConnected) {
//                    if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_251)) {
//                        msgService.PowerSendCmd(PowerCtrlCmd.cmdGearSetRefrig252Model, (byte) (0xE8 + progress));
//                        mFridgeStatusStatus.update(FridgeStatusTableName.freeze_level, progress - 24);
//                    } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_401)) {
//                        msgService.PowerSendCmd(PowerCtrlCmd.cmdGearSetRefrig401Model, (byte) (0xE9 + progress));
//                        mFridgeStatusStatus.update(FridgeStatusTableName.freeze_level, progress - 23);
//                    } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_256)) {
//                        //                        msgService.PowerSendCmd(PowerCtrlCmd.cmdGearSetRefrig256Model, (byte) (0xE6 + progress));
//                        msgService.PowerSendCmd(PowerCtrlCmd.cmdGearSetRefrig256Model, (byte) (0xE8 + progress));
//                        //                        mFridgeStatusStatus.update(FridgeStatusTableName.freeze_level, progress - 26);
//                        mFridgeStatusStatus.update(FridgeStatusTableName.freeze_level, progress - 24);
//                    } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_476)) {
//                        msgService.PowerSendCmd(PowerCtrlCmd.cmdGearSetRefrig476Model, (byte) (0xE8 + progress));
//                        mFridgeStatusStatus.update(FridgeStatusTableName.freeze_level, progress - 24);
//                    } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_630)) {
//                        msgService.PowerSendCmd(PowerCtrlCmd.cmdSetFreezeBar630, (byte) (0xE9 + progress));
//                        mFridgeStatusStatus.update(FridgeStatusTableName.freeze_level, progress - 23);
//                    }
//                    query();
//
//                }
//
//            }
//        });
//
//
//        mChangeTemperatureSeekbar.setMax(CHANGELEVEL);
//        //        mChangeTemperatureSeekbar.setProgress(2);//TODO 获取变温室温度
//        mChangeTemperatureSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
//                //                seekBar.setProgress(getMedi|aCurrentVol());//TODO 设置变温室温度
//                if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_251)) {
//                    mTvChange.setText((-18 + i) + "℃");
//                } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_256)) {
//                    mTvChange.setText((-18 + i) + "℃");
//                } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_630)) {
//                    mTvChange.setText((-20 + i) + "℃");
//                }
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                int progress = seekBar.getProgress();
//                if (isModebusConnected) {
//                    if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_251)) {
//                        msgService.PowerSendCmd(PowerCtrlCmd.cmdGearSetVarTemp252Model, (byte) (0xEE + progress));
//                        mFridgeStatusStatus.update(FridgeStatusTableName.middle_level, progress - 18); //变温室档位(变温温度从-3℃到5℃线性9档)
//                        msgService.startUpdateMidTimer();
//                    } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_256)) {
//                        msgService.PowerSendCmd(PowerCtrlCmd.cmdGearSetVarTemp256Model, (byte) (0xEE + progress));
//                        mFridgeStatusStatus.update(FridgeStatusTableName.middle_level, progress - 18); //变温室档位(变温温度从-3℃到5℃线性9档)
//                        msgService.startUpdateMidTimer();
//                    } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_630)) {
//                        msgService.PowerSendCmd(PowerCtrlCmd.cmdSetChangeBar630, (byte) (0xEC + progress));
//                        mFridgeStatusStatus.update(FridgeStatusTableName.middle_level, progress - 20); //变温室档位(变温温度从-3℃到5℃线性9档)
//                        msgService.startUpdateMidTimer();
//                    }
//                    query();
//
//                }
//
//            }
//        });
//        mVoiceSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                //                seekBar.getProgress();
//                setVolumeValue(seekBar.getProgress());
//            }
//        });
//
//        updateTemperUI();
//
//
//    }
//
//    private AudioManager m_AudioManager;
//    private int m_MaxPressVolume;
//    private int m_MaxMusicVolume;
//
//    /**
//     * 获取音量
//     *
//     * @return
//     */
//    private int getVolume() {
//        if (mySharedPreferences == null) {
//            mySharedPreferences = getSharedPreferences("haier", Activity.MODE_PRIVATE);
//        }
//        int volume = mySharedPreferences.getInt("settings_volume", 5);
//        //        int volume = SharedPreferencesCacheUtil.loadIntegerData(CacheConfig.VOLUME_SILENT_VALUE);
//
//        if (0 == volume && null != m_AudioManager) {
//            volume = m_AudioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
//        }
//        return volume;
//    }
//
//    private void initBrightScreen() {
//        //        private VerticalSeekBar mBrightSeekBar;//bright_seekBar_set
//        //        private VerticalSeekBar mScreenSeekBar;//screen_seekBar_set
//        int time = getScreenOffTime();//获取屏幕熄灭时间
//        int second = time / 1000;
//        if (second == 15) {
//            screen_textView_show.setText("15秒");
//            mScreenSeekBar.setProgress(13);
//        } else if (second == 30) {
//            screen_textView_show.setText("30秒");
//            mScreenSeekBar.setProgress(26);
//        } else if (second == 60) {
//            screen_textView_show.setText("1分钟");
//            mScreenSeekBar.setProgress(40);
//        } else if (second == 120) {
//            screen_textView_show.setText("2分钟");
//            mScreenSeekBar.setProgress(55);
//        } else if (second == 300) {
//            screen_textView_show.setText("5分钟");
//            mScreenSeekBar.setProgress(70);
//        } else if (second == 600) {
//            screen_textView_show.setText("10分钟");
//            mScreenSeekBar.setProgress(82);
//        } else if (second == 900) {
//            screen_textView_show.setText("15分钟");
//            mScreenSeekBar.setProgress(100);
//        }
//
//        bright_textView_show.setText("" + getScreenBrightness());
//        mBrightSeekBar.setProgress(getScreenBrightness());
//    }
//
//    /**
//     * 设置系统休眠时间
//     */
//    public void setTimeOfSetting(int value) {
//        Settings.System.putInt(SettingDialogActivity.this.getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, value);
//    }
//
//
//    /**
//     * 设置屏幕亮度
//     *
//     * @param screenBrightness
//     */
//    private void setScreenBrightness(int screenBrightness) {
//
//        screenBrightness = screenBrightness * 255 / 100;
//        Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, screenBrightness);
//        Window window = getWindow();
//        WindowManager.LayoutParams params = window.getAttributes();
//        params.screenBrightness = screenBrightness / 255f;
//        window.setAttributes(params);
//    }
//
//    /**
//     * 获取屏幕亮度
//     *
//     * @return
//     */
//    private int getScreenBrightness() {
//        int screenBrightness = 0;
//        try {
//            screenBrightness = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
//        } catch (Settings.SettingNotFoundException e) {
//            e.printStackTrace();
//        }
//        screenBrightness = screenBrightness * 100 / 255;
//        return screenBrightness;
//    }
//
//    /**
//     * 获得锁屏时间  毫秒
//     */
//    private int getScreenOffTime() {
//        int screenOffTime = 0;
//        try {
//            screenOffTime = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT);
//        } catch (Exception localException) {
//
//        }
//        return screenOffTime;
//    }
//
//    /**
//     * 设置背光时间  毫秒
//     */
//    private void setScreenOffTime(int paramInt) {
//        try {
//            Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, paramInt);
//        } catch (Exception localException) {
//            localException.printStackTrace();
//        }
//    }
//
//    /**
//     * 设置声音音量
//     *
//     * @param value
//     */
//    private void setVolumeValue(int value) {
//        int pressVolume = value;
//        mTvBright.setText(value + "");
//        int musicVolume = value * m_MaxMusicVolume / m_MaxPressVolume;
//        m_AudioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, pressVolume, AudioManager.FLAG_PLAY_SOUND);
//        m_AudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, musicVolume, AudioManager.FLAG_VIBRATE);
//        int mVolue = Math.round(5 * (getVolume() * 1.0f / m_MaxPressVolume));
//
//        SharedPreferences.Editor editor = mySharedPreferences.edit();
//        editor.putInt("settings_volume", value);
//        editor.commit();
//        //        Intent it = new Intent(Utils.ACTION_VOLUE_MAIN);
//        //        it.putExtra("mVolue",mVolue);
//        //        sendBroadcast(it);
//    }
//
//    /**
//     * 初始化声音
//     */
//    private void initVolumeValue() {
//        if (m_AudioManager == null) {
//            m_AudioManager = (AudioManager) getSystemService(Service.AUDIO_SERVICE);
//        }
//        m_MaxPressVolume = m_AudioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);
//        m_MaxMusicVolume = m_AudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
//        //        mBrightSeekBar3.setMaxProssValue(m_MaxPressVolume);
//        mVoiceSeekBar.setMax(m_MaxPressVolume);
//        if (mySharedPreferences == null) {
//            mySharedPreferences = getSharedPreferences("haier", Activity.MODE_PRIVATE);
//        }
//        //         boolean isQuiet = mySharedPreferences.getBoolean("isQuiet", false);
//        //        if (isQuiet) {
//        //            int value = mySharedPreferences.getInt("settings_volume", 5);
//        //            mVoiceSeekBar.setProgress(value);
//        //            mTvBright.setText(value);
//        //        } else {
//        int value = getVolume();
//        mVoiceSeekBar.setProgress(value);
//        mTvBright.setText(value + "");
//        //        }
//    }
//
//    private void initLevel() {
//        if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_251)) {
//            COLDLEVEL = 8;
//            FREEZELEVEL = 8;
//            CHANGELEVEL = 23;
//
//        } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_401)) {
//            COLDLEVEL = 6;
//            FREEZELEVEL = 8;
//        } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_595)) {
//            COLDLEVEL = 6;
//            FREEZELEVEL = 8;
//        } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_256)) {
//            COLDLEVEL = 8;
//            FREEZELEVEL = 8;
//            CHANGELEVEL = 23;
//        } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_476)) {
//            COLDLEVEL = 7;
//            FREEZELEVEL = 10;
//        } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_630)) {
//            COLDLEVEL = 8;
//            FREEZELEVEL = 8;
//            CHANGELEVEL = 25;
//        }
//    }
//
//
//    @Override
//    public void onPause() {
//        isLeavel = false;
//        isFirstIn = false;
//        if (isModebusConnected && null != msgService) {
//            msgService.setPowerCtrlFast(false);
//        }
//        super.onPause();
//
//        unregisterReceiver(receiveUpdateRemperUI);
//
//    }
//
//
//    public void press(Button button) {
//        button.setBackgroundResource(R.drawable.btn_myfamily_sure_630);
//        button.setTextColor(getResources().getColor(R.color.mine_others_background));
//    }
//
//    public void unPress(Button button) {
//        button.setBackgroundResource(R.drawable.bt_mode_control_630);
//        button.setTextColor(getResources().getColor(R.color.mine_others_background));
//    }
//
//
//    private void updateMode(boolean isHoldiday, boolean isSmart, boolean isQuickCold, boolean isQuickFreeze, boolean isCleanUp, boolean isTibit) {
//        int[] mode = new int[]{0, 0, 0, 0};
//        if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_401)) {
//            if (isCleanUp) {
//                mode[0] = 0;
//                press(mBtHoliday);
//            } else {
//                mode[0] = 0;
//                unPress(mBtHoliday);
//            }
//        } else {
//            if (isHoldiday) {
//                mode[0] = 1;
//                if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_476)) {
//                    press(btn_holiday_476);
//                } else {
//                    press(mBtHoliday);
//                }
//            } else {
//                mode[0] = 0;
//                if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_476)) {
//                    unPress(btn_holiday_476);
//                } else {
//                    unPress(mBtHoliday);
//                }
//            }
//        }
//        if (isSmart) {
//            mode[1] = 1;
//            if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_476)) {
//                press(btn_smart_476);
//            } else {
//                press(mBtSmart);
//            }
//        } else {
//            mode[1] = 0;
//            if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_476)) {
//                unPress(btn_smart_476);
//            } else {
//                unPress(mBtSmart);
//            }
//        }
//
//        if (isQuickFreeze) {
//            mode[2] = 1;
//            if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_476)) {
//                press(btn_freeze_476);
//            } else {
//                press(mBtQuickFreeze);
//            }
//            msgService.startFreezeTimer();
//        } else {
//            mode[2] = 0;
//            if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_476)) {
//                unPress(btn_freeze_476);
//            } else {
//                unPress(mBtQuickFreeze);
//            }
//            msgService.stopFreezeTimer();
//        }
//        if (isQuickCold) {
//            mode[3] = 1;
//            if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_476)) {
//                press(btn_cold_476);
//            } else {
//                press(mBtQuickCold);
//            }
//            msgService.startColdTimer();
//
//        } else {
//            mode[3] = 0;
//            if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_476)) {
//                unPress(btn_cold_476);
//            } else {
//                unPress(mBtQuickCold);
//            }
//            msgService.stopColdTimer();
//        }
//
//        if (isTibit) {
//            press(mBtTitbit);
//        } else {
//            unPress(mBtTitbit);
//        }
//
//        setSeekBarEnableByMode(mode);
//        modeDetail = mode;
//
//    }
//
//
//    private void setSeekBarEnableByMode(int[] mode) {
//        String modeStr = StringUtil.ArraysToString(mode);
//        if (modeStr.equals(StringUtil.ArraysToString(mode1))) {
//            if (isColdingOn()) {
//                mColdTemperatureSeekbar.setEnabled(true);
//            }
//
//            mChangeTemperatureSeekbar.setEnabled(true);
//            mFreezeTemperatureSeekbar.setEnabled(true);
//
//            if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_251)) {
//                mTvCold.setText((mColdTemperatureSeekbar.getProgress() + 2) + "℃");
//                mTvFreeze.setText((mFreezeTemperatureSeekbar.getProgress() - 24) + "℃");
//                mTvChange.setText((-18 + mChangeTemperatureSeekbar.getProgress()) + "℃");
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_401)) {
//                mTvCold.setText((mColdTemperatureSeekbar.getProgress() + 1) + "℃");
//                mTvFreeze.setText((mFreezeTemperatureSeekbar.getProgress() - 23) + "℃");
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_595)) {
//                mTvCold.setText((mColdTemperatureSeekbar.getProgress() + 2) + "℃");
//                mTvFreeze.setText((mFreezeTemperatureSeekbar.getProgress() - 24) + "℃");
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_256)) {
//                mTvCold.setText((mColdTemperatureSeekbar.getProgress() + 2) + "℃");
//                //                mTvFreeze.setText((mFreezeTemperatureSeekbar.getProgress() - 26) + "℃");
//                mTvFreeze.setText((mFreezeTemperatureSeekbar.getProgress() - 24) + "℃");
//                if (isTibit) {
//                    mTvChange.setText("珍品中");
//                    mChangeTemperatureSeekbar.setEnabled(false);
//                } else {
//                    mTvChange.setText((-18 + mChangeTemperatureSeekbar.getProgress()) + "℃");
//                    mChangeTemperatureSeekbar.setEnabled(true);
//                }
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_476)) {
//                mTvCold.setText((mColdTemperatureSeekbar.getProgress() + 1) + "℃");
//                mTvFreeze.setText((mFreezeTemperatureSeekbar.getProgress() - 24) + "℃");
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_630)) {
//                mTvCold.setText((mColdTemperatureSeekbar.getProgress() + 1) + "℃");
//                mTvFreeze.setText((mFreezeTemperatureSeekbar.getProgress() - 23) + "℃");
//                mTvChange.setText((-20 + mChangeTemperatureSeekbar.getProgress()) + "℃");
//            }
//        } else if (modeStr.equals(StringUtil.ArraysToString(mode2))) {
//            mTvCold.setText("假日");
//            if (isColdingOn()) {
//                mColdTemperatureSeekbar.setEnabled(false);
//            }
//            mChangeTemperatureSeekbar.setEnabled(true);
//            mFreezeTemperatureSeekbar.setEnabled(true);
//            if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_251)) {
//                mTvFreeze.setText((mFreezeTemperatureSeekbar.getProgress() - 24) + "℃");
//                mTvChange.setText((-18 + mChangeTemperatureSeekbar.getProgress()) + "℃");
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_401)) {
//                mTvFreeze.setText((mFreezeTemperatureSeekbar.getProgress() - 23) + "℃");
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_595)) {
//                mTvFreeze.setText((mFreezeTemperatureSeekbar.getProgress() - 24) + "℃");
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_256)) {
//                //                mTvFreeze.setText((mFreezeTemperatureSeekbar.getProgress() - 26) + "℃");
//                mTvFreeze.setText((mFreezeTemperatureSeekbar.getProgress() - 24) + "℃");
//                if (isTibit) {
//                    mChangeTemperatureSeekbar.setEnabled(false);
//                    mTvChange.setText("珍品中");
//                } else {
//                    mChangeTemperatureSeekbar.setEnabled(true);
//                    mTvChange.setText((-18 + mChangeTemperatureSeekbar.getProgress()) + "℃");
//                }
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_476)) {
//                mTvFreeze.setText((mFreezeTemperatureSeekbar.getProgress() - 24) + "℃");
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_630)) {
//                mTvFreeze.setText((mFreezeTemperatureSeekbar.getProgress() - 23) + "℃");
//                mTvChange.setText((-20 + mChangeTemperatureSeekbar.getProgress()) + "℃");
//            }
//
//
//        } else if (modeStr.equals(StringUtil.ArraysToString(mode3))) {
//            mTvCold.setText("智能");
//            mTvFreeze.setText("智能");
//            if (isColdingOn()) {
//                mColdTemperatureSeekbar.setEnabled(false);
//
//            }
//            mFreezeTemperatureSeekbar.setEnabled(false);
//            mChangeTemperatureSeekbar.setEnabled(true);
//
//            if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_256) && isTibit) {
//                mChangeTemperatureSeekbar.setEnabled(false);
//                mTvChange.setText("珍品");
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_630)) {
//                mTvChange.setText((-20 + mChangeTemperatureSeekbar.getProgress()) + "℃");
//            } else {
//                mTvChange.setText((-18 + mChangeTemperatureSeekbar.getProgress()) + "℃");
//            }
//        } else if (modeStr.equals(StringUtil.ArraysToString(mode4))) {
//            mTvFreeze.setText("速冻");
//            if (isColdingOn()) {
//                mColdTemperatureSeekbar.setEnabled(true);
//            }
//            mFreezeTemperatureSeekbar.setEnabled(false);
//            mChangeTemperatureSeekbar.setEnabled(true);
//
//            if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_251)) {
//                mTvCold.setText((mColdTemperatureSeekbar.getProgress() + 2) + "℃");
//                mTvChange.setText((-18 + mChangeTemperatureSeekbar.getProgress()) + "℃");
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_401)) {
//                mTvCold.setText((mColdTemperatureSeekbar.getProgress() + 1) + "℃");
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_595)) {
//                mTvCold.setText((mColdTemperatureSeekbar.getProgress() + 2) + "℃");
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_256)) {
//                mTvCold.setText((mColdTemperatureSeekbar.getProgress() + 2) + "℃");
//                if (isTibit) {
//                    mChangeTemperatureSeekbar.setEnabled(false);
//                    mTvChange.setText("珍品");
//                } else {
//                    mChangeTemperatureSeekbar.setEnabled(true);
//                    mTvChange.setText((-18 + mChangeTemperatureSeekbar.getProgress()) + "℃");
//                }
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_476)) {
//                mTvCold.setText((mColdTemperatureSeekbar.getProgress() + 1) + "℃");
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_630)) {
//                mTvCold.setText((mColdTemperatureSeekbar.getProgress() + 1) + "℃");
//                mTvChange.setText((-20 + mChangeTemperatureSeekbar.getProgress()) + "℃");
//            }
//        } else if (modeStr.equals(StringUtil.ArraysToString(mode5))) {//速冷
//            /*if (isColdingOn()) {
//                mColdTemperatureSeekbar.setEnabled(true);
//
//            }
//            mFreezeTemperatureSeekbar.setEnabled(false);
//            mChangeTemperatureSeekbar.setEnabled(true);*/
//            if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_251)) {
//                mTvChange.setText("速冷中");
//                mTvCold.setText((mColdTemperatureSeekbar.getProgress() + 2) + "℃");
//                mTvFreeze.setText((mFreezeTemperatureSeekbar.getProgress() - 24) + "℃");
//                if (isColdingOn()) {
//                    mColdTemperatureSeekbar.setEnabled(true);
//                }
//                mFreezeTemperatureSeekbar.setEnabled(true);
//                mChangeTemperatureSeekbar.setEnabled(false);
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_401)) {
//                mTvCold.setText("速冷中");
//                mTvFreeze.setText((mFreezeTemperatureSeekbar.getProgress() - 23) + "℃");
//                if (isColdingOn()) {
//                    mColdTemperatureSeekbar.setEnabled(false);
//                }
//                mFreezeTemperatureSeekbar.setEnabled(true);
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_595)) {
//                mTvCold.setText("速冷中");
//                mTvFreeze.setText((mFreezeTemperatureSeekbar.getProgress() - 24) + "℃");
//                if (isColdingOn()) {
//                    mColdTemperatureSeekbar.setEnabled(false);
//                }
//                mFreezeTemperatureSeekbar.setEnabled(true);
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_256)) {
//                mTvChange.setText("速冷中");
//                mTvCold.setText((mColdTemperatureSeekbar.getProgress() + 2) + "℃");
//                //                mTvFreeze.setText((mFreezeTemperatureSeekbar.getProgress() - 26) + "℃");
//                mTvFreeze.setText((mFreezeTemperatureSeekbar.getProgress() - 24) + "℃");
//                if (isColdingOn()) {
//                    mColdTemperatureSeekbar.setEnabled(true);
//                }
//                mFreezeTemperatureSeekbar.setEnabled(true);
//                mChangeTemperatureSeekbar.setEnabled(false);
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_476)) {
//                mTvCold.setText("速冷中");
//                mTvFreeze.setText((mFreezeTemperatureSeekbar.getProgress() - 24) + "℃");
//                if (isColdingOn()) {
//                    mColdTemperatureSeekbar.setEnabled(false);
//                }
//                mFreezeTemperatureSeekbar.setEnabled(true);
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_630)) {
//                mTvCold.setText("速冷");
//                mTvFreeze.setText((mFreezeTemperatureSeekbar.getProgress() - 23) + "℃");
//                mTvChange.setText((mChangeTemperatureSeekbar.getProgress() - 20) + "℃");
//                mColdTemperatureSeekbar.setEnabled(false);
//                mFreezeTemperatureSeekbar.setEnabled(true);
//                mChangeTemperatureSeekbar.setEnabled(true);
//            }
//        } else if (modeStr.equals(StringUtil.ArraysToString(mode6))) {
//            if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_251)) {
//                mTvChange.setText("速冷中");
//                mTvCold.setText("假日运行中");
//                mTvFreeze.setText("速冻中");
//                if (isColdingOn()) {
//                    mColdTemperatureSeekbar.setEnabled(false);
//
//                }
//                mFreezeTemperatureSeekbar.setEnabled(false);
//                mChangeTemperatureSeekbar.setEnabled(false);
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_256)) {
//                mTvChange.setText("速冷中");
//                mTvCold.setText("假日运行中");
//                mTvFreeze.setText("速冻中");
//                if (isColdingOn()) {
//                    mColdTemperatureSeekbar.setEnabled(false);
//
//                }
//                mFreezeTemperatureSeekbar.setEnabled(false);
//                mChangeTemperatureSeekbar.setEnabled(false);
//            }
//
//        } else if (modeStr.equals(StringUtil.ArraysToString(mode7))) {
//            mTvCold.setText("假日");
//            mTvFreeze.setText("速冻");
//            if (isColdingOn()) {
//                mColdTemperatureSeekbar.setEnabled(false);
//
//            }
//            mFreezeTemperatureSeekbar.setEnabled(false);
//            if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_251)) {
//                mTvChange.setText((-18 + mChangeTemperatureSeekbar.getProgress()) + "℃");
//                mChangeTemperatureSeekbar.setEnabled(true);
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_256)) {
//                if (isTibit) {
//                    mChangeTemperatureSeekbar.setEnabled(false);
//                    mTvChange.setText("珍品中");
//                } else {
//                    mChangeTemperatureSeekbar.setEnabled(true);
//                    mTvChange.setText((-18 + mChangeTemperatureSeekbar.getProgress()) + "℃");
//                }
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_630)) {
//                mTvChange.setText((-20 + mChangeTemperatureSeekbar.getProgress()) + "℃");
//                mChangeTemperatureSeekbar.setEnabled(true);
//            }
//
//        } else if (modeStr.equals(StringUtil.ArraysToString(mode8))) {
//            mTvFreeze.setText("速冻");
//            mFreezeTemperatureSeekbar.setEnabled(false);
//            if (isColdingOn()) {
//                mColdTemperatureSeekbar.setEnabled(false);
//            }
//
//            if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_251)) {
//                mTvCold.setText((mColdTemperatureSeekbar.getProgress() + 2) + "℃");
//                mTvChange.setText("速冷中");
//                mChangeTemperatureSeekbar.setEnabled(false);
//                if (isColdingOn()) {
//                    mColdTemperatureSeekbar.setEnabled(true);
//                }
//
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_401)) {
//                mTvCold.setText("速冷中");
//                if (isColdingOn()) {
//                    mColdTemperatureSeekbar.setEnabled(false);
//                }
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_595)) {
//                mTvCold.setText("速冷中");
//                if (isColdingOn()) {
//                    mColdTemperatureSeekbar.setEnabled(false);
//
//                }
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_256)) {
//                mTvCold.setText((mColdTemperatureSeekbar.getProgress() + 2) + "℃");
//                mTvChange.setText("速冷中");
//                mChangeTemperatureSeekbar.setEnabled(false);
//                if (isColdingOn()) {
//                    mColdTemperatureSeekbar.setEnabled(true);
//                }
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_476)) {
//                mTvCold.setText("速冷中");
//                if (isColdingOn()) {
//                    mColdTemperatureSeekbar.setEnabled(false);
//                }
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_630)) {
//                mTvChange.setText((mChangeTemperatureSeekbar.getProgress() - 20) + "℃");
//                mTvCold.setText("速冷");
//                mChangeTemperatureSeekbar.setEnabled(true);
//                mColdTemperatureSeekbar.setEnabled(false);
//            }
//
//        } else if (modeStr.equals(StringUtil.ArraysToString(mode9))) {
//            if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_251)) {
//                if (isColdingOn()) {
//                    mColdTemperatureSeekbar.setEnabled(false);
//
//                }
//                mFreezeTemperatureSeekbar.setEnabled(true);
//                mChangeTemperatureSeekbar.setEnabled(false);
//                mTvCold.setText("假日运行中");
//                mTvChange.setText("速冷中");
//                mTvFreeze.setText((mFreezeTemperatureSeekbar.getProgress() - 24) + "℃");
//            }
//            if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_256)) {
//                if (isColdingOn()) {
//                    mColdTemperatureSeekbar.setEnabled(false);
//
//                }
//                mFreezeTemperatureSeekbar.setEnabled(true);
//                mChangeTemperatureSeekbar.setEnabled(false);
//                mTvCold.setText("假日运行中");
//                mTvChange.setText("速冷中");
//                //                mTvFreeze.setText((mFreezeTemperatureSeekbar.getProgress() - 26) + "℃");
//                mTvFreeze.setText((mFreezeTemperatureSeekbar.getProgress() - 24) + "℃");
//            }
//        } else if (modeStr.equals(StringUtil.ArraysToString(mode10))) {
//            if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_251)) {
//                if (isColdingOn()) {
//                    mColdTemperatureSeekbar.setEnabled(false);
//
//                }
//                mFreezeTemperatureSeekbar.setEnabled(false);
//                mChangeTemperatureSeekbar.setEnabled(false);
//                mTvCold.setText("智能运行中");
//                mTvChange.setText("速冷中");
//                mTvFreeze.setText("智能运行中");
//            }
//            if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_256)) {
//                if (isColdingOn()) {
//                    mColdTemperatureSeekbar.setEnabled(false);
//
//                }
//                mFreezeTemperatureSeekbar.setEnabled(false);
//                mChangeTemperatureSeekbar.setEnabled(false);
//                mTvCold.setText("智能运行中");
//                mTvChange.setText("速冷中");
//                mTvFreeze.setText("智能运行中");
//            }
//        }
//    }
//
//
//    long oldtime1, oldtime2, oldtime3, oldtime4, oldtime5;
//
//    private int m_coldClickCount = 5;
//    private long startColdClickTime;
//
//    @Override
//    public void onClick(View v) {
//        if (!isModebusConnected) {
//            return;
//        }
//        switch (v.getId()) {
//            case R.id.button15://智能
//                if (System.currentTimeMillis() - oldtime1 > 100) {
//                    if (isSmart) {
//                        msgService.PowerSendCmd(PowerCtrlCmd.cmdSmartOff);
//                        mFridgeStatusStatus.update(FridgeStatusTableName.smart, false);
//                        sendAllLevelCmd();
//                    } else {
//                        if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_401)) {//401速冻智能互斥自己做
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdQuickFreezeOff);
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdQuickCoolOff);
//                            sendAllLevelCmd();
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdSmartOn);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.quick_freeze, false);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.quick_cooling, false);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.smart, true);
//                        } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_251)) {
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdQuickFreezeOff);
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdQuickCoolOff);
//                            sendAllLevelCmd();
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdSmartOn);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.smart, true);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.quick_freeze, false);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.holiday, false);
//                            if (!(boolean) SpUtils.getInstance(FridgeApplication.get()).get(ConstantUtil.ISCOLDOPENON, true)) {
//                                SpUtils.getInstance(FridgeApplication.get()).put(ConstantUtil.ISCOLDOPENON, true);
//                                updateTemperUI();
//                            }
//                        } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_256)) {
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdQuickFreezeOff);
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdQuickCoolOff);
//                            sendAllLevelCmd();
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdSmartOn);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.smart, true);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.quick_freeze, false);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.holiday, false);
//                            if (!(boolean) SpUtils.getInstance(FridgeApplication.get()).get(ConstantUtil.ISCOLDOPENON, true)) {
//                                SpUtils.getInstance(FridgeApplication.get()).put(ConstantUtil.ISCOLDOPENON, true);
//                                updateTemperUI();
//                            }
//                        } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_630)) {
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdQuickFreezeOff);
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdQuickCoolOff);
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdHolidayOff);
//                            sendAllLevelCmd();
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdSmartOn);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.smart, true);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.quick_freeze, false);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.holiday, false);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.quick_cooling, false);
//                            if (!(boolean) SpUtils.getInstance(FridgeApplication.get()).get(ConstantUtil.ISCOLDOPENON, true)) {
//                                SpUtils.getInstance(FridgeApplication.get()).put(ConstantUtil.ISCOLDOPENON, true);
//                                updateTemperUI();
//                            }
//                        }
//                    }
//                    query();
//
//                }
//                oldtime1 = System.currentTimeMillis();
//
//                break;
//            case R.id.button16://假日 ///净化
//                if (System.currentTimeMillis() - oldtime2 > 100) {
//
//                    if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_401)) {
//                        if (isCleanUp) {
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdPowerOff);
//                            sendAllLevelCmd();
//                            mFridgeStatusStatus.update(FridgeStatusTableName.cleanup, false);
//                        } else {
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdPowerOn);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.cleanup, true);
//                        }
//                    }
//                    if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_251)) {
//                        if (isHoldiday) {
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdHolidayOff);
//                            sendAllLevelCmd();
//                            mFridgeStatusStatus.update(FridgeStatusTableName.holiday, false);
//
//                        } else {
//                            //                            if (isColdingOn()) {
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdSmartOff);
//                            sendAllLevelCmd();
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdHolidayOn);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.holiday, true);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.smart, false);
//                            if (!(boolean) SpUtils.getInstance(FridgeApplication.get()).get(ConstantUtil.ISCOLDOPENON, true)) {
//                                SpUtils.getInstance(FridgeApplication.get()).put(ConstantUtil.ISCOLDOPENON, true);
//                                updateTemperUI();
//                                int coldLevel = mFridgeStatusStatus.selectFridgeStatus().getCold_level();
//                                msgService.PowerSendCmd(PowerCtrlCmd.cmdGearSetFreeze252Model, (byte) (0x02 + (coldLevel - 2)));
//                            }
//                            //                            } else {
//                            //                                ToastUtil.showToastLong("冷藏室已关闭，如要开启假日请先开启冷藏室");
//                            //                            }
//                        }
//                    }
//                    if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_256)) {
//                        if (isHoldiday) {
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdHolidayOff);
//                            sendAllLevelCmd();
//                            mFridgeStatusStatus.update(FridgeStatusTableName.holiday, false);
//                        } else {
//                            //                            if (isColdingOn()) {
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdSmartOff);
//                            sendAllLevelCmd();
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdHolidayOn);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.holiday, true);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.smart, false);
//                            if (!(boolean) SpUtils.getInstance(FridgeApplication.get()).get(ConstantUtil.ISCOLDOPENON, true)) {
//                                SpUtils.getInstance(FridgeApplication.get()).put(ConstantUtil.ISCOLDOPENON, true);
//                                updateTemperUI();
//                                int coldLevel = mFridgeStatusStatus.selectFridgeStatus().getCold_level();
//                                msgService.PowerSendCmd(PowerCtrlCmd.cmdGearSetFreeze252Model, (byte) (0x02 + (coldLevel - 2)));
//                            }
//                            //                            } else {
//                            //                                ToastUtil.showToastLong("冷藏室已关闭，如要开启假日请先开启冷藏室");
//                            //                            }
//                        }
//                    }
//                    if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_630)) {
//                        if (isHoldiday) {
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdHolidayOff);
//                            sendAllLevelCmd();
//                            mFridgeStatusStatus.update(FridgeStatusTableName.holiday, false);
//
//                        } else {
//                            //                            if (isColdingOn()) {
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdSmartOff);
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdQuickCoolOff);
//                            sendAllLevelCmd();
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdHolidayOn);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.holiday, true);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.smart, false);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.quick_cooling, false);
//                            updateTemperUI();
//                            int coldLevel = mFridgeStatusStatus.selectFridgeStatus().getCold_level();
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdSetColdBar630, (byte) (0x01 + (coldLevel - 1)));
//                        }
//                    }
//                    query();
//
//                }
//
//                oldtime2 = System.currentTimeMillis();
//
//                break;
//            case R.id.button17://速冻
//                if (System.currentTimeMillis() - oldtime3 > 100) {
//
//                    if (isQuickFreeze) {
//                        msgService.PowerSendCmd(PowerCtrlCmd.cmdQuickFreezeOff);
//                        sendAllLevelCmd();
//                        mFridgeStatusStatus.update(FridgeStatusTableName.quick_freeze, false);
//                    } else {
//                        SpUtils.getInstance(FridgeApplication.get()).put(ConstantUtil.FREEZETIME, System.currentTimeMillis() / 1l);
//
//                        if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_401)) {//401速冻智能互斥自己做
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdSmartOff);//速冻不会关掉智能
//                            sendAllLevelCmd();
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdQuickFreezeOn);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.quick_freeze, true);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.smart, false);
//                        } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_251)) {
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdSmartOff);
//                            sendAllLevelCmd();
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdQuickFreezeOn);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.quick_freeze, true);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.smart, false);
//                        } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_256)) {
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdSmartOff);
//                            sendAllLevelCmd();
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdQuickFreezeOn);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.quick_freeze, true);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.smart, false);
//                        } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_630)) {
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdSmartOff);
//                            sendAllLevelCmd();
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdQuickFreezeOn);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.quick_freeze, true);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.smart, false);
//                        }
//                    }
//                    query();
//
//                }
//                oldtime3 = System.currentTimeMillis();
//
//                break;
//            case R.id.button18://速冷
//                if (System.currentTimeMillis() - oldtime4 > 100) {
//                    if (isTibit) {
//                        shakeButton("珍品");
//                        ToastUtil.showToastLong("珍品模式已开启，如需选择速冷模式请先退出珍品模式");
//                        return;
//                    }
//                    if (isQuickCold) {
//                        if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_256)) {
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdVerTempQuickCoolOff256Model);
//                            sendAllLevelCmd();
//                            mFridgeStatusStatus.update(FridgeStatusTableName.quick_cooling, false);
//                        } else {
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdQuickCoolOff);
//                            sendAllLevelCmd();
//                            mFridgeStatusStatus.update(FridgeStatusTableName.quick_cooling, false);
//                        }
//
//                    } else {
//                        SpUtils.getInstance(FridgeApplication.get()).put(ConstantUtil.COLDTIME, System.currentTimeMillis() / 1l);
//                        if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_401)) {//401速冻智能互斥自己做
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdSmartOff);//速冷不会关掉智能
//                            sendAllLevelCmd();
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdQuickCoolOn);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.quick_cooling, true);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.smart, false);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.holiday, false);
//
//                        } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_251)) {
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdQuickCoolOn);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.quick_cooling, true);
//                        } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_256)) {
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdVerTempQuickCoolOn256Model);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.quick_cooling, true);
//                        } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_630)) {
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdSmartOff);//速冷不会关掉智能
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdHolidayOff);
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdQuickCoolOn);
//                            sendAllLevelCmd();
//                            mFridgeStatusStatus.update(FridgeStatusTableName.quick_cooling, true);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.smart, false);
//                            mFridgeStatusStatus.update(FridgeStatusTableName.holiday, false);
//                        }
//                    }
//                    query();
//
//                }
//                oldtime4 = System.currentTimeMillis();
//
//                break;
//
//            case R.id.button3://珍品
//                if (System.currentTimeMillis() - oldtime5 > 100) {
//                    if (isTibit) {
//                        msgService.PowerSendCmd(PowerCtrlCmd.cmdTitbitOff256Model);
//                        sendAllLevelCmd();
//                        mFridgeStatusStatus.update(FridgeStatusTableName.titbit, false);
//                    } else {
//                        if (isQuickCold) {
//                            msgService.PowerSendCmd(PowerCtrlCmd.cmdQuickCoolOff);
//                            sendAllLevelCmd();
//                            mFridgeStatusStatus.update(FridgeStatusTableName.quick_cooling, false);
//                        }
//                        msgService.PowerSendCmd(PowerCtrlCmd.cmdTitbitOn256Model);
//                        mFridgeStatusStatus.update(FridgeStatusTableName.titbit, true);
//                    }
//                    query();
//
//                }
//                oldtime5 = System.currentTimeMillis();
//                break;
//
//            case R.id.left_view:
//                if (clickForTestNum == 5) {
//                    startTestClickTime = System.currentTimeMillis();
//                    clickForTestNum--;
//                } else if (clickForTestNum < 5 && (clickForTestNum % 2 == 1)) {
//                    clickForTestNum--;
//
//                }
//                long endTestClickTime = System.currentTimeMillis();
//                if (clickForTestNum == 0 && (endTestClickTime - startTestClickTime <= 1000 * 5)) {
//                    clickForTestNum = 5;
//                    startActivity(new Intent(SettingDialogActivity.this, TftActivity.class));
//                } else if (endTestClickTime - startTestClickTime > 1000 * 5) {
//                    clickForTestNum = 5;
//                }
//                MyLogUtil.d(TAG, "clickForTestNumR:::" + clickForTestNum);
//
//                break;
//            case R.id.right_view:
//                if (clickForTestNum < 5 && (clickForTestNum % 2 == 0)) {
//                    clickForTestNum--;
//                }
//                MyLogUtil.d(TAG, "clickForTestNumL:::" + clickForTestNum);
//
//                break;
//            case R.id.btn_smart_476:
//                if (System.currentTimeMillis() - oldtime1 > 100) {
//                    if (isSmart) {
//                        msgService.PowerSendCmd(PowerCtrlCmd.cmdSmartOff);
//                        mFridgeStatusStatus.update(FridgeStatusTableName.smart, false);
//                        sendAllLevelCmd();
//                    } else {
//                        msgService.PowerSendCmd(PowerCtrlCmd.cmdQuickFreezeOff);
//                        msgService.PowerSendCmd(PowerCtrlCmd.cmdQuickCoolOff);
//                        msgService.PowerSendCmd(PowerCtrlCmd.cmdHolidayOff);
//                        sendAllLevelCmd();
//                        msgService.PowerSendCmd(PowerCtrlCmd.cmdSmartOn);
//                        mFridgeStatusStatus.update(FridgeStatusTableName.quick_freeze, false);
//                        mFridgeStatusStatus.update(FridgeStatusTableName.quick_cooling, false);
//                        mFridgeStatusStatus.update(FridgeStatusTableName.holiday, false);
//                        mFridgeStatusStatus.update(FridgeStatusTableName.smart, true);
//                    }
//                    query();
//                }
//                oldtime1 = System.currentTimeMillis();
//
//                break;
//            case R.id.btn_holiday_476:
//                if (System.currentTimeMillis() - oldtime2 > 100) {
//                    if (isHoldiday) {
//                        msgService.PowerSendCmd(PowerCtrlCmd.cmdHolidayOff);
//                        sendAllLevelCmd();
//                        mFridgeStatusStatus.update(FridgeStatusTableName.holiday, false);
//                    } else {
//                        msgService.PowerSendCmd(PowerCtrlCmd.cmdSmartOff);
//                        msgService.PowerSendCmd(PowerCtrlCmd.cmdQuickCoolOff);
//                        sendAllLevelCmd();
//                        msgService.PowerSendCmd(PowerCtrlCmd.cmdHolidayOn);
//                        mFridgeStatusStatus.update(FridgeStatusTableName.holiday, true);
//                        mFridgeStatusStatus.update(FridgeStatusTableName.smart, false);
//                        mFridgeStatusStatus.update(FridgeStatusTableName.quick_cooling, false);
//                    }
//                    query();
//
//                }
//                oldtime2 = System.currentTimeMillis();
//                break;
//            case R.id.btn_freeze_476:
//                if (System.currentTimeMillis() - oldtime3 > 100) {
//                    if (isQuickFreeze) {
//                        msgService.PowerSendCmd(PowerCtrlCmd.cmdQuickFreezeOff);
//                        sendAllLevelCmd();
//                        mFridgeStatusStatus.update(FridgeStatusTableName.quick_freeze, false);
//                    } else {
//                        SpUtils.getInstance(FridgeApplication.get()).put(ConstantUtil.FREEZETIME, System.currentTimeMillis() / 1l);
//                        msgService.PowerSendCmd(PowerCtrlCmd.cmdSmartOff);//速冻不会关掉智能
//                        sendAllLevelCmd();
//                        msgService.PowerSendCmd(PowerCtrlCmd.cmdQuickFreezeOn);
//                        mFridgeStatusStatus.update(FridgeStatusTableName.quick_freeze, true);
//                        mFridgeStatusStatus.update(FridgeStatusTableName.smart, false);
//                    }
//                    query();
//                }
//                oldtime3 = System.currentTimeMillis();
//                break;
//            case R.id.btn_cold_476:
//                if (System.currentTimeMillis() - oldtime4 > 100) {
//                    if (isQuickCold) {
//                        msgService.PowerSendCmd(PowerCtrlCmd.cmdQuickCoolOff);
//                        sendAllLevelCmd();
//                        mFridgeStatusStatus.update(FridgeStatusTableName.quick_cooling, false);
//                    } else {
//                        SpUtils.getInstance(FridgeApplication.get()).put(ConstantUtil.COLDTIME, System.currentTimeMillis() / 1l);
//                        msgService.PowerSendCmd(PowerCtrlCmd.cmdSmartOff);//速冷不会关掉智能
//                        msgService.PowerSendCmd(PowerCtrlCmd.cmdHolidayOff);//速冷不会关掉智能
//                        sendAllLevelCmd();
//                        msgService.PowerSendCmd(PowerCtrlCmd.cmdQuickCoolOn);
//                        mFridgeStatusStatus.update(FridgeStatusTableName.quick_cooling, true);
//                        mFridgeStatusStatus.update(FridgeStatusTableName.smart, false);
//                        mFridgeStatusStatus.update(FridgeStatusTableName.holiday, false);
//                        query();
//                    }
//                }
//                oldtime4 = System.currentTimeMillis();
//                break;
//            case R.id.btn_shajun_smart:
//                if (isBtnPressed(btn_shajun_smart)) {
//                    unPress(btn_shajun_smart);
//                    rl_uv_layout.setVisibility(View.INVISIBLE);
//                    setUvMode(0);
//                    pressRbforUI(btn_cold_476);//clean btn3-9
//                    stopLoopTimer();
//                    AnimUtil.startAnim(iv_guangbiao, false);
//                } else {
//                    pressRbforUI(btn_cold_476);//clean btn3-9
//
//                    pressUvBtn(btn_shajun_smart);
//                    startLoopTimer(6);
//                    AnimUtil.startAnim(iv_guangbiao, true);
//                }
//                query();
//                break;
//
//            case R.id.btn_shajun_strong:
//                if (isBtnPressed(btn_shajun_strong)) {
//                    unPress(btn_shajun_strong);
//                    rl_uv_layout.setVisibility(View.INVISIBLE);
//                    setUvMode(0);
//                    pressRbforUI(btn_cold_476);//clean btn3-9
//                    stopLoopTimer();
//                    AnimUtil.startAnim(iv_guangbiao, false);
//
//                } else {
//                    pressRbforUI(btn_cold_476);//clean btn3-9
//
//                    pressUvBtn(btn_shajun_strong);
//                    startLoopTimer(4);
//                    AnimUtil.startAnim(iv_guangbiao, true);
//
//                }
//                query();
//
//                break;
//
//            case R.id.btn_shajun_byself:
//                if (isBtnPressed(btn_shajun_byself)) {
//                    unPress(btn_shajun_byself);
//                    rl_uv_layout.setVisibility(View.INVISIBLE);
//                    setUvMode(0);
//                    pressRbforUI(btn_cold_476);//clean btn3-9
//                    stopLoopTimer();
//                    AnimUtil.startAnim(iv_guangbiao, false);
//
//                } else {
//                    pressRbforUI(btn_cold_476);//clean btn3-9
//                    pressUvBtn(btn_shajun_byself);
//                    stopLoopTimer();
//                    setUvMode(0);
//                    AnimUtil.startAnim(iv_guangbiao, false);
//
//                }
//                query();
//
//                break;
//
//            case R.id.rb_3_hour:
//                pressByselfBtn(rb_3_hour);
//                query();
//
//                break;
//            case R.id.rb_4_hour:
//                pressByselfBtn(rb_4_hour);
//                query();
//
//                break;
//            case R.id.rb_5_hour:
//                pressByselfBtn(rb_5_hour);
//                query();
//
//                break;
//            case R.id.rb_6_hour:
//                pressByselfBtn(rb_6_hour);
//                query();
//
//                break;
//            case R.id.rb_7_hour:
//                pressByselfBtn(rb_7_hour);
//                query();
//
//                break;
//            case R.id.rb_8_hour:
//                pressByselfBtn(rb_8_hour);
//                query();
//
//                break;
//            case R.id.rb_9_hour:
//                pressByselfBtn(rb_9_hour);
//                query();
//                break;
//            case R.id.text_view_300: {
//                if (clickForTestNum == 5) {
//                    startTestClickTime = System.currentTimeMillis();
//                    clickForTestNum--;
//                } else if (clickForTestNum < 5) {
//                    clickForTestNum--;
//                }
//                long endTestClickTime630 = System.currentTimeMillis();
//                if (clickForTestNum == 0 && (endTestClickTime630 - startTestClickTime <= 1000 * 5)) {
//                    clickForTestNum = 5;
//                    startActivity(new Intent(SettingDialogActivity.this, TftActivity.class));
//                } else if (endTestClickTime630 - startTestClickTime > 1000 * 5) {
//                    clickForTestNum = 5;
//                }
//                break;
//            }
//            case R.id.text_view_301:
//                if (m_coldClickCount == 5) {
//                    startColdClickTime = System.currentTimeMillis();
//                    m_coldClickCount--;
//                } else if (m_coldClickCount < 5) {
//                    m_coldClickCount--;
//                }
//                long endTestClickTime630 = System.currentTimeMillis();
//                if (m_coldClickCount == 0 && (endTestClickTime630 - startColdClickTime <= 1000 * 5)) {
//                    m_coldClickCount = 5;
//                    ToastMng.getInstance().showToast("进入自检模式");
//                    msgService.stopPowerCtrlBrd();
//                    Intent intent = new Intent();
//                    intent.setComponent(new ComponentName("com.byd.testhaier", "com.byd.testhaier.TestMainActivity"));
//                    startActivityForResult(intent, REQUEST_CODE_FACTORYTEST);
//
//                    // 关闭服务
//                    //                    if (SystemUtils.isServiceExisted(this, HaierControlService.class.getName())) {
//                    //                        UMLauncherApplication.getInstance().unBindCommService();
//                    //                        UMLauncherApplication.getInstance().stopCommService();
//                    //                    }
////                    Intent intent = new Intent();
////                    intent.setAction("android.provider.Telephony.SECRET_CODE");
////                    intent.setData(Uri.parse("android_secret_code://66"));
////                    sendBroadcast(intent);
////                    Intent intent = new Intent();
////                    intent.setAction("android.provider.Telephony.SECRET_CODE");
////                    intent.setData(Uri.parse("android_secret_code://66"));
////                    sendBroadcast(intent);
//                } else if (endTestClickTime630 - startColdClickTime > 1000 * 5) {
//                    m_coldClickCount = 5;
//                }
//
//                break;
//            //打开wifi界面
//            case R.id.wifi_layout:
//                setupWifi();
//                break;
//            //打开系统版本界面
//            case R.id.update_layout:
//                setupUpdateSystem();
//                break;
//            default:
//                break;
//        }
//
//    }
//
//    public void startLoopTimer(int hour) {
//        if (isTimerServiceBind) {
//            UvLoopTimerUtil.startLoopTimer(this, hour, ConstantUtil.BROADCAST_ACTION_START_TIMER_LOOP);
//        }
//    }
//
//    public void startLoopTimerOnce(int hour) {
//        if (isTimerServiceBind) {
//            UvLoopTimerUtil.startLoopTimer(this, hour, ConstantUtil.BROADCAST_ACTION_START_TIMER_LOOP_ONCE);
//
//        }
//    }
//
//    public void stopLoopTimer() {
//        if (isTimerServiceBind) {
//            //            timerService.stopLoopTimer(this);
//            UvLoopTimerUtil.stopLoopTimer(this);
//        }
//    }
//
//
//    public void timerEndShow() {
//        rl_timer_476.setVisibility(View.INVISIBLE);
//    }
//
//    public void pressUvBtn(Button btn) {
//        rl_timer_476.setVisibility(View.VISIBLE);
//        if (btn.getId() == R.id.btn_shajun_smart) {
//            press(btn_shajun_smart);
//            unPress(btn_shajun_byself);
//            unPress(btn_shajun_strong);
//            rl_timer_smart.setVisibility(View.VISIBLE);
//            rl_timer_stronger.setVisibility(View.GONE);
//            rl_timer_byself.setVisibility(View.GONE);
//            rl_uv_layout.setVisibility(View.VISIBLE);
//            setUvMode(1);
//        } else if (btn.getId() == R.id.btn_shajun_strong) {
//            unPress(btn_shajun_smart);
//            unPress(btn_shajun_byself);
//            press(btn_shajun_strong);
//            rl_timer_smart.setVisibility(View.GONE);
//            rl_timer_stronger.setVisibility(View.VISIBLE);
//            rl_timer_byself.setVisibility(View.GONE);
//            rl_uv_layout.setVisibility(View.VISIBLE);
//            setUvMode(2);
//        } else if (btn.getId() == R.id.btn_shajun_byself) {
//            unPress(btn_shajun_smart);
//            press(btn_shajun_byself);
//            unPress(btn_shajun_strong);
//            rl_timer_smart.setVisibility(View.GONE);
//            rl_timer_stronger.setVisibility(View.GONE);
//            rl_timer_byself.setVisibility(View.VISIBLE);
//            rl_uv_layout.setVisibility(View.VISIBLE);
//        }
//    }
//
//    public void pressByselfBtn(Button btn) {
//        if (isBtnPressed(btn)) {
//            unPress(btn);
//            rl_uv_layout.setVisibility(View.INVISIBLE);
//            unPress(btn_shajun_byself);
//            setUvMode(0);
//            stopLoopTimer();
//            AnimUtil.startAnim(iv_guangbiao, false);
//
//        } else {
//            pressRb(btn);
//            rl_uv_layout.setVisibility(View.VISIBLE);
//            rl_timer_476.setVisibility(View.VISIBLE);
//            setUvMode(getBtnCode(btn.getId()));
//            startLoopTimer(getBtnCode(btn.getId()));
//            AnimUtil.startAnim(iv_guangbiao, true);
//
//        }
//    }
//
//
//    public void setUvMode(int mode) {//0-9  0关闭
//        mFridgeStatusStatus.update(FridgeStatusTableName.sterilization_mode, mode);
//        if (isModebusConnected) {
//            msgService.PowerSendCmd(mode > 0 ? PowerCtrlCmd.cmdSterilizationOn476Model : PowerCtrlCmd.cmdSterilizationOff476Model);
//        }
//    }
//
//
//    private boolean isBtnPressed(Button btn) {
//        if (btn.getTextColors().getDefaultColor() == -1) {
//            return true;
//        }
//        return false;
//    }
//
//
//    public boolean isColdingOn() {
//        return tbColdingOpen.isChecked();
//    }
//
//
//    public int getBtnCode(int id) {
//        if (id == R.id.rb_3_hour) {
//            return 3;
//        } else if (id == R.id.rb_4_hour) {
//            return 4;
//        } else if (id == R.id.rb_5_hour) {
//            return 5;
//        } else if (id == R.id.rb_6_hour) {
//            return 6;
//        } else if (id == R.id.rb_7_hour) {
//            return 7;
//        } else if (id == R.id.rb_8_hour) {
//            return 8;
//        } else if (id == R.id.rb_9_hour) {
//            return 9;
//        }
//        return 3;
//    }
//
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        if (isModebusConnected) {
//            unbindService(conn);
//            isModebusConnected = false;
//        }
//        if (isTimerServiceBind) {
//            unbindService(timerConn);
//            isTimerServiceBind = false;
//        }
//
//        AnimUtil.startAnim(iv_guangbiao, false);
//
//    }
//
//
//    private BroadcastReceiver receiveUpdateRemperUI = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            if (intent.getAction().endsWith(ConstantUtil.BROADCAST_ACTION_UPDATE_TEMPER_UI)) {
//                if (intent.getStringExtra("which").equals("temper")) {
//                    updateTemperUI();
//                } else if (intent.getStringExtra("which").equals("uv")) {
//                    updateUvUI();
//                }
//            }
//        }
//    };
//
//    public void updateUvUI() {
//        mFridgeStatus = mFridgeStatusStatus.selectFridgeStatus();
//        int uvCode = mFridgeStatus.getSterilization_mode();
//        switch (uvCode) {
//            case 0:
//                unPress(btn_shajun_smart);
//                unPress(btn_shajun_strong);
//                unPress(btn_shajun_byself);
//                rl_uv_layout.setVisibility(View.GONE);
//                if (isTimerServiceBind) {
//                    stopLoopTimer();
//                    AnimUtil.startAnim(iv_guangbiao, false);
//                }
//                break;
//            case 1:
//                pressUvBtn(btn_shajun_smart);
//                startLoopTimerOnce(6);
//                AnimUtil.startAnim(iv_guangbiao, true);
//                break;
//            case 2:
//                pressUvBtn(btn_shajun_strong);
//                startLoopTimerOnce(4);
//                AnimUtil.startAnim(iv_guangbiao, true);
//                break;
//            case 3:
//                pressUvBtn(btn_shajun_byself);
//                pressRbforUI(rb_3_hour);
//                rl_uv_layout.setVisibility(View.VISIBLE);
//                startLoopTimerOnce(3);
//                AnimUtil.startAnim(iv_guangbiao, true);
//                break;
//            case 4:
//                pressUvBtn(btn_shajun_byself);
//                pressRbforUI(rb_4_hour);
//                rl_uv_layout.setVisibility(View.VISIBLE);
//                startLoopTimerOnce(4);
//                AnimUtil.startAnim(iv_guangbiao, true);
//                break;
//            case 5:
//                pressUvBtn(btn_shajun_byself);
//                pressRbforUI(rb_5_hour);
//                rl_uv_layout.setVisibility(View.VISIBLE);
//                startLoopTimerOnce(5);
//                AnimUtil.startAnim(iv_guangbiao, true);
//                break;
//            case 6:
//                pressUvBtn(btn_shajun_byself);
//                pressRbforUI(rb_6_hour);
//                rl_uv_layout.setVisibility(View.VISIBLE);
//                startLoopTimerOnce(6);
//                AnimUtil.startAnim(iv_guangbiao, true);
//                break;
//            case 7:
//                pressUvBtn(btn_shajun_byself);
//                pressRbforUI(rb_7_hour);
//                rl_uv_layout.setVisibility(View.VISIBLE);
//                startLoopTimerOnce(7);
//                AnimUtil.startAnim(iv_guangbiao, true);
//                break;
//            case 8:
//                pressUvBtn(btn_shajun_byself);
//                pressRbforUI(rb_8_hour);
//                rl_uv_layout.setVisibility(View.VISIBLE);
//                startLoopTimerOnce(8);
//                AnimUtil.startAnim(iv_guangbiao, true);
//                break;
//            case 9:
//                pressUvBtn(btn_shajun_byself);
//                pressRbforUI(rb_9_hour);
//                rl_uv_layout.setVisibility(View.VISIBLE);
//                startLoopTimerOnce(9);
//                AnimUtil.startAnim(iv_guangbiao, true);
//                break;
//            default:
//                break;
//        }
//
//    }
//
//
//    public void updateTemperUI() {
//        if (mFridgeStatus != null) {
//            mFridgeStatus = mFridgeStatusStatus.selectFridgeStatus();
//            int coldLevel = mFridgeStatus.getCold_level();
//            int freezeLevel = mFridgeStatus.getFreeze_level();
//            int middleLevel = mFridgeStatus.getMiddle_level();
//
//            if (coldLevel == 0 && freezeLevel == 0 && middleLevel == 0) {
//                coldLevel = 5;
//                freezeLevel = -18;
//                middleLevel = 4;
//            }
//            mTvCold.setText(coldLevel + "℃");
//            mTvChange.setText(middleLevel + "℃");
//            mTvFreeze.setText(freezeLevel + "℃");
//
//            if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_251)) {
//                mColdTemperatureSeekbar.setProgress(coldLevel - 2);
//                mFreezeTemperatureSeekbar.setProgress(freezeLevel + 24);
//                mChangeTemperatureSeekbar.setProgress(middleLevel + 18);
//
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_401)) {
//                mColdTemperatureSeekbar.setProgress(coldLevel - 1);
//                mFreezeTemperatureSeekbar.setProgress(freezeLevel + 23);
//
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_256)) {
//                mColdTemperatureSeekbar.setProgress(coldLevel - 2);
//                //                mFreezeTemperatureSeekbar.setProgress(freezeLevel + 26);
//                mFreezeTemperatureSeekbar.setProgress(freezeLevel + 24);
//                mChangeTemperatureSeekbar.setProgress(middleLevel + 18);
//
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_476)) {
//                mColdTemperatureSeekbar.setProgress(coldLevel - 1);
//                mFreezeTemperatureSeekbar.setProgress(freezeLevel + 24);
//
//            } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_630)) {
//                mColdTemperatureSeekbar.setProgress(coldLevel - 1);
//                mFreezeTemperatureSeekbar.setProgress(freezeLevel + 23);
//                mChangeTemperatureSeekbar.setProgress(middleLevel + 20);
//            }
//
//            boolean isColdOpen = (boolean) SpUtils.getInstance(FridgeApplication.get()).get(ConstantUtil.ISCOLDOPENON, true);
//            if (isColdOpen) {
//                tbColdingOpen.setChecked(true);
//                openCold();
//                mTvCold.setTextColor(getResources().getColor(R.color.mine_others_background));
//                tvColdingText.setTextColor(getResources().getColor(R.color.mine_others_background));
//            } else {
//                tbColdingOpen.setChecked(false);
//                closeCold();
//                mTvCold.setTextColor(getResources().getColor(R.color.black2));
//                tvColdingText.setTextColor(getResources().getColor(R.color.black2));
//            }
//        }
//    }
//
//
//    public void sendAllLevelCmd() {
//        mFridgeStatus = mFridgeStatusStatus.selectFridgeStatus();
//        int coldLevel = mFridgeStatus.getCold_level();//关闭智能  重发档位
//        int freezeLevel = mFridgeStatus.getFreeze_level();
//        int midLevel = mFridgeStatus.getMiddle_level();
//        if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_251)) {
//            if ((boolean) SpUtils.getInstance(FridgeApplication.get()).get(ConstantUtil.ISCOLDOPENON, true)) {
//                msgService.PowerSendCmd(PowerCtrlCmd.cmdGearSetFreeze252Model, (byte) (0x02 + (coldLevel - 2)));
//            } else {
//                msgService.PowerSendCmd(PowerCtrlCmd.cmdGearSetFreeze252Model, (byte) (0x00));
//            }
//            msgService.PowerSendCmd(PowerCtrlCmd.cmdGearSetRefrig252Model, (byte) (0xE8 + (freezeLevel + 24)));
//            msgService.PowerSendCmd(PowerCtrlCmd.cmdGearSetVarTemp252Model, (byte) (0xEE + (midLevel + 18)));
//
//        } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_401)) {
//            msgService.PowerSendCmd(PowerCtrlCmd.cmdGearSetFreeze401Model, (byte) (0x01 + (coldLevel - 1)));
//            msgService.PowerSendCmd(PowerCtrlCmd.cmdGearSetRefrig401Model, (byte) (0xE9 + (freezeLevel + 23)));
//        } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_256)) {
//            if ((boolean) SpUtils.getInstance(FridgeApplication.get()).get(ConstantUtil.ISCOLDOPENON, true)) {
//                msgService.PowerSendCmd(PowerCtrlCmd.cmdGearSetFreeze256Model, (byte) (0x02 + (coldLevel - 2)));
//            } else {
//                msgService.PowerSendCmd(PowerCtrlCmd.cmdGearSetFreeze256Model, (byte) (0x00));
//            }
//            msgService.PowerSendCmd(PowerCtrlCmd.cmdGearSetRefrig256Model, (byte) (0xE8 + (freezeLevel + 24)));
//            msgService.PowerSendCmd(PowerCtrlCmd.cmdGearSetVarTemp256Model, (byte) (0xEE + (midLevel + 18)));
//        } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_476)) {
//            msgService.PowerSendCmd(PowerCtrlCmd.cmdGearSetFreeze476Model, (byte) (0x01 + (coldLevel - 1)));
//            msgService.PowerSendCmd(PowerCtrlCmd.cmdGearSetRefrig476Model, (byte) (0xE8 + (freezeLevel + 24)));
//        } else if (CURRENTFRIDGEMODE.equals(FRIDGEMODE_630)) {
//            msgService.PowerSendCmd(PowerCtrlCmd.cmdSetColdBar630, (byte) (coldLevel));
//            msgService.PowerSendCmd(PowerCtrlCmd.cmdSetFreezeBar630, (byte) (freezeLevel));
//            msgService.PowerSendCmd(PowerCtrlCmd.cmdSetChangeBar630, (byte) (midLevel));
//        }
//
//    }
//
//    private void query() {
//        Intent intent = new Intent(this, modbusService.class);
//        intent.setAction(RemoteControllerReceiver.KEY_QUERY);
//        startService(intent);
//    }
//
//
//    /**
//     * wifi 按钮
//     *
//     * @author Jeffery Leng
//     * @date 2016/10/19
//     * @email JefferyLeng@guider.cc
//     */
//    public void setupWifi() {
//        WifiSettingDialog_630 wifiDialog = new WifiSettingDialog_630(this);
//        wifiDialog.show();
//
//    }
//
//    /**
//     * 系统更新
//     */
//    public void setupUpdateSystem() {
//        SystemUpdateDialog_630 updateDialog = new SystemUpdateDialog_630(this);
//        updateDialog.show();
//
//    }
//
//    private static final int REQUEST_CODE_FACTORYTEST = 768;
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case REQUEST_CODE_FACTORYTEST:
//                if (ConstantUtil.MODBUS_SERVICE_STARTUP) {
//                    msgService.restartPowerCtrlBrd();
//                }
//                break;
//            default:
//                break;
//        }
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        // TODO: add setContentView(...) invocation
//        ButterKnife.bind(this);
//    }
//
//    @OnClick(R.id.settings_back_img)
//    public void onClick() {
//        finish();
//    }
//}
