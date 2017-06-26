package com.shining.p010_recycleviewall.shoucang.domain;


import java.io.Serializable;
import java.util.List;

/**
 * Created by geek 2016年11月28日19:24:29
 */
public class FmNewFoodlistModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private FmNewFoodItemBean food_top;
    private List<FmNewFoodItemBean> food_list;

    public FmNewFoodlistModel() {
    }

    public FmNewFoodlistModel(FmNewFoodItemBean food_top, List<FmNewFoodItemBean> food_list) {
        this.food_top = food_top;
        this.food_list = food_list;
    }

    public FmNewFoodItemBean getFood_top() {
        return food_top;
    }

    public void setFood_top(FmNewFoodItemBean food_top) {
        this.food_top = food_top;
    }

    public List<FmNewFoodItemBean> getFood_list() {
        return food_list;
    }

    public void setFood_list(List<FmNewFoodItemBean> food_list) {
        this.food_list = food_list;
    }
}
