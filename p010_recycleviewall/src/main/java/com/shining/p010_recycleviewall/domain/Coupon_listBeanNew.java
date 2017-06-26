package com.shining.p010_recycleviewall.domain;

import java.io.Serializable;

/**
 * Created by geek on 2016/2/25.
 */
public class Coupon_listBeanNew implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String coupon_color;
    private String coupon_str;
    private String coupon_info;

    public Coupon_listBeanNew() {
    }

    public Coupon_listBeanNew(String coupon_color, String coupon_str, String coupon_info) {
        this.coupon_color = coupon_color;
        this.coupon_str = coupon_str;
        this.coupon_info = coupon_info;
    }

    public String getCoupon_color() {
        return coupon_color;
    }

    public void setCoupon_color(String coupon_color) {
        this.coupon_color = coupon_color;
    }

    public String getCoupon_str() {
        return coupon_str;
    }

    public void setCoupon_str(String coupon_str) {
        this.coupon_str = coupon_str;
    }

    public String getCoupon_info() {
        return coupon_info;
    }

    public void setCoupon_info(String coupon_info) {
        this.coupon_info = coupon_info;
    }
}
