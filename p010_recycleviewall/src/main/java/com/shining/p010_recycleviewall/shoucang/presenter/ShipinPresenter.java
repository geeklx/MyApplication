package com.shining.p010_recycleviewall.shoucang.presenter;


import com.shining.p010_recycleviewall.net.Net;
import com.shining.p010_recycleviewall.shoucang.api.ShoucangApi;
import com.shining.p010_recycleviewall.shoucang.domain.ShoucangShipinIndexBean;
import com.shining.p010_recycleviewall.shoucang.domain.ShoucangShipinIndexModel;
import com.shining.p010_recycleviewall.shoucang.iview.IShipinView;
import com.shining.p010_recycleviewall.shoucang.params.IndexFoodParams;
import com.shining.p010_recycleviewall.tablayout.fragmentframelayout.mvp.Presenter;
import com.shining.p010_recycleviewall.utils.DataProvider;
import com.shining.p010_recycleviewall.utils.ParamsUtils;

import org.loader.glin.Callback;
import org.loader.glin.Result;

import java.util.List;

/**
 * shining
 * 2017年2月28日13:09:33
 */

public class ShipinPresenter extends Presenter<IShipinView> {

    /**
     * 菜谱首页数据请求bufen
     */
    public void getShipinPresenter() {
        IndexFoodParams p = new IndexFoodParams(DataProvider.getFridge_id(), DataProvider.getFamily_id(), DataProvider.getUser_id());
        Net.build(ShoucangApi.class, getClass().getName()).getindex_shipin(ParamsUtils.just(p)).enqueue(new Callback<ShoucangShipinIndexBean>() {

            @Override
            public void onResponse(Result<ShoucangShipinIndexBean> result) {
                if (!hasView()) {
                    return;
                }
                if (!result.isOK()) {
                    getView().onGetShipinNotok(result.isOK());
                    return;
                }
                ShoucangShipinIndexBean data = result.getResult();
                if (data == null) {
                    getView().onGetShipinFailed("");
                    return;
                }
                List<ShoucangShipinIndexModel> list = result.getResult().getListitem();
                if (list == null || list.size() == 0) {
                    getView().onGetShipinEmpty("");
                    return;
                }
                getView().onGetShipinSuccess(data);

            }
        });
    }
}
