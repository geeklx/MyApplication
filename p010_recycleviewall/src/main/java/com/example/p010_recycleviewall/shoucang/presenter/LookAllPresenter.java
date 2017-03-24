package com.example.p010_recycleviewall.shoucang.presenter;


import com.example.p010_recycleviewall.net.Net;
import com.example.p010_recycleviewall.shoucang.api.ShoucangApi;
import com.example.p010_recycleviewall.shoucang.domain.FmNewFoodItemBean;
import com.example.p010_recycleviewall.shoucang.domain.FmNewFoodlistModel;
import com.example.p010_recycleviewall.shoucang.iview.ILookAllView;
import com.example.p010_recycleviewall.shoucang.params.FoodManagerNewFoodlistParams;
import com.example.p010_recycleviewall.tablayout.fragmentframelayout.mvp.Presenter;
import com.example.p010_recycleviewall.utils.DataProvider;
import com.example.p010_recycleviewall.utils.ParamsUtils;

import org.loader.glin.Callback;
import org.loader.glin.Result;

import java.util.List;

/**
 * 收藏查看全部Demo
 * 2017年3月1日15:18:06
 */

public class LookAllPresenter extends Presenter<ILookAllView> {

    /**
     * 收藏查看全部Demobufen
     */
    public void getLookAllPresenter() {
        FoodManagerNewFoodlistParams p = new FoodManagerNewFoodlistParams(DataProvider.getFridge_id(), DataProvider.getUser_id(), "", "-1");
        Net.build(ShoucangApi.class, getClass().getName()).fm_get_food_list(ParamsUtils.just(p)).enqueue(new Callback<FmNewFoodlistModel>() {

            @Override
            public void onResponse(Result<FmNewFoodlistModel> result) {
                if (!hasView()) {
                    return;
                }
                if (!result.isOK()) {
                    getView().onGetLookAllNotok(result.isOK());
                    return;
                }
                FmNewFoodlistModel data = result.getResult();
                if (data == null) {
                    getView().onGetLookAllFailed("");
                    return;
                }
                List<FmNewFoodItemBean> list = result.getResult().getFood_list();
                if (list == null || list.size() == 0) {
                    getView().onGetLookAllEmpty("");
                    return;
                }
                getView().onGetLookAllSuccess(data);
            }
        });
    }
}
