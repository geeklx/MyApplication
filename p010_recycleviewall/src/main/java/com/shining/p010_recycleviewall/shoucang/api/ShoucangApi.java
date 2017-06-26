package com.shining.p010_recycleviewall.shoucang.api;


import com.shining.p010_recycleviewall.application.ConstantNetUtil;
import com.shining.p010_recycleviewall.shoucang.domain.FmNewFoodDeletefoodModel;
import com.shining.p010_recycleviewall.shoucang.domain.FmNewFoodlistModel;
import com.shining.p010_recycleviewall.shoucang.domain.ShoucangCaipuIndexBean;
import com.shining.p010_recycleviewall.shoucang.domain.ShoucangDiantaiIndexBean;
import com.shining.p010_recycleviewall.shoucang.domain.ShoucangShangpinIndexBean;
import com.shining.p010_recycleviewall.shoucang.domain.ShoucangShipinIndexBean;
import com.shining.p010_recycleviewall.shoucang.domain.ShoucangYinyueIndexBean;

import org.loader.glin.annotation.JSON;
import org.loader.glin.call.Call;

/**
 * Created by Pengliang on 2016/8/16.
 */
public interface ShoucangApi {

    @JSON(ConstantNetUtil.URL_BUSINESS + "")
    Call<ShoucangCaipuIndexBean> getindex_caipu(String json);

    @JSON(ConstantNetUtil.URL_BUSINESS + "")
    Call<ShoucangShipinIndexBean> getindex_shipin(String json);

    @JSON(ConstantNetUtil.URL_BUSINESS + "")
    Call<ShoucangDiantaiIndexBean> getindex_diantai(String json);

    @JSON(ConstantNetUtil.URL_BUSINESS + "")
    Call<ShoucangYinyueIndexBean> getindex_yinyue(String json);

    @JSON(ConstantNetUtil.URL_BUSINESS + "")
    Call<ShoucangShangpinIndexBean> getindex_shangpin(String json);

    //根据新鲜度获取冰箱中的食材列表接口,food_fresh传空串显示所有食材，新鲜度tag 101：新鲜 102:正常 104:快过期 105：已过期
    //TODO 请求model FoodManagerNewFoodlistParams
    @JSON("http://192.168.200.96:8080/v2/" + "food/food.fresh.query")
    Call<FmNewFoodlistModel> fm_get_food_list(String json);

    //冰箱内食材删除
    //TODO 请求model FoodManagerNewDeleteParams
    @JSON("http://192.168.200.96:8080/v2/" + "food/food.todel.batch.delete")
    Call<FmNewFoodDeletefoodModel> fm_deletefood(String json);

}
