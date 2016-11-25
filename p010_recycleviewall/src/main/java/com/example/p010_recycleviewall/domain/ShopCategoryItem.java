package com.example.p010_recycleviewall.domain;

public class ShopCategoryItem {

    public static final String DEF_TAG_NAME = "推荐";
    public static final String DEF_TAG_ID = "-1";


    /**
     * category2_id : 2
     * category2_name : 新鲜果蔬
     * category3_list : [{"category3_id":"54","category3_name":"莲藕"},{"category3_id":"55","category3_name":"胡萝卜"}]
     */

    private String category2_id;
    private String category2_name;

    public ShopCategoryItem() {
    }

    public ShopCategoryItem(String category2_id, String category2_name) {
        this.category2_id = category2_id;
        this.category2_name = category2_name;
    }

    public String getCategory2_id() {
        return category2_id;
    }

    public void setCategory2_id(String category2_id) {
        this.category2_id = category2_id;
    }

    public String getCategory2_name() {
        return category2_name;
    }

    public void setCategory2_name(String category2_name) {
        this.category2_name = category2_name;
    }

}
