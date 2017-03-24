package com.example.p010_recycleviewall.shoucang.presenter;


import com.example.p010_recycleviewall.net.Net;
import com.example.p010_recycleviewall.shoucang.api.ShoucangApi;
import com.example.p010_recycleviewall.shoucang.domain.ShoucangYinyueIndexBean;
import com.example.p010_recycleviewall.shoucang.domain.ShoucangYinyueIndexModel;
import com.example.p010_recycleviewall.shoucang.iview.IYinyueView;
import com.example.p010_recycleviewall.shoucang.params.IndexFoodParams;
import com.example.p010_recycleviewall.tablayout.fragmentframelayout.mvp.Presenter;
import com.example.p010_recycleviewall.utils.DataProvider;
import com.example.p010_recycleviewall.utils.ParamsUtils;

import org.loader.glin.Callback;
import org.loader.glin.Result;

import java.util.List;

/**
 * shining
 * 2017年2月28日13:09:33
 */

public class YinyuePresenter extends Presenter<IYinyueView> {

    /**
     * 菜谱首页数据请求bufen
     */
    public void getYinyuePresenter() {
        IndexFoodParams p = new IndexFoodParams(DataProvider.getFridge_id(), DataProvider.getFamily_id(), DataProvider.getUser_id());
        Net.build(ShoucangApi.class, getClass().getName()).getindex_yinyue(ParamsUtils.just(p)).enqueue(new Callback<ShoucangYinyueIndexBean>() {

            @Override
            public void onResponse(Result<ShoucangYinyueIndexBean> result) {
                if (!hasView()) {
                    return;
                }
                if (!result.isOK()) {
                    getView().onGetYinyueNotok(result.isOK());
                    return;
                }
                ShoucangYinyueIndexBean data = result.getResult();
                if (data == null) {
                    getView().onGetYinyueFailed("");
                    return;
                }
                List<ShoucangYinyueIndexModel> list = result.getResult().getListitem();
                if (list == null || list.size() == 0) {
                    getView().onGetYinyueEmpty("");
                    return;
                }
                getView().onGetYinyueSuccess(data);

            }
        });
    }
}
