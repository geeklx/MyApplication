package com.haiersmart.voice.process.baidu;

import android.util.Log;

import com.haiersmart.voice.bean.BaiduFridgeResultBean;
import com.haiersmart.voice.process.engine.SpeechHandleIntermediary;

/**
 * Create with Android Studio.
 *
 * @author Hsueh
 * @email i@hsueh.top
 * @date 2017/2/13 18:49
 */

public class BaiduFridgeProcessor extends BaiduBaseProcessor<BaiduFridgeResultBean> {

    private static String TAG = BaiduFridgeProcessor.class.getSimpleName();
    private String id;

    @Override
    protected void process(BaiduFridgeResultBean commonResultBean, SpeechHandleIntermediary speechHandle) {
//        EventBus.getDefault().register(this);
        String intent = commonResultBean.getResult().getNlu().getIntent();
        BaiduFridgeResultBean.ResultBean.NluBean.SlotsBean slots = commonResultBean.getResult().getNlu().getSlots();
//        FridgeControlUtil fridgeControlUtil = FridgeControlUtil.getInstance();

//        fridgeControlUtil.init();

//        if (!TextUtils.isEmpty(intent)) {
//            Log.d(TAG, "process: intent---->" + intent);
//            switch (intent) {
//                case VoiceConstants.BAIDU_INTENT_TYPE_TEMP:
//                    Log.d(TAG, "process: 温度处理intent");
//                    String room = slots.getRoom();
//                    String temp_negative = slots.getTemp_negative();
//                    String set_temp = "";
//                    if (temp_negative != null && !TextUtils.isEmpty(temp_negative) && "零下".equals(temp_negative)) {
//                        set_temp = String.valueOf(Integer.parseInt(slots.getSet_temp()) * (-1));
//                    } else {
//                        set_temp = slots.getSet_temp();
//                    }
//                    String temp_down = slots.getTemp_down();
//                    String temp_down_size = slots.getTemp_down_size();
//                    String temp_up = slots.getTemp_up();
//                    String temp_up_size = slots.getTemp_up_size();
//
//                    handleTemp(room, temp_down, temp_down_size, temp_negative, temp_up, temp_up_size, set_temp, fridgeControlUtil, speechHandle);
//
//                    break;
//                case VoiceConstants.BAIDU_INTENT_TYPE_TIME:
//                    Log.d(TAG, "process: 屏幕关闭延时intent");
//                    String timeout = slots.getTimeout();
//                    if (timeout != null && !TextUtils.isEmpty(timeout)) {
//                        int time = Integer.parseInt(timeout) * 1000;
//                        fridgeControlUtil.setScreenOffTime(time);
//                        speechHandle.ttsPlay("屏幕将于" + timeout + "秒后关闭");
//                    }
//                    break;
//                //商城相关业务处理
//                case VoiceConstants.BAIDU_INTENT_TYPE_MARKET:
//                    String category = slots.getCategory_2();
//                    Log.d(TAG, "process: 商城处理intent ： category : " + category);
//                    //new
////                    if (AppManager.getInstance().top() instanceof ShopIndexActivity) {
////                        AISpeechUtil.startPlayTTS("当前已是商城页面了哦");
////                        return;
////                    }
////                    Intent shopIntent = new Intent(mContext, ShopIndexActivity.class);
////                    shopIntent.setAction("voice_tobuy");
////                    shopIntent.putExtra(SpeechOperationAppConstants.Shop.COMMODITY_CATEFORY, category);
////                    UIHandleIntermediary.startActivityWithIntent(shopIntent, "好的，已经为您打开商城");
//                    //old
////                    activity.startActivity(new Intent(activity, ShopIndexActivity.class));
////                    speechHandle.ttsPlay("好的，已为您打开商城");
//                    break;
//                case VoiceConstants.BAIDU_INTENT_TYPE_FOOD_MANAGER:
//                    Log.d(TAG, "process: 食材管理处理intent");
////                    if (!UserUtils.isUserLogin()) {
////                        speechHandle.ttsPlay("请先登录哦~");
////                        return;
////                    }
//                    String name = slots.getName();
//                    String action = slots.getAction();
////                    if (FoodManagerUtil.ADD.equals(action)) {
////                        updateFoodManager(FoodManagerUtil.ADD, name, "");
////                        speechHandle.ttsPlay("正在帮您添加" + name);
////                    } else if (FoodManagerUtil.DEl.equals(action)) {
////                        updateFoodManager(FoodManagerUtil.DEl, name, "");
////                        speechHandle.ttsPlay("正在帮您删除" + name);
////                    }
//                    break;
//                case VoiceConstants.BAIDU_INTENT_TYPE_FOOD_SEARCH:
//                    Log.d(TAG, "process: 食材搜索处理intent");
////                    if (!UserUtils.isUserLogin()) {
////                        speechHandle.ttsPlay("请先登录哦~");
////                        return;
////                    }
//                    String not_expire = slots.getNot_expire();
//                    String foodNameSeach = slots.getName();
//                    String fresh = slots.getFresh();
//                    String normal = slots.getNormal();
//                    String expiring = slots.getExpiring();
//                    String expired = slots.getExpired();
//                    String freshName = "";
////                    if (!TextUtils.isEmpty(fresh)) {
////                        freshName = FoodManagerUtil.FRESH;
////                    } else if (!TextUtils.isEmpty(normal)) {
////                        freshName = FoodManagerUtil.NORMAL;
////                    } else if (!TextUtils.isEmpty(expiring)) {
////                        freshName = FoodManagerUtil.EXPIRING;
////                    } else if (!TextUtils.isEmpty(expired)) {
////                        freshName = FoodManagerUtil.EXPIRED;
////                    } else if (!TextUtils.isEmpty(foodNameSeach)
////                            && !TextUtils.isEmpty(not_expire)) {
////                        freshName = "";
////                    }
////                    updateFoodManager(FoodManagerUtil.QUERY, "", freshName);
////                    speechHandle.ttsPlay("正在帮您查询...");
//                    break;
//                case VoiceConstants.BAIDU_INTENT_TYPE_SETMODE:
//                    Log.d(TAG, "process: 冰箱温度模式设置intent");
//                    String mode = slots.getMode();
//                    if (mode != null && !TextUtils.isEmpty(mode)) {
////                        if (CtrlServiceUtil.isContainMode(mode)) {
////                            speechHandle.ttsPlay("冰箱没有" + mode + "模式哦");
////                            return;
////                        }
//                        fridgeControlUtil.setModel(mode);
//                        speechHandle.ttsPlay("已帮您将冰箱设置为" + mode + "模式");
//                    } else {
//                        speechHandle.ttsPlay("对不起 ,小馨没有听清楚 ");
//                    }
//                    break;
//                case VoiceConstants.BAIDU_INTENT_TYPE_CLOSEMODE:
//                    String roomClose = slots.getRoom();
//                    String close = slots.getClose();
//                    handleClose(roomClose, close, fridgeControlUtil);
//                    break;
//                case VoiceConstants.BAIDU_INTENT_TYPE_VERSION:
//                    String sysVersion = fridgeControlUtil.getSysVersion();
//                    if (sysVersion != null && !TextUtils.isEmpty(sysVersion)) {
//                        speechHandle.ttsPlay("系统版本为" + sysVersion);
//                    }
//                    break;
//                //拍照识别intent
//                case VoiceConstants.BAIDU_INTENT_FOOD_IMAGE_RECOGNITION:
//                    Log.d(TAG, "process: 食材识别intent");
////                    SpeechHandleIntermediary.build().ttsPlay("为您打开拍照界面");
////                    UIHandleIntermediary.startActivity(BaiduFoodRecActivity.class);
//                    break;
//                case VoiceConstants.BAIDU_INTENT_SETTING_APP:
//                    SpeechHandleIntermediary.build().ttsPlay("暂不支持该功能哦");
//                    break;
//                default:
//                    SpeechHandleIntermediary.build().ttsPlay("暂不支持该功能哦");
//                    break;
//            }
//        }


    }

