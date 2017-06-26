package com.shining.p010_recycleviewall.api;

import com.shining.p010_recycleviewall.domain.PackageOneKeyBuyBeanNewList;

import org.loader.glin.annotation.JSON;
import org.loader.glin.call.Call;

/**
 * Created by qibin on 2016/8/4.
 */

public interface FoodManagerApi {

    @JSON("" + "food/food.category.query")
    Call<PackageOneKeyBuyBeanNewList> food_category_query(String json);


}