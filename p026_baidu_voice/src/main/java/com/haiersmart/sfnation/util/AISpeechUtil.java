package com.haiersmart.sfnation.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;

import com.haiersmart.sfnation.R;
import com.haiersmart.sfnation.application.FridgeApplication;
import com.haiersmart.sfnation.common.AppManager;
import com.haiersmart.sfnation.common.MobileUtils;
import com.haiersmart.sfnation.common.MyLogUtil;
import com.haiersmart.sfnation.common.SpUtils;
import com.haiersmart.sfnation.common.ToastUtil;
import com.haiersmart.sfnation.constant.ConstantUtil;
import com.haiersmart.voice.process.baidu.BaiduEmotionProcessUtil;
import com.haiersmart.voice.process.engine.SpeechHandleIntermediary;
import com.haiersmart.voice.utils.FloatAnimUtil;
import com.haiersmart.voice.utils.FloatPresenter;
import com.haiersmart.voice.utils.system.SystemUtil;

import static com.haiersmart.voice.utils.VoiceEmotionContants.EMOTION_SLEEP;
import static com.haiersmart.voice.utils.VoiceEmotionContants.EMOTION_SPEAK;
import static com.haiersmart.voice.utils.VoiceEmotionContants.EMOTION_WELCOME;

/**
 * Created by Pengliang on 2016/11/21.
 * 云之声工具类
 * 增加海知语音
 */

public class AISpeechUtil {
    public static final String TAG = AISpeechUtil.class.getSimpleName();

    public static boolean iSVoiceOpen() {
        return (boolean) SpUtils.getInstance(FridgeApplication.get()).get(ConstantUtil.VOICE_SWITCH, true);
    }

    public static boolean iSFirstWakeUP() {
        return (boolean) SpUtils.getInstance(FridgeApplication.get()).get(ConstantUtil.FIRST_WAKEUP, true);
    }

    public static void startSpeechWork() {//开启唤醒
        SpeechHandleIntermediary.build().startWakeUp();
        FloatPresenter.getMyFV().setVisibility(View.VISIBLE);
        //开启语音工作
        initSpeech();
    }

