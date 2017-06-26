package com.shining.p010_recycleviewall.tablayout.fragmentframelayout;

import com.shining.p010_recycleviewall.domain.ShopCategoryItem;
import com.shining.p010_recycleviewall.tablayout.fragmentframelayout.mvp.IView;

import java.util.List;

public interface IShopCategoryView extends IView {
    void onGetShopCategory(List<ShopCategoryItem> categories);
}
