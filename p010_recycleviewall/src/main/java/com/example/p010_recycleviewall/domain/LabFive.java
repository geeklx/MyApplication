package com.example.p010_recycleviewall.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by geek on 2016/2/26.
 */
public class LabFive {

    //    private static final long serialVersionUID = 1L;
    public static LabFive mInstance;

    private ShopCategories mParent_model = new ShopCategories();
    private List<ShopCategoryItem> mParent_model_item = new ArrayList<ShopCategoryItem>();

    public void LabFive_item() {
//        mParent_model_item.add(new ShopCategoryItem("-1","推荐bufen"));
        mParent_model_item.add(new ShopCategoryItem("1","电台bufen"));
        mParent_model_item.add(new ShopCategoryItem("2","资讯bufen"));
        mParent_model_item.add(new ShopCategoryItem("3","时尚bufen"));
        mParent_model_item.add(new ShopCategoryItem("4","娱乐bufen"));
        mParent_model_item.add(new ShopCategoryItem("5","脱口秀bufen"));
        mParent_model_item.add(new ShopCategoryItem("6","英语bufen"));
        mParent_model_item.add(new ShopCategoryItem("7","旅游bufen"));
        mParent_model_item.add(new ShopCategoryItem("8","名校公开课bufen"));
        mParent_model_item.add(new ShopCategoryItem("9","儿童bufen"));
        mParent_model_item.add(new ShopCategoryItem("10","有声书bufen"));
        mParent_model_item.add(new ShopCategoryItem("11","百家讲坛bufen"));
        mParent_model_item.add(new ShopCategoryItem("12","相声bufen"));
        mParent_model_item.add(new ShopCategoryItem("13","戏曲bufen"));
        mParent_model_item.add(new ShopCategoryItem("14","养生bufen"));
    }
    public LabFive() {
        LabFive_item();
        mParent_model.setCategory2_list(mParent_model_item);
    }

    public ShopCategories getmParent_model() {
        return mParent_model;
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
