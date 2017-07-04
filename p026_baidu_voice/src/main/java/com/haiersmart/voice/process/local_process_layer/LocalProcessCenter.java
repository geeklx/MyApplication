package com.haiersmart.voice.process.local_process_layer;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.haiersmart.sfnation.MainActivity;
import com.haiersmart.sfnation.common.AppActionActivity;
import com.haiersmart.sfnation.common.AppManager;
import com.haiersmart.sfnation.common.MyLogUtil;
import com.haiersmart.sfnation.common.NewAppManager;
import com.haiersmart.sfnation.common.ShellExe;
import com.haiersmart.sfnation.util.AISpeechUtil;
import com.haiersmart.voice.bean.local_feature_support.LocalFeatureSupport;
import com.haiersmart.voice.bean.local_feature_support.TargetBusiness;
import com.haiersmart.voice.process.engine.SpeechHandleIntermediary;
import com.haiersmart.voice.ui.UIHandleIntermediary;
import com.haiersmart.voice.utils.JsonUtils;
import com.haiersmart.voice.utils.file.FileOperationsUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.haiersmart.voice.ui.UIHandleIntermediary.TTS_CLOSE_APP;
import static com.haiersmart.voice.ui.UIHandleIntermediary.TTS_UNSOPPORTED_OPERATION;

/**
 * 本地资源处理中心
 * Created by JefferyLeng on 2017/2/24.
 */
public class LocalProcessCenter {

    private static String TAG = "local";

    public static final String OPERATION_OPEN = "open";
    public static final String OPERATION_CLOSE = "close";

    public static final String FOOD_MANAGER_CLASS = "com.haiersmart.sfnation.ui.cookbook.FoodManagerNewBanActivity";
    public static final String MAIN_CLASS = "com.haiersmart.sfnation.ui.MainActivity";
    public static final String COOKBOOK_CLASS = "com.haiersmart.sfnation.ui.cookbooknew.CookBookActivityNew";
    public static final String COOKBOOK_RESULT_CLASS = "com.haiersmart.sfnation.ui.cookbooknew.CookBookSearchResultActivity";

    static Map<String, TargetBusiness> localResourceMap = new HashMap<>();

    public static void parse() {
        try {
            InputStream inputStream = AppManager.getInstance().top().getAssets().open("local_feature_support.json");
            String localFeatureJson = FileOperationsUtil.convertInputStreamToString(inputStream);
            Log.d(TAG, "parse: -------->" + localFeatureJson);
            LocalFeatureSupport localFeatureSupport = JsonUtils.fromJson(localFeatureJson, LocalFeatureSupport.class);
            List<LocalFeatureSupport.AppsBean.AppBean> appBeanList = localFeatureSupport.getApps().getApp();
            for (LocalFeatureSupport.AppsBean.AppBean appBean : appBeanList) {
                List<LocalFeatureSupport.AppsBean.AppBean.OperationlistBean.OperationBean> operationBeanList = appBean.getOperationlist().getOperation();
                for (LocalFeatureSupport.AppsBean.AppBean.OperationlistBean.OperationBean operationBean : operationBeanList) {
                    List<String> queryList = operationBean.getQuerylist().getQuery();
                    for (String query : queryList) {
                        localResourceMap.put(query, new TargetBusiness(appBean.getClassX(), operationBean.getAttr()));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 匹配本地功能支持
     *
     * @param asrResult
     * @return
     */
    public static boolean matchLocalResource(String asrResult) {
        parse();
        Log.d("local", "matchLocalResource: --------> asrResult : " + asrResult);

        TargetBusiness targetBusiness = localResourceMap.get(asrResult);
        if (targetBusiness == null) {
            return false;
        }
        String operation = targetBusiness.getOperation();
        String className = targetBusiness.getClassName();
        Log.d("local", "matchLocalResource: ----> matchResult : " + targetBusiness.getOperation());
        MyLogUtil.d("classname::::1", "" + className);
        MyLogUtil.d("classname::::2", "" + AppManager.getInstance().top().getClass().getName());

//        if (className.equals(FOOD_MANAGER_CLASS) && !UserUtils.isUserLogin()) {
//            AISpeechUtil.startPlayTTS("想要打开食材管理，请先登录哦");
//            return true;
//        }

        if (className.equals(MAIN_CLASS)) {
            if (!(AppManager.getInstance().top().getClass().getName().equals(MAIN_CLASS))) {
                Activity activity = AppManager.getInstance().top();
                activity.startActivity(new Intent(activity, MainActivity.class));
                AISpeechUtil.startPlayTTS("已返回首页");
            } else {
                AISpeechUtil.startPlayTTS("已经在首页了");
            }
            return true;
        }

        switch (operation) {
            case OPERATION_CLOSE:
                //new
                //remove
                AppActionActivity appActionActivity = NewAppManager.getInstance().get(className);
                if(appActionActivity!=null){
                    NewAppManager.getInstance().remove(appActionActivity);
                    ShellExe.RootCCTCommand("input keyevent 4");
                    SpeechHandleIntermediary.build().ttsPlay(TTS_CLOSE_APP);
                }else {
                    SpeechHandleIntermediary.build().ttsPlay(TTS_UNSOPPORTED_OPERATION);
                }
                //old
//                if (!className.equals(AppManager.getInstance().top().getClass().getName())) {
//                    if (!(className.equals(COOKBOOK_CLASS) && AppManager.getInstance().top().getClass().getName().equals(COOKBOOK_RESULT_CLASS))) {
//                        AISpeechUtil.startPlayTTS("不在当前页哦~");
//                        return true;
//                    }
//                }
//                if (className.equals(COOKBOOK_CLASS)) {
//                    if (AppManager.getInstance().top().getClass().getName().equals(COOKBOOK_RESULT_CLASS)) {
//                        UIHandleIntermediary.finishActivity(COOKBOOK_RESULT_CLASS);
//                    } else {
//                        UIHandleIntermediary.finishActivity(className);
//                    }
//                    return true;
//                }
//                UIHandleIntermediary.finishActivity(className);
                break;
            case OPERATION_OPEN:
                //new
                if (NewAppManager.getInstance().isTop(className)) {
                    AISpeechUtil.startPlayTTS("已是当前页了哦~");
                } else {
                    UIHandleIntermediary.startActivity(className);
                }
                //old
//                if (className.equals(AppManager.getInstance().top().getClass().getName())) {
//                    AISpeechUtil.startPlayTTS("已是当前页了哦~");
//                    return true;
//                }
//                UIHandleIntermediary.startActivity(className);
                break;
        }

        return true;
    }


    public static boolean isExit(String recognitionResult) {
        if (TextUtils.isEmpty(recognitionResult)) {
            return false;
        }

        String[] exits = new String[]{"小馨再见", "关闭小新", "关闭小心", "再见", "拜拜", "闭嘴", "你吵死了", "退下", "滚蛋", "别说话", "请闭嘴"};

        for (String str : exits) {
            if (str.equals(recognitionResult)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isWakeVoice(String recognitionResult) {
        if (TextUtils.isEmpty(recognitionResult)) {
            return false;
        }
        String[] wakeVoices = new String[]{"你好小心", "你好", "小心", "小度", "小度小度",
                "你好小度", "小度你好", "小心你好", "您好"};
        for (String str : wakeVoices) {
            if (str.equals(recognitionResult)) {
                return true;
            }
        }
        return false;
    }
}
