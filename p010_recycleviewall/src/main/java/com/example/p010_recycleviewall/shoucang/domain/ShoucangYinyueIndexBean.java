package com.example.p010_recycleviewall.shoucang.domain;

import java.io.Serializable;
import java.util.List;


public class ShoucangYinyueIndexBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String num;
    private List<ShoucangYinyueIndexModel> listitem;

    public ShoucangYinyueIndexBean() {
    }

    public ShoucangYinyueIndexBean(String num, List<ShoucangYinyueIndexModel> listitem) {
        this.num = num;
        this.listitem = listitem;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public List<ShoucangYinyueIndexModel> getListitem() {
        return listitem;
    }

    public void setListitem(List<ShoucangYinyueIndexModel> listitem) {
        this.listitem = listitem;
    }
}