    public static void playVoiceVideo() {
        if (iSFirstWakeUP()) {
            FloatPresenter.destory();
            String uri = "android.resource://" + FridgeApplication.get().getPackageName() + "/" + R.raw.duer_show;
            FloatPresenter.getVedioView().setVisibility(View.VISIBLE);
            FloatPresenter.getVedioView().setVideoURI(Uri.parse(uri));
            FloatPresenter.getVedioView().start();
            FloatPresenter.getVedioView().setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    SpUtils.getInstance(FridgeApplication.get()).put(ConstantUtil.FIRST_WAKEUP, false);
                    reWakeup(true);
                    FloatPresenter.destoryVedioView();
                }
            });
        } else {
            reWakeup(true);
        }

    }


    public static void stopSpeechWork() {//关闭唤醒
        SpeechHandleIntermediary.build().stopWorkFlow();
        BaiduEmotionProcessUtil.stopEmotion();
//        FloatPresenter.getMyFV().setVisibility(View.GONE);
        FloatPresenter.destory();
    }

    public static void breakVoice() {
        if (iSVoiceOpen()) {
            startPlayTTS("再见", false, new TTSStateListener() {
                @Override
                public void stop() {
//                    stopAsrAndStartWakeup();
//                    breakTTs();
//                    SpeechHandleIntermediary.build().startWakeUp();
//                    BaiduEmotionProcessUtil.stopEmotion();
//                    FloatPresenter.destory();
                    waitWakeup();
                }
            });
        } else {
            ToastUtil.showToastCenter("请打开语音开关");
        }
    }

    public static void breakTTs() {
        SpeechHandleIntermediary.build().breakVoice();
    }

    private static long OldTime;

    public static void reWakeup(boolean istts) {
        if (SystemClock.uptimeMillis() - OldTime < 1000 * 2) {
            return;
        }
        OldTime = SystemClock.uptimeMillis();
        SpeechHandleIntermediary.build().reWakeUp(istts);
    }

    public static void stopAsrAndStartWakeup() {
        SpeechHandleIntermediary.build().stopAsr();
        SpeechHandleIntermediary.build().startWakeUp();
    }

    private static boolean isTillTtsEnd;
    private static Handler handler = new Handler();

    public static void playAnim(String emotion, final boolean isTts) {
//        isTillTtsEnd = isTts;
//        BaiduEmotionProcessUtil.getEmotionProcessUtil().processEmotion(emotion);
//        if (!isTillTtsEnd) {
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    if (!isTillTtsEnd) {
//                        FloatAnimUtil.getFloatAnimUtil().stop();
//                        FloatPresenter.destory();
//                    }
//                }
//            }, 5 * 1000);
//        }
    }

    private static void playAnim(String emotion) {
        FloatPresenter.getLayout().toSpeak(emotion.equals(EMOTION_SPEAK));
        BaiduEmotionProcessUtil.getEmotionProcessUtil().processEmotion(emotion);
    }

    private static String partialText = "";

    private static void initSpeech() {
        SpeechHandleIntermediary.build().listenWorkFlowStatus(new SpeechHandleIntermediary.IGetWorkFlowStatus() {
            @Override
            public void wakeUpSuccess(boolean istts) {
                //亮屏
                SystemUtil.brightScreen();
                //恢复默认图片
//                if (isBreak) {
//                    playAnim(EMOTION_WELCOME, false);
//                } else {
//                    playAnim(EMOTION_DEFAULT, false);
//                }

                MediaControllerUtil.switchPlayState(MediaControllerUtil.TTS_Player);
                if (iSFirstWakeUP()) {
                    playVoiceVideo();
                } else {
                    playAnim(EMOTION_WELCOME);
                    if (istts) {
                        if (MobileUtils.isNetworkConnected(FridgeApplication.get())) {
                            startPlayTTS("您好");
                        } else {
                            startPlayTTS("请连接网络");
                        }
                    }
                }

                android.util.Log.e(TAG, "wakeUpSuccess: ------> 唤醒成功 " + istts + "  " + AppManager.getInstance().top().getClass().getSimpleName());

//                BaiduEmotionProcessUtil.getEmotionProcessUtil().processEmotion(EMOTION_DEFAULT);
//                if (!com.haiersmart.utilslib.app.AppManager.getInstance().top().getClass().equals(MainActivity.class)) {
//                    EmotionProcessUtil.getEmotionProcessUtil().processEmotion(VoiceEmotionContants.EMOTION_DEFAULT);
//                    android.util.Log.e(TAG, "wakeUpSuccess: ------> 唤醒成功 " + AppManager.getInstance().top().getClass().getSimpleName());
//                    SpeechHandleIntermediary.build().ttsPlay("您好");
//                } else {
//                    startActivity(MainActivity_Voice.class);
//                }
            }

            @Override
            public void wakeUpFailure() {
                android.util.Log.d(TAG, "wakeUpFailure: ----> " + TAG);
            }

            @Override
            public void asrStart() {
                android.util.Log.d(TAG, "asrStart: -----> " + TAG);
                partialText = "";
                setSpeakTvText("");
            }

            @Override
            public void asrFinish(String speakText) {
                android.util.Log.d(TAG, "asrFinish: -----> ");
//				updateUIByASRResult();
                partialText = "";
                setSpeakTvVis(false);
//                setSpeakTvText(SpeechHandleIntermediary.build().getASRResult());
                setSpeakTvText(speakText);
                playAnim(EMOTION_WELCOME);
                MyLogUtil.d("ASRResult", "" + SpeechHandleIntermediary.build().getASRResult());
            }

            @Override
            public void asrBegin() {
                android.util.Log.d(TAG, "asrBegin: ------>");
                //亮屏
                SystemUtil.brightScreen();
                playAnim(EMOTION_SPEAK);
                setSpeakTvVis(true);
                partialText = "";
                setSpeakTvText("");
            }

            @Override
            public void asrPartial(String speakText) {
                android.util.Log.d(TAG, "asrPartial: ------->");
//				updateUIByASRResult();

                partialText = SpeechHandleIntermediary.build().getASRResult();
                setSpeakTvVis(true);
                playAnim(EMOTION_SPEAK);
                setSpeakTvText(speakText);
                MyLogUtil.d("ASRResult", "" + partialText);
                MyLogUtil.d("ASRResult", "VISIBLE");
            }

            @Override
            public void asrError() {
                android.util.Log.d(TAG, "asrError: -------> ");
//                playAnim(EMOTION_SLEEP);
                playAnim(EMOTION_WELCOME);
                setSpeakTvVis(false);

            }

            @Override
            public void asrStop() {
                android.util.Log.d(TAG, "asrStop: --------> MainActivity");
//                BaiduEmotionProcessUtil.getEmotionProcessUtil().processEmotion(VoiceEmotionContants.EMOTION_SLEEP);
//                BaiduEmotionProcessUtil.stopEmotion();
//                FloatPresenter.destory();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        setSpeakTvVis(false);
                        playAnim(EMOTION_SLEEP);
                    }
                });

            }


            @Override
            public void ttsSynthesizefinish() {
                android.util.Log.d(TAG, "ttsSynthesizefinish: ------>");
            }

            @Override
            public void ttsStart() {
                android.util.Log.d(TAG, "ttsStart: -------> ttsStart");
//                stopAsrAndStartWakeup();
                MediaControllerUtil.switchPlayState(MediaControllerUtil.TTS_Player);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        setSpeakTvText("");
                        partialText = "";
                        setSpeakTvVis(false);
                        MyLogUtil.d("ASRResult", "INVISIBLE");
                    }
                });
            }

            @Override
            public void ttsFinish() {
                android.util.Log.d(TAG, "ttsFinish: ------->");
                if (ttsStateListener != null) {
                    ttsStateListener.stop();
                    ttsStateListener = null;
                }
//                SpeechHandleIntermediary.build().welcomeWorkFlow();
//                reWakeup(false);
//                BaiduEmotionProcessUtil.getEmotionProcessUtil().processEmotion(VoiceEmotionContants.EMOTION_DEFAULT);
                if (isTillTtsEnd) {
                    FloatAnimUtil.getFloatAnimUtil().stop();
                    FloatPresenter.destory();
                }
//                playAnim(EMOTION_SLEEP);
//                playAnim(EMOTION_WELCOME);

            }

            @Override
            public void ttsError() {//未实现
//                if (ttsStateListener != null) {
//                    ttsStateListener.stop();
//                    ttsStateListener = null;
//                }
//
//                android.util.Log.d(TAG, "ttsError: -------->");
////                playAnim(EMOTION_SLEEP);
//                playAnim(EMOTION_WELCOME);
            }

            @Override
            public void asrRecEnd() {

            }

        });
    }

    public static void setSpeakTvVis(boolean isVis) {
        if (FloatPresenter.getTvSpeakText() == null) {
            return;
        }
        FloatPresenter.getTvSpeakText().setVisibility(isVis ? View.VISIBLE : View.INVISIBLE);
    }

    public static void setSpeakTvText(String text) {
        if (FloatPresenter.getTvSpeakText() == null) {
            return;
        }
        FloatPresenter.getTvSpeakText().setText(text);
    }


    public static void startTalk(Context context) {
        MyLogUtil.d(TAG, "!--->startTalk----");
        reWakeup(true);
    }

    public static void startPlayTTS(Context context, String str) {
        MyLogUtil.d(TAG, "!--->startTalk----");
        SpeechHandleIntermediary.build().ttsPlay(str);
    }

    public static void startPlayTTS(String str) {
        MyLogUtil.d(TAG, "!--->startTalk----");
        SpeechHandleIntermediary.build().ttsPlay(str);
    }

    public static void startPlayTTS(String str, TTSStateListener ttsListener) {
        MyLogUtil.d(TAG, "!--->startTalk----");
        SpeechHandleIntermediary.build().ttsPlay(str);
        ttsStateListener = ttsListener;
    }

    public static void startPlayTTS(String str, boolean isWithArs, TTSStateListener ttsListener) {
        MyLogUtil.d(TAG, "!--->startTalk----");
        SpeechHandleIntermediary.build().ttsPlay(str, isWithArs);
        ttsStateListener = ttsListener;
    }

    public static void startPlayTTS(String str, boolean isWithArs) {
        MyLogUtil.d(TAG, "!--->startTalk----");
        SpeechHandleIntermediary.build().ttsPlay(str, isWithArs);
    }

    public interface TTSStateListener {
        void stop();
    }

    private static TTSStateListener ttsStateListener;


    public static void stopplaytts() {
        MyLogUtil.d(TAG, "!--->goToSleep----");
        breakTTs();
    }

    public static void waitWakeup() {
        MyLogUtil.d(TAG, "!--->waitWakeup----");
        SpeechHandleIntermediary.build().toWaitWakeup();
    }
}
