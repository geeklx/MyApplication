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
 * Created by yuzhen on 2017/2/20.
 *@time 2017/2/20  10:38
 */
public class BaiduRecipe {
    //菜谱图片
    public String recipeImgUrl;
    //菜谱名称
    public String recipeName;

    public BaiduRecipe(){

    }
    public BaiduRecipe(String recipeImgUrl,String recipeName){
        this.recipeImgUrl = recipeImgUrl;
        this.recipeName = recipeName;
    }

    public String getRecipeImgUrl() {
        return recipeImgUrl;
    }

    public void setRecipeImgUrl(String recipeImgUrl) {
        this.recipeImgUrl = recipeImgUrl;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }
}
