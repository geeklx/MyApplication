/**
 * Copyright 2016,Smart Haier.All rights reserved
 * Description:
 * Author:jiayuzhen
 * ModifyBy:
 * ModifyDate:
 * ModifyDes:
 */
package com.haiersmart.voice.bean.foodRec;

import java.util.List;

/**
 * Created by yuzhen on 2017/2/20.
 *@time 2017/2/20  10:34
 */
public class BaiduFoodRecipe {
    //食材名称
    public String foodName;
    //食材图片地址
    public String foodImgUrl;
    //食材好处
    public String foodBenefit;
    //选购贴士
    public String foodBuyHelp;
    //食材说明
    public String foodDesc;
    //关联菜谱
    public List<BaiduRecipe> baiduRecipe;

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodImgUrl() {
        return foodImgUrl;
    }

    public void setFoodImgUrl(String foodImgUrl) {
        this.foodImgUrl = foodImgUrl;
    }

    public String getFoodBenefit() {
        return foodBenefit;
    }

    public void setFoodBenefit(String foodBenefit) {
        this.foodBenefit = foodBenefit;
    }

    public String getFoodBuyHelp() {
        return foodBuyHelp;
    }

    public void setFoodBuyHelp(String foodBuyHelp) {
        this.foodBuyHelp = foodBuyHelp;
    }

    public String getFoodDesc() {
        return foodDesc;
    }

    public void setFoodDesc(String foodDesc) {
        this.foodDesc = foodDesc;
    }

    public List<BaiduRecipe> getBaiduRecipe() {
        return baiduRecipe;
    }

    public void setBaiduRecipe(List<BaiduRecipe> baiduRecipe) {
        this.baiduRecipe = baiduRecipe;
    }
}