    /**
     * 语音处理模式关闭
     *
     * @param roomClose         关闭的舱室
     * @param close             关闭
     * @param fridgeControlUtil
     */
//    private void handleClose(String roomClose, String close, FridgeControlUtil fridgeControlUtil) {
//        if (TextUtils.isEmpty(roomClose) || TextUtils.isEmpty(close)) {
//            AISpeechUtil.startPlayTTS("小馨没有听清楚哦");
//            return;
//        }
////        if (CtrlServiceUtil.isContainMode(roomClose)) {
////            AISpeechUtil.startPlayTTS("冰箱没有" + roomClose + "模式哦");
////            return;
////        }
//        fridgeControlUtil.closeMode(roomClose);
//        AISpeechUtil.startPlayTTS("已为您关闭" + roomClose + "模式");
//
//
//    }

//    private void handleTemp(String room, String temp_down, String temp_down_size, String temp_negative, String temp_up, String temp_up_size, String set_temp, FridgeControlUtil fridgeControlUtil, SpeechHandleIntermediary speechHandle) {
//        if (TextUtils.isEmpty(room)
//                && TextUtils.isEmpty(temp_down)
//                && TextUtils.isEmpty(temp_down_size)
//                && TextUtils.isEmpty(temp_negative)
//                && TextUtils.isEmpty(temp_up)
//                && TextUtils.isEmpty(temp_up_size)
//                && TextUtils.isEmpty(set_temp)) {
//            Log.d(TAG, "查询所有舱室的温度");
//
////            String temperVoice = CtrlServiceUtil.getShowTempVoice();
////            speechHandle.ttsPlay(temperVoice);
//            return;
//        }
//        setTemp(fridgeControlUtil, room, set_temp, temp_down, temp_down_size, temp_up, temp_up_size);
//
//    }

