package com.example.p010_recycleviewall.shoucang.domain;

import java.io.Serializable;
import java.util.List;


public class ShoucangDiantaiIndexBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String num;
    private List<ShoucangDiantaiIndexModel> listitem;

    public ShoucangDiantaiIndexBean() {
    }

    public ShoucangDiantaiIndexBean(String num, List<ShoucangDiantaiIndexModel> listitem) {
        this.num = num;
        this.listitem = listitem;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public List<ShoucangDiantaiIndexModel> getListitem() {
        return listitem;
    }

    public void setListitem(List<ShoucangDiantaiIndexModel> listitem) {
        this.listitem = listitem;
    }
}
