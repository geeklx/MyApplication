package com.example.p010_recycleviewall.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geek on 2016/2/26.
 */
public class LabFour {

    //    private static final long serialVersionUID = 1L;
    public static LabFour mInstance;

    private List<TabLayoutcontentBean> mParent_model = new ArrayList<TabLayoutcontentBean>();
    private List<TabLayouttitleBean> Impressions = new ArrayList<TabLayouttitleBean>();

    private void ListViewLabShouyeLeft12() {
        Impressions.add(new TabLayouttitleBean("01", "推荐", "", false, false));
        Impressions.add(new TabLayouttitleBean("02", "电台", "", false, false));
        Impressions.add(new TabLayouttitleBean("03", "资讯", "", false, false));
        Impressions.add(new TabLayouttitleBean("04", "时尚", "", false, false));
        Impressions.add(new TabLayouttitleBean("05", "娱乐", "", false, false));
        Impressions.add(new TabLayouttitleBean("06", "脱口秀", "", false, false));
        Impressions.add(new TabLayouttitleBean("07", "英语", "", false, false));
        Impressions.add(new TabLayouttitleBean("08", "旅游", "", false, false));
        Impressions.add(new TabLayouttitleBean("09", "名校公开课", "", false, false));
        Impressions.add(new TabLayouttitleBean("10", "儿童", "", false, false));
        Impressions.add(new TabLayouttitleBean("11", "有声书", "", false, false));
        Impressions.add(new TabLayouttitleBean("12", "百家讲坛", "", false, false));
        Impressions.add(new TabLayouttitleBean("13", "相声", "", false, false));
        Impressions.add(new TabLayouttitleBean("14", "戏曲", "", false, false));
        Impressions.add(new TabLayouttitleBean("15", "养生", "", false, false));
    }

    public LabFour() {
        ListViewLabShouyeLeft12();
        mParent_model.add(new TabLayoutcontentBean("推荐bufen"));
        mParent_model.add(new TabLayoutcontentBean("电台bufen"));
        mParent_model.add(new TabLayoutcontentBean("资讯bufen"));
        mParent_model.add(new TabLayoutcontentBean("时尚bufen"));
        mParent_model.add(new TabLayoutcontentBean("娱乐bufen"));
        mParent_model.add(new TabLayoutcontentBean("脱口秀bufen"));
        mParent_model.add(new TabLayoutcontentBean("英语bufen"));
        mParent_model.add(new TabLayoutcontentBean("旅游bufen"));
        mParent_model.add(new TabLayoutcontentBean("名校公开课bufen"));
        mParent_model.add(new TabLayoutcontentBean("儿童bufen"));
        mParent_model.add(new TabLayoutcontentBean("有声书bufen"));
        mParent_model.add(new TabLayoutcontentBean("百家讲坛bufen"));
        mParent_model.add(new TabLayoutcontentBean("相声bufen"));
        mParent_model.add(new TabLayoutcontentBean("戏曲bufen"));
        mParent_model.add(new TabLayoutcontentBean("养生bufen"));
    }

    public List<TabLayoutcontentBean> getmParent_model() {
        return mParent_model;
    }

    public List<TabLayouttitleBean> getImpressions() {
        return Impressions;
    }

    //    public static LabTwo getmInstance() {
//        if (mInstance == null) {
//            synchronized (LabTwo.class) {
//                if (mInstance == null) {
//                    mInstance = new LabTwo();
//                }
//            }
//        }
//        return mInstance;
//    }
}
