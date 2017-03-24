package com.example.p010_recycleviewall.shoucang.iview;


import com.example.p010_recycleviewall.shoucang.domain.ShoucangDiantaiIndexBean;
import com.example.p010_recycleviewall.tablayout.fragmentframelayout.mvp.IView;

public interface IDiantaiView extends IView {
    void onGetDiantaiSuccess(ShoucangDiantaiIndexBean data);

    void onGetDiantaiNotok(boolean isok);

    void onGetDiantaiEmpty(String msg);

    void onGetDiantaiFailed(String msg);
}
