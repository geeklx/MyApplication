package com.example.p029_banner_lunbo.domain;

import java.io.Serializable;

public class Biaoge_listBean implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int img_url;
    private String text_content;

    public Biaoge_listBean() {
    }

    public Biaoge_listBean(int img_url, String text_content) {
        this.img_url = img_url;
        this.text_content = text_content;
    }

    public int getImg_url() {
        return img_url;
    }

    public void setImg_url(int img_url) {
        this.img_url = img_url;
    }

    public String getText_content() {
        return text_content;
    }

    public void setText_content(String text_content) {
        this.text_content = text_content;
    }
}
