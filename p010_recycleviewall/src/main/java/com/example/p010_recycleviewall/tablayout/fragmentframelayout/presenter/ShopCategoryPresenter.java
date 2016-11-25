package com.example.p010_recycleviewall.tablayout.fragmentframelayout.presenter;

import com.example.p010_recycleviewall.domain.LabFive;
import com.example.p010_recycleviewall.domain.ShopCategories;
import com.example.p010_recycleviewall.domain.ShopCategoryItem;
import com.example.p010_recycleviewall.tablayout.fragmentframelayout.IShopCategoryView;
import com.example.p010_recycleviewall.tablayout.fragmentframelayout.mvp.Presenter;

import java.util.ArrayList;
import java.util.List;

public class ShopCategoryPresenter extends Presenter<IShopCategoryView> {


    public void getCategories() {
        //假数据bufen
        LabFive lf = new LabFive();
        ShopCategories shopCategories = lf.getmParent_model();
        List<ShopCategoryItem> list = shopCategories.getCategory2_list();
        if (list == null) {
            list = new ArrayList<ShopCategoryItem>();
            shopCategories.setCategory2_list(list);
        }

        list.add(0, buildFixedCate());
        getView().onGetShopCategory(list);
    }

    /**
     * 创建固定的类目（本地创建“推荐”）
     *
     * @return
     */
    private ShopCategoryItem buildFixedCate() {
        ShopCategoryItem item = new ShopCategoryItem();
        item.setCategory2_name(ShopCategoryItem.DEF_TAG_NAME);
        item.setCategory2_id(ShopCategoryItem.DEF_TAG_ID);
        return item;
    }
}
