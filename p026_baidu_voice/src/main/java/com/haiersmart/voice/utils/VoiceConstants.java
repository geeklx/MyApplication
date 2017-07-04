package com.haiersmart.voice.utils;

/**
 * 常量工具类
 * Created by JefferyLeng on 2016/12/21.
 */
public interface VoiceConstants {

    String SPEECH_CURRENT_STATUS_SP = "isVoice";

    boolean SPEECH_CURRENT_STATUS_OPEN = true;

    boolean SPEECH_CURRENT_STATUS_CLOSE = false;


    /**
     * 如果当前正在语音播放 用户有新的语音对话 该种情况 需要breakoff当前tts voice
     */
    String ACTION_BREAK_OFF_CURRENT_TTS_VOICE = "com.haiersmart.action.breakoff.current.voice";

    /**
     * 识别结束状态
     */
    String ACTION_CHANGE_UI_BY_CURRENT_VOICE_STATAUS_OVER = "com.haiersmart.action.voice.status.over";

    /**
     * 重新唤醒识别机制
     */
    String ACTION_RESTART_SPEECH_RECOGNITION = "com.haiersmart.action.restart.speech.recognition";

    /**
     * todo:临时演示使用 后期不需要
     * 重新启动语音识别模块的action
     */
    String ACTION_GET_VOICE_REC_TEXT = "com.haiersmart.action.get.voice.rec.text";

    /**
     * todo:临时演示使用  后期不需要
     */
    String VOICE_REC_EXTAR_KEIY = "voice_rec_text";


    /****************   馨厨语义命令  start   *******************/

    /** 通用属性命令 */

    /****************   馨厨语义命令  end     *******************/


    /**
     * 百度语意解析类型
     */

    /**
     * 处理语义解析类型 -- 火车票
     */
    String BAIDU_DOMAIN_TYPE_TRAIN_TICKET = "22";

    String BAIDU_INTENT_TYPE_TRAIN_TICKET = "22";

    String BAIDU_DOMAIN_TYPE_QICHE_TICKET = "30";

    String BAIDU_DOMAIN_TYPE_FEIJI_TICKET = "23";

    String BAIDU_DOMAIN_100 = "100";


    /**
     * 视频相关
     */
    String BAIDU_DOMAIN_TYPE_MOVIE = "movie_satisfy";

    /**
     * 优酷视频
     */
    String BAIDU_DOMAIN_TYPE_YOUKU_VIDEO = "FILM";

    String BAIDU_INTENT_TYPE_YOUKU_VIDEO = "SEARCH_FILM";

    /**
     * 处理语义解析类型 -- 天气
     */
    String BAIDU_DOMAIN_TYPE_WEATHER = "duer_weather";

    String BAIDU_INTENT_TYPE_WEATHER = "sys_weather";
    /**
     * 处理语义解析类型 -- 地址查询类需求
     */
    String BAIDU_DOMAIN_TYPE_ADDRESS = "lbs";

    String BAIDU_INTENT_TYPE_ADDRESS = "poi";
    /**
     * 处理语义解析类型 -- 音乐类需求
     */
    String BAIDU_DOMAIN_TYPE_MUSIC = "audio.music";

    String BAIDU_INTENT_TYPE_MUSIC = "audio.music.play";

    String BAIDU_INTENT_TYPE_MUSIC_NEXT = "audio.music.next";

    String BAIDU_INTENT_TYPE_MUSIC_ASK = "audio.music.ask";
    /**
     * 处理语义解析类型 -- 让度秘唱歌，类似调戏
     */
    String BAIDU_DOMAIN_TYPE_MUSIC_SING = "music";

    String BAIDU_INTENT_TYPE_MUSIC_SING = "music_sing";
    /**
     * 处理语义解析类型 -- 打开网址
     */
    String BAIDU_DOMAIN_TYPE_OPEN_WEB = "phone";

    String BAIDU_INTENT_TYPE_OPEN_WEB = "open_website";
    /**
     * 处理语义解析类型 -- 发短信
     */
    String BAIDU_DOMAIN_TYPE_SMS = "phone";

    String BAIDU_INTENT_TYPE_SMS = "sms";
    /**
     * 处理语义解析类型 -- 提醒、闹钟
     */
    String BAIDU_DOMAIN_TYPE_ALARM = "remind";

