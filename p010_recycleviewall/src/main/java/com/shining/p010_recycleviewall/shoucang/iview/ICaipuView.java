package com.shining.p010_recycleviewall.shoucang.iview;


import com.shining.p010_recycleviewall.shoucang.domain.ShoucangCaipuIndexBean;
import com.shining.p010_recycleviewall.tablayout.fragmentframelayout.mvp.IView;

public interface ICaipuView extends IView {
    void onGetCaipuSuccess(ShoucangCaipuIndexBean data);

    void onGetCaipuNotok(boolean isok);

    void onGetCaipuEmpty(String msg);

    void onGetCaipuFailed(String msg);
}
