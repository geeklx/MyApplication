package com.example.p010_recycleviewall.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geek on 2016/2/26.
 */
public class LabThree {
    //    /**
//     *
//     */
//    private static final long serialVersionUID = 1L;
//    public static LabThree mInstance;

    private List<PackageOneKeyBuyBeanNew> mParent_model = new ArrayList<PackageOneKeyBuyBeanNew>();
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

    public LabThree() {
        ListViewLabShouyeLeft12();
        ListViewLabShouyeLeft122();
        mParent_model.add(new PackageOneKeyBuyBeanNew("1", "1", "1", "1", "1", "1", 0.01, 1, 1, "1", "1", "1", 0.01, "1", 1, "1", null, Impressions, coupon_listBeanNew));
        mParent_model.add(new PackageOneKeyBuyBeanNew("2", "2", "2", "2", "2", "2", 0.02, 2, 2, "2", "2", "2", 0.02, "2", 2, "2", null, Impressions, coupon_listBeanNew));
        mParent_model.add(new PackageOneKeyBuyBeanNew("3", "3", "3", "3", "3", "3", 0.03, 3, 3, "3", "3", "3", 0.03, "3", 3, "3", null, Impressions, coupon_listBeanNew));
        mParent_model.add(new PackageOneKeyBuyBeanNew("4", "4", "4", "4", "4", "4", 0.04, 4, 4, "4", "4", "4", 0.04, "4", 4, "4", null, Impressions, coupon_listBeanNew));
        mParent_model.add(new PackageOneKeyBuyBeanNew("5", "5", "5", "5", "5", "5", 0.05, 5, 5, "5", "5", "5", 0.05, "5", 5, "5", null, Impressions, coupon_listBeanNew));
    }


    public List<PackageOneKeyBuyBeanNew> getmParent_model1() {
        return mParent_model;
    }

//    public static LabThree getmInstance() {
//        if (mInstance == null) {
//            synchronized (LabThree.class) {
//                if (mInstance == null) {
//                    mInstance = new LabThree();
//                }
//            }
//        }
//        return mInstance;
//    }
}