    /**
     * @param fridgeControlUtil
     * @param room
     * @param set_temp
     * @param temp_down
     * @param temp_down_size
     * @param temp_up
     * @param temp_up_size
     */
//    private void setTemp(FridgeControlUtil fridgeControlUtil, String room, String set_temp, String temp_down, String temp_down_size, String temp_up, String temp_up_size) {
//
//
//        if (null != set_temp
//                && !TextUtils.isEmpty(set_temp)
//                && !TextUtils.isEmpty(room)
//                && TextUtils.isEmpty(temp_down)
//                && TextUtils.isEmpty(temp_up)) {
//
////            fridgeControlUtil.setTemp(set_temp,room);
//            fridgeControlUtil.updateTemp(set_temp, room, "set");
//
//        } else if (null != temp_down
//                && !TextUtils.isEmpty(temp_down)
//                && TextUtils.isEmpty(temp_down_size)
//                && TextUtils.isEmpty(set_temp)
//                && TextUtils.isEmpty(temp_up)
//                && TextUtils.isEmpty(temp_up_size)) {
//
//            fridgeControlUtil.updateTemp("1", room, "down");
//
//        } else if (null != temp_down
//                && !TextUtils.isEmpty(temp_down)
//                && !TextUtils.isEmpty(temp_down_size)
//                && TextUtils.isEmpty(set_temp)
//                && TextUtils.isEmpty(temp_up)
//                && TextUtils.isEmpty(temp_up_size)) {
//
//            fridgeControlUtil.updateTemp(temp_down_size, room, "down");
//
//        } else if (null != temp_up
//                && !TextUtils.isEmpty(temp_up)
//                && !TextUtils.isEmpty(temp_up_size)
//                && TextUtils.isEmpty(set_temp)
//                && TextUtils.isEmpty(temp_down)
//                && TextUtils.isEmpty(temp_down_size)) {
//
//            fridgeControlUtil.updateTemp(temp_up_size, room, "up");
//
//        } else if (null != temp_up
//                && !TextUtils.isEmpty(temp_up)
//                && TextUtils.isEmpty(temp_up_size)
//                && TextUtils.isEmpty(set_temp)
//                && TextUtils.isEmpty(temp_down)
//                && TextUtils.isEmpty(temp_down_size)) {
//
//            fridgeControlUtil.updateTemp("1", room, "up");
//
//        } else if (null != room && !TextUtils.isEmpty(room)
//                && TextUtils.isEmpty(temp_down)
//                && TextUtils.isEmpty(temp_down_size)
//                && TextUtils.isEmpty(temp_up)
//                && TextUtils.isEmpty(temp_up_size)
//                && TextUtils.isEmpty(set_temp)) {
//            fridgeControlUtil.updateTemp("0", room, "query");
//        } else {
//            SpeechHandleIntermediary.build().ttsPlay("抱歉，该功能暂时不支持呦！");
//        }
//    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void Name2Id(Name2IdEvent idEvent) {
//        String id = idEvent.getId();
//        String name = idEvent.getName();
//        Log.d(TAG, "Name: " + name + ",id" + id);
//        this.id = id;
//
//    }

    /**
     * @param type
     * @param name
     * @param fresh
     */
    public void updateFoodManager(final String type, final String name, final String fresh) {
        Log.e(TAG, "updateFoodManager: " + type + "," + name + "," + fresh);
//        if (FoodManagerUtil.QUERY.equals(type)) {
//            if (!TextUtils.isEmpty(fresh)) {
//                FoodManagerUtil.getInstance().queryByFresh(fresh);
//            }
//            FoodManagerUtil.getInstance().queryByFresh(fresh);
//            //new
//            if (!(AppManager.getInstance().top() instanceof FoodManagerNewBanActivity)) {
//                AISpeechUtil.startPlayTTS("当前已是食材管理页哦~");
//                Intent intent = new Intent(AppManager.getInstance().top(), FoodManagerNewBanActivity.class);
//                intent.putExtra("fresh", fresh);
//                UIHandleIntermediary.startActivityWithIntent(intent, "好的，正在为您打开食材管理");
//                AppManager.getInstance().top().startActivity(intent);
//            }
//
//            return;
//        }
//
//        FoodManagerUtil.getInstance().name2Id(name, new Name2IdCallBack() {
//            @Override
//            public void getId(String addId, String delId) {
//                switch (type) {
//                    case FoodManagerUtil.ADD:
//                        Log.d(TAG, "getId: ------> 添加");
//                        if (TextUtils.isEmpty(addId)) {
//                            AISpeechUtil.startPlayTTS("没有该食材哟");
//                            return;
//                        }
//                        FoodManagerUtil.getInstance().addFoodBynetWork(addId, name);
//                        break;
//                    case FoodManagerUtil.DEl:
//                        Log.d(TAG, "getId: ------> 删除");
//                        if (TextUtils.isEmpty(delId)) {
//                            AISpeechUtil.startPlayTTS("没有该食材哟");
//                            return;
//                        }
//                        FoodManagerUtil.getInstance().deleteFoodByNetwork(delId, name);
////                        EventBus.getDefault().post(new FoodManagerEvent(id));
//                        break;
//                    default:
//                        break;
//                }
//            }
//        });


//        EventBus.getDefault().unregister(this);
    }
}