    String BAIDU_INTENT_TYPE_ALARM = "remind";

    /**
     * 计时器
     */
    String BAIDU_DOMAIN_TYPE_TIMER = "remind";

    String BAIDU_INTENT_TYPE_TIMER = "timer";
    /**
     * 处理语义解析类型 -- unKnown
     */
    String BAIDU_DOMAIN_TYPE_DEFAULT = "unknown";

    String BAIDU_INTENT_TYPE_DEFAULT = "unknown";
    /**
     * 处理语义解析类型 -- 菜谱
     */
    String BAIDU_DOMAIN_TYPE_COOKBOOK = "cookbook";

    String BAIDU_INTENT_TYPE_COOKBOOK = "cookbook.open";
    /**
     * 处理语义解析类型 -- 冰箱控制
     */
    String BAIDU_DOMAIN_TYPE_FRIDGE_CONTROL = "fridge";

//    String BAIDU_DOMAIN_TYPE_AUDIO_UNICAST_CONTROL= "audio.unicast";

    String BAIDU_DOMAIN_TYPE_AUDIO_UNICAST_CONTROL = "audio.unicast";


    //温度
    String BAIDU_INTENT_TYPE_TEMP = "fridge.setting.temperature";
    //关闭屏幕
    String BAIDU_INTENT_TYPE_TIME = "fridge.setting.timeout_off";
    //打开商城
    String BAIDU_INTENT_TYPE_MARKET = "fridge.app.market";
    //食材管理
    String BAIDU_INTENT_TYPE_FOOD_MANAGER = "fridge.food.manage";
    //查询食材
    String BAIDU_INTENT_TYPE_FOOD_SEARCH = "fridge.food.search";
    //设置冰箱模式
    String BAIDU_INTENT_TYPE_SETMODE = "fridge.setting.mode";
    //关闭模式
    String BAIDU_INTENT_TYPE_CLOSEMODE = "fridge.setting.room";
    //冰箱版本
    String BAIDU_INTENT_TYPE_VERSION = "fridge.info.version";


    /**
     * 处理语义解析类型 -- 喜马拉雅
     */
    String BAIDU_DOMAIN_TYPE_XMLY = "audio.unicast";
    String BAIDU_INTENT_TYPE_XMLY = "audio.unicast.play";

    /**
     * 处理语义解析类型 -- 控制
     */
    String BAIDU_DOMAIN_TYPE_HARDWARE_CONTROL = "control.hardware";
    //音量
    String BAIDU_INTENT_TYPE_VOLUME = "control.hardware.volume";
    //屏幕亮度
    String BAIDU_INTENT_TYPE_BRIGHT = "control.hardware.screen.bright";

    /**
     * 留言板
     */
    String BAIDU_DOMAIN_TYPE_MESSAGEBOARD = "memory";
    String BAIDU_INTENT_TYPE_MESSAGEBOARD = "memory";

    /**
     * 冰箱模式
     */
    String FRIDGE_MODE_SUDONG = "速冻";
    String FRIDGE_MODE_SULENG = "速冷";
    String FRIDGE_MODE_ZHINENG = "智能";
    String FRIDGE_MODE_JIARI = "假日";
    String FRIDGE_MODE_ZHENPIN = "珍品";
    String FRIDGE_MODE_SHAJUN = "杀菌";
    String FRIDGE_MODE_JINGHUA = "净化";
//    /**
//     * 冰箱仓室
//     */
//    String FRIDGE_ROOM_LENGDONG = "冷冻室";
//    String FRIDGE_ROOM_LENGCANG = "冷藏室";
//    String FRIDGE_ROOM_BIANWEN = "变温室";
//
//    /**
//     * 冰箱温度调节类型
//     */
//    String FRIDGE_TEMP_UP = "up";
//    String FRIDGE_TEMP_DOWN = "down";
//    String FRIDGE_TEMP_SET = "set";

    /**
     * 拍照识别intent
     */
    String BAIDU_INTENT_FOOD_IMAGE_RECOGNITION = "fridge.image.recognition";
    String BAIDU_INTENT_SETTING_APP = "fridge.setting.app";


}
