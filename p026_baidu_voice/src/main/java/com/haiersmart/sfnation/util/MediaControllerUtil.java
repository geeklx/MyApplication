package com.haiersmart.sfnation.util;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * 多媒体控制 gyy
 */
public class MediaControllerUtil {
    public static final int Video_Player = 0x00001;// 视频播放器
    public static final int Music_Player = 0x00002;// 音乐播放器
    public static final int TTS_Player = 0x00003;// 语音播放器
    public static final int Null_Player = 0xfffff;// 空
    public static final int Youku_Player = 0x0004;//优酷视频播放器
    public static final int Netease_Player = 0x005;//网易云播放器
    public static int Current_Player = Null_Player;
    //    public static VideoControllerUtil videoControllerUtil;
    public static WeakReference<Context> reference;
//    static YoukuBasePlayerManager youkuBasePlayerManager;

    public static final int IS_FM_PLAYED = 0x11111;//喜马拉雅最后一次播放
    public static final int IS_NETS_PLAYED = 0x22222;//网易云音乐最后一次播放
    public static final int NO_PLAYED = 0x00000;//没有播放过任何音乐
    public static int LAST_PLAYED = NO_PLAYED;//

    public static int getLastPlayed() {
        return LAST_PLAYED;
    }

    public static void setLastPlayed(int lastPlayed) {
        LAST_PLAYED = lastPlayed;
    }

    /**
     * 在播放器的play中调用，使其他播放器暂停
     *
     * @param player
     */
    public static void switchPlayState(int player) {
        if (reference == null) {
            return;
        }
        Context context = reference.get();
        if (Current_Player == player)
            return;
        switch (player) {
            case Video_Player:// 视频
//                if (MusicControllerUtil.isPlay()) {
//                    MusicControllerUtil.pause();
//                }
//                if (MusicController.isPlaying()) {
//                    MusicController.pause();
//                }
                AISpeechUtil.waitWakeup();
                Current_Player = Video_Player;
                break;
            case Music_Player:// 音频
//                VideoControllerUtil.getInstance().pause();
//                if (MusicController.isPlaying()) {
//                    MusicController.pause();
//                }
                AISpeechUtil.waitWakeup();
                Current_Player = Music_Player;
                setLastPlayed(IS_FM_PLAYED);
                break;
            case TTS_Player:// 语音
//                if (MusicControllerUtil.isPlay()) {
//                    MusicControllerUtil.pause();
//                }
//                if (MusicController.isPlaying()) {
//                    MusicController.pause();
//                }
//                VideoControllerUtil.allPause();
//                if (youkuBasePlayerManager != null) {
//                    youkuBasePlayerManager.getMediaPlayerDelegate().pause();
//                }
                Current_Player = TTS_Player;
                break;
            case Youku_Player://优酷视频
                AISpeechUtil.waitWakeup();
                Current_Player = Youku_Player;
                break;
            case Netease_Player://网易云音乐
//                if (MusicControllerUtil.isPlay()) {
//                    MusicControllerUtil.pause();
//                }
                AISpeechUtil.waitWakeup();
                Current_Player = Netease_Player;
                setLastPlayed(IS_NETS_PLAYED);
                break;
            default:
                break;
        }

    }

    private static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    /**
     * 初始化多媒体管理
     *
     * @param context
     */
    public static void init(Context context) {
        reference = new WeakReference<Context>(context);
    }

    /**
     * 销毁多媒体管理
     */
    public static void destroy() {
        reference.clear();
    }

//    public static void setYoukuManager(YoukuBasePlayerManager manager) {
//        youkuBasePlayerManager = manager;
//    }
}