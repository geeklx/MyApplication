package com.shining.p010_recycleviewall.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by geek on 2016/2/25.
 */
public class Scroll_list implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private List<Scroll_listBean> listBean;

    public Scroll_list() {
    }

    public Scroll_list(List<Scroll_listBean> listBean) {
        this.listBean = listBean;
    }

    public List<Scroll_listBean> getListBean() {
        return listBean;
    }

    public void setListBean(List<Scroll_listBean> listBean) {
        this.listBean = listBean;
    }
}
