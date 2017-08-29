package com.shining.p010_recycleviewall.domain;

import java.io.Serializable;

/**
 * Created by geek on 2016/2/25.
 */
public class Scroll_listBean implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;
    private String text_content;
    private String img_url;

    public Scroll_listBean() {
    }

    public Scroll_listBean(String id, String text_content, String img_url) {
        this.id = id;
        this.text_content = text_content;
        this.img_url = img_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText_content() {
        return text_content;
    }

    public void setText_content(String text_content) {
        this.text_content = text_content;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
