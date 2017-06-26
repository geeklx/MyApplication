package com.shining.p010_recycleviewall.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geek on 2016/2/26.
 */
public class LabTwo {
    //    /**
//     *
//     */
//    private static final long serialVersionUID = 1L;
    public static LabTwo mInstance;

    private List<PackageOneKeyBuyBeanNew> mParent_model2 = new ArrayList<PackageOneKeyBuyBeanNew>();
    private List<TagBeanNew> Impressions = new ArrayList<TagBeanNew>();
    private List<Coupon_listBeanNew> coupon_listBeanNew = new ArrayList<Coupon_listBeanNew>();

    private void ListViewLabShouyeLeft12() {
        Impressions.add(new TagBeanNew("1", "农场"));
        Impressions.add(new TagBeanNew("2", "有机"));
        Impressions.add(new TagBeanNew("3", "时令"));
    }
    private void ListViewLabShouyeLeft122() {
        coupon_listBeanNew.add(new Coupon_listBeanNew("#ffffff", "首免",""));
        coupon_listBeanNew.add(new Coupon_listBeanNew("#FF8A00", "首减","满100减20"));
//        coupon_listBeanNew.add(new Coupon_listBeanNew("#ffffff", "满减","减减减"));
    }
    public LabTwo() {
        ListViewLabShouyeLeft12();
        ListViewLabShouyeLeft122();
        mParent_model2.add(new PackageOneKeyBuyBeanNew("6", "1", "1", "1", "1", "1", 0.01, 1, 1, "1", "1", "1", 0.01, "1", 1, "1", null, Impressions, coupon_listBeanNew));
        mParent_model2.add(new PackageOneKeyBuyBeanNew("7", "1", "1", "1", "1", "1", 0.01, 1, 1, "1", "1", "1", 0.01, "1", 1, "1", null, Impressions, coupon_listBeanNew));
        mParent_model2.add(new PackageOneKeyBuyBeanNew("8", "1", "1", "1", "1", "1", 0.01, 1, 1, "1", "1", "1", 0.01, "1", 1, "1", null, Impressions, coupon_listBeanNew));
    }

    public List<PackageOneKeyBuyBeanNew> getmParent_model2() {
        return mParent_model2;
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
