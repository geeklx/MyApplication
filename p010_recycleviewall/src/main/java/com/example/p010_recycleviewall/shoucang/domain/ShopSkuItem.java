package com.example.p010_recycleviewall.shoucang.domain;

/**
 * <p>function: </p>
 * <p>description:  </p>
 * <p>history:  1. 2016/11/22</p>
 * <p>Author: geek</p>
 * <p>modification:</p>
 */
public class ShopSkuItem {

    /**
     * sku_id : 11212233
     * sku_market_price : 40.0
     * sku_price : 11.0
     * sku_amount : 5
     * sku_image : http://fsonehaier.com.local/XXXXXX.jpg
     * sku_name : 两个装美国秘鲁以色列进口软籽石榴
     * sku_title : 新鲜孕妇安胎水果全国顺丰包邮
     * business_type_id : 1
     * business_type : 易果生鲜
     * business_color : #ffffff
     * business_image : http://fsonehaier.com.local/XXXXXX.jpg
     */

    private String sku_id;
    private double sku_market_price;
    private double sku_price;
    private int sku_amount;
    private String sku_image;
    private String sku_name;
    private String sku_title;
    private int business_type_id;
    private String business_type;
    private String business_color;
    private String business_image;
    private int is_goods; // 0代表商品，1代表非商品
    private int is_use;
    private boolean is_traceability;

    public boolean is_traceability() {
        return is_traceability;
    }

    public void setIs_traceability(boolean is_traceability) {
        this.is_traceability = is_traceability;
    }

    public int getIs_use() {
        return is_use;
    }

    public void setIs_use(int is_use) {
        this.is_use = is_use;
    }

    public String getSku_id() {
        return sku_id;
    }

    public void setSku_id(String sku_id) {
        this.sku_id = sku_id;
    }

    public double getSku_market_price() {
        return sku_market_price;
    }

    public void setSku_market_price(double sku_market_price) {
        this.sku_market_price = sku_market_price;
    }

    public double getSku_price() {
        return sku_price;
    }

    public void setSku_price(double sku_price) {
        this.sku_price = sku_price;
    }

    public int getSku_amount() {
        return sku_amount;
    }

    public void setSku_amount(int sku_amount) {
        this.sku_amount = sku_amount;
    }

    public String getSku_image() {
        return sku_image;
    }

    public void setSku_image(String sku_image) {
        this.sku_image = sku_image;
    }

    public String getSku_name() {
        return sku_name;
    }

    public void setSku_name(String sku_name) {
        this.sku_name = sku_name;
    }

    public String getSku_title() {
        return sku_title;
    }

    public void setSku_title(String sku_title) {
        this.sku_title = sku_title;
    }

    public int getBusiness_type_id() {
        return business_type_id;
    }

    public void setBusiness_type_id(int business_type_id) {
        this.business_type_id = business_type_id;
    }

    public String getBusiness_type() {
        return business_type;
    }

    public void setBusiness_type(String business_type) {
        this.business_type = business_type;
    }

    public String getBusiness_color() {
        return business_color;
    }

    public void setBusiness_color(String business_color) {
        this.business_color = business_color;
    }

    public String getBusiness_image() {
        return business_image;
    }

    public void setBusiness_image(String business_image) {
        this.business_image = business_image;
    }

    public int getIs_goods() {
        return is_goods;
    }

    public void setIs_goods(int is_goods) {
        this.is_goods = is_goods;
    }
}
