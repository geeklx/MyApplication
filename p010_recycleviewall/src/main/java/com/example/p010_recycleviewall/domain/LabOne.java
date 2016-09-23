package com.example.p010_recycleviewall.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geek on 2016/2/26.
 */
public class LabOne {
    //    /**
//     *
//     */
//    private static final long serialVersionUID = 1L;
    public static LabOne mInstance;

    private List<PackageOneKeyBuyBeanNew> mParent_model = new ArrayList<PackageOneKeyBuyBeanNew>();
    private List<PackageOneKeyBuyBeanNew> mParent_model2 = new ArrayList<PackageOneKeyBuyBeanNew>();
    private List<TagBeanNew> Impressions = new ArrayList<TagBeanNew>();
    private List<Coupon_listBeanNew> coupon_listBeanNew = new ArrayList<Coupon_listBeanNew>();

    private void ListViewLabShouyeLeft12() {
        Impressions.add(new TagBeanNew("1", "农场"));
        Impressions.add(new TagBeanNew("2", "有机"));
        Impressions.add(new TagBeanNew("3", "时令"));
    }

    private void ListViewLabShouyeLeft122() {
        coupon_listBeanNew.add(new Coupon_listBeanNew("#ffffff", "首免", ""));
        coupon_listBeanNew.add(new Coupon_listBeanNew("#FF8A00", "首减", "满100减20"));
//        coupon_listBeanNew.add(new Coupon_listBeanNew("#ffffff", "满减","减减减"));
    }

    public LabOne() {
        ListViewLabShouyeLeft12();
        ListViewLabShouyeLeft122();
        mParent_model.add(new PackageOneKeyBuyBeanNew("1", "1", "1", "1", "1", "1", 0.01, 1, 1, "1", "1", "1", 0.01, "1", 1, "1", null, Impressions, coupon_listBeanNew));
        mParent_model.add(new PackageOneKeyBuyBeanNew("2", "1", "1", "1", "1", "1", 0.01, 1, 1, "1", "1", "1", 0.01, "1", 1, "1", null, Impressions, coupon_listBeanNew));
        mParent_model.add(new PackageOneKeyBuyBeanNew("3", "1", "1", "1", "1", "1", 0.01, 1, 1, "1", "1", "1", 0.01, "1", 1, "1", null, Impressions, coupon_listBeanNew));
        mParent_model.add(new PackageOneKeyBuyBeanNew("4", "1", "1", "1", "1", "1", 0.01, 1, 1, "1", "1", "1", 0.01, "1", 1, "1", null, Impressions, coupon_listBeanNew));
        mParent_model.add(new PackageOneKeyBuyBeanNew("5", "1", "1", "1", "1", "1", 0.01, 1, 1, "1", "1", "1", 0.01, "1", 1, "1", null, Impressions, coupon_listBeanNew));
    }


    public List<PackageOneKeyBuyBeanNew> getmParent_model1() {
        return mParent_model;
    }

//    public static LabOne getmInstance() {
//        if (mInstance == null) {
//            synchronized (LabOne.class) {
//                if (mInstance == null) {
//                    mInstance = new LabOne();
//                }
//            }
//        }
//        return mInstance;
//    }
}
