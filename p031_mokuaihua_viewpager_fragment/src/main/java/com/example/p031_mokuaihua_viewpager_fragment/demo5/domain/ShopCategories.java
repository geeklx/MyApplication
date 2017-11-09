package com.example.p031_mokuaihua_viewpager_fragment.demo5.domain;

import java.io.Serializable;
import java.util.List;

public class ShopCategories implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private List<ShopCategoryItem> category2_list;

    public List<ShopCategoryItem> getCategory2_list() {
        return category2_list;
    }

    public void setCategory2_list(List<ShopCategoryItem> category2_list) {
        this.category2_list = category2_list;
    }
}
