package com.haiersmart.voice.utils;

/**
 * 避免原来的业务代码收到影响 语音操作的业务 都会携带对应的意图
 * Created by JefferyLeng on 2017/2/28.
 */

public class SpeechOperationAppConstants {
    /**
     * 进入菜谱相关界面的操作意图
     */
    public static String SPEECH_INTENT_COOKBOOK = "speech_intent_cookbook";

    public static String SPEECH_DATA_COOKBOOK_CATEGORY = "speech_data_cookbook_cate";

    public static interface Cookbook {
        /*material	食材
        system	菜系
        dish	菜名
        tag	类别：热门、特色、当季
        season	季节
        flavor	口味
        holiday	节日
        people	人群
        symptom	症状*/

        String MATERIAL = "material";
        String SYSTEM = "system";
        String DISH = "dish";
        String TAG = "tag";
        String SEASON = "season";
        String FLAVOR = "flavor";
        String HOLIDAY = "holiday";
        String PEOPLE = "people";
        String SYMPTOM = "symptom";
    }

    public static interface YoukuVideo {
        String FILM_TYPE = "film_type";
    }

    public static interface Shop {
        String COMMODITY_CATEFORY = "commodity_category";
    }
}
