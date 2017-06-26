package com.shining.p010_recycleviewall.shoucang.presenter;


import com.shining.p010_recycleviewall.net.Net;
import com.shining.p010_recycleviewall.shoucang.api.ShoucangApi;
import com.shining.p010_recycleviewall.shoucang.domain.ShoucangCaipuIndexBean;
import com.shining.p010_recycleviewall.shoucang.domain.ShoucangCaipuIndexModel;
import com.shining.p010_recycleviewall.shoucang.iview.ICaipuView;
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

public class CaipuPresenter extends Presenter<ICaipuView> {

    /**
     * 菜谱首页数据请求bufen
     */
    public void getCaipuPresenter() {
        IndexFoodParams p = new IndexFoodParams(DataProvider.getFridge_id(), DataProvider.getFamily_id(), DataProvider.getUser_id());
        Net.build(ShoucangApi.class, getClass().getName()).getindex_caipu(ParamsUtils.just(p)).enqueue(new Callback<ShoucangCaipuIndexBean>() {

            @Override
            public void onResponse(Result<ShoucangCaipuIndexBean> result) {
                if (!hasView()) {
                    return;
                }
                if (!result.isOK()) {
                    getView().onGetCaipuNotok(result.isOK());
                    return;
                }
                ShoucangCaipuIndexBean data = result.getResult();
                if (data == null) {
                    getView().onGetCaipuFailed("");
                    return;
                }
                List<ShoucangCaipuIndexModel> list = result.getResult().getListitem();
                if (list == null || list.size() == 0) {
                    getView().onGetCaipuEmpty("");
                    return;
                }
                getView().onGetCaipuSuccess(data);

            }
        });
    }
}
