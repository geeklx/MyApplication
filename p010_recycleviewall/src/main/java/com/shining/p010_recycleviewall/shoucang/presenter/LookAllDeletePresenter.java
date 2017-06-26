package com.shining.p010_recycleviewall.shoucang.presenter;


import com.shining.p010_recycleviewall.net.Net;
import com.shining.p010_recycleviewall.shoucang.api.ShoucangApi;
import com.shining.p010_recycleviewall.shoucang.domain.FmNewFoodDeletefoodModel;
import com.shining.p010_recycleviewall.shoucang.iview.ILookAllDeleteView;
import com.shining.p010_recycleviewall.shoucang.params.FoodManagerNewDeleteParams;
import com.shining.p010_recycleviewall.tablayout.fragmentframelayout.mvp.Presenter;
import com.shining.p010_recycleviewall.utils.DataProvider;
import com.shining.p010_recycleviewall.utils.ParamsUtils;

import org.loader.glin.Callback;
import org.loader.glin.Result;

/**
 * 收藏查看全部Demo
 * 2017年3月1日15:18:06
 */

public class LookAllDeletePresenter extends Presenter<ILookAllDeleteView> {

    /**
     * 收藏查看全部Demobufen
     */
    public void getLookAllDeletePresenter(String food_ids) {
        FoodManagerNewDeleteParams p = new FoodManagerNewDeleteParams(DataProvider.getFridge_id(), DataProvider.getUser_id(), food_ids);
        Net.build(ShoucangApi.class, getClass().getName()).fm_deletefood(ParamsUtils.just(p)).enqueue(new Callback<FmNewFoodDeletefoodModel>() {

            @Override
            public void onResponse(Result<FmNewFoodDeletefoodModel> result) {
                if (!hasView()) {
                    return;
                }
                if (!result.isOK()) {
                    getView().onGetLookAllDeleteNotok(result.isOK());
                    return;
                }
                FmNewFoodDeletefoodModel data = result.getResult();
                if (data == null) {
                    getView().onGetLookAllDeleteFailed("");
                    return;
                }
                getView().onGetLookAllDeleteSuccess(data);
            }
        });
    }
}
