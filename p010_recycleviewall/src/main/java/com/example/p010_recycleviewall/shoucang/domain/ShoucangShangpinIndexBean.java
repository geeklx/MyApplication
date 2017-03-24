package com.example.p010_recycleviewall.shoucang.domain;

import java.io.Serializable;
import java.util.List;


public class ShoucangShangpinIndexBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String num;
    private List<ShopSkuItem> listitem;

    public ShoucangShangpinIndexBean() {
    }

    public ShoucangShangpinIndexBean(String num, List<ShopSkuItem> listitem) {
        this.num = num;
        this.listitem = listitem;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public List<ShopSkuItem> getListitem() {
        return listitem;
    }

    public void setListitem(List<ShopSkuItem> listitem) {
        this.listitem = listitem;
    }
}
