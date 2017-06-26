package com.shining.p010_recycleviewall.domain;

import java.io.Serializable;

/**
 * Created by qibin on 2016/7/22.
 */

public class FoodmanagerrightgetParams implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fridge_id;
    private String user_id;
    private int current_page;
    private int page_size;
    private String food_category_id;

    public FoodmanagerrightgetParams() {
    }

    public FoodmanagerrightgetParams(String fridge_id, String user_id, int current_page, int page_size, String food_category_id) {
        this.fridge_id = fridge_id;
        this.user_id = user_id;
        this.current_page = current_page;
        this.page_size = page_size;
        this.food_category_id = food_category_id;
    }

    public String getFridge_id() {
        return fridge_id;
    }

    public void setFridge_id(String fridge_id) {
        this.fridge_id = fridge_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }

    public String getFood_category_id() {
        return food_category_id;
    }

    public void setFood_category_id(String food_category_id) {
        this.food_category_id = food_category_id;
    }
}
