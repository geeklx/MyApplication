package com.shining.p010_recycleviewall.shoucang.params;


import java.io.Serializable;

/**
 * Created by geek 2016年11月28日19:24:29
 */
public class FoodManagerNewDeleteParams implements Serializable {
    private static final long serialVersionUID = 1L;
    private String fridge_id ;
    private String user_id;
    private String fridge_food_ids;

    public FoodManagerNewDeleteParams() {
    }

    public FoodManagerNewDeleteParams(String fridge_id, String user_id, String fridge_food_ids) {
        this.fridge_id = fridge_id;
        this.user_id = user_id;
        this.fridge_food_ids = fridge_food_ids;
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

    public String getFridge_food_ids() {
        return fridge_food_ids;
    }

    public void setFridge_food_ids(String fridge_food_ids) {
        this.fridge_food_ids = fridge_food_ids;
    }
}
