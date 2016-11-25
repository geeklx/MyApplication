package com.example.p010_recycleviewall.tablayout.fragmentframelayout;

import com.example.p010_recycleviewall.domain.ShopCategoryItem;
import com.example.p010_recycleviewall.tablayout.fragmentframelayout.mvp.IView;

import java.util.List;

public interface IShopCategoryView extends IView {
    void onGetShopCategory(List<ShopCategoryItem> categories);
}
