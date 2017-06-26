package com.shining.p010_recycleviewall.shoucang.iview;


import com.shining.p010_recycleviewall.shoucang.domain.FmNewFoodDeletefoodModel;
import com.shining.p010_recycleviewall.tablayout.fragmentframelayout.mvp.IView;

public interface ILookAllDeleteView extends IView {
    void onGetLookAllDeleteSuccess(FmNewFoodDeletefoodModel data);
    void onGetLookAllDeleteNotok(boolean isok);
    void onGetLookAllDeleteEmpty(String msg);
    void onGetLookAllDeleteFailed(String msg);
}
