package com.shining.p010_recycleviewall.shoucang.domain;

import java.io.Serializable;

/**
 * Created by qibin on 2016/8/4.
 */

public class IndexFoodFragmentUpdateIds implements Serializable {
    private static final long serialVersionUID = 1L;

    private String food_name;
    private String food_definition_id;

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_definition_id() {
        return food_definition_id;
    }

    public void setFood_definition_id(String food_definition_id) {
        this.food_definition_id = food_definition_id;
    }
}
