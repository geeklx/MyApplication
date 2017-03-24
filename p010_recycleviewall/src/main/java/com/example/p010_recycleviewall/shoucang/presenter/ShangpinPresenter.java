package com.example.p010_recycleviewall.shoucang.presenter;


import com.example.p010_recycleviewall.net.Net;
import com.example.p010_recycleviewall.shoucang.api.ShoucangApi;
import com.example.p010_recycleviewall.shoucang.domain.ShopSkuItem;
import com.example.p010_recycleviewall.shoucang.domain.ShoucangShangpinIndexBean;
import com.example.p010_recycleviewall.shoucang.iview.IShangpinView;
import com.example.p010_recycleviewall.shoucang.params.IndexFoodParams;
import com.example.p010_recycleviewall.tablayout.fragmentframelayout.mvp.Presenter;
import com.example.p010_recycleviewall.utils.ParamsUtils;

import org.loader.glin.Callback;
import org.loader.glin.Result;

import java.util.List;

/**
 * shining
 * 2017年2月28日13:09:33
 */

public class ShangpinPresenter extends Presenter<IShangpinView> {

    /**
     * 菜谱首页数据请求bufen
     */
    public void getShangpinPresenter() {
//        IndexFoodParams p = new IndexFoodParams(DataProvider.getFridge_id(), DataProvider.getFamily_id(), DataProvider.getUser_id());
        IndexFoodParams p = new IndexFoodParams("201607041002250672","201607041002250671", "201605262127400016");
        Net.build(ShoucangApi.class, getClass().getName()).getindex_shangpin(ParamsUtils.just(p)).enqueue(new Callback<ShoucangShangpinIndexBean>() {

            @Override
            public void onResponse(Result<ShoucangShangpinIndexBean> result) {
                if (!hasView()) {
                    return;
                }
                if (!result.isOK()) {
                    getView().onGetShangpinNotok(result.isOK());
                    return;
                }
                ShoucangShangpinIndexBean data = result.getResult();
                if (data == null) {
                    getView().onGetShangpinFailed("");
                    return;
                }
                List<ShopSkuItem> list = result.getResult().getListitem();
                if (list == null || list.size() == 0) {
                    getView().onGetShangpinEmpty("");
                    return;
                }
                getView().onGetShangpinSuccess(data);

            }
        });
    }
}
