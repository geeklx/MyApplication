package com.example.p010_recycleviewall.shoucang.domain;

import java.io.Serializable;
import java.util.List;


public class ShoucangShipinIndexBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String num;
    private List<ShoucangShipinIndexModel> listitem;

    public ShoucangShipinIndexBean() {
    }

    public ShoucangShipinIndexBean(String num, List<ShoucangShipinIndexModel> listitem) {
        this.num = num;
        this.listitem = listitem;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public List<ShoucangShipinIndexModel> getListitem() {
        return listitem;
    }

    public void setListitem(List<ShoucangShipinIndexModel> listitem) {
        this.listitem = listitem;
    }
}
