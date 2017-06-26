package com.shining.p010_recycleviewall.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by geek on 2016/2/25.
 */
public class Biaoge_list implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private List<Biaoge_listBean> listBean;

    public Biaoge_list() {
    }

    public Biaoge_list(List<Biaoge_listBean> listBean) {
        this.listBean = listBean;
    }

    public List<Biaoge_listBean> getListBean() {
        return listBean;
    }

    public void setListBean(List<Biaoge_listBean> listBean) {
        this.listBean = listBean;
    }
}
