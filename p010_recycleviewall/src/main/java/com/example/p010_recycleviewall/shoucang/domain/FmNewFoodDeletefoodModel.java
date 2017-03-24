package com.example.p010_recycleviewall.shoucang.domain;

import java.io.Serializable;

public class FmNewFoodDeletefoodModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String  fridge_have_food;

    public FmNewFoodDeletefoodModel() {
    }

    public FmNewFoodDeletefoodModel(String fridge_have_food) {
        this.fridge_have_food = fridge_have_food;
    }

    public String getFridge_have_food() {
        return fridge_have_food;
    }

    public void setFridge_have_food(String fridge_have_food) {
        this.fridge_have_food = fridge_have_food;
    }
}
