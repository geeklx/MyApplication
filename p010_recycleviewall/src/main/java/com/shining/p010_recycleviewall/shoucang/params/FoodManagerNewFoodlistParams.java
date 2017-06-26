package com.shining.p010_recycleviewall.shoucang.params;


import java.io.Serializable;

/**
 * Created by geek 2016年11月28日19:24:29
 */
public class FoodManagerNewFoodlistParams implements Serializable {
    private static final long serialVersionUID = 1L;
    private String fridge_id;
    private String user_id;
    private String food_fresh;
    private String fridge_cabin;


    public FoodManagerNewFoodlistParams() {
    }

    public FoodManagerNewFoodlistParams(String fridge_id, String user_id, String food_fresh, String fridge_cabin) {
        this.fridge_id = fridge_id;
        this.user_id = user_id;
        this.food_fresh = food_fresh;
        this.fridge_cabin = fridge_cabin;
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

    public String getFood_fresh() {
        return food_fresh;
    }

    public void setFood_fresh(String food_fresh) {
        this.food_fresh = food_fresh;
    }

    public String getFridge_cabin() {
        return fridge_cabin;
    }

    public void setFridge_cabin(String fridge_cabin) {
        this.fridge_cabin = fridge_cabin;
    }
}
