package com.shining.p010_recycleviewall.shoucang.domain;

import java.io.Serializable;
import java.util.List;


public class ShoucangCaipuIndexBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String num;
    private List<ShoucangCaipuIndexModel> listitem;

    public ShoucangCaipuIndexBean() {
    }

    public ShoucangCaipuIndexBean(String num, List<ShoucangCaipuIndexModel> listitem) {
        this.num = num;
        this.listitem = listitem;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public List<ShoucangCaipuIndexModel> getListitem() {
        return listitem;
    }

    public void setListitem(List<ShoucangCaipuIndexModel> listitem) {
        this.listitem = listitem;
    }
}
