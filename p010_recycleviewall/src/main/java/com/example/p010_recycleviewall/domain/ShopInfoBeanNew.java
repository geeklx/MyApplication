
package com.example.p010_recycleviewall.domain;

import java.io.Serializable;

/**
 * Created by geek on 2016/4/6.
 */
public class ShopInfoBeanNew implements Serializable {
    private static final long serialVersionUID = 1L;
    private String shop_id;
    private String shop_name;
    private String distance;
    private String shop_image;

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getShop_image() {
        return shop_image;
    }

    public void setShop_image(String shop_image) {
        this.shop_image = shop_image;
    }
}
