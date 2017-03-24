package com.example.p010_recycleviewall.shoucang.params;

import java.io.Serializable;

/**
 * Created by qibin on 2016/7/22.
 */

public class IndexFoodParams implements Serializable {
    private static final long serialVersionUID = 1L;

    private String fridge_id;
    private String family_id;
    private String user_id;


    public IndexFoodParams() {
    }

    public IndexFoodParams(String fridge_id, String family_id, String user_id) {
        this.fridge_id = fridge_id;
        this.family_id = family_id;
        this.user_id = user_id;
    }

    public String getFridge_id() {
        return fridge_id;
    }

    public void setFridge_id(String fridge_id) {
        this.fridge_id = fridge_id;
    }

    public String getFamily_id() {
        return family_id;
    }

    public void setFamily_id(String family_id) {
        this.family_id = family_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
