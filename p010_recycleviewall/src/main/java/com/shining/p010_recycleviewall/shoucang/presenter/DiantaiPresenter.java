package com.shining.p010_recycleviewall.shoucang.presenter;


import com.shining.p010_recycleviewall.net.Net;
import com.shining.p010_recycleviewall.shoucang.api.ShoucangApi;
import com.shining.p010_recycleviewall.shoucang.domain.ShoucangDiantaiIndexBean;
import com.shining.p010_recycleviewall.shoucang.domain.ShoucangDiantaiIndexModel;
import com.shining.p010_recycleviewall.shoucang.iview.IDiantaiView;
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

public class DiantaiPresenter extends Presenter<IDiantaiView> {

    /**
     * 菜谱首页数据请求bufen
     */
    public void getDiantaiPresenter() {
        IndexFoodParams p = new IndexFoodParams(DataProvider.getFridge_id(), DataProvider.getFamily_id(), DataProvider.getUser_id());
        Net.build(ShoucangApi.class, getClass().getName()).getindex_diantai(ParamsUtils.just(p)).enqueue(new Callback<ShoucangDiantaiIndexBean>() {

            @Override
            public void onResponse(Result<ShoucangDiantaiIndexBean> result) {
                if (!hasView()) {
                    return;
                }
                if (!result.isOK()) {
                    getView().onGetDiantaiNotok(result.isOK());
                    return;
                }
                ShoucangDiantaiIndexBean data = result.getResult();
                if (data == null) {
                    getView().onGetDiantaiFailed("");
                    return;
                }
                List<ShoucangDiantaiIndexModel> list = result.getResult().getListitem();
                if (list == null || list.size() == 0) {
                    getView().onGetDiantaiEmpty("");
                    return;
                }
                getView().onGetDiantaiSuccess(data);

            }
        });
    }
}
