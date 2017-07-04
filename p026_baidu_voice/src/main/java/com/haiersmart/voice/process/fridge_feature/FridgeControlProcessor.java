package com.haiersmart.voice.process.fridge_feature;//package com.haiersmart.voice.process.fridge_feature;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.util.Log;
//
//import com.haiersmart.sfnation.dboper.status.FridgeStatusStatus;
//import com.haiersmart.sfnation.domain.FridgeStatus;
//import com.haiersmart.voice.activity.AppManager;
//import com.haiersmart.voice.bean.FridgeControlResultBean;
//import com.haiersmart.voice.process.BaseProcessor;
//import com.haiersmart.voice.process.EmotionProcessUtil;
//import com.haiersmart.voice.process.SpeechHelper;
//import com.haiersmart.voice.ui.dialog.FridgeControlActivity;
//import com.haiersmart.voice.ui.dialog.FridgeControlDialog;
//import com.haiersmart.voice.utils.FridgeControlUtil;
//import com.haiersmart.voice.utils.VoiceEmotionContants;
//
//import java.util.List;
//
///**
// * 冰箱控制处理器
// * Created by xuejinge on 2016/12/27.
// */
//public class FridgeControlProcessor extends BaseProcessor<FridgeControlResultBean> {
//    FridgeControlDialog dialog;
//    FridgeStatus fridgeStatus;
//    private FridgeControlUtil mFridgeControlUtil;
//
//    @Override
//    protected void process(FridgeControlResultBean commonResultBean, SpeechHelper speechHelper) {
//
//        mFridgeControlUtil = FridgeControlUtil.getInstance();
//        fridgeStatus = FridgeStatusStatus.getInstance().selectFridgeStatus();
//
//        List<FridgeControlResultBean.ResultBeanX.IntentsBean> intents = commonResultBean.getResult().getIntents();
//        if (intents != null && intents.size() > 0) {
//            FridgeControlResultBean.ResultBeanX.IntentsBean.ParametersBean parameters = intents.get(0).getParameters();
//            //调节 类型
//            String attr = parameters.getAttr();
//            //调节的具体值
//            String attr_value = parameters.getAttr_value();
//            //调节范围
//            String temperature_zone = parameters.getTemperature_zone();
//            //操作类型(增加还是减小)
//            String operation = parameters.getOperation();
//
//            String text = intents.get(0).getOutputs().get(0).getProperty().getText();
//
////            if (TextUtils.isEmpty(operation)){
////                speechHelper.compoundByDuer("我不知道是要增大还是要减小哦,你可以这样说,冷冻室温度减小一度");
////                return;
////            }
////            if (TextUtils.isEmpty(temperature_zone)){
////                speechHelper.compoundByDuer("请说清楚是要调节什么室哦");
////                return;
////            }
//            switch (attr) {
//                //Volume
//                case "volume":
//                    int anInt = Integer.parseInt(attr_value);
//                    mFridgeControlUtil.updateVolumeValue(operation, anInt);
//                    speechHelper.compoundByDuer(text + ",当前音量是" + mFridgeControlUtil.getVolume());
//                    break;
//                //Temperature
//                case "temperature":
//                    int temp = 0;
//                    switch (temperature_zone) {
//                        case "冷藏室":
//                            Log.i(TAG,"冷藏室获取中");
//                            if(fridgeStatus.isHoliday()||(fridgeStatus.isHoliday()&&fridgeStatus.isQuick_freeze())){
//                                speechHelper.compoundByDuer("假日模式下冷藏室温度不可调节");
//                            } else if(fridgeStatus.isSmart()){
//                                speechHelper.compoundByDuer("智能模式下冷藏室温度不可调节");
//                            } else if(fridgeStatus.isQuick_cooling()||(fridgeStatus.isQuick_cooling()&&fridgeStatus.isQuick_freeze())){
//                                speechHelper.compoundByDuer("速冷模式下冷藏室温度不可调节");
//                            }else{
//                                speakTemp(attr_value,operation, temperature_zone,speechHelper,text);
//                            }
//                            temp = mFridgeControlUtil.getMiddleLevel();
//                            break;
//                        case "冷冻室":
//                            Log.i(TAG,"冷冻室获取中");
//                            if(fridgeStatus.isSmart()){
//                                speechHelper.compoundByDuer("智能模式下冷冻室温度不可调节");
//                            }else if(fridgeStatus.isQuick_freeze()||(fridgeStatus.isQuick_cooling()&&fridgeStatus.isQuick_freeze())||(fridgeStatus.isHoliday()&&fridgeStatus.isQuick_freeze())){
//                                speechHelper.compoundByDuer("速冻模式下冷冻室温度不可调节");
//                            }else{
//                                speakTemp(attr_value,operation, temperature_zone,speechHelper,text);
//                            }
//                            temp = mFridgeControlUtil.getFreezeLevel();
//                            break;
//                        case "变温室":
//                            Log.i(TAG,"变温室获取中");
//                            speakTemp(attr_value,operation, temperature_zone,speechHelper,text);
//                            temp = mFridgeControlUtil.getColdLevel();
//                            break;
//                    }
//
//                    Activity activity = AppManager.getAppManager().currentActivity();
//                    Intent intent  = new Intent("com.ai_voice_notifytemp");
//                    activity.sendBroadcast(intent);
////                    if(dialog==null){
////                        dialog = new FridgeControlDialog(activity);
////
////                    }
////                    if(!dialog.isShowing()){
////                        dialog.show();
////                    }
//                    AppManager.getAppManager().finishActivity(FridgeControlActivity.class);
//                    Intent intent2 = new Intent(activity, FridgeControlActivity.class);
//                    activity.startActivity(intent2);
//                    EmotionProcessUtil.getEmotionProcessUtil().processEmotion(VoiceEmotionContants.EMOTION_CALENDAR);
//                    break;
//                case "version":
//                    String sysVersion = mFridgeControlUtil.getSysVersion();
//                    speechHelper.compoundByDuer("当前系统版本为" + sysVersion);
//                    break;
//                case "auto_lock":
//                    // TODO: 2017/1/5 等待接口返回value确定再进行修改
//                    mFridgeControlUtil.setScreenOffTime(Integer.parseInt(attr_value) * 1000);
//                    break;
//                case "mode":
//                    mFridgeControlUtil.setModel(attr_value);
//                    speechHelper.compoundByDuer("已进入" + attr_value);
//                    break;
//                case "brightness":
//                    int systemBrightness = mFridgeControlUtil.getSystemBrightness();
//                    mFridgeControlUtil.changeScreenBrightness(operation, Integer.parseInt(attr_value));
//                    String text1 = commonResultBean.getResult().getIntents().get(0).getOutputs().get(0).getProperty().getText();
//                    speechHelper.compoundByDuer(text1);
//                    break;
//                default:
//                    break;
//            }
//
//
//        }
//    }
//public void speakTemp(String attr_value, String operation, String temperature_zone, SpeechHelper speechHelper, String text){
//    mFridgeControlUtil.updateTemp(attr_value,operation, temperature_zone);
//
//    if ("decrease".equals(operation)) {
//        //speechHelper.compoundByDuer(text + ",当前温度为" + (temp - Integer.parseInt(attr_value)) + "度");
//        Log.i(TAG,"调节数值："+attr_value);
//        speechHelper.compoundByDuer(text);
//
//    } else if ("increase".equals(operation)) {
//        //speechHelper.compoundByDuer(text + ",当前温度为" + (temp + Integer.parseInt(attr_value)) + "度");
//        speechHelper.compoundByDuer(text);
//    }
//}
//
//}
