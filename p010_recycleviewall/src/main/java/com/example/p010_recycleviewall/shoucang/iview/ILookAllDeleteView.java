package com.example.p010_recycleviewall.shoucang.iview;


import com.example.p010_recycleviewall.shoucang.domain.FmNewFoodDeletefoodModel;
import com.example.p010_recycleviewall.tablayout.fragmentframelayout.mvp.IView;

public interface ILookAllDeleteView extends IView {
    void onGetLookAllDeleteSuccess(FmNewFoodDeletefoodModel data);
    void onGetLookAllDeleteNotok(boolean isok);
    void onGetLookAllDeleteEmpty(String msg);
    void onGetLookAllDeleteFailed(String msg);
}
