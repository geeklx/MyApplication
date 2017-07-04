//package com.haiersmart.voice.utils;
//
//import android.app.Activity;
//import android.text.TextUtils;
//import android.util.Log;
//
//import com.haiersmart.sfnation.common.AppManager;
//import com.haiersmart.sfnation.common.ToastUtil;
//import com.haiersmart.sfnation.util.AISpeechUtil;
//import com.haiersmart.voice.process.engine.SpeechHandleIntermediary;
//
//
//import java.util.List;
//
///**
// * Create with Android Studio.
// *
// * @author Hsueh
// * @email i@hsueh.top
// * @date 2017/1/20 13:53
// */
//
//public class FoodManagerUtil {
//    //冰箱和用户id
//    private String fridge_id = "";
//    private String user_id = "";
//    private String family_id = "";
//    List<Food_listInfoNew> mPublicFood;
//    private String foodNameId = "";
//    public static final String ADD = "add";
//    public static final String DEl = "del";
//    public static final String QUERY = "query";
//
//    public static final String FRESH = "101";
//    public static final String NORMAL = "102";
//    public static final String EXPIRING = "104";
//    public static final String EXPIRED = "105";
//
//    private static String TAG = "FoodManagerUtil";
//
//
//    public FoodManagerUtil() {
//        getFridgeData();
//    }
//
//    static FoodManagerUtil mFoodManagerUtil = new FoodManagerUtil();
//
//    public static FoodManagerUtil getInstance() {
//
//        return mFoodManagerUtil;
//    }
//
//
//    //添加食材
//    public void addFoodBynetWork(String ids, final String name) {
//
//        FoodmanagerrightaddParams p = new FoodmanagerrightaddParams(DataProvider.getFridge_id(), DataProvider.getUser_id(), ids);
//        Net.build(FoodManagerApi.class, getClass().getName()).food_add(ParamsUtils.just(p)).enqueue(new Callback<Object>() {
//            @Override
//            public void onResponse(Result<Object> result) {
//                if (result.isOK()) {
//                    SpeechHandleIntermediary.build().ttsPlay("已帮您把" + name + "添加到冰箱");
//                    com.haiersmart.sfnation.bizutils.ToastUtil.showToastCenter("添加成功");
//                    if (AppManager.getInstance().top().getClass().getSimpleName().equals("FoodManagerNewBanActivity")) {
//                        Activity activity = AppManager.getInstance().top();
//                        ((FoodManagerNewBanActivity) activity).retry();
//                    }
//                } else {
//                    Log.i("jceshi", "请求失败");
//                    SpeechHandleIntermediary.build().ttsPlay("对不起，服务器不给力，添加失败");
//                    ToastUtil.showToastCenter("网络请求失败");
//                    //ToastUtil.showToastLong(result.getMessage());
//                }
//
//            }
//        });
//    }
//
//    /**
//     * 新鲜度
//     * 根据新鲜度获取冰箱中的食材列表接口,food_fresh传空串显示所有食材，
//     * 新鲜度tag 101：新鲜 102:正常 104:快过期 105：已过期
//     *
//     * @param fresh
//     */
//    public void queryByFresh(final String fresh) {
//        FoodmanagerleftupdatesParams params = new FoodmanagerleftupdatesParams(DataProvider.getFridge_id(), DataProvider.getUser_id(), fresh);
//        Net.build(FoodManagerApi.class, getClass().getName()).food_fresh_query(ParamsUtils.just(params)).enqueue(new Callback<FoodManagerLeftfoodfreshquery>() {
//            @Override
//            public void onResponse(Result<FoodManagerLeftfoodfreshquery> result) {
//                if (result.isOK()) {
//                    ToastUtil.showToastShort("查询成功");
//                    List<Food_listModel> food_list = result.getResult().getFood_list();
//                    StringBuffer buffer = new StringBuffer();
//                    for (int i = 0; i < food_list.size(); i++) {
//                        String food_name = food_list.get(i).getFood_name();
//                        buffer.append(food_name + " ,");
//                    }
//                    String freshName = "";
//                    switch (fresh) {
//                        case FRESH:
//                            freshName = "新鲜";
//                            break;
//                        case NORMAL:
//                            freshName = "正常";
//                            break;
//                        case EXPIRING:
//                            freshName = "即将过期";
//                            break;
//                        case EXPIRED:
//                            freshName = "已过期";
//                            break;
//                        default:
//                            freshName="冰箱里";
//                            break;
//                    }
//                    if (TextUtils.isEmpty(buffer.toString())) {
//
//                        SpeechHandleIntermediary.build().ttsPlay("冰箱里没有" + freshName + "的食材呀");
//                    } else {
//
//                        SpeechHandleIntermediary.build().ttsPlay(freshName + "的食材有:  " + buffer.toString());
//                    }
//                } else {
//                    ToastUtil.showToastShort("查询失败");
//                    SpeechHandleIntermediary.build().ttsPlay("对不起，服务器不给力，查询失败");
//                }
//            }
//        });
//    }
//
//
//    //删除过期食材
//    public void deleteByFresh(String fresh) {
//        FoodmanagerleftupdatesParams params = new FoodmanagerleftupdatesParams(DataProvider.getFridge_id(), DataProvider.getUser_id(), fresh);
//        Net.build(FoodManagerApi.class, getClass().getName()).food_fresh_query(ParamsUtils.just(params)).enqueue(new Callback<FoodManagerLeftfoodfreshquery>() {
//
//            @Override
//            public void onResponse(Result<FoodManagerLeftfoodfreshquery> result) {
//                if (result.isOK()) {
//                    ToastUtil.showToastShort("操作成功");
//                    List<Food_listModel> food_list = result.getResult().getFood_list();
//                    for (int i = 0; i < food_list.size(); i++) {
//                        String food_definition_id = food_list.get(i).getFood_definition_id();
//                        deleteFoodByNetwork(food_definition_id, "");
//                    }
//                } else {
//                    ToastUtil.showToastShort("操作失败");
//                }
//            }
//        });
//    }
//
//    //删除冰箱食材
//    public void deleteFoodByNetwork(String ids, final String name) {
//        FoodmanagerrightDeleteParams p = new FoodmanagerrightDeleteParams(DataProvider.getFridge_id(), DataProvider.getUser_id(), ids);
//        Net.build(FoodManagerApi.class, getClass().getName()).food_delete(ParamsUtils.just(p)).enqueue(new Callback<Fridge_have_food>() {
//            @Override
//            public void onResponse(Result<Fridge_have_food> result) {
//                if (result.isOK()) {
//                    SpeechHandleIntermediary.build().ttsPlay("已帮您把" + name + "从冰箱删除");
//                    com.haiersmart.sfnation.bizutils.ToastUtil.showToastCenter("删除成功");
//                    if (AppManager.getInstance().top().getClass().getSimpleName().equals("FoodManagerNewBanActivity")) {
//                        Activity activity = AppManager.getInstance().top();
//                        ((FoodManagerNewBanActivity) activity).retry();
//                    }
//                } else {
//                    Log.i("jceshi", "请求失败");
//                    SpeechHandleIntermediary.build().ttsPlay("对不起，服务器不给力，删除失败");
//                    ToastUtil.showToastCenter("网络请求失败");
//                }
//            }
//        });
//    }
//
//    /*
//    * 获取暂存数据
//    * */
//    private static void getDateFromSave() {
////        mPublicFood = SaveValueUtil.getPublicFood();
//
//    }
//
//    private void getFridgeData() {
//        fridge_id = DataProvider.getFridge_id();
//        user_id = DataProvider.getUser_id();
//        family_id = DataProvider.getFamily_id();
//
//
//    }
//
//    /**
//     * Name2Id
//     *
//     * @param foodName
//     */
//    public void name2Id(final String foodName, final Name2IdCallBack name2IdCallBack) {
//        FoodVoiceQueryParams params = new FoodVoiceQueryParams(DataProvider.getFridge_id(), DataProvider.getUser_id(), foodName);
//        Net.build(FoodManagerApi.class, getClass().getName()).voice_query(ParamsUtils.just(params)).enqueue(new Callback<VoiceFoodQueryResult>() {
//            @Override
//            public void onResponse(Result<VoiceFoodQueryResult> result) {
//                if (result.isOK()) {
//                    VoiceFoodQueryResult voiceFoodQueryResult = result.getResult();
//                    String definition_id = "";
//                    String foods_ids = "";
//                    if (voiceFoodQueryResult.getFood_definition_id() != null) {
//                        definition_id = voiceFoodQueryResult.getFood_definition_id();
//                    }
//                    if (voiceFoodQueryResult.getFridge_food_ids() != null) {
//                        foods_ids = voiceFoodQueryResult.getFridge_food_ids();
//                    }
//                    name2IdCallBack.getId(definition_id, foods_ids);
//                } else {
////                    SpeechHandleIntermediary.build().ttsPlay("找不到这个食材呀");
//                    AISpeechUtil.startPlayTTS("服务器不给力哦");
//                }
//            }
//        });
//
//    }
//
//}
