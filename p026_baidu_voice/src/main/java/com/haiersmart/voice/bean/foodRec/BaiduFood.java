/**
 * Copyright 2016,Smart Haier.All rights reserved
 * Description:
 * Author:jiayuzhen
 * ModifyBy:
 * ModifyDate:
 * ModifyDes:
 */
package com.haiersmart.voice.bean.foodRec;

/**
 * Created by yuzhen on 2017/2/17.
 *@time 2017/2/17  15:14
 */
public class BaiduFood {
    public String name;
    public String probability;

    public BaiduFood(String name, String probability) {
        this.name = name;
        this.probability = probability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }
}
