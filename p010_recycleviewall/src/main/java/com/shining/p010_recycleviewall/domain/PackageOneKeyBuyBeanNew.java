package com.shining.p010_recycleviewall.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by geek on 2016/4/6.
 */
public class PackageOneKeyBuyBeanNew implements Serializable {
    private static final long serialVersionUID = 1L;

    private String goods_id;
    private String sku_id;
    private String sku_category_id;
    private String sku_title;
    private String sku_notice;
    private String sku_spec;
    private double sku_price;
    private int sku_check;
    private int sku_count;
    private String sku_image;
    private String sku_pki;
    private String speed;
    private double starts;
    private String deliver;
    private int collect_state;
    private String buy_count;
    private ShopInfoBeanNew shop_info;
    private List<TagBeanNew> tag_list;
    private List<Coupon_listBeanNew> coupon_list;

    public PackageOneKeyBuyBeanNew() {
    }

    public PackageOneKeyBuyBeanNew(String goods_id, String sku_id, String sku_category_id, String sku_title, String sku_notice, String sku_spec, double sku_price, int sku_check, int sku_count, String sku_image, String sku_pki, String speed, double starts, String deliver, int collect_state, String buy_count, ShopInfoBeanNew shop_info, List<TagBeanNew> tag_list, List<Coupon_listBeanNew> coupon_list) {
        this.goods_id = goods_id;
        this.sku_id = sku_id;
        this.sku_category_id = sku_category_id;
        this.sku_title = sku_title;
        this.sku_notice = sku_notice;
        this.sku_spec = sku_spec;
        this.sku_price = sku_price;
        this.sku_check = sku_check;
        this.sku_count = sku_count;
        this.sku_image = sku_image;
        this.sku_pki = sku_pki;
        this.speed = speed;
        this.starts = starts;
        this.deliver = deliver;
        this.collect_state = collect_state;
        this.buy_count = buy_count;
        this.shop_info = shop_info;
        this.tag_list = tag_list;
        this.coupon_list = coupon_list;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public String getSku_category_id() {
        return sku_category_id;
    }

    public void setSku_category_id(String sku_category_id) {
        this.sku_category_id = sku_category_id;
    }

    public String getSku_title() {
        return sku_title;
    }

    public void setSku_title(String sku_title) {
        this.sku_title = sku_title;
    }

    public String getSku_notice() {
        return sku_notice;
    }

    public void setSku_notice(String sku_notice) {
        this.sku_notice = sku_notice;
    }

    public String getSku_spec() {
        return sku_spec;
    }

    public void setSku_spec(String sku_spec) {
        this.sku_spec = sku_spec;
    }

    public double getSku_price() {
        return sku_price;
    }

    public void setSku_price(double sku_price) {
        this.sku_price = sku_price;
    }

    public int getSku_check() {
        return sku_check;
    }

    public void setSku_check(int sku_check) {
        this.sku_check = sku_check;
    }

    public int getSku_count() {
        return sku_count;
    }

    public void setSku_count(int sku_count) {
        this.sku_count = sku_count;
    }

    public String getSku_image() {
        return sku_image;
    }

    public void setSku_image(String sku_image) {
        this.sku_image = sku_image;
    }

    public String getSku_pki() {
        return sku_pki;
    }

    public void setSku_pki(String sku_pki) {
        this.sku_pki = sku_pki;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public double getStarts() {
        return starts;
    }

    public void setStarts(double starts) {
        this.starts = starts;
    }

    public String getDeliver() {
        return deliver;
    }

    public void setDeliver(String deliver) {
        this.deliver = deliver;
    }

    public int getCollect_state() {
        return collect_state;
    }

    public void setCollect_state(int collect_state) {
        this.collect_state = collect_state;
    }

    public String getBuy_count() {
        return buy_count;
    }

    public void setBuy_count(String buy_count) {
        this.buy_count = buy_count;
    }

    public ShopInfoBeanNew getShop_info() {
        return shop_info;
    }

    public void setShop_info(ShopInfoBeanNew shop_info) {
        this.shop_info = shop_info;
    }

    public List<TagBeanNew> getTag_list() {
        return tag_list;
    }

    public void setTag_list(List<TagBeanNew> tag_list) {
        this.tag_list = tag_list;
    }

    public List<Coupon_listBeanNew> getCoupon_list() {
        return coupon_list;
    }

    public void setCoupon_list(List<Coupon_listBeanNew> coupon_list) {
        this.coupon_list = coupon_list;
    }
}
