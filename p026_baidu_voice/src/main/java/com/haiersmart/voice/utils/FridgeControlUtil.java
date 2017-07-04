//package com.haiersmart.voice.utils;
//
//import android.content.Intent;
//import android.provider.Settings;
//import android.text.TextUtils;
//import android.view.Window;
//import android.view.WindowManager;
//
//import com.baidu.speech.core.LogUtil;
//import com.haiersmart.sfnation.application.FridgeApplication;
//import com.haiersmart.sfnation.common.AppManager;
//import com.haiersmart.sfnation.common.MyLogUtil;
//import com.haiersmart.sfnation.util.AISpeechUtil;
//import com.haiersmart.voice.process.engine.SpeechHandleIntermediary;
//
//
//
///**
// * Create with Android Studio.
// * 冰箱控制工具类
// *
// * @author Hsueh
// * @email jingexue@gmail.com
// * @date 2017/1/5 15:45
// * <p>
// * cpl修改为适应10.1
// */
//
//public class FridgeControlUtil {
//
//    /**
//     * 冰箱模式
//     */
//    private static final String FRIDGE_MODE_SUDONG = "速冻";
//    private static final String FRIDGE_MODE_SULENG = "速冷";
//    private static final String FRIDGE_MODE_ZHINENG = "智能";
//    private static final String FRIDGE_MODE_JIARI = "假日";
//    private static final String FRIDGE_MODE_ZHENPIN = "珍品";
//    private static final String FRIDGE_MODE_SHAJUN = "杀菌";
//    private static final String FRIDGE_MODE_JINGHUA = "净化";
//    /**
//     * 冰箱仓室
//     */
//    public static final String FRIDGE_ROOM_LENGDONG = "冷冻室";
//    public static final String FRIDGE_ROOM_LENGCANG = "冷藏室";
//    public static final String FRIDGE_ROOM_BIANWEN = "变温室";
//
//    /**
//     * 冰箱温度调节类型
//     */
//    private static final String FRIDGE_TEMP_UP = "up";
//    private static final String FRIDGE_TEMP_DOWN = "down";
//    private static final String FRIDGE_TEMP_SET = "set";
//    private static final String FRIDGE_TEMP_QUERY = "query";
//    private static final String FRIDGE_BRIGHT_UP = "up";
//    private static final String FRIDGE_BRIGHT_DOWN = "down";
//
//    private String TAG = getClass().getSimpleName();
//
//    private final static FridgeControlUtil fridgeControlUtil = new FridgeControlUtil();
//
//    public static FridgeControlUtil getInstance() {
//
//        return fridgeControlUtil;
//    }
//
//    private FridgeControlUtil() {
//    }
//
//    public void init() {
//        initVolumeValue();
//    }
//
//    /**
//     * 设置温度
//     *
//     * @param room
//     */
//    private void setTemp(String temp, String room) {
//        int anInt = Integer.parseInt(temp);
//        MyLogUtil.d("sssssssss::::", "" + anInt + "  " + room + "   " + DataProvider.getFridge_mode());
//        Intent mIntent = new Intent();
//        mIntent.setAction("com.haier.tft.control.pcb");
//
//        if (!CtrlServiceUtil.isOkTOSet(room)) {
//            AISpeechUtil.startPlayTTS("当前模式下不能调节" + room + "温度哦");
//            return;
//        }
//
//        if (CtrlServiceUtil.isInRange(anInt, room)) {
//            mIntent.putExtra("sendControl", getSet(room) + temp);
//            SpeechHandleIntermediary.build().ttsPlay("好的,已帮您将" + room + "设置为" + temp + "度");
//            com.haiersmart.sfnation.bizutils.ToastUtil.showToastCenter("设置成功");
//        } else {
//            SpeechHandleIntermediary.build().ttsPlay("已超出" + room + "控制范围内哦");
//            return;
//        }
//
//        AppManager.getInstance().top().sendBroadcast(mIntent);
//        LogUtil.d(TAG, "设置命令广播发送");
//    }
//
//    private String getSet(String room) {
//        switch (room) {
//            case FRIDGE_ROOM_LENGCANG:
//                return "setCold:";
//            case FRIDGE_ROOM_LENGDONG:
//                return "setFreeze:";
//            case FRIDGE_ROOM_BIANWEN:
//                return "setCustomArea:";
//        }
//        return "setCold:";
//    }
//
//    /**
//     * 调节温度判断封装
//     *
//     * @param value
//     * @param room
//     * @param type
//     */
//    public void updateTemp(String value, String room, String type) {
//        MyLogUtil.d("sssssssss::::", "" + value + "  " + room + "   " + type);
//        //获取当前仓室的温度
//        if (TextUtils.isEmpty(room)) {
//            SpeechHandleIntermediary.build().ttsPlay("小馨不知道你要调节哪个舱室呀,请说清楚一点 , 例如,冷藏室温度降低 .");
//            return;
//        }
//        int currentShowTemp = getCurrentShowTemp(room);
//
//        if (!CtrlServiceUtil.isHasChange(room)) {
//            SpeechHandleIntermediary.build().ttsPlay("冰箱没有变温室哦");
//            return;
//        }
//
//        if (FRIDGE_TEMP_QUERY.equals(type)) {
//            SpeechHandleIntermediary.build().ttsPlay(room + "温度为" + currentShowTemp + "度");
//            return;
//        }
//
//        int currentTemp = getCurrentLevelTemp(room);//当前档位
//
//        if (FRIDGE_TEMP_DOWN.equals(type)) {
//            setTemp(String.valueOf(currentTemp - Integer.parseInt(value)), room);
//        } else if (FRIDGE_TEMP_UP.equals(type)) {
//            setTemp(String.valueOf(currentTemp + Integer.parseInt(value)), room);
//        } else if (FRIDGE_TEMP_SET.equals(type)) {
//            setTemp(String.valueOf(value), room);
//        }
//
//    }
//
//    /**
//     * 获取当前显示温度
//     *
//     * @return
//     */
//    public int getCurrentShowTemp(String room) {
//
//        switch (room) {
//            case FRIDGE_ROOM_LENGCANG:
//                return CtrlServiceUtil.getColdShowTemp();
//            case FRIDGE_ROOM_LENGDONG:
//                return CtrlServiceUtil.getFreezeShowTemp();
//            case FRIDGE_ROOM_BIANWEN:
//                return CtrlServiceUtil.getChangeShowTemp();
//        }
//        return 0;
//    }
//
//    /**
//     * 获取当前档位
//     *
//     * @return
//     */
//    public int getCurrentLevelTemp(String room) {
//
//        switch (room) {
//            case FRIDGE_ROOM_LENGCANG:
//                return CtrlServiceUtil.getColdLevel();
//            case FRIDGE_ROOM_LENGDONG:
//                return CtrlServiceUtil.getFreezeLevel();
//            case FRIDGE_ROOM_BIANWEN:
//                return CtrlServiceUtil.getChangeLevel();
//        }
//        return 0;
//    }
//
//    //冷藏室
//    public int getColdLevel() {
//        return CtrlServiceUtil.getColdLevel();
//    }
//
//    //变温室
//    public int getMiddleLevel() {
//        return CtrlServiceUtil.getChangeLevel();
//    }
//
//    //冷冻室
//    public int getFreezeLevel() {
//        return CtrlServiceUtil.getFreezeLevel();
//    }
//
//
//    private void initVolumeValue() {
//
//    }
//
//    /**
//     * 获取音量
//     *
//     * @return
//     */
//
//    public int getVolume() {
//
//        int volume = 0;
//
//        return volume;
//    }
//
//    /**
//     * 设置声音音量
//     *
//     * @param value
//     */
//
//
//    public void setVolumeValue(int value) {
//
//
//    }
//
//
//    /**
//     * 设置模式
//     *
//     * @param value
//     */
//    public void setModel(String value) {
//        Intent mIntent = new Intent();
//        mIntent.setAction("com.haier.tft.control.pcb");
//
//        switch (value) {
//            case FRIDGE_MODE_JIARI:
//                mIntent.putExtra("sendControl", "model:openHolidayModel");
//                break;
//            case FRIDGE_MODE_SUDONG:
//                mIntent.putExtra("sendControl", "model:openFreezeModel");
//                break;
//            case FRIDGE_MODE_SULENG:
//                mIntent.putExtra("sendControl", "model:openColdModel");
//                break;
//            case FRIDGE_MODE_ZHINENG:
//                mIntent.putExtra("sendControl", "model:openSmartModel");
//                break;
//            case FRIDGE_MODE_ZHENPIN:
//                mIntent.putExtra("sendControl", "model:openZhenPinModel");
//                break;
//            case FRIDGE_MODE_SHAJUN:
//                mIntent.putExtra("sendControl", "setUVModel:1");//默认杀菌智能模式
//                break;
//            case FRIDGE_MODE_JINGHUA:
//                mIntent.putExtra("sendControl", "model:openCleanModel");
//                break;
//            default:
//                break;
//        }
//        AppManager.getInstance().top().sendBroadcast(mIntent);
//        LogUtil.d(TAG, "设置命令广播发送");
//    }
//
//    /**
//     * 设置背光时间  毫秒
//     */
//    public void setScreenOffTime(int paramInt) {
//        try {
//            Settings.System.putInt(AppManager.getInstance().top().getContentResolver(), Settings.System.SCREEN_OFF_TIMEOUT, paramInt);
//        } catch (Exception localException) {
//            localException.printStackTrace();
//        }
//    }
//
//    /**
//     * 设置屏幕亮度
//     *
//     * @param screenBrightness
//     */
//    private void setScreenBrightness(int screenBrightness) {
//
//        screenBrightness = screenBrightness * 255 / 100;
//        Settings.System.putInt(FridgeApplication.get().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, screenBrightness);
//        Window window = AppManager.getInstance().top().getWindow();
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
//            screenBrightness = Settings.System.getInt(AppManager.getInstance().top().getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
//        } catch (Settings.SettingNotFoundException e) {
//            e.printStackTrace();
//        }
//        screenBrightness = screenBrightness * 100 / 255;
//        return screenBrightness;
//    }
//
//
//    /**
//     * 设置屏幕亮度
//     *
//     * @param brightness
//     */
//    public void changeScreenBrightness(String type, int brightness) {
//        int anInt = getScreenBrightness();
//        anInt = anInt + 1;
//        switch (type) {
//            case FRIDGE_BRIGHT_UP:
//                if (anInt + brightness >= 100) {
//                    setScreenBrightness(100);
//                    SpeechHandleIntermediary.build().ttsPlay("已帮您调节到最大亮度");
//                    return;
//                }
//
//                setScreenBrightness(anInt + brightness);
//                SpeechHandleIntermediary.build().ttsPlay("已帮您调亮屏幕");
//                break;
//            case FRIDGE_BRIGHT_DOWN:
//                if (anInt - brightness <= 10) {
//                    setScreenBrightness(10);
//                    SpeechHandleIntermediary.build().ttsPlay("已帮您调节到最小亮度");
//                    return;
//                }
//                setScreenBrightness(anInt - brightness);
//                SpeechHandleIntermediary.build().ttsPlay("以帮你调暗屏幕");
//                break;
//            default:
//                break;
//        }
//
//    }
//
//
//    /**
//     * @return OS Version
//     */
//    public String getSysVersion() {
//        return DataProvider.getOs_version();
//    }
//
//    public void closeMode(String roomClose) {
//        Intent mIntent = new Intent();
//        mIntent.setAction("com.haier.tft.control.pcb");
//        switch (roomClose) {
//            case FRIDGE_MODE_JIARI:
//                mIntent.putExtra("sendControl", "model:closeHolidayModel");
//                break;
//            case FRIDGE_MODE_SUDONG:
//                mIntent.putExtra("sendControl", "model:closeFreezeModel");
//                break;
//            case FRIDGE_MODE_SULENG:
//                mIntent.putExtra("sendControl", "model:closeColdModel");
//                break;
//            case FRIDGE_MODE_ZHINENG:
//                mIntent.putExtra("sendControl", "model:closeSmartModel");
//                break;
//            case FRIDGE_MODE_ZHENPIN:
//                mIntent.putExtra("sendControl", "model:closeZhenPinModel");
//                break;
//            case FRIDGE_MODE_SHAJUN:
//                mIntent.putExtra("sendControl", "setUVModel:0");//关闭杀菌
//                break;
//            case FRIDGE_MODE_JINGHUA:
//                mIntent.putExtra("sendControl", "model:closeCleanModel");
//                break;
//            default:
//                break;
//        }
//        AppManager.getInstance().top().sendBroadcast(mIntent);
//        LogUtil.d(TAG, "设置命令广播发送");
//    }
//}
